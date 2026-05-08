package org.spearedrice.asianmod.advancement;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

public class ParameterizedUseToolCriterion extends SimpleCriterionTrigger<ParameterizedUseToolCriterion.Conditions> {

    public void trigger(ServerPlayer player, int totalTimes) {
        trigger(player, conditions -> conditions.requirementsMet(totalTimes));
    }

    @Override
    public Codec<Conditions> codec() {
        return Conditions.CODEC;
    }

    public record Conditions(Optional<ContextAwarePredicate> playerPredicate, int requiredTimes)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(Conditions::playerPredicate),
                Codec.INT.fieldOf("requiredTimes").forGetter(Conditions::requiredTimes)
        ).apply(instance, Conditions::new));

        @Override
        public Optional<ContextAwarePredicate> player() {
            return playerPredicate;
        }

        public boolean requirementsMet(int totalTimes) {
            return totalTimes > requiredTimes;
        }
    }
}