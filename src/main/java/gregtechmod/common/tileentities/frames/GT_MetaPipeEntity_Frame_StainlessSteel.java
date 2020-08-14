package gregtechmod.common.tileentities.frames;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Frame;

public class GT_MetaPipeEntity_Frame_StainlessSteel extends GT_MetaPipeEntity_Frame {

   public GT_MetaPipeEntity_Frame_StainlessSteel(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaPipeEntity_Frame_StainlessSteel() {}

   public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaPipeEntity_Frame_StainlessSteel();
   }

   public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
      return 389;
   }
}
