package arr.armuriii.penumbra.init;

import arr.armuriii.penumbra.PenumbraNecrotic;
import arr.armuriii.reg.RegModule;
import arr.armuriii.reg.inits.BlockRegisterInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.item.CreativeModeTabs.INGREDIENTS;

@SuppressWarnings("unused")
@RegModule(modid = PenumbraNecrotic.MOD_ID)
public class PNBlocks implements BlockRegisterInitializer {

    @RegModule.RegistryEntry
    public static Block SHADOW_WEAVINGS = (Block) new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL))
            .PN$setItemProperties(new Item.Properties().useBlockDescriptionPrefix().rarity(Rarity.PENUMBRA_NECROTIC_PENUMBRA));

    @Override
    public void postRegister() {
        CreativeModeTabEvents.modifyOutputEvent(INGREDIENTS).register(output -> output.accept(SHADOW_WEAVINGS));
    }
}
