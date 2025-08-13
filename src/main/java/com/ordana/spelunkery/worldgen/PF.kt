package com.ordana.spelunkery.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object PF
{
	fun paradox (context: BootstrapContext<PlacedFeature>)
	{
		val pf = context.lookup(Registries.PLACED_FEATURE)
		val biomes = context.lookup(Registries.BIOME)
	}
}