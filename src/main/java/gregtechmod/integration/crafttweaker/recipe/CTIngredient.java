package gregtechmod.integration.crafttweaker.recipe;

import gregtechmod.api.recipe.Ingredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;

import java.util.List;
import java.util.stream.Collectors;

@ZenClass("mods.gregtechmod.recipe.Ingredient")
public class CTIngredient {

    private final int count;
    private final List<IItemStack> variants;

    public CTIngredient(Ingredient ingredient) {
        this.count = ingredient.getCount();
        this.variants = ingredient.getVariants().stream()
            .map(MineTweakerMC::getIItemStack)
            .collect(Collectors.toList());
    }

    @ZenGetter("count")
    public int getCount() {
        return 0;
    }

    @ZenGetter("variants")
    public List<IItemStack> getVariants() {
        return this.variants;
    }
}
