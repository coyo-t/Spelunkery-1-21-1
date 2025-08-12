package com.ordana.spelunkery.reg

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties

object ModFoods
{
	val GRILLED_PORTABELLA = FoodProperties.Builder().run {
		nutrition(6)
		saturationModifier(0.8f)
		effect({ MobEffectInstance(MobEffects.DIG_SPEED, 1800, 2) }, 1f)
		build()
	}

	val PORTABELLA = FoodProperties.Builder().run {
		nutrition(3)
		saturationModifier(0.4f)
		effect({ MobEffectInstance(MobEffects.DIG_SPEED, 300, 1) }, 0.5f)
		build()
	}

	val CRIMINI = FoodProperties.Builder().run {
		nutrition(2)
		saturationModifier(1.5f)
		build()
	}

	val BUTTON_MUSHROOM = FoodProperties.Builder().run {
		nutrition(2)
		saturationModifier(0.3f)
		fast()
		effect({ MobEffectInstance(MobEffects.DIG_SPEED, 18000, 2) }, 0.01f)
		build()
	}

	val PORTAL_FLUID = FoodProperties.Builder().run {
		nutrition(0)
		saturationModifier(0f)
		alwaysEdible()
		build()
	}

}
