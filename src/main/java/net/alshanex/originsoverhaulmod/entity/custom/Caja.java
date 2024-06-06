package net.alshanex.originsoverhaulmod.entity.custom;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.LightningStrike;
import io.redspace.ironsspellbooks.entity.spells.devour_jaw.DevourJaw;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.registry.EffectRegistry;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

public class Caja extends LivingEntity implements GeoEntity {

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private float damage;
    private int age;

    public Caja(EntityType<? extends Caja> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public Caja(Level level, LivingEntity owner, float damage) {
        this(ModEntities.CAJA.get(), level);
        setOwner(owner);
        setDamage(damage);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    protected void clientDiggingParticles(LivingEntity livingEntity) {
        RandomSource randomsource = livingEntity.getRandom();
        BlockState blockstate = livingEntity.getBlockStateOn();
        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            for (int i = 0; i < 15; ++i) {
                double d0 = livingEntity.getX() + (double) Mth.randomBetween(randomsource, -0.5F, 0.5F);
                double d1 = livingEntity.getY();
                double d2 = livingEntity.getZ() + (double) Mth.randomBetween(randomsource, -0.5F, 0.5F);
                livingEntity.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    private static final ParticleEmitterInfo SANDSTORM = new ParticleEmitterInfo(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "HolySandstorm"));
    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (age > 520) {
                this.discard();
            }
            if (age >= 60 && age < 400 && age%10 == 0){
                LivingEntity owner = getOwner();
                owner.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,45,1));
                owner.addEffect(new MobEffectInstance(EffectRegistry.MUMMY_EFFECT.get(), 45, 1, false, false, true));
                var radiusSqr = 600; //30
                this.level().getEntitiesOfClass(Mummy.class, this.getBoundingBox().inflate(20, 12, 20),
                                mummyEntity -> horizontalDistanceSqr(mummyEntity, this) < radiusSqr)
                        .forEach(targetEntity -> {
                            if(age%40 == 0){
                                AAALevel.addParticle(this.level(),false,SANDSTORM.clone().position(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ()));
                                targetEntity.setSandstorm(true);
                            }

                            if(targetEntity.inSandstorm){
                                var radiusSqrSec = 60; //3
                                targetEntity.level().getEntitiesOfClass(LivingEntity.class, targetEntity.getBoundingBox().inflate(8, 20, 8),
                                                livingEntity -> livingEntity != targetEntity &&
                                                        !(livingEntity instanceof Mummy) &&
                                                        !(livingEntity instanceof Caja) &&
                                                        livingEntity != getOwner() &&
                                                        horizontalDistanceSqr(livingEntity, targetEntity) < radiusSqrSec &&
                                                        livingEntity.isPickable() &&
                                                        !livingEntity.isSpectator()
                                        )
                                        .forEach(target -> {
                                            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,20,1));
                                            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20,1));
                                            DamageSources.applyDamage(target, damage/3, ExampleSpellRegistry.MUMMY.get().getDamageSource(targetEntity, getOwner()));
                                            Vec3 lookDirection = target.getLookAngle();
                                            Vec3 knockbackDirection = new Vec3(-lookDirection.x, 0, -lookDirection.z).normalize();
                                            target.setDeltaMovement(target.getDeltaMovement().add(knockbackDirection.scale(1)));
                                        });
                            }
                        });
            }
            if(age >= 120 && age <= 430 && age%40==30){
                var radiusSqr = 600; //30
                this.level().getEntitiesOfClass(Mummy.class, this.getBoundingBox().inflate(20, 12, 20),
                                mummyEntity -> horizontalDistanceSqr(mummyEntity, this) < radiusSqr)
                        .forEach(targetEntity -> {
                            if(targetEntity.inSandstorm){
                                LivingEntity owner = getOwner();
                                targetEntity.remove(Entity.RemovalReason.KILLED);
                                owner.heal(owner.getMaxHealth()*0.05f);

                                if(age > 60){
                                    if(age-20 < 60){
                                        age = 60;
                                    } else {
                                        age-=20;
                                    }
                                }
                            }
                        });
            }
            if (age == 470 || age == 0)
                playSound(SoundRegistry.VOID_TENTACLES_LEAVE.get(), 2, 1);
        } else {
            if(age < 30 || age > 469){
                clientDiggingParticles(this);
            }
        }
        age++;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return false;
        return super.hurt(pSource, pAmount);
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel) this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity) entity;
            }
        }

        return this.owner;
    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.age = pCompound.getInt("Age");
        if (pCompound.hasUUID("Owner")) {
            this.ownerUUID = pCompound.getUUID("Owner");
        }
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.singleton(ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Age", this.age);
        if (this.ownerUUID != null) {
            pCompound.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    private PlayState animationPredicate(software.bernie.geckolib.core.animation.AnimationState event) {

        var controller = event.getController();

        if (age > 419) {
            controller.setAnimation(ANIMATION_RETREAT);
        }  else if (controller.getAnimationState() == AnimationController.State.STOPPED) {
            controller.setAnimation(ANIMATION_IDLE);
        }

        return PlayState.CONTINUE;
    }

    private PlayState risePredicate(software.bernie.geckolib.core.animation.AnimationState event) {

        var controller = event.getController();

        if (age < 30) {
            controller.setAnimation(ANIMATION_RISE);
            return PlayState.CONTINUE;
        } else
            return PlayState.STOP;


    }


    private final RawAnimation ANIMATION_RISE = RawAnimation.begin().thenPlay("animation.sample2.spawn");
    private final RawAnimation ANIMATION_RETREAT = RawAnimation.begin().thenPlay("animation.sample2.despawn");
    private final RawAnimation ANIMATION_IDLE = RawAnimation.begin().thenPlay("animation.sample2.stand");

    private final AnimationController controller = new AnimationController(this, "sample_controller", 20, this::animationPredicate);
    private final AnimationController riseController = new AnimationController(this, "sample_rise_controller", 0, this::risePredicate);


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(riseController);
        controllerRegistrar.add(controller);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
}
