package net.orcinus.galosphere.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.orcinus.galosphere.api.Spectatable;

@OnlyIn(Dist.CLIENT)
public class SpectatorVisionOverlay {

    @SubscribeEvent
    public void onHandRender(RenderHandEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getCameraEntity() instanceof Spectatable spectatable && spectatable.getManipulatorUUID() != null && mc.level != null) {
            Player player = mc.level.getPlayerByUUID(spectatable.getManipulatorUUID());
            if (player == mc.player) {
                event.setCanceled(true);
            }
        }
    }

}
