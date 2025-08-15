package net.orcinus.galosphere.init;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.galosphere.Galosphere;

import java.util.List;

@Mod.EventBusSubscriber(modid = Galosphere.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GEnchantmentEffectComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Galosphere.MODID);

    public static final RegistryObject<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> SALTBOUND_TABLET_DECELERATION = DATA_COMPONENTS.register("saltbound_tablet_deceleration", () -> DataComponentType.<List<ConditionalEffect<EnchantmentValueEffect>>>builder().persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY).listOf()).build());
    public static final RegistryObject<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> SALTBOUND_TABLET_SUSTAIN = DATA_COMPONENTS.register("saltbound_tablet_sustain", () -> DataComponentType.<List<ConditionalEffect<EnchantmentValueEffect>>>builder().persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY).listOf()).build());
    public static final RegistryObject<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> SALTBOUND_TABLET_RUPTURE = DATA_COMPONENTS.register("saltbound_tablet_rupture", () -> DataComponentType.<List<ConditionalEffect<EnchantmentValueEffect>>>builder().persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY).listOf()).build());

}