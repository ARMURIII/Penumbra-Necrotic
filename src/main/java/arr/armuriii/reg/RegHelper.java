package arr.armuriii.reg;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.function.TriFunction;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import oshi.util.tuples.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.reflections.ReflectionUtils.Fields;

public class RegHelper {

    @SuppressWarnings("unchecked")
    public static void registerInit(String prefix) {
        Reflections reflections = new Reflections(prefix);

        List<Class<?>> annotated =
                reflections.getTypesAnnotatedWith(RegModule.class)
                .stream().sorted(Comparator.comparingInt(
                c-> c.getAnnotation(RegModule.class
                ).priority())).toList();

        for (Class<?> clazz : annotated) {

            var regModule = clazz.getAnnotation(RegModule.class);
            try {
                ModRegisterInitializer<?> inst = (ModRegisterInitializer<?>) clazz.getConstructor().newInstance();
                Registry<?> reg = inst.registry();
                inst.preRegister();

                if (reg instanceof Registry<?> registry) {
                    for (Field field : ReflectionUtils.get(Fields.of(clazz))) {
                         var entry = field.getAnnotation(RegModule.RegistryEntry.class);
                         if (entry == null)
                             continue;

                         var pair = getPair(field.get(null),registry);

                         if (pair.getA() == null || pair.getB() == null)
                             continue;

                         String id = Objects.equals(entry.id(), "") ? field.getName().toLowerCase() : entry.id();

                        var modifyEntry = clazz.getMethod("modifyEntry", Object.class, ResourceKey.class,Identifier.class);

                        var onParam = clazz.getMethod("onParameter", String.class, Object.class, ResourceKey.class,Identifier.class);

                        var pairValueKey = register(pair.getA(),pair.getB(),Identifier.fromNamespaceAndPath(regModule.modid(),id),((o, resourceKey, identifier) -> {
                            try {
                                return modifyEntry.invoke(inst,o,resourceKey,identifier); // idc about cast errors
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }),(ModRegisterInitializer<Object>) inst);

                        field.set(null,pairValueKey.getA());

                        for (String param : entry.params())
                            onParam.invoke(inst,param,pair.getA(),pairValueKey.getB(),Identifier.fromNamespaceAndPath(regModule.modid(),id));

                        inst.postRegister();
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Pair<T,Registry<T>> getPair(T object, Registry<?> registry) {
        try {
            return new Pair<>(object,(Registry<T>) registry);
        }catch (Exception ignored) {
            return new Pair<>(null,null);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Pair<T,ResourceKey<T>> register(T instance, Registry<T> registry, Identifier id, TriFunction<Object,ResourceKey<?>,Identifier,Object> modifyEntry,ModRegisterInitializer<T> init) {
        ResourceKey<T> key = ResourceKey.create(registry.key(), id);
        instance = (T) modifyEntry.apply(instance, key,id);
        return new Pair<>(init.register(instance,registry,id,key),key);
    }
}
