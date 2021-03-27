package gregtechmod.common.gui;

import net.minecraft.entity.player.*;
import gregtechmod.api.interfaces.*;
import gregtechmod.common.containers.*;
import gregtechmod.api.gui.*;
import gregtechmod.api.util.*;

public class GT_GUIContainer_TradeOMat_Settings extends GT_GUIContainerMetaTile_Machine
{
    public GT_GUIContainer_TradeOMat_Settings(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(new GT_Container_TradeOMat_Settings(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/Tradeomat_Settings.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRenderer.drawString("Settings", 8, 5, 4210752);
        this.fontRenderer.drawString("Your Price", 30, 27, 4210752);
        this.fontRenderer.drawString("Your Offer", 30, 45, 4210752); // TODO LOCALE
        if (this.mContainer != null) {
            this.fontRenderer.drawString("Performed Trades: " + GT_Utility.parseNumberToString(((GT_Container_TradeOMat_Settings)this.mContainer).mPerformedTrades.get()), 8, 64, 4210752);
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
