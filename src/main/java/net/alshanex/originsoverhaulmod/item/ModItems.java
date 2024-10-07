package net.alshanex.originsoverhaulmod.item;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OriginsOverhaulMod.MOD_ID);

    public static final RegistryObject<Item> IMPEVO = ITEMS.register("impevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOBLINEVO = ITEMS.register("goblinevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELFWOODEVO = ITEMS.register("elfwoodevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELFMOONEVO = ITEMS.register("elfmoonevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELFHIGHEVO = ITEMS.register("elfhighevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIANEVO = ITEMS.register("enderianevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FELINEEVO = ITEMS.register("felineevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIRENEVO = ITEMS.register("sirenevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YETIEVO = ITEMS.register("yetievo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLAGUEEVO = ITEMS.register("plagueevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELYTRIANEVO = ITEMS.register("elytrianevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PHANTOMEVO = ITEMS.register("phantomevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GORGONEVO = ITEMS.register("gorgonevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DWARFEVO = ITEMS.register("dwarfevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REVENANTEVO = ITEMS.register("revenantevo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_CRAB_SOUL = ITEMS.register("amethyst_crab_soul", () -> new AmethystCrabSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> CORAL_GOLEM_SOUL = ITEMS.register("coral_golem_soul", () -> new CoralGolemSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CORALSSUS_SOUL = ITEMS.register("coralssus_soul", () -> new CoralssusSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> DREAD_GHOUL_SOUL = ITEMS.register("dread_ghoul_soul", () -> new DreadGhoulSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> DREAD_BEAST_SOUL = ITEMS.register("dread_beast_soul", () -> new DreadBeastSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> DREAD_KNIGHT_SOUL = ITEMS.register("dread_knight_soul", () -> new DreadKnightSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> DREAD_THRALL_SOUL = ITEMS.register("dread_thrall_soul", () -> new DreadThrallSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HYDRA_SOUL = ITEMS.register("hydra_soul", () -> new HydraSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> IGNITED_BERSERKER_SOUL = ITEMS.register("ignited_berserker_soul", () -> new IgnitedBerserkerSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> IGNITED_REVENANT_SOUL = ITEMS.register("ignited_revenant_soul", () -> new IgnitedRevenantSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> KOBOLEDIATOR_SOUL = ITEMS.register("kobolediator_soul", () -> new KobolediatorSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KOBOLETON_SOUL = ITEMS.register("koboleton_soul", () -> new KoboletonSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SKELETON_SOUL = ITEMS.register("skeleton_soul", () -> new SkeletonSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> WADJET_SOUL = ITEMS.register("wadjet_soul", () -> new WadjetSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ZOMBIE_SOUL = ITEMS.register("zombie_soul", () -> new ZombieSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VEX_SOUL = ITEMS.register("vex_soul", () -> new VexSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> DRAUGR_SOUL = ITEMS.register("draugr_soul", () -> new DraugrSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ELITE_DRAUGR_SOUL = ITEMS.register("elite_draugr_soul", () -> new EliteDraugrSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> ROYAL_DRAUGR_SOUL = ITEMS.register("royal_draugr_soul", () -> new RoyalDraugrSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> APTGANGR_SOUL = ITEMS.register("aptgangr_soul", () -> new AptgangrSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ENDER_GOLEM_SOUL = ITEMS.register("ender_golem_soul", () -> new EnderGolemSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
