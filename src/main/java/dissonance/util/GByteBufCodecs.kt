package dissonance.util

import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import java.util.UUID

object GByteBufCodecs
{
	val UUID = object : StreamCodec<ByteBuf, UUID>
	{
		override fun decode(ob: ByteBuf) = FriendlyByteBuf.readUUID(ob)
		override fun encode(ob: ByteBuf, object2: UUID) = FriendlyByteBuf.writeUUID(ob, object2)
	}
}