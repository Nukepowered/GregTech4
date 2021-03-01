package gregtechmod.common.tileentities.machines.multi;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cofh.lib.util.helpers.MathHelper;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_OutputBus;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_MultiBlockBase;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Multi_ThermalBoiler extends GT_MetaTileEntity_MultiBlockBase {

	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 158, GregTech_API.gregtechmod);}
	
	public GT_MetaTileEntity_Multi_ThermalBoiler(int aID, String mName) {
		super(aID, mName, RecipeMaps.HOT_FUELS);
	}
	
	public GT_MetaTileEntity_Multi_ThermalBoiler() {
		super(RecipeMaps.HOT_FUELS);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_ThermalBoiler();
	}
	
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new MultiblockGenerator(100, map, this);
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
	public void endProcess() {
		super.endProcess();
		Recipe recipe = recipeLogic.getCurrentRecipe();
		if (recipe != null) {
			if (GT_Utility.areStacksEqual(mInventory[1], GT_Items.Component_LavaFilter.getWildcard(1)) && recipe.getFluidInputs().contains(new FluidStack(FluidRegistry.LAVA, 0))) {
				List<ItemStack> bonus = new ArrayList<>();
				if (getBaseMetaTileEntity().getRandomNumber(10000) < 100)
					bonus.add(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper));
				if (getBaseMetaTileEntity().getRandomNumber(9000) < 50)
					bonus.add(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin));
				if (getBaseMetaTileEntity().getRandomNumber(8500) < 25)
					bonus.add(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Electrum));
				bonus.forEach(item -> this.addOutput(item));
			}
		}
	}
	
	@Override
	protected void updateLogic() {
		recipeLogic.update();
		if (recipeLogic.isActive()) {
			if (doRandomMaintenanceDamage()) {
				if (!polluteEnvironment(getPollutionPerTick(mInventory[1]))) {
	    			stopMachine();
	    		}
			}
		}
	}
	
	@Override
	public boolean onRunningTick() {
		int tGeneratedEU = (int) (recipeLogic.getEUt() * (mEfficiency / 100.0D));
		if (tGeneratedEU > 0) {
			if (this.depleteInput(GT_ModHandler.getWater((tGeneratedEU + 160) / 160))) {
				this.addOutput(GT_ModHandler.getSteam(tGeneratedEU));
			} else return false;
		}
		
		return true;
	}
	
	@Override
	public boolean checkMachine(ItemStack aStack) {
		byte tSide = getBaseMetaTileEntity().getBackFacing();
		if (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1)) {
			if (getBaseMetaTileEntity().getBlockAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaIDAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2) != 14) {
				TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2);
				if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) {
					IMetaTileEntity mte = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity();
					if (mte != null) {
						if (mte instanceof GT_MetaTileEntity_Hatch_Maintenance) {
							maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)mte);
						} else if (mte instanceof GT_MetaTileEntity_Hatch_Input) {
							mInputHatches.add((GT_MetaTileEntity_Hatch_Input)mte);
						} else if (mte instanceof GT_MetaTileEntity_Hatch_Output) {
							mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)mte);
						} else {
							return false;
						}
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
						if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) {
							IMetaTileEntity mte = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity();
							if (mte != null) {
								if (mte instanceof GT_MetaTileEntity_Hatch_Maintenance) {
									maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)mte);
								} else if (mte instanceof GT_MetaTileEntity_Hatch_Input) {
									mInputHatches.add((GT_MetaTileEntity_Hatch_Input)mte);
								} else if (mte instanceof GT_MetaTileEntity_Hatch_Output) {
									mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)mte);
								} else if (mte instanceof GT_MetaTileEntity_Hatch_OutputBus) {
									mOutputBusses.add((GT_MetaTileEntity_Hatch_OutputBus)mte);
								} else return false;
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
		return 100;
	}
	
	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 0;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_ThermalBoiler.tooltip";
	}
	
	private static class MultiblockGenerator extends GeneratorRecipeLogic {
		
		private static final float RECIPE_BOOST = 16.66666666F;
		
		protected MultiblockGenerator(int efficiency, RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(efficiency, recipeMap, machine);
		}
		
		@Override
		public boolean update() {
			boolean success = false;
			GT_MetaTileEntity_MultiBlockBase machine = (GT_MetaTileEntity_MultiBlockBase) getMachine();
			IGregTechTileEntity base = machine.getBaseMetaTileEntity();
			
			// TODO change this after changing generator's recipe logic
			if (progressTime > 0) {
				int tmp = progressTime;
				success = updateRecipeProgress();
				if (tmp == 0 && !success) {
					throw new IllegalStateException();
				}
			}

			if (progressTime == 0) {
				if ((machine.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0) && base.isAllowedToWork()) {// || !getMachine().getFluidInputs().isEmpty()) { // TODO may be generators will not work
					trySerachRecipe();
				} else {
					previousRecipe = null;
					base.setActive(false);
				}
			}

			return success;
		}
		
		@Override
		protected void startRecipe(Recipe recipe) {
			
			if (getMachine().spaceForOutput(recipe)) {
				previousRecipe = recipe;
				maxProgressTime = MathHelper.floor(recipe.getDuration() / RECIPE_BOOST);
				progressTime = 1;
				EUt = MathHelper.floor(recipe.getEUt() * RECIPE_BOOST);
				if (consumeInputs(recipe)) {
					triggerMachine(true);
					getMachine().startProcess();
				} else {
					GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
					EUt = 0;
					progressTime = 0;
					maxProgressTime = 0;
					previousRecipe = null;
				}
				
			} else {
				triggerMachine(false);
			}
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if (((GT_MetaTileEntity_MultiBlockBase) getMachine()).onRunningTick()) {
				if ((progressTime += progressTimeManipulator.applyAsInt(1)) >= maxProgressTime) {
					progressTime = 0;
					maxProgressTime = 0;
					EUt = 0;

					endRecipe(previousRecipe);
					getMachine().endProcess();
					return true;
				}
			} else ((GT_MetaTileEntity_MultiBlockBase) getMachine()).stopMachine();

			return false;
		}
	}
}
