package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.OrePrefixes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.init.Items;
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
	public static volatile int VERSION = 408;
	
	private static final HashMap<String, ItemStack> sName2OreMap = new HashMap<String, ItemStack>();
	private static final HashMap<ItemStack, String> sToRegister = new HashMap<ItemStack, String>();
	private static final HashMap<Integer, String> sItemhash2NameMap = new HashMap<Integer, String>();
	private static final ArrayList<ItemStackKey> sBlackList = new ArrayList<ItemStackKey>();
	
	private static int isRegisteringOre = 0, isAddingOre = 0;
	
	public static void activateUnificator() {
		GT_Log.log.info("Registering oredictionary tags...");
		for (Entry<ItemStack, String> entry : sToRegister.entrySet()) {
			OreDictionary.registerOre(entry.getValue(), entry.getKey());
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.info("Registering " + entry.getValue() + " ore");
			}
		}
		
		sToRegister.clear();
	}
	
	/**
	 * The Blacklist just prevents the Item from being Unificated into something else.
	 * Useful if you have things like the Industrial Diamond, which is better than regular Diamond, but also placeable in absolutely all Diamond Recipes.
	 */
	public static void addToBlacklist(ItemStack aStack) {
		if (GT_Utility.isStackValid(aStack)) sBlackList.add(ItemStackKey.from(aStack));
	}
	
	public static boolean isBlacklisted(ItemStack aStack) {
		return GT_Utility.isItemStackInList(aStack, sBlackList);
	}
	
	public static void add(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
		add(aPrefix.get(aMaterial), aStack);
	}
	
	public static void add(Object aName, ItemStack aStack) {
		set(aName, aStack, false, false);
	}
	
	public static void addLater(Object aName, ItemStack aStack) {
		setLater(aName, aStack);
	}
	
	public static void addLater(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
		setLater(aPrefix.get(aMaterial), aStack);
	}
	
	public static void set(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
		set(aPrefix.get(aMaterial), aStack);
	}
	
	public static void set(Object aName, ItemStack aStack) {
		set(aName, aStack, true, false);
	}
	
	public static void set(Object aName, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered) {
		if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack) || Items.feather.getDamage(aStack) == GregTech_API.ITEM_WILDCARD_DAMAGE) return;
		isAddingOre++;
		aStack = GT_Utility.copyAmount(1, aStack);
		if (!aAlreadyRegistered) registerOre(aName.toString(), aStack);
		addAssociation(aName, aStack);
		if (aOverwrite || GT_Utility.isStackInvalid(sName2OreMap.get(aName.toString()))) {
			sName2OreMap.put(aName.toString(), aStack);
		}
		isAddingOre--;
	}
	
	public static void setLater(Object aName, ItemStack aStack) {
		setLater(aName, aStack, true, false);
	}
	
	public static void setLater(Object aName, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered) {
		if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack) || Items.feather.getDamage(aStack) == GregTech_API.ITEM_WILDCARD_DAMAGE) return;
		isAddingOre++;
		aStack = GT_Utility.copyAmount(1, aStack);
		if (!aAlreadyRegistered) sToRegister.put(aStack, aName.toString());
		addAssociation(aName, aStack);
		if (aOverwrite || GT_Utility.isStackInvalid(sName2OreMap.get(aName.toString()))) {
			sName2OreMap.put(aName.toString(), aStack);
		}
		isAddingOre--;
	}
	
	public static synchronized ItemStack getFirstOre(Object aName, long aAmount) {
		if (GT_Utility.isStringInvalid(aName)) return null;
		if (GT_Utility.isStackValid(sName2OreMap.get(aName.toString()))) return GT_Utility.copyAmount(aAmount, sName2OreMap.get(aName.toString()));
		return GT_Utility.copyAmount(aAmount, getOres(aName).toArray(new ItemStack[0]));
	}
	
	public static synchronized ItemStack get(Object aName, long aAmount) {
		return get(aName, null, aAmount, true, true);
	}
	
	public static synchronized ItemStack get(Object aName, ItemStack aReplacement, long aAmount) {
		return get(aName, aReplacement, aAmount, true, true);
	}
	
	public static synchronized ItemStack get(OrePrefixes aPrefix, Object aMaterial, long aAmount) {
		return get(aPrefix, aMaterial, null, aAmount);
	}
	
	public static synchronized ItemStack get(OrePrefixes aPrefix, Object aMaterial, ItemStack aReplacement, long aAmount) {
		return get(aPrefix.get(aMaterial), aReplacement, aAmount, false, true);
	}
	
	public static synchronized ItemStack get(Object aName, ItemStack aReplacement, long aAmount, boolean aMentionPossibleTypos, boolean aNoInvalidAmounts) {
		if (aNoInvalidAmounts && aAmount < 1) return null;
		if (!sName2OreMap.containsKey(aName.toString()) && aMentionPossibleTypos) GT_Log.log.error("Unknown Key for Unification, Typo? " + aName);
		return GT_Utility.copyAmount(aAmount, sName2OreMap.get(aName.toString()), getFirstOre(aName, aAmount), aReplacement);
	}
	
	public static ItemStack[] setStackArray(boolean aUseBlackList, ItemStack... aStacks) {
		for (int i = 0; i < aStacks.length; i++) aStacks[i] = get(aUseBlackList, GT_Utility.copy(aStacks[i]));
		return aStacks;
	}
	
	public static ItemStack[] getStackArray(boolean aUseBlackList, Object... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = get(aUseBlackList, GT_Utility.copy((ItemStack)aStacks[i]));
		return rStacks;
	}
	
	public static ItemStack setStack(ItemStack aStack) {
		return setStack(true, aStack);
	}
	
	public static ItemStack setStack(boolean aUseBlackList, ItemStack aStack) {
		if (GT_Utility.isStackInvalid(aStack)) return aStack;
		ItemStack tStack = get(aUseBlackList, aStack);
		if (GT_Utility.areStacksEqual(aStack, tStack)) return aStack;
		aStack.func_150996_a(tStack.getItem());
		Items.feather.setDamage(aStack, Items.feather.getDamage(tStack));
		return aStack;
	}
	
	public static ItemStack get(ItemStack aStack) {
		return get(true, aStack);
	}
	
	public static ItemStack get(boolean aUseBlackList, ItemStack aStack) {
		if (GT_Utility.isStackInvalid(aStack)) return null;
		if (aUseBlackList && GT_Utility.isItemStackInList(aStack, sBlackList)) return GT_Utility.copy(aStack);
		String tName = sItemhash2NameMap.get(GT_Utility.stackToInt(aStack));
		ItemStack rStack = null;
		if (GT_Utility.isStringValid(tName)) rStack = sName2OreMap.get(tName);
		if (GT_Utility.isStackInvalid(rStack)) return GT_Utility.copy(aStack);
		assert rStack != null;
		rStack.setTagCompound(aStack.getTagCompound());
		return GT_Utility.copyAmount(aStack.stackSize, rStack);
	}
	
	public static void addAssociation(Object aName, ItemStack aStack) {
		if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack)) return;
		if (Items.feather.getDamage(aStack) == GregTech_API.ITEM_WILDCARD_DAMAGE) {
			aStack = GT_Utility.copyAmount(1, aStack);
			for (byte i = 0; i < 16; i++) {
				Items.feather.setDamage(aStack, i);
				sItemhash2NameMap.put(GT_Utility.stackToInt(aStack), aName.toString());
			}
		}
		sItemhash2NameMap.put(GT_Utility.stackToInt(aStack), aName.toString());
	}
	
	public static String getAssociation(ItemStack aStack) {
		return sItemhash2NameMap.get(GT_Utility.stackToInt(aStack));
	}
	
	public static boolean isItemStackInstanceOf(ItemStack aStack, Object aName) {
		if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack)) return false;
		for (ItemStack tOreStack : getOres(aName.toString())) if (GT_Utility.areStacksEqual(tOreStack, aStack, !tOreStack.hasTagCompound())) return true;
		return false;
	}
	
	public static boolean isItemStackDye(ItemStack aStack) {
		if (GT_Utility.isStackInvalid(aStack)) return false;
		for (Dyes tDye : Dyes.values()) if (tDye != Dyes._NULL && isItemStackInstanceOf(aStack, tDye.toString())) return true;
		return false;
	}
	
    public static boolean registerOre(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
    	return registerOre(aPrefix.get(aMaterial), aStack);
    }
    
    public static boolean registerOre(Object aName, ItemStack aStack) {
    	if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack)) return false;
    	String tName = aName.toString();
    	if (tName.equals("")) return false;
    	ArrayList<ItemStack> tList = getOres(tName);
    	for (int i = 0; i < tList.size(); i++) if (GT_Utility.areStacksEqual(tList.get(i), aStack, true)) return false;
    	isRegisteringOre++;
    	OreDictionary.registerOre(tName, GT_Utility.copyAmount(1, aStack));
    	isRegisteringOre--;
    	return true;
    }
    
    public static boolean registerOreLater(OrePrefixes aPrefix, Object aMaterial, ItemStack aStack) {
    	return registerOreLater(aPrefix.get(aMaterial), aStack);
    }
    
    public static boolean registerOreLater(Object aName, ItemStack aStack) {
    	if (GT_Utility.isStringInvalid(aName) || GT_Utility.isStackInvalid(aStack)) return false;
    	String tName = aName.toString();
    	if (tName.equals("")) return false;
    	ArrayList<ItemStack> tList = getOres(tName);
    	for (int i = 0; i < tList.size(); i++) if (GT_Utility.areStacksEqual(tList.get(i), aStack, true)) return false;
    	isRegisteringOre++;
    	sToRegister.put(GT_Utility.copyAmount(1, aStack), tName);
    	isRegisteringOre--;
    	return true;
    }
    
    public static boolean isRegisteringOres() {
    	return isRegisteringOre > 0;
    }
    
    public static boolean isAddingOres() {
    	return isAddingOre > 0;
    }
    
    /**
     * @return a Copy of the OreDictionary.getOres() List
     */
    public static synchronized ArrayList<ItemStack> getOres(OrePrefixes aPrefix, Object aMaterial) {
    	return getOres(aPrefix.get(aMaterial));
    }
    
    /**
     * @return a Copy of the OreDictionary.getOres() List
     */
    public static synchronized ArrayList<ItemStack> getOres(Object aOreName) {
    	String aName = aOreName==null?"":aOreName.toString();
    	ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
    	if (GT_Utility.isStringValid(aName)) rList.addAll(OreDictionary.getOres(aName));
    	return rList;
    }
}
