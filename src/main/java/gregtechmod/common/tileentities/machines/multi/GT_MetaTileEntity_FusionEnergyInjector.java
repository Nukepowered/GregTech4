package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_FusionEnergyInjector extends MetaTileEntity {
	
	public IGregTechTileEntity mFusionComputer;
	
	public GT_MetaTileEntity_FusionEnergyInjector(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_FusionEnergyInjector() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxEUInput()								{return 8192;}
    @Override public int maxEUStore()								{return 10000000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_FusionEnergyInjector();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		getBaseMetaTileEntity().setActive(mFusionComputer!=null&&mFusionComputer.isActive());
    	}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aActive?20:19;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Fusion_Energy.tooltip";
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
