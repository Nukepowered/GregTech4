package gregtechmod.common.recipe;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author TheDarkDnKTv
 *
 */
public class UnifierRecipeEntry implements Ingredient {
	
	private final OrePrefixes prefix;
	private final Materials material;
	private final int count;
	
	public UnifierRecipeEntry(OrePrefixes prefix, Materials material) {
		this(prefix, material, 1);
	}
	
	public UnifierRecipeEntry(OrePrefixes prefix, Materials material, int count) {
		if (prefix == null || material == null) 
			throw new IllegalArgumentException("Prefix: " + prefix + ", material: " + material);
		if (!OreDictionary.doesOreNameExist(prefix.get(material)))
			throw new IllegalArgumentException("OrePrefix & Materials combination returns null item!");
		this.prefix = prefix;
		this.material = material;
		this.count = count;
	}

	@Override
	public boolean match(ItemStack input) {
//		GT_OreDictUnificator. // FIXME Fix oredict unificator
		return false;
	}

	@Override
	public List<ItemStack> getVariants() {
		List<ItemStack> copies = GT_Utility.copy(GT_OreDictUnificator.getOres(prefix, material));
		for (ItemStack stack : copies) stack.stackSize = count;
		return copies;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public boolean isWildcard() {
		return false;
	}
}
