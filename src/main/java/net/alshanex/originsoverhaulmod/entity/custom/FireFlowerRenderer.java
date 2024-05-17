package net.alshanex.originsoverhaulmod.entity.custom;


import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.models.FireFlowerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FireFlowerRenderer extends GeoEntityRenderer<FireFlower>{

    public FireFlowerRenderer(EntityRendererProvider.Context context) {
        super(context, new FireFlowerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FireFlower animatable){
        return new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/flor_animada.png");
    }

    @Override
    public void render(FireFlower animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
