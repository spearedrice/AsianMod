package org.spearedrice.asianmod.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public class IncenseStick extends Item {

    public IncenseStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10);

        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(frontOfPlayer.getCenter());
        level.addFreshEntity(lightningBolt);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            TooltipDisplay displayComponent,
            Consumer<Component> textConsumer,
            TooltipFlag type
    ) {
        textConsumer.accept(
                Component.translatable("itemTooltip.AsianMod.incense_stick")
                        .withStyle(ChatFormatting.GOLD)
        );
    }
}