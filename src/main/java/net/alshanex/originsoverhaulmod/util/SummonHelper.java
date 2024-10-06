package net.alshanex.originsoverhaulmod.util;

import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.SummonedSkeleton;
import io.redspace.ironsspellbooks.entity.mobs.SummonedVex;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;
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
import net.alshanex.originsoverhaulmod.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SummonHelper {

    public static boolean isSoulItem (ItemStack item){
        if(item.getItem() == ModItems.AMETHYST_CRAB_SOUL.get() || item.getItem() == ModItems.CORAL_GOLEM_SOUL.get()
                || item.getItem() == ModItems.CORALSSUS_SOUL.get() || item.getItem() == ModItems.DREAD_BEAST_SOUL.get()
                || item.getItem() == ModItems.DREAD_GHOUL_SOUL.get() || item.getItem() == ModItems.DREAD_KNIGHT_SOUL.get()
                || item.getItem() == ModItems.DREAD_THRALL_SOUL.get() || item.getItem() == ModItems.HYDRA_SOUL.get()
                || item.getItem() == ModItems.IGNITED_BERSERKER_SOUL.get() || item.getItem() == ModItems.IGNITED_REVENANT_SOUL.get()
                || item.getItem() == ModItems.KOBOLEDIATOR_SOUL.get() || item.getItem() == ModItems.KOBOLETON_SOUL.get()
                || item.getItem() == ModItems.SKELETON_SOUL.get() || item.getItem() == ModItems.WADJET_SOUL.get()
                || item.getItem() == ModItems.ZOMBIE_SOUL.get() || item.getItem() == ModItems.VEX_SOUL.get()){
            return true;
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
        } else {
            return new SummonedSkeleton(world, summoner, true);
        }
    }
}
