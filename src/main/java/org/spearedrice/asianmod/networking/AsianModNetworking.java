package org.spearedrice.asianmod.networking;

import net.minecraft.resources.Identifier;

import net.fabricmc.api.ModInitializer;

import org.spearedrice.asianmod.AsianMod;

public class AsianModNetworking implements ModInitializer {

    public static final String MOD_ID = AsianMod.MOD_ID;

    @Override
    public void onInitialize() {
        NetworkPayloads.initialize();
    }

    public static Identifier getId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}