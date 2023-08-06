package gregtechmod.common.tileentities.energy.production.multi;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntSupplier;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.ItemStackKey;
import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.MTEWorkableMultiblock;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

/** A class of common multitubine logic, usually an shape of multiblock and simple logic
 * @author TheDarkDnKTv
 *
 */
public abstract class AbstractTurbine extends MTEWorkableMultiblock {

	protected static final Map<ItemStackKey, ITurbineMeta> TURBINES = new HashMap<>();

	protected ITurbineMeta turbine;
	protected int MAX_EFFICIENCY = 10_000;
	protected int TURBINE_OUTPUT_EU = 800;
	protected boolean NEED_AN_MUFFLER = false;
	
	@Override public void updateEfficiency() 						{}
	@Override public void endProcess() 								{}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public int maxEUOutput() 								{return TURBINE_OUTPUT_EU;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 156, GregTech_API.gregtechmod);}
    
	public AbstractTurbine(int aID, String aName, RecipeMap<?> map) {
		super(aID, aName, map);
	}
	
	public AbstractTurbine(RecipeMap<?> map) {
		super(map);
	}

	public static void registerTurbine(ItemStack stack, ITurbineMeta meta) {
		ItemStackKey key = ItemStackKey.from(stack, 1, stack.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE);
		TURBINES.putIfAbsent(key, meta);
	}
	
	protected abstract Pair<Block, Integer> getHull();
	
	/**
	 * Called right after resources was consumed
	 */
	protected void onRecipeUpdateTick() {
		int newEfficiency = Math.min(mEfficiency + turbine.getEfficiencyGrowRate(),
				turbine.getMaxEfficiency() - ((getIdealStatus() - getRepairStatus()) * 1000));
		mEfficiency = Math.max(0, newEfficiency);
	}
	
	private boolean checkAir() {
		int tAirCount = 0;
		for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) for (byte k = -1; k < 2; k++) {
			if (getBaseMetaTileEntity().getAirOffset(i, j, k)) tAirCount++;
		}

		return tAirCount == 10;
	}

	protected ITurbineMeta getTurbine(ItemStack stack) {
		if (GT_Utility.isStackInvalid(stack)) {
			return null;
		}

		return Optional.ofNullable(TURBINES.get(ItemStackKey.from(mInventory[1], 1)))
				.orElseGet(() -> TURBINES.get(ItemStackKey.from(mInventory[1], 1, true)));
	}

	@Override
	protected final boolean checkMachine(ItemStack aStack) {
		byte tSide = getBaseMetaTileEntity().getBackFacing();
		if (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1) && getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2)) {
			TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 3);
			if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Dynamo) {
				mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
			} else {
				return false;
			}
			
			for (byte i = 2; i < 6; i++) {
				if (null != (tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(i, 2)) && tTileEntity instanceof IGregTechTileEntity) {
					if (((IGregTechTileEntity)tTileEntity).getFrontFacing() == getBaseMetaTileEntity().getFrontFacing() && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
						if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity().getClass() == this.getClass()) return false;
					}
				}
			}
			
			int tX = getBaseMetaTileEntity().getXCoord(), tY = getBaseMetaTileEntity().getYCoord(), tZ = getBaseMetaTileEntity().getZCoord();
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) if (i != 0 || j != 0) for (byte k = 0; k < 4; k++) {
				Pair<Block, Integer> hull = this.getHull();
				
				if ((i == 0 || j == 0) && (k == 1 || k == 2)) {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != hull.getKey() || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != hull.getValue()) {
						tTileEntity = getBaseMetaTileEntity().getTileEntity(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i));
						if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
							IMetaTileEntity mte = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity();
							if (mte instanceof GT_MetaTileEntity_Hatch_Maintenance) {
								maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Input) {
								mInputHatches.add((GT_MetaTileEntity_Hatch_Input)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Output) {
								mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)mte);
							} else if (NEED_AN_MUFFLER && mte instanceof GT_MetaTileEntity_Hatch_Muffler) {
								mufflerHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Muffler)mte);
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				} else {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != hull.getKey() || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != hull.getValue()) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		
		return NEED_AN_MUFFLER ? (mufflerHatch != null && mufflerHatch.get() != null) : true;
	}
	
	@Override
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new MultiTurbineLogic(() -> mEfficiency / 100, map, this);
	}
	
	@Override
	public void stopMachine() {
		recipeLogic.stop();
		turbine = null;
		mEfficiency = 0;
    	getBaseMetaTileEntity().setActive(false);
	}
	
	@Override
	public void startProcess() {
		this.turbine = this.getTurbine(mInventory[1]);
		if (this.turbine == null) {
			this.stopMachine();
		}
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		if (this.recipeLogic.isActive()) {
			this.turbine = this.getTurbine(mInventory[1]);
			if (this.turbine == null) {
				this.stopMachine();
			}
		}
	}
	
	@Override
	public void onServerStart() {
		ItemStack rcTurbine = GT_ModHandler.getRCItem("part.turbine.rotor", 1, GregTech_API.ITEM_WILDCARD_DAMAGE);
		if (rcTurbine != null) {
			registerTurbine(rcTurbine, Turbines.RC);
		}

		registerTurbine(GT_Items.Component_Turbine_Bronze.getWildcard(1), Turbines.BRONZE);
		registerTurbine(GT_Items.Component_Turbine_Steel.getWildcard(1), Turbines.STEEL);
		registerTurbine(GT_Items.Component_Turbine_Magnalium.getWildcard(1), Turbines.MAGNALIUM);
		registerTurbine(GT_Items.Component_Turbine_TungstenSteel.getWildcard(1), Turbines.TUNGSTENSTEEL);
		registerTurbine(GT_Items.Component_Turbine_Carbon.getWildcard(1), Turbines.CARBON);
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		InfoBuilder b =  InfoBuilder.create()
				.newKey("metatileentity.turbine.speed", GT_Utility.parseNumberToString(mEfficiency * 100.0D / MAX_EFFICIENCY))
				.newKey("metatileentity.multiblock.dynamo_output", (int)(TURBINE_OUTPUT_EU * mEfficiency * 1.0D / MAX_EFFICIENCY))
				.newKey("metatileentity.multiblock.malfunction_amount", getIdealStatus() - getRepairStatus());
		if (isCorrectMachinePart(mInventory[1])) {
			int damage = mInventory[1].getMaxDamage() - mInventory[1].getItemDamage();
			if (this.turbine == Turbines.RC)
				damage /= 2;
			b.newKey("metatileentity.turbine.durability", GT_Utility.parseNumberToString(damage));
		} else {
			b.newKey("metatileentity.turbine.no_turbine");
		}
		return b.build();
	}

	@Override
	public final boolean isCorrectMachinePart(ItemStack aStack) {
		return this.getMaxEfficiency(aStack) > 0;
	}

	@Override
	public final int getMaxEfficiency(ItemStack aStack) {
		return Optional.ofNullable(this.getTurbine(aStack))
				.map(ITurbineMeta::getMaxEfficiency)
				.orElse(0);
	}
	
	@Override
	public final int getDamageToComponent(ItemStack aStack) {
		return this.getTurbine(aStack) == Turbines.RC ? 2 : 1;
	}

	@Override
	public final boolean explodesOnComponentBreak(ItemStack aStack) {
		return true;
	}

	public interface ITurbineMeta {

		int getMaxEfficiency();

		int getEfficiencyGrowRate();
	}

	public static class MultiTurbineLogic extends GeneratorRecipeLogic {

		protected int consumptionModifier;

		protected MultiTurbineLogic(IntSupplier efficiency, RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(efficiency, recipeMap, machine);
			metadataVerifier = rec -> rec.getInputs().isEmpty() && rec.getAllOutputs().isEmpty(); // only fluid recipes allow
		}

		protected boolean depleteInputs() {
			for (FluidStack fluid : previousRecipe.getFluidInputs()) {
				FluidStack dummy = fluid.copy();
				dummy.amount *= consumptionModifier;
				if (!getMachine().depleteInput(dummy))
					return false;
			}
			
			return true;
		}
		
		@Override 
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			
			
			if (base.isAllowedToWork()) {
				if (leftEU > 0) {
					success = updateRecipeProgress();
				}

				if (leftEU <= 0) {
					if (getMachine().hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0) {
						trySerachRecipe(); 
					}
				}
			} else if (leftEU > 0) getMachine().stopMachine();
			
			return success;
		}
		
		@Override
		protected void trySerachRecipe() {
			if (getMachine().allowToCheckRecipe()) {
				if (previousRecipe != null) {
					if (match(previousRecipe)) {
						startRecipe(previousRecipe);
					} else {
						previousRecipe = null;
						getMachine().stopMachine();
					}
				} else {
					// find new recipe
					Recipe resRec = findRecipe();
					if (resRec != null)
						startRecipe(resRec);
				}
			}
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if ((getMachine().getBaseMetaTileEntity().getTimer() % 10 != 0 || getMachine().checkAir())) {
				getMachine().onRecipeUpdateTick();
				long EU = Math.min((long) (getMachine().maxEUOutput() * (efficiency.getAsInt() / 100.0D)), leftEU);
				leftEU -= EU;
				getMachine().addEnergyOutput((int) EU);
				return true;
			}

			getMachine().stopMachine();
			return false;
		}

		@Override
		protected void startRecipe(Recipe recipe) {
			if (getMachine().spaceForOutput(recipe)) {
				previousRecipe = recipe;
				progressTime = 1;

				int recipeBaseEu = recipe.getDuration() * recipe.getEUt();
				consumptionModifier = Math.max(1, getMachine().maxEUOutput() / recipeBaseEu);
				leftEU = (long) recipeBaseEu *  consumptionModifier;
				maxProgressTime = (int) Math.ceil(leftEU * 1.0D / getMachine().maxEUOutput());
				triggerMachine(true);
				getMachine().startProcess();
				if (this.isActive() && this.depleteInputs()) {
					return;
				}
			}

			getMachine().stopMachine();
		}
		
		@Override
		protected AbstractTurbine getMachine() {
			return (AbstractTurbine) metaTileEntity.get();
		}
	}

	public enum Turbines implements ITurbineMeta {
		BRONZE			(60_00		,10),
		STEEL			(80_00		,20),
		MAGNALIUM		(100_00	,50),
		TUNGSTENSTEEL	(90_00		,15),
		CARBON			(125_00	,100),
		RC				(80_00		,20);

		Turbines(int max, int grow) {
			this.maxEfficiency = max;
			this.efficiencyGrow = grow;
		}

		private final int maxEfficiency;
		private final int efficiencyGrow;

		@Override
		public int getMaxEfficiency() {
			return this.maxEfficiency;
		}

		@Override
		public int getEfficiencyGrowRate() {
			return this.efficiencyGrow;
		}
	}
}
