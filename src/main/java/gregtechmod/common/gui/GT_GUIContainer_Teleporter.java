package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.containers.GT_Container_Teleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class GT_GUIContainer_Teleporter extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_Teleporter(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "Teleporter.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    	fontRenderer.drawString("Teleporter", 46, 8, 16448255); // TODO LOCALE
        if (mContainer != null) {
        	fontRenderer.drawString("X: "   + GT_Utility.parseNumberToString(((GT_Container_Teleporter)mContainer).mTargetX.get()), 46, 16, 16448255);
        	fontRenderer.drawString("Y: "   + GT_Utility.parseNumberToString(((GT_Container_Teleporter)mContainer).mTargetY.get()), 46, 24, 16448255);
        	fontRenderer.drawString("Z: "   + GT_Utility.parseNumberToString(((GT_Container_Teleporter)mContainer).mTargetZ.get()), 46, 32, 16448255);
        	if (((GT_Container_Teleporter)mContainer).mEgg.get()) {
        		int sID = ((GT_Container_Teleporter)mContainer).mTargetD.get();
        		String dimName = null;
        		if (DimensionManager.isDimensionRegistered(sID)) {
        			WorldProvider w = DimensionManager.createProviderFor(sID);
        			dimName = w == null ? String.format("[ID=%d]", sID) : w.getDimensionName();
        		} else dimName = String.format("[ID=%d]", sID);
        		
        		fontRenderer.drawSplitString("Dim: \n" + dimName, 46, 40, 90, 16448255);
        	}
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
