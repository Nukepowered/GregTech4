package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.maps.DummyRecipeMap;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@ZenClass("mods.gregtechmod.recipe.RecipeMaps")
public class CTRecipeMaps {

    public static final Map<String, RecipeMap<?>> RECIPE_MAPS;
    static {
        Map<String, RecipeMap<?>> result = Arrays.stream(RecipeMaps.class.getFields())
            .filter(CTRecipeMaps::fieldPredicate)
            .collect(Collectors.toMap(Field::getName, CTRecipeMaps::valueGetter));

        RECIPE_MAPS = Collections.unmodifiableMap(result);
    }

    private static RecipeMap<?> valueGetter(Field f) {
        RecipeMap<?> map = null;

        try {
            map = (RecipeMap<?>) f.get(null);
        } catch (Exception e) {
            GT_Log.log.catching(e);
        }

        return map;
    }

    private static boolean fieldPredicate(Field field) {
        Class<?> type = field.getType();
        return Modifier.isStatic(field.getModifiers()) && RecipeMap.class.isAssignableFrom(type) &&
            !DummyRecipeMap.class.isAssignableFrom(type);
    }

    @ZenMethod
    public static RecipeMap<?> getRecipeMap(String name) {
        return RECIPE_MAPS.get(name.toUpperCase());
    }
}
