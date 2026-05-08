package org.spearedrice.asianmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import org.spearedrice.asianmod.entity.SlipperEntity;

public class SlipperItem extends Item {

    public SlipperItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

=        if (!level.isClientSide) {

            SlipperEntity slipper = new SlipperEntity(level, player);

            slipper.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,
                    2.2F,
                    1.0F
            );

            level.addFreshEntity(slipper);

            player.getCooldowns().addCooldown(this, 10);
            player.swing(hand, true);
        }

        return InteractionResult.SUCCESS;
    }
}