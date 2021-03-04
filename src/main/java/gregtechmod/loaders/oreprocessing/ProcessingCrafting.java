package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingCrafting implements IOreRecipeRegistrator {

   public ProcessingCrafting() {
      OrePrefixes.crafting.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aOreDictName.equals(GT_OreDictNames.craftingRedstoneTorch.toString())) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Iron, 1L), GregTech_API.getGregTechComponent(30, 1), 800, 16);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Aluminium, 1L), GregTech_API.getGregTechComponent(87, 1), 800, 16);
      } else if(aOreDictName.equals(GT_OreDictNames.craftingRawMachineTier01.toString())) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), new ItemStack(Blocks.noteblock, 4, 32767), new ItemStack(GregTech_API.sBlockList[1], 1, 66), 800, 1);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), new ItemStack(Blocks.stone_button, 16, 32767), new ItemStack(GregTech_API.sBlockList[1], 1, 67), 800, 1);
         if(!GT_Utility.areStacksEqual(new ItemStack(GregTech_API.sBlockList[1], 1, 79), aStack)) {
            GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GregTech_API.getGregTechComponent(22, 1), new ItemStack(GregTech_API.sBlockList[1], 1, 79), 1600, 2);
         }

         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GregTech_API.getGregTechComponent(7, 1), GT_ModHandler.getIC2Item("solarPanel", 1L), 1600, 2);
      } else if(aOreDictName.equals(GT_OreDictNames.craftingGenerator.toString())) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.gearGt, (Object)Materials.Steel, 1L), GregTech_API.getGregTechComponent(25, 1), 3200, 4);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.gearGt, (Object)Materials.StainlessSteel, 1L), GregTech_API.getGregTechComponent(25, 1), 3200, 4);
      } else if(aOreDictName.equals(GT_OreDictNames.craftingWireCopper.toString())) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(2L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.BatteryAlloy, 6L), GT_Items.Battery_Hull_MV.get(1L, new Object[0]), 1600, 2);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.Circuit_Board_Basic.get(1L, new Object[0]), GT_Utility.copyAmount(3L, aStack), GT_Items.Circuit_Basic.get(1L, new Object[0]), 800, 1);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.Circuit_Basic.get(1L, new Object[0]), GT_Utility.copyAmount(1L, aStack), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L), 800, 1);
      } else if(aOreDictName.equals(GT_OreDictNames.craftingWireTin.toString())) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.BatteryAlloy, 2L), GT_Items.Battery_Hull_LV.get(1L, new Object[0]), 800, 1);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.Circuit_Basic.get(1L, new Object[0]), GT_Utility.copyAmount(1L, aStack), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L), 800, 1);
      }

   }
}
