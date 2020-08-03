package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class GT_Cover_ItemValve extends GT_Cover_Valve {
	
	public GT_Cover_ItemValve(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aCoverVariable % 6 > 1 && aTileEntity instanceof IMachineProgress && (((IMachineProgress)aTileEntity).isAllowedToWork() != (aCoverVariable % 6 < 4))) return aCoverVariable;
		if (!(aCoverVariable%2==1&&aSide==1) && !(aCoverVariable%2==0&&aSide==0) && aTileEntity.getUniversalEnergyCapacity() >= 128) {
			if (aTileEntity.getUniversalEnergyStored() >= 128) {
				aTileEntity.decreaseStoredEnergyUnits(GT_Utility.moveOneItemStack(aCoverVariable%2==0?aTileEntity:aTileEntity.getIInventoryAtSide(aSide), aCoverVariable%2!=0?aTileEntity:aTileEntity.getIInventoryAtSide(aSide), (byte)(aCoverVariable%2!=0?GT_Utility.getOppositeSide(aSide):aSide), (byte)(aCoverVariable%2==0?GT_Utility.getOppositeSide(aSide):aSide), null, false, (byte)64, (byte)1, (byte)64, (byte)1), true);
			}
		} else {
			GT_Utility.moveOneItemStack(aCoverVariable%2==0?aTileEntity:aTileEntity.getIInventoryAtSide(aSide), aCoverVariable%2!=0?aTileEntity:aTileEntity.getIInventoryAtSide(aSide), aSide, GT_Utility.getOppositeSide(aSide), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
		}
		return super.doCoverThings(aSide, aInputRedstone, aCoverID, aCoverVariable, aTileEntity);
	}
	
	@Override
	public boolean letsItemsIn(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return aCoverVariable>=6||aCoverVariable%2!=0;
	}
	
	@Override
	public boolean letsItemsOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return aCoverVariable>=6||aCoverVariable%2==0;
	}
	
	@Override
	public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 2;
	}
}