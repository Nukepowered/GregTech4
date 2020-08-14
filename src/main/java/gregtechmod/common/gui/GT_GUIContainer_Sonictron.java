package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.common.containers.GT_Container_Sonictron;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_Sonictron extends GT_GUIContainerMetaID_Machine {
	
    public GT_GUIContainer_Sonictron(InventoryPlayer aInventoryPlayer, GT_TileEntity_Sonictron aTileEntity) {
        super(new GT_Container_Sonictron(aInventoryPlayer, aTileEntity), aTileEntity, GregTech_API.GUI_PATH + "Sonictron.png");
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
