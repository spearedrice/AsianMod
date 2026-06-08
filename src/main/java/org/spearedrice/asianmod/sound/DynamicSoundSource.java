package org.spearedrice.asianmod.sound;

import net.minecraft.world.phys.Vec3;

public interface DynamicSoundSource {


    int getTick();


    Vec3 getPosition();


    float getNormalizedStress();
}