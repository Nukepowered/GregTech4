package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.common.recipe.RecipeEntry;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ZenClass("mods.gregtechmod.recipe.RecipeFactory")
public class CTRecipeFactory {

    private final RecipeFactory<?> backingFactory;

    public CTRecipeFactory(RecipeFactory<?> backingFactory) {
        this.backingFactory = backingFactory;
    }

    @ZenMethod
    public CTRecipeFactory EUt(int energy) {
        backingFactory.EUt(energy);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory duration(int ticks) {
        backingFactory.duration(ticks);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory startEU(int amount) {
        backingFactory.startEU(amount);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory setShaped(boolean value) {
        backingFactory.setShaped(value);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory nonConsumable(IItemStack stack) {
        backingFactory.nonConsumable(MineTweakerMC.getItemStack(stack));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory circuit(int config) {
        backingFactory.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, config));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory input(IIngredient ingredient) {
        List<ItemStack> inputs = ingredient.getItems().stream()
            .map(MineTweakerMC::getItemStack)
            .collect(Collectors.toList());
        backingFactory.input(RecipeEntry.fromStacks(ingredient.getAmount(), inputs));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory inputs(IIngredient... ingredients) {
        Arrays.stream(ingredients).forEach(this::input);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory fluidInput(ILiquidStack fluid) {
        backingFactory.input(MineTweakerMC.getLiquidStack(fluid));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory fluidInputs(ILiquidStack... fluids) {
        Arrays.stream(fluids).forEach(this::fluidInput);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory output(IItemStack output) {
        backingFactory.output(MineTweakerMC.getItemStack(output));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory outputs(IItemStack... outputs) {
        Arrays.stream(outputs).forEach(this::output);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory chancedOutput(IItemStack output, int chance) {
        backingFactory.chanced(MineTweakerMC.getItemStack(output), chance);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory fluidOutput(ILiquidStack fluid) {
        backingFactory.output(MineTweakerMC.getLiquidStack(fluid));
        return this;
    }

    @ZenMethod
    public CTRecipeFactory fluidOutputs(ILiquidStack... fluids) {
        Arrays.stream(fluids).forEach(this::fluidOutput);
        return this;
    }

    @ZenMethod
    public CTRecipeFactory withMeta(String key, int value) {
        backingFactory.withMeta(key, Integer.valueOf(value));
        return this;
    }

    @ZenMethod
    public CTRecipe build() {
        return new CTRecipe(backingFactory.build());
    }

    @ZenMethod
    public void buildAndRegister() {
        backingFactory.buildAndRegister();
    }
}
