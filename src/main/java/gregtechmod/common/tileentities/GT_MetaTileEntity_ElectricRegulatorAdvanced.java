package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_ElectricRegulatorAdvanced extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public int[] mTargetSlots = new int[] {0,0,0,0,0,0,0,0,0};
	
	public GT_MetaTileEntity_ElectricRegulatorAdvanced(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricRegulatorAdvanced() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUPulses()								{return 4;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 123);}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex<9;}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getBackFacing();}
	@Override public int getMinimumStoredEU()						{return 1000;}
    @Override public int getInvSize()								{return 19;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricRegulatorAdvanced();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mTargetSlot1", mTargetSlots[0]);
    	aNBT.setInteger("mTargetSlot2", mTargetSlots[1]);
    	aNBT.setInteger("mTargetSlot3", mTargetSlots[2]);
    	aNBT.setInteger("mTargetSlot4", mTargetSlots[3]);
    	aNBT.setInteger("mTargetSlot5", mTargetSlots[4]);
    	aNBT.setInteger("mTargetSlot6", mTargetSlots[5]);
    	aNBT.setInteger("mTargetSlot7", mTargetSlots[6]);
    	aNBT.setInteger("mTargetSlot8", mTargetSlots[7]);
    	aNBT.setInteger("mTargetSlot9", mTargetSlots[8]);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTargetSlots[0] = aNBT.getInteger("mTargetSlot1");
		mTargetSlots[1] = aNBT.getInteger("mTargetSlot2");
		mTargetSlots[2] = aNBT.getInteger("mTargetSlot3");
		mTargetSlots[3] = aNBT.getInteger("mTargetSlot4");
		mTargetSlots[4] = aNBT.getInteger("mTargetSlot5");
		mTargetSlots[5] = aNBT.getInteger("mTargetSlot6");
		mTargetSlots[6] = aNBT.getInteger("mTargetSlot7");
		mTargetSlots[7] = aNBT.getInteger("mTargetSlot8");
		mTargetSlots[8] = aNBT.getInteger("mTargetSlot9");
	}
	
	@SuppressWarnings("unused")
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 500 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%10 == 0)) {
			TileEntity tTileEntity1, tTileEntity2;
			ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
			tList.add(null);
			int tCosts = 0;
			for (int i = 0; i < 9 && tCosts == 0; i++) {
				if (mInventory[i+9] == null) continue;
				tList.set(0, mInventory[i+9]);
				tCosts = GT_Utility.moveOneItemStackIntoSlot(getBaseMetaTileEntity(), getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing()), getBaseMetaTileEntity().getBackFacing(), mTargetSlots[i], tList, false, (byte)mInventory[i+9].stackSize, (byte)mInventory[i+9].stackSize, (byte)64, (byte)1)*3;
				if (tCosts > 0) getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCosts, true);
			}
		}
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, mInventory[aIndex+9]);
	}
	
	@Override
	public String getDescription() {
		return "Lets you save up to 8 additional Machines when used correctly!";
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 135+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);
		int tIndex = 135+(aRedstone?8:0);
		
		switch (aFacing) {
		case 0:
			return tIndex+64;
		case 1:
			return tIndex+32;
		case 2: switch (aSide) {
			case 0: return tIndex+32;
			case 1: return tIndex+32;
			case 4: return tIndex+16;
			case 5: return tIndex+48;
			}
		case 3: switch (aSide) {
			case 0: return tIndex+64;
			case 1: return tIndex+64;
			case 4: return tIndex+48;
			case 5: return tIndex+16;
			}
		case 4: switch (aSide) {
			case 0: return tIndex+16;
			case 1: return tIndex+16;
			case 2: return tIndex+48;
			case 3: return tIndex+16;
			}
		case 5: switch (aSide) {
			case 0: return tIndex+48;
			case 1: return tIndex+48;
			case 2: return tIndex+16;
			case 3: return tIndex+48;
			}
		}
		return tIndex;
	}
}
