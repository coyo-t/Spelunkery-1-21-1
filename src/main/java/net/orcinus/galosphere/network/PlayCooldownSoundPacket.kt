package net.orcinus.galosphere.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.orcinus.galosphere.Galosphere

class PlayCooldownSoundPacket() : CustomPacketPayload
{
	constructor(buf: FriendlyByteBuf) : this()

	fun write(buf: FriendlyByteBuf)
	{
	}

	override fun type () = TYPE

	companion object
	{
		@JvmField
		val CODEC = CustomPacketPayload.codec(PlayCooldownSoundPacket::write, ::PlayCooldownSoundPacket)
		@JvmField
		val TYPE = CustomPacketPayload.Type<PlayCooldownSoundPacket>(Galosphere.id("play_cooldown_sound"))
	}
}