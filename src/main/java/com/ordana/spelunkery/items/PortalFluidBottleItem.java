package com.ordana.spelunkery.items;

import com.ordana.spelunkery.configs.CommonConfigs;
import com.ordana.spelunkery.utils.LevelHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
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
	
	public static final FoodProperties PORTAL_FLUID = (new FoodProperties.Builder()).nutrition(0).saturationModifier(0F).alwaysEdible().build();
	
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
			ItemStack itemStack2 = ItemUtils.createFilledResult(stack, player, Items.GLASS_BOTTLE.getDefaultInstance());
			player.setItemInHand(player.getUsedItemHand(), itemStack2);
			
		}
		if (livingEntity instanceof ServerPlayer serverPlayer && CommonConfigs.PORTAL_FLUID_DRINKING.get())
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
				final var fuck = uhh.target();
				if (fuck.isPresent())
				{
					LevelHelper.teleportToAnchorPosition(serverPlayer, fuck.get());
				}
			}
			
			
//			CompoundTag compoundTag = stack.getOrCreateTag();
//			boolean bl = compoundTag.contains("anchorPos");
//			boolean bl2 = compoundTag.contains("anchorDimension");
//			PortalFluidBottleItem.getDimension(compoundTag);
//
//			if (bl && bl2)
//			{
//				LevelHelper.teleportToAnchorPosition(serverPlayer, getAnchorPos(compoundTag));
//			}
//			else LevelHelper.teleportToSpawnPosition(serverPlayer);
		}
		return stack;
	}
	
}
