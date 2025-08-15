package net.orcinus.galosphere.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.entities.SpectatorVision;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class SpectatorVisionRenderer extends EntityRenderer<SpectatorVision> {
    private static final Function<Integer, ResourceLocation> FUNCTION = integer -> Galosphere.id("textures/entity/spectator_vision/spectator_vision_" + integer + ".png");

    public SpectatorVisionRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SpectatorVision entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        float size = 1.5F;
        float sin = Mth.sin(entity.tickCount / 4.0F) / 16.0F;
        poseStack.scale(size, size, size);
        poseStack.translate(0, sin, 0);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity)));
        vertex(vertexConsumer, pose, i, 0.0f, 0, 0, 1);
        vertex(vertexConsumer, pose, i, 1.0f, 0, 1, 1);
        vertex(vertexConsumer, pose, i, 1.0f, 1, 1, 0);
        vertex(vertexConsumer, pose, i, 0.0f, 1, 0, 0);
        poseStack.popPose();
    }

    @Override
    protected int getBlockLightLevel(SpectatorVision entity, BlockPos blockPos) {
        return entity.getPhase();
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, int i, float f, int j, int k, int l) {
        vertexConsumer.addVertex(pose, f - 0.5f, (float)j - 0.5f, 0.0f).setColor(-1).setUv(k, l).setOverlay(OverlayTexture.NO_OVERLAY).setLight(i).setNormal(pose, 0.0f, 1.0f, 0.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(SpectatorVision entity) {
        return FUNCTION.apply(entity.getPhase() + 1);
    }
}