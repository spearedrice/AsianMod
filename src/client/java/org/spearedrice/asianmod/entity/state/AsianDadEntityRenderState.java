package org.spearedrice.asianmod.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class AsianDadEntityRenderState extends LivingEntityRenderState {

    public int stance = 0;

    public final AnimationState angryAnimationState = new AnimationState();
}