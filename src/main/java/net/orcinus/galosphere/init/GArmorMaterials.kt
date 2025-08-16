package net.orcinus.galosphere.init

import net.minecraft.Util
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.orcinus.galosphere.Galosphere
import java.util.*
import java.util.function.Supplier

object GArmorMaterials
{
	@JvmField
	val STERLING = register(
		"sterling", EnumMap<ArmorItem.Type, Int>(ArmorItem.Type::class.java).apply {
			put(ArmorItem.Type.BOOTS, 1)
			put(ArmorItem.Type.LEGGINGS, 4)
			put(ArmorItem.Type.CHESTPLATE, 3)
			put(ArmorItem.Type.HELMET, 1)
			put(ArmorItem.Type.BODY, 3)
		},
		9,
		SoundEvents.ARMOR_EQUIP_CHAIN,
		0.0f,
		0.0f
	) { Ingredient.of(GItems.SILVER_INGOT.get()) }

	private fun register(
		string: String,
		enumMap: EnumMap<ArmorItem.Type, Int>,
		i: Int,
		holder: Holder<SoundEvent>,
		f: Float,
		g: Float,
		supplier: Supplier<Ingredient>
	): Holder<ArmorMaterial>
	{
		val list = listOf(ArmorMaterial.Layer(Galosphere.id(string)))
		return register(string, enumMap, i, holder, f, g, supplier, list)
	}

	private fun register(
		string: String,
		enumMap: EnumMap<ArmorItem.Type, Int>,
		i: Int,
		holder: Holder<SoundEvent>,
		f: Float,
		g: Float,
		supplier: Supplier<Ingredient>,
		list: List<ArmorMaterial.Layer>
	): Holder<ArmorMaterial>
	{
		val enumMap2 = EnumMap<ArmorItem.Type, Int>(ArmorItem.Type::class.java)
		for (type in ArmorItem.Type.entries.toTypedArray())
		{
			enumMap2.put(type, enumMap.get(type))
		}
		return Registry.registerForHolder(
			BuiltInRegistries.ARMOR_MATERIAL,
			Galosphere.id(string),
			ArmorMaterial(enumMap2, i, holder, supplier, list, f, g)
		)
	}
}