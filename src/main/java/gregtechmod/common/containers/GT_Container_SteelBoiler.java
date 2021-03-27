package gregtechmod.common.containers;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.steam.GT_MetaTileEntity_Boiler_Steel;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.InventoryPlayer;

public class GT_Container_SteelBoiler extends GT_Container_BronzeBoiler {

	public GT_Container_SteelBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
    
    @Override
    protected void write(JsonObject data, boolean force) {
    	GT_MetaTileEntity_Boiler_Steel m = (GT_MetaTileEntity_Boiler_Steel) mTileEntity.getMetaTileEntity();
    	
    	mTemperature.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, (m.mTemperature * 54) / 990)));
    	mProcessingEnergy.updateAndWriteChanges(data, force, Math.min(14, Math.max(m.mProcessingEnergy>0?1:0, (m.mProcessingEnergy * 14) / 640)));
    	mSteamAmount.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, ((m.mSteam == null ? 0 : m.mSteam.amount) * 54) / 31900)));
    	mWaterAmount.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, ((m.mFluid[0] == null ? 0 : m.mFluid[0].amount) * 54) / 15900)));
    }
}
