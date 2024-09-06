package net.alshanex.originsoverhaulmod.setup;


import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
        event.put(ModEntities.FRIENDLY_WATCHER.get(),FriendlyWatcher.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 20.0).add(Attributes.MOVEMENT_SPEED, 0.2800000011920929).add(Attributes.ATTACK_DAMAGE, 5.0).add(Attributes.MAX_HEALTH, 25.0).add(Attributes.ARMOR, 5.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.5).build());
    }
}
