package org.spearedrice.asianmod.enchantment;

import net.fabricmc.api.ModInitializer;

public class AsianModEnchantments implements ModInitializer {
    @Override
    public void onInitialize() {
        ModEnchantmentEffects.registerModEnchantmentEffects();
    }
}