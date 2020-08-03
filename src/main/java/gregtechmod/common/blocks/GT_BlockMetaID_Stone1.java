package gregtechmod.common.blocks;

import gregtechmod.api.GregTech_API;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Stone1 extends Block {
	
	public static final int Metablockcount = 16;
	public static Icon[] mIcons = new Icon[Metablockcount];
	
	public GT_BlockMetaID_Stone1(int aID) {
        super(aID, Material.rock);
        setResistance(60.0F);
        setUnlocalizedName("BlockMetaID_Stone1");
        setStepSound(Block.soundStoneFootstep);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
        for (int i = 0; i < 16; i++) MinecraftForge.setBlockHarvestLevel(this, i, "pickaxe",  3);
	}
	
	@Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4) {
        return blockHardness = Block.stone.blockHardness * 3.0F;
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	for (int i = 0; i < mIcons.length; i++) mIcons[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":getUnlocalizedName() + "/" + i));
	}
	
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) % 8 < 3;
	}
	
	@Override
    public Icon getBlockTexture(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
		return this.getIcon(aSide, aWorld.getBlockMetadata(xCoord, yCoord, zCoord));
    }
    
	@Override
	public Icon getIcon(int aSide, int aMeta) {
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
	public int idDropped(int par1, Random par2Random, int par3) {
        return blockID;
    }
	
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < Metablockcount; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}