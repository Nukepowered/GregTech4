package gregtechmod.api.interfaces;

import java.util.List;

import gregtechmod.api.recipe.Recipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 * Using for mark classes working with RecipeLogic class
 */
public interface IRecipeWorkable {
	public IGregTechTileEntity getBaseMetaTileEntity();
	
	public int getMinimumStoredEU();
	
	public void startProcess();
	
	public void endProcess();
	
	public void stutterProcess();
	
	public boolean allowToCheckRecipe();
	
	public boolean spaceForOutput(Recipe recipe);
	
	public ItemStack getStackIn(int slotIdx);
	
	public List<ItemStack> getInputItems();
	
	public List<ItemStack> getOutputItems();
	
	public List<FluidStack> getFluidInputs();
	
	public List<FluidStack> getFluidOutputs();
}
