package arr.armuriii.reg.mixin;

import arr.armuriii.reg.interfaces.PropertiesItem;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin implements PropertiesItem {

    @Unique
    private Item.Properties properties;

    @ModifyReceiver(method = "<init>",at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/Item;builtInRegistryHolder:Lnet/minecraft/core/Holder$Reference;", opcode = Opcodes.PUTFIELD))
    private static Item rememberProperties(Item instance, Holder.Reference<Item> value, Item.Properties properties) {
        ((ItemMixin)(Object)instance).properties = properties;
        return instance;
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;itemIdOrThrow()Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<Item> doNotThrow(Item.Properties instance, Operation<ResourceKey<Item>> original) {
        try {
            return original.call(instance);
        } catch (Exception ignored) {
            return null;
        }
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;effectiveModel()Lnet/minecraft/resources/Identifier;"))
    private Identifier doNotThrowBruh(Item.Properties instance, Operation<Identifier> original) {
        try {
            return original.call(instance);
        } catch (Exception ignored) {
            return null;
        }
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;finalizeInitializer(Lnet/minecraft/network/chat/Component;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/core/component/DataComponentInitializers$Initializer;"))
    private DataComponentInitializers.Initializer<Item> doNotThrow(Item.Properties instance, Component name, Identifier model, Operation<DataComponentInitializers.Initializer<Item>> original) {
        try {
            return original.call(instance, name, model);
        } catch (Exception ignored) {
            return null;
        }
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;effectiveDescriptionId()Ljava/lang/String;"))
    private String doNotThrowBro(Item.Properties instance, Operation<String> original) {
        try {
            return original.call(instance);
        } catch (Exception ignored) {
            return null;
        }
    }

    @WrapWithCondition(method = "<init>",at = @At(value = "INVOKE", target = "Lnet/minecraft/core/component/DataComponentInitializers;add(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/component/DataComponentInitializers$Initializer;)V"))
    private <T> boolean ignoreDataComponentForNow(DataComponentInitializers instance, ResourceKey<T> key, DataComponentInitializers.Initializer<T> initializer) {
        return key != null;
    }


    @Override
    public Item.Properties PN$getProperties() {
        return properties;
    }
}
