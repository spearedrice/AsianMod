package org.spearedrice.asianmod.recipe;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class UpgradingRecipe implements Recipe<UpgradingRecipeInput> {

    private final ItemStack result;
    private final Ingredient baseItem;
    private final Ingredient upgradeItem;

    public UpgradingRecipe(ItemStack result, Ingredient baseItem, Ingredient upgradeItem) {
        this.baseItem = baseItem;
        this.upgradeItem = upgradeItem;
        this.result = result;
    }

    public ItemStack getResult() {
        return result;
    }

    public Ingredient getBaseItem() {
        return baseItem;
    }

    public Ingredient getUpgradeItem() {
        return upgradeItem;
    }

    @Override
    public boolean matches(UpgradingRecipeInput input, Level level) {
        return baseItem.test(input.baseItem()) && upgradeItem.test(input.upgradeItem());
    }

    @Override
    public ItemStack assemble(UpgradingRecipeInput input, HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<UpgradingRecipeInput>> getSerializer() {
        return AsianModRecipes.UPGRADING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<UpgradingRecipeInput>> getType() {
        return AsianModRecipes.UPGRADING_RECIPE_TYPE;
    }

    @Override
    public @Nullable RecipeBookCategory recipeBookCategory() {
        return null;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}