package org.spearedrice.asianmod.enchantment;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.spearedrice.asianmod.enchantment.effect.IncenseEnchantmentEffect;

public class ModEnchantmentEffects {

    public static final Logger LOGGER = LoggerFactory.getLogger("asianmod");

    public static final MapCodec<IncenseEnchantmentEffect> INCENSE_EFFECT =
            register("incense_effect", IncenseEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(
                BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                Identifier.fromNamespaceAndPath("asianmod", id),
                codec
        );
    }

    public static void registerModEnchantmentEffects() {
        LOGGER.info("Registering EnchantmentEffects for asianmod");
    }
}