package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.Caja;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CajaModel extends GeoModel<Caja> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/sample2.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/sample.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/sample2.animation.json");

    @Override
    public ResourceLocation getModelResource(Caja object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Caja mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Caja animatable) {
        return animationResource;
    }
}