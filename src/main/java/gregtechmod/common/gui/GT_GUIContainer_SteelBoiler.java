package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_SteelBoiler;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_SteelBoiler extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_SteelBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_SteelBoiler(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "SteelBoiler.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("High Pressure Coal Boiler", 8,  4, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	int tScale;
        	tScale = ((GT_Container_SteelBoiler)mContainer).mSteamAmount;
        	if (tScale > 0) drawTexturedModalRect(x + 70, y + 25 + 54-tScale, 194, 54-tScale, 10, tScale);
        	tScale = ((GT_Container_SteelBoiler)mContainer).mWaterAmount;
        	if (tScale > 0) drawTexturedModalRect(x + 83, y + 25 + 54-tScale, 204, 54-tScale, 10, tScale);
        	tScale = ((GT_Container_SteelBoiler)mContainer).mTemperature;
        	if (tScale > 0) drawTexturedModalRect(x + 96, y + 25 + 54-tScale, 214, 54-tScale, 10, tScale);
        	tScale = ((GT_Container_SteelBoiler)mContainer).mProcessingEnergy;
        	if (tScale > 0) drawTexturedModalRect(x +117, y + 44 + 14-tScale, 177, 14-tScale, 15, tScale+1);
        }
    }
}
