package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeCraftingTable extends GT_MetaTileEntity_AdvancedCraftingTable {
	
	public GT_MetaTileEntity_BronzeCraftingTable(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_BronzeCraftingTable() {
		
	}
	
	@Override public boolean isElectric()							{return false;}
	@Override public boolean isPneumatic()							{return false;}
	@Override public boolean isSteampowered()						{return false;}
	@Override public boolean isTransformerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isEnetInput()							{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public int maxEUInput()								{return 0;}
    @Override public int maxEUStore()								{return 0;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeCraftingTable();
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
	    getBaseMetaTileEntity().openGUI(aPlayer, 161);
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover();
	}
	
	@Override
    public int rechargerSlotStartIndex() {
    	return 0;
    }
	
	@Override
    public int rechargerSlotCount() {
    	return 0;
    }
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return 315;
		if (aSide==1) return 317;
		if (aFacing == 0 || aFacing == 1) return 318;
		if (aFacing == 2 || aFacing == 3) return 319;
		return 320;
	}
	
	@Override
	public String getDescription() {
		return "For the smaller Projects";
	}
	
	@Override
	public int getCapacity() {
		return 16000;
	}
}
