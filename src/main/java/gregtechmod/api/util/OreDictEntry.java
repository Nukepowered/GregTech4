package gregtechmod.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import net.minecraft.item.ItemStack;

public class OreDictEntry {
	public final String oreDictName;
	public final List<ItemStack> ores;

	
	private OreDictEntry(String oreDictName) {
		this.ores = new ArrayList<>();
		this.oreDictName = oreDictName;
	}
	
	public static OreDictEntry create(String oreDictName, ItemStack...ores) {
		if (GT_Utility.isStringInvalid(oreDictName)) throw new IllegalArgumentException("Invalid string");
		
		OreDictEntry entry = new OreDictEntry(oreDictName);
		if (ores != null && ores.length > 0) entry.ores.addAll(Arrays.asList(ores));
		return entry;
	}
	
	@Override
	public int hashCode() {
		return oreDictName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OreDictEntry) {
			return ((OreDictEntry) obj).oreDictName.equals(oreDictName);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).toString();
	}
}
