package net.orcinus.galosphere.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.api.GoldenBreath;

@OnlyIn(Dist.CLIENT)
public class GoldenBreathOverlay implements LayeredDraw.Layer {
    private static final ResourceLocation GALOSPHERE_ICONS = Galosphere.id("textures/gui/galosphere_icons.png");

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        if (!mc.options.hideGui && mc.gameMode != null && mc.gameMode.canHurtPlayer() && mc.getCameraEntity() instanceof Player) {
            RenderSystem.enableBlend();
            this.renderGoldenAirSupply(guiGraphics, mc.player, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
            RenderSystem.disableBlend();
        }
    }

    private void renderGoldenAirSupply(GuiGraphics guiGraphics, Player player, int width, int height) {
        if (player == null) return;

        int n = width / 2 + 91;
        int o = height - 39;
        int t = o - 20;

        float y = ((GoldenBreath) player).getMaxGoldenAirSupply();
        float z = Math.min(((GoldenBreath) player).getGoldenAirSupply(), y);
        if (z < y && player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
            int ab = Mth.ceil((double)(z - 2) * 4.0 / (double)y);
            int ac = Mth.ceil((double)z * 4.0 / (double)y) - ab;
            for (int ad = 0; ad < ab + ac; ++ad) {
                if (ad < ab) {
                    guiGraphics.blit(GALOSPHERE_ICONS, n - ad * 8 - 9, t, 16, 18, 9, 9);
                    continue;
                }
                guiGraphics.blit(GALOSPHERE_ICONS, n - ad * 8 - 9, t, 25, 18, 9, 9);
            }
        }
    }
}
