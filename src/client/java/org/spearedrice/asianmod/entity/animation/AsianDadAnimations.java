package org.spearedrice.asianmod.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.PartNames;

public class AsianDadAnimations {
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(1)
			.looping()
			.addAnimation(PartNames.HEAD, new AnimationChannel(
					AnimationChannel.Targets.ROTATION,
					new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation(PartNames.LEFT_LEG, new AnimationChannel(
					AnimationChannel.Targets.ROTATION,
					new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation(PartNames.RIGHT_LEG, new AnimationChannel(
					AnimationChannel.Targets.ROTATION,
					new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition ANGRY = AnimationDefinition.Builder.withLength(0.5f)
			.looping()
			.addAnimation(PartNames.HEAD, new AnimationChannel(
					AnimationChannel.Targets.ROTATION,
					new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25f, KeyframeAnimations.degreeVec(5, 0, 0), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5f, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition THROWING = AnimationDefinition.Builder.withLength(0.6f)
			.addAnimation(PartNames.HEAD, new AnimationChannel(
					AnimationChannel.Targets.ROTATION,
					new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.2f, KeyframeAnimations.degreeVec(-10, 0, 0), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4f, KeyframeAnimations.degreeVec(-20, 0, 0), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6f, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}