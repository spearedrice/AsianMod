package org.spearedrice.asianmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import org.spearedrice.asianmod.item.ModItems;

public class SlipperEntity extends ThrowableItemProjectile {

    public SlipperEntity(EntityType<? extends SlipperEntity> type, Level level) {
        super(type, level);
    }

    public SlipperEntity(Level level, LivingEntity owner) {
        super(AsianModEntities.SLIPPER, level);
        this.setOwner(owner);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SLIPPER;
    }

    @Override
    protected void onHitEntity(net.minecraft.world.phys.EntityHitResult hit) {
        super.onHitEntity(hit);

        var target = hit.getEntity();

        float dmg = 3.0F + random.nextFloat() * 5.0F;

        target.hurt(this.damageSources().thrown(this, this.getOwner()), dmg);

        level().playSound(
                null,
                this.blockPosition(),
                SoundEvents.WOOL_HIT,
                SoundSource.PLAYERS,
                1.0F,
                0.9F + random.nextFloat() * 0.3F
        );
    }
}