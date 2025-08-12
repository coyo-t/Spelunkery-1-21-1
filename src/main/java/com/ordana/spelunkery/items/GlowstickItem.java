package com.ordana.spelunkery.items;

import com.ordana.spelunkery.entities.ThrownGlowstickEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class GlowstickItem extends BlockItem
{
	private final DyeColor color;
	
	public GlowstickItem (DyeColor color, Block block, Properties properties)
	{
		super(block, properties);
		this.color = color;
	}
	
	public DyeColor getColor ()
	{
		return this.color;
	}
	
	public InteractionResultHolder<ItemStack> use (Level level, Player player, InteractionHand usedHand)
	{
		ItemStack itemStack = player.getItemInHand(usedHand);
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide)
		{
			ThrownGlowstickEntity glowstick = new ThrownGlowstickEntity(level, player);
			glowstick.setItem(itemStack);
			glowstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
			
			glowstick.setColor(this.color);
			
			level.addFreshEntity(glowstick);
		}
		
		player.awardStat(Stats.ITEM_USED.get(this));
		if (!player.getAbilities().instabuild)
		{
			itemStack.shrink(1);
		}
		
		return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
	}
	
//	Supplier<BiMap<Item, DyeColor>> ITEM_TO_DYE = Suppliers.memoize(() -> {
//		var builder = ImmutableBiMap.<Item, DyeColor>builder()
//				  .put(ModItems.RED_GLOWSTICK.get(), DyeColor.RED)
//				  .put(ModItems.ORANGE_GLOWSTICK.get(), DyeColor.ORANGE)
//				  .put(ModItems.YELLOW_GLOWSTICK.get(), DyeColor.YELLOW)
//				  .put(ModItems.LIME_GLOWSTICK.get(), DyeColor.LIME)
//				  .put(ModItems.GREEN_GLOWSTICK.get(), DyeColor.GREEN)
//				  .put(ModItems.CYAN_GLOWSTICK.get(), DyeColor.CYAN)
//				  .put(ModItems.LIGHT_BLUE_GLOWSTICK.get(), DyeColor.LIGHT_BLUE)
//				  .put(ModItems.BLUE_GLOWSTICK.get(), DyeColor.BLUE)
//				  .put(ModItems.PURPLE_GLOWSTICK.get(), DyeColor.PURPLE)
//				  .put(ModItems.MAGENTA_GLOWSTICK.get(), DyeColor.MAGENTA)
//				  .put(ModItems.PINK_GLOWSTICK.get(), DyeColor.PINK)
//				  .put(ModItems.BROWN_GLOWSTICK.get(), DyeColor.BROWN)
//				  .put(ModItems.BLACK_GLOWSTICK.get(), DyeColor.BLACK)
//				  .put(ModItems.WHITE_GLOWSTICK.get(), DyeColor.WHITE)
//				  .put(ModItems.GRAY_GLOWSTICK.get(), DyeColor.GRAY)
//				  .put(ModItems.LIGHT_GRAY_GLOWSTICK.get(), DyeColor.LIGHT_GRAY);
//		return builder.build();
//	});
	
	public Optional<DyeColor> getGlowstick (Item item)
	{
		return Optional.empty();
	}
}
