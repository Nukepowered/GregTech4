package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.RecipeHandler.IRecipeMatcher;
import gregtechmod.common.RecipeHandler.InventoryRecipeMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Class for Automatic Recipe registering.
 */
public class GT_RecipeRegistrator {
	public static volatile int VERSION = 416;
	/**
	 * List of Materials, which are used in the Creation of Sticks. All Rod Materials are automatically added to this List.
	 */
	public static final List<Materials> sRodMaterialList = new ArrayList<Materials>();
	
	/**
	 * @param aStack the stack to be recycled.
	 * @param aMaterial the Material.
	 * @param aMaterialAmount the amount of it in Material Units.
	 * @param aAllowAlloySmelter if it is allowed to be recycled inside the Alloy Smelter.
	 */
	public static void registerBasicReverseMaceratingAndSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount, boolean aAllowAlloySmelter) {
		registerBasicReverseMacerating(aStack, aMaterial, aMaterialAmount);
		registerBasicReverseSmelting(aStack, aMaterial, aMaterialAmount, aAllowAlloySmelter);
	}
	
	/**
	 * @param aStack the stack to be recycled.
	 * @param aMaterial the Material.
	 * @param aMaterialAmount the amount of it in Material Units.
	 */
	public static void registerBasicReverseMaceratingAndSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount) {
		registerBasicReverseMaceratingAndSmelting(aStack, aMaterial, aMaterialAmount, true);
	}
	
	/**
	 * @param aStack the stack to be recycled.
	 * @param aMaterial the Material.
	 * @param aMaterialAmount the amount of it in Material Units.
	 * @param aAllowAlloySmelter if it is allowed to be recycled inside the Alloy Smelter.
	 */
	public static void registerBasicReverseSmelting(ItemStack aStack, Materials aMaterial, long aMaterialAmount, boolean aAllowAlloySmelter) {
		if (aStack == null || aMaterial == null || aMaterialAmount <= 0) return;
		aMaterialAmount /= aStack.stackSize;
		ItemStack tStack1 = GT_OreDictUnificator.get(OrePrefixes.ingot , aMaterial, 1), tStack2 = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 1);
		if (tStack1 != null && aMaterialAmount % GregTech_API.MATERIAL_UNIT == 0) {
			if (aAllowAlloySmelter)
				GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(	GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot				, aMaterial, (int)( aMaterialAmount      / GregTech_API.MATERIAL_UNIT)));
			else
				GT_ModHandler.addSmeltingRecipe(					GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot				, aMaterial, (int)( aMaterialAmount      / GregTech_API.MATERIAL_UNIT)));
		} else {
			boolean temp = true;
			byte tLimit = aAllowAlloySmelter?(byte)aStack.getMaxStackSize():1;
			if (tStack2 != null && temp) for (byte i = 1; i <= tLimit && (aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT <= 64; i++) if (aMaterialAmount*9*i >= GregTech_API.MATERIAL_UNIT && (aMaterialAmount*9*i) % GregTech_API.MATERIAL_UNIT == 0 && (tStack1 == null || (aMaterialAmount*i) % GregTech_API.MATERIAL_UNIT != 0)) {
				if (aAllowAlloySmelter)
					GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(	GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT, tStack2));
				else
					GT_ModHandler.addSmeltingRecipe(					GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT, tStack2));
				temp = false; break;
			}
			if (tStack1 != null && temp) for (byte i = 1; i <= tLimit && (aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT <= 64; i++) if (aMaterialAmount * i >= GregTech_API.MATERIAL_UNIT && (aMaterialAmount * i) % GregTech_API.MATERIAL_UNIT == 0) {
				if (aAllowAlloySmelter)
					GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(	GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT, tStack1));
				else
					GT_ModHandler.addSmeltingRecipe(					GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT, tStack1));
				temp = false; break;
			}
			if (tStack2 != null && temp) for (byte i = 1; i <= tLimit && (aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT <= 64; i++) if (aMaterialAmount*9*i >= GregTech_API.MATERIAL_UNIT) {
				if (aAllowAlloySmelter)
					GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(	GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT, tStack2));
				else
					GT_ModHandler.addSmeltingRecipe(					GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount*9*i) / GregTech_API.MATERIAL_UNIT, tStack2));
				temp = false; break;
			}
			if (tStack1 != null && temp) for (byte i = 1; i <= tLimit && (aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT <= 64; i++) if (aMaterialAmount * i >= GregTech_API.MATERIAL_UNIT) {
				if (aAllowAlloySmelter)
					GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(	GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT, tStack1));
				else
					GT_ModHandler.addSmeltingRecipe(					GT_Utility.copyAmount(i, aStack), GT_Utility.copyAmount((aMaterialAmount * i) / GregTech_API.MATERIAL_UNIT, tStack1));
				temp = false; break;
			}
		}
	}
	
	/**
	 * @param aStack the stack to be recycled.
	 * @param aMaterial the Material.
	 * @param aMaterialAmount the amount of it in Material Units.
	 */
	public static void registerBasicReverseMacerating(ItemStack aStack, Materials aMaterial, long aMaterialAmount) {
		if (aStack == null || aMaterial == null || aMaterialAmount <= 0) return;
		aMaterialAmount /= aStack.stackSize;
		if ( aMaterialAmount      %  GregTech_API.MATERIAL_UNIT == 0) {
			GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust					, aMaterial, (int)( aMaterialAmount      / GregTech_API.MATERIAL_UNIT)), null, 0, true);
		} else
		if ((aMaterialAmount * 4) %  GregTech_API.MATERIAL_UNIT == 0) {
			GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall				, aMaterial, (int)((aMaterialAmount * 4) / GregTech_API.MATERIAL_UNIT)), null, 0, true);
		} else
		if ((aMaterialAmount * 9) >= GregTech_API.MATERIAL_UNIT) {
			if (!GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustTiny			, aMaterial, (int)((aMaterialAmount * 9) / GregTech_API.MATERIAL_UNIT)), null, 0, true)) {
				if (!GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall	, aMaterial, (int)((aMaterialAmount * 4) / GregTech_API.MATERIAL_UNIT)), null, 0, true)) {
					GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust			, aMaterial, (int)( aMaterialAmount      / GregTech_API.MATERIAL_UNIT)), null, 0, true);
				}
			}
		}
	}
	
	/**
	 * You give this Function a Material and it will scan almost everything for adding recycling Recipes
	 * 
	 * @param aMat a Material, for example an Ingot or a Gem.
	 * @param aOutput the Dust you usually get from macerating aMat
	 * @param aBackSmelting allows to reverse smelt into aMat (false for Gems)
	 * @param aBackMacerating allows to reverse macerate into aOutput
	 */
	public static void registerUsagesForMaterials(ItemStack aMat, ItemStack aOutput, String aPlate, boolean aBackSmelting, boolean aBackMacerating, boolean aRecipeReplacing) {
		if (aMat == null || aOutput == null) return;
		aMat = GT_Utility.copy(aMat);
		aOutput = GT_Utility.copy(aOutput);
		ItemStack tStack, tUnificated = GT_OreDictUnificator.get(true, aMat);
		String aAssotiation = GT_OreDictUnificator.getAssociation(aMat);
		if (aOutput.stackSize < 1) aOutput.stackSize = 1;
		if (aAssotiation == null || !aAssotiation.startsWith(OrePrefixes.ingot.toString())) aPlate = null;
		if (aPlate != null && GT_OreDictUnificator.getFirstOre(aPlate, 1) == null) aPlate = null;
		
		if (!GT_Utility.areStacksEqual(GT_OreDictUnificator.get(aMat), new ItemStack(Items.iron_ingot, 1))) {
			RecipeHandler.scheduleCraftingToRemove(new DoubleMatcher(new ItemStack(Items.bucket, 1), false, aMat, null, aMat, null, aMat, null, null, null, null));
			RecipeHandler.scheduleCraftingToRemove(new DoubleMatcher(new ItemStack(Items.bucket, 1), false, null, null, null, aMat, null, aMat, null, aMat, null));
			RecipeHandler.scheduleCraftingToRemove(new DoubleMatcher(new ItemStack(Items.minecart, 1), false, aMat, null, aMat, aMat, aMat, aMat, null, null, null));
			RecipeHandler.scheduleCraftingToRemove(new DoubleMatcher(new ItemStack(Items.minecart, 1), false, null, null, null, aMat, null, aMat, aMat, aMat, aMat));
		}
		
		// Shitcode, but i do not want to solve it :P
		if (aBackMacerating || aBackSmelting) {
			sMt1.func_150996_a(aMat.getItem());
			sMt1.stackSize = 1;
			Items.feather.setDamage(sMt1, Items.feather.getDamage(aMat));
			
			for (int i = 0; i < sShapes1.length; i++) {
				ItemStack[] tRecipe = sShapes1[i];
				int tAmount1 = 0;
				for (ItemStack tMat : tRecipe)
					if (tMat == sMt1) tAmount1++;
				for (ItemStack tCrafted : GT_ModHandler.getRecipeOutputs(tRecipe)) {
					if (aBackMacerating) GT_ModHandler.addPulverisationRecipe(tCrafted, GT_Utility.copyAmount(tAmount1, aOutput), null, 0, false);
					final ItemStack tmp = GT_Utility.copyAmount(tAmount1, tUnificated);
					if (aBackSmelting) RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tCrafted, tmp));
				}
				
				for (Materials tMaterial : sRodMaterialList) {
					ItemStack tMt2 = GT_OreDictUnificator.get(OrePrefixes.stick, tMaterial, 1), tMt3 = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMaterial, 2);
			    	if (tMt2 != null) {
						sMt2.func_150996_a(tMt2.getItem());
						sMt2.stackSize = 1;
						Items.feather.setDamage(sMt2, Items.feather.getDamage(tMt2));
						
						int tAmount2 = 0;
						for (ItemStack tMat : tRecipe) 
							if (tMat == sMt2) tAmount2++;
						for (ItemStack tCrafted : GT_ModHandler.getVanillyToolRecipeOutputs(tRecipe)) {
							if (aBackMacerating) GT_ModHandler.addPulverisationRecipe(tCrafted, GT_Utility.copyAmount(tAmount1, aOutput), tAmount2>0?GT_Utility.mul(tAmount2, tMt3):null, 100, false);
							if (aBackSmelting) GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tCrafted, GT_Utility.copyAmount(tAmount1, tUnificated));
							if (aRecipeReplacing && aPlate != null && sShapesA[i] != null && sShapesA[i].length > 1) {
								if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, aAssotiation.replaceFirst(OrePrefixes.ingot.toString(), "")+"."+sShapesA[i][0], true)) {
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
	
	public static void registerBlockForcibly(OrePrefixes aPrefix, Materials aMaterial, List<ItemStack> unifiedStacks) {
		String dictName = OrePrefixes.block.get(aMaterial);
		for (ItemStack a : unifiedStacks) {
			ItemStack block = aMaterial.mSmallBlock ? GT_ModHandler.getRecipeOutput(a, a, null, a, a, null) : GT_ModHandler.getRecipeOutput(a, a, a, a, a, a, a, a, a);
			if (block != null) {
				Optional<String> optional = Arrays.stream(OreDictionary.getOreIDs(block))
					.mapToObj(i -> OreDictionary.getOreName(i))
					.filter(name -> name.startsWith(OrePrefixes.block.toString()))
					.findAny();
				if (!optional.isPresent() && !GregTech_API.sUnification.get(GT_ConfigCategories.forceoredict, block, dictName).equals("false")) {
					GT_OreDictUnificator.set(dictName, block);
				}
			}
		}
	}
	
	private static final ItemStack sMt1 = new ItemStack(Blocks.air, 1, 0), sMt2 = new ItemStack(Blocks.air, 1, 1);
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
	
	/** Matches fisrt output, then check recipe
	 * @author TheDarkDnKTv
	 */
	public static class DoubleMatcher implements IRecipeMatcher {
		
		private RecipeHandler.InventoryRecipeMatcher matcher;
		private ItemStack output;
		
		public DoubleMatcher(ItemStack output, boolean reusable, ItemStack...pseudoInventory) {
			Objects.requireNonNull(output);
			Objects.requireNonNull(pseudoInventory);
			if (GT_Utility.isStackInvalid(output) || pseudoInventory.length < 1) throw new IllegalArgumentException();
			this.matcher = new InventoryRecipeMatcher(reusable, pseudoInventory);
			this.output = output;
		}
		
		@Override
		public boolean matches(IRecipe recipe) {
			return recipe.getRecipeOutput() != null && output.isItemEqual(recipe.getRecipeOutput()) && matcher.matches(recipe);
		}
		
		@Override
		public boolean isReusable() {
			return matcher.isReusable();
		}
	}
}