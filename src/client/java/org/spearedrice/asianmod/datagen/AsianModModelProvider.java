package org.spearedrice.asianmod.datagen;

 import java.util.List;
 import java.util.Optional;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Count;
import net.minecraft.client.renderer.item.properties.select.ContextDimension;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.block.custom.OilLampBlock;
import org.spearedrice.asianmod.block.custom.VerticalSlabBlock;
import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.recipe.AsianModRecipes;

public class AsianModModelProvider extends FabricModelProvider {

    public AsianModModelProvider(FabricDataOutput output) {
        super(output);
    }

	@Override
	public void generateBlockStateModels(BlockModelGenerators gen) {

		gen.createTrivialCube(ModBlocks.STEEL_BLOCK);

		gen.createTrivialCube(ModBlocks.RAMMED_EARTH);
		gen.createTrivialBlock(ModBlocks.COMPACTED_TIMBER, TexturedModel.COLUMN);
		gen.createTrivialCube(ModBlocks.ABACUS_BLOCK);
		gen.createTrivialCube(ModBlocks.BRASS_CHEST);
		gen.createTrivialCube(ModBlocks.BELLOWS_BLOCK);
		gen.createTrivialBlock(ModBlocks.CLAY_PIPE, TexturedModel.COLUMN);

		Identifier oilLampModel = TexturedModel.CUBE.create(ModBlocks.OIL_LAMP, gen.modelOutput);
		gen.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.OIL_LAMP)
				.with(BlockModelGenerators.createBooleanModelDispatch(OilLampBlock.ACTIVATED,
						BlockModelGenerators.plainVariant(gen.createSuffixedVariant(ModBlocks.OIL_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube)),
						BlockModelGenerators.plainVariant(oilLampModel))));
		gen.registerSimpleItemModel(ModBlocks.OIL_LAMP, oilLampModel);

		gen.createTrivialCube(ModBlocks.LINGZHI);
		gen.createTrivialCube(ModBlocks.VERTICAL_BAMBOO_SLAB);

		gen.createTrivialCube(ModBlocks.NEPHRITE_BLOCK);
		gen.createDoor(ModBlocks.NEPHRITE_DOOR);


		gen.createTrivialCube(ModBlocks.NEPHRITE_JADE_BLOCK);
		gen.createTrivialCube(ModBlocks.RAW_NEPHRITE_JADE_BLOCK);
		gen.createTrivialCube(ModBlocks.NEPHRITE_JADE_ORE);
		gen.createTrivialCube(ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE);

		gen.createTrivialCube(ModBlocks.SKY_LANTERN_BLOCK);

		gen.family(ModBlocks.PORCELAIN_BLOCK)
				.stairs(ModBlocks.PORCELAIN_STAIRS)
				.slab(ModBlocks.PORCELAIN_SLAB)
				.fence(ModBlocks.PORCELAIN_FENCE);

	}

	@Override
	public void generateItemModels(ItemModelGenerators gen) {
		gen.generateFlatItem(ModItems.NEPHRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_HELMET, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_LEGGINGS, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_BOOTS, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

		gen.generateFlatItem(ModItems.ABACUS, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.NEPHRITE_JADE, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.INCENSE_STICK, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.FERMENTED_RESIDUE, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.SLIPPER, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.COW_DUNG, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.PORCELAIN, ModelTemplates.FLAT_ITEM);
		gen.generateDyedItem(ModItems.SILK_GLOVES, 0xFFA06540);
		gen.generateFlatItem(ModItems.SKY_LANTERN, ModelTemplates.FLAT_ITEM);
		gen.generateBooleanDispatch(
				ModItems.PAPER_LANTERN,
				ItemModelUtils.isUsingItem(),
				ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.PAPER_LANTERN, "_lit", ModelTemplates.FLAT_ITEM)),
				ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.PAPER_LANTERN, ModelTemplates.FLAT_ITEM))
		);
		ItemModel.Unbaked needleOne = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.FLYING_NEEDLE, "_one", ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked needleTwo = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.FLYING_NEEDLE, "_two", ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked needleThree = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.FLYING_NEEDLE, "_three", ModelTemplates.FLAT_ITEM));
		gen.itemModelOutput.accept(
				ModItems.FLYING_NEEDLE,
				ItemModelUtils.rangeSelect(
						new Count(false),
						List.of(
								ItemModelUtils.override(needleOne, 1.0F),
								ItemModelUtils.override(needleTwo, 2.0F),
								ItemModelUtils.override(needleThree, 3.0F)
						)
				)
		);
		gen.generateFlatItem(ModItems.FUGU, ModelTemplates.FLAT_ITEM);
		gen.generateFlatItem(ModItems.MERCURY_BUCKET, ModelTemplates.FLAT_ITEM);
		ItemModel.Unbaked pearlOverworld = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.DRAGON_PEARL, "_overworld", ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked pearlNether = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.DRAGON_PEARL, "_nether", ModelTemplates.FLAT_ITEM));
		ItemModel.Unbaked pearlEnd = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.DRAGON_PEARL, "_end", ModelTemplates.FLAT_ITEM));
		gen.itemModelOutput.accept(
				ModItems.DRAGON_PEARL,
				ItemModelUtils.select(new ContextDimension(),
						ItemModelUtils.when(Level.OVERWORLD, pearlOverworld),
						ItemModelUtils.when(Level.NETHER, pearlNether),
						ItemModelUtils.when(Level.END, pearlEnd)
				)
		);
	}

	@Override
	public String getName() {
		return "AsianMod Model Provider";
	}


	public static class CustomBlockStateModelGenerator {

		public static final ModelTemplate VERTICAL_SLAB =
				block("vertical_slab", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);

		private static ModelTemplate block(String parent, TextureSlot... slots) {
			return new ModelTemplate(
					Optional.of(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "block/" + parent)),
					Optional.empty(),
					slots
			);
		}

        public static TextureMapping blockAndTopForEnds(Block block) {
            return new TextureMapping()
                    .put(TextureSlot.TOP, ModelLocationUtils.getModelLocation(block, "_top"))
                    .put(TextureSlot.BOTTOM, ModelLocationUtils.getModelLocation(block, "_top"))
                    .put(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(block));
        }

        public static void registerVerticalSlab(
                BlockModelGenerators gen,
                Block slab,
                Block fullBlock,
                TextureMapping textures
        ) {
            Identifier slabModel = VERTICAL_SLAB.create(slab, textures, gen.modelOutput);
            Identifier fullModel = ModelLocationUtils.getModelLocation(fullBlock);

            gen.blockStateOutput.accept(
                    createVerticalSlabBlockStates(slab, slabModel, fullModel)
            );

            gen.registerSimpleItemModel(slab, slabModel);
        }

        private static BlockModelDefinitionGenerator createVerticalSlabBlockStates(
                Block slab,
                Identifier slabModel,
                Identifier fullModel
        ) {

            MultiVariant slabVariant = BlockModelGenerators.plainVariant(slabModel);
            MultiVariant fullVariant = BlockModelGenerators.plainVariant(fullModel);

            return MultiVariantGenerator.dispatch(slab)
                    .with(PropertyDispatch.initial(VerticalSlabBlock.FACING, VerticalSlabBlock.SINGLE)
                            .select(Direction.NORTH, true, slabVariant)
                            .select(Direction.EAST, true, slabVariant.with(BlockModelGenerators.Y_ROT_90))
                            .select(Direction.SOUTH, true, slabVariant.with(BlockModelGenerators.Y_ROT_180))
                            .select(Direction.WEST, true, slabVariant.with(BlockModelGenerators.Y_ROT_270))
                            .select(Direction.NORTH, false, fullVariant)
                            .select(Direction.EAST, false, fullVariant)
                            .select(Direction.SOUTH, false, fullVariant)
                            .select(Direction.WEST, false, fullVariant)
                    );
        }
    }
}