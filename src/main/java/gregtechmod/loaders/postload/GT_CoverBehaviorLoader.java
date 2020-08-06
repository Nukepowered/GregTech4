package gregtechmod.loaders.postload;

import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.common.covers.GT_Cover_ControlsWork;
import gregtechmod.common.covers.GT_Cover_Conveyor;
import gregtechmod.common.covers.GT_Cover_Crafting;
import gregtechmod.common.covers.GT_Cover_DoesWork;
import gregtechmod.common.covers.GT_Cover_Drain;
import gregtechmod.common.covers.GT_Cover_EUMeter;
import gregtechmod.common.covers.GT_Cover_EnergyOnly;
import gregtechmod.common.covers.GT_Cover_ItemMeter;
import gregtechmod.common.covers.GT_Cover_ItemValve;
import gregtechmod.common.covers.GT_Cover_LiquidMeter;
import gregtechmod.common.covers.GT_Cover_RedstoneConductor;
import gregtechmod.common.covers.GT_Cover_RedstoneOnly;
import gregtechmod.common.covers.GT_Cover_RedstoneReceiverExternal;
import gregtechmod.common.covers.GT_Cover_RedstoneReceiverInternal;
import gregtechmod.common.covers.GT_Cover_RedstoneSignalizer;
import gregtechmod.common.covers.GT_Cover_RedstoneTransmitterExternal;
import gregtechmod.common.covers.GT_Cover_RedstoneTransmitterInternal;
import gregtechmod.common.covers.GT_Cover_Screen;
import gregtechmod.common.covers.GT_Cover_SolarPanel;
import gregtechmod.common.covers.GT_Cover_Valve;
import gregtechmod.common.covers.GT_Cover_Vent;
import gregtechmod.common.items.GT_MetaItem_Component;
import net.minecraft.item.ItemStack;


public class GT_CoverBehaviorLoader implements Runnable {
	@Override
	public void run() {
		GT_Log.log.info("GT_Mod: Adding Cover Behaviors");
		new GT_Cover_Vent(new ItemStack[] {GT_ModHandler.getIC2Item("reactorVent", 1), GT_ModHandler.getIC2Item("reactorVentCore", 1), GT_ModHandler.getIC2Item("reactorVentSpread", 1)}, 1);
		new GT_Cover_Vent(new ItemStack[] {GT_ModHandler.getIC2Item("reactorVentDiamond", 1), GT_ModHandler.getIC2Item("reactorVentGold", 1)}, 2);
		new GT_Cover_DoesWork(GT_MetaItem_Component.instance.getStack(30, 1));
		new GT_Cover_ControlsWork(GT_MetaItem_Component.instance.getStack(31, 1));
		new GT_Cover_EUMeter(GT_MetaItem_Component.instance.getStack(15, 1));
		new GT_Cover_EnergyOnly(GT_MetaItem_Component.instance.getStack(0, 1));
		new GT_Cover_RedstoneOnly(GT_MetaItem_Component.instance.getStack(1, 1));
		new GT_Cover_Screen(GT_MetaItem_Component.instance.getStack(4, 1));
		new GT_Cover_Conveyor(GT_MetaItem_Component.instance.getStack(5, 1));
		new GT_Cover_Valve(GT_MetaItem_Component.instance.getStack(6, 1));
		new GT_Cover_SolarPanel(GT_MetaItem_Component.instance.getStack(7, 1), 32, 4);
		new GT_Cover_ItemValve(GT_MetaItem_Component.instance.getStack(8, 1));
		new GT_Cover_Drain(GT_MetaItem_Component.instance.getStack(9, 1));
		new GT_Cover_LiquidMeter(GT_MetaItem_Component.instance.getStack(10, 1));
		new GT_Cover_ItemMeter(GT_MetaItem_Component.instance.getStack(11, 1));
		new GT_Cover_Crafting(GT_MetaItem_Component.instance.getStack(64, 1));
		new GT_Cover_SolarPanel(GT_MetaItem_Component.instance.getStack(65, 1), 32*  5, 32   );
		new GT_Cover_SolarPanel(GT_MetaItem_Component.instance.getStack(66, 1), 32* 64, 32* 8);
		new GT_Cover_SolarPanel(GT_MetaItem_Component.instance.getStack(67, 1), 32*512, 32*64);
		new GT_Cover_RedstoneReceiverInternal(GT_MetaItem_Component.instance.getStack(82, 1));
		new GT_Cover_RedstoneReceiverExternal(GT_MetaItem_Component.instance.getStack(83, 1));
		new GT_Cover_RedstoneTransmitterInternal(GT_MetaItem_Component.instance.getStack(84, 1));
		new GT_Cover_RedstoneTransmitterExternal(GT_MetaItem_Component.instance.getStack(85, 1));
		new GT_Cover_RedstoneConductor(GT_MetaItem_Component.instance.getStack(86, 1));
		new GT_Cover_RedstoneSignalizer(GT_MetaItem_Component.instance.getStack(87, 1));
	}
}