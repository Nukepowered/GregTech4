package gregtechmod.common.recipe.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.common.recipe.ChancedStack;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.UnifierRecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class BlastFurnanceRecipeFactory extends RecipeFactory<BlastFurnanceRecipeFactory> {
	
	private int minTemp = -1;
	
	public BlastFurnanceRecipeFactory() {}

	public BlastFurnanceRecipeFactory(RecipeMap<BlastFurnanceRecipeFactory> map) {
		super(map);
	}
	
	public BlastFurnanceRecipeFactory minTemperature(int value) {
		if (minTemp >= 0)
			throw new GT_RecipeException("Can not set temperature twice!");
		if (value <= 0)
			errors.append(" - Blast temperature should be grather than zero!\n");
		this.minTemp = value;
		return this;
	}
	
	@Override
	protected void reset() {
		super.reset();
		minTemp = -1;
	}
	
	@Override
	public BlastFurnanceRecipeFactory nonConsumable(ItemStack stack) {
		super.input(RecipeEntry.singleton(stack, 0, Match.DAMAGE));
		return this;
	}

	@Override
	public BlastFurnanceRecipeFactory input(ItemStack stack, boolean checkDamage, boolean checkNBT) {
		@SuppressWarnings("serial")
		List<Match> vals = new ArrayList<Match>() {{
			if (checkDamage && (stack != null && stack.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE)) add(Match.DAMAGE);
			if (checkNBT) 	 add(Match.NBT);
		}};

		
		super.input(RecipeEntry.singleton(stack, vals.toArray(new Match[0])));
		return this;
	}

	@Override
	public BlastFurnanceRecipeFactory input(OrePrefixes prefix, Materials material, int count) {
		if (count < 0) errors.append("- Count less than ZERO!!!, idx: " + (inputItems.size() + 1) + "\n");
		inputItems.add(new UnifierRecipeEntry(prefix, material, count));
		return this;
	}
	
	@Override
	public BlastFurnanceRecipeFactory inputs(ItemStack... stacks) {
		for (ItemStack stack : stacks)
			this.input(stack);
		return this;
	}

	@Override
	public BlastFurnanceRecipeFactory input(String oreDict, int amount) {
		if (amount < 0) errors.append("- Count less than ZERO!!!");
		super.input(RecipeEntry.oreDict(oreDict, amount, Match.DAMAGE));
		return this;
	}

	@Override
	public BlastFurnanceRecipeFactory chanced(ItemStack stack, int chance) {
		super.chanced(new ChancedStack(stack.copy(), chance));
		return this;
	}

	@Override
	public Recipe build() {
		if (EUt < 0) 						errors.append(" - EU/t was not set!\n");
		if (duration <= 0) 					errors.append(" - Duration was not set!\n");
		if (minTemp < 0) 					errors.append(" - Blast temperature was not set!\n");
		if (inputItems.isEmpty() &&
				inputFluids.isEmpty()) 		errors.append(" - Input of recipe shall not be empty!\n");
		if (outputItems.isEmpty() &&
				chancedOutput.isEmpty() &&
				outputFluids.isEmpty()) 	errors.append(" - Output of recipe shall not be empty!");
		if (errors.length() == 0) {
			Recipe recipe = new Recipe(startEU, EUt, duration, shaped, inputItems, outputItems, chancedOutput, inputFluids, outputFluids, Collections.singletonMap("minTemp", Integer.valueOf(minTemp)));
			reset();
			return recipe;
		} throw new GT_RecipeException(errors.toString());
	}

}
