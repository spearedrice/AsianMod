package org.spearedrice.asianmod.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

import org.spearedrice.asianmod.sound.instance.SoundInstanceCallback;

public abstract class AbstractDynamicSoundInstance extends AbstractTickableSoundInstance {
    protected final DynamicSoundSource soundSource;
    protected TransitionState transitionState;

    protected final int startTransitionTicks, endTransitionTicks;

    protected final float maxVolume;
    protected final float minPitch, maxPitch;

    protected int currentTick = 0, transitionTick = 0;

    protected final SoundInstanceCallback callback;



    protected AbstractDynamicSoundInstance(DynamicSoundSource soundSource, SoundEvent soundEvent, SoundSource soundCategory,
                                           int startTransitionTicks, int endTransitionTicks, float maxVolume, float minPitch, float maxPitch,
                                           SoundInstanceCallback callback) {
        super(soundEvent, soundCategory, SoundInstance.createUnseededRandom());

        this.soundSource = soundSource;
        this.callback = callback;

        this.maxVolume = maxVolume;
        this.minPitch = minPitch;
        this.maxPitch = maxPitch;
        this.startTransitionTicks = startTransitionTicks;
        this.endTransitionTicks = endTransitionTicks;

        this.volume = 0.0f;
        this.pitch = minPitch;
        this.looping = true;
        this.transitionState = TransitionState.STARTING;
        this.setPositionToEntity();
    }


    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (this.soundSource == null) {
            this.callback.onFinished(this);
        }

        this.currentTick++;
        this.setPositionToEntity();

        switch (this.transitionState) {
            case STARTING -> {
                this.transitionTick++;

                if (this.transitionTick > this.startTransitionTicks) {
                    this.transitionTick = 0;
                    this.transitionState = TransitionState.RUNNING;
                }
            }
            case ENDING -> {
                this.transitionTick++;

                if (this.transitionTick > this.endTransitionTicks) {
                    this.callback.onFinished(this);
                }
            }
        }

    }

    protected void modulateSoundForTransition() {
        float normalizedTick = switch (transitionState) {
            case STARTING -> (float) this.transitionTick / this.startTransitionTicks;
            case ENDING -> 1.0f - ((float) this.transitionTick / this.endTransitionTicks);
            default -> 1.0f;
        };

        this.volume = Mth.lerp(normalizedTick, 0.0f, this.maxVolume);
    }

    protected void modulateSoundForStress() {
        this.pitch = Mth.lerp(this.soundSource.getNormalizedStress(), this.minPitch, this.maxPitch);
    }

    protected void setPositionToEntity() {
        this.x = soundSource.getPosition().x();
        this.y = soundSource.getPosition().y();
        this.z = soundSource.getPosition().z();
    }

    public void end() {
        this.transitionState = TransitionState.ENDING;
    }
}