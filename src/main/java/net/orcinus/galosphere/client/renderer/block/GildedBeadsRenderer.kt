package net.orcinus.galosphere.client.renderer.block

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.resources.model.Material
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.RotationSegment
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere.Companion.id
import net.orcinus.galosphere.blocks.GildedBeadsBlock
import net.orcinus.galosphere.blocks.blockentities.GildedBeadsBlockEntity
import net.orcinus.galosphere.init.GModelLayers
import java.util.function.Function

@OnlyIn(Dist.CLIENT)
class GildedBeadsRenderer(context: BlockEntityRendererProvider.Context) : BlockEntityRenderer<GildedBeadsBlockEntity>
{
	private val gilded_beads: ModelPart

	init
	{
		val modelPart = context.bakeLayer(GModelLayers.GILDED_BEADS)
		this.gilded_beads = modelPart.getChild("gilded_beads")
	}

	override fun render(
		blockEntity: GildedBeadsBlockEntity,
		f: Float,
		poseStack: PoseStack,
		multiBufferSource: MultiBufferSource,
		i: Int,
		j: Int
	)
	{
		poseStack.pushPose()
		poseStack.translate(0.5, 1.5, 0.5)
		poseStack.mulPose(
			Axis.YP.rotationDegrees(
				-RotationSegment.convertToDegrees(
					blockEntity.blockState.getValue(GildedBeadsBlock.ROTATION)
				)
			)
		)
		poseStack.mulPose(Axis.XP.rotationDegrees(180f))
		val vertexConsumer = FUNCTION.apply(blockEntity.blockState).buffer(multiBufferSource) { RenderType.entityCutoutNoCull(it) }
		this.gilded_beads.render(poseStack, vertexConsumer, i, j)
		poseStack.popPose()
	}

	companion object
	{
		val FUNCTION: Function<BlockState, Material> = Function { state ->
			Material(
				TextureAtlas.LOCATION_BLOCKS,
				id("entity/gilded_beads/" + (if (state.getValue(BlockStateProperties.BOTTOM)) "gilded_beads_head" else "gilded_beads_body"))
			)
		}
	}
}