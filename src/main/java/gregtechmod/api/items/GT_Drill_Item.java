package gregtechmod.api.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;


public class GT_Drill_Item extends GT_Tool_Item {
	public GT_Drill_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aItem, aUnlocalized, aEnglish, "For quickly making Holes", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
		setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
		addToBlockList(Blocks.monster_egg);
		addToBlockList(Blocks.tnt);
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
}