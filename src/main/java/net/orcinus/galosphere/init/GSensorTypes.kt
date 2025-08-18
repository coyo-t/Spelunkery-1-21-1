package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.ai.sensing.SensorType
import net.minecraft.world.entity.ai.sensing.TemptingSensor
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.entities.ai.SparkleAi
import net.orcinus.galosphere.entities.ai.SpectreAi
import net.orcinus.galosphere.entities.ai.sensors.BerserkerEntitySensor
import net.orcinus.galosphere.entities.ai.sensors.NearestLichenMossSensor
import net.orcinus.galosphere.entities.ai.sensors.NearestPollinatedClusterSensor
import net.orcinus.galosphere.entities.ai.sensors.PreservedEntitySensor
import java.util.function.Supplier

object GSensorTypes
{
	@JvmField
	val SENSOR_TYPES = DeferredRegister.create(Registries.SENSOR_TYPE, Galosphere.MODID)

	@JvmField
	val SPARKLE_TEMPTATIONS = SENSOR_TYPES.register("sparkle_temptations") { rs ->
		SensorType { TemptingSensor(SparkleAi.getTemptations()) }
	}

	@JvmField
	val SPECTRE_TEMPTATIONS = SENSOR_TYPES.register("spectre_temptations") { rs ->
		SensorType { TemptingSensor(SpectreAi.getTemptations()) }
	}

	@JvmField
	val NEAREST_POLLINATED_CLUSTER = SENSOR_TYPES.register("nearest_pollinated_cluster") { rs ->
		SensorType { NearestPollinatedClusterSensor() }
	}

	@JvmField
	val NEAREST_LICHEN_MOSS = SENSOR_TYPES.register("nearest_lichen_moss") { rs ->
		SensorType { NearestLichenMossSensor() }
	}

	@JvmField
	val BLIGHTED_ENTITY_SENSOR = SENSOR_TYPES.register("blighted_entity_sensor") { rs ->
		SensorType { BerserkerEntitySensor() }
	}

	@JvmField
	val PRESERVED_ENTITY_SENSOR = SENSOR_TYPES.register("preserved_entity_sensor") { rs ->
		SensorType { PreservedEntitySensor() }
	}
}