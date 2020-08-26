package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import gregtechmod.api.util.GT_Utility;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.StatCollector;

public abstract class GT_MetaPipeEntity_Item extends MetaPipeEntity {
	
	public int mTransferredItems = 0;
	public byte mLastReceivedFrom = 0, oLastReceivedFrom = 0;
	
	public GT_MetaPipeEntity_Item(int aID, String aName) {
		super(aID, aName);
	}
	
	public GT_MetaPipeEntity_Item() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
    @Override public final boolean renderInside()					{return false;}
    @Override public final int getInvSize()							{return getMaxPipeCapacity();}
    @Override public int getProgresstime()							{return getPipeContent()*64;}
    @Override public int maxProgresstime()							{return getMaxPipeCapacity()*64;}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setByte("mLastReceivedFrom", mLastReceivedFrom);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mLastReceivedFrom = aNBT.getByte("mLastReceivedFrom");
	}
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getTimer() % 10 == 0) {
			mConnections = 0;
			if (getBaseMetaTileEntity().getTimer() % 20 == 0) mTransferredItems = 0;
			
			for (byte i = 0; i < 6; i++) {
			   	TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSide(i);
			    if (tTileEntity != null) {
			    	boolean temp = GT_Utility.isConnectableNonInventoryPipe(tTileEntity, GT_Utility.getOppositeSide(i));
	    			if (tTileEntity instanceof IGregTechTileEntity) {
		    			temp = true;
		    			if (getBaseMetaTileEntity().getMetaTileEntity() == null) continue;
						if (getBaseMetaTileEntity().getColorization() >= 0) {
							byte tColor = ((IGregTechTileEntity)tTileEntity).getColorization();
							if (tColor >= 0 && (tColor & 15) != (getBaseMetaTileEntity().getColorization() & 15)) {
								continue;
							}
						}
		    			if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity().connectsToItemPipe(GT_Utility.getOppositeSide(i))) {
		    				mConnections |= (1<<i);
		    				continue;
		    			}
    				}
		    		if (tTileEntity instanceof IInventory) {
		    			temp = true;
			    		if (((IInventory)tTileEntity).getSizeInventory() <= 0) {
			    			continue;
			    		}
	    			}
		    		if (tTileEntity instanceof ISidedInventory) {
		    			temp = true;
			    		int[] tSlots = ((ISidedInventory)tTileEntity).getAccessibleSlotsFromSide(GT_Utility.getOppositeSide(i));
			    		if (tSlots == null || tSlots.length <= 0) {
			    			continue;
			    		}
	    			}
		    		if (temp) {
					    if (getBaseMetaTileEntity().getCoverBehaviorAtSide(i).alwaysLookConnected(i, getBaseMetaTileEntity().getCoverIDAtSide(i), getBaseMetaTileEntity().getCoverDataAtSide(i), getBaseMetaTileEntity())) {
					    	mConnections |= (1<<i);
		    				continue;
					    }
			    		if (getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsIn(i, getBaseMetaTileEntity().getCoverIDAtSide(i), getBaseMetaTileEntity().getCoverDataAtSide(i), getBaseMetaTileEntity())) {
					    	mConnections |= (1<<i);
		    				continue;
					    }
					    if (getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsOut(i, getBaseMetaTileEntity().getCoverIDAtSide(i), getBaseMetaTileEntity().getCoverDataAtSide(i), getBaseMetaTileEntity())) {
					    	mConnections |= (1<<i);
		    				continue;
					    }
		    		}
			    }
			}
			
	    	if (oLastReceivedFrom == mLastReceivedFrom && !isInventoryEmpty() && pipeCapacityCheck()) {
	        	doTickProfilingInThisTick = false;
	    		Map<GT_MetaPipeEntity_Item, Long> tMap = GT_Utility.sortMapByValuesAcending(GT_Utility.scanPipes(this, new HashMap<GT_MetaPipeEntity_Item, Long>(), 0, false));
	    		for (GT_MetaPipeEntity_Item tTileEntity : tMap.keySet()) {
	    			tTileEntity.sendItemStack(getBaseMetaTileEntity());
	    			if (isInventoryEmpty()) break;
	    		}
	    	}
	    	
			if (isInventoryEmpty()) mLastReceivedFrom = 6;
	    	oLastReceivedFrom = mLastReceivedFrom;
		}
    }
    
    private void sendItemStack(Object aSender) {
    	if (pipeCapacityCheck()) {
	    	byte tOffset = (byte)getBaseMetaTileEntity().getRandomNumber(6), tSide = 0;
	    	for (byte i = 0; i < 6; i++) {
	    		tSide = (byte)((i+tOffset)%6);
	    		if (isInventoryEmpty() || (tSide != mLastReceivedFrom || aSender != getBaseMetaTileEntity())) {
				    insertItemStackIntoTileEntity(aSender, tSide);
				}
	    	}
	    	mTransferredItems++;
    	}
    }
    
    public void insertItemStackIntoTileEntity(Object aSender, byte aSide) {
    	if (getBaseMetaTileEntity().getCoverBehaviorAtSide(aSide).letsItemsOut(aSide, getBaseMetaTileEntity().getCoverIDAtSide(aSide), getBaseMetaTileEntity().getCoverDataAtSide(aSide), getBaseMetaTileEntity())) {
	    	TileEntity tInventory = getBaseMetaTileEntity().getTileEntityAtSide(aSide);
			if (tInventory != null && !(tInventory instanceof BaseMetaPipeEntity)) {
				if ((!(tInventory instanceof TileEntityHopper) && !(tInventory instanceof TileEntityDispenser)) || getBaseMetaTileEntity().getMetaIDAtSide(aSide) != GT_Utility.getOppositeSide(aSide)) {
					while (GT_Utility.moveOneItemStack(aSender, tInventory, (byte)6, GT_Utility.getOppositeSide(aSide), null, false, (byte)64, (byte)1, (byte)64, (byte)1) > 0) {/*Do nothing*/}
				}
			}
    	}
    }
    
    public boolean pipeCapacityCheck() {
    	return mTransferredItems <= 0 || getPipeContent() < getMaxPipeCapacity();
    }
    
	private int getPipeContent() {
		return mTransferredItems;
	}
	
	private int getMaxPipeCapacity() {
		return Math.max(1, getPipeCapacity());
	}
	
	/**
	 * Amount of ItemStacks this Pipe can conduct per Second.
	 */
	public abstract int getPipeCapacity();
	
	/**
	 * Can be used to make flow control Pipes, like Redpowers Restriction Tubes.
	 * Every normal Pipe returns a Value of 32768, so you can easily insert lower Numbers to set Routing priorities
	 * Negative Numbers to "suck" Items into a certain direction are also possible.
	 */
	public abstract int getStepSize();
	
	@Override
	public boolean canExtractItem(int aIndex, ItemStack aStack, int aSide) {
		return true;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return true;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		if (isInventoryEmpty()) mLastReceivedFrom = aSide;
		return mLastReceivedFrom == aSide && mInventory[aIndex] == null;
	}
	
	@Override
	public String getDescription() {
		return StatCollector.translateToLocalFormatted("pipes.item.capacity", getMaxPipeCapacity());
	}

	private boolean isInventoryEmpty() {
		for (ItemStack tStack : mInventory) if (tStack != null) return false;
		return true;
	}
}