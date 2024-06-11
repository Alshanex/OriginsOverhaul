package net.alshanex.originsoverhaulmod.entity.custom;

import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Parasyte extends AbstractMagicProjectile implements GeoEntity{
    private UUID targetUUID;
    private Entity cachedTarget;
    private List<Entity> victims;
    private Vec3 startingPos;

    public Parasyte(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        victims = new ArrayList<>();

        this.setNoGravity(true);
    }

    public Parasyte(Level pLevel, LivingEntity owner, LivingEntity target, Vec3 startingPos) {
        this(ModEntities.PARASYTE.get(), pLevel);
        this.setOwner(owner);
        this.setTarget(target);
        this.startingPos = startingPos;
    }

    int airTime;

    public void setAirTime(int airTimeInTicks) {
        airTime = airTimeInTicks;
    }

    public void setTarget(@Nullable Entity pOwner) {
        if (pOwner != null) {
            this.targetUUID = pOwner.getUUID();
            this.cachedTarget = pOwner;
        }

    }

    @Nullable
    public Entity getTarget() {
        if (this.cachedTarget != null && !this.cachedTarget.isRemoved()) {
            return this.cachedTarget;
        } else if (this.targetUUID != null && this.level() instanceof ServerLevel) {
            this.cachedTarget = ((ServerLevel) this.level()).getEntity(this.targetUUID);
            return this.cachedTarget;
        } else {
            return null;
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.targetUUID != null) {
            tag.putUUID("Target", this.targetUUID);
        }
        if (this.airTime > 0) {
            tag.putInt("airTime", airTime);
        }
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("Target")) {
            this.targetUUID = tag.getUUID("Target");
        }
        if (tag.contains("airTime")) {
            this.airTime = tag.getInt("airTime");
        }
    }

    @Override
    public void trailParticles() {
        for (int i = 0; i < 1; i++) {
            Vec3 random = new Vec3(
                    Utils.getRandomScaled(this.getBbWidth() * .5f),
                    0,
                    Utils.getRandomScaled(this.getBbWidth() * .5f)
            );
            level().addParticle(ParticleTypes.ASH, getX() + random.x, getY(), getZ() + random.z, 0, -.05, 0);
        }
    }

    private void doFallingDamage(Entity target) {
        if (level().isClientSide)
            return;
        if (!canHitEntity(target) || victims.contains(target))
            return;
        boolean flag = DamageSources.applyDamage(target, getDamage() / 2, ExampleSpellRegistry.CONTROL.get().getDamageSource(this, getOwner()));
        if (flag) {
            victims.add(target);
            target.invulnerableTime = 0;
        }
        //Ironsspellbooks.logger.debug("IceBlockProjectile.doFallingDamage: {}", target.getName().getString());

    }

    private void doImpactDamage() {
        float explosionRadius = 3.5f;
        level().getEntities(this, this.getBoundingBox().inflate(explosionRadius)).forEach((entity) -> {
            if (canHitEntity(entity)) {
                double distance = entity.distanceToSqr(position());
                if (distance < explosionRadius * explosionRadius) {
                    double p = (1 - Math.pow(Math.sqrt(distance) / (explosionRadius), 3));
                    float damage = (float) (this.damage * p);
                    //Ironsspellbooks.logger.debug("IceBlockProjectile.doImpactDamage distance: {} p: {}", Math.sqrt(distance), p);

                    DamageSources.applyDamage(entity, damage, ExampleSpellRegistry.CONTROL.get().getDamageSource(this, getOwner()));
                }
            }

        });
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    @Override
    public void tick() {
        this.firstTick = false;
        xo = getX();
        yo = getY();
        zo = getZ();
        xOld = getX();
        yOld = getY();
        zOld = getZ();
        yRotO = getYRot();
        xRotO = getXRot();
        if (!level().isClientSide) {
            if(this.getTarget() == null){
                airTime = 0;
            }
            if (airTime <= 0) {
                //Falling
                if (onGround()) {
                    doImpactDamage();
                    impactParticles(getX(), getY(), getZ());
                    discard();
                } else {
                    level().getEntities(this, getBoundingBox().inflate(0.35)).forEach(this::doFallingDamage);
                }
                if(getOwner().isPassenger()){
                    getOwner().stopRiding();
                }
                getOwner().moveTo(this.startingPos);
            } else {
                if(!getOwner().isPassenger()){
                    getOwner().startRiding(getTarget());
                }
                if(getOwner() instanceof Player){
                    Player owner = (Player)getOwner();
                    owner.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY.get(),5,1));
                    owner.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,5,10));
                }
            }
            if (airTime-- > 0) {
                boolean tooHigh = false;
                this.setDeltaMovement(getDeltaMovement().multiply(.95f, .75f, .95f));
                if (getTarget() != null) {
                    var target = getTarget();
                    Vec3 diff = target.position().subtract(this.position());
                    if (diff.horizontalDistanceSqr() > 1) {
                        this.setDeltaMovement(getDeltaMovement().add(diff.multiply(1, 0, 1).normalize().scale(.025f)));
                    }
                    if (this.getY() > (target.getY() + target.getBbHeight() + 1)) {
                        tooHigh = true;
                    }
                } else {
                    if (airTime % 3 == 0) {
                        HitResult ground = Utils.raycastForBlock(level(), position(), position().subtract(0, 3.5, 0), ClipContext.Fluid.ANY);
                        if (ground.getType() == HitResult.Type.MISS) {
                            tooHigh = true;
                        } else if (Math.abs(position().y - ground.getLocation().y) < 4) {
                        }
                    }
                }
                if (tooHigh) {
                    this.setDeltaMovement(getDeltaMovement().add(0, -.005, 0));
                } else {
                    this.setDeltaMovement(getDeltaMovement().add(0, .01, 0));
                }
                if (airTime == 0) {
                    this.setDeltaMovement(0, 0.5, 0);
                }
            } else {
                this.setDeltaMovement(0, getDeltaMovement().y - .15, 0);
            }
        } else {
            trailParticles();
        }
        move(MoverType.SELF, getDeltaMovement());
    }

    @Override
    public void setXRot(float pXRot) {
//        IronsSpellbooks.LOGGER.debug("IceBlockProjectile: Something is trying to set my x rot! Ignoring.");
        //super.setXRot(pXRot);
    }

    @Override
    public void setYRot(float pYRot) {
//        super.setYRot(pYRot);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return pTarget != getOwner() && super.canHitEntity(pTarget);
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleTypes.ASH, x, y, z, 50, .8, .1, .8, 0.2, false);
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public Optional<SoundEvent> getImpactSound() {
        return Optional.empty();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
}
