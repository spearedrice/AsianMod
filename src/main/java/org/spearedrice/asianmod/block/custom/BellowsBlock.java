package org.spearedrice.asianmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import org.spearedrice.asianmod.block.entity.custom.BellowsBlockEntity;
import org.spearedrice.asianmod.block.entity.ModBlockEntities;

public class BellowsBlock extends BaseEntityBlock {

    public static final MapCodec<BellowsBlock> CODEC = simpleCodec(BellowsBlock::new);

    public BellowsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BellowsBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.BELLOWS_BLOCK_ENTITY, BellowsBlockEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        BlockEntity be = level.getBlockEntity(pos);

        if (!(be instanceof BellowsBlockEntity bellows)) {
            return InteractionResult.PASS;
        }

        ItemStack stack = player.getMainHandItem();

        if (stack.is(ItemTags.COALS)) {
            if (bellows.setFuelIfPossible(bellows.getFuel() + 40)) {
                stack.shrink(1);
                playSound(level, SoundEvents.LEVER_CLICK, pos);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        if (bellows.isRunning()) {
            bellows.setNormalizedStress(bellows.getNormalizedStress() + 0.2f);
            return InteractionResult.SUCCESS;
        }

        if (bellows.getFuel() > 0) {
            bellows.turnOn();
            playSound(level, SoundEvents.LEVER_CLICK, pos);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private static void playSound(Level level, SoundEvent sound, BlockPos pos) {
        if (level.isClientSide()) return;
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 0.8f, 1f);
    }
}