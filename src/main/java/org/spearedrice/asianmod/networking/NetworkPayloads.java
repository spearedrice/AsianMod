package org.spearedrice.asianmod.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import org.spearedrice.asianmod.networking.payload.BellowsSoundInstancePacket;

public class NetworkPayloads {

    public static void initialize() {
        registerS2C(
                BellowsSoundInstancePacket.IDENTIFIER,
                BellowsSoundInstancePacket.CODEC
        );
    }

    private static <T extends CustomPacketPayload> void registerS2C(
            CustomPacketPayload.Type<T> id,
            StreamCodec<RegistryFriendlyByteBuf, T> codec
    ) {
        PayloadTypeRegistry.playS2C().register(id, codec);
    }

    private static <T extends CustomPacketPayload> void registerC2S(
            CustomPacketPayload.Type<T> id,
            StreamCodec<RegistryFriendlyByteBuf, T> codec
    ) {
        PayloadTypeRegistry.playC2S().register(id, codec);
    }
}