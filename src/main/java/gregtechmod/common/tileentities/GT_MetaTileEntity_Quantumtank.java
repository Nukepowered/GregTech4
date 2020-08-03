package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Quantumtank extends GT_MetaTileEntity_BasicTank {
	
	public GT_MetaTileEntity_Quantumtank(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Quantumtank() {
		
	}
	
	@Override public boolean unbreakable()							{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 114);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Quantumtank();
	}
	
	@Override public boolean doesFillContainers()	{return true;}
	@Override public boolean doesEmptyContainers()	{return true;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return false;}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0)
    		return 38;
    	else if (aSide == 1)
    		return 37;
    	else
    		return 36;
	}
	
	@Override
	public String getDescription() {
		return "With a capacity of 488.28125 Chunks!";
	}
	
	@Override
	public int getCapacity() {
		return 2000000000;
	}
	
	@Override
	public int getTankPressure() {
		return 100;
	}
}
