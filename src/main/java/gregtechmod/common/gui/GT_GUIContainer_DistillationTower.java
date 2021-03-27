package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_DistillationTower extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_DistillationTower(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_DistillationTower(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "DistillationTower.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	fontRenderer.drawSplitString(I18n.format("metatileentity.GT_DistillationTower.name"), 116, 4, 60, 4210752);
        if (!((GT_Container_DistillationTower)mContainer).mMachine.get())
        	fontRenderer.drawSplitString(I18n.format("metatileentity.status.multiblock.incomplete"), 116, 30, 60, 0xF00000);
        if (((mContainer).mDisplayErrorCode.get() & 1) != 0)
        	fontRenderer.drawSplitString(I18n.format("metatileentity.NOT_ENOUGH_ENERGY"), 116, 48, 60, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null && mContainer.mProgressTime.get()>0) {
        	GT_Container_DistillationTower cont = (GT_Container_DistillationTower)mContainer;
        	int tScale = Math.max(0, Math.min(72, (cont.mProgressTime.get()>0?1:0) + (cont.mProgressTime.get() * 72) / (cont.mMaxProgressTime.get()<1?1:cont.mMaxProgressTime.get())));
        	drawTexturedModalRect(x + 80, y + 76-tScale, 176, 76-tScale, 16, tScale);
        }
    }
}
