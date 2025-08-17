package net.orcinus.galosphere.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

class PlayCooldownSoundPacket() : CustomPacketPayload
{
	constructor(buf: FriendlyByteBuf) : this()

	fun write(buf: FriendlyByteBuf)
	{
	}

	override fun type () = TYPE

	companion object
	{
		val CODEC = CustomPacketPayload.codec(PlayCooldownSoundPacket::write, ::PlayCooldownSoundPacket)
		val TYPE = CustomPacketPayload.createType<PlayCooldownSoundPacket>("play_cooldown_sound")
	}
}