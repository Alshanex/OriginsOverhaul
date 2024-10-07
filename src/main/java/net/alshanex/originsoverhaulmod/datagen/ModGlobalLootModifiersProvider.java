package net.alshanex.originsoverhaulmod.datagen;

import net.alshanex.originsoverhaulmod.OriginsOverhaulMod;
import net.alshanex.originsoverhaulmod.item.ModItems;
import net.alshanex.originsoverhaulmod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, OriginsOverhaulMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("zombie_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build()
        }, ModItems.ZOMBIE_SOUL.get()));

        add("skeleton_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build()
        }, ModItems.SKELETON_SOUL.get()));

        add("vex_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/vex")).build()
        }, ModItems.VEX_SOUL.get()));

        add("koboleton_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/koboleton")).build()
        }, ModItems.KOBOLETON_SOUL.get()));

        add("dread_beast_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("iceandfire:entities/dread_beast")).build()
        }, ModItems.DREAD_BEAST_SOUL.get()));

        add("dread_ghoul_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("iceandfire:entities/dread_ghoul")).build()
        }, ModItems.DREAD_GHOUL_SOUL.get()));

        add("dread_knight_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("iceandfire:entities/dread_knight")).build()
        }, ModItems.DREAD_KNIGHT_SOUL.get()));

        add("dread_thrall_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("iceandfire:entities/dread_thrall")).build()
        }, ModItems.DREAD_THRALL_SOUL.get()));

        add("draugr_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/draugr")).build()
        }, ModItems.DRAUGR_SOUL.get()));

        add("elite_draugr_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/elite_draugr")).build()
        }, ModItems.ELITE_DRAUGR_SOUL.get()));

        add("royal_draugr_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/royal_draugr")).build()
        }, ModItems.ROYAL_DRAUGR_SOUL.get()));

        add("coral_golem_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/coral_golem")).build()
        }, ModItems.CORAL_GOLEM_SOUL.get()));

        add("ignited_berserker_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/ignited_berserker")).build()
        }, ModItems.IGNITED_BERSERKER_SOUL.get()));

        add("ignited_revenant_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/ignited_revenant")).build()
        }, ModItems.IGNITED_REVENANT_SOUL.get()));

        add("wadjet_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/wadjet")).build()
        }, ModItems.WADJET_SOUL.get()));

        add("kobolediator_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/kobolediator")).build()
        }, ModItems.KOBOLEDIATOR_SOUL.get()));

        add("amethyst_crab_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/amethyst_crab")).build()
        }, ModItems.AMETHYST_CRAB_SOUL.get()));

        add("coralssus_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/coralssus")).build()
        }, ModItems.CORALSSUS_SOUL.get()));

        add("aptrgangr_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/aptrgangr")).build()
        }, ModItems.APTGANGR_SOUL.get()));

        add("ender_golem_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("cataclysm:entities/ender_golem")).build()
        }, ModItems.ENDER_GOLEM_SOUL.get()));

        add("hydra_soul_drop", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("iceandfire:entities/hydra")).build()
        }, ModItems.HYDRA_SOUL.get()));
    }
}
