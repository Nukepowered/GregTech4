package gregtechmod.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * Main GUI-Container-Class which basically contains the Code needed to prevent crashes from improperly Coded Items.
 */
public class GT_GUIContainer extends GuiContainer {
	
	public boolean mCrashed = false;
	protected FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	public ResourceLocation mGUIbackground;
	
	public GT_GUIContainer(Container aContainer, String aGUIbackground) {
		super(aContainer);
		mGUIbackground = new ResourceLocation(aGUIbackground);
	}
	
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {}
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	mc.renderEngine.bindTexture(mGUIbackground);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        try {
        	super.drawScreen(par1, par2, par3);
        } catch(Throwable e) {}
    }
}
