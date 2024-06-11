package net.alshanex.originsoverhaulmod.entity.custom;

import net.alshanex.originsoverhaulmod.entity.models.CrownModel;
import net.alshanex.originsoverhaulmod.entity.models.ParasyteModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ParasyteRenderer extends GeoEntityRenderer<Parasyte> {
    public ParasyteRenderer(EntityRendererProvider.Context context) {
        super(context, new ParasyteModel());
    }
}
