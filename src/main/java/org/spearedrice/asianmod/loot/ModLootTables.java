package org.spearedrice.asianmod.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import org.spearedrice.asianmod.AsianMod;


public class ModLootTables {
    public static ResourceKey<LootTable> TEST_CHEST_LOOT = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "chests/test_loot"));
    public static ResourceKey<LootTable> ABACUS_CHEST = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "chests/abacus_chest"));
    public static ResourceKey<LootTable> ANCIENT_TEMPLE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "chests/ancient_temple_chest"));
}