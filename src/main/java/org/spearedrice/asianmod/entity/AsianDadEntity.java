package org.spearedrice.asianmod.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.sound.CustomSounds;

public class AsianDadEntity extends PathfinderMob implements RangedAttackMob {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState angryAnimationState = new AnimationState();
	public final AnimationState throwingAnimationState = new AnimationState();

	public AsianDadEntity(Level world) {
		this(ModEntityTypes.ASIAN_DAD_ENTITY, world);
	}

	public AsianDadEntity(EntityType<? extends AsianDadEntity> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return PathfinderMob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20)
				.add(Attributes.MOVEMENT_SPEED, 0.3)
				.add(Attributes.ATTACK_DAMAGE, 3.0)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5);
	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0, 40, 10.0F));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		this.setAggressive(true);
		this.startUsingItem(net.minecraft.world.InteractionHand.MAIN_HAND);
		double dx = target.getX() - this.getX();
		double dy = target.getY(0.3333333333333333) - this.getEyeY();
		double dz = target.getZ() - this.getZ();
		double dist = Math.sqrt(dx * dx + dz * dz);
		SlipperEntity slipper = new SlipperEntity(this.level(), this);
		slipper.shoot(dx, dy + dist * 0.2, dz, 1.6F, 12.0F);
		this.level().addFreshEntity(slipper);
		this.stopUsingItem();
		this.setAggressive(false);
	}

	@Override
	public void tick() {
		super.tick();
		if (!throwingAnimationState.isStarted() && !angryAnimationState.isStarted()) {
			if (!idleAnimationState.isStarted()) {
				idleAnimationState.start(this.tickCount);
			}
		}
	}

	private void throwSlipper(LivingEntity target) {
		if (this.level().isClientSide()) return;

		if (target == null) {
			if (this.getTarget() != null) {
				target = this.getTarget();
			} else {
				Player nearest = null;
				double closest = Double.MAX_VALUE;
				for (Player p : this.level().players()) {
					double d = this.distanceToSqr(p);
					if (d < closest) {
						closest = d;
						nearest = p;
					}
				}
				if (nearest == null) return;
				target = nearest;
			}
		}

		SlipperEntity slipper = new SlipperEntity(this.level(), this);
		Vec3 dir = new Vec3(target.getX() - this.getX(), target.getEyeY() - this.getEyeY(), target.getZ() - this.getZ()).normalize().scale(1.5);
		slipper.setDeltaMovement(dir);
		this.level().addFreshEntity((net.minecraft.world.entity.Entity)slipper);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return CustomSounds.BELLOWS;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return CustomSounds.WIND_CHIME;
	}

	public int getExperienceReward() {
		return 10;
	}
}