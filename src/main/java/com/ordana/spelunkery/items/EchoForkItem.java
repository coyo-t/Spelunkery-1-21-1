package com.ordana.spelunkery.items;

import com.ordana.spelunkery.reg.GameRulez;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class EchoForkItem extends Item
{
	
	public EchoForkItem (Properties properties)
	{
		super(properties);
	}
	
	@Override
	@NotNull
	public InteractionResultHolder<ItemStack> use (@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		if (player.isSecondaryUseActive())
		{
			removeGlow(player, stack, level);
		}
		else
		{
			tollFork(player, stack, level);
			player.getCooldowns().addCooldown(this, getCooldown(level));
			if (player instanceof ServerPlayer serverPlayer)
			{
				CriteriaTriggers.USING_ITEM.trigger(serverPlayer, stack);
			}
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}
	
	private int getRange (Level level)
	{
		return Mth.clamp(level.getGameRules().getInt(GameRulez.ECHO_TUNER_RANGE), 1, 256);
	}
	
	private int getTime (Level level)
	{
		return Mth.clamp(level.getGameRules().getInt(GameRulez.ECHO_TUNER_TIME), 1, 72000);
	}
	
	private int getCooldown (Level level)
	{
		return Mth.clamp(level.getGameRules().getInt(GameRulez.ECHO_TUNER_COOLDOWN), 1, 72000);
	}
	
	public void tollFork (Player player, ItemStack stack, Level level)
	{
		if (!player.level().isClientSide && stack.getItem() instanceof EchoForkItem)
		{
			level.playSound(null, player.blockPosition(), SoundEvents.SCULK_CLICKING, SoundSource.BLOCKS, 1.0f, 1.0f);
			level.playSound(null, player.blockPosition(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1.0f, 1.0f);
			
			final var area = (new AABB(player.blockPosition())).inflate(getRange(level));
			level
			.getEntities(EntityTypeTest.forClass(LivingEntity.class), area,LivingEntity::isAlive)
			.forEach(item -> item.addEffect(new MobEffectInstance(MobEffects.GLOWING, getTime(level), 0, true, false, true)));
		}
	}
	
	public void removeGlow (Player player, ItemStack stack, Level level)
	{
		if (!player.level().isClientSide && stack.getItem() instanceof EchoForkItem)
		{
			level.playSound(null, player.blockPosition(), SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1.0f, 2.0f);
			
			final var area = (new AABB(player.blockPosition())).inflate(getRange(level));
			level
			.getEntities(EntityTypeTest.forClass(LivingEntity.class), area, LivingEntity::isAlive)
			.forEach(item -> item.removeEffect(MobEffects.GLOWING));
		}
	}
}
