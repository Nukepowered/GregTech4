package gregtechmod.common.recipe.factory;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.maps.DummyRecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/** Fake RecipeMap which generates recipes for Thermal boiler from hot fuels map
 * Will allow only fluid-fluid recipe, replacing cells to fluids (TODO add fluid out to thermal generator)
 * @author TheDarkDnKTv
 *
 */
public class ThermalBoilerRecipeMap extends DummyRecipeMap {
	
	public ThermalBoilerRecipeMap() {
		super(0, 0, 0, 1, 0, 1, 0, 1);
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> input, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		return super.findRecipe(input, fluidInputs, metaChecker);
	}
	
	@Override
	public Set<Recipe> getMappedRecipes(List<ItemStack> input, List<FluidStack> fluidInputs) {
		Set<Recipe> recipesTotal = new HashSet<>();

		for (FluidStack fluid : fluidInputs) {
			if (GT_Utility.isFluidStackValid(fluid) && fluid.getFluid() != FluidRegistry.WATER) {
				int fluidInt = GT_Utility.fluidStackToInt(fluid);
				List<Recipe> recipesFluid = MAPPINGS.get(fluidInt);
				
				if (recipesFluid != null) {
					recipesTotal.addAll(recipesFluid);
				} else {
					Set<Recipe> recipes1 = RecipeMaps.HOT_FUELS.getMappedRecipes(input, fluidInputs);
					Recipe recipe = this.findRecipe(recipes1, input, fluidInputs, r -> true);
					
					if (recipe != null) {
						this.createMappings(recipe);
						recipesTotal.add(recipe);
					} else {
						for (Recipe rec : recipes1) {
							if (rec.getInputs().isEmpty()) {
								this.createMappings(rec);
								recipesTotal.add(rec);
							} else if (rec.getInputs().size() == 1 && rec.getInputs().get(0).match(GT_Items.Cell_Empty.get(1))) {
								FluidStack fluidOut = GT_Utility.getFluidForFilledItem(rec.getOutputs().get(0));
								if (fluidOut != null) {
									rec = new Recipe(rec.getEUtoStart(),
											rec.getEUt(),
											rec.getDuration(),
											rec.isShaped(),
											Collections.emptyList(),
											Collections.emptyList(),
											Collections.emptyList(),
											rec.getFluidInputs(),
											Collections.singletonList(fluidOut),
											Collections.emptyMap());
									this.createMappings(rec);
									recipesTotal.add(rec);
								}
							}
						}
					}
				}
			}
		}
		
		return recipesTotal;
	}
}
