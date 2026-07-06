package arr.armuriii.penumbra.mixin;

import arr.armuriii.penumbra.interfaces.StyleRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Rarity.class)
public enum RarityMixin implements StyleRarity {
    PENUMBRA_NECROTIC_PENUMBRA(4,"penumbra",ChatFormatting.DARK_PURPLE) {
        @Override
        public Style PN$withStyle(Style style) {
             return style.withColor(0xE0B0FF); //mauve
        }
    };

    @Shadow
    RarityMixin(int id, String name, ChatFormatting color) {
    }

    @Shadow
    public ChatFormatting color() {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Override
    public Style PN$withStyle(Style style) {
        return style.applyFormat(this.color());
    }
}
