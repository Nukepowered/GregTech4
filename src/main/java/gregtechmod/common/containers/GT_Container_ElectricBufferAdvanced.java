package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferAdvanced;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricBufferSmall;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_ElectricBufferAdvanced extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricBufferAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  80,  23));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  44, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  80, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1, 134, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex == 1) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput;
			    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
		    	return null;
		    } else if (aSlotIndex == 2) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
		    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull)
				    GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slot contains something");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Redstone");
		    	return null;
		    } else if (aSlotIndex == 3) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert;
		    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert)
				    GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Redstone");
		    	return null;
		    } else if (aSlotIndex == 4) {
		    	((GT_MetaTileEntity_ElectricBufferAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlot = Math.max(0, ((GT_MetaTileEntity_ElectricBufferAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlot - ((aShifthold==1)?16:1));
		    	return null;
		    } else if (aSlotIndex == 5) {
		    	((GT_MetaTileEntity_ElectricBufferAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlot = Math.min(8192, ((GT_MetaTileEntity_ElectricBufferAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlot + ((aShifthold==1)?16:1));
		    	return null;
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int mTargetSlot;
    
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mTargetSlot = ((GT_MetaTileEntity_ElectricBufferAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlot;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mTargetSlot);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mTargetSlot = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 1;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
}
