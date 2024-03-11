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
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
