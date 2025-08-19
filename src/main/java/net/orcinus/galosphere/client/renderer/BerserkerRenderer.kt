package net.orcinus.galosphere.client.renderer

import com.google.common.collect.Maps
import net.minecraft.Util
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere.Companion.id
import net.orcinus.galosphere.client.model.BerserkerModel
import net.orcinus.galosphere.entities.Berserker
import net.orcinus.galosphere.init.GModelLayers
import java.util.function.Consumer

@OnlyIn(Dist.CLIENT)
class BerserkerRenderer(context: EntityRendererProvider.Context) : MobRenderer<Berserker, BerserkerModel>(
	context,
	BerserkerModel(context.bakeLayer(GModelLayers.BERSERKER)),
	0.9f
)
{
	override fun getTextureLocation(entity: Berserker) = TEXTURES[entity.stage]!!

	override fun isShaking(livingEntity: Berserker) = livingEntity.isShedding

	companion object
	{
		private val TEXTURES = mapOf(
			0 to id("textures/entity/berserker/berserker.png"),
			1 to id("textures/entity/berserker/half_damaged_berserker.png"),
			2 to id("textures/entity/berserker/high_damaged_berserker.png"),
			3 to id("textures/entity/berserker/stationary_berserker.png"),
		)
	}
}