package gregtechmod.common.tileentities.machines.multi;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.metatileentity.implementations.MetaTileEntityMultiblock;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public class GT_MetaTileEntity_Multi_SteamTurbine extends MetaTileEntityMultiblock {
	
	public static final int TURBINE_OUTPUT_EU = 800;
	public static final int MAX_EFFICIENCY = 100_00;
	
	protected int mEfficiencyIncrease = 0;
	
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 157, GregTech_API.gregtechmod);}
	@Override public boolean isGivingInformation() 					{return true;}
	@Override public void updateEfficiency() 						{}
	
	public GT_MetaTileEntity_Multi_SteamTurbine(int aID, String mName) {
		super(aID, mName);
		mEfficiency = MAX_EFFICIENCY;
	}
	
	public GT_MetaTileEntity_Multi_SteamTurbine() {
		mEfficiency = MAX_EFFICIENCY;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Multi_SteamTurbine();
	}
	
	@Override
	public boolean isCorrectMachinePart(ItemStack aStack) {
		return this.getMaxEfficiency(aStack) > 0;
	}
	
	@Override
	public int getDamageToComponent(ItemStack aStack) {
		return GT_Utility.areStacksEqual(GT_ModHandler.getRCItem("part.turbine.rotor", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), aStack)?2:1;
	}
	
	@Override
	public void onPostTick() {
		super.onPostTick();
		if (getBaseMetaTileEntity().isServerSide()) {
			if (this.checkRecipe(mInventory[1]) && getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getAirAtSide(getBaseMetaTileEntity().getFrontFacing())) {
				mEfficiency = Math.max(0, Math.min(mEfficiency + mEfficiencyIncrease, getMaxEfficiency(mInventory[1]) - ((getIdealStatus() - getRepairStatus()) * 1000)));
				this.addEnergyOutput((int)(TURBINE_OUTPUT_EU * mEfficiency * 1.0D / MAX_EFFICIENCY));
				if (!getBaseMetaTileEntity().isActive()) getBaseMetaTileEntity().setActive(true);
				if (!doRandomMaintenanceDamage() || !polluteEnvironment(getPollutionPerTick(mInventory[1]))) {
					stopMachine(); 
				}
			} else {
				mEfficiency = 0;
				mEfficiencyIncrease = 0;
				stopMachine();
			}
		}
	}
	
	public boolean checkRecipe(ItemStack aStack) {
		if (depleteInput(GT_ModHandler.getSteam(TURBINE_OUTPUT_EU * 2)) || depleteInput(GT_ModHandler.getIC2Steam(TURBINE_OUTPUT_EU * 2))) {
			if (GT_Items.Component_Turbine_Bronze.isStackEqual(aStack, true, true)) {
				this.mEfficiencyIncrease = 10;
			} else if (GT_Items.Component_Turbine_Steel.isStackEqual(aStack, true, true)) {
				this.mEfficiencyIncrease = 20;
			} else if (GT_Items.Component_Turbine_Magnalium.isStackEqual(aStack, true, true)) {
				this.mEfficiencyIncrease = 50;
			} else if (GT_Items.Component_Turbine_TungstenSteel.isStackEqual(aStack, true, true)) {
				this.mEfficiencyIncrease = 15;
			} else if (GT_Items.Component_Turbine_Carbon.isStackEqual(aStack, true, true)) {
				this.mEfficiencyIncrease = 100;
			} else {
				this.mEfficiencyIncrease = 20;
			}
			addOutput(GT_ModHandler.getWater(10));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkMachine(ItemStack aStack) {
		byte tSide = getBaseMetaTileEntity().getBackFacing();
		if (getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 1) && getBaseMetaTileEntity().getAirAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 2)) {
			TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getBackFacing(), 3);
			if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Hatch_Dynamo) {
				mDynamoHatches.add((GT_MetaTileEntity_Hatch_Dynamo)((IGregTechTileEntity)tTileEntity).getMetaTileEntity());
			} else {
				return false;
			}
			
			int tAirCount = 0;
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) for (byte k = -1; k < 2; k++) {
				if (getBaseMetaTileEntity().getAirOffset(i, j, k)) tAirCount++;
			}
			if (tAirCount != 10) return false;
			for (byte i = 2; i < 6; i++) {
				if (null != (tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(i, 2)) && tTileEntity instanceof IGregTechTileEntity) {
					if (((IGregTechTileEntity)tTileEntity).getFrontFacing() == getBaseMetaTileEntity().getFrontFacing() && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
						if (((IGregTechTileEntity)tTileEntity).getMetaTileEntity() instanceof GT_MetaTileEntity_Multi_SteamTurbine) return false;
					}
				}
			}
			
			int tX = getBaseMetaTileEntity().getXCoord(), tY = getBaseMetaTileEntity().getYCoord(), tZ = getBaseMetaTileEntity().getZCoord();
			for (byte i = -1; i < 2; i++) for (byte j = -1; j < 2; j++) if (i != 0 || j != 0) for (byte k = 0; k < 4; k++) {
				if ((i == 0 || j == 0) && (k == 1 || k == 2)) {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != 13) {
						tTileEntity = getBaseMetaTileEntity().getTileEntity(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i));
						if (tTileEntity != null && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getMetaTileEntity() != null) {
							IMetaTileEntity mte = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity();
							if (mte instanceof GT_MetaTileEntity_Hatch_Maintenance) {
								maintenanceHatch = new WeakReference<>((GT_MetaTileEntity_Hatch_Maintenance)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Input) {
								mInputHatches.add((GT_MetaTileEntity_Hatch_Input)mte);
							} else if (mte instanceof GT_MetaTileEntity_Hatch_Output) {
								mOutputHatches.add((GT_MetaTileEntity_Hatch_Output)mte);
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				} else {
					if (getBaseMetaTileEntity().getBlock(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != GregTech_API.sBlockList[0] || getBaseMetaTileEntity().getMetaID(tX+(tSide<4?i:tSide==5?+k:-k), tY+j, tZ+(tSide<4?tSide==3?+k:-k:i)) != 13) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean explodesOnComponentBreak(ItemStack aStack) {
		return true;
	}
	
	@Override
	public int getMaxEfficiency(ItemStack aStack) {
		if (GT_Items.Component_Turbine_Bronze.isStackEqual(aStack, true, true)) return  60_00;
		if (GT_Items.Component_Turbine_Steel.isStackEqual(aStack, true, true)) return  80_00;
		if (GT_Items.Component_Turbine_Magnalium.isStackEqual(aStack, true, true)) return 100_00;
		if (GT_Items.Component_Turbine_TungstenSteel.isStackEqual(aStack, true, true)) return  90_00;
		if (GT_Items.Component_Turbine_Carbon.isStackEqual(aStack, true, true)) return 125_00;
		if (GT_Utility.areStacksEqual(aStack, GT_ModHandler.getRCItem("part.turbine.rotor", 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) return 80_00;
		return 0;
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			if (aActive) return GT_BlockMetaID_Block.mIconSteamTurbineActive[4];
			return GT_BlockMetaID_Block.mIconSteamTurbine[4];
		}
		return null;
	}
	
	@Override
	public int getPollutionPerTick(ItemStack aStack) {
		return 0;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		InfoBuilder b =  InfoBuilder.create()
				.newKey("metatileentity.turbine.speed", GT_Utility.parseNumberToString(mEfficiency * 100.0D / MAX_EFFICIENCY))
				.newKey("metatileentity.multiblock.dynamo_output", (int)(TURBINE_OUTPUT_EU * mEfficiency * 1.0D / MAX_EFFICIENCY))
				.newKey("metatileentity.multiblock.malfunction_amount", getIdealStatus() - getRepairStatus());
		if (isCorrectMachinePart(mInventory[1])) {
			b.newKey("metatileentity.turbine.durability", GT_Utility.parseNumberToString(mInventory[1].getMaxDamage() - mInventory[1].getItemDamage()));
		} else {
			b.newKey("metatileentity.turbine.no_turbine");
		}
		return b.build();
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Multi_SteamTurbine.tooltip";
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		aNBT.setInteger("mEfficiencyIncrease", mEfficiencyIncrease);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mEfficiencyIncrease = aNBT.getInteger("mEfficiencyIncrease");
	}
}
