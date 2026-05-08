package org.spearedrice.asianmod.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.PartNames;

public class AsianDadAnimations {

    public static final AnimationDefinition ANGRY_STANCE =
            AnimationDefinition.Builder.withLength(1.0f)
                    .looping()
                    .addAnimation(PartNames.HEAD, new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(-10, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
                    .addAnimation(PartNames.LEFT_ARM, new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-90, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
                    .addAnimation(PartNames.RIGHT_ARM, new AnimationChannel(
                            AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(-90, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
                    .build();
}