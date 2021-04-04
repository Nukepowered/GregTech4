package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Centrifuge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Centrifuge extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Centrifuge(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Centrifuge(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Centrifuge.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	super.drawGuiContainerForegroundLayer(par1, par2);
    	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        fontRenderer.drawString("Industrial", 110,  4, 4210752);
        fontRenderer.drawString("Centrifuge", 110, 12, 4210752);
        if ((((GT_Container_Centrifuge)mContainer).mDisplayErrorCode.get() & 1) != 0)
        	fontRenderer.drawSplitString(StatCollector.translateToLocal("metatileentity.NOT_ENOUGH_ENERGY"), 8, ySize - 102, 90, 4210752);
        else
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
      
        if (mContainer != null && mContainer.mProgressTime.get()>0) {
        	int tScale = (int) ((mContainer.mProgressTime.get() * 1.0D / mContainer.mMaxProgressTime.get() * 10));
        	tScale = tScale < 0 || tScale > 10 ? 0 : tScale;
        	drawTexturedModalRect(x + 83, y + 33 - tScale, 193, 33 - tScale, 10, tScale);
        	drawTexturedModalRect(x + 78 - tScale, y + 38, 188 - tScale, 38 , tScale, 10);
        	drawTexturedModalRect(x + 83, y + 53, 193, 53, 10, tScale);
        	drawTexturedModalRect(x + 98, y + 38, 208, 38, tScale, 10);
        }
    }
}
