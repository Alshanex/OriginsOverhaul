package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.ShieldFlower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.List;
public class ShieldFlowerModel extends GeoModel<ShieldFlower>{
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/flor_escudo.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/shield_flower.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/flor_escudo.animation.json");

    @Override
    public ResourceLocation getModelResource(ShieldFlower object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(ShieldFlower mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(ShieldFlower animatable) {
        return animationResource;
    }
}
