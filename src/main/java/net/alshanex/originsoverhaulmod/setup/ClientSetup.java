package net.alshanex.originsoverhaulmod.setup;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.*;
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
        event.registerEntityRenderer(ModEntities.CROWN.get(), CrownRenderer::new);
        event.registerEntityRenderer(ModEntities.BALANZA.get(), BalanzaRenderer::new);
        event.registerEntityRenderer(ModEntities.BALANZA_BUENA.get(), BalanzaBuenaRenderer::new);
        event.registerEntityRenderer(ModEntities.MUMMY.get(), MummyRenderer::new);
        event.registerEntityRenderer(ModEntities.CAJA.get(), CajaRenderer::new);
    }
}
