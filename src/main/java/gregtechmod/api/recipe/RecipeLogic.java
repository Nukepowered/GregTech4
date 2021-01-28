package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public abstract class RecipeLogic {
	private WeakReference<GT_MetaTileEntity_BasicMachine> metaTileEntity;
	private List<GT_Recipe> recipeMap;
	
	private int maxProgressTime;
	private int progressTime;
	private int EUt;
	private int overclockersCount;
	
	private GT_Recipe previousRecipe;
	
	protected RecipeLogic() {
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
				
				getMachine().endProcess();
			}
		} else {
			getMachine().getBaseMetaTileEntity().setActive(false);
		}
	}
	
	protected void trySerachRecipe() {
		if (getMachine().getBaseMetaTileEntity().isAllowedToWork() && getMachine().allowToCheckRecipe()) {
			if (previousRecipe != null) {
				if (previousRecipe.isRecipeInputEqual(true, true, aInputs)) { // TODO add I/O item handlers to MTE
					maxProgressTime = previousRecipe.mDuration;
					progressTime = 1;
					EUt = previousRecipe.mEUt;
				};
			} else {
				// find new recipe
			}
		}
//		getMachine().getBaseMetaTileEntity().setActive(true);
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
