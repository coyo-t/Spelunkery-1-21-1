package dissonance.mixin.client;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.orcinus.galosphere.Galosphere;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(ModelBakery.class)
public class ModelBakeryMixin
{
	
	@Inject(at=@At("RETURN"), method="loadBlockModel")
	private void G$loadBlockModel (ResourceLocation resourceLocation, CallbackInfoReturnable<BlockModel> cir)
	{
		BlockModel returnValue = cir.getReturnValue();
		if (resourceLocation.equals(ResourceLocation.withDefaultNamespace("item/crossbow")))
		{
			List<ItemOverride.Predicate> glowFlareConditions = Lists.newArrayList();
			List<ItemOverride.Predicate> spectreFlareConditions = Lists.newArrayList();
			
			glowFlareConditions.add(new ItemOverride.Predicate(ResourceLocation.withDefaultNamespace("charged"), 1));
			glowFlareConditions.add(new ItemOverride.Predicate(Galosphere.id("glow_flare"), 1));
			returnValue.getOverrides().add(new ItemOverride(Galosphere.id("item/crossbow_glow_flare"), glowFlareConditions));
			
			spectreFlareConditions.add(new ItemOverride.Predicate(ResourceLocation.withDefaultNamespace("charged"), 1));
			spectreFlareConditions.add(new ItemOverride.Predicate(Galosphere.id("spectre_flare"), 1));
			returnValue.getOverrides().add(new ItemOverride(Galosphere.id("item/crossbow_spectre_flare"), spectreFlareConditions));
		}
	}
	
}