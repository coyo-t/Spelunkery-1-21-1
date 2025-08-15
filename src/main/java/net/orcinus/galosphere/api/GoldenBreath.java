package net.orcinus.galosphere.api;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

public interface GoldenBreath {

    void setGoldenAirSupply(float goldenAirSupply);

    float getGoldenAirSupply();

    default float getMaxGoldenAirSupply() {
        return 300;
    }

    default int decreaseGoldenAirSupply(LivingEntity livingEntity, int i) {
        AttributeInstance attributeInstance = livingEntity.getAttribute(Attributes.OXYGEN_BONUS);
        double d = attributeInstance != null ? attributeInstance.getValue() : 0.0;
        int reductionValue = livingEntity.isEyeInFluid(FluidTags.WATER) ? 1 : 4;
        if (d > 0.0 && livingEntity.getRandom().nextDouble() >= 1.0 / (d + 1.0)) {
            return i;
        }
        return Math.max(i - reductionValue, 0);
    }

}
