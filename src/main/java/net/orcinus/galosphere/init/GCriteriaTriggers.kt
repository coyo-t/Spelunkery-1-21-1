package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.criterion.GCriterion

object GCriteriaTriggers
{
	@JvmField
	val CRITERION_TRIGGERS =
		DeferredRegister.create(Registries.TRIGGER_TYPE, Galosphere.MODID)
	@JvmField
	val WARPED_TELEPORT =
		CRITERION_TRIGGERS.register("warped_teleport", ::GCriterion)
	@JvmField
	val ACTIVATE_PINK_SALT_CHAMBER =
		CRITERION_TRIGGERS.register("activate_pink_salt_chamber", ::GCriterion)
}
