package org.spearedrice.asianmod.block.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import org.spearedrice.asianmod.block.entity.ModBlockEntities;
import org.spearedrice.asianmod.networking.payload.BellowsSoundInstancePacket;
import org.spearedrice.asianmod.sound.DynamicSoundSource;

public class BellowsBlockEntity extends BlockEntity implements DynamicSoundSource {

    public static final int MAX_FUEL = 200;
    private int tick = -1;
    private int fuel = 0;
    private float normalizedStress = 0;

    public BellowsBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BELLOWS_BLOCK_ENTITY, pos, state);
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    @Override
    public int getTick() {
        return this.tick;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BellowsBlockEntity entity) {
        if (entity.getTick() < 0) return;

        entity.setTick(entity.getTick() + 1);
        entity.setFuelIfPossible(entity.getFuel() - 1);
        entity.setNormalizedStress(entity.getNormalizedStress() - 0.02f);

        if (!level.isClientSide() && entity.getFuel() > 0) {
            PlayerLookup.tracking(entity).forEach(player -> {
                String stateText = "Bellows Fuel: %s | Stress: %s".formatted(
                        entity.getFuel(),
                        String.format("%.02f", entity.getNormalizedStress())
                );
                player.displayClientMessage(Component.literal(stateText), true);
            });
        }

        if (entity.getFuel() <= 0) {
            entity.turnOff();
            entity.setFuelIfPossible(0);
        }
    }

    @Override
    public float getNormalizedStress() {
        return Mth.clamp(normalizedStress, 0, 1);
    }

    public void setNormalizedStress(float normalizedStress) {
        this.normalizedStress = Mth.clamp(normalizedStress, 0, 1);
    }

    public int getFuel() {
        return Mth.clamp(this.fuel, 0, MAX_FUEL);
    }

    public boolean setFuelIfPossible(int fuel) {
        boolean consumeItem = this.getFuel() != MAX_FUEL;
        this.fuel = Mth.clamp(fuel, 0, MAX_FUEL);
        return consumeItem;
    }

    @Override
    public Vec3 getPosition() {
        return this.getBlockPos().getCenter();
    }

    public void turnOn() {
        if (this.getFuel() > 0) {
            this.setTick(0);
            this.setNormalizedStress(0);
            this.sendPacketToTrackingClients(new BellowsSoundInstancePacket(true, this.getBlockPos()));
            this.syncToChunk();
        }
    }

    public void turnOff() {
        this.tick = -1;
        this.sendPacketToTrackingClients(new BellowsSoundInstancePacket(false, this.getBlockPos()));
        this.syncToChunk();
    }

    public boolean isRunning() {
        return this.getTick() > -1;
    }

    public void sendPacketToTrackingClients(CustomPacketPayload payload) {
        if (payload == null || !(this.getLevel() instanceof ServerLevel)) return;
        PlayerLookup.tracking(this).forEach(player -> ServerPlayNetworking.send(player, payload));
    }

    public void syncToChunk() {
        if (!(getLevel() instanceof ServerLevel serverLevel)) return;
        serverLevel.getChunkSource().blockChanged(this.getBlockPos());
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }
}