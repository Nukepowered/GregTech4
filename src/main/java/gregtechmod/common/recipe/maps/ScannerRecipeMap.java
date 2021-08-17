package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class ScannerRecipeMap extends DummyRecipeMap {

	public ScannerRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> inputs, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		for (int i = 0; i < inputs.size(); i++) {
			ItemStack input = inputs.get(i);
			if (GT_Utility.isStackValid(input) && GT_Items.IC2_Crop_Seeds.isStackEqual(input, true, true)) {
				   NBTTagCompound data = input.getTagCompound();
				   if (data == null) return null; // May not work
				   int dur = 0, eut = 0;
				   if (data.getByte("scan") < 4) {
					   data.setByte("scan", (byte)4);
					   dur = 20;
					   eut = 500;
				   } else {
					   dur = 1;
					   eut = 1;
				   }
				   
				   ItemStack output = input.copy();
				   output.setTagCompound(data);
				   return new Recipe(0, eut, dur, false,
						   Collections.singleton(RecipeEntry.singleton(input, Match.STRICT)),
						   Collections.singleton(output),
						   Collections.emptyList());
			}
		}
	   
	   return null;
	}
}
