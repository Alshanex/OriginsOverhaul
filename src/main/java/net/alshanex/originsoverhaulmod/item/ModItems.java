package net.alshanex.originsoverhaulmod.item;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.item.custom.DreadGhoulSoul;
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
    public static final RegistryObject<Item> AMETHYST_CRAB_SOUL = ITEMS.register("amethyst_crab_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORAL_GOLEM_SOUL = ITEMS.register("coral_golem_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CORALSSUS_SOUL = ITEMS.register("coralssus_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DREAD_GHOUL_SOUL = ITEMS.register("dread_ghoul_soul", () -> new DreadGhoulSoul(ItemPropertiesHelper.equipment(1).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> DREAD_BEAST_SOUL = ITEMS.register("dread_beast_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DREAD_KNIGHT_SOUL = ITEMS.register("dread_knight_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DREAD_THRALL_SOUL = ITEMS.register("dread_thrall_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DROWNED_SOUL = ITEMS.register("drowned_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERMAN_SOUL = ITEMS.register("enderman_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HUSK_SOUL = ITEMS.register("husk_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HYDRA_SOUL = ITEMS.register("hydra_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IGNITED_BERSERKER_SOUL = ITEMS.register("ignited_berserker_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IGNITED_REVENANT_SOUL = ITEMS.register("ignited_revenant_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KOBOLEDIATOR_SOUL = ITEMS.register("kobolediator_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KOBOLETON_SOUL = ITEMS.register("koboleton_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PILLAGER_SOUL = ITEMS.register("pillager_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SKELETON_SOUL = ITEMS.register("skeleton_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VINDICATOR_SOUL = ITEMS.register("vindicator_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WADJET_SOUL = ITEMS.register("wadjet_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WITHER_SKELETON_SOUL = ITEMS.register("wither_skeleton_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZOMBIE_SOUL = ITEMS.register("zombie_soul",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VEX_SOUL = ITEMS.register("vex_soul",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
