package gregtechmod.common;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class GT_FuelHandler implements IFuelHandler {
	
	public GT_FuelHandler() {
		GameRegistry.registerFuelHandler(this);
	}
	
	@Override
	public int getBurnTime(ItemStack aFuel) {
		if (aFuel == null) return 0;
		if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Item.sign, 1)))				return   600;
		if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Item.doorWood, 1)))			return  1800;
		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustWood"))				return   100;
		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallWood"))			return    25;
		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyWood"))			return    11;
		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechItem(0, 1,15)))	return  1600;
		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechItem(0, 1,60)))	return   200;
		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechItem(0, 1,61)))	return   200;
		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechItem(0, 1,62)))	return   200;
		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechItem(0, 1,63)))	return   200;
		return 0;
	}
}