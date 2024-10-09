package net.alshanex.originsoverhaulmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = OriginsOverhaulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue EPIC_SUMMONS_CAP = BUILDER
            .comment("Maximum number of active epic summons")
            .defineInRange("epicCap", 2, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue LEGENDARY_SUMMONS_CAP = BUILDER
            .comment("Maximum number of active legendary summons")
            .defineInRange("legendaryCap", 1, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue LEGENDARY_SUMMON_VALUE = BUILDER
            .comment("Value of legendary summons in common summons")
            .defineInRange("legendaryValue", 10, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EPIC_SUMMON_VALUE = BUILDER
            .comment("Value of epic summons in common summons")
            .defineInRange("epicValue", 4, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue RARE_SUMMON_VALUE = BUILDER
            .comment("Value of rare summons in common summons")
            .defineInRange("rareValue", 2, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue MAX_SUMMONS = BUILDER
            .comment("Maximum number of active summons")
            .defineInRange("maxSummons", 15, 1, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int epicCap;
    public static int legendaryCap;
    public static int legendaryValue;
    public static int epicValue;
    public static int rareValue;
    public static int maxSummons;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        epicCap = EPIC_SUMMONS_CAP.get();
        legendaryCap = LEGENDARY_SUMMONS_CAP.get();
        legendaryValue = LEGENDARY_SUMMON_VALUE.get();
        epicValue = EPIC_SUMMON_VALUE.get();
        rareValue = RARE_SUMMON_VALUE.get();
        maxSummons = MAX_SUMMONS.get();
    }
}
