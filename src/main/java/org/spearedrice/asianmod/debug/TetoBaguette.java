package org.spearedrice.asianmod.debug;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;

public class TetoBaguette extends Item {

    public TetoBaguette(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult interactLivingEntity(
            ItemStack stack,
            Player user,
            LivingEntity entity,
            InteractionHand hand
    ) {
        Level level = user.level();

        if (level.isClientSide()) {
            AsianModDebug.LOGGER.info("You interacted with an entity!");
        }

        String output = "Is Client World: %s | Health: %s / %s | The item was used with the %s"
                .formatted(
                        user.level().isClientSide(),
                        entity.getHealth(),
                        entity.getMaxHealth(),
                        hand.name()
                );

        AsianModDebug.LOGGER.info(output);

        if (!user.level().isClientSide()) {
            AsianModDebug.LOGGER.warn("Don't touch that!");

            if (stack.getCount() > 1) {
                IllegalArgumentException exception =
                        new IllegalArgumentException("Only one item is allowed");

                AsianModDebug.LOGGER.error("Error while interacting with an entity", exception);
                throw exception;
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player user = context.getPlayer();
        BlockPos targetPos = context.getClickedPos();
        ItemStack itemStack = context.getItemInHand();
        BlockState state = level.getBlockState(targetPos);

        if (state.is(ConventionalBlockTags.STONES)
                || state.is(ConventionalBlockTags.COBBLESTONES)) {

            Component newName = Component.literal("[")
                    .append(state.getBlock().getName())
                    .append(Component.literal("]"));

            itemStack.set(DataComponents.CUSTOM_NAME, newName);

            if (user != null) {
                user.displayClientMessage(Component.literal("Changed Item Name"), true);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}