package org.spearedrice.asianmod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PaperLanternItem extends Item {
	public PaperLanternItem(Properties settings) {
		super(settings);
	}

	@Override
	public InteractionResult use(Level level, Player user, InteractionHand hand) {
		user.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}
}