package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EvokerFangsModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;


public class WaterSlashRenderer extends EntityRenderer<WaterSlashEntity> {
    private final WaterSplashModel model;
    public WaterSlashRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new WaterSplashModel(pContext.bakeLayer(ModelLayers.EVOKER_FANGS));
    }
    public void render(WaterSlashEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        if (entity.tickCount < entity.waitTime)
            return;
        float f = entity.tickCount + partialTicks;
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot()));
        poseStack.scale(-1, -1, 1);
        poseStack.scale(1.85f, 1.85f, 1.85f);
        this.model.setupAnim(entity, f, 0.0F, 0.0F, entity.getYRot(), entity.getXRot());
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, multiBufferSource, light);
    }
    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation("originsoverhaulmod", path);
    }
    @Override
    public ResourceLocation getTextureLocation(WaterSlashEntity pEntity) {
        return id("textures/entity/water_splash.png");
    }
    static class WaterSplashModel extends EvokerFangsModel<WaterSlashEntity> {
        private final ModelPart root;
        private final ModelPart base;
        private final ModelPart upperJaw;
        private final ModelPart lowerJaw;

        public WaterSplashModel(ModelPart pRoot) {
            super(pRoot);
            this.root = pRoot;
            this.base = pRoot.getChild("base");
            this.upperJaw = pRoot.getChild("upper_jaw");
            this.lowerJaw = pRoot.getChild("lower_jaw");
        }

        @Override
        public void setupAnim(WaterSlashEntity entity, float time, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
            this.upperJaw.zRot = (float) Math.PI;
            this.lowerJaw.zRot = (float) Math.PI;

            this.upperJaw.y = -18F;
            this.lowerJaw.y = this.upperJaw.y;
            this.base.y = this.upperJaw.y;
        }

        /*@Override
        public void setupAnim(WaterSlashEntity entity, float time, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
            time -= entity.waitTime;
            float interval = entity.warmupTime - entity.waitTime;

            float f = Mth.clamp(time / interval, 0, 1);
            f = 1 - f * f * f * f;
            this.upperJaw.zRot = (float) Math.PI - f * 0.35F * (float) Math.PI;
            this.lowerJaw.zRot = (float) Math.PI + f * 0.35F * (float) Math.PI;

            float f2 = (time / interval);
            f2 = .5f * Mth.cos(.5f * Mth.PI * (f2 - 1)) + .5f;
            f2 *= f2;
            this.upperJaw.y = -18F * f2 + 16f;
            this.lowerJaw.y = this.upperJaw.y;
            this.base.y = this.upperJaw.y;
        }*/
    }
}
