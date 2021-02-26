package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Grinder;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Grinder extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Grinder(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Grinder(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Grinder.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Industrial Grinder", 8,  4, 4210752);
        if (!((GT_Container_Grinder)mContainer).mMachine)
        	fontRenderer.drawString("Incomplete Machine Casing!", 8, ySize - 104, 4210752);
        if (((mContainer).mDisplayErrorCode & 1) != 0)
        	fontRenderer.drawString("Insufficient Energy Line!", 8, ySize - 94, 4210752);
        else
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	GT_Container_Grinder cont = (GT_Container_Grinder) mContainer;
        	int tScale = Math.max(0, Math.min(20, (cont.mProgressTime>0?1:0) + (cont.mProgressTime * 20) / (cont.mMaxProgressTime<1?1:cont.mMaxProgressTime)));
        	if (mContainer.mProgressTime > 0) drawTexturedModalRect(x + 58, y + 28, 176, 0, tScale, 11);

        	tScale = cont.mWaterAmount/550;
        	if (tScale > 0)
        		drawTexturedModalRect(x + 33, y + 33 + 18 - tScale, 176, 33 + 18 - tScale, 18, tScale);
        }
    }
}
