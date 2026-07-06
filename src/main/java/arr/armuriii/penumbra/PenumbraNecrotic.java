package arr.armuriii.penumbra;

import arr.armuriii.reg.RegHelper;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PenumbraNecrotic implements ModInitializer {
	public static final String MOD_ID = "penumbra-necrotic";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RegHelper.registerInit("arr.armuriii.penumbra");
    }

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
