package net.alshanex.originsoverhaulmod.effect;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, OriginsOverhaulMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }

    public static final RegistryObject<MobEffect> MONARCH = MOB_EFFECT_DEFERRED_REGISTER.register("zone", () -> new MonarchEffect(MobEffectCategory.BENEFICIAL, 0x9f0be3));
}
