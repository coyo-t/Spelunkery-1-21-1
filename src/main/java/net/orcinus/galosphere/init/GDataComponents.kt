package net.orcinus.galosphere.init

import com.mojang.serialization.Codec
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere

object GDataComponents
{
	@JvmField
	val DATA_COMPONENT_TYPES =
		DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Galosphere.MODID)

	@JvmField
	val PRESERVED =
		DATA_COMPONENT_TYPES.register("preserved") { rs ->
			DataComponentType.builder<Boolean>().persistent(
				Codec.BOOL
			).networkSynchronized(ByteBufCodecs.BOOL).build()
		}
}