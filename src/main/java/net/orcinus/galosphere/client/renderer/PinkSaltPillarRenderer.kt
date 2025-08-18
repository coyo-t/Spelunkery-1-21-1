package net.orcinus.galosphere.client.renderer

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere.Companion.id
import net.orcinus.galosphere.client.model.PinkSaltPillarModel
import net.orcinus.galosphere.entities.PinkSaltPillar
import net.orcinus.galosphere.init.GModelLayers

@OnlyIn(Dist.CLIENT)
class PinkSaltPillarRenderer(context: EntityRendererProvider.Context) : EntityRenderer<PinkSaltPillar>(context)
{
	private val model = PinkSaltPillarModel<PinkSaltPillar>(context.bakeLayer(GModelLayers.PINK_SALT_PILLAR))

	override fun render(
		entity: PinkSaltPillar,
		f: Float,
		g: Float,
		poseStack: PoseStack,
		multiBufferSource: MultiBufferSource,
		i: Int
	)
	{
		if (!entity.isActive())
		{
			return
		}
		val ticks = entity.tickCount + g
		poseStack.pushPose()
		poseStack.scale(1.0f, -1.0f, 1.0f)
		poseStack.translate(0.0f, -1.0f, 0.0f)
		this.model.setupAnim(entity, 0.0f, 0.0f, ticks, entity.getYRot(), entity.getXRot())
		val vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(TEXTURE))
		this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY)
		poseStack.popPose()
		super.render(entity, f, g, poseStack, multiBufferSource, i)
	}

	override fun getTextureLocation(entity: PinkSaltPillar) = TEXTURE

	companion object
	{
		private val TEXTURE = id("textures/entity/pink_salt_pillar/pink_salt_pillar.png")
	}
}