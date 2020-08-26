package gregtechmod.api.util;

import java.util.Objects;

import net.minecraft.item.ItemStack;

public class OreDictEntry {
	public final ItemStack ore;
	public final String oreDictName;
	public final String modName;
	
	private OreDictEntry(ItemStack ore, String modName, String oreDictName) {
		this.ore = ore;
		this.modName = modName;
		this.oreDictName = oreDictName;
	}
	
	public static OreDictEntry create(ItemStack ore, String modName, String oreDictName) {
		Objects.requireNonNull(ore);
		Objects.requireNonNull(ore.getItem());
		if (GT_Utility.isStringInvalid(modName) || GT_Utility.isStringInvalid(oreDictName)) throw new IllegalArgumentException("Invalid string");
		
		return new OreDictEntry(ore, modName, oreDictName);
	}
}
