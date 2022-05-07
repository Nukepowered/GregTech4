package gregtechmod.common.recipe.maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.Random;

import gregtechmod.api.recipe.ChancedOutput;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.ChancedStack;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;

import cpw.mods.fml.common.Loader;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class PulverizerRecipeMap extends RecipeMap<SimpleRecipeFactory> {
	
	// Made NOT to spam with throwing exceptions every recipe check
	public final static boolean RC = Loader.isModLoaded("Railcraft"), TE = Loader.isModLoaded("ThermalExpansion");
	
	public PulverizerRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs, new SimpleRecipeFactory());
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> inputs, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		Recipe result = super.findRecipe(inputs, fluidInputs, metaChecker);
		
		for (int i = 0; i < inputs.size() && result == null; i++) {
			ItemStack input = inputs.get(i);
			if (GT_Utility.isStackValid(input)) {
				input = input.copy();

				if (RC) try { // RailCraft recipes
					mods.railcraft.api.crafting.IRockCrusherRecipe recipe = mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.getRecipe(input);
					if (recipe != null) {
						input.stackSize = recipe.getInput().stackSize;
						if (recipe.getPossibleOuputs().size() <= maxOutputs) {
							List<ChancedOutput> chanced = new ArrayList<>();
							List<ItemStack> outputs = new ArrayList<>();
							
							for (Entry<ItemStack, Float> e : recipe.getOutputs()) {
								if (e.getValue() == 1.0F) {
									outputs.add(e.getKey().copy());
								} else {
									int chance = (int)(e.getValue() * 10000);	// This weird thingy was invented to find duplicates and create PiecedStack
									ChancedStack val = new ChancedStack(e.getKey(), chance == 0 ? 1 : chance);
									Optional<ChancedOutput> opt = chanced.stream().filter(a -> a.equals(val)).findFirst();
									if (opt.isPresent()) {
										ChancedOutput listStack = opt.get();
										chanced.remove(listStack);
										ItemStack total = val.getStack();
										total.stackSize += listStack.getStack().stackSize;
										chanced.add(new PiecedChancedStack(total, listStack.getChance()));
									} else chanced.add(val);
								}
							}

							return new Recipe(0, 32, 500, false, 							// 25 seconds per 32 EU/t will be total 16,000EU
									Collections.singleton(RecipeEntry.singleton(input, Match.STRICT)),	// In RC Recipe running 5 seconds and with consumption 160RF total 16,000RF = 64,000EU
									outputs,
									chanced);
						}
					}
				} catch (Throwable e) {}
				
				try { // IC2 recipes
					ItemStack copy2 = input.copy();
					ic2.api.recipe.RecipeOutput recipe = ic2.api.recipe.Recipes.macerator.getOutputFor(copy2, true);
					if (recipe != null) {
						input.stackSize = input.stackSize - copy2.stackSize;
						return new Recipe(0, 2, 400, false,
								Collections.singleton(RecipeEntry.singleton(input, Match.STRICT)),
								GT_Utility.copy(recipe.items),
								Collections.emptyList());
					}
				} catch (Throwable e) {}
				
				if (TE) try {  // ThermalExpansion recipes
					cofh.thermalexpansion.api.crafting.recipes.IPulverizerRecipe recipe = cofh.thermalexpansion.util.crafting.PulverizerManager.getRecipe(input);
		    		if (recipe != null) {
		    			ItemStack secondary = recipe.getSecondaryOutput();
		    			input.stackSize = recipe.getInput().stackSize;
		    			return new Recipe(0, 16, recipe.getEnergy() / 16, false,
		    					Collections.singleton(RecipeEntry.singleton(input, Match.STRICT)),
		    					Collections.singleton(recipe.getPrimaryOutput()),
		    					secondary == null ? Collections.emptyList() : Collections.singleton(new ChancedStack(secondary, recipe.getSecondaryOutputChance() * 100)));
		    		}
				} catch(Throwable e) {}
			}
		}

		return result;
	}
	
	/**
	 * Instead of ChancedStack will check chance for every single item in stack
	 * @author TheDarkDnKTv
	 *
	 */
	private static class PiecedChancedStack extends ChancedStack {

		public PiecedChancedStack(ItemStack stack, int chance) {
			super(stack, chance);
		}
		
		@Override
		public Optional<ItemStack> get(Random random) {
			ItemStack stack = this.getStack();
			int amount = stack.stackSize;
			stack.stackSize = 0;
			for (int i = 0; i < amount; i++) 
				if (random.nextInt(SimpleRecipeFactory.MAX_CHANCE) <= getChance())
					stack.stackSize++;
			
			return Optional.ofNullable(stack.stackSize > 0 ? stack : null);
		}
	}
}
