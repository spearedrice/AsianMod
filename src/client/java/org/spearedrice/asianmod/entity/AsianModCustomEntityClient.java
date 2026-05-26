package org.spearedrice.asianmod.entity;

import net.minecraft.client.renderer.entity.EntityRenderers;

import net.fabricmc.api.ClientModInitializer;

import org.spearedrice.asianmod.entity.ModEntityTypes;
import org.spearedrice.asianmod.entity.model.ModEntityModelLayers;
import org.spearedrice.asianmod.entity.renderer.AsianDadEntityRenderer;
import org.spearedrice.asianmod.entity.renderer.SlipperEntityRenderer;

public class AsianModCustomEntityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityModelLayers.registerModelLayers();
		EntityRenderers.register(ModEntityTypes.ASIAN_DAD_ENTITY, AsianDadEntityRenderer::new);
		EntityRenderers.register(ModEntityTypes.SLIPPER_ENTITY, SlipperEntityRenderer::new);
	}
}