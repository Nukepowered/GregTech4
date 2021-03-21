package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Pulverizer extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Pulverizer(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
		loopLen = 290;
	}
	
	public GT_MetaTileEntity_Pulverizer(RecipeMap<?> recipeMap) {
		super(recipeMap);
		loopLen = 290;
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 149);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Pulverizer(recipeLogic.recipeMap);
	}
    
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(201), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
	}
	
	@Override
    public List<ItemStack> getInputItems() {
    	return new ListAdapter<>(mInventory, 1, 2);
    }
	
	@Override
	public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 3, 6);
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aSide!=mMainFacing?aIndex>=3||aIndex<8:false;
	}
	
	@Override
	public int getInvSize() {
		return 8;
	}
	
	@Override
	public int dechargerSlotStartIndex() {
		return 7;
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 256;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 257;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 228;
	}
	
	@Override
	public int getTopFacingActive() {
		return 229;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Pulverizer.tooltip";
	}
}