package net.orcinus.galosphere.init

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.ConditionalEffect
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GEnchantmentEffectComponents
{
	@JvmField
	val DATA_COMPONENTS =
		DeferredRegister.create(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Galosphere.MODID)

	@JvmField
	val SALTBOUND_TABLET_DECELERATION =
		DATA_COMPONENTS.register("saltbound_tablet_deceleration") { rs ->
			DataComponentType.builder<MutableList<ConditionalEffect<EnchantmentValueEffect>>>().persistent(
				ConditionalEffect.codec(
					EnchantmentValueEffect.CODEC,
					LootContextParamSets.ENCHANTED_ENTITY
				).listOf()
			).build()
		}

	@JvmField
	val SALTBOUND_TABLET_SUSTAIN =
		DATA_COMPONENTS.register("saltbound_tablet_sustain") { rs ->
			DataComponentType.builder<MutableList<ConditionalEffect<EnchantmentValueEffect>>>().persistent(
				ConditionalEffect.codec(
					EnchantmentValueEffect.CODEC,
					LootContextParamSets.ENCHANTED_ENTITY
				).listOf()
			).build()
		}

	@JvmField
	val SALTBOUND_TABLET_RUPTURE =
		DATA_COMPONENTS.register("saltbound_tablet_rupture") { rs ->
			DataComponentType.builder<MutableList<ConditionalEffect<EnchantmentValueEffect>>>().persistent(
				ConditionalEffect.codec(
					EnchantmentValueEffect.CODEC,
					LootContextParamSets.ENCHANTED_ENTITY
				).listOf()
			).build()
		}
}