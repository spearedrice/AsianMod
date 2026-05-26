package org.spearedrice.asianmod.sound.instance;

import org.spearedrice.asianmod.sound.AbstractDynamicSoundInstance;

public interface SoundInstanceCallback {
    <T extends AbstractDynamicSoundInstance> void onFinished(T soundInstance);
}