package dissonance.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.orcinus.galosphere.entities.GlowFlare;
import net.orcinus.galosphere.entities.SpectreFlare;
import net.orcinus.galosphere.init.GItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin
{
	
	@Inject(at=@At("HEAD"), method="createProjectile", cancellable=true)
	private void G$createProjectile (Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean bl, CallbackInfoReturnable<Projectile> cir)
	{
		if (itemStack2.is(GItems.GLOW_FLARE.get()))
		{
			cir.setReturnValue(new GlowFlare(level, itemStack2, livingEntity, livingEntity.getX(), livingEntity.getEyeY() - (double)0.15F, livingEntity.getZ(), true));
		}
		else if (itemStack2.is(GItems.SPECTRE_FLARE.get()))
		{
			cir.setReturnValue(new SpectreFlare(level, itemStack2, livingEntity, livingEntity.getX(), livingEntity.getEyeY() - (double)0.15F, livingEntity.getZ(), true));
		}
	}
	
}