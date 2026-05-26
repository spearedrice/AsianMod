package org.spearedrice.asianmod.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.entity.AsianDadEntity;
import org.spearedrice.asianmod.entity.model.AsianDadEntityModel;
import org.spearedrice.asianmod.entity.model.ModEntityModelLayers;
import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityRenderer extends MobRenderer<AsianDadEntity, AsianDadEntityRenderState, AsianDadEntityModel> {
	private static final Identifier TEXTURE = AsianMod.id("textures/entity/asian_dad.png");

	public AsianDadEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new AsianDadEntityModel(context.bakeLayer(ModEntityModelLayers.ASIAN_DAD)), 0.5f);
	}

	@Override
	public AsianDadEntityRenderState createRenderState() {
		return new AsianDadEntityRenderState();
	}

	@Override
	public void extractRenderState(AsianDadEntity entity, AsianDadEntityRenderState state, float tickProgress) {
		super.extractRenderState(entity, state, tickProgress);
		state.idleAnimationState.copyFrom(entity.idleAnimationState);
		state.angryAnimationState.copyFrom(entity.angryAnimationState);
		state.throwingAnimationState.copyFrom(entity.throwingAnimationState);
	}

	@Override
	public Identifier getTextureLocation(AsianDadEntityRenderState state) {
		// Keep existing render-state-based texture method for compatibility with
		// the render-state system used by this renderer.
		return TEXTURE;
	}

	// Some rendering entry points call getTextureLocation with the entity itself.
	// Provide an overload to ensure the correct texture is always returned.
	public Identifier getTextureLocation(AsianDadEntity entity) {
		return TEXTURE;
	}
}