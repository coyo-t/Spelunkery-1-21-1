package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GMobEffects
{
	class MF(category: MobEffectCategory, col:Int): MobEffect(category, col)

	private infix fun MobEffectCategory.mfDoom (c:Int) = MF(this, c)
	private infix fun (()->MobEffect).named (s:String) = MOB_EFFECTS.register(s, Supplier(this))


	@JvmField val MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Galosphere.MODID)

	@JvmField val ASTRAL = { MobEffectCategory.BENEFICIAL mfDoom 0xc4b4b7 } named "astral"
	@JvmField val BLOCK_BANE = { MobEffectCategory.HARMFUL mfDoom 0x742a07 } named "block_bane"
}
