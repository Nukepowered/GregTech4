package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneConductor extends GT_CoverBehavior {
	
	public GT_Cover_RedstoneConductor(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		     if (aCoverVariable == 0) aTileEntity.setOutputRedstoneSignal(aSide, aTileEntity.getStrongestRedstone());
		else if (aCoverVariable  < 7) aTileEntity.setOutputRedstoneSignal(aSide, aTileEntity.getInternalInputRedstoneSignal((byte)(aCoverVariable - 1)));
		
		return aCoverVariable;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable = (aCoverVariable + 1) % 7;
		switch (aCoverVariable) {
		case 0: GT_Utility.sendChatToPlayer(aPlayer, "Conducts strongest Input"); break;
		case 1: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from bottom Input"); break;
		case 2: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from top Input"); break;
		case 3: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from north Input"); break;
		case 4: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from south Input"); break;
		case 5: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from west Input"); break;
		case 6: GT_Utility.sendChatToPlayer(aPlayer, "Conducts from east Input"); break;
		}
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
		return 1;
	}
}