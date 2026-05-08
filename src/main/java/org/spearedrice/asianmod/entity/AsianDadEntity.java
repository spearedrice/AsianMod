package org.spearedrice.asianmod.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class AsianDadEntity extends PathfinderMob {

    private static final EntityDataAccessor<Integer> STANCE =
            SynchedEntityData.defineId(AsianDadEntity.class, EntityDataSerializers.INT);

    private int angerTimeLeft = 0;

    public final AnimationState angryAnimationState = new AnimationState();

    public AsianDadEntity(EntityType<? extends AsianDadEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createCubeAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0,
                new NearestAttackableTargetGoal<>(this, Player.class, true)
        );

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(STANCE, 0);
    }

    public boolean isAngry() {
        return entityData.get(STANCE) == 1;
    }

    public void setAngry(boolean angry) {
        entityData.set(STANCE, angry ? 1 : 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {

            if (isAngry()) {
                if (--angerTimeLeft <= 0) {
                    setAngry(false);
                }
            } else {
                if (this.random.nextInt(400) == 0) {
                    setAngry(true);
                    angerTimeLeft = 80 + random.nextInt(80);
                }
            }
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);

        if (data == STANCE) {
            angryAnimationState.animateWhen(isAngry(), this.tickCount);
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("stance", entityData.get(STANCE));
        output.putInt("anger_time_left", angerTimeLeft);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        entityData.set(STANCE, input.getInt("stance").orElse(0));
        angerTimeLeft = input.getInt("anger_time_left").orElse(0);
    }
}