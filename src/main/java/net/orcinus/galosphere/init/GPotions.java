package net.orcinus.galosphere.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.galosphere.Galosphere;

@Mod.EventBusSubscriber(modid = Galosphere.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Galosphere.MODID);

    public static final RegistryObject<Potion> ASTRAL = POTIONS.register("astral", () -> new Potion(new MobEffectInstance(GMobEffects.ASTRAL.getHolder().get(), 1800)));
    public static final RegistryObject<Potion> LONG_ASTRAL = POTIONS.register("long_astral", () -> new Potion("astral", new MobEffectInstance(GMobEffects.ASTRAL.getHolder().get(), 3600)));

}
