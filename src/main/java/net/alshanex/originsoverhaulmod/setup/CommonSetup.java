package net.alshanex.originsoverhaulmod.setup;


import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OriginsOverhaulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FIRE_FLOWER.get(), FireFlower.createLivingAttributes().build());
        event.put(ModEntities.THUNDER_FLOWER.get(), ThunderFlower.createLivingAttributes().build());
        event.put(ModEntities.ENDER_FLOWER.get(), EnderFlower.createLivingAttributes().build());
        event.put(ModEntities.SHIELD_FLOWER.get(), ShieldFlower.createLivingAttributes().build());
        event.put(ModEntities.BALANZA.get(), Balanza.createLivingAttributes().build());
        event.put(ModEntities.BALANZA_BUENA.get(), BalanzaBuena.createLivingAttributes().build());
        event.put(ModEntities.MUMMY.get(), Mummy.bakeAttributes().build());
        event.put(ModEntities.CAJA.get(), Caja.createLivingAttributes().build());
    }
}
