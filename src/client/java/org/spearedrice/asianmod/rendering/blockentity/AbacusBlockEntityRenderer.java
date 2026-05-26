package org.spearedrice.asianmod.rendering.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import org.jetbrains.annotations.Nullable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import org.spearedrice.asianmod.block.entity.custom.AbacusBlockEntity;

public class AbacusBlockEntityRenderer implements BlockEntityRenderer<AbacusBlockEntity, AbacusBlockEntityRenderState> {
    private final Font font;

    public AbacusBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        font = context.font();
    }

    @Override
    public AbacusBlockEntityRenderState createRenderState() {
        return new AbacusBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(AbacusBlockEntity blockEntity, AbacusBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setClicks(blockEntity.getClicks());
    }

    @Override
    public void submit(AbacusBlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        matrices.pushPose();
        matrices.translate(0.5, 1, 0.5);
        matrices.mulPose(Axis.XP.rotationDegrees(90));
        matrices.scale(1/18f, 1/18f, 1/18f);
        String text = state.getClicks() + "";
        float width = font.width(text);
        queue.submitText(
                matrices,
                -width / 2, -4f,
                Component.literal(text).getVisualOrderText(),
                false,
                Font.DisplayMode.SEE_THROUGH,
                state.lightCoords,
                0xffffffff,
                0,
                0
        );
        matrices.popPose();
    }
}