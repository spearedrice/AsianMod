package org.spearedrice.asianmod.sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;

import org.spearedrice.asianmod.sound.instance.SoundInstanceCallback;

public class DynamicSoundManager implements SoundInstanceCallback {
    private static final Minecraft client = Minecraft.getInstance();
    private static DynamicSoundManager instance;
    private final List<AbstractDynamicSoundInstance> activeSounds = new ArrayList<>();

    private DynamicSoundManager() {
    }

    public static DynamicSoundManager getInstance() {
        if (instance == null) {
            instance = new DynamicSoundManager();
        }

        return instance;
    }

    public <T extends AbstractDynamicSoundInstance> void play(T soundInstance) {
        if (this.activeSounds.contains(soundInstance)) return;

        client.getSoundManager().play(soundInstance);
        this.activeSounds.add(soundInstance);
    }

    public <T extends AbstractDynamicSoundInstance> void stop(T soundInstance) {
        client.getSoundManager().stop(soundInstance);
        this.activeSounds.remove(soundInstance);
    }

    public Optional<AbstractDynamicSoundInstance> getPlayingSoundInstance(SoundEvent soundEvent) {
        for (var activeSound : this.activeSounds) {
            if (activeSound.getIdentifier().equals(soundEvent.location())) {
                return Optional.of(activeSound);
            }
        }

        return Optional.empty();
    }


    @Override
    public <T extends AbstractDynamicSoundInstance> void onFinished(T soundInstance) {
        this.stop(soundInstance);
    }
}