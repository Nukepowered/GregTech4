package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import ic2.api.item.IBoxable;
import ic2.api.item.IElectricItem;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import java.lang.reflect.Method;
import java.util.*;

import cofh.thermalexpansion.api.crafting.recipes.ISmelterRecipe;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This is the Interface I use for interacting with other Mods.
 * 
 * Due to the many imports, this File can cause compile Problems if not all the APIs are installed
 */
public class GT_ModHandler {
	public static volatile int VERSION = 416;
	
	/**
	 * Returns if that Liquid is Water
	 */
	public static boolean isWater(FluidStack aLiquid) {
		if (aLiquid == null) return false;
		return aLiquid.isFluidEqual(getWater(1));
	}
	
	/**
	 * Returns a Liquid Stack with given amount of Water.
	 */
	public static FluidStack getWater(long aAmount) {
		return new FluidStack(FluidRegistry.WATER, (int) aAmount);
	}
	
	/**
	 * Returns if that Liquid is Lava
	 */
	public static boolean isLava(FluidStack aLiquid) {
		if (aLiquid == null) return false;
		return aLiquid.isFluidEqual(getLava(1));
	}
	
	/**
	 * Returns a Liquid Stack with given amount of Lava.
	 */
	public static FluidStack getLava(long aAmount) {
		return new FluidStack(FluidRegistry.LAVA, (int) aAmount);
	}
	
	/**
	 * Returns if that Liquid is Steam
	 */
	public static boolean isSteam(FluidStack aLiquid) {
		if (aLiquid == null) return false;
		return aLiquid.isFluidEqual(getSteam(1)) || aLiquid.isFluidEqual(getIC2Steam(1));
	}
	
	/**
	 * Returns a Liquid Stack with given amount of Steam.
	 */
	public static FluidStack getSteam(long aAmount) {
		return FluidRegistry.getFluidStack("steam", (int)aAmount);
	}
	
	public static FluidStack getIC2Steam(long aAmount) {
		return FluidRegistry.getFluidStack("ic2steam", (int)aAmount);
	}
	
	public static ItemStack getEmptyCell(long aAmount) {
		return GT_Items.Cell_Empty.get(aAmount);
	}
	
	public static ItemStack getAirCell(long aAmount) {
		return GT_Items.Cell_Air.get(aAmount);
	}
	
	public static ItemStack getWaterCell(long aAmount) {
		return GT_Items.Cell_Water.get(aAmount);
	}
	
	public static ItemStack getLavaCell(long aAmount) {
		return GT_Items.Cell_Lava.get(aAmount);
	}
	
	/**
	 * @param aValue the Value of this Stack, when burning inside a Furnace (200 = 1 Burn Process = 500 EU, max = 32767 (that is 81917.5 EU)), limited to Short because the vanilla Furnace otherwise can't handle it properly, stupid Mojang...
	 */
	public static ItemStack setFuelValue(ItemStack aStack, short aValue) {
        aStack.setTagCompound(GT_Utility.getNBTContainingShort(aStack.getTagCompound(), "GT.ItemFuelValue", aValue));
        return aStack;
	}
	
	private static final Map<String, ItemStack> sIC2ItemMap = new HashMap<String, ItemStack>();
	
	/**
	 * Gets an Item from IndustrialCraft, and returns a Replacement Item if not possible
	 */
	public static ItemStack getIC2Item(String aItem, long aAmount, ItemStack aReplacement) {
		if (GT_Utility.isStringInvalid(aItem) || !GregTech_API.sPreloadStarted) return null;
		//if (GregTech_API.DEBUG_MODE) GT_Log.out.println("Requested the Item '" + aItem + "' from the IC2-API");
		if (!sIC2ItemMap.containsKey(aItem)) try {sIC2ItemMap.put(aItem, ic2.api.item.IC2Items.getItem(aItem));} catch (Throwable e) {/*Do nothing*/}
		return GT_Utility.copyAmount(aAmount, sIC2ItemMap.get(aItem), aReplacement);
	}
	
	/**
	 * Gets an Item from IndustrialCraft, but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getIC2Item(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getIC2Item(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		Items.feather.setDamage(rStack, aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from IndustrialCraft, but the Damage Value can be specified
	 */
	public static ItemStack getIC2Item(String aItem, long aAmount, int aMeta) {
		return getIC2Item(aItem, aAmount, aMeta, null);
	}
	
	/**
	 * Gets an Item from IndustrialCraft
	 */
	public static ItemStack getIC2Item(String aItem, long aAmount) {
		return getIC2Item(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from RailCraft
	 */
	public static ItemStack getRCItem(String aItem, long aAmount) {
		return getRCItem(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from RailCraft, and returns a Replacement Item if not possible
	 */
	public static ItemStack getRCItem(String aItem, long aAmount, ItemStack aReplacement) {
		if (GT_Utility.isStringInvalid(aItem) || !GregTech_API.sPreloadStarted) return null;
		return GT_Utility.copyAmount(aAmount, GameRegistry.findItemStack("Railcraft", aItem, (int)aAmount), aReplacement);
	}
	
	/**
	 * Gets an Item from RailCraft, but the Damage Value can be specified
	 */
	public static ItemStack getRCItem(String aItem, long aAmount, int aMeta) {
		ItemStack rStack = getRCItem(aItem, aAmount);
		if (rStack == null) return null;
		Items.feather.setDamage(rStack, aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from RailCraft, but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getRCItem(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getRCItem(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		Items.feather.setDamage(rStack, aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from ThermoCraft, and returns a Replacement Item if not possible
	 */
	public static ItemStack getTEItem(String aItem, long aAmount, ItemStack aReplacement) {
		if (GT_Utility.isStringInvalid(aItem) || !GregTech_API.sPreloadStarted) return null;
		return GT_Utility.copyAmount(aAmount, GameRegistry.findItemStack("ThermalExpansion", aItem, (int)aAmount), aReplacement);
	}
	
	/**
	 * Gets an Item from ThermalExplosion, but the Damage Value can be specified
	 */
	public static ItemStack getTEItem(String aItem, long aAmount, int aMeta) {
		ItemStack rStack = getTEItem(aItem, aAmount);
		if (rStack == null) return null;
		Items.feather.setDamage(rStack, aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from ThermalCraft
	 */
	public static ItemStack getTEItem(String aItem, long aAmount) {
		return getTEItem(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from Thermal Expansion (Did I spell it right this time?), but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getTEItem(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getTEItem(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		Items.feather.setDamage(rStack, aMeta);
		return rStack;
	}
	
	/**
	 * adds an RC-Boiler Fuel
	 */
	public static void addBoilerFuel(FluidStack aLiquid, int aValue) {
		if (aLiquid == null || aValue <= 0) return;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Fuels.boilerfuels, aLiquid.getFluid().getUnlocalizedName(), true)) return;
		try {
			mods.railcraft.api.fuel.FuelManager.addBoilerFuel(aLiquid.getFluid(), aValue);
		} catch(Throwable e) {/*Do nothing*/}
	}
    
	/**
	 * OUT OF ORDER
	 */
	public static boolean getModeKeyDown(EntityPlayer aPlayer) {
		return false;
	}
	
	/**
	 * OUT OF ORDER
	 */
	public static boolean getBoostKeyDown(EntityPlayer aPlayer) {
		return false;
	}
	
	/**
	 * OUT OF ORDER
	 */
	public static boolean getJumpKeyDown(EntityPlayer aPlayer) {
		return false;
	}
	
	/**
	 * Adds a Valuable Ore to the Miner
	 */
	public static boolean addValuableOre(ItemStack aStack, int aValue) {
		if (aValue <= 0) return false;
		try {
			Class<?> type = Class.forName("ic2.api.recipe.IRecipeInput");
			Object value = Class.forName("ic2.api.recipe.RecipeInputItemStack").getConstructor(ItemStack.class, int.class).newInstance(aStack, aStack.stackSize);
			Class.forName("ic2.core.IC2").getMethod("addValuableOre", type, int.class).invoke(null, value, aValue); // TODO mb need RecipeInputOreDict
		} catch (Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * Adds a Scrapbox Drop. Fails at April first for the "suddenly Hoes"-Feature of IC2
	 */
	public static boolean addScrapboxDrop(float aChance, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aOutput == null || aChance <= 0) return false;
		aOutput.stackSize = 1;
		if (GT_Config.system && !GT_Utility.areStacksEqual(aOutput, new ItemStack(Items.wooden_hoe, 1, 0))) return false;
		aChance = (float)GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.scrapboxdrops, aOutput, aChance);
		if (aChance <= 0) return false;
		try {
			GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addDrop", true, false, true, GT_Utility.copy(aOutput), aChance);
			GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addRecipe", true, true, false, GT_Utility.copy(aOutput), aChance);
		} catch (Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * Adds an Item to the Recycler Blacklist
	 */
	public static boolean addToRecyclerBlackList(ItemStack aRecycledStack) {
		if (aRecycledStack == null) return false;		
		try {
			Class<?> recipeInputStack = Class.forName("ic2.api.recipe.RecipeInputItemStack"); 
			Class<?> iRecipeInput = Class.forName("ic2.api.recipe.IRecipeInput");
			Method add = Class.forName("ic2.api.recipe.IListRecipeManager").getMethod("add", iRecipeInput);
			Object o = recipeInputStack.getConstructor(ItemStack.class).newInstance(aRecycledStack.copy());
			add.invoke(ic2.api.recipe.Recipes.recyclerBlacklist, o);
		} catch (Throwable e) {}
		return true;
	}
	
	/**
	 * Just simple Furnace smelting. Unbelievable how Minecraft fails at making a simple ItemStack->ItemStack mapping...
	 */
	@SuppressWarnings("deprecation")
	public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		if (aInput.getItem().hasContainerItem()) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.smelting, aInput, true)) return false;
		FurnaceRecipes.smelting().func_151394_a(aInput.copy(), aOutput.copy(), 0.0F);
		return true;
	}
	
	/**
	 * Adds to Furnace AND Alloysmelter AND Induction Smelter
	 */
	public static boolean addSmeltingAndAlloySmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		if (aInput == null || aOutput == null) return false;
		boolean temp = false;
		if (aInput.stackSize == 1 && addSmeltingRecipe(aInput, aOutput)) temp = true;
		if (RecipeMaps.ALLOY_SMELTING.factory().EUt(3).duration(160).input(aInput).output(aOutput).buildAndRegister()) temp = true;
		if (addInductionSmelterRecipe(aInput, null, aOutput, null, aOutput.stackSize * 800, 0)) temp = true;
		return temp;
	}
	
	/**
	 * Adds a Recipe to Forestrys Squeezer
	 */
	public static boolean addSqueezerRecipe(ItemStack aInput, FluidStack aOutput, int aTime) {
		if (aInput == null || aOutput == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.squeezer, aInput, true)) return false;
		try {
			forestry.api.recipes.RecipeManagers.squeezerManager.addRecipe(aTime>0?aTime:100, new ItemStack[] {GT_Utility.copy(aInput)}, aOutput);
		} catch(Throwable e) {
			return false;
		}
		return true;
	}
	
	/**
	 * LiquidTransposer Recipe for both directions
	 */
	public static boolean addLiquidTransposerRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aRF) {
		aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
		if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposer, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.util.crafting.TransposerManager.addFillRecipe(aRF, aEmptyContainer, aFullContainer, aLiquid, true, true);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * LiquidTransposer Recipe for filling Containers
	 */
	public static boolean addLiquidTransposerFillRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aRF) {
		aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
		if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposerfilling, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.util.crafting.TransposerManager.addFillRecipe(aRF, aEmptyContainer, aFullContainer, aLiquid, false, true);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * LiquidTransposer Recipe for emptying Containers
	 */
	public static boolean addLiquidTransposerEmptyRecipe(ItemStack aFullContainer, FluidStack aLiquid, ItemStack aEmptyContainer, int aRF) {
		aEmptyContainer = GT_OreDictUnificator.get(true, aEmptyContainer);
		if (aFullContainer == null || aEmptyContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.liquidtransposeremptying, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.util.crafting.TransposerManager.addExtractionRecipe(aRF, aFullContainer, aEmptyContainer, aLiquid, 100, false, true);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/** @param secondaryChance - 1-100%  */
	public static boolean addTEPulveriserRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance, int RF) {
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, input, true)) return false;
		try {
			cofh.thermalexpansion.util.crafting.PulverizerManager.addRecipe(RF, input, primaryOutput, secondaryOutput, secondaryChance, true);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * IC2-Extractor Recipe. Overloads old Recipes automatically
	 */
	public static boolean addExtractionRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), null);
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.extractor, aInput, true)) return false;
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), null, aOutput);
		return true;
	}
	
	public static boolean addExtractionRecipe(IRecipeInput input, ItemStack output) {
		return GT_Utility.addSimpleIC2MachineRecipe(input, getExtractorRecipeList(), null, output);
	}
	
	/**
	 * RC-BlastFurnace Recipes
	 */
	public static boolean addRCBlastFurnaceRecipe(ItemStack aInput, ItemStack aOutput, int aTime) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null || aTime <= 0) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.rcblastfurnace, aInput, true)) return false;
		aInput = GT_Utility.copy(aInput);
		aOutput = GT_Utility.copy(aOutput);
		try {
			mods.railcraft.api.crafting.RailcraftCraftingManager.blastFurnace.addRecipe(aInput, true, false, aTime, aOutput);
		} catch (Throwable e) {
			return false;
		}
		return true;
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1) {
		return addPulverisationRecipe(aInput, aOutput1, null, 0, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, aChance, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, boolean aOverwrite) {
		return addPulverisationRecipe(aInput, aOutput1, null, 0, aOverwrite);
	}

	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, boolean aOverwrite) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, aOverwrite);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		
		String assocIn = GT_OreDictUnificator.getAssociation(aInput);
		String assocOut = GT_OreDictUnificator.getAssociation(aOutput1);
		
		if (aInput == null || aOutput1 == null || (assocIn != null ? assocIn.equals(assocOut) : false)) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), null);
		
		if (GT_Utility.getContainerItem(aInput) == null) {
			if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.maceration, aInput, true)) {
				GT_Utility.addSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), null, aOutput1);
			}
			
			if (!OrePrefixes.log.contains(aInput)) {
				if (Materials.Wood.contains(aOutput1)) {
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput, true)) {
						if (aOutput2 == null)
							addSawmillRecipe(GT_Utility.copy(aInput), 1200, GT_Utility.copy(aOutput1));
						else
							addSawmillRecipe(GT_Utility.copy(aInput), 1200, aChance<=0?10:aChance, GT_Utility.copy(aOutput1), GT_Utility.copy(aOutput2));
						
					}
				} else {
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.rockcrushing, aInput, true)) {
						try {
							if (Block.getBlockFromItem(aInput.getItem()) != Blocks.obsidian) {
								mods.railcraft.api.crafting.IRockCrusherRecipe tRecipe = mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.createNewRecipe(GT_Utility.copyAmount(1, aInput), aInput.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE, false);
								tRecipe.addOutput(GT_Utility.copy(aOutput1), 1.0F/aInput.stackSize);
								tRecipe.addOutput(GT_Utility.copy(aOutput2), (0.01F*(aChance<=0?10:aChance))/aInput.stackSize);
							}
						} catch(Throwable e) {/*Do nothing*/}
					}
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput, true)) {
						addTEPulveriserRecipe(GT_Utility.copy(aInput), GT_Utility.copy(aOutput1), GT_Utility.copy(aOutput2), aChance<=0?10:aChance, 2400);
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds Several Pulverizer-Type Recipes.
	 */
	public static boolean addPulverisationRecipe(OreDictEntry aInput, int amount, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
		if (aInput == null || aOutput1 == null || GT_Utility.getContainerItem(aInput.ores.get(0)) != null) return false;
		OrePrefixes pref = OrePrefixes.getOrePrefix(aInput.oreDictName);
		Materials mat = OrePrefixes.getMaterial(aInput.oreDictName, pref);
		
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.maceration, aInput.oreDictName, true)) {
			GT_Utility.addSimpleIC2MachineRecipe(RecipeEntry.fromStacks(amount, aInput.ores, Match.STRICT), getMaceratorRecipeList(), null, aOutput1);
		}
		
		if (pref != OrePrefixes.log) {
			if (mat == Materials.Wood) {
				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput.oreDictName, true)) {
					addSawmillRecipe(aInput, amount, aChance, aOutput1, aOutput2);
				}
			} else {
				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.rockcrushing, aInput.oreDictName, true) && Loader.isModLoaded("Railcraft")) {
					for (ItemStack stack : aInput.ores) {
						if (Block.getBlockFromItem(stack.getItem()) != Blocks.obsidian) {
							mods.railcraft.api.crafting.IRockCrusherRecipe tRecipe = mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.createNewRecipe(GT_Utility.copyAmount(1, stack), stack.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE, false);
							tRecipe.addOutput(GT_Utility.copy(aOutput1), 1.0F/stack.stackSize);
							tRecipe.addOutput(GT_Utility.copy(aOutput2), (0.01F*(aChance<=0?10:aChance))/stack.stackSize);
						}
					}
				}
				
				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.pulverization, aInput.oreDictName, true)) {
					addTEPulveriserRecipe(GT_Utility.copy(aInput.ores.get(0)), GT_Utility.copy(aOutput1), GT_Utility.copy(aOutput2), aChance<=0?10:aChance, 2400);
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Adds a Recipe to the Sawmills of GregTech and ThermalCraft
	 */
	public static boolean addSawmillRecipe(ItemStack aInput1, ItemStack...outputs) {
		return addSawmillRecipe(aInput1, 2400, outputs);
	}
	
	public static boolean addSawmillRecipe(ItemStack aInput1, int aRF, ItemStack...outputs) {
		return addSawmillRecipe(aInput1, aRF, 100, outputs);
	}
	
	public static boolean addSawmillRecipe(ItemStack aInput1, int aRF, int aChance, ItemStack...outputs) {
		if (aInput1 == null || outputs[0] == null || !GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.sawmill, aInput1, true)) return false;
		boolean result = false;
	    try {
	    	result = cofh.thermalexpansion.util.crafting.SawmillManager.addRecipe(aRF, aInput1, outputs[0], outputs.length > 1 ? outputs[1] : null, aChance, true);
		} catch(Throwable e) {/*Do nothing*/}
		return result;
	}
	
	/**
	 * Adds a Recipe to the Sawmills of GregTech and ThermalCraft, but oredict
	 */
	public static boolean addSawmillRecipe(OreDictEntry entry, int inAmount, int aChance, ItemStack...outputs) {
		return addSawmillRecipe(entry, inAmount, 2400, aChance, outputs);
	}
	
	public static boolean addSawmillRecipe(OreDictEntry entry, int inAmount, int aRF, int aChance, ItemStack...outputs) {
		if (entry == null || entry.ores == null || entry.ores.size() == 0 || !GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.sawmill, entry.oreDictName, true)) return false;
		boolean result = false;
		try {
			// Accroding on TE code, it saves all recipe OreDict format, so i can just create one recipe
			result = cofh.thermalexpansion.util.crafting.SawmillManager.addRecipe(aRF, entry.ores.get(0), outputs[0], outputs.length > 1 ? outputs[1] : null, aChance, true);
		} catch(Throwable e) {/*Do nothing*/}
		return result;
	}
	
	/**
	 * Induction Smelter Recipes for TE
	 */
	public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		if (aInput1 == null || aOutput1 == null || GT_Utility.getContainerItem(aInput1) != null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.inductionsmelter, aInput2==null?aInput1:aOutput1, true)) return false;
	    try {
	    	cofh.thermalexpansion.util.crafting.SmelterManager.addRecipe(aEnergy, GT_Utility.copy(aInput1), aInput2==null?new ItemStack(Blocks.sand, 1, 0):aInput2, aOutput1, aOutput2, aChance, true);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	public static boolean removeInductionSmelterRecipe(ItemStack output) {
		if (GT_Utility.isStackInvalid(output)) return false;
		try {
			for (ISmelterRecipe recipe : cofh.thermalexpansion.util.crafting.SmelterManager.getRecipeList()) {
				if (recipe.getPrimaryOutput().isItemEqual(output)) {
					cofh.thermalexpansion.util.crafting.SmelterManager.removeRecipe(recipe.getPrimaryInput(), recipe.getSecondaryInput());
					break;
				}
			}
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * Induction Smelter Recipes and Alloy Smelter Recipes
	 */
	public static boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, boolean aAllowSecondaryInputEmpty) {
		if (aInput1 == null || (aInput2 == null && !aAllowSecondaryInputEmpty) || aOutput1 == null) return false;
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		boolean temp = false;
		if (RecipeMaps.ALLOY_SMELTING.factory().EUt(aEUt).duration(aDuration).inputs(aInput1, aInput2).output(aOutput1).buildAndRegister()) temp = true;
		if (addInductionSmelterRecipe(aInput1, aInput2, aOutput1, null, aDuration * aEUt * 2, 0)) temp = true;
		return temp;
	}
	
	/**
	 * Smelts Ores to Ingots
	 */
	public static boolean addOreToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		addSmeltingRecipe(aInput, GT_Utility.copy(aOutput));
		return true;
	}
	
	public static Map<IRecipeInput, RecipeOutput> getExtractorRecipeList() {
		try {
			return ic2.api.recipe.Recipes.extractor.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}
	
	public static Map<IRecipeInput, RecipeOutput> getCompressorRecipeList() {
		try {
			return ic2.api.recipe.Recipes.compressor.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}
	
	public static Map<IRecipeInput, RecipeOutput> getMaceratorRecipeList() {
		try {
			return ic2.api.recipe.Recipes.macerator.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}

	public static Map<IRecipeInput, RecipeOutput> getThermalCentrifugeRecipeList() {
		try {
			return ic2.api.recipe.Recipes.centrifuge.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}
	
	public static Map<IRecipeInput, RecipeOutput> getOreWashingRecipeList() {
		try {
			return ic2.api.recipe.Recipes.oreWashing.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}
	
	public static Map<IRecipeInput, RecipeOutput> getMassFabricatorList() {
		try {
			return ic2.api.recipe.Recipes.matterAmplifier.getRecipes();
		} catch(Throwable e) {/*Do nothing*/}
		return Collections.emptyMap();
	}
	
	public static List<ItemStack> getMaceratorResult(ItemStack input) {
		try {
			ic2.api.recipe.RecipeOutput recipe = ic2.api.recipe.Recipes.macerator.getOutputFor(input, true);
			if (recipe != null) {
				return GT_Utility.copy(recipe.items);
			}
		} catch (Throwable e) {}
		
		return null;
	}
	
	public static List<ItemStack> getExtractorResult(ItemStack input) {
		try {
			ic2.api.recipe.RecipeOutput recipe = ic2.api.recipe.Recipes.extractor.getOutputFor(input, true);
			if (recipe != null) {
				return GT_Utility.copy(recipe.items);
			}
		} catch (Throwable e) {}
		
		return null;
	}
	
	public static List<ItemStack> getCompressorResult(ItemStack input) {
		try {
			ic2.api.recipe.RecipeOutput recipe = ic2.api.recipe.Recipes.compressor.getOutputFor(input, true);
			if (recipe != null) {
				return GT_Utility.copy(recipe.items);
			}
		} catch (Throwable e) {}
		
		return null;
	}
	
	/**
	 * IC2-ThermalCentrifuge Recipe. Overloads old Recipes automatically
	 */
	public static boolean addThermalCentrifugeRecipe(ItemStack aInput, int aHeat, Object... aOutput) {
		if (aInput == null || aOutput == null) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getThermalCentrifugeRecipeList(), null);
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.thermalcentrifuge, aInput, true)) return false;
		NBTTagCompound tNBT = new NBTTagCompound();
		tNBT.setInteger("minHeat", aHeat);
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getThermalCentrifugeRecipeList(), tNBT, aOutput);
		return true;
	}
	
	/**
	 * IC2-OreWasher Recipe. Overloads old Recipes automatically
	 */
	public static boolean addOreWasherRecipe(ItemStack aInput, int aWaterAmount, Object... aOutput) {
		if (aInput == null || aOutput == null) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getOreWashingRecipeList(), null);
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.orewashing, aInput, true)) return false;
		NBTTagCompound tNBT = new NBTTagCompound();
		tNBT.setInteger("amount", aWaterAmount);
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getOreWashingRecipeList(), tNBT, aOutput);
		return true;
	}
	
	/**
	 * IC2-Compressor Recipe. Overloads old Recipes automatically
	 */
	public static boolean addCompressionRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.compression, aInput, true)) return false;
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), null, aOutput);
		return true;
	}
	
	public static boolean addCompressionRecipe(IRecipeInput aInput, ItemStack aOutput) {
		return GT_Utility.addSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), null, aOutput);
	}
	
	public static boolean addCompressionRecipe(OreDictEntry aInput, int amount, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.compression, aInput.oreDictName, true)) return false;
		GT_Utility.addSimpleIC2MachineRecipe(RecipeEntry.fromStacks(amount, aInput.ores, Match.STRICT), getCompressorRecipeList(), null, aOutput);
		return true;
	}
	
	/**
	 * @param aValue Scrap = 5000, Scrapbox = 45000
	 * @param oreDict
	 */
	public static boolean addIC2MatterAmplifier(int aValue, String oreDict) {
		if (GT_Utility.isStackInvalid(oreDict) || aValue <= 0) return false;
		if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.massfabamplifier, oreDict, true)) return false;
		try {
			NBTTagCompound tNBT = new NBTTagCompound();
			tNBT.setInteger("amplification", aValue);
			ic2.api.recipe.Recipes.matterAmplifier.addRecipe(new ic2.api.recipe.RecipeInputOreDict(oreDict), tNBT);
		} catch(Throwable e) {/*Do nothing*/}
		return true;
	}
	
	/**
	 * Rolling Machine Crafting Recipe
	 */
	public static boolean addRollingMachineRecipe(ItemStack aResult, Object[] aRecipe) {
		aResult = GT_OreDictUnificator.get(true, aResult);
		if (aResult == null || aRecipe == null || aResult.stackSize <= 0) return false;
		try {
			mods.railcraft.api.crafting.RailcraftCraftingManager.rollingMachine.getRecipeList().add(new ShapedOreRecipe(GT_Utility.copy(aResult), aRecipe));
		} catch(Throwable e) {
			return addCraftingRecipe(GT_Utility.copy(aResult), false, aRecipe);
		}
		return true;
	}
	
	private static boolean sBufferCraftingRecipes = true;
	private static final List<IRecipe> sAllRecipeList = Collections.synchronizedList(new ArrayList<IRecipe>(2000)), sBufferRecipeList = new ArrayList<IRecipe>(300), sSingleNonBlockDamagableRecipeList = new ArrayList<IRecipe>(300);
	
	public static void stopBufferingCraftingRecipes() {
		sBufferCraftingRecipes = false;
		for (IRecipe tRecipe : sBufferRecipeList) GameRegistry.addRecipe(tRecipe);
		sBufferRecipeList.clear();
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, Object... aRecipe) {
		return addCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object... aRecipe) {
		return addCraftingRecipe(aResult, aUseIC2Handler, false, aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, Object... aRecipe) {
		return addCraftingRecipe(aResult, aUseIC2Handler, aMirrored, true, aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, boolean aBuffered, Object... aRecipe) {
		return addCraftingRecipe(aResult, aUseIC2Handler, aMirrored, aBuffered, false, aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, boolean aBuffered, boolean aKeepNBT, Object... aRecipe) {
		aResult = GT_OreDictUnificator.get(true, aResult);
		if (aResult != null && aResult.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE) Items.feather.setDamage(aResult, 0);
		if (aRecipe == null || aRecipe.length <= 0) return false;
		for (byte i = 0; i < aRecipe.length; i++) {
			if (aRecipe[i] instanceof Enum) aRecipe[i] = aRecipe[i].toString();
		}
		try { while (true) {
		    String shape = "";
		    int idx = 0;
		    if (aRecipe[idx] instanceof Boolean) {
		    	idx++;
		    }
	        while (aRecipe[idx] instanceof String) {
	            String s = (String)aRecipe[idx++];
	            shape += s;
	            while (s.length() < 3) s+=" ";
	            if (s.length() > 3) throw new IllegalArgumentException();
	        }
		    if (aRecipe[idx] instanceof Boolean) {
		    	idx++;
		    }
	        HashMap<Character, ItemStack> itemMap = new HashMap<Character, ItemStack>();
	        itemMap.put(' ', null);
	        for (; idx < aRecipe.length; idx += 2) {
				if (aRecipe[idx] == null || aRecipe[idx + 1] == null) {
					if (GregTech_API.DEBUG_MODE) GT_Log.log.warn("WARNING: Missing Item for shaped Recipe: " + (aResult==null?"null":aResult.getDisplayName()));
					return false;
				}
	            Character chr = (Character)aRecipe[idx];
	            Object in = aRecipe[idx + 1];
	            if (in instanceof ItemStack) {
	                itemMap.put(chr, GT_Utility.copy((ItemStack)in));
	            } else if (in instanceof String) {
	            	ItemStack tStack = GT_OreDictUnificator.getFirstOre(in, 1);
	            	if (tStack == null) break;
	                itemMap.put(chr, tStack);
	            } else {
	                throw new IllegalArgumentException();
	            }
	        }
	        ItemStack[] tRecipe = new ItemStack[9];
	        int x = -1;
	        for (char chr : shape.toCharArray()) {
	        	tRecipe[++x] = itemMap.get(chr);
	            if (tRecipe[x] != null)
	            	if (tRecipe[x].getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE)
	            		Items.feather.setDamage(tRecipe[x], 0);
		    }
	        removeRecipe(tRecipe);
	        break;
		}} catch(Throwable e) {GT_Log.log.catching(e);}
		
		if (aResult == null || aResult.stackSize <= 0) return false;
		
		if (aResult.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aResult.getItemDamage() < 0) throw new IllegalArgumentException();
		
		if (aKeepNBT) {
			GameRegistry.addRecipe(new GT_Shaped_NBT_Keeping_Recipe(GT_Utility.copy(aResult), aRecipe).setMirrored(aMirrored));
			return true;
		}
//		if (aUseIC2Handler) {
//			try {
//				ic2.api.recipe.Recipes.advRecipes.addRecipe(GT_Utility.copy(aResult), aRecipe);
//				return true;
//			} catch (Throwable e) {/*Do nothing*/}
//		}
		if (sBufferCraftingRecipes && aBuffered)
			sBufferRecipeList.add(new GT_Shaped_Recipe(GT_Utility.copy(aResult), aRecipe).setMirrored(aMirrored));
		else
			GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Utility.copy(aResult), aRecipe).setMirrored(aMirrored));
		return true;
	}

	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object... aRecipe) {
		return addShapelessCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
	}

	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object... aRecipe) {
		return addShapelessCraftingRecipe(aResult, aUseIC2Handler, true, aRecipe);
	}
	
	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aBuffered, Object... aRecipe) {
		return addShapelessCraftingRecipe(aResult, aUseIC2Handler, aBuffered, false, aRecipe);
	}
	
	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aBuffered, boolean aKeepNBT, Object... aRecipe) {
		aResult = GT_OreDictUnificator.get(true, aResult);
		if (aRecipe == null || aRecipe.length <= 0) return false;
		for (byte i = 0; i < aRecipe.length; i++) {
			if (aRecipe[i] instanceof Enum) {
				aRecipe[i] = aRecipe[i].toString();
			}
		}
		try {
	        ItemStack[] tRecipe = new ItemStack[9];
	        int i = 0;
			for (Object tObject : aRecipe) {
				if (tObject == null) {
					if (GregTech_API.DEBUG_MODE) GT_Log.log.warn("WARNING: Missing Item for shapeless Recipe: " + (aResult==null?"null":aResult.getDisplayName()));
					return false;
				}
				if (tObject instanceof ItemStack) {
					tRecipe[i] = (ItemStack)tObject;
				} else if (tObject instanceof String) {
					tRecipe[i] = GT_OreDictUnificator.getFirstOre(tObject, 1);
				} else if (tObject instanceof Boolean) {
					// TODO
				} else {
	                throw new IllegalArgumentException();
				}
				i++;
			}
	        removeRecipe(tRecipe);
		} catch(Throwable e) {GT_Log.log.catching(e);}
		
		if (aResult == null || aResult.stackSize <= 0) return false;
		
		if (aResult.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aResult.getItemDamage() < 0) throw new IllegalArgumentException();
		
		if (aKeepNBT) {
			GameRegistry.addRecipe(new GT_Shapeless_NBT_Keeping_Recipe(GT_Utility.copy(aResult), aRecipe));
			return true;
		}
//		if (aUseIC2Handler) {
//			try {
//				ic2.api.recipe.Recipes.advRecipes.addShapelessRecipe(GT_Utility.copy(aResult), aRecipe);
//				return true;
//			} catch (Throwable e) {/*Do nothing*/}
//		}
		if (sBufferCraftingRecipes && aBuffered)
			sBufferRecipeList.add(new GT_Shapeless_Recipe(GT_Utility.copy(aResult), aRecipe));
		else
			GameRegistry.addRecipe(new GT_Shapeless_Recipe(GT_Utility.copy(aResult), aRecipe));
		return true;
	}
	
	/**
	 * Removes a Smelting Recipe
	 */
	public static boolean removeFurnaceSmelting(ItemStack aInput) {
		if (aInput != null) {			
			@SuppressWarnings("unchecked")
			Map<ItemStack, ItemStack> recipes = FurnaceRecipes.smelting().getSmeltingList();
			return recipes.entrySet().removeIf(e -> e.getKey().isItemEqual(aInput));
		}
		return false;
	}
	
	/**
	 * Removes a Crafting Recipe and gives you the former output of it.
	 * @param aRecipe The content of the Crafting Grid as ItemStackArray with length 9
	 * @return the output of the old Recipe or null if there was nothing.
	 */
    public static ItemStack removeRecipe(ItemStack... aRecipe) {
    	if (aRecipe == null) return null;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return null;
    	ItemStack rReturn = null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < aRecipe.length && i < 9; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			try {
				if (tList.get(i).matches(aCrafting, GregTech_API.sDummyWorld)) {
					rReturn = tList.get(i).getCraftingResult(aCrafting);
					tList.remove(i--);
				}
			} catch(Throwable e) {GT_Log.log.catching(e);}
		}
		for (int i = 0; i < sBufferRecipeList.size(); i++) {
			IRecipe recipe = null;
			try {
				if ((recipe = sBufferRecipeList.get(i)) != null) {
					if (recipe.matches(aCrafting, GregTech_API.sDummyWorld)) {
						rReturn = sBufferRecipeList.get(i).getCraftingResult(aCrafting);
						sBufferRecipeList.remove(i--);
					}
				}
			} catch(Throwable e) {GT_Log.log.catching(e);}
		}
		return rReturn;
    }
    
	/**
	 * Removes a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
    public static boolean removeRecipeByOutput(ItemStack aOutput) {
    	if (aOutput == null) return false;
    	boolean rReturn = false;
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		aOutput = GT_OreDictUnificator.get(aOutput);
		for (int i = 0; i < tList.size(); i++) {
			if (GT_Utility.areStacksEqual(GT_OreDictUnificator.get(tList.get(i).getRecipeOutput()), aOutput)) {
				tList.remove(i--);
				rReturn = true;
			}
		}
		return rReturn;
    }
    
    /**
     * Checks all Crafting Handlers for Recipe Output
     * Used for the Autocrafting Table
     */
    public static ItemStack getAllRecipeOutput(World aWorld, ItemStack... aRecipe) {
    	if (aRecipe == null) return null;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		@SuppressWarnings("unchecked")
		List<IRecipe> tList = CraftingManager.getInstance().getRecipeList();
		synchronized(sAllRecipeList) {
			if (sAllRecipeList.size() != tList.size()) {
				sAllRecipeList.clear();
				sAllRecipeList.addAll(tList);
			}
			for (int i = 0, j = sAllRecipeList.size(); i < j; i++) {
				IRecipe tRecipe = sAllRecipeList.get(i);
				if (tRecipe.matches(aCrafting, aWorld)) {
					if (i > 10) {
						sAllRecipeList.remove(i);
						sAllRecipeList.add(i-10, tRecipe);
					}
					return tRecipe.getCraftingResult(aCrafting);
				}
			}
		}
		
        int tIndex = 0;
        ItemStack tStack1 = null, tStack2 = null;
        for (int i = 0, j = aCrafting.getSizeInventory(); i < j; i++) {
            ItemStack tStack = aCrafting.getStackInSlot(i);
            if (tStack != null) {
                if (tIndex == 0) tStack1 = tStack;
                if (tIndex == 1) tStack2 = tStack;
                tIndex++;
            }
        }
        
        if (tIndex == 2) {
            assert tStack1 != null && tStack2 != null;
        	if (tStack1.getItem() == tStack2.getItem() && tStack1.stackSize == 1 && tStack2.stackSize == 1 && tStack1.getItem().isRepairable()) {
        		int tNewDamage = tStack1.getMaxDamage() + tStack1.getItemDamage() - tStack2.getItemDamage() + tStack1.getMaxDamage() / 20;
                return new ItemStack(tStack1.getItem(), 1, tNewDamage<0?0:tNewDamage);
            }
        }
		
		return null;
    }
    
    /**
     * Gives you a copy of the Output from a Crafting Recipe
     * Used for Recipe Detection.
     */
    public static ItemStack getRecipeOutput(ItemStack... aRecipe) {
    	return getRecipeOutput(false, aRecipe);
    }
    
    /**
     * Gives you a copy of the Output from a Crafting Recipe
     * Used for Recipe Detection.
     */
    public static ItemStack getRecipeOutput(boolean aUncopiedStack, ItemStack... aRecipe) {
    	if (aRecipe == null) return null;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override
		public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {temp = false;
			try {
				temp = tList.get(i).matches(aCrafting, GregTech_API.sDummyWorld);
			} catch(Throwable e) {GT_Log.log.catching(e);}
			if (temp) {
				ItemStack tOutput = aUncopiedStack?tList.get(i).getRecipeOutput():tList.get(i).getCraftingResult(aCrafting);
				if (tOutput == null || tOutput.stackSize <= 0) {
					// Seriously, who would ever do that shit?
					if (!GregTech_API.sPostloadFinished) throw new GT_ItsNotMyFaultException("Seems another Mod added a Crafting Recipe with null Output. Tell the Developer of said Mod to fix that.");
				} else {
					if (aUncopiedStack) return tOutput;
					return GT_Utility.copy(tOutput);
				}
			}
		}
		return null;
    }

    /**
     * Gives you a list of the Outputs from a Crafting Recipe
     * If you have multiple Mods, which add Bronze Armor for example
     * This also removes old Recipes from the List.
     */
    @SuppressWarnings("unchecked")
	public static ArrayList<ItemStack> getVanillyToolRecipeOutputs(ItemStack... aRecipe) {
    	if (!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) sSingleNonBlockDamagableRecipeList.clear();
    	if (sSingleNonBlockDamagableRecipeList.isEmpty()) {
    		for (IRecipe tRecipe : (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList()) {
    			ItemStack tStack = tRecipe.getRecipeOutput();
    			if (tStack != null && tStack.getItem() != null && tStack.getMaxStackSize() == 1 && tStack.getMaxDamage() > 0 && !isElectricItem(tStack) && !(tStack.getItem() instanceof ItemBlock)) {
    				if (!(tRecipe instanceof ShapelessRecipes || tRecipe instanceof ShapelessOreRecipe)) {
    					if (tRecipe instanceof ShapedOreRecipe) {
    						boolean temp = true;
    						for (Object tObject : ((ShapedOreRecipe)tRecipe).getInput()) {
    							if (tObject != null && tObject instanceof ItemStack && (((ItemStack)tObject).getItem() == null || ((ItemStack)tObject).getMaxStackSize() < 2 || ((ItemStack)tObject).getMaxDamage() > 0 || ((ItemStack)tObject).getItem() instanceof ItemBlock)) {
    								temp = false;
    								break;
    							}
    						}
    						if (temp) sSingleNonBlockDamagableRecipeList.add(tRecipe);
    					} else if (tRecipe instanceof ShapedRecipes) {
    						boolean temp = true;
    						for (ItemStack tObject : ((ShapedRecipes)tRecipe).recipeItems) {
    							if (tObject != null && (tObject.getItem() == null || tObject.getMaxStackSize() < 2 || tObject.getMaxDamage() > 0 || tObject.getItem() instanceof ItemBlock)) {
    								temp = false;
    								break;
    							}
    						}
    						if (temp) sSingleNonBlockDamagableRecipeList.add(tRecipe);
    					} else {
    	    				sSingleNonBlockDamagableRecipeList.add(tRecipe);
    					}
    				}
    			}
    		}
    		GT_Log.log.info("Created a List of Tool Recipes containing " + sSingleNonBlockDamagableRecipeList.size() + " Recipes for recycling." + (sSingleNonBlockDamagableRecipeList.size()>2048?" Scanning all these Recipes is the reason for the startup Lag you receive right now.":""));
    	}
    	ArrayList<ItemStack> rList = getRecipeOutputs(sSingleNonBlockDamagableRecipeList, true, aRecipe);
    	if (!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) sSingleNonBlockDamagableRecipeList.clear();
    	return rList;
    }
    
    /**
     * Gives you a list of the Outputs from a Crafting Recipe
     * If you have multiple Mods, which add Bronze Armor for example
     */
	@SuppressWarnings("unchecked")
    public static synchronized List<ItemStack> getRecipeOutputs(ItemStack... aRecipe) {
		return getRecipeOutputs(CraftingManager.getInstance().getRecipeList(), false, aRecipe);
    }
    
    /**
     * Gives you a list of the Outputs from a Crafting Recipe
     * If you have multiple Mods, which add Bronze Armor for example
     */
    public static ArrayList<ItemStack> getRecipeOutputs(List<IRecipe> aList, boolean aDeleteFromList, ItemStack... aRecipe) {
    	ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
    	if (aRecipe == null) return rList;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return rList;
    	InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override
		public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		for (int i = 0; i < aList.size(); i++) {
			temp = false;
			try {
				temp = aList.get(i).matches(aCrafting, GregTech_API.sDummyWorld);
			} catch(Throwable e) {
				if (!(e instanceof NullPointerException)) GT_Log.log.catching(e);
			}
			if (temp) {
				ItemStack tOutput = aList.get(i).getCraftingResult(aCrafting);
				if (tOutput == null || tOutput.stackSize <= 0) {
					// Seriously, who would ever do that shit?
					if (!GregTech_API.sPostloadFinished) throw new GT_ItsNotMyFaultException("Seems another Mod added a Crafting Recipe with null Output. Tell the Developer of said Mod to fix that.");
				} else {
					rList.add(GT_Utility.copy(tOutput));
					if (aDeleteFromList) aList.remove(i--);
				}
			}
		}
		return rList;
    }
    
    /**
     * Used in Ore processors.
     */
    public static ItemStack getSmeltingOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
    	if (GT_Utility.isStackInvalid(aInput)) {
			return null;
		}

    	ItemStack rStack = GT_OreDictUnificator.get(FurnaceRecipes.smelting().getSmeltingResult(aInput));
    	if (rStack != null && (aOutputSlot == null || (GT_Utility.areStacksEqual(rStack, aOutputSlot) && rStack.stackSize + aOutputSlot.stackSize <= aOutputSlot.getMaxStackSize()))) {
			if (aRemoveInput) aInput.stackSize--;
			return rStack;
		}

    	return null;
    }
    
    /**
     * Used in my own Recycler.
     * 
     * Only produces Scrap if aScrapChance == 0. aScrapChance is usually the random Number I give to the Function
     * If you directly insert 0 as aScrapChance then you can check if its Recycler-Blacklisted or similar
     */
    public static ItemStack getRecyclerOutput(ItemStack aInput, int aScrapChance) {
    	if (aInput == null || aScrapChance != 0) return null;
		try {
			if (ic2.api.recipe.Recipes.recyclerWhitelist.isEmpty()) return ic2.api.recipe.Recipes.recyclerBlacklist.contains(aInput)?null:GT_Items.IC2_Scrap.get(1);
			return ic2.api.recipe.Recipes.recyclerWhitelist.contains(aInput)?GT_Items.IC2_Scrap.get(1):null;
		} catch (Throwable e) {/*Do nothing*/}
		try {
			return ic2.api.recipe.Recipes.recyclerBlacklist.contains(aInput)?null:GT_Items.IC2_Scrap.get(1);
		} catch (Throwable e) {/*Do nothing*/}
    	return null;
    }
    
    /**
     * For the Scrapboxinator
     */
	public static ItemStack getRandomScrapboxDrop() {
		return ic2.api.recipe.Recipes.scrapboxDrops.getDrop(GT_Items.IC2_Scrapbox.get(1), false);
	}
	
    /**
     * Adds TileEntity to E-net
     */
	public static boolean addTileToEnet(World aWorld, TileEntity aTileEntity) {
		try {
			Object temp = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet");
			if (aTileEntity instanceof ic2.api.energy.tile.IEnergyTile && temp != null && temp instanceof Boolean && !((Boolean)temp)) {
				Event tEvent = (Event)Class.forName("ic2.api.energy.event.EnergyTileLoadEvent").getConstructors()[0].newInstance((ic2.api.energy.tile.IEnergyTile)aTileEntity);
				MinecraftForge.EVENT_BUS.post(tEvent);
				return true;
			}
		} catch(Throwable e) {}
		return false;
	}
	
    /**
     * Removes TileEntity from E-net
     */
	public static boolean removeTileFromEnet(World aWorld, TileEntity aTileEntity) {
		try {
			Object temp = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet");
			if (aTileEntity instanceof ic2.api.energy.tile.IEnergyTile && temp != null && temp instanceof Boolean && (Boolean)temp) {
				Event tEvent = (Event)Class.forName("ic2.api.energy.event.EnergyTileUnloadEvent").getConstructors()[0].newInstance((ic2.api.energy.tile.IEnergyTile)aTileEntity);
				MinecraftForge.EVENT_BUS.post(tEvent);
				return true;
			}
		} catch(Throwable e) {}
		return false;
	}
	
    /**
     * Emits Energy to the E-net. Uses the internals of GT TileEntities for emitting Energy if it is not connected to the IC-2-Enet.
     * @return the remaining Energy.
     */
	public static int emitEnergyToEnet(int aVoltage, World aWorld, TileEntity aTileEntity) {
//		try {
//			Object temp = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet");
//			if (aTileEntity instanceof ic2.api.energy.tile.IEnergySource && temp != null && temp instanceof Boolean && (Boolean)temp) {
//				return aVoltage;
//			}
//		} catch(Throwable e) {/*Do nothing*/}
//		if (aTileEntity instanceof IBasicEnergyContainer && aTileEntity instanceof IHasWorldObjectAndCoords) {
//			for (byte i = 0; i < 6; i++) if (((IBasicEnergyContainer)aTileEntity).outputsEnergyTo(i)) {
//				TileEntity tTileEntity = ((IHasWorldObjectAndCoords)aTileEntity).getTileEntityAtSide(i);
//				if (tTileEntity instanceof IBasicEnergyContainer) {
//					if (((IBasicEnergyContainer)tTileEntity).inputEnergyFrom(GT_Utility.getOppositeSide(i))) {
//						if (((IBasicEnergyContainer)tTileEntity).getStoredEU() < ((IBasicEnergyContainer)tTileEntity).getEUCapacity()) {
//							if (((IBasicEnergyContainer)tTileEntity).injectEnergyUnits(GT_Utility.getOppositeSide(i), aVoltage, 1)) {
//								return 0;
//							}
//						}
//					}
//				}
//			}
//		}
		return aVoltage;
	}
	
	/**
	 * Charges an Electric Item. Only if it's a valid Electric Item of course.
	 * @return the actually used Energy.
	 */
	public static double chargeElectricItem(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate) {
		try {
			if (isElectricItem(aStack)) {
				return ic2.api.item.ElectricItem.manager.charge(aStack, aCharge, aTier, aIgnoreLimit, aSimulate);
			}
		} catch (Throwable e) {/*Do nothing*/}
		return 0;
	}
	
	/**
	 * Discharges an Electric Item. Only if it's a valid Electric Item for that of course.
	 * @return the Energy got from the Item.
	 */
	public static double dischargeElectricItem(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreLimit, boolean externally, boolean aSimulate, boolean aIgnoreDischargability) {
		try {
			if (isElectricItem(aStack)) {
				if (aIgnoreDischargability || ((ic2.api.item.IElectricItem)aStack.getItem()).canProvideEnergy(aStack)) {
					return ic2.api.item.ElectricItem.manager.discharge(aStack, aCharge, aTier, aIgnoreLimit, externally, aSimulate);
				}
			}
		} catch (Throwable e) {/*Do nothing*/}
		return 0;
	}
	
	/**
	 * Gets the max Charge Level of an Electric Item. Only if it's a valid Electric Item for that of course.
	 * @return the Energy got from the Item.
	 */
	public static double getMaxElectricCharge(ItemStack aStack) {
		try {
			if (isElectricItem(aStack)) {
				return ((ic2.api.item.IElectricItem)aStack.getItem()).getMaxCharge(aStack);
			}
		} catch (Throwable e) {/*Do nothing*/}
		return 0;
	}
	
	/**
	 * Uses an Electric Item. Only if it's a valid Electric Item for that of course.
	 * @return if the action was successful
	 */
	public static boolean canUseElectricItem(ItemStack aStack, int aCharge) {
		try {
			if (isElectricItem(aStack)) {
				return ic2.api.item.ElectricItem.manager.canUse(aStack, aCharge);
			}
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	/**
	 * Uses an Electric Item. Only if it's a valid Electric Item for that of course.
	 * @return if the action was successful
	 */
	public static boolean useElectricItem(ItemStack aStack, int aCharge, EntityPlayer aPlayer) {
		try {
			if (isElectricItem(aStack)) {
				ic2.api.item.ElectricItem.manager.use(aStack, 0, aPlayer);
				if (ic2.api.item.ElectricItem.manager.canUse(aStack, aCharge)) {
					return ic2.api.item.ElectricItem.manager.use(aStack, aCharge, aPlayer);
				}
			}
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	/**
	 * Uses an Item. Tries to discharge in case of Electric Items
	 */
	@SuppressWarnings("deprecation")
	public static boolean damageOrDechargeItem(ItemStack aStack, int aDamage, int aDecharge, EntityLivingBase aPlayer) {
		if (GT_Utility.isStackInvalid(aStack) || (aStack.getMaxStackSize() <= 1 && aStack.stackSize > 1)) return false;
		if (aPlayer != null && aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).capabilities.isCreativeMode) return true;
		if (GT_ModHandler.isElectricItem(aStack)) {
			if (canUseElectricItem(aStack, aDecharge)) {
				if (aPlayer != null && aPlayer instanceof EntityPlayer) {
					return GT_ModHandler.useElectricItem(aStack, aDecharge, (EntityPlayer)aPlayer);
				} else {
					return GT_ModHandler.dischargeElectricItem(aStack, aDecharge, Integer.MAX_VALUE, true, false, false, true) >= aDecharge;
				}
			}
		} else if (aStack.getItem().isDamageable()) {
			if (aPlayer == null) {
				aStack.setItemDamage(aStack.getItemDamage() + aDamage);
			} else {
				aStack.damageItem(aDamage, aPlayer);
			}
			if (aStack.getItemDamage() >= aStack.getMaxDamage()) {
				aStack.setItemDamage(aStack.getMaxDamage() + 1);
				if (aStack.getItem().hasContainerItem() && aStack.getItem() instanceof GT_Tool_Item) {
					GT_Tool_Item item = (GT_Tool_Item) aStack.getItem();
					item.getEmptyItem(aStack);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Uses a Soldering Iron
	 */
	public static boolean useSolderingIron(ItemStack aStack, EntityLivingBase aPlayer) {
		if (aPlayer == null || aStack == null) return false;
		if (GT_Utility.isItemStackInList(aStack, GregTech_API.sSolderingToolList, true)) {
			if (aPlayer instanceof EntityPlayer) {
				EntityPlayer tPlayer = (EntityPlayer)aPlayer;
				if (tPlayer.capabilities.isCreativeMode) return true;
				for (int i = 0; i < tPlayer.inventory.mainInventory.length; i++) {
					if (GT_Utility.isItemStackInList(tPlayer.inventory.mainInventory[i], GregTech_API.sSolderingMetalList, true)) {
						if (damageOrDechargeItem(aStack, 1, 1000, tPlayer)) {
							if (tPlayer.inventory.mainInventory[i].getItemDamage() >= tPlayer.inventory.mainInventory[i].getMaxDamage()) tPlayer.inventory.mainInventory[i] = null;
						    if (damageOrDechargeItem(tPlayer.inventory.mainInventory[i], 1, 1000, tPlayer)) {
								if (tPlayer.inventory.mainInventory[i].getItemDamage() >= tPlayer.inventory.mainInventory[i].getMaxDamage()) tPlayer.inventory.mainInventory[i] = null;
							    if (tPlayer.inventoryContainer != null) tPlayer.inventoryContainer.detectAndSendChanges();
								return true;
							}
						}
					}
				}
			} else {
				damageOrDechargeItem(aStack, 1, 1000, aPlayer);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Is this an electric Item, which can charge other Items?
	 */
	public static boolean isChargerItem(ItemStack aStack) {
		try {
			if (isElectricItem(aStack)) {
				return ((ic2.api.item.IElectricItem)aStack.getItem()).canProvideEnergy(aStack);
			}
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	/**
	 * Is this an electric Item?
	 */
	public static boolean isElectricItem(ItemStack aStack) {
		try {
			return aStack != null && aStack.getItem() instanceof ic2.api.item.IElectricItem && ((IElectricItem)aStack.getItem()).getTier(aStack) < Integer.MAX_VALUE;
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	public static boolean isElectricItem(ItemStack aStack, byte aTier) {
		try {
			return aStack != null && aStack.getItem() instanceof ic2.api.item.IElectricItem && ((IElectricItem)aStack.getItem()).getTier(aStack) <= aTier;
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	public static boolean acceptsGT(TileEntity aTileEntity) {
		try {
			return aTileEntity instanceof gregtechmod.api.interfaces.IBasicEnergyContainer;
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	public static boolean acceptsEU(TileEntity aTileEntity) {
		try {
			return aTileEntity instanceof ic2.api.energy.tile.IEnergyAcceptor;
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	public static boolean acceptsRF(TileEntity aTileEntity) {
		try {
			Class<?> api = Class.forName("cofh.api.energy.IEnergyReceiver");
			if (api != null && api.isAssignableFrom(aTileEntity.getClass())) {
				return true;
			}
			
		} catch (Throwable e) {/*Do nothing*/}
		return false;
	}
	
	public static Object sBoxableWrapper = GT_Utility.callConstructor("gregtechmod.api.util.GT_IBoxableWrapper", 0, null, false);
	
	public static void registerBoxableItemToToolBox(ItemStack aStack) {
		if (aStack != null) registerBoxableItemToToolBox(aStack.getItem());
	}
	
	public static void registerBoxableItemToToolBox(Item aItem) {
		if (aItem != null && sBoxableWrapper != null) {
			try {
				ic2.api.item.ItemWrapper.registerBoxable(aItem, (IBoxable)sBoxableWrapper);
			} catch(Throwable e) {/*Do nothing*/}
		}
	}
	
	public static int getCapsuleCellContainerCountMultipliedWithStackSize(ItemStack... aStacks) {
		int rAmount = 0;
		for (ItemStack tStack : aStacks) if (tStack != null) rAmount += getCapsuleCellContainerCount(tStack) * tStack.stackSize;
		return rAmount;
	}
	
	public static int getCapsuleCellContainerCount(ItemStack aStack) {
		if (aStack == null) return 0;
		Item tItem = aStack.getItem();
		if (tItem == null) return 0;
		if (GT_Utility.areStacksEqual(GT_Utility.getContainerForFilledItem(aStack), GT_Items.Cell_Empty.get(1)) || OrePrefixes.cell.contains(aStack) || OrePrefixes.cellPlasma.contains(aStack)) return 1;
		if (GT_Utility.areStacksEqual(aStack, getIC2Item("hydratedCoalCell", 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) return 1;
		return 0;
	}
}