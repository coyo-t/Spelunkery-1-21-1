package net.orcinus.galosphere.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.StreamDecoder
import net.minecraft.network.codec.StreamMemberEncoder
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

@JvmRecord
data class BarometerPacket(@JvmField val weatherTicks: Int) : CustomPacketPayload
{
	constructor(buf: FriendlyByteBuf) : this(buf.readInt())

	override fun type () = TYPE

	companion object
	{
		val CODEC = CustomPacketPayload.codec({ o, f -> f.writeInt(o.weatherTicks) }, ::BarometerPacket)
		val TYPE = CustomPacketPayload.createType<BarometerPacket>("send_barometer_info")
	}
}