package org.spearedrice.asianmod.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import org.spearedrice.asianmod.AsianMod;

public class CustomSounds {

    private CustomSounds() {}

    public static final SoundEvent BELLOWS_LOOP = registerSound("bellows_loop");
    public static final SoundEvent WIND_CHIME = registerSound("wind_chime");
    public static final SoundEvent SLIPPER_THROWN = registerSound("slipper_thrown");

    private static SoundEvent registerSound(String id) {
        Identifier identifier =
                Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, id);

        return Registry.register(
                BuiltInRegistries.SOUND_EVENT,
                identifier,
                SoundEvent.createVariableRangeEvent(identifier)
        );
    }

    public static void initialize() {
        AsianMod.LOGGER.info("Registering {} Sounds", AsianMod.MOD_ID);
    }
}