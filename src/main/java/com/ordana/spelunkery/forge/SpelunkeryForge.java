package com.ordana.spelunkery.forge;

import com.ordana.spelunkery.Spelunkery;
import com.ordana.spelunkery.SpelunkeryClient;
import com.ordana.spelunkery.reg.ModSetup;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Spelunkery.MOD_ID)
@EventBusSubscriber(modid = Spelunkery.MOD_ID)
public class SpelunkeryForge
{
	public SpelunkeryForge (IEventBus bus)
	{
		bus.addListener(RegisterEvent.class, it -> {
			Spelunkery.commonInit();
			if (PlatHelper.getPhysicalSide().isClient())
			{
				ClientEventsForge.init();
				SpelunkeryClient.init();
			}
			ModSetup.setup();
		});
	}
	
	
	
}

