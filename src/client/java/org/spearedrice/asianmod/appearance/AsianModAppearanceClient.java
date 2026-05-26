package org.spearedrice.asianmod.appearance;

import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.client.event.AsianDadMusicEventListener;
import org.spearedrice.asianmod.entity.ModEntityTypes;
import org.spearedrice.asianmod.fluid.ModFluids;

public class AsianModAppearanceClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "color"), RainTintSource.MAP_CODEC);
		ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
			if (blockAndTintGetter != null && blockPos != null) {
				BlockState stateBelow = blockAndTintGetter.getBlockState(blockPos.below());

				if (stateBelow.is(Blocks.GRASS_BLOCK)) {
					return 0xB0E0E6;
				}
			}

			return 0xC0C0C0;
		}, ModBlocks.PORCELAIN_LEAVES);

		ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
			if (blockAndTintGetter != null && blockPos != null) {
				BlockState stateBelow = blockAndTintGetter.getBlockState(blockPos.below());

				if (stateBelow.is(Blocks.GRASS_BLOCK)) {
					return 0x98FB98;
				}
			}

			return 0xFFDAB9;
		}, ModBlocks.LINGZHI);

		BlockRenderLayerMap.putBlock(ModBlocks.PORCELAIN_LEAVES, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.PORCELAIN_ORE, ChunkSectionLayer.SOLID);
		BlockRenderLayerMap.putBlock(ModBlocks.LINGZHI, ChunkSectionLayer.CUTOUT);

		FluidRenderHandlerRegistry.INSTANCE.register(
				ModFluids.MERCURY_STILL,
				ModFluids.MERCURY_FLOWING,
				new SimpleFluidRenderHandler(
						SimpleFluidRenderHandler.WATER_STILL,
						SimpleFluidRenderHandler.WATER_FLOWING,
						0xB5B5B5
				)
		);
		BlockRenderLayerMap.putBlock(ModBlocks.MERCURY, ChunkSectionLayer.TRANSLUCENT);

		// Register entity renderers
		EntityRendererRegistry.register(ModEntityTypes.SLIPPER_ENTITY, ThrownItemRenderer::new);

		// Register event listeners
		AsianDadMusicEventListener.register();
	}
}