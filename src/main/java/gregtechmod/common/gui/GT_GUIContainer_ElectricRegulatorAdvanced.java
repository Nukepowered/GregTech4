package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricRegulatorAdvanced;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricRegulatorAdvanced extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ElectricRegulatorAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ElectricRegulatorAdvanced(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "ElectricRegulatorAdvanced.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[0], 120,  9, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[1], 137,  9, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[2], 155,  9, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[3], 120, 26, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[4], 137, 26, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[5], 155, 26, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[6], 120, 43, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[7], 137, 43, 16448255);
    	fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)mContainer).mTargetSlots[8], 155, 43, 16448255);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
