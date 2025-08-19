package net.orcinus.galosphere.init

import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GParticleTypes
{
	@JvmField val PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Galosphere.MODID)

	@JvmField val AURA_RINGER_INDICATOR = rp("aura_ringer_indicator")

	@JvmField val WARPED = rp("warped")
	@JvmField val ALLURITE_RAIN = rp("allurite_rain")
	@JvmField val LUMIERE_RAIN = rp("lumiere_rain")
	@JvmField val AMETHYST_RAIN = rp("amethyst_rain")
	@JvmField val PINK_SALT_FALLING_DUST = rp("pink_salt_falling_dust")

	private fun rp(key: String)
		= PARTICLES.register(key, Supplier { SimpleParticleType(false) })
}
