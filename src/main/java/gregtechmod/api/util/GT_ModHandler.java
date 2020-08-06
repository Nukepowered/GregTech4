package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IBasicEnergyContainer;
import gregtechmod.api.interfaces.ICapsuleCellContainer;
import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import ic2.api.item.IBoxable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
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
	public static volatile int VERSION = 404;
	
	/**
	 * These are getting assigned somewhen in the Load Phase
	 */
	public static ItemStack
			mNuggetIron	= null,
			mNuggetCopper = null,
			mNuggetTin = null,
			mNuggetSilver = null,
			mNuggetLead	= null,
			mIronNugget = null,
			mSilverNugget = null,
			mTinNugget = null,
			mCopperNugget = null,
			mRuby = null,
			mGreenSapphire = null,
			mSapphire = null,
			mSilver = null,
			mTin = null,
			mCopper = null,
			mNikolite = null,
			mRedAlloy = null,
			mBlueAlloy = null,
			mBrass = null,
			mSiliconBoule = null,
			mSiliconWafer = null,
			mBlueWafer = null,
			mRedWafer = null,
			mRPTinPlate = null,
			mFineCopper = null,
			mFineIron = null,
			mCopperCoil = null,
			mBlutricMotor = null,
			mCanvas = null,
			mDiamondDrawplate = null,
			mBCFuelBucket = null,
			mBCWoodGear = null,
			mBCStoneGear = null,
			mBCIronGear = null,
			mBCGoldGear = null,
			mBCDiamondGear = null,
			mIC_Cell = null,
			mIC_AirCell = null,
			mIC_WaterCell = null,
			mIC_LavaCell = null,
			mIC_Fuelcan = null;
	
	public static boolean isIC2loaded() {
		return getIC2Item("resin", 1) != null;
	}
	
	public static boolean isRCloaded() {
		return getRCItem("machine.alpha.rolling.machine", 1) != null;
	}
	
	public static boolean isTEloaded() {
		return getTEItem("slag", 1) != null;
	}
	
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
	public static FluidStack getWater(int aAmount) {
		return FluidRegistry.getFluidStack("water", aAmount);
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
	public static FluidStack getLava(int aAmount) {
		return FluidRegistry.getFluidStack("lava", aAmount);
	}
	
	/**
	 * Returns if that Liquid is Steam
	 */
	public static boolean isSteam(FluidStack aLiquid) {
		if (aLiquid == null) return false;
		return aLiquid.isFluidEqual(getSteam(1));
	}
	
	/**
	 * Returns a Liquid Stack with given amount of Steam.
	 */
	public static FluidStack getSteam(int aAmount) {
		return FluidRegistry.getFluidStack("steam", aAmount);
	}
	
	/**
	 * I was just really tired of always writing the same String, without being able to just use Ctrl+Space for Auto-Completing the Function
	 */
	public static ItemStack getEmptyFuelCan(int aAmount) {
		if (mIC_Fuelcan == null) {
			mIC_Fuelcan = getIC2Item("fuelCan", 1);
			if (mIC_Fuelcan == null) {
				mIC_Fuelcan = getIC2Item("fuelCanEmpty", 1);
				if (mIC_Fuelcan == null) {
					mIC_Fuelcan = getIC2Item("emptyFuelCan", 1);
				}
			}
		}
		return GT_Utility.copy(aAmount, mIC_Fuelcan);
	}
	
	/**
	 * I was just really tired of always writing the same String, without being able to just use Ctrl+Space for Auto-Completing the Function
	 */
	public static ItemStack getEmptyCell(int aAmount) {
		if (mIC_Cell == null) {
			mIC_Cell = getIC2Item("cell", 1);
			if (mIC_Cell == null) {
				mIC_Cell = getIC2Item("cellEmpty", 1);
				if (mIC_Cell == null) {
					mIC_Cell = getIC2Item("emptyCell", 1);
					if (mIC_AirCell == null) {
						mIC_AirCell = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Empty, 1);
					}
				}
			}
		}
		return GT_Utility.copy(aAmount, mIC_Cell);
	}
	
	/**
	 * I was just really tired of always writing the same String, without being able to just use Ctrl+Space for Auto-Completing the Function
	 */
	public static ItemStack getAirCell(int aAmount) {
		if (mIC_AirCell == null) {
			mIC_AirCell = getIC2Item("airCell", 1);
			if (mIC_AirCell == null) {
				mIC_AirCell = getIC2Item("cellAir", 1);
				if (mIC_AirCell == null) {
					mIC_AirCell = getIC2Item("cellOxygen", 1);
					if (mIC_AirCell == null) {
						mIC_AirCell = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oxygen, 1);
					}
				}
			}
		}
		return GT_Utility.copy(aAmount, mIC_AirCell);
	}
	
	/**
	 * I was just really tired of always writing the same String, without being able to just use Ctrl+Space for Auto-Completing the Function
	 */
	public static ItemStack getWaterCell(int aAmount) {
		if (mIC_WaterCell == null) {
			mIC_WaterCell = getIC2Item("waterCell", 1);
			if (mIC_WaterCell == null) {
				mIC_WaterCell = getIC2Item("cellWater", 1);
				if (mIC_AirCell == null) {
					mIC_AirCell = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1);
				}
			}
		}
		return GT_Utility.copy(aAmount, mIC_WaterCell);
	}
	
	/**
	 * I was just really tired of always writing the same String, without being able to just use Ctrl+Space for Auto-Completing the Function
	 */
	public static ItemStack getLavaCell(int aAmount) {
		if (mIC_LavaCell == null) {
			mIC_LavaCell = getIC2Item("lavaCell", 1);
			if (mIC_LavaCell == null) {
				mIC_LavaCell = getIC2Item("cellLava", 1);
				if (mIC_AirCell == null) {
					mIC_AirCell = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Lava, 1);
				}
			}
		}
		return GT_Utility.copy(aAmount, mIC_LavaCell);
	}
	
	private static final Map<String, ItemStack> sIC2ItemMap = new HashMap<String, ItemStack>();
	
	/**
	 * Gets an Item from IndustrialCraft, and returns a Replacement Item if not possible
	 */
	public static ItemStack getIC2Item(String aItem, int aAmount, ItemStack aReplacement) {
		if (aItem == null || aItem.equals("") || !GregTech_API.sPreloadStarted) return null;
		if (!sIC2ItemMap.containsKey(aItem)) try {sIC2ItemMap.put(aItem, ic2.api.item.IC2Items.getItem(aItem));} catch (Throwable e) {}
		return GT_Utility.copy(aAmount, sIC2ItemMap.get(aItem), aReplacement);
	}
	
	/**
	 * Gets an Item from IndustrialCraft, but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getIC2Item(String aItem, int aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getIC2Item(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		rStack.setItemDamage(aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from IndustrialCraft, but the Damage Value can be specified
	 */
	public static ItemStack getIC2Item(String aItem, int aAmount, int aMeta) {
		return getIC2Item(aItem, aAmount, aMeta, null);
	}
	
	/**
	 * Gets an Item from IndustrialCraft
	 */
	public static ItemStack getIC2Item(String aItem, int aAmount) {
		return getIC2Item(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from RailCraft
	 */
	public static ItemStack getRCItem(String aItem, int aAmount) {
		return getRCItem(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from RailCraft, and returns a Replacement Item if not possible
	 */
	public static ItemStack getRCItem(String aItem, int aAmount, ItemStack aReplacement) {
		if (aItem == null || aItem.equals("")) return null;
		return GT_Utility.copy(aAmount, GameRegistry.findItemStack("Railcraft", aItem, aAmount), aReplacement);
	}
	
	/**
	 * Gets an Item from RailCraft, but the Damage Value can be specified
	 */
	public static ItemStack getRCItem(String aItem, int aAmount, int aMeta) {
		ItemStack rStack = getRCItem(aItem, aAmount);
		if (rStack == null) return null;
		rStack.setItemDamage(aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from RailCraft, but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getRCItem(String aItem, int aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getRCItem(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		rStack.setItemDamage(aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from ThermoCraft, and returns a Replacement Item if not possible
	 * Changed: Return items only from ThermalExpansion's TEItems, not ThermalFoundation, ThermalDynamics
	 */
	public static ItemStack getTEItem(String aItem, int aAmount, ItemStack aReplacement) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		try {
			Class<?> teItems = Class.forName("cofh.thermalexpansion.item.TEItems");
			Field field = teItems.getDeclaredField(aItem);
			if (field != null && ItemStack.class.isAssignableFrom(field.getType())) {
				rStack = ((ItemStack) field.get(null)).copy();
			}
		} catch (Throwable e) {}
		return GT_Utility.copy(aAmount, rStack, aReplacement);
	}
	
	/**
	 * Gets an Item from ThermoCraft, but the Damage Value can be specified
	 */
	public static ItemStack getTEItem(String aItem, int aAmount, int aMeta) {
		ItemStack rStack = getTEItem(aItem, aAmount);
		if (rStack == null) return null;
		rStack.setItemDamage(aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from ThermoCraft
	 */
	public static ItemStack getTEItem(String aItem, int aAmount) {
		return getTEItem(aItem, aAmount, null);
	}
	
	/**
	 * Gets an Item from ThermoCraft, but the Damage Value can be specified, and returns a Replacement Item with the same Damage if not possible
	 */
	public static ItemStack getTEItem(String aItem, int aAmount, int aMeta, ItemStack aReplacement) {
		ItemStack rStack = getTEItem(aItem, aAmount, aReplacement);
		if (rStack == null) return null;
		rStack.setItemDamage(aMeta);
		return rStack;
	}
	
	/**
	 * Gets an Item from Forestry
	 */
	public static ItemStack getFRItem(String aItem, int aAmount) {
		if (aItem == null || aItem.equals("")) return null;
		ItemStack rStack = null;
		try {
			Class<?> frPlugins = Class.forName("forestry.plugins.PluginCore");
			Class<?> itemRegistry = Class.forName("forestry.core.items.ItemRegistryCore");
			Field items = frPlugins.getDeclaredField("items");
			Field item = itemRegistry.getDeclaredField(aItem);
			Object instance = items.get(null);
			if (Item.class.isAssignableFrom(item.getType())) {
				Item i = (Item) item.get(instance);
				rStack = new ItemStack(i, aAmount);
			}
		} catch (Throwable e) {}
		return GT_Utility.copy(aAmount, rStack);
	}
	
	/**
	 * @param aValue Fuel value in EU
	 */
	public static ItemStack getFuelCan(int aValue) {
		if (aValue <= 0) return getIC2Item("fuelCan", 1);
		ItemStack rFuelCanStack = getIC2Item("filledFuelCan", 1);
		if (rFuelCanStack == null) return null;
		NBTTagCompound tNBT = new NBTTagCompound();
        tNBT.setInteger("value", aValue/5);
        rFuelCanStack.setTagCompound(tNBT);
        return rFuelCanStack;
	}
	
	/**
	 * @param aFuelCan the Item you want to check
	 * @return the exact Value in EU the Fuel Can is worth if its even a Fuel Can.
	 */
	public static int getFuelCanValue(ItemStack aFuelCan) {
		if (aFuelCan == null || !GT_Utility.areStacksEqual(aFuelCan, getIC2Item("filledFuelCan", 1))) return 0;
		NBTTagCompound tNBT = aFuelCan.getTagCompound();
		if (tNBT == null) return 0;
		return tNBT.getInteger("value")*5;
	}
	
	/**
	 * adds an RC-Boiler Fuel
	 */
	public static void addBoilerFuel(FluidStack aLiquid, int aValue) {
		if (aLiquid == null || aValue <= 0) return;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.boilerfuels, aLiquid.getFluid().getUnlocalizedName(), true)) return;
		try {
			mods.railcraft.api.fuel.FuelManager.addBoilerFuel(aLiquid.getFluid(), aValue);
		} catch(Throwable e) {}
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
	public static boolean addValuableOre(ItemStack stack, int aValue) {
		if (aValue <= 0) return false;
		try {
			Class<?> recipeInputItemStack = Class.forName("ic2.api.recipe.RecipeInputItemStack");
			Object recipeInputStack = recipeInputItemStack.getConstructor(ItemStack.class).newInstance(stack);
			Class.forName("ic2.core.IC2").getMethod("addValuableOre", recipeInputItemStack, int.class).invoke(recipeInputStack, aValue);
		} catch (Throwable e) {}
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
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.scrapboxdrops, aOutput, true)) return false;
		try {
			GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addDrop", true, false, true, GT_Utility.copy(aOutput), aChance);
			GT_Utility.callMethod(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "addRecipe", true, true, false, GT_Utility.copy(aOutput), aChance);
		} catch (Throwable e) {}
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
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.smelting, aInput, true)) return false;
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
		if (GregTech_API.sRecipeAdder.addAlloySmelterRecipe(aInput, null, aOutput, 130, 3)) temp = true;
		if (addInductionSmelterRecipe(aInput, null, aOutput, null, aOutput.stackSize*100, 0)) temp = true;
		return temp;
	}
	
	/**
	 * Adds a Recipe to Forestrys Squeezer
	 */
	public static boolean addSqueezerRecipe(ItemStack aInput, FluidStack aOutput, int aTime) {
		if (aInput == null || aOutput == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.squeezer, aInput, true)) return false;
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
	public static boolean addLiquidTransposerRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aMJ) {
		aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
		if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.liquidtransposer, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.api.crafting.CraftingHandlers.transposer.addFillRecipe(aMJ, aEmptyContainer, aFullContainer, aLiquid, true, false);
		} catch(Throwable e) {}
		return true;
	}
	
	/**
	 * LiquidTransposer Recipe for filling Containers
	 */
	public static boolean addLiquidTransposerFillRecipe(ItemStack aEmptyContainer, FluidStack aLiquid, ItemStack aFullContainer, int aMJ) {
		aFullContainer = GT_OreDictUnificator.get(true, aFullContainer);
		if (aEmptyContainer == null || aFullContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.liquidtransposerfilling, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.api.crafting.CraftingHandlers.transposer.addFillRecipe(aMJ, aEmptyContainer, aFullContainer, aLiquid, false, false);
		} catch(Throwable e) {}
		return true;
	}
	
	/**
	 * LiquidTransposer Recipe for emptying Containers
	 */
	public static boolean addLiquidTransposerEmptyRecipe(ItemStack aFullContainer, FluidStack aLiquid, ItemStack aEmptyContainer, int aMJ) {
		aEmptyContainer = GT_OreDictUnificator.get(true, aEmptyContainer);
		if (aFullContainer == null || aEmptyContainer == null || aLiquid == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.liquidtransposeremptying, aFullContainer, true)) return false;
		try {
			cofh.thermalexpansion.api.crafting.CraftingHandlers.transposer.addExtractionRecipe(aMJ, aFullContainer, aEmptyContainer, aLiquid, 100, false, false);
		} catch(Throwable e) {}
		return true;
	}
	
	/**
	 * IC2-Extractor Recipe. Overloads old Recipes automatically
	 */
	public static boolean addExtractionRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), null);
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.extractor, aInput, true)) return false;
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getExtractorRecipeList(), aOutput);
		return true;
	}
	
	/**
	 * RC-BlastFurnace Recipes
	 */
	public static boolean addRCBlastFurnaceRecipe(ItemStack aInput, ItemStack aOutput, int aTime) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null || aTime <= 0) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.rcblastfurnace, aInput, true)) return false;
		aInput = GT_Utility.copy(aInput);
		aOutput = GT_Utility.copy(aOutput);
		if (!isRCloaded()) return false;
		try {
			mods.railcraft.api.crafting.RailcraftCraftingManager.blastFurnace.addRecipe(aInput, true, false, aTime, aOutput);
		} catch (Throwable e) {}
		return true;
	}
	
	private static Map<Integer, Object> sPulverizerRecipes = new HashMap<Integer, Object>();
	
	/**
	 * @return Object that can either be cast into IPulverizerRecipe or into GT_PulverizerRecipe
	 */
	public static Object getPulverizerRecipe(ItemStack aInput) {
		if (aInput == null) return null;
		Object tObject = sPulverizerRecipes.get(GT_Utility.stackToInt(aInput));
		if (tObject != null) {
			return tObject;
		}
		
		ItemStack tInput = GT_Utility.copy(aInput);
		tInput.setItemDamage(GregTech_API.ITEM_WILDCARD_DAMAGE);
		tObject = sPulverizerRecipes.get(GT_Utility.stackToInt(tInput));
		if (tObject != null) {
			return tObject;
		}
		
		try {
    		for (cofh.thermalexpansion.util.crafting.PulverizerManager.RecipePulverizer tRecipe : cofh.thermalexpansion.util.crafting.PulverizerManager.getRecipeList()) {
    			if (GT_Utility.areStacksEqual(tRecipe.getInput(), aInput)) {
		    		return tRecipe;
    			}
    		}
		} catch(Throwable e) {}
		return null;
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1) {
		return addPulverisationRecipe(aInput, aOutput1, null, 0, false, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, false, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, aChance, false, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, boolean aOverwrite) {
		return addPulverisationRecipe(aInput, aOutput1, null, 0, aOverwrite, false);
	}

	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, boolean aOverwrite) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, 100, aOverwrite, false);
	}
	
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite) {
		return addPulverisationRecipe(aInput, aOutput1, aOutput2, aChance, aOverwrite, false);
	}
	
	/**
	 * Adds Several Pulverizer-Type Recipes.
	 */
	@SuppressWarnings("deprecation")
	public static boolean addPulverisationRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, int aChance, boolean aOverwrite, boolean aTakeOriginalPrimary) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		if (aInput == null || aOutput1 == null || aInput.getItem() == null || aOutput1.getItem() == null) return false;
		if (aTakeOriginalPrimary) {
			ItemStack tOriginal = getMaceratorOutput(aInput, false, null);
			if (tOriginal != null) {
				aOutput1 = GT_OreDictUnificator.get(true, tOriginal);
			}
		}
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), null);
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.maceration, aInput, true)) return false;
		if (!aInput.getItem().hasContainerItem()) {
			if (aInput.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE) {
				for (byte i = 0; i < 16; i++) GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(aInput.getItem(), aInput.stackSize, i), getMaceratorRecipeList(), null);
				GT_Utility.addSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), aOutput1);
			} else {
				GT_Utility.addSimpleIC2MachineRecipe(aInput, getMaceratorRecipeList(), aOutput1);
			}
		}
		try {
			sPulverizerRecipes.put(GT_Utility.stackToInt(aInput), new GT_PulverizerRecipe(aInput, aOutput1, aOutput2, aChance<=0?10:aChance));
		} catch(Throwable e) {}
		if (Materials.Wood.contains(aOutput1)) {
			try {
				if (!aInput.getItem().hasContainerItem()) {
					if (aOutput2 == null)
						cofh.thermalexpansion.api.crafting.CraftingHandlers.sawmill.addRecipe(80, GT_Utility.copy(aInput), GT_Utility.copy(aOutput1), null, 0, aOverwrite);
					else
						cofh.thermalexpansion.api.crafting.CraftingHandlers.sawmill.addRecipe(80, GT_Utility.copy(aInput), GT_Utility.copy(aOutput1), GT_Utility.copy(aOutput2), aChance<=0?10:aChance, aOverwrite);
				}
			} catch(Throwable e) {}
		} else {
			try {
				if (!aInput.getItem().hasContainerItem()) {
					if (OrePrefixes.ingot.contains(aInput)) {
						appeng.api.AEApi.instance().registries().grinder().addRecipe(aInput, aOutput1, 5);
					}
				}
			} catch(Throwable e) {}
			try {
				if (!aInput.getItem().hasContainerItem()) {
					if (Block.getBlockFromItem(aInput.getItem()) != Blocks.obsidian)  {
						mods.railcraft.api.crafting.IRockCrusherRecipe tRecipe = mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.createNewRecipe(GT_Utility.copy(1, aInput), aInput.getItemDamage() != GregTech_API.ITEM_WILDCARD_DAMAGE, false);
						tRecipe.addOutput(GT_Utility.copy(aOutput1), 1.0F/aInput.stackSize);
						tRecipe.addOutput(GT_Utility.copy(aOutput2), (0.01F*(aChance<=0?10:aChance))/aInput.stackSize);
					}
				}
			} catch(Throwable e) {}
			try {
				if (!aInput.getItem().hasContainerItem()) {
					if (aOutput2 == null) {
						cofh.thermalexpansion.api.crafting.CraftingHandlers.pulverizer.addRecipe(400, GT_Utility.copy(aInput), GT_Utility.copy(aOutput1), null, 0, aOverwrite);
					} else {
						cofh.thermalexpansion.api.crafting.CraftingHandlers.pulverizer.addRecipe(400, GT_Utility.copy(aInput), GT_Utility.copy(aOutput1), GT_Utility.copy(aOutput2), aChance<=0?10:aChance, aOverwrite);
					}
				}
			} catch(Throwable e) {}
		}
		return true;
	}
	
	/**
	 * Adds a Recipe to the Sawmills of GregTech and ThermalCraft
	 */
	public static boolean addSawmillRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		if (aInput1 == null || aOutput1 == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.sawmill, aInput1, true)) return false;
	    try {
	    	cofh.thermalexpansion.api.crafting.CraftingHandlers.sawmill.addRecipe(160, aInput1, aOutput1, aOutput2, 100, false);
		} catch(Throwable e) {}
	    GregTech_API.sRecipeAdder.addSawmillRecipe(aInput1, getWaterCell(1), aOutput1, aOutput2, getEmptyCell(1));
		return true;
	}
	
	/**
	 * Induction Smelter Recipes and Alloy Smelter Recipes
	 */
	public static boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		if (aInput1 == null || aInput2 == null || aOutput1 == null) return false;
		boolean temp = false;
		if (GregTech_API.sRecipeAdder.addAlloySmelterRecipe(aInput1, aInput2, aOutput1, aDuration, aEUt)) temp = true;
		if (addInductionSmelterRecipe(aInput1, aInput2, aOutput1, null, aDuration * 2, 0)) temp = true;
		return temp;
	}
	
	/**
	 * Induction Smelter Recipes for TE
	 */
	@SuppressWarnings("deprecation")
	public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {
		aOutput1 = GT_OreDictUnificator.get(true, aOutput1);
		aOutput2 = GT_OreDictUnificator.get(true, aOutput2);
		if (aInput1 == null || aOutput1 == null || aInput1.getItem().hasContainerItem()) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.inductionsmelter, aInput2==null?aInput1:aOutput1, true)) return false;
	    try {
	    	cofh.thermalexpansion.api.crafting.CraftingHandlers.smelter.addRecipe(aEnergy, GT_Utility.copy(aInput1), aInput2==null?new ItemStack(Blocks.sand, 1, 0):aInput2, aOutput1, aOutput2, aChance, false);
		} catch(Throwable e) {}
		return true;
	}
	
	/**
	 * Smelts dusts to Ingots
	 */
	public static boolean addDustToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		cofh.thermalexpansion.api.crafting.CraftingHandlers.furnace.addRecipe(1000, aInput, aOutput, false);
		addSmeltingRecipe(aInput, aOutput);
		return true;
	}
	
	/**
	 * Smelts Ores to Ingots
	 */
	public static boolean addOreToIngotSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		cofh.thermalexpansion.api.crafting.CraftingHandlers.furnace.addRecipe(1600, aInput, aOutput, false);
		addSmeltingRecipe(aInput.copy(), GT_Utility.copy(aOutput));
		return true;
	}
	
	private static Map<Object	, Object>	sExtractorRecipes	= new HashMap<Object, Object>();
	private static Map<Object	, Object>	sMaceratorRecipes	= new HashMap<Object, Object>();
	private static Map<Object	, Object>	sCompressorRecipes	= new HashMap<Object, Object>();
	private static Map<ItemStack, Integer>	sMassfabRecipes		= new HashMap<ItemStack, Integer>();
	private static Map<ItemStack, Float>	sScrapboxRecipes	= new HashMap<ItemStack, Float>();
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getExtractorRecipeList() {
		try {
			return (Map<Object, Object>)GT_Utility.getField(ic2.api.recipe.Recipes.extractor.getClass(), "recipes").get(ic2.api.recipe.Recipes.extractor);
		} catch(Throwable e) {}
		return sExtractorRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getCompressorRecipeList() {
		try {
			return (Map<Object, Object>)GT_Utility.getField(ic2.api.recipe.Recipes.compressor.getClass(), "recipes").get(ic2.api.recipe.Recipes.compressor);
		} catch(Throwable e) {}
		return sCompressorRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getMaceratorRecipeList() {
		try {
			return (Map<Object, Object>)GT_Utility.getField(ic2.api.recipe.Recipes.macerator.getClass(), "recipes").get(ic2.api.recipe.Recipes.macerator);
		} catch(Throwable e) {}
		return sMaceratorRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<ItemStack, Integer> getMassFabricatorList() {
		try {
			return (Map<ItemStack, Integer>)GT_Utility.getField(ic2.api.recipe.Recipes.matterAmplifier.getClass(), "recipes").get(ic2.api.recipe.Recipes.matterAmplifier);
		} catch(Throwable e) {}
		return sMassfabRecipes;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<ItemStack, Float> getScrapboxList() {
		try {
			return (Map<ItemStack, Float>)GT_Utility.getField(ic2.api.recipe.Recipes.scrapboxDrops.getClass(), "recipes").get(ic2.api.recipe.Recipes.scrapboxDrops);
		} catch(Throwable e) {}
		return sScrapboxRecipes;
	}
	
	/**
	 * IC2-Compressor Recipe. Overloads old Recipes automatically
	 */
	public static boolean addCompressionRecipe(ItemStack aInput, ItemStack aOutput) {
		aOutput = GT_OreDictUnificator.get(true, aOutput);
		if (aInput == null || aOutput == null) return false;
		GT_Utility.removeSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), null);
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.compression, aInput, true)) return false;
		GT_Utility.addSimpleIC2MachineRecipe(aInput, getCompressorRecipeList(), aOutput);
		return true;
	}
	
	/**
	 * @param aValue Scrap = 5000, Scrapbox = 45000, Diamond Dust 125000
	 */
	public static boolean addIC2MatterAmplifier(ItemStack aAmplifier, int aValue) {
		if (aAmplifier == null || aValue <= 0) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.massfabamplifier, aAmplifier, true)) return false;
		try {
			GT_Utility.callMethod(ic2.api.recipe.Recipes.matterAmplifier, "addRecipe", false, true, false, aAmplifier, aValue);
			NBTTagCompound tNBT = new NBTTagCompound();
			tNBT.setInteger("amplification", aValue);
			GT_Utility.callMethod(ic2.api.recipe.Recipes.matterAmplifier, "addRecipe", false, false, false, aAmplifier, tNBT);
		} catch(Throwable e) {}
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
	private static ArrayList<IRecipe> sCraftingRecipeList = new ArrayList<IRecipe>(), sSingleNonBlockDamagableRecipeList = new ArrayList<IRecipe>();
	
	public static void stopBufferingCraftingRecipes() {
		sBufferCraftingRecipes = false;
		for (IRecipe tRecipe : sCraftingRecipeList) GameRegistry.addRecipe(tRecipe);
		sCraftingRecipeList.clear();
	}
	
	/**
	 * Special Handler for UUM Recipes
	 */
	public static boolean addUUMRecipe(ItemStack aResult, Object[] aRecipe) {
		if (aRecipe == null) return false;
		if (aResult != null && GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.uumrecipe, aResult, true)) {
			return addCraftingRecipe(aResult, true, false, aRecipe);
		} else {
			return addCraftingRecipe(null, true, false, aRecipe);
		}
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, Object[] aRecipe) {
		return addCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object[] aRecipe) {
		return addCraftingRecipe(aResult, isElectricItem(aResult), false, aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, Object[] aRecipe) {
		return addCraftingRecipe(aResult, isElectricItem(aResult), aMirrored, true, aRecipe);
	}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aMirrored, boolean aBuffered, Object[] aRecipe) {
		aResult = GT_OreDictUnificator.get(true, aResult);
		if (aRecipe == null || aRecipe.length <= 0) return false;
		for (byte i = 0; i < aRecipe.length; i++) {
			if (aRecipe[i] instanceof Enum) {
				aRecipe[i] = aRecipe[i].toString();
			}
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
	            	ItemStack tStack = GT_OreDictUnificator.getFirstOre((String)in, 1);
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
	            		tRecipe[x].setItemDamage(0);
		    }
	        removeRecipe(tRecipe);
	        break;
		}} catch(Throwable e) {GT_Log.log.catching(e);}
		
		if (aResult == null || aResult.stackSize <= 0) return false;
		
		if (aResult.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aResult.getItemDamage() < 0) throw new IllegalArgumentException();
		
		if (aUseIC2Handler) {
			try {
				ic2.api.recipe.Recipes.advRecipes.addRecipe(GT_Utility.copy(aResult), aRecipe);
				return true;
			} catch (Throwable e) {}
		}
		if (sBufferCraftingRecipes && aBuffered)
			sCraftingRecipeList.add(new ShapedOreRecipe(GT_Utility.copy(aResult), aRecipe).setMirrored(aMirrored));
		else
			GameRegistry.addRecipe(new ShapedOreRecipe(GT_Utility.copy(aResult), aRecipe).setMirrored(aMirrored));
		return true;
	}

	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object[] aRecipe) {
		return addShapelessCraftingRecipe(aResult, isElectricItem(aResult), aRecipe);
	}
	
	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, Object[] aRecipe) {
		return addShapelessCraftingRecipe(aResult, aUseIC2Handler, true, aRecipe);
	}
	
	/**
	 * Shapeless Crafting Recipes. Deletes conflicting Recipes too.
	 */
	public static boolean addShapelessCraftingRecipe(ItemStack aResult, boolean aUseIC2Handler, boolean aBuffered, Object[] aRecipe) {
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
					tRecipe[i] = GT_OreDictUnificator.getFirstOre((String)tObject, 1);
				} else if (tObject instanceof Boolean) {
					
				} else {
	                throw new IllegalArgumentException();
				}
				i++;
			}
	        removeRecipe(tRecipe);
		} catch(Throwable e) {GT_Log.log.catching(e);}
		
		if (aResult == null || aResult.stackSize <= 0) return false;
		
		if (aResult.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aResult.getItemDamage() < 0) throw new IllegalArgumentException();
		
		if (aUseIC2Handler) {
			try {
				ic2.api.recipe.Recipes.advRecipes.addShapelessRecipe(GT_Utility.copy(aResult), aRecipe);
				return true;
			} catch (Throwable e) {}
		}
		if (sBufferCraftingRecipes && aBuffered)
			sCraftingRecipeList.add(new ShapelessOreRecipe(GT_Utility.copy(aResult), aRecipe));
		else
			GameRegistry.addRecipe(new ShapelessOreRecipe(GT_Utility.copy(aResult), aRecipe));
		return true;
	}
	
	/**
	 * Removes a Smelting Recipe
	 */
	public static boolean removeFurnaceSmelting(ItemStack aInput, ItemStack aOutput) {
		boolean temp = false;
		if (aInput != null) {
			FurnaceRecipes.smelting().getSmeltingList().remove(aInput);
			temp = true;
		}
		if (aOutput != null) {
			
			temp = true;
		}
		return temp;
	}
	
	/**
	 * Removes a Crafting Recipe and gives you the former output of it.
	 * @param aRecipe The content of the Crafting Grid as ItemStackArray with length 9
	 * @return the output of the old Recipe or null if there was nothing.
	 */
    public static ItemStack removeRecipe(ItemStack[] aRecipe) {
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
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < aRecipe.length && i < 9; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			try {
				if (tList.get(i).matches(aCrafting, GregTech_API.sDummyWorld)) {
					rReturn = tList.get(i).getRecipeOutput();
					tList.remove(i--);
				}
			} catch(Throwable e) {GT_Log.log.catching(e);}
		}
		for (int i = 0; i < sCraftingRecipeList.size(); i++) {
			try {
				if (sCraftingRecipeList.get(i).matches(aCrafting, GregTech_API.sDummyWorld)) {
					rReturn = sCraftingRecipeList.get(i).getRecipeOutput();
					sCraftingRecipeList.remove(i--);
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
    public static boolean removeRecipe(ItemStack aOutput) {
    	if (aOutput == null) return false;
    	boolean rReturn = false;
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {
			if (GT_Utility.areStacksEqual(tList.get(i).getRecipeOutput(), aOutput)) {
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
    public static ItemStack getAllRecipeOutput(ItemStack[] aRecipe, World aWorld) {
    	if (aRecipe == null) return null;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		ItemStack rOutput = CraftingManager.getInstance().findMatchingRecipe(aCrafting, aWorld);
		return GT_Utility.copy(rOutput);
    }
    
    /**
     * Gives you a copy of the Output from a Crafting Recipe
     * Used for Recipe Detection.
     */
    public static ItemStack getRecipeOutput(ItemStack[] aRecipe) {
    	if (aRecipe == null) return null;
    	boolean temp = false;
    	for (byte i = 0; i < aRecipe.length; i++) {
    		if (aRecipe[i] != null) {
    			temp = true;
        		break;
    		}
    	}
    	if (!temp) return null;
		InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		@SuppressWarnings("unchecked")
		ArrayList<IRecipe> tList = (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < tList.size(); i++) {temp = false;
			try {
				temp = tList.get(i).matches(aCrafting, GregTech_API.sDummyWorld);
			} catch(Throwable e) {GT_Log.log.catching(e);}
			if (temp) {
				ItemStack tOutput = tList.get(i).getRecipeOutput();
				if (tOutput == null || tOutput.stackSize <= 0) {
					// Seriously, who would ever do that shit?
					if (!GregTech_API.sPostloadFinished) throw new GT_ItsNotMyFaultException("Seems another Mod added a Crafting Recipe with null Output. Tell the Developer of said Mod to fix that.");
				} else {
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
	public static ArrayList<ItemStack> getSingleNonBlockDamagableRecipeOutputs(ItemStack[] aRecipe) {
    	if (!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) sSingleNonBlockDamagableRecipeList.clear();
    	if (sSingleNonBlockDamagableRecipeList.isEmpty()) {
    		for (IRecipe tRecipe : (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList()) {
    			ItemStack tStack = tRecipe.getRecipeOutput();
    			if (tStack != null && tStack.getItem() != null && tStack.getMaxStackSize() == 1 && tStack.getMaxDamage() > 0 && !isElectricItem(tStack) && !(tStack.getItem() instanceof ItemBlock))
    				sSingleNonBlockDamagableRecipeList.add(tRecipe);
    		}
    	}
    	ArrayList<ItemStack> rList = getRecipeOutputs(aRecipe, sSingleNonBlockDamagableRecipeList, true);
    	if (!GregTech_API.sPostloadStarted || GregTech_API.sPostloadFinished) sSingleNonBlockDamagableRecipeList.clear();
    	return rList;
    }
    
    /**
     * Gives you a list of the Outputs from a Crafting Recipe
     * If you have multiple Mods, which add Bronze Armor for example
     */
    @SuppressWarnings("unchecked")
	public static ArrayList<ItemStack> getRecipeOutputs(ItemStack[] aRecipe) {
    	return getRecipeOutputs(aRecipe, (ArrayList<IRecipe>)CraftingManager.getInstance().getRecipeList(), false);
    }
    
    /**
     * Gives you a list of the Outputs from a Crafting Recipe
     * If you have multiple Mods, which add Bronze Armor for example
     */
    public static ArrayList<ItemStack> getRecipeOutputs(ItemStack[] aRecipe, List<IRecipe> aList, boolean aDeleteFromList) {
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
    	InventoryCrafting aCrafting = new InventoryCrafting(new Container() {public boolean canInteractWith(EntityPlayer var1) {return false;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) aCrafting.setInventorySlotContents(i, aRecipe[i]);
		for (int i = 0; i < aList.size(); i++) {
			temp = false;
			try {
				temp = aList.get(i).matches(aCrafting, GregTech_API.sDummyWorld);
			} catch(Throwable e) {GT_Log.log.catching(e);}
			if (temp) {
				ItemStack tOutput = aList.get(i).getRecipeOutput();
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
     * Used in my own Macerator. Decreases StackSize of the Input if wanted.
     */
    public static ItemStack getMaceratorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
    	return GT_Utility.copy(getMachineOutput(aInput, getMaceratorRecipeList(), aRemoveInput, aOutputSlot)[0]);
    }
    
    /**
     * Used in my own Extractor. Decreases StackSize of the Input if wanted.
     */
    public static ItemStack getExtractorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
    	return GT_Utility.copy(getMachineOutput(aInput, getExtractorRecipeList(), aRemoveInput, aOutputSlot)[0]);
    }
    
    /**
     * Used in my own Compressor. Decreases StackSize of the Input if wanted.
     */
    public static ItemStack getCompressorOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
    	return GT_Utility.copy(getMachineOutput(aInput, getCompressorRecipeList(), aRemoveInput, aOutputSlot)[0]);
    }
    
    /**
     * Used in my own Furnace.
     */
    public static ItemStack getSmeltingOutput(ItemStack aInput, boolean aRemoveInput, ItemStack aOutputSlot) {
    	if (aInput == null) return null;
    	ItemStack rStack = GT_Utility.copy(FurnaceRecipes.smelting().getSmeltingResult(aInput));
    	if (rStack != null && (aOutputSlot == null || (GT_Utility.areStacksEqual(rStack, aOutputSlot) && rStack.stackSize + aOutputSlot.stackSize <= aOutputSlot.getMaxStackSize()))) {
			if (aRemoveInput) aInput.stackSize--;
			return rStack;
		}
    	return null;
    }
    
    /**
     * Used in my own Machines. Decreases StackSize of the Input if wanted.
     * 
     * Checks also if there is enough Space in the Output Slots.
     */
    public static ItemStack[] getMachineOutput(ItemStack aInput, Map<Object, Object> aRecipeList, boolean aRemoveInput, ItemStack... aOutputSlots) {
    	if (aOutputSlots == null) return new ItemStack[0];
    	if (aInput == null) return new ItemStack[aOutputSlots.length];
    	try {
			for (Entry<Object, Object> tEntry : aRecipeList.entrySet()) {
				if (tEntry.getValue() != null) {
					if (tEntry.getKey() instanceof ItemStack) {
						if (GT_Utility.areStacksEqual((ItemStack)tEntry.getKey(), aInput) && aInput.stackSize >= ((ItemStack)tEntry.getKey()).stackSize) {
							if (aInput.stackSize >= ((ItemStack)tEntry.getKey()).stackSize) {
								if (tEntry.getValue() instanceof ItemStack) {
									if (aOutputSlots[0] == null || (GT_Utility.areStacksEqual(((ItemStack)tEntry.getKey()), aOutputSlots[0]) && ((ItemStack)tEntry.getKey()).stackSize + aOutputSlots[0].stackSize <= aOutputSlots[0].getMaxStackSize())) {
										if (aRemoveInput) aInput.stackSize-=((ItemStack)tEntry.getKey()).stackSize;
										return new ItemStack[] {GT_Utility.copy((ItemStack)tEntry.getValue())};
									}
									break;
								}
								
								@SuppressWarnings("unchecked")
								ItemStack[] tList = (ItemStack[])((List<ItemStack>)GT_Utility.getFieldContent(tEntry.getValue(), "items", false, false)).toArray();
								if (tList.length == 0) break;
								ItemStack[] rList = new ItemStack[aOutputSlots.length];
								
								for (byte i = 0; i < aOutputSlots.length; i++) {
									if (tList[i] != null) {
										if (aOutputSlots[i] == null || (GT_Utility.areStacksEqual(tList[i], aOutputSlots[i]) && tList[i].stackSize + aOutputSlots[i].stackSize <= aOutputSlots[i].getMaxStackSize())) {
											rList[i] = GT_Utility.copy(tList[i]);
										} else {
									    	return new ItemStack[aOutputSlots.length];
										}
									}
								}
								
								if (aRemoveInput) aInput.stackSize-=((ItemStack)tEntry.getKey()).stackSize;
								return rList;
							}
							break;
						}
					} else {
						Object temp = GT_Utility.callMethod(tEntry.getKey(), "matches", false, false, false, aInput);
						if (temp instanceof Boolean && (Boolean)temp) {
							temp = GT_Utility.callMethod(tEntry.getKey(), "getAmount", false, false, false);
							if (temp instanceof Integer && (Integer)temp <= aInput.stackSize) {
								@SuppressWarnings("unchecked")
								ItemStack[] tList = (ItemStack[])((List<ItemStack>)GT_Utility.getFieldContent(tEntry.getValue(), "items", false, false)).toArray();
								if (tList.length == 0) break;
								ItemStack[] rList = new ItemStack[aOutputSlots.length];
								
								for (byte i = 0; i < aOutputSlots.length; i++) {
									if (tList[i] != null) {
										if (aOutputSlots[i] == null || (GT_Utility.areStacksEqual(tList[i], aOutputSlots[i]) && tList[i].stackSize + aOutputSlots[i].stackSize <= aOutputSlots[i].getMaxStackSize())) {
											rList[i] = GT_Utility.copy(tList[i]);
										} else {
									    	return new ItemStack[aOutputSlots.length];
										}
									}
								}
								
								if (aRemoveInput) aInput.stackSize-=(Integer)temp;
								return rList;
							}
							break;
						}
					}
				}
			}
    	} catch(Throwable e) {
    		if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
    	}
    	return new ItemStack[aOutputSlots.length];
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
			if (ic2.api.recipe.Recipes.recyclerBlacklist.contains(aInput)) return null;
		} catch (Throwable e) {}
    	return getIC2Item("scrap", 1);
    }
    
    /**
     * For the Scrapboxinator
     */
	public static ItemStack getRandomScrapboxDrop(World aWorld) {
		ItemStack rStack = null;
		try {
			rStack = (ItemStack)GT_Utility.callMethod(GT_Utility.getField(ic2.api.recipe.Recipes.class, "scrapboxDrops"), "", false, false, false, GT_ModHandler.getIC2Item("scrapBox", 1), false);
		} catch (Throwable e) {}
		try {
			if (rStack == null) rStack = GT_OreDictUnificator.get(true, (ItemStack)Class.forName("ic2.core.item.ItemScrapbox").getMethod("getDrop", World.class).invoke(null, aWorld));
		} catch (Throwable e) {}
		return rStack;
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
		try {
			Object temp = GT_Utility.callPublicMethod(aTileEntity, "isAddedToEnergyNet");
			if (aTileEntity instanceof ic2.api.energy.tile.IEnergySource && temp != null && temp instanceof Boolean && (Boolean)temp) {
				try {
					// FIXME: future problem with energy IC2 here
//					Event tEvent = (Event)Class.forName("ic2.api.energy.event.EnergyTileSourceEvent").getConstructors()[0].newInstance((ic2.api.energy.tile.IEnergySource)aTileEntity, aVoltage);
//					MinecraftForge.EVENT_BUS.post(tEvent);
//					return ((ic2.api.energy.event.EnergyTileSourceEvent)tEvent).amount;
				} catch(Throwable e) {}
				return aVoltage;
			}
		} catch(Throwable e) {}
		if (aTileEntity instanceof IBasicEnergyContainer && aTileEntity instanceof IHasWorldObjectAndCoords) {
			for (byte i = 0; i < 6; i++) if (((IBasicEnergyContainer)aTileEntity).outputsEnergyTo(i)) {
				TileEntity tTileEntity = ((IHasWorldObjectAndCoords)aTileEntity).getTileEntityAtSide(i);
				if (tTileEntity instanceof IBasicEnergyContainer) {
					if (((IBasicEnergyContainer)tTileEntity).inputEnergyFrom(GT_Utility.getOppositeSide(i))) {
						if (((IBasicEnergyContainer)tTileEntity).getStoredEU() < ((IBasicEnergyContainer)tTileEntity).getEUCapacity()) {
							if (((IBasicEnergyContainer)tTileEntity).injectEnergyUnits(GT_Utility.getOppositeSide(i), aVoltage, 1)) {
								return 0;
							}
						}
					}
				}
			}
		}
		return aVoltage;
	}
	
	/**
	 * Charges an Electric Item. Only if it's a valid Electric Item of course.
	 * @return the actually used Energy.
	 */
	public static double chargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate) {
		try {
			if (isElectricItem(aStack)) {
				return ic2.api.item.ElectricItem.manager.charge(aStack, aCharge, aTier, aIgnoreLimit, aSimulate);
			}
		} catch (Throwable e) {}
		return 0;
	}
	
	/**
	 * Discharges an Electric Item. Only if it's a valid Electric Item for that of course.
	 * @return the Energy got from the Item.
	 */
	public static double dischargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean externally, boolean aSimulate, boolean aIgnoreDischargability) {
		try {
			if (isElectricItem(aStack)) {
				if (aIgnoreDischargability || ((ic2.api.item.IElectricItem)aStack.getItem()).canProvideEnergy(aStack)) {
					return ic2.api.item.ElectricItem.manager.discharge(aStack, aCharge, aTier, aIgnoreLimit, externally, aSimulate);
				}
			}
		} catch (Throwable e) {}
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
		} catch (Throwable e) {}
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
		} catch (Throwable e) {}
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
		} catch (Throwable e) {}
		return false;
	}
	
	/**
	 * Uses an Item. Tries to discharge in case of Electric Items
	 */
	@SuppressWarnings("deprecation")
	public static boolean damageOrDechargeItem(ItemStack aStack, int aDamage, int aDecharge, EntityLivingBase aPlayer) {
		if (aStack == null) return false;
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
				if (aStack.getItem().hasContainerItem()) {
					ItemStack tStack = aStack.getItem().getContainerItem(aStack);
					if (tStack != null) {
//						aStack.itemID = tStack.itemID; // TODO: wtf is it?
						aStack.setItemDamage(tStack.getItemDamage());
						aStack.stackSize = tStack.stackSize;
						aStack.setTagCompound(tStack.getTagCompound());
					}
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
		if (aPlayer instanceof EntityPlayer) {
			EntityPlayer tPlayer = (EntityPlayer)aPlayer;
			if (tPlayer.capabilities.isCreativeMode) return true;
			if (GT_Utility.isItemStackInList(aStack, GregTech_API.sSolderingToolList)) for (int i = 0; i < 36; i++) {
				if (GT_Utility.isItemStackInList(tPlayer.inventory.mainInventory[i], GregTech_API.sSolderingMetalList)) {
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
		} catch (Throwable e) {}
		return false;
	}
	
	/**
	 * Is this an electric Item?
	 */
	public static boolean isElectricItem(ItemStack aStack) {
		try {
			return aStack != null && aStack.getItem() instanceof ic2.api.item.IElectricItem;
		} catch (Throwable e) {}
		return false;
	}
	
	public static boolean acceptsGT(TileEntity aTileEntity) {
		try {
			return aTileEntity instanceof gregtechmod.api.interfaces.IBasicEnergyContainer;
		} catch (Throwable e) {}
		return false;
	}
	
	public static boolean acceptsEU(TileEntity aTileEntity) {
		try {
			return aTileEntity instanceof ic2.api.energy.tile.IEnergyAcceptor;
		} catch (Throwable e) {}
		return false;
	}
	
	public static boolean acceptsMJ(TileEntity aTileEntity) {
		try {
			return aTileEntity instanceof cofh.api.energy.IEnergyReceiver;
		} catch (Throwable e) {}
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
			} catch(Throwable e) {}
		}
	}
	
	public static int getCapsuleCellContainerCountMultipliedWithStackSize(ItemStack aStack) {
		if (aStack == null) return 0;
		return getCapsuleCellContainerCount(aStack)*aStack.stackSize;
	}
	
	@SuppressWarnings("deprecation")
	public static int getCapsuleCellContainerCount(ItemStack aStack) {
		if (aStack == null) return 0;
		if (GT_Utility.areStacksEqual(aStack, getEmptyCell(1))) return 1;
		Item tItem = aStack.getItem();
		if (tItem == null) return 0;
		ItemStack tStack = null;
		if (tItem.hasContainerItem() && null != (tStack = tItem.getContainerItem(aStack)) && GT_Utility.areStacksEqual(tStack, getEmptyCell(1))) return tStack.stackSize;
		if (tItem instanceof ICapsuleCellContainer) return ((ICapsuleCellContainer)tItem).CapsuleCellContainerCount(aStack);
		tStack = GT_Utility.getContainerForFilledItem(aStack);
		if (tStack != null && GT_Utility.areStacksEqual(tStack, getEmptyCell(1))) return 1;
		
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("cell"						, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("airCell"					, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("waterCell"				, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("lavaCell"					, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("hydratedCoalCell"			, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("coalfuelCell"				, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("bioCell"					, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("biofuelCell"				, 1))) return 1;
		if (GT_Utility.areStacksEqual(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), getIC2Item("electrolyzedWaterCell"	, 1))) return 1;
		return 0;
	}
}