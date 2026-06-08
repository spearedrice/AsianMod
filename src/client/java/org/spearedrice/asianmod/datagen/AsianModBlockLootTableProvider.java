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
import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.recipe.AsianModRecipes;


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
        dropSelf(ModBlocks.BELLOWS_BLOCK);
        dropSelf(ModBlocks.CLAY_PIPE);
        dropSelf(ModBlocks.OIL_LAMP);


        dropSelf(ModBlocks.PORCELAIN_BLOCK);
        dropSelf(ModBlocks.PORCELAIN_FENCE);
        dropSelf(ModBlocks.PORCELAIN_SLAB);
        dropSelf(ModBlocks.PORCELAIN_STAIRS);
        dropSelf(ModBlocks.PORCELAIN_LOG);
        dropSelf(ModBlocks.PORCELAIN_LEAVES);


        dropSelf(ModBlocks.NEPHRITE_BLOCK);
        dropSelf(ModBlocks.NEPHRITE_DOOR);


        dropSelf(ModBlocks.NEPHRITE_JADE_BLOCK);
        dropSelf(ModBlocks.RAW_NEPHRITE_JADE_BLOCK);
        dropSelf(ModBlocks.NEPHRITE_JADE_ORE);
        dropSelf(ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE);




        dropSelf(ModBlocks.VERTICAL_BAMBOO_SLAB);
        dropSelf(ModBlocks.LINGZHI);
        dropSelf(ModBlocks.STEEL_BLOCK);
        dropSelf(ModBlocks.VERTICAL_OAK_LOG_SLAB);
        dropSelf(ModBlocks.DUPLICATOR_BLOCK);
        dropSelf(AsianModRecipes.UPGRADING_BLOCK);
        dropSelf(ModBlocks.SKY_LANTERN_BLOCK);


        add(ModBlocks.PORCELAIN_ORE,
                createOreDrop(ModBlocks.PORCELAIN_ORE, Items.CLAY_BALL));




    }
}