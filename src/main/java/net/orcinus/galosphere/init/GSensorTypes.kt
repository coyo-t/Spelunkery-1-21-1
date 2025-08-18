package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.ai.sensing.SensorType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.entities.ai.sensors.BerserkerEntitySensor
import net.orcinus.galosphere.entities.ai.sensors.PreservedEntitySensor

object GSensorTypes
{
	@JvmField
	val SENSOR_TYPES = DeferredRegister.create(Registries.SENSOR_TYPE, Galosphere.MODID)


	@JvmField
	val BLIGHTED_ENTITY_SENSOR = SENSOR_TYPES.register("blighted_entity_sensor") { rs ->
		SensorType { BerserkerEntitySensor() }
	}

	@JvmField
	val PRESERVED_ENTITY_SENSOR = SENSOR_TYPES.register("preserved_entity_sensor") { rs ->
		SensorType { PreservedEntitySensor() }
	}
}