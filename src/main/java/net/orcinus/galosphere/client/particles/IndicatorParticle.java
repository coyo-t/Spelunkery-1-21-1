package net.orcinus.galosphere.client.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.blocks.MonstrometerBlock;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class IndicatorParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    private static final Vector3f SAFE_COLOR = Vec3.fromRGB24(0xFFB219).toVector3f();
    private static final Vector3f UNSAFE_COLOR = Vec3.fromRGB24(0x93B9FF).toVector3f();

    public IndicatorParticle(ClientLevel world, double x, double y, double z, double pQuadSizeMulitiplier, SpriteSet sprites) {
        super(world, x, y, z);

        alpha = 1;
        quadSize = 0;
        lifetime = 48;

        rCol = getColor().x();
        gCol = getColor().y();
        bCol = getColor().z();

        setSpriteFromAge(this.sprites = sprites);
    }

    private Vector3f getColor() {
        return MonstrometerBlock.isUnsafe(level, BlockPos.containing(x, y, z)) ? UNSAFE_COLOR : SAFE_COLOR;
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;

        quadSize = Mth.lerp(0.06F, quadSize, 0.5F);

        rCol = Mth.lerp(0.25F, rCol, getColor().x());
        gCol = Mth.lerp(0.25F, gCol, getColor().y());
        bCol = Mth.lerp(0.25F, bCol, getColor().z());

        if (age++ >= lifetime) {
            remove();
        } else {
            if (age > (lifetime / 2)) {
                alpha -= 0.04F;
            }
        }

        setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer consumer, Camera camera, float delta) {
        this.renderRotatedQuad(consumer, camera, Axis.XP.rotation(Mth.PI / 2), delta);
        this.renderRotatedQuad(consumer, camera, Axis.XN.rotation(Mth.PI / 2), delta);
    }

    @Override
    protected int getLightColor(float tint) {
        return Math.max(50, super.getLightColor(tint));
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double speed, double pYSpeed, double pZSpeed) {
            return new IndicatorParticle(pLevel, pX, pY, pZ, speed, sprites);
        }
    }
}