package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_Multi_ThermalBoiler extends GT_MetaTileEntity_MultiBlockBase {

	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 158, GregTech_API.gregtechmod);}
	
	public GT_MetaTileEntity_Multi_ThermalBoiler(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Multi_ThermalBoiler() {
		
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_ThermalBoiler();
	}
	
	@Override
	public boolean isCorrectMachinePart(ItemStack aStack) {
		return true;
	}
	
	@Override
	public int getDamageToComponent(ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_Items.Component_LavaFilter.getWildcard(1)) ? 1 : 0;
	}
	
	@Override
	public boolean checkRecipe(ItemStack aStack) {
		for (GT_Recipe tRecipe : GT_Recipe.sHotFuels) {
			if (depleteInput(tRecipe.getRepresentativeInput(0))) {
				mEUt = 1600;
				mMaxProgresstime = (tRecipe.mStartEU * 2) / 5;
				mEfficiencyIncrease = mMaxProgresstime * 30;
				if (tRecipe.getOutput(0) != null) mOutputItems = new ItemStack[] { GT_Utility.copy(tRecipe.getOutput(0)) };
				if (GT_Utility.areStacksEqual(aStack, GT_Items.Component_LavaFilter.getWildcard(1))) {
					if (tRecipe.getOutput(1) != null && getBaseMetaTileEntity().getRandomNumber(1000) < 100) mOutputItems = new ItemStack[] { GT_Utility.copy(tRecipe.getOutput(1)) }; else
					if (tRecipe.getOutput(2) != null && getBaseMetaTileEntity().getRandomNumber( 900) <  50) mOutputItems = new ItemStack[] { GT_Utility.copy(tRecipe.getOutput(2)) }; else
					if (tRecipe.getOutput(3) != null && getBaseMetaTileEntity().getRandomNumber( 850) <  25) mOutputItems = new ItemStack[] { GT_Utility.copy(tRecipe.getOutput(3)) };
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onRunningTick(ItemStack aStack) {
		if (mEUt > 0) {
			int tGeneratedEU = (int)(((long)mEUt * 2 * mEfficiency) / 10000);
			// removed from if: && depleteInput(GT_ModHandler.getWater((tGeneratedEU + 160) / 160))
			if (tGeneratedEU > 0) { 
				addOutput(GT_ModHandler.getSteam(tGeneratedEU));
			}
			return true;
		}
		return true;
	}
	
	@Override
	public boolean checkMachine(ItemStack aStack) {
		byte tSide = getBaseMetaTileEntity().getBackFacing();
		if (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1)) {
			if (getBaseMetaTileEntity().getBlockAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaIDAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2) != 14) {
				TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2);
				if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
					if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Maintenance) {
						mMaintenanceHatches.add((GT_MetaTileEntity_Hatch_Maintenance)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
					} else if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Input) {
						mInputHatches.add((GT_MetaTileEntity_Hatch_Input)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
					} else if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Output) {
						mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
			
			int tX = getBaseMetaTileEntity().getXCoord(), tY = getBaseMetaTileEntity().getYCoord(), tZ = getBaseMetaTileEntity().getZCoord();
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) if (i != 0 || j != 0) for (byte k = 0; k < 3; k++) {
				if ((i == 0 || j == 0) && (k == 1)) {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != 14) {
						TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntity(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i));
						if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
							if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Maintenance) {
								mMaintenanceHatches.add((GT_MetaTileEntity_Hatch_Maintenance)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
							} else if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Input) {
								mInputHatches.add((GT_MetaTileEntity_Hatch_Input)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
							} else if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Output) {
								mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				} else {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != 14) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return aActive?84:83;
    	return super.getTextureIndex(aSide, aFacing, aActive, aRedstone);
	}
	
	@Override
	public boolean explodesOnComponentBreak(ItemStack aStack) {
		return false;
	}
	
	@Override
	public int getMaxEfficiency(ItemStack aStack) {
		return 10000;
	}
	
	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 0;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_ThermalBoiler.tooltip";
	}
	@Override
	public int getAmountOfOutputs() {
		return 1;
	}
}
