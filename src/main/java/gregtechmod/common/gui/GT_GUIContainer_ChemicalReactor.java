package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ChemicalReactor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_ChemicalReactor extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ChemicalReactor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ChemicalReactor(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "ChemicalReactor.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Chemical Reactor", 8,  4, 4210752);
        if (((mContainer).mDisplayErrorCode.get() & 1) != 0)
        	fontRenderer.drawString("Insufficient Energy Line!", 8, ySize - 94, 4210752);
        else
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null && mContainer.mProgressTime.get()>0) {
        	int tScale = Math.max(0, Math.min(10, (mContainer.mProgressTime.get()>0?1:0) + (mContainer.mProgressTime.get() * 10) / (mContainer.mMaxProgressTime.get()<1?1:mContainer.mMaxProgressTime.get())));
        	drawTexturedModalRect(x + 73, y + 34, 183, 34, 30, tScale);
        }
    }
}
