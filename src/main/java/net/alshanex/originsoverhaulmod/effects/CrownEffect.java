package net.alshanex.originsoverhaulmod.effects;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrownEffect extends MagicMobEffect {
    public CrownEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 40 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        var radiusSqr = 400; //20
        entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(20, 12, 20),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                !(livingEntity instanceof Player)&&
                                !DamageSources.isFriendlyFireBetween(livingEntity, entity) &&
                                Utils.hasLineOfSight(entity.level(), entity, livingEntity, false)
                )
                .forEach(targetEntity -> {
                    if (targetEntity instanceof Mob) { // Verifica si puede ser agresiva y atacar
                        Mob mobTarget = (Mob) targetEntity;
                        mobTarget.setTarget(entity); // Configura a `entity` como objetivo de ataque
                    }
                });
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }
}
