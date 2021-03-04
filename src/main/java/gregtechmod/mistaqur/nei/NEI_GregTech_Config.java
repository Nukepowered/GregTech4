package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.gui.GT_GUIContainer_AdvancedWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_BronzeWorkbench;
import gregtechmod.common.gui.GT_GUIContainer_DieselGenerator;
import gregtechmod.common.gui.GT_GUIContainer_GasTurbine;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyConverter;
import gregtechmod.common.gui.GT_GUIContainer_PlasmaGenerator;
import gregtechmod.common.gui.GT_GUIContainer_SemifluidGenerator;
import gregtechmod.common.gui.GT_GUIContainer_ThermalGenerator;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.mistaqur.nei.handlers.AssemblerRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.BenderRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.BlastRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.BronzeBlastRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.CannerRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.CentrifugeRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.ChemicalRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.CutterRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.DistillationRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.ElectrolyzerRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.ExtruderRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.FusionRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.GeneratorHandler;
import gregtechmod.mistaqur.nei.handlers.GrinderRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.ImplosionRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.LatheRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.SawmillRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.VacuumFreezerRecipeHandler;
import gregtechmod.mistaqur.nei.handlers.WiremillRecipeHandler;
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
		new BronzeBlastRecipeHandler();
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
		new ExtruderRecipeHandler();
		
		new GeneratorHandler(RecipeMaps.HOT_FUELS		, "thermal_generator"	, GT_GUIContainer_ThermalGenerator.class);
		new GeneratorHandler(RecipeMaps.DIESEL_FUELS	, "diesel_generator"	, GT_GUIContainer_DieselGenerator.class);
		new GeneratorHandler(RecipeMaps.TURBINE_FUELS	, "gas_turbine"			, GT_GUIContainer_GasTurbine.class);
		new GeneratorHandler(RecipeMaps.DENSE_FUELS		, "semifluid_generator"	, GT_GUIContainer_SemifluidGenerator.class);
		new GeneratorHandler(RecipeMaps.PLASMA_FUELS	, "plasma_generator"	, GT_GUIContainer_PlasmaGenerator.class);
		new GeneratorHandler(RecipeMaps.MAGIC_FUELS		, "magic_generator"		, GT_GUIContainer_MagicEnergyConverter.class);
		
        try {
    		Class.forName("codechicken.nei.api.API");
    		codechicken.nei.api.API.registerGuiOverlay(GT_GUIContainer_AdvancedWorkbench.class, "crafting", 57, 22);
    		codechicken.nei.api.API.registerGuiOverlayHandler(GT_GUIContainer_AdvancedWorkbench.class, new codechicken.nei.recipe.DefaultOverlayHandler(57, 22), "crafting");
    		codechicken.nei.api.API.registerGuiOverlay(GT_GUIContainer_BronzeWorkbench.class, "crafting", 57, 22);
    		codechicken.nei.api.API.registerGuiOverlayHandler(GT_GUIContainer_BronzeWorkbench.class, new codechicken.nei.recipe.DefaultOverlayHandler(57, 22), "crafting");
        } catch(Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
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
		return "(3.08)";
	}

}

