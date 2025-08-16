package net.orcinus.galosphere.items.components

import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ExtraCodecs
import net.orcinus.galosphere.util.GByteBufCodecs
import java.util.*

@JvmRecord
data class SpectreBound(val id: Int, @JvmField val uuid: UUID)
{
	companion object
	{
		val CODEC =
			RecordCodecBuilder.create {
				it.group(
					ExtraCodecs.POSITIVE_INT.fieldOf("spectre_bound_id").forGetter(SpectreBound::id),
					UUIDUtil.CODEC.fieldOf("spectre_bound_uuid").forGetter(SpectreBound::uuid)
				).apply(it, ::SpectreBound)
			}
		val STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.INT,
				SpectreBound::id,
				GByteBufCodecs.UUID,
				SpectreBound::uuid,
				::SpectreBound,
			)
	}
}