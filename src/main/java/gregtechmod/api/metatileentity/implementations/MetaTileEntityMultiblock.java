package gregtechmod.api.metatileentity.implementations;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InventoryHandlerList;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.api.util.WeakList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

/** Common multiblock class
 * @author TheDarkDnKTv
 * 
 */
public abstract class MetaTileEntityMultiblock extends MetaTileEntity {
	
	/* REPAIR STATUS */
	public boolean mWrench 			= false;
	public boolean mScrewdriver 	= false;
	public boolean mSoftHammer 		= false;
	public boolean mHardHammer 		= false;
	public boolean mSolderingTool 	= false;
	public boolean mCrowbar 		= false;
	
	/* STRUCTURE STATUS */
	protected boolean structComplete 	= false;
	protected boolean needCheckStruct 	= true;
	
	/* MISC */
	public int mPollution = 0, mRuntime = 0, mEfficiency = 100;
	protected int MAX_FLUID_STACK = 16_000;
	
	/* COMPONENTS */
	protected WeakList<GT_MetaTileEntity_BasicTank> 				mInputHatches 		= new WeakList<>();
	protected WeakList<GT_MetaTileEntity_BasicTank> 				mOutputHatches 		= new WeakList<>();
	protected WeakList<GT_MetaTileEntity_Hatch_InputBus> 			mInputBusses 		= new WeakList<>();
	protected WeakList<GT_MetaTileEntity_Hatch_OutputBus> 			mOutputBusses 		= new WeakList<>();
	protected WeakList<GT_MetaTileEntity_Hatch_Dynamo> 				mDynamoHatches 		= new WeakList<>();
	protected WeakList<GT_MetaTileEntity_Hatch_EnergyInput>	 		mEnergyHatches 		= new WeakList<>();
	protected WeakReference<GT_MetaTileEntity_Hatch_Muffler> 		mufflerHatch 		= new WeakReference<>(null);
	protected WeakReference<GT_MetaTileEntity_Hatch_Maintenance> 	maintenanceHatch	= new WeakReference<>(null);
	
	protected List<ItemStack> itemInputs 	= null;
	protected List<ItemStack> itemOutputs	= null;
	protected List<FluidStack> fluidInputs	= null;
	protected List<FluidStack> fluidOutputs	= null;
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex > 0;}
    @Override public void onMachineBlockUpdate() 					{needCheckStruct = true;}
	@Override public int getInvSize()								{return 2;}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
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
        		if (structComplete) {
        			this.findInventories();
        			updateEfficiency();
        		} else {
        			itemInputs 		= null;
        			itemOutputs 	= null;
        			fluidInputs 	= null;
        			fluidOutputs 	= null;
        		}
    		} else if (!structComplete && getBaseMetaTileEntity().getTimer() % 600 == 0) {
    			needCheckStruct = true;
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
						updateEfficiency();
    				}
    			}
    			
    			if (getRepairStatus() <= 0)
    				stopMachine();
    		} else stopMachine();

			getBaseMetaTileEntity().setErrorDisplayID((getBaseMetaTileEntity().getErrorDisplayID()&~127)|(mWrench?0:1)|(mScrewdriver?0:2)|(mSoftHammer?0:4)|(mHardHammer?0:8)|(mSolderingTool?0:16)|(mCrowbar?0:32)|(structComplete?0:64));
		}
	}
	
	/**
	 * Checks if this is a Correct Machine Part for this kind of Machine (Turbine Rotor for example)
	 */
	public abstract boolean isCorrectMachinePart(ItemStack aStack);
	
	/**
	 * Checks the Machine. You have to assign the MetaTileEntities for the Hatches here.
	 */
	protected abstract boolean checkMachine(ItemStack aStack);
	
	/**
	 * Gets the maximum Efficiency that spare Part can get (0 - 100)
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
	 * If it explodes when the Component has to be replaced.
	 */
	public abstract boolean explodesOnComponentBreak(ItemStack aStack);
	
	public void stopMachine() {
    	mEfficiency = 0;
    	getBaseMetaTileEntity().setActive(false);
	}
	
	public void updateEfficiency() {
		mEfficiency = (int) (this.getMaxEfficiency(mInventory[1]) * (this.getRepairStatus() * 1.0D / this.getIdealStatus()));
	}
	
	public int getRepairStatus() {
		return (mWrench?1:0) + (mScrewdriver?1:0) + (mSoftHammer?1:0) + (mHardHammer?1:0) + (mSolderingTool?1:0) + (mCrowbar?1:0);
	}
	
	public int getIdealStatus() {
		return 6;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	public static boolean isValidMetaTileEntity(MetaTileEntity aMetaTileEntity) {
		return aMetaTileEntity != null && aMetaTileEntity.getBaseMetaTileEntity() != null && aMetaTileEntity.getBaseMetaTileEntity().getMetaTileEntity() == aMetaTileEntity;
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
	
	protected void findInventories() {
		List<List<ItemStack>> items = new ArrayList<>();
		List<List<FluidStack>> fluids = new ArrayList<>();
		
		for (GT_MetaTileEntity_Hatch_InputBus bus : mInputBusses)
			items.add(new ListAdapter<>(bus.mInventory));
		itemInputs = new InventoryHandlerList<>(items);
		items.clear();
		for (GT_MetaTileEntity_Hatch_OutputBus bus : mOutputBusses)
			items.add(new ListAdapter<>(bus.mInventory));
		itemOutputs = new InventoryHandlerList<>(items);
		items.clear();
		
		for (GT_MetaTileEntity_BasicTank hatch : mInputHatches)
			fluids.add(new ListAdapter<>(hatch.mFluid));
		fluidInputs = new InventoryHandlerList<>(fluids);
		fluids.clear();
		for (GT_MetaTileEntity_BasicTank hatch : mOutputHatches)
			fluids.add(new ListAdapter<>(hatch.mFluid));
		fluidOutputs = new InventoryHandlerList<>(fluids);
		fluids.clear();
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
	
	public boolean doRandomMaintenanceDamage() {
		if (!isCorrectMachinePart(mInventory[1]) || getRepairStatus() == 0) {
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
	
	public boolean depleteInput(ItemStack item) {
		List<ItemStack> input = this.getInputItems();
		int[] itemAmountInSlots = new int[input.size()];
		int amount = item.stackSize;
		for (int i = 0; i < input.size() && amount > 0; i++) {
			ItemStack itemInSlot = input.get(i);
			
			if (itemInSlot == null) {
				itemAmountInSlots[i] = 0;
			} else {
				int toConsume = 0;
				if (item.isItemEqual(itemInSlot)) {
					toConsume = Math.min(itemInSlot.stackSize, amount);
					amount -= toConsume;
				}
				
				itemAmountInSlots[i] = itemInSlot.stackSize - toConsume;
			}
		}
		
		if (amount <= 0) {
			for (int i = 0; i < input.size(); i++) {
				ItemStack itemSlot = input.get(i);
				if (itemSlot != null && itemSlot.stackSize != itemAmountInSlots[i]) {
					if (itemAmountInSlots[i] == 0) {
						input.remove(i);
					} else {
						itemSlot.stackSize = itemAmountInSlots[i];
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean depleteInput(FluidStack fluid) {
		List<FluidStack> input = this.getFluidInputs();
		int[] itemAmountInSlots = new int[input.size()];
		int amount = fluid.amount;
		for (int i = 0; i < input.size(); i++) {
			FluidStack fluidInSlot = input.get(i);
			
			if (fluidInSlot == null) {
				itemAmountInSlots[i] = 0;
			} else {
				int toConsume = 0;
				if (fluid.isFluidEqual(fluidInSlot)) {
					toConsume = Math.min(fluidInSlot.amount, amount);
					amount -= toConsume;
				}
				
				itemAmountInSlots[i] = fluidInSlot.amount - toConsume;
			}
		}
		
		if (amount <= 0) {
			for (int i = 0; i < input.size(); i++) {
				FluidStack itemSlot = input.get(i);
				if (itemSlot != null && itemSlot.amount != itemAmountInSlots[i]) {
					if (itemAmountInSlots[i] == 0) {
						input.remove(i);
					} else {
						itemSlot.amount = itemAmountInSlots[i];
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean addOutput(ItemStack item) {
		List<ItemStack> outputs = this.getOutputItems();
		Map<Integer, Integer> newInv = new HashMap<>();
		int amount = item.stackSize;
		
		for (int i = 0; i < outputs.size() && amount > 0; i++) {
			ItemStack itemInSlot = outputs.get(i);
			
			if (itemInSlot == null) {
				outputs.set(i, item);
				return true;
			} else {
				if (item.isItemEqual(itemInSlot)) {
					int newSize = itemInSlot.stackSize;
					newSize = Math.min(itemInSlot.getMaxStackSize(), itemInSlot.stackSize + amount);
					amount -= newSize - itemInSlot.stackSize;
					newInv.put(i, newSize);
				}
			}
		}
		
		if (amount <= 0) {
			for (Integer idx : newInv.keySet()) {
				outputs.get(idx).stackSize = newInv.get(idx);
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean addOutput(FluidStack fluid) {
		List<FluidStack> outputs = this.getFluidOutputs();
		Map<Integer, Integer> newInv = new HashMap<>();
		int amount = fluid.amount;
		
		for (int i = 0; i < outputs.size() && amount > 0; i++) {
			FluidStack itemInSlot = outputs.get(i);
			
			if (itemInSlot == null) {
				outputs.set(i, fluid);
				return true;
			} else {
				if (fluid.isFluidEqual(itemInSlot)) {
					int newSize = itemInSlot.amount;
					newSize = Math.min(MAX_FLUID_STACK, itemInSlot.amount + amount);
					amount -= newSize - itemInSlot.amount;
					newInv.put(i, newSize);
				}
			}
		}
		
		if (amount <= 0) {
			for (Integer idx : newInv.keySet()) {
				outputs.get(idx).amount = newInv.get(idx);
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (GT_Utility.isFluidStackValid(resource)) {
			List<FluidStack> fluidInputs = this.getFluidInputs();
			for (int i = 0; i < fluidInputs.size(); i++) {
				FluidStack stackInSlot = fluidInputs.get(i);
				if (GT_Utility.isFluidStackValid(stackInSlot)) {
					if (stackInSlot.isFluidEqual(resource)) {
						int space = getCapacity() - stackInSlot.amount;
						int toFill = resource.amount <= space  ? resource.amount : space;
						if (doFill) {
							stackInSlot.amount += toFill;
						}
						
						return toFill;
					}
				} else {
					int amount = Math.min(getCapacity(), resource.amount);
					FluidStack copy = resource.copy();
					copy.amount = amount;
					if (doFill)
						fluidInputs.set(i, copy);
					
					return amount;
				}
				
			}
		}
		
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
		if (GT_Utility.isFluidStackValid(aFluid)) 
		for (int i = 0; i < fluidOutputs.size(); i++) {
			FluidStack stackInSlot = fluidOutputs.get(i);
			if (GT_Utility.isFluidStackValid(stackInSlot) && aFluid.isFluidEqual(stackInSlot)) {
				int amount = Math.min(aFluid.amount, stackInSlot.amount);
				FluidStack result = stackInSlot.copy();
				result.amount = amount;
				if (doDrain) {
					if (stackInSlot.amount == amount) {
						fluidOutputs.set(i, null);
					} else stackInSlot.amount -= amount;
				}
				
				return result;
			}
		}
		
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
		for (int i = 0; i < fluidOutputs.size(); i++) {
			FluidStack stackInSlot = fluidOutputs.get(i);
			if (GT_Utility.isFluidStackValid(stackInSlot)) {
				int amount = Math.min(maxDrain, stackInSlot.amount);
				FluidStack result = stackInSlot.copy();
				result.amount = amount;
				if (doDrain) {
					if (stackInSlot.amount == amount) {
						fluidOutputs.set(i, null);
					} else stackInSlot.amount -= amount;
				}
				
				return result;
			}
		}
		
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
		for (GT_MetaTileEntity_BasicTank hatch : mInputHatches) {
			if (isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		for (GT_MetaTileEntity_BasicTank hatch : mOutputHatches) {
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
		
		return getBaseMetaTileEntity().hasInventoryBeenModified();
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
	
	public List<ItemStack> getInputItems() {
		if (structComplete) {
			if (itemInputs != null) {
				return itemInputs;
			}
		}
		
		return Collections.emptyList();
	}
	
	public List<ItemStack> getOutputItems() {
		if (structComplete) {
			if (itemOutputs != null) {
				return itemOutputs;
			}
		}
		
		return Collections.emptyList();
	}
	
	public List<FluidStack> getFluidInputs() {
		if (structComplete) {
			if (fluidInputs != null) {
				return fluidInputs;
			}
		}
		
		return Collections.emptyList();
	}
	
	public List<FluidStack> getFluidOutputs() {
		if (structComplete) {
			if (fluidOutputs != null) {
				return fluidOutputs;
			}
		}
		
		return Collections.emptyList();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return 32;
		if (aSide==1) return 29;
    	return 40;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
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
	
	public MetaTileEntityMultiblock(int aID, String aName) {
		super(aID, aName);
	}
	
	public MetaTileEntityMultiblock() {}
}
