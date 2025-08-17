package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.effects.GMobEffect
import java.util.function.Supplier

object GMobEffects
{
	@JvmField
	val MOB_EFFECTS =
		DeferredRegister.create(Registries.MOB_EFFECT, Galosphere.MODID)

	@JvmField
	val ASTRAL =
		MOB_EFFECTS.register("astral") { rs -> GMobEffect(MobEffectCategory.BENEFICIAL, 0xc4b4b7) }

	@JvmField
	val BLOCK_BANE =
		MOB_EFFECTS.register("block_bane") { rs -> GMobEffect(MobEffectCategory.HARMFUL, 0x742a07) }
}
