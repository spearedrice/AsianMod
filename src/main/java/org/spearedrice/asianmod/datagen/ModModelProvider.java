package org.spearedrice.asianmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NEPHRITE_JADE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_NEPHRITE_JADE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NEPHRITE_JADE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ASIAN_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.NEPHRITE_JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_NEPHRITE_JADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOK_CHOY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COW_DUNG, Models.GENERATED);
    }
}