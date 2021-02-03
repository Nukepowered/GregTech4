package gregtechmod.common.recipe;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import gregtechmod.api.recipe.Ingredient;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/** An implementation of Ingredient interface
 * 
 * NEVER INCLUDE ANYTHING FROM common PACKAGE IN YOUR MODS!
 * @author TheDarkDnKTv
 *
 */
public class IngredientImpl implements Ingredient {
	
	private HashSet<ItemStack> variants; // check out hashmap impl, may be use stackToInt with hashmap will be faster then iteration
	private EnumSet<MatchOption> options;
	private boolean consumable;
	
	private IngredientImpl() {
		variants = new HashSet<>();
		options = EnumSet.noneOf(MatchOption.class);
	}
	
	public static Ingredient singleton(ItemStack stack, MatchOption...options) {
		return singleton(stack, true, options);
	}
	
	public static Ingredient singleton(ItemStack stack, boolean consumable, MatchOption...options) {
		IngredientImpl result = new IngredientImpl();
		result.variants.add(stack);
		for (MatchOption o : options) result.options.add(o);
		return result;
	}
	
	public static Ingredient oreDict(ItemStack stack) {
		
		return null; // TODO
	}
	
	public static Ingredient oreDict(String name) {
		IngredientImpl result = new IngredientImpl();
		List<ItemStack> entries = OreDictionary.getOres(name);
		if (!entries.isEmpty()) {
			result.variants.addAll(entries);
		} else
			throw new IllegalArgumentException("Wrong ore dictionary key supplied: " + name);
		return result;
	}
	
	@Override
	public boolean match(ItemStack input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ItemStack> getVariants() {
		// TODO Auto-generated method stub
		return new ArrayList<>(variants);
	}

	@Override
	public boolean consumable() {
		return consumable;
	}
	
	public static enum MatchOption {
		/** Check damage match */
		DAMAGE,
		/** Check NBT match */
		NBT;
	}
}
