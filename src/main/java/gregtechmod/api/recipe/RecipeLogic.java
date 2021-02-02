package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.function.Supplier;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public class RecipeLogic {
	public final WeakReference<IRecipeWorkable> metaTileEntity;
	public final List<Recipe> recipeMap;
	
	public int batterySlot 		= 5;
	public boolean moveItems 	= true;
	/** Do not consume inputs on custom recipe provider, it is only should <b><i>provide</i></b> a recipe instance */
	protected Supplier<Recipe> customRecipeProvider;
	protected int maxProgressTime;
	protected int progressTime;
	protected int EUt;
	
	private int overclockersCount;
	private Recipe previousRecipe;
	private boolean stuttering;
	private boolean wasNoEnergy;
	
	public RecipeLogic(List<Recipe> recipeMap, IRecipeWorkable machine) {
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
		if (moveItems) moveItems();
		
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
					if (isInputNonEmpty() && base.isUniversalEnergyStored(getMachine().getMinimumStoredEU() - 100)) {
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
			return Recipe.findEqualRecipe(true, recipeMap, getMachine().getBaseMetaTileEntity(), getMachine().getInputSlots());
		} else return customRecipeProvider.get();
	}
	
	protected boolean match(Recipe recipe) {
		return recipe.match(false, getMachine().getBaseMetaTileEntity(), getMachine().getInputSlots());
	}
	
	protected void consumeInputs(Recipe recipe) {
		recipe.match(true, getMachine().getBaseMetaTileEntity(), getMachine().getInputSlots());
	}
	
	protected void moveItems() {
		// Slot 0 = HoloSlot
		// Slot 1 = Left Input
		// Slot 2 = right Input
		// Slot 3 = left Output
		// Slot 4 = right Output
		// Slot 5 = battery Slot in most cases
		IInventory inv = getMachine().getBaseMetaTileEntity();
		int[] in = getMachine().getInputSlots();
		int[] out = getMachine().getOutputSlots();
		if (in.length > 1) GT_Utility.moveStackFromSlotAToSlotB(inv, inv, in[0], in[1], (byte)64, (byte)1, (byte)64, (byte)1);
		if (out.length > 1)  GT_Utility.moveStackFromSlotAToSlotB(inv, inv, out[0], out[1], (byte)64, (byte)1, (byte)64, (byte)1);
	}
	
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe)) {
			previousRecipe = recipe;
			maxProgressTime = GT_Utility.isDebugItem(getMachine().getStackInSlot(batterySlot)) ? 1 : recipe.mDuration;
			progressTime = 1;
			EUt = recipe.mEUt;
			consumeInputs(recipe);
			getMachine().getBaseMetaTileEntity().setActive(true);
			getMachine().startProcess();
		} else {
			getMachine().getBaseMetaTileEntity().setActive(false);
		}
	}
	
	protected void endRecipe(Recipe recipe) {
		ItemStack[] outputs = recipe.getOutputs();
		if (outputs.length <= getMachine().getOutputSlots().length) {
			for (ItemStack out : outputs) {
				for (int i : getMachine().getOutputSlots()) {
					if (getMachine().getBaseMetaTileEntity().addStackToSlot(i, out.copy())) {
						break;
					}
				}
			}
		} else {
			GT_Log.log.catching(new IllegalStateException("Found recipe with more items output machine has slots!"));
		}
		
		stuttering = false;
		getMachine().endProcess();
	}
	
	protected boolean isInputNonEmpty() {
		for (int i : getMachine().getInputSlots()) {
			ItemStack s = getMachine().getStackInSlot(i);
			if (s != null && s.stackSize > 0) return true;
		}
		
		return false;
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
		return maxProgressTime / (int)Math.pow(2, overclockersCount);
	}
	
	public int getProgressTime() {
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
