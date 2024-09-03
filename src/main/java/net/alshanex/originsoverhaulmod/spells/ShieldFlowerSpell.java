package net.alshanex.originsoverhaulmod.spells;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.alshanex.originsoverhaulmod.entity.custom.ShieldFlower;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class ShieldFlowerSpell extends AbstractSpell{
    private final ResourceLocation spellId = new ResourceLocation(IronsSpellbooks.MODID, "shield_flower");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(30)
            .build();

    public ShieldFlowerSpell() {
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
        return Optional.of(SoundRegistry.VOID_TENTACLES_START.get());
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.VOID_TENTACLES_FINISH.get());
    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .15f, false);
        return true;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        List<Vec3> positions = new ArrayList<>();
        Vec3 pos1 = new Vec3(entity.getX() + 3.0d, entity.getY(), entity.getZ());
        positions.add(pos1);
        Vec3 pos2 = new Vec3(entity.getX() + 3.0d, entity.getY(), entity.getZ() + 1.0d);
        positions.add(pos2);
        Vec3 pos3 = new Vec3(entity.getX() + 3.0d, entity.getY(), entity.getZ() - 1.0d);
        positions.add(pos3);
        Vec3 pos4 = new Vec3(entity.getX() + 2.0d, entity.getY(), entity.getZ() + 2.0d);
        positions.add(pos4);
        Vec3 pos5 = new Vec3(entity.getX() + 2.0d, entity.getY(), entity.getZ() - 2.0d);
        positions.add(pos5);
        Vec3 pos6 = new Vec3(entity.getX() + 1.0d, entity.getY(), entity.getZ() + 3.0d);
        positions.add(pos6);
        Vec3 pos7 = new Vec3(entity.getX() + 1.0d, entity.getY(), entity.getZ() - 3.0d);
        positions.add(pos7);

        Vec3 pos8 = new Vec3(entity.getX() - 3.0d, entity.getY(), entity.getZ());
        positions.add(pos8);
        Vec3 pos9 = new Vec3(entity.getX() - 3.0d, entity.getY(), entity.getZ() + 1.0d);
        positions.add(pos9);
        Vec3 pos10 = new Vec3(entity.getX() - 3.0d, entity.getY(), entity.getZ() - 1.0d);
        positions.add(pos10);
        Vec3 pos11 = new Vec3(entity.getX() - 2.0d, entity.getY(), entity.getZ() + 2.0d);
        positions.add(pos11);
        Vec3 pos12 = new Vec3(entity.getX() - 2.0d, entity.getY(), entity.getZ() - 2.0d);
        positions.add(pos12);
        Vec3 pos13 = new Vec3(entity.getX() - 1.0d, entity.getY(), entity.getZ() + 3.0d);
        positions.add(pos13);
        Vec3 pos14 = new Vec3(entity.getX() - 1.0d, entity.getY(), entity.getZ() - 3.0d);
        positions.add(pos14);

        Vec3 pos15 = new Vec3(entity.getX(), entity.getY(), entity.getZ() + 3.0d);
        positions.add(pos15);
        Vec3 pos16 = new Vec3(entity.getX(), entity.getY(), entity.getZ() - 3.0d);
        positions.add(pos16);

        for (Vec3 pos : positions){
            ShieldFlower vine = new ShieldFlower(level, entity, getDamage(spellLevel, entity));
            vine.moveTo(pos);
            vine.setDamage(getDamage(spellLevel, entity));
            level.addFreshEntity(vine);
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity);
    }
}
