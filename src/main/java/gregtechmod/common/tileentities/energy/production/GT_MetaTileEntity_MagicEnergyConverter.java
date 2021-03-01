package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GT_MetaTileEntity_MagicEnergyConverter extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_MagicEnergyConverter(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName, recipeMap, efficiency);
	}

	public GT_MetaTileEntity_MagicEnergyConverter(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap, efficiency);
	}

	@Override public boolean isFacingValid(byte aFacing) {return true;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 24 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 125);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_MagicEnergyConverter(recipeLogic.recipeMap, ((GeneratorRecipeLogic)recipeLogic).efficiency);
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == aFacing ? (aActive ? 86 : 83) : 90;
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_MagicConverter.tooltip";
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
		} catch(Throwable e) {}
    }
}
