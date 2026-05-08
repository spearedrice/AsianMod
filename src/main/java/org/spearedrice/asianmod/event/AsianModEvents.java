package org.spearedrice.asianmod.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import org.spearedrice.asianmod.entity.AsianDadEntity;

public class AsianModEvents implements ModInitializer {

    @Override
    public void onInitialize() {

        registerBlockEvents();
        registerSheepEvents();
        registerDeathEvents();
    }

    private void registerBlockEvents() {
        AttackBlockCallback.EVENT.register((player, level, hand, pos, direction) -> {
            BlockState state = level.getBlockState(pos);

            if (!player.isSpectator()
                    && player.getMainHandItem().isEmpty()
                    && state.requiresCorrectToolForDrops()
                    && level instanceof ServerLevel serverLevel) {

                player.hurtServer(serverLevel, level.damageSources().generic(), 1.0F);
            }

            return InteractionResult.PASS;
        });
    }

    private void registerSheepEvents() {
        SheepShearCallback.EVENT.register((player, sheep) -> {
            sheep.setSheared(true);

            drop(player, new ItemStack(Items.EMERALD));
            return InteractionResult.FAIL;
        });
    }

    private void registerDeathEvents() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {

            if (!(entity instanceof AsianDadEntity dad)) return;
            if (!(dad.level() instanceof ServerLevel level)) return;

            double x = dad.getX();
            double y = dad.getY();
            double z = dad.getZ();

            level.addFreshEntity(new ItemEntity(level, x, y, z,
                    new ItemStack(Items.GOLD_INGOT, 3)));

            level.addFreshEntity(new ItemEntity(level, x, y, z,
                    new ItemStack(Items.EMERALD, 2)));

            level.addFreshEntity(new ItemEntity(level, x, y, z,
                    new ItemStack(Items.DIAMOND, 1)));
        });
    }

    private void drop(net.minecraft.world.entity.player.Player player, ItemStack stack) {
        player.level().addFreshEntity(new ItemEntity(
                player.level(),
                player.getX(),
                player.getY(),
                player.getZ(),
                stack
        ));
    }
}