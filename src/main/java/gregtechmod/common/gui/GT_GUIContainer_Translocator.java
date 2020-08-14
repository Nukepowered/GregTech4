package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Translocator;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_Translocator extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Translocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Translocator(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Translocator.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
