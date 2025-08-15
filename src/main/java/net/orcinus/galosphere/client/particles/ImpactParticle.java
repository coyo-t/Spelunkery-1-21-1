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
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ImpactParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public ImpactParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet sprites) {
        super(clientLevel, d, e, f);
        this.alpha = 0.8F;
        this.quadSize = 1.3F;
        this.lifetime = 48;
        this.sprites = sprites;
        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        quadSize = Mth.lerp(0.25F, quadSize, 6);
        if (age++ >= lifetime) {
            remove();
        } else {
            alpha = Mth.lerp(0.08F, alpha, 0);
        }
        if (alpha <= 0.01F) remove();
        setSpriteFromAge(sprites);
    }

    @Override
    public void render(VertexConsumer consumer, Camera camera, float delta) {
        this.renderRotatedQuad(consumer, camera, Axis.XP.rotation(Mth.PI / 2), delta);
        this.renderRotatedQuad(consumer, camera, Axis.XN.rotation(Mth.PI / 2), delta);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new ImpactParticle(clientLevel, d, e, f, this.sprites);
        }
    }

}