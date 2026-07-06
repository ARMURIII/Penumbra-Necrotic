package arr.armuriii.penumbra.init;

import arr.armuriii.penumbra.PenumbraNecrotic;
import arr.armuriii.reg.RegModule;
import arr.armuriii.reg.inits.ItemRegisterInitializer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

@RegModule(modid = PenumbraNecrotic.MOD_ID)
@SuppressWarnings("unused")
public class PNItems implements ItemRegisterInitializer {

    @RegModule.RegistryEntry()
    public static final Item TEST = new Item(new Item.Properties().rarity(Rarity.RARE));

    @Override
    public void init() {
    }
}
