package net.alshanex.originsoverhaulmod.entity.models;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.BalanzaBuena;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BalanzaBuenaModel extends GeoModel<BalanzaBuena> {
    public static final ResourceLocation modelResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "geo/balanza.geo.json");
    public static final ResourceLocation textureResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "textures/entity/balanza.png");
    public static final ResourceLocation animationResource = new ResourceLocation(OriginsOverhaulMod.MOD_ID, "animations/balanza.animation.json");

    @Override
    public ResourceLocation getModelResource(BalanzaBuena object) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(BalanzaBuena mob) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(BalanzaBuena animatable) {
        return animationResource;
    }
}
