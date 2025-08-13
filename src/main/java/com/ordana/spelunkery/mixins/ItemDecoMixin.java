package com.ordana.spelunkery.mixins;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiGraphics.class)
public abstract class ItemDecoMixin implements IGuiGraphicsExtension
{
	
	@Shadow public abstract void fill (RenderType renderType, int minX, int minY, int maxX, int maxY, int color);
	
	@ModifyExpressionValue(
		method="renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
		at=@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;isBarVisible()Z", ordinal = 0)
	)
	private boolean fpw_subplantDurabilityRender (
		boolean original,
		@Local(argsOnly=true) ItemStack stack,
		@Local(argsOnly=true, ordinal=0) int x,
		@Local(argsOnly=true, ordinal=1) int y
	)
	{
		if (original)
		{
//			System.out.printf("MACHINE WITNESS %s%n @ %d %d", stack, x, y);
			final var c = stack.getBarColor();
			fill(RenderType.GUI_OVERLAY, x, y, x + 16, y + 16, (0xFF<<24)|c);
		}
		return false;
	}
	
}
