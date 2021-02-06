package gregtechmod.api.interfaces;

import java.util.List;

import gregtechmod.api.recipe.Recipe;
import net.minecraft.item.ItemStack;

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
	
	public ItemStack getStackInSlot(int slotIdx);
	
	/**
	 * Array of input slot ids
	 */
	public List<ItemStack> getInputItems();
	
	/**
	 * Array of output slot ids
	 */
	public List<ItemStack> getOutputItems();
}
