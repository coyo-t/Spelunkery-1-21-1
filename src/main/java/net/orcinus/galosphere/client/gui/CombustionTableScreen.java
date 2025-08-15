package net.orcinus.galosphere.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.Galosphere;

@OnlyIn(Dist.CLIENT)
public class CombustionTableScreen extends AbstractContainerScreen<CombustionTableMenu> {
    private static final ResourceLocation TEXTURE = Galosphere.id("textures/gui/container/combustion_table.png");

    public CombustionTableScreen(CombustionTableMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 201;
    }

    @Override
    public void render(GuiGraphics source, int mouseX, int mouseY, float delta) {
        this.renderBackground(source, mouseX, mouseY, delta);
        super.render(source, mouseX, mouseY, delta);
        this.renderTooltip(source, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

}