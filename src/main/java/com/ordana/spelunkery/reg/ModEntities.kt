package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.blocks.entity.FallingLayerEntity
import com.ordana.spelunkery.entities.ThrownGlowstickEntity
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredRegister

object ModEntities
{
	@JvmField
	val THINGS = DeferredRegister.create(Registries.ENTITY_TYPE, Spelunkery.MOD_ID)

	//Thrown Entities
	@JvmField
	var GLOWSTICK = rg(
		name     = "glowstick",
		factory  = { t, w -> ThrownGlowstickEntity(t, w) },
		category = MobCategory.MISC,
		size     = 0.28f to 0.98f
	)

	//Other Entities
	@JvmField
	var FALLING_LAYER = rg(
		name     = "falling_layer",
		factory  = { t, w -> FallingLayerEntity(t, w) },
		category = MobCategory.MISC,
		size     = 0.98f to 0.98f
	)

	private fun <T : Entity> rg(
		name: String,
		factory: EntityFactory<T>,
		category: MobCategory,
		size: Pair<Float, Float>,
	) = THINGS.register(name) { rs ->
		EntityType.Builder
			.of(factory, category)
			.sized(size.first, size.second)
			.clientTrackingRange(10)
			.setShouldReceiveVelocityUpdates(true)
			.updateInterval(20)
			.build(rs.toString())
	}
}
