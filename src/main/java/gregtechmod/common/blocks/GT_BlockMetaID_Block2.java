package gregtechmod.common.blocks;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_OreDictUnificator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Block2 extends Block {

	public static IIcon[] mIcons = new IIcon[52];
	
	public GT_BlockMetaID_Block2() {
        super(Material.iron);
        setHardness(3.0F);
        setResistance(10.0F);
        setBlockName("BlockMetaID_Block2");
        setStepSound(Block.soundTypeMetal);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
		for (int i = 0; i < 16; i++) setHarvestLevel("pickaxe", 2, i);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
    	for (int i = 0; i < mIcons.length; i++) mIcons[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GT_Config.system ? "troll" : getUnlocalizedName() + "/" + i));
    	if (GregTech_API.sPostloadFinished) {
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateLead")			, mIcons[ 0]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateElectrum")		, mIcons[ 1]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateZinc")			, mIcons[ 2]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOlivine")			, mIcons[ 3]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGreenSapphire")	, mIcons[ 4]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("platePlatinum")		, mIcons[ 5]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungsten")		, mIcons[ 6]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateNickel")			, mIcons[ 7]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungstenSteel")	, mIcons[ 8]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAlloyIridium")	, mIcons[ 9]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateInvar")			, mIcons[10]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOsmium")			, mIcons[11]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateIridium")			, mIcons[12]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateDenseBronze")		, mIcons[13]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetYellow")	, mIcons[14]);
        	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetRed")		, mIcons[15]);
    	}
	}
	
	@Override
    public boolean isBeaconBase(IBlockAccess aWorld, int aX, int aY, int aZ, int beaconX, int beaconY, int beaconZ) {
        return !GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ));
    }
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, Block par5, int par6) {
		if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
			GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
		}
	}
	
	@Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		int meta = aWorld.getBlockMetadata(aX, aY, aZ);
		if (GregTech_API.isMachineBlock(this, meta) || (GregTech_API.sMachineIDs.containsKey(this) && meta == 13)) {
			GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
		}
	}
    
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
	}
	
	@Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		if (world == null) return 0;
		Integer tMeta = world.getBlockMetadata(x, y, z);
		if (tMeta ==  0) return  60.0F;
		if (tMeta ==  1) return  30.0F;
		if (tMeta ==  2) return  30.0F;
		if (tMeta ==  3) return  30.0F;
		if (tMeta ==  4) return  30.0F;
		if (tMeta ==  5) return  30.0F;
		if (tMeta ==  6) return 100.0F;
		if (tMeta ==  7) return  45.0F;
		if (tMeta ==  8) return 300.0F;
		if (tMeta ==  9) return 400.0F;
		if (tMeta == 10) return  60.0F;
		if (tMeta == 11) return 900.0F;
		if (tMeta == 12) return 600.0F;
		if (tMeta == 13) return  60.0F;
		if (tMeta == 14) return  30.0F;
		if (tMeta == 15) return  30.0F;
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }
	
	@Override
    public float getBlockHardness(World world, int x, int y, int z) {
		if (world == null) return 0;
		Integer tMeta = world.getBlockMetadata(x, y, z);
		if (tMeta ==  8) return 100.0F;
		if (tMeta ==  9) return 200.0F;
		if (tMeta == 13) return  10.0F;
        return 3.0F;
    }
	
	@Override
    public IIcon getIcon(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
        int tMeta = aWorld.getBlockMetadata(xCoord, yCoord, zCoord);
		if ((tMeta != 8 && tMeta != 9 && tMeta != 13) || (xCoord == 0 && yCoord == 0 && zCoord == 0) || !GT_BlockMetaID_Block.mConnectedMachineTextures) return this.getIcon(aSide, tMeta);
        int tStartIndex=(tMeta==8?16:tMeta==9?28:40);
		
    	boolean[] tConnectedSides = {
    		aWorld.getBlock(xCoord, yCoord-1, zCoord) == this && aWorld.getBlockMetadata(xCoord, yCoord-1, zCoord) == tMeta,
    		aWorld.getBlock(xCoord, yCoord+1, zCoord) == this && aWorld.getBlockMetadata(xCoord, yCoord+1, zCoord) == tMeta,
    		aWorld.getBlock(xCoord+1, yCoord, zCoord) == this && aWorld.getBlockMetadata(xCoord+1, yCoord, zCoord) == tMeta,
    		aWorld.getBlock(xCoord, yCoord, zCoord+1) == this && aWorld.getBlockMetadata(xCoord, yCoord, zCoord+1) == tMeta,
    		aWorld.getBlock(xCoord-1, yCoord, zCoord) == this && aWorld.getBlockMetadata(xCoord-1, yCoord, zCoord) == tMeta,
    		aWorld.getBlock(xCoord, yCoord, zCoord-1) == this && aWorld.getBlockMetadata(xCoord, yCoord, zCoord-1) == tMeta
    	};
    	
    	switch (aSide) {
    	case 0:
    		if (tConnectedSides[0]) return mIcons[tStartIndex+7];
    		
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+2];
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+3];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+4];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+5];

    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+8];
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+9];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+10];
    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+11];
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[2]) return mIcons[tStartIndex+1];
    		if (!tConnectedSides[5]&&!tConnectedSides[3]) return mIcons[tStartIndex+0];
    	case 1:
    		if (tConnectedSides[1]) return mIcons[tStartIndex+7];
    		
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+2];
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+3];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+4];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+5];
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+8];
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return mIcons[tStartIndex+9];
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+10];
    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+11];
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return mIcons[tStartIndex+1];
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return mIcons[tStartIndex+0];
    	case 2:
    		if (tConnectedSides[5]) return mIcons[tStartIndex+7];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+2];
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+5];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+4];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+3];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+11];
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+10];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+9];
    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+8];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return mIcons[tStartIndex+1];
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return mIcons[tStartIndex+0];
    	case 3:
    		if (tConnectedSides[3]) return mIcons[tStartIndex+7];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+4];
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+5];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+2];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+3];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+10];
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return mIcons[tStartIndex+11];
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+8];
    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+9];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return mIcons[tStartIndex+1];
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return mIcons[tStartIndex+0];
    	case 4:
    		if (tConnectedSides[4]) return mIcons[tStartIndex+7];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+5];
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+4];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+3];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+2];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+10];
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+9];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+8];
    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+11];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return mIcons[tStartIndex+0];
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return mIcons[tStartIndex+1];
    	case 5:
    		if (tConnectedSides[2]) return mIcons[tStartIndex+7];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+6];

    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+5];
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+2];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+3];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+4];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+11];
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return mIcons[tStartIndex+8];
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+9];
    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+10];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return mIcons[tStartIndex+7];
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return mIcons[tStartIndex+0];
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return mIcons[tStartIndex+1];
    	default: return mIcons[tStartIndex+7];
    	}
    }
    
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		if (entity instanceof IBossDisplayData && world.getBlockMetadata(x, y, z) == 9) { // Make wither unable to break iridium reinforced stone
			return false;
		}
		
		return super.canEntityDestroy(world, x, y, z, entity);
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMeta) {
		if (aMeta < 0 || aMeta >= mIcons.length) return null;
		return mIcons[aMeta];
	}
	
	@Override
	public int damageDropped(int par1) {
        return par1;
    }
	
	@Override
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return par1World.getBlockMetadata(par2, par3, par4);
    }
	
	@Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}