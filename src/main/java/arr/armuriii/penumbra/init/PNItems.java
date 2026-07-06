package arr.armuriii.penumbra.init;

import arr.armuriii.penumbra.PenumbraNecrotic;
import arr.armuriii.reg.RegModule;
import arr.armuriii.reg.inits.ItemRegisterInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import static net.minecraft.world.item.CreativeModeTabs.*;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

@SuppressWarnings("unused")
@RegModule(modid = PenumbraNecrotic.MOD_ID,priority = 500)
public class PNItems implements ItemRegisterInitializer {

    @RegModule.RegistryEntry
    public static Item SHADOW_CLOTH = new Item(new Item.Properties().rarity(Rarity.PENUMBRA_NECROTIC_PENUMBRA));

    @Override
    public void postRegister() {
        CreativeModeTabEvents.modifyOutputEvent(INGREDIENTS).register(output -> output.accept(SHADOW_CLOTH));
    }
}
