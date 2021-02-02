package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Scanner extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Scanner(int aID, String aName, List<Recipe> recipeMap) {
      super(aID, aName, recipeMap);
   }

   public GT_MetaTileEntity_Scanner(List<Recipe> recipeMap) {
	   super(recipeMap);
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 180);
   }

   public int getElectricTier() {
      return 3;
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_Scanner(recipeLogic.recipeMap);
   }
   
   @Override
   public void initRecipeLogic(List<Recipe> recipeMap) {
	   recipeLogic = new RecipeLogic(recipeMap, this) {
		   @Override
		   protected void moveItems() {
			   GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
		   }
		   
		   @Override
		   protected Recipe findRecipe() {
			   if (GT_Utility.isStackValid(mInventory[1]) && GT_Items.IC2_Crop_Seeds.isStackEqual(mInventory[1], true, true)) {
				   NBTTagCompound data = mInventory[1].getTagCompound();
				   if (data == null) return null; // May not work
				   int dur = 0, eut = 0;
				   if (data.getByte("scan") < 4) {
					   data.setByte("scan", (byte)4);
					   dur = 20;
					   eut = 500;
				   } else {
					   dur = 1;
					   eut = 1;
				   }
				   
				   ItemStack output = mInventory[1].copy();
				   output.setTagCompound(data);
				   return new Recipe(mInventory[1].copy(), null, output, null, null, null, dur, eut, 0, false);
			   }
			   
			   return null;
		   }
	   };
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_Items.IC2_Crop_Seeds.isStackEqual(aStack, true, true);
   }

   public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
      super.startSoundLoop(aIndex, aX, aY, aZ);
      if(aIndex == 1) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(212)), 10, 1.0F, aX, aY, aZ);
      }

   }

   public void startProcess() {
      this.sendLoopStart((byte)1);
   }

   public boolean hasTwoSeperateInputs() {
      return true;
   }

   public int getTopFacingInactive() {
      return 37;
   }

   public int getTopFacingActive() {
      return 37;
   }

   public int getFrontFacingInactive() {
      return 224;
   }

   public int getFrontFacingActive() {
      return 225;
   }

   public String getDescription() {
      return "metatileentity.GT_Scanner.tooltip";
   }
}
