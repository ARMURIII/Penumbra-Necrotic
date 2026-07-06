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
    public static void registerInit(String prefix) {
        Reflections reflections = new Reflections(prefix);

        Set<Class<?>> annotated =
                reflections.getTypesAnnotatedWith(RegModule.class);



        for (Class<?> clazz : annotated) {

            var regModule = clazz.getAnnotation(RegModule.class);

            try {
                var inst = clazz.getConstructor().newInstance();
                Registry<?> reg = null;
                if (Arrays.stream(clazz.getInterfaces()).anyMatch(inter->inter==ModRegisterInitializer.class)) {
                    clazz.getMethod("init").invoke(inst);
                    if (clazz.getMethod("registry").invoke(inst) instanceof Registry<?> registry)
                        reg = registry;
                }

                if (reg instanceof Registry<?> registry) {
                    System.out.println("GOT: "+reg);
                    for (Field field : ReflectionUtils.get(Fields.of(clazz))) {
                         var entry = field.getAnnotation(RegModule.RegistryEntry.class);
                         if (entry == null)
                             continue;

                        System.out.println("FIELD: "+field.getName());

                         var pair = getPair(field.get(null),registry);

                         if (pair.getA() == null || pair.getB() == null)
                             continue;

                         String id = Objects.equals(entry.id(), "") ? field.getName().toLowerCase() : entry.id();

                        var onRegister = clazz.getMethod("onRegister", Object.class, ResourceKey.class,Identifier.class);

                        register(pair.getA(),pair.getB(),Identifier.fromNamespaceAndPath(regModule.modid(),id),((o, resourceKey, identifier) -> {
                            try {
                                return onRegister.invoke(inst,o,resourceKey,identifier);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e);
                            }
                        }));
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
    private static <T> void register(T instance, Registry<T> registry, Identifier id, TriFunction<Object,ResourceKey<?>,Identifier,Object> onRegister) {
        ResourceKey<T> key = ResourceKey.create(registry.key(), id);
        instance = (T) onRegister.apply(instance, key,id);
        Registry.register(registry, key, instance);
    }
}
