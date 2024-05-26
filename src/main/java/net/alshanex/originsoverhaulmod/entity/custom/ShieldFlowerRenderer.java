package net.alshanex.originsoverhaulmod.entity.custom;

import net.alshanex.originsoverhaulmod.entity.models.ShieldFlowerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShieldFlowerRenderer extends GeoEntityRenderer<ShieldFlower> {
    public ShieldFlowerRenderer(EntityRendererProvider.Context context) {
        super(context, new ShieldFlowerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldFlower animatable){
        return new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/shield_flower.png");
    }

    @Override
    public void render(ShieldFlower animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
