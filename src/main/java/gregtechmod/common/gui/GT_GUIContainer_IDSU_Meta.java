package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_IDSU_Meta;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_IDSU_Meta extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_IDSU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_IDSU_Meta(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "IDSU.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	fontRenderer.drawString("I.D.S.U.", 11, 8, 16448255);
        if (mContainer != null) {
        	GT_Container_IDSU_Meta idsu = (GT_Container_IDSU_Meta) mContainer;
        	fontRenderer.drawString("ID: " + idsu.mPlayerHash.get(), 11, 16, 16448255);
        	fontRenderer.drawString("EU: " + GT_Utility.parseNumberToString(idsu.mEnergy.get()), 11, 24, 16448255);
        	fontRenderer.drawString("MAX: " + GT_Utility.parseNumberToString(idsu.mStorage.get()), 11, 32, 16448255);
        	fontRenderer.drawString("MAX EU/t IN: " + GT_Utility.parseNumberToString(mContainer.mInput.get()), 11, 40, 16448255);
        	fontRenderer.drawString("EU/t OUT: " + GT_Utility.parseNumberToString(mContainer.mOutput.get()), 11, 48, 16448255);
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
