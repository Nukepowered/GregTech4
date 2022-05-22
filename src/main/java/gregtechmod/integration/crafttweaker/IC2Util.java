package gregtechmod.integration.crafttweaker;

import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Map;

@ZenClass("mods.gregtechmod.IC2Util")
public class IC2Util {

    @ZenMethod
    public static boolean removeExtractorRecipe(IItemStack input, @Optional IItemStack output)
    {
        return removeIC2Recipe(input, GT_ModHandler.getExtractorRecipeList(), output);
    }

    @ZenMethod
    public static boolean removeCompressorRecipe(IItemStack input, @Optional IItemStack output)
    {
        return removeIC2Recipe(input, GT_ModHandler.getCompressorRecipeList(), output);
    }

    @ZenMethod
    public static boolean removeThermalCentrifugeRecipe(IItemStack input, @Optional IItemStack output)
    {
        return removeIC2Recipe(input, GT_ModHandler.getThermalCentrifugeRecipeList(), output);
    }

    @ZenMethod
    public static boolean removeOreWashingRecipe(IItemStack input, @Optional IItemStack output)
    {
        return removeIC2Recipe(input, GT_ModHandler.getOreWashingRecipeList(), output);
    }

    @ZenMethod
    public static boolean removeMaceratorRecipe(IItemStack input, @Optional IItemStack output)
    {
        return removeIC2Recipe(input, GT_ModHandler.getMaceratorRecipeList(), output);
    }

    private static boolean removeIC2Recipe(IItemStack input, Map<IRecipeInput, RecipeOutput> recipeList, IItemStack output)
    {
        return GT_Utility.removeSimpleIC2MachineRecipe(
                MineTweakerMC.getItemStack(input),
                recipeList,
                MineTweakerMC.getItemStack(output)
        );
    }
}
