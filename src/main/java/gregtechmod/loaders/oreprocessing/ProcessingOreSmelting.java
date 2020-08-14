package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingOreSmelting implements IOreRecipeRegistrator {

   private final OrePrefixes[] mSmeltingPrefixes;


   public ProcessingOreSmelting() {
      this.mSmeltingPrefixes = new OrePrefixes[]{OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.dustRefined};
      OrePrefixes[] arr$ = this.mSmeltingPrefixes;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         OrePrefixes tPrefix = arr$[i$];
         tPrefix.add((IOreRecipeRegistrator)this);
      }

   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aMaterial.contains(SubTag.NO_SMELTING)) {
         if(!aMaterial.mBlastFurnaceRequired && !aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
            switch(ProcessingOreSmelting.NamelessClass1425840077.$SwitchMap$gregtechmod$api$enums$OrePrefixes[aPrefix.ordinal()]) {
            case 1:
            case 2:
            case 3:
               ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)aMaterial.mDirectSmelting, 10L);
               if(tStack == null) {
                  tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial.mDirectSmelting, 1L);
               }

               GT_ModHandler.addSmeltingRecipe(aStack, tStack);
               break;
            default:
               GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial.mDirectSmelting, 1L));
            }
         } else {
            GT_ModHandler.removeFurnaceSmelting(aStack);
            GregTech_API.sRecipeAdder.addBlastRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mBlastFurnaceTemp > 1750?GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), 1L):GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), (ItemStack)null, Math.max(aMaterial.getMass() / 4, 1) * aMaterial.mBlastFurnaceTemp, 120, aMaterial.mBlastFurnaceTemp);
            if(aMaterial.mBlastFurnaceTemp <= 1000) {
               GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), aMaterial.mBlastFurnaceTemp * 2);
            }
         }
      }

   }

   // $FF: synthetic class
   static class NamelessClass1425840077 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$OrePrefixes = new int[OrePrefixes.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushed.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushedPurified.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$OrePrefixes[OrePrefixes.crushedCentrifuged.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
