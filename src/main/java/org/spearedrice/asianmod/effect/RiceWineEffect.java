package org.spearedrice.asianmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class RiceWineEffect extends MobEffect {

    protected RiceWineEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe9b8b3);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.giveExperiencePoints(1 << amplifier);
        }

        return super.applyEffectTick(level, entity, amplifier);
    }
}