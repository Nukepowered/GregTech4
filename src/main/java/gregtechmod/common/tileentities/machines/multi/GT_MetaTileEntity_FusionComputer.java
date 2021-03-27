package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.metatileentity.implementations.MetaTileEntityMultiblock;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.api.util.InventoryHandler;
import gregtechmod.api.util.InventoryHandlerList;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.api.util.WeakList;
import gregtechmod.common.recipe.RecipeMaps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_FusionComputer extends MetaTileEntity implements IRecipeWorkable {

	/* STRUCTURE STATUS */
	protected boolean structComplete 	= false;
	protected boolean needCheckStruct 	= true;
	
	protected int MAX_FLUID_STACK = 16_000;
	
	protected RecipeLogic recipeLogic;
	
	private WeakList<GT_MetaTileEntity_BasicTank> mPlasmaExtractors   	= new WeakList<>();
	private WeakList<GT_MetaTileEntity_BasicTank> mPlasmaInjectors   	= new WeakList<>();
	private WeakList<IGregTechTileEntity> mEnergyInjectors   			= new WeakList<>();

	protected List<FluidStack> fluidInputs	= null;
	protected List<FluidStack> fluidOutputs	= null;
	protected List<ItemStack> itemInputs	= null;
	protected List<ItemStack> itemOutputs	= null;
	
	public GT_MetaTileEntity_FusionComputer(int aID, String mName) {
		super(aID, mName);
		recipeLogic = new FusionRecipeLogic();
	}
	
	public GT_MetaTileEntity_FusionComputer() {
		recipeLogic = new FusionRecipeLogic();
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public int getInvSize()								{return 1;}
    @Override public int maxEUStore()								{return mEnergyInjectors.size() * 10000000;}
    @Override public int getEUVar()									{return getStoredEU();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 143);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int increaseProgress(int aProgress)			{return aProgress;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_FusionComputer();
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	private void setComputerOf(MetaTileEntity aMetaTileEntity, boolean setreset) {
		if (aMetaTileEntity != null) {
			if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector) {
				((GT_MetaTileEntity_FusionInjector)aMetaTileEntity).setComputer(setreset?getBaseMetaTileEntity():null);
				if (setreset)
					mPlasmaInjectors.add((GT_MetaTileEntity_FusionInjector)aMetaTileEntity);
			}
			if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector) {
				((GT_MetaTileEntity_FusionEnergyInjector)aMetaTileEntity).setComputer(setreset?getBaseMetaTileEntity():null);
				if (setreset)
					mEnergyInjectors.add(aMetaTileEntity.getBaseMetaTileEntity());
			}
			if (aMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor) {
				((GT_MetaTileEntity_FusionExtractor)aMetaTileEntity).setComputer(setreset?getBaseMetaTileEntity():null);
				if (setreset)
					mPlasmaExtractors.add((GT_MetaTileEntity_FusionExtractor)aMetaTileEntity);
			}
		}
	}
	
	private void reset() {
		for (GT_MetaTileEntity_BasicTank tTileEntity : mPlasmaExtractors  ) setComputerOf(tTileEntity, false);
		for (GT_MetaTileEntity_BasicTank tTileEntity : mPlasmaInjectors  ) setComputerOf(tTileEntity, false);
		for (IGregTechTileEntity tTileEntity : mEnergyInjectors   ) setComputerOf((MetaTileEntity)tTileEntity.getMetaTileEntity(), false);
		
		mPlasmaExtractors.clear();
		mPlasmaInjectors.clear();
		mEnergyInjectors.clear();
		
		fluidInputs = null;
		fluidOutputs = null;
		itemInputs = null;
		itemOutputs = null;
	}
	
    @Override
    public void onMachineBlockUpdate() {
    	needCheckStruct = true;
    }
    
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		if (needCheckStruct) {
    			reset();
    			structComplete = checkMachine();
        		needCheckStruct = false;
        		
        		if (structComplete) {
        			fluidInputs = new InventoryHandlerList<>(mPlasmaInjectors.stream().map(extr -> new ListAdapter<>(extr.mFluid)).collect(Collectors.toList()));
        			fluidOutputs = new InventoryHandlerList<>(mPlasmaExtractors.stream().map(extr -> new ListAdapter<>(extr.mFluid)).collect(Collectors.toList()));
        			itemInputs = new InventoryHandlerList<>(mPlasmaInjectors.stream().map(extr -> new ListAdapter<>(extr.mInventory, extr.getInputSlot(), extr.getInputSlot())).collect(Collectors.toList()));
        			itemOutputs = new InventoryHandlerList<>(mPlasmaExtractors.stream().map(extr -> new ListAdapter<>(extr.mInventory, extr.getOutputSlot(), extr.getOutputSlot())).collect(Collectors.toList()));
        			getBaseMetaTileEntity().setErrorDisplayID(0);
        		} else {
        			getBaseMetaTileEntity().setErrorDisplayID(1);
        		}
    		} else if (!structComplete && getBaseMetaTileEntity().getTimer() % 600 == 0) {
    			needCheckStruct = true;
    		}
    		
    		if (structComplete) {
    			recipeLogic.update();
    		} else recipeLogic.stop();
    	}
    }
    
    public boolean hasInventoryBeenModified() {
		for (GT_MetaTileEntity_BasicTank hatch : mPlasmaInjectors) {
			if (MetaTileEntityMultiblock.isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		for (GT_MetaTileEntity_BasicTank hatch : mPlasmaExtractors) {
			if (MetaTileEntityMultiblock.isValidMetaTileEntity(hatch)) {
				if (hatch.getBaseMetaTileEntity().hasInventoryBeenModified()) {
					return true;
				}
			}
		}
		
		return getBaseMetaTileEntity().hasInventoryBeenModified();
	}
    
    private int getStoredEU() {
    	int rEU = 0;
    	for (IGregTechTileEntity tTileEntity : mEnergyInjectors) {
    		rEU += tTileEntity.getUniversalEnergyStored();
    	}
    	return rEU;
    }
    
    private boolean decreaseStoredEU(int aEU) {
    	if (aEU <= 0) return true;
    	if (getStoredEU() < aEU) return false;
    	for (IGregTechTileEntity tTileEntity : mEnergyInjectors) {
    		if (aEU > tTileEntity.getUniversalEnergyStored()) {
    			aEU -= tTileEntity.getUniversalEnergyStored();
    			if (!tTileEntity.decreaseStoredEnergyUnits(tTileEntity.getUniversalEnergyStored(), true)) return false;
    		} else {
    			return tTileEntity.decreaseStoredEnergyUnits(aEU, true);
    		}
    	}
    	return false;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide != aFacing) return aActive?20:19;
		return aActive?50:48;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", recipeLogic.getDisplayProgress() * 100.0D / recipeLogic.getDisplayMaxProgress())
				.newKey("sensor.progress.secs", recipeLogic.getDisplayProgress() / 20)
				.newKey("sensor.progress.secs.1", recipeLogic.getDisplayMaxProgress() / 20)
				.newKey("nei.extras.eu_total", GT_Utility.parseNumberToString(getStoredEU()))
				.build();
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	@Override
	public String getDescription() {
		return "metatileentity.GT_Fusion_Computer.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
    private boolean checkMachine() {
    	reset();
    	int xCenter = getBaseMetaTileEntity().getXCoord() + ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX*5, yCenter = getBaseMetaTileEntity().getYCoord(), zCenter = getBaseMetaTileEntity().getZCoord() + ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ*5;
    	
    	if (isAdvancedMachineCasing(xCenter + 5, yCenter, zCenter) || xCenter + 5 == getBaseMetaTileEntity().getXCoord())
    	if (isAdvancedMachineCasing(xCenter - 5, yCenter, zCenter) || xCenter - 5 == getBaseMetaTileEntity().getXCoord())
    	if (isAdvancedMachineCasing(xCenter, yCenter, zCenter + 5) || zCenter + 5 == getBaseMetaTileEntity().getZCoord())
    	if (isAdvancedMachineCasing(xCenter, yCenter, zCenter - 5) || zCenter - 5 == getBaseMetaTileEntity().getZCoord())
    	if (checkCoils(xCenter, yCenter, zCenter))
    	if (checkHulls(xCenter, yCenter, zCenter))
    	if (checkUpperOrLowerHulls(xCenter, yCenter+1, zCenter))
    	if (checkUpperOrLowerHulls(xCenter, yCenter-1, zCenter))
        if (addIfEnergyInjector(xCenter+4, yCenter, zCenter+3))
        if (addIfEnergyInjector(xCenter+4, yCenter, zCenter-3))
        if (addIfEnergyInjector(xCenter+4, yCenter, zCenter+5))
        if (addIfEnergyInjector(xCenter+4, yCenter, zCenter-5))
        if (addIfEnergyInjector(xCenter-4, yCenter, zCenter+3))
        if (addIfEnergyInjector(xCenter-4, yCenter, zCenter-3))
        if (addIfEnergyInjector(xCenter-4, yCenter, zCenter+5))
        if (addIfEnergyInjector(xCenter-4, yCenter, zCenter-5))
        if (addIfEnergyInjector(xCenter+3, yCenter, zCenter+4))
        if (addIfEnergyInjector(xCenter-3, yCenter, zCenter+4))
        if (addIfEnergyInjector(xCenter+5, yCenter, zCenter+4))
        if (addIfEnergyInjector(xCenter-5, yCenter, zCenter+4))
        if (addIfEnergyInjector(xCenter+3, yCenter, zCenter-4))
        if (addIfEnergyInjector(xCenter-3, yCenter, zCenter-4))
        if (addIfEnergyInjector(xCenter+5, yCenter, zCenter-4))
        if (addIfEnergyInjector(xCenter-5, yCenter, zCenter-4))
        if (addIfExtractor(xCenter+1, yCenter, zCenter-5))
        if (addIfExtractor(xCenter+1, yCenter, zCenter+5))
        if (addIfExtractor(xCenter-1, yCenter, zCenter-5))
        if (addIfExtractor(xCenter-1, yCenter, zCenter+5))
        if (addIfExtractor(xCenter+1, yCenter, zCenter-7))
        if (addIfExtractor(xCenter+1, yCenter, zCenter+7))
        if (addIfExtractor(xCenter-1, yCenter, zCenter-7))
        if (addIfExtractor(xCenter-1, yCenter, zCenter+7))
        if (addIfExtractor(xCenter+5, yCenter, zCenter-1))
        if (addIfExtractor(xCenter+5, yCenter, zCenter+1))
        if (addIfExtractor(xCenter-5, yCenter, zCenter-1))
        if (addIfExtractor(xCenter-5, yCenter, zCenter+1))
        if (addIfExtractor(xCenter+7, yCenter, zCenter-1))
        if (addIfExtractor(xCenter+7, yCenter, zCenter+1))
        if (addIfExtractor(xCenter-7, yCenter, zCenter-1))
        if (addIfExtractor(xCenter-7, yCenter, zCenter+1))
        if (addIfInjector(xCenter+1, yCenter+1, zCenter-6))
        if (addIfInjector(xCenter+1, yCenter+1, zCenter+6))
        if (addIfInjector(xCenter-1, yCenter+1, zCenter-6))
        if (addIfInjector(xCenter-1, yCenter+1, zCenter+6))
        if (addIfInjector(xCenter-6, yCenter+1, zCenter+1))
        if (addIfInjector(xCenter+6, yCenter+1, zCenter+1))
        if (addIfInjector(xCenter-6, yCenter+1, zCenter-1))
        if (addIfInjector(xCenter+6, yCenter+1, zCenter-1))
        if (addIfInjector(xCenter+1, yCenter-1, zCenter-6))
        if (addIfInjector(xCenter+1, yCenter-1, zCenter+6))
        if (addIfInjector(xCenter-1, yCenter-1, zCenter-6))
        if (addIfInjector(xCenter-1, yCenter-1, zCenter+6))
        if (addIfInjector(xCenter-6, yCenter-1, zCenter+1))
        if (addIfInjector(xCenter+6, yCenter-1, zCenter+1))
        if (addIfInjector(xCenter-6, yCenter-1, zCenter-1))
        if (addIfInjector(xCenter+6, yCenter-1, zCenter-1))
        	return true;
    	reset();
    	return false;
    }
    
    private boolean checkCoils(int aX, int aY, int aZ) {
		return     isFusionCoil(aX + 6, aY, aZ - 1)
				&& isFusionCoil(aX + 6, aY, aZ    )
				&& isFusionCoil(aX + 6, aY, aZ + 1)
				
				&& isFusionCoil(aX + 5, aY, aZ - 3)
				&& isFusionCoil(aX + 5, aY, aZ - 2)
				&& isFusionCoil(aX + 5, aY, aZ + 2)
				&& isFusionCoil(aX + 5, aY, aZ + 3)
				
				&& isFusionCoil(aX + 4, aY, aZ - 4)
				&& isFusionCoil(aX + 4, aY, aZ + 4)
				
				&& isFusionCoil(aX + 3, aY, aZ - 5)
				&& isFusionCoil(aX + 3, aY, aZ + 5)
				
				&& isFusionCoil(aX + 2, aY, aZ - 5)
				&& isFusionCoil(aX + 2, aY, aZ + 5)
				
				&& isFusionCoil(aX + 1, aY, aZ - 6)
				&& isFusionCoil(aX + 1, aY, aZ + 6)
				
				&& isFusionCoil(aX    , aY, aZ - 6)
				&& isFusionCoil(aX    , aY, aZ + 6)
				
				&& isFusionCoil(aX - 1, aY, aZ - 6)
				&& isFusionCoil(aX - 1, aY, aZ + 6)
				
				&& isFusionCoil(aX - 2, aY, aZ - 5)
				&& isFusionCoil(aX - 2, aY, aZ + 5)
				
				&& isFusionCoil(aX - 3, aY, aZ - 5)
				&& isFusionCoil(aX - 3, aY, aZ + 5)
				
				&& isFusionCoil(aX - 4, aY, aZ - 4)
				&& isFusionCoil(aX - 4, aY, aZ + 4)
				
				&& isFusionCoil(aX - 5, aY, aZ - 3)
				&& isFusionCoil(aX - 5, aY, aZ - 2)
				&& isFusionCoil(aX - 5, aY, aZ + 2)
				&& isFusionCoil(aX - 5, aY, aZ + 3)
				
				&& isFusionCoil(aX - 6, aY, aZ - 1)
				&& isFusionCoil(aX - 6, aY, aZ    )
				&& isFusionCoil(aX - 6, aY, aZ + 1);
    }
    
    private boolean checkUpperOrLowerHulls(int aX, int aY, int aZ) {
		return     isAdvancedMachineCasing(aX + 6, aY, aZ    )
				
				&& isAdvancedMachineCasing(aX + 5, aY, aZ - 3)
				&& isAdvancedMachineCasing(aX + 5, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX + 5, aY, aZ + 2)
				&& isAdvancedMachineCasing(aX + 5, aY, aZ + 3)
				
				&& isAdvancedMachineCasing(aX + 4, aY, aZ - 4)
				&& isAdvancedMachineCasing(aX + 4, aY, aZ + 4)
				
				&& isAdvancedMachineCasing(aX + 3, aY, aZ - 5)
				&& isAdvancedMachineCasing(aX + 3, aY, aZ + 5)
				
				&& isAdvancedMachineCasing(aX + 2, aY, aZ - 5)
				&& isAdvancedMachineCasing(aX + 2, aY, aZ + 5)
				
				&& isAdvancedMachineCasing(aX    , aY, aZ - 6)
				&& isAdvancedMachineCasing(aX    , aY, aZ + 6)
				
				&& isAdvancedMachineCasing(aX - 2, aY, aZ - 5)
				&& isAdvancedMachineCasing(aX - 2, aY, aZ + 5)
				
				&& isAdvancedMachineCasing(aX - 3, aY, aZ - 5)
				&& isAdvancedMachineCasing(aX - 3, aY, aZ + 5)
				
				&& isAdvancedMachineCasing(aX - 4, aY, aZ - 4)
				&& isAdvancedMachineCasing(aX - 4, aY, aZ + 4)
				
				&& isAdvancedMachineCasing(aX - 5, aY, aZ - 3)
				&& isAdvancedMachineCasing(aX - 5, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX - 5, aY, aZ + 2)
				&& isAdvancedMachineCasing(aX - 5, aY, aZ + 3)
				
				&& isAdvancedMachineCasing(aX - 6, aY, aZ    );
    }
    
    private boolean checkHulls(int aX, int aY, int aZ) {
		return     isAdvancedMachineCasing(aX + 6, aY, aZ - 3)
				&& isAdvancedMachineCasing(aX + 6, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX + 6, aY, aZ + 2)
				&& isAdvancedMachineCasing(aX + 6, aY, aZ + 3)
				
				&& isAdvancedMachineCasing(aX + 3, aY, aZ - 6)
				&& isAdvancedMachineCasing(aX + 3, aY, aZ + 6)
				&& isAdvancedMachineCasing(aX + 2, aY, aZ - 6)
				&& isAdvancedMachineCasing(aX + 2, aY, aZ + 6)
				
				&& isAdvancedMachineCasing(aX - 2, aY, aZ - 6)
				&& isAdvancedMachineCasing(aX - 2, aY, aZ + 6)
				&& isAdvancedMachineCasing(aX - 3, aY, aZ - 6)
				&& isAdvancedMachineCasing(aX - 3, aY, aZ + 6)
				
				&& isAdvancedMachineCasing(aX - 7, aY, aZ    )
				&& isAdvancedMachineCasing(aX + 7, aY, aZ    )
				&& isAdvancedMachineCasing(aX    , aY, aZ - 7)
				&& isAdvancedMachineCasing(aX    , aY, aZ + 7)
				
				&& isAdvancedMachineCasing(aX - 6, aY, aZ - 3)
				&& isAdvancedMachineCasing(aX - 6, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX - 6, aY, aZ + 2)
				&& isAdvancedMachineCasing(aX - 6, aY, aZ + 3)
				
				&& isAdvancedMachineCasing(aX - 4, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX - 4, aY, aZ + 2)
				&& isAdvancedMachineCasing(aX + 4, aY, aZ - 2)
				&& isAdvancedMachineCasing(aX + 4, aY, aZ + 2)
				
				&& isAdvancedMachineCasing(aX - 2, aY, aZ - 4)
				&& isAdvancedMachineCasing(aX - 2, aY, aZ + 4)
				&& isAdvancedMachineCasing(aX + 2, aY, aZ - 4)
				&& isAdvancedMachineCasing(aX + 2, aY, aZ + 4);
    }
    
    private boolean addIfEnergyInjector(int aX, int aY, int aZ) {
    	if (isEnergyInjector(aX, aY, aZ)) {
    		setComputerOf(getMetaTileEntity(aX, aY, aZ), true);
    		return true;
    	}
    	return isAdvancedMachineCasing(aX, aY, aZ);
    }
    
    private boolean addIfInjector(int aX, int aY, int aZ) {
    	if (isInjector(aX, aY, aZ)) {
    		setComputerOf(getMetaTileEntity(aX, aY, aZ), true);
    		return true;
    	}
    	return isAdvancedMachineCasing(aX, aY, aZ);
    }
    
    private boolean addIfExtractor(int aX, int aY, int aZ) {
    	if (isExtractor(aX, aY, aZ)) {
    		setComputerOf(getMetaTileEntity(aX, aY, aZ), true);
    		return true;
    	}
    	return isAdvancedMachineCasing(aX, aY, aZ);
    }
    
    private boolean isAdvancedMachineCasing(int aX, int aY, int aZ) {
    	return getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockList[0] && getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 15;
    }
    
    private boolean isFusionCoil(int aX, int aY, int aZ) {
    	return getBaseMetaTileEntity().getBlock(aX, aY, aZ) == GregTech_API.sBlockList[0] && getBaseMetaTileEntity().getMetaID(aX, aY, aZ) ==  1;
    }
    
    private boolean isEnergyInjector(int aX, int aY, int aZ) {
    	MetaTileEntity tMetaTileEntity = getMetaTileEntity(aX, aY, aZ);
    	if (tMetaTileEntity == null) return false;
    	return tMetaTileEntity instanceof GT_MetaTileEntity_FusionEnergyInjector;
    }
    
    private boolean isInjector(int aX, int aY, int aZ) {
    	MetaTileEntity tMetaTileEntity = getMetaTileEntity(aX, aY, aZ);
    	if (tMetaTileEntity == null) return false;
    	return tMetaTileEntity instanceof GT_MetaTileEntity_FusionInjector;
    }
    
    private boolean isExtractor(int aX, int aY, int aZ) {
    	MetaTileEntity tMetaTileEntity = getMetaTileEntity(aX, aY, aZ);
    	if (tMetaTileEntity == null) return false;
    	return tMetaTileEntity instanceof GT_MetaTileEntity_FusionExtractor;
    }
    
    private MetaTileEntity getMetaTileEntity(int aX, int aY, int aZ) {
    	TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntity(aX, aY, aZ);
    	if (tTileEntity == null || !(tTileEntity instanceof IGregTechTileEntity)) return null;
    	Object tObject = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity();
    	if (tObject == null || !(tObject instanceof MetaTileEntity)) return null;
    	return (MetaTileEntity)tObject;
    }

	@Override
	public void startProcess() {}

	@Override
	public void endProcess() {}

	@Override
	public void stutterProcess() {}

	@Override
	public boolean allowToCheckRecipe() {
		return true;
	}

	@Override
	public boolean spaceForOutput(Recipe recipe) {
		List<ItemStack> outputSlots = this.getOutputItems();
		List<ItemStack> allOutputs = recipe.getAllOutputs();
		
		for (ItemStack current : allOutputs) {
			int amount = current.stackSize;
			for (int i = 0; current != null && amount > 0 && i < outputSlots.size(); i++) {
				ItemStack slot = outputSlots.get(i);
				if (slot == null) {
					amount = 0;
					break;
				} else if (GT_Utility.areStacksEqual(slot, current)) {
					int newSize = Math.min(slot.getMaxStackSize(), amount + slot.stackSize);
					amount -= newSize;
				}
			}
			
			if (amount > 0) {
				return false;
			}
		}
		
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
		
		return true;
	}

	@Override
	public List<ItemStack> getInputItems() {
		if (structComplete && itemInputs != null) {
			return itemInputs;
		}
		
		return new InventoryHandler<>(2);
	}

	@Override
	public List<ItemStack> getOutputItems() {
		if (structComplete && itemOutputs != null) {
			return itemOutputs;
		}
		
		return new InventoryHandler<>(1);
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		if (structComplete && fluidInputs != null) {
			return fluidInputs;
		}
		
		return new InventoryHandler<>(2);
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		if (structComplete && fluidOutputs != null) {
			return fluidOutputs;
		}
		
		return new InventoryHandler<>(1);
	}
	
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	recipeLogic.saveToNBT(aNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	recipeLogic.loadFromNBT(aNBT);
	}
	
	private class FusionRecipeLogic extends RecipeLogic {
		
		private boolean firstStart = true;
		
		protected FusionRecipeLogic() {
			super(RecipeMaps.FUSION_REACTOR, GT_MetaTileEntity_FusionComputer.this);
		}
		
		@Override
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			GT_MetaTileEntity_FusionComputer machine = (GT_MetaTileEntity_FusionComputer)getMachine();
			overclockersCount = base.getOverclockerUpgradeCount();
			
			if (base.isAllowedToWork()) {
				if (progressTime > 0) {
					int tmp = progressTime;
					success = updateRecipeProgress();
					if (tmp == 0 && !success) {
						throw new IllegalStateException();
					}
				}
				
				if (progressTime == 0) {
					if (machine.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {
						if (machine.getStoredEU() >= machine.getMinimumStoredEU() - 100) {
							trySerachRecipe();
							wasNoEnergy = false;
						} else {
							previousRecipe = null;
							wasNoEnergy = true;
							triggerMachine(false);
						} 
					} else {
						previousRecipe = null;
					}
				}
			} 
			
			return success;
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if (((GT_MetaTileEntity_FusionComputer)getMachine()).decreaseStoredEU(EUt)) {
				if ((progressTime += progressTimeManipulator.applyAsInt((int)Math.pow(2, overclockersCount))) >= maxProgressTime) {
					progressTime = 0;
					maxProgressTime = 0;
					EUt = 0;
					
					endRecipe(previousRecipe);
					getMachine().endProcess();
					return true;
				}
			} else stop();
			
			return false;
		}
		
		@Override
		protected void trySerachRecipe() {
			if (getMachine().allowToCheckRecipe()) {
				if (previousRecipe != null) {
					if (match(previousRecipe)) {
						startRecipe(previousRecipe);
					} else {
						previousRecipe = null;
						triggerMachine(false);
					}
				} else {
					firstStart = true;
					Recipe resRec = findRecipe();
					if (resRec != null)
						startRecipe(resRec);
					firstStart = false;
				}
			}
		}
		
		
		@Override
		protected void startRecipe(Recipe recipe) {
			if (getMachine().spaceForOutput(recipe) && firstStart ? ((GT_MetaTileEntity_FusionComputer)getMachine()).decreaseStoredEU(recipe.getEUtoStart()) : true) {
				previousRecipe = recipe;
				maxProgressTime = recipe.getDuration();
				progressTime = 1;
				EUt = recipe.getEUt();
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
				
			} else stop();
		}
		
		@Override
		public void stop() {
			super.stop();
			getMachine().getBaseMetaTileEntity().disableWorking();
			triggerMachine(false);
			firstStart = true;
		}
		
		@Override
		public void saveToNBT(NBTTagCompound data) {
			super.saveToNBT(data);
			NBTTagCompound data1 = data.getCompoundTag("RecipeLogic");
			data1.setBoolean("firstStart", firstStart);
			data.setTag("RecipeLogic", data1);
		}
		
		@Override
		public void loadFromNBT(NBTTagCompound data) {
			super.loadFromNBT(data);
			NBTTagCompound data1 = data.getCompoundTag("RecipeLogic");
			firstStart = data1.getBoolean("firstStart");
		}
	}

	@Override
	public ItemStack getStackIn(int slotIdx) {
		return super.getStackInSlot(slotIdx);
	}
}
