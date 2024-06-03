package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.entity.models.CajaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CajaRenderer extends GeoEntityRenderer<Caja> {
    public CajaRenderer(EntityRendererProvider.Context context) {
        super(context, new CajaModel());
    }

    @Override
    public void render(Caja animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
