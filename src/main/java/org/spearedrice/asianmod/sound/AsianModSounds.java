package org.spearedrice.asianmod.sound;

import org.slf4j.Logger;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

import net.fabricmc.api.ModInitializer;

import org.spearedrice.asianmod.AsianMod;

public class AsianModSounds implements ModInitializer {

    public static final String MOD_ID = AsianMod.MOD_ID;
    public static final Logger LOGGER = AsianMod.LOGGER;

    @Override
    public void onInitialize() {

        //  test
        Registry.register(
                BuiltInRegistries.SOUND_EVENT,
                Identifier.fromNamespaceAndPath(MOD_ID, "metal_whistle_simple"),
                SoundEvent.createVariableRangeEvent(
                        Identifier.fromNamespaceAndPath(MOD_ID, "metal_whistle_simple")
                )
        );

        //  registration system
        CustomSounds.initialize();
    }

    public static Identifier identifierOf(String path) {
        return Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, path);
    }
}