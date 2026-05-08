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
import org.spearedrice.asianmod.block.custom.VerticalSlabBlock;
import corg.spearedrice.asianmod.item.ModItems;

public class AsianModModelProvider extends FabricModelProvider {

    public AsianModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        blockStateModelGenerator.createTrivialCube(ModBlocks.STEEL_BLOCK);

        blockStateModelGenerator.createTrivialBlock(
                ModBlocks.PIPE_BLOCK,
                TexturedModel.COLUMN_ALT
        );

        blockStateModelGenerator.family(ModBlocks.RUBY_BLOCK)
                .stairs(ModBlocks.RUBY_STAIRS)
                .slab(ModBlocks.RUBY_SLAB)
                .fence(ModBlocks.RUBY_FENCE);

        blockStateModelGenerator.createDoor(ModBlocks.RUBY_DOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.RUBY_TRAPDOOR);

        CustomBlockStateModelGenerator.registerVerticalSlab(
                blockStateModelGenerator,
                ModBlocks.VERTICAL_OAK_LOG_SLAB,
                Blocks.OAK_LOG,
                CustomBlockStateModelGenerator.blockAndTopForEnds(Blocks.OAK_LOG)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

        itemModelGenerator.generateFlatItem(ModItems.RUBY, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MINI_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.ENHANCED_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);

        // 🩴 slippers
        itemModelGenerator.generateDyedItem(ModItems.SLIPPERS, 0xFFFFFF);

        // porcelain progression items
        itemModelGenerator.generateFlatItem(ModItems.PORCELAIN_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PORCELAIN, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateBooleanDispatch(
                ModItems.FLASHLIGHT,
                ItemModelUtils.isUsingItem(),
                ItemModelUtils.plainModel(
                        itemModelGenerator.createFlatItemModel(ModItems.FLASHLIGHT, "_lit", ModelTemplates.FLAT_ITEM)
                ),
                ItemModelUtils.plainModel(
                        itemModelGenerator.createFlatItemModel(ModItems.FLASHLIGHT, ModelTemplates.FLAT_ITEM)
                )
        );

        ItemModel.Unbaked knifeOne = ItemModelUtils.plainModel(
                itemModelGenerator.createFlatItemModel(ModItems.THROWING_KNIVES, "_one", ModelTemplates.FLAT_ITEM)
        );

        ItemModel.Unbaked knifeTwo = ItemModelUtils.plainModel(
                itemModelGenerator.createFlatItemModel(ModItems.THROWING_KNIVES, "_two", ModelTemplates.FLAT_ITEM)
        );

        ItemModel.Unbaked knifeThree = ItemModelUtils.plainModel(
                itemModelGenerator.createFlatItemModel(ModItems.THROWING_KNIVES, "_three", ModelTemplates.FLAT_ITEM)
        );

        itemModelGenerator.itemModelOutput.accept(
                ModItems.THROWING_KNIVES,
                ItemModelUtils.rangeSelect(
                        new Count(false),
                        List.of(
                                ItemModelUtils.override(knifeOne, 1.0F),
                                ItemModelUtils.override(knifeTwo, 2.0F),
                                ItemModelUtils.override(knifeThree, 3.0F)
                        )
                )
        );
    }

    @Override
    public String getName() {
        return "AsianModModelProvider";
    }

    // =========================
    // CUSTOM BLOCK MODEL STUFF
    // =========================
    public static class CustomBlockStateModelGenerator {

        public static final ModelTemplate VERTICAL_SLAB =
                block("vertical_slab", TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);

        private static ModelTemplate block(String parent, TextureSlot... keys) {
            return new ModelTemplate(
                    Optional.of(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "block/" + parent)),
                    Optional.empty(),
                    keys
            );
        }

        public static TextureMapping blockAndTopForEnds(Block block) {
            return new TextureMapping()
                    .put(TextureSlot.TOP, ModelLocationUtils.getModelLocation(block, "_top"))
                    .put(TextureSlot.BOTTOM, ModelLocationUtils.getModelLocation(block, "_top"))
                    .put(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(block));
        }

        public static void registerVerticalSlab(
                BlockModelGenerators generator,
                Block slab,
                Block fullBlock,
                TextureMapping textures
        ) {
            Identifier slabModel =
                    VERTICAL_SLAB.create(slab, textures, generator.modelOutput);

            Identifier fullModel =
                    ModelLocationUtils.getModelLocation(fullBlock);

            generator.blockStateOutput.accept(
                    MultiVariantGenerator.dispatch(slab)
                            .with(PropertyDispatch.initial(VerticalSlabBlock.FACING, VerticalSlabBlock.SINGLE)
                                    .select(Direction.NORTH, true, BlockModelGenerators.plainVariant(slabModel))
                                    .select(Direction.EAST, true, BlockModelGenerators.plainVariant(slabModel))
                                    .select(Direction.SOUTH, true, BlockModelGenerators.plainVariant(slabModel))
                                    .select(Direction.WEST, true, BlockModelGenerators.plainVariant(slabModel))
                                    .select(Direction.NORTH, false, BlockModelGenerators.plainVariant(fullModel))
                                    .select(Direction.EAST, false, BlockModelGenerators.plainVariant(fullModel))
                                    .select(Direction.SOUTH, false, BlockModelGenerators.plainVariant(fullModel))
                                    .select(Direction.WEST, false, BlockModelGenerators.plainVariant(fullModel))
                            )
            );

            generator.registerSimpleItemModel(slab, slabModel);
        }
    }
}