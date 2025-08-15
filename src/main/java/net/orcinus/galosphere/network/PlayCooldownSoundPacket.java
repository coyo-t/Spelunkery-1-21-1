package net.orcinus.galosphere.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PlayCooldownSoundPacket() implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, PlayCooldownSoundPacket> CODEC = CustomPacketPayload.codec(PlayCooldownSoundPacket::write, PlayCooldownSoundPacket::new);
    public static final Type<PlayCooldownSoundPacket> TYPE = CustomPacketPayload.createType("play_cooldown_sound");

    public PlayCooldownSoundPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}