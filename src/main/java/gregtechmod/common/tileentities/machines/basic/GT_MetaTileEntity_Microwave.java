package gregtechmod.common.tileentities.machines.basic;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class GT_MetaTileEntity_Microwave extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Microwave(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Microwave(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 148);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Microwave(recipeLogic.recipeMap);
	}
	
	@Override
	public void initRecipeLogic(List<Recipe> recipeMap) {
		super.initRecipeLogic(recipeMap);
		recipeLogic.setRecipeProvider(() -> {
			ItemStack output = null;
			if (GT_Utility.isStackValid(mInventory[2]) && (output = checkForExlosion()) != null) {
				if (TileEntityFurnace.getItemBurnTime(mInventory[2]) > 0 || TileEntityFurnace.getItemBurnTime(output) > 0) {
		    		mInventory[2] = null;
					getBaseMetaTileEntity().setOnFire();
				} else {
					ItemStack input = mInventory[2].copy();
					input.stackSize = 1;
					return new Recipe(input, null, output, null, null, null, 25, 4, 0, false);
				}
			}
			
			return null;
		});
	}
	
	private ItemStack checkForExlosion() {
		ItemStack tOutput = GT_ModHandler.getSmeltingOutput(mInventory[2], false, mInventory[3]);
		if (mInventory[2].getItem() == GT_Items.Spray_CFoam.getItem() ||
				GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)) ||
				GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("constructionFoam", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)) ||
				GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("CFCell", 1))) {
//			mInventory[2] = null;
//			getBaseMetaTileEntity().doExplosion(128); // FIXME get filled sprayer
			try {
				ItemStack foamSprayer = GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, new ItemStack(Blocks.sponge, 1));
				NBTTagCompound data = new NBTTagCompound();
				NBTTagCompound fluid = new NBTTagCompound();
				fluid.setInteger("Amount", 10000);
				fluid.setString("Fluid", "ic2constructionfoam");
				data.setTag("Fluid", fluid);
				foamSprayer.setTagCompound(data);
				
				
				
				for (int i = 0; i < 6; i++) {
					foamSprayer.getItem().onItemUse(foamSprayer, new FakePlayer((WorldServer) getBaseMetaTileEntity().getWorld(), new GameProfile(UUID.randomUUID(),"Foo")),
							getBaseMetaTileEntity().getWorld(), 
							getBaseMetaTileEntity().getXCoord(), 
							getBaseMetaTileEntity().getYCoord(), 
							getBaseMetaTileEntity().getZCoord(), i, 0, 0, 0);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			return null;
		} else {
			if (OrePrefixes.ingot.contains(mInventory[2])
					|| OrePrefixes.ingot.contains(tOutput)
					|| Materials.Netherrack.contains(mInventory[2])
					|| Materials.Gunpowder.contains(mInventory[2])
					|| Materials.Gunpowder.contains(tOutput)
					|| mInventory[2].isItemEqual(new ItemStack(Blocks.netherrack))
					|| mInventory[2].getItem() == Items.egg) {
				
				mInventory[2] = null;
				getBaseMetaTileEntity().doExplosion(128);
				return null;
			}
		}
		
		return tOutput;
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
		return "metatileentity.GT_Microwave.tooltip";
	}
}
