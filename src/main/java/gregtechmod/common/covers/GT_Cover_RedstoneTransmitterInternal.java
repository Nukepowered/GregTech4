package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneTransmitterInternal extends GT_Cover_RedstoneWirelessBase {
	
	public GT_Cover_RedstoneTransmitterInternal(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		GregTech_API.sWirelessRedstone.put(aCoverVariable, aTileEntity.getOutputRedstoneSignal(aSide));
		return aCoverVariable;
	}
	
	@Override
	public boolean letsRedstoneGoOut(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 1;
	}
}
