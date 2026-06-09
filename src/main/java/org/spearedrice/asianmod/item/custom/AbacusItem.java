package org.spearedrice.asianmod.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import org.spearedrice.asianmod.component.ModComponents;

public class AbacusItem extends Item {
    public AbacusItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        int count = stack.getOrDefault(ModComponents.CLICK_COUNT_COMPONENT, 0);
        stack.set(ModComponents.CLICK_COUNT_COMPONENT, ++count);

        return InteractionResult.SUCCESS;
    }


}