package org.spearedrice.asianmod.client.appearance;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.fluid.ModFluids;

public class AsianModAppearanceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // color
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {

            if (world != null && pos != null) {
                BlockState below = world.getBlockState(pos.below());

                if (below.is(Blocks.GRASS_BLOCK)) {
                    return 0xB0E0E6;
                }
            }

            return 0xC0C0C0;
        }, ModBlocks.PORCELAIN_LEAVES);

        BlockRenderLayerMap.putBlock(ModBlocks.PORCELAIN_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PORCELAIN_ORE, ChunkSectionLayer.SOLID);

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.MERCURY_STILL,
                ModFluids.MERCURY_FLOWING,
                new SimpleFluidRenderHandler(
                        SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        0xB5B5B5 // metallic mercury color
                )
        );

        BlockRenderLayerMap.putBlock(ModBlocks.MERCURY_BLOCK, ChunkSectionLayer.TRANSLUCENT);
    }
}