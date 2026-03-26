package org.spearedrice.asianmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(
            FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.NEPHRITE_JADE_BLOCK);
        addDrop(ModBlocks.RAW_NEPHRITE_JADE_BLOCK);
        addDrop(ModBlocks.ASIAN_BLOCK);

        addDrop(ModBlocks.NEPHRITE_JADE_ORE, oreDrops(ModBlocks.NEPHRITE_JADE_ORE, ModItems.NEPHRITE_JADE));
    }
}