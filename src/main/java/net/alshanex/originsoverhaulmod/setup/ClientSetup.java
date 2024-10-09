package net.alshanex.originsoverhaulmod.setup;

import com.github.L_Ender.cataclysm.client.render.entity.*;
import com.github.L_Ender.lionfishapi.client.model.render.RendererKobolediator;
import com.github.alexthe666.iceandfire.client.render.entity.*;
import io.redspace.ironsspellbooks.entity.mobs.keeper.KeeperRenderer;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.minecraft.client.renderer.entity.*;
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
        event.registerEntityRenderer(ModEntities.SUMMONED_DREAD_BEAST.get(), RenderDreadBeast::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_DREAD_THRALL.get(), RenderDreadThrall::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_DREAD_KNIGHT.get(), RenderDreadKnight::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_DREAD_GHOUL.get(), RenderDreadGhoul::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_KOBOLETON.get(), Koboleton_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_CORAL_GOLEM.get(), Coral_Golem_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_CORALSSUS.get(), Coralssus_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_IGNITED_BERSERKER.get(), Ignited_Berserker_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_IGNITED_REVENANT.get(), Ignited_Revenant_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_AMETHYST_CRAB.get(), Amethyst_Crab_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_HYDRA.get(), RenderHydra::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_WADJET.get(), Wadjet_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_KOBOLEDIATOR.get(), Kobolediator_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_DRAUGR.get(), Draugr_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_ELITE_DRAUGR.get(), Elite_Draugr_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_ROYAL_DRAUGR.get(), Royal_Draugr_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_APTGANGR.get(), Aptrgangr_Renderer::new);
        event.registerEntityRenderer(ModEntities.SUMMONED_ENDER_GOLEM.get(), Ender_Golem_Renderer::new);
    }
}
