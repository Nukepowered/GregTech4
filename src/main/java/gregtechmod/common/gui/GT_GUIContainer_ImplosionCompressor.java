package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ImplosionCompressor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GT_GUIContainer_ImplosionCompressor extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ImplosionCompressor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ImplosionCompressor(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Implosion.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
        fontRenderer.drawString("Implosion Compressor", 8,  4, 4210752);
        if (!((GT_Container_ImplosionCompressor)mContainer).mMachine)
        	fontRenderer.drawString("Incomplete Machine Casing!", 8, ySize - 103, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null && mContainer.mProgressTime>0) {
        	GT_Container_ImplosionCompressor cont = (GT_Container_ImplosionCompressor) mContainer;
        	int tScale = Math.max(0, Math.min(20, (cont.mProgressTime>0?1:0) + (cont.mProgressTime * 20) / (cont.mMaxProgressTime<1?1:cont.mMaxProgressTime)));
        	this.drawTexturedModalRect(x + 58, y + 28, 176, 0, tScale, 11);
        }
    }
}
