package gregtechmod.common.gui;

import net.minecraft.entity.player.*;
import gregtechmod.api.interfaces.*;
import gregtechmod.common.containers.*;
import gregtechmod.api.gui.*;

public class GT_GUIContainer_TradeOMat_Main extends GT_GUIContainerMetaTile_Machine
{
    public GT_GUIContainer_TradeOMat_Main(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(new GT_Container_TradeOMat_Main(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Tradeomat_Main.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRenderer.drawString("Trade-O-Mat", 8, 5, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}