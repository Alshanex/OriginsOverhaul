package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.FireFlower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.List;

public class FireFlowerModel extends GeoModel<FireFlower>{
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/flor_fuego_anim_gecko.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/flor_animada.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/flor_fuego_anim.stand.animation.json");

    @Override
    public ResourceLocation getModelResource(FireFlower object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(FireFlower mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(FireFlower animatable) {
        return animationResource;
    }
}
