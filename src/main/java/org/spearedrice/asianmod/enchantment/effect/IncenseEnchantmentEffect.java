package org.spearedrice.asianmod.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record IncenseEnchantmentEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {

    public static final MapCodec<IncenseEnchantmentEffect> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            LevelBasedValue.CODEC.fieldOf("amount")
                                    .forGetter(IncenseEnchantmentEffect::amount)
                    ).apply(instance, IncenseEnchantmentEffect::new)
            );

    @Override
    public void apply(ServerLevel serverLevel, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim) {
            if (context.owner() instanceof Player) {

                float numStrikes = this.amount.calculate(level);

                for (float i = 0; i < numStrikes; i++) {
                    BlockPos position = victim.blockPosition();

                    EntityType.LIGHTNING_BOLT.spawn(
                            serverLevel,
                            position,
                            EntitySpawnReason.TRIGGERED
                    );
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}