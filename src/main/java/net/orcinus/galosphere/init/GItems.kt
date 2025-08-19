package net.orcinus.galosphere.init

import net.minecraft.world.entity.Entity
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.AnimalArmorItem
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.neoforged.neoforge.common.DeferredSpawnEggItem
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.items.*
import java.util.function.Supplier

object GItems
{
	@JvmField
	val ITEMS = DeferredRegister.createItems(Galosphere.MODID)

	@JvmField
	val ICON_ITEM = regIt("icon_item") {
		object : Item(Properties().stacksTo(0)) {
			override fun inventoryTick(stack: ItemStack, world: Level, entity: Entity, slotId: Int, isSelected: Boolean)
			{
				stack.count = 0
			}
		}
	}

	@JvmField
	val PRESERVED_SPAWN_EGG = regIt("preserved_spawn_egg") { DeferredSpawnEggItem(GEntityTypes.PRESERVED_CORPSE, 15703431, 7246179, Item.Properties()) }

	@JvmField
	val ALLURITE_SHARD = regIt("allurite_shard")

	@JvmField
	val LUMIERE_SHARD = regIt("lumiere_shard")

	@JvmField
	val PINK_SALT_SHARD = regIt("pink_salt_shard")

	@JvmField
	val RAW_SILVER = regIt("raw_silver")

	@JvmField
	val SILVER_INGOT = regIt("silver_ingot")

	@JvmField
	val SILVER_NUGGET = regIt("silver_nugget")

	@JvmField
	val SILVER_UPGRADE_SMITHING_TEMPLATE = regIt("silver_upgrade_smithing_template") { SilverSmithingTemplateItem() }

	@JvmField
	val BAROMETER = regIt("barometer")

	@JvmField
	val STERLING_HELMET = regIt("sterling_helmet") { SterlingArmorItem(ArmorItem.Type.HELMET, Item.Properties().stacksTo(1)) }

	@JvmField
	val STERLING_CHESTPLATE = regIt("sterling_chestplate") { SterlingArmorItem(ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)) }

	@JvmField
	val STERLING_LEGGINGS = regIt("sterling_leggings") { SterlingArmorItem(ArmorItem.Type.LEGGINGS, Item.Properties().stacksTo(1)) }

	@JvmField
	val STERLING_BOOTS = regIt("sterling_boots") { SterlingArmorItem(ArmorItem.Type.BOOTS, Item.Properties().stacksTo(1)) }

	@JvmField
	val STERLING_HORSE_ARMOR = regIt("sterling_horse_armor") {
		AnimalArmorItem(
			GArmorMaterials.STERLING,
			AnimalArmorItem.BodyType.EQUESTRIAN,
			false,
			Item.Properties().stacksTo(1)
		)
	}

	@JvmField
	val SALTED_JERKY = regIt("salted_jerky") { Item(Item.Properties().food(GFoods.SALTED_JERKY)) }

	@JvmField
	val CURED_MEMBRANE = regIt("cured_membrane")

	@JvmField
	val LICHEN_CORDYCEPS = regIt("lichen_cordyceps") { LichenCordycepsItem(GBlocks.LICHEN_CORDYCEPS.get(), Item.Properties().food(GFoods.LICHEN_CORDYCEPS)) }

	@JvmField
	val GOLDEN_LICHEN_CORDYCEPS = regIt("golden_lichen_cordyceps") {
		Item(
			Item.Properties().food(GFoods.GOLDEN_LICHEN_CORDYCEPS)
		)
	}

	@JvmField
	val CHANDELIER = regIt("chandelier") { ChandelierItem(GBlocks.CHANDELIER.get(), Item.Properties()) }

	@JvmField
	val SALTBOUND_TABLET = regIt("saltbound_tablet") { SaltboundTabletItem(Item.Properties().stacksTo(1).durability(432)) }

	@JvmField
	val PRESERVED_TEMPLATE = regIt("preserved_template") { PreservedSmithingTemplateItem() }

	@JvmField
	val PRESERVED_FLESH = regIt("preserved_flesh") {
		PreservedFleshItem(
			Item.Properties().stacksTo(1).durability(180)
				.food(FoodProperties.Builder().nutrition(4).saturationModifier(0.1f).build())
		)
	}

	fun <T : Item> regIt(name: String, thing: Supplier<T>): Supplier<T>
	{
		return ITEMS.register(name, thing)
	}

	fun regIt(name: String): Supplier<Item>
	{
		return regIt(name) { Item(Item.Properties()) }
	}
}
