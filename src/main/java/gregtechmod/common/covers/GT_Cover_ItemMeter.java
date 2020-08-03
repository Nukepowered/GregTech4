package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_ItemMeter extends GT_CoverBehavior {
	
	public GT_Cover_ItemMeter(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		int[] tSlots = aTileEntity.getAccessibleSlotsFromSide(aSide);
		int tAll = 0, tFull = 0;
		for (int i : tSlots) {
			tAll+=64;
			ItemStack tStack = aTileEntity.getStackInSlot(i);
			if (tStack != null) {
				tFull += (tStack.stackSize*64) / tStack.getMaxStackSize();
			}
		}
		tAll/=15;
		if (tAll > 0) {
			aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable==0?(byte)(tFull/tAll):(byte)(15-tFull/tAll));
		} else {
			aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable==0?(byte)0:15);
		}
		return aCoverVariable;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aCoverVariable==0)
			GT_Utility.sendChatToPlayer(aPlayer, "Inverted");
		else
			GT_Utility.sendChatToPlayer(aPlayer, "Normal");
		return aCoverVariable==0?1:0;
	}
	
	@Override
	public boolean letsEnergyIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsEnergyOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsLiquidIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsLiquidOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean manipulatesSidedRedstoneOutput(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 5;
	}
}
