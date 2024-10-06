package net.alshanex.originsoverhaulmod.setup;


import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.common.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.CoralGolemSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.CoralssusSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.IgnitedBerserkerSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.IgnitedRevenantSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.AmethystCrabSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.HydraSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.KobolediatorSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.WadjetSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.rare.*;
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
        //event.put(ModEntities.FRIENDLY_WATCHER.get(),FriendlyWatcher.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 20.0).add(Attributes.MOVEMENT_SPEED, 0.2800000011920929).add(Attributes.ATTACK_DAMAGE, 5.0).add(Attributes.MAX_HEALTH, 25.0).add(Attributes.ARMOR, 5.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.5).build());
        event.put(ModEntities.SUMMONED_DREAD_BEAST.get(), DreadBeastSummon.bakeAttributes().build());
        event.put(ModEntities.SUMMONED_DREAD_GHOUL.get(), DreadGhoulSummon.bakeAttributes().build());
        event.put(ModEntities.SUMMONED_KOBOLETON.get(), KoboletonSummon.koboleton().build());
        event.put(ModEntities.SUMMONED_CORAL_GOLEM.get(), CoralGolemSummon.coralgolem().build());
        event.put(ModEntities.SUMMONED_CORALSSUS.get(), CoralssusSummon.coralssus().build());
        event.put(ModEntities.SUMMONED_IGNITED_REVENANT.get(), IgnitedRevenantSummon.ignited_revenant().build());
        event.put(ModEntities.SUMMONED_IGNITED_BERSERKER.get(), IgnitedBerserkerSummon.ignited_berserker().build());
        event.put(ModEntities.SUMMONED_AMETHYST_CRAB.get(), AmethystCrabSummon.amethyst_crab().build());
        event.put(ModEntities.SUMMONED_HYDRA.get(), HydraSummon.bakeAttributes().build());
        event.put(ModEntities.SUMMONED_KOBOLEDIATOR.get(), KobolediatorSummon.kobolediator().build());
        event.put(ModEntities.SUMMONED_WADJET.get(), WadjetSummon.wadjet().build());
        event.put(ModEntities.SUMMONED_DREAD_KNIGHT.get(), DreadKnightSummon.bakeAttributes().build());
        event.put(ModEntities.SUMMONED_DREAD_THRALL.get(), DreadThrallSummon.bakeAttributes().build());














    }
}
