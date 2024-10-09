package net.alshanex.originsoverhaulmod.item.custom;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.alshanex.originsoverhaulmod.registry.ExampleSpellRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EliteDraugrSoul extends Item implements IPresetSpellContainer{
    public EliteDraugrSoul(Properties pProperties) {
        super(pProperties.stacksTo(16));
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        if (!ISpellContainer.isSpellContainer(itemStack)) {
            var spellContainer = ISpellContainer.create(1, true, false);
            spellContainer.addSpell(ExampleSpellRegistry.SUMMONING.get(), 1, true, itemStack);
            spellContainer.save(itemStack);
        }
    }
}
