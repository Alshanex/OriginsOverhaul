package net.alshanex.originsoverhaulmod.entity;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.WaterCutProjectile;
import net.alshanex.originsoverhaulmod.entity.custom.WaterSlashEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OriginsOverhaulMod.MOD_ID);

    public static final RegistryObject<EntityType<WaterSlashEntity>> WATER_SLASH_ENTITY =
            ENTITY_TYPES.register("water_slash", () -> EntityType.Builder.<WaterSlashEntity>of(WaterSlashEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("water_slash"));

    public static final RegistryObject<EntityType<WaterCutProjectile>> WATER_CUT_PROJECTILE =
            ENTITY_TYPES.register("water_cut", () -> EntityType.Builder.<WaterCutProjectile>of(WaterCutProjectile::new, MobCategory.MISC)
                    .sized(2f, .5f)
                    .clientTrackingRange(64)
                    .build("water_cut"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
