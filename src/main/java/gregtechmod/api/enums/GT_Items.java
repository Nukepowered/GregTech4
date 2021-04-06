package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IItemContainer;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Class containing all non-OreDict Items of GregTech.
 */
public enum GT_Items implements IItemContainer {
	TE_Slag,
	TE_Slag_Rich,
	TE_Rockwool,
	TE_Hardened_Glass,
	IC2_Scrap,
	IC2_Scrapbox,
	IC2_Fertilizer,
	IC2_Mixed_Metal_Ingot,
	IC2_Resin,
	IC2_Crop_Seeds,
	IC2_Grin_Powder,
	IC2_Energium_Dust,
	IC2_Compressed_Coal_Ball,
	IC2_Compressed_Coal_Chunk,
	IC2_Fuel_Rod_Empty,
	IC2_Food_Can_Empty,
	IC2_Food_Can_Filled,
	IC2_Food_Can_Spoiled,
	IC2_Industrial_Diamond,
	Shape_Empty,
	Shape_Mold_Plate,
	Shape_Mold_Casing,
	Shape_Mold_Gear,
	Shape_Mold_Credit,
	Shape_Extruder_Plate,
	Shape_Extruder_Cell,
	Shape_Extruder_Ring,
	Shape_Extruder_Rod,
	Shape_Extruder_Bolt,
	Shape_Extruder_Ingot,
	Shape_Extruder_Wire,
	Shape_Extruder_Casing,
	Shape_Extruder_Pipe_Small,
	Shape_Extruder_Pipe_Medium,
	Shape_Extruder_Pipe_Large,
	Shape_Extruder_Block,
	Shape_Extruder_Sword,
	Shape_Extruder_Pickaxe,
	Shape_Extruder_Shovel,
	Shape_Extruder_Axe,
	Shape_Extruder_Hoe,
	Shape_Extruder_Hammer,
	Shape_Extruder_File,
	Shape_Extruder_Saw,
	Shape_Extruder_Gear,
	Credit_Copper,
	Credit_Iron,
	Credit_Silver,
	Credit_Gold,
	Credit_Platinum,
	Credit_Osmium,
	Credit_Greg_Copper,
	Credit_Greg_Cupronickel,
	Credit_Greg_Silver,
	Credit_Greg_Gold,
	Credit_Greg_Platinum,
	Credit_Greg_Osmium,
	Credit_Greg_Naquadah,
	Credit_Greg_Neutronium,
	Coin_Gold_Ancient,
	Coin_Doge,
	Coin_Chocolate,
	Cell_Empty,
	Cell_Water,
	Cell_Lava,
	Cell_Air,
	Can_Food_Empty,
	Can_Food_Filled,
	Can_Food_Spoiled,
	Bottle_Purple_Drink,
	Food_Potato_On_Stick,
	Food_Potato_On_Stick_Roasted,
	Crop_Drop_Argentia,
	Crop_Drop_Plumbilia,
	Crop_Drop_Indigo,
	Crop_Drop_Ferru,
	Crop_Drop_Aurelia,
	Crop_Drop_OilBerry,
	Crop_Drop_MilkWart,
	Crop_Drop_BobsYerUncleRanks,
	Crop_Drop_Coppon,
	Crop_Drop_Tine,
	Circuit_Integrated,
	Circuit_Board_Basic,
	Circuit_Board_Advanced,
	Circuit_Board_Elite,
	Circuit_Parts_Advanced,
	Circuit_Basic,
	Circuit_Advanced,
	Circuit_Data,
	Circuit_Elite,
	Circuit_Master,
	Circuit_Ultimate,
	Battery_Hull_LV,
	Battery_Hull_MV,
	Battery_SU_LV_SulfuricAcid,
	Battery_SU_LV_Mercury,
	Battery_SU_MV_SulfuricAcid,
	Battery_SU_MV_Mercury,
	Battery_RE_LV_Lithium,
	Battery_RE_LV_Sodium,
	Battery_RE_MV_Lithium,
	Battery_RE_MV_Sodium,
	ZPM,
	Fuel_Can_Plastic_Empty,
	Fuel_Can_Plastic_Filled,
	Upgrade_Overclocker,
	Upgrade_Transformer,
	Upgrade_Battery,
	McGuffium_239,
	NC_SensorCard,
	NC_SensorKit,
	Tool_Mortar_Iron,
	Tool_Mortar_Wood,
	Tool_Cheat,
	Tool_Scanner,
	Tool_Crowbar_Iron,
	Tool_Screwdriver_Iron,
	Tool_Screwdriver_TungstenSteel,
	Tool_Screwdriver_Electric,
	Tool_Wrench_Iron,
	Tool_Wrench_Bronze,
	Tool_Wrench_Steel,
	Tool_Wrench_TungstenSteel,
	Tool_Wrench_Electric,
	Tool_Wrench_Advanced,
	Tool_Hammer_Forge,
	Tool_Hammer_Rubber,
	Tool_Hammer_Plastic,
	Tool_Hammer_Iron,
	Tool_Hammer_Bronze,
	Tool_Hammer_Steel,
	Tool_Hammer_TungstenSteel,
	Tool_File_Iron,
	Tool_File_Bronze,
	Tool_File_Steel,
	Tool_File_TungstenSteel,
	Tool_Saw_Iron,
	Tool_Saw_Bronze,
	Tool_Saw_Steel,
	Tool_Saw_TungstenSteel,
	Tool_Saw_Electric,
	Tool_Saw_Advanced,
	Tool_Drill_Advanced,
	Tool_SolderingIron_Electric,
	Tool_SolderingMaterial_Tin,
	Tool_SolderingMaterial_Lead,
	Tool_Rockcutter,
	Tool_Teslastaff,
	Tool_DataOrb,
	Tool_Sonictron,
	Tool_Destructopack,
	Tool_Sword_Flint,
	Tool_Sword_Bronze,
	Tool_Sword_Steel,
	Tool_Sword_TungstenSteel,
	Tool_Pickaxe_Flint,
	Tool_Pickaxe_Bronze,
	Tool_Pickaxe_Steel,
	Tool_Pickaxe_TungstenSteel,
	Tool_Shovel_Flint,
	Tool_Shovel_Bronze,
	Tool_Shovel_Steel,
	Tool_Shovel_TungstenSteel,
	Tool_Axe_Flint,
	Tool_Axe_Bronze,
	Tool_Axe_Steel,
	Tool_Axe_TungstenSteel,
	Tool_Hoe_Flint,
	Tool_Hoe_Bronze,
	Tool_Hoe_Steel,
	Tool_Hoe_TungstenSteel,
	Tool_Scoop_Aluminium,
	Tool_Jackhammer_Bronze,
	Tool_Jackhammer_Steel,
	Tool_Jackhammer_Diamond,
	Spray_Empty,
	Spray_Bug,
	Spray_Ice,
	Spray_Hardener,
	Spray_CFoam,
	Spray_Pepper,
	Spray_Hydration,
	Spray_Color_00,
	Spray_Color_01,
	Spray_Color_02,
	Spray_Color_03,
	Spray_Color_04,
	Spray_Color_05,
	Spray_Color_06,
	Spray_Color_07,
	Spray_Color_08,
	Spray_Color_09,
	Spray_Color_10,
	Spray_Color_11,
	Spray_Color_12,
	Spray_Color_13,
	Spray_Color_14,
	Spray_Color_15,
	Armor_Cheat,
	Armor_Cloaking,
	Armor_Lamp,
	Armor_LithiumPack,
	Armor_LapotronicPack,
	Armor_ForceField,
	Energy_LapotronicOrb,
	Reactor_Coolant_He_1,
	Reactor_Coolant_He_3,
	Reactor_Coolant_He_6,
	Reactor_Coolant_NaK_1,
	Reactor_Coolant_NaK_3,
	Reactor_Coolant_NaK_6,
	Reactor_NeutronReflector,
	Component_Turbine_Bronze,
	Component_Turbine_Steel,
	Component_Turbine_Magnalium,
	Component_Turbine_TungstenSteel,
	Component_Turbine_Carbon,
	Component_LavaFilter,
	
	Machine_Bronze_Boiler,
	Machine_Bronze_Furnace,
	Machine_Bronze_CraftingTable,
	Machine_Bronze_Macerator,
	Machine_Bronze_Extractor,
	Machine_Bronze_Hammer,
	Machine_Bronze_Compressor,
	Machine_Bronze_BlastFurnace,
	Machine_Bronze_AlloySmelter,
	Machine_Steel_Boiler,
	Machine_Steel_Furnace,
	
	Frame_Iron,
	Frame_Aluminium,
	Frame_Steel,
	Frame_StainlessSteel,
	Frame_TungstenSteel,
	
	Pipe_Bronze_Small,
	Pipe_Bronze_Medium,
	Pipe_Bronze_Large,
	Pipe_Steel_Small,
	Pipe_Steel_Medium,
	Pipe_Steel_Large,
	Pipe_StainlessSteel_Small,
	Pipe_StainlessSteel_Medium,
	Pipe_StainlessSteel_Large,
	Pipe_TungstenSteel_Small,
	Pipe_TungstenSteel_Medium,
	Pipe_TungstenSteel_Large,
	Pipe_Brass_Medium,
	Pipe_Brass_Large,
	Pipe_Electrum_Medium,
	Pipe_Electrum_Large,
	Pipe_Platinum_Medium,
	Pipe_Platinum_Large,
	
	NULL;
	
	public static final GT_Items[] SPRAY_CAN_DYES = {Spray_Color_00, Spray_Color_01, Spray_Color_02, Spray_Color_03, Spray_Color_04, Spray_Color_05, Spray_Color_06, Spray_Color_07, Spray_Color_08, Spray_Color_09, Spray_Color_10, Spray_Color_11, Spray_Color_12, Spray_Color_13, Spray_Color_14, Spray_Color_15};
	private ItemStack mStack;
	private boolean mHasNotBeenSet = true;
	
	public IItemContainer set(Item aItem) {
		mHasNotBeenSet = false;
		if (aItem == null) return this;
		ItemStack aStack = new ItemStack(aItem, 1, 0);
		mStack = GT_Utility.copyAmount(1, aStack);
		return this;
	}
	
	public IItemContainer set(ItemStack aStack) {
		mHasNotBeenSet = false;
		mStack = GT_Utility.copyAmount(1, aStack);
		return this;
	}
	
	@Override
	public Item getItem() {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return null;
		return mStack.getItem();
	}
	
	@Override
	public Block getBlock() {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		return Block.getBlockFromItem(getItem());
	}
	
	@Override
	public final boolean hasBeenSet() {
		return !mHasNotBeenSet;
	}
	
	@Override
	public boolean isStackEqual(Object aStack) {
		return isStackEqual(aStack, false, false);
	}
	
	@Override
	public boolean isStackEqual(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
		if (GT_Utility.isStackInvalid(aStack)) return false;
		return GT_Utility.areUnificationsEqual((ItemStack)aStack, aWildcard?getWildcard(1):get(1), aIgnoreNBT);
	}
	
	@Override
	public ItemStack get(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, GT_Utility.cast(aReplacements));
		return GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(mStack));
	}
	
	@Override
	public ItemStack getWildcard(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, GT_Utility.cast(aReplacements));
		return GT_Utility.copyAmountAndMetaData(aAmount, GregTech_API.ITEM_WILDCARD_DAMAGE, GT_OreDictUnificator.get(mStack));
	}
	
	@Override
	public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, GT_Utility.cast(aReplacements));
		return GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(mStack));
	}
	
	@Override
	public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, GT_Utility.cast(aReplacements));
		return GT_Utility.copyAmountAndMetaData(aAmount, mStack.getMaxDamage()-1, GT_OreDictUnificator.get(mStack));
	}
	
	@Override
	public ItemStack getWithCharge(long aAmount, int aEnergy, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (GT_Utility.isStackInvalid(rStack)) return null;
		GT_ModHandler.chargeElectricItem(rStack, aEnergy, Integer.MAX_VALUE, true, false);
		return GT_Utility.copyAmount(aAmount, rStack);
	}
	
	@Override
	public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		if (GT_Utility.isStackInvalid(mStack)) return GT_Utility.copyAmount(aAmount, GT_Utility.cast(aReplacements));
		return GT_Utility.copyAmountAndMetaData(aAmount, aMetaValue, GT_OreDictUnificator.get(mStack));
	}
	
	@Override
	public IItemContainer registerOre(Object... aOreNames) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames) GT_OreDictUnificator.registerOre(tOreName, get(1));
		return this;
	}
	
	@Override
	public IItemContainer registerWildcardAsOre(Object... aOreNames) {
		if (mHasNotBeenSet) throw new IllegalAccessError("The Enum '" + toString() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames) GT_OreDictUnificator.registerOre(tOreName, getWildcard(1));
		return this;
	}
}