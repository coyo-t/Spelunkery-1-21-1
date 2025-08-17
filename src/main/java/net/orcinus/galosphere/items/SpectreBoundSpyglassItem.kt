package net.orcinus.galosphere.items

import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUtils
import net.minecraft.world.item.UseAnim
import net.minecraft.world.level.Level
import net.orcinus.galosphere.api.SpectreBoundSpyglass
import net.orcinus.galosphere.init.GCriteriaTriggers

class SpectreBoundSpyglassItem(properties: Properties) : Item(properties)
{
	override fun getUseDuration(itemStack: ItemStack, livingEntity: LivingEntity): Int
	{
		return 1200
	}

	override fun getUseAnimation(itemStack: ItemStack): UseAnim
	{
		return UseAnim.SPYGLASS
	}

	override fun use(level: Level, player: Player, interactionHand: InteractionHand): InteractionResultHolder<ItemStack?>
	{
		if (!SpectreBoundSpyglass.canUseSpectreBoundedSpyglass(player.getItemInHand(interactionHand)))
		{
			return InteractionResultHolder.fail<ItemStack?>(player.getItemInHand(interactionHand))
		}
		else
		{
			if (player is ServerPlayer)
			{
				if (!level.isClientSide)
				{
					GCriteriaTriggers.USE_SPECTRE_SPYGLASS.get().trigger(player)
				}
				player.awardStat(Stats.ITEM_USED.get(this))
			}
			player.playSound(SoundEvents.SPYGLASS_USE, 1.0f, 1.0f)
			return ItemUtils.startUsingInstantly(level, player, interactionHand)
		}
	}

	override fun finishUsingItem(itemStack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack
	{
		this.stopUsing(livingEntity)
		return itemStack
	}

	override fun releaseUsing(itemStack: ItemStack, level: Level, livingEntity: LivingEntity, i: Int)
	{
		this.stopUsing(livingEntity)
	}

	override fun isFoil(itemStack: ItemStack): Boolean
	{
		return true
	}

	private fun stopUsing(livingEntity: LivingEntity)
	{
		livingEntity.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0f, 1.0f)
		if (livingEntity is Player)
		{
			livingEntity.cooldowns.addCooldown(this, 20)
		}
	}
}
