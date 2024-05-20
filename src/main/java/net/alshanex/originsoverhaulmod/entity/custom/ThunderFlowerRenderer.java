package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.models.ThunderFlowerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThunderFlowerRenderer extends GeoEntityRenderer<ThunderFlower> {
    public ThunderFlowerRenderer(EntityRendererProvider.Context context) {
        super(context, new ThunderFlowerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ThunderFlower animatable){
        return new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/flor_trueno.png");
    }

    @Override
    public void render(ThunderFlower animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
