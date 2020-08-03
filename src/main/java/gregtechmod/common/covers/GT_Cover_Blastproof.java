package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.item.ItemStack;

public class GT_Cover_Blastproof extends GT_CoverBehavior {
	
	private final float mLevel;
	
	public GT_Cover_Blastproof(ItemStack[] aCovers, float aLevel) {
		super(aCovers);
		mLevel = aLevel;
	}
	
	@Override
	public float getBlastProofLevel(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return mLevel;
	}
	
	@Override
	public boolean isSimpleCover() {
		return true;
	}
}