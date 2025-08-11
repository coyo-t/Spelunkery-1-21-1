package com.ordana.spelunkery.forge;

import com.ordana.spelunkery.SpelunkeryClient;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.AddLayers;
import net.neoforged.neoforge.common.NeoForge;

public class ClientEventsForge
{
	
	public static void init ()
	{
		NeoForge.EVENT_BUS.register(ClientEventsForge.class);
		NeoForge.EVENT_BUS.addListener(ClientEventsForge::onAddLayers);
		NeoForge.EVENT_BUS.addListener(ClientEventsForge::loadComplete);
	}
	
	public static void loadComplete (FMLLoadCompleteEvent event)
	{
		SpelunkeryClient.checkIfFailed();
	}
	
	@SuppressWarnings("unchecked")
	public static void onAddLayers (AddLayers event)
	{
		for (var skinType: event.getSkins())
		{
			var renderer = event.getSkin(skinType);
			if (renderer != null)
			{
				// FIXME
//				renderer.render(new ParachuteLayer(skinType.pa));
//				renderer.addLayer(new ParachuteLayer(renderer));
			}
		}
	}
}
