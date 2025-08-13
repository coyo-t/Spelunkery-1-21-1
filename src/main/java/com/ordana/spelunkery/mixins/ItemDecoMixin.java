package com.ordana.spelunkery.mixins;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
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
			if (stack.is(Items.BUNDLE))
			{
				final var C_PARTIAL = 0xFF_7F7FFF;
				final var C_FULL = 0xFF_FF7F7F;
				
				final var contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
				final var fac = contents.weight().doubleValue();
				
				if (fac > 0.0)
				{
					final var c = Mth.equal(fac, 1.0) ? C_FULL : C_PARTIAL;
					fill(RenderType.GUI_OVERLAY, x, y, x + 16, y + (int)Math.round(fac * 16), (0xFF<<24)|c);
				}
				
			}
			else
			{
				final int valueMax = stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
				final int value    = stack.getOrDefault(DataComponents.DAMAGE, 0);
				
				if (valueMax > 0)
				{
					final var fac = (double)value / (double)valueMax;
					final var c = stack.getBarColor();
					fill(RenderType.GUI_OVERLAY, x, y, x + 16, y + 16 - (int)Math.round(fac * 16), (0xFF<<24)|c);
				}
			}
		
			
		}
		return false;
	}
	
}
