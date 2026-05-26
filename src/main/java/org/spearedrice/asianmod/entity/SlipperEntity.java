package org.spearedrice.asianmod.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.sound.CustomSounds;

public class SlipperEntity extends Projectile implements ItemSupplier {

	public SlipperEntity(Level world, LivingEntity thrower) {
		super(ModEntityTypes.SLIPPER_ENTITY, world);
		this.setOwner(thrower);
	}

	public SlipperEntity(EntityType<? extends SlipperEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (!this.level().isClientSide()) {
			Entity entity = result.getEntity();
			LivingEntity thrower = (LivingEntity) this.getOwner();
			entity.hurt(this.damageSources().thrown(this, thrower), 30 + this.random.nextInt(7));
			this.level().playSound(null, this.getX(), this.getY(), this.getZ(), CustomSounds.SLIPPER_HIT, SoundSource.NEUTRAL, 1.0F, 1.0F);
			this.discard();
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	public double getDefaultGravity() {
		return 0.03D;
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModItems.SLIPPER);
	}
}