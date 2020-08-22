package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Translocator extends MetaTileEntity {

	public boolean bOutput = true, bInvertFilter = false;
	public int mSuccess = 0;
	
	public GT_MetaTileEntity_Translocator(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Translocator() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{return getBaseMetaTileEntity().getFrontFacing() == aSide || getBaseMetaTileEntity().getBackFacing() == aSide;}
	@Override public int getMinimumStoredEU()						{return 2000;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return bOutput?32:0;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 10;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 101);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Translocator();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setBoolean("bOutput", bOutput);
    	aNBT.setBoolean("bInvertFilter", bInvertFilter);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		bOutput = aNBT.getBoolean("bOutput");
		bInvertFilter = aNBT.getBoolean("bInvertFilter");
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing() && aSide != getBaseMetaTileEntity().getBackFacing();
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 1000 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%(197 - (getBaseMetaTileEntity().getOverclockerUpgradeCount()*49)) == 0 || (getBaseMetaTileEntity().getTimer()%5 == 0 && mSuccess > 0) || mSuccess >= 20)) {
			mSuccess--;
			ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
			for (int i = 0; i < 9; i++) tList.add(mInventory[i]);
			int tCost = (getBaseMetaTileEntity().getOverclockerUpgradeCount()+1) * GT_Utility.moveOneItemStack(getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getFrontFacing()), getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing()), getBaseMetaTileEntity().getBackFacing(), getBaseMetaTileEntity().getFrontFacing(), tList, bInvertFilter, (byte)64, (byte)1, (byte)64, (byte)1)*2*((mInventory[0] == null && mInventory[1] == null && mInventory[2] == null && mInventory[3] == null && mInventory[4] == null && mInventory[5] == null && mInventory[6] == null && mInventory[7] == null && mInventory[8] == null)?1:2);
			if (tCost > 0) {
				getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
				mSuccess = 30;
			}
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 112+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);
		
		int tIndex = 128+(aRedstone?8:0);
		
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
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Translocator.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
}
