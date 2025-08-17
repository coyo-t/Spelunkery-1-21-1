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
import net.orcinus.galosphere.client.animations.PinkSaltPillarAnimations
import net.orcinus.galosphere.entities.PinkSaltPillar

@OnlyIn(Dist.CLIENT)
class PinkSaltPillarModel<T : PinkSaltPillar?>(root: ModelPart) : HierarchicalModel<T?>()
{
	private val root: ModelPart

	init
	{
		this.root = root.getChild("root")
	}

	override fun setupAnim(
		entity: T?,
		limbSwing: Float,
		limbSwingAmount: Float,
		ageInTicks: Float,
		netHeadYaw: Float,
		headPitch: Float
	)
	{
		this.root().allParts.forEach { it.resetPose() }
		this.animate(entity!!.emergeAnimationState, PinkSaltPillarAnimations.PINK_SALT_PILLAR_EMERGE, ageInTicks)
		this.animate(entity.retractAnimationState, PinkSaltPillarAnimations.PINK_SALT_PILLAR_RETRACT, ageInTicks)
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
		@JvmStatic
		fun createBodyLayer(): LayerDefinition
		{
			val meshdefinition = MeshDefinition()
			val partdefinition = meshdefinition.getRoot()

			val root = partdefinition.addOrReplaceChild(
				"root",
				CubeListBuilder.create().texOffs(0, 0)
					.addBox(-5.0f, -32.0f, -5.0f, 10.0f, 32.0f, 10.0f, CubeDeformation(0.0f)),
				PartPose.offset(0.0f, 24.0f, 0.0f)
			)

			return LayerDefinition.create(meshdefinition, 48, 48)
		}
	}
}