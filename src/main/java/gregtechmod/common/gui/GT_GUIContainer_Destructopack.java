package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GT_GUIContainer_Destructopack extends GT_GUIContainer {
	
	public ResourceLocation mGUIbackground;
	
    public GT_GUIContainer_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
    	super(new GT_Container_Item_Destructopack(aInventoryPlayer, aItem), GregTech_API.GUI_PATH + "Destructopack.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString(GT_LanguageManager.mNameListItem[33], 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}