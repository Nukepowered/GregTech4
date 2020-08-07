package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_RedstoneNoteBlock extends MetaTileEntity {
	
	private byte mRedstoneStrength = 0;
	
	public GT_MetaTileEntity_RedstoneNoteBlock(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_RedstoneNoteBlock() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
    @Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneNoteBlock();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
    @Override
    public void onPreTick() {
	    if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide()) {
	    	if (getBaseMetaTileEntity().getStrongestRedstone() != mRedstoneStrength) {
	    		mRedstoneStrength = getBaseMetaTileEntity().getStrongestRedstone();
		    	if (mRedstoneStrength > 0) sendSound(mRedstoneStrength);
	    	}
		}
    }
    
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		aIndex--;
        ItemStack tStack;
        switch (getBaseMetaTileEntity().getFrontFacing()) {
        case  1: tStack = new ItemStack(Blocks.gold_block, 1); break;
        case  2: tStack = new ItemStack(Blocks.stone, 1); break;
        case  3: tStack = new ItemStack(Blocks.sand, 1); break;
        case  4: tStack = new ItemStack(Blocks.glass, 1); break;
        case  5: tStack = new ItemStack(Blocks.log, 1); break;
        default: tStack = new ItemStack(Blocks.iron_block, 1); break;
        }
        tStack.stackSize = 1+(int)(aIndex * 1.714);
        GT_Mod.gregtechproxy.doSonictronSound(tStack, getBaseMetaTileEntity().getWorld(), aX, aY, aZ);
        getBaseMetaTileEntity().getWorld().spawnParticle("note", aX, aY+0.7, aZ, aIndex/14.0, 0.0, 0.0);
	}
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return 293;
	}
	
	@Override
	public String getDescription() {
		return "Rotate to switch Notes";
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
