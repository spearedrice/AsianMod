package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.appearance.AsianModAppearance;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.enchantment.ModEnchantments;
import org.spearedrice.asianmod.entity.ModEntityTypes;
import org.spearedrice.asianmod.item.ModItems;

// :::datagen-translations:provider
public class AsianModEnglishLangProvider extends FabricLanguageProvider {

    protected AsianModEnglishLangProvider(
            FabricDataOutput dataOutput,
            CompletableFuture<HolderLookup.Provider> registryLookup
    ) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(
            HolderLookup.Provider holderLookup,
            TranslationBuilder translationBuilder
    ) {

        // core things
        translationBuilder.add("text.asianmod.greeting", "Hello there!");

        // items
        translationBuilder.add(ModItems.NEPHRITE_HELMET, "Nephrite Helmet");
        translationBuilder.add(ModItems.NEPHRITE_CHESTPLATE, "Nephrite Chestplate");
        translationBuilder.add(ModItems.NEPHRITE_LEGGINGS, "Nephrite Leggings");
        translationBuilder.add(ModItems.NEPHRITE_BOOTS, "Nephrite Boots");
        translationBuilder.add(ModItems.NEPHRITE_SWORD, "Nephrite Sword");

        translationBuilder.add(ModItems.SLIPPER, "Dad's Favorite Slipper");
        translationBuilder.add(ModItems.SLIPPER, "Slipper");

        translationBuilder.add(ModItems.SUSPICIOUS_SUBSTANCE, "Suspicious Substance");
        translationBuilder.add(ModItems.INCENSE_STICK, "Incense Stick");
        translationBuilder.add(ModItems.FUGU, "Fugu");
        translationBuilder.add(ModItems.COW_DUNG, "Cow Dung");

        translationBuilder.add(ModItems.COUNTER, "Counter");
        translationBuilder.add(ModItems.COUNTER, "Counter");
        translationBuilder.add(ModItems.COUNTER, "Used %1$s times");

        translationBuilder.add(ModItems.ABACUS, "Abacus");

        // effects / potion
        translationBuilder.add(
                Util.makeDescriptionId("effect",
                        Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "rice_wine")),
                "Rice Wine"
        );

        translationBuilder.add("item.minecraft.potion.effect.rice_wine", "Rice Wine");
        translationBuilder.add("death.attack.rice_wine", "%1$s died from Rice Wine damage!");

        // enchantments
        translationBuilder.addEnchantment(ModEnchantments.THUNDERING, "Thundering");
        translationBuilder.addEnchantment(ModEnchantments.REPULSION_CURSE, "Curse of Repulsion");

        // entities
        translationBuilder.add(ModEntityTypes.ASIAN_DAD, "Asian Dad");

        // blocks
        translationBuilder.add(ModBlocks.RAMMED_EARTH, "Rammed Earth");
        translationBuilder.add(ModBlocks.COMPACTED_TIMBER, "Compacted Timber");
        translationBuilder.add(ModBlocks.ABACUS_BLOCK, "Abacus Block");
        translationBuilder.add(ModBlocks.BRASS_CHEST, "Brass Chest");

        translationBuilder.add(ModBlocks.BELLOWS_BLOCK, "Bellows Block");
        translationBuilder.add(ModBlocks.CLAY_PIPE_BLOCK, "Clay Pipe Block");
        translationBuilder.add(ModBlocks.OIL_LAMP, "Oil Lamp");

        translationBuilder.add(ModBlocks.PORCELAIN_BLOCK, "Porcelain Block");
        translationBuilder.add(ModBlocks.PORCELAIN_STAIRS, "Porcelain Stairs");
        translationBuilder.add(ModBlocks.PORCELAIN_SLAB, "Porcelain Slab");
        translationBuilder.add(ModBlocks.PORCELAIN_FENCE, "Porcelain Fence");
        translationBuilder.add(ModBlocks.PORCELAIN_DOOR, "Porcelain Door");
        translationBuilder.add(ModBlocks.PORCELAIN_TRAPDOOR, "Porcelain Trapdoor");

        translationBuilder.add(ModBlocks.VERTICAL_BAMBOO_SLAB, "Vertical Bamboo Slab");
        translationBuilder.add(ModBlocks.LINGZHI, "Lingzhi");

        // item group
        translationBuilder.add("itemGroup.asianmod", "AsianMod");

        // keybinds
        translationBuilder.add("key.category.asianmod.custom_category", "AsianMod Custom Category");
        translationBuilder.add("key.asianmod.send_to_chat", "Send to Chat");

        // tooltip
        translationBuilder.add(
                "itemTooltip.asianmod.incense_stick",
                "This can call divine judgment on your enemies"
        );
    }
}
