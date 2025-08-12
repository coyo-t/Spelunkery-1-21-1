package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.neoforged.neoforge.registries.DeferredRegister

object ModSoundEvents
{
	@JvmField val THINGS = mutableListOf<SoundEvent>()

	@JvmStatic
	fun init(r: DeferredRegister<SoundEvent>)
	{
		for (thing in THINGS)
		{
			r.register(thing.location.path) { res -> thing }
		}
	}

	@JvmField var PORTAL_FLUID_AMBIENT = rg("portal_fluid_ambient")
	@JvmField var PORTAL_FLUID_SUBMERGE = rg("portal_fluid_submerge")
	@JvmField var PORTAL_FLUID_TELEPORT = rg("portal_fluid_teleport")

	@JvmField var PORTAL_FLUID_BUCKET_EMPTY = rg("portal_fluid_bucket_empty")
	@JvmField var PORTAL_FLUID_BUCKET_FILL = rg("portal_fluid_bucket_fill")
	@JvmField var PORTAL_FLUID_BOTTLE_EMPTY = rg("portal_fluid_bottle_empty")
	@JvmField var PORTAL_FLUID_BOTTLE_FILL = rg("portal_fluid_bottle_fill")
	@JvmField var BOAT_PADDLE_PORTAL_FLUID = rg("boat_paddle_portal_fluid")
	@JvmField var SPRING_WATER_AMBIENT = rg("spring_water_ambient")
	@JvmField var SPRING_WATER_POP = rg("spring_water_pop")
	@JvmField var JANGLE = rg("jangle")

	private fun rg (name:String) = rg(name) { SoundEvent.createVariableRangeEvent(it) }

	private fun rg(name: String, p:(r: ResourceLocation)->SoundEvent): SoundEvent
	{
		return p(Spelunkery.res(name))
	}
}
