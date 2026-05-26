package org.spearedrice.asianmod.sound.instance;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

import org.spearedrice.asianmod.block.entity.custom.BellowsBlockEntity;
import org.spearedrice.asianmod.sound.AbstractDynamicSoundInstance;
import org.spearedrice.asianmod.sound.DynamicSoundSource;

public class BellowsSoundInstance extends AbstractDynamicSoundInstance {
    public BellowsSoundInstance(DynamicSoundSource soundSource, SoundEvent soundEvent, SoundSource soundCategory,
                               int startTransitionTicks, int endTransitionTicks, float maxVolume, float minPitch, float maxPitch,
                               SoundInstanceCallback callback) {
        super(soundSource, soundEvent, soundCategory, startTransitionTicks, endTransitionTicks, maxVolume, minPitch, maxPitch, callback);
    }

    @Override
    public void tick() {
        if (soundSource instanceof BellowsBlockEntity blockEntity && blockEntity.isRemoved()) {
            this.end();
        }

        super.tick();

        this.modulateSoundForTransition();
        this.modulateSoundForStress();
    }

}