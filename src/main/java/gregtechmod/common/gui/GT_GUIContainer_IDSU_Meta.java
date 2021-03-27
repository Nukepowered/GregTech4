package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_IDSU_Meta;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_IDSU_Meta extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_IDSU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_IDSU_Meta(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "IDSU.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	fontRenderer.drawString(I18n.format("metatileentity.GT_IDSU.UI.name"), 11, 8, 16448255);
        if (mContainer != null) {
        	GT_Container_IDSU_Meta idsu = (GT_Container_IDSU_Meta) mContainer;
        	fontRenderer.drawString(I18n.format("metatileentity.GT_IDSU.UI.ID", GT_Utility.parseNumberToString(idsu.mPlayerHash.get())), 11, 17, 16448255);
        	fontRenderer.drawString(I18n.format("metatileentity.STORAGE_EU", GT_Utility.parseNumberToString(idsu.mEnergy.get())), 11, 26, 16448255);
        	fontRenderer.drawString(I18n.format("metatileentity.STORAGE_EU_MAX", GT_Utility.parseNumberToString(idsu.mStorage.get())), 11, 35, 16448255);
        	fontRenderer.drawString(I18n.format("metatileentity.STORAGE_EU_T_IN", GT_Utility.parseNumberToString(mContainer.mInput.get())), 11, 44, 16448255);
        	fontRenderer.drawString(I18n.format("metatileentity.STORAGE_EU_T_OUT", GT_Utility.parseNumberToString(mContainer.mOutput.get())), 11, 53, 16448255);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	GT_Container_IDSU_Meta idsu = (GT_Container_IDSU_Meta) mContainer;
        	int tScale = (int)(116 * (idsu.mEnergy.get() * 1.0D / idsu.mStorage.get()));
    		drawTexturedModalRect(x + 8, y + 73, 0, 251, tScale, 5);
        }
    }
}
