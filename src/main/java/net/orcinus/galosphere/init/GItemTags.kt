package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.orcinus.galosphere.Galosphere

object GItemTags
{
	@JvmField
	val NON_SINKABLES_HORSE_ARMORS = create("non_sinkable_horse_armors")
	@JvmField
	val SALTBOUND_TABLET_ENCHANTABLE = create("saltbound_tablet_enchantable")
	@JvmField
	val REPAIRS_PRESERVED_FLESH = create("repairs_preserved_flesh")

	private fun create(name: String) = TagKey.create(Registries.ITEM, Galosphere.id(name))
}
