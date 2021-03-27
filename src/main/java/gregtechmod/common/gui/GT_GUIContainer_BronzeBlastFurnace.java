package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_BronzeBlastFurnace;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BronzeBlastFurnace extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_BronzeBlastFurnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_BronzeBlastFurnace(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "BronzeBlastFurnace.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(I18n.format("metatileentity.GT_BronzeBlastFurnace.name"), 8,  4, 4210752);
        if (mContainer != null) {
        	if (((GT_Container_BronzeBlastFurnace) mContainer).mDisplayErrorCode.get() > 0)
        		fontRenderer.drawString(I18n.format("metatileentity.status.multiblock.incomplete"), 8, ySize - 95, 0x9d2913);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	if (mContainer.mProgressTime.get()>0) drawTexturedModalRect(x + 58, y + 28, 176, 0, Math.max(0, Math.min(20, (mContainer.mProgressTime.get()>0?1:0) + (mContainer.mProgressTime.get() * 20) / (mContainer.mMaxProgressTime.get()<1?1:mContainer.mMaxProgressTime.get()))), 11);
        }
    }
}
