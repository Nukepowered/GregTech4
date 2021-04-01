package gregtechmod.common.tileentities.energy.production.multi;

import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;

import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class GT_MetaTileEntity_Multi_GasTurbine extends AbstractTurbine {
	
	public GT_MetaTileEntity_Multi_GasTurbine(int aID, String mName) {
		super(aID, mName, RecipeMaps.TURBINE_FUELS);
		TURBINE_OUTPUT_EU = 675;
		NEED_AN_MUFFLER = true;
	}
	
	public GT_MetaTileEntity_Multi_GasTurbine() {
		super(RecipeMaps.TURBINE_FUELS);
		TURBINE_OUTPUT_EU = 675;
		NEED_AN_MUFFLER = true;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_GasTurbine();
	}

	@Override
	protected Pair<Block, Integer> getHull() {
		return Pair.of(GregTech_API.sBlockList[0], Integer.valueOf(14));
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			if (aActive) return GT_BlockMetaID_Block.mIconGasTurbineActive[4];
			return GT_BlockMetaID_Block.mIconGasTurbine[4];
		}
		return null;
	}
	
	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 1;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_GasTurbine.tooltip";
	}
}
