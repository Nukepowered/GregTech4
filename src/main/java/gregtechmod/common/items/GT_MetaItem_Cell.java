package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Cell extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Cell(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aOreDictName, boolean aPlasma, Materials aMaterial) {
		instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.cell.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
		
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		
		GT_ModHandler.addExtractionRecipe(instance.getUnunifiedStack(aMeta, 1), GT_ModHandler.getEmptyCell(1));
		
		if (aPlasma)
			GT_OreDictUnificator.addLater(OrePrefixes.cellPlasma, aMaterial, instance.getUnunifiedStack(aMeta, 1));
		else
			GT_OreDictUnificator.addLater(OrePrefixes.cell, aMaterial, instance.getUnunifiedStack(aMeta, 1));
		
		return instance.getUnunifiedStack(aMeta, 1);
	}
}
