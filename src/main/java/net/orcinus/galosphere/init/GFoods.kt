package net.orcinus.galosphere.init

import net.minecraft.world.food.FoodProperties

object GFoods
{
	val LICHEN_CORDYCEPS = FoodProperties.Builder().run {
		nutrition(2)
		saturationModifier(0.1f)
		fast()
		build()
	}
	val GOLDEN_LICHEN_CORDYCEPS = FoodProperties.Builder().run {
		nutrition(2)
		saturationModifier(0.1f)
		fast()
		build()
	}
	val SALTED_JERKY = FoodProperties.Builder().run {
		nutrition(6)
		saturationModifier(0.2f)
		build()
	}
}
