package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.criterion.GCriterion
import java.util.function.Supplier

object GCriteriaTriggers
{
	@JvmField
	val CRITERION_TRIGGERS =
		DeferredRegister.create(Registries.TRIGGER_TYPE, Galosphere.MODID)

	@JvmField
	val LUMIERE_COMPOST =
		CRITERION_TRIGGERS.register("lumiere_compost", ::GCriterion)
	@JvmField
	val WARPED_TELEPORT =
		CRITERION_TRIGGERS.register("warped_teleport", ::GCriterion)
	@JvmField
	val USE_SPECTRE_SPYGLASS =
		CRITERION_TRIGGERS.register("use_spectre_spyglass", ::GCriterion)
	@JvmField
	val LIGHT_SPREAD =
		CRITERION_TRIGGERS.register("light_spread", ::GCriterion)
	@JvmField
	val USE_SPECTRE_FLARE =
		CRITERION_TRIGGERS.register("use_spectre_flare", ::GCriterion)
	@JvmField
	val ACTIVATE_PINK_SALT_CHAMBER =
		CRITERION_TRIGGERS.register("activate_pink_salt_chamber", ::GCriterion)
}
