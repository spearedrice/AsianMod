package org.spearedrice.asianmod.event;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface SheepShearCallback {

    Event<SheepShearCallback> EVENT = EventFactory.createArrayBacked(
            SheepShearCallback.class,
            (listeners) -> (player, sheep) -> {

                for (SheepShearCallback listener : listeners) {
                    InteractionResult result = listener.interact(player, sheep);

                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }

                return InteractionResult.SUCCESS;
            }
    );

    InteractionResult interact(Player player, Sheep sheep);
}