package gregtechmod.api.metatileentity.implementations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;
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
		recipeLogic = new GeneratorRecipeLogic(this::getEfficiency, recipeMap, this);
	}
	
	public GT_MetaTileEntity_BasicGenerator(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap);
		this.efficiency = efficiency;
		recipeLogic = new GeneratorRecipeLogic(this::getEfficiency, recipeMap, this);
	}
	
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isEnetInput() 							{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?12:0;}
	@Override public int maxEUStore()								{return 1000000;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override protected void initRecipeLogic(RecipeMap<?> map) 		{}
    
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public int getInputSlot() 			{return 0;}
	@Override public int getOutputSlot() 			{return 1;}
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
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("nei.extras.eu_total", GT_Utility.parseNumberToString(((GeneratorRecipeLogic)recipeLogic).getLeftEU()))
				.newKey("metatileentity.generator.efficiency", GT_Utility.parseNumberToString(efficiency))
				.build();
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
	
	public int getEfficiency() {
		return this.efficiency;
	}
}
