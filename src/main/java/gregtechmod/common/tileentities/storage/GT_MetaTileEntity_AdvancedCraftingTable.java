package gregtechmod.common.tileentities.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GT_MetaTileEntity_AdvancedCraftingTable extends GT_MetaTileEntity_BasicTank {
	
	public boolean mFlushMode = false;
	
	public GT_MetaTileEntity_AdvancedCraftingTable(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_AdvancedCraftingTable() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return aIndex < 31 || aIndex > 32;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetInput()							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 1000;}
    @Override public int getInvSize()								{return 35;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_AdvancedCraftingTable();
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
	    getBaseMetaTileEntity().openGUI(aPlayer, 160);
	}
	
    @Override
    public void onFirstTick() {
    	getCraftingOutput();
    }
    
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return false;}
	@Override public boolean displaysStackSize()	{return false;}
	
    @Override
    public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
	    	if (getBaseMetaTileEntity().hasInventoryBeenModified()) getCraftingOutput();
	    	fillLiquidContainers();
	    	if (mFlushMode) {
	    		mFlushMode = false;
	    		for (byte i = 21; i < 30; i++) {
	    			if (mInventory[i] != null) {
	    				if (mInventory[i].stackSize == 0) {
	    					mInventory[i] = null;
	    				} else {
	    					mFlushMode = true;
		    				break;
	    				}
	    			}
	    		}
	    	}
		}
    }
    
    public void sortIntoTheInputSlots() {
    	for (byte i = 21; i < 30; i++) if (mInventory[i] != null) {
    		if (mInventory[i].stackSize == 0) {
    			mInventory[i] = null;
    		} 
    		if (mInventory[i] != null) for (byte j = 0; j < 16; j++) {
		    	if (GT_Utility.areStacksEqual(mInventory[i], mInventory[j])) {
		    		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), i, j, (byte)64, (byte)1, (byte)64, (byte)1);
		    	}
        	}
    		if (mInventory[i] != null) for (byte j = 0; j < 16; j++) {
		    	if (mInventory[j] == null) {
		    		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), i, j, (byte)64, (byte)1, (byte)64, (byte)1);
		    	}
        	}
    	}
    }
    
    private void fillLiquidContainers() {
		for (byte i = 16; i < 21 && mFluid[0] != null; i++) {
			ItemStack tOutput = GT_Utility.fillFluidContainer(mFluid[0], mInventory[i]);
			if (tOutput != null) {
				if (mInventory[i].stackSize == 1) {
					mFluid[0].amount -= GT_Utility.getFluidForFilledItem(tOutput).amount * tOutput.stackSize;
					mInventory[i] = tOutput;
				} else for (byte j = 16; j < 21; j++) {
					if (mInventory[j] == null || (GT_Utility.areStacksEqual(tOutput, mInventory[j]) && mInventory[j].stackSize + tOutput.stackSize <= tOutput.getMaxStackSize())) {
						mFluid[0].amount -= GT_Utility.getFluidForFilledItem(tOutput).amount * tOutput.stackSize;
						getBaseMetaTileEntity().decrStackSize(i, 1);
						if (mInventory[j] == null) {
							mInventory[j] = tOutput;
						} else {
							mInventory[j].stackSize++;
						}
						break;
					}
				}
				if (mFluid[0] != null && mFluid[0].amount <= 0) mFluid[0] = null;
			}
		}
		if (mFluid[0] != null && mFluid[0].amount <= 0) mFluid[0] = null;
    }
    
    public void setBluePrint(ItemStack aStack) {
    	if (aStack == null) aStack = mInventory[30];
    	if (mInventory[31] == null || aStack == null || aStack.getItem() != null || aStack.getItemDamage() != 0 || aStack.stackSize != 1 || aStack.hasTagCompound()) return;
    	NBTTagCompound tNBT = new NBTTagCompound();
        NBTTagList tNBT_ItemList = new NBTTagList();
        for (int i = 0; i < 9; i++) {
            ItemStack tStack = mInventory[i+21];
            if (tStack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                tStack.writeToNBT(tag);
                tNBT_ItemList.appendTag(tag);
            }
        }
        tNBT.setTag("Inventory", tNBT_ItemList);
        aStack.setTagCompound(tNBT);
    }
    
    public ItemStack getCraftingOutput() {
    	if (mInventory[30] != null && mInventory[30].getItem() != null && mInventory[30].getItemDamage() == 0 && mInventory[30].hasTagCompound()) {
    		NBTTagCompound tNBT = mInventory[30].getTagCompound();
        	NBTTagList tNBT_ItemList = tNBT.getTagList("Blueprint", 10);
            for (int i = 0; i < tNBT_ItemList.tagCount() && i < 9; i++) {
                NBTTagCompound tag = (NBTTagCompound) tNBT_ItemList.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < 9 && mInventory[slot+21] == null) {
	                mInventory[slot+21] = GT_Utility.loadItem(tag);
	                if (mInventory[slot+21] != null) mInventory[slot+21].stackSize = 0;
                }
            }
    	}
    	mInventory[31] = GT_ModHandler.getAllRecipeOutput(getBaseMetaTileEntity().getWorld(), new ItemStack[] {mInventory[21], mInventory[22], mInventory[23], mInventory[24], mInventory[25], mInventory[26], mInventory[27], mInventory[28], mInventory[29]});
		return mInventory[31];
    }
    
    public boolean canDoCraftingOutput() {
    	if (mInventory[31] == null) return false;
		for (ItemStack tStack : recipeContent()) {
			if (tStack.stackSize > getAmountOf(tStack)) {
				return false;
			}
		}
		return true;
    }
    
    private int getAmountOf(ItemStack aStack) {
    	int tAmount = 0;
    	for (byte i = 0; i < 30 && tAmount < 9; i++) {
    		if (GT_Utility.areStacksOrToolsEqual(aStack, mInventory[i])) {
    			tAmount+=mInventory[i].stackSize;
    		}
    	}
    	return tAmount;
    }
    
	private ArrayList<ItemStack> recipeContent() {
		ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
		for (byte i = 21; i < 30; i++) {
			if (mInventory[i] != null) {
				boolean temp = false;
				for (byte j = 0; j < tList.size(); j++) {
					if (GT_Utility.areStacksOrToolsEqual(mInventory[i], tList.get(j))) {
						tList.get(j).stackSize++;
						temp = true;
						break;
					}
				}
				if (!temp) tList.add(GT_Utility.copy(1, mInventory[i]));
			}
		}
		return tList;
	}
    
	public ItemStack consumeMaterials(EntityPlayer aPlayer, ItemStack aHoldStack) {
	    if (mInventory[31] == null) return aHoldStack;
	    if (aHoldStack != null) {
	    	if (!GT_Utility.areStacksEqual(aHoldStack, mInventory[31])) return aHoldStack;
	    	if (aHoldStack.stackSize + mInventory[31].stackSize > aHoldStack.getMaxStackSize()) return aHoldStack;
	    }
		for (byte i = 21; i < 30; i++) if (mInventory[i] != null) {
			for (byte j = 0; j <= i; j++) {
				if (j < 21 || j == i) {
					if (GT_Utility.areStacksOrToolsEqual(mInventory[i], mInventory[j]) && mInventory[j].stackSize > 0) {
						ItemStack tStack = GT_Utility.getContainerItem(mInventory[j]);
						if (tStack == null || (tStack.isItemStackDamageable() && tStack.getItemDamage() >= tStack.getMaxDamage())) {
							getBaseMetaTileEntity().decrStackSize(j, 1);
						} else if (mInventory[j].stackSize == 1) {
							mInventory[j] = tStack;
						} else {
							getBaseMetaTileEntity().decrStackSize(j, 1);
							for (byte k = 0; k < 21; k++) {
								if (mInventory[k] == null) {
									mInventory[k] = tStack;
									break;
								} else {
									if (GT_Utility.areStacksEqual(tStack, mInventory[k])) {
										if (tStack.stackSize + mInventory[k].stackSize <= mInventory[k].getMaxStackSize()) {
											mInventory[k].stackSize += tStack.stackSize;
											break;
										}
									}
								}
							}
						}
						break;
					}
				}
			}
		}
		if (aHoldStack == null) {
			aHoldStack = GT_Utility.copy(mInventory[31]);
			aHoldStack.onCrafting(getBaseMetaTileEntity().getWorld(), aPlayer, mInventory[31].stackSize);
		} else {
			aHoldStack.stackSize += mInventory[31].stackSize;
			aHoldStack.onCrafting(getBaseMetaTileEntity().getWorld(), aPlayer, mInventory[31].stackSize);
		}
		
		fillLiquidContainers();
		
		return aHoldStack;
	}
	
	@Override
    public int rechargerSlotStartIndex() {
    	return 16;
    }
	
	@Override
    public int rechargerSlotCount() {
    	return 5;
    }
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return  32;
		if (aSide==1) return 290;
		if (aFacing == 0 || aFacing == 1) return 222;
		if (aFacing == 2 || aFacing == 3) return 223;
		return 215;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_ProjectTable.tooltip";
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		if (aIndex < 16) {
			for (byte i = 0; i < 16; i++) if (GT_Utility.areStacksOrToolsEqual(aStack, mInventory[i])) return aIndex == i;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 33 || (mFlushMode && aIndex >= 21 && aIndex < 30);
	}
	
	@Override
	public int getCapacity() {
		return 64000;
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
