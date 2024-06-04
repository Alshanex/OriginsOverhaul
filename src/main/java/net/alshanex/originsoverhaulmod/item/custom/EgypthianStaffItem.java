package net.alshanex.originsoverhaulmod.item.custom;

import com.github.alexthe666.iceandfire.datagen.tags.IafEntityTags;
import com.github.alexthe666.iceandfire.entity.EntityHydra;
import com.github.alexthe666.iceandfire.entity.EntityTroll;
import com.github.alexthe666.iceandfire.entity.util.IBlacklistedFromStatues;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import net.alshanex.originsoverhaulmod.entity.custom.Mummy;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;

public class EgypthianStaffItem extends SwordItem {
    public EgypthianStaffItem(Properties pProperties) {
        super(Tiers.NETHERITE, 5, -1.9F, new Properties());
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity){
            boolean apply = entity instanceof IBlacklistedFromStatues blacklisted && !blacklisted.canBeTurnedToStone() || entity.getType().is(IafEntityTags.IMMUNE_TO_GORGON_STONE) || entity instanceof EntityTroll || entity instanceof EntityHydra || entity instanceof Player;
            if (!apply && entity.isPickable() && !((LivingEntity)entity).isDeadOrDying()) {
                float currentHealth = ((LivingEntity) entity).getHealth();
                float maxHealth = ((LivingEntity) entity).getMaxHealth();
                if( currentHealth < maxHealth * 0.05f || currentHealth < 5f || this.getDamage() > currentHealth){
                    var random = Math.random();
                    if(random >= 0.5){
                        Level nivel = entity.level();

                        entity.remove(Entity.RemovalReason.KILLED);

                        Mummy mummy = Mummy.buildMummyEntity((LivingEntity) entity);
                        mummy.absMoveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        mummy.yBodyRot = entity.getYRot();

                        nivel.addFreshEntity(mummy);
                    }
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
