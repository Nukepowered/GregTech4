package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaMachine_Item extends ItemBlock {
	public String[] mString0 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString1 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString2 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString3 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString4 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString5 = new String[GregTech_API.MAXIMUM_METATILE_IDS],
					mString6 = new String[GregTech_API.MAXIMUM_METATILE_IDS];
	
    public GT_MetaMachine_Item(Block bl) {
        super(bl);
        setMaxDamage(0);
        setHasSubtypes(true);
        setUnlocalizedName(GT_LanguageManager.mNameList1[0]);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
		try {
			int tDamage = aStack.getItemDamage();
			
			if (tDamage < 0 || tDamage >= GregTech_API.MAXIMUM_METATILE_IDS) return;
			
			if (mString1[tDamage] == null) {
				mString0[tDamage] = "";
				mString1[tDamage] = "";
				mString2[tDamage] = "";
				mString3[tDamage] = "";
				mString4[tDamage] = "";
				mString5[tDamage] = "";
				mString6[tDamage] = "";
				if (tDamage != 0) {
					TileEntity temp = GregTech_API.sBlockList[1].createTileEntity(aPlayer.worldObj, tDamage > 15 ? GregTech_API.mMetaTileList[tDamage] == null ? 0 : GregTech_API.mMetaTileList[tDamage].getTileEntityBaseType() : tDamage);
					if (temp != null) {
						temp.setWorldObj(aPlayer.worldObj); temp.xCoord = 0; temp.yCoord = 0; temp.zCoord = 0;
						if (temp instanceof IGregTechTileEntity) {
							IGregTechTileEntity tTileEntity = (IGregTechTileEntity)temp;
							tTileEntity.setInitialValuesAsNBT(new NBTTagCompound(), (short)tDamage);
							if (tTileEntity.getDescription()    != null) mString0[tDamage] = I18n.format(tTileEntity.getDescription());
							if (tTileEntity.getInputVoltage()		> 0) mString1[tDamage] = I18n.format("metatileentity.TileEntity_EUp_IN.tooltip",tTileEntity.getInputVoltage()); else mString1[tDamage] = "";
							if (tTileEntity.getMetaTileEntity() == null || tTileEntity.getMetaTileEntity().getSpecialVoltageToolTip() == null) {
								if (tTileEntity.getOutputVoltage()	> 0) mString2[tDamage] = I18n.format("metatileentity.TileEntity_EUp_OUT.tooltip", tTileEntity.getOutputVoltage()); else mString2[tDamage] = "";
							} else {
								mString2[tDamage] = I18n.format(tTileEntity.getMetaTileEntity().getSpecialVoltageToolTip());
							}
							if (tTileEntity.getOutputAmperage()		> 1) mString3[tDamage] = I18n.format("metatileentity.TileEntity_EUp_AMOUNT.tooltip",tTileEntity.getOutputAmperage()); else mString3[tDamage] = "";
							if (tTileEntity.getEUCapacity()		> 10000) mString4[tDamage] = I18n.format("metatileentity.TileEntity_EUp_STORE.tooltip",tTileEntity.getEUCapacity()); else mString4[tDamage] = "";
							mString5[tDamage] = (tTileEntity.isOverclockerUpgradable()?"O ":"") + (tTileEntity.isTransformerUpgradable()?"T ":"") + (tTileEntity.isBatteryUpgradable(0, (byte)0)?"B ":"") + (tTileEntity.isRFConverterUpgradable()?"M ":"") + (tTileEntity.isSteamEngineUpgradable()?"S ":"");
							if (!mString5[tDamage].equals(""))
								mString5[tDamage] = I18n.format("metatileentity.TileEntity_UPGRADES.tooltip") + " " + (tTileEntity.isOverclockerUpgradable()?"O ":"") + (tTileEntity.isTransformerUpgradable()?"T ":"") + (tTileEntity.isBatteryUpgradable(0, (byte)0)?"B ":"") + (tTileEntity.isRFConverterUpgradable()?"M ":"") + (tTileEntity.isSteamEngineUpgradable()?"S ":"");
							mString6[tDamage] = "";
						}
					}
				}
			}

			if (!mString0[tDamage].equals("")) aList.add(mString0[tDamage]);
			if (!mString1[tDamage].equals("")) aList.add(mString1[tDamage]);
			if (!mString2[tDamage].equals("")) aList.add(mString2[tDamage]);
			if (!mString3[tDamage].equals("")) aList.add(mString3[tDamage]);
			if (!mString4[tDamage].equals("")) aList.add(mString4[tDamage]);
			if (!mString5[tDamage].equals("")) aList.add(mString5[tDamage]);
			if (!mString6[tDamage].equals("")) aList.add(mString6[tDamage]);
			
			NBTTagCompound aNBT = aStack.getTagCompound();
			
			if (aNBT != null) {
				if (aNBT.getBoolean("mMJConverter")) aList.add(I18n.format("metatileentity.MJCONVERTER.tooltip"));
				if (aNBT.getBoolean("mSteamConverter")) aList.add(I18n.format("metatileentity.STEAMCONVERTER.tooltip"));
//				if (aNBT.getBoolean("mMJConverter")) aList.add(GT_LanguageManager.addStringLocalization("TileEntity_MJCONVERTER", "has MJ-Converter"));
//				if (aNBT.getBoolean("mSteamConverter")) aList.add(GT_LanguageManager.addStringLocalization("TileEntity_STEAMCONVERTER", "has Steam Upgrade"));
				int tAmount = 0;
				if ((tAmount = aNBT.getByte("mOverclockers")) 		> 0)  aList.add(I18n.format("metatileentity.OVERCLOCKERS.tooltip",tAmount));
				if ((tAmount = aNBT.getByte("mTransformers")) 		> 0)  aList.add(I18n.format("metatileentity.TRANSFORMERS.tooltip",tAmount));
				if ((tAmount = aNBT.getByte("mRSEnergyCells"))		> 0)  aList.add(I18n.format("metatileentity.ENERGYCELLS.tooltip",tAmount));
				if ((tAmount = aNBT.getByte("mSteamTanks"))			> 0)  aList.add(I18n.format("metatileentity.STEAMTANKS.tooltip",tAmount));
				if ((tAmount = aNBT.getInteger("mUpgradedStorage"))	> 0)  aList.add(I18n.format("metatileentity.EUSTORAGES.tooltip",tAmount));
//				if ((tAmount = aNBT.getByte("mOverclockers")) 		> 0) aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("TileEntity_OVERCLOCKERS", "Overclocker Upgrades"));
//				if ((tAmount = aNBT.getByte("mTransformers")) 		> 0) aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("TileEntity_TRANSFORMERS", "Transformer Upgrades"));
//				if ((tAmount = aNBT.getByte("mRSEnergyCells"))		> 0) aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("TileEntity_ENERGYCELLS", "Energy Cell Upgrades"));
//				if ((tAmount = aNBT.getByte("mSteamTanks"))			> 0) aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("TileEntity_STEAMTANKS", "Steam Tank Upgrades"));
//				if ((tAmount = aNBT.getInteger("mUpgradedStorage"))	> 0) aList.add(tAmount + " " + GT_LanguageManager.addStringLocalization("TileEntity_EUSTORAGES", "Additional EU-Storage"));
			}
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister aIconRegister) {}
    
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return false;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack aStack) {
    	short tDamage = (short)aStack.getItemDamage();
		if (tDamage < 0 || tDamage >= GregTech_API.MAXIMUM_METATILE_IDS) return "";
    	if (tDamage < 16) return getUnlocalizedName() + "." + GT_LanguageManager.mNameList1[tDamage];
    	
    	if (GregTech_API.mMetaTileList[tDamage] != null) {
    		return "metatileentity." + GregTech_API.mMetaTileList[tDamage].getMetaName();
    	}
    	
    	return "null";
    }
    
    @Override
    public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int side, float hitX, float hitY, float hitZ, int aMeta) {
		short tDamage = (short)aStack.getItemDamage();
		if (aStack.getItemDamage()>15) {
    	   if (GregTech_API.mMetaTileList[tDamage] == null) {
    		   return false;
    	   } else {
	    	   if (!aWorld.setBlock(aX, aY, aZ, this.field_150939_a, GregTech_API.mMetaTileList[tDamage].getTileEntityBaseType(), 3)) {
	    		   return false;
	    	   }
	    	   IGregTechTileEntity tTileEntity = (IGregTechTileEntity)aWorld.getTileEntity(aX, aY, aZ);
	    	   if (tTileEntity != null) {
	    		   if (aStack.getTagCompound() != null && tTileEntity.isServerSide()) {
	        		   tTileEntity.setInitialValuesAsNBT(aStack.getTagCompound(), tDamage);
	    		   } else {
	        		   tTileEntity.setInitialValuesAsNBT(null, tDamage);
	    		   }
	    		   if (aPlayer != null) tTileEntity.setOwnerName(aPlayer.getDisplayName());
	    	   }
    	   }
       } else {
    	   if (!aWorld.setBlock(aX, aY, aZ, this.field_150939_a, tDamage, 3)) {
               return false;
           }
       }
       
       if (aWorld.getBlock(aX, aY, aZ) == this.field_150939_a) {
           this.field_150939_a.onBlockPlacedBy(aWorld, aX, aY, aZ, aPlayer, aStack);
           this.field_150939_a.onPostBlockPlaced(aWorld, aX, aY, aZ, tDamage);
       }
       return true;
    }
}
