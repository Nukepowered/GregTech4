package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_AdvancedTranslocator;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_AdvancedTranslocator extends GT_ContainerMetaTile_Machine {

	public GT_Container_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,  63,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  80,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  97,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  63, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  80, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  5,  97, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  6,  63, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  7,  80, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  8,  97, 40, false, true, 1));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  43,  5, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9, 117,  5, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex < 9) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack != null) {
		    		tStack = GT_Utility.copy(1, tStack);
		    	}
		    	tSlot.putStack(tStack);
		    	return null;
		    } else if (aSlotIndex == 9) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bOutput;
			    if (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bOutput)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
		    	return null;
		    } else if (aSlotIndex == 10) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bInvertFilter;
			    if (((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).bInvertFilter)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Inverted the Filter");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Filter works normal");
		    	return null;
		    } else if (aSlotIndex == 11) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mInputSide = (byte) ((((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mInputSide + 1) % 6);
		    } else if (aSlotIndex == 12) {
		    	((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mOutputSide = (byte) ((((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mOutputSide + 1) % 6);
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int mInputSide, mOutputSide;
    
    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mInputSide  = ((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mInputSide;
    	mOutputSide = ((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mOutputSide;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mInputSide);
            var1.sendProgressBarUpdate(this, 101, mOutputSide);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mInputSide = par2; break;
    	case 101: mOutputSide = par2; break;
    	}
    }
}
