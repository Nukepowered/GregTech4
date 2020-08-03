package gregtechmod.common.gui;

import gregtechmod.api.gui.GT_GUIContainer_BasicMachine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainer_BasicMachine_Extractor extends GT_GUIContainer_BasicMachine {
    public GT_GUIContainer_BasicMachine_Extractor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID, String aName, String aTextureFile) {
        super(aInventoryPlayer, aTileEntity, aID, aName, aTextureFile);
    }
}