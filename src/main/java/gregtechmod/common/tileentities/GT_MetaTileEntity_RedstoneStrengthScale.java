package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaTileEntity_RedstoneStrengthScale extends GT_MetaTileEntity_RedstoneStrengthDisplay {
	
	public static IIcon[] sIconList = new IIcon[256];
	
	public GT_MetaTileEntity_RedstoneStrengthScale(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_RedstoneStrengthScale() {
		
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneStrengthScale();
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide==aFacing?sIconList[mType*16+mRedstoneStrength]:null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aBlockIconRegister) {
		for (int i = 0; i < 32; i++) {
			sIconList[i] = aBlockIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + "tile.RedstoneScale/" + i);
		}
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) mType=(byte)((mType+1)%2);
	}
	
	@Override
	public String getDescription() {
		return "Redstone Strength on a Scale";
	}
}