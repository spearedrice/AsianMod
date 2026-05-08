package org.spearedrice.asianmod.networking.payload;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import org.spearedrice.asianmod.AsianMod;

public record BellowsSoundInstancePacket(boolean shouldStart, BlockPos blockEntityPos)
        implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BellowsSoundInstancePacket> IDENTIFIER =
            new CustomPacketPayload.Type<>(AsianMod.id("bellows_sound_instance"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return IDENTIFIER;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, BellowsSoundInstancePacket> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.BOOL,
                    BellowsSoundInstancePacket::shouldStart,
                    BlockPos.STREAM_CODEC,
                    BellowsSoundInstancePacket::blockEntityPos,
                    BellowsSoundInstancePacket::new
            );
}