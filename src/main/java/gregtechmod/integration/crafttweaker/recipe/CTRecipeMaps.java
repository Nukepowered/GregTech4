package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.factory.BlastFurnaceRecipeFactory;
import gregtechmod.common.recipe.factory.GeneratorRecipeFactory;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;
import org.apache.commons.lang3.tuple.Pair;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@ZenClass("mods.gregtechmod.recipe.RecipeMaps")
public class CTRecipeMaps {

    public static final Map<String, RecipeMap<?>> SIMPLE_RECIPE_MAPS;
    static {
        Map<String, RecipeMap<?>> result = Arrays.stream(RecipeMaps.class.getFields())
            .filter(f -> f.getType() == RecipeMap.class)
            .map(CTRecipeMaps::pairMapper)
            .filter(p -> p.getValue().factory().getClass() == SimpleRecipeFactory.class)
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        SIMPLE_RECIPE_MAPS = Collections.unmodifiableMap(result);
    }

    private static Pair<String, RecipeMap<?>> pairMapper(Field f) {
        RecipeMap<?> map = null;

        try {
            map = (RecipeMap<?>) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Pair.of(f.getName(), map);
    }


    @ZenMethod
    public static RecipeMap<SimpleRecipeFactory> getSimpleMap(String name) {
        return (RecipeMap<SimpleRecipeFactory>) SIMPLE_RECIPE_MAPS.get(name.toUpperCase());
    }

    // MISC \\

    @ZenMethod
    public static RecipeMap<BlastFurnaceRecipeFactory> electricBlast() {
        return RecipeMaps.BLAST_FURNACE;
    }

    @ZenMethod
    public static RecipeMap<SimpleRecipeFactory> printer() {
        return RecipeMaps.PRINTER;
    }

    @ZenMethod
    public static RecipeMap<SimpleRecipeFactory> pulverizer() {
        return RecipeMaps.PULVERIZING;
    }

    // FUELS \\

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> dieselFuels() {
        return RecipeMaps.DIESEL_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> turbineFuels() {
        return RecipeMaps.TURBINE_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> hotFuels() {
        return RecipeMaps.HOT_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> denseFuels() {
        return RecipeMaps.DENSE_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> plasmaFuels() {
        return RecipeMaps.PLASMA_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> magicFuels() {
        return RecipeMaps.MAGIC_FUELS;
    }

    @ZenMethod
    public static RecipeMap<GeneratorRecipeFactory> steamFuels() {
        return RecipeMaps.STEAM_FUELS;
    }
}
