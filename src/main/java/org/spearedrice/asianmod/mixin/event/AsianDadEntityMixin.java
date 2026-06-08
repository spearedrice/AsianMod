package org.spearedrice.asianmod.mixin.event;

import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.entity.AsianDadEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;

@Mixin(LivingEntity.class)
public class AsianDadEntityMixin {


    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"))
    private void asianmod$onDeath(DamageSource source, CallbackInfo ci) {

        LivingEntity self = (LivingEntity)(Object)this;

        if (!(self instanceof AsianDadEntity dad)) return;

        if (self.level().isClientSide()) return;
        dad.spawnAtLocation((ServerLevel) dad.level(), new ItemStack(ModItems.SLIPPER, 5));
        dad.spawnAtLocation((ServerLevel) dad.level(), new ItemStack(Items.GOLD_INGOT, 3));
        dad.spawnAtLocation((ServerLevel) dad.level(), new ItemStack(Items.DIAMOND, 1));
        dad.spawnAtLocation((ServerLevel) dad.level(), new ItemStack(Items.EMERALD, 4));
    }
}