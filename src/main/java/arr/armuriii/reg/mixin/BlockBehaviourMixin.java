
package arr.armuriii.reg.mixin;

import arr.armuriii.reg.interfaces.PropertiesBlockBehaviour;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin implements PropertiesBlockBehaviour {

    @Unique
    private BlockBehaviour.Properties properties;
    @Unique
    private Item.Properties itemProperties;

    @ModifyReceiver(method = "<init>",at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour;hasCollision:Z", opcode = Opcodes.PUTFIELD))
    private static BlockBehaviour rememberProperties(BlockBehaviour instance, boolean value, BlockBehaviour.Properties properties) {
        ((BlockBehaviourMixin)(Object)instance).properties = properties;
        return instance;
    }

    @WrapOperation(method = "<init>",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;effectiveDescriptionId()Ljava/lang/String;"))
    private String PN$doNotThrowErrorsDumbass(BlockBehaviour.Properties instance, Operation<String> original) {
        try {
            return original.call(instance);
        } catch (Exception ignored) {
            return null;
        }
    }

    @WrapOperation(method = "<init>",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;effectiveDrops()Ljava/util/Optional;"))
    private Optional<ResourceKey<LootTable>> PN$doNotThrowErrorsBuddy(BlockBehaviour.Properties instance, Operation<Optional<ResourceKey<LootTable>>> original) {
        try {
            return original.call(instance);
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    @Override
    public BlockBehaviour.Properties PN$getProperties() {
        return properties;
    }

    @Override
    public Item.Properties PN$getItemProperties() {
        return itemProperties;
    }

    @Override
    public BlockBehaviour PN$setItemProperties(Item.Properties properties) {
        this.itemProperties = properties;
        return (BlockBehaviour)(Object)this;
    }
}
