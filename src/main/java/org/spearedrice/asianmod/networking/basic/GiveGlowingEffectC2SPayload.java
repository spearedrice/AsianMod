package org.spearedrice.asianmod.networking.basic;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import org.spearedrice.asianmod.AsianMod;

public record GiveGlowingEffectC2SPayload(int entityId) implements CustomPacketPayload {

    public static final Identifier GIVE_GLOWING_EFFECT_PAYLOAD_ID =
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "give_glowing_effect");

    public static final CustomPacketPayload.Type<GiveGlowingEffectC2SPayload> ID =
            new CustomPacketPayload.Type<>(GIVE_GLOWING_EFFECT_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, GiveGlowingEffectC2SPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    GiveGlowingEffectC2SPayload::entityId,
                    GiveGlowingEffectC2SPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
