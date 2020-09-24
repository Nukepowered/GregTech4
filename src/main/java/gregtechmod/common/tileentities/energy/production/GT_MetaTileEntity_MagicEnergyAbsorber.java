package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Log;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_MagicEnergyAbsorber extends MetaTileEntity {
	public static int sEnergyPerEnderCrystal = 32, sEnergyFromVis = 12800;
	public static final ArrayList<EntityEnderCrystal> sUsedDragonCrystalList = new ArrayList<EntityEnderCrystal>();
	public static final boolean THAUMCRAFT_LOADED = Loader.isModLoaded("Thaumcraft");
	
	public EntityEnderCrystal mTargetedCrystal;
	private int elementIndex = 0;
	private Object energySource = null;
	public boolean isActive1 = false, isActive2 = false;
	
	private ArrayList<?> supportedAspects = null;
	
	public GT_MetaTileEntity_MagicEnergyAbsorber(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_MagicEnergyAbsorber() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public int maxEUOutput()								{return Math.max(128, sEnergyPerEnderCrystal);}
	@Override public int getInvSize()								{return 3;}
	@Override public int maxEUStore()								{return Math.max(1000000, Math.max(sEnergyFromVis, sEnergyPerEnderCrystal));}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 126);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_MagicEnergyAbsorber();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setBoolean("isActive1", isActive1);
		aNBT.setBoolean("isActive2", isActive2);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		isActive1 = aNBT.getBoolean("isActive1");
		isActive2 = aNBT.getBoolean("isActive2");
	}
	
	@Override
	public void onConfigLoad(GT_Config aConfig) {
    	sEnergyPerEnderCrystal	= aConfig.get(GT_ConfigCategories.machineconfig, "MagicEnergyAbsorber.EnergyPerTick.EnderCrystal", 32);
    	sEnergyFromVis			= aConfig.get(GT_ConfigCategories.machineconfig, "MagicEnergyAbsorber.EnergyPerVis", 12800);
	}
	
	@Override
	public void onServerStart() {
		sUsedDragonCrystalList.clear();
	}
	
	@Override
	public void onServerStop() {
		sUsedDragonCrystalList.clear();
    }
	
    @SuppressWarnings("unchecked")
	@Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && energySource == null && getBaseMetaTileEntity().getWorld().getWorldTime() % 20 == 0 && supportedAspects != null) {
    		elementIndex = (elementIndex + 1) < 6 ? elementIndex + 1 : 0;
    		energySource = supportedAspects.get(elementIndex);
    	}
    	
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getTimer()%10==0) {
    		if (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU())) {
	    		try {
		    		if (mInventory[0] != null && mInventory[1] == null) {
		    			if (mInventory[0].isItemEnchanted() && mInventory[0].getItem().getItemEnchantability() > 0) {
			                NBTTagList tEnchantments = mInventory[0].getEnchantmentTagList();
			                if (tEnchantments != null) {
			                    for (int i = 0; i < tEnchantments.tagCount(); i++) {
			                        short tID = ((NBTTagCompound)tEnchantments.getCompoundTagAt(i)).getShort("id");
			                        short tLevel = ((NBTTagCompound)tEnchantments.getCompoundTagAt(i)).getShort("lvl");
			                        if (tID > -1 && tID < Enchantment.enchantmentsList.length) {
				                        Enchantment tEnchantment = Enchantment.enchantmentsList[tID];
				                        if (tEnchantment != null) {
				                        	getBaseMetaTileEntity().increaseStoredEnergyUnits((1000000*tLevel)/(tEnchantment.getMaxLevel()*tEnchantment.getWeight()), true);
				                        }
			                        }
			                    }
			                    mInventory[0].stackTagCompound.removeTag("ench");
			                }
			    		} else if (mInventory[0].getItem() instanceof ItemEnchantedBook) {
		    				NBTTagList tEnchantments = ((ItemEnchantedBook)mInventory[0].getItem()).func_92110_g(mInventory[0]);
			                if (tEnchantments != null) {
			                    for (int i = 0; i < tEnchantments.tagCount(); i++) {
			                        short tID = ((NBTTagCompound)tEnchantments.getCompoundTagAt(i)).getShort("id");
			                        short tLevel = ((NBTTagCompound)tEnchantments.getCompoundTagAt(i)).getShort("lvl");
			                        if (tID > -1 && tID < Enchantment.enchantmentsList.length) {
				                        Enchantment tEnchantment = Enchantment.enchantmentsList[tID];
				                        if (tEnchantment != null) {
				                        	getBaseMetaTileEntity().increaseStoredEnergyUnits((1000000*tLevel)/(tEnchantment.getMaxLevel()*tEnchantment.getWeight()), true);
				                        }
			                        }
			                    }
			    				mInventory[0] = new ItemStack(Items.book, 1);
			                }
		    			}
		    			
		            	mInventory[1] = mInventory[0];
		            	mInventory[0] = null;
		            }
    			} catch(Throwable e) {
    				if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
    			}
    		}
    		
    		if (sEnergyPerEnderCrystal > 0 && isActive1) {
    			if (mTargetedCrystal == null) {
	    			ArrayList<EntityEnderCrystal> tList = (ArrayList<EntityEnderCrystal>)getBaseMetaTileEntity().getWorld().getEntitiesWithinAABB(EntityEnderCrystal.class, AxisAlignedBB.getBoundingBox(getBaseMetaTileEntity().getXCoord()-64, getBaseMetaTileEntity().getYCoord()-64, getBaseMetaTileEntity().getZCoord()-64, getBaseMetaTileEntity().getXCoord()+64, getBaseMetaTileEntity().getYCoord()+64, getBaseMetaTileEntity().getZCoord()+64));
	    			if (tList != null && !tList.isEmpty()) {
	    				tList.removeAll(sUsedDragonCrystalList);
	    				if (tList.size() > 0) {
	    					mTargetedCrystal = tList.get(0);
	    					if (mTargetedCrystal != null) {
		    					sUsedDragonCrystalList.add(mTargetedCrystal);
	    					}
	    				}
	    			}
    			} else {
    				if (mTargetedCrystal.isEntityAlive()) {
    					getBaseMetaTileEntity().increaseStoredEnergyUnits(sEnergyPerEnderCrystal*10, false);
    				} else {
    					sUsedDragonCrystalList.remove(mTargetedCrystal);
    					mTargetedCrystal = null;
    				}
    			}
    		}
    		
    		if (sEnergyFromVis > 0 && isActive2 && getBaseMetaTileEntity().getUniversalEnergyStored() < sEnergyFromVis && THAUMCRAFT_LOADED) {
    			try {
    				
    				if (energySource != null && thaumcraft.common.lib.events.EssentiaHandler.findEssentia((TileEntity) this.getBaseMetaTileEntity(), (thaumcraft.api.aspects.Aspect)energySource, ForgeDirection.UNKNOWN, 20)) {
    					if (thaumcraft.api.aspects.AspectSourceHelper.drainEssentia((TileEntity) this.getBaseMetaTileEntity(), (thaumcraft.api.aspects.Aspect)energySource, ForgeDirection.UNKNOWN, 20)) {
        					getBaseMetaTileEntity().increaseStoredEnergyUnits(sEnergyFromVis, true);
        					List<?> tList = getBaseMetaTileEntity().getWorld().getEntitiesWithinAABB(thaumcraft.common.entities.monster.EntityWisp.class, AxisAlignedBB.getBoundingBox(getBaseMetaTileEntity().getXCoord()-8, getBaseMetaTileEntity().getYCoord()-8, getBaseMetaTileEntity().getZCoord()-8, getBaseMetaTileEntity().getXCoord()+8, getBaseMetaTileEntity().getYCoord()+8, getBaseMetaTileEntity().getZCoord()+8));
        					if (!tList.isEmpty()) getBaseMetaTileEntity().doExplosion(8192);
        				}
    				} else energySource = null;
    			} catch(Throwable e) {
    				if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
    			}
    		}
    		
			getBaseMetaTileEntity().setActive(getBaseMetaTileEntity().getUniversalEnergyStored() >= getBaseMetaTileEntity().getOutputVoltage() + getMinimumStoredEU());
    	}
    }
    
    @Override
	public void onExplosion() {
    	if (sEnergyFromVis > 0 && isActive2) {
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
					if (bg.biomeID != thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeTaint.biomeID) {
						thaumcraft.common.lib.utils.Utils.setBiomeAt(tWorld, x, z, thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeTaint);
					}
					if (tWorld.rand.nextBoolean()) {
						x = te.xCoord + tWorld.rand.nextInt(10) - tWorld.rand.nextInt(10);
						z = te.zCoord + tWorld.rand.nextInt(10) - tWorld.rand.nextInt(10);
						y = te.yCoord + tWorld.rand.nextInt(5) - tWorld.rand.nextInt(5);
						if (thaumcraft.common.blocks.BlockTaintFibres.spreadFibres(tWorld, x, y, z)) {
						}
					}
				}
			} catch(Throwable e) {
				if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
			}
    	}
    }
    
    @Override
    public void inValidate() {
    	if (mTargetedCrystal != null) sUsedDragonCrystalList.remove(mTargetedCrystal);
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	return aActive?91:92;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_MagicAbsorber.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==0;
	}
}
