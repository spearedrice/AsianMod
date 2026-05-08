package org.spearedrice.asianmod.interface_injection;

import java.util.Optional;
import net.minecraft.sounds.SoundEvent;

public interface BucketEmptySoundGetter {
    default Optional<SoundEvent> asianmod$getBucketEmptySound() {
        throw new AssertionError("Implemented in Mixin");
    }
}