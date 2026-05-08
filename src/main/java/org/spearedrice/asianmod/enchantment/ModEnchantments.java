package org.spearedrice.asianmod.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import org.spearedrice.asianmod.AsianMod;

// take from tutorial since i actually want it
public class ModEnchantments {
    // :::register-enchantment
    public static final ResourceKey<Enchantment> THUNDERING = key("thundering");
    // :::register-enchantment
    public static final ResourceKey<Enchantment> REPULSION_CURSE = key("repulsion_curse");

    // :::key-helper
    private static ResourceKey<Enchantment> key(String path) {
        Identifier id = Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }
    // :::key-helper
}