package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public abstract class GT_MetaTileEntity_MultiBlockBase extends MetaTileEntity implements IRecipeWorkable {
	
	private MultiblockRecipeLogic recipeLogic;
	
	/* REPAIR STATUS */
	public boolean mWrench 			= false;
	public boolean mScrewdriver 	= false;
	public boolean mSoftHammer 		= false;
	public boolean mHardHammer 		= false;
	public boolean mSolderingTool 	= false;
	public boolean mCrowbar 		= false;
	
	/* STRUCTURE STATUS */
	protected boolean structComplete 	= false;
	protected boolean needCheckStruct 	= false;
	
	/* MISC */
	public int mPollution = 0, mEfficiencyIncrease = 0, mRuntime = 0, mEfficiency = 0;
	
	protected List<GT_MetaTileEntity_Hatch_Input> mInputHatches 					= new ArrayList<GT_MetaTileEntity_Hatch_Input>();
	protected List<GT_MetaTileEntity_Hatch_Output> mOutputHatches 					= new ArrayList<GT_MetaTileEntity_Hatch_Output>();
	protected List<GT_MetaTileEntity_Hatch_InputBus> mInputBusses 					= new ArrayList<GT_MetaTileEntity_Hatch_InputBus>();
	protected List<GT_MetaTileEntity_Hatch_OutputBus> mOutputBusses 				= new ArrayList<GT_MetaTileEntity_Hatch_OutputBus>();
	protected List<GT_MetaTileEntity_Hatch_Dynamo> mDynamoHatches 					= new ArrayList<GT_MetaTileEntity_Hatch_Dynamo>();
	protected List<GT_MetaTileEntity_Hatch_EnergyInput> mEnergyHatches 				= new ArrayList<GT_MetaTileEntity_Hatch_EnergyInput>();
	protected WeakReference<GT_MetaTileEntity_Hatch_Muffler> mufflerHatch 			= new WeakReference<>(null);
	protected WeakReference<GT_MetaTileEntity_Hatch_Maintenance> maintenanceHatch 	= new WeakReference<>(null);
	
	public GT_MetaTileEntity_MultiBlockBase(int aID, String aName, RecipeMap<?> map) {
		super(aID, aName);
		initRecipeLogic(map);
	}
	
	public GT_MetaTileEntity_MultiBlockBase(RecipeMap<?> map) {
		initRecipeLogic(map);
	}
	
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new MultiblockRecipeLogic(map, this);
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean allowToCheckRecipe() 					{return true;}
	@Override public boolean isGivingInformation() 					{return true;}
	@Override public void startProcess() 							{}
	@Override public void endProcess() 								{}
	@Override public void stutterProcess() 							{}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex > 0;}
	@Override public int getInvSize()								{return 2;}
    @Override public void onMachineBlockUpdate() 					{needCheckStruct = true;}
	@Override public RecipeLogic getRecipeLogic() 					{return recipeLogic;}
    @Override public int increaseProgress(int aProgress)			{recipeLogic.increaseProgressTime(aProgress);return recipeLogic.getMaxProgressTime()-recipeLogic.getProgressTime();}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		recipeLogic.saveToNBT(aNBT);
    	aNBT.setInteger("mEfficiencyIncrease", mEfficiencyIncrease);
    	aNBT.setInteger("mEfficiency", mEfficiency);
    	aNBT.setInteger("mPollution", mPollution);
    	aNBT.setInteger("mRuntime", mRuntime);
    	
    	NBTTagCompound repairData = new NBTTagCompound();
    	repairData.setBoolean("mWrench", mWrench);
    	repairData.setBoolean("mScrewdriver", mScrewdriver);
    	repairData.setBoolean("mSoftHammer", mSoftHammer);
    	repairData.setBoolean("mHardHammer", mHardHammer);
    	repairData.setBoolean("mSolderingTool", mSolderingTool);
    	repairData.setBoolean("mCrowbar", mCrowbar);
    	aNBT.setTag("RepairData", repairData);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		recipeLogic.loadFromNBT(aNBT);
    	mEfficiencyIncrease = aNBT.getInteger("mEfficiencyIncrease");
    	mEfficiency = aNBT.getInteger("mEfficiency");
    	mPollution = aNBT.getInteger("mPollution");
    	mRuntime = aNBT.getInteger("mRuntime");
    	
    	if (aNBT.hasKey("RepairData")) {
    		NBTTagCompound repairData = aNBT.getCompoundTag("RepairData");
    		mWrench 		= repairData.getBoolean("mWrench");
    		mScrewdriver 	= repairData.getBoolean("mScrewdriver");
    		mSoftHammer 	= repairData.getBoolean("mSoftHammer");
    		mHardHammer 	= repairData.getBoolean("mHardHammer");
    		mSolderingTool 	= repairData.getBoolean("mSolderingTool");
    		mCrowbar 		= repairData.getBoolean("mCrowbar");
    	}
	}
    
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
    		if (mEfficiency < 0) mEfficiency = 0;
    		if (needCheckStruct) {
    			mInputHatches.clear();
				mInputBusses.clear();
				mOutputHatches.clear();
				mOutputBusses.clear();
				mDynamoHatches.clear();
				mEnergyHatches.clear();
				mufflerHatch.clear();
				maintenanceHatch.clear();
    			structComplete = checkMachine(mInventory[1]);
        		needCheckStruct = false;
    		}
    		
    		if (structComplete) {
    			if (getBaseMetaTileEntity().getTimer() % 10 == 0) {
    				if (isValidMetaTileEntity(maintenanceHatch.get())) {
    					if (maintenanceHatch.get().mWrench) mWrench = true;
    					if (maintenanceHatch.get().mScrewdriver) mScrewdriver = true;
						if (maintenanceHatch.get().mSoftHammer) mSoftHammer = true;
						if (maintenanceHatch.get().mHardHammer) mHardHammer = true;
						if (maintenanceHatch.get().mSolderingTool) mSolderingTool = true;
						if (maintenanceHatch.get().mCrowbar) mCrowbar = true;
						maintenanceHatch.get().mWrench = false;
						maintenanceHatch.get().mScrewdriver = false;
						maintenanceHatch.get().mSoftHammer = false;
						maintenanceHatch.get().mHardHammer = false;
						maintenanceHatch.get().mSolderingTool = false;
						maintenanceHatch.get().mCrowbar = false;
    				}
    			}
    			
    			if (getRepairStatus() > 0) {
    				recipeLogic.update();
    				if (recipeLogic.isActive()) {
    					if (doRandomMaintenanceDamage()) {
    						if (onRunningTick(mInventory[1]) && !polluteEnvironment(getPollutionPerTick(mInventory[1]))) {
				    			stopMachine();
				    		}
    					} else {
    						mEfficiency = Math.max(0, mEfficiency - 1000);
    					}
    				}
    			} else stopMachine();
    		} else stopMachine();

			getBaseMetaTileEntity().setErrorDisplayID((getBaseMetaTileEntity().getErrorDisplayID()&~127)|(mWrench?0:1)|(mScrewdriver?0:2)|(mSoftHammer?0:4)|(mHardHammer?0:8)|(mSolderingTool?0:16)|(mCrowbar?0:32)|(structComplete?0:64));
		}
	}
	
	public boolean polluteEnvironment(int aPollutionLevel) {
		mPollution += aPollutionLevel;
		GT_MetaTileEntity_Hatch_Muffler tHatch = mufflerHatch.get();
		if (isValidMetaTileEntity(tHatch)) {
			if (mPollution >= 10000) {
				if (tHatch.polluteEnvironment()) {
		    		mPollution -= 10000;
				}
			}
		}
		
		return mPollution < 10000;
	}

	/**
	 * Called every tick the Machine runs
	 */
	public boolean onRunningTick(ItemStack aStack) {
		if (recipeLogic.getEUt() > 0) {
			int tGeneratedEU = (int)(((long)recipeLogic.getEUt() * mEfficiency) / 10000);
			addEnergyOutput(tGeneratedEU);
		} else if (recipeLogic.getEUt() < 0) {
			int tConsumedEU = (int)(((long)-recipeLogic.getEUt() * 10000) / Math.max(1000, mEfficiency));
			if (!drainEnergyInput(tConsumedEU)) {
				stopMachine();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if this is a Correct Machine Part for this kind of Machine (Turbine Rotor for example)
	 */
	public abstract boolean isCorrectMachinePart(ItemStack aStack);
	
	/**
	 * Checks the Recipe
	 */
	public abstract boolean checkRecipe(ItemStack aStack);
	
	/**
	 * Checks the Machine. You have to assign the MetaTileEntities for the Hatches here.
	 */
	protected abstract boolean checkMachine(ItemStack aStack);
	
	/**
	 * Gets the maximum Efficiency that spare Part can get (0 - 10000)
	 */
	public abstract int getMaxEfficiency(ItemStack aStack);
	
	/**
	 * Gets the pollution this Device outputs to a Muffler per tick (10000 = one Pullution Block)
	 */
	public abstract int getPollutionPerTick(ItemStack aStack);
	
	/**
	 * Gets the damage to the ItemStack, usually 0 or 1.
	 */
	public abstract int getDamageToComponent(ItemStack aStack);
	
	/**
	 * Gets the Amount of possibly outputted Items for loading the Output Stack Array from NBT.
	 */
	public abstract int getAmountOfOutputs();
	
	/**
	 * If it explodes when the Component has to be replaced.
	 */
	public abstract boolean explodesOnComponentBreak(ItemStack aStack);
	
	public void stopMachine() {
		recipeLogic.stop();
    	mEfficiency = 0;
		getBaseMetaTileEntity().disableWorking();
	}
	
	public int getRepairStatus() {
		return (mWrench?1:0) + (mScrewdriver?1:0) + (mSoftHammer?1:0) + (mHardHammer?1:0) + (mSolderingTool?1:0) + (mCrowbar?1:0);
	}
	
	public int getIdealStatus() {
		return 6;
	}
	
	public static boolean isValidMetaTileEntity(MetaTileEntity aMetaTileEntity) {
		return aMetaTileEntity.getBaseMetaTileEntity() != null && aMetaTileEntity.getBaseMetaTileEntity().getMetaTileEntity() == aMetaTileEntity;
	}
	
	public boolean doRandomMaintenanceDamage() {
		if (!isCorrectMachinePart(mInventory[1]) || getRepairStatus() == 0) {
			stopMachine();
			return false;
		}
		if (mRuntime++>1000) {
			mRuntime = 0;
			if (getBaseMetaTileEntity().getRandomNumber(6000) == 0) {
				switch (getBaseMetaTileEntity().getRandomNumber(6)) {
				case  0: mWrench = false; break;
				case  1: mScrewdriver = false; break;
				case  2: mSoftHammer = false; break;
				case  3: mHardHammer = false; break;
				case  4: mSolderingTool = false; break;
				case  5: mCrowbar = false; break;
				}
			}
			if (mInventory[1] != null && getBaseMetaTileEntity().getRandomNumber(2) == 0) {
				mInventory[1].setItemDamage(mInventory[1].getItemDamage() + getDamageToComponent(mInventory[1]));
				if (mInventory[1].getItemDamage() >= mInventory[1].getMaxDamage()) {
					if (explodesOnComponentBreak(mInventory[1])) {
						mInventory[1] = null;
						for (MetaTileEntity tTileEntity : mInputHatches) tTileEntity.getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						for (MetaTileEntity tTileEntity : mOutputHatches) tTileEntity.getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						for (MetaTileEntity tTileEntity : mDynamoHatches) tTileEntity.getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						for (MetaTileEntity tTileEntity : mEnergyHatches) tTileEntity.getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						if (mufflerHatch.get() != null)
							mufflerHatch.get().getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						if (maintenanceHatch.get() != null)
							maintenanceHatch.get().getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
						getBaseMetaTileEntity().doExplosion(GregTech_API.VOLTAGE_ULTIMATE);
					} else {
						mInventory[1] = null;
					}
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean spaceForOutput(Recipe recipe) {
		List<ItemStack> outputSlots = this.getOutputItems();
		List<ItemStack> allOutputs = recipe.getAllOutputs();
		
		for (ItemStack current : allOutputs) {
			int amount = current.stackSize;
			for (int i = 0; current != null && amount > 0 && i < outputSlots.size(); i++) {
				ItemStack slot = outputSlots.get(i);
				if (slot == null) {
					amount = 0;
					break;
				} else if (GT_Utility.areStacksEqual(slot, current)) {
					int newSize = Math.min(slot.getMaxStackSize(), amount + slot.stackSize);
					amount -= newSize;
				}
			}
			
			if (amount > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	// TODO
	@Override
	public List<ItemStack> getInputItems() {
		
		return null;
		
	}
	
	@Override
	public List<ItemStack> getOutputItems() {
		return null;
	}
	
	@Override
	public List<FluidStack> getFluidInputs() {
		
		
		
		return null;
	}
	
	@Override
	public List<FluidStack> getFluidOutputs() {
		return null;
	}
	
	@Override
	public int getMinimumStoredEU() {
		int energy = 0;
		for (GT_MetaTileEntity_Hatch_EnergyInput tHatch : mEnergyHatches) {
			if (isValidMetaTileEntity(tHatch)) {
				energy += tHatch.getMinimumStoredEU();
			}
		}
		
		return energy;
	}
	
	public boolean isUniversalEnergyStored(int value) {
		int energy = 0;
		for (GT_MetaTileEntity_Hatch_EnergyInput tHatch : mEnergyHatches) {
			if (isValidMetaTileEntity(tHatch)) {
				energy += tHatch.getBaseMetaTileEntity().getUniversalEnergyStored();
			}
		}
		
		return energy <= value;
	}
	
	public boolean hasInventoryBeenModified() {
		for (GT_MetaTileEntity_Hatch_Input hatch : mInputHatches) {
			if (isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		for (GT_MetaTileEntity_Hatch_Output hatch : mOutputHatches) {
			if (isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		for (GT_MetaTileEntity_Hatch_InputBus hatch : mInputBusses) {
			if (isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		for (GT_MetaTileEntity_Hatch_OutputBus hatch : mOutputBusses) {
			if (isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean addEnergyOutput(int aEU) {
		if (aEU <= 0) return true;
		for (GT_MetaTileEntity_Hatch_Dynamo tHatch : mDynamoHatches) {
			if (isValidMetaTileEntity(tHatch)) {
				if (tHatch.getBaseMetaTileEntity().increaseStoredEnergyUnits(aEU, false)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean drainEnergyInput(int aEU) {
		if (aEU <= 0) return true;
		for (GT_MetaTileEntity_Hatch_EnergyInput tHatch : mEnergyHatches) {
			if (isValidMetaTileEntity(tHatch)) {
				IGregTechTileEntity ent = tHatch.getBaseMetaTileEntity();
				int tmp = Math.min(ent.getUniversalEnergyStored(), aEU);
				ent.decreaseStoredEnergyUnits(tmp, false);
				aEU -= tmp;
				if (aEU <= 0) break;
			}
		}
		return aEU <= 0;
	}
	
	
	public boolean addToMachineList(IGregTechTileEntity aTileEntity) {
		if (aTileEntity == null) return false;
		IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
		if (aMetaTileEntity == null) return false;
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Input)			return mInputHatches.add((GT_MetaTileEntity_Hatch_Input)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_InputBus)		return mInputBusses.add((GT_MetaTileEntity_Hatch_InputBus)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Output)			return mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_OutputBus)		return mOutputBusses.add((GT_MetaTileEntity_Hatch_OutputBus)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_EnergyInput)		return mEnergyHatches.add((GT_MetaTileEntity_Hatch_EnergyInput)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Dynamo)			return mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)aMetaTileEntity);
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Maintenance) {
			maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)aMetaTileEntity);
			return true;
		}
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_Muffler) {
			mufflerHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Muffler)aMetaTileEntity);
			return true;
		}
		
		return false;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return 32;
		if (aSide==1) return 29;
    	return 40;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", recipeLogic.getDisplayProgress() * 100.0D / recipeLogic.getDisplayMaxProgress())
				.newKey("sensor.progress.secs", recipeLogic.getDisplayProgress() / 20)
				.newKey("sensor.progress.secs.1", recipeLogic.getDisplayMaxProgress() / 20)
				.build();
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	public static class MultiblockRecipeLogic extends RecipeLogic {

		public MultiblockRecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(recipeMap, machine);
		}
		
		@Override
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			GT_MetaTileEntity_MultiBlockBase machine = getMachine1();
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
					if (machine.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {
						if (machine.isUniversalEnergyStored(machine.getMinimumStoredEU() - 100)) {
							trySerachRecipe();
							wasNoEnergy = false;
						} else {
							previousRecipe = null;
							wasNoEnergy = true;
							triggerMachine(false);
						} 
					}
				}
			} 
			
			return success;
		}
		
		@Override
		protected void endRecipe(Recipe recipe) {
			super.endRecipe(recipe);
			GT_MetaTileEntity_MultiBlockBase machine = getMachine1();
			machine.mEfficiencyIncrease = 0;
			machine.mEfficiency = Math.max(0, Math.min(machine.mEfficiency + machine.mEfficiencyIncrease, machine.getMaxEfficiency(machine.mInventory[1]) - ((machine.getIdealStatus() - machine.getRepairStatus()) * 1000)));
		}
		
		private GT_MetaTileEntity_MultiBlockBase getMachine1() {
			return (GT_MetaTileEntity_MultiBlockBase) getMachine();
		}
		
		public void stop() {
			EUt = 0;
			maxProgressTime = 0;
			progressTime = 0;
		}
	}
}
