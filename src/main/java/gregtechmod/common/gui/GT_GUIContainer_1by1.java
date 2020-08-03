package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_1by1;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_1by1 extends GT_GUIContainerMetaTile_Machine {
	
	private final String mName;
	
    public GT_GUIContainer_1by1(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID, String aName) {
        super(new GT_Container_1by1(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID, GregTech_API.GUI_PATH + "1by1.png");
        mName = aName;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(mName, 8,  4, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
