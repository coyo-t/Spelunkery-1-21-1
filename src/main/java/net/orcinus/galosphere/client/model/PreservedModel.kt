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
import net.minecraft.util.Mth
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.client.animations.PreservedAnimations
import net.orcinus.galosphere.entities.PreservedCorpse
import kotlin.math.min

@OnlyIn(Dist.CLIENT)
class PreservedModel<T : PreservedCorpse>(root: ModelPart) : HierarchicalModel<T>()
{
	private val root = root.getChild("root")

	override fun setupAnim(
		entity: T,
		limbSwing: Float,
		limbSwingAmount: Float,
		ageInTicks: Float,
		netHeadYaw: Float,
		headPitch: Float
	)
	{
		this.root().allParts.forEach(ModelPart::resetPose)
		this.animate(entity.digAnimationState, PreservedAnimations.PRESERVED_EMERGING_FLOOR, ageInTicks)
		val h = min(0.5f, 3.0f * limbSwingAmount)
		val i = limbSwing * 0.8662f
		val j = Mth.cos(i)
		val k = Mth.sin(i)
		val l = min(0.1f, h)
		this.getPart("body").zRot = 0.1f * k * h
		this.getPart("body").xRot = 1.0f * j * l
		this.getPart("right_leg").xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount
		this.getPart("left_leg").xRot = Mth.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount
		this.getPart("left_arm").xRot = -Mth.HALF_PI - (0.4f * j * h)
		this.getPart("left_arm").zRot = 0.0f
		this.getPart("right_arm").xRot = -Mth.HALF_PI - (0.4f * k * h)
		this.getPart("right_arm").zRot = 0.0f
	}

	private fun getPart(name: String): ModelPart
	{
		return this.root.getChild(name)
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
			val partdefinition = meshdefinition.root

			val root =
				partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0f, 24.0f, 0.0f))

			val right_leg = root.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.1f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, CubeDeformation(0.0f)),
				PartPose.offset(-1.9f, -12.0f, 0.0f)
			)

			val left_leg = root.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create().texOffs(0, 0).mirror()
					.addBox(-1.9f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, CubeDeformation(0.0f)).mirror(false),
				PartPose.offset(1.9f, -12.0f, 0.0f)
			)

			val right_arm = root.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create().texOffs(40, 0)
					.addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, CubeDeformation(0.0f))
					.texOffs(40, 16).addBox(-3.0f, -4.0f, -2.0f, 4.0f, 2.0f, 4.0f, CubeDeformation(0.0f)),
				PartPose.offsetAndRotation(-5.0f, -22.0f, 0.0f, -1.5708f, 0.0f, 0.0f)
			)

			val left_arm = root.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create().texOffs(40, 0).mirror()
					.addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, CubeDeformation(0.0f)).mirror(false)
					.texOffs(40, 16).mirror().addBox(-1.0f, -4.0f, -2.0f, 4.0f, 2.0f, 4.0f, CubeDeformation(0.0f))
					.mirror(false),
				PartPose.offsetAndRotation(5.0f, -22.0f, 0.0f, -1.5708f, 0.0f, 0.0f)
			)

			val body = root.addOrReplaceChild(
				"body",
				CubeListBuilder.create().texOffs(16, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, CubeDeformation(0.0f))
					.texOffs(0, 16).addBox(-4.0f, -5.0f, -2.0f, 8.0f, 5.0f, 4.0f, CubeDeformation(0.0f)),
				PartPose.offset(0.0f, -24.0f, 0.0f)
			)

			return LayerDefinition.create(meshdefinition, 64, 32)
		}
	}
}