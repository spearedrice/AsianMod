package org.spearedrice.asianmod.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.entity.AsianDadEntity;
import org.spearedrice.asianmod.entity.model.AsianDadEntityModel;
import org.spearedrice.asianmod.entity.model.ModEntityModelLayers;
import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityRenderer extends MobRenderer<
        AsianDadEntity,
        AsianDadEntityRenderState,
        AsianDadEntityModel> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AsianMod.MOD_ID, "textures/entity/asian_dad.png");

    public AsianDadEntityRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new AsianDadEntityModel(context.bakeLayer(ModEntityModelLayers.ASIAN_DAD)),
                0.5f
        );
    }

    @Override
    public AsianDadEntityRenderState createRenderState() {
        return new AsianDadEntityRenderState();
    }

    @Override
    public void extractRenderState(
            AsianDadEntity entity,
            AsianDadEntityRenderState state,
            float tickProgress
    ) {
        super.extractRenderState(entity, state, tickProgress);

        state.stance = entity.getStance();

        state.angerAnimationState.copyFrom(entity.angerAnimationState);
    }

    @Override
    public ResourceLocation getTextureLocation(AsianDadEntityRenderState state) {
        return TEXTURE;
    }
}