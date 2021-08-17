package gregtechmod.common.recipe;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author TheDarkDnKTv
 *
 */
public class UnifierRecipeEntry implements Ingredient, IRecipeInput  {
	
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
			throw new IllegalArgumentException("OrePrefix & Materials combination returns null item! " + prefix.get(material));
		this.prefix = prefix;
		this.material = material;
		this.count = count;
	}

	@Override
	public boolean match(ItemStack input) {
		return GT_OreDictUnificator.isItemStackInstanceOf(input, prefix, material);
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
	
	@Override
	public int hashCode() {
		return Objects.hash(prefix, material, count);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UnifierRecipeEntry) {
			UnifierRecipeEntry r = (UnifierRecipeEntry) obj;
			return r.prefix == prefix && r.material == material && r.count == count;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this)
				.append("prefix", prefix)
				.append("material", material)
				.append("count", count)
				.build();
	}

	////////////////
	// IC2 Compat //
	////////////////
	
	@Override
	public boolean matches(ItemStack subject) {
		return match(subject);
	}

	@Override
	public int getAmount() {
		return getCount();
	}

	@Override
	public List<ItemStack> getInputs() {
		return getVariants();
	}
}
