package net.orcinus.galosphere.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class PreservedFleshItem extends Item {

    public PreservedFleshItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {

        if (!(livingEntity instanceof Player player)) return itemStack;

        itemStack.hurtAndBreak(1, livingEntity, LivingEntity.getSlotForHand(InteractionHand.MAIN_HAND));
        FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
        if (foodProperties != null && !livingEntity.level().isClientSide) {
            player.getFoodData().eat(foodProperties);
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5f, level.random.nextFloat() * 0.1f + 0.9f);
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            }
            level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity.getEatingSound(itemStack), SoundSource.NEUTRAL, 1.0f, 1.0f + (level.random.nextFloat() - level.random.nextFloat()) * 0.4f);
            livingEntity.gameEvent(GameEvent.EAT);
        }
        return itemStack;
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.is(Items.ROTTEN_FLESH);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}