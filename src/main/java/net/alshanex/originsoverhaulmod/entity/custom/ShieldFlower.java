package net.alshanex.originsoverhaulmod.entity.custom;

import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.particle.ShockwaveParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
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


public class ShieldFlower extends LivingEntity implements GeoEntity, AntiMagicSusceptible{

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private float damage;
    private int age;

    public ShieldFlower(EntityType<? extends ShieldFlower> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public ShieldFlower(Level level, LivingEntity owner, float damage) {
        this(ModEntities.SHIELD_FLOWER.get(), level);
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
    public boolean canCollideWith(Entity entity) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
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

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (age > 300) {
                //IronsSpellbooks.LOGGER.debug("Discarding void Tentacle (age:{})", age);
                this.discard();
            } else {
                if (age > 40 && age < 260 && age % 10 == 0) {
                    level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2d)).forEach(this::dealDamage);
                }
            }
            if (age == 260)
                playSound(SoundRegistry.VOID_TENTACLES_LEAVE.get(), 2, 1);
        } else {
            if(age < 40 || age > 279){
                clientDiggingParticles(this);
            }
            if (age < 280)
                if (Utils.random.nextFloat() < .15f)
                    level().addParticle(ParticleHelper.ACID, getX() + Utils.getRandomScaled(.5f), getY() + Utils.getRandomScaled(.5f) + .2f, getZ() + Utils.getRandomScaled(.5f), Utils.getRandomScaled(2f), -random.nextFloat() * .5f, Utils.getRandomScaled(2f));
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

    public boolean dealDamage(LivingEntity target) {
        if (target != getOwner())
            if (DamageSources.applyDamage(target, damage, ExampleSpellRegistry.SHIELD_FLOWER.get().getDamageSource(this, getOwner()))) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                return true;
            }
        return false;
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

    @Override
    public void onAntiMagic(MagicData playerMagicData) {
        MagicManager.spawnParticles(level(), ParticleTypes.SMOKE, getX(), getY() + 1, getZ(), 50, .2, 1.25, .2, .08, false);
        this.discard();
    }

    private PlayState animationPredicate(software.bernie.geckolib.core.animation.AnimationState event) {
        //if (age >= getDelay()) {
        var controller = event.getController();
        //if (controller.getAnimationState() == AnimationState.Stopped) {
        //}
        //IronsSpellbooks.LOGGER.debug("TentacleAnimOffset: {}", controller.tickOffset);
        if (age > 265) {
            controller.setAnimation(ANIMATION_RETREAT);
        } else if (controller.getAnimationState() == AnimationController.State.STOPPED) {
        }

        return PlayState.CONTINUE;
        //}

        //return PlayState.STOP;
    }

    private PlayState risePredicate(software.bernie.geckolib.core.animation.AnimationState event) {
        //if (age >= getDelay()) {
        var controller = event.getController();
        //if (controller.getAnimationState() == AnimationState.Stopped) {
        //}
        //IronsSpellbooks.LOGGER.debug("TentacleAnimOffset: {}", controller.tickOffset);
        if (age < 40) {
            controller.setAnimation(ANIMATION_RISE);
            return PlayState.CONTINUE;
        } else
            return PlayState.STOP;


    }


    private final RawAnimation ANIMATION_RISE = RawAnimation.begin().thenPlay("animation.flor_escudo.rise");
    private final RawAnimation ANIMATION_RETREAT = RawAnimation.begin().thenPlay("animation.flor_escudo.despawn");

    private final AnimationController controller = new AnimationController(this, "shield_flower_controller", 20, this::animationPredicate);
    private final AnimationController riseController = new AnimationController(this, "shield_flower_rise_controller", 0, this::risePredicate);


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
