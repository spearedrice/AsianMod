package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import org.spearedrice.asianmod.loot.ModLootTables;


public class AsianModChestLootTableProvider extends SimpleFabricLootTableProvider {

    public AsianModChestLootTableProvider(FabricDataOutput output,
                                          CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {


        consumer.accept(ModLootTables.ABACUS_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(2.0f))

                        .add(LootItem.lootTableItem(Items.PAPER)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0f))))

                        .add(LootItem.lootTableItem(Items.STICK))

                        .add(LootItem.lootTableItem(Items.EMERALD))
                )
        );


        consumer.accept(ModLootTables.ANCIENT_TEMPLE_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(3.0f))

                        .add(LootItem.lootTableItem(Items.CLAY_BALL)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f))))

                        .add(LootItem.lootTableItem(Items.GOLD_INGOT))

                        .add(LootItem.lootTableItem(Items.BAMBOO))
                )
        );
    }
}