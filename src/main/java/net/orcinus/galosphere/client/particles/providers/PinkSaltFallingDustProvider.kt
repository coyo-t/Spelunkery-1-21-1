package net.orcinus.galosphere.client.particles.providers

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.FallingDustParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.SimpleParticleType
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class PinkSaltFallingDustProvider(private val sprite: SpriteSet) : ParticleProvider<SimpleParticleType>
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
	): Particle?
	{
		val j = 15568753
		val k = (j shr 16 and 0xFF).toFloat() / 255.0f
		val l = (j shr 8 and 0xFF).toFloat() / 255.0f
		val m = (j and 0xFF).toFloat() / 255.0f
		return MyParticle(clientLevel, d, e, f, k, l, m, this.sprite)
	}

	internal class MyParticle(
		level: ClientLevel,
		x: Double,
		y: Double,
		z: Double,
		xSpeed: Float,
		ySpeed: Float,
		zSpeed: Float,
		sprites: SpriteSet
	) : FallingDustParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites)
}