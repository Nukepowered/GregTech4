package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
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
	public static volatile int VERSION = 404;
	
	/**
	 * If you want to remove Recipes, then set the Index to null, instead of removing the complete Entry!
	 * That's because I have a mapping for quick access, so you should also remove the Mapping of the Recipe.
	 * 
	 * However, every single one of these Recipes has a Config, so you could just disable the Config Setting.
	 */
	public static ArrayList<GT_Recipe> sFusionRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sCentrifugeRecipes	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sElectrolyzerRecipes = new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sGrinderRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sBlastRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sImplosionRecipes	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sSawmillRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sVacuumRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sChemicalRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sDistillationRecipes	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sWiremillRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sBenderRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sAlloySmelterRecipes	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sAssemblerRecipes	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sCannerRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sCNCRecipes			= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sLatheRecipes		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sCutterRecipes		= new ArrayList<GT_Recipe>();
	
	public static ArrayList<GT_Recipe> sDieselFuels			= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sTurbineFuels		= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sHotFuels			= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sDenseLiquidFuels	= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sPlasmaFuels			= new ArrayList<GT_Recipe>();
	public static ArrayList<GT_Recipe> sMagicFuels			= new ArrayList<GT_Recipe>();
	
	public static Map<Long, Integer> pFusionRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pCentrifugeRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pElectrolyzerRecipes	= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pGrinderRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pBlastRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pImplosionRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pSawmillRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pVacuumRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pChemicalRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pDistillationRecipes	= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pWiremillRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pBenderRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pAlloySmelterRecipes	= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pAssemblerRecipes		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pCannerRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pCNCRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pLatheRecipes			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pCutterRecipes			= new HashMap<Long, Integer>();
	
	public static Map<Long, Integer> pDieselFuels			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pTurbineFuels			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pHotFuels				= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pDenseLiquidFuels		= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pPlasmaFuels			= new HashMap<Long, Integer>();
	public static Map<Long, Integer> pMagicFuels			= new HashMap<Long, Integer>();
	
	public static HashMap<ArrayList<GT_Recipe>, Map<Long, Integer>> mRecipeMaps = new HashMap<ArrayList<GT_Recipe>, Map<Long, Integer>>();
	
	static {
    	mRecipeMaps.put(sFusionRecipes			, pFusionRecipes);
    	mRecipeMaps.put(sCentrifugeRecipes		, pCentrifugeRecipes);
    	mRecipeMaps.put(sElectrolyzerRecipes	, pElectrolyzerRecipes);
    	mRecipeMaps.put(sGrinderRecipes			, pGrinderRecipes);
    	mRecipeMaps.put(sBlastRecipes			, pBlastRecipes);
    	mRecipeMaps.put(sImplosionRecipes		, pImplosionRecipes);
    	mRecipeMaps.put(sSawmillRecipes			, pSawmillRecipes);
    	mRecipeMaps.put(sVacuumRecipes			, pVacuumRecipes);
    	mRecipeMaps.put(sChemicalRecipes		, pChemicalRecipes);
    	mRecipeMaps.put(sDistillationRecipes	, pDistillationRecipes);
    	mRecipeMaps.put(sWiremillRecipes		, pWiremillRecipes);
    	mRecipeMaps.put(sBenderRecipes			, pBenderRecipes);
    	mRecipeMaps.put(sAlloySmelterRecipes	, pAlloySmelterRecipes);
    	mRecipeMaps.put(sAssemblerRecipes		, pAssemblerRecipes);
    	mRecipeMaps.put(sCannerRecipes			, pCannerRecipes);
    	mRecipeMaps.put(sCNCRecipes				, pCNCRecipes);
    	mRecipeMaps.put(sLatheRecipes			, pLatheRecipes);
    	mRecipeMaps.put(sCutterRecipes			, pCutterRecipes);
    	
    	mRecipeMaps.put(sDieselFuels			, pDieselFuels);
    	mRecipeMaps.put(sTurbineFuels			, pTurbineFuels);
    	mRecipeMaps.put(sHotFuels				, pHotFuels);
    	mRecipeMaps.put(sDenseLiquidFuels		, pDenseLiquidFuels);
    	mRecipeMaps.put(sPlasmaFuels			, pPlasmaFuels);
    	mRecipeMaps.put(sMagicFuels				, pMagicFuels);
	}
	
	public static void reinit() {
        GT_Log.log.info("GT_Mod: Re-Initializing Item Hashcodes for quick Recipe access.");
        for (Map.Entry<ArrayList<GT_Recipe>, Map<Long, Integer>> tEntry : mRecipeMaps.entrySet()) {
        	tEntry.getValue().clear();
        	for (int i = 0; i < tEntry.getKey().size(); i++) {
        		tEntry.getValue().put(GT_Utility.stacksToLong(tEntry.getKey().get(i).mInput1, tEntry.getKey().get(i).mInput2), i);
        	}
        }
	}
	
	public final ItemStack mInput1, mInput2, mOutput1, mOutput2, mOutput3, mOutput4;
	public final int mDuration, mEUt, mStartEU;
	
	public ItemStack getRepresentativeInput1() {
		return mInput1;
	}
	
	public ItemStack getRepresentativeInput2() {
		return mInput2;
	}
	
	private GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
		aInput1  = GT_OreDictUnificator.get(true, aInput1 );
		aInput2  = GT_OreDictUnificator.get(true, aInput2 );
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		aOutput3 = GT_OreDictUnificator.get(true, aOutput3);
		aOutput4 = GT_OreDictUnificator.get(true, aOutput4);
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
		
		mInput1 = aInput1;
		mInput2 = aInput2;
		mOutput1 = aOutput1;
		mOutput2 = aOutput2;
		mOutput3 = aOutput3;
		mOutput4 = aOutput4;
		mDuration = aDuration;
		mStartEU = aStartEU;
		mEUt = aEUt;
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aStartEU, int aType) {
		this(aInput1, aOutput1, null, null, null, aStartEU, aType);
	}
	
	// aStartEU = EU per Liter! If there is no Liquid for this Object, then it gets multiplied with 1000!
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aStartEU, int aType) {
		this(aInput1, null, aOutput1, aOutput2, aOutput3, aOutput4, 0, 0, Math.max(1, aStartEU));
		
		if (mInput1 != null && aStartEU > 0) {
			switch (aType) {
			// Diesel Generator
			case 0:
				pDieselFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sDieselFuels.size());
				sDieselFuels.add(this);
				break;
			// Gas Turbine
			case 1:
				pTurbineFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sTurbineFuels.size());
				sTurbineFuels.add(this);
				break;
			// Thermal Generator
			case 2:
				pHotFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sHotFuels.size());
				sHotFuels.add(this);
				break;
			// Plasma Generator
			case 4:
				pPlasmaFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sPlasmaFuels.size());
				sPlasmaFuels.add(this);
				break;
			// Magic Generator
			case 5:
				pMagicFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sMagicFuels.size());
				sMagicFuels.add(this);
				break;
			// Fluid Generator. Usually 3. Every wrong Type ends up in the Semifluid Generator
			default:
				pDenseLiquidFuels.put(GT_Utility.stacksToLong(mInput1, mInput2), sDenseLiquidFuels.size());
				sDenseLiquidFuels.add(this);
				break;
			}
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), aEUt, Math.max(Math.min(aStartEU, 160000000), 0));
		if (mInput1 != null && mInput2 != null && findEqualFusionRecipe(mInput1, mInput2) != null) {
			pFusionRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sFusionRecipes.size());
			sFusionRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), 5, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualCentrifugeRecipe(mInput1, mInput2) != null) {
			pCentrifugeRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sCentrifugeRecipes.size());
			sCentrifugeRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualElectrolyzerRecipe(mInput1, mInput2) != null) {
			pElectrolyzerRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sElectrolyzerRecipes.size());
			sElectrolyzerRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
		this(aInput1, null, aOutput1, aOutput2, null, null, aDuration, aEUt, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualLatheRecipe(mInput1, mInput2) != null) {
			pLatheRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sLatheRecipes.size());
			sLatheRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aDuration, ItemStack aOutput1, int aEUt) {
		this(aInput1, null, aOutput1, null, null, null, aDuration, aEUt, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualCutterRecipe(mInput1, mInput2) != null) {
			pCutterRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sCutterRecipes.size());
			sCutterRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, null, 200*aInput1.stackSize, 30, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualSawmillRecipe(mInput1, mInput2) != null) {
			pSawmillRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sSawmillRecipes.size());
			sSawmillRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, 100*aInput1.stackSize, 120, 0);
		checkCellBalance();
		if (mInput1 != null && aInput2 != null && mOutput1 != null && findEqualGrinderRecipe(mInput1, mInput2) != null) {
			pGrinderRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sGrinderRecipes.size());
			sGrinderRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aCellAmount, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		this(aInput1, aCellAmount>0?GT_ModHandler.getEmptyCell(Math.min(64, Math.max(1, aCellAmount))):null, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualDistillationRecipe(mInput1, mInput2) != null) {
			pDistillationRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sDistillationRecipes.size());
			sDistillationRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), aLevel > 0 ? aLevel : 100);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualBlastRecipe(mInput1, mInput2) != null) {
			pBlastRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sBlastRecipes.size());
			sBlastRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		this(aInput1, GT_ModHandler.getIC2Item("industrialTnt", aInput2>0?aInput2<64?aInput2:64:1, new ItemStack(Blocks.tnt, aInput2>0?aInput2<64?aInput2:64:1)), aOutput1, aOutput2, null, null, 20, 30, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualImplosionRecipe(mInput1, mInput2) != null) {
			pImplosionRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sImplosionRecipes.size());
			sImplosionRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, int aDuration, ItemStack aOutput1) {
		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualWiremillRecipe(mInput1, mInput2) != null) {
			pWiremillRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sWiremillRecipes.size());
			sWiremillRecipes.add(this);
		}
	}
	
	public GT_Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aOutput1) {
		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualBenderRecipe(mInput1, mInput2) != null) {
			pBenderRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sBenderRecipes.size());
			sBenderRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualAssemblerRecipe(mInput1, mInput2) != null) {
			pAssemblerRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sAssemblerRecipes.size());
			sAssemblerRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, int aEUt, int aDuration, ItemStack aOutput1) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualAlloySmelterRecipe(mInput1, mInput2) != null) {
			pAlloySmelterRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sAlloySmelterRecipes.size());
			sAlloySmelterRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1, ItemStack aOutput2) {
		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualCannerRecipe(mInput1, mInput2) != null) {
			pCannerRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sCannerRecipes.size());
			sCannerRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), 120, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualVacuumRecipe(mInput1, mInput2) != null) {
			pVacuumRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sVacuumRecipes.size());
			sVacuumRecipes.add(this);
		}
	}
	
	public GT_Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), 30, 0);
		checkCellBalance();
		if (mInput1 != null && mOutput1 != null && findEqualChemicalRecipe(mInput1, mInput2) != null) {
			pChemicalRecipes.put(GT_Utility.stacksToLong(mInput1, mInput2), sChemicalRecipes.size());
			sChemicalRecipes.add(this);
		}
	}
	
	public static int findEqualRecipeIndex(ItemStack aInput1, ItemStack aInput2, boolean aShapeless, ArrayList<GT_Recipe> aList, Map<Long, Integer> aHash) {
		int i = -1;
		if (aShapeless && (i = findEqualRecipeIndex(aInput2, aInput1, false, aList, aHash)) >= 0) {
			return i;
		}
		if (aInput1 == null) return -1;
		
		aInput1 = GT_OreDictUnificator.get(false, aInput1);
		aInput2 = GT_OreDictUnificator.get(false, aInput2);
		
		long k;
		if (aHash.containsKey(k = GT_Utility.stacksToLong(aInput1, aInput2)))
			i = aHash.get(k);
		else
			if (aHash.containsKey(k = GT_Utility.stacksToLong(aInput1, null)))
				i = aHash.get(k);
		
		if (i >= 0 && i < aList.size() && isRecipeInputEqual(aShapeless, false, aInput1, aInput2, aList.get(i))) return i;
		
		boolean temp = false;
		for (i = 0; i < aList.size(); i++) {
			if (isRecipeInputEqual(aShapeless, false, aInput1, aInput2, aList.get(i))) {
				temp = true;
				break;
			}
		}
		
		if (temp) {
			if (GregTech_API.DEBUG_MODE && aList.get(i).mInput1.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE && (aList.get(i).mInput2 == null || aList.get(i).mInput2.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE)) GT_Log.log.error("Didn't find Recipe via Hashcode, did another Mod attempt to remove a Recipe improperly? Hash = " + k + " / " + aInput1.getDisplayName() + " / " + (aInput2==null?"NULL":aInput2.getDisplayName()));
			return i;
		}
		return -1;
	}
	
	public void checkCellBalance() {
		try {
			if (mInput1 == null) return;
			int tInputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mInput1) + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mInput2);
			int tOutputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutput1) + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutput2) + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutput3) + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutput4);
			
			if (tInputAmount < tOutputAmount) {
				if (!Materials.Tin.contains(mInput1) && !Materials.Tin.contains(mInput2))
					GT_Log.log.error("You get more Cells, than you put in? There must be something wrong. " + mInput1.getDisplayName() + " / " + (mInput2==null?"NULL":mInput2.getDisplayName()));
			} else if (tInputAmount > tOutputAmount && !GT_Utility.areStacksEqual(mInput1, GT_ModHandler.getLavaCell(1))) {
				if (!Materials.Tin.contains(mOutput1) && !Materials.Tin.contains(mOutput2) && !Materials.Tin.contains(mOutput3) && !Materials.Tin.contains(mOutput4))
					GT_Log.log.error("You get less Cells, than you put in? My Machines usually don't destroy Cells. " + mInput1.getDisplayName() + " / " + (mInput2==null?"NULL":mInput2.getDisplayName()));
			}
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
	}
	
	public static GT_Recipe findEqualRecipe(ItemStack aInput1, ItemStack aInput2, boolean aShapeless, ArrayList<GT_Recipe> aList, Map<Long, Integer> aHash) {
		int tIndex = findEqualRecipeIndex(aInput1, aInput2, aShapeless, aList, aHash);
		if (tIndex >= 0) return aList.get(tIndex);
		return null;
	}
	
	public boolean isRecipeInputEqual(boolean aShapeless, boolean aDecreaseStacksizeBySuccess, ItemStack aInput1, ItemStack aInput2) {
		if (aShapeless) if (isRecipeInputEqual(false, aDecreaseStacksizeBySuccess, aInput2, aInput1)) return true;
		if (aInput1 == null)
			return false;
		else {
			ItemStack tInput1 = GT_OreDictUnificator.get(false, aInput1), tInput2 = GT_OreDictUnificator.get(false, aInput2);
			if (tInput1.getItem() == mInput1.getItem() && (tInput1.getItemDamage() == mInput1.getItemDamage() || mInput1.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE) && aInput1.stackSize >= mInput1.stackSize) {
				if (mInput2 != null && (aInput2 == null || !(tInput2.getItem() == mInput2.getItem() && (tInput2.getItemDamage() == mInput2.getItemDamage() || mInput2.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE)) || aInput2.stackSize < mInput2.stackSize)) return false;
				if (aDecreaseStacksizeBySuccess) {
					aInput1.stackSize -= mInput1.stackSize;
					if (mInput2 != null) aInput2.stackSize -= mInput2.stackSize;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean isRecipeInputEqual(boolean aShapeless, boolean aDecreaseStacksizeBySuccess, ItemStack aInput1, ItemStack aInput2, GT_Recipe aRecipe) {
		if (aRecipe == null) return false;
		return aRecipe.isRecipeInputEqual(aShapeless, aDecreaseStacksizeBySuccess, aInput1, aInput2);
	}
	
	public static GT_Recipe findEqualWiremillRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sWiremillRecipes, pWiremillRecipes);
	}
	
	public static GT_Recipe findEqualBenderRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sBenderRecipes, pBenderRecipes);
	}

	public static GT_Recipe findEqualAssemblerRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sAssemblerRecipes, pAssemblerRecipes);
	}
	
	public static GT_Recipe findEqualAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sAlloySmelterRecipes, pAlloySmelterRecipes);
	}

	public static GT_Recipe findEqualCannerRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sCannerRecipes, pCannerRecipes);
	}
	
	public static GT_Recipe findEqualDistillationRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sDistillationRecipes, pDistillationRecipes);
	}
	
	public static GT_Recipe findEqualFusionRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sFusionRecipes, pFusionRecipes);
	}

	public static GT_Recipe findEqualCentrifugeRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sCentrifugeRecipes, pCentrifugeRecipes);
	}

	public static GT_Recipe findEqualElectrolyzerRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sElectrolyzerRecipes, pElectrolyzerRecipes);
	}

	public static GT_Recipe findEqualSawmillRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sSawmillRecipes, pSawmillRecipes);
	}
	
	public static GT_Recipe findEqualGrinderRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sGrinderRecipes, pGrinderRecipes);
	}
	
	public static GT_Recipe findEqualBlastRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sBlastRecipes, pBlastRecipes);
	}

	public static GT_Recipe findEqualImplosionRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sImplosionRecipes, pImplosionRecipes);
	}

	public static GT_Recipe findEqualVacuumRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, false, sVacuumRecipes, pVacuumRecipes);
	}
	
	public static GT_Recipe findEqualChemicalRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sChemicalRecipes, pChemicalRecipes);
	}

	public static GT_Recipe findEqualLatheRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sLatheRecipes, pLatheRecipes);
	}
	
	public static GT_Recipe findEqualCutterRecipe(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipe(aInput1, aInput2, true, sCutterRecipes, pCutterRecipes);
	}
	
	public static int findEqualWiremillRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sWiremillRecipes, pWiremillRecipes);
	}
	
	public static int findEqualBenderRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sBenderRecipes, pBenderRecipes);
	}

	public static int findEqualAssemblerRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sAssemblerRecipes, pAssemblerRecipes);
	}
	
	public static int findEqualAlloySmelterRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sAlloySmelterRecipes, pAlloySmelterRecipes);
	}

	public static int findEqualCannerRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sCannerRecipes, pCannerRecipes);
	}
	
	public static int findEqualDistillationRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sDistillationRecipes, pDistillationRecipes);
	}
	
	public static int findEqualFusionRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sFusionRecipes, pFusionRecipes);
	}

	public static int findEqualCentrifugeRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sCentrifugeRecipes, pCentrifugeRecipes);
	}

	public static int findEqualElectrolyzerRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sElectrolyzerRecipes, pElectrolyzerRecipes);
	}

	public static int findEqualSawmillRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sSawmillRecipes, pSawmillRecipes);
	}
	
	public static int findEqualGrinderRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sGrinderRecipes, pGrinderRecipes);
	}
	
	public static int findEqualBlastRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sBlastRecipes, pBlastRecipes);
	}

	public static int findEqualImplosionRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sImplosionRecipes, pImplosionRecipes);
	}

	public static int findEqualVacuumRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, false, sVacuumRecipes, pVacuumRecipes);
	}
	
	public static int findEqualChemicalRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sChemicalRecipes, pChemicalRecipes);
	}

	public static int findEqualLatheRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sLatheRecipes, pLatheRecipes);
	}
	
	public static int findEqualCutterRecipeIndex(ItemStack aInput1, ItemStack aInput2) {
		return findEqualRecipeIndex(aInput1, aInput2, true, sCutterRecipes, pCutterRecipes);
	}
}