package arr.armuriii.reg;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

@SuppressWarnings("unused")
public interface ModRegisterInitializer<T> {
    default void preRegister() {

    }
    default void postRegister() {

    }

    default void onParameter(String param,T instance, ResourceKey<T> key, Identifier id) {

    }

    Registry<T> registry();

    default T register(T instance, Registry<T> registry, Identifier id,ResourceKey<T> key) {
        return Registry.register(registry, key, instance);
    }

    default T modifyEntry(T instance, ResourceKey<T> key, Identifier id) {
        return instance;
    }
}
