package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingCrushed implements IOreRecipeRegistrator {

   public ProcessingCrushed() {
      OrePrefixes.crushed.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(0, aMaterial, aMaterial.mOreByProducts), 1L), 10, false);
      GT_ModHandler.addOreWasherRecipe(GT_Utility.copyAmount(1L, aStack), 1000, new Object[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(0, aMaterial, aMaterial.mOreByProducts), 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Stone, 1L)});
      if(!aMaterial.contains(SubTag.NO_SMELTING)) {
         GT_ModHandler.addThermalCentrifugeRecipe(GT_Utility.copyAmount(1L, aStack), Math.min(5000, Math.abs(aMaterial.getMass() * 20)), new Object[]{GT_OreDictUnificator.get(OrePrefixes.crushedCentrifuged, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, GT_Utility.selectItemInList(1, aMaterial, aMaterial.mOreByProducts), 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Stone, 1L)});
      }

   }
}
