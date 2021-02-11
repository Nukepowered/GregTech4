package gregtechmod.common.containers;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class UniversalMacerator extends GT_ContainerMetaTile_Machine {
	
    public boolean mOutputting = false, mItemTransfer = false, mSeperatedInputs = false;
    
	/**
	 * @param aInventoryPlayer
	 * @param aTileEntity
	 */
	public UniversalMacerator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override
	public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  1,  35,  25));
        addSlotToContainer(new Slot(mTileEntity,  2,  53,  25));
        addSlotToContainer(new Slot(mTileEntity,  7,  80,  63));
        List<ItemStack> outputs = ((IRecipeWorkable) mTileEntity.getMetaTileEntity()).getOutputItems();
        for (int i = 0; i < outputs.size(); i++) {
        	int xOffset = 18 * (i % 2);
        	int yOffset = 18 * (i / 2);
        	
        	addSlotToContainer(new GT_Slot_Output(mTileEntity,  3 + i, 107 + xOffset, 16 + yOffset));
        }
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 44, 63, false, true, 1));
	}
	
    @Override
	public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 7) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex == 7) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bOutput;
			    return null;
		    }
		    if (aSlotIndex == 8) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bItemTransfer = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bItemTransfer;
			    return null;
		    }
		    if (aSlotIndex == 9) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bSeperatedInputs = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bSeperatedInputs;
			    return null;
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
	
    @Override
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	
    	mOutputting = ((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bOutput;
    	mItemTransfer = ((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bItemTransfer;
    	mSeperatedInputs = ((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bSeperatedInputs;
    	
        @SuppressWarnings("rawtypes")
		Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 101, mOutputting?1:0);
            var1.sendProgressBarUpdate(this, 102, mItemTransfer?1:0);
            var1.sendProgressBarUpdate(this, 103, mSeperatedInputs?1:0);
        }
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 101: mOutputting = (par2 != 0); break;
    	case 102: mItemTransfer = (par2 != 0); break;
    	case 103: mSeperatedInputs = (par2 != 0); break;
    	}
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
