package net.orcinus.galosphere.init;

import com.mojang.serialization.MapCodec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.util.PillagerSilverLootModifier;

@Mod.EventBusSubscriber(modid = Galosphere.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Galosphere.MODID);

    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> PILLAGER_SILVER_LOOT = LOOT_MODIFIERS.register("pillager_silver_loot", () -> PillagerSilverLootModifier.CODEC);

}