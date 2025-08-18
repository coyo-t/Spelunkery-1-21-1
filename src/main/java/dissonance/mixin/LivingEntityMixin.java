package dissonance.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.orcinus.galosphere.init.GMobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin
{
	@Unique
	private final LivingEntity $this = (LivingEntity)(Object)this;
	
	@Inject(at=@At("HEAD"), method="isInWall", cancellable=true)
	private void G$isInWall (CallbackInfoReturnable<Boolean> cir)
	{
		if ($this.hasEffect(GMobEffects.ASTRAL))
		{
			cir.setReturnValue(false);
		}
	}
	
}
