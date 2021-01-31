package gregtechmod.api.recipe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.function.Supplier;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class incapsulating main beahvoir of recipes machine
 * @author TheDarkDnKTv
 *
 */
public class RecipeLogic {
	public final WeakReference<GT_MetaTileEntity_BasicMachine> metaTileEntity;
	public final List<Recipe> recipeMap;
	
	protected Supplier<Recipe> recipeHandler;
	protected int maxProgressTime;
	protected int progressTime;
	protected int EUt;
	
	private int overclockersCount;
	private Recipe previousRecipe;
	private boolean needRecipeRecheck;
	
	
	public RecipeLogic(List<Recipe> recipeMap, GT_MetaTileEntity_BasicMachine machine) {
		this.recipeMap = recipeMap;
		maxProgressTime = 0;
		progressTime = 0;
		EUt = 0;
		overclockersCount = 0;
		metaTileEntity = new WeakReference<>(machine);
		needRecipeRecheck = true;
	}
	
	public void update() {
		overclockersCount = getMachine().getBaseMetaTileEntity().getOverclockerUpgradeCount();
		moveItems();
		
		if (getMachine().getBaseMetaTileEntity().isAllowedToWork()) {
			if (getMachine().getBaseMetaTileEntity().hasInventoryBeenModified() || getMachine().getBaseMetaTileEntity().hasWorkJustBeenEnabled())
				needRecipeRecheck = true;
			
			if (progressTime > 0) {
				updateRecipeProgress();
			}
			
			if (progressTime == 0 && needRecipeRecheck) {
				if (isInputNonEmpty()) {
					trySerachRecipe();
				} else {
					previousRecipe = null;
					needRecipeRecheck = false;
					getMachine().getBaseMetaTileEntity().setActive(false);
				}
			}
		}
	}
	
	public void setHandler(Supplier<Recipe> handler) {
		recipeHandler = handler;
	}
	
	protected void updateRecipeProgress() {
		if (getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(EUt * (int)Math.pow(4, overclockersCount), false)) {
			if ((progressTime += (int)Math.pow(2, overclockersCount)) >= maxProgressTime) {
				progressTime = 0;
				maxProgressTime = 0;
				EUt = 0;
				
				endRecipe(previousRecipe);
				getMachine().endProcess();
			}
		} else {
			getMachine().getBaseMetaTileEntity().setActive(false);
			if (!getMachine().bStuttering) {
				getMachine().stutterProcess();
				if (getMachine().useStandardStutterSound()) getMachine().sendSound((byte)8);
				getMachine().bStuttering = true;
			}
		}
	}
	
	protected void trySerachRecipe() {
		if (getMachine().allowToCheckRecipe()) {
			if (previousRecipe != null) {
				if (previousRecipe.match(false, getMachine().getBaseMetaTileEntity(), getMachineInputs())) { // TODO add I/O item handlers to MTE
					startRecipe(previousRecipe);
				} else {
					previousRecipe = null;
					getMachine().getBaseMetaTileEntity().setActive(false);
				}
			} else {
				// find new recipe
				Recipe resRec = recipeHandler != null ? recipeHandler.get() :
					recipeMap.stream()
					.filter(rec -> rec.match(false, getMachine().getBaseMetaTileEntity(), getMachineInputs()))
					.findFirst().orElse(null);
				if (resRec != null)
					startRecipe(resRec);
			}
			
			needRecipeRecheck = false;
		}
	}
	
	protected void moveItems() {
		// Slot 0 = HoloSlot
		// Slot 1 = Left Input
		// Slot 2 = right Input
		// Slot 3 = left Output
		// Slot 4 = right Output
		// Slot 5 = battery Slot in most cases
		IInventory inv = getMachine().getBaseMetaTileEntity();
		int[] in = getMachine().getInputSlots();
		int[] out = getMachine().getOutputSlots();
		if (in.length > 1) GT_Utility.moveStackFromSlotAToSlotB(inv, inv, in[0], in[1], (byte)64, (byte)1, (byte)64, (byte)1);
		if (out.length > 1)  GT_Utility.moveStackFromSlotAToSlotB(inv, inv, out[0], out[1], (byte)64, (byte)1, (byte)64, (byte)1);
	}
	
	protected void startRecipe(Recipe recipe) {
		if (getMachine().spaceForOutput(recipe.getOutputs()[0], recipe.getOutputs().length > 1 ? recipe.getOutputs()[1] : null)) {
			previousRecipe = recipe;
			maxProgressTime = recipe.mDuration;
			progressTime = 1;
			EUt = recipe.mEUt;
			recipe.match(true, getMachine().getBaseMetaTileEntity(), getMachineInputs());
			getMachine().getBaseMetaTileEntity().setActive(true);
			getMachine().startProcess();
		}
	}
	
	protected void endRecipe(Recipe recipe) {
		ItemStack[] outputs = recipe.getOutputs();
		if (outputs.length <= getMachineOutputs().length) {
			for (ItemStack out : outputs) {
				for (int i : getMachineOutputs()) {
					if (getMachine().getBaseMetaTileEntity().addStackToSlot(i, out.copy())) {
						break;
					}
				}
			}
		} else {
			GT_Log.log.catching(new IllegalStateException("Found recipe with more items output machine has slots!"));
		}
		
		getMachine().bStuttering = false;
		getMachine().endProcess();
	}
	
	private boolean isInputNonEmpty() {
		for (int i : getMachineInputs()) {
			ItemStack s = getMachine().getStackInSlot(i);
			if (s != null && s.stackSize > 0) return true;
		}
		
		
		return false;
	}
	
	/**
	 * Specify machine input slots
	 */
	protected int[] getMachineInputs() {
		return new int[] {1, 2};
	}
	
	protected int[] getMachineOutputs() {
		return new int[] {3, 4};
	}
	
	private GT_MetaTileEntity_BasicMachine getMachine() {
		return metaTileEntity.get();
	}
	
	public boolean isActive() {
		return maxProgressTime > 0;
	}
	
	public int getMaxProgressTime() {
		return maxProgressTime;
	}
	
	public int getProgressTime() {
		return progressTime;
	}
	
	public int getEUt() {
		return EUt;
	}
	
	public void saveToNBT(NBTTagCompound data) {
		NBTTagCompound data1 = new NBTTagCompound();
		data1.setInteger("TotalTime", maxProgressTime);
		data1.setInteger("CurrentTime", progressTime);
		data1.setInteger("EUt", EUt);
		data.setTag("RecipeLogic", data1);
	}
	
	public void loadFromNBT(NBTTagCompound data) {
		NBTTagCompound data1 = data.getCompoundTag("RecipeLogic");
		if (data1 != null) {
			maxProgressTime = data1.getInteger("TotalTime");
			progressTime = data1.getInteger("CurrentTime");
			EUt = data1.getInteger("EUt");
		}
	}
}
