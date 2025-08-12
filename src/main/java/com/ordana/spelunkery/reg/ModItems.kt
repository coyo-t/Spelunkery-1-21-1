package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.items.*
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.*
import net.minecraft.world.level.Level
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModItems
{
	@JvmField
	val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(Spelunkery.MOD_ID)

	@JvmField
	val STONE_PEBBLE = regItem("stone_pebble") { Item(Item.Properties()) }
	@JvmField
	val DEEPSLATE_PEBBLE = regItem("deepslate_pebble") { Item(Item.Properties()) }
	val NETHERRACK_PEBBLE = regItem("netherrack_pebble") { Item(Item.Properties()) }
	@JvmField
	val MAGMA_PEBBLE = regItem("magma_pebble") { Item(Item.Properties()) }
	@JvmField
	val BLACKSTONE_PEBBLE = regItem("blackstone_pebble") { Item(Item.Properties()) }
	@JvmField
	val BASALT_PEBBLE = regItem("basalt_pebble") { Item(Item.Properties()) }
	@JvmField
	val END_STONE_PEBBLE = regItem("end_stone_pebble") { Item(Item.Properties()) }

	@JvmField
	val SALT = regItem("salt") { BlockItem(ModBlocks.SALT.get(), Item.Properties()) }
	@JvmField
	val NEPHRITE_CHUNK = regItem("nephrite_chunk") { Item(Item.Properties()) }
	@JvmField
	val RAW_MAGNETITE = regItem("raw_magnetite") { Item(Item.Properties()) }
	@JvmField
	val RAW_IRON_NUGGET = regItem("raw_iron_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_COPPER_NUGGET = regItem("raw_copper_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_GOLD_NUGGET = regItem("raw_gold_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_MAGNETITE_NUGGET = regItem("raw_magnetite_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_ZINC_NUGGET = regItem("raw_zinc_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_LEAD_NUGGET = regItem("raw_lead_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_SILVER_NUGGET = regItem("raw_silver_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_TIN_NUGGET = regItem("raw_tin_nugget") { Item(Item.Properties()) }
	@JvmField
	val RAW_BISMUTH_NUGGET = regItem("raw_bismuth_nugget") { Item(Item.Properties()) }
	@JvmField
	val COPPER_NUGGET = regItem("copper_nugget") { Item(Item.Properties()) }
	@JvmField
	val BISMUTH_NUGGET = regItem("bismuth_nugget") { Item(Item.Properties()) }

	// FIXME add fueltime data for these two
	// 200
	@JvmField
	val COAL_LUMP = regItem("coal_lump") { Item(Item.Properties()) }

	// 200
	@JvmField
	val CHARCOAL_LUMP = regItem("charcoal_lump") { Item(Item.Properties()) }

	@JvmField
	val ROUGH_CINNABAR_SHARD = regItem("rough_cinnabar_shard") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_LAZURITE_SHARD = regItem("rough_lazurite_shard") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_EMERALD_SHARD = regItem("rough_emerald_shard") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_DIAMOND_SHARD = regItem("rough_diamond_shard") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_QUARTZ_SHARD = regItem("rough_quartz_shard", { Item(Item.Properties()) })
	@JvmField
	val ROUGH_JADE_SHARD = regItem("rough_jade_shard", { Item(Item.Properties()) })
	@JvmField
	val ROUGH_CINNABAR = regItem("rough_cinnabar") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_LAZURITE = regItem("rough_lazurite") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_EMERALD = regItem("rough_emerald") { Item(Item.Properties()) }
	@JvmField
	val ROUGH_DIAMOND = regItem("rough_diamond") { Item(Item.Properties()) }
	@JvmField
	val CINNABAR_SHARD = regItem("cinnabar_shard") { Item(Item.Properties()) }
	@JvmField
	val LAPIS_LAZULI_SHARD = regItem("lapis_lazuli_shard") { Item(Item.Properties()) }
	@JvmField
	val EMERALD_SHARD = regItem("emerald_shard") { Item(Item.Properties()) }
	@JvmField
	val DIAMOND_SHARD = regItem("diamond_shard") { Item(Item.Properties()) }
	@JvmField
	val JADE_SHARD = regItem("jade_shard") { Item(Item.Properties()) }
	@JvmField
	val CINNABAR = regItem("cinnabar") { Item(Item.Properties()) }
	@JvmField
	val COMPRESSION_BLAST_MINER = regItem("compression_blast_miner") {
		BlockItem(ModBlocks.COMPRESSION_BLAST_MINER.get(), Item.Properties())
	}

	//food
	@JvmField
	val BUTTON_MUSHROOM = regItem("button_mushroom") {
		BlockItem(ModBlocks.BUTTON_MUSHROOM.get(), Item.Properties().food(ModFoods.BUTTON_MUSHROOM))
	}
	@JvmField
	val CRIMINI = regItem("crimini") {
		BlockItem(ModBlocks.CRIMINI.get(), Item.Properties().food(ModFoods.CRIMINI))
	}
	@JvmField
	val PORTABELLA = regItem("portabella") {
		BlockItem(ModBlocks.PORTABELLA.get(), Item.Properties().food(ModFoods.PORTABELLA))
	}
	@JvmField
	val GRILLED_PORTABELLA = regItem("grilled_portabella") {
		Item(Item.Properties().food(ModFoods.GRILLED_PORTABELLA))
	}

	@JvmField
	val MAGNETIC_COMPASS = regItem("magnetic_compass") {
		object : Item(Properties())
		{
			override fun shouldCauseReequipAnimation(
				oldStack: ItemStack,
				newStack: ItemStack,
				slotChanged: Boolean
			): Boolean
			{
				return false
			}

			override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack?>
			{
				val stack = player.getItemInHand(hand)
				val co = player.position()
				player.displayClientMessage(
					Component.translatable("tooltip.spelunkery.player_pos", co.x, co.z).setStyle(
						Style.EMPTY.applyFormat(ChatFormatting.DARK_GREEN)
					), true
				)
				return InteractionResultHolder(InteractionResult.SUCCESS, stack)
			}
		}
	}

	@JvmField
	val PORTAL_FLUID_BOTTLE = regItem("portal_fluid_bottle", {
		PortalFluidBottleItem(
			Item.Properties().food(PortalFluidBottleItem.PORTAL_FLUID).stacksTo(16).rarity(Rarity.UNCOMMON)
		)
	})
	@JvmField
	val PORTAL_FLUID_BUCKET = regItem(
		"portal_fluid_bucket",
		{ BucketItem(ModFluids.PORTAL_FLUID.get(), (Item.Properties().stacksTo(1))) })
	@JvmField
	val SPRING_WATER_BUCKET = regItem(
		"spring_water_bucket",
		{ SpringWaterBucketItem(ModFluids.STILL_SPRING_WATER.get(), (Item.Properties().stacksTo(1))) })
	@JvmField
	val ECHO_FORK =
		regItem("echo_fork", { EchoForkItem(Item.Properties().stacksTo(1)) })
	@JvmField
	val GLOWSTICK =
		regItem("glowstick", { GlowstickItem(null, ModBlocks.GLOWSTICK.get(), Item.Properties()) })

	//glowsticks
	@JvmField
	val RED_GLOWSTICK = regItem(
		"red_glowstick",
		{ GlowstickItem(DyeColor.RED, ModBlocks.RED_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val ORANGE_GLOWSTICK = regItem(
		"orange_glowstick",
		{ GlowstickItem(DyeColor.ORANGE, ModBlocks.ORANGE_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val YELLOW_GLOWSTICK = regItem(
		"yellow_glowstick",
		{ GlowstickItem(DyeColor.YELLOW, ModBlocks.YELLOW_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val LIME_GLOWSTICK = regItem(
		"lime_glowstick",
		{ GlowstickItem(DyeColor.LIME, ModBlocks.LIME_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val GREEN_GLOWSTICK = regItem(
		"green_glowstick",
		{ GlowstickItem(DyeColor.GREEN, ModBlocks.GREEN_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val CYAN_GLOWSTICK = regItem(
		"cyan_glowstick",
		{ GlowstickItem(DyeColor.CYAN, ModBlocks.CYAN_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val LIGHT_BLUE_GLOWSTICK = regItem(
		"light_blue_glowstick",
		{ GlowstickItem(DyeColor.LIGHT_BLUE, ModBlocks.LIGHT_BLUE_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val BLUE_GLOWSTICK = regItem(
		"blue_glowstick",
		{ GlowstickItem(DyeColor.BLUE, ModBlocks.BLUE_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val PURPLE_GLOWSTICK = regItem(
		"purple_glowstick",
		{ GlowstickItem(DyeColor.PURPLE, ModBlocks.PURPLE_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val MAGENTA_GLOWSTICK = regItem(
		"magenta_glowstick",
		{ GlowstickItem(DyeColor.MAGENTA, ModBlocks.MAGENTA_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val PINK_GLOWSTICK = regItem(
		"pink_glowstick",
		{ GlowstickItem(DyeColor.PINK, ModBlocks.PINK_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val BROWN_GLOWSTICK = regItem(
		"brown_glowstick",
		{ GlowstickItem(DyeColor.BROWN, ModBlocks.BROWN_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val BLACK_GLOWSTICK = regItem(
		"black_glowstick",
		{ GlowstickItem(DyeColor.BLACK, ModBlocks.BLACK_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val WHITE_GLOWSTICK = regItem(
		"white_glowstick",
		{ GlowstickItem(DyeColor.WHITE, ModBlocks.WHITE_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val GRAY_GLOWSTICK = regItem(
		"gray_glowstick",
		{ GlowstickItem(DyeColor.GRAY, ModBlocks.GRAY_GLOWSTICK.get(), Item.Properties()) })
	@JvmField
	val LIGHT_GRAY_GLOWSTICK = regItem(
		"light_gray_glowstick",
		{ GlowstickItem(DyeColor.LIGHT_GRAY, ModBlocks.LIGHT_GRAY_GLOWSTICK.get(), Item.Properties()) })


//	fun rb(name: String, it: Block)
//	{
//		return ITEMS.register(name) { BlockItem(it, Item.Properties()) }
//	}


	fun <T : Item> regItem(name: String, itemSup: Supplier<T>): Supplier<T>
	{
		return ITEMS.register(name, itemSup)
	}
}
