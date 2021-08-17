package gregtechmod.common.tileentities.machines.basic;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class GT_MetaTileEntity_Microwave extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Microwave(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Microwave(RecipeMap<?> recipeMap) {
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
	public void initRecipeLogic(RecipeMap<?> recipeMap) {
		recipeLogic = new RecipeLogic(recipeMap, this) {
			@Override
			protected Recipe findRecipe() {
				ItemStack output = null;
				
				List<ItemStack> inputs = getInputItems();
				for (int i = 0; i < inputs.size(); i++) {
					ItemStack input = inputs.get(i);
					if (GT_Utility.isStackValid(input) && (output = checkForExlosion(input, i)) != null) {
						if (TileEntityFurnace.getItemBurnTime(input) > 0 || TileEntityFurnace.getItemBurnTime(output) > 0) {
				    		inputs.set(i, null);
							getBaseMetaTileEntity().setOnFire();
						} else {
							ItemStack input1 = input.copy();
							input1.stackSize = 1;
							return new Recipe(0, 4, 25, false,
									Collections.singleton(RecipeEntry.singleton(input1, Match.STRICT)),
									Collections.singleton(output.copy()),
									Collections.emptyList());
						}
					}
				}
				
				return null;
			}
		};
	}
	
	private ItemStack checkForExlosion(ItemStack input, int inIdx) {
		ItemStack tOutput = GT_ModHandler.getSmeltingOutput(input, false, null);
		if (input.getItem() == GT_Items.Spray_CFoam.getItem() ||
				input.isItemEqual(GT_ModHandler.getIC2Item("constructionFoamSprayer", 1)) ||
				input.isItemEqual(GT_ModHandler.getIC2Item("constructionFoam", 1)) ||
				input.isItemEqual(GT_ModHandler.getIC2Item("CFCell", 1))) {
			IGregTechTileEntity ent = getBaseMetaTileEntity();
			getInputItems().set(inIdx, null);
			ent.doExplosion(128);
			try {
				ItemStack foamSprayer = GT_ModHandler.getIC2Item("constructionFoamSprayer", 1, new ItemStack(Blocks.sponge, 1));
				NBTTagCompound data = new NBTTagCompound();
				NBTTagCompound fluid = new NBTTagCompound();
				fluid.setInteger("Amount", 32000);
				fluid.setString("FluidName", "ic2constructionfoam");
				data.setTag("Fluid", fluid);
				foamSprayer.setTagCompound(data);
				for (int i = 0; i < 6; i++) {
					foamSprayer.getItem().onItemUse(foamSprayer, new FakePlayer((WorldServer) ent.getWorld(), new GameProfile(UUID.randomUUID(),"Foo")), ent.getWorld(), 
							ent.getXCoord(), 
							ent.getYCoord(), 
							ent.getZCoord(), i, 0, 0, 0);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			return null;
		} else {
			if (OrePrefixes.ingot.contains(input)
					|| OrePrefixes.ingot.contains(tOutput)
					|| Materials.Netherrack.contains(input)
					|| Materials.Gunpowder.contains(input)
					|| Materials.Gunpowder.contains(tOutput)
					|| input.isItemEqual(new ItemStack(Blocks.netherrack))
					|| input.getItem() == Items.egg) {
				
				getInputItems().set(inIdx, null);
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
