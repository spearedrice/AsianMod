package org.spearedrice.asianmod.worldgen;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;

public class AsianModWorldConfiguredFeatures {

 	public static final ResourceKey<ConfiguredFeature<?, ?>> PORCELAIN_ORE_CONFIGURED_KEY =
			ResourceKey.create(
					Registries.CONFIGURED_FEATURE,
					Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_ore")
			);

	public static final ResourceKey<ConfiguredFeature<?, ?>> NEPHRITE_JADE_ORE_CONFIGURED_KEY =
			ResourceKey.create(
					Registries.CONFIGURED_FEATURE,
					Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "nephrite_jade_ore")
			);

	public static final ResourceKey<ConfiguredFeature<?, ?>> PORCELAIN_TREE_CONFIGURED_KEY =
			ResourceKey.create(
					Registries.CONFIGURED_FEATURE,
					Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_tree")
			);

	public static void configure(BootstrapContext<ConfiguredFeature<?, ?>> context) {

		RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

		List<OreConfiguration.TargetBlockState> porcelainOreConfig =
				List.of(
						OreConfiguration.target(stoneReplaceable, ModBlocks.PORCELAIN_ORE.defaultBlockState()),
						OreConfiguration.target(deepslateReplaceable, ModBlocks.PORCELAIN_ORE.defaultBlockState())
				);

		List<OreConfiguration.TargetBlockState> nephriteOreConfig =
				List.of(
						OreConfiguration.target(stoneReplaceable, ModBlocks.NEPHRITE_JADE_ORE.defaultBlockState()),
						OreConfiguration.target(deepslateReplaceable, ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE.defaultBlockState())
				);

		context.register(
				PORCELAIN_ORE_CONFIGURED_KEY,
				new ConfiguredFeature<>(
						Feature.ORE,
						new OreConfiguration(porcelainOreConfig, 12)
				)
		);

		context.register(
				NEPHRITE_JADE_ORE_CONFIGURED_KEY,
				new ConfiguredFeature<>(
						Feature.ORE,
						new OreConfiguration(nephriteOreConfig, 12)
				)
		);


		TreeConfiguration porcelainTree = new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.PORCELAIN_LOG),
				new StraightTrunkPlacer(4, 2, 0),
				BlockStateProvider.simple(ModBlocks.PORCELAIN_LEAVES),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 2)
		).build();

		context.register(
				PORCELAIN_TREE_CONFIGURED_KEY,
				new ConfiguredFeature<>(Feature.TREE, porcelainTree)
		);
	}
}