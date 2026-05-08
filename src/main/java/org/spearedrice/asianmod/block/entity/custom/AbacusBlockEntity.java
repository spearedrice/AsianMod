package org.spearedrice.asianmod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import org.spearedrice.asianmod.block.entity.ModBlockEntities;

public class AbacusBlockEntity extends BlockEntity {

    private int clicks = 0;
    private int ticksSinceLast = 0;

    public AbacusBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ABACUS_BLOCK_ENTITY, pos, state);
    }

    public int getClicks() {
        return clicks;
    }

    public void incrementClicks() {
        if (ticksSinceLast < 10) return;
        ticksSinceLast = 0;
        clicks++;
        setChanged();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putInt("clicks", clicks);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        clicks = input.getIntOr("clicks", 0);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AbacusBlockEntity entity) {
        entity.ticksSinceLast++;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }
}