package dissonance

import com.ordana.spelunkery.Spelunkery
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModLoadingContext
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.orcinus.galosphere.Galosphere


@Mod(DissonanceCompendium.ID)
class DissonanceCompendium(ev: IEventBus, container: ModContainer)
{
	val spelunkery = Spelunkery(ev, container)
	val galosphere = Galosphere(ev, container)

	init
	{
		val modEventBus = ModLoadingContext.get().activeContainer.eventBus
		val eventBus = NeoForge.EVENT_BUS


		// spelunkery


	}


	companion object
	{
		const val ID = "dissonance"
	}
}

