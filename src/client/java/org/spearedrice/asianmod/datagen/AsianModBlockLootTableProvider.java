package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import org.spearedrice.asianmod.block.ModBlocks;

// :::datagen-loot-tables:block-provider
public class AsianModBlockLootTableProvider extends FabricBlockLootTableProvider {

    protected AsianModBlockLootTableProvider(FabricDataOutput dataOutput,
                                             CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        dropSelf(ModBlocks.RAMMED_EARTH);
        dropSelf(ModBlocks.COMPACTED_TIMBER);
        dropSelf(ModBlocks.ABACUS_BLOCK);
        dropSelf(ModBlocks.BRASS_CHEST);
        dropSelf(ModBlocks.BELLOWS);
        dropSelf(ModBlocks.CLAY_PIPE_BLOCK);
        dropSelf(ModBlocks.OIL_LAMP);

        dropSelf(ModBlocks.PORCELAIN_BLOCK);
        dropSelf(ModBlocks.PORCELAIN_DOOR);
        dropSelf(ModBlocks.PORCELAIN_FENCE);
        dropSelf(ModBlocks.PORCELAIN_SLAB);
        dropSelf(ModBlocks.PORCELAIN_STAIRS);
        dropSelf(ModBlocks.PORCELAIN_TRAPDOOR);

        dropSelf(ModBlocks.VERTICAL_BAMBOO_SLAB);
        dropSelf(ModBlocks.LINGZHI);

        dropSelf(ModBlocks.COMPACTED_BAMBOO);
        dropSelf(ModBlocks.WIND_CHIME_BLOCK);

        add(ModBlocks.PORCELAIN_ORE,
                createOreDrop(ModBlocks.PORCELAIN_ORE, Items.CLAY_BALL));

        dropWithSilkTouch(ModBlocks.OIL_LAMP);
    }
}
