package net.alshanex.originsoverhaulmod.entity.custom;

import net.alshanex.originsoverhaulmod.entity.models.CrownModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrownRenderer extends GeoEntityRenderer<Crown> {
    public CrownRenderer(EntityRendererProvider.Context context) {
        super(context, new CrownModel());
    }
}
