package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class GT_MetaTileEntity_MagicEnergyConverter extends GT_MetaTileEntity_BasicTank {
	
	public GT_MetaTileEntity_MagicEnergyConverter(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_MagicEnergyConverter() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?24:0;}
	@Override public int maxEUStore()								{return 1000000000;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 125);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_MagicEnergyConverter();
	}
	
	@Override public boolean doesFillContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return false;}
	@Override public boolean isFluidInputAllowed(FluidStack aFluid) {return isValidFuel(aFluid);}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getTimer()%10==0) {
    		if (mFluid == null) {
    			if (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()))
    				mInventory[2] = null;
    			else
    				if (mInventory[2] == null)
    					mInventory[2] = new ItemStack(Blocks.fire, 1);
    		} else {
    			if (getFuelValue(mFluid) > 0) while (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()) && mFluid.amount > 0) {
    				if (getBaseMetaTileEntity().increaseStoredEnergyUnits(getFuelValue(mFluid), true)) mFluid.amount--;
    			}
    		}
    		if (mInventory[0] != null && getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU())) {
    			for (int i = 0; i < GT_Recipe.sMagicFuels.size(); i++) {
    				if (GT_Utility.areStacksEqual(mInventory[0], GT_Recipe.sMagicFuels.get(i).mInput1)) {
    					if (GT_Utility.getFluidForFilledItem(mInventory[0]) == null) {
    						if (GT_Recipe.sMagicFuels.get(i).mOutput1 != null) {
    							if (mInventory[1] == null) {
    								if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sMagicFuels.get(i).mStartEU*1000, true)) {
    									getBaseMetaTileEntity().decrStackSize(0, 1);
    									mInventory[1] = GT_Utility.copy(GT_Recipe.sMagicFuels.get(i).mOutput1);
    								}
    							} else if (GT_Utility.areStacksEqual(mInventory[1], GT_Recipe.sMagicFuels.get(i).mOutput1) && mInventory[1].stackSize + GT_Recipe.sMagicFuels.get(i).mOutput1.stackSize <= mInventory[1].getMaxStackSize()) {
    								if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sMagicFuels.get(i).mStartEU*1000, true)) {
        								getBaseMetaTileEntity().decrStackSize(0, 1);
        	    						mInventory[1].stackSize += GT_Recipe.sMagicFuels.get(i).mOutput1.stackSize;
    								}
    							}
    						} else {
    							if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sMagicFuels.get(i).mStartEU*1000, true)) {
    								getBaseMetaTileEntity().decrStackSize(0, 1);
    							}
    						}
    					}
    					break;
    				}
    			}
			}
    	}
    	
    	if (getBaseMetaTileEntity().isServerSide()) getBaseMetaTileEntity().setActive(getBaseMetaTileEntity().getUniversalEnergyStored() >= getBaseMetaTileEntity().getOutputVoltage() + getMinimumStoredEU());
    }
    
    @Override
	public void onExplosion() {
	   	try {
	   		TileEntity te = (TileEntity) this.getBaseMetaTileEntity();
    		World tWorld = this.getBaseMetaTileEntity().getWorld();
    		int iterations = getBaseMetaTileEntity().getRandomNumber(200) + 100;
			int x = 0;
			int z = 0;
			int y = 0;
			for (int i = 0; i < iterations; i++) {
				x = te.xCoord + tWorld.rand.nextInt(16) - tWorld.rand.nextInt(16);
				z = te.zCoord + tWorld.rand.nextInt(16) - tWorld.rand.nextInt(16);
				final BiomeGenBase bg = tWorld.getBiomeGenForCoords(x, z);
				if (bg.biomeID != ThaumcraftWorldGenerator.biomeTaint.biomeID) {
					Utils.setBiomeAt(tWorld, x, z, ThaumcraftWorldGenerator.biomeTaint);
				}
				if (tWorld.rand.nextBoolean()) {
					x = te.xCoord + tWorld.rand.nextInt(10) - tWorld.rand.nextInt(10);
					z = te.zCoord + tWorld.rand.nextInt(10) - tWorld.rand.nextInt(10);
					y = te.yCoord + tWorld.rand.nextInt(5) - tWorld.rand.nextInt(5);
					if (BlockTaintFibres.spreadFibres(tWorld, x, y, z)) {
					}
				}
			}
		} catch(Throwable e) {}
    }
    
    public boolean isValidFuel(FluidStack aFluid) {
    	return getFuelValue(aFluid)>0;
    }
    
    /**
     * @return EU per MB
     */
    public int getFuelValue(FluidStack aFluid) {
    	if (aFluid == null) return 0;
    	FluidStack tFluid;
    	for (int i = 0; i < GT_Recipe.sMagicFuels.size(); i++) {
    		if ((tFluid = GT_Utility.getFluidForFilledItem(GT_Recipe.sMagicFuels.get(i).mInput1)) != null) if (aFluid.isFluidEqual(tFluid)) return GT_Recipe.sMagicFuels.get(i).mStartEU;
        }
    	return 0;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == aFacing)
    		return aActive?86:83;
    	else
    		return 90;
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public String getDescription() {
		return "A Generator running off Magic Fuel";
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
