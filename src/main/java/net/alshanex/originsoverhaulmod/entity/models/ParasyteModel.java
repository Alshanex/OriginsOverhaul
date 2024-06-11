package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.Crown;
import net.alshanex.originsoverhaulmod.entity.custom.Parasyte;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ParasyteModel extends GeoModel<Parasyte> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/invisible.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/transparent.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/invisible.animation.json");

    public ParasyteModel(){

    }

    @Override
    public ResourceLocation getModelResource(Parasyte object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(Parasyte mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(Parasyte animatable) {
        return animationResource;
    }
}
