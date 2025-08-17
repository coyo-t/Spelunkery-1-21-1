package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.MenuType.MenuSupplier
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.client.gui.CombustionTableMenu
import java.util.function.Supplier

object GMenuTypes
{
	@JvmField
	val MENU_TYPES =
		DeferredRegister.create(Registries.MENU, Galosphere.MODID)

	@JvmField
	val COMBUSTION_TABLE =
		MENU_TYPES.register(
			"combustion_table"
		) { rl ->
			MenuType({ id, inventory ->
				CombustionTableMenu(
					id,
					inventory
				)
			}, FeatureFlags.VANILLA_SET)
		}
}