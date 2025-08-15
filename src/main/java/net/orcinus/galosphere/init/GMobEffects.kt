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
		MOB_EFFECTS.register("astral", Supplier { GMobEffect(MobEffectCategory.BENEFICIAL, 12891319) })
	@JvmField
	val BLOCK_BANE =
		MOB_EFFECTS.register("block_bane", Supplier { GMobEffect(MobEffectCategory.HARMFUL, 7612935) })
}
