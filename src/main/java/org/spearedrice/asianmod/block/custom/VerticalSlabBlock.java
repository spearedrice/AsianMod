package org.spearedrice.asianmod.block.custom;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalSlabBlock extends Block {

    public static final BooleanProperty SINGLE = BooleanProperty.create("single");
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);
    public static final VoxelShape WEST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
    public static final VoxelShape EAST_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public VerticalSlabBlock(Properties settings) {
        super(settings);
    }

    private VoxelShape getShapeInternal(BlockState state) {
        boolean single = state.getValue(SINGLE);
        Direction direction = state.getValue(FACING);

        if (!single) return Shapes.block();

        return switch (direction) {
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case NORTH -> NORTH_SHAPE;
            default -> Shapes.block();
        };
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShapeInternal(state);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShapeInternal(state);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        Direction direction = state.getValue(FACING);

        if (context.getItemInHand().is(this.asItem()) && state.getValue(SINGLE)) {
            if (context.replacingClickedOnBlock()) {
                return context.getClickedFace().getOpposite() == direction;
            }
        }

        return false;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        Direction direction = ctx.getHorizontalDirection();
        BlockState existing = ctx.getLevel().getBlockState(pos);
        BlockState state = Objects.requireNonNull(super.getStateForPlacement(ctx));

        if (existing.is(this) && existing.getValue(FACING) == ctx.getClickedFace().getOpposite()) {
            return state.setValue(SINGLE, false);
        }

        if (direction == Direction.NORTH && ctx.getClickLocation().z - pos.getZ() > 0.5) {
            return state.setValue(FACING, Direction.SOUTH).setValue(SINGLE, true);
        } else if (direction == Direction.SOUTH && ctx.getClickLocation().z - pos.getZ() < 0.5) {
            return state.setValue(FACING, Direction.NORTH).setValue(SINGLE, true);
        } else if (direction == Direction.WEST && ctx.getClickLocation().x - pos.getX() > 0.5) {
            return state.setValue(FACING, Direction.EAST).setValue(SINGLE, true);
        } else if (direction == Direction.EAST && ctx.getClickLocation().x - pos.getX() < 0.5) {
            return state.setValue(FACING, Direction.WEST).setValue(SINGLE, true);
        } else {
            return state.setValue(FACING, direction);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SINGLE, FACING);
    }
}