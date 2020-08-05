package gregtechmod.api.items;

import net.minecraft.init.Blocks;


public class GT_Drill_Item extends GT_Tool_Item {
	public GT_Drill_Item(String aName, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aName, "For quickly making Holes", aMaxDamage, aEntityDamage, -1, aDisChargedGTID, aToolQuality, aToolStrength); // FIXME lang
		setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
		addToBlockList(Blocks.tnt);
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
}