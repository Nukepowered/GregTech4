package gregtechmod.common.tileentities.storage;

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

   public int mItemCount = 0;
   public boolean isDigitalChest = true;
   public static int sDigitalItemCount = '\u8000';


   public GT_MetaTileEntity_DigitalChest(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_DigitalChest() {}

   public void onConfigLoad(GT_Config aConfig) {
      sDigitalItemCount = Math.max(1024, aConfig.get(GT_ConfigCategories.machineconfig, "DigitalChest.MaxItems", sDigitalItemCount));
   }

   @Override public boolean unbreakable() 							{return true;}
   @Override public boolean isSimpleMachine() 						{return true;}
   @Override  public int getInvSize() 								{return 1;}
   @Override public boolean isFacingValid(byte aFacing) 			{return true;}
   @Override public boolean isAccessAllowed(EntityPlayer aPlayer) 	{return true;}
   @Override public boolean ownerControl() 							{return false;}
   @Override public boolean isEnetOutput() 							{return false;}
   @Override public boolean isEnetInput() 							{return false;}
   @Override public boolean isOutputFacing(byte aSide) 				{return false;}
   @Override public boolean isInputFacing(byte aSide) 				{return false;}
   @Override public boolean isDigitalChest() 						{return isDigitalChest;}
   @Override public boolean isWrenchable()							{return this.mItemCount <= 0;}
   @Override public void setItemCount(int aCount) 					{this.mItemCount = aCount; }

   public int getMaxItemCount() {
      return sDigitalItemCount - 192;
   }

   @Override
   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
      if(tPlayerItem == null) {
         if(mInventory[0] != null) {
            for(int i = 0; this.mItemCount < this.getMaxItemCount() && i < aPlayer.inventory.getSizeInventory(); ++i) {
            	ItemStack itemInSlot = aPlayer.inventory.getStackInSlot(i);
				if (itemInSlot != null && itemInSlot.isItemEqual(getStoredItem()) && !itemInSlot.hasTagCompound()) {
					int tmp = mItemCount;
					mItemCount = Math.min(tmp + itemInSlot.stackSize, getMaxItemCount());
					tmp = mItemCount - tmp;
					if (tmp == itemInSlot.stackSize) {
						aPlayer.inventory.setInventorySlotContents(i, null);
					} else {
						itemInSlot.stackSize -= tmp;
					}
				}
			}

            GT_Utility.sendChatToPlayer(aPlayer, mItemCount + " of " + mInventory[0].getDisplayName());
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
            this.mItemCount = tPlayerItem.stackSize;
            aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
         } else if (tPlayerItem.isItemEqual(mInventory[0]) && !tPlayerItem.hasTagCompound()) {
        	 int tmp = mItemCount;
        	 mItemCount = Math.min(mItemCount + tPlayerItem.stackSize, getMaxItemCount());
        	 tmp = mItemCount - tmp;
        	 if (tmp == tPlayerItem.stackSize) {
        		 aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
        	 } else {
        		 tPlayerItem.stackSize -= tmp;
        	 }
        	 
         } else {
        	 GT_Utility.sendChatToPlayer(aPlayer, this.mItemCount + " of " + mInventory[0].getDisplayName());
         }
      }

      if(aPlayer.inventoryContainer != null) {
         aPlayer.inventoryContainer.detectAndSendChanges();
      }
   }

   @Override
   public void onLeftclick(EntityPlayer aPlayer) {
		if (mInventory[0] != null) {
			if (mItemCount > 0) {
				ItemStack tOutput = mInventory[0].copy();
				tOutput.stackSize = aPlayer.isSneaking() ? 1 : Math.min(tOutput.getMaxStackSize(), mItemCount);
				mItemCount -= tOutput.stackSize;
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
			
			if (mItemCount <= 0 && getBaseMetaTileEntity().isAllowedToWork()) {
				mInventory[0] = null;
			}
		} 
   }

   @Override
   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_DigitalChest();
   }

   @Override
   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.setInteger("mItemCount", this.mItemCount);
   }

   @Override
   public void loadNBTData(NBTTagCompound aNBT) {
      this.mItemCount = aNBT.getInteger("mItemCount");
   }

   @Override
   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   @Override
   public void onPostTick() {
//      if(this.getBaseMetaTileEntity().isServerSide() && this.mItemID != null) {
//         if(this.mItemID == null || this.getItemCount() <= 0 && this.getBaseMetaTileEntity().isAllowedToWork()) {
//            this.mItemID = null;
//            this.mItemMeta = 0;
//            this.mItemCount = 0;
//         }
//
//         if(super.mInventory[1] == null) {
//            super.mInventory[1] = new ItemStack(this.mItemID, 0, this.mItemMeta);
//         } else if(this.mItemCount < this.getMaxItemCount() && super.mInventory[1].getItem() == this.mItemID && super.mInventory[1].getItemDamage() == this.mItemMeta && !super.mInventory[1].hasTagCompound()) {
//            this.mItemCount += super.mInventory[1].stackSize;
//            if(this.mItemCount > this.getMaxItemCount()) {
//               super.mInventory[1].stackSize = this.mItemCount - this.getMaxItemCount();
//               this.mItemCount = this.getMaxItemCount();
//            } else {
//               super.mInventory[1].stackSize = 0;
//            }
//         }
//
//         if(super.mInventory[2] == null) {
//            super.mInventory[2] = new ItemStack(this.mItemID, 0, this.mItemMeta);
//         } else if(this.mItemCount < this.getMaxItemCount() && super.mInventory[2].getItem() == this.mItemID && super.mInventory[2].getItemDamage() == this.mItemMeta && !super.mInventory[2].hasTagCompound()) {
//            this.mItemCount += super.mInventory[2].stackSize;
//            if(this.mItemCount > this.getMaxItemCount()) {
//               super.mInventory[2].stackSize = this.mItemCount - this.getMaxItemCount();
//               this.mItemCount = this.getMaxItemCount();
//            } else {
//               super.mInventory[2].stackSize = 0;
//            }
//         }
//
//         if(this.mItemCount > 0) {
//            if(super.mInventory[0] == null) {
//               super.mInventory[0] = new ItemStack(this.mItemID, 0, this.mItemMeta);
//            }
//
//            if(super.mInventory[0] != null && super.mInventory[0].getItem() == this.mItemID && super.mInventory[0].getItemDamage() == this.mItemMeta && !super.mInventory[0].hasTagCompound()) {
//               while(super.mInventory[0].stackSize < super.mInventory[0].getMaxStackSize() && this.mItemCount > 0) {
//                  --this.mItemCount;
//                  ++super.mInventory[0].stackSize;
//               }
//            }
//         }
//      }

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
	   return InfoBuilder.create()
			   .newKey("metatileentity.GT_Barrel.item", GT_Utility.isStackValid(getStoredItem()) ? getStoredItem() : "materials.Empty")
			   .newKey("metatileentity.GT_Barrel.count", GT_Utility.parseNumberToString(mItemCount), GT_Utility.parseNumberToString(getMaxItemCount()))
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
   public ItemStack[] getStoredItemData() {
		return mInventory;
   }

   @Override
   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   @Override
   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return (aIndex == 1 || aIndex == 2) && mInventory[0] == null || (mInventory[0].isItemEqual(aStack) && !aStack.hasTagCompound());
   }
}
