package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GPotions
{
	@JvmField
	val POTIONS = DeferredRegister.create(Registries.POTION, Galosphere.MODID)

	@JvmField
	val ASTRAL = POTIONS.register("astral") { r -> Potion(MobEffectInstance(GMobEffects.ASTRAL, 1800)) }

	@JvmField
	val LONG_ASTRAL = POTIONS.register("long_astral") { r -> Potion("astral", MobEffectInstance(GMobEffects.ASTRAL, 3600)) }
}
