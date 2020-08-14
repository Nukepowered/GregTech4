package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Scanner extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Scanner(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_Scanner() {}

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 180);
   }

   public int getElectricTier() {
      return 3;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Scanner();
   }

   public void checkRecipe() {
      GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
      if(super.mInventory[3] != null) {
         super.bOutputBlocked = true;
      } else if(GT_Utility.isStackValid(super.mInventory[1]) && super.mInventory[1].stackSize > 0 && GT_Items.IC2_Crop_Seeds.isStackEqual(super.mInventory[1], true, true)) {
         NBTTagCompound tNBT = super.mInventory[1].getTagCompound();
         if(tNBT == null) {
            tNBT = new NBTTagCompound();
         }

         if(tNBT.getByte("scan") < 4) {
            tNBT.setByte("scan", (byte)4);
            super.mMaxProgresstime = 20;
            super.mEUt = 500;
         } else {
            super.mMaxProgresstime = 1;
            super.mEUt = 1;
         }

         --super.mInventory[1].stackSize;
         super.mOutputItem1 = GT_Utility.copyAmount(1L, new Object[]{super.mInventory[1]});
         super.mOutputItem1.setTagCompound(tNBT);
         return;
      }

      super.mMaxProgresstime = 0;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_Items.IC2_Crop_Seeds.isStackEqual(aStack, true, true);
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(212)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getTopFacingInactive() {
      return 37;
   }

   public int getTopFacingActive() {
      return 37;
   }

   public int getFrontFacingInactive() {
      return 224;
   }

   public int getFrontFacingActive() {
      return 225;
   }

   public String getDescription() {
      return "Scans Crops and other things.";
   }
}
