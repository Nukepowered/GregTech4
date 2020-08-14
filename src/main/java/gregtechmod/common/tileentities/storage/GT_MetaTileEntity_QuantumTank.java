package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_QuantumTank extends GT_MetaTileEntity_BasicTank {

   public GT_MetaTileEntity_QuantumTank(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_QuantumTank() {}

   public boolean unbreakable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex < 2;
   }

   public boolean isFacingValid(byte aFacing) {
      return false;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 114);
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_QuantumTank();
   }

   public boolean doesFillContainers() {
      return true;
   }

   public boolean doesEmptyContainers() {
      return true;
   }

   public boolean canTankBeFilled() {
      return true;
   }

   public boolean canTankBeEmptied() {
      return true;
   }

   public boolean displaysItemStack() {
      return true;
   }

   public boolean displaysStackSize() {
      return false;
   }

   public boolean isFluidChangingAllowed() {
      return this.getBaseMetaTileEntity().isAllowedToWork();
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?38:(aSide == 1?37:36);
   }

   public String getDescription() {
      return "With a capacity of 488.28125 Chunks!";
   }

   public int getCapacity() {
      return 2000000000;
   }

   public int getTankPressure() {
      return 100;
   }
}
