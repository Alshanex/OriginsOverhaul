package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.EnderFlower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EnderFlowerModel extends GeoModel<EnderFlower> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/ender_flower.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/ender_flower.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/ender_flower.animation.json");

    @Override
    public ResourceLocation getModelResource(EnderFlower object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(EnderFlower mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(EnderFlower animatable) {
        return animationResource;
    }
}
