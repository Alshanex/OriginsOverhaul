package net.alshanex.originsoverhaulmod.entity;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.FireFlower;
import net.alshanex.originsoverhaulmod.entity.custom.ThunderFlower;
import net.alshanex.originsoverhaulmod.entity.custom.WaterCutProjectile;
import net.alshanex.originsoverhaulmod.entity.custom.WaterSlashEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
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

    public static final RegistryObject<EntityType<FireFlower>> FIRE_FLOWER =
            ENTITY_TYPES.register("fire_flower", () -> EntityType.Builder.<FireFlower>of(FireFlower::new, MobCategory.MISC)
                    .sized(1.5f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "fire_flower").toString()));

    public static final RegistryObject<EntityType<ThunderFlower>> THUNDER_FLOWER =
            ENTITY_TYPES.register("thunder_flower", () -> EntityType.Builder.<ThunderFlower>of(ThunderFlower::new, MobCategory.MISC)
                    .sized(1f, 2f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "thunder_flower").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
