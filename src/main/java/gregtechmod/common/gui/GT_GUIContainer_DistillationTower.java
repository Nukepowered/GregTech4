package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_DistillationTower extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_DistillationTower(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
        super(new GT_Container_DistillationTower(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID, GregTech_API.GUI_PATH + "DistillationTower.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Distillation"		, 116,  4, 4210752);
        fontRenderer.drawString("Tower"				, 116, 12, 4210752);
        if (!((GT_Container_DistillationTower)mContainer).mMachine) {
        	fontRenderer.drawString("Incomplete"	, 116, 20, 4210752);
        	fontRenderer.drawString("Machine"		, 116, 28, 4210752);
        	fontRenderer.drawString("Casing!"		, 116, 36, 4210752);
        }
        if (((mContainer).mDisplayErrorCode & 1) != 0) {
        	fontRenderer.drawString("Insufficient"	, 116, 44, 4210752);
        	fontRenderer.drawString("Energy"		, 116, 52, 4210752);
        	fontRenderer.drawString("Line!"			, 116, 60, 4210752);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null && mContainer.mProgressTime>0) {
        	int tScale = ((GT_Container_DistillationTower)mContainer).mProgressScale;
        	drawTexturedModalRect(x + 80, y + 76-tScale, 176, 76-tScale, 16, tScale);
        }
    }
}
