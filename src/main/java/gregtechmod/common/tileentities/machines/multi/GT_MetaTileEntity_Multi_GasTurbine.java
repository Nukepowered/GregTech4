package gregtechmod.common.tileentities.machines.multi;

import java.lang.ref.WeakReference;
import java.util.function.IntSupplier;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.MTEWorkableMultiblock;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

// FIXME Check turbine when recipes available!
public class GT_MetaTileEntity_Multi_GasTurbine extends MTEWorkableMultiblock {

	protected int mEfficiencyIncrease = 0;
	
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 156, GregTech_API.gregtechmod);}
	@Override public int maxEUOutput() 								{return 675;}
	
	public GT_MetaTileEntity_Multi_GasTurbine(int aID, String mName) {
		super(aID, mName, RecipeMaps.TURBINE_FUELS);
	}
	
	public GT_MetaTileEntity_Multi_GasTurbine() {
		super(RecipeMaps.TURBINE_FUELS);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_GasTurbine();
	}
	
	@Override
	public boolean isCorrectMachinePart(ItemStack aStack) {
		return this.getMaxEfficiency(aStack) > 0;
	}
	
	@Override
	public boolean explodesOnComponentBreak(ItemStack aStack) {
		return true;
	}
	
	@Override
	public int getDamageToComponent(ItemStack aStack) {
		return GT_Utility.areStacksEqual(GT_ModHandler.getRCItem("part.turbine.rotor", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), aStack)?2:1;
	}
	
	@Override
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new GeneratorMultiblockRecipeLogic(() -> mEfficiency / 100, map, this);
	}
	
	@Override
	public int getMaxEfficiency(ItemStack aStack) {
		if (GT_Items.Component_Turbine_Bronze.isStackEqual(aStack, true, true)) return  60_00;
		if (GT_Items.Component_Turbine_Steel.isStackEqual(aStack, true, true)) return  80_00;
		if (GT_Items.Component_Turbine_Magnalium.isStackEqual(aStack, true, true)) return 100_00;
		if (GT_Items.Component_Turbine_TungstenSteel.isStackEqual(aStack, true, true)) return  90_00;
		if (GT_Items.Component_Turbine_Carbon.isStackEqual(aStack, true, true)) return 125_00;
		if (GT_Utility.areStacksEqual(aStack, GT_ModHandler.getRCItem("part.turbine.rotor", 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) return 80_00;
		return 0;
	}
	
	@Override
	public boolean checkMachine(ItemStack aStack) {
		byte tSide = getBaseMetaTileEntity().getBackFacing();
		if (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1) && getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2)) {
			TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 3);
			if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Dynamo) {
				mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
			} else {
				return false;
			}
			
			int tAirCount = 0;
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) for (byte k = -1; k < 2; k++) {
				if (getBaseMetaTileEntity().getAirOffset(i, j, k)) tAirCount++;
			}
			if (tAirCount != 10) return false;
			for (byte i = 2; i < 6; i++) {
				if (null != (tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(i, 2)) && tTileEntity instanceof IGregTechTileEntity) {
					if (((IGregTechTileEntity)tTileEntity).getFrontFacing() == getBaseMetaTileEntity().getFrontFacing() && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
						if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Multi_GasTurbine) return false;
					}
				}
			}
			
			int tX = getBaseMetaTileEntity().getXCoord(), tY = getBaseMetaTileEntity().getYCoord(), tZ = getBaseMetaTileEntity().getZCoord();
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) if (i != 0 || j != 0) for (byte k = 0; k < 4; k++) {
				if ((i == 0 || j == 0) && (k == 1 || k == 2)) {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != 14) {
						tTileEntity = getBaseMetaTileEntity().getTileEntity(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i));
						if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
							IMetaTileEntity mte = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity();
							if (mte instanceof GT_MetaTileEntity_Hatch_Maintenance) {
								maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Input) {
								mInputHatches.add((GT_MetaTileEntity_Hatch_Input)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Output) {
								mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Muffler) {
								mufflerHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Muffler)mte);
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
	public void startProcess() {
		if (GT_Items.Component_Turbine_Bronze.isStackEqual(mInventory[1], true, true)) {
			mEfficiencyIncrease = 10;
		} else if (GT_Items.Component_Turbine_Steel.isStackEqual(mInventory[1], true, true)) {
			mEfficiencyIncrease = 20;
		} else if (GT_Items.Component_Turbine_Magnalium.isStackEqual(mInventory[1], true, true)) {
			mEfficiencyIncrease = 50;
		} else if (GT_Items.Component_Turbine_TungstenSteel.isStackEqual(mInventory[1], true, true)) {
			mEfficiencyIncrease = 15;
		} else if (GT_Items.Component_Turbine_Carbon.isStackEqual(mInventory[1], true, true)) {
			mEfficiencyIncrease = 100;
		} else {
			mEfficiencyIncrease = 20;
		}
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
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		aNBT.setInteger("mEfficiencyIncrease", mEfficiencyIncrease);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mEfficiencyIncrease = aNBT.getInteger("mEfficiencyIncrease");
	}
	
	protected class GeneratorMultiblockRecipeLogic extends GeneratorRecipeLogic {

		protected GeneratorMultiblockRecipeLogic(IntSupplier efficiency, RecipeMap<?> recipeMap, IRecipeWorkable tile) {
			super(efficiency, recipeMap, tile);
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if (leftEU > 0) {
				int EU = (int) (Math.min(((MetaTileEntity) getMachine()).maxEUOutput(), leftEU) * (efficiency.getAsInt() / 100.0D));
				leftEU -= EU;
				if (addEnergyOutput(EU)) {
					if (leftEU <= 0) {
						progressTime = 0;
						maxProgressTime = 0;
						EUt = 0;
						leftEU = 0;
		
						endRecipe(previousRecipe);
						getMachine().endProcess();
						return true;
					}
				}
			}
				
			return false;
		}
		
		@Override
		protected void startRecipe(Recipe recipe) {
			if (getMachine().spaceForOutput(recipe)) {
				previousRecipe = recipe;
				progressTime = 1;
				leftEU = recipe.getDuration() * recipe.getEUt();
				maxProgressTime = (int) Math.ceil(leftEU * 1.0D / ((MetaTileEntity) getMachine()).maxEUOutput());
				if (consumeInputs(recipe)) {
					triggerMachine(true);
					getMachine().startProcess();
				} else {
					GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
					leftEU = 0;
					progressTime = 0;
					maxProgressTime = 0;
					previousRecipe = null;
				}
			} else {
				triggerMachine(false);
			}
		}
		
	}
}
