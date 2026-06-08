package org.spearedrice.asianmod.block.entity.custom;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import org.spearedrice.asianmod.block.entity.ModBlockEntities;
import org.spearedrice.asianmod.container.ImplementedContainer;
import org.spearedrice.asianmod.menu.custom.UpgradingMenu;

public class UpgradingBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {

	public static final int CONTAINER_SIZE = 3 * 3;
	private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

	public UpgradingBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ABACUS_BLOCK_ENTITY, pos, state);
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return items;
	}

	@Override
	protected void saveAdditional(ValueOutput valueOutput) {
		super.saveAdditional(valueOutput);
		ContainerHelper.saveAllItems(valueOutput, items);
	}

	@Override
	protected void loadAdditional(ValueInput valueInput) {
		super.loadAdditional(valueInput);
		ContainerHelper.loadAllItems(valueInput, items);
	}

	@Override
	@NonNull
	public Component getDisplayName() {
		return Component.translatable("block.asianmod.upgrading");
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new UpgradingMenu(containerId, inventory);
	}
}