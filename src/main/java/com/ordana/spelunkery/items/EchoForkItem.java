package com.ordana.spelunkery.items;

import com.ordana.spelunkery.configs.CommonConfigs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

import java.util.List;

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
		if (player.isSecondaryUseActive()) removeGlow(player, stack, level);
		else
		{
			tollFork(player, stack, level);
			player.getCooldowns().addCooldown(this, CommonConfigs.ECHO_COOLDOWN.get());
			if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.USING_ITEM.trigger(serverPlayer, stack);
		}
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}
	
	public void tollFork (Player player, ItemStack stack, Level level)
	{
		if (!player.level().isClientSide && stack.getItem() instanceof EchoForkItem)
		{
			level.playSound(null, player.blockPosition(), SoundEvents.SCULK_CLICKING, SoundSource.BLOCKS, 1.0f, 1.0f);
			level.playSound(null, player.blockPosition(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1.0f, 1.0f);
			
			AABB area = (new AABB(player.blockPosition())).inflate(CommonConfigs.ECHO_FORK_RANGE.get());
			List<LivingEntity> entities = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), area,
					  LivingEntity::isAlive
			);
			
			entities.forEach(item -> item.addEffect(new MobEffectInstance(MobEffects.GLOWING, CommonConfigs.ECHO_DURRATION.get(), 0, true, false, true)));
		}
	}
	
	public void removeGlow (Player player, ItemStack stack, Level level)
	{
		if (!player.level().isClientSide && stack.getItem() instanceof EchoForkItem)
		{
			level.playSound(null, player.blockPosition(), SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1.0f, 2.0f);
			
			AABB area = (new AABB(player.blockPosition())).inflate(CommonConfigs.ECHO_FORK_RANGE.get() + 4);
			List<LivingEntity> entities = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), area,
					  LivingEntity::isAlive
			);
			
			entities.forEach(item -> item.removeEffect(MobEffects.GLOWING));
		}
	}
}
