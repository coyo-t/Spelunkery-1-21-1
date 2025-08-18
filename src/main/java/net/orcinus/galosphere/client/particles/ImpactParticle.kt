package net.orcinus.galosphere.client.particles

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.minecraft.client.Camera
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.Mth
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import kotlin.math.max

@OnlyIn(Dist.CLIENT)
class ImpactParticle (clientLevel: ClientLevel, d: Double, e: Double, f: Double, sprites: SpriteSet):
	TextureSheetParticle(clientLevel, d, e, f)
{
	private val sprites: SpriteSet

	init
	{
		this.alpha = 0.8f
		this.quadSize = 1.3f
		this.lifetime = 48
		this.sprites = sprites
		this.rCol = 1.0f
		this.gCol = 1.0f
		this.bCol = 1.0f
		this.setSpriteFromAge(sprites)
	}

	override fun tick()
	{
		quadSize = Mth.lerp(0.25f, quadSize, 6f)
		if (age++ >= lifetime)
		{
			remove()
		}
		else
		{
			alpha = Mth.lerp(0.08f, alpha, 0f)
		}
		if (alpha <= 0.01f) remove()
		setSpriteFromAge(sprites)
	}

	override fun render(consumer: VertexConsumer, camera: Camera, delta: Float)
	{
		renderRotatedQuad(consumer, camera, Axis.XP.rotation(Mth.PI / 2), delta)
		renderRotatedQuad(consumer, camera, Axis.XN.rotation(Mth.PI / 2), delta)
	}

	override fun getRenderType(): ParticleRenderType
	{
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT
	}

	override fun getLightColor(tint: Float): Int
	{
		return max(50, super.getLightColor(tint))
	}

	@OnlyIn(Dist.CLIENT)
	class Provider(private val sprites: SpriteSet) : ParticleProvider<SimpleParticleType>
	{
		override fun createParticle(
			particleOptions: SimpleParticleType,
			clientLevel: ClientLevel,
			d: Double,
			e: Double,
			f: Double,
			g: Double,
			h: Double,
			i: Double
		): Particle
		{
			return ImpactParticle(clientLevel, d, e, f, sprites)
		}
	}
}