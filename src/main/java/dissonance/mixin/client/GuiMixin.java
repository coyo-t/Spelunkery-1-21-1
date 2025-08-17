package dissonance.mixin.client;

import net.minecraft.client.gui.Gui;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(Gui.class)
public class GuiMixin
{
	// FIXME
	/*
	@Inject(at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/LayeredDraw;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift=At.Shift.BEFORE), method="render", cancellable=true)
	private void G$preRender (GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci)
	{
		Minecraft mc = Minecraft.getInstance();
		if (mc.player instanceof SpectreBoundSpyglass spectreBoundSpyglass && spectreBoundSpyglass.isUsingSpectreBoundedSpyglass())
		{
			ci.cancel();
		}
		if (mc.getCameraEntity() instanceof Spectatable spectatable && spectatable.getManipulatorUUID() != null && mc.level != null)
		{
			Player player = mc.level.getPlayerByUUID(spectatable.getManipulatorUUID());
			if (player == mc.player)
			{
				ci.cancel();
			}
		}
	}
	
	@Inject(at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/LayeredDraw;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift=At.Shift.AFTER), method="render")
	private void G$postRender (GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci)
	{
		GLayeredDraws.layeredDraw.render(guiGraphics, deltaTracker);
	}
	*/
}
