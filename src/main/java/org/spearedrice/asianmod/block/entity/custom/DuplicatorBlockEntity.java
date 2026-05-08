package org.spearedrice.asianmod.block.entity.custom;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import org.spearedrice.asianmod.block.entity.ModBlockEntities;
import org.spearedrice.asianmod.container.ImplementedContainer;

public class DuplicatorBlockEntity extends BlockEntity implements ImplementedContainer, WorldlyContainer {

    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private int timeSinceDropped = 0;

    public DuplicatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DUPLICATOR_BLOCK_ENTITY, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, items);
        super.saveAdditional(output);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DuplicatorBlockEntity entity) {
        if (entity.isEmpty()) return;

        entity.timeSinceDropped++;

        if (entity.timeSinceDropped < 10) return;
        entity.timeSinceDropped = 0;

        ItemStack duplicate = entity.getItem(0).split(1);

        Block.popResourceFromFace(level, pos, Direction.UP, duplicate);
        Block.popResourceFromFace(level, pos, Direction.UP, duplicate);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[]{0};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return dir == Direction.UP;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return true;
    }
}