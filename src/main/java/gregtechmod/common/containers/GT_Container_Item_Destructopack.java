package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class GT_Container_Item_Destructopack extends Container {
	
	ItemStack mItem;
	
    public GT_Container_Item_Destructopack (InventoryPlayer aInventoryPlayer, ItemStack aItem) {
    	mItem = aItem;
    	
    	IInventory tInventory = new IInventory() {
			@Override public void setInventorySlotContents(int var1, ItemStack var2) {}
			@Override public void openInventory() {}
			@Override public void markDirty() {}
			@Override public boolean isUseableByPlayer(EntityPlayer var1) {return false;}
			@Override public ItemStack getStackInSlotOnClosing(int var1) {return null;}
			@Override public ItemStack getStackInSlot(int var1) {return null;}
			@Override public int getSizeInventory() {return 0;}
			@Override public int getInventoryStackLimit() {return 0;}
			@Override public String getInventoryName() {return null;}
			@Override public ItemStack decrStackSize(int var1, int var2) {return null;}
			@Override public void closeInventory() {}
			@Override public boolean hasCustomInventoryName() {return false; }
			@Override public boolean isItemValidForSlot(int i, ItemStack itemstack) {return false;}
    	};
    	
    	addSlotToContainer(new GT_Slot_Holo(tInventory, 0, 80, 17, false, false, 1));
    	
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
            	addSlotToContainer(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
        	addSlotToContainer(new Slot(aInventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
	
	@Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	
    	ItemStack ret = null;
    	if (aSlotIndex < 1) {
    		if (aPlayer.inventory.getItemStack() != null) {
    			
    			if (aShifthold == 1 || (ret = emtpyContainer(aPlayer.inventory.getItemStack(), aPlayer, aMouseclick == 0)) == null) {
	    			if (aMouseclick == 0) {
	    				if (aShifthold == 1) {
	    					for (int i = 0; i < aPlayer.inventory.getSizeInventory(); i++) {
	    						ItemStack tStack = aPlayer.inventory.getStackInSlot(i);
	    						if (tStack != null) {
	    							if (GT_Utility.areStacksEqual(tStack, aPlayer.inventory.getItemStack())) {
	    								aPlayer.inventory.setInventorySlotContents(i, null);
	    							}
	    						}
	    					}
	    				}
	    				aPlayer.inventory.setItemStack(null);
	    			} else if (aPlayer.inventory.getItemStack().stackSize < 2) {
	    				aPlayer.inventory.setItemStack(null);
	    			} else {
	    				aPlayer.inventory.getItemStack().stackSize--;
	    				ret = aPlayer.inventory.getItemStack();
	    			}
    			}
    		}
    		
    		return ret;
    	}
    	
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int aSlotIndex) {
        Slot slotObject = (Slot)inventorySlots.get(aSlotIndex);
        slotObject.putStack(null);
        return null;
	}
	
	protected ItemStack emtpyContainer(ItemStack held, EntityPlayer player, boolean leftClick) {
		ItemStack result = null;

		if (held.stackSize > 1) {
			ItemStack copy = this.getContainer(GT_Utility.copyAmount(1, held));
			if (copy != null) {
				if (leftClick) {
					copy.stackSize = held.stackSize;
					player.inventory.setItemStack(copy);
					result = copy;
				} else {
					held.stackSize--;
					player.inventory.addItemStackToInventory(copy);
					result = held;
				}
			}
		} else {
			result = getContainer(held);
			if (result != null)
				player.inventory.setItemStack(result);
		}
		
		return result;
	}
	
	/**
	 * Gets an empty container of stack
	 * @return null if no fluid in stack, otherwise will return container stack
	 */
	public ItemStack getContainer(ItemStack item) {
		if (FluidContainerRegistry.isContainer(item))
			return FluidContainerRegistry.drainFluidContainer(item);
		else if (item.getItem() instanceof IFluidContainerItem) {
			FluidStack fl = ((IFluidContainerItem)item.getItem()).drain(item, Integer.MAX_VALUE, true);
			if (fl != null && fl.amount > 0)
				return item;
		}
		
		return null;
	}
}
