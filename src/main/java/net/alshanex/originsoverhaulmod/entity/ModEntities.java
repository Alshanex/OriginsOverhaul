package net.alshanex.originsoverhaulmod.entity;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.entity.custom.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.common.*;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.CoralGolemSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.CoralssusSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.IgnitedBerserkerSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.epic.IgnitedRevenantSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.AmethystCrabSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.HydraSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.KobolediatorSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.legendary.WadjetSummon;
import net.alshanex.originsoverhaulmod.entity.custom.summons.rare.*;
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

    public static final RegistryObject<EntityType<FriendlyWatcher>> FRIENDLY_WATCHER =
            ENTITY_TYPES.register("friendly_watcher", () -> EntityType.Builder.<FriendlyWatcher>of(FriendlyWatcher::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "friendly_watcher").toString()));

    public static final RegistryObject<EntityType<KoboletonSummon>> SUMMONED_KOBOLETON =
            ENTITY_TYPES.register("summoned_koboleton", () -> EntityType.Builder.<KoboletonSummon>of(KoboletonSummon::new, MobCategory.MONSTER)
                    .sized(1f, 1.3f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_koboleton").toString()));

    public static final RegistryObject<EntityType<DreadBeastSummon>> SUMMONED_DREAD_BEAST =
            ENTITY_TYPES.register("summoned_dread_beast", () -> EntityType.Builder.<DreadBeastSummon>of(DreadBeastSummon::new, MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_dread_beast").toString()));

    public static final RegistryObject<EntityType<DreadGhoulSummon>> SUMMONED_DREAD_GHOUL =
            ENTITY_TYPES.register("summoned_dread_ghoul", () -> EntityType.Builder.<DreadGhoulSummon>of(DreadGhoulSummon::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_dread_ghoul").toString()));

    public static final RegistryObject<EntityType<DreadKnightSummon>> SUMMONED_DREAD_KNIGHT =
            ENTITY_TYPES.register("summoned_dread_knight", () -> EntityType.Builder.<DreadKnightSummon>of(DreadKnightSummon::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_dread_knight").toString()));

    public static final RegistryObject<EntityType<DreadThrallSummon>> SUMMONED_DREAD_THRALL =
            ENTITY_TYPES.register("summoned_dread_thrall", () -> EntityType.Builder.<DreadThrallSummon>of(DreadThrallSummon::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_dread_thrall").toString()));

    public static final RegistryObject<EntityType<IgnitedRevenantSummon>> SUMMONED_IGNITED_REVENANT =
            ENTITY_TYPES.register("summoned_ignited_revenant", () -> EntityType.Builder.<IgnitedRevenantSummon>of(IgnitedRevenantSummon::new, MobCategory.MONSTER)
                    .sized(1f, 1.8f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_ignited_revenant").toString()));

    public static final RegistryObject<EntityType<IgnitedBerserkerSummon>> SUMMONED_IGNITED_BERSERKER =
            ENTITY_TYPES.register("summoned_ignited_berserker", () -> EntityType.Builder.<IgnitedBerserkerSummon>of(IgnitedBerserkerSummon::new, MobCategory.MONSTER)
                    .sized(1f, 1.4f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_ignited_berserker").toString()));

    public static final RegistryObject<EntityType<CoralssusSummon>> SUMMONED_CORALSSUS =
            ENTITY_TYPES.register("summoned_coralssus", () -> EntityType.Builder.<CoralssusSummon>of(CoralssusSummon::new, MobCategory.MONSTER)
                    .sized(3f, 3.3f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_coralssus").toString()));

    public static final RegistryObject<EntityType<CoralGolemSummon>> SUMMONED_CORAL_GOLEM =
            ENTITY_TYPES.register("summoned_coral_golem", () -> EntityType.Builder.<CoralGolemSummon>of(CoralGolemSummon::new, MobCategory.MONSTER)
                    .sized(3f, 3.3f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_coral_golem").toString()));

    public static final RegistryObject<EntityType<WadjetSummon>> SUMMONED_WADJET =
            ENTITY_TYPES.register("summoned_wadjet", () -> EntityType.Builder.<WadjetSummon>of(WadjetSummon::new, MobCategory.MONSTER)
                    .sized(2f, 4.4f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_wadjet").toString()));

    public static final RegistryObject<EntityType<KobolediatorSummon>> SUMMONED_KOBOLEDIATOR =
            ENTITY_TYPES.register("summoned_kobolediator", () -> EntityType.Builder.<KobolediatorSummon>of(KobolediatorSummon::new, MobCategory.MONSTER)
                    .sized(3f, 4.4f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_kobolediator").toString()));

    public static final RegistryObject<EntityType<AmethystCrabSummon>> SUMMONED_AMETHYST_CRAB =
            ENTITY_TYPES.register("summoned_amethyst_crab", () -> EntityType.Builder.<AmethystCrabSummon>of(AmethystCrabSummon::new, MobCategory.MONSTER)
                    .sized(5f, 2.7f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_amethyst_crab").toString()));

    public static final RegistryObject<EntityType<HydraSummon>> SUMMONED_HYDRA =
            ENTITY_TYPES.register("summoned_hydra", () -> EntityType.Builder.<HydraSummon>of(HydraSummon::new, MobCategory.MONSTER)
                    .sized(2.8f, 3f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(OriginsOverhaulMod.MOD_ID, "summoned_hydra").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
