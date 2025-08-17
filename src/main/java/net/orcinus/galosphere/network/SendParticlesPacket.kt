package net.orcinus.galosphere.network

import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

@JvmRecord
data class SendParticlesPacket(@JvmField val blockPos: BlockPos) : CustomPacketPayload
{
	private constructor(buf: FriendlyByteBuf) : this(buf.readBlockPos())

	private fun write(buf: FriendlyByteBuf)
	{
		buf.writeBlockPos(blockPos)
	}

	override fun type() = TYPE

	companion object
	{
		val CODEC = CustomPacketPayload.codec(SendParticlesPacket::write, ::SendParticlesPacket)
		val TYPE = CustomPacketPayload.createType<SendParticlesPacket>("send_particles")
	}
}
