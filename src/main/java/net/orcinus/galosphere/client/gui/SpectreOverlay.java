package net.orcinus.galosphere.client.gui;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.orcinus.galosphere.api.SpectreBoundSpyglass;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class SpectreOverlay
{
	
	@SubscribeEvent(priority=EventPriority.LOW)
	public void onPreRender (ScreenEvent.Render.Pre event)
	{
		if (event.isCanceled()) return;
		Optional.ofNullable(Minecraft.getInstance().player).filter(SpectreBoundSpyglass.class::isInstance).map(SpectreBoundSpyglass.class::cast).filter(SpectreBoundSpyglass::isUsingSpectreBoundedSpyglass).ifPresent(spectreBoundedSpyglass -> event.setCanceled(true));
	}
	
}
