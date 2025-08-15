package dissonance.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.galosphere.init.GMobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    @Shadow protected abstract void setModelProperties(AbstractClientPlayer p_117819_);

    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float f) {
        super(context, model, f);
    }

    @Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
    private void G$renderHand(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, ModelPart modelPart, ModelPart modelPart2, CallbackInfo ci) {
        ResourceLocation resourceLocation = abstractClientPlayer.getSkin().texture();
        if (abstractClientPlayer.hasEffect(GMobEffects.ASTRAL.getHolder().orElseThrow())) {
            PlayerModel<AbstractClientPlayer> playerModel = this.getModel();
            this.setModelProperties(abstractClientPlayer);
            playerModel.attackTime = 0.0f;
            playerModel.crouching = false;
            playerModel.swimAmount = 0.0f;
            playerModel.setupAnim(abstractClientPlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            modelPart.xRot = 0.0f;
            int newAlpha = FastColor.ARGB32.colorFromFloat(0.35F, 1.0F, 1.0F, 1.0F);
            modelPart.render(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(resourceLocation)), i, OverlayTexture.NO_OVERLAY, newAlpha);
            modelPart2.xRot = 0.0f;
            modelPart2.render(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(resourceLocation)), i, OverlayTexture.NO_OVERLAY, newAlpha);
            ci.cancel();
        }
    }

}
