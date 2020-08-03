package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_DoesWork extends GT_CoverBehavior {
	
	public GT_Cover_DoesWork(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aTileEntity instanceof IMachineProgress) {
			if (aCoverVariable < 2) {
				int tScale = ((IMachineProgress)aTileEntity).getMaxProgress()/15;
				if (tScale > 0 && ((IMachineProgress)aTileEntity).hasThingsToDo()) {
					aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(((IMachineProgress)aTileEntity).getProgress()/tScale):(byte)(15-((IMachineProgress)aTileEntity).getProgress()/tScale));
				} else {
					aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
				}
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, ((aCoverVariable%2==0)!=(((IMachineProgress)aTileEntity).getMaxProgress()==0))?(byte)0:15);
			}
		} else {
			aTileEntity.setOutputRedstoneSignal(aSide, (byte)0);
		}
		return aCoverVariable;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable=(aCoverVariable+1)%4;
		if (aCoverVariable == 0) GT_Utility.sendChatToPlayer(aPlayer, "Normal");
		if (aCoverVariable == 1) GT_Utility.sendChatToPlayer(aPlayer, "Inverted");
		if (aCoverVariable == 2) GT_Utility.sendChatToPlayer(aPlayer, "Ready to work");
		if (aCoverVariable == 3) GT_Utility.sendChatToPlayer(aPlayer, "Not ready to work");
		return aCoverVariable;
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
