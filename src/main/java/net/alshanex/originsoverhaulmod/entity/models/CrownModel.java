package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.Crown;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrownModel extends GeoModel<Crown> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/spike.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/spike.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/spike.animation.json");

    public CrownModel(){

    }

    @Override
    public ResourceLocation getModelResource(Crown object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Crown mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Crown animatable) {
        return animationResource;
    }
}
