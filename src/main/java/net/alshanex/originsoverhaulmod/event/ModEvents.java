package net.alshanex.originsoverhaulmod.event;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.network.ClientboundSyncMana;
import io.redspace.ironsspellbooks.setup.Messages;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.registry.EffectRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = OriginsOverhaulMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if(event.getEntity() instanceof ServerPlayer){
                ServerPlayer player = (ServerPlayer) event.getEntity();
                if(player.hasEffect(EffectRegistry.MONARCH.get())){

                    List<LivingEntity> targetEntities = player.level().getEntitiesOfClass(LivingEntity.class,
                            player.getBoundingBox().inflate(20, 12, 20),
                            livingEntity -> livingEntity != player && livingEntity instanceof MagicSummon &&
                                    ((MagicSummon) livingEntity).getSummoner() == player
                    );

                    int numTargets = targetEntities.size();

                    if(numTargets > 0){
                        float damagePerEntity = event.getAmount() / numTargets;

                        event.setAmount(0);

                        targetEntities.forEach(targetEntity -> {
                            targetEntity.hurt(event.getSource(), damagePerEntity);
                        });
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event){
            if(event.getEntity() instanceof MagicSummon){
                MagicSummon summon = (MagicSummon) event.getEntity();
                if(summon.getSummoner() != null && summon.getSummoner() instanceof ServerPlayer){
                    ServerPlayer summoner = (ServerPlayer) summon.getSummoner();
                    MagicData magicData = MagicData.getPlayerMagicData(summoner);
                    if(summoner.hasEffect(EffectRegistry.MONARCH.get()) && magicData.getMana() > 10){
                        magicData.setMana(magicData.getMana() - 10);
                        Messages.sendToPlayer(new ClientboundSyncMana(magicData), summoner);
                        event.setCanceled(true);
                        event.getEntity().setHealth(event.getEntity().getMaxHealth());
                        if(event.getSource().getEntity() instanceof LivingEntity){
                            ((LivingEntity) event.getSource().getEntity()).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1));
                            ((LivingEntity) event.getSource().getEntity()).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                        }
                    }
                }
            }
        }
    }
}
