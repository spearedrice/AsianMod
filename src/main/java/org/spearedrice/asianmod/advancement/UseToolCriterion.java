package org.spearedrice.asianmod.advancement;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

public class UseToolCriterion extends SimpleCriterionTrigger<UseToolCriterion.Conditions> {

    public void trigger(ServerPlayer player) {
        trigger(player, Conditions::requirementsMet);
    }

    @Override
    public Codec<Conditions> codec() {
        return Conditions.CODEC;
    }

    public record Conditions(Optional<ContextAwarePredicate> playerPredicate)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<Conditions> CODEC =
                ContextAwarePredicate.CODEC.optionalFieldOf("player")
                        .xmap(Conditions::new, Conditions::player)
                        .codec();

        @Override
        public Optional<ContextAwarePredicate> player() {
            return playerPredicate;
        }

        public boolean requirementsMet() {
            return true;
        }
    }
}