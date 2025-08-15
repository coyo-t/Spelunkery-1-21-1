package dissonance.mixin;

import com.google.common.collect.Sets;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceKey;
import net.orcinus.galosphere.init.GBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Iterator;
import java.util.Set;

@Mixin(RegistrySetBuilder.BuildState.class)
public class BuiltStateMixin {

    // TEMPORARY FIX FOR THROWING EXCEPTIONS FOR MODDED BIOMES
    @ModifyVariable(method = "reportNotCollectedHolders", at = @At("STORE"), ordinal = -1)
    private Iterator<ResourceKey<Object>> modifyHolders(Iterator<ResourceKey<Object>> value) {
        Set<ResourceKey<Object>> set = Sets.newHashSet();
        value.forEachRemaining(resourceKey -> {
            if (!GBiomes.BIOMES.contains(resourceKey)) {
                set.add(resourceKey);
            }
        });
        return set.iterator();
    }

}
