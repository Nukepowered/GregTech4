package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricFilter extends GT_MetaTileEntity_ElectricBufferSmall {

   public boolean bInvertFilter = false;
   public boolean bIgnoreNBT = false;


   public GT_MetaTileEntity_ElectricFilter(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_ElectricFilter() {}

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

   public int maxRFStore() {
      return this.maxEUStore();
   }

   public int getInvSize() {
      return 19;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 173);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricFilter();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      super.saveNBTData(aNBT);
      aNBT.setBoolean("bInvertFilter", this.bInvertFilter);
      aNBT.setBoolean("bIgnoreNBT", this.bIgnoreNBT);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      super.loadNBTData(aNBT);
      this.bInvertFilter = aNBT.getBoolean("bInvertFilter");
      this.bIgnoreNBT = aNBT.getBoolean("bIgnoreNBT");
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      if(aSide == this.getBaseMetaTileEntity().getBackFacing()) {
         return false;
      } else if(this.bInvertFilter) {
         for(byte i = 9; i < 18; ++i) {
            if(GT_Utility.areStacksEqual(super.mInventory[i], aStack, this.bIgnoreNBT)) {
               return false;
            }
         }

         return true;
      } else {
         return GT_Utility.areStacksEqual(super.mInventory[aIndex + 9], aStack, this.bIgnoreNBT);
      }
   }

   public String getDescription() {
      return "A Filter, which also buffers Items!"; // TODO locale
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
