package org.spearedrice.asianmod.datagen.internal;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.data.models.model.ModelLocationUtils;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.block.custom.OilLampBlock;
import org.spearedrice.asianmod.block.custom.AbacusBlock;
import org.spearedrice.asianmod.item.ModItems;





public class AsianModInternalModelProvider extends FabricModelProvider {

    public AsianModInternalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {


        gen.createSimpleBlock(ModBlocks.ABACUS_BLOCK, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.ABACUS_BLOCK)));


        gen.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(ModBlocks.OIL_LAMP)
                        .with(BlockModelGenerators.createBooleanModelDispatch(
                                OilLampBlock.ACTIVATED,
                                BlockModelGenerators.plainVariant(
                                        gen.createSuffixedVariant(ModBlocks.OIL_LAMP, "_on",
                                                ModelTemplates.CUBE_ALL,
                                                TextureMapping::cube
                                        )
                                ),
                                BlockModelGenerators.plainVariant(
                                        TexturedModel.CUBE.create(ModBlocks.OIL_LAMP, gen.modelOutput)
                                )
                        ))
        );









        gen.createCrossBlock(ModBlocks.PORCELAIN_LEAVES,
                BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {


        gen.generateFlatItem(ModItems.SLIPPER, ModelTemplates.FLAT_ITEM);


        gen.generateFlatItem(ModItems.PORCELAIN, ModelTemplates.FLAT_ITEM);


        gen.generateFlatItem(ModItems.NEPHRITE_HELMET, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.NEPHRITE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.NEPHRITE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.NEPHRITE_BOOTS, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.NEPHRITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);


        gen.generateFlatItem(ModItems.ABACUS, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "AsianMod Internal Model Provider";
    }
}