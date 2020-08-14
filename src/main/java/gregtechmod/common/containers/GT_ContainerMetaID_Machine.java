package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Container;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_ContainerMetaID_Machine extends GT_Container {

    protected GT_TileEntityMetaID_Machine mTileEntity;
    
    public GT_ContainerMetaID_Machine (InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity, int aID) {
        super(aInventoryPlayer, aTileEntity);
    	mTileEntity = aTileEntity;
        mID = aID;
        
        addSlots(aInventoryPlayer);
        
        if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer);
        
        detectAndSendChanges();
    }
    
    public int mEnergy, mStorage, mOutput, mInput, mID;
    
    @SuppressWarnings("rawtypes")
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.getWorld().isRemote) return;
        mStorage = mTileEntity.maxEUStore();
    	mEnergy = mTileEntity.getStored();
    	mOutput = mTileEntity.maxEUOutput();
    	mInput = mTileEntity.maxEUInput();
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 0, mEnergy & 65535);
            var1.sendProgressBarUpdate(this, 1, mEnergy >>> 16);
            var1.sendProgressBarUpdate(this, 2, mStorage & 65535);
            var1.sendProgressBarUpdate(this, 3, mStorage >>> 16);
            var1.sendProgressBarUpdate(this, 4, mOutput);
            var1.sendProgressBarUpdate(this, 5, mInput);
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 0: mEnergy = mEnergy & -65536 | par2; break;
    	case 1: mEnergy = mEnergy &  65535 | par2 << 16; break;
    	case 2: mStorage = mStorage & -65536 | par2; break;
    	case 3: mStorage = mStorage &  65535 | par2 << 16; break;
    	case 4: mOutput = par2; break;
    	case 5: mInput = par2; break;
    	}
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return mTileEntity.isUseableByPlayer(player);
    }
}