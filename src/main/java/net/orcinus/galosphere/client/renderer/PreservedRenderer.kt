package net.orcinus.galosphere.client.renderer

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere.Companion.id
import net.orcinus.galosphere.client.model.PreservedModel
import net.orcinus.galosphere.entities.PreservedCorpse
import net.orcinus.galosphere.init.GModelLayers

@OnlyIn(Dist.CLIENT)
class PreservedRenderer(context: EntityRendererProvider.Context) :
	MobRenderer<PreservedCorpse, PreservedModel<PreservedCorpse>>(
		context,
		PreservedModel(context.bakeLayer(GModelLayers.PRESERVED)),
		0.5f
	)
{
	override fun getTextureLocation(entity: PreservedCorpse) = TEXTURE

	companion object
	{
		private val TEXTURE = id("textures/entity/preserved/preserved.png")
	}
}