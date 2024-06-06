package net.alshanex.originsoverhaulmod.effects;

import com.github.alexthe666.iceandfire.datagen.tags.IafEntityTags;
import com.github.alexthe666.iceandfire.entity.EntityHydra;
import com.github.alexthe666.iceandfire.entity.EntityTroll;
import com.github.alexthe666.iceandfire.entity.util.IBlacklistedFromStatues;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.spells.EchoingStrikeEntity;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.alshanex.originsoverhaulmod.entity.custom.Mummy;
import net.alshanex.originsoverhaulmod.item.ModItems;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.alshanex.originsoverhaulmod.registry.EffectRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MummyEffect extends MagicMobEffect {
    public MummyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @SubscribeEvent
    public static void createMummy(LivingHurtEvent event) {
        var damageSource = event.getSource();
        if (!(damageSource instanceof SpellDamageSource) && damageSource.getEntity() instanceof LivingEntity attacker) {
            var effect = attacker.getEffect(EffectRegistry.MUMMY_EFFECT.get());
            if (effect != null && attacker.getMainHandItem().getItem() == ModItems.EGYPTHIAN_STAFF.get()) {
                LivingEntity entity = event.getEntity();
                boolean apply = entity instanceof IBlacklistedFromStatues blacklisted && !blacklisted.canBeTurnedToStone() || entity.getType().is(IafEntityTags.IMMUNE_TO_GORGON_STONE) || entity instanceof EntityTroll || entity instanceof EntityHydra || entity instanceof Player;
                if (!apply && entity.isPickable() && !entity.isDeadOrDying()) {
                    var random = Math.random();
                    if(random >= 0.5){
                        Level nivel = entity.level();

                        entity.remove(Entity.RemovalReason.KILLED);

                        Mummy mummy = Mummy.buildMummyEntity(entity, attacker);
                        mummy.absMoveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        mummy.yBodyRot = entity.getYRot();

                        nivel.addFreshEntity(mummy);
                    }
                }
            }
        }
    }
}
