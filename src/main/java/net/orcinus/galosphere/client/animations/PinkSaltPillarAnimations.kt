package net.orcinus.galosphere.client.animations

import dissonance.util.extension.buildAnimation
import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
object PinkSaltPillarAnimations
{
	@JvmField
	val PINK_SALT_PILLAR_EMERGE = buildAnimation(0.125) {
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.SCALE,
				Keyframe(
					0f, KeyframeAnimations.scaleVec(0.5, 0.0, 0.5),
					AnimationChannel.Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.scaleVec(0.8, 0.4, 0.8),
					AnimationChannel.Interpolations.CATMULLROM
				),
				Keyframe(
					0.125f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					AnimationChannel.Interpolations.CATMULLROM
				)
			)
		)
	}

	@JvmField
	val PINK_SALT_PILLAR_RETRACT = buildAnimation(0.16766666) {
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.SCALE,
				Keyframe(
					0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					AnimationChannel.Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					AnimationChannel.Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.scaleVec(0.7, 1.0, 0.7),
					AnimationChannel.Interpolations.CATMULLROM
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.scaleVec(0.7, 0.0, 0.6),
					AnimationChannel.Interpolations.CATMULLROM
				)
			)
		)
	}
}