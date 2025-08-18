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
}