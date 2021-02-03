package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Extruder extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Extruder(int aID, String aName, List<Recipe> recipeMap) {
      super(aID, aName, recipeMap);
   }

   public GT_MetaTileEntity_Extruder(List<Recipe> recipeMap) {
	   super(recipeMap);
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
   public void initRecipeLogic(List<Recipe> recipeMap) {
	   recipeLogic = new RecipeLogic(recipeMap, this) {
		   @Override
		   protected void moveItems() {
			   GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
		   }
	   };
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(208)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex == 1 && super.allowPutStack(aIndex, aSide, aStack);
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
