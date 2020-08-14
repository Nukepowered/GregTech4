package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRedstoneCircuitBlock;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_RedstoneCircuitBlock extends MetaTileEntity implements IRedstoneCircuitBlock {
	
	public int mGate = 0, mGateData[] = new int[] {0,0,0,0,0,0,0,0};
    public boolean bOutput = true;
	
	public GT_MetaTileEntity_RedstoneCircuitBlock(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_RedstoneCircuitBlock() {
		
	}
	
	@Override public boolean hasSidedRedstoneOutputBehavior()		{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{return getBaseMetaTileEntity().getBackFacing() == aSide;}
	@Override public int getMinimumStoredEU()						{return 500;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return bOutput?32:0;}
    @Override public int getInvSize()								{return 5;}
    @Override public int maxEUStore()								{return 1000;}
    @Override public int maxMJStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 147);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneCircuitBlock();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setInteger("mGate", mGate);
		aNBT.setIntArray("mGateData", mGateData);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		mGate = aNBT.getInteger("mGate");
		mGateData = aNBT.getIntArray("mGateData");
		if (mGateData.length != 8) mGateData = new int[] {0,0,0,0,0,0,0,0};
	}

	public void switchOutput() {
		bOutput = !bOutput;
	}
	
	public void switchGateForward(boolean aShift) {
		try {
		Set<Integer> tKeys = GregTech_API.sCircuitryBehaviors.keySet();
		ArrayList<Integer> tList = new ArrayList<Integer>();
		tList.addAll(tKeys);
		if (tList.size() <= 0) return;
		Collections.sort(tList);
		if (!GregTech_API.sCircuitryBehaviors.containsKey(mGate)) mGate = tList.get(0);
		int tIndex = Collections.binarySearch(tList, mGate);
		tIndex+=aShift?16:1;
		while (tIndex >= tList.size()) tIndex -= tList.size();
		mGate = tList.get(tIndex);
		switchGate();
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
	}
	
	public void switchGateBackward(boolean aShift) {
		try {
		Set<Integer> tKeys = GregTech_API.sCircuitryBehaviors.keySet();
		ArrayList<Integer> tList = new ArrayList<Integer>();
		tList.addAll(tKeys);
		if (tList.size() <= 0) return;
		Collections.sort(tList);
		if (!GregTech_API.sCircuitryBehaviors.containsKey(mGate)) mGate = tList.get(0);
		int tIndex = Collections.binarySearch(tList, mGate);
		tIndex-=aShift?16:1;
		while (tIndex < 0) tIndex += tList.size();
		mGate = tList.get(tIndex);
		switchGate();
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
	}
	
    @Override
    public void onFacingChange() {
    	resetRedstone();
    }
    
    private void resetRedstone() {
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)0, (byte)0);
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)1, (byte)0);
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)2, (byte)0);
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)3, (byte)0);
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)4, (byte)0);
    	getBaseMetaTileEntity().setInternalOutputRedstoneSignal((byte)5, (byte)0);
    }
    
	public void changeGateData(int aIndex, int aValue) {
		mGateData[aIndex] += aValue;
		validateGateData();
	}
	
	public void stackGateData(int aIndex, ItemStack aStack) {
		mGateData[aIndex] = GT_Utility.stackToInt(aStack);
		validateGateData();
	}
	
	private void switchGate() {
		resetRedstone();
		for (int i = 0; i < mGateData.length; i++) mGateData[i] = 0;
    	GT_CircuitryBehavior tBehaviour = GregTech_API.sCircuitryBehaviors.get(mGate);
    	if (tBehaviour != null) try {tBehaviour.initParameters(mGateData, this);} catch(Throwable e) {GT_Log.log.catching(e);}
		validateGateData();
	}
	
	private void validateGateData() {
    	GT_CircuitryBehavior tBehaviour = GregTech_API.sCircuitryBehaviors.get(mGate);
    	if (tBehaviour != null) try {tBehaviour.validateParameters(mGateData, this);} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public void onFirstTick() {
    	getBaseMetaTileEntity().setGenericRedstoneOutput(true);
		validateGateData();
	}
	
    @Override
    public void onPreTick() {
    	getBaseMetaTileEntity().setGenericRedstoneOutput(true);
	    if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide()) {
	    	mInventory[0] = mInventory[1] = mInventory[2] = mInventory[3] = mInventory[4] = null;
		    if (getBaseMetaTileEntity().getUniversalEnergyStored() > 400) {
		    	if (getBaseMetaTileEntity().isActive()) {
			    	GT_CircuitryBehavior tBehaviour = GregTech_API.sCircuitryBehaviors.get(mGate);
			    	if (tBehaviour != null) {try{
			    		tBehaviour.onTick(mGateData, this);
				    	if (tBehaviour.displayItemStack(mGateData, this, 0)) mInventory[1] = GT_Utility.intToStack(mGateData[0]);
				    	if (tBehaviour.displayItemStack(mGateData, this, 1)) mInventory[2] = GT_Utility.intToStack(mGateData[1]);
				    	if (tBehaviour.displayItemStack(mGateData, this, 2)) mInventory[3] = GT_Utility.intToStack(mGateData[2]);
				    	if (tBehaviour.displayItemStack(mGateData, this, 3)) mInventory[4] = GT_Utility.intToStack(mGateData[3]);
			    	} catch(Throwable e) {GT_Log.log.catching(e);}}
		    	}
				getBaseMetaTileEntity().setErrorDisplayID(0);
		    } else {
				getBaseMetaTileEntity().setErrorDisplayID(1);
		    }
		}
    }
    
    @Override
    public byte getOutputFacing() {
		return getBaseMetaTileEntity().getBackFacing();
	}
    
    @Override
	public boolean setRedstone(byte aStrength, byte aSide) {
		if (getOutputRedstone(aSide) != aStrength) {
			if (getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, false)) {
				getBaseMetaTileEntity().setInternalOutputRedstoneSignal(aSide, aStrength);
				getBaseMetaTileEntity().setErrorDisplayID(0);
				return true;
			} else {
				getBaseMetaTileEntity().setErrorDisplayID(1);
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==getOutputFacing()) {
			if (aSide==0) return aRedstone?56:54;
			if (aSide==1) return aRedstone?53:52;
			return aRedstone?94:93;
		}
		if (aSide==0) return aRedstone?60:59;
		if (aSide==1) return aRedstone?58:57;
		return aRedstone?62:61;
	}
	
	@Override
	public String getDescription() {
		return "Computes Redstone";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public byte getOutputRedstone(byte aSide) {
		return getBaseMetaTileEntity().getOutputRedstoneSignal(aSide);
	}
	
	@Override
	public byte getInputRedstone(byte aSide) {
		return getBaseMetaTileEntity().getInternalInputRedstoneSignal(aSide);
	}
	
	@Override
	public Block getBlockAtSide(byte aSide) {
		return getBaseMetaTileEntity().getBlockAtSide(aSide);
	}
	
	@Override
	public byte getMetaAtSide(byte aSide) {
		return getBaseMetaTileEntity().getMetaIDAtSide(aSide);
	}
	
	@Override
	public TileEntity getTileEntityAtSide(byte aSide) {
		return getBaseMetaTileEntity().getTileEntityAtSide(aSide);
	}
	
	@Override
	public int getRandom(int aRange) {
		return getBaseMetaTileEntity().getRandomNumber(aRange);
	}
	
	@Override
	public GT_CoverBehavior getCover(byte aSide) {
		return getBaseMetaTileEntity().getCoverBehaviorAtSide(aSide);
	}

	@Override
	public int getCoverID(byte aSide) {
		return getBaseMetaTileEntity().getCoverIDAtSide(aSide);
	}

	@Override
	public int getCoverVariable(byte aSide) {
		return getBaseMetaTileEntity().getCoverDataAtSide(aSide);
	}

	@Override
	public ICoverable getOwnTileEntity() {
		return getBaseMetaTileEntity();
	}
}
