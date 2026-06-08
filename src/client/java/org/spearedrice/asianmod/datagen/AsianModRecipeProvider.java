package org.spearedrice.asianmod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import org.spearedrice.asianmod.AsianMod;

public class AsianModRecipeProvider extends FabricRecipeProvider {

    public AsianModRecipeProvider(FabricDataOutput output,
                                  CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {

            @Override
            public void buildRecipes() {


                shapeless(RecipeCategory.MISC, Items.DIRT)
                        .requires(Items.COARSE_DIRT)
                        .unlockedBy(getHasName(Items.COARSE_DIRT), has(Items.COARSE_DIRT))
                        .save(exporter);


                shaped(RecipeCategory.MISC, Items.CRAFTING_TABLE)
                        .pattern("ll")
                        .pattern("ll")
                        .define('l', ItemTags.LOGS)
                        .group("multi_bench")
                        .unlockedBy(getHasName(Items.CRAFTING_TABLE), has(Items.CRAFTING_TABLE))
                        .save(exporter);


                shaped(RecipeCategory.MISC, Items.LOOM)
                        .pattern("ww")
                        .pattern("ll")
                        .define('w', ItemTags.WOOL)
                        .define('l', ItemTags.LOGS)
                        .group("multi_bench")
                        .unlockedBy(getHasName(Items.LOOM), has(Items.LOOM))
                        .save(exporter);


                doorBuilder(Items.OAK_DOOR, Ingredient.of(Items.OAK_BUTTON))
                        .unlockedBy(getHasName(Items.OAK_BUTTON), has(Items.OAK_BUTTON))
                        .save(exporter);


                oreSmelting(
                        List.of(Items.BREAD, Items.COOKIE, Items.HAY_BLOCK),
                        RecipeCategory.FOOD,
                        Items.WHEAT,
                        0.1f,
                        300,
                        "food_to_wheat"
                );
            }
        };
    }

    @Override
    public String getName() {
        return AsianMod.MOD_ID + " Recipes";
    }
}