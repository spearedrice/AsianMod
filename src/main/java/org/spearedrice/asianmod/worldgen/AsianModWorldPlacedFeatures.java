package org.spearedrice.asianmod.worldgen;

import java.util.List;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import org.spearedrice.asianmod.AsianMod;

public class AsianModWorldPlacedFeatures {
	public static final ResourceKey<PlacedFeature> PORCELAIN_ORE_PLACED_KEY =
			ResourceKey.create(
				Registries.PLACED_FEATURE,
				Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_ore_placed")
			);

	public static final ResourceKey<PlacedFeature> NEPHRITE_JADE_ORE_PLACED_KEY =
			ResourceKey.create(
				Registries.PLACED_FEATURE,
				Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "nephrite_jade_ore_placed")
			);

	public static final ResourceKey<PlacedFeature> PORCELAIN_TREE_PLACED_KEY =
			ResourceKey.create(
				Registries.PLACED_FEATURE,
				Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_tree_placed")
			);

	public static void configure(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


		List<PlacementModifier> porcelainOreModifiers = List.of(
				CountPlacement.of(12),
					BiomeFilter.biome(),
					InSquarePlacement.spread(),
				HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(-32), 3))
		);


		List<PlacementModifier> nephriteOreModifiers = List.of(
				CountPlacement.of(12),
					BiomeFilter.biome(),
					InSquarePlacement.spread(),
				HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(0), 3))
		);

		List<PlacementModifier> porcelainTreeModifiers = List.of(
				RarityFilter.onAverageOnceEvery(10),
				BiomeFilter.biome(),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE
		);

		context.register(
				PORCELAIN_ORE_PLACED_KEY,
				new PlacedFeature(
					configuredFeatures.getOrThrow(AsianModWorldConfiguredFeatures.PORCELAIN_ORE_CONFIGURED_KEY),
					porcelainOreModifiers
				)
		);

		context.register(
				NEPHRITE_JADE_ORE_PLACED_KEY,
				new PlacedFeature(
					configuredFeatures.getOrThrow(AsianModWorldConfiguredFeatures.NEPHRITE_JADE_ORE_CONFIGURED_KEY),
					nephriteOreModifiers
				)
		);

		context.register(
				PORCELAIN_TREE_PLACED_KEY,
				new PlacedFeature(
					configuredFeatures.getOrThrow(AsianModWorldConfiguredFeatures.PORCELAIN_TREE_CONFIGURED_KEY),
					porcelainTreeModifiers
				)
		);
	}
}