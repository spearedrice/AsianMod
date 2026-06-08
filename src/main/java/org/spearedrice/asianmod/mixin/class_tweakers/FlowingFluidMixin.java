package org.spearedrice.asianmod.mixin.class_tweakers;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;

import org.spearedrice.asianmod.interface_injection.BucketEmptySoundGetter;

@Mixin(FlowingFluid.class)
public abstract class FlowingFluidMixin implements BucketEmptySoundGetter {

    @Override
    public Optional<SoundEvent> asianmod$getBucketEmptySound() {

        return Optional.of(SoundEvents.BUCKET_EMPTY);
    }
}