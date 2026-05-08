package org.spearedrice.asianmod.interface_injection;

import java.util.Optional;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.material.FlowingFluid;

class AsianModInterfaceInjection {

    // help
    void example(FlowingFluid flowingFluid) {
        Optional<SoundEvent> sound = flowingFluid.asianmod$getBucketEmptySound();
    }
}