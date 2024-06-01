package net.alshanex.originsoverhaulmod.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.models.BalanzaBuenaModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BalanzaBuenaRenderer extends GeoEntityRenderer<BalanzaBuena> {
    public BalanzaBuenaRenderer(EntityRendererProvider.Context context) {
        super(context, new BalanzaBuenaModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BalanzaBuena animatable){
        return new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/balanza.png");
    }

    @Override
    public void render(BalanzaBuena animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
