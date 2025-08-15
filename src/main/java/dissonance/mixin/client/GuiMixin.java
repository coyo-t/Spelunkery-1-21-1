package dissonance.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.api.Spectatable;
import net.orcinus.galosphere.api.SpectreBoundSpyglass;
import net.orcinus.galosphere.init.GLayeredDraws;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(Gui.class)
public class GuiMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDraw;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.BEFORE), method = "render", cancellable = true)
    private void G$preRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player instanceof SpectreBoundSpyglass spectreBoundSpyglass && spectreBoundSpyglass.isUsingSpectreBoundedSpyglass()) {
            ci.cancel();
        }
        if (mc.getCameraEntity() instanceof Spectatable spectatable && spectatable.getManipulatorUUID() != null && mc.level != null) {
            Player player = mc.level.getPlayerByUUID(spectatable.getManipulatorUUID());
            if (player == mc.player) {
                ci.cancel();
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDraw;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.AFTER), method = "render")
    private void G$postRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        GLayeredDraws.layeredDraw.render(guiGraphics, deltaTracker);
    }

}
