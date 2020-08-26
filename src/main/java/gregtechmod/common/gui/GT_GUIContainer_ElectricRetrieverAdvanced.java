package gregtechmod.common.gui;

import net.minecraft.entity.player.*;
import gregtechmod.api.interfaces.*;
import gregtechmod.common.containers.*;
import gregtechmod.api.gui.*;

public class GT_GUIContainer_ElectricRetrieverAdvanced extends GT_GUIContainerMetaTile_Machine
{
    public GT_GUIContainer_ElectricRetrieverAdvanced(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ElectricRetrieverAdvanced(aInventoryPlayer, aTileEntity), "gregtech_addon:textures/gui/ElectricRetrieverAdvanced.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[0], 120, 9, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[1], 137, 9, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[2], 155, 9, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[3], 120, 26, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[4], 137, 26, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[5], 155, 26, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[6], 120, 43, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[7], 137, 43, 16448255);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRetrieverAdvanced)this.mContainer).mTargetSlots[8], 155, 43, 16448255);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        final int x = (this.width - this.xSize) / 2;
        final int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
