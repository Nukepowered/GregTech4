package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Electrolyzer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Electrolyzer extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Electrolyzer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Electrolyzer(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Electrolyzer.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(I18n.format("metatileentity.GT_Electrolyzer.name"), 8,  4, 4210752);
        if (((mContainer).mDisplayErrorCode.get() & 1) != 0)
        	fontRenderer.drawString(I18n.format("metatileentity.NOT_ENOUGH_ENERGY"), 8, ySize - 94, 4210752);
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
        	drawTexturedModalRect(x + 73, y + 44 - tScale, 183, 44 - tScale, 30, tScale);
        }
    }
}
