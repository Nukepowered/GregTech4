package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.recipe.Recipe;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mc1710.liquid.MCLiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;
import java.util.stream.Collectors;

@ZenClass("mods.gregtechmod.recipe.Recipe")
public class CTRecipe {

    public final Recipe backingRecipe;

    public CTRecipe(Recipe recipe) {
        backingRecipe = recipe;
    }

    @ZenGetter
    public int duration() {
        return backingRecipe.getDuration();
    }

    @ZenGetter
    public int EUt() {
        return backingRecipe.getEUt();
    }

    @ZenGetter
    public int startEU() {
        return backingRecipe.getEUtoStart();
    }

    @ZenGetter
    public boolean shaped() {
        return backingRecipe.isShaped();
    }

    @ZenGetter
    public boolean fluidRecipe() {
        return backingRecipe.isFluidRecipe();
    }

    @ZenGetter
    public List<String> metaKeys() {
        return backingRecipe.getMetaKeys();
    }

    @ZenMethod
    public Object getMeta(String key) {
        return backingRecipe.getMeta(key);
    }

    @ZenGetter("outputs")
    public List<IItemStack> getOutputs() {
        return backingRecipe.getOutputs().stream()
            .map(MineTweakerMC::getIItemStack)
            .collect(Collectors.toList());
    }

    @ZenGetter("chancedOutputs")
    public List<CTChancedOutput> getChancedOutputs() {
        return backingRecipe.getChancedOutputs().stream()
            .map(CTChancedOutput::new)
            .collect(Collectors.toList());
    }

    @ZenMethod
    public List<IItemStack> getAllOutputs() {
        return backingRecipe.getAllOutputs().stream()
            .map(MineTweakerMC::getIItemStack)
            .collect(Collectors.toList());
    }

    @ZenGetter("fluidOutputs")
    public List<ILiquidStack> getFluidOutputs() {
        return backingRecipe.getFluidOutputs().stream()
            .map(MCLiquidStack::new)
            .collect(Collectors.toList());
    }

    @ZenGetter("inputs")
    public List<CTIngredient> getInputs() {
        return backingRecipe.getInputs().stream()
            .map(CTIngredient::new)
            .collect(Collectors.toList());
    }

    @ZenGetter("fluidInputs")
    public List<ILiquidStack> getFluidInputs() {
        return backingRecipe.getFluidInputs().stream()
            .map(MCLiquidStack::new)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return backingRecipe.toString();
    }
}
