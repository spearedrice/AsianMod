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
import org.spearedrice.asianmod.block.custom.VerticalSlabBlock;
import org.spearedrice.asianmod.item.ModItems;

public class AsianModModelProvider extends FabricModelProvider {

    public AsianModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {

        gen.createTrivialCube(ModBlocks.STEEL_BLOCK);

        gen.createTrivialBlock(ModBlocks.PIPE_BLOCK, TexturedModel.COLUMN_ALT);

        gen.family(ModBlocks.RUBY_BLOCK)
                .stairs(ModBlocks.RUBY_STAIRS)
                .slab(ModBlocks.RUBY_SLAB)
                .fence(ModBlocks.RUBY_FENCE);

        gen.createDoor(ModBlocks.RUBY_DOOR);
        gen.createTrapdoor(ModBlocks.RUBY_TRAPDOOR);

        CustomBlockStateModelGenerator.registerVerticalSlab(
                gen,
                ModBlocks.VERTICAL_OAK_LOG_SLAB,
                Blocks.OAK_LOG,
                CustomBlockStateModelGenerator.blockAndTopForEnds(Blocks.OAK_LOG)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {

        gen.generateFlatItem(ModItems.RUBY, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(ModItems.MINI_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(ModItems.NEPHRITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Slippers (i guess bro)
        gen.generateDyedItem(ModItems.SLIPPERS, 0xFFA06540);

        gen.generateBooleanDispatch(
                ModItems.FLASHLIGHT,
                ItemModelUtils.isUsingItem(),
                ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.FLASHLIGHT, "_lit", ModelTemplates.FLAT_ITEM)),
                ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.FLASHLIGHT, ModelTemplates.FLAT_ITEM))
        );

        ItemModel.Unbaked knifeOne = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.THROWING_KNIVES, "_one", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked knifeTwo = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.THROWING_KNIVES, "_two", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked knifeThree = ItemModelUtils.plainModel(gen.createFlatItemModel(ModItems.THROWING_KNIVES, "_three", ModelTemplates.FLAT_ITEM));

        gen.itemModelOutput.accept(
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

        CustomItemModelGenerator.registerScaled2x(ModItems.BALLOON, gen);
    }

    @Override
    public String getName() {
        return "AsianMod Model Provider";
    }

    // =========================
    // ITEM MODEL HELPERS
    // =========================

    public static class CustomItemModelGenerator {

        public static final ModelTemplate SCALED2X =
                item("scaled2x", TextureSlot.LAYER0);

        public static void registerScaled2x(Item item, ItemModelGenerators gen) {
            Identifier model = SCALED2X.create(
                    item,
                    TextureMapping.singleSlot(TextureSlot.LAYER0, ModelLocationUtils.getModelLocation(item)),
                    gen.modelOutput
            );

            gen.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
        }

        private static ModelTemplate item(String parent, TextureSlot slot) {
            return new ModelTemplate(
                    Optional.of(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "item/" + parent)),
                    Optional.empty(),
                    slot
            );
        }
    }

    // =========================
    // BLOCK MODEL HELPERS
    // =========================

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