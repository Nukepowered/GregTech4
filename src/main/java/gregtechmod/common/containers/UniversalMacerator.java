package gregtechmod.common.containers;

import java.util.List;

import gregtechmod.api.gui.GT_Container_BasicMachine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class UniversalMacerator extends GT_Container_BasicMachine {

	/**
	 * @param aInventoryPlayer
	 * @param aTileEntity
	 */
	public UniversalMacerator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override
	public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  35,  25));
        addSlotToContainer(new Slot(mTileEntity,  1,  53,  25));
        addSlotToContainer(new Slot(mTileEntity,  6,  80,  63));
        List<ItemStack> outputs = ((IRecipeWorkable) mTileEntity.getMetaTileEntity()).getOutputItems();
        for (int i = 0; i < outputs.size(); i++) {
        	int xOffset = 18 * (i % 2);
        	int yOffset = 18 * (i / 2);
        	
        	addSlotToContainer(new GT_Slot_Output(mTileEntity,  2 + i, 107 + xOffset, 16 + yOffset));
        }
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, -1,  8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, -1, 26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, -1, 44, 63, false, true, 1));
	}
	
	@Override
	public boolean handleClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (mTileEntity.getMetaTileEntity() != null && tSlot != null) {
	    	GT_MetaTileEntity_BasicMachine mte = (GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity();
		    if (aSlotIndex == 7) {
		    	mte.bOutput = !mte.bOutput;
		    	return true;
		    } else if (aSlotIndex == 8) {
		    	mte.bItemTransfer = !mte.bItemTransfer;
		    	return true;
		    } else if (aSlotIndex == 9) {
		    	mte.bSeperatedInputs = !mte.bSeperatedInputs;
		    	return true;
		    }
    	}
	    
	    return false;
    }
	
    @Override
	public int getSlotCount() {
    	return 7;
    }
    
    @Override
	public int getShiftClickSlotCount() {
    	return 2;
    }
}
