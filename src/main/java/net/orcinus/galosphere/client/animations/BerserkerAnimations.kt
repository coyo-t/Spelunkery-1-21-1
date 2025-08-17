package net.orcinus.galosphere.client.animations

import dissonance.util.extension.buildAnimation
import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationChannel.Interpolations
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
object BerserkerAnimations
{
	@JvmField
	val BERSERKER_SHAKE = buildAnimation(2.5) {
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(-0.05f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(-0.06f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0.24f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.posVec(-0.26f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834331f, KeyframeAnimations.posVec(-0.04f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0.43f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.29167663f, KeyframeAnimations.posVec(-0.53f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.posVec(0.12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0.53f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.posVec(-0.82f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.45834337f, KeyframeAnimations.posVec(0.39f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.posVec(-1.16f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5834333f, KeyframeAnimations.posVec(0.86f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6249999999999999f, KeyframeAnimations.posVec(0.39f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6766665f, KeyframeAnimations.posVec(-1.57f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083431f, KeyframeAnimations.posVec(1.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7916764f, KeyframeAnimations.posVec(-1.71f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.834333f, KeyframeAnimations.posVec(2.04f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.8749999999999997f, KeyframeAnimations.posVec(-0.58f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9167663f, KeyframeAnimations.posVec(-1.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9583429f, KeyframeAnimations.posVec(2.41f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(-1.25f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0416763f, KeyframeAnimations.posVec(-1.23f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.083433f, KeyframeAnimations.posVec(3.28f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1249999999999998f, KeyframeAnimations.posVec(-2.65f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1676665f, KeyframeAnimations.posVec(-0.72f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.posVec(4.15f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(-4.33f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.posVec(0.24f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0.38f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3750000000000002f, KeyframeAnimations.posVec(-0.32f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.416767f, KeyframeAnimations.posVec(0.06f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0.5f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.041676664f, KeyframeAnimations.degreeVec(0f, 0f, -0.33f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, -0.22f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(0f, 0f, 0.68f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, -0.59f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834331f, KeyframeAnimations.degreeVec(0f, 0f, -0.07f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.24999999999999997f, KeyframeAnimations.degreeVec(0f, 0f, 0.78f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.29167663f, KeyframeAnimations.degreeVec(0f, 0f, -0.88f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 0.18f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0.78f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, -1.15f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.45834337f, KeyframeAnimations.degreeVec(0f, 0f, 0.52f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0.65f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, -1.36f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5834333f, KeyframeAnimations.degreeVec(0f, 0f, 0.92f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6249999999999999f, KeyframeAnimations.degreeVec(0f, 0f, 0.39f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6766665f, KeyframeAnimations.degreeVec(0f, 0f, -1.47f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083431f, KeyframeAnimations.degreeVec(0f, 0f, 1.34f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7499999999999998f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7916764f, KeyframeAnimations.degreeVec(0f, 0f, -1.45f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.834333f, KeyframeAnimations.degreeVec(0f, 0f, 1.72f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.8749999999999997f, KeyframeAnimations.degreeVec(0f, 0f, -0.49f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9167663f, KeyframeAnimations.degreeVec(0f, 0f, -1.26f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9583429f, KeyframeAnimations.degreeVec(0f, 0f, 2.03f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9999999999999996f, KeyframeAnimations.degreeVec(0f, 0f, -1.05f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0416763f, KeyframeAnimations.degreeVec(0f, 0f, -0.92f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.083433f, KeyframeAnimations.degreeVec(0f, 0f, 2.2f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1249999999999998f, KeyframeAnimations.degreeVec(0f, 0f, -1.63f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1676665f, KeyframeAnimations.degreeVec(0f, 0f, -0.41f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.degreeVec(0f, 0f, 2.21f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, 0f, -2.17f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0.17f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3433335f, KeyframeAnimations.degreeVec(0f, 0f, 1.15f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3750000000000002f, KeyframeAnimations.degreeVec(0f, 0f, -0.97f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.416767f, KeyframeAnimations.degreeVec(0f, 0f, 0.17f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(-2f, 11f, 11f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.posVec(-2f, 11f, 11f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(12.5f, 0f, -32.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(0f, 72.5f, 112.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.degreeVec(0f, 72.5f, 112.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0834335f, KeyframeAnimations.degreeVec(0f, -5.04f, 85.28f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(2f, 11f, 11f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.posVec(2f, 11f, 11f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(12.5f, 0f, 32.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(0f, -72.5f, -112.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.degreeVec(0f, -72.5f, -112.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0834335f, KeyframeAnimations.degreeVec(0f, 5.04f, -85.28f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, -13f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(0f, 10.5f, 9f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.posVec(0f, 10.5f, 9f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(70f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
	}

	@JvmField
	val BERSERKER_ATTACK = buildAnimation(3) {
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7916766f, KeyframeAnimations.posVec(0f, 11f, 6f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 8f, 5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, -16.62f, 1.38f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4167667f, KeyframeAnimations.posVec(0f, -15.62f, 1.38f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, -16.62f, 1.38f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.posVec(0f, -16.62f, 1.38f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7916766f, KeyframeAnimations.degreeVec(42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(-42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(-42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(47.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4167667f, KeyframeAnimations.degreeVec(42.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(47.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(46.56f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.degreeVec(40f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"firstSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.8343335f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"secondSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.4167667f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5834333f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.7083435f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.9167665f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"thirdSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.7916765f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, 10f, 5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, 10f, 5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(0f, -10f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.degreeVec(-163.26f, -5.19f, 16.74f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(-162.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4167667f, KeyframeAnimations.degreeVec(-80f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(-72.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.degreeVec(-62.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.75f, KeyframeAnimations.degreeVec(-35f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7916766f, KeyframeAnimations.posVec(0f, 10f, 5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 10f, 5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.375f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, -10f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.posVec(0f, -11f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.041676664f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(-163.26f, 5.19f, -16.74f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(-162.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.degreeVec(-80f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5416767f, KeyframeAnimations.degreeVec(-72.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.degreeVec(-62.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.degreeVec(-35f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.2916767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, -1.38f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3750000000000002f, KeyframeAnimations.posVec(0f, 1.27f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.416767f, KeyframeAnimations.posVec(0f, -1.07f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4583437f, KeyframeAnimations.posVec(0f, 0.82f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5000000000000004f, KeyframeAnimations.posVec(0f, -0.56f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5416772f, KeyframeAnimations.posVec(0f, 0.31f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.583434f, KeyframeAnimations.posVec(0f, -0.12f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
	}

	@JvmField
	val BERSERKER_IMPALING = buildAnimation(2.5) {
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(1f, 3f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.posVec(1f, 3f, 1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, 0.5f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2083435f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(1f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.posVec(1f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.degreeVec(-6.39f, 15.58f, 25.96f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.degreeVec(-6.39f, 15.58f, 25.96f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"firstSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.875f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.3433335f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"secondSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9583433f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.4167665f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"thirdSpike",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0416765f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 8.05f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(0f, 15f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.posVec(0f, 15f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, -1f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 0f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.posVec(0f, -1f, -4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5416767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					1.0416767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0.06f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1250000000000002f, KeyframeAnimations.posVec(0f, 0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.167667f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2083437f, KeyframeAnimations.posVec(0f, -0.44f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2500000000000004f, KeyframeAnimations.posVec(0f, -0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2916772f, KeyframeAnimations.posVec(0f, 0.38f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.343334f, KeyframeAnimations.posVec(0f, 0.23f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3750000000000007f, KeyframeAnimations.posVec(0f, -0.3f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.4167674f, KeyframeAnimations.posVec(0f, -0.28f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.4583441f, KeyframeAnimations.posVec(0f, 0.22f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5000000000000009f, KeyframeAnimations.posVec(0f, 0.31f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5416776f, KeyframeAnimations.posVec(0f, -0.13f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5834344f, KeyframeAnimations.posVec(0f, -0.31f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625000000000001f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.6766679f, KeyframeAnimations.posVec(0f, 0.29f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.7083446f, KeyframeAnimations.posVec(0f, 0.02f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.7500000000000013f, KeyframeAnimations.posVec(0f, -0.26f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.791678f, KeyframeAnimations.posVec(0f, -0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.8343348f, KeyframeAnimations.posVec(0f, 0.22f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.8750000000000016f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.9167683f, KeyframeAnimations.posVec(0f, -0.17f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.958345f, KeyframeAnimations.posVec(0f, -0.13f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.0000000000000018f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.0416783f, KeyframeAnimations.posVec(0f, 0.14f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.083435f, KeyframeAnimations.posVec(0f, -0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.1250000000000013f, KeyframeAnimations.posVec(0f, -0.12f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.167668f, KeyframeAnimations.posVec(0f, 0.03f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.2083444f, KeyframeAnimations.posVec(0f, 0.1f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.250000000000001f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.2916774f, KeyframeAnimations.posVec(0f, -0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.343334f, KeyframeAnimations.posVec(0f, -0.01f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.3750000000000004f, KeyframeAnimations.posVec(0f, 0.04f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.416767f, KeyframeAnimations.posVec(0f, 0.01f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.4583435f, KeyframeAnimations.posVec(0f, -0.01f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
	}

	@JvmField
	val BERSERKER_WALK = buildAnimation(2) {
		looping()
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, -0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0.7f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.posVec(0f, 0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.posVec(0f, -0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, -0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.posVec(0f, 0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, 0.7f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, 0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.posVec(0f, -0.49f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 2.43f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(1.7f, 0f, 2.92f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(2.4f, 0f, 2.96f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(1.7f, 0f, 2.56f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 1.76f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(-1.7f, 0f, 0.7f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(-2.4f, 0f, -0.47f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(-1.7f, 0f, -1.57f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, -2.43f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(1.7f, 0f, -2.92f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(2.4f, 0f, -2.96f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(1.7f, 0f, -2.56f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(0f, 0f, -1.76f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(-1.7f, 0f, -0.7f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(-2.4f, 0f, 0.47f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(-1.7f, 0f, 1.57f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(0f, 0f, 2.43f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.posVec(0f, -0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, -0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.posVec(0f, -0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, 0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.posVec(0f, 0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, -0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.posVec(0f, -0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.posVec(0f, -0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(1.8f, 1.71f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(1.27f, 1.37f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(0f, 0.82f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(-1.27f, 0.14f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-1.8f, -0.56f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(-1.27f, -1.17f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(0f, -1.6f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(1.27f, -1.79f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(1.8f, -1.71f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(1.27f, -1.37f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, -0.82f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(-1.27f, -0.14f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(-1.8f, 0.56f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(-1.27f, 1.17f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(0f, 1.6f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(1.27f, 1.79f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(1.8f, 1.71f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(-12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(12f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.9583434f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7083433f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9583433f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(-12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(-12f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"leftLeg",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.375f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftLeg",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(-12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"rightLeg",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.625f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.posVec(0f, 2f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightLeg",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(-11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(-8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(-4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(12f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.625f, KeyframeAnimations.degreeVec(11.09f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(8.49f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(4.59f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, -0.08f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, 0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.posVec(0f, -0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834331f, KeyframeAnimations.posVec(0f, 0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.24999999999999997f, KeyframeAnimations.posVec(0f, -0.04f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0416767f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, -0.08f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, 0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, -0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.posVec(0f, 0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, -0.04f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
	}

	@JvmField
	val BERSERKER_RECOVERING = buildAnimation(4) {
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, -2f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.posVec(0f, -6f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.posVec(0f, -5f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.posVec(0f, -6f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5416765f, KeyframeAnimations.posVec(0f, -6f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(72.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.2916765f, KeyframeAnimations.degreeVec(59.32f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.posVec(0f, 0f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.75f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"firstSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4583433f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.875f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"secondSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4583433f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5834334f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9583434f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.8343333f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"thirdSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4583433f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.6766666f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7916766f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9167666f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(0f, 0f, -2.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.degreeVec(0f, 0f, 2.5f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, 0f, 4f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 0f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.041676664f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.041676664f, KeyframeAnimations.posVec(0f, 0.3f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, -0.48f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, 0.55f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.posVec(0f, -0.52f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834331f, KeyframeAnimations.posVec(0f, 0.39f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.24999999999999997f, KeyframeAnimations.posVec(0f, -0.21f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
	}

	@JvmField
	val BERSERKER_PUNCH = buildAnimation(0.7083434) {
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.125f, KeyframeAnimations.posVec(0f, 0f, 9f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 0f, -3.75f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 0f, -3.75f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0f, 2.13f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(-40.66f, 63.53f, 43f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(-42.3f, 64.08f, 43.27f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.16766666f, KeyframeAnimations.degreeVec(-43.94f, 64.63f, 43.9f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(-45.58f, 65.19f, 44.77f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-75.67f, 31.5f, 26.94f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(-61.55f, -48.92f, -19.75f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.degreeVec(-61.55f, -48.92f, -19.75f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6766666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, 0f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.posVec(0f, 0f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.posVec(0f, 0f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.125f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(-2.41f, -2.5f, -4.33f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-2.41f, -2.5f, -4.33f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.posVec(0f, 2f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.posVec(0f, 2f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.posVec(0f, 0f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 0f, 1f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.20834334f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
	}

	@JvmField
	val BERSERKER_SPAWN_MINIONS = buildAnimation(3.5) {
		addAnimation(
			"root",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.3433333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, -0.71f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.posVec(0f, -0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.45834337f, KeyframeAnimations.posVec(0f, 0.51f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, -0.2f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.5834334f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.625f, KeyframeAnimations.posVec(0f, -0.97f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.6766665f, KeyframeAnimations.posVec(0f, 0.3f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7083431f, KeyframeAnimations.posVec(0f, 0.32f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7499999999999998f, KeyframeAnimations.posVec(0f, -0.23f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.7916766f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.834333f, KeyframeAnimations.posVec(0f, -0.02f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.8749999999999997f, KeyframeAnimations.posVec(0f, 0.1f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9167663f, KeyframeAnimations.posVec(0f, -0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9583429f, KeyframeAnimations.posVec(0f, -0.01f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					0.9999999999999996f, KeyframeAnimations.posVec(0f, 0.09f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0416763f, KeyframeAnimations.posVec(0f, -0.06f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.125f, KeyframeAnimations.posVec(0f, -0.71f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.1676665f, KeyframeAnimations.posVec(0f, 0.73f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.posVec(0f, -0.11f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.posVec(0f, 1f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3433335f, KeyframeAnimations.posVec(0f, -0.56f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.3750000000000002f, KeyframeAnimations.posVec(0f, -0.15f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.416767f, KeyframeAnimations.posVec(0f, 0.21f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.4583433f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5000000000000004f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.5416772f, KeyframeAnimations.posVec(0f, -0.08f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.583434f, KeyframeAnimations.posVec(0f, 0.12f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.6250000000000007f, KeyframeAnimations.posVec(0f, -0.03f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.6766674f, KeyframeAnimations.posVec(0f, -0.07f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.7083441f, KeyframeAnimations.posVec(0f, 0.06f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.posVec(0f, 0.42f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.8343344f, KeyframeAnimations.posVec(0f, -0.85f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.875000000000001f, KeyframeAnimations.posVec(0f, 0.4f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.9167679f, KeyframeAnimations.posVec(0f, 0.04f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					1.9583433f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.0000000000000013f, KeyframeAnimations.posVec(0f, -0.1f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.041678f, KeyframeAnimations.posVec(0f, 0.01f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.0834335f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.125f, KeyframeAnimations.posVec(0f, 0.97f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.1676674f, KeyframeAnimations.posVec(0f, -0.3f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.208344f, KeyframeAnimations.posVec(0f, -0.32f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.2500000000000004f, KeyframeAnimations.posVec(0f, 0.23f, 0f),
					Interpolations.LINEAR
				),
				Keyframe(
					2.2916765f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.LINEAR
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.3433333f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4167667f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.75f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.4583435f, KeyframeAnimations.posVec(0f, -2.25f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.posVec(0f, -2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.08343333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.8343334f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.9167667f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"rightArm",
			AnimationChannel(
				AnimationChannel.Targets.SCALE,
				Keyframe(
					2.4583435f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.scaleVec(1.0, 0.9, 1.0),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5834334f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.9167666f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.75f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.posVec(0f, 10f, 2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0834335f, KeyframeAnimations.posVec(0f, -15f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.4583435f, KeyframeAnimations.posVec(0f, -2.25f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.posVec(0f, -2f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.75f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.4167667f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.8343333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.degreeVec(-75f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.25f, KeyframeAnimations.degreeVec(-82.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"leftArm",
			AnimationChannel(
				AnimationChannel.Targets.SCALE,
				Keyframe(
					2.4583435f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.scaleVec(1.0, 0.9, 1.0),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(0f, 0f, -1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.posVec(0f, 0f, -1f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"body",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(-20f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(0.24f, 9.73f, -29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(-5.33f, -5.92f, -29.15f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(0.24f, -9.73f, 29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(-5.33f, 5.92f, 29.15f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.degreeVec(0.24f, 9.73f, -29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(-5.33f, -5.92f, -29.15f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(0.24f, -9.73f, 29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.degreeVec(-5.33f, 5.92f, 29.15f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.degreeVec(0.24f, 9.73f, -29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(-5.33f, -5.92f, -29.15f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.degreeVec(0.24f, -9.73f, 29.45f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.degreeVec(10.12f, -4.63f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"firstSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.5834335f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"secondSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4167667f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5416766f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.6766666f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0416767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.125f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2083433f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.3433333f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7083433f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.8343333f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0416765f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.1676665f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"thirdSpike",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.4583433f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5834334f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.7083434f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.375f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.875f, KeyframeAnimations.degreeVec(0f, 0f, 30f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.0834335f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.2083435f, KeyframeAnimations.degreeVec(0f, 0f, -27.5f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.6766665f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.POSITION,
				Keyframe(
					0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.posVec(0f, 7f, 3f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.posVec(-5f, -3f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.posVec(-3.67f, 0.67f, 0.2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.posVec(4.59f, -3.88f, -2.6f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.posVec(1.53f, 1.63f, -1.31f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.posVec(-5f, -3f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.posVec(-3.67f, 0.67f, 0.2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.posVec(4.59f, -3.88f, -2.6f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.posVec(1.53f, 1.63f, -1.31f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.posVec(-5f, -3f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.posVec(-3.67f, 0.67f, 0.2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.posVec(4.59f, -3.88f, -2.6f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.625f, KeyframeAnimations.posVec(1.19f, -4.85f, -1.24f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.posVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
		addAnimation(
			"head",
			AnimationChannel(
				AnimationChannel.Targets.ROTATION,
				Keyframe(
					0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.25f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.375f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.5f, KeyframeAnimations.degreeVec(28.6f, -39.85f, -16.37f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					0.625f, KeyframeAnimations.degreeVec(25.84f, -11.25f, 1.32f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1f, KeyframeAnimations.degreeVec(24.42f, 14.22f, 11.2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.0834333f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.1676667f, KeyframeAnimations.degreeVec(28.6f, -39.85f, -16.37f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.2916767f, KeyframeAnimations.degreeVec(25.84f, -11.25f, 1.32f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.6766667f, KeyframeAnimations.degreeVec(24.42f, 14.22f, 11.2f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					1.7916767f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2f, KeyframeAnimations.degreeVec(28.6f, -39.85f, -16.37f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					2.125f, KeyframeAnimations.degreeVec(25.84f, -11.25f, 1.32f),
					Interpolations.CATMULLROM
				),
				Keyframe(
					3.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
					Interpolations.CATMULLROM
				)
			)
		)
	}
}