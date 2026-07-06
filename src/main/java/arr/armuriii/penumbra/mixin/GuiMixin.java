package arr.armuriii.penumbra.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow
    private ItemStack lastToolHighlight;

    @WrapOperation(method = "extractSelectedItemName",at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/MutableComponent;withStyle(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;",ordinal = 0))
    private MutableComponent PN$applyRarity(MutableComponent instance, ChatFormatting format, Operation<MutableComponent> original) {
        return instance.withStyle(this.lastToolHighlight.getRarity().PN$getStyle());
    }
}
