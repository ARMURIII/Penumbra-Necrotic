package arr.armuriii.reg;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

@SuppressWarnings("unused")
public interface ModRegisterInitializer<T> {
    void init();

    Registry<T> registry();

    default T onRegister(T instance, ResourceKey<T> key, Identifier id) {
        return instance;
    }
}
