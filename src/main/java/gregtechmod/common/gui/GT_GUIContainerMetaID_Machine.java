package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainerMetaID_Machine extends GT_GUIContainer {

	public GT_TileEntityMetaID_Machine mTileEntity;
	public GT_ContainerMetaID_Machine  mContainer;
	
	public GT_GUIContainerMetaID_Machine(GT_ContainerMetaID_Machine aContainer, GT_TileEntityMetaID_Machine aTileEntity, String aGUIbackground) {
		super(aContainer, aGUIbackground);
        mTileEntity = aTileEntity;
        mContainer  = aContainer;
	}
	
    public GT_GUIContainerMetaID_Machine(InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity, String aGUIbackground) {
        this(new GT_ContainerMetaID_Machine(aInventoryPlayer, aTileEntity), aTileEntity, aGUIbackground);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}