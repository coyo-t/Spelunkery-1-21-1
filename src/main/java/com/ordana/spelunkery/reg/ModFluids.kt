package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.fluids.PortalFluid
import com.ordana.spelunkery.fluids.SpringWater
import net.mehvahdjukaar.moonlight.api.fluids.ModFlowingFluid
import net.mehvahdjukaar.moonlight.api.platform.RegHelper
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModFluids : Fluids()
{
	@JvmField
	val THINGS = DeferredRegister.create(Registries.FLUID, Spelunkery.MOD_ID)

	private fun <T: Fluid> rg (name:String, thing: Supplier<T>) = THINGS.register(name, thing)

	// FIXME HOW DO FLUID WORK???????????????

	@JvmField
	val STILL_PORTAL_FLUID: Supplier<Fluid> = rg("portal_fluid") {
		PortalFluid.Source(
			ModFlowingFluid.properties().apply {
				supportsBoating(true)
				lightLevel(5)
			},
			ModBlocks.PORTAL_FLUID
		)
	}

	@JvmField
	val FLOWING_PORTAL_FLUID: Supplier<FlowingFluid> = rg("flowing_portal_fluid") {
		PortalFluid.Flowing(
			ModFlowingFluid.properties().apply {
				supportsBoating(true)
				lightLevel(5)
			},
			ModBlocks.PORTAL_FLUID
		)
	}

	@JvmField
	val FLOWING_SPRING_WATER: Supplier<FlowingFluid> = rg("flowing_spring_water") {
		SpringWater.Flowing(
			ModFlowingFluid.properties().apply {
				supportsBoating(true)
				canDrown(true)
				lightLevel(2)
				setCanConvertToSource(false)
			},
			ModBlocks.SPRING_WATER
		)
	}

	@JvmField
	val STILL_SPRING_WATER: Supplier<Fluid> = rg("spring_water") {
		SpringWater.Source(
			ModFlowingFluid.properties().apply {
				supportsBoating(true)
				canDrown(true)
				lightLevel(2)
				setCanConvertToSource(false)
			},
			ModBlocks.SPRING_WATER
		)
	}
}
