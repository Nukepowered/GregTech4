package gregtechmod.integration.nei;

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
import gregtechmod.integration.nei.handlers.AlloySmelterRecipeHandler;
import gregtechmod.integration.nei.handlers.AssemblerRecipeHandler;
import gregtechmod.integration.nei.handlers.BenderRecipeHandler;
import gregtechmod.integration.nei.handlers.BlastRecipeHandler;
import gregtechmod.integration.nei.handlers.BronzeBlastRecipeHandler;
import gregtechmod.integration.nei.handlers.CannerRecipeHandler;
import gregtechmod.integration.nei.handlers.CentrifugeRecipeHandler;
import gregtechmod.integration.nei.handlers.ChemicalRecipeHandler;
import gregtechmod.integration.nei.handlers.CutterRecipeHandler;
import gregtechmod.integration.nei.handlers.DistillationRecipeHandler;
import gregtechmod.integration.nei.handlers.ElectrolyzerRecipeHandler;
import gregtechmod.integration.nei.handlers.ExtruderRecipeHandler;
import gregtechmod.integration.nei.handlers.FusionRecipeHandler;
import gregtechmod.integration.nei.handlers.GeneratorHandler;
import gregtechmod.integration.nei.handlers.GrinderRecipeHandler;
import gregtechmod.integration.nei.handlers.ImplosionRecipeHandler;
import gregtechmod.integration.nei.handlers.LatheRecipeHandler;
import gregtechmod.integration.nei.handlers.SawmillRecipeHandler;
import gregtechmod.integration.nei.handlers.VacuumFreezerRecipeHandler;
import gregtechmod.integration.nei.handlers.WiremillRecipeHandler;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.guihook.GuiContainerManager;

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
		
		GuiContainerManager.addInputHandler(new GT_NEIInputHandler());
		
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

