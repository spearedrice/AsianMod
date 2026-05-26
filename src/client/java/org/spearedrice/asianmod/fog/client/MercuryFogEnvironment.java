package org.spearedrice.asianmod.fog.client;

import org.jetbrains.annotations.Nullable;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;

import org.spearedrice.asianmod.fluid.ModFluids;
import org.spearedrice.asianmod.fog.AsianModFogTypes;

public class MercuryFogEnvironment extends FogEnvironment {
    @Override
    public void setupFog(FogData data, Camera camera, ClientLevel level, float renderDistance, DeltaTracker delta) {
        data.environmentalStart = -4.0F;
        data.environmentalEnd = 3.0F;
    }

    @Override
    public boolean isApplicable(@Nullable FogType submersionType, Entity cameraEntity) {
        return submersionType == AsianModFogTypes.MERCURY;
    }

    @Override
    public int getBaseColor(ClientLevel level, Camera camera, int viewDistance, float tickDelta) {
        return 0xC0C0C0;
    }
}