package gregtechmod.api.items;

import net.minecraft.init.Blocks;


public class GT_Drill_Item extends GT_Tool_Item {
	public GT_Drill_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aUnlocalized, "item.GT_Drill_Advanced.tooltip", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
		setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
		addToBlockList(Blocks.monster_egg);
		addToBlockList(Blocks.tnt);
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
}