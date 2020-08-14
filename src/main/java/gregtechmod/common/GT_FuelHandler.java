package gregtechmod.common;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class GT_FuelHandler implements IFuelHandler {
	
	public GT_FuelHandler() {
		GameRegistry.registerFuelHandler(this);
	}
	
	@Override
	public int getBurnTime(ItemStack aFuel) {
		if (aFuel == null || aFuel.getItem() == null) return 0;
        short rFuelValue = 0;
        if(aFuel.getItem() instanceof GT_MetaGenerated_Item) {
           Short tNBT = ((GT_MetaGenerated_Item)aFuel.getItem()).mBurnValues.get(Short.valueOf((short)aFuel.getItemDamage()));
           if(tNBT != null) {
              rFuelValue = (short)Math.max(rFuelValue, tNBT.shortValue());
           }
        }

		NBTTagCompound tNBT1 = aFuel.getTagCompound();
		if (tNBT1 != null) {
			short tValue = tNBT1.getShort("GT.ItemFuelValue");
			rFuelValue = (short) Math.max(rFuelValue, tValue);
		}

		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "gemLignite")) {
			rFuelValue = (short) Math.max(rFuelValue, 300);
		}

		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustWood")) {
			rFuelValue = (short) Math.max(rFuelValue, 100);
		}

		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustSmallWood")) {
			rFuelValue = (short) Math.max(rFuelValue, 25);
		}

		if (GT_OreDictUnificator.isItemStackInstanceOf(aFuel, "dustTinyWood")) {
			rFuelValue = (short) Math.max(rFuelValue, 11);
		}

		if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Items.sign, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 600);
		}

		if (GT_Utility.areStacksEqual(aFuel, new ItemStack(Items.wooden_door, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 600);
		}

		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(15, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 1600);
		}

		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(60, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 200);
		}

		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(61, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 200);
		}

		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(62, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 200);
		}

		if (GT_Utility.areStacksEqual(aFuel, GregTech_API.getGregTechMaterial(63, 1))) {
			rFuelValue = (short) Math.max(rFuelValue, 200);
		}

		return rFuelValue;
	}
}