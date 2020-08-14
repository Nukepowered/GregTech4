package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaTileEntity_RedstoneButtonPanel extends MetaTileEntity {
	
	public byte mRedstoneStrength = 0, mType = 0, mUpdate = 0;
	
	public static IIcon[] sIconList = new IIcon[256];
	
	public GT_MetaTileEntity_RedstoneButtonPanel(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_RedstoneButtonPanel() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
    @Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneButtonPanel();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setByte("mRedstoneStrength", mRedstoneStrength);
		aNBT.setByte("mType", mType);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		mRedstoneStrength = aNBT.getByte("mRedstoneStrength");
		mType = aNBT.getByte("mType");
	}
	
	@Override
	public void onValueUpdate(byte aValue) {
		mRedstoneStrength = (byte)(aValue & 15);
		mType = (byte)(aValue >>> 4);
	}
	
	@Override
	public byte getUpdateData() {
		return (byte)((mRedstoneStrength & 15) | (mType << 4));
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) {
			if (getBaseMetaTileEntity().isServerSide()) {
				mUpdate = 2;
				switch (mType) {
				case  0: default:
					switch (aSide) {
					case  0: case  1:
						mRedstoneStrength = (byte)((byte)(  aX*4) + 4 * (byte)(  aZ*4));
						break;
					case  2:
						mRedstoneStrength = (byte)((byte)(4-aX*4) + 4 * (byte)(4-aY*4));
						break;
					case  3:
						mRedstoneStrength = (byte)((byte)(  aX*4) + 4 * (byte)(4-aY*4));
						break;
					case  4:
						mRedstoneStrength = (byte)((byte)(  aZ*4) + 4 * (byte)(4-aY*4));
						break;
					case  5:
						mRedstoneStrength = (byte)((byte)(4-aZ*4) + 4 * (byte)(4-aY*4));
						break;
					}
					break;
				case  1:
					switch (aSide) {
					case  0: case  1:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << (((byte)(  aX*2) + 2 * (byte)(  aZ*2)))));
						break;
					case  2:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << (((byte)(2-aX*2) + 2 * (byte)(2-aY*2)))));
						break;
					case  3:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << (((byte)(  aX*2) + 2 * (byte)(2-aY*2)))));
						break;
					case  4:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << (((byte)(  aZ*2) + 2 * (byte)(2-aY*2)))));
						break;
					case  5:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << (((byte)(2-aZ*2) + 2 * (byte)(2-aY*2)))));
						break;
					}
					break;
				case  2:
					switch (aSide) {
					case  0: case  1:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << ((byte)(  aZ*4))));
						break;
					case  2:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << ((byte)(4-aY*4))));
						break;
					case  3:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << ((byte)(4-aY*4))));
						break;
					case  4:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << ((byte)(4-aY*4))));
						break;
					case  5:
						mRedstoneStrength = (byte)(mRedstoneStrength ^ (1 << ((byte)(4-aY*4))));
						break;
					}
					break;
				}
			}
			return true;
		}
		return false;
	}
	
    @Override
    public void onPreTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	getBaseMetaTileEntity().setGenericRedstoneOutput(true);
	    	if (mUpdate > 0) mUpdate--; else if (getBaseMetaTileEntity().isAllowedToWork()) mRedstoneStrength = 0;
	    	for (byte i = 0; i < 6; i++) getBaseMetaTileEntity().setInternalOutputRedstoneSignal(i, i == getBaseMetaTileEntity().getFrontFacing()?(byte)0:mRedstoneStrength);
		}
    }
    
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing() || GregTech_API.getCoverBehavior(aCoverID).isGUIClickable(aSide, aCoverID, 0, getBaseMetaTileEntity());
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) mType=(byte)((mType+1)%3);
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide==aFacing?sIconList[mType*16+mRedstoneStrength]:null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aBlockIconRegister) {
		for (int i = 0; i < 48; i++) {
			sIconList[i] = aBlockIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + "tile.ButtonPanel/" + i);
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return aRedstone?56:54;
		if (aSide==1) return aRedstone?53:52;
		return aRedstone?94:93;
	}
	
	@Override
	public String getDescription() {
		return "Rightclick with Screwdriver to change Button Design";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
}
