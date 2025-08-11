package com.ordana.spelunkery.items;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DepthGaugeItem extends Item
{
	public DepthGaugeItem (Properties properties)
	{
		super(properties);
	}
	
	//Override
	public boolean shouldCauseReequipAnimation (ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return false;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use (Level level, @NotNull Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		player.displayClientMessage(Component.translatable("tooltip.spelunkery.depth_gauge_depth", player.position().y).setStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_GREEN)), true);
		if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.USING_ITEM.trigger(serverPlayer, stack);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}
}