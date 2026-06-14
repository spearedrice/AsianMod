package org.spearedrice.asianmod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.block.ModBlocks;

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

                oreSmelting(
                        List.of(ModBlocks.PORCELAIN_ORE),
                        RecipeCategory.MISC,
                        ModItems.PORCELAIN,
                        0.3f,
                        200,
                        "porcelain_from_ore"
                );

                oreSmelting(
                        List.of(ModItems.RAW_NEPHRITE_JADE),
                        RecipeCategory.MISC,
                        ModItems.NEPHRITE_JADE,
                        0.7f,
                        200,
                        "nephrite_jade_from_raw"
                );

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_NEPHRITE_JADE_BLOCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.RAW_NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.RAW_NEPHRITE_JADE), has(ModItems.RAW_NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEPHRITE_BLOCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_HELMET)
                        .pattern("###")
                        .pattern("# #")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_CHESTPLATE)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_LEGGINGS)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_BOOTS)
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.COMBAT, ModItems.NEPHRITE_SWORD)
                        .pattern("#")
                        .pattern("#")
                        .pattern("X")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .define('X', Items.STICK)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_AXE)
                        .pattern("##")
                        .pattern(" #")
                        .pattern(" X")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .define('X', Items.STICK)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.TOOLS, ModItems.NEPHRITE_HOE)
                        .pattern("##")
                        .pattern(" X")
                        .pattern(" X")
                        .define('#', ModItems.NEPHRITE_JADE)
                        .define('X', Items.STICK)
                        .unlockedBy(getHasName(ModItems.NEPHRITE_JADE), has(ModItems.NEPHRITE_JADE))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PORCELAIN_BLOCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ModItems.PORCELAIN)
                        .unlockedBy(getHasName(ModItems.PORCELAIN), has(ModItems.PORCELAIN))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PORCELAIN_STAIRS, 4)
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', ModBlocks.PORCELAIN_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.PORCELAIN_BLOCK), has(ModBlocks.PORCELAIN_BLOCK))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PORCELAIN_SLAB, 6)
                        .pattern("###")
                        .define('#', ModBlocks.PORCELAIN_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.PORCELAIN_BLOCK), has(ModBlocks.PORCELAIN_BLOCK))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PORCELAIN_FENCE, 3)
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', ModBlocks.PORCELAIN_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.PORCELAIN_BLOCK), has(ModBlocks.PORCELAIN_BLOCK))
                        .save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.SILK_GLOVES)
                        .pattern("##")
                        .pattern("##")
                        .define('#', Items.STRING)
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.PAPER_LANTERN)
                        .pattern("#")
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.PAPER)
                        .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.SKY_LANTERN)
                        .pattern(" X ")
                        .pattern("#Y#")
                        .pattern(" # ")
                        .define('#', ModItems.PAPER_LANTERN)
                        .define('X', Items.FEATHER)
                        .define('Y', Items.STRING)
                        .unlockedBy(getHasName(ModItems.PAPER_LANTERN), has(ModItems.PAPER_LANTERN))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.INCENSE_STICK)
                        .pattern("X")
                        .pattern("#")
                        .define('#', Items.STICK)
                        .define('X', Items.BONE_MEAL)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.ABACUS)
                        .pattern("#X#")
                        .pattern("XYX")
                        .pattern("#X#")
                        .define('#', Items.STICK)
                        .define('X', Items.STRING)
                        .define('Y', Items.STICK)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(exporter);

                shapeless(RecipeCategory.MISC, ModItems.FERMENTED_RESIDUE)
                        .requires(Items.WHEAT)
                        .requires(Items.SUGAR)
                        .requires(Items.GLASS_BOTTLE)
                        .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                        .save(exporter);

                shaped(RecipeCategory.MISC, ModItems.SLIPPER)
                        .pattern("# #")
                        .pattern("## ")
                        .define('#', Items.LEATHER)
                        .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                        .save(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return AsianMod.MOD_ID + " Recipes";
    }
}