package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public class RecipeLogic {
	public static final Random recipeRandom = new Random();
	
	public WeakReference<IRecipeWorkable> metaTileEntity;
	public final RecipeMap<?> recipeMap;
	
	public int MAX_FLUID_STACK = 16_000;
	public int batterySlot 		= 4;
	/** Custom fucntion called every recipe progress time update, if you want to speed up machine because of some factor, just increase the value up */
	protected IntUnaryOperator progressTimeManipulator = i -> i;
	protected Predicate<Recipe> metadataVerifier = recipe -> true;
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
		int fluidIn = machine.getFluidInputs().size();
		int fluidOut = machine.getFluidOutputs().size();

		if (recipeMap != null) {
			if (inputs < recipeMap.minInputs || inputs > recipeMap.maxInputs || outputs < recipeMap.minOutputs || outputs > recipeMap.maxOutputs
					|| fluidIn < recipeMap.minFluidInputs || fluidIn > recipeMap.maxFluidInputs || fluidOut < recipeMap.minFluidOutputs || fluidOut > recipeMap.maxFluidOutputs) {
				throw new IllegalArgumentException("Wrong recipe map was supplied to machine!\n"
						+ "inputs: " + inputs + "; outputs: " + outputs + "\n"
								+ "fluid inputs:" + fluidIn + "; fluid outputs: " + fluidOut + "\n" + recipeMap.toString());
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
	
	/** For child classes use only! */
	protected RecipeLogic(RecipeMap<?> map) {
		recipeMap = map;
	}
	
	public boolean update() {
		boolean success = false;
		IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
		overclockersCount = base.getOverclockerUpgradeCount();
		
		if (base.isAllowedToWork()) {
			if (progressTime > 0) {
				success = updateRecipeProgress();
			}
			
			if (progressTime == 0) {
				if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {// || isInputNonEmpty()) {
					if (base.isUniversalEnergyStored(getMachine().getMinimumStoredEU() - 100)) {
						trySerachRecipe();
						wasNoEnergy = false;
					} else {
						previousRecipe = null;
						wasNoEnergy = true;
						triggerMachine(false);
					} 
				} else {
					previousRecipe = null;
				}
			}
		} 
		
		return success;
	}
	
	public void setProgressTimeManipulator(IntUnaryOperator applier) {
		progressTimeManipulator = applier;
	}
	
	public void setMetadataVerify(Predicate<Recipe> verifier) {
		metadataVerifier = verifier;
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
					triggerMachine(false);
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
		return recipeMap.findRecipe(getMachine().getInputItems(), getMachine().getFluidInputs(), metadataVerifier);
	}
	
	protected boolean match(Recipe recipe) {
		return metadataVerifier.test(recipe) && recipe.matches(false, getMachine().getInputItems(), getMachine().getFluidInputs());
	}
	
	protected boolean consumeInputs(Recipe recipe) {
		return recipe.matches(true, getMachine().getInputItems(), getMachine().getFluidInputs());
	}
	
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe) && getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(recipe.getEUtoStart(), false)) {
			previousRecipe = recipe;
			maxProgressTime = GT_Utility.isDebugItem(getMachine().getStackIn(batterySlot)) ? 1 : recipe.getDuration();
			progressTime = 1;
			EUt = recipe.getEUt();
			if (consumeInputs(recipe)) {
				triggerMachine(true);
				getMachine().startProcess();
			} else {
				GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
				EUt = 0;
				progressTime = 0;
				maxProgressTime = 0;
				previousRecipe = null;
			}
			
		} else {
			triggerMachine(false);
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
				if (slot == null) {
					ItemStack stack = recipeOut.copy();
					stack.stackSize = amount;
					outputs.set(i, stack);
					amount = 0;
					break;
				} else if (GT_Utility.areStacksEqual(recipeOut, slot)) {
					int newSize = Math.min(slot.getMaxStackSize(), slot.stackSize + amount);
					amount -= newSize - slot.stackSize;
					slot.stackSize = newSize;
				}
			}
			
			if (amount > 0) {
				GT_Log.log.error(String.format("Output overflow detected for machine (%s) left amount: %s, stack: %s", getMachine().getClass().getName(), amount, recipeOut));
				getMachine().getBaseMetaTileEntity().disableWorking();
				triggerMachine(false);
			}
		}
		
		List<FluidStack> fluidOutputs = getMachine().getFluidOutputs();
		for (FluidStack fluid : recipe.getFluidOutputs()) {
			int amount = fluid.amount;
			for (int i = 0; amount > 0 && i < fluidOutputs.size(); i++) {
				FluidStack stackInSlot = fluidOutputs.get(i);
				if (GT_Utility.isFluidStackValid(stackInSlot) && stackInSlot.isFluidEqual(fluid)) {
					int tmp = Math.min(MAX_FLUID_STACK, stackInSlot.amount + fluid.amount);
					amount -= tmp - stackInSlot.amount;
					stackInSlot.amount = tmp;
				} else if (stackInSlot == null) {
					fluidOutputs.set(i, fluid.copy());
					amount = 0;
				}
			}
			
			if (amount > 0) {
				GT_Log.log.error(String.format("Output overflow detected for machine (%s) left amount: %s, fluid: %s", getMachine().getClass().getName(), amount, FluidRegistry.getFluidName(fluid)));
				getMachine().getBaseMetaTileEntity().disableWorking();
				triggerMachine(false);
			}
		}
		
		stuttering = false;
		getMachine().endProcess();
	}
	
	protected void triggerMachine(boolean value) {
		getMachine().getBaseMetaTileEntity().setActive(value);
	}
	
	protected boolean isInputNonEmpty() {
		return !getMachine().getInputItems().isEmpty() || !getMachine().getFluidInputs().isEmpty();
	}
	
	protected IRecipeWorkable getMachine() {
		return metaTileEntity.get();
	}
	
	/**
	 * Will reset working state
	 * Needed in multiblocks
	 */
	public void stop() {
		EUt = 0;
		maxProgressTime = 0;
		progressTime = 0;
	}
	
	public void increaseProgressTime(int amount) {
		progressTime += amount;
	}
	
	public boolean isActive() {
		return maxProgressTime > 0 && getMachine().getBaseMetaTileEntity().isAllowedToWork();
	}
	
	public int getMaxProgressTime() {
		return maxProgressTime;
	}
	
	public int getProgressTime() {
		return progressTime;
	}
	
	public Recipe getCurrentRecipe() {
		return previousRecipe;
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
