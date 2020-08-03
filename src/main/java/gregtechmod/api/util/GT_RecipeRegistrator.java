package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Class for Automatic Recipe registering.
 */
public class GT_RecipeRegistrator {
	public static volatile int VERSION = 404;
	/**
	 * List of Materials, which are used in the Creation of Sticks. All Rod Materials are automatically added to this List.
	 */
	public static final List<Materials> sRodMaterialList = new ArrayList<Materials>();
	
	/**
	 * You give this Function a Material and it will scan almost everything for adding recycling Recipes
	 * 
	 * @param aMat a Material, for example an Ingot or a Gem.
	 * @param aOutput the Dust you usually get from macerating aMat
	 * @param aBackSmelting allows to reverse smelt into aMat (false for Gems)
	 * @param aBackMacerating allows to reverse macerate into aOutput
	 */
	public static void registerUsagesForMaterials(ItemStack aMat, ItemStack aOutput, String aPlate, boolean aBackSmelting, boolean aBackMacerating) {
		if (aMat == null || aOutput == null) return;
		aMat = GT_Utility.copy(aMat);
		aOutput = GT_Utility.copy(aOutput);
		ItemStack tStack, tUnificated = GT_OreDictUnificator.get(true, aMat);
		ArrayList<ItemStack> tList;
		String aAssotiation = GT_OreDictUnificator.getAssociation(aMat);
		if (aOutput.stackSize < 1) aOutput.stackSize = 1;
		if (aAssotiation == null || !aAssotiation.startsWith(OrePrefixes.ingot.toString())) aPlate = null;
		if (aPlate != null && GT_OreDictUnificator.getFirstOre(aPlate, 1) == null) aPlate = null;
		
		if (!GT_Utility.areStacksEqual(aMat, new ItemStack(Item.ingotIron, 1))) {
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {aMat, null, aMat, null, aMat, null, null, null, null})) != null)
				if (GT_Utility.areStacksEqual(tStack, new ItemStack(Item.bucketEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {aMat, null, aMat, null, aMat, null, null, null, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, null, null, aMat, null, aMat, null, aMat, null})) != null)
				if (GT_Utility.areStacksEqual(tStack, new ItemStack(Item.bucketEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, aMat, null, aMat, null, aMat, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {aMat, null, aMat, aMat, aMat, aMat, null, null, null})) != null)
				if (GT_Utility.areStacksEqual(tStack, new ItemStack(Item.minecartEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {aMat, null, aMat, aMat, aMat, aMat, null, null, null});
			if ((tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, null, null, aMat, null, aMat, aMat, aMat, aMat})) != null)
				if (GT_Utility.areStacksEqual(tStack, new ItemStack(Item.minecartEmpty, 1)))
					GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, aMat, null, aMat, aMat, aMat, aMat});
		}
		
		if (aBackMacerating || aBackSmelting) {
			sMt1.itemID = aMat.itemID;
			sMt1.stackSize = 1;
			sMt1.setItemDamage(aMat.getItemDamage());
			
			for (ItemStack[] tRecipe : sShapes1) {
				int tAmount1 = 0;
				for (ItemStack tMat : tRecipe) {
					if (tMat == sMt1) tAmount1++;
				}
				for (ItemStack tCrafted : GT_ModHandler.getRecipeOutputs(tRecipe)) {
					if (aBackMacerating) GT_ModHandler.addPulverisationRecipe(tCrafted, GT_Utility.copy(tAmount1, aOutput), null, 0, false);
					if (aBackSmelting) GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tCrafted, GT_Utility.copy(tAmount1, tUnificated));
				}
			}
			
		    for (Materials tMaterial : sRodMaterialList) {
		    	ItemStack tMt2 = GT_OreDictUnificator.getFirstOre(OrePrefixes.stick.toString() + tMaterial, 1), tMt3 = GT_OreDictUnificator.getFirstOre(OrePrefixes.dustSmall.toString() + tMaterial, 2);
		    	if (tMt2 != null) {
					sMt2.itemID = tMt2.itemID;
					sMt2.stackSize = 1;
					sMt2.setItemDamage(tMt2.getItemDamage());
					
					for (int i = 0; i < sShapes1.length; i++) {
						ItemStack[] tRecipe = sShapes1[i];
						
						int tAmount1 = 0, tAmount2 = 0;
						for (ItemStack tMat : tRecipe) {
							if (tMat == sMt1) tAmount1++;
							if (tMat == sMt2) tAmount2++;
						}
						for (ItemStack tCrafted : GT_ModHandler.getSingleNonBlockDamagableRecipeOutputs(tRecipe)) {
							if (aBackMacerating) GT_ModHandler.addPulverisationRecipe(tCrafted, GT_Utility.copy(tAmount1, aOutput), tAmount2>0?GT_Utility.mul(tAmount2, tMt3):null, 100, false);
							if (aBackSmelting) GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tCrafted, GT_Utility.copy(tAmount1, tUnificated));
							
							if (aPlate != null && sShapesA[i] != null && sShapesA[i].length > 1) {
								if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, aAssotiation.replaceFirst(OrePrefixes.ingot.toString(), "")+"."+sShapesA[i][0], true)) {
									if (null != (tStack = GT_ModHandler.removeRecipe(tRecipe))) {
										switch (sShapesA[i].length) {
										case  2: GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[] {sShapesA[i][1]									, s_P.charAt(0), aPlate, s_H.charAt(0), GT_ToolDictNames.craftingToolHardHammer, s_R.charAt(0), OrePrefixes.stick.toString() + tMaterial, s_I.charAt(0), aAssotiation, s_F.charAt(0), GT_ToolDictNames.craftingToolFile, s_W.charAt(0), GT_ToolDictNames.craftingToolWrench}); break;
										case  3: GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[] {sShapesA[i][1], sShapesA[i][2]					, s_P.charAt(0), aPlate, s_H.charAt(0), GT_ToolDictNames.craftingToolHardHammer, s_R.charAt(0), OrePrefixes.stick.toString() + tMaterial, s_I.charAt(0), aAssotiation, s_F.charAt(0), GT_ToolDictNames.craftingToolFile, s_W.charAt(0), GT_ToolDictNames.craftingToolWrench}); break;
										default: GT_ModHandler.addCraftingRecipe(tStack, false, false, true, new Object[] {sShapesA[i][1], sShapesA[i][2], sShapesA[i][3]	, s_P.charAt(0), aPlate, s_H.charAt(0), GT_ToolDictNames.craftingToolHardHammer, s_R.charAt(0), OrePrefixes.stick.toString() + tMaterial, s_I.charAt(0), aAssotiation, s_F.charAt(0), GT_ToolDictNames.craftingToolFile, s_W.charAt(0), GT_ToolDictNames.craftingToolWrench}); break;
										}
									}
								}
							}
						}
					}
		    	}
		    }
		}
	}
	/*
	public static boolean registerStandardOreRecipes(String aOreName, ItemStack aOreStack, int aMultiplier, boolean aForce) {
		if (aOreName == null || aOreStack == null) return false;
		aMultiplier = Math.max(1, aMultiplier);
		GT_RecipeRegistrator tRecipe = sRecipeRegistrators.get(aOreName);
		if (tRecipe != null) return registerStandardOreRecipes(aOreName, aOreStack, aMultiplier, tRecipe);
		if (aForce) {
			if (GregTech_API.DEBUG_MODE) GT_Log.err.println("Warning: Missing Ore Data for '" + aOreName + "'");
			System.out.println("Ore Name: " + aOreName + " !!!Unknown Ore detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information. This Ore will still get added to the List of the IC2-Miner, but with a low Value.");
        	registerStandardOreRecipes(aOreName, aOreStack, aMultiplier, new GT_RecipeRegistrator(aOreName, 1));
		}
		return false;
	}
	*/
	
	private static final ItemStack sMt1 = new ItemStack(0, 1, 0), sMt2 = new ItemStack(0, 1, 1);
	private static final String s_H = "H", s_F = "F", s_I = "I", s_P = "P", s_R = "R", s_W = "W";
	
	private static final ItemStack[][]
		sShapes1 = new ItemStack[][] {
		{sMt1, null, sMt1, sMt1, sMt1, sMt1, null, sMt1, null},
		{null, sMt1, null, sMt1, sMt1, sMt1, sMt1, null, sMt1},
		{sMt1, sMt1, sMt1, sMt1, null, sMt1, null, null, null},
	    {sMt1, null, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1},
	    {sMt1, sMt1, sMt1, sMt1, null, sMt1, sMt1, null, sMt1},
	    {null, null, null, sMt1, null, sMt1, sMt1, null, sMt1},
		{null, sMt1, null, null, sMt1, null, null, sMt2, null},
	    {sMt1, sMt1, sMt1, null, sMt2, null, null, sMt2, null},
	    {null, sMt1, null, null, sMt2, null, null, sMt2, null},
	    {sMt1, sMt1, null, sMt1, sMt2, null, null, sMt2, null},
	    {null, sMt1, sMt1, null, sMt2, sMt1, null, sMt2, null},
	    {sMt1, sMt1, null, null, sMt2, null, null, sMt2, null},
	    {null, sMt1, sMt1, null, sMt2, null, null, sMt2, null},
	    {null, sMt1, null, sMt1, null, null, null, sMt1, sMt2},
	    {null, sMt1, null, null, null, sMt1, sMt2, sMt1, null},
	    {null, sMt1, null, sMt1, null, sMt1, null, null, sMt2},
	    {null, sMt1, null, sMt1, null, sMt1, sMt2, null, null},
	    {null, sMt2, null, null, sMt1, null, null, sMt1, null},
	    {null, sMt2, null, null, sMt2, null, sMt1, sMt1, sMt1},
	    {null, sMt2, null, null, sMt2, null, null, sMt1, null},
	    {null, sMt2, null, sMt1, sMt2, null, sMt1, sMt1, null},
	    {null, sMt2, null, null, sMt2, sMt1, null, sMt1, sMt1},
	    {null, sMt2, null, null, sMt2, null, sMt1, sMt1, null},
	    {sMt1, null, null, null, sMt2, null, null, null, sMt2},
	    {null, null, sMt1, null, sMt2, null, sMt2, null, null},
	    {sMt1, null, null, null, sMt2, null, null, null, null},
	    {null, null, sMt1, null, sMt2, null, null, null, null},
	    {sMt1, sMt2, null, null, null, null, null, null, null},
	    {sMt2, sMt1, null, null, null, null, null, null, null},
	    {sMt1, null, null, sMt2, null, null, null, null, null},
	    {sMt2, null, null, sMt1, null, null, null, null, null},
	    {sMt1, sMt1, sMt1, sMt1, sMt1, sMt1, null, sMt2, null},
		{sMt1, sMt1, null, sMt1, sMt1, sMt2, sMt1, sMt1, null},
		{null, sMt1, sMt1, sMt2, sMt1, sMt1, null, sMt1, sMt1},
		{null, sMt2, null, sMt1, sMt1, sMt1, sMt1, sMt1, sMt1},
		{sMt1, sMt1, sMt1, sMt1, sMt2, sMt1, null, sMt2, null},
		{sMt1, sMt1, null, sMt1, sMt2, sMt2, sMt1, sMt1, null},
		{null, sMt1, sMt1, sMt2, sMt2, sMt1, null, sMt1, sMt1},
		{null, sMt2, null, sMt1, sMt2, sMt1, sMt1, sMt1, sMt1},
		{sMt1, null, null, null, sMt1, null, null, null, null},
		{null, sMt1, null, sMt1, null, null, null, null, null},
		{sMt1, sMt1, null, sMt2, null, sMt1, sMt2, null, null},
		{null, sMt1, sMt1, sMt1, null, sMt2, null, null, sMt2}
	};
	
	private static final String[][] sShapesA = new String[][] {
		null,
		null,
		{"Helmet"						, s_P+s_P+s_P, s_P+s_H+s_P},
		{"ChestPlate"					, s_P+s_H+s_P, s_P+s_P+s_P, s_P+s_P+s_P},
		{"Pants"						, s_P+s_P+s_P, s_P+s_H+s_P, s_P+" "+s_P},
		{"Boots"						, s_P+" "+s_P, s_P+s_H+s_P},
		{"Sword"						, " "+s_P+" ", s_F+s_P+s_H, " "+s_R+" "},
		{"Pickaxe"						, s_P+s_I+s_I, s_F+s_R+s_H, " "+s_R+" "},
		{"Shovel"						, s_F+s_P+s_H, " "+s_R+" ", " "+s_R+" "},
		{"Axe"							, s_P+s_I+s_H, s_P+s_R+" ", s_F+s_R+" "},
		{"Axe"							, s_P+s_I+s_H, s_P+s_R+" ", s_F+s_R+" "},
		{"Hoe"							, s_P+s_I+s_H, s_F+s_R+" ", " "+s_R+" "},
		{"Hoe"							, s_P+s_I+s_H, s_F+s_R+" ", " "+s_R+" "},
		{"Sickle"						, " "+s_P+" ", s_P+s_F+" ", s_H+s_P+s_R},
		{"Sickle"						, " "+s_P+" ", s_P+s_F+" ", s_H+s_P+s_R},
		{"Sickle"						, " "+s_P+" ", s_P+s_F+" ", s_H+s_P+s_R},
		{"Sickle"						, " "+s_P+" ", s_P+s_F+" ", s_H+s_P+s_R},
		{"Sword"						, " "+s_R+" ", s_F+s_P+s_H, " "+s_P+" "},
		{"Pickaxe"						, " "+s_R+" ", s_F+s_R+s_H, s_P+s_I+s_I},
		{"Shovel"						, " "+s_R+" ", " "+s_R+" ", s_F+s_P+s_H},
		{"Axe"							, s_F+s_R+" ", s_P+s_R+" ", s_P+s_I+s_H},
		{"Axe"							, s_F+s_R+" ", s_P+s_R+" ", s_P+s_I+s_H},
		{"Hoe"							, " "+s_R+" ", s_F+s_R+" ", s_P+s_I+s_H},
		{"Hoe"							, " "+s_R+" ", s_F+s_R+" ", s_P+s_I+s_H},
		{"Spear"						, s_P+s_H+" ", s_F+s_R+" ", " "+" "+s_R},
		{"Spear"						, s_P+s_H+" ", s_F+s_R+" ", " "+" "+s_R},
		{"Knive"						, s_H+s_P, s_R+s_F},
		{"Knive"						, s_F+s_H, s_P+s_R},
		{"Knive"						, s_F+s_H, s_P+s_R},
		{"Knive"						, s_P+s_F, s_R+s_H},
		{"Knive"						, s_P+s_F, s_R+s_H},
		null,
		null,
		null,
		null,
		{"WarAxe"						, s_P+s_P+s_P, s_P+s_R+s_P, s_F+s_R+s_H},
		null,
		null,
		null,
		{"Shears"						, s_H+s_P, s_P+s_F},
		{"Shears"						, s_H+s_P, s_P+s_F},
		{"Scythe"						, s_I+s_P+s_H, s_R+s_F+s_P, s_R+" "+" "},
		{"Scythe"						, s_H+s_P+s_I, s_P+s_F+s_R, " "+" "+s_R}
	};
}