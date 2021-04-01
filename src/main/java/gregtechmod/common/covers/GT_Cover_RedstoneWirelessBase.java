package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;

public abstract class GT_Cover_RedstoneWirelessBase extends GT_CoverBehavior {
	
	public GT_Cover_RedstoneWirelessBase(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public boolean onCoverRemoval(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
		GregTech_API.sWirelessRedstone.put(aCoverVariable, (byte)0);
		return true;
	}
	
	@Override
	public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (((aX > 0.375 && aX < 0.625) || aSide > 3) && ((aY > 0.375 && aY < 0.625) || aSide < 2) && ((aZ > 0.375 && aZ < 0.625) || aSide == 2 || aSide == 3)) {
			GregTech_API.sWirelessRedstone.put(aCoverVariable, (byte)0);
			aCoverVariable = GT_Utility.stackToInt1(aPlayer.inventory.getCurrentItem());
			aTileEntity.setCoverDataAtSide(aSide, aCoverVariable);
			GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.GT_Tesseract.message.1", aCoverVariable));
			return true;
		}
		return false;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (((aX > 0.375 && aX < 0.625) || aSide > 3) && ((aY > 0.375 && aY < 0.625) || aSide < 2) && ((aZ > 0.375 && aZ < 0.625) || aSide == 2 || aSide == 3)) {
			
		} else {
			GregTech_API.sWirelessRedstone.put(aCoverVariable, (byte)0);
			
			byte tScrew = 0;
			
			switch (aSide) {
			case  0: case  1:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(  aZ*2));
				break;
			case  2:
				tScrew = (byte)((byte)(2-aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  3:
				tScrew = (byte)((byte)(  aX*2) + 2 * (byte)(2-aY*2));
				break;
			case  4:
				tScrew = (byte)((byte)(  aZ*2) + 2 * (byte)(2-aY*2));
				break;
			case  5:
				tScrew = (byte)((byte)(2-aZ*2) + 2 * (byte)(2-aY*2));
				break;
			}
			switch (tScrew) {
			case  0:
				aCoverVariable-=32;
				break;
			case  1:
				aCoverVariable+=32;
				break;
			case  2:
				aCoverVariable-=1024;
				break;
			case  3:
				aCoverVariable+=1024;
				break;
			}
		}
		GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.GT_Tesseract.message.1", aCoverVariable));
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
	public String getDescription(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return StatCollector.translateToLocalFormatted("metatileentity.GT_Tesseract.message.1", aCoverVariable);
	}
	
	@Override
	public short getTickRate(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 1;
	}
}
