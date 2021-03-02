package gregtechmod.common.recipe.logic;

import java.util.function.IntSupplier;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;

import net.minecraft.nbt.NBTTagCompound;

public class GeneratorRecipeLogic extends RecipeLogic {
	
	public final IntSupplier efficiency; // FIXME change generator logic to TOTAL EU
	protected long leftEU = 0;
	
	public GeneratorRecipeLogic(IntSupplier efficiency, RecipeMap<?> recipeMap, IRecipeWorkable machine) {
		super(recipeMap, machine);
		this.efficiency = efficiency;
	}
	
	/** For internal use only */
	protected GeneratorRecipeLogic(IntSupplier efficiency, RecipeMap<?> recipeMap) {
		super(recipeMap);
		this.efficiency = efficiency;
	}
	
	@Override 
	public boolean update() {
		boolean success = false;
		IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
		
		if (base.isAllowedToWork()) {
			if (leftEU > 0) {
				long tmp = leftEU;
				success = updateRecipeProgress();
				if (tmp == 0 && !success) {
					throw new IllegalStateException();
				}
			}
			
			if (leftEU == 0) {
				if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0) {
					if (base.getUniversalEnergyStored() < (base.getOutputVoltage() * 10 + getMachine().getMinimumStoredEU())) {
						trySerachRecipe();
					} else {
						previousRecipe = null;
						base.setActive(false);
					} 
				}
			}
		} 
		
		return success;
	}
	
	@Override
	protected boolean updateRecipeProgress() {
		if (leftEU > 0) {
			int EU = (int) Math.min(((MetaTileEntity) getMachine()).maxEUOutput(), leftEU);
			EU = progressTimeManipulator.applyAsInt(EU);
			leftEU -= EU;
			if (getMachine().getBaseMetaTileEntity().increaseStoredEnergyUnits(EU, false)) {
				if (leftEU <= 0) {
					progressTime = 0;
					maxProgressTime = 0;
					EUt = 0;
					leftEU = 0;
	
					endRecipe(previousRecipe);
					getMachine().endProcess();
					return true;
				}
			}
		}
			
		return false;
	}
	
	@Override
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe)) {
			previousRecipe = recipe;
			progressTime = 1;
			leftEU = (int) (recipe.getDuration() * recipe.getEUt() * (efficiency.getAsInt() / 100.0D));
			maxProgressTime = (int) Math.ceil(leftEU * 1.0D / ((MetaTileEntity) getMachine()).maxEUOutput());
			if (consumeInputs(recipe)) {
				triggerMachine(true);
				getMachine().startProcess();
			} else {
				GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
				leftEU = 0;
				progressTime = 0;
				maxProgressTime = 0;
				previousRecipe = null;
			}
		} else {
			triggerMachine(false);
		}
	}
	
	@Override
	public void saveToNBT(NBTTagCompound data) {
		super.saveToNBT(data);
		NBTTagCompound tag = data.getCompoundTag("RecipeLogic");
		tag.setLong("leftEU", leftEU);
	}
	
	public void loadFromNBT(NBTTagCompound data) {
		super.loadFromNBT(data);
		NBTTagCompound tag = data.getCompoundTag("RecipeLogic");
		leftEU = tag.getLong("leftEU");
	}
	
	public long getLeftEU() {
		return leftEU;
	}
}