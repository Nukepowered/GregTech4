package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Safe extends MetaTileEntity {
	
	public int success = 0;
	
	public GT_MetaTileEntity_Safe(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Safe() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public int getInvSize()								{return 29;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 28;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 128);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean ownerControl()							{return true;}
	@Override public boolean isEnetOutput()							{return false;}
	@Override public boolean isEnetInput()							{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Safe();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide()) {
			if (mInventory[0] != null && (success-->0 || getBaseMetaTileEntity().getTimer() % 200 == 20)) {
				for (int i = 1; mInventory[0] != null && i < getInvSize()-1; i++) {
					if (mInventory[i] != null && GT_Utility.areStacksEqual(mInventory[i], mInventory[0])) {
						success = GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 0, i, (byte)64, (byte)1, (byte)64, (byte)1);
					}
				}
				for (int i = 1; mInventory[0] != null && i < getInvSize()-1; i++) {
					if (mInventory[i] == null) {
						success = GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 0, i, (byte)64, (byte)1, (byte)64, (byte)1);
					}
				}
			}
			if (mInventory[28] == null) {
				if (mInventory[0] != null && mInventory[0].stackSize < 1) {
					mInventory[0] = null;
				}
			} else {
				mInventory[28].stackSize = 0;
				if (mInventory[0] == null || mInventory[0].stackSize < 1) {
					mInventory[0] = GT_Utility.copy(mInventory[28]);
				}
			}
		}
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==0 || mInventory[0] == null || GT_Utility.areStacksEqual(mInventory[0], aStack);
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0)
    		return 32;
    	else if (aSide == 1)
    		return 29;
    	else
    		return aSide==aFacing?214:40;
	}
	
	@Override
	public String getDescription() {
		return "This is completly 'Safe', except against Explosions";
	}
}
