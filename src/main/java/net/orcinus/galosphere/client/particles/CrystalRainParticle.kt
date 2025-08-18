package net.orcinus.galosphere.client.particles

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SimpleAnimatedParticle
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.SimpleParticleType
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

class CrystalRainParticle(
	world: ClientLevel,
	x: Double,
	y: Double,
	z: Double,
	velocityX: Double,
	velocityY: Double,
	velocityZ: Double,
	set: SpriteSet
) : SimpleAnimatedParticle(world, x, y, z, set, 0.0125f)
{
	init
	{
		this.xd = velocityX
		this.yd = velocityY
		this.zd = velocityZ
		this.quadSize *= 2
		this.lifetime = 60 + this.random.nextInt(12)
		this.setSpriteFromAge(set)
	}

	override fun move(x: Double, y: Double, z: Double)
	{
		boundingBox = boundingBox.move(x, y, z)
		setLocationFromBoundingbox()
	}

	@OnlyIn(Dist.CLIENT)
	class Provider(private val sprites: SpriteSet) : ParticleProvider<SimpleParticleType>
	{
		override fun createParticle(
			p_106566_: SimpleParticleType,
			world: ClientLevel,
			x: Double,
			y: Double,
			z: Double,
			velocityX: Double,
			velocityY: Double,
			velocityZ: Double
		): Particle
		{
			return CrystalRainParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprites)
		}
	}
}
