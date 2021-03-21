package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Scanner extends GT_MetaTileEntity_BasicMachine {

   public GT_MetaTileEntity_Scanner(int aID, String aName, RecipeMap<?> recipeMap) {
      super(aID, aName, recipeMap);
   }

   public GT_MetaTileEntity_Scanner(RecipeMap<?> recipeMap) {
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
   
   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return super.allowPutStack(aIndex, aSide, aStack) && GT_Items.IC2_Crop_Seeds.isStackEqual(aStack, true, true);
   }
   
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(212), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
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
