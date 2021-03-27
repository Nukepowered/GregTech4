package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_FusionComputer;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_FusionComputer extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_FusionComputer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_FusionComputer(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "FusionComputer.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	if (mContainer != null) {
    		GT_Container_FusionComputer m = (GT_Container_FusionComputer) mContainer;
    		if (m.mDisplayErrorCode.get() > 0)
    			fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.6"), 7, 100, 0xF00000);
        }
    }
    	
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	int tScale = (int)(147 * (mContainer.mEnergy.get() * 1.0D / mContainer.mStorage.get()));
    		drawTexturedModalRect(x + 5, y + 156, 0, 251, tScale, 5);
        }
    }
}
