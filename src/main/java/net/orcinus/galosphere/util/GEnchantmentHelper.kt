package net.orcinus.galosphere.util

import net.minecraft.core.component.DataComponentType
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.ConditionalEffect
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect
import net.orcinus.galosphere.init.GEnchantmentEffectComponents
import org.apache.commons.lang3.mutable.MutableFloat
import kotlin.math.max

object GEnchantmentHelper
{
	@JvmStatic
	fun hasEnfeeble(serverLevel: ServerLevel, itemStack: ItemStack, entity: Entity): Int
	{
		val mutableFloat = MutableFloat(0f)
		EnchantmentHelper.runIterationOnItem(itemStack) { holder, i ->
			addEffects(
				holder.value(),
				serverLevel,
				i,
				itemStack,
				entity,
				mutableFloat,
				GEnchantmentEffectComponents.SALTBOUND_TABLET_DECELERATION.get()
			)
		}
		return max(0, mutableFloat.toInt())
	}

	@JvmStatic
	fun hasRupture(serverLevel: ServerLevel, itemStack: ItemStack, entity: Entity): Int
	{
		val mutableFloat = MutableFloat(0f)
		EnchantmentHelper.runIterationOnItem(itemStack) { holder, i ->
			addEffects(
				holder.value(),
				serverLevel,
				i,
				itemStack,
				entity,
				mutableFloat,
				GEnchantmentEffectComponents.SALTBOUND_TABLET_RUPTURE.get()
			)
		}
		return max(0, mutableFloat.toInt())
	}

	@JvmStatic
	fun getSustainingTicks(serverLevel: ServerLevel, itemStack: ItemStack, entity: Entity): Int
	{
		val mutableFloat = MutableFloat(0f)
		EnchantmentHelper.runIterationOnItem(itemStack) { holder, i ->
			addEffects(
				holder.value(),
				serverLevel,
				i,
				itemStack,
				entity,
				mutableFloat,
				GEnchantmentEffectComponents.SALTBOUND_TABLET_SUSTAIN.get()
			)
		}
		return max(0, mutableFloat.toInt())
	}

	fun addEffects(
		enchantment: Enchantment,
		serverLevel: ServerLevel,
		i: Int,
		itemStack: ItemStack,
		entity: Entity,
		mutableFloat: MutableFloat,
		type: DataComponentType<MutableList<ConditionalEffect<EnchantmentValueEffect>>>
	)
	{
		Enchantment.applyEffects(
			enchantment.getEffects(type),
			Enchantment.entityContext(serverLevel, i, entity, entity.position())
		) { mutableFloat.value = it.process(i, entity.getRandom(), mutableFloat.toFloat()) }
	}
}