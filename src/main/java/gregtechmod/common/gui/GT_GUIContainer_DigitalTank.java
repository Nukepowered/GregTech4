package gregtechmod.common.gui;

import net.minecraft.entity.player.*;
import gregtechmod.api.interfaces.*;
import gregtechmod.api.gui.*;
import net.minecraft.util.*;
import gregtechmod.api.util.*;

public class GT_GUIContainer_DigitalTank extends GT_GUIContainerMetaTile_Machine
{
    public GT_GUIContainer_DigitalTank(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(new GT_Container_BasicTank(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/BasicTank.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString("Digital Tank", 8, 6, 4210752);
        if (this.mContainer != null) {
            this.fontRenderer.drawString("Liquid Amount", 10, 20, 16448255);
            this.fontRenderer.drawString(GT_Utility.parseNumberToString(((GT_Container_BasicTank)this.mContainer).mContent.get()), 10, 30, 16448255);
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
