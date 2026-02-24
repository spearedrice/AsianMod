package org.spearedrice.asianmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spearedrice.asianmod.block.ModBlocks;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.END_STONE, Blocks.END_STONE_BRICKS,
            Blocks.OAK_LOG, ModBlocks.NEPHRITE_JADE_BLOCK,
            Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK,
            Blocks.DIRT, Blocks.EMERALD_BLOCK,
            Blocks.BEDROCK, Blocks.AIR
    );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock) && !world.isClient()) {
            Block resultBlock = CHISEL_MAP.get(clickedBlock);
            if (resultBlock != null) {
                world.setBlockState(context.getBlockPos(), resultBlock.getDefaultState());

                context.getStack().damage(1,
                        (net.minecraft.server.network.ServerPlayerEntity) context.getPlayer(),
                        EquipmentSlot.MAINHAND);

                world.playSound(null,
                        context.getBlockPos(),
                        SoundEvents.BLOCK_GRINDSTONE_USE,
                        SoundCategory.BLOCKS,
                        1.0f,
                        1.0f);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}