package org.spearedrice.asianmod.client.command;

import net.minecraft.network.chat.Component;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class AsianModClientCommands implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(
                    ClientCommandManager.literal("slipperdebug")
                            .executes(context -> {

                                context.getSource().sendFeedback(
                                        Component.literal("Slipper is currently working, a homing slipper has been deployed to your location.")
                                );

                                return 1;
                            })
            );
        });
    }
}