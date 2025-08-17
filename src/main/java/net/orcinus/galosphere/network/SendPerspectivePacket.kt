package net.orcinus.galosphere.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.codec.StreamDecoder
import net.minecraft.network.codec.StreamMemberEncoder
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import java.util.*

@JvmRecord
data class SendPerspectivePacket(@JvmField val uuid: UUID, @JvmField val id: Int) : CustomPacketPayload
{
	constructor(buf: FriendlyByteBuf) : this(buf.readUUID(), buf.readInt())

	fun write(buf: FriendlyByteBuf)
	{
		buf.writeUUID(this.uuid)
		buf.writeInt(this.id)
	}

	override fun type () = TYPE

	companion object
	{
		val CODEC = CustomPacketPayload.codec(SendPerspectivePacket::write, ::SendPerspectivePacket)
		val TYPE = CustomPacketPayload.createType<SendPerspectivePacket>("send_perspective")
	}
}
