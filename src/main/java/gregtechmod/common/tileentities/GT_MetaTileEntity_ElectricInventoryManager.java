package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricInventoryManager extends MetaTileEntity {
	
	public int[] mSlotRange = new int[4];
	public boolean mWorkedLastTick = false;
	
	public GT_MetaTileEntity_ElectricInventoryManager(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ElectricInventoryManager() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUOutput()								{return 32;}
    @Override public int maxEUPulses()								{return 4;}
    @Override public int maxEUStore()								{return 100000;}
    @Override public int maxMJStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 129);}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex<3;}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{for (int i = 0; i < mSlotRange.length; i++) if (aSide == getRangeDirection(i) && getRangeEnergy(i)) return true; return false;}
	@Override public int getMinimumStoredEU()						{return 50000;}
    @Override public int getInvSize()								{return 16;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricInventoryManager();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setInteger("mSlotRange0", mSlotRange[0]);
		aNBT.setInteger("mSlotRange1", mSlotRange[1]);
		aNBT.setInteger("mSlotRange2", mSlotRange[2]);
		aNBT.setInteger("mSlotRange3", mSlotRange[3]);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		mSlotRange[0] = aNBT.getInteger("mSlotRange0");
		mSlotRange[1] = aNBT.getInteger("mSlotRange1");
		mSlotRange[2] = aNBT.getInteger("mSlotRange2");
		mSlotRange[3] = aNBT.getInteger("mSlotRange3");
	}
	
	public void iterateRangeDirection(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~7) | (((mSlotRange[aIndex]&7)+1)%6);
	}
	
	public void switchRangeEnergy(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~8) | ((mSlotRange[aIndex]&8)>0?0:8);
	}
	
	public void iterateSlot1Direction(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~112) | (((((mSlotRange[aIndex]&112)>>4)+1)%6)<<4);
	}
	
	public void iterateSlot2Direction(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~896) | (((((mSlotRange[aIndex]&896)>>7)+1)%6)<<7);
	}
	
	public void iterateSlot3Direction(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~7168) | (((((mSlotRange[aIndex]&7168)>>10)+1)%6)<<10);
	}
	
	public void switchSlot1InOut(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~8192) | ((mSlotRange[aIndex]&8192)>0?0:8192);
	}
	
	public void switchSlot2InOut(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~16384) | ((mSlotRange[aIndex]&16384)>0?0:16384);
	}
	
	public void switchSlot3InOut(int aIndex) {
		mSlotRange[aIndex] = (mSlotRange[aIndex]&~32768) | ((mSlotRange[aIndex]&32768)>0?0:32768);
	}
	
	public byte getRangeDirection(int aIndex) {
		return (byte)(mSlotRange[aIndex]&7);
	}
	
	public byte getSlot1Direction(int aIndex) {
		return (byte)((mSlotRange[aIndex]&112)>>4);
	}
	
	public byte getSlot2Direction(int aIndex) {
		return (byte)((mSlotRange[aIndex]&896)>>7);
	}
	
	public byte getSlot3Direction(int aIndex) {
		return (byte)((mSlotRange[aIndex]&7168)>>10);
	}
	
	public boolean getRangeEnergy(int aIndex) {
		return (mSlotRange[aIndex]&8)>0;
	}
	
	public boolean getSlot1InOut(int aIndex) {
		return (mSlotRange[aIndex]&8192)>0;
	}
	
	public boolean getSlot2InOut(int aIndex) {
		return (mSlotRange[aIndex]&16384)>0;
	}
	
	public boolean getSlot3InOut(int aIndex) {
		return (mSlotRange[aIndex]&32768)>0;
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 5000 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%100 == 0 || mWorkedLastTick || getBaseMetaTileEntity().hasInventoryBeenModified())) {
			mWorkedLastTick = false;
			
			IInventory[] tTileEntities = new IInventory[] {
					getBaseMetaTileEntity().getIInventoryAtSide((byte)0),
					getBaseMetaTileEntity().getIInventoryAtSide((byte)1),
					getBaseMetaTileEntity().getIInventoryAtSide((byte)2),
					getBaseMetaTileEntity().getIInventoryAtSide((byte)3),
					getBaseMetaTileEntity().getIInventoryAtSide((byte)4),
					getBaseMetaTileEntity().getIInventoryAtSide((byte)5),
					null, null
			};
			
			int tCost = 0;
			
			for (int i = 0; i < 4; i++) {
				if (tTileEntities[getRangeDirection(i)] != null) {
					ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
					ItemStack tStack;
					tList.add(null);
					
					tStack = mInventory[3+i*3+0];
					if (tStack == null) {
						if (getSlot1InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot1Direction(i), getSlot1Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot1Direction(i), getSlot1Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
					} else {
						tList.set(0, tStack);
						if (getSlot1InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot1Direction(i), getSlot1Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot1Direction(i), getSlot1Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
					}
					
					tStack = mInventory[3+i*3+1];
					if (tStack == null) {
						if (getSlot2InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot2Direction(i), getSlot2Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot2Direction(i), getSlot2Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
					} else {
						tList.set(0, tStack);
						if (getSlot2InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot2Direction(i), getSlot2Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot2Direction(i), getSlot2Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
					}
					
					tStack = mInventory[3+i*3+2];
					if (tStack == null) {
						if (getSlot3InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot3Direction(i), getSlot3Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot3Direction(i), getSlot3Direction(i), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
					} else {
						tList.set(0, tStack);
						if (getSlot3InOut(i))
							tCost += 5 * GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntities[getRangeDirection(i)], getSlot3Direction(i), getSlot3Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
						else
							tCost += 5 * GT_Utility.moveOneItemStack(tTileEntities[getRangeDirection(i)], getBaseMetaTileEntity(), getSlot3Direction(i), getSlot3Direction(i), tList, false, (byte)tStack.stackSize, (byte)1, (byte)64, (byte)1);
					}
				}
			}
			
			if (tCost > 0) {
				mWorkedLastTick = true;
				getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
			}
		}
	}

	@Override
	public String getDescription() {
		return "It's simpler than you think. I promise.";
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		switch (aSide) {
		case 0: return 113 + (aRedstone?8:0);
		case 1: return 112 + (aRedstone?8:0);
		case 2: return 116 + (aRedstone?8:0);
		case 3: return 213 + (aRedstone?8:0);
		case 4: return 212 + (aRedstone?8:0);
		case 5: return 117 + (aRedstone?8:0);
		}
		return 0;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return true;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return true;
	}
}
