package net.alshanex.originsoverhaulmod.effect;

import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.entity.spells.LightningStrike;
import io.redspace.ironsspellbooks.network.spell.ClientboundParticleShockwave;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.setup.Messages;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MonarchEffect extends MagicMobEffect {
    public MonarchEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 60 == 0;
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        var radiusSqr = 200; //10

        entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10, 10, 10),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                livingEntity instanceof MagicSummon &&
                                ((MagicSummon) livingEntity).getSummoner() == entity
                )
                .forEach(targetEntity -> {
                    targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 62,1));
                    targetEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 62,2));
                    targetEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 62,2));
                });

        float radiusParticles = 6 + 6 * .75f;

        if(!entity.level().isClientSide){
            MagicManager.spawnParticles(entity.level(), new BlastwaveParticleOptions(SchoolRegistry.ICE.get().getTargetingColor(), radiusParticles), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
            Messages.sendToPlayersTrackingEntity(new ClientboundParticleShockwave(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radiusParticles, ModParticle.PHANTOM_WING_FLAME.get()), entity, true);
        }
    }

}
