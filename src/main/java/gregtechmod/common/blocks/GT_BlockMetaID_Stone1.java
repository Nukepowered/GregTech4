package gregtechmod.common.blocks;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Stone1 extends Block {
	
	public static final int Metablockcount = 16;
	public static IIcon[] mIcons = new IIcon[Metablockcount];
	
	public GT_BlockMetaID_Stone1() {
        super(Material.rock);
        setResistance(60.0F);
        setBlockName("BlockMetaID_Stone1");
        setStepSound(Block.soundTypeStone);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
        for (int i = 0; i < 16; i++) setHarvestLevel("pickaxe", 3, i);
	}
	
	@Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4) {
        return blockHardness = Blocks.stone.getBlockHardness(par1World, par2, par3, par4) * 3.0F;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
    	for (int i = 0; i < mIcons.length; i++) mIcons[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : getUnlocalizedName() + "/" + i));
	}
	
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) % 8 < 3;
	}
	
	@Override
    public IIcon getIcon(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
		return this.getIcon(aSide, aWorld.getBlockMetadata(xCoord, yCoord, zCoord));
    }
    
	@Override
	public IIcon getIcon(int aSide, int aMeta) {
		if (aMeta < 0 || aMeta >= mIcons.length) return null;
		return mIcons[aMeta];
	}
	
	@Override
	public int damageDropped(int par1) {
        return par1%8==0?par1+1:par1;
    }
	
	@Override
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMetadata(par2, par3, par4);
    }
	
	@Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }
	
	@Override
	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
		return true;
	}
	
	@Override
	public float getExplosionResistance(Entity ent) {
		if (ent instanceof IBossDisplayData) {
			return blockResistance / 10.0F;
		}
		
		return super.getExplosionResistance(ent);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < Metablockcount; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}