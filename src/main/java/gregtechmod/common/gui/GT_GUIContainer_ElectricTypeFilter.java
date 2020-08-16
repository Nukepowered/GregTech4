package gregtechmod.common.gui;

import net.minecraft.entity.player.*;
import gregtechmod.api.interfaces.*;
import gregtechmod.common.containers.*;
import gregtechmod.api.gui.*;

public class GT_GUIContainer_ElectricTypeFilter extends GT_GUIContainerMetaTile_Machine
{
    public GT_GUIContainer_ElectricTypeFilter(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ElectricTypeFilter(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricTypeFilter.png");
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
