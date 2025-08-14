package com.ordana.spelunkery.utils

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack


infix fun ItemStack.isItem (other: Item)
	= `is`(other)
