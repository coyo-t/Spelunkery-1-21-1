package net.orcinus.galosphere.init

import com.mojang.serialization.Codec
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.component.CustomData
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.items.components.SpectreBound
import java.util.function.Supplier

object GDataComponents
{
	@JvmField
	val DATA_COMPONENT_TYPES =
		DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Galosphere.MODID)

	@JvmField
	val EXPLOSION = DATA_COMPONENT_TYPES.register(
		"explosion",
		Supplier {
			DataComponentType.builder<Int>().persistent(ExtraCodecs.POSITIVE_INT)
				.networkSynchronized(ByteBufCodecs.VAR_INT).build()
		})
	@JvmField
	val DURATION = DATA_COMPONENT_TYPES.register(
		"duration",
		Supplier {
			DataComponentType.builder<Int>().persistent(ExtraCodecs.POSITIVE_INT)
				.networkSynchronized(ByteBufCodecs.VAR_INT).build()
		})
	@JvmField
	val BOUNCY = DATA_COMPONENT_TYPES.register(
		"bouncy",
		Supplier {
			DataComponentType.builder<Int>().persistent(ExtraCodecs.POSITIVE_INT)
				.networkSynchronized(ByteBufCodecs.VAR_INT).build()
		})
	@JvmField
	val PRESERVED =
		DATA_COMPONENT_TYPES.register("preserved", Supplier {
			DataComponentType.builder<Boolean>().persistent(
				Codec.BOOL
			).networkSynchronized(ByteBufCodecs.BOOL).build()
		})
	@JvmField
	val SPECTRE_BOUND =
		DATA_COMPONENT_TYPES.register(
			"spectre_bound",
			Supplier {
				DataComponentType.builder<SpectreBound>().persistent(SpectreBound.CODEC)
					.networkSynchronized(SpectreBound.STREAM_CODEC).build()
			})
	@JvmField
	val BOTTLE_ENTITY_DATA =
		DATA_COMPONENT_TYPES.register(
			"bottle_entity_data",
			Supplier {
				DataComponentType.builder<CustomData>().persistent(CustomData.CODEC)
					.networkSynchronized(CustomData.STREAM_CODEC).build()
			})
}