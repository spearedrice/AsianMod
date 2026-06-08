package org.spearedrice.asianmod.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

import org.spearedrice.asianmod.entity.AsianDadEntity;
import org.spearedrice.asianmod.entity.model.AsianDadEntityModel;
import org.spearedrice.asianmod.entity.model.ModEntityModelLayers;
import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityRenderer extends MobRenderer<AsianDadEntity, AsianDadEntityRenderState, AsianDadEntityModel> {

	public AsianDadEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new AsianDadEntityModel(context.bakeLayer(ModEntityModelLayers.ASIAN_DAD)), 0.5f);
	}

	@Override
	public AsianDadEntityRenderState createRenderState() {
		return new AsianDadEntityRenderState();
	}
	@Override
	public void extractRenderState(AsianDadEntity entity, AsianDadEntityRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.rightArmPose = this.getArmPose(entity, HumanoidArm.RIGHT);
		state.leftArmPose = this.getArmPose(entity, HumanoidArm.LEFT);
	}

	@Override
	public Identifier getTextureLocation(AsianDadEntityRenderState state) {
		return Identifier.fromNamespaceAndPath("asianmod", "textures/entity/asian_dad.png");
	}
	protected net.minecraft.client.model.HumanoidModel.ArmPose getArmPose(AsianDadEntity entity, HumanoidArm arm) {
		if(entity.isAggressive() && entity.isUsingItem()) {
			return net.minecraft.client.model.HumanoidModel.ArmPose.THROW_TRIDENT;
		}
		return net.minecraft.client.model.HumanoidModel.ArmPose.EMPTY;
	}
}