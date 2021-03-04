package gregtechmod.common.tileentities.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_DigitalChest extends MetaTileEntity {

   public boolean isDigitalChest = true;
   public static int sDigitalItemCount = '\u8000';


   public GT_MetaTileEntity_DigitalChest(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_DigitalChest() {}

   public void onConfigLoad(GT_Config aConfig) {
      sDigitalItemCount = Math.max(1024, aConfig.get(GT_ConfigCategories.machineconfig, "DigitalChest.MaxItems", sDigitalItemCount));
   }


   @Override public void saveNBTData(NBTTagCompound aNBT) {}
   @Override public void loadNBTData(NBTTagCompound aNBT) {}
   @Override public boolean unbreakable() 							{return true;}
   @Override public boolean isSimpleMachine() 						{return true;}
   @Override  public int getInvSize() 								{return 2;}
   @Override public boolean isFacingValid(byte aFacing) 			{return true;}
   @Override public boolean isAccessAllowed(EntityPlayer aPlayer) 	{return true;}
   @Override public boolean ownerControl() 							{return false;}
   @Override public boolean isEnetOutput() 							{return false;}
   @Override public boolean isEnetInput() 							{return false;}
   @Override public boolean isOutputFacing(byte aSide) 				{return false;}
   @Override public boolean isInputFacing(byte aSide) 				{return false;}
   @Override public boolean isDigitalChest() 						{return isDigitalChest;}
   @Override public boolean isWrenchable()							{return mInventory[0] == null || mInventory[0].stackSize <= 0;}
   @Override public void setItemCount(int aCount) 					{if (mInventory[0] != null) mInventory[0].stackSize = aCount; }
   @Override public boolean setStackToZeroInsteadOfNull(int aIndex) {return !getBaseMetaTileEntity().isAllowedToWork();}

   public int getMaxItemCount() {
      return sDigitalItemCount - 192;
   }
   
   @Override
   public void onPostTick() { // Second slot needed for inserting, stupid checks from other mods wouldn't let stack more than 64 items
	   if (mInventory[0] != null) {
		   if (mInventory[1] == null) {
			   mInventory[1] = GT_Utility.copyAmount(0, mInventory);
		   } else {
			   if (mInventory[1].stackSize > 0) {
				   mInventory[0].stackSize += mInventory[1].stackSize;
				   mInventory[1].stackSize = 0;
			   }
		   }
	   } else {
		   if (mInventory[1] != null) {
			   if (setStackToZeroInsteadOfNull(0)) {
				   mInventory[0] = mInventory[1];
				   mInventory[1] = null;
			   } else {
				   mInventory[1] = null;
			   }
		   }
	   }
   }
   
   @Override
   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
      if(tPlayerItem == null) {
         if(mInventory[0] != null) {
            for(int i = 0; mInventory[0].stackSize < this.getMaxItemCount() && i < aPlayer.inventory.getSizeInventory(); ++i) {
            	ItemStack itemInSlot = aPlayer.inventory.getStackInSlot(i);
				if (itemInSlot != null && itemInSlot.isItemEqual(getStoredItem()) && !itemInSlot.hasTagCompound()) {
					int tmp = mInventory[0].stackSize;
					mInventory[0].stackSize = Math.min(tmp + itemInSlot.stackSize, getMaxItemCount());
					tmp = mInventory[0].stackSize - tmp;
					if (tmp == itemInSlot.stackSize) {
						aPlayer.inventory.setInventorySlotContents(i, null);
					} else {
						itemInSlot.stackSize -= tmp;
					}
				}
			}

            GT_Utility.sendChatToPlayer(aPlayer, mInventory[0].stackSize + " of " + mInventory[0].getDisplayName());
         }
      } else {
         if(isDigitalChest && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingQuantumChestUpgrade")) {
            if(tPlayerItem.stackSize > 0 || aPlayer.capabilities.isCreativeMode) {
               if(!aPlayer.capabilities.isCreativeMode) {
                  --tPlayerItem.stackSize;
               }

               NBTTagCompound var4 = new NBTTagCompound();
               ((TileEntity)this.getBaseMetaTileEntity()).writeToNBT(var4);
               this.getBaseMetaTileEntity().issueClientUpdate();
               this.getBaseMetaTileEntity().setInitialValuesAsNBT(var4, (short)49);
            }

            return;
         }

         if(mInventory[0] == null) {
            mInventory[0] = tPlayerItem;
            mInventory[0].stackSize = tPlayerItem.stackSize;
            aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
         } else if (tPlayerItem.isItemEqual(mInventory[0]) && !tPlayerItem.hasTagCompound()) {
        	 int tmp = mInventory[0].stackSize;
        	 mInventory[0].stackSize = Math.min(mInventory[0].stackSize + tPlayerItem.stackSize, getMaxItemCount());
        	 tmp = mInventory[0].stackSize - tmp;
        	 if (tmp == tPlayerItem.stackSize) {
        		 aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
        	 } else {
        		 tPlayerItem.stackSize -= tmp;
        	 }
        	 
         } else {
        	 GT_Utility.sendChatToPlayer(aPlayer, mInventory[0].stackSize + " of " + mInventory[0].getDisplayName());
         }
      }

      if(aPlayer.inventoryContainer != null) {
         aPlayer.inventoryContainer.detectAndSendChanges();
      }
   }

   @Override
   public void onLeftclick(EntityPlayer aPlayer) {
		if (mInventory[0] != null) {
			if (mInventory[0].stackSize > 0) {
				ItemStack tOutput = mInventory[0].copy();
				tOutput.stackSize = aPlayer.isSneaking() ? 1 : Math.min(tOutput.getMaxStackSize(), mInventory[0].stackSize);
				mInventory[0].stackSize -= tOutput.stackSize;
				EntityItem tEntity = new EntityItem(this.getBaseMetaTileEntity().getWorld(),
						(double) this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1)
								+ 0.5D,
						(double) this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1)
								+ 0.5D,
						(double) this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1)
								+ 0.5D,
						tOutput);
				tEntity.motionX = 0.0D;
				tEntity.motionY = 0.0D;
				tEntity.motionZ = 0.0D;
				this.getBaseMetaTileEntity().getWorld().spawnEntityInWorld(tEntity);
			}
			
			if (mInventory[0].stackSize <= 0 && getBaseMetaTileEntity().isAllowedToWork()) {
				mInventory[0] = null;
			}
		} 
   }

   @Override
   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_DigitalChest();
   }

   @Override
   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?37:(aSide == 0?32:(aSide == 1?29:40));
   }

   private ItemStack getStoredItem() {
	   ItemStack stack = mInventory[0];
	   if (stack == null) {
		   return null;
	   } else {
		   return stack.copy();
	   }
   }
   
   @Override
   public Map<String, List<Object>> getInfoData() {
	   ItemStack inventory = getStoredItem();
	   return InfoBuilder.create()
			   .newKey("metatileentity.GT_Barrel.item", GT_Utility.isStackValid(inventory) ? inventory : "materials.Empty")
			   .newKey("metatileentity.GT_Barrel.count", GT_Utility.parseNumberToString(GT_Utility.isStackValid(inventory) ? inventory.stackSize : 0), GT_Utility.parseNumberToString(getMaxItemCount()))
			   .build();
   }

   @Override
   public boolean isGivingInformation() {
      return true;
   }

   @Override
   public String getDescription() {
      return "metatileentity.GT_Barrel.tooltip";
   }
   
   @Override
   public ArrayList<String> getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList<String> aList) {
	   aList.add("Internal inventory: " + Arrays.asList(mInventory).toString());
	   return aList;
   }
   
   @Override
   public ItemStack[] getStoredItemData() {
		return mInventory;
   }
   
   @Override
   public int getInventoryStackLimit() {
	   return getMaxItemCount();
   }
   
   @Override
   public ItemStack getStackInSlot(int aIndex) {
	   if (aIndex >= 0 && aIndex < mInventory.length) return mInventory[aIndex]; 
	   
	   return null;
   }
   
   @Override
   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   @Override
   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 && (mInventory[0] == null || (mInventory[0].isItemEqual(aStack) && !aStack.hasTagCompound()));
   }
}
