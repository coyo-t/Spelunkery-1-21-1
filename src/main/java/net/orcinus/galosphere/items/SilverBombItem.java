package net.orcinus.galosphere.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.orcinus.galosphere.entities.SilverBomb;
import net.orcinus.galosphere.init.GDataComponents;

import java.util.List;

public class SilverBombItem extends Item {

    public SilverBombItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, 20);
        if (!world.isClientSide()) {
            SilverBomb silverBomb = new SilverBomb(world, player, itemstack);
            silverBomb.setItem(itemstack);
            silverBomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
            world.addFreshEntity(silverBomb);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        String text = "item.galosphere.silver_bomb.";
        if (this.getModifierValue(itemStack, GDataComponents.DURATION.get()) > 0) {
            list.add(Component.translatable(text + "duration").append(" ").append(String.valueOf(this.getModifierValue(itemStack, GDataComponents.DURATION.get()))).withStyle(ChatFormatting.GRAY));
        }
        if (this.getModifierValue(itemStack, GDataComponents.BOUNCY.get()) > 0) {
            list.add(Component.translatable(text + "bouncy").append(" ").append(String.valueOf(this.getModifierValue(itemStack, GDataComponents.BOUNCY.get()))).withStyle(ChatFormatting.GRAY));
        }
        if (this.getModifierValue(itemStack, GDataComponents.EXPLOSION.get()) > 0) {
            list.add(Component.translatable(text + "explosion").append(" ").append(String.valueOf(this.getModifierValue(itemStack, GDataComponents.EXPLOSION.get()))).withStyle(ChatFormatting.GRAY));
        }
    }

    private int getModifierValue(ItemStack stack, DataComponentType<Integer> dataComponentType) {
        return stack.getOrDefault(dataComponentType, 0);
    }

}
