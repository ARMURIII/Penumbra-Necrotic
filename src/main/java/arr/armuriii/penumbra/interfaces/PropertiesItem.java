package arr.armuriii.penumbra.interfaces;

import net.minecraft.world.item.Item;

public interface PropertiesItem {
    default Item.Properties PN$getProperties() {
        throw new AssertionError("Implemented in Mixin");
    }
}
