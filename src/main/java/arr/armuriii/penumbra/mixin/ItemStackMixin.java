package arr.armuriii.penumbra.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Rarity getRarity();

    @WrapOperation(method = "getStyledHoverName",at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;withStyle(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;",ordinal = 0))
    private MutableComponent PN$applyRarity(MutableComponent instance, ChatFormatting format, Operation<MutableComponent> original) {
        return instance.withStyle(this.getRarity().PN$getStyle());
    }

    @WrapOperation(method = "getDisplayName",at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;withStyle(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;",ordinal = 1))
    private MutableComponent PN$applyRarityHover(MutableComponent instance, ChatFormatting format, Operation<MutableComponent> original) {
        return instance.withStyle(this.getRarity().PN$getStyle());
    }
}
