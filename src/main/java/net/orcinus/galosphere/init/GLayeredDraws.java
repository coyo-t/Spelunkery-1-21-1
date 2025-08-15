package net.orcinus.galosphere.init;

import net.minecraft.client.gui.LayeredDraw;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.client.gui.GoldenBreathOverlay;

@OnlyIn(Dist.CLIENT)
public class GLayeredDraws {
    public static final LayeredDraw layeredDraw = new LayeredDraw().add(new GoldenBreathOverlay());
}
