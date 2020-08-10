package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This File contains the functions used for Recipes. Please do not include this File AT ALL in your Moddownload as it ruins compatibility
 * This is just the Core of my Recipe System, if you just want to GET the Recipes I add, then you can access this File.
 * Do NOT add Recipes using the Constructors inside this Class, The GregTech_API File calls the correct Functions for these Constructors.
 * 
 * I know this File causes some Errors, because of missing Main Functions, but if you just need to compile Stuff, then remove said erroreous Functions.
 */
public class GT_Recipe {
	public static volatile int VERSION = 408;
	
	public static final ArrayList<GT_Recipe> sFusionRecipes				= new ArrayList<GT_Recipe>(  50);
	public static final ArrayList<GT_Recipe> sCentrifugeRecipes			= new ArrayList<GT_Recipe>(1000);
	public static final ArrayList<GT_Recipe> sElectrolyzerRecipes		= new ArrayList<GT_Recipe>( 200);
	public static final ArrayList<GT_Recipe> sGrinderRecipes			= new ArrayList<GT_Recipe>( 200);
	public static final ArrayList<GT_Recipe> sBlastRecipes				= new ArrayList<GT_Recipe>( 300);
	public static final ArrayList<GT_Recipe> sImplosionRecipes			= new ArrayList<GT_Recipe>(  50);
	public static final ArrayList<GT_Recipe> sSawmillRecipes			= new ArrayList<GT_Recipe>( 100);
	public static final ArrayList<GT_Recipe> sVacuumRecipes				= new ArrayList<GT_Recipe>( 100);
	public static final ArrayList<GT_Recipe> sChemicalRecipes			= new ArrayList<GT_Recipe>( 100);
	public static final ArrayList<GT_Recipe> sDistillationRecipes		= new ArrayList<GT_Recipe>(  50);
	public static final ArrayList<GT_Recipe> sWiremillRecipes			= new ArrayList<GT_Recipe>(  50);
	public static final ArrayList<GT_Recipe> sBenderRecipes				= new ArrayList<GT_Recipe>( 400);
	public static final ArrayList<GT_Recipe> sAlloySmelterRecipes		= new ArrayList<GT_Recipe>(2000);
	public static final ArrayList<GT_Recipe> sAssemblerRecipes			= new ArrayList<GT_Recipe>( 300);
	public static final ArrayList<GT_Recipe> sCannerRecipes				= new ArrayList<GT_Recipe>( 300);
	public static final ArrayList<GT_Recipe> sCNCRecipes				= new ArrayList<GT_Recipe>( 100);
	public static final ArrayList<GT_Recipe> sLatheRecipes				= new ArrayList<GT_Recipe>( 400);
	public static final ArrayList<GT_Recipe> sCutterRecipes				= new ArrayList<GT_Recipe>( 200);
	public static final ArrayList<GT_Recipe> sExtruderRecipes			= new ArrayList<GT_Recipe>(1000);
	public static final ArrayList<GT_Recipe> sHammerRecipes				= new ArrayList<GT_Recipe>( 200);
	
	public static final ArrayList<GT_Recipe> sDieselFuels				= new ArrayList<GT_Recipe>();
	public static final ArrayList<GT_Recipe> sTurbineFuels				= new ArrayList<GT_Recipe>();
	public static final ArrayList<GT_Recipe> sHotFuels					= new ArrayList<GT_Recipe>();
	public static final ArrayList<GT_Recipe> sDenseLiquidFuels			= new ArrayList<GT_Recipe>();
	public static final ArrayList<GT_Recipe> sPlasmaFuels				= new ArrayList<GT_Recipe>();
	public static final ArrayList<GT_Recipe> sMagicFuels				= new ArrayList<GT_Recipe>();
	
	/** It is an IdentityHashMap, because it uses a List as Key, and since that List changes (and therefore the Result of the equals Method), the Key is not secure, while the Identity is. */
	private static final IdentityHashMap<List<GT_Recipe>, HashMap<Integer, List<GT_Recipe>>> sRecipeMappings = new IdentityHashMap<List<GT_Recipe>, HashMap<Integer, List<GT_Recipe>>>();
	
	public static void reInit() {
        GT_Log.out.println("GT_Mod: Re-Unificating Recipes.");
        for (Entry<List<GT_Recipe>, HashMap<Integer, List<GT_Recipe>>> tMapEntry : sRecipeMappings.entrySet()) {
        	HashMap<Integer, List<GT_Recipe>> tMap = tMapEntry.getValue();
        	if (tMap != null) tMap.clear();
        	for (GT_Recipe tRecipe : tMapEntry.getKey()) {
            	GT_OreDictUnificator.setStackArray(true, tRecipe.mInputs);
            	GT_OreDictUnificator.setStackArray(true, tRecipe.mOutputs);
            	if (tMap != null) tRecipe.addToMap(tMap);
        	}
        }
	}
	
	/**
	 * If you want to change the Output, feel free to modify or replace the ItemStack Array, for Inputs, please add a new Recipe, because of the HashMaps.
	 */
	public ItemStack[] mInputs, mOutputs;
	public int mDuration, mEUt, mStartEU;
	
	/**
	 * Use this to just disable a specific Recipe, but the Config enables that already for every single Recipe.
	 */
	public boolean mEnabled = true;
	
	public ItemStack getRepresentativeInput1() {
		return getRepresentativeInput(0);
	}
	
	public ItemStack getRepresentativeInput2() {
		return getRepresentativeInput(1);
	}
	
	public ItemStack getRepresentativeInput(int aIndex) {
		if (aIndex < 0 || aIndex >= mInputs.length) return null;
		return GT_Utility.copy(mInputs[aIndex]);
	}
	
	public ItemStack getOutput(int aIndex) {
		if (aIndex < 0 || aIndex >= mOutputs.length) return null;
		return GT_Utility.copy(mOutputs[aIndex]);
	}
	
	private final void addToMap(HashMap<Integer, List<GT_Recipe>> aMap) {
		for (ItemStack tStack : mInputs) if (tStack != null) {
			Integer tIntStack = GT_Utility.stackToInt(tStack);
			List<GT_Recipe> tList = aMap.get(tIntStack);
			if (tList == null) aMap.put(tIntStack, tList = new ArrayList<GT_Recipe>(2));
			tList.add(this);
		}
	}
	
	private final void addToLists(List<GT_Recipe> aList) {
		HashMap<Integer, List<GT_Recipe>> aMap = sRecipeMappings.get(aList);
		if (aMap == null) sRecipeMappings.put(aList, aMap = new HashMap<Integer, List<GT_Recipe>>());
		aList.add(this);
		addToMap(aMap);
	}
	
	public static GT_Recipe findEqualRecipe(boolean aShapeless, boolean aNotUnificated, List<GT_Recipe> aList, ItemStack... aInputs) {
		if (aInputs.length < 1) return null;
		HashMap<Integer, List<GT_Recipe>> tMap = sRecipeMappings.get(aList);
		if (aNotUnificated) GT_OreDictUnificator.setStackArray(true, aInputs);
		if (tMap == null) {
			for (GT_Recipe tRecipe : aList) if (tRecipe.isRecipeInputEqual(aShapeless, false, aInputs)) return tRecipe.mEnabled?tRecipe:null;
		} else {
			for (ItemStack tStack : aInputs) if (tStack != null) {
				aList = tMap.get(GT_Utility.stackToInt(tStack));
				if (aList != null) for (GT_Recipe tRecipe : aList) if (tRecipe.isRecipeInputEqual(aShapeless, false, aInputs)) return tRecipe.mEnabled?tRecipe:null;
				aList = tMap.get(GT_Utility.stackToWildcard(tStack));
				if (aList != null) for (GT_Recipe tRecipe : aList) if (tRecipe.isRecipeInputEqual(aShapeless, false, aInputs)) return tRecipe.mEnabled?tRecipe:null;
			}
		}
		return null;
	}
	
	public void checkCellBalance() {
		if (!GregTech_API.SECONDARY_DEBUG_MODE || mInputs.length < 1) return;
		
		int tInputAmount  = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mInputs);
		int tOutputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutputs);
		
		if (tInputAmount < tOutputAmount) {
			if (!Materials.Tin.contains(mInputs)) {
				GT_Log.err.println("You get more Cells, than you put in? There must be something wrong.");
				new Exception().printStackTrace(GT_Log.err);
			}
		} else if (tInputAmount > tOutputAmount) {
			if (!Materials.Tin.contains(mOutputs)) {
				GT_Log.err.println("You get less Cells, than you put in? GT Machines usually don't destroy Cells.");
				new Exception().printStackTrace(GT_Log.err);
			}
		}
	}
	
	public boolean isRecipeInputEqual(boolean aShapeless, boolean aDecreaseStacksizeBySuccess, ItemStack... aInputs) {
		if (aInputs.length < 1 || mInputs.length < 1) return false;
		if (aShapeless && aInputs.length > 1 && aInputs[1] != null && isRecipeInputEqual(false, aDecreaseStacksizeBySuccess, aInputs[1], aInputs[0])) return true;
		
		if (aInputs[0] != null && ((GT_Utility.areUnificationsEqual(aInputs[0], mInputs[0], true) || GT_Utility.areUnificationsEqual(GT_OreDictUnificator.get(false, aInputs[0]), mInputs[0], true)) && aInputs[0].stackSize >= mInputs[0].stackSize))
		if (mInputs.length < 2 || (aInputs.length > 1 && (GT_Utility.areUnificationsEqual(aInputs[1], mInputs[1], true) || GT_Utility.areUnificationsEqual(GT_OreDictUnificator.get(false, aInputs[1]), mInputs[1], true)) && aInputs[1].stackSize >= mInputs[1].stackSize)) {
			if (aDecreaseStacksizeBySuccess) {
				aInputs[0].stackSize -= mInputs[0].stackSize;
				if (mInputs.length > 1) aInputs[1].stackSize -= mInputs[1].stackSize;
			}
			return true;
		}
		return false;
	}
	
	public static boolean addRecipe(List<GT_Recipe> aList, boolean aShapeless, ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
		return addRecipe(aList, aShapeless, new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt, aStartEU));
	}
	
	public static boolean addRecipe(List<GT_Recipe> aList, boolean aShapeless, GT_Recipe aRecipe) {
		if (findEqualRecipe(aShapeless, false, aList, aRecipe.mInputs) != null) return false;
		aRecipe.addToLists(aList);
		return true;
	}
	
	private GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
		aInput1  = GT_OreDictUnificator.get(true, aInput1);
		aInput2  = GT_OreDictUnificator.get(true, aInput2);
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		aOutput3 = GT_OreDictUnificator.get(true, aOutput3);
		aOutput4 = GT_OreDictUnificator.get(true, aOutput4);
		
		if (aInput1 != null && aInput1.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE) {
			if (GT_Utility.areStacksEqual(aInput1, aOutput1)) {
				if (aInput1.stackSize >= aOutput1.stackSize) {
					aInput1.stackSize -= aOutput1.stackSize;
					aOutput1 = null;
				} else {
					aOutput1.stackSize -= aInput1.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput1, aOutput2)) {
				if (aInput1.stackSize >= aOutput2.stackSize) {
					aInput1.stackSize -= aOutput2.stackSize;
					aOutput2 = null;
				} else {
					aOutput2.stackSize -= aInput1.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput1, aOutput3)) {
				if (aInput1.stackSize >= aOutput3.stackSize) {
					aInput1.stackSize -= aOutput3.stackSize;
					aOutput3 = null;
				} else {
					aOutput3.stackSize -= aInput1.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput1, aOutput4)) {
				if (aInput1.stackSize >= aOutput4.stackSize) {
					aInput1.stackSize -= aOutput4.stackSize;
					aOutput4 = null;
				} else {
					aOutput4.stackSize -= aInput1.stackSize;
				}
			}
		}
		
		if (aInput2 != null && aInput2.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE) {
			if (GT_Utility.areStacksEqual(aInput2, aOutput1)) {
				assert aOutput1 != null;
				if (aInput2.stackSize >= aOutput1.stackSize) {
					aInput2.stackSize -= aOutput1.stackSize;
					aOutput1 = null;
				} else {
					aOutput1.stackSize -= aInput2.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput2, aOutput2)) {
				assert aOutput2 != null;
				if (aInput2.stackSize >= aOutput2.stackSize) {
					aInput2.stackSize -= aOutput2.stackSize;
					aOutput2 = null;
				} else {
					aOutput2.stackSize -= aInput2.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput2, aOutput3)) {
				assert aOutput3 != null;
				if (aInput2.stackSize >= aOutput3.stackSize) {
					aInput2.stackSize -= aOutput3.stackSize;
					aOutput3 = null;
				} else {
					aOutput3.stackSize -= aInput2.stackSize;
				}
			}
			if (GT_Utility.areStacksEqual(aInput2, aOutput4)) {
				assert aOutput4 != null;
				if (aInput2.stackSize >= aOutput4.stackSize) {
					aInput2.stackSize -= aOutput4.stackSize;
					aOutput4 = null;
				} else {
					aOutput4.stackSize -= aInput2.stackSize;
				}
			}
		}
		
		for (byte i = 64; i > 1; i--) if (aDuration / i > 0) {
			if (aInput1  == null || aInput1 .stackSize % i == 0)
			if (aInput2  == null || aInput2 .stackSize % i == 0)
			if (aOutput1 == null || aOutput1.stackSize % i == 0)
			if (aOutput2 == null || aOutput2.stackSize % i == 0)
			if (aOutput3 == null || aOutput3.stackSize % i == 0)
			if (aOutput4 == null || aOutput4.stackSize % i == 0) {
				if (aInput1  != null) aInput1 .stackSize /= i;
				if (aInput2  != null) aInput2 .stackSize /= i;
				if (aOutput1 != null) aOutput1.stackSize /= i;
				if (aOutput2 != null) aOutput2.stackSize /= i;
				if (aOutput3 != null) aOutput3.stackSize /= i;
				if (aOutput4 != null) aOutput4.stackSize /= i;
				aDuration /= i;
			}
		}
		
		if (aInput1 == null) mInputs = new ItemStack [] {}; else if (aInput2 == null) mInputs = new ItemStack[] {aInput1}; else mInputs = new ItemStack[] {aInput1, aInput2};
		mOutputs = new ItemStack[] {aOutput1, aOutput2, aOutput3, aOutput4};
		mDuration = aDuration;
		mStartEU = aStartEU;
		mEUt = aEUt;
		
//		checkCellBalance();
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aStartEU, int aType) {
		this(aInput1, aOutput1, null, null, null, aStartEU, aType);
	}
	
	// aStartEU = EU per Liter! If there is no Liquid for this Object, then it gets multiplied with 1000!
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aStartEU, int aType) {
		this(aInput1, null, aOutput1, aOutput2, aOutput3, aOutput4, 0, 0, Math.max(1, aStartEU));
		
		if (mInputs.length > 0 && aStartEU > 0) {
			switch (aType) {
			// Diesel Generator
			case 0:
				addToLists(sDieselFuels);
				break;
			// Gas Turbine
			case 1:
				addToLists(sTurbineFuels);
				break;
			// Thermal Generator
			case 2:
				addToLists(sHotFuels);
				break;
			// Plasma Generator
			case 4:
				addToLists(sPlasmaFuels);
				break;
			// Magic Generator
			case 5:
				addToLists(sMagicFuels);
				break;
			// Fluid Generator. Usually 3. Every wrong Type ends up in the Semifluid Generator
			default:
				addToLists(sDenseLiquidFuels);
				break;
			}
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), aEUt, Math.max(Math.min(aStartEU, 160000000), 0));
		if (mInputs.length > 1 && findEqualRecipe(true, false, sFusionRecipes, mInputs) == null) {
			addToLists(sFusionRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), 5, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sCentrifugeRecipes, mInputs) == null) {
			addToLists(sCentrifugeRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sElectrolyzerRecipes, mInputs) == null) {
			addToLists(sElectrolyzerRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
		this(aInput1, null, aOutput1, aOutput2, null, null, aDuration, aEUt, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sLatheRecipes, mInputs[0]) == null) {
			addToLists(sLatheRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aDuration, ItemStack aOutput1, int aEUt) {
		this(aInput1, null, aOutput1, null, null, null, aDuration, aEUt, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sCutterRecipes, mInputs[0]) == null) {
			addToLists(sCutterRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, null, 200*aInput1.stackSize, 30, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sSawmillRecipes, mInputs) == null) {
			addToLists(sSawmillRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, 100*aInput1.stackSize, 120, 0);
		if (mInputs.length > 0 && aInput2 != null && mOutputs[0] != null && findEqualRecipe(false, false, sGrinderRecipes, mInputs) == null) {
			addToLists(sGrinderRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aCellAmount, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		this(aInput1, aCellAmount>0?GT_Items.Cell_Empty.get(Math.min(64, Math.max(1, aCellAmount))):null, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sDistillationRecipes, mInputs) == null) {
			addToLists(sDistillationRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), aLevel > 0 ? aLevel : 100);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sBlastRecipes, mInputs) == null) {
			addToLists(sBlastRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		this(aInput1, GT_ModHandler.getIC2Item("industrialTnt", aInput2>0?aInput2<64?aInput2:64:1, new ItemStack(Block.tnt, aInput2>0?aInput2<64?aInput2:64:1)), aOutput1, aOutput2, null, null, 20, 30, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sImplosionRecipes, mInputs) == null) {
			addToLists(sImplosionRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, int aDuration, ItemStack aOutput1) {
		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sWiremillRecipes, mInputs[0]) == null) {
			addToLists(sWiremillRecipes);
		}
	}
	
	public GT_Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aOutput1) {
		this(aInput1, GT_Items.Circuit_Integrated.getWithDamage(0, aInput1.stackSize), aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, false, sBenderRecipes, mInputs) == null) {
			addToLists(sBenderRecipes);
		}
	}
	
	public GT_Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aShape, ItemStack aOutput1) {
		this(aInput1, aShape, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 1 && mOutputs[0] != null && findEqualRecipe(false, false, sExtruderRecipes, mInputs) == null) {
			addToLists(sExtruderRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sAssemblerRecipes, mInputs) == null) {
			addToLists(sAssemblerRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, int aEUt, int aDuration, ItemStack aOutput1) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sAlloySmelterRecipes, mInputs) == null) {
			addToLists(sAlloySmelterRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1, ItemStack aOutput2) {
		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sCannerRecipes, mInputs) == null) {
			addToLists(sCannerRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), 120, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sVacuumRecipes, mInputs[0]) == null) {
			addToLists(sVacuumRecipes);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), 30, 0);
		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, false, sChemicalRecipes, mInputs) == null) {
			addToLists(sChemicalRecipes);
		}
	}
}