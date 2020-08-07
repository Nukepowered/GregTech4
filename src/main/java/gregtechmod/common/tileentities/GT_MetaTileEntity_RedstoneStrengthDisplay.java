package gregtechmod.common.tileentities;

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

public class GT_MetaTileEntity_RedstoneStrengthDisplay extends MetaTileEntity {
	
	public byte mRedstoneStrength = 0, mType = 0;
	
	public static IIcon[] sIconList = new IIcon[256];
	
	public GT_MetaTileEntity_RedstoneStrengthDisplay(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_RedstoneStrengthDisplay() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
    @Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneStrengthDisplay();
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
    public void onPreTick() {
	    if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide()) {
	    	mRedstoneStrength = getBaseMetaTileEntity().getStrongestRedstone();
		}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return aRedstone?60:59;
		if (aSide==1) return aRedstone?58:57;
		return aRedstone?62:61;
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) mType=(byte)((mType+1)%6);
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide==aFacing?sIconList[mType*16+mRedstoneStrength]:null;
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aBlockIconRegister) {
		for (int i = 0; i < 96; i++) {
			sIconList[i] = aBlockIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + "tile.RedstoneDisplay/" + i);
		}
	}
	
	@Override
	public String getDescription() {
		return "Displays Redstone Strength";
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
