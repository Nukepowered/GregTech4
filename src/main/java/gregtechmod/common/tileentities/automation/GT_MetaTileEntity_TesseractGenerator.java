package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IDigitalChest;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Utility;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_TesseractGenerator extends MetaTileEntity {
	
	public static int TESSERACT_ENERGY_COST = 8, TESSERACT_ENERGY_COST_DIMENSIONAL = 32, tNeededEnergy = 0;
	
	public static final Map<Integer, GT_MetaTileEntity_TesseractGenerator> sTesseractGenerators = new HashMap<Integer, GT_MetaTileEntity_TesseractGenerator>();
	
	public byte isWorking = 0;
	public int mFrequency = 0, oFrequency = 0;
	
	public GT_MetaTileEntity_TesseractGenerator(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_TesseractGenerator() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return false;}
	@Override public int getInvSize()								{return 0;}
	@Override public int getMinimumStoredEU()						{return getBaseMetaTileEntity().getEUCapacity()/2;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUOutput()								{return 0;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean ownerControl()							{return true;}
	@Override public boolean unbreakable()							{return true;}
	@Override public int getProgresstime()							{return sTesseractGenerators.get(mFrequency) == this && isWorking >= 20 ? 999 : 0;}
	@Override public int maxProgresstime()							{return 1000;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_TesseractGenerator();
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
		TESSERACT_ENERGY_COST				= aConfig.get(GT_ConfigCategories.machineconfig, "Tesseract.EnergyPerTick",  8);
		TESSERACT_ENERGY_COST_DIMENSIONAL	= aConfig.get(GT_ConfigCategories.machineconfig, "Tesseract.InterDimensionalEnergyPerTick", 32);
	}
	
	@Override
	public void onServerStart() {
		sTesseractGenerators.clear();
	}
	
	@Override
	public void onServerStop() {
		sTesseractGenerators.clear();
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
			GT_Utility.sendChatToPlayer(aPlayer, "Frequency: " + mFrequency + (sTesseractGenerators.get(mFrequency) != null && sTesseractGenerators.get(mFrequency) != this?EnumChatFormatting.RED+" (Occupied)":""));
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
			
			int type = sTesseractGenerators.get(mFrequency) != null && sTesseractGenerators.get(mFrequency) != this ? 2 : 1;
			GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.GT_Tesseract.message." + type, mFrequency));
		}
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override
	public String[] getInfoData() {
		return new String[] { "Tesseract Generator", "Freq: " + mFrequency, sTesseractGenerators.get(mFrequency)==this?"Active":"Inactive" }; // TODO REWORK LOCALE
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	public boolean isSendingInformation() {
		TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IGregTechDeviceInformation) return ((IGregTechDeviceInformation)tTileEntity).isGivingInformation();
		return false;
	}
	
	@Override
	public boolean isDigitalChest() {
		TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest) return ((IDigitalChest)tTileEntity).isDigitalChest();
		return false;
	}
	
	@Override
	public ItemStack[] getStoredItemData() {
		TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest) return ((IDigitalChest)tTileEntity).getStoredItemData();
		return null;
	}
	
	@Override
	public void setItemCount(int aCount) {
		TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest) ((IDigitalChest)tTileEntity).setItemCount(aCount);
	}
	
	@Override
	public int getMaxItemCount() {
		TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity != null && getBaseMetaTileEntity().isAllowedToWork() && tTileEntity instanceof IDigitalChest) return ((IDigitalChest)tTileEntity).getMaxItemCount();
		return 0;
	}
	
	@Override
	public boolean isItemValidForSlot(int aIndex, ItemStack aStack) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.isItemValidForSlot(aIndex, aStack);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int aSide) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return new int[0];
		if (tTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity).getAccessibleSlotsFromSide(aSide);
		int[] rArray = new int[getSizeInventory()];
		for (int i = 0; i < getSizeInventory(); i++) rArray[i] = i;
		return rArray;
	}
	
	@Override
	public boolean canInsertItem(int aIndex, ItemStack aStack, int aSide) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		if (tTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity).canInsertItem(aIndex, aStack, aSide);
		return true;
	}
	
	@Override
	public boolean canExtractItem(int aIndex, ItemStack aStack, int aSide) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		if (tTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity).canExtractItem(aIndex, aStack, aSide);
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int aIndex) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.getStackInSlot(aIndex);
	}
	
	@Override
	public void setInventorySlotContents(int aIndex, ItemStack aStack) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return;
		tTileEntity.setInventorySlotContents(aIndex, aStack);
	}
	
	@Override
	public ItemStack decrStackSize(int aIndex, int aAmount) {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.decrStackSize(aIndex, aAmount);
	}
	
	@Override
	public String getInventoryName() {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return "";
		return tTileEntity.getInventoryName();
	}
	
	@Override
	public int getInventoryStackLimit() {
		IInventory tTileEntity = getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.getInventoryStackLimit();
	}
	
	@Override
	public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.canFill(aSide, aFluid);
	}
	
	@Override
	public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return false;
		return tTileEntity.canDrain(aSide, aFluid);
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection aSide) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return new FluidTankInfo[] {};
		return tTileEntity.getTankInfo(aSide);
	}
	
	@Override
    public int fill_default(ForgeDirection aDirection, FluidStack aFluid, boolean doFill) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return 0;
		return tTileEntity.fill(aDirection, aFluid, doFill);
    }
	
	@Override
	public FluidStack drain(ForgeDirection aDirection, int maxDrain, boolean doDrain) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.drain(aDirection, maxDrain, doDrain);
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(getBaseMetaTileEntity().getBackFacing());
		if (tTileEntity == null || !getBaseMetaTileEntity().isAllowedToWork()) return null;
		return tTileEntity.drain(aSide, aFluid, doDrain);
	}
	
	public boolean addEnergyConsumption(GT_MetaTileEntity_TesseractTerminal aTerminal) {
		if (!getBaseMetaTileEntity().isAllowedToWork()) return false;
		tNeededEnergy += aTerminal.getBaseMetaTileEntity().getWorld() == getBaseMetaTileEntity().getWorld() ? TESSERACT_ENERGY_COST : TESSERACT_ENERGY_COST_DIMENSIONAL;
		return true;
	}
	
	public boolean isValidTesseractGenerator(String aOwnerName, boolean aWorkIrrelevant) {
		return getBaseMetaTileEntity() != null && !getBaseMetaTileEntity().isInvalidTileEntity() && getBaseMetaTileEntity().isAllowedToWork() && (aOwnerName == null || getBaseMetaTileEntity().getOwnerName().equals(aOwnerName)) && (aWorkIrrelevant || isWorking >= 20);
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
			if (mFrequency != oFrequency) {
				if (sTesseractGenerators.get(oFrequency) == this) {
					sTesseractGenerators.remove(oFrequency);
					getBaseMetaTileEntity().issueBlockUpdate();
				}
				oFrequency = mFrequency;
			}
			if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().decreaseStoredEnergyUnits(tNeededEnergy, false)) {
				if (sTesseractGenerators.get(mFrequency) == null || !sTesseractGenerators.get(mFrequency).isValidTesseractGenerator(null, true)) {
					sTesseractGenerators.put(mFrequency, this);
				}
			} else {
				if (sTesseractGenerators.get(mFrequency) == this) {
					sTesseractGenerators.remove(mFrequency);
					getBaseMetaTileEntity().issueBlockUpdate();
				}
			}
			
			if (sTesseractGenerators.get(mFrequency) == this) {
				if (isWorking < 20) isWorking++;
				if (isWorking == 20) {
					getBaseMetaTileEntity().issueBlockUpdate();
					isWorking++;
				}
			} else {
				isWorking = 0;
			}
			
			tNeededEnergy = 0;
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return 313;
		if (GT_Utility.getOppositeSide(aSide) == aFacing) return 71;
		return 312;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Tesseract_Generator.tooltip";
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
