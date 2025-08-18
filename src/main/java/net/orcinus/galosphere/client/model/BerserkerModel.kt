package net.orcinus.galosphere.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.HierarchicalModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.client.animations.BerserkerAnimations
import net.orcinus.galosphere.entities.Berserker

@OnlyIn(Dist.CLIENT)
class BerserkerModel<T : Berserker>(root: ModelPart) : HierarchicalModel<T>()
{
	private val root = root.getChild("root")

	override fun setupAnim(
		entity: T?,
		limbSwing: Float,
		limbSwingAmount: Float,
		ageInTicks: Float,
		netHeadYaw: Float,
		headPitch: Float
	)
	{
		this.root().allParts.forEach(ModelPart::resetPose)
		val head = this.root.getChild("head")
		head.xRot = headPitch * (Math.PI.toFloat() / 180)
		head.yRot = netHeadYaw * (Math.PI.toFloat() / 180)
		this.animateWalk(BerserkerAnimations.BERSERKER_WALK, limbSwing, limbSwingAmount, 3.0f, 4.5f)
		this.animate(entity!!.attackAnimationState, BerserkerAnimations.BERSERKER_ATTACK, ageInTicks, 1.0f)
		this.animate(entity.impalingAnimationState, BerserkerAnimations.BERSERKER_IMPALING, ageInTicks, 1.0f)
		this.animate(entity.roarAnimationState, BerserkerAnimations.BERSERKER_SHAKE, ageInTicks, 1.0f)
		this.animate(entity.punchAnimationState, BerserkerAnimations.BERSERKER_PUNCH, ageInTicks, 1.0f)
		this.animate(entity.summoningAnimationState, BerserkerAnimations.BERSERKER_SPAWN_MINIONS, ageInTicks, 1.0f)
	}

	override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int)
	{
		this.root.render(poseStack, vertexConsumer, i, j, k)
	}

	override fun root(): ModelPart
	{
		return this.root
	}

	companion object
	{
		fun createBodyLayer(): LayerDefinition
		{
			val meshdefinition = MeshDefinition()
			val partdefinition = meshdefinition.getRoot()

			val root =
				partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0f, 24.0f, 0.0f))

			val rightLeg = root.addOrReplaceChild(
				"rightLeg",
				CubeListBuilder.create().texOffs(62, 0)
					.addBox(-4.0f, -2.5f, -2.0f, 8.0f, 15.0f, 6.0f, CubeDeformation(0.0f)),
				PartPose.offset(-7.0f, -12.5f, 11.0f)
			)

			val leftLeg = root.addOrReplaceChild(
				"leftLeg",
				CubeListBuilder.create().texOffs(58, 62)
					.addBox(-4.0f, -2.5f, -2.0f, 8.0f, 15.0f, 6.0f, CubeDeformation(0.0f)),
				PartPose.offset(7.0f, -12.5f, 11.0f)
			)

			val rightArm = root.addOrReplaceChild(
				"rightArm",
				CubeListBuilder.create().texOffs(0, 36)
					.addBox(-4.0f, -3.5f, -4.0f, 8.0f, 24.0f, 8.0f, CubeDeformation(0.0f))
					.texOffs(32, 75).addBox(-4.0f, -5.5f, -4.0f, 8.0f, 2.0f, 8.0f, CubeDeformation(0.0f)),
				PartPose.offset(-10.0f, -20.5f, -8.0f)
			)

			val leftArm = root.addOrReplaceChild(
				"leftArm",
				CubeListBuilder.create().texOffs(32, 36)
					.addBox(-4.0f, -3.5f, -4.0f, 8.0f, 24.0f, 8.0f, CubeDeformation(0.0f))
					.texOffs(78, 75).addBox(-4.0f, -5.5f, -4.0f, 8.0f, 2.0f, 8.0f, CubeDeformation(0.0f)),
				PartPose.offset(10.0f, -20.5f, -8.0f)
			)

			val body = root.addOrReplaceChild(
				"body",
				CubeListBuilder.create().texOffs(0, 0)
					.addBox(-10.0f, -5.75f, -18.5f, 20.0f, 14.0f, 22.0f, CubeDeformation(0.0f)),
				PartPose.offsetAndRotation(0.0f, -17.25f, 9.5f, -0.3927f, 0.0f, 0.0f)
			)

			val firstSpike = body.addOrReplaceChild(
				"firstSpike",
				CubeListBuilder.create().texOffs(64, 36)
					.addBox(-3.0f, -17.0f, -3.5f, 6.0f, 18.0f, 6.0f, CubeDeformation(0.0f)),
				PartPose.offsetAndRotation(0.0f, -5.75f, -13.0f, -0.1745f, 0.0f, 0.0f)
			)

			val secondSpike = body.addOrReplaceChild(
				"secondSpike",
				CubeListBuilder.create().texOffs(0, 0)
					.addBox(-2.0f, -15.0f, -1.5f, 4.0f, 16.0f, 4.0f, CubeDeformation(0.0f)),
				PartPose.offsetAndRotation(0.0f, -5.75f, -6.0f, -0.2618f, 0.0f, 0.0f)
			)

			val thirdSpike = body.addOrReplaceChild(
				"thirdSpike",
				CubeListBuilder.create().texOffs(90, 0)
					.addBox(-1.0f, -12.0f, -1.5f, 2.0f, 13.0f, 4.0f, CubeDeformation(0.0f)),
				PartPose.offsetAndRotation(0.0f, -5.75f, 0.5f, -0.3491f, 0.0f, 0.0f)
			)

			val head = root.addOrReplaceChild(
				"head",
				CubeListBuilder.create().texOffs(24, 68).addBox(-4.0f, 3.0f, -6.0f, 8.0f, 2.0f, 5.0f, CubeDeformation(0.0f))
					.texOffs(0, 68).addBox(-4.0f, -5.0f, -6.0f, 8.0f, 8.0f, 8.0f, CubeDeformation(0.0f)),
				PartPose.offset(0.0f, -25.0f, -9.0f)
			)

			return LayerDefinition.create(meshdefinition, 112, 96)
		}
	}
}