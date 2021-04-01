package gregtechmod.common.tileentities.energy.production.multi;

import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * @author TheDarkDnKTv
 *
 */
public class MultiPlasmaTurbine extends AbstractTurbine {

	public MultiPlasmaTurbine(int aID, String aName) {
		super(aID, aName, RecipeMaps.PLASMA_FUELS);
		TURBINE_OUTPUT_EU = 8192;
	}

	public MultiPlasmaTurbine() {
		super(RecipeMaps.PLASMA_FUELS);
		TURBINE_OUTPUT_EU = 8192;
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new MultiPlasmaTurbine();
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			if (aActive) return GT_BlockMetaID_Block.mIconPlasmaTurbineActive[4];
			return GT_BlockMetaID_Block.mIconPlasmaTurbine[4];
		}
		return null;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_PlasmaTurbine.tooltip";
	}

	@Override
	protected Pair<Block, Integer> getHull() {
		return Pair.of(GregTech_API.sBlockList[0], Integer.valueOf(15));
	}

	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 0;
	}
}
