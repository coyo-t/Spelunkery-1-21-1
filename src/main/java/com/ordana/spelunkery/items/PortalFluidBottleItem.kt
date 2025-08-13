package com.ordana.spelunkery.items;

import com.ordana.spelunkery.reg.GameRulez;
import com.ordana.spelunkery.utils.LevelHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.core.component.DataComponents.LODESTONE_TRACKER;

public class PortalFluidBottleItem extends HoneyBottleItem
{
	public PortalFluidBottleItem (Properties properties)
	{
		super(properties);
	}
	
	//Override
	public boolean shouldCauseReequipAnimation (ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return false;
	}
	
	@Override
	public boolean isFoil (@NotNull ItemStack stack)
	{
		return true;
	}
	
	@Override
	@NotNull
	public SoundEvent getDrinkingSound ()
	{
		return SoundEvents.HONEY_DRINK;
	}
	
	@Override
	@NotNull
	public SoundEvent getEatingSound ()
	{
		return SoundEvents.HONEY_DRINK;
	}
	
	
	@Override
	@NotNull
	public ItemStack finishUsingItem (@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity)
	{
		if (livingEntity instanceof Player player)
		{
			player.setItemInHand(
				player.getUsedItemHand(),
				ItemUtils.createFilledResult(stack, player, Items.GLASS_BOTTLE.getDefaultInstance())
			);
			
		}
		if (livingEntity instanceof ServerPlayer serverPlayer && level.getGameRules().getBoolean(GameRulez.BADLANDS_CHUGS_PORTALS))
		{
			CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
			serverPlayer.awardStat(Stats.ITEM_USED.get(this));
			
			var uhh = stack.get(LODESTONE_TRACKER);
			if (uhh == null)
			{
				LevelHelper.teleportToSpawnPosition(serverPlayer);
			}
			else
			{
				uhh.target().ifPresent(globalPos -> LevelHelper.teleportToAnchorPosition(serverPlayer, globalPos));
			}
		}
		return stack;
	}
	
}
