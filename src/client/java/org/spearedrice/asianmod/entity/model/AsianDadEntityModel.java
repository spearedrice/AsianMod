package org.spearedrice.asianmod.entity.model;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import org.spearedrice.asianmod.entity.animation.AsianDadAnimations;
import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityModel extends EntityModel<AsianDadEntityRenderState> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final KeyframeAnimation idle;
	private final KeyframeAnimation angry;
	private final KeyframeAnimation throwing;

	public AsianDadEntityModel(ModelPart root) {
		super(root);
		body = root.getChild(PartNames.BODY);
		head = root.getChild(PartNames.HEAD);
		leftArm = root.getChild(PartNames.LEFT_ARM);
		rightArm = root.getChild(PartNames.RIGHT_ARM);
		leftLeg = root.getChild(PartNames.LEFT_LEG);
		rightLeg = root.getChild(PartNames.RIGHT_LEG);
		this.idle = AsianDadAnimations.IDLE.bake(root);
		this.angry = AsianDadAnimations.ANGRY.bake(root);
		this.throwing = AsianDadAnimations.THROWING.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();
		root.addOrReplaceChild(
				PartNames.BODY,
				CubeListBuilder.create().texOffs(32, 32).addBox(
						-4,
						0,
						-2,
						8,
						12,
						4
				),
				PartPose.offset(0, 0, 0)
		);
		root.addOrReplaceChild(
				PartNames.HEAD,
				CubeListBuilder.create().texOffs(0, 0).addBox(-4, -8, -4, 8, 8, 8),
				PartPose.offset(0, 0, 0)
		);
		root.addOrReplaceChild(
				PartNames.LEFT_ARM,
				CubeListBuilder.create().texOffs(80, 32).addBox(-4, -2, -2, 4, 12, 4),
				PartPose.offset(4, 2, 0)
		);
		root.addOrReplaceChild(
				PartNames.RIGHT_ARM,
				CubeListBuilder.create().texOffs(80, 32).addBox(0, -2, -2, 4, 12, 4),
				PartPose.offset(-4, 2, 0)
		);
		root.addOrReplaceChild(
				PartNames.LEFT_LEG,
				CubeListBuilder.create().texOffs(0, 32).addBox(-2, 0, -2, 4, 12, 4),
				PartPose.offset(2, 12, 0)
		);
		root.addOrReplaceChild(
				PartNames.RIGHT_LEG,
				CubeListBuilder.create().texOffs(32, 32).addBox(-2, 0, -2, 4, 12, 4),
				PartPose.offset(-2, 12, 0)
		);
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(AsianDadEntityRenderState state) {
		super.setupAnim(state);
		if (state.throwingAnimationState.isStarted()) {
			this.throwing.apply(state.throwingAnimationState, state.ageInTicks);
		} else if (state.angryAnimationState.isStarted()) {
			this.angry.apply(state.angryAnimationState, state.ageInTicks);
		} else if (state.idleAnimationState.isStarted()) {
			this.idle.apply(state.idleAnimationState, state.ageInTicks);
		} else {
			head.xRot = state.xRot * Mth.RAD_TO_DEG;
			head.yRot = state.yRot * Mth.RAD_TO_DEG;
			float limbSwingAmplitude = state.walkAnimationSpeed;
			float limbSwingAnimationProgress = state.walkAnimationPos;
			leftArm.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662f) * limbSwingAmplitude;
			rightArm.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662f + Mth.PI) * limbSwingAmplitude;
			leftLeg.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662f + Mth.PI) * limbSwingAmplitude;
			rightLeg.xRot = Mth.cos(limbSwingAnimationProgress * 0.6662f) * limbSwingAmplitude;
		}
	}
}