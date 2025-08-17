package net.orcinus.galosphere.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SendParticlesPacket(BlockPos blockPos) implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, SendParticlesPacket> CODEC = CustomPacketPayload.codec(SendParticlesPacket::write, SendParticlesPacket::new);
    public static final Type<SendParticlesPacket> TYPE = CustomPacketPayload.createType("send_particles");

    private SendParticlesPacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    private void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
