package net.orcinus.galosphere.items

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.component.DataComponents
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.orcinus.galosphere.init.GItemTags

class PreservedFleshItem(properties: Properties) : Item(properties)
{
	override fun finishUsingItem(itemStack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack
	{
		if (livingEntity !is Player) return itemStack

		itemStack.hurtAndBreak(1, livingEntity, LivingEntity.getSlotForHand(InteractionHand.MAIN_HAND))
		val foodProperties = itemStack[DataComponents.FOOD]
		if (foodProperties != null && !livingEntity.level().isClientSide)
		{
			livingEntity.getFoodData().eat(foodProperties)
			livingEntity.awardStat(Stats.ITEM_USED.get(itemStack.item))
			level.playSound(
				null,
				livingEntity.x,
				livingEntity.y,
				livingEntity.z,
				SoundEvents.PLAYER_BURP,
				SoundSource.PLAYERS,
				0.5f,
				level.random.nextFloat() * 0.1f + 0.9f
			)
			if (livingEntity is ServerPlayer)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger(livingEntity, itemStack)
			}
			level.playSound(
				null,
				livingEntity.x,
				livingEntity.y,
				livingEntity.z,
				livingEntity.getEatingSound(itemStack),
				SoundSource.NEUTRAL,
				1.0f,
				1.0f + (level.random.nextFloat() - level.random.nextFloat()) * 0.4f
			)
			livingEntity.gameEvent(GameEvent.EAT)
		}
		return itemStack
	}

	override fun isValidRepairItem(itemStack: ItemStack, itemStack2: ItemStack): Boolean
	{
		return itemStack2.`is`(GItemTags.REPAIRS_PRESERVED_FLESH)
	}

	override fun isEnchantable(stack: ItemStack): Boolean
	{
		return false
	}
}