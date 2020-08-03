package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_BronzeWorkbench;
import codechicken.nei.api.IConfigureNEI;

public class NEI_GregTech_Config implements IConfigureNEI {
	public static boolean sIsAdded = true;
	
	@Override
	public void loadConfig() {
        sIsAdded = false;
		new CentrifugeRecipeHandler();
		new ElectrolyzerRecipeHandler();
		new ChemicalRecipeHandler();
		new VacuumFreezerRecipeHandler();
		new GrinderRecipeHandler();
		new BlastRecipeHandler();
		new SawmillRecipeHandler();
		new ImplosionRecipeHandler();
		new FusionRecipeHandler();
		new DistillationRecipeHandler();
		new WiremillRecipeHandler();
		new AlloySmelterRecipeHandler();
		new CannerRecipeHandler();
		new BenderRecipeHandler();
		new AssemblerRecipeHandler();
		new LatheRecipeHandler();
		new CutterRecipeHandler();
		/*
		new DieselFuelsHandler();
		new TurbineFuelsHandler();
		new HotFuelsHandler();
		new DenseLiquidFuelsHandler();
		new PlasmaFuelsHandler();
		new MagicFuelsHandler();
		*/
        try {
    		Class.forName("codechicken.nei.api.API");
    		codechicken.nei.api.API.registerGuiOverlay(GT_GUIContainer_AdvancedWorkbench.class, "crafting", 57, 22);
    		codechicken.nei.api.API.registerGuiOverlayHandler(GT_GUIContainer_AdvancedWorkbench.class, new codechicken.nei.recipe.DefaultOverlayHandler(57, 22), "crafting");
    		codechicken.nei.api.API.registerGuiOverlay(GT_GUIContainer_BronzeWorkbench.class, "crafting", 57, 22);
    		codechicken.nei.api.API.registerGuiOverlayHandler(GT_GUIContainer_BronzeWorkbench.class, new codechicken.nei.recipe.DefaultOverlayHandler(57, 22), "crafting");
        } catch(Throwable e) {
        	if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
        }
        sIsAdded = true;
	}
	
	@Override
	public String getName() {
		return "GregTech NEI Plugin";
	}
	
	/**
	 * This is just last time I was on looking for this Version Number :P
	 */
	@Override
	public String getVersion() {
		return "(3.02c)";
	}

}

