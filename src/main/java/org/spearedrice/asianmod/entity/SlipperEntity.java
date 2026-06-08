package org.spearedrice.asianmod.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.ItemStack;
import org.spearedrice.asianmod.item.ModItems;

public class SlipperEntity extends ThrowableProjectile implements ItemSupplier {
	public SlipperEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
		super(type, level);
	}

	public SlipperEntity(Level level, LivingEntity owner) {
		super(ModEntityTypes.SLIPPER_ENTITY, owner.getX(), owner.getEyeY(), owner.getZ(), level);
		this.setOwner(owner);
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (result instanceof EntityHitResult entityHit && !this.level().isClientSide()) {
			Entity target = entityHit.getEntity();
			Entity owner = this.getOwner();
			target.hurt(this.level().damageSources().thrown(this, owner instanceof LivingEntity le ? le : null), 4.0F);
			this.discard();
		}
	}

	@Override
	protected double getDefaultGravity() { return 0.03; }

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModItems.SLIPPER);
	}
}