package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;
import java.util.function.IntUnaryOperator;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public class RecipeLogic {
	public static final Random recipeRandom = new Random();
	
	public final WeakReference<IRecipeWorkable> metaTileEntity;
	public final RecipeMap<?> recipeMap;
	
	public int batterySlot 		= 5;
	/** Custom fucntion called every recipe progress time update, if you want to speed up machine because of some factor, just increase the value up */
	protected IntUnaryOperator progressTimeManipulator = i -> i;
	protected Recipe previousRecipe;
	protected int maxProgressTime;
	protected int progressTime;
	protected int EUt;
	
	protected int overclockersCount;
	protected boolean stuttering;
	protected boolean wasNoEnergy;
	
	public RecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
		int inputs = machine.getInputItems().size();
		int outputs = machine.getOutputItems().size();
		
		if (recipeMap != null) {
			if (inputs < recipeMap.minInputs || inputs > recipeMap.maxInputs || outputs < recipeMap.minOutputs || outputs > recipeMap.maxOutputs) {
//				throw new IllegalArgumentException("Wrong recipe map was supplied to machine!\n" + "inputs: " + inputs + "; outputs=" + outputs + "\n" + recipeMap.toString());
				
			}
		} else {
			GT_Log.log.warn("RecipeMap for machine with class name " + machine.getClass().getSimpleName() + " == null! Make sure you set up custom recipe provoder, otherwise machine will spam errors");
		}
		
		this.recipeMap = recipeMap;
		maxProgressTime = 0;
		progressTime = 0;
		EUt = 0;
		overclockersCount = 0;
		stuttering = false;
		wasNoEnergy = false;
		metaTileEntity = new WeakReference<>(machine);
	}
	
	public boolean update() {
		boolean success = false;
		IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
		overclockersCount = base.getOverclockerUpgradeCount();
		
		if (base.isAllowedToWork()) {
			if (progressTime > 0) {
				int tmp = progressTime;
				success = updateRecipeProgress();
				if (tmp == 0 && !success) {
					throw new IllegalStateException();
				}
			}
			
			if (progressTime == 0) {
				if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {// || isInputNonEmpty()) {
					if (base.isUniversalEnergyStored(getMachine().getMinimumStoredEU() - 100)) {
						trySerachRecipe();
						wasNoEnergy = false;
					} else {
						previousRecipe = null;
						wasNoEnergy = true;
						base.setActive(false);
					} 
				}
			}
		} 
		
		return success;
	}
	
	public void setProgressTimeManipulator(IntUnaryOperator applier) {
		progressTimeManipulator = applier;
	}
	
	protected boolean updateRecipeProgress() {
		if (getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(EUt * (int)Math.pow(4, overclockersCount), false)) {
			if ((progressTime += progressTimeManipulator.applyAsInt((int)Math.pow(2, overclockersCount))) >= maxProgressTime) {
				progressTime = 0;
				maxProgressTime = 0;
				EUt = 0;
				
				endRecipe(previousRecipe);
				getMachine().endProcess();
				return true;
			}
		} else {
			if (!stuttering) {
				getMachine().stutterProcess();
				stuttering = true;
			}
		}
		
		return false;
	}
	
	protected void trySerachRecipe() {
		if (getMachine().allowToCheckRecipe()) {
			if (previousRecipe != null) {
				if (match(previousRecipe)) {
					startRecipe(previousRecipe);
				} else {
					previousRecipe = null;
					getMachine().getBaseMetaTileEntity().setActive(false);
				}
			} else {
				// find new recipe
				Recipe resRec = findRecipe();
				if (resRec != null)
					startRecipe(resRec);
			}
		}
	}
	
	protected Recipe findRecipe() {
		return recipeMap.findRecipe(getMachine().getInputItems(), getMachine().getFluidInputs());
	}
	
	protected boolean match(Recipe recipe) {
		return recipe.matches(false, getMachine().getInputItems(), getMachine().getFluidInputs());
	}
	
	protected boolean consumeInputs(Recipe recipe) {
		return recipe.matches(true, getMachine().getInputItems(), getMachine().getFluidInputs());
	}
	
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe)) {
			previousRecipe = recipe;
			maxProgressTime = GT_Utility.isDebugItem(getMachine().getStackInSlot(batterySlot)) ? 1 : recipe.getDuration();
			progressTime = 1;
			EUt = recipe.getEUt();
			if (consumeInputs(recipe)) {
				getMachine().getBaseMetaTileEntity().setActive(true);
				getMachine().startProcess();
			} else {
				GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
				EUt = 0;
				progressTime = 0;
				maxProgressTime = 0;
				previousRecipe = null;
			}
			
		} else {
			getMachine().getBaseMetaTileEntity().setActive(false);
		}
	}
	
	/**
	 * Will put outputs to machine and execute machine's end recipe callbacks
	 * @param recipe
	 */
	protected void endRecipe(Recipe recipe) {
		List<ItemStack> outputs = getMachine().getOutputItems();
		List<ItemStack> recipeOutputs = recipe.getResults(recipeRandom);
		
		for (ItemStack recipeOut : recipeOutputs) {
			int amount = recipeOut.stackSize;
			for (int i = 0; i < outputs.size() && amount > 0; i++) {
				ItemStack slot = outputs.get(i);
				if (GT_Utility.areStacksEqual(recipeOut, slot)) {
					int newSize = Math.min(slot.getMaxStackSize(), slot.stackSize + amount);
					amount -= newSize - slot.stackSize;
					slot.stackSize = newSize;
				}
			}
			
			for (int i = 0; i < outputs.size() && amount > 0; i++) {
				ItemStack slot = outputs.get(i);
				if (slot == null) {
					ItemStack stack = recipeOut.copy();
					stack.stackSize = amount;
					outputs.set(i, stack);
					amount = 0;
					break;
				}
			}
			
			if (amount > 0)
				GT_Log.log.error("Output overflow detected! Left items: " + amount + " for output stack: " + recipeOut);
		}
		
		stuttering = false;
		getMachine().endProcess();
	}
	
	protected boolean isInputNonEmpty() {
		return !getMachine().getInputItems().isEmpty() || !getMachine().getFluidInputs().isEmpty();
	}
	
	protected IRecipeWorkable getMachine() {
		return metaTileEntity.get();
	}
	
	public void increaseProgressTime(int amount) {
		progressTime += amount;
	}
	
	public boolean isActive() {
		return maxProgressTime > 0;
	}
	
	public int getMaxProgressTime() {
		return maxProgressTime;
	}
	
	public int getProgressTime() {
		return progressTime;
	}
	
	/**
	 * Using ONLY for display time
	 */
	public int getDisplayMaxProgress() {
		return maxProgressTime / (int)Math.pow(2, overclockersCount) / progressTimeManipulator.applyAsInt(1);
	}
	
	/**
	 * Using ONLY for display time
	 */
	public int getDisplayProgress() {
		return progressTime / (int)Math.pow(2, overclockersCount) / progressTimeManipulator.applyAsInt(1);
	}
	
	public int getEUt() {
		return EUt;
	}
	
	public void saveToNBT(NBTTagCompound data) {
		NBTTagCompound data1 = new NBTTagCompound();
		data1.setInteger("TotalTime", maxProgressTime);
		data1.setInteger("CurrentTime", progressTime);
		data1.setInteger("EUt", EUt);
		if (previousRecipe != null && progressTime > 0) {
			previousRecipe.writeToNBT(data1);
		}
		data.setTag("RecipeLogic", data1);
	}
	
	public void loadFromNBT(NBTTagCompound data) {
		NBTTagCompound data1 = data.getCompoundTag("RecipeLogic");
		if (data1 != null && progressTime == 0) {
			maxProgressTime = data1.getInteger("TotalTime");
			progressTime = data1.getInteger("CurrentTime");
			EUt = data1.getInteger("EUt");
			Recipe res = Recipe.loadFromNBT(recipeMap, data1);
			if (res != null) {
				previousRecipe = res;
			}
		}
	}
}
