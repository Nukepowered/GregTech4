package gregtechmod.api.interfaces;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.OreDictEntry;

public interface IOreRecipeRegistrator {
	/**
	 * Contains a Code Fragment, used in the OrePrefix to register Recipes. Better than using a switch/case, like I did before.
	 * @param aPrefix always != null
	 * @param dictEntry TODO
	 */
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry);
	
	/**
	 * All checks from OrePrefixes and OreDictHandler classes was moved here, execute this method before executing processor
	 * @param aPrefix
	 * @param aMaterial
	 * @return
	 */
	default boolean isExecutable(OrePrefixes aPrefix, Materials aMaterial) {
		return aMaterial != null && !aPrefix.isIgnored(aMaterial) && (aMaterial != Materials._NULL || aPrefix.mIsSelfReferencing || !aPrefix.mIsMaterialBased);
	}
	
	/**
	 * Shortcut, just call it to get material in begining
	 * @param aPrefix
	 * @param entry
	 * @return
	 */
	default Materials getMaterial(OrePrefixes aPrefix, OreDictEntry entry) {
		return (aPrefix == null) ? Materials._NULL : OrePrefixes.getMaterial(entry.oreDictName, aPrefix);
	}
}