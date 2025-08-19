package dissonance

import com.ordana.spelunkery.Spelunkery
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.orcinus.galosphere.Galosphere
import org.apache.logging.log4j.LogManager


//@Mod(DissonanceCompendium.ID)
class DissonanceCompendium(ev: IEventBus)
{
	val spelunkery = Spelunkery(ev)
	val galosphere = Galosphere(ev)

	init
	{
		ev.addListener<FMLCommonSetupEvent> { ev ->
			println("\n\n\n\nMACHINE WITNESS\n\nFUCK\n\n\n\n")
		}


	}


	companion object
	{
		const val ID = "dissonance"
		val LOGGER = LogManager.getLogger()
	}
}

