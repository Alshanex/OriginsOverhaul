package net.alshanex.originsoverhaulmod.setup;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.FireFlowerRenderer;
import net.alshanex.originsoverhaulmod.entity.custom.ThunderFlowerRenderer;
import net.alshanex.originsoverhaulmod.entity.custom.EnderFlowerRenderer;
import net.alshanex.originsoverhaulmod.entity.custom.ShieldFlowerRenderer;
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
        event.registerEntityRenderer(ModEntities.FIRE_FLOWER.get(), FireFlowerRenderer::new);
        event.registerEntityRenderer(ModEntities.THUNDER_FLOWER.get(), ThunderFlowerRenderer::new);
        event.registerEntityRenderer(ModEntities.ENDER_FLOWER.get(), EnderFlowerRenderer::new);
        event.registerEntityRenderer(ModEntities.SHIELD_FLOWER.get(), ShieldFlowerRenderer::new);
    }
}
