package org.spearedrice.asianmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import net.fabricmc.api.ModInitializer;

public class AsianModEffects implements ModInitializer {

    public static final Holder<MobEffect> RICE_WINE =
            Registry.registerForHolder(
                    BuiltInRegistries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath("asianmod", "rice_wine"),
                    new RiceWineEffect()
            );

    @Override
    public void onInitialize() {

    }
}