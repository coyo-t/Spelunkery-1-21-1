package net.orcinus.galosphere.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BarometerPacket(int weatherTicks) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, BarometerPacket> CODEC = CustomPacketPayload.codec(BarometerPacket::write, BarometerPacket::new);
    public static final Type<BarometerPacket> TYPE = CustomPacketPayload.createType("send_barometer_info");

    public BarometerPacket(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.weatherTicks);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}