package gregtechmod.integration.crafttweaker;

import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.integration.crafttweaker.recipe.*;
import minetweaker.MineTweakerAPI;

public class GregTechTweaker {

    public static void init() {
        MineTweakerAPI.registerClass(RecipeMap.class);
        MineTweakerAPI.registerClass(CTRecipeMaps.class);
        MineTweakerAPI.registerClass(CTRecipeFactory.class);
        MineTweakerAPI.registerClass(CTIngredient.class);
        MineTweakerAPI.registerClass(CTChancedOutput.class);
        MineTweakerAPI.registerClass(CTRecipe.class);

        MineTweakerAPI.logInfo("GregTech 4 Mod support loaded");
    }
}
