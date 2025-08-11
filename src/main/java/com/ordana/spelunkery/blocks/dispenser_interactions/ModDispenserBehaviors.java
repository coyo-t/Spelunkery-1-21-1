package com.ordana.spelunkery.blocks.dispenser_interactions;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.util.DispenserHelper;

public class ModDispenserBehaviors
{
	
	public static void init ()
	{
		RegHelper.addDynamicDispenserBehaviorRegistration(ModDispenserBehaviors::registerBehaviors);
	}
	
	public static void registerBehaviors (DispenserHelper.Event event)
	{
		event.register(new CBMBehavior());
	}
}