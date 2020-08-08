package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
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
		try { // TODO localization
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
				if (tDamage == 0) {
					
				} else {
					TileEntity temp = GregTech_API.sBlockList[1].createTileEntity(aPlayer.worldObj, tDamage > 15 ? GregTech_API.mMetaTileList[tDamage] == null ? 0 : GregTech_API.mMetaTileList[tDamage].getTileEntityBaseType() : tDamage);
					if (temp != null) {
						temp.setWorldObj(aPlayer.worldObj); temp.xCoord = 0; temp.yCoord = 0; temp.zCoord = 0;
						if (temp instanceof IGregTechTileEntity) {
							IGregTechTileEntity tTileEntity = (IGregTechTileEntity)temp;
							tTileEntity.setInitialValuesAsNBT(new NBTTagCompound(), (short)tDamage);
							if (tTileEntity.getDescription()    != null) mString0[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_DESCRIPTION_" + tDamage, tTileEntity.getDescription());
							if (tTileEntity.getInputVoltage()		> 0) mString1[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_EUp_IN", "Max EU/p IN: ") + tTileEntity.getInputVoltage();					else mString1[tDamage] = "";
							if (tTileEntity.getMetaTileEntity() == null || tTileEntity.getMetaTileEntity().getSpecialVoltageToolTip() == null) {
								if (tTileEntity.getOutputVoltage()	> 0) mString2[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_EUp_OUT", "Max EU/p OUT: ") + tTileEntity.getOutputVoltage();					else mString2[tDamage] = "";
							} else {
								mString2[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_VoltageToolTip_" + tDamage, tTileEntity.getMetaTileEntity().getSpecialVoltageToolTip());
							}
							if (tTileEntity.getOutputAmperage()		> 1) mString3[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_EUp_AMOUNT", "Amount of Output Packets: ") + tTileEntity.getOutputAmperage();	else mString3[tDamage] = "";
							if (tTileEntity.getEUCapacity()		> 10000) mString4[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_EUp_STORE", "EU Storage: ") + tTileEntity.getEUCapacity();					else mString4[tDamage] = "";
							mString5[tDamage] = (tTileEntity.isOverclockerUpgradable()?"O ":"") + (tTileEntity.isTransformerUpgradable()?"T ":"") + (tTileEntity.isBatteryUpgradable(0, (byte)0)?"B ":"") + (tTileEntity.isMJConverterUpgradable()?"M ":"") + (tTileEntity.isSteamEngineUpgradable()?"S ":"");
							if (!mString5[tDamage].equals("")) mString5[tDamage] = "LOCALE";//GT_LanguageManager.addStringLocalization("TileEntity_UPGRADES", "Possible Upgrades: ") + mString5[tDamage];
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
//				if (aNBT.getBoolean("mMJConverter")) aList.add(GT_LanguageManager.addStringLocalization("TileEntity_MJCONVERTER", "has MJ-Converter"));
//				if (aNBT.getBoolean("mSteamConverter")) aList.add(GT_LanguageManager.addStringLocalization("TileEntity_STEAMCONVERTER", "has Steam Upgrade"));
//				int tAmount = 0;
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
    public void registerIcons(IIconRegister aIconRegister) {
		GT_Log.log.info("GT_Mod: Setting up Icon Register for Items");
    	GregTech_API.sItemIcons = aIconRegister;
    	
    	GT_Log.log.info("GT_Mod: Starting Item Icon Load Phase Clientside");
    	for (Runnable tRunnable : GregTech_API.sGTItemIconload) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    }
    
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
