package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
public class WaterCutRenderer extends EntityRenderer<WaterCutProjectile>{
    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation("originsoverhaulmod", path);
    }
    private static ResourceLocation TEXTURE = id("textures/entity/water_cut.png");
    private static ResourceLocation TEXTURES[] = {
            new ResourceLocation("textures/particle/sweep_0.png"),
            new ResourceLocation("textures/particle/sweep_1.png"),
            new ResourceLocation("textures/particle/sweep_2.png"),
            new ResourceLocation("textures/particle/sweep_3.png"),
            new ResourceLocation("textures/particle/sweep_4.png"),
            new ResourceLocation("textures/particle/sweep_5.png"),
            new ResourceLocation("textures/particle/sweep_6.png"),
            new ResourceLocation("textures/particle/sweep_7.png")
    };

    public WaterCutRenderer(Context context) {
        super(context);
    }

    @Override
    public void render(WaterCutProjectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();

        Pose pose = poseStack.last();
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        entity.animationTime++;
        poseStack.mulPose(Axis.ZP.rotationDegrees(((entity.animationSeed % 30) - 15) * (float) Math.sin(entity.animationTime * .015)));

        //VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

        float oldWith = (float) entity.oldBB.getXsize();
        float width = entity.getBbWidth();
        width = oldWith + (width - oldWith) * Math.min(partialTicks, 1);
        //float halfWidth = width * .5f;
        //old color: 125, 0, 10

        //drawSlash(pose, bufferSource, light, width, 2);

        poseStack.mulPose(Axis.YP.rotationDegrees(-15));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-10));
        drawSlash(pose,entity, bufferSource, light, width, 4);

        poseStack.mulPose(Axis.YP.rotationDegrees(30));
        poseStack.mulPose(Axis.ZP.rotationDegrees(20));
        drawSlash(pose,entity, bufferSource, light, width, 0);

        poseStack.popPose();

        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    private void drawSlash(Pose pose, WaterCutProjectile entity, MultiBufferSource bufferSource, int light, float width, int offset) {
        Matrix4f poseMatrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity,offset)));
        float halfWidth = width * .5f;
        //old color: 125, 0, 10
        consumer.vertex(poseMatrix, -halfWidth, -.1f, -halfWidth).color(100, 150, 255, 255).uv(0f, 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, -.1f, -halfWidth).color(100, 150, 255, 255).uv(1f, 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, -.1f, halfWidth).color(100, 150, 255, 255).uv(1f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normalMatrix, 0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, -halfWidth, -.1f, halfWidth).color(100, 150, 255, 255).uv(0f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normalMatrix, 0f, 1f, 0f).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(WaterCutProjectile entity) {
        int frame = (entity.animationTime / 4) % TEXTURES.length;
        return TEXTURES[frame];
        //return TEXTURE;
    }

    private ResourceLocation getTextureLocation(WaterCutProjectile entity,int offset) {
        int frame = (entity.animationTime / 6 + offset) % TEXTURES.length;
        return TEXTURES[frame];
    }
}
