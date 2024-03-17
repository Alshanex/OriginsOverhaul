package net.alshanex.originsoverhaulmod.entity.custom;

import net.alshanex.originsoverhaulmod.registry.SoundRegistry;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.Optional;

public class WaterSlashEntity extends AoeEntity{
    public WaterSlashEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    LivingEntity target;
    public WaterSlashEntity(Level level, LivingEntity owner, LivingEntity target) {
        this(ModEntities.WATER_SLASH_ENTITY.get(), level);
        setOwner(owner);
        this.target = target;
    }
    @Override
    public void applyEffect(LivingEntity target) {
        if (target == this.target) {
            if (DamageSources.applyDamage(target, getDamage(), ExampleSpellRegistry.WATER_SLASH.get().getDamageSource(this, getOwner())) && getOwner() instanceof LivingEntity livingOwner) {
                target.setDeltaMovement(target.getDeltaMovement().add(0, 4.5f, 0));
                target.hurtMarked = true;
            }
        }
    }

    public final int waitTime = 5;
    public final int warmupTime = waitTime + 8;
    public final int deathTime = warmupTime + 8;
    @Override
    public void tick() {
        if (tickCount < waitTime) {
            if (this.target != null)
                setPos(this.target.position());
        } else if (tickCount == waitTime) {
            this.playSound(SoundRegistry.WATER_SPLASH.get(), 2, 1);
        } else if (tickCount == warmupTime) {
            if (level().isClientSide) {
                float y = this.getYRot();
                int countPerSide = 25;
                //These particles were not at all what I intended. But they're cooler. no clue how it works
                for (int i = -countPerSide; i < countPerSide; i++) {
                    Vec3 motion = new Vec3(0, Math.abs(countPerSide) - i, countPerSide * .5f).yRot(y).normalize().multiply(.4f, .8f, .4f);
                    level().addParticle(ParticleHelper.SNOWFLAKE, getX(), getY() + .5f, getZ(), motion.x, motion.y, motion.z);
                }
            } else {
                checkHits();
            }
        } else if (tickCount > deathTime)
            discard();
    }
    @Override
    protected Vec3 getInflation() {
        return new Vec3(2, 2, 2);
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public void refreshDimensions() {
        return;
    }

    @Override
    public void ambientParticles() {
        return;
    }

    @Override
    public float getParticleCount() {
        return 0;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.empty();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
