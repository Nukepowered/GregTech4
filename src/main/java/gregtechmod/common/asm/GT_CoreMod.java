package gregtechmod.common.asm;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * @author TheDarkDnKTv
 *
 */
@IFMLLoadingPlugin.Name(GT_CoreMod.NAME)
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"gregtechmod.common.asm."})
public class GT_CoreMod implements IFMLLoadingPlugin {
	
	public static final String NAME = "GregTech-Core";
	public static final Logger log;
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[] {
				"gregtechmod.common.asm.EnergyNetTransformer"
		};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
	
	static String getIC2CoreClass(String clz) {
		return "ic2.core." + clz;
	}
	
	static String getIC2APIClass(String clz) {
		return "ic2.api." + clz;
	}
	
	static String formatName(String name) {
		return name.replace('.', '/');
	}
	
	static {
		log	= LogManager.getLogger(NAME); 
	}
}
