package gregtechmod.common.recipe.maps;

import java.util.List;
import java.util.function.Predicate;

import cpw.mods.fml.common.Loader;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class RecipeMapPrinter extends RecipeMap<SimpleRecipeFactory> {

	public RecipeMapPrinter(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs, 0, 0, 0, 0, new SimpleRecipeFactory());
	}

	@Override
	public Recipe findRecipe(List<ItemStack> itemInputs, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		if (GT_Utility.isStackValid(itemInputs.get(2)) && GT_Utility.isStackValid(itemInputs.get(0)) && GT_Utility.isStackValid(itemInputs.get(1))) {
			if (itemInputs.get(2).getItem() == Items.writable_book || itemInputs.get(2).getItem() == Items.written_book &&
					itemInputs.get(0).getItem() == Items.book
					&& GT_OreDictUnificator.isItemStackDye(Dyes.dyeBlack, itemInputs.get(1))) {
				RecipeFactory<?> fact = factory().EUt(1).duration(200).setShaped(true);
				fact.input(RecipeEntry.singleton(itemInputs.get(0), 1, Match.DAMAGE));
				fact.input(RecipeEntry.singleton(itemInputs.get(1), 1, Match.DAMAGE));
				fact.input(RecipeEntry.singleton(itemInputs.get(2), 0, Match.DAMAGE, Match.NBT));
				fact.output(GT_Utility.copy(1, itemInputs.get(2)));
				return fact.build();
			}
			if (itemInputs.get(2).getItem() == Items.filled_map &&
					itemInputs.get(0).getItem() == Items.map
					&& GT_OreDictUnificator.isItemStackDye(Dyes.dyeBlack, itemInputs.get(1))) {
				RecipeFactory<?> fact = factory().EUt(1).duration(200).setShaped(true);
				fact.input(RecipeEntry.singleton(itemInputs.get(0), 1, Match.DAMAGE));
				fact.input(RecipeEntry.singleton(itemInputs.get(1), 1, Match.DAMAGE));
				fact.input(RecipeEntry.singleton(itemInputs.get(2), 0, Match.DAMAGE, Match.NBT));
				fact.output(GT_Utility.copy(1, itemInputs.get(2)));
				return fact.build();
			}
			if (Loader.isModLoaded("arsmagica2")) {
				if (itemInputs.get(2).getItem().getUnlocalizedName().equals("item.ArsMagica:spell_recipe") &&
						itemInputs.get(0).getItem().getUnlocalizedName().equals("item.ArsMagica:spell_parchment")
						&& itemInputs.get(1).getItem().getUnlocalizedName().equals("item.ArsMagica:rune_blank")) {
					return factory().EUt(4).duration(500)
							.input(RecipeEntry.singleton(itemInputs.get(0), 1, Match.DAMAGE))
							.input(RecipeEntry.singleton(itemInputs.get(1), 5, Match.DAMAGE))
							.output(GT_Utility.copy(1, itemInputs.get(2)))
							.build();
				}
			}
		}
		
		
		return super.findRecipe(itemInputs, fluidInputs, metaChecker);
	}
}
