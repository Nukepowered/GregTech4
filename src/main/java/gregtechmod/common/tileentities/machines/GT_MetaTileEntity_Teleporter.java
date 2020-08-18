package gregtechmod.common.tileentities.machines;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.DimensionManager;

public class GT_MetaTileEntity_Teleporter extends MetaTileEntity {
	
	public int mTargetX = 0, mTargetY = 0, mTargetZ = 0, mTargetD = 0, mCharge = 0;
	public boolean mDebug = false, hasEgg = false;
	public static boolean sInterDimensionalTeleportAllowed = true;
	
	public GT_MetaTileEntity_Teleporter(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Teleporter() {
		
	}
	
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetInput()							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
	@Override public int maxEUInput()								{return GregTech_API.VOLTAGE_ULTIMATE;}
    @Override public int maxEUStore()								{return 10000000;}
    @Override public int maxRFStore()								{return 10000000;}
    @Override public int maxSteamStore()							{return 10000000;}
	@Override public boolean isValidSlot(int aIndex)				{return false;}
	@Override public int getInvSize()								{return 1;}
	
	@Override public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		if (GT_Utility.isDebugItem(aPlayer.getCurrentEquippedItem())) {
			mDebug = true;
		} else if (aSide != getBaseMetaTileEntity().getFrontFacing()) {
			getBaseMetaTileEntity().openGUI(aPlayer, 152);
		}
		return true;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Teleporter();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setInteger("mTargetX", mTargetX);
		aNBT.setInteger("mTargetY", mTargetY);
		aNBT.setInteger("mTargetZ", mTargetZ);
		aNBT.setInteger("mTargetD", mTargetD);
		aNBT.setInteger("mCharge" , mCharge);
		aNBT.setBoolean("mDebug"  , mDebug);
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		mTargetX = aNBT.getInteger("mTargetX");
		mTargetY = aNBT.getInteger("mTargetY");
		mTargetZ = aNBT.getInteger("mTargetZ");
		mTargetD = aNBT.getInteger("mTargetD");
		mCharge  = aNBT.getInteger("mCharge");
		mDebug   = aNBT.getBoolean("mDebug");
	}
	
	@Override
	public void onConfigLoad(GT_Config aConfig) {
		sInterDimensionalTeleportAllowed = aConfig.get(GT_ConfigCategories.machineconfig, "Teleporter.Interdimensional", true);
	}
	
	@Override
	public void onFirstTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
			if (mTargetX == 0 && mTargetY == 0 && mTargetZ == 0 && mTargetD == 0) {
				mTargetX = getBaseMetaTileEntity().getXCoord();
				mTargetY = getBaseMetaTileEntity().getYCoord();
				mTargetZ = getBaseMetaTileEntity().getZCoord();
				mTargetD = getBaseMetaTileEntity().getWorld().provider.dimensionId;
			}
			hasEgg = checkForEgg();
		}
	}
	
	public boolean checkForEgg() {
		for (byte i = -5; i <= 5; i++) for (byte j = -5; j <= 5; j++) for (byte k = -5; k <= 5; k++) if (getBaseMetaTileEntity().getBlockOffset(i, j, k) == Blocks.dragon_egg) return true;
		return false;
	}
	
	public boolean hasDimensionalTeleportCapability() {
		return mDebug || hasEgg;
	}
	
	public boolean isDimensionalTeleportAvailable() {
		return mDebug || (hasDimensionalTeleportCapability() && GT_Utility.isRealDimension(mTargetD) && GT_Utility.isRealDimension(getBaseMetaTileEntity().getWorld().provider.dimensionId));
	}
	
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
			if (getBaseMetaTileEntity().getTimer() % 100 == 50) {
				hasEgg = checkForEgg();
			}
			
			if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getRedstone()) {
				mCharge -= GregTech_API.VOLTAGE_INSANE;
				if (mCharge < 0) mCharge = 0;
				
				int tDistance = distanceCalculation(), tCost = 0;
				
				for (Object tObject : getBaseMetaTileEntity().getWorld().getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 2)-1, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 2)-1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 2)-1, getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 2)+2, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 2)+2, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 2)+2))) {
					if (tObject instanceof Entity && !((Entity)tObject).isDead) {
						Entity tEntity = (Entity)tObject;
						
						if ((mCharge >= (tCost = (int)(tDistance * tDistance * weightCalculation(tEntity))) && tCost >= 0) || mDebug) {
							if (!mDebug) mCharge -= tCost;
							
							if (tEntity.ridingEntity != null) tEntity.mountEntity(null);
							if (tEntity.riddenByEntity != null) tEntity.riddenByEntity.mountEntity(null);
							
							if (mTargetD != getBaseMetaTileEntity().getWorld().provider.dimensionId && isDimensionalTeleportAvailable() && GT_Utility.moveEntityToDimensionAtCoords(tEntity, mTargetD, mTargetX+0.5, mTargetY+0.5, mTargetZ+0.5)) {
								
							} else {
								if (tEntity instanceof EntityLiving) {
									((EntityLiving)tEntity).setPositionAndUpdate(mTargetX+0.5, mTargetY+0.5, mTargetZ+0.5);
								} else {
									if (tEntity instanceof EntityPlayer) {
										((EntityPlayerMP) tEntity).playerNetServerHandler.setPlayerLocation(mTargetX+0.5, mTargetY+0.5, mTargetZ+0.5, tEntity.rotationYaw, tEntity.rotationPitch);
									} else {
										tEntity.setPosition(mTargetX+0.5, mTargetY+0.5, mTargetZ+0.5);
									}
								}
							}
							
							GT_Log.log.warn(String.format("Teleported %s from (%s %s %s, DIM: %s) to (%s %s %s, DIM: %s)", 
									tEntity.toString(), 
									getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getYCoord(), getBaseMetaTileEntity().getZCoord(), getBaseMetaTileEntity().getWorld().provider.getDimensionName(),
									mTargetX, mTargetY, mTargetZ, DimensionManager.createProviderFor(mTargetD).getDimensionName()));
						} else {
							int tCharge = getBaseMetaTileEntity().getUniversalEnergyStored();
							if (tCharge > 0 && mCharge + tCharge > 0) {
								mCharge += tCharge;
								getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCharge, true);
							}
						}
					}
				}
				
				getBaseMetaTileEntity().setActive(true);
			} else {
				getBaseMetaTileEntity().setActive(false);
			}
		}
	}
	
	private int distanceCalculation() {
		return Math.abs((mTargetD != getBaseMetaTileEntity().getWorld().provider.dimensionId && isDimensionalTeleportAvailable() ? 100 : 1) * (int)Math.sqrt(Math.pow(getBaseMetaTileEntity().getXCoord() - mTargetX, 2) + Math.pow(getBaseMetaTileEntity().getYCoord() - mTargetY, 2) + Math.pow(getBaseMetaTileEntity().getZCoord() - mTargetZ, 2)));
	}
	
	private float weightCalculation(Entity aEntity) {
		try {
			if (aEntity instanceof net.minecraft.client.particle.EntityFX) return -1;
		} catch (Throwable e) {}
		if (aEntity instanceof EntityFishHook) return -1;
		if (aEntity instanceof EntityDragonPart) return -1;
		if (aEntity instanceof EntityWeatherEffect) return -1;
		
		if (aEntity instanceof EntityPlayer) {
			EntityPlayer tPlayer = (EntityPlayer)aEntity;
        	int tCount = 64;
    		for (int i = 0; i < 36; i++) if (tPlayer.inventory.getStackInSlot(i) != null) tCount+=(tPlayer.inventory.getStackInSlot(i).getMaxStackSize()>1?tPlayer.inventory.getStackInSlot(i).stackSize:64);
        	for (int i = 0; i <  4; i++) if (tPlayer.inventory.armorInventory[i] != null) tCount+=256;
        	return tCount / 666.6F;
		}
		
		if (GT_Utility.getClassName(aEntity).equals("EntityItnt")) return 5.0F;
		if (GT_Utility.getClassName(aEntity).equals("EntityNuke")) return 50.0F;
		
		if (aEntity instanceof EntityArrow) return 0.001F;
		if (aEntity instanceof EntityBoat) return 0.1F;
		if (aEntity instanceof EntityEnderCrystal) return 2.0F;
		if (aEntity instanceof EntityEnderEye) return 0.001F;
		if (aEntity instanceof EntityFallingBlock) return 0.01F;
		if (aEntity instanceof EntityFireball) return 0.001F;
		if (aEntity instanceof EntityFireworkRocket) return 0.001F;
		if (aEntity instanceof EntityHanging) return 0.005F;
		if (aEntity instanceof EntityItem) return 0.001F;
		if (aEntity instanceof EntityLiving) return 0.5F;
		if (aEntity instanceof EntityMinecart) return 0.1F;
		if (aEntity instanceof EntityThrowable) return 0.001F;
		if (aEntity instanceof EntityTNTPrimed) return 5.0F;
		if (aEntity instanceof EntityXPOrb) return 0.001F;
		
		return -1;
	}
	
	@Override
	public String[] getInfoData() {
		return new String[] { "Charge: " + mCharge, "X: " + mTargetX + "  Y: " + mTargetY + "  Z: " + mTargetZ, "Dimension: " + mTargetD};
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return aActive?271:270;
		return 63;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Teleporter.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
}