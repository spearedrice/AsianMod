package org.spearedrice.asianmod.gamerule;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;

import org.spearedrice.asianmod.AsianMod;

public class AsianModGameRules implements ModInitializer {

    public static final GameRule<Boolean> BAD_VISION_BOOLEAN_GAMERULE =
            GameRuleBuilder.forBoolean(false)
                    .category(GameRuleCategory.MISC)
                    .buildAndRegister(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "bad_vision"));

    public static final GameRule<Double> DOUBLE_GAMERULE =
            GameRuleBuilder.forDouble(6.7)
                    .category(GameRuleCategory.MISC)
                    .buildAndRegister(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "double_example"));

    private static void initializeBadVision() {
        ServerTickEvents.END_WORLD_TICK.register(serverLevel -> {

            boolean doMobGriefing = serverLevel.getGameRules().get(GameRules.MOB_GRIEFING);

            boolean badVisionEnabled = serverLevel.getGameRules()
                    .get(AsianModGameRules.BAD_VISION_BOOLEAN_GAMERULE);

            if (badVisionEnabled) {
                for (Player player : serverLevel.getPlayers(p -> true)) {
                    player.addEffect(new MobEffectInstance(
                            MobEffects.BLINDNESS,
                            40,
                            1,
                            false,
                            false,
                            false
                    ));
                }
            }
        });
    }

    @Override
    public void onInitialize() {
        initializeBadVision();
    }
}