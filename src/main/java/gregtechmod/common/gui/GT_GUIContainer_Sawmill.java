package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_Sawmill;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_Sawmill extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Sawmill(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Sawmill(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Sawmill.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(I18n.format("metatileentity.GT_Sawmill.name"), 8,  4, 4210752);
        if (!((GT_Container_Sawmill)mContainer).mMachine.get())
        	fontRenderer.drawString(I18n.format("metatileentity.status.multiblock.incomplete"), 8, ySize - 104, 0xF00000);
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
        
        if (mContainer != null) {
        	GT_Container_Sawmill cont = (GT_Container_Sawmill) mContainer;
        	int tScale = Math.max(0, Math.min(20, (cont.mProgressTime.get() > 0?1:0) + (cont.mProgressTime.get() * 20) / (cont.mMaxProgressTime.get()<1?1:cont.mMaxProgressTime.get())));
        	if (mContainer.mProgressTime.get() > 0) drawTexturedModalRect(x + 58, y + 28, 176, 0, tScale, 11);

        	tScale = ((GT_Container_Sawmill)mContainer).mWaterAmount.get()/550;
        	if (tScale > 0)
        		drawTexturedModalRect(x + 33, y + 33 + 18 - tScale, 176, 33 + 18 - tScale, 18, tScale);
        }
    }
}
