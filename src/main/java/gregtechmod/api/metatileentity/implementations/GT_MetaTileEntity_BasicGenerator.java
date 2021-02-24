package gregtechmod.api.metatileentity.implementations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public abstract class GT_MetaTileEntity_BasicGenerator extends BasicFluidWorkable {
	
	/** A mapping of allowed fluid inputs by generator maps */
	protected static final Map<Class<?>, Set<Integer>> ALLOWED_FLUIDS = new HashMap<>();
	
	protected int efficiency;
	
	public GT_MetaTileEntity_BasicGenerator(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName, recipeMap);
		this.efficiency = efficiency;
	}
	
	public GT_MetaTileEntity_BasicGenerator(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap);
		this.efficiency = efficiency;
	}
	
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isEnetInput() 							{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?12:0;}
	@Override public int maxEUStore()								{return 1000000;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public int getInputSlot() 			{return 0;}
	@Override public int getOutputSlot() 			{return 1;}
	@Override public int getStackDisplaySlot() 		{return 2;}
	@Override public List<ItemStack> getInputItems() { return new ListAdapter<>(mInventory, 0, 0); }
	@Override public List<ItemStack> getOutputItems() { return new ListAdapter<>(mInventory, 1, 1); }
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return false;}
	
	@Override public boolean isFluidInputAllowed(FluidStack aFluid) {
		if (GT_Utility.isFluidStackValid(aFluid)) {
			Set<Integer> allowedValues = ALLOWED_FLUIDS.get(this.getClass());
			if (allowedValues != null) {
				return allowedValues.contains(GT_Utility.fluidStackToInt(aFluid));
			} else {
				allowedValues = new HashSet<>();
				for (Recipe recipe : recipeLogic.recipeMap.getRecipes()) {
					 for (FluidStack fluid : recipe.getFluidInputs()) {
						 allowedValues.add(GT_Utility.fluidStackToInt(fluid));
					 }
				}
				
				ALLOWED_FLUIDS.put(this.getClass(), allowedValues);
			}
		}
		
		return false;
	}
	
	@Override
	public void initRecipeLogic(RecipeMap<?> recipeMap) {
		recipeLogic = new GeneratorRecipeLogic(recipeMap, this);
	}
	
    @Override
    public void onPostTick() {
    	super.onPostTick();
    }
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
	
	protected class GeneratorRecipeLogic extends RecipeLogic {
		public GeneratorRecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(recipeMap, machine);
		}
		
		@Override 
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			
			if (base.isAllowedToWork()) {
				if (progressTime > 0) {
					int tmp = progressTime;
					success = updateRecipeProgress();
					if (tmp == 0 && !success) {
						throw new IllegalStateException();
					}
				}
				
				if (progressTime == 0) {
					if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || mFluid != null) {
						int a = base.getUniversalEnergyStored();
						int b = base.getOutputVoltage() * 10;
						int c = getMachine().getMinimumStoredEU();
						if (a < (b + c)) {
							trySerachRecipe();
						} else {
							previousRecipe = null;
							base.setActive(false);
						} 
					}
				}
			} 
			
			return success;
		}
		
		@Override
		protected boolean updateRecipeProgress() {
				if (getMachine().getBaseMetaTileEntity().increaseStoredEnergyUnits(EUt * efficiency / 100, false)) {
					if ((progressTime += progressTimeManipulator.applyAsInt(1)) >= maxProgressTime) {
						progressTime = 0;
						maxProgressTime = 0;
						EUt = 0;
						
						endRecipe(previousRecipe);
						getMachine().endProcess();
						return true;
					}
				}
				
			return false;
		}
	}
}
