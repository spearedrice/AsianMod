package org.spearedrice.asianmod.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class CustomSounds {
	private CustomSounds() {
	}

	public static final SoundEvent BELLOWS = registerSound("bellows");
	public static final SoundEvent WIND_CHIME = registerSound("wind_chime");
	public static final SoundEvent SLIPPER_THROWN = registerSound("slipper_thrown");
	public static final SoundEvent SLIPPER_HIT = registerSound("slipper_hit");
	public static final SoundEvent RED_SUN = registerSound("music/red_sun");

	private static SoundEvent registerSound(String id) {
		Identifier identifier = Identifier.fromNamespaceAndPath(AsianModSounds.MOD_ID, id);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
	}

	public static void initialize() {
		AsianModSounds.LOGGER.info("Registering " + AsianModSounds.MOD_ID + " Sounds");
	}
}