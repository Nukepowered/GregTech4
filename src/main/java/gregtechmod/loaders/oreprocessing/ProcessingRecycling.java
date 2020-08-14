package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingRecycling implements IOreRecipeRegistrator {

   public ProcessingRecycling() {
      OrePrefixes[] arr$ = OrePrefixes.values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         OrePrefixes tPrefix = arr$[i$];
         if(tPrefix.mIsMaterialBased && tPrefix.mMaterialAmount > 0L && tPrefix != OrePrefixes.ingotHot) {
            tPrefix.add((IOreRecipeRegistrator)this);
         }
      }

   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aPrefix.toString().startsWith("dust") && !aPrefix.toString().startsWith("crushed") && aMaterial != Materials.Blaze) {
         if(aPrefix.mIsContainer) {
            if(aMaterial != Materials.Empty) {
               if(aMaterial == Materials.Mercury && aPrefix != OrePrefixes.cell) {
                  GregTech_API.sRecipeAdder.addCannerRecipe(aStack, (ItemStack)null, GT_Utility.getContainerItem(aStack), GT_OreDictUnificator.get(OrePrefixes.gem, (Object)aMaterial, aPrefix.mMaterialAmount / 3628800L), Math.max(aMaterial.getMass() / 2, 1), 2);
               }

               if(aPrefix != OrePrefixes.cell || aMaterial != Materials.Creosote && aMaterial != Materials.SulfuricAcid && aMaterial != Materials.Mercury && aMaterial != Materials.CoalFuel && aMaterial != Materials.BioFuel && aMaterial != Materials.Water && aMaterial != Materials.Nitrogen && aMaterial != Materials.ConstructionFoam) {
                  GregTech_API.sRecipeAdder.addCannerRecipe(aStack, (ItemStack)null, GT_Utility.getContainerItem(aStack), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, aPrefix.mMaterialAmount / 3628800L), Math.max(aMaterial.getMass() / 2, 1), 2);
               }
            }
         } else {
            GT_RecipeRegistrator.registerBasicReverseMacerating(aStack, aMaterial, aPrefix.mMaterialAmount);
            if(GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L) != null && !aMaterial.contains(SubTag.NO_SMELTING) && aPrefix != OrePrefixes.ingot && aPrefix != OrePrefixes.nugget) {
               GT_RecipeRegistrator.registerBasicReverseSmelting(aStack, aMaterial, aPrefix.mMaterialAmount, true);
            }
         }
      }

   }
}
