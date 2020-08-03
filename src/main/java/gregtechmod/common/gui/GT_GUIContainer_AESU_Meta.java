package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_AESU_Meta;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_AESU_Meta extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_AESU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
        super(new GT_Container_AESU_Meta(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID, GregTech_API.GUI_PATH + "AESU.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	fontRenderer.drawString("A.E.S.U.", 11, 8, 16448255);
        if (mContainer != null) {
        	fontRenderer.drawString("EU: " + toNumber(mContainer.mEnergy), 11, 16, 16448255);
        	fontRenderer.drawString("MAX: " + toNumber(mContainer.mStorage), 11, 24, 16448255);
        	fontRenderer.drawString("MAX EU/t IN: " + toNumber(mContainer.mInput), 11, 32, 16448255);
        	fontRenderer.drawString("EU/t OUT: " + toNumber(mContainer.mOutput), 11, 40, 16448255);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	int tScale = mContainer.mEnergy/Math.max(1, mContainer.mStorage/97);
    		drawTexturedModalRect(x + 8, y + 73, 0, 251, tScale, 5);
        }
    }
}
