package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This is the Core of my OreDict Unification Code
 * 
 * If you just want to use this to unificate your Items, then use the Function in the GregTech_API File
 * 
 * P.S. It is intended to be named "Unificator" and not "Unifier", because that sounds more awesome.
 */
public class GT_OreDictUnificator {
	public static volatile int VERSION = 404;
	
	private static final HashMap<String, ItemStack> sName2OreMap = new HashMap<String, ItemStack>();
	private static final HashMap<Integer, String> sItemhash2NameMap = new HashMap<Integer, String>();
	private static final HashMap<Integer, Materials> sItemhash2MaterialMap = new HashMap<Integer, Materials>();
	private static final ArrayList<Integer> sBlackList = new ArrayList<Integer>();
	
	private static int isRegisteringOre = 0;
	
	/**
	 * The Blacklist just prevents the Item from being Unificated into something else.
	 * Useful if you have things like the Industrial Diamond, which is better than regular Diamond, but also placeable in absolutely all Diamond Recipes.
	 */
	public static void addToBlacklist(ItemStack aStack) {
		if (aStack != null) sBlackList.add(GT_Utility.stackToInt(aStack));
	}
	
	public static void add(OrePrefixes aPrefix, Materials aMaterial, ItemStack aOreStack) {
		add(aPrefix.get(aMaterial), aOreStack);
	}
	
	public static void add(String aOreDictName, ItemStack aOreStack) {
		set(aOreDictName, aOreStack, false);
	}
	
	public static void set(OrePrefixes aPrefix, Materials aMaterial, ItemStack aOreStack) {
		set(aPrefix.get(aMaterial), aOreStack);
	}
	
	public static void set(String aOreDictName, ItemStack aOreStack) {
		set(aOreDictName, aOreStack, true);
	}
	
	public static void set(String aOreDictName, ItemStack aOreStack, boolean aOverwrite) {
		if (aOreDictName == null || aOreDictName.equals("") || aOreDictName.startsWith("itemDust") || aOreStack == null || aOreStack.getItemDamage() < 0) return;
		aOreStack = GT_Utility.copy(1, aOreStack);
		addAssociation(aOreDictName, aOreStack);
		if (sName2OreMap.get(aOreDictName) == null) {
			sName2OreMap.put(aOreDictName, aOreStack);
			registerOre(aOreDictName, aOreStack);
		} else {
			if (aOverwrite && GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.specialunificationtargets, aOreStack, true)) {
				sName2OreMap.remove(aOreDictName);
				sName2OreMap.put(aOreDictName, aOreStack);
			}
			registerOre(aOreDictName, aOreStack);
		}
	}
	
	public static void override(String aOreDictName, ItemStack aOreStack) {
		if (aOreDictName == null || aOreDictName.equals("") || aOreDictName.startsWith("itemDust") || aOreStack == null || aOreStack.getItemDamage() < 0) return;
		if (aOreStack.getUnlocalizedName() == null || aOreStack.getUnlocalizedName().equals("") || GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.specialunificationtargets, aOreStack, true)) set(aOreDictName, aOreStack);
	}
	
	public static ItemStack getFirstOre(String aOreDictName, int aAmount) {
		if (aOreDictName == null || aOreDictName.equals("")) return null;
		if (sName2OreMap.containsKey(aOreDictName)) return get(aOreDictName, null, aAmount, false, false);
		ArrayList<ItemStack> tList = getOres(aOreDictName);
		if (tList.size() > 0) return GT_Utility.copy(aAmount, tList.get(0));
		return null;
	}
	
	public static ItemStack get(String aOreDictName, int aAmount) {
		return get(aOreDictName, null, aAmount, true, true);
	}
	
	public static ItemStack get(String aOreDictName, ItemStack aReplacement, int aAmount) {
		return get(aOreDictName, aReplacement, aAmount, true, true);
	}
	
	public static ItemStack get(OrePrefixes aPrefix, Materials aMaterial, int aAmount) {
		return get(aPrefix, aMaterial, null, aAmount);
	}
	
	public static ItemStack get(OrePrefixes aPrefix, Materials aMaterial, ItemStack aReplacement, int aAmount) {
		return get(aPrefix.get(aMaterial.mMaterialInto), aReplacement, aAmount, false, true);
	}
	
	public static ItemStack get(String aOreDictName, ItemStack aReplacement, int aAmount, boolean aMentionPossibleTypos, boolean aNoInvalidAmounts) {
		if (aNoInvalidAmounts && aAmount < 1) return null;
		if (!sName2OreMap.containsKey(aOreDictName) && aMentionPossibleTypos) GT_Log.err.println("Unknown Key for Unification, Typo? " + aOreDictName);
		return GT_Utility.copy(aAmount, sName2OreMap.get(aOreDictName), aReplacement);
	}
	
	public static ItemStack setStack(ItemStack aOreStack) {
		return setStack(true, aOreStack);
	}
	
	public static ItemStack setStack(boolean aUseBlackList, ItemStack aOreStack) {
		if (aOreStack == null) return null;
		ItemStack tStack = get(true, aOreStack);
		aOreStack.itemID = tStack.itemID;
		aOreStack.setItemDamage(tStack.getItemDamage());
		return aOreStack;
	}
	
	public static ItemStack get(ItemStack aOreStack) {
		return get(true, aOreStack);
	}
	
	public static ItemStack get(boolean aUseBlackList, ItemStack aOreStack) {
		if (aOreStack == null) return null;
		if (aUseBlackList && GT_Utility.isItemStackInList(aOreStack, sBlackList)) return GT_Utility.copy(aOreStack);
		String tName = sItemhash2NameMap.get(GT_Utility.stackToInt(aOreStack));
		ItemStack rStack = null;
		if (tName != null) rStack = sName2OreMap.get(tName);
		if (rStack == null) rStack = aOreStack;
		rStack.setTagCompound(aOreStack.getTagCompound());
		return GT_Utility.copy(aOreStack.stackSize, rStack);
	}
	
	public static void addAssociation(String aOreDictName, ItemStack aOreStack) {
		if (aOreDictName == null || aOreDictName.equals("") || aOreStack == null) return;
		
		if (aOreStack.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE) {
			aOreStack = GT_Utility.copy(aOreStack);
			for (byte i = 0; i < 16; i++) {
				aOreStack.setItemDamage(i);
				sItemhash2NameMap.put(GT_Utility.stackToInt(aOreStack), aOreDictName);
			}
		}
		
		sItemhash2NameMap.put(GT_Utility.stackToInt(aOreStack), aOreDictName);
	}
	
	public static String getAssociation(ItemStack aOreStack) {
		return sItemhash2NameMap.get(GT_Utility.stackToInt(aOreStack));
	}
	
	public static boolean isItemStackInstanceOf(ItemStack aOreStack, String aOreName) {
		if (aOreStack == null || aOreName == null || aOreName.equals("")) return false;
		for (ItemStack tOreStacks : getOres(aOreName))
			if (GT_Utility.areStacksEqual(tOreStacks, aOreStack))
				return true;
		return false;
	}
	
	public static boolean isItemStackDye(ItemStack aStack) {
		if (aStack == null) return false;
		for (Dyes tDye : Dyes.values()) if (tDye != Dyes._NULL && isItemStackInstanceOf(aStack, tDye.toString())) return true;
		return false;
	}
	
    public static boolean registerOre(OrePrefixes aPrefix, Materials aMaterial, ItemStack aStack) {
    	return registerOre(aPrefix.get(aMaterial.mMaterialInto), aStack);
    }
    
    public static boolean registerOre(Object aName, ItemStack aStack) {
    	if (aName == null || aStack == null || aStack.getItem() == null) return false;
    	String tName = aName.toString();
    	if (tName.equals("")) return false;
    	ArrayList<ItemStack> tList = getOres(tName);
    	for (int i = 0; i < tList.size(); i++) if (GT_Utility.areStacksEqual(tList.get(i), aStack)) return false;
    	isRegisteringOre++;
    	OreDictionary.registerOre(tName, GT_Utility.copy(1, aStack));
    	isRegisteringOre--;
    	return true;
    }
    
    public static boolean isRegisteringOres() {
    	return isRegisteringOre > 0;
    }
    
    public static ArrayList<ItemStack> getOres(OrePrefixes aPrefix, Materials aMaterial) {
    	return getOres(aPrefix.get(aMaterial.mMaterialInto));
    }
    
    public static ArrayList<ItemStack> getOres(String aOreName) {
    	ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
    	rList.addAll(OreDictionary.getOres(aOreName));
    	return rList;
    }
}
