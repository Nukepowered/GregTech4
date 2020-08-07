package gregtechmod.common.tileentities;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

public class GT_MetaTileEntity_TesseractTerminal extends MetaTileEntity {
	
	public int mFrequency = 0;
	
	public boolean mDidWork = false;
	
	public static boolean sInterDimensionalTesseractAllowed = true;
	
	public GT_MetaTileEntity_TesseractTerminal(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_TesseractTerminal() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return false;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return false;}
	@Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean ownerControl()							{return true;}
	@Override public int getProgresstime()							{return getTesseract(mFrequency, false) != null ? 999 : 0;}
	@Override public int maxProgresstime()							{return 1000;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_TesseractTerminal();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mFrequency", mFrequency);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		mFrequency = aNBT.getInteger("mFrequency");
	}
	
	@Override
	public void onConfigLoad(GT_Config aConfig) {
		sInterDimensionalTesseractAllowed = aConfig.addAdvConfig(GT_ConfigCategories.machineconfig, "Tesseract.Interdimensional", true);
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) {
			byte tScrew = 0;
			switch (aSide) {
			case  0: case  1:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(  aZ*2));
				break;
			case  2:
				tScrew = (byte)((byte)(2-aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  3:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  4:
				tScrew = (byte)((byte)(  aZ*2) + 2 * (byte)(2-aY*2));
				break;
			case  5:
				tScrew = (byte)((byte)(2-aZ*2) + 2 * (byte)(2-aY*2));
				break;
			}
			switch (tScrew) {
			case  0:
				mFrequency-=1;
				break;
			case  1:
				mFrequency+=1;
				break;
			}
			GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + mFrequency + (getTesseract(mFrequency, false)==null?"":EnumChatFormatting.GREEN+" (Connected)"));
		}
		
		return true;
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) {
			byte tScrew = 0;
			switch (aSide) {
			case  0: case  1:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(  aZ*2));
				break;
			case  2:
				tScrew = (byte)((byte)(2-aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  3:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  4:
				tScrew = (byte)((byte)(  aZ*2) + 2 * (byte)(2-aY*2));
				break;
			case  5:
				tScrew = (byte)((byte)(2-aZ*2) + 2 * (byte)(2-aY*2));
				break;
			}
			switch (tScrew) {
			case  0:
				mFrequency-=64;
				break;
			case  1:
				mFrequency+=64;
				break;
			case  2:
				mFrequency-=512;
				break;
			case  3:
				mFrequency+=512;
				break;
			}
			GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + mFrequency + (getTesseract(mFrequency, false)==null?"":EnumChatFormatting.GREEN+" (Connected)"));
		}
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	public GT_MetaTileEntity_TesseractGenerator getTesseract(int aFrequency, boolean aWorkIrrelevant) {
		GT_MetaTileEntity_TesseractGenerator rTesseract = GT_MetaTileEntity_TesseractGenerator.sTesseractGenerators.get(aFrequency);
		if (rTesseract == null || !rTesseract.isValidTesseractGenerator(getBaseMetaTileEntity().getOwnerName(), aWorkIrrelevant)) return null;
		if (!sInterDimensionalTesseractAllowed && rTesseract.getBaseMetaTileEntity().getWorld() != getBaseMetaTileEntity().getWorld()) return null;
		return rTesseract;
	}
	
	@Override
	public String getMainInfo() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork()) if (tTileEntity.isSendingInformation()) return tTileEntity.getMainInfo();
		return "Tesseract Terminal";
	}
	
	@Override
	public String getSecondaryInfo() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork()) if (tTileEntity.isSendingInformation()) return tTileEntity.getSecondaryInfo();
		return "Freq: " + mFrequency;
	}
	
	@Override
	public String getTertiaryInfo() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork()) if (tTileEntity.isSendingInformation()) return tTileEntity.getTertiaryInfo();
		return getTesseract(mFrequency, false)!=null?"Active":"Inactive";
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public boolean isDigitalChest() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.isDigitalChest();
	}
	
	@Override
	public ItemStack[] getStoredItemData() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.getStoredItemData();
	}
	
	@Override
	public void setItemCount(int aCount) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return;
		tTileEntity.setItemCount(aCount);
	}
	
	@Override
	public int getMaxItemCount() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.getMaxItemCount();
	}
	
	@Override
	public boolean isItemValidForSlot(int aIndex, ItemStack aStack) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.isItemValidForSlot(aIndex, aStack);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int aSide) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return new int[0];
		return tTileEntity.getAccessibleSlotsFromSide(aSide);
	}
	
	@Override
	public boolean canInsertItem(int aIndex, ItemStack aStack, int aSide) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		if (tTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity).canInsertItem(aIndex, aStack, aSide);
		return true;
	}
	
	@Override
	public boolean canExtractItem(int aIndex, ItemStack aStack, int aSide) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		if (tTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity).canExtractItem(aIndex, aStack, aSide);
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int aIndex) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.getStackInSlot(aIndex);
	}
	
	@Override
	public void setInventorySlotContents(int aIndex, ItemStack aStack) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return;
		tTileEntity.setInventorySlotContents(aIndex, aStack);
	}
	
	@Override
	public ItemStack decrStackSize(int aIndex, int aAmount) {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.decrStackSize(aIndex, aAmount);
	}
	
	@Override
	public String getInventoryName() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return "";
		return tTileEntity.getInventoryName();
	}
	
	@Override
	public int getInventoryStackLimit() {
		GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.getInventoryStackLimit();
	}
	
	@Override
	public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.canFill(aSide, aFluid);
	}
	
	@Override
	public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.canDrain(aSide, aFluid);
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return new FluidTankInfo[] {};
		return tTileEntity.getTankInfo(aSide);
	}
	
	@Override
    public int fill_default(ForgeDirection aDirection, FluidStack aFluid, boolean doFill) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.fill(aDirection, aFluid, doFill);
    }
	
	@Override
	public FluidStack drain(ForgeDirection aDirection, int maxDrain, boolean doDrain) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.drain(aDirection, maxDrain, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
    	GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, false);
    	if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.drain(aSide, aFluid, doDrain);
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork()) {
			GT_MetaTileEntity_TesseractGenerator tTileEntity = getTesseract(mFrequency, true);
			if (tTileEntity != null) {
				tTileEntity.addEnergyConsumption(this);
				if (mDidWork == false && getTesseract(mFrequency, false) != null) {
					mDidWork = true;
					getBaseMetaTileEntity().issueBlockUpdate();
				}
			} else {
				if (mDidWork == true) {
					mDidWork = false;
					getBaseMetaTileEntity().issueBlockUpdate();
				}
			}
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return 313;
		return 312;
	}
	
	@Override
	public String getDescription() {
		return "Accesses Tesseracts remotely";
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
