package net.alshanex.originsoverhaulmod.spells;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.entity.spells.electrocute.ElectrocuteProjectile;
import io.redspace.ironsspellbooks.entity.spells.root.RootEntity;
import io.redspace.ironsspellbooks.network.ClientboundSyncMana;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.setup.Messages;
import io.redspace.ironsspellbooks.spells.EntityCastData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class ForestGuardianSpell extends AbstractSpell{

    private final ResourceLocation spellId = new ResourceLocation(IronsSpellbooks.MODID, "forest_guardian");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getTickDamage(spellLevel, caster), 2)));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(40)
            .build();

    public ForestGuardianSpell() {
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 0;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 12;
    }

    @Override
    public CastType getCastType() {
        return CastType.CONTINUOUS;
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
        return Optional.of(SoundRegistry.RAY_OF_SIPHONING.get());
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        var radiusSqr = 100; //5
        world.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10, 5, 10),
                        livingEntity -> livingEntity != entity &&
                                horizontalDistanceSqr(livingEntity, entity) < radiusSqr &&
                                livingEntity.isPickable() &&
                                !livingEntity.isSpectator() &&
                                (livingEntity instanceof Mob || livingEntity instanceof ServerPlayer) &&
                                !Utils.shouldHealEntity(entity, livingEntity)
                )
                .forEach(targetEntity -> {

                    Vec3 spawn = targetEntity.position();
                    RootEntity rootEntity = new RootEntity(world, entity);
                    rootEntity.setDuration(20);
                    rootEntity.setTarget(targetEntity);
                    rootEntity.moveTo(spawn);
                    world.addFreshEntity(rootEntity);
                    targetEntity.stopRiding();
                    targetEntity.startRiding(rootEntity, true);

                    entity.heal(getTickDamage(spellLevel,entity)*0.1f);
                    targetEntity.hurt(getDamageSource(entity),getTickDamage(spellLevel, entity));

                    if(targetEntity instanceof ServerPlayer){
                        MagicData magicData = MagicData.getPlayerMagicData(targetEntity);
                        var newMana = Math.max(magicData.getMana() - this.baseManaCost*0.25f, 0);
                        magicData.setMana(newMana);
                        Messages.sendToPlayer(new ClientboundSyncMana(magicData), (ServerPlayer) targetEntity);
                    }

                    if(entity instanceof ServerPlayer){
                        MagicData magicData = MagicData.getPlayerMagicData(entity);
                        var newMana = Math.max(magicData.getMana() + this.baseManaCost*0.25f, 0);
                        magicData.setMana(newMana);
                        Messages.sendToPlayer(new ClientboundSyncMana(magicData), (ServerPlayer) entity);
                    }
                });

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getTickDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel*10, caster) * .4f;
    }

    private float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    @Override
    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return false;
    }


}
