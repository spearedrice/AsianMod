package org.spearedrice.asianmod.menu.custom;

import java.util.List;
import java.util.Optional;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import org.spearedrice.asianmod.recipe.ModRecipes;
import org.spearedrice.asianmod.recipe.UpgradingRecipe;
import org.spearedrice.asianmod.recipe.UpgradingRecipeInput;

public class UpgradingMenu extends AbstractContainerMenu {

    private final Container input = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            slotsChanged(this);
        }
    };

    private final ResultContainer output = new ResultContainer();
    private final Level level;

    public UpgradingMenu(int id, Inventory inventory) {
        super(ModRecipes.UPGRADING_MENU_TYPE, id);

        this.level = inventory.player.level();

        addSlot(new Slot(input, 0, 27, 47));
        addSlot(new Slot(input, 1, 76, 47));

        addSlot(new Slot(output, 0, 134, 47) {
            @Override
            public void onTake(Player player, ItemStack stack) {
                UpgradingMenu.this.onTake(player, stack);
            }
        });

        addStandardInventorySlots(inventory, 8, 84);
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);

        if (container == input && level instanceof ServerLevel serverLevel) {
            UpgradingRecipeInput recipeInput =
                    new UpgradingRecipeInput(input.getItem(0), input.getItem(1));

            Optional<RecipeHolder<UpgradingRecipe>> recipe =
                    serverLevel.recipeAccess().getRecipeFor(
                            ModRecipes.UPGRADING_RECIPE_TYPE,
                            recipeInput,
                            serverLevel
                    );

            if (recipe.isPresent()) {
                output.setItem(0, recipe.get().value().assemble(recipeInput, serverLevel.registryAccess()));
                output.setRecipeUsed(recipe.get());
            } else {
                output.clearContent();
                output.setRecipeUsed(null);
            }
        }
    }

    public void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player, stack.getCount());
        output.awardUsedRecipes(player, List.of(input.getItem(0), input.getItem(1)));

        input.removeItem(0, stack.getCount());
        input.removeItem(1, stack.getCount());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Minimal sanity so shift-click isn't completely dead
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        int inputSize = 2;

        if (index < inputSize) {
            if (!moveItemStackTo(stack, inputSize, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!moveItemStackTo(stack, 0, inputSize, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, input);
    }
}