package net.alshanex.originsoverhaulmod.item;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.minecraft.world.item.Item;
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
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
