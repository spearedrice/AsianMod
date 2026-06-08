package org.spearedrice.asianmod.mixin.event;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;

import org.spearedrice.asianmod.event.SheepShearCallback;

@Mixin(Sheep.class)
public class SheepEntityMixin {

    @Inject(
            method = "mobInteract",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/sheep/Sheep;shear(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/world/item/ItemStack;)V"
            ),
            cancellable = true
    )
    private void asianmod$onShear(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {

        if (player == null) return;

        InteractionResult result =
                SheepShearCallback.EVENT.invoker().interact(player, (Sheep) (Object) this);


        if (result == InteractionResult.FAIL) {
            cir.setReturnValue(InteractionResult.FAIL);
            return;
        }


        if (result == InteractionResult.SUCCESS) {
            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
        }


    }
}