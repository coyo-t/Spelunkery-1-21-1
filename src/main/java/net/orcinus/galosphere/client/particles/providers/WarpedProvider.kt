package net.orcinus.galosphere.client.particles.providers

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpellParticle
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.SimpleParticleType

class WarpedProvider(private val sprite: SpriteSet) : ParticleProvider<SimpleParticleType>
{
	override fun createParticle(
		type: SimpleParticleType,
		world: ClientLevel,
		x: Double,
		y: Double,
		z: Double,
		velX: Double,
		velY: Double,
		velZ: Double
	): Particle
	{
		return MySpellz(world, x, y, z, velX, velY, velZ, sprite).apply {
			setColor(world.random.nextFloat(), 0.98f, 1.0f)
		}
	}

	// This Is Stupid
	internal class MySpellz(
		level: ClientLevel,
		x: Double,
		y: Double,
		z: Double,
		xSpeed: Double,
		ySpeed: Double,
		zSpeed: Double,
		sprites: SpriteSet
	) : SpellParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites)
}
