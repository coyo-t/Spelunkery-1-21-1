package net.orcinus.galosphere.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public class GByteBufCodecs {
    public static final StreamCodec<ByteBuf, UUID> UUID = new StreamCodec<>() {
        @Override
        public java.util.UUID decode(ByteBuf object) {
            return FriendlyByteBuf.readUUID(object);
        }

        @Override
        public void encode(ByteBuf object, java.util.UUID object2) {
            FriendlyByteBuf.writeUUID(object, object2);
        }
    };
}