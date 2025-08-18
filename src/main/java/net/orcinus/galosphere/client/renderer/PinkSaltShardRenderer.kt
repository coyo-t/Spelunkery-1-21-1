package net.orcinus.galosphere.client.renderer

import net.minecraft.client.renderer.entity.ArrowRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere.Companion.id
import net.orcinus.galosphere.entities.PinkSaltShard

@OnlyIn(Dist.CLIENT)
class PinkSaltShardRenderer(context: EntityRendererProvider.Context) : ArrowRenderer<PinkSaltShard>(context)
{
	override fun getTextureLocation(entity: PinkSaltShard) = TEXTURE

	companion object
	{
		val TEXTURE = id("textures/entity/projectiles/pink_salt_shard.png")
	}
}