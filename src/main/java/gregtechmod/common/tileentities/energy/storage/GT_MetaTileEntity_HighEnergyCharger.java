package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_ChargerBox;

public class GT_MetaTileEntity_HighEnergyCharger extends GT_MetaTileEntity_ChargerBox {

   public GT_MetaTileEntity_HighEnergyCharger(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_HighEnergyCharger() {}

   public boolean isTransformerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return false;
   }

   public boolean isSimpleMachine() {
      return false;
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public int maxEUInput() {
      return 131072;
   }

   public int maxEUOutput() {
      return 131072;
   }

   public int maxEUStore() {
      return 2097152;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_HighEnergyCharger();
   }

   public void onPreTick() {
      super.onPreTick();
      if(this.getBaseMetaTileEntity().isServerSide()) {
         this.getBaseMetaTileEntity().setActive(GT_ModHandler.isElectricItem(super.mInventory[0], (byte)7) && GT_ModHandler.canUseElectricItem(super.mInventory[0], 1));
      }

   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?(aActive?412:411):(aSide == GT_Utility.getOppositeSide(aFacing)?103:(aSide < 2?409:410));
   }

   public String getDescription() {
      return "(De-)Charges at Tier 7 (Output at the Back)";
   }
}
