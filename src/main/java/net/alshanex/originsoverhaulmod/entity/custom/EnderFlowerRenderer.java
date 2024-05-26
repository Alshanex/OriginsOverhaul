package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.models.EnderFlowerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EnderFlowerRenderer extends GeoEntityRenderer<EnderFlower> {
    public EnderFlowerRenderer(EntityRendererProvider.Context context) {
        super(context, new EnderFlowerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(EnderFlower animatable){
        return new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/ender_flower.png");
    }

    @Override
    public void render(EnderFlower animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
