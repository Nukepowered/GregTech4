package gregtechmod.common.tileentities.storage;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;

public class GT_MetaTileEntity_QuantumChest extends GT_MetaTileEntity_DigitalChest {

   public static int sQuantumItemCount = 2_000_000_000;


   public GT_MetaTileEntity_QuantumChest(int aID, String aName) {
      super(aID, aName);
      isDigitalChest = false;
   }

   public GT_MetaTileEntity_QuantumChest() {
      isDigitalChest = false;
   }

   public void onConfigLoad(GT_Config aConfig) {
      sQuantumItemCount = Math.max(GT_MetaTileEntity_DigitalChest.sDigitalItemCount, aConfig.get(GT_ConfigCategories.machineconfig, "QuantumChest.MaxItems", sQuantumItemCount));
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_QuantumChest();
   }

   public int getMaxItemCount() {
      return sQuantumItemCount - 192;
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == aFacing?55:(aSide == 0?3:0);
   }

   public String getDescription() {
      return "tile.BlockMetaID_Machine.Quantumchest.tooltip";
   }
}
