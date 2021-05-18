package gregtechmod.common.gui;

import java.lang.reflect.Field;
import java.util.Collections;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidContainerItem;


public class GT_GUIContainer_Destructopack extends GT_GUIContainer {
	
	protected static Field theSlot;
	
	public GT_GUIContainer_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
    	super(new GT_Container_Item_Destructopack(aInventoryPlayer, aItem), GregTech_API.GUI_PATH + "Destructopack.png");
    }
    
	
	static {
		try {
			theSlot = ReflectionHelper.findField(GuiContainer.class, "field_147006_u", "theSlot");
		} catch (Throwable e) {
			theSlot = null;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void drawTooltips(int posX, int posY) {
		super.drawTooltips(posX, posY);
		
		if (theSlot != null) {
			try {
				ItemStack stack = this.mc.thePlayer.inventory.getItemStack();
				if (stack != null && theSlot.get(this) instanceof GT_Slot_Holo && (FluidContainerRegistry.isContainer(stack) || stack.getItem() instanceof IFluidContainerItem)) {
					this.func_146283_a(Collections.singletonList(I18n.format("item.destructpack.fluid_info")), posX, posY);
				}
			} catch (Throwable e) {}
		}
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