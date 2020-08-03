package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class GT_Cover_Vent extends GT_CoverBehavior {
	
	private final int mEfficiency;
	
	public GT_Cover_Vent(ItemStack[] aCovers, int aEfficiency) {
		super(aCovers);
		mEfficiency = aEfficiency;
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aTileEntity instanceof IMachineProgress) {
			if (((IMachineProgress)aTileEntity).hasThingsToDo() && aCoverVariable != ((IMachineProgress)aTileEntity).getProgress()) {
				if (!GT_Utility.hasBlockHitBox(aTileEntity.getWorld(), aTileEntity.getOffsetX(aSide, 1), aTileEntity.getOffsetY(aSide, 1), aTileEntity.getOffsetZ(aSide, 1))) {
					((IMachineProgress)aTileEntity).increaseProgress(mEfficiency);
				}
			}
			return ((IMachineProgress)aTileEntity).getProgress();
		}
		return 0;
	}
	
	@Override
	public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 60;
	}
}