package net.alshanex.originsoverhaulmod.entity.custom;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.EchoingStrikeEntity;
import io.redspace.ironsspellbooks.entity.spells.LightningStrike;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
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
public class EnderFlower extends LivingEntity implements GeoEntity, AntiMagicSusceptible{
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private float damage;
    private int age;

    public EnderFlower(EntityType<? extends EnderFlower> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public EnderFlower(Level level, LivingEntity owner, float damage) {
        this(ModEntities.ENDER_FLOWER.get(), level);
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

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (age > 300) {
                this.discard();
            }
            if(age > 20 && age < 260 && age % 20 == 0){
                EchoingStrikeEntity echo = new EchoingStrikeEntity(this.level(), this, 0, 3f);
                echo.setPos(this.getBoundingBox().getCenter().subtract(0, echo.getBbHeight() * .5f, 0));
                this.level().addFreshEntity(echo);
                var radiusSqr = 200; //10
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20, 12, 20),
                                livingEntity -> livingEntity != this &&
                                        horizontalDistanceSqr(livingEntity, this) < radiusSqr &&
                                        livingEntity.isPickable() &&
                                        !livingEntity.isSpectator() &&
                                        livingEntity != this.owner &&
                                        !DamageSources.isFriendlyFireBetween(livingEntity, this) &&
                                        Utils.hasLineOfSight(this.level(), this, livingEntity, false)
                        )
                        .forEach(targetEntity -> {
                            Vec3 dest = findTeleportLocation(this.level(), targetEntity, 22);
                            targetEntity.teleportTo(dest.x, dest.y, dest.z);
                        });
            }
            if (age == 260)
                playSound(SoundRegistry.VOID_TENTACLES_LEAVE.get(), 2, 1);
        } else {
            if(age < 20 || age > 279){
                clientDiggingParticles(this);
            }
        }
        age++;
    }

    public static Vec3 findTeleportLocation(Level level, LivingEntity entity, float maxDistance) {

        var blockHitResult = Utils.getTargetBlock(level, entity, ClipContext.Fluid.NONE, maxDistance);
        var pos = blockHitResult.getBlockPos();

        Vec3 bbOffset = entity.getForward().normalize().multiply(entity.getBbWidth() / 3, 0, entity.getBbHeight() / 3);
        Vec3 bbImpact = blockHitResult.getLocation().subtract(bbOffset);
        //        Vec3 lower = level.clip(new ClipContext(start, start.add(0, maxSteps * -2, 0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null)).getLocation();
        int ledgeY = (int) level.clip(new ClipContext(Vec3.atBottomCenterOf(pos).add(0, 3, 0), Vec3.atBottomCenterOf(pos), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null)).getLocation().y;
        boolean isAir = level.getBlockState(new BlockPos(new Vec3i(pos.getX(), ledgeY, pos.getZ()))).isAir();
        boolean los = level.clip(new ClipContext(bbImpact, bbImpact.add(0, ledgeY - pos.getY(), 0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getType() == HitResult.Type.MISS;

        if (isAir && los && Math.abs(ledgeY - pos.getY()) <= 3) {
            Vec3 correctedPos = new Vec3(pos.getX(), ledgeY, pos.getZ());
            return correctedPos.add(0.5, 0.076, 0.5);
        } else {
            return level.clip(new ClipContext(bbImpact, bbImpact.add(0, -entity.getBbHeight(), 0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getLocation().add(0, 0.076, 0);
        }
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
            controller.setAnimation(ANIMATION_IDLE);
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
        if (age < 20) {
            controller.setAnimation(ANIMATION_RISE);
            return PlayState.CONTINUE;
        } else
            return PlayState.STOP;


    }


    private final RawAnimation ANIMATION_RISE = RawAnimation.begin().thenPlay("animation.ender_flower.rise");
    private final RawAnimation ANIMATION_RETREAT = RawAnimation.begin().thenPlay("animation.ender_flower.despawn");
    private final RawAnimation ANIMATION_IDLE = RawAnimation.begin().thenLoop("animation.ender_flower.stand");

    private final AnimationController controller = new AnimationController(this, "ender_flower_controller", 20, this::animationPredicate);
    private final AnimationController riseController = new AnimationController(this, "ender_flower_rise_controller", 0, this::risePredicate);


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
