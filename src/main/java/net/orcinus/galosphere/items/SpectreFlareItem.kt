package net.orcinus.galosphere.items

import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.orcinus.galosphere.entities.SpectreFlare
import net.orcinus.galosphere.init.GCriteriaTriggers

class SpectreFlareItem(properties: Properties) : Item(properties)
{
	override fun use(level: Level, player: Player, interactionHand: InteractionHand): InteractionResultHolder<ItemStack>
	{
		val itemStack = player.getItemInHand(interactionHand)
		level.playSound(
			null,
			player.x,
			player.y,
			player.z,
			SoundEvents.ENDER_PEARL_THROW,
			SoundSource.NEUTRAL,
			0.5f,
			0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
		)
		if (!level.isClientSide)
		{
			val spectreFlare = SpectreFlare(level, player, itemStack)
			spectreFlare.setPos(player.x, player.eyeY - 0.1, player.z)
			spectreFlare.shootFromRotation(player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f)
			level.addFreshEntity(spectreFlare)
			if (player is ServerPlayer)
			{
				GCriteriaTriggers.USE_SPECTRE_FLARE.get().trigger(player)
			}
		}
		player.awardStat(Stats.ITEM_USED.get(this))
		if (!player.abilities.instabuild)
		{
			itemStack.shrink(1)
		}
		return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide())
	}
}