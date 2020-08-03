package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.FakePlayer;

public class GT_MetaTileEntity_Microwave extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Microwave(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Microwave() {
		
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 148);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Microwave();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
		if (mInventory[2] != null) {
			ItemStack tOutput = GT_ModHandler.getSmeltingOutput(mInventory[2], false, mInventory[3]);
			if (mInventory[2].getItem() == GregTech_API.sItemList[93]
			 || GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE))
			 || GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("constructionFoamPellet", 1))
			 || GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("constructionFoam", 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
	    		mInventory[2] = null;
	    		getBaseMetaTileEntity().doExplosion(128);
				try {
					for (byte i = 0; i < 6; i++) {
						GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, new ItemStack(Block.sponge, 1)).getItem().onItemUse(GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, new ItemStack(Block.sponge, 1)), new FakePlayer(getBaseMetaTileEntity().getWorld(), "Foo"), getBaseMetaTileEntity().getWorld(), getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getYCoord(), getBaseMetaTileEntity().getZCoord(), i, 0, 0, 0);
					}
				} catch(Throwable e) {}
				return;
			}
			
			if ((OrePrefixes.ingot.contains(mInventory[2]) && !Materials.Meat.contains(mInventory[2]) && !Materials.MeatCooked.contains(mInventory[2]) && !Materials.MeatRaw.contains(mInventory[2]))
			||  (OrePrefixes.ingot.contains(tOutput) && !Materials.Meat.contains(tOutput) && !Materials.MeatCooked.contains(tOutput) && !Materials.MeatRaw.contains(tOutput))
			||  Materials.Netherrack.contains(mInventory[2])
			||  Materials.Gunpowder.contains(mInventory[2])
			||  Materials.Gunpowder.contains(tOutput)
			||  mInventory[2].isItemEqual(new ItemStack(Block.netherrack))
			||  mInventory[2].getItem() == Item.egg) {
	    		mInventory[2] = null;
				getBaseMetaTileEntity().doExplosion(128);
				return;
			}
			if (TileEntityFurnace.getItemBurnTime(mInventory[2]) > 0 || TileEntityFurnace.getItemBurnTime(tOutput) > 0) {
	    		mInventory[2] = null;
				getBaseMetaTileEntity().setOnFire();
				return;
			}
			if (tOutput != null) {
				mOutputItem1 = tOutput;
				mEUt = 4;
	    		mMaxProgresstime = 25;
	    		mInventory[2].stackSize--;
			}
		}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 1;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 5;
	}
	
	@Override
	public String getDescription() {
		return "Did you really read the instruction Manual?";
	}
}
