package gregtechmod.common.blocks;

import gregtechmod.api.GregTech_API;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_LightSource;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Block_LightSource extends BlockContainer {
	
	public GT_Block_LightSource() {
        super(Material.air);
        setBlockName("GT_LightSource");
        setLightLevel(1.0F);
        setLightOpacity(1);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
    @SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + "void");
    }
    
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
    
    @Override
    public boolean canCollideCheck(int par1, boolean par2) {
        return par2 && par1 == 0;
    }
    @Override
    public int getRenderBlockPass() {
        return 1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
	public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public int getRenderType() {
        return -1;
    }
    
	@Override
	public int damageDropped(int par1) {
        return 0;
    }
	
	@Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }
	
	@Override
	public int quantityDropped(int par1, int par3, Random par2Random) {
        return 0;
    }

	@Override
	public TileEntity createNewTileEntity(World aWorld, int meta) {
		return new GT_TileEntity_LightSource();
	}
	
	@SuppressWarnings("rawtypes")
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        
    }
}
