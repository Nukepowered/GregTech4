package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public class RecipeLogic {
	private WeakReference<GT_MetaTileEntity_BasicMachine> metaTileEntity;
	protected List<Recipe> recipeMap;
	
	protected int maxProgressTime;
	protected int progressTime;
	protected int EUt;
	private int overclockersCount;
	
	private Recipe previousRecipe;
	
	public RecipeLogic(List<Recipe> recipeMap) {
		this.recipeMap = recipeMap;
		maxProgressTime = 0;
		progressTime = 0;
		EUt = 0;
		overclockersCount = 0;
	}
	
	public void update() {
		overclockersCount = getMachine().getBaseMetaTileEntity().getOverclockerUpgradeCount();
		if (getMachine().getBaseMetaTileEntity().isAllowedToWork()) {
			if (progressTime > 0) {
				updateRecipeProgress();
			}
			if (progressTime == 0) {
				trySerachRecipe();
			}
		}
	}
	
	protected void updateRecipeProgress() {
		if (getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(EUt * (int)Math.pow(4, overclockersCount), false)) {
			if ((progressTime += (int)Math.pow(2, overclockersCount)) >= maxProgressTime) {
				progressTime = 0;
				maxProgressTime = 0;
				EUt = 0;
				
				endRecipe(previousRecipe);
				getMachine().endProcess();
			}
		} else {
			getMachine().getBaseMetaTileEntity().setActive(false);
		}
	}
	
	protected void trySerachRecipe() {
		if (getMachine().allowToCheckRecipe()) {
			if (previousRecipe != null) {
				if (previousRecipe.match(true, getMachineInputs())) { // TODO add I/O item handlers to MTE
					startRecipe(previousRecipe);
				};
			} else {
				// find new recipe
				for (Recipe rec : recipeMap) { // FIXME idk maybe this is bad
					if (rec.match(false, getMachineInputs())) {
						startRecipe(rec);
						return;
					}
				}
			}
		}
	}
	
	protected void startRecipe(Recipe recipe) {
		previousRecipe = recipe;
		maxProgressTime = recipe.mDuration;
		progressTime = 1;
		EUt = recipe.mEUt;
		getMachine().getBaseMetaTileEntity().setActive(true);
	}
	
	protected void endRecipe(Recipe recipe) {
		ItemStack[] outputs = recipe.getOutputs();
		if (outputs.length <= getMachineOutputs().length) {
			
		}
	}
	
	/**
	 * Specify machine input slots
	 */
	protected ItemStack[] getMachineInputs() {
		return subArray(getMachine().mInventory, 2, 2);
	}
	
	protected ItemStack[] getMachineOutputs() {
		return subArray(getMachine().mInventory, 3, 3);
	}
	
	/**
	 * Will copy links of all objects in arr to new array
	 */
	private ItemStack[] subArray(ItemStack[] arr, int from, int to) {
		assert from >= to;
		ItemStack[] res = new ItemStack[(to - from) == 0 ? 1 : (to - from)];
		for (int i = 0; i < res.length; i++) {
			res[i] = arr[from + i];
		}
		
		return res;
	}
	
	private GT_MetaTileEntity_BasicMachine getMachine() {
		return metaTileEntity.get();
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
	
	public int getEUt() {
		return EUt;
	}
	
	public void saveToNBT(NBTTagCompound data) {
		NBTTagCompound data1 = new NBTTagCompound();
		data1.setInteger("TotalTime", maxProgressTime);
		data1.setInteger("CurrentTime", progressTime);
		data1.setInteger("EUt", EUt);
		data.setTag("RecipeLogic", data1);
	}
	
	public void loadFromNBT(NBTTagCompound data) {
		NBTTagCompound data1 = data.getCompoundTag("RecipeLogic");
		if (data1 != null) {
			maxProgressTime = data1.getInteger("TotalTime");
			progressTime = data1.getInteger("CurrentTime");
			EUt = data1.getInteger("EUt");
		}
	}
}
