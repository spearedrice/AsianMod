package org.spearedrice.asianmod.advancement;

import java.util.HashMap;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class AsianModDatagenAdvancement implements ModInitializer {

    private static final HashMap<Item, Integer> TOOL_USES = new HashMap<>();

    @Override
    public void onInitialize() {
        ModCriteria.init();

        PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, blockEntity) -> {
            if (player instanceof ServerPlayer serverPlayer) {

                Item item = player.getMainHandItem().getItem();

                int usedCount = TOOL_USES.getOrDefault(item, 0) + 1;
                TOOL_USES.put(item, usedCount);

                ModCriteria.USE_TOOL.trigger(serverPlayer);
                ModCriteria.PARAMETERIZED_USE_TOOL.trigger(serverPlayer, usedCount);

                serverPlayer.sendSystemMessage(
                        Component.literal("Tool used: " + item.getDescription().getString() + " (" + usedCount + ")")
                );
            }
        });
    }
}