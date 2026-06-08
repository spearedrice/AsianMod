package org.spearedrice.asianmod.damage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RiceWineBlock extends LiquidBlock {

    public RiceWineBlock(Properties settings) {
        super(org.spearedrice.asianmod.fluid.ModFluids.RICE_WINE_STILL, settings);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity && level instanceof ServerLevel serverLevel) {

            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(AsianModDamageTypes.RICE_WINE_DAMAGE.identifier())
                            .get()
            );

            entity.hurtServer(serverLevel, damageSource, 5.0f);
        }
    }
}