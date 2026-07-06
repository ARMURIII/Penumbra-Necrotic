package arr.armuriii.reg.inits;

import arr.armuriii.reg.ModRegisterInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface BlockRegisterInitializer extends ModRegisterInitializer<Block> {

    @Override
    default Registry<Block> registry() {
        return BuiltInRegistries.BLOCK;
    }

    @Override
    default Block register(Block block, Registry<Block> registry, Identifier id, ResourceKey<Block> key) {
        var registered = ModRegisterInitializer.super.register(block,registry,id,key);
        if (block.PN$getItemProperties() != null) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
            Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block,block.PN$getItemProperties().setId(itemKey)));
        }
        return registered;
    }

    @Override
    default Block modifyEntry(Block instance, ResourceKey<Block> key, Identifier id) {
        instance.PN$getProperties().setId(key);
        instance.drops = instance.PN$getProperties().effectiveDrops();
        instance.descriptionId = instance.PN$getProperties().effectiveDescriptionId();
        return instance;
    }
}
