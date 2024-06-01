package net.alshanex.originsoverhaulmod.spells;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.CastTargetingData;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.Log;
import io.redspace.ironsspellbooks.util.ModTags;
import net.alshanex.originsoverhaulmod.entity.custom.Balanza;
import net.alshanex.originsoverhaulmod.entity.custom.BalanzaBuena;
import net.alshanex.originsoverhaulmod.entity.custom.FireFlower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
@AutoSpellConfig
public class OsirisSpell extends AbstractSpell{
    private final ResourceLocation spellId = new ResourceLocation(IronsSpellbooks.MODID, "osiris_trial");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(30)
            .build();

    public OsirisSpell() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 3;
        this.castTime = 20;
        this.baseManaCost = 150;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .35f);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        if (playerMagicData.getAdditionalCastData() instanceof CastTargetingData castTargetingData) {
            LivingEntity target = castTargetingData.getTarget((ServerLevel) level);

            if (target != null) {

                Vec3 playerPos = entity.position();
                Vec3 targetPos = target.position();
                Vec3 direction = playerPos.subtract(targetPos);

                double horizontalDistance = Math.sqrt(direction.x * direction.x + direction.z * direction.z);
                float yaw = (float) (Mth.atan2(direction.z, direction.x) * (180 / Math.PI)) - 90.0F;
                float pitch = (float) (-(Mth.atan2(direction.y, horizontalDistance) * (180 / Math.PI)));

                target.setYRot(yaw);
                target.setXRot(pitch);
                target.yHeadRot = yaw;
                target.yBodyRot = yaw;

                float playerYaw = entity.getYRot();
                float playerPitch = entity.getXRot();
                Vec3 directionPlayer = getDirectionVector(playerYaw, playerPitch);
                Vec3 spawn = playerPos.add(directionPlayer.x, 0, directionPlayer.z);

                MobEffectInstance slownessEffect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 130, 10, false, false);
                target.addEffect(slownessEffect);

                double chance = Math.random();

                Balanza balanzaMala = new Balanza(level, entity, getDamage(spellLevel, entity), target, getHpBonus(spellLevel, entity));
                balanzaMala.moveTo(spawn);
                balanzaMala.setDamage(getDamage(spellLevel, entity));
                balanzaMala.setYRot(yaw);
                balanzaMala.setXRot(pitch);
                balanzaMala.yHeadRot = yaw;
                balanzaMala.yBodyRot = yaw;

                BalanzaBuena balanzaBuena = new BalanzaBuena(level, entity, getDamage(spellLevel, entity), target, (int) getSpellPower(spellLevel, entity), getSchoolType());
                balanzaBuena.moveTo(spawn);
                balanzaBuena.setDamage(getDamage(spellLevel, entity));
                balanzaBuena.setYRot(yaw);
                balanzaBuena.setXRot(pitch);
                balanzaBuena.yHeadRot = yaw;
                balanzaBuena.yBodyRot = yaw;

                if (target instanceof Monster) {
                    level.addFreshEntity(balanzaMala);
                } else if (target instanceof Player){
                    double bloodSpellPowerAttribute = target.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER.get());
                    double holySpellPowerAttribute = target.getAttributeValue(AttributeRegistry.BLOOD_SPELL_POWER.get());

                    if(bloodSpellPowerAttribute > holySpellPowerAttribute){
                        if (chance <= 0.75) {
                            level.addFreshEntity(balanzaMala);
                        } else {
                            level.addFreshEntity(balanzaBuena);
                        }
                    } else if (bloodSpellPowerAttribute < holySpellPowerAttribute){
                        if (chance <= 0.25) {
                            level.addFreshEntity(balanzaMala);
                        } else {
                            level.addFreshEntity(balanzaBuena);
                        }
                    } else {
                        if (chance <= 0.5) {
                            level.addFreshEntity(balanzaMala);
                        } else {
                            level.addFreshEntity(balanzaBuena);
                        }
                    }
                } else {
                    if (chance <= 0.5) {
                        level.addFreshEntity(balanzaMala);
                    } else {
                        level.addFreshEntity(balanzaBuena);
                    }
                }
            }
        }


        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private Vec3 getDirectionVector(float yaw, float pitch) {
        double x = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        double y = -Math.sin(Math.toRadians(pitch));
        double z = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        return new Vec3(x, y, z);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity);
    }

    public int getHpBonus(int spellLevel, LivingEntity caster) {
        return 2 * (int) (getSpellPower(spellLevel, caster) * .25f);
    }
}
