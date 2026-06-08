package org.spearedrice.asianmod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import org.spearedrice.asianmod.component.ModComponents;
import org.spearedrice.asianmod.worldgen.AsianModWorldPlacedFeatures;

public class AsianMod implements ModInitializer {
	public static final String MOD_ID = "asianmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final SimpleParticleType SPARKLE_PARTICLE = FabricParticleTypes.simple();

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "sparkle_particle"), SPARKLE_PARTICLE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				AsianModWorldPlacedFeatures.PORCELAIN_ORE_PLACED_KEY
		);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				AsianModWorldPlacedFeatures.NEPHRITE_JADE_ORE_PLACED_KEY
		);
		BiomeModifications.addFeature(
				BiomeSelectors.tag(BiomeTags.IS_FOREST),
				GenerationStep.Decoration.VEGETAL_DECORATION,
				AsianModWorldPlacedFeatures.PORCELAIN_TREE_PLACED_KEY
		);
		ComponentTooltipAppenderRegistry.addAfter(DataComponents.DAMAGE, ModComponents.COMPONENT_WITH_TOOLTIP);
	}
}