package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.recipe.ChancedOutput;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;

@ZenClass("mods.gregtechmod.recipe.ChancedOutput")
public class CTChancedOutput {

    private final IItemStack stack;
    private final int chance;

    public CTChancedOutput(ChancedOutput chancedOutput) {
        this.stack = MineTweakerMC.getIItemStack(chancedOutput.getStack());
        this.chance = chancedOutput.getChance();
    }

    @ZenGetter
    public IItemStack stack() {
        return stack;
    }

    @ZenGetter
    public int chance() {
        return chance;
    }
}
