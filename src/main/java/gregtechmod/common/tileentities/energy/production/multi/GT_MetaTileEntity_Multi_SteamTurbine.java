package gregtechmod.common.tileentities.energy.production.multi;

import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;

import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class GT_MetaTileEntity_Multi_SteamTurbine extends AbstractTurbine {
	
	public GT_MetaTileEntity_Multi_SteamTurbine(int aID, String mName) {
		super(aID, mName, RecipeMaps.STEAM_FUELS);
		TURBINE_OUTPUT_EU = 800;
	}
	
	public GT_MetaTileEntity_Multi_SteamTurbine() {
		super(RecipeMaps.STEAM_FUELS);
		TURBINE_OUTPUT_EU = 800;
	}
	
	@Override
	protected void onRecipeUpdateTick() {
		super.onRecipeUpdateTick();
		addOutput(GT_ModHandler.getWater(10));
	}
	
	@Override
	protected Pair<Block, Integer> getHull() {
		return Pair.of(GregTech_API.sBlockList[0], Integer.valueOf(13));
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_SteamTurbine();
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			if (aActive) return GT_BlockMetaID_Block.mIconSteamTurbineActive[4];
			return GT_BlockMetaID_Block.mIconSteamTurbine[4];
		}
		return null;
	}
	
	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 0;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_SteamTurbine.tooltip";
	}
}
