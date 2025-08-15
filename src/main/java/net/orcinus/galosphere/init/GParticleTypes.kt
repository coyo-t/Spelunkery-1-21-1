package net.orcinus.galosphere.init

import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GParticleTypes
{
	@JvmField
	val PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Galosphere.MODID)

	@JvmField
	val AURA_RINGER_INDICATOR = registerParticle("aura_ringer_indicator", false)
	@JvmField
	val SILVER_BOMB = registerParticle("item_silverbomb", false)
	@JvmField
	val WARPED = registerParticle("warped", false)
	@JvmField
	val ALLURITE_RAIN = registerParticle("allurite_rain", false)
	@JvmField
	val LUMIERE_RAIN = registerParticle("lumiere_rain", false)
	@JvmField
	val AMETHYST_RAIN = registerParticle("amethyst_rain", false)
	@JvmField
	val SPECTATE_ORB = registerParticle("spectate_orb", false)
	@JvmField
	val PINK_SALT_FALLING_DUST = registerParticle("pink_salt_falling_dust", false)
	@JvmField
	val IMPACT = registerParticle("impact", false)

	fun registerParticle(key: String, alwaysShow: Boolean): Supplier<SimpleParticleType>
	{
		return PARTICLES.register(key, Supplier { SimpleParticleType(alwaysShow) })
	}
}
