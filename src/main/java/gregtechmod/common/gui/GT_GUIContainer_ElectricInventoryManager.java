package gregtechmod.common.gui;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricInventoryManager;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_ElectricInventoryManager extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ElectricInventoryManager(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(new GT_Container_ElectricInventoryManager(aInventoryPlayer, aTileEntity), GregTech_API.GUI_PATH + "InventoryManager.png");
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	Byte[] rangedDirs = ((GT_Container_ElectricInventoryManager)mContainer).mRangeDirections.get();
        	Byte[] targetDirs = ((GT_Container_ElectricInventoryManager)mContainer).mTargetDirections.get();
        	int targetEnergy = ((GT_Container_ElectricInventoryManager)mContainer).mTargetEnergy.get();
        	int targetInOut = ((GT_Container_ElectricInventoryManager)mContainer).mTargetInOut.get();
        	
        	drawTexturedModalRect(x +   4, y +  4, rangedDirs[0]*18, 202, 18, 54);
        	drawTexturedModalRect(x +  60, y +  4, rangedDirs[1]*18, 202, 18, 54);
        	drawTexturedModalRect(x +  79, y +  4, rangedDirs[2]*18, 202, 18, 54);
        	drawTexturedModalRect(x + 135, y +  4, rangedDirs[3]*18, 202, 18, 54);
        	
        	drawTexturedModalRect(x +  23, y + 59, rangedDirs[0]*18+126, 166, 18, 18);
        	drawTexturedModalRect(x +  41, y + 59, rangedDirs[1]*18+126, 166, 18, 18);
        	drawTexturedModalRect(x +  98, y + 59, rangedDirs[2]*18+126, 166, 18, 18);
        	drawTexturedModalRect(x + 116, y + 59, rangedDirs[3]*18+126, 166, 18, 18);
        	
        	drawTexturedModalRect(x +   4, y + 59, 108, (targetEnergy & 1)!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  60, y + 59, 108, (targetEnergy & 2)!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  79, y + 59, 108, (targetEnergy & 4)!=0?184:166, 18, 18);
        	drawTexturedModalRect(x + 135, y + 59, 108, (targetEnergy & 8)!=0?184:166, 18, 18);
        	
        	int i = -1;
        	
        	drawTexturedModalRect(x +  23, y +  4, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  23, y + 22, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  23, y + 40, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  41, y +  4, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  41, y + 22, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  41, y + 40, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  98, y +  4, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  98, y + 22, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x +  98, y + 40, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x + 116, y +  4, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x + 116, y + 22, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        	drawTexturedModalRect(x + 116, y + 40, targetDirs[++i]*18, (targetInOut&(1<<i))!=0?184:166, 18, 18);
        }
    }
}
