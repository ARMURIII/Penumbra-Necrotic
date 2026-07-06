package arr.armuriii.reg.interfaces;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface PropertiesBlockBehaviour {
    default BlockBehaviour.Properties PN$getProperties() {
        throw new AssertionError("Implemented in Mixin");
    }

    default Item.Properties PN$getItemProperties() {
        throw new AssertionError("Implemented in Mixin");
    }

    default BlockBehaviour PN$setItemProperties(Item.Properties properties) {
        throw new AssertionError("Implemented in Mixin");
    }
}
