package org.spearedrice.asianmod.block.custom;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.spearedrice.asianmod.block.entity.ModBlockEntities;
import org.spearedrice.asianmod.block.entity.custom.DuplicatorBlockEntity;

public class DuplicatorBlock extends BaseEntityBlock {

    public DuplicatorBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DuplicatorBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DuplicatorBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof DuplicatorBlockEntity duplicator)) {
            return InteractionResult.PASS;
        }

        if (!duplicator.canPlaceItemThroughFace(0, stack, hit.getDirection())) {
            return InteractionResult.PASS;
        }

        if (!player.getItemInHand(hand).isEmpty() && duplicator.isEmpty()) {
            duplicator.setItem(0, player.getItemInHand(hand).copy());
            player.getItemInHand(hand).setCount(0);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.DUPLICATOR_BLOCK_ENTITY, DuplicatorBlockEntity::tick);
    }
}