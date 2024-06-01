package net.alshanex.originsoverhaulmod.entity.custom;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.devour_jaw.DevourJaw;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;


import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

public class Balanza extends LivingEntity implements GeoEntity, AntiMagicSusceptible {

    @Nullable
    private LivingEntity owner;
    @Nullable
    private LivingEntity target;
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private UUID targetUUID;
    private float damage;
    private int age;
    private int hpBonus;

    public Balanza(EntityType<? extends Balanza> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public Balanza(Level level, LivingEntity owner, float damage, LivingEntity target, int hpBonus) {
        this(ModEntities.BALANZA.get(), level);
        setOwner(owner);
        setDamage(damage);
        setTarget(target);
        this.hpBonus = hpBonus;
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
    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (age > 260) {
                this.discard();
            }
            if (age >= 110 && age <= 200 && age % 20 ==0){
                LivingEntity target = getTarget();
                if(target != null){
                    DevourJaw devour = new DevourJaw(this.level(), getOwner(), target);
                    devour.setPos(target.position());
                    devour.setYRot(target.getYRot());
                    devour.setDamage(this.damage);
                    devour.vigorLevel = (this.hpBonus / 2) - 1;
                    this.level().addFreshEntity(devour);
                }
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

    @Nullable
    public LivingEntity getTarget() {
        if (this.target == null && this.targetUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel) this.level()).getEntity(this.targetUUID);
            if (entity instanceof LivingEntity) {
                this.target = (LivingEntity) entity;
            }
        }

        return this.target;
    }

    public void setOwner(@Nullable LivingEntity pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner == null ? null : pOwner.getUUID();
    }

    public void setTarget(@Nullable LivingEntity pTarget) {
        this.target = pTarget;
        this.targetUUID = pTarget == null ? null : pTarget.getUUID();
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
        if (age > 200) {
            controller.setAnimation(ANIMATION_RETREAT);
        } else if (age > 70 && age <= 200) {
            controller.setAnimation(ANIMATION_BAD);
        }

        return PlayState.CONTINUE;
    }

    private PlayState risePredicate(software.bernie.geckolib.core.animation.AnimationState event) {
        //if (age >= getDelay()) {
        var controller = event.getController();
        //if (controller.getAnimationState() == AnimationState.Stopped) {
        //}
        //IronsSpellbooks.LOGGER.debug("TentacleAnimOffset: {}", controller.tickOffset);
        if (age <= 70) {
            controller.setAnimation(ANIMATION_RISE);
            return PlayState.CONTINUE;
        } else
            return PlayState.STOP;


    }


    private final RawAnimation ANIMATION_RISE = RawAnimation.begin().thenPlay("animation.balanza.spawn");
    private final RawAnimation ANIMATION_RETREAT = RawAnimation.begin().thenPlay("animation.balanza.despawn");
    private final RawAnimation ANIMATION_BAD = RawAnimation.begin().thenPlay("animation.balanza.bad");

    private final AnimationController controller = new AnimationController(this, "balanza_controller", 20, this::animationPredicate);
    private final AnimationController riseController = new AnimationController(this, "balanza_rise_controller", 0, this::risePredicate);


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
