package com.ordana.spelunkery.items

import com.ordana.spelunkery.reg.GameRulez
import com.ordana.spelunkery.utils.LevelHelper
import net.minecraft.ChatFormatting
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.component.DataComponents
import net.minecraft.core.component.DataComponents.LODESTONE_TRACKER
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.stats.Stats
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.HoneyBottleItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUtils
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class PortalFluidBottleItem(properties: Properties) : HoneyBottleItem(properties)
{
	override fun shouldCauseReequipAnimation(oldStack: ItemStack, newStack: ItemStack, slotChanged: Boolean)
		= false

	override fun isFoil(stack: ItemStack) = true

	override fun getDrinkingSound() = SoundEvents.HONEY_DRINK

	override fun getEatingSound() = SoundEvents.HONEY_DRINK

	override fun appendHoverText(
		stack: ItemStack,
		context: TooltipContext,
		tooltipComponents: MutableList<Component?>,
		tooltipFlag: TooltipFlag
	)
	{
		if (tooltipFlag.hasShiftDown())
		{
			stack.get(LODESTONE_TRACKER)?.target?.ifPresent {
				val co = it.pos
				val dim = it.dimension
				val cal = Component.translatable(
					"tooltip.spelunkery.portal_fluid_target",
					-co.z,
					+co.x,
					+co.y,
					dim.toString(),
				)
				cal.withStyle(ChatFormatting.GRAY)
				tooltipComponents += cal
			}
		}
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
	}

	override fun finishUsingItem(stack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack
	{
		if (livingEntity is Player)
		{
			livingEntity.setItemInHand(
				livingEntity.usedItemHand,
				ItemUtils.createFilledResult(stack, livingEntity, Items.GLASS_BOTTLE.defaultInstance)
			)
		}
		if (livingEntity is ServerPlayer && level.gameRules.getBoolean(GameRulez.BADLANDS_CHUGS_PORTALS))
		{
			CriteriaTriggers.CONSUME_ITEM.trigger(livingEntity, stack)
			livingEntity.awardStat(Stats.ITEM_USED.get(this))

			val uhh = stack.get(LODESTONE_TRACKER)
			if (uhh == null)
			{
				LevelHelper.teleportToSpawnPosition(livingEntity)
			}
			else
			{
				uhh.target.ifPresent { LevelHelper.teleportToAnchorPosition(livingEntity, it) }
			}
		}
		return stack
	}
}
