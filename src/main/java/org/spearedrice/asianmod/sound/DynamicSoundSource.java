package org.spearedrice.asianmod.sound;

import net.minecraft.world.phys.Vec3;

public interface DynamicSoundSource {

    // ticks since this sound source started existing
    int getTick();

    // world position of the sound source
    Vec3 getPosition();

    // 0.0 → 1.0 stress value affecting pitch/behavior
    float getNormalizedStress();
}