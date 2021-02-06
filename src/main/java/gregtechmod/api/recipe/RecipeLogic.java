package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

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
	/** Do not consume inputs on custom recipe provider, it is only should <b><i>provide</i></b> a recipe instance */
	protected Supplier<Recipe> customRecipeProvider;
	protected int maxProgressTime;
	protected int progressTime;
	protected int EUt;
	
	private int overclockersCount;
	private Recipe previousRecipe;
	private boolean stuttering;
	private boolean wasNoEnergy;
	
	public RecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
		int inputs = machine.getInputItems().size();
		int outputs = machine.getOutputItems().size();
		
		if (inputs < recipeMap.minInputs || inputs > recipeMap.maxInputs || outputs < recipeMap.minOutputs || outputs > recipeMap.maxOutputs) {
			throw new IllegalArgumentException("Wrong recipe map was supplied to machine!\n" + "inputs: " + inputs + "; outputs=" + outputs + "\n" + recipeMap.toString());
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
				if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {
					if (!getMachine().getInputItems().isEmpty() && base.isUniversalEnergyStored(getMachine().getMinimumStoredEU() - 100)) {
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
	
	public void setRecipeProvider(Supplier<Recipe> handler) {
		customRecipeProvider = handler;
	}
	
	protected boolean updateRecipeProgress() {
		if (getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(EUt * (int)Math.pow(4, overclockersCount), false)) {
			if ((progressTime += (int)Math.pow(2, overclockersCount)) >= maxProgressTime) {
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
		if (customRecipeProvider == null) {
			return recipeMap.findRecipe(getMachine().getInputItems());
		} else return customRecipeProvider.get();
	}
	
	protected boolean match(Recipe recipe) {
		return recipe.matches(false, getMachine().getInputItems());
	}
	
	protected void consumeInputs(Recipe recipe) {
		recipe.matches(true, getMachine().getInputItems());
	}
	
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe)) {
			previousRecipe = recipe;
			maxProgressTime = GT_Utility.isDebugItem(getMachine().getStackInSlot(batterySlot)) ? 1 : recipe.getDuration();
			progressTime = 1;
			EUt = recipe.getEUt();
			consumeInputs(recipe);
			getMachine().getBaseMetaTileEntity().setActive(true);
			getMachine().startProcess();
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
			for (int i = 0; i < outputs.size(); i++) {
				ItemStack slot = outputs.get(i);
				if (slot == null) {
					outputs.set(i, recipeOut.copy());
					amount = 0;
				} else if (GT_Utility.areStacksEqual(recipeOut, slot)) {
					int newSize = Math.min(slot.getMaxStackSize(), slot.stackSize + amount);
					slot.stackSize = newSize;
					amount -= newSize;
				}
				
				if (amount == 0)
					break;
			}
			
			if (amount > 0)
				GT_Log.log.error("Output overflow detected! Left items: " + amount + " for output stack: " + recipeOut);
		}
		
		stuttering = false;
		getMachine().endProcess();
	}
	
	private IRecipeWorkable getMachine() {
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
		return maxProgressTime / (int)Math.pow(2, overclockersCount);
	}
	
	/**
	 * Using ONLY for display time
	 */
	public int getDisplayProgress() {
		return progressTime / (int)Math.pow(2, overclockersCount);
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
