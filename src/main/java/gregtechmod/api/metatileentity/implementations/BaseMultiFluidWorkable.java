package gregtechmod.api.metatileentity.implementations;

import java.util.List;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InventoryHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public abstract class BaseMultiFluidWorkable extends BaseMultiWorkable {
	
	public int MAX_FLUID_STACK = 16_000;
	protected final List<FluidStack> fluidInputs, fluidOutputs;

	public BaseMultiFluidWorkable(int aID, String aBasicName, RecipeMap<?> map, int fluidInputs, int fluidOutputs) {
		super(aID, aBasicName, map);
		this.fluidInputs = new InventoryHandler<>(fluidInputs);
		this.fluidOutputs = new InventoryHandler<>(fluidOutputs);
		recipeLogic = new RecipeLogic(map, this) {
			@Override protected void triggerMachine(boolean value) {}
		};
	}

	public BaseMultiFluidWorkable(RecipeMap<?> map, int fluidInputs, int fluidOutputs) {
		super(map);
		this.fluidInputs = new InventoryHandler<>(fluidInputs);
		this.fluidOutputs = new InventoryHandler<>(fluidOutputs);
		recipeLogic = new RecipeLogic(map, this) {
			@Override protected void triggerMachine(boolean value) {}
		};
	}

	@Override // Fuck this shit, called before i init fluid IO
	protected void initRecipeLogic(RecipeMap<?> map) {}
	
	@Override
	public boolean spaceForOutput(Recipe recipe) {
		for (FluidStack fluid : recipe.getFluidOutputs()) {
			int amount = fluid.amount;
			for (int i = 0; amount > 0 && i < fluidOutputs.size(); i++) {
				FluidStack stackInSlot = fluidOutputs.get(i);
				if (GT_Utility.isFluidStackValid(stackInSlot) && stackInSlot.isFluidEqual(fluid)) {
					int tmp = Math.min(MAX_FLUID_STACK, stackInSlot.amount + fluid.amount);
					amount -= tmp - stackInSlot.amount;
				} else if (stackInSlot == null) amount = 0;
			}
			
			if (amount > 0)
				return false;
		}
		
		return super.spaceForOutput(recipe);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		if (!fluidInputs.isEmpty()) {
			NBTTagCompound data = new NBTTagCompound();
			for (int i = 0; i < fluidInputs.size(); i++) {
				FluidStack fluid = fluidInputs.get(i);
				NBTTagCompound flTag = fluid == null ? null : fluid.writeToNBT(new NBTTagCompound());
				data.setTag("fluid." + i, flTag);
			}
			
			aNBT.setTag("FluidInputs", data);
		}
		
		if (!fluidOutputs.isEmpty()) {
			NBTTagCompound data = new NBTTagCompound();
			for (int i = 0; i < fluidOutputs.size(); i++) {
				FluidStack fluid = fluidOutputs.get(i);
				NBTTagCompound flTag = fluid == null ? null : fluid.writeToNBT(new NBTTagCompound());
				data.setTag("fluid." + i, flTag);
			}
			
			aNBT.setTag("FluidOutputs", data);
		}
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		
		if (aNBT.hasKey("FluidInputs")) {
			NBTTagCompound data = aNBT.getCompoundTag("FluidInputs");
			for (int i = 0; i < fluidInputs.size(); i++) {
				NBTTagCompound fluid = data.hasKey("fluid." + i) ? data.getCompoundTag("fluid." + i) : null;
				if (fluid != null) {
					fluidInputs.set(i, FluidStack.loadFluidStackFromNBT(fluid));
				}
			}
		}
		
		if (aNBT.hasKey("FluidOutputs")) {
			NBTTagCompound data = aNBT.getCompoundTag("FluidOutputs");
			for (int i = 0; i < fluidOutputs.size(); i++) {
				NBTTagCompound fluid = data.hasKey("fluid." + i) ? data.getCompoundTag("fluid." + i) : null;
				if (fluid != null) {
					fluidOutputs.set(i, FluidStack.loadFluidStackFromNBT(fluid));
				}
			}
		}
	}
	
	@Override
	public List<FluidStack> getFluidInputs() {
		return fluidInputs;
	}
	
	@Override
	public List<FluidStack> getFluidOutputs() {
		return fluidOutputs;
	}
	
	@Override
	public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
		return fluidInputs.isEmpty() || fluidInputs.contains(new FluidStack(aFluid, 0));
	}
	
	@Override
	public boolean canDrain(ForgeDirection aSide, Fluid aFluid) {
		return fluidOutputs.contains(new FluidStack(aFluid, 0));
	}
	
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (GT_Utility.isFluidStackValid(resource)) {
			for (int i = 0; i < fluidInputs.size(); i++) {
				FluidStack stackInSlot = fluidInputs.get(i);
				if (!GT_Utility.isFluidStackValid(stackInSlot) || stackInSlot.isFluidEqual(resource)) {
					int space = getCapacity() - stackInSlot.amount;
					int toFill = resource.amount <= space  ? resource.amount : space;
					if (doFill) {
						stackInSlot.amount += toFill;
					}
					
					return toFill;
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
		if (GT_Utility.isFluidStackValid(aFluid)) 
		for (int i = 0; i < fluidOutputs.size(); i++) {
			FluidStack stackInSlot = fluidOutputs.get(i);
			if (GT_Utility.isFluidStackValid(stackInSlot) && aFluid.isFluidEqual(stackInSlot)) {
				int amount = Math.min(aFluid.amount, stackInSlot.amount);
				FluidStack result = stackInSlot.copy();
				result.amount = amount;
				if (doDrain) {
					if (stackInSlot.amount == amount) {
						fluidOutputs.set(i, null);
					} else stackInSlot.amount -= amount;
				}
				
				return result;
			}
		}
		
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
		for (int i = 0; i < fluidOutputs.size(); i++) {
			FluidStack stackInSlot = fluidOutputs.get(i);
			if (GT_Utility.isFluidStackValid(stackInSlot)) {
				int amount = Math.min(maxDrain, stackInSlot.amount);
				FluidStack result = stackInSlot.copy();
				result.amount = amount;
				if (doDrain) {
					if (stackInSlot.amount == amount) {
						fluidOutputs.set(i, null);
					} else stackInSlot.amount -= amount;
				}
				
				return result;
			}
		}
		
		return null;
	}
}
