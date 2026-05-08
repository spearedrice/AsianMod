package org.spearedrice.asianmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.item.custom.ChiselItem;

import java.util.function.Function;

public class ModItems {

    public static final Item NEPHRITE_JADE = register("nephrite_jade", Item::new);
    public static final Item RAW_NEPHRITE_JADE = register("raw_nephrite_jade", Item::new);
    public static final Item CHISEL = register("chisel", settings -> new ChiselItem(settings.maxDamage(32)));

    private static <T extends Item> T register(String name, Function<Item.Settings, T> factory) {
        Identifier id = Identifier.of(AsianMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings settings = new Item.Settings().registryKey(key);

        T item = factory.apply(settings);

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.add(NEPHRITE_JADE);
                    entries.add(RAW_NEPHRITE_JADE);
                    entries.add(CHISEL);
                });
    }
}