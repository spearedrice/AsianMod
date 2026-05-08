package org.spearedrice.asianmod.saveddata;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class AsianModSavedData implements ModInitializer {

    @Override
    public void onInitialize() {

        PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {

            MinecraftServer server = level.getServer();
            if (server == null) return;

            SavedBlockData savedData = SavedBlockData.getSavedBlockData(server);

            savedData.incrementBlocksBroken();

            player.displayClientMessage(
                    Component.literal("Blocks broken: " + savedData.getBlocksBroken()),
                    false
            );
        });
    }
}