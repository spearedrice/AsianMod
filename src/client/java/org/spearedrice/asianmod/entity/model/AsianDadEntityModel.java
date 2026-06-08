package org.spearedrice.asianmod.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityModel extends EntityModel<AsianDadEntityRenderState> {
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public AsianDadEntityModel(ModelPart root) {
		super(root);
		this.head = root.getChild(PartNames.HEAD);
		this.hat = this.head.getChild(PartNames.HAT);
		this.body = root.getChild(PartNames.BODY);
		this.rightArm = root.getChild(PartNames.RIGHT_ARM);
		this.leftArm = root.getChild(PartNames.LEFT_ARM);
		this.rightLeg = root.getChild(PartNames.RIGHT_LEG);
		this.leftLeg = root.getChild(PartNames.LEFT_LEG);
	}

	public static LayerDefinition createBodyLayer() {

		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();


		root.addOrReplaceChild(PartNames.HEAD,
			CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8),
			PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = root.getChild(PartNames.HEAD);
		head.addOrReplaceChild(PartNames.HAT,
			CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(0.5F)),
			PartPose.ZERO);


		root.addOrReplaceChild(PartNames.BODY,
			CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4),
			PartPose.offset(0.0F, 0.0F, 0.0F));


		root.addOrReplaceChild(PartNames.RIGHT_ARM,
			CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4),
			PartPose.offset(-5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_ARM,
			CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4),
			PartPose.offset(5.0F, 2.0F, 0.0F));


		root.addOrReplaceChild(PartNames.RIGHT_LEG,
			CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
			PartPose.offset(-1.9F, 12.0F, 0.0F));
		root.addOrReplaceChild(PartNames.LEFT_LEG,
			CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4),
			PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void setupAnim(AsianDadEntityRenderState state) {
		this.hat.visible = false;
		if(state.rightArmPose == net.minecraft.client.model.HumanoidModel.ArmPose.THROW_TRIDENT) {
			this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
		}
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
	}

}