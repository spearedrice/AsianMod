package org.spearedrice.asianmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.spearedrice.asianmod.AsianMod;

public class ModItems {

    public static final Item NEPHRITE_JADE = registerItem("nephrite_jade");
    public static final Item RAW_NEPHRITE_JADE = registerItem("raw_nephrite_jade");

    private static Item registerItem(String name) {
        Identifier id = Identifier.of(AsianMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item item = new Item(new Item.Settings().registryKey(key));

        // mojang fuck you stop updating this i an hour of my life
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void registerModItems() {
        AsianMod.LOGGER.info("Registering Mod Items for " + AsianMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.add(NEPHRITE_JADE);
                    entries.add(RAW_NEPHRITE_JADE);
                });
    }
}
