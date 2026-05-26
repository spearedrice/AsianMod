package org.spearedrice.asianmod.datagen;

import java.util.Optional;
import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.item.ModItems;
import org.spearedrice.asianmod.potion.AsianModPotions;
import org.spearedrice.asianmod.advancement.ModCriteria;
import org.spearedrice.asianmod.advancement.ParameterizedUseToolCriterion;
import org.spearedrice.asianmod.advancement.UseToolCriterion;

public class AsianModAdvancementProvider extends FabricAdvancementProvider {

	public AsianModAdvancementProvider(FabricDataOutput output,
									   java.util.concurrent.CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider lookup, Consumer<AdvancementHolder> consumer) {

		AdvancementHolder getSlipper = Advancement.Builder.advancement()
				.display(
						ModItems.SLIPPER,
						Component.literal("Parental Authority"),
						Component.literal("You obtained the slipper."),
						Identifier.withDefaultNamespace("textures/gui/advancements/backgrounds/adventure.png"),
						AdvancementType.TASK,
						true,
						true,
						false
				)
				.addCriterion("got_slipper",
						InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SLIPPER))
				.save(consumer, AsianMod.MOD_ID + ":get_slipper");

		AdvancementHolder drinkRiceWine = Advancement.Builder.advancement()
				.parent(getSlipper)
				.display(
						Items.POTION,
						Component.literal("Bad Decisions"),
						Component.literal("You drank rice wine."),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("drink_rice_wine",
						ConsumeItemTrigger.TriggerInstance.usedItem(
								ItemPredicate.Builder.item()
						)
				)
				.save(consumer, AsianMod.MOD_ID + ":drink_rice_wine");

		AdvancementHolder useSlipper = Advancement.Builder.advancement()
				.parent(getSlipper)
				.display(
						ModItems.SLIPPER,
						Component.literal("Discipline"),
						Component.literal("Use the slipper."),
						null,
						AdvancementType.GOAL,
						true,
						true,
						false
				)
				.addCriterion("use_slipper",
						ModCriteria.USE_TOOL.createCriterion(
								new UseToolCriterion.Conditions(Optional.empty())
						)
				)
				.save(consumer, AsianMod.MOD_ID + ":use_slipper");

		AdvancementHolder useSlipperFiveTimes = Advancement.Builder.advancement()
				.parent(useSlipper)
				.display(
						ModItems.SLIPPER,
						Component.literal("Repeated Offenses"),
						Component.literal("Use the slipper 5 times."),
						null,
						AdvancementType.CHALLENGE,
						true,
						true,
						false
				)
				.addCriterion("use_slipper_5",
						ModCriteria.PARAMETERIZED_USE_TOOL.createCriterion(
								new ParameterizedUseToolCriterion.Conditions(Optional.empty(), 5)
						)
				)
				.save(consumer, AsianMod.MOD_ID + ":use_slipper_5");
	}
}