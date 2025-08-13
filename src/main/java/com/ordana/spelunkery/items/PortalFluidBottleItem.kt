package com.ordana.spelunkery.items

import com.ordana.spelunkery.reg.GameRulez
import com.ordana.spelunkery.utils.LevelHelper
import net.minecraft.ChatFormatting
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.component.DataComponents.LODESTONE_TRACKER
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.stats.Stats
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.HoneyBottleItem
import net.minecraft.world.item.ItemStack
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
		stack.get(LODESTONE_TRACKER)?.target?.ifPresent {
			val co = it.pos
			val dim = it.dimension
			val cal = Component.translatable(
				"tooltip.spelunkery.portal_fluid_target",
				-co.z,
				+co.x,
				+co.y,
				Component.translatable(dim.location().toLanguageKey("dimension"))
			)

			cal.withStyle(ChatFormatting.GRAY)
			tooltipComponents += cal
		}

		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
	}

	override fun finishUsingItem(stack: ItemStack, level: Level, user: LivingEntity): ItemStack
	{
		val eatRes = when (val fp = stack.getFoodProperties(user))
		{
			null -> stack
			else -> user.eat(level, stack, fp)
		}

		if (eatRes.isEmpty)
		{
			return ItemStack(Items.GLASS_BOTTLE)
		}
		else
		{
			if (user is Player)
			{
				user.addEffect(MobEffectInstance(MobEffects.CONFUSION, 4 * level.tickRateManager().tickrate().toInt(), 6))
				if (!user.hasInfiniteMaterials())
				{
					val itemstack = ItemStack(Items.GLASS_BOTTLE)
					if (!user.getInventory().add(itemstack))
					{
						user.drop(itemstack, false)
					}
				}

				if (level.gameRules.getBoolean(GameRulez.BADLANDS_CHUGS_PORTALS))
				{
					if (user is ServerPlayer)
					{
						CriteriaTriggers.CONSUME_ITEM.trigger(user, stack)
						user.awardStat(Stats.ITEM_USED.get(this))

						val uhh = stack.get(LODESTONE_TRACKER)
						if (uhh == null)
						{
							LevelHelper.teleportToSpawnPosition(user)
						}
						else
						{
							uhh.target.ifPresent { LevelHelper.teleportToAnchorPosition(user, it) }
						}
					}
				}
			}
			return eatRes
		}
	}
}
