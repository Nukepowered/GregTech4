package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Multi_AutoCrafter extends GT_MetaTileEntity_MultiBlockBase {

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public void onRightclick(EntityPlayer aPlayer) {}

   public GT_MetaTileEntity_Multi_AutoCrafter(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_Multi_AutoCrafter() {}

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Multi_AutoCrafter();
   }

   public boolean isCorrectMachinePart(ItemStack aStack) {
      return true;
   }

   public int getDamageToComponent(ItemStack aStack) {
      return 0;
   }

   public boolean checkRecipe(ItemStack aStack) {
      return false;
   }

   public boolean onRunningTick(ItemStack aStack) {
      return true;
   }

   public boolean checkMachine(ItemStack aStack) {
      byte tSide = this.getBaseMetaTileEntity().getBackFacing();
      if(!this.getBaseMetaTileEntity().getAirAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 1)) {
         return false;
      } else if((this.getBaseMetaTileEntity().getBlockAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaIDAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2) != 15) && !this.addToMachineList(this.getBaseMetaTileEntity().getIGregTechTileEntityAtSideAndDistance(this.getBaseMetaTileEntity().getBackFacing(), 2))) {
         return false;
      } else {
         int tX = this.getBaseMetaTileEntity().getXCoord();
         short tY = this.getBaseMetaTileEntity().getYCoord();
         int tZ = this.getBaseMetaTileEntity().getZCoord();

         for(byte i = -1; i < 2; ++i) {
            for(byte j = -1; j < 2; ++j) {
               if(i != 0 || j != 0) {
                  for(byte k = 0; k < 5; ++k) {
                     if((i == 0 || j == 0) && k > 0 && k < 4) {
                        if((this.getBaseMetaTileEntity().getBlock(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 15) && !this.addToMachineList(this.getBaseMetaTileEntity().getIGregTechTileEntity(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)))) {
                           return false;
                        }
                     } else if(this.getBaseMetaTileEntity().getBlock(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != GregTech_API.sBlockList[0] || this.getBaseMetaTileEntity().getMetaID(tX + (tSide < 4?i:(tSide == 5?k:-k)), tY + j, tZ + (tSide < 4?(tSide == 3?k:-k):i)) != 15) {
                        return false;
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?84:83):super.getTextureIndex(aSide, aFacing, aActive, aRedstone);
   }

   public boolean explodesOnComponentBreak(ItemStack aStack) {
      return false;
   }

   public int getMaxEfficiency(ItemStack aStack) {
      return 10000;
   }

   public int getPollutionPerTick(ItemStack aStack) {
      return 0;
   }

   public int getAmountOfOutputs() {
      return 1;
   }

   public String getDescription() {
      return "Highly Advanced Autocrafter";
   }
}
