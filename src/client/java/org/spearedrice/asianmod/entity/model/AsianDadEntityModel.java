package org.spearedrice.asianmod.entity.model;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import org.spearedrice.asianmod.entity.animation.AsianDadAnimations;
import org.spearedrice.asianmod.entity.state.AsianDadEntityRenderState;

public class AsianDadEntityModel extends EntityModel<AsianDadEntityRenderState> {

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    private final KeyframeAnimation angryStance;

    public AsianDadEntityModel(ModelPart root) {
        super(root);

        this.head = root.getChild(PartNames.HEAD);
        this.body = root.getChild(PartNames.BODY);
        this.leftArm = root.getChild(PartNames.LEFT_ARM);
        this.rightArm = root.getChild(PartNames.RIGHT_ARM);
        this.leftLeg = root.getChild(PartNames.LEFT_LEG);
        this.rightLeg = root.getChild(PartNames.RIGHT_LEG);

        this.angryStance = AsianDadAnimations.ANGRY_STANCE.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {

        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild(PartNames.HEAD,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4, -8, -4, 8, 8, 8),
                PartPose.offset(0, 0, 0)
        );

        root.addOrReplaceChild(PartNames.BODY,
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4, 0, -2, 8, 12, 4),
                PartPose.offset(0, 0, 0)
        );

        root.addOrReplaceChild(PartNames.LEFT_ARM,
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-1, -2, -2, 4, 12, 4),
                PartPose.offset(5, 2, 0)
        );

        root.addOrReplaceChild(PartNames.RIGHT_ARM,
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .mirror()
                        .addBox(-3, -2, -2, 4, 12, 4),
                PartPose.offset(-5, 2, 0)
        );

        root.addOrReplaceChild(PartNames.LEFT_LEG,
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2, 0, -2, 4, 12, 4),
                PartPose.offset(2, 12, 0)
        );

        root.addOrReplaceChild(PartNames.RIGHT_LEG,
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-2, 0, -2, 4, 12, 4),
                PartPose.offset(-2, 12, 0)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(AsianDadEntityRenderState state) {

        super.setupAnim(state);

        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
        this.head.xRot = state.xRot * Mth.DEG_TO_RAD;

        if (state.angryAnimationState.isStarted()) {
            this.angryStance.apply(state.angryAnimationState, state.ageInTicks);
            return;
        }

        float walkSpeed = state.walkAnimationSpeed;
        float walkPos = state.walkAnimationPos;

        this.leftLeg.xRot = Mth.cos(walkPos * 0.6662F) * 1.4F * walkSpeed;
        this.rightLeg.xRot = Mth.cos(walkPos * 0.6662F + Mth.PI) * 1.4F * walkSpeed;

        this.leftArm.xRot = Mth.cos(walkPos * 0.6662F + Mth.PI) * 1.2F * walkSpeed;
        this.rightArm.xRot = Mth.cos(walkPos * 0.6662F) * 1.2F * walkSpeed;

        this.rightArm.zRot = 0.15F;
        this.leftArm.zRot = -0.15F;
    }
}