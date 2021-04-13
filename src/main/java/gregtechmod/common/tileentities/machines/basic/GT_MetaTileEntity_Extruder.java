package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Extruder extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Extruder(int aID, String aName, RecipeMap<?> recipeMap) {
      super(aID, aName, recipeMap);
      loopLen = 130;
   }

   public GT_MetaTileEntity_Extruder(RecipeMap<?> recipeMap) {
	   super(recipeMap);
	   loopLen = 130;
   }

   public int getElectricTier() {
      return 2;
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 181);
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Extruder(recipeLogic.recipeMap);
   }

   @Override
   public void doSound(byte aIndex, double aX, double aY, double aZ) {
	   super.doSound(aIndex, aX, aY, aZ);
	   if(aIndex == 1) {
		   GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(208)), 10, 1.0F, aX, aY, aZ);
	   }
		   
   }

   @Override
   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 0 && super.allowPutStack(aIndex, aSide, aStack);
   }

   public int getTopFacingInactive() {
      return 405;
   }

   public int getTopFacingActive() {
      return 406;
   }

   public int getSideFacingInactive() {
      return 242;
   }

   public int getSideFacingActive() {
      return 226;
   }

   public int getFrontFacingInactive() {
      return 407;
   }

   public int getFrontFacingActive() {
      return 408;
   }

   public String getDescription() {
      return "metatileentity.GT_Extruder.tooltip";
   }
}
