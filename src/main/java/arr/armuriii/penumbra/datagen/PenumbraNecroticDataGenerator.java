package arr.armuriii.penumbra.datagen;

import static arr.armuriii.penumbra.init.PNBlocks.*;
import static arr.armuriii.penumbra.init.PNItems.*;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PenumbraNecroticDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(LanguageProvider::new);
		pack.addProvider(ModelProvider::new);
	}

	static class LanguageProvider extends FabricLanguageProvider {
		protected LanguageProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
			super(packOutput, "en_us", registryLookup);
		}

		@Override
		public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, @NonNull TranslationBuilder translationBuilder) {
			translationBuilder.add(SHADOW_CLOTH,"Shadow Cloth");

			translationBuilder.add(SHADOW_WEAVINGS,"Shadow Weavings");
		}
	}

	static class ModelProvider extends FabricModelProvider {

		public ModelProvider(FabricPackOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(@NonNull BlockModelGenerators gen) {
			gen.createTrivialBlock(SHADOW_WEAVINGS, TexturedModel.COLUMN_ALT);
		}

		@Override
		public void generateItemModels(@NonNull ItemModelGenerators gen) {
			gen.generateFlatItem(SHADOW_CLOTH, ModelTemplates.FLAT_ITEM);
		}
	}
}
