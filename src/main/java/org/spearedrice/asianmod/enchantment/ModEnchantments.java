package org.spearedrice.asianmod.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> RITUALISTIC_SACRIFICE =
            key("ritualistic_sacrifice");

    public static final ResourceKey<Enchantment> BURDENED_SPIRIT =
            key("burdened_spirit");

    private static ResourceKey<Enchantment> key(String path) {
        Identifier id = Identifier.fromNamespaceAndPath("asianmod", path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }
}