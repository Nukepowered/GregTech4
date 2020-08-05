package gregtechmod.common.blocks;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_OreDictUnificator;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Ore extends Block {

	public IIcon[] mIconsRed = new IIcon[96], mIconsBlack = new IIcon[96], mIconsStone = new IIcon[96], mIconsNether = new IIcon[96], mIconsEnd = new IIcon[96], mIcons = new IIcon[96];
	
	public static final int Metablockcount = 16;
	
	public GT_BlockMetaID_Ore() {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(10.0F);
        setBlockName("BlockMetaID_Ore");
        setStepSound(Block.soundTypeStone);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
        
		setHarvestLevel("pickaxe", 1, 0);
		setHarvestLevel("pickaxe", 1, 1);
		setHarvestLevel("pickaxe", 3, 2);
		setHarvestLevel("pickaxe", 2, 3);
		setHarvestLevel("pickaxe", 2, 4);
		setHarvestLevel("pickaxe", 1, 5);
		setHarvestLevel("pickaxe", 1, 6);
		setHarvestLevel("pickaxe", 2, 7);
		setHarvestLevel("pickaxe", 1, 8);
		setHarvestLevel("pickaxe", 2, 9);
		setHarvestLevel("pickaxe", 3, 10);
		setHarvestLevel("pickaxe", 3, 11);
		setHarvestLevel("pickaxe", 2, 12);
		setHarvestLevel("pickaxe", 2, 13);
		setHarvestLevel("pickaxe", 2, 14);
		setHarvestLevel("pickaxe", 2, 15);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
		for (int i = 0; i < 96; i++) mIcons[i]       = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_Default"+ "/" + i));
		for (int i = 0; i < 96; i++) mIconsStone[i]  = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_Stone"  + "/" + i));
		for (int i = 0; i < 96; i++) mIconsNether[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_Nether" + "/" + i));
		for (int i = 0; i < 96; i++) mIconsEnd[i]    = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_End"    + "/" + i));
		for (int i = 0; i < 96; i++) mIconsBlack[i]  = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_Black"  + "/" + i));
		for (int i = 0; i < 96; i++) mIconsRed[i]    = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : "tile.Ore_Red"    + "/" + i));
	}
	
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        int var8 = 0;
    	switch(par5) {
    	case  2: var8 = 30 + par1World.rand.nextInt(21); break;
    	case  3: var8 =  3 + par1World.rand.nextInt( 5); break;
    	case  4: var8 =  3 + par1World.rand.nextInt( 5); break;
    	case  6: var8 =  1 + par1World.rand.nextInt( 1); break;
    	case  7: var8 =  3 + par1World.rand.nextInt( 3); break;
    	case  8: var8 =  1 + par1World.rand.nextInt( 1); break;
    	}
        if (var8>0) dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
    }
	
	@Override
    public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
        ArrayList<ItemStack> rList = new ArrayList<ItemStack>();
		switch (aMeta) {
		case  2:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Iridium, 1+aWorld.rand.nextInt(1 + (aFortune/2))));
			break;
		case  3:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 1 + aWorld.rand.nextInt(1 + aFortune)));
			if (aWorld.rand.nextInt(Math.max(1, 32/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed, 1));
			break;
		case  4:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 1 + aWorld.rand.nextInt(1 + aFortune)));
			if (aWorld.rand.nextInt(Math.max(1, 64/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire, 1));
			break;
		case  6:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Pyrite, 2 + aWorld.rand.nextInt(1 + aFortune)));
			break;
		case  7:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cinnabar, 2 + aWorld.rand.nextInt(1 + aFortune)));
			if (aWorld.rand.nextInt(Math.max(1,  4/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1));
			break;
		case  8:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sphalerite, 2 + aWorld.rand.nextInt(1 + aFortune)));
			if (aWorld.rand.nextInt(Math.max(1,  4/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc, 1));
			if (aWorld.rand.nextInt(Math.max(1, 32/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow, 1));
			break;
		case 11:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 1 + aWorld.rand.nextInt(1 + aFortune)));
			break;
		case 12:
			rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodalite, 6 + 3 * aWorld.rand.nextInt(1 + aFortune)));
			if (aWorld.rand.nextInt(Math.max(1,  4/(aFortune+1))) == 0)
				rList.add(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1));
			break;
		default:
			rList.add(new ItemStack(this, 1, aMeta));
			break;
		}
        return rList;
    }
	
    public boolean canDragonDestroy(World world, int x, int y, int z) {
        return false;
    }
	
	@Override
    public float getBlockHardness(World world, int x, int y, int z) {
		if (world == null) return 0;
		switch (world.getBlockMetadata(x, y, z)) {
		case  1: return  3.0F;
		case  2: return 20.0F;
		case  3: return  4.0F;
		case  4: return  4.0F;
		case  5: return  3.0F;
		case  6: return  2.0F;
		case  7: return  3.0F;
		case  8: return  2.0F;
		case  9: return  4.0F;
		case 10: return  3.5F;
		default: return super.getBlockHardness(world, x, y, z);
		}
    }
	
	public byte getStyle(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Block tBlock;
		int tMeta;
		
		tBlock = aWorld.getBlock(aX + 1, aY, aZ);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX+1, aY, aZ);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		
		tBlock = aWorld.getBlock(aX - 1, aY, aZ);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX-1, aY, aZ);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		
		tBlock = aWorld.getBlock(aX, aY + 1, aZ);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX, aY+1, aZ);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		
		tBlock = aWorld.getBlock(aX, aY - 1, aZ);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX, aY-1, aZ);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		
		tBlock = aWorld.getBlock(aX, aY, aZ + 1);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX, aY, aZ+1);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		
		tBlock = aWorld.getBlock(aX, aY, aZ - 1);
		if (tBlock == Blocks.stone) return 0;
		if (tBlock == Blocks.netherrack) return -1;
		if (tBlock == Blocks.end_stone) return +1;
		if (tBlock != Blocks.air) {
			tMeta = aWorld.getBlockMetadata(aX, aY, aZ-1);
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBlackGranite")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneBasalt")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneAbyssal")) return +2;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedRock")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneRedGranite")) return +3;
			if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(tBlock, 1, tMeta), "stoneGranite")) return +2;
		}
		try {
			if (aWorld instanceof World) {
				tBlock = aWorld.getBlock(aX + 1, aY, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX+1, aY, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX+1, aY, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX+1, aY, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX-1, aY, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX-1, aY, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX-1, aY, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX-1, aY, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY + 1, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY+1, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY+1, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY+1, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY - 1, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY-1, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY-1, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY-1, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY, aZ + 1);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ+1, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ+1, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ+1, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY, aZ - 1);
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ-1, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ-1, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen((World)aWorld, aX, aY, aZ-1, Blocks.end_stone)) return  1;
			} else {
				tBlock = aWorld.getBlock(aX + 1, aY, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX+1, aY, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX+1, aY, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX+1, aY, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX - 1, aY, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX-1, aY, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX-1, aY, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX-1, aY, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY + 1, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY+1, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY+1, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY+1, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY - 1, aZ);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY-1, aZ, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY-1, aZ, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY-1, aZ, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY, aZ + 1);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ+1, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ+1, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ+1, Blocks.end_stone)) return  1;
				tBlock = aWorld.getBlock(aX, aY, aZ - 1);
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ-1, Blocks.stone)) return  0;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ-1, Blocks.netherrack)) return -1;
				if (tBlock != null && tBlock.isReplaceableOreGen(null, aX, aY, aZ-1, Blocks.end_stone)) return  1;
			}
		} catch(Throwable e) {}
		
		return Byte.MIN_VALUE;
	}
	
	@Override
    public IIcon getIcon(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		int tSide, aIndex = aWorld.getBlockMetadata(aX, aY, aZ)*6+((tSide = Math.abs(aSide^aX^aY^aZ))%6);
		if (aIndex < 0 || aIndex >= 96) return null;
		switch (getStyle(aWorld, aX, aY, aZ)) {
		case -1: if (!GT_Mod.sInvisibleOres || tSide % 12 > 6) return mIconsNether[aIndex]; else return Blocks.netherrack.getBlockTextureFromSide(aSide);
		case  0: if (!GT_Mod.sInvisibleOres || tSide % 12 > 6) return mIconsStone [aIndex]; else return Blocks.stone     .getBlockTextureFromSide(aSide);
		case +1: if (!GT_Mod.sInvisibleOres || tSide % 12 > 6) return mIconsEnd   [aIndex]; else return Blocks.end_stone .getBlockTextureFromSide(aSide);
		case +2: if (!GT_Mod.sInvisibleOres || tSide % 12 > 6) return mIconsBlack [aIndex]; else return GregTech_API.sBlockList[5].getIcon(aSide, 0);
		case +3: if (!GT_Mod.sInvisibleOres || tSide % 12 > 6) return mIconsRed   [aIndex]; else return GregTech_API.sBlockList[5].getIcon(aSide, 8);
		default: return mIcons[aIndex];
		}
    }
    
	@Override
	public IIcon getIcon(int aSide, int aMeta) {
		if (aMeta < 0 || aMeta*6+aSide >= 96) return null;
		return mIconsStone[aMeta*6+aSide];
	}
	
	@Override
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMetadata(par2, par3, par4);
    }
	
	@SideOnly(Side.CLIENT)
	@Override 
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 1; i < Metablockcount; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
