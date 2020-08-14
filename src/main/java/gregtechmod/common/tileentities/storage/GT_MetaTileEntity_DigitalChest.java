package gregtechmod.common.tileentities.storage;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_DigitalChest extends MetaTileEntity {

   public int mItemCount = 0;
   public int mItemID = 0;
   public int mItemMeta = 0;
   public boolean isDigitalChest = true;
   public static int sDigitalItemCount = '\u8000';


   public GT_MetaTileEntity_DigitalChest(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_DigitalChest() {}

   public void onConfigLoad(GT_Config aConfig) {
      sDigitalItemCount = Math.max(1024, aConfig.get(GT_ConfigCategories.machineconfig, "DigitalChest.MaxItems", sDigitalItemCount));
   }

   public boolean unbreakable() {
      return true;
   }

   public boolean isWrenchable() {
      return this.mItemCount <= 0;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public int getInvSize() {
      return 3;
   }

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean ownerControl() {
      return false;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return false;
   }

   public int getProgresstime() {
      return this.mItemCount + (super.mInventory[0] == null?0:super.mInventory[0].stackSize) + (super.mInventory[1] == null?0:super.mInventory[1].stackSize) + (super.mInventory[2] == null?0:super.mInventory[2].stackSize);
   }

   public int maxProgresstime() {
      return this.getMaxItemCount();
   }

   public int getMaxItemCount() {
      return sDigitalItemCount - 192;
   }

   public void setItemCount(int aCount) {
      this.mItemCount = aCount;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
      if(tPlayerItem == null) {
         if(this.mItemID > 0) {
            for(int tNBT = 0; this.mItemCount < this.getMaxItemCount() && tNBT < aPlayer.inventory.getSizeInventory(); ++tNBT) {
               if(aPlayer.inventory.getStackInSlot(tNBT) != null && aPlayer.inventory.getStackInSlot(tNBT).itemID == this.mItemID && aPlayer.inventory.getStackInSlot(tNBT).getItemDamage() == this.mItemMeta && !aPlayer.inventory.getStackInSlot(tNBT).hasTagCompound()) {
                  this.mItemCount += aPlayer.inventory.getStackInSlot(tNBT).stackSize;
                  if(aPlayer.inventory.getStackInSlot(tNBT).stackSize == 111) {
                     this.mItemCount = this.getMaxItemCount() + 192 - (this.mItemCount + (super.mInventory[0] == null?0:super.mInventory[0].stackSize) + (super.mInventory[1] == null?0:super.mInventory[1].stackSize) + (super.mInventory[2] == null?0:super.mInventory[2].stackSize));
                  } else if(this.mItemCount > this.getMaxItemCount()) {
                     aPlayer.inventory.getStackInSlot(tNBT).stackSize = this.mItemCount - this.getMaxItemCount();
                     this.mItemCount = this.getMaxItemCount();
                  } else {
                     aPlayer.inventory.getStackInSlot(tNBT).stackSize = 0;
                  }
               }

               if(aPlayer.inventory.getStackInSlot(tNBT) != null && aPlayer.inventory.getStackInSlot(tNBT).stackSize <= 0) {
                  aPlayer.inventory.setInventorySlotContents(tNBT, (ItemStack)null);
               }
            }

            GT_Utility.sendChatToPlayer(aPlayer, this.mItemCount + (super.mInventory[0] == null?0:super.mInventory[0].stackSize) + (super.mInventory[1] == null?0:super.mInventory[1].stackSize) + (super.mInventory[2] == null?0:super.mInventory[2].stackSize) + " of " + (new ItemStack(this.mItemID, 1, this.mItemMeta)).getDisplayName());
         }
      } else {
         if(this.isDigitalChest && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingQuantumChestUpgrade")) {
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

         if(this.mItemID <= 0) {
            this.mItemID = tPlayerItem.itemID;
            this.mItemMeta = tPlayerItem.getItemDamage();
            this.mItemCount = 0;
         }

         if(this.mItemID > 0) {
            if(this.mItemCount < this.getMaxItemCount() && tPlayerItem.itemID == this.mItemID && tPlayerItem.getItemDamage() == this.mItemMeta && !tPlayerItem.hasTagCompound()) {
               this.mItemCount += tPlayerItem.stackSize;
               if(tPlayerItem.stackSize == 111) {
                  this.mItemCount = this.getMaxItemCount();
               } else if(this.mItemCount > this.getMaxItemCount()) {
                  tPlayerItem.stackSize = this.mItemCount - this.getMaxItemCount();
                  this.mItemCount = this.getMaxItemCount();
               } else {
                  tPlayerItem.stackSize = 0;
               }
            } else {
               GT_Utility.sendChatToPlayer(aPlayer, this.mItemCount + (super.mInventory[0] == null?0:super.mInventory[0].stackSize) + (super.mInventory[1] == null?0:super.mInventory[1].stackSize) + (super.mInventory[2] == null?0:super.mInventory[2].stackSize) + " of " + (new ItemStack(this.mItemID, 1, this.mItemMeta)).getDisplayName());
            }
         }
      }

      if(aPlayer.inventoryContainer != null) {
         aPlayer.inventoryContainer.detectAndSendChanges();
      }

   }

   public void onLeftclick(EntityPlayer aPlayer) {
      if(super.mInventory[0] != null && super.mInventory[0].stackSize > 0) {
         ItemStack tOutput = GT_Utility.copy(new Object[]{super.mInventory[0]});
         if(aPlayer.func_70093_af()) {
            tOutput.stackSize = 1;
         }

         this.getBaseMetaTileEntity().func_70298_a(0, tOutput.stackSize);
         EntityItem tEntity = new EntityItem(this.getBaseMetaTileEntity().getWorld(), (double)this.getBaseMetaTileEntity().getOffsetX(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetY(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, (double)this.getBaseMetaTileEntity().getOffsetZ(this.getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5D, tOutput);
         tEntity.field_70159_w = 0.0D;
         tEntity.field_70181_x = 0.0D;
         tEntity.field_70179_y = 0.0D;
         this.getBaseMetaTileEntity().getWorld().spawnEntityInWorld(tEntity);
      }

   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_DigitalChest();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.setInteger("mItemCount", this.mItemCount);
      aNBT.setInteger("mItemID", this.mItemID);
      aNBT.setInteger("mItemMeta", this.mItemMeta);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mItemCount = aNBT.getInteger("mItemCount");
      this.mItemID = aNBT.getInteger("mItemID");
      this.mItemMeta = aNBT.getInteger("mItemMeta");
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.mItemID != 0) {
         if(Item.itemsList[this.mItemID] == null || this.getItemCount() <= 0 && this.getBaseMetaTileEntity().isAllowedToWork()) {
            this.mItemID = 0;
            this.mItemMeta = 0;
            this.mItemCount = 0;
         }

         if(super.mInventory[1] == null) {
            super.mInventory[1] = new ItemStack(this.mItemID, 0, this.mItemMeta);
         } else if(this.mItemCount < this.getMaxItemCount() && super.mInventory[1].itemID == this.mItemID && super.mInventory[1].getItemDamage() == this.mItemMeta && !super.mInventory[1].hasTagCompound()) {
            this.mItemCount += super.mInventory[1].stackSize;
            if(this.mItemCount > this.getMaxItemCount()) {
               super.mInventory[1].stackSize = this.mItemCount - this.getMaxItemCount();
               this.mItemCount = this.getMaxItemCount();
            } else {
               super.mInventory[1].stackSize = 0;
            }
         }

         if(super.mInventory[2] == null) {
            super.mInventory[2] = new ItemStack(this.mItemID, 0, this.mItemMeta);
         } else if(this.mItemCount < this.getMaxItemCount() && super.mInventory[2].itemID == this.mItemID && super.mInventory[2].getItemDamage() == this.mItemMeta && !super.mInventory[2].hasTagCompound()) {
            this.mItemCount += super.mInventory[2].stackSize;
            if(this.mItemCount > this.getMaxItemCount()) {
               super.mInventory[2].stackSize = this.mItemCount - this.getMaxItemCount();
               this.mItemCount = this.getMaxItemCount();
            } else {
               super.mInventory[2].stackSize = 0;
            }
         }

         if(this.mItemCount > 0) {
            if(super.mInventory[0] == null) {
               super.mInventory[0] = new ItemStack(this.mItemID, 0, this.mItemMeta);
            }

            if(super.mInventory[0] != null && super.mInventory[0].itemID == this.mItemID && super.mInventory[0].getItemDamage() == this.mItemMeta && !super.mInventory[0].hasTagCompound()) {
               while(super.mInventory[0].stackSize < super.mInventory[0].getMaxStackSize() && this.mItemCount > 0) {
                  --this.mItemCount;
                  ++super.mInventory[0].stackSize;
               }
            }
         }
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?37:(aSide == 0?32:(aSide == 1?29:40));
   }

   private ItemStack getStoredItem() {
      return this.mItemID == 0?null:new ItemStack(this.mItemID, 1, this.mItemMeta);
   }

   private int getItemCount() {
      return this.mItemCount + (super.mInventory[0] == null?0:super.mInventory[0].stackSize) + (super.mInventory[1] == null?0:super.mInventory[1].stackSize) + (super.mInventory[2] == null?0:super.mInventory[2].stackSize);
   }

   public String[] getInfoData() {
      return this.getStoredItem() == null?new String[]{"", "", "Max: " + (this.getMaxItemCount() + 192)}:new String[]{GT_LanguageManager.getTranslateableItemStackName(this.getStoredItem()), "" + this.getItemCount(), "Max: " + (this.getMaxItemCount() + 192)};
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "To lock the saved Item as Filter, use Rubber Hammer on it.";
   }

   public boolean isDigitalChest() {
      return true;
   }

   public ItemStack[] getStoredItemData() {
      return new ItemStack[]{new ItemStack(this.mItemID, this.mItemCount, this.mItemMeta)};
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return (aIndex == 1 || aIndex == 2) && aStack.itemID == this.mItemID && aStack.getItemDamage() == this.mItemMeta && !aStack.hasTagCompound();
   }

}
