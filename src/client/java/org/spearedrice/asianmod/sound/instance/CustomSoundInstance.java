package org.spearedrice.asianmod.sound.instance;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

public class CustomSoundInstance extends AbstractTickableSoundInstance {
    private final LivingEntity entity;

    public CustomSoundInstance(LivingEntity entity, SoundEvent soundEvent, SoundSource soundCategory) {
        super(soundEvent, soundCategory, SoundInstance.createUnseededRandom());
        this.entity = entity;
        this.volume = 1.0f;
        this.pitch = 1.0f;
        this.looping = true;
        this.setPositionToEntity();
    }

    @Override
    public void tick() {
        if (this.entity == null || this.entity.isRemoved() || this.entity.isDeadOrDying()) {
            this.stop();
            return;
        }

        this.setPositionToEntity();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    private void setPositionToEntity() {
        this.x = this.entity.getX();
        this.y = this.entity.getY();
        this.z = this.entity.getZ();
    }
}