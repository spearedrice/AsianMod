package org.spearedrice.asianmod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class CustomSoundItem extends Item {
    public CustomSoundItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        if (!entity.level().isClientSide()) {
            entity.playSound(SoundEvents.BELL_BLOCK, 2f, 0.8f);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            context.getLevel().playSound(
                    null,
                    context.getClickedPos(),
                    SoundEvents.COPPER_PLACE,
                    SoundSource.BLOCKS,
                    1f,
                    0.9f
            );
        }
        return InteractionResult.SUCCESS;
    }
}