package gregtechmod.common.recipe;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_RecipeException;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class SimpleRecipeFactory extends RecipeFactory {

	public SimpleRecipeFactory() {
		super();
	}
	
	public SimpleRecipeFactory(RecipeMap map) {
		super(map);
	}
	
	@Override
	public RecipeFactory nonConsumable(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeFactory input(ItemStack stack, boolean checkDamage, boolean checkNBT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeFactory inputs(ItemStack... stacks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeFactory input(String oreDict, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe build() {
		if (EUt < 0) 						errors.append(" - EU/t was not set!\n");
		if (duration <= 0) 					errors.append(" - Duration was not set!\n");
		if (inputItems.isEmpty()) 			errors.append(" - Input of recipe shall not be empty!\n");
		if (outputItems.isEmpty() &&
				chancedOutput.isEmpty()) 	errors.append(" - Output of recipe shall not be empty!");
		if (errors.length() == 0) {
			Recipe recipe = new Recipe(startEU, EUt, duration, shaped, inputItems, outputItems, chancedOutput);
			this.reset();
			return recipe;
		} throw new GT_RecipeException(errors.toString());
	}

}
