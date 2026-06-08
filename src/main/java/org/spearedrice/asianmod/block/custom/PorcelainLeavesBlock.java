package org.spearedrice.asianmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PorcelainLeavesBlock extends Block {
	public static final MapCodec<PorcelainLeavesBlock> CODEC = simpleCodec(PorcelainLeavesBlock::new);

	public PorcelainLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends Block> codec() {
		return CODEC;
	}
}