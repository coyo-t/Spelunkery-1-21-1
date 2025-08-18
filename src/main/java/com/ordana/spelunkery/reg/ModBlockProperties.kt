package com.ordana.spelunkery.reg

import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty

object ModBlockProperties
{
	val ILLUMINATED = BooleanProperty.create("illuminated")
	@JvmField
	val TOP = BooleanProperty.create("top")
	@JvmField
	val FLOOR = BooleanProperty.create("floor")
	@JvmField
	val PRIMED = BooleanProperty.create("primed")

	@JvmField
	val CAPS = IntegerProperty.create("caps", 1, 8)
	val LIGHT = IntegerProperty.create("light", 0, 16)
}
