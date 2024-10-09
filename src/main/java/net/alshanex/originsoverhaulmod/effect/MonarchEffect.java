package net.alshanex.originsoverhaulmod.effect;

import com.github.L_Ender.cataclysm.init.ModParticle;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.mobs.MagicSummon;
import io.redspace.ironsspellbooks.network.spell.ClientboundParticleShockwave;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.setup.Messages;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import java.util.Random;

@Mod.EventBusSubscriber
public class MonarchEffect extends MagicMobEffect {
    public MonarchEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    private static final Random RANDOM = new Random();

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
                    targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 22,1,false,false));
                    targetEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 22,2,false,false));

                    if(!entity.level().isClientSide){
                        Messages.sendToPlayersTrackingEntity(new ClientboundParticleShockwave(new Vec3(targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight()+0.3, targetEntity.getZ()), targetEntity.getBbWidth()/2, ModParticle.PHANTOM_WING_FLAME.get()), entity, true);
                    }
                });

        var radius = 10;

        if(entity.level().isClientSide){
            for (int i = 0; i < 30; i++) {
                double randomDistance = radius * RANDOM.nextDouble();
                double randomAngle = 2 * Math.PI * RANDOM.nextDouble();

                double x = entity.getX() + randomDistance * Math.cos(randomAngle);
                double z = entity.getZ() + randomDistance * Math.sin(randomAngle);
                double y = entity.getY() + 0.3;

                entity.level().addParticle(ParticleTypes.SOUL, x, y, z, 0, 0, 0);
            }
        }
    }

}
