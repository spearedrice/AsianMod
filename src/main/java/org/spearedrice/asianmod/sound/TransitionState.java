package org.spearedrice.asianmod.sound;

import net.minecraft.resources.Identifier;

import org.spearedrice.asianmod.AsianMod;

public enum TransitionState {

    STARTING("starting_phase"),
    RUNNING("idle_phase"),
    ENDING("ending_phase");

    private final Identifier identifier;

    TransitionState(String name) {
        this.identifier =
                Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name);
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}