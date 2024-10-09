package net.alshanex.originsoverhaulmod.util;


import io.github.edwinmindcraft.apoli.api.ApoliAPI;
import io.github.edwinmindcraft.apoli.api.component.IPowerContainer;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.SummonedSkeleton;
import io.redspace.ironsspellbooks.entity.mobs.SummonedVex;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;
import net.alshanex.originsoverhaulmod.entity.custom.summons.common.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.rare.*;
import net.alshanex.originsoverhaulmod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SummonHelper {

    public static boolean isSoulItem (ItemStack item){
        if(item.getItem() == ModItems.AMETHYST_CRAB_SOUL.get() || item.getItem() == ModItems.CORAL_GOLEM_SOUL.get()
                || item.getItem() == ModItems.CORALSSUS_SOUL.get() || item.getItem() == ModItems.DREAD_BEAST_SOUL.get()
                || item.getItem() == ModItems.DREAD_GHOUL_SOUL.get() || item.getItem() == ModItems.DREAD_KNIGHT_SOUL.get()
                || item.getItem() == ModItems.DREAD_THRALL_SOUL.get() || item.getItem() == ModItems.HYDRA_SOUL.get()
                || item.getItem() == ModItems.IGNITED_BERSERKER_SOUL.get() || item.getItem() == ModItems.IGNITED_REVENANT_SOUL.get()
                || item.getItem() == ModItems.KOBOLEDIATOR_SOUL.get() || item.getItem() == ModItems.KOBOLETON_SOUL.get()
                || item.getItem() == ModItems.SKELETON_SOUL.get() || item.getItem() == ModItems.WADJET_SOUL.get()
                || item.getItem() == ModItems.ZOMBIE_SOUL.get() || item.getItem() == ModItems.VEX_SOUL.get()
                || item.getItem() == ModItems.DRAUGR_SOUL.get() || item.getItem() == ModItems.ELITE_DRAUGR_SOUL.get()
                || item.getItem() == ModItems.ROYAL_DRAUGR_SOUL.get() || item.getItem() == ModItems.APTGANGR_SOUL.get()
                || item.getItem() == ModItems.ENDER_GOLEM_SOUL.get()){
            return true;
        } else { return false;}
    }

    public static boolean isLegendarySoul (ItemStack item){
        if(item.getItem() == ModItems.AMETHYST_CRAB_SOUL.get() || item.getItem() == ModItems.CORALSSUS_SOUL.get()
                || item.getItem() == ModItems.HYDRA_SOUL.get() || item.getItem() == ModItems.KOBOLEDIATOR_SOUL.get()
                || item.getItem() == ModItems.WADJET_SOUL.get() || item.getItem() == ModItems.APTGANGR_SOUL.get()
                || item.getItem() == ModItems.ENDER_GOLEM_SOUL.get()){
            return true;
        } else { return false;}
    }

    public static boolean isEpicSoul (ItemStack item){
        if(item.getItem() == ModItems.CORAL_GOLEM_SOUL.get()|| item.getItem() == ModItems.IGNITED_BERSERKER_SOUL.get()
                || item.getItem() == ModItems.IGNITED_REVENANT_SOUL.get()){
            return true;
        } else { return false;}
    }

    public static boolean isRareSoul (ItemStack item){
        if(item.getItem() == ModItems.DREAD_KNIGHT_SOUL.get()|| item.getItem() == ModItems.DREAD_THRALL_SOUL.get()
                || item.getItem() == ModItems.VEX_SOUL.get() || item.getItem() == ModItems.ELITE_DRAUGR_SOUL.get()
                || item.getItem() == ModItems.ROYAL_DRAUGR_SOUL.get()){
            return true;
        } else { return false;}
    }

    private static float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    public static boolean hasLegendarySummoned(LivingEntity entity){
        var radiusSqr = 1000; //50
        return entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(50, 50, 50),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                livingEntity instanceof MagicSummon &&
                                ((MagicSummon) livingEntity).getSummoner() == entity
                )
                .stream()
                .anyMatch(targetEntity -> targetEntity instanceof AmethystCrabSummon ||
                        targetEntity instanceof WadjetSummon ||
                        targetEntity instanceof KobolediatorSummon ||
                        targetEntity instanceof HydraSummon ||
                        targetEntity instanceof CoralssusSummon ||
                        targetEntity instanceof AptgangrSummon ||
                        targetEntity instanceof EnderGolemSummon);
    }

    public static boolean hasEpicsSummoned(LivingEntity entity){
        var radiusSqr = 1000; //50
        long count = entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(50, 50, 50),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                livingEntity instanceof MagicSummon &&
                                ((MagicSummon) livingEntity).getSummoner() == entity
                )
                .stream()
                .filter(targetEntity -> targetEntity instanceof CoralGolemSummon ||
                        targetEntity instanceof IgnitedBerserkerSummon ||
                        targetEntity instanceof IgnitedRevenantSummon)
                .count();

        return count == 2;
    }

    public static boolean isNecromancer(LivingEntity entity){
        IPowerContainer powerContainer = ApoliAPI.getPowerContainer(entity);
        return powerContainer != null && powerContainer.hasPower(new ResourceLocation("medievalorigins","revenant/can_summon_souls"));
    }

    public static boolean isDeathCaller(LivingEntity entity){
        IPowerContainer powerContainer = ApoliAPI.getPowerContainer(entity);
        return powerContainer != null && powerContainer.hasPower(new ResourceLocation("medievalorigins","death_caller/reset_cds"));
    }

    public static long getCurrentSummons (LivingEntity entity){
        var radiusSqr = 1000; //50
        int summonsCount = 0;
        List<LivingEntity> summons = entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(50, 50, 50),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                livingEntity instanceof MagicSummon &&
                                ((MagicSummon) livingEntity).getSummoner() == entity
                );

        for (LivingEntity summon : summons) {
            if (summon instanceof CoralGolemSummon || summon instanceof IgnitedRevenantSummon
                    || summon instanceof IgnitedBerserkerSummon) {
                summonsCount += 4;
            } else if (summon instanceof AmethystCrabSummon || summon instanceof WadjetSummon
                        || summon instanceof KobolediatorSummon || summon instanceof HydraSummon
                        || summon instanceof CoralssusSummon || summon instanceof AptgangrSummon
                        || summon instanceof EnderGolemSummon) {
                summonsCount += 10;
            } else if (summon instanceof DreadKnightSummon || summon instanceof DreadThrallSummon
                    || summon instanceof SummonedVex || summon instanceof EliteDraugrSummon
                    || summon instanceof RoyalDraugrSummon) {
                summonsCount += 2;
            } else {
                summonsCount += 1;
            }
        }

        return summonsCount;
    }

    public static boolean canSummon(LivingEntity entity, ItemStack item){
        if(isNecromancer(entity)){
            long currentSummons = getCurrentSummons(entity);
            if(currentSummons < 15){
                if(isRareSoul(item)){
                    if(currentSummons < 14){
                        return true;
                    } else { return false;}
                } else if (isEpicSoul(item)){
                    if(currentSummons < 12 && !hasEpicsSummoned(entity)){
                        return true;
                    } else { return false;}
                } else if (isLegendarySoul(item)){
                    if(currentSummons < 6 && !hasLegendarySummoned(entity)){
                        return true;
                    } else { return false;}
                } else { return true;}
            } else { return false;}
        } else { return false;}
    }

    public static Monster getSummonType (ItemStack item, Level world, LivingEntity summoner){
        if(item.getItem() == ModItems.AMETHYST_CRAB_SOUL.get()){
            return new AmethystCrabSummon(world, summoner);
        } else if (item.getItem() == ModItems.CORAL_GOLEM_SOUL.get()){
            return new CoralGolemSummon(world, summoner);
        } else if (item.getItem() == ModItems.CORALSSUS_SOUL.get()){
            return new CoralssusSummon(world, summoner);
        } else if (item.getItem() == ModItems.DREAD_THRALL_SOUL.get()){
            return new DreadThrallSummon(world, summoner);
        } else if (item.getItem() == ModItems.DREAD_KNIGHT_SOUL.get()){
            return new DreadKnightSummon(world, summoner);
        } else if (item.getItem() == ModItems.DREAD_GHOUL_SOUL.get()){
            return new DreadGhoulSummon(world, summoner);
        } else if (item.getItem() == ModItems.DREAD_BEAST_SOUL.get()){
            return new DreadBeastSummon(world, summoner);
        } else if (item.getItem() == ModItems.ZOMBIE_SOUL.get()){
            return new SummonedZombie(world, summoner, true);
        } else if (item.getItem() == ModItems.VEX_SOUL.get()){
            return new SummonedVex(world, summoner);
        } else if (item.getItem() == ModItems.HYDRA_SOUL.get()){
            return new HydraSummon(world, summoner);
        } else if (item.getItem() == ModItems.IGNITED_REVENANT_SOUL.get()){
            return new IgnitedRevenantSummon(world, summoner);
        } else if (item.getItem() == ModItems.IGNITED_BERSERKER_SOUL.get()){
            return new IgnitedBerserkerSummon(world, summoner);
        } else if (item.getItem() == ModItems.KOBOLETON_SOUL.get()){
            return new KoboletonSummon(world, summoner);
        } else if (item.getItem() == ModItems.KOBOLEDIATOR_SOUL.get()){
            return new KobolediatorSummon(world, summoner);
        } else if (item.getItem() == ModItems.WADJET_SOUL.get()){
            return new WadjetSummon(world, summoner);
        } else if (item.getItem() == ModItems.DRAUGR_SOUL.get()){
            return new DraugrSummon(world, summoner);
        } else if (item.getItem() == ModItems.ELITE_DRAUGR_SOUL.get()){
            return new EliteDraugrSummon(world, summoner);
        } else if (item.getItem() == ModItems.ROYAL_DRAUGR_SOUL.get()){
            return new RoyalDraugrSummon(world, summoner);
        } else if (item.getItem() == ModItems.APTGANGR_SOUL.get()){
            return new AptgangrSummon(world, summoner);
        } else if (item.getItem() == ModItems.ENDER_GOLEM_SOUL.get()){
            return new EnderGolemSummon(world, summoner);
        } else {
            return new SummonedSkeleton(world, summoner, true);
        }
    }
}
