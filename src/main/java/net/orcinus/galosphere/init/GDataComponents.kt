package net.orcinus.galosphere.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.component.CustomData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.items.components.SpectreBound;

@Mod.EventBusSubscriber(modid = Galosphere.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Galosphere.MODID);

    public static final RegistryObject<DataComponentType<Integer>> EXPLOSION = DATA_COMPONENT_TYPES.register("explosion", () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
    public static final RegistryObject<DataComponentType<Integer>> DURATION = DATA_COMPONENT_TYPES.register("duration", () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
    public static final RegistryObject<DataComponentType<Integer>> BOUNCY = DATA_COMPONENT_TYPES.register("bouncy", () -> DataComponentType.<Integer>builder().persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
    public static final RegistryObject<DataComponentType<Boolean>> PRESERVED = DATA_COMPONENT_TYPES.register("preserved", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    public static final RegistryObject<DataComponentType<SpectreBound>> SPECTRE_BOUND = DATA_COMPONENT_TYPES.register("spectre_bound", () -> DataComponentType.<SpectreBound>builder().persistent(SpectreBound.CODEC).networkSynchronized(SpectreBound.STREAM_CODEC).build());
    public static final RegistryObject<DataComponentType<CustomData>> BOTTLE_ENTITY_DATA = DATA_COMPONENT_TYPES.register("bottle_entity_data", () -> DataComponentType.<CustomData>builder().persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC).build());

}