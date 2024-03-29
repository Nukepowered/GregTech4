package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_Container_BasicTank;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_GasTurbine extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_GasTurbine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_BasicTank(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "BasicTank.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        fontRenderer.drawString(I18n.format("metatileentity.GT_GasTurbine.name"), 8, 6, 4210752);
        if (mContainer != null) {
        	fontRenderer.drawString(I18n.format("metatileentity.GT_GasTurbine.amount"), 10, 20, 16448255);
        	fontRenderer.drawString(GT_Utility.parseNumberToString(((GT_Container_BasicTank)mContainer).mContent.get()), 10, 30, 16448255);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
