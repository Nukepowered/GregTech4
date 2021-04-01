package gregtechmod.api.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * The GUI-Container I use for all my Basic Machines
 * 
 * As the NEI-RecipeTransferRect Handler can't handle one GUI-Class for all GUIs I needed to produce some dummy-classes which extend this class
 */
public class GT_GUIContainer_MultiMachine extends GT_GUIContainerMetaTile_Machine {
	
	String mName = "";
	
    public GT_GUIContainer_MultiMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_MultiMachine(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "MultiblockDisplay.png");
        IMetaTileEntity mte = aTileEntity.getMetaTileEntity();
        mName = mte != null ? String.format("metatileentity.%s.name", mte.getInventoryName()) : "null";
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(I18n.format(mName), 10,  8, 16448255);
        
        if (mContainer != null) {
        	GT_Container_MultiMachine m = (GT_Container_MultiMachine) mContainer;
        	int errorCode = m.mDisplayErrorCode.get();
        	if ((errorCode &  1) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.0"), 10, 16, 16448255);
        	if ((errorCode &  2) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.1"), 10, 24, 16448255);
        	if ((errorCode &  4) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.2"), 10, 32, 16448255);
        	if ((errorCode &  8) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.3"), 10, 40, 16448255);
        	if ((errorCode & 16) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.4"), 10, 48, 16448255);
        	if ((errorCode & 32) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.5"), 10, 56, 16448255);
        	if ((errorCode & 64) != 0) fontRenderer.drawString(I18n.format("metatileentity.status.malfunction.6"), 10, 64, 16448255);
        	
        	if (errorCode == 0) {
        		if (m.mActive.get()) {
        			fontRenderer.drawString(I18n.format("metatileentity.status.multiblock.running"), 10, 16, 16448255);
    			} else {
    				fontRenderer.drawSplitString(I18n.format("metatileentity.status.multiblock.idle"), 10, 16, 150, 16448255);
    			}
        	}
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
