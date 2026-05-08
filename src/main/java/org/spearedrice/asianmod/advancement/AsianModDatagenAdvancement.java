package org.spearedrice.asianmod.advancement;

import java.util.HashMap;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class AsianModDatagenAdvancement implements ModInitializer {
    @Override
    public void onInitialize() {
        ModCriteria.init();

        HashMap<Item, Integer> tools = new HashMap<>();

        PlayerBlockBreakEvents.AFTER.register(((level, player, blockPos, blockState, blockEntity) -> {
            if (player instanceof ServerPlayer serverPlayer) {
                Item item = player.getMainHandItem().getItem();

                Integer usedCount = tools.getOrDefault(item, 0);
                usedCount++;
                tools.put(item, usedCount);

                ModCriteria.USE_TOOL.trigger(serverPlayer);
                ModCriteria.PARAMETERIZED_USE_TOOL.trigger(serverPlayer, usedCount);

                serverPlayer.sendSystemMessage(
                        Component.nullToEmpty("You've used \"" + item + "\" as a tool " + usedCount + " times!")
                );
            }
        }));
    }
}