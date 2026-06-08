package org.spearedrice.asianmod.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.effect.AsianModEffects;


public class AsianModPotions implements ModInitializer {

    public static final Holder<Potion> RICE_WINE_POTION =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "rice_wine"),
                    new Potion("rice_wine",
                            new MobEffectInstance(
                                    AsianModEffects.RICE_WINE,
                                    3600,
                                    0
                            ))
            );

    @Override
    public void onInitialize() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.addMix(
                    Potions.WATER,
                    Items.SUGAR_CANE,
                    RICE_WINE_POTION
            );
        });
    }
}