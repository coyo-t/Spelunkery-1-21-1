package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.items.EchoForkItem
import com.ordana.spelunkery.items.GlowstickItem
import com.ordana.spelunkery.items.PortalFluidBottleItem
import net.minecraft.ChatFormatting
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.Level
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModItems
{
	@JvmStatic
	fun inititiititititialliziaation ()
	{
		for (i in ModBlocks.ITEMZORNAMEZOR.indices)
		{
			val name = ModBlocks.ITEMZORNAMEZOR[i]
			val thing = ModBlocks.SHOULD_ITEMITITIZE[i]
			regItem(name) { BlockItem(thing.get(), Item.Properties()) }
		}
	}

	@JvmField
	val ITEMS = DeferredRegister.createItems(Spelunkery.MOD_ID)

	@JvmField val STONE_PEBBLE = regItem("stone_pebble")
	@JvmField val DEEPSLATE_PEBBLE = regItem("deepslate_pebble")
	@JvmField val NETHERRACK_PEBBLE = regItem("netherrack_pebble")
	@JvmField val MAGMA_PEBBLE = regItem("magma_pebble")
	@JvmField val BLACKSTONE_PEBBLE = regItem("blackstone_pebble")
	@JvmField val BASALT_PEBBLE = regItem("basalt_pebble")
	@JvmField val END_STONE_PEBBLE = regItem("end_stone_pebble")

	@JvmField val SALT = regItem("salt") { BlockItem(ModBlocks.SALT.get(), Item.Properties()) }
	@JvmField val NEPHRITE_CHUNK = regItem("nephrite_chunk")
	@JvmField val RAW_MAGNETITE = regItem("raw_magnetite")
	@JvmField val RAW_IRON_NUGGET = regItem("raw_iron_nugget")
	@JvmField val RAW_COPPER_NUGGET = regItem("raw_copper_nugget")
	@JvmField val RAW_GOLD_NUGGET = regItem("raw_gold_nugget")
	@JvmField val RAW_MAGNETITE_NUGGET = regItem("raw_magnetite_nugget")
	@JvmField val RAW_ZINC_NUGGET = regItem("raw_zinc_nugget")
	@JvmField val RAW_LEAD_NUGGET = regItem("raw_lead_nugget")
	@JvmField val RAW_SILVER_NUGGET = regItem("raw_silver_nugget")
	@JvmField val RAW_TIN_NUGGET = regItem("raw_tin_nugget")
	@JvmField val RAW_BISMUTH_NUGGET = regItem("raw_bismuth_nugget")
	@JvmField val COPPER_NUGGET = regItem("copper_nugget")
	@JvmField val BISMUTH_NUGGET = regItem("bismuth_nugget")

	// FIXME add fueltime data for these two
	// 200
	@JvmField val COAL_LUMP = regItem("coal_lump")

	// 200
	@JvmField val CHARCOAL_LUMP = regItem("charcoal_lump")

	@JvmField val ROUGH_CINNABAR_SHARD = regItem("rough_cinnabar_shard")
	@JvmField val ROUGH_LAZURITE_SHARD = regItem("rough_lazurite_shard")
	@JvmField val ROUGH_EMERALD_SHARD = regItem("rough_emerald_shard")
	@JvmField val ROUGH_DIAMOND_SHARD = regItem("rough_diamond_shard")
	@JvmField val ROUGH_QUARTZ_SHARD = regItem("rough_quartz_shard")

	@JvmField val ROUGH_JADE_SHARD = regItem("rough_jade_shard")

	@JvmField val ROUGH_CINNABAR = regItem("rough_cinnabar")
	@JvmField val ROUGH_LAZURITE = regItem("rough_lazurite")
	@JvmField val ROUGH_EMERALD = regItem("rough_emerald")
	@JvmField val ROUGH_DIAMOND = regItem("rough_diamond")
	@JvmField val CINNABAR_SHARD = regItem("cinnabar_shard")
	@JvmField val LAPIS_LAZULI_SHARD = regItem("lapis_lazuli_shard")
	@JvmField val EMERALD_SHARD = regItem("emerald_shard")
	@JvmField val DIAMOND_SHARD = regItem("diamond_shard")
	@JvmField val JADE_SHARD = regItem("jade_shard")
	@JvmField val CINNABAR = regItem("cinnabar")
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
			) = false

			override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack?>
			{
				val stack = player.getItemInHand(hand)
				val co = player.position()
				player.displayClientMessage(
					Component.translatable("tooltip.spelunkery.player_pos", Mth.floor(co.x), -Mth.floor(co.z)).setStyle(
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
			Item.Properties().apply {
				food(ModFoods.PORTAL_FLUID)
				stacksTo(16)
				rarity(Rarity.UNCOMMON)
			}
		)
	})

	//FIXME GOOOOOOP
//	@JvmField
//	val PORTAL_FLUID_BUCKET = regItem(
//		"portal_fluid_bucket",
//		{ BucketItem(ModFluids.PORTAL_FLUID.get(), (Item.Properties().stacksTo(1))) })
//	@JvmField
//	val SPRING_WATER_BUCKET = regItem(
//		"spring_water_bucket",
//		{ SpringWaterBucketItem(ModFluids.STILL_SPRING_WATER.get(), (Item.Properties().stacksTo(1))) })
	@JvmField
	val ECHO_FORK = regItem("echo_fork") { EchoForkItem(Item.Properties().stacksTo(1)) }

	@JvmField
	val GLOWSTICK = regItem("glowstick") { GlowstickItem(null, ModBlocks.GLOWSTICK.get(), Item.Properties()) }


//	fun rb(name: String, it: Block)
//	{
//		return ITEMS.register(name) { BlockItem(it, Item.Properties()) }
//	}

	fun regItem (name:String) = regItem(name) { Item(Item.Properties()) }

	fun <T : Item> regItem(name: String, itemSup: Supplier<T>): Supplier<T>
	{
		return ITEMS.register(name, itemSup)
	}
}
