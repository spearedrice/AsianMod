package org.spearedrice.asianmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;

import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {

                List<ItemLike> nephriteSmeltables = List.of(
                        ModItems.RAW_NEPHRITE_JADE,
                        ModBlocks.NEPHRITE_JADE_ORE,
                        ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE
                );

                oreSmelting(nephriteSmeltables, RecipeCategory.MISC, ModItems.NEPHRITE_JADE, 0.25f, 200, "nephrite_jade");
                oreBlasting(nephriteSmeltables, RecipeCategory.MISC, ModItems.NEPHRITE_JADE, 0.25f, 100, "nephrite_jade");

                offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.NEPHRITE_JADE.asItem(), RecipeCategory.DECORATIONS, ModBlocks.NEPHRITE_JADE_BLOCK.asItem());

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_NEPHRITE_JADE_BLOCK)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.RAW_NEPHRITE_JADE), has(ModItems.RAW_NEPHRITE_JADE))
                        .save(exporter);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_NEPHRITE_JADE, 9)
                        .requires(ModBlocks.RAW_NEPHRITE_JADE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_NEPHRITE_JADE_BLOCK), has(ModBlocks.RAW_NEPHRITE_JADE_BLOCK))
                        .save(exporter);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_NEPHRITE_JADE, 32)
                        .requires(ModBlocks.ASIAN_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.ASIAN_BLOCK), has(ModBlocks.ASIAN_BLOCK))
                        .save(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "AsianMod Recipes";
    }
}

// code is radioactive