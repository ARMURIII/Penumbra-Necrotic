package arr.armuriii.reg.inits;

import arr.armuriii.reg.ModRegisterInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public interface ItemRegisterInitializer extends ModRegisterInitializer<Item> {

    @Override
    default Registry<Item> registry() {
        return BuiltInRegistries.ITEM;
    }

    @Override
    default Item modifyEntry(Item instance, ResourceKey<Item> key, Identifier id) {
        instance.PN$getProperties().setId(key);

        instance.descriptionId = instance.PN$getProperties().effectiveDescriptionId();

        DataComponentInitializers.Initializer<Item> componentInitializer = instance.PN$getProperties().finalizeInitializer(Component.translatable(instance.getDescriptionId()),
                instance.PN$getProperties().effectiveModel());
        BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(key, componentInitializer);
        return instance;
    }
}
