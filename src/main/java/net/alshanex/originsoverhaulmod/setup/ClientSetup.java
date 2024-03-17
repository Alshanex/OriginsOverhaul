package net.alshanex.originsoverhaulmod.setup;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.WaterSlashRenderer;
import net.alshanex.originsoverhaulmod.entity.custom.WaterCutRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OriginsOverhaulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.WATER_SLASH_ENTITY.get(), WaterSlashRenderer::new);
        event.registerEntityRenderer(ModEntities.WATER_CUT_PROJECTILE.get(), WaterCutRenderer::new);
    }
}
