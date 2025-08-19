package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.ai.sensing.SensorType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.entities.ai.PreservedEntitySensor

object GSensorTypes
{
	@JvmField
	val SENSOR_TYPES = DeferredRegister.create(Registries.SENSOR_TYPE, Galosphere.MODID)

	@JvmField
	val PRESERVED_ENTITY_SENSOR = SENSOR_TYPES.register("preserved_entity_sensor") { rs ->
		SensorType(::PreservedEntitySensor)
	}
}