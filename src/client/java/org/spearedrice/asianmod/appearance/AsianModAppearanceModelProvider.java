package org.spearedrice.asianmod.appearance;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.item.ModItems;

public class AsianModAppearanceModelProvider extends FabricModelProvider {

    public AsianModAppearanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {

        gen.createSimpleBlock(ModBlocks.PORCELAIN_ORE);

        gen.createRotatedPillar(ModBlocks.PORCELAIN_LOG);

        gen.createCrossBlock(ModBlocks.PORCELAIN_LEAVES, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {

        gen.generateFlatItem(ModItems.SLIPPER, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(ModBlocks.PORCELAIN_ORE.asItem(), ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "AsianMod Appearance Models";
    }
}