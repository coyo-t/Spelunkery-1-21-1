package dissonance.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.orcinus.galosphere.api.SpectreBoundSpyglass;
import net.orcinus.galosphere.init.GMobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements SpectreBoundSpyglass
{
	private static final EntityDataAccessor<ItemStack> BANNER_STACK = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.ITEM_STACK);
	private static final EntityDataAccessor<Float> GOLDEN_AIR_SUPPLY = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Boolean> USING_SPECTRE_BOUNDED_SPYGLASS = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.BOOLEAN);
	@Unique
	private final LivingEntity $this = (LivingEntity)(Object)this;

	
	@Inject(at=@At("HEAD"), method="defineSynchedData")
	public void G$defineSynchedData (SynchedEntityData.Builder builder, CallbackInfo ci)
	{
		builder.define(BANNER_STACK, ItemStack.EMPTY);
		builder.define(GOLDEN_AIR_SUPPLY, 0.0F);
		builder.define(USING_SPECTRE_BOUNDED_SPYGLASS, false);
	}
	
	@Inject(at=@At("RETURN"), method="addAdditionalSaveData")
	public void G$addAdditionalSaveData (CompoundTag tag, CallbackInfo ci)
	{
		ItemStack stack = $this.getEntityData().get(BANNER_STACK);
		if (!stack.isEmpty())
		{
			tag.put("BannerStack", stack.save($this.registryAccess()));
		}
		tag.putBoolean("UsingSpectreBoundedSpyglass", this.isUsingSpectreBoundedSpyglass());
	}
	
	@Inject(at=@At("RETURN"), method="readAdditionalSaveData")
	public void G$readAdditionalSavaData (CompoundTag tag, CallbackInfo ci)
	{
		this.setUsingSpectreBoundedSpyglass(tag.getBoolean("UsingSpectreBoundedSpyglass"));
	}

	
	@Inject(at=@At("HEAD"), method="isInWall", cancellable=true)
	private void G$isInWall (CallbackInfoReturnable<Boolean> cir)
	{
		if ($this.hasEffect(GMobEffects.ASTRAL))
		{
			cir.setReturnValue(false);
		}
	}
	
	@Override
	public boolean isUsingSpectreBoundedSpyglass ()
	{
		return $this.getEntityData().get(USING_SPECTRE_BOUNDED_SPYGLASS);
	}
	
	@Override
	public void setUsingSpectreBoundedSpyglass (boolean usingSpectreBoundedSpyglass)
	{
		$this.getEntityData().set(USING_SPECTRE_BOUNDED_SPYGLASS, usingSpectreBoundedSpyglass);
	}
	
}
