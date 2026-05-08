package org.spearedrice.asianmod.networking.basic;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import org.spearedrice.asianmod.AsianMod;

public class AsianModNetworkingBasic implements ModInitializer {

    public static final ResourceKey<Item> COMBUSTIBLE_RICE_WINE_REGISTRY_KEY =
            ResourceKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "combustible_rice_wine")
            );

    public static final Item COMBUSTIBLE_RICE_WINE =
            Registry.register(
                    BuiltInRegistries.ITEM,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "combustible_rice_wine"),
                    new Item(new Item.Properties().setId(COMBUSTIBLE_RICE_WINE_REGISTRY_KEY))
            );

    @Override
    public void onInitialize() {

        PayloadTypeRegistry.playS2C().register(
                SummonLightningS2CPayload.ID,
                SummonLightningS2CPayload.CODEC
        );

        PayloadTypeRegistry.playC2S().register(
                GiveGlowingEffectC2SPayload.ID,
                GiveGlowingEffectC2SPayload.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(
                GiveGlowingEffectC2SPayload.ID,
                (payload, context) -> {

                    Entity entity = context.player().level().getEntity(payload.entityId());

                    if (entity instanceof LivingEntity livingEntity
                            && livingEntity.closerThan(context.player(), 5)) {

                        livingEntity.addEffect(
                                new MobEffectInstance(MobEffects.GLOWING, 100)
                        );
                    }
                }
        );
    }
}