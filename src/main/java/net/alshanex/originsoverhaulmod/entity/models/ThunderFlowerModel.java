package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.ThunderFlower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ThunderFlowerModel extends GeoModel<ThunderFlower> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/thunder_flower.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/flor_trueno.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/thunder_flower.animation.json");

    @Override
    public ResourceLocation getModelResource(ThunderFlower object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(ThunderFlower mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(ThunderFlower animatable) {
        return animationResource;
    }
}
