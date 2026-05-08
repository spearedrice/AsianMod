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

@Mixin(LivingEntity.class)
public class AsianDadEntityMixin {

    // is it die or death loot
    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"))
    private void asianmod$onDeath(DamageSource source, CallbackInfo ci) {

        LivingEntity self = (LivingEntity)(Object)this;

        if (!(self instanceof AsianDadEntity dad)) return;

        if (self.level().isClientSide()) return;
        dad.spawnAtLocation(new ItemStack(ModItems.ASIAN_DAD_SLIPPERS, 5));
        dad.spawnAtLocation(new ItemStack(Items.GOLD_INGOT, 3));
        dad.spawnAtLocation(new ItemStack(Items.DIAMOND, 1));
        dad.spawnAtLocation(new ItemStack(Items.EMERALD, 4));
    }
}