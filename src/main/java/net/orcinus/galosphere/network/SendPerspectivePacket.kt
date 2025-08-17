package net.orcinus.galosphere.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record SendPerspectivePacket(UUID uuid, int id) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, SendPerspectivePacket> CODEC = CustomPacketPayload.codec(SendPerspectivePacket::write, SendPerspectivePacket::new);
    public static final Type<SendPerspectivePacket> TYPE = CustomPacketPayload.createType("send_perspective");

    public SendPerspectivePacket(FriendlyByteBuf buf) {
        this(buf.readUUID(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
        buf.writeInt(this.id);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
