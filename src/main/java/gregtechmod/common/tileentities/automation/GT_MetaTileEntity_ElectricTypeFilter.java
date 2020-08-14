package gregtechmod.common.tileentities.automation;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricTypeFilter extends GT_MetaTileEntity_ElectricBufferSmall {

   public OrePrefixes mPrefix;
   public int mRotationIndex;
   public boolean bInvertFilter;
   public boolean bNBTAllowed;


   public GT_MetaTileEntity_ElectricTypeFilter(int aID, String aName) {
      super(aID, aName);
      this.mPrefix = OrePrefixes.ore;
      this.mRotationIndex = 0;
      this.bInvertFilter = false;
      this.bNBTAllowed = false;
   }

   public GT_MetaTileEntity_ElectricTypeFilter() {
      this.mPrefix = OrePrefixes.ore;
      this.mRotationIndex = 0;
      this.bInvertFilter = false;
      this.bNBTAllowed = false;
   }

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 9;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 11;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 174);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricTypeFilter();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.setString("mPrefix", this.mPrefix.toString());
      aNBT.setBoolean("bInvertFilter", this.bInvertFilter);
      aNBT.setBoolean("bNBTAllowed", this.bNBTAllowed);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.mPrefix = OrePrefixes.getPrefix(aNBT.getString("mPrefix"), this.mPrefix);
      this.bInvertFilter = aNBT.getBoolean("bInvertFilter");
      this.bNBTAllowed = aNBT.getBoolean("bNBTAllowed");
   }

   public void clickTypeIcon(boolean aRightClick) {
      if(this.getBaseMetaTileEntity().isServerSide()) {
         label37:
         for(int i = 0; i < OrePrefixes.values().length; ++i) {
            if(this.mPrefix == OrePrefixes.values()[i]) {
               this.mPrefix = null;

               while(true) {
                  if(this.mPrefix != null) {
                     break label37;
                  }

                  if(aRightClick) {
                     --i;
                     if(i < 0) {
                        i = OrePrefixes.values().length - 1;
                     }
                  } else {
                     ++i;
                     if(i >= OrePrefixes.values().length) {
                        i = 0;
                     }
                  }

                  if(!OrePrefixes.values()[i].mPrefixedItems.isEmpty() && OrePrefixes.values()[i].mPrefixInto == OrePrefixes.values()[i]) {
                     this.mPrefix = OrePrefixes.values()[i];
                  }
               }
            }
         }

         this.mRotationIndex = 0;
      }

   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 8L == 0L) {
         if(this.mPrefix.mPrefixedItems.isEmpty()) {
            super.mInventory[9] = null;
         } else {
            super.mInventory[9] = GT_Utility.copyAmount(1L, new Object[]{this.mPrefix.mPrefixedItems.get(this.mRotationIndex = (this.mRotationIndex + 1) % this.mPrefix.mPrefixedItems.size())});
            super.mInventory[9].setItemName(this.mPrefix.toString());
         }
      }

   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing() && (this.bNBTAllowed || !aStack.hasTagCompound()) && this.mPrefix.contains(aStack) != this.bInvertFilter;
   }

   public String getDescription() {
      return "A Buffer, which uses OrePrefixes to filter";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 390 + (aRedstone?5:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 390 + (aRedstone?5:0);
         switch(aFacing) {
         case 0:
            return tIndex + 4;
         case 1:
            return tIndex + 2;
         case 2:
            switch(aSide) {
            case 0:
               return tIndex + 2;
            case 1:
               return tIndex + 2;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 1;
            case 5:
               return tIndex + 3;
            }
         case 3:
            switch(aSide) {
            case 0:
               return tIndex + 4;
            case 1:
               return tIndex + 4;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 3;
            case 5:
               return tIndex + 1;
            }
         case 4:
            switch(aSide) {
            case 0:
               return tIndex + 3;
            case 1:
               return tIndex + 1;
            case 2:
               return tIndex + 3;
            case 3:
               return tIndex + 1;
            }
         case 5:
            switch(aSide) {
            case 0:
               return tIndex + 1;
            case 1:
               return tIndex + 3;
            case 2:
               return tIndex + 1;
            case 3:
               return tIndex + 3;
            }
         default:
            return tIndex;
         }
      }
   }
}
