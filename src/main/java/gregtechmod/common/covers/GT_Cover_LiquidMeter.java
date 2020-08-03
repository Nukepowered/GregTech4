package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_Cover_LiquidMeter extends GT_CoverBehavior {
	
	public GT_Cover_LiquidMeter(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aTileEntity instanceof IFluidHandler) {
			FluidTankInfo[] tTanks = ((IFluidHandler)aTileEntity).getTankInfo(ForgeDirection.UNKNOWN);
			long tAll = 0, tFull = 0;
			if (tTanks != null) for (FluidTankInfo tTank : tTanks) if (tTank != null) {
				tAll+=tTank.capacity;
				FluidStack tLiquid = tTank.fluid;
				if (tLiquid != null) {
					tFull += tLiquid.amount;
				}
			}
			tAll/=15;
			if (tAll > 0) {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable==0?(byte)(tFull/tAll):(byte)(15-tFull/tAll));
			} else {
				aTileEntity.setOutputRedstoneSignal(aSide, aCoverVariable==0?(byte)0:15);
			}
		} else {
			aTileEntity.setOutputRedstoneSignal(aSide, (byte)0);
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
