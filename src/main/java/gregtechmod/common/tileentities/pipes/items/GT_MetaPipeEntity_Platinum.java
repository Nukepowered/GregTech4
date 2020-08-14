package gregtechmod.common.tileentities.pipes.items;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Platinum extends GT_MetaPipeEntity_Item {

   public GT_MetaPipeEntity_Platinum(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaPipeEntity_Platinum() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Platinum();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return aConnected?385:384;
   }

   public float getThickNess() {
      return 0.5F;
   }

   public int getPipeCapacity() {
      return 4;
   }

   public int getStepSize() {
      return 8192;
   }
}
