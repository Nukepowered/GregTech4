package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Cover_EUMeter extends GT_CoverBehavior {
	
	public GT_Cover_EUMeter(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		int tScale = 0;
		if (aCoverVariable < 2) {
			tScale = aTileEntity.getUniversalEnergyCapacity()/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getUniversalEnergyStored()/tScale):(byte)(15-(aTileEntity.getUniversalEnergyStored()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		} else if (aCoverVariable < 4) {
			tScale = aTileEntity.getEUCapacity()/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getStoredEU()/tScale):(byte)(15-(aTileEntity.getStoredEU()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		} else if (aCoverVariable < 6) {
			tScale = aTileEntity.getRFCapacity()/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getStoredRF()/tScale):(byte)(15-(aTileEntity.getStoredRF()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		} else if (aCoverVariable < 8) {
			tScale = aTileEntity.getSteamCapacity()/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getStoredSteam()/tScale):(byte)(15-(aTileEntity.getStoredSteam()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		} else if (aCoverVariable < 10) {
			tScale = aTileEntity.getInputVoltage()/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getAverageElectricInput()/tScale):(byte)(15-(aTileEntity.getAverageElectricInput()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		} else if (aCoverVariable < 12) {
			tScale = (aTileEntity.getOutputVoltage()*aTileEntity.getOutputAmperage())/15;
			if (tScale > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)(aTileEntity.getAverageElectricOutput()/tScale):(byte)(15-(aTileEntity.getAverageElectricOutput()/tScale)));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable%2==0?(byte)0:15);
			}
		}
		return aCoverVariable;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable=(aCoverVariable+1)%12;
		if (aCoverVariable ==  0) GT_Utility.sendChatToPlayer(aPlayer, "Normal Universal Storage");
		if (aCoverVariable ==  1) GT_Utility.sendChatToPlayer(aPlayer, "Inverted Universal Storage");
		if (aCoverVariable ==  2) GT_Utility.sendChatToPlayer(aPlayer, "Normal Electricity Storage");
		if (aCoverVariable ==  3) GT_Utility.sendChatToPlayer(aPlayer, "Inverted Electricity Storage");
		if (aCoverVariable ==  4) GT_Utility.sendChatToPlayer(aPlayer, "Normal MJ Storage");
		if (aCoverVariable ==  5) GT_Utility.sendChatToPlayer(aPlayer, "Inverted MJ Storage");
		if (aCoverVariable ==  6) GT_Utility.sendChatToPlayer(aPlayer, "Normal Steam Storage");
		if (aCoverVariable ==  7) GT_Utility.sendChatToPlayer(aPlayer, "Inverted Steam Storage");
		if (aCoverVariable ==  8) GT_Utility.sendChatToPlayer(aPlayer, "Normal Average Electric Input");
		if (aCoverVariable ==  9) GT_Utility.sendChatToPlayer(aPlayer, "Inverted Average Electric Input");
		if (aCoverVariable == 10) GT_Utility.sendChatToPlayer(aPlayer, "Normal Average Electric Output");
		if (aCoverVariable == 11) GT_Utility.sendChatToPlayer(aPlayer, "Inverted Average Electric Output");
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
