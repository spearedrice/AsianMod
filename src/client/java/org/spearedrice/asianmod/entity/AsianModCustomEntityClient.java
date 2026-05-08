package org.spearedrice.asianmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

import org.spearedrice.asianmod.entity.model.ModEntityModelLayers;
import org.spearedrice.asianmod.entity.renderer.AsianDadEntityRenderer;
import org.spearedrice.asianmod.entity.renderer.SlipperEntityRenderer;
import org.spearedrice.asianmod.entity.ModEntityTypes;

public class AsianModCustomEntityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.registerModelLayers();

        EntityRenderers.register(
                ModEntityTypes.ASIAN_DAD,
                AsianDadEntityRenderer::new
        );

        EntityRenderers.register(
                ModEntityTypes.SLIPPER,
                SlipperEntityRenderer::new
        );
    }
}