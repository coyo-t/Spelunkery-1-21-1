package com.ordana.spelunkery.mixins;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.ordana.spelunkery.datamalarky.SlotDecoManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.GuiSpriteManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.extensions.IGuiGraphicsExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(GuiGraphics.class)
public abstract class ItemDecoMixin implements IGuiGraphicsExtension
{
	
	@Shadow public abstract void fill (RenderType renderType, int minX, int minY, int maxX, int maxY, int color);

	@Shadow abstract void innerBlit (
		ResourceLocation atlasLocation,
		int x1,
		int x2,
		int y1,
		int y2,
		int blitOffset,
		float minU,
		float maxU,
		float minV,
		float maxV
	);
	
	@Shadow @Final private GuiSpriteManager sprites;
	
	@Shadow public abstract void blitSprite (ResourceLocation sprite, int textureWidth, int textureHeight, int uPosition, int vPosition, int x, int y, int uWidth, int vHeight);
	
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
		final var maybeThing = SlotDecoManager.getThing(stack);
	
		if (maybeThing != null)
		{
			final double fac;
			final var maybeVSrc = stack.get(maybeThing.getCurrentValueSource());
			final var maybeVMax = stack.get(maybeThing.getMaxValueSource());
			
			if (maybeVSrc instanceof Integer vs && maybeVMax instanceof Integer vm)
			{
				fac = Mth.clamp((double)vs / (double)vm, 0.0, 1.0);
			}
			else
			{
				fac = 0.5;
			}
			final var p = maybeThing.getAt();
			final var xx = x + p.x;
			final var yy = y + p.y;
			final var sub = maybeThing.getSubImage(fac);
			System.out.println(sub);
			final var bs = maybeThing.getBaseSize();
			// fucks sake
			
			blitSprite(sub, bs.width, bs.height, 0, 0, xx, yy, bs.width, bs.height);
			
//			final var fuck = sprites.getSprite(sub);
//			final var cont = fuck.contents();
//			innerBlit(
//				sub,
//				xx,
//				xx + cont.width(),
//				yy,
//				yy + cont.height(),
//				-100,
//				fuck.getU0(),
//				fuck.getU1(),
//				fuck.getV0(),
//				fuck.getV1()
//			);
//			fill(RenderType.GUI_OVERLAY, x, yy, x + 8, yy + 8, 0xFF_FF0000);
			return false;
		}
	
		if (stack.is(Items.BUNDLE))
		{
			final var C_PARTIAL = 0xFF_7F7FFF;
			final var C_FULL = 0xFF_FF7F7F;
			
			final var contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
			final var fac = contents.weight().doubleValue();
			
			if (fac > 0.0)
			{
				final var c = Mth.equal(fac, 1.0) ? C_FULL : C_PARTIAL;
				fill(RenderType.GUI_OVERLAY, x, y, x + 8, y + (int)Math.round(fac * 16), (0xFF<<24)|c);
			}
			
		}
		else if (stack.getItem() instanceof BlockItem bi)
		{
			if (bi == Items.BEE_NEST || bi == Items.BEEHIVE)
			{
				final var bs = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
				final int honey = Objects.requireNonNullElse(bs.get(BlockStateProperties.LEVEL_HONEY), 0);
				if (honey > 0)
				{
					final var HR_MIN = 0xac;
					final var HG_MIN = 0x32;
					final var HB_MIN = 0x32;
					final var HR_MAX = 0xfb;
					final var HG_MAX = 0xf2;
					final var HB_MAX = 0x36;
					
					final var fac = (double)Mth.clamp(honey, 0, 5) / 5.0;
					final var ifac = (int)Math.round(fac * 255.0);
					final var r = (((HR_MAX-HR_MIN) * ifac + 0xFF) >> 8) + HR_MIN;
					final var g = (((HG_MAX-HG_MIN) * ifac + 0xFF) >> 8) + HG_MIN;
					final var b = (((HB_MAX-HB_MIN) * ifac + 0xFF) >> 8) + HB_MIN;
					final var c = ((r&0xFF)<<16)|((g&0xFF)<<8)|(b&0xFF)|(0xFF<<24);
					fill(RenderType.GUI_OVERLAY, x, y, x + 8, y + (int)Math.round(fac * 16), c);
				}
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
				fill(RenderType.GUI_OVERLAY, x, y, x + 8, y + 16 - (int)Math.round(fac * 16), (0xFF<<24)|c);
			}
		}
		
		
		return false;
	}
	
}
