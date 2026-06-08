package org.spearedrice.asianmod.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.spearedrice.asianmod.entity.SlipperEntity;
import org.spearedrice.asianmod.sound.CustomSounds;

public class SlipperItem extends Item {
	public SlipperItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), CustomSounds.SLIPPER_THROWN, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide()) {
			var look = player.getLookAngle().normalize().scale(1.5);
			SlipperEntity slipperEntity = new SlipperEntity(level, player);
			slipperEntity.setDeltaMovement(look);
			level.addFreshEntity((net.minecraft.world.entity.Entity)slipperEntity);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		if (!player.getAbilities().instabuild) {
			itemStack.shrink(1);
		}

		return InteractionResult.SUCCESS;
	}
}