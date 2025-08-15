package net.orcinus.galosphere.items.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.orcinus.galosphere.util.GByteBufCodecs;

import java.util.UUID;

public record SpectreBound(int id, UUID uuid) {
    public static final Codec<SpectreBound> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.POSITIVE_INT.fieldOf("spectre_bound_id").forGetter(SpectreBound::id),
            UUIDUtil.CODEC.fieldOf("spectre_bound_uuid").forGetter(SpectreBound::uuid)
    ).apply(instance, SpectreBound::new));
    public static final StreamCodec<ByteBuf, SpectreBound> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, SpectreBound::id, GByteBufCodecs.UUID, SpectreBound::uuid, SpectreBound::new);
}