package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import com.ordana.spelunkery.items.*;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.ordana.spelunkery.Spelunkery.MOD_ID;

public class ModItems
{

	public static final DeferredRegister.Items ITEMS   = DeferredRegister.createItems(MOD_ID);
	
	public static void init ()
	{
	}
	
	
	public static final Supplier<Item> STONE_PEBBLE = regItem("stone_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> DEEPSLATE_PEBBLE = regItem("deepslate_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> NETHERRACK_PEBBLE = regItem("netherrack_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> MAGMA_PEBBLE = regItem("magma_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> BLACKSTONE_PEBBLE = regItem("blackstone_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> BASALT_PEBBLE = regItem("basalt_pebble", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> END_STONE_PEBBLE = regItem("end_stone_pebble", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> ROCK_SALT = regItem("rock_salt", () ->
			  new BlockItem(ModBlocks.ROCK_SALT.get(), new Item.Properties()));
	public static final Supplier<Item> SALT = regItem("salt", () ->
			  new BlockItem(ModBlocks.SALT.get(), new Item.Properties()));
	public static final Supplier<Item> NEPHRITE_CHUNK = regItem("nephrite_chunk", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_MAGNETITE = regItem("raw_magnetite", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> RAW_IRON_NUGGET = regItem("raw_iron_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_COPPER_NUGGET = regItem("raw_copper_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_GOLD_NUGGET = regItem("raw_gold_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_MAGNETITE_NUGGET = regItem("raw_magnetite_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_ZINC_NUGGET = regItem("raw_zinc_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_LEAD_NUGGET = regItem("raw_lead_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_SILVER_NUGGET = regItem("raw_silver_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_TIN_NUGGET = regItem("raw_tin_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> RAW_BISMUTH_NUGGET = regItem("raw_bismuth_nugget", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> COPPER_NUGGET = regItem("copper_nugget", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> BISMUTH_NUGGET = regItem("bismuth_nugget", () ->
			  new Item(new Item.Properties()));
	
	// FIXME add fueltime data for these two
	// 200
	public static final Supplier<Item> COAL_LUMP = regItem("coal_lump", () ->
			  new Item(new Item.Properties()));
	// 200
	public static final Supplier<Item> CHARCOAL_LUMP = regItem("charcoal_lump", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> ROUGH_CINNABAR_SHARD = regItem("rough_cinnabar_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_LAZURITE_SHARD = regItem("rough_lazurite_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_EMERALD_SHARD = regItem("rough_emerald_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_DIAMOND_SHARD = regItem("rough_diamond_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_QUARTZ_SHARD = regItem("rough_quartz_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_JADE_SHARD = regItem("rough_jade_shard", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> ROUGH_CINNABAR = regItem("rough_cinnabar", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_LAZURITE = regItem("rough_lazurite", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_EMERALD = regItem("rough_emerald", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> ROUGH_DIAMOND = regItem("rough_diamond", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> CINNABAR_SHARD = regItem("cinnabar_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> LAPIS_LAZULI_SHARD = regItem("lapis_lazuli_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> EMERALD_SHARD = regItem("emerald_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> DIAMOND_SHARD = regItem("diamond_shard", () ->
			  new Item(new Item.Properties()));
	public static final Supplier<Item> JADE_SHARD = regItem("jade_shard", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> CINNABAR = regItem("cinnabar", () ->
			  new Item(new Item.Properties()));
	
	public static final Supplier<Item> COMPRESSION_BLAST_MINER = regItem("compression_blast_miner", () ->
			  new BlockItem(ModBlocks.COMPRESSION_BLAST_MINER.get(), new Item.Properties()));
	
	//food
	public static final Supplier<Item> BUTTON_MUSHROOM = regItem("button_mushroom", () ->
			  new BlockItem(ModBlocks.BUTTON_MUSHROOM.get(), new Item.Properties().food(ModFoods.BUTTON_MUSHROOM)));
	public static final Supplier<Item> CRIMINI = regItem("crimini", () ->
			  new BlockItem(ModBlocks.CRIMINI.get(), new Item.Properties().food(ModFoods.CRIMINI)));
	public static final Supplier<Item> PORTABELLA = regItem("portabella", () ->
			  new BlockItem(ModBlocks.PORTABELLA.get(), new Item.Properties().food(ModFoods.PORTABELLA)));
	public static final Supplier<Item> GRILLED_PORTABELLA = regItem("grilled_portabella", () ->
			  new Item(new Item.Properties().food(ModFoods.GRILLED_PORTABELLA)));
	
	
	public static final Supplier<Item> DUST_BUN = regItem("dust_bun", () ->
			  new DustBunItem(new Item.Properties().stacksTo(16)));
	public static final Supplier<Item> BUNNY_EARS = regItem("bunny_ears", () ->
			  new BunnyEarsItem(new Item.Properties().stacksTo(1)));
	// FIXME should have curse of binding permenantly (component?)
	public static final Supplier<Item> TRUE_CROWN = regItem("true_crown", () ->
			  new TrueCrownItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
	
	//mining gear
	public static final Supplier<Item> DEPTH_GAUGE = regItem("depth_gauge", () ->
			  new DepthGaugeItem(new Item.Properties()));
	public static final Supplier<Item> MAGNETIC_COMPASS = regItem("magnetic_compass", () -> new Item(new Item.Properties())
	{
		@Override
		public boolean shouldCauseReequipAnimation (ItemStack oldStack, ItemStack newStack, boolean slotChanged)
		{
			return false;
		}
		
		@Override
		public InteractionResultHolder<ItemStack> use (@NotNull Level level, @NotNull Player player, InteractionHand hand)
		{
			ItemStack stack = player.getItemInHand(hand);
			final var co = player.position();
			player.displayClientMessage(Component.translatable("tooltip.spelunkery.player_pos", co.x, co.z).setStyle(Style.EMPTY.applyFormat(ChatFormatting.DARK_GREEN)), true);
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
		}
	});
	public static final Supplier<Item> PORTAL_FLUID_BOTTLE = regItem("portal_fluid_bottle", () ->
			  new PortalFluidBottleItem(new Item.Properties().food(PortalFluidBottleItem.PORTAL_FLUID).stacksTo(16).rarity(Rarity.UNCOMMON)));
	
	public static final Supplier<Item> PORTAL_FLUID_BUCKET = regItem("portal_fluid_bucket", () ->
			  new BucketItem(ModFluids.PORTAL_FLUID.get(), (new Item.Properties().stacksTo(1))));
	public static final Supplier<Item> SPRING_WATER_BUCKET = regItem("spring_water_bucket", () ->
			  new SpringWaterBucketItem(ModFluids.SPRING_WATER.get(), (new Item.Properties().stacksTo(1))));
	public static final Supplier<Item> ECHO_FORK = regItem("echo_fork", () ->
			  new EchoForkItem(new Item.Properties().stacksTo(1)));
	public static final Supplier<Item> GLOWSTICK = regItem("glowstick", () ->
			  new GlowstickItem(null, ModBlocks.GLOWSTICK.get(), new Item.Properties()));
	
	//glowsticks
	public static final Supplier<Item> RED_GLOWSTICK = regItem("red_glowstick", () ->
			  new GlowstickItem(DyeColor.RED, ModBlocks.RED_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> ORANGE_GLOWSTICK = regItem("orange_glowstick", () ->
			  new GlowstickItem(DyeColor.ORANGE, ModBlocks.ORANGE_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> YELLOW_GLOWSTICK = regItem("yellow_glowstick", () ->
			  new GlowstickItem(DyeColor.YELLOW, ModBlocks.YELLOW_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> LIME_GLOWSTICK = regItem("lime_glowstick", () ->
			  new GlowstickItem(DyeColor.LIME, ModBlocks.LIME_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> GREEN_GLOWSTICK = regItem("green_glowstick", () ->
			  new GlowstickItem(DyeColor.GREEN, ModBlocks.GREEN_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> CYAN_GLOWSTICK = regItem("cyan_glowstick", () ->
			  new GlowstickItem(DyeColor.CYAN, ModBlocks.CYAN_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> LIGHT_BLUE_GLOWSTICK = regItem("light_blue_glowstick", () ->
			  new GlowstickItem(DyeColor.LIGHT_BLUE, ModBlocks.LIGHT_BLUE_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> BLUE_GLOWSTICK = regItem("blue_glowstick", () ->
			  new GlowstickItem(DyeColor.BLUE, ModBlocks.BLUE_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> PURPLE_GLOWSTICK = regItem("purple_glowstick", () ->
			  new GlowstickItem(DyeColor.PURPLE, ModBlocks.PURPLE_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> MAGENTA_GLOWSTICK = regItem("magenta_glowstick", () ->
			  new GlowstickItem(DyeColor.MAGENTA, ModBlocks.MAGENTA_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> PINK_GLOWSTICK = regItem("pink_glowstick", () ->
			  new GlowstickItem(DyeColor.PINK, ModBlocks.PINK_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> BROWN_GLOWSTICK = regItem("brown_glowstick", () ->
			  new GlowstickItem(DyeColor.BROWN, ModBlocks.BROWN_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> BLACK_GLOWSTICK = regItem("black_glowstick", () ->
			  new GlowstickItem(DyeColor.BLACK, ModBlocks.BLACK_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> WHITE_GLOWSTICK = regItem("white_glowstick", () ->
			  new GlowstickItem(DyeColor.WHITE, ModBlocks.WHITE_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> GRAY_GLOWSTICK = regItem("gray_glowstick", () ->
			  new GlowstickItem(DyeColor.GRAY, ModBlocks.GRAY_GLOWSTICK.get(), new Item.Properties()));
	public static final Supplier<Item> LIGHT_GRAY_GLOWSTICK = regItem("light_gray_glowstick", () ->
			  new GlowstickItem(DyeColor.LIGHT_GRAY, ModBlocks.LIGHT_GRAY_GLOWSTICK.get(), new Item.Properties()));
	
	public static final Supplier<Item> DUST_BUNNY_SPAWN_EGG = RegHelper.registerItem(Spelunkery.res("dust_bunny_spawn_egg"), () ->
			  PlatHelper.newSpawnEgg(ModEntities.DUST_BUNNY, 0x5E625E, 0x3F3C39, new Item.Properties()));


	
	public static Supplier<Item> rb (String name, Block it)
	{
		return ITEMS.register(name, () -> new BlockItem(it, new Item.Properties()));
	}
	
	
	
	public static <T extends Item> Supplier<T> regItem (String name, Supplier<T> itemSup)
	{
		return ITEMS.register(name, itemSup);
	}
	
}
