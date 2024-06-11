package net.alshanex.originsoverhaulmod.entity;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    public static final RegistryObject<EntityType<EnderFlower>> ENDER_FLOWER =
            ENTITY_TYPES.register("ender_flower", () -> EntityType.Builder.<EnderFlower>of(EnderFlower::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "ender_flower").toString()));

    public static final RegistryObject<EntityType<ShieldFlower>> SHIELD_FLOWER =
            ENTITY_TYPES.register("shield_flower", () -> EntityType.Builder.<ShieldFlower>of(ShieldFlower::new, MobCategory.MISC)
                    .sized(1f, 4f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "shield_flower").toString()));
    public static final RegistryObject<EntityType<Crown>> CROWN =
            ENTITY_TYPES.register("crown", () -> EntityType.Builder.<Crown>of(Crown::new, MobCategory.MISC)
                    .sized(1, 1)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "crown").toString()));
    public static final RegistryObject<EntityType<Balanza>> BALANZA =
            ENTITY_TYPES.register("balanza_mala", () -> EntityType.Builder.<Balanza>of(Balanza::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "balanza_mala").toString()));
    public static final RegistryObject<EntityType<BalanzaBuena>> BALANZA_BUENA =
            ENTITY_TYPES.register("balanza_buena", () -> EntityType.Builder.<BalanzaBuena>of(BalanzaBuena::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "balanza_buena").toString()));
    public static final RegistryObject<EntityType<Mummy>> MUMMY =
            ENTITY_TYPES.register("mummy", () -> EntityType.Builder.of(Mummy::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "mummy").toString()));
    public static final RegistryObject<EntityType<Caja>> CAJA =
            ENTITY_TYPES.register("caja", () -> EntityType.Builder.<Caja>of(Caja::new, MobCategory.MISC)
                    .sized(1.5f, 3f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "caja").toString()));
    public static final RegistryObject<EntityType<Parasyte>> PARASYTE =
            ENTITY_TYPES.register("parasyte", () -> EntityType.Builder.<Parasyte>of(Parasyte::new, MobCategory.MISC)
                    .sized(1, 1)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "parasyte").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
