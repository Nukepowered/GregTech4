package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_MaintenanceHatch;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_MaintenanceHatch extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_MaintenanceHatch(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_MaintenanceHatch(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Maintenance.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Maintenance Hatch", 8,  4, 4210752);
        fontRenderer.drawString("Click with Tool to repair.", 8,  12, 4210752); // TODO locale
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
