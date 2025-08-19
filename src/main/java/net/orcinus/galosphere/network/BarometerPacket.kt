package net.orcinus.galosphere.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.StreamDecoder
import net.minecraft.network.codec.StreamMemberEncoder
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.orcinus.galosphere.Galosphere

@JvmRecord
data class BarometerPacket(@JvmField val weatherTicks: Int) : CustomPacketPayload
{
	constructor(buf: FriendlyByteBuf) : this(buf.readInt())

	override fun type () = TYPE

	companion object
	{
		@JvmField
		val CODEC = CustomPacketPayload.codec({ o, f -> f.writeInt(o.weatherTicks) }, ::BarometerPacket)
		@JvmField
		val TYPE = CustomPacketPayload.Type<BarometerPacket>(Galosphere.id("send_barometer_info"))
	}
}