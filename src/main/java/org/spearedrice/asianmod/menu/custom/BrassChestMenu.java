package org.spearedrice.asianmod.menu.custom;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import org.spearedrice.asianmod.block.entity.custom.BrassChestBlockEntity;
import org.spearedrice.asianmod.menu.ModMenuType;

public class BrassChestMenu extends AbstractContainerMenu {

    private final Container container;

    public BrassChestMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(BrassChestBlockEntity.CONTAINER_SIZE));
    }

    public BrassChestMenu(final int containerId, final Inventory inventory, final Container container) {
        super(ModMenuType.BRASS_CHEST, containerId);
        checkContainerSize(container, BrassChestBlockEntity.CONTAINER_SIZE);
        this.container = container;

        container.startOpen(inventory.player);

        int rows = 3;
        int columns = 3;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                int slot = x + y * 3;
                this.addSlot(new Slot(container, slot, 62 + x * 18, 17 + y * 18));
            }
        }

        this.addStandardInventorySlots(inventory, 8, 84);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack clicked = stack.copy();

        if (slotIndex < container.getContainerSize()) {
            if (!this.moveItemStackTo(stack, container.getContainerSize(), this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.moveItemStackTo(stack, 0, container.getContainerSize(), false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return clicked;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }
}