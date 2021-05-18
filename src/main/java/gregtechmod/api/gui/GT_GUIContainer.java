package gregtechmod.api.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

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
	
	@SideOnly(Side.CLIENT)
	protected void drawTooltips(int posX, int posY) {
		if (inventorySlots instanceof GT_Container) {
			List<GT_FluidSlot> fluids = ((GT_Container)inventorySlots).fluidSlots;
			for (GT_FluidSlot slot : fluids) {
				if (this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, posX, posY)) {
			    	GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
			    	{
				    	List<String> tooltipData = Lists.newArrayList();
				    	FluidStack fluid = slot.getFluid();
				    	if (GT_Utility.isFluidStackValid(fluid)) {
				    		tooltipData.add(fluid.getLocalizedName());
				    		tooltipData.add(EnumChatFormatting.GRAY + I18n.format("metatileentity.fluid.amount", GT_Utility.parseNumberToString(fluid.amount)));
				    	} else {
				    		tooltipData.add(I18n.format("metatileentity.fluid.empty"));
				    	}
				    	
				    	if (slot.canFill)
				    		tooltipData.add(EnumChatFormatting.GRAY.toString() + EnumChatFormatting.ITALIC.toString() + I18n.format("metatileentity.fluid.fill"));
				    	if (slot.canDrain)
				    		tooltipData.add(EnumChatFormatting.GRAY.toString() + EnumChatFormatting.ITALIC.toString() + I18n.format("metatileentity.fluid.drain"));
				    	
						this.func_146283_a(tooltipData, posX, posY);
			    	}
			    	GL11.glPopAttrib();
				}
			}
		}
	}
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	if (inventorySlots instanceof GT_Container) {
    		for (GT_FluidSlot slot : ((GT_Container)inventorySlots).fluidSlots)
        		slot.draw(this.guiLeft, this.guiTop, this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, par2, par3));
    	}
    	
    	mc.renderEngine.bindTexture(mGUIbackground);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
    	if (mCrashed) {
    		this.mc.thePlayer.closeScreen();
    		return;
    	}
    	
        try {
        	super.drawScreen(par1, par2, par3);
        	this.drawTooltips(par1, par2);
        } catch(Throwable e) {
        	mCrashed = true;
        	GT_Log.log.catching(new RuntimeException("Crash occured while rendering UI, it will be closed", e));
        }
    }
}
