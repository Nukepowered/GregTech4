package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_Barrel extends MetaTileEntity {
	
	public int mItemCount = 0, mItemID = 0, mItemMeta = 0;
	
	public boolean isDigitalChest;
	
	public GT_MetaTileEntity_Barrel(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
		isDigitalChest = true;
	}
	
	public GT_MetaTileEntity_Barrel() {
		isDigitalChest = true;
	}
	
	@Override public boolean unbreakable()							{return true;}
	@Override public boolean isWrenchable()							{return mItemCount <= 0;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public int getInvSize()								{return 3;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean ownerControl()							{return false;}
	@Override public boolean isEnetOutput()							{return false;}
	@Override public boolean isEnetInput()							{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public int getProgresstime()							{return mItemCount+(mInventory[0]==null?0:mInventory[0].stackSize)+(mInventory[1]==null?0:mInventory[1].stackSize)+(mInventory[2]==null?0:mInventory[2].stackSize);}
	@Override public int maxProgresstime()							{return getMaxItemCount();}
	@Override public int getMaxItemCount()							{return GT_Mod.sBarrelItemCount - 192;}
	@Override public void setItemCount(int aCount)					{mItemCount = aCount;}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {
	    ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
	    
	    if (tPlayerItem == null) {
		    if (mItemID > 0) {
		    	for (int i = 0; mItemCount < getMaxItemCount() && i < aPlayer.inventory.getSizeInventory(); i++) {
		    		if (aPlayer.inventory.getStackInSlot(i) != null && aPlayer.inventory.getStackInSlot(i).itemID == mItemID && aPlayer.inventory.getStackInSlot(i).getItemDamage() == mItemMeta && !aPlayer.inventory.getStackInSlot(i).hasTagCompound()) {
					    mItemCount += aPlayer.inventory.getStackInSlot(i).stackSize;
					    if (aPlayer.inventory.getStackInSlot(i).stackSize == 111) {
					    	mItemCount = getMaxItemCount() + 192 - (mItemCount + (mInventory[0]==null?0:mInventory[0].stackSize)+(mInventory[1]==null?0:mInventory[1].stackSize)+(mInventory[2]==null?0:mInventory[2].stackSize));
					    } else {
					    	if (mItemCount > getMaxItemCount()) {
					    		aPlayer.inventory.getStackInSlot(i).stackSize = mItemCount - getMaxItemCount();
							    mItemCount = getMaxItemCount();
					        } else {
					    		aPlayer.inventory.getStackInSlot(i).stackSize = 0;
				    		}
					    }
		    		}
			    	if (aPlayer.inventory.getStackInSlot(i) != null && aPlayer.inventory.getStackInSlot(i).stackSize <= 0) {
			    		aPlayer.inventory.setInventorySlotContents(i, null);
			    	}
		    	}
		    	GT_Utility.sendChatToPlayer(aPlayer, (mItemCount + (mInventory[0]==null?0:mInventory[0].stackSize)+(mInventory[1]==null?0:mInventory[1].stackSize)+(mInventory[2]==null?0:mInventory[2].stackSize)) + " of " + new ItemStack(mItemID, 1, mItemMeta).getDisplayName());
		    }
	    } else {
		    if (isDigitalChest && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingQuantumChestUpgrade")) {
		    	if (tPlayerItem.stackSize > 0 || aPlayer.capabilities.isCreativeMode) {
		    		if (!aPlayer.capabilities.isCreativeMode) tPlayerItem.stackSize-=1;
		    		NBTTagCompound tNBT = new NBTTagCompound();
		    		((TileEntity)getBaseMetaTileEntity()).writeToNBT(tNBT);
		    		getBaseMetaTileEntity().issueClientUpdate();
		    		getBaseMetaTileEntity().setInitialValuesAsNBT(tNBT, (short)49);
		    	}
		    	return;
		    }
		    
		    if (mItemID <= 0) {
		    	mItemID = tPlayerItem.itemID;
		    	mItemMeta = tPlayerItem.getItemDamage();
		    	mItemCount = 0;
		    }
		    if (mItemID > 0) {
				if (mItemCount < getMaxItemCount() && tPlayerItem.itemID == mItemID && tPlayerItem.getItemDamage() == mItemMeta && !tPlayerItem.hasTagCompound()) {
				    mItemCount += tPlayerItem.stackSize;
				    if (tPlayerItem.stackSize == 111) {
				    	mItemCount = getMaxItemCount();
				    } else {
				    	if (mItemCount > getMaxItemCount()) {
				    		tPlayerItem.stackSize = mItemCount - getMaxItemCount();
						    mItemCount = getMaxItemCount();
				        } else {
				        	tPlayerItem.stackSize = 0;
			    		}
				    }
		    	} else {
		    		GT_Utility.sendChatToPlayer(aPlayer, (mItemCount + (mInventory[0]==null?0:mInventory[0].stackSize)+(mInventory[1]==null?0:mInventory[1].stackSize)+(mInventory[2]==null?0:mInventory[2].stackSize)) + " of " + new ItemStack(mItemID, 1, mItemMeta).getDisplayName());
				}
		    }
	    }
	    if (aPlayer.inventoryContainer != null) aPlayer.inventoryContainer.detectAndSendChanges();
	}
	
	@Override public void onLeftclick(EntityPlayer aPlayer) {
		if (mInventory[0] != null && mInventory[0].stackSize > 0) {
			ItemStack tOutput = GT_Utility.copy(mInventory[0]);
			if (aPlayer.isSneaking()) tOutput.stackSize = 1;
			getBaseMetaTileEntity().decrStackSize(0, tOutput.stackSize);
			EntityItem tEntity = new EntityItem(getBaseMetaTileEntity().getWorld(), getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, tOutput);
			tEntity.motionX = 0;
			tEntity.motionY = 0;
			tEntity.motionZ = 0;
			getBaseMetaTileEntity().getWorld().spawnEntityInWorld(tEntity);
		}
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Barrel();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mItemCount", mItemCount);
    	aNBT.setInteger("mItemID"	, mItemID);
    	aNBT.setInteger("mItemMeta"	, mItemMeta);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mItemCount	= aNBT.getInteger("mItemCount");
    	mItemID		= aNBT.getInteger("mItemID");
    	mItemMeta	= aNBT.getInteger("mItemMeta");
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && mItemID != 0) {
			if (Item.itemsList[mItemID] == null) {
				mItemID = 0;
				mItemMeta = 0;
				mItemCount = 0;
			}
			
			if (mInventory[1] == null) {
			    mInventory[1] = new ItemStack(mItemID, 0, mItemMeta);
			} else {
				if (mItemCount < getMaxItemCount() && mInventory[1].itemID == mItemID && mInventory[1].getItemDamage() == mItemMeta && !mInventory[1].hasTagCompound()) {
				    mItemCount += mInventory[1].stackSize;
			    	if (mItemCount > getMaxItemCount()) {
					  	mInventory[1].stackSize = mItemCount - getMaxItemCount();
					    mItemCount = getMaxItemCount();
			        } else {
					   	mInventory[1].stackSize = 0;
		    		}
		    	}
			}
			
			if (mInventory[2] == null) {
			    mInventory[2] = new ItemStack(mItemID, 0, mItemMeta);
			} else {
			    if (mItemCount < getMaxItemCount() && mInventory[2].itemID == mItemID && mInventory[2].getItemDamage() == mItemMeta && !mInventory[2].hasTagCompound()) {
				  	mItemCount += mInventory[2].stackSize;
			    	if (mItemCount > getMaxItemCount()) {
					   	mInventory[2].stackSize = mItemCount - getMaxItemCount();
					   	mItemCount = getMaxItemCount();
			    	} else {
					   	mInventory[2].stackSize = 0;
			    	}
			   	}
			}
	    	
		    if (mItemCount > 0) {
			    if (mInventory[0] == null) {
			    	mInventory[0] = new ItemStack(mItemID, 0, mItemMeta);
			    }
			    if (mInventory[0] != null && mInventory[0].itemID == mItemID && mInventory[0].getItemDamage() == mItemMeta && !mInventory[0].hasTagCompound()) {
				    while (mInventory[0].stackSize < mInventory[0].getMaxStackSize() && mItemCount > 0) {
				    	mItemCount--;
				    	mInventory[0].stackSize++;
				    }
			    }
	    	}
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return 37;
    	if (aSide == 0)
    		return 32;
    	else if (aSide == 1)
    		return 29;
    	else
    		return 40;
	}
	
	public ItemStack getStoredItem() {
		if (mItemID == 0) return null;
		return new ItemStack(mItemID, 1, mItemMeta);
	}
	
	@Override
	public String getMainInfo() {
		if (getStoredItem() == null) return "";
		return getStoredItem().getDisplayName();
	}
	@Override
	public String getSecondaryInfo() {
		if (getStoredItem() == null) return "";
		return ""+(mItemCount+(mInventory[0]==null?0:mInventory[0].stackSize)+(mInventory[1]==null?0:mInventory[1].stackSize)+(mInventory[2]==null?0:mInventory[2].stackSize));
	}
	@Override
	public String getTertiaryInfo() {
		return "Max: " + (getMaxItemCount()+192);
	}
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	@Override
	public String getDescription() {
		return "Eight times better than a Barrel!";
	}
	@Override
	public boolean isDigitalChest() {
		return true;
	}
	@Override
	public ItemStack[] getStoredItemData() {
		return new ItemStack[] {new ItemStack(mItemID, mItemCount, mItemMeta)};
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 0;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return (aIndex==1||aIndex==2) && aStack.itemID == mItemID && aStack.getItemDamage() == mItemMeta && !aStack.hasTagCompound();
	}
}
