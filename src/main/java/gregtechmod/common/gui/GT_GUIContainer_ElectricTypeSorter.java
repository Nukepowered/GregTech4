package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricTypeSorter;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricTypeSorter extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ElectricTypeSorter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ElectricTypeSorter(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "ElectricTypeSorter.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	String tINString = "", tOUTString = "";
    	switch (mContainer.mTileEntity.getFrontFacing()) {
    	case  0: tINString = "UP"; break;
    	case  1: tINString = "DOWN"; break;
    	case  2: tINString = "SOUTH"; break;
    	case  3: tINString = "NORTH"; break;
    	case  4: tINString = "EAST"; break;
    	case  5: tINString = "WEST"; break;
    	default: tINString = "FAIL"; break;
    	}
    	
    	switch (((GT_Container_ElectricTypeSorter)mContainer).mTargetDirection) {
    	case  0: tOUTString = "DOWN"; break;
    	case  1: tOUTString = "UP"; break;
    	case  2: tOUTString = "NORTH"; break;
    	case  3: tOUTString = "SOUTH"; break;
    	case  4: tOUTString = "WEST"; break;
    	case  5: tOUTString = "EAST"; break;
    	default: tOUTString = "FAIL"; break;
    	}
    	
    	fontRenderer.drawString(tINString, 138, 7, 16448255);
    	fontRenderer.drawString(tOUTString, 100, 65, 16448255);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	drawTexturedModalRect(x + 70, y + 22, ((GT_Container_ElectricTypeSorter)mContainer).mMode*18, 166, 18, 18);
        }
    }
}
