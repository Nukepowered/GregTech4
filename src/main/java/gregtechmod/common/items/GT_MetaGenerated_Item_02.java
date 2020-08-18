package gregtechmod.common.items;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import java.util.Arrays;
import java.util.List;

public class GT_MetaGenerated_Item_02 extends GT_MetaGenerated_Item {

   private static final List<Materials> sTempToolHeadMaterials = Arrays.asList(new Materials[]{
		   Materials.Wood,
		   Materials.Stone,
		   Materials.Iron,
		   Materials.Gold,
		   Materials.Diamond,
		   Materials.Bronze,
		   Materials.Steel,
		   Materials.TungstenSteel,
		   Materials.Rubber,
		   Materials.Plastic
   });


   public GT_MetaGenerated_Item_02() {
      super("GregTech_MetaGenerated_Item_02", new OrePrefixes[]{OrePrefixes.toolHeadSword, OrePrefixes.toolHeadPickaxe, OrePrefixes.toolHeadShovel, OrePrefixes.toolHeadAxe, OrePrefixes.toolHeadHoe, OrePrefixes.toolHeadHammer, OrePrefixes.toolHeadFile, OrePrefixes.toolHeadSaw, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, OrePrefixes.gearGt});
   }

   public IIconContainer getIconContainer(int aMetaData, Materials aMaterial) {
      return aMaterial.mIconSet[32 + aMetaData / 1000];
   }

   public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
      if(!super.doesMaterialAllowGeneration(aPrefix, aMaterial)) {
         return false;
      } else {
         switch(aPrefix) {
         case toolHeadSword:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadPickaxe:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadShovel:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadAxe:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadHoe:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadHammer:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadFile:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case toolHeadSaw:
            return (aMaterial.mTypes & 64) != 0 && sTempToolHeadMaterials.contains(aMaterial);
         case gearGt:
            return (aMaterial.mTypes & 128) != 0;
         default:
            return false;
         }
      }
   }
}
