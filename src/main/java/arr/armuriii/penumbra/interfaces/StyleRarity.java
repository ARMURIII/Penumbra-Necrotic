package arr.armuriii.penumbra.interfaces;

import net.minecraft.network.chat.Style;

public interface StyleRarity {
    default Style PN$withStyle(Style style) {
        throw new AssertionError("Implemented in Mixin");
    }

    default Style PN$getStyle() {
        return PN$withStyle(Style.EMPTY);
    }
}
