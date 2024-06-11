package net.alshanex.originsoverhaulmod;

import com.mojang.logging.LogUtils;
import dev.architectury.registry.ReloadListenerRegistry;
import mod.chloeprime.aaaparticles.client.loader.EffekAssetLoader;
import net.alshanex.originsoverhaulmod.entity.ModEntities;
import net.alshanex.originsoverhaulmod.item.ModItems;
import net.alshanex.originsoverhaulmod.registry.EffectRegistry;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import net.alshanex.originsoverhaulmod.registry.SoundRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OriginsOverhaulMod.MOD_ID)
public class OriginsOverhaulMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "originsoverhaulmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public OriginsOverhaulMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ExampleSpellRegistry.register(modEventBus);

        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        EffectRegistry.register(modEventBus);

        SoundRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.IMPEVO);
            event.accept(ModItems.GOBLINEVO);
            event.accept(ModItems.ELFWOODEVO);
            event.accept(ModItems.ELFMOONEVO);
            event.accept(ModItems.ELFHIGHEVO);
            event.accept(ModItems.ENDERIANEVO);
            event.accept(ModItems.FELINEEVO);
            event.accept(ModItems.SIRENEVO);
            event.accept(ModItems.YETIEVO);
            event.accept(ModItems.PLAGUEEVO);
            event.accept(ModItems.ELYTRIANEVO);
            event.accept(ModItems.PHANTOMEVO);
            event.accept(ModItems.GORGONEVO);
            event.accept(ModItems.DWARFEVO);
            event.accept(ModItems.REVENANTEVO);
        }

        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.EGYPTHIAN_STAFF);
            event.accept(ModItems.BESTIARY);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, new EffekAssetLoader(), new ResourceLocation(MOD_ID, "effeks"));
        }
    }
}
