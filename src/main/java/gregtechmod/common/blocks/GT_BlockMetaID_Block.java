package gregtechmod.common.blocks;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Multi_GasTurbine;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Multi_SteamTurbine;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_BlockMetaID_Block extends Block {
	
	public static Icon[] mIcons = new Icon[52], mIconGasTurbine = new Icon[9], mIconGasTurbineActive = new Icon[9], mIconSteamTurbine = new Icon[9], mIconSteamTurbineActive = new Icon[9];
	
	public static boolean mConnectedMachineTextures = true;
	
	public GT_BlockMetaID_Block(int aID) {
        super(aID, Material.iron);
        setHardness(3.0F);
        setResistance(10.0F);
        setUnlocalizedName("BlockMetaID_Block");
        setStepSound(Block.soundMetalFootstep);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
        for (int i = 0; i < 16; i++) MinecraftForge.setBlockHarvestLevel(this, i, "pickaxe",  2);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	for (int i = 0; i < mIcons					.length; i++) mIcons					[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":getUnlocalizedName() + "/" + i));
    	for (int i = 0; i < mIconGasTurbine			.length; i++) mIconGasTurbine			[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":"tile.GasTurbine/GasTurbine" + (i+1)));
    	for (int i = 0; i < mIconGasTurbineActive	.length; i++) mIconGasTurbineActive		[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":"tile.GasTurbine/GasTurbineActive" + (i+1)));
    	for (int i = 0; i < mIconSteamTurbine		.length; i++) mIconSteamTurbine			[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":"tile.SteamTurbine/SteamTurbine" + (i+1)));
    	for (int i = 0; i < mIconSteamTurbineActive	.length; i++) mIconSteamTurbineActive	[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_BLOCK + (GregTech_API.sConfiguration.system?"troll":"tile.SteamTurbine/SteamTurbineActive" + (i+1)));
    	
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSilver")			, mIcons[ 3]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateRuby")			, mIcons[ 4]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSapphire")		, mIcons[ 5]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAluminium")		, mIcons[ 7]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTitanium")		, mIcons[ 8]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateChrome")			, mIcons[ 9]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSteel")			, mIcons[11]);
    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateBrass")			, mIcons[12]);
	}
	
	@Override
    public boolean isBeaconBase(World aWorld, int aX, int aY, int aZ, int beaconX, int beaconY, int beaconZ) {
        return !GregTech_API.isMachineBlock(blockID, aWorld.getBlockMetadata(aX, aY, aZ));
    }
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, int par5, int par6) {
		if (GregTech_API.isMachineBlock(blockID, aWorld.getBlockMetadata(aX, aY, aZ))) {
			GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
		}
	}
	
	@Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		if (GregTech_API.isMachineBlock(blockID, aWorld.getBlockMetadata(aX, aY, aZ))) {
			GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
		}
	}
    
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
		return false;
	}
	
	@Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		if (world == null) return 0;
		Integer tMeta = world.getBlockMetadata(x, y, z);
		if (tMeta == null) tMeta = 0;
		if (tMeta ==  0) return  30.0F;
		if (tMeta ==  1) return  30.0F;
		if (tMeta ==  2) return 300.0F;
		if (tMeta ==  3) return  30.0F;
		if (tMeta ==  4) return  30.0F;
		if (tMeta ==  5) return  30.0F;
		if (tMeta ==  6) return  30.0F;
		if (tMeta ==  7) return  30.0F;
		if (tMeta ==  8) return 200.0F;
		if (tMeta ==  9) return 100.0F;
		if (tMeta == 10) return 200.0F;
		if (tMeta == 11) return 100.0F;
		if (tMeta == 12) return  30.0F;
		if (tMeta == 13) return  30.0F;
		if (tMeta == 14) return  60.0F;
		if (tMeta == 15) return  30.0F;
		return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }
	
	@Override
    public float getBlockHardness(World world, int x, int y, int z) {
		if (world == null) return 0;
		Integer tMeta = world.getBlockMetadata(x, y, z);
		if (tMeta == null) tMeta = 0;
		if (tMeta ==  2) return 100.0F;
		if (tMeta ==  8) return 10.0F;
		if (tMeta ==  9) return 10.0F;
		if (tMeta == 10) return 10.0F;
        return 3.0F;
    }
    
	@Override
	public Icon getIcon(int aSide, int aMeta) {
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
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
        return blockID;
    }
    
	@Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
	
	@Override
    public Icon getBlockTexture(IBlockAccess aWorld, int xCoord, int yCoord, int zCoord, int aSide) {
        int tMeta = aWorld.getBlockMetadata(xCoord, yCoord, zCoord);
		if (tMeta < 13 || (xCoord == 0 && yCoord == 0 && zCoord == 0) || !mConnectedMachineTextures) return this.getIcon(aSide, tMeta);
        int tStartIndex=(tMeta==15?40:tMeta==14?28:16);
        
        if (tMeta == 13) {
        	TileEntity tTileEntity;
        	Object tMetaTileEntity;
        	if (aSide == 2 || aSide == 3) {
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[0];  return mIconSteamTurbine[0];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord    , zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[3];  return mIconSteamTurbine[3];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[6];  return mIconSteamTurbine[6];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord                   , yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[1];  return mIconSteamTurbine[1];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord                   , yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[7];  return mIconSteamTurbine[7];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[8];  return mIconSteamTurbine[8];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord    , zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[5];  return mIconSteamTurbine[5];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[2];  return mIconSteamTurbine[2];
        		}
        	} else if (aSide == 4 || aSide == 5) {
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[0];  return mIconSteamTurbine[0];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord    , zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[3];  return mIconSteamTurbine[3];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[6];  return mIconSteamTurbine[6];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord                   )) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[1];  return mIconSteamTurbine[1];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord                   )) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[7];  return mIconSteamTurbine[7];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[8];  return mIconSteamTurbine[8];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord    , zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[5];  return mIconSteamTurbine[5];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_SteamTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconSteamTurbineActive[2];  return mIconSteamTurbine[2];
        		}
        	}
        }
        
        if (tMeta == 14) {
        	TileEntity tTileEntity;
        	Object tMetaTileEntity;
        	if (aSide == 2 || aSide == 3) {
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[0];  return mIconGasTurbine[0];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord    , zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[3];  return mIconGasTurbine[3];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==3?+1:-1), yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[6];  return mIconGasTurbine[6];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord                   , yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[1];  return mIconGasTurbine[1];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord                   , yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[7];  return mIconGasTurbine[7];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord + 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[8];  return mIconGasTurbine[8];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord    , zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[5];  return mIconGasTurbine[5];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord + (aSide==2?+1:-1), yCoord - 1, zCoord)) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[2];  return mIconGasTurbine[2];
        		}
        	} else if (aSide == 4 || aSide == 5) {
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[0];  return mIconGasTurbine[0];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord    , zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[3];  return mIconGasTurbine[3];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord + (aSide==4?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[6];  return mIconGasTurbine[6];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord                   )) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[1];  return mIconGasTurbine[1];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord                   )) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[7];  return mIconGasTurbine[7];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord + 1, zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[8];  return mIconGasTurbine[8];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord    , zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[5];  return mIconGasTurbine[5];
        		}
        		if (null != (tTileEntity = aWorld.getBlockTileEntity(xCoord, yCoord - 1, zCoord + (aSide==5?+1:-1))) && tTileEntity instanceof IGregTechTileEntity && ((IGregTechTileEntity)tTileEntity).getFrontFacing() == aSide && null != (tMetaTileEntity = ((IGregTechTileEntity)tTileEntity).getMetaTileEntity()) && tMetaTileEntity instanceof GT_MetaTileEntity_Multi_GasTurbine) {
        			if (((IGregTechTileEntity)tTileEntity).isActive()) return mIconGasTurbineActive[2];  return mIconGasTurbine[2];
        		}
        	}
        }
        
    	boolean[] tConnectedSides = {
    		aWorld.getBlockId(xCoord, yCoord-1, zCoord) == blockID && aWorld.getBlockMetadata(xCoord, yCoord-1, zCoord) == tMeta,
    		aWorld.getBlockId(xCoord, yCoord+1, zCoord) == blockID && aWorld.getBlockMetadata(xCoord, yCoord+1, zCoord) == tMeta,
    		aWorld.getBlockId(xCoord+1, yCoord, zCoord) == blockID && aWorld.getBlockMetadata(xCoord+1, yCoord, zCoord) == tMeta,
    		aWorld.getBlockId(xCoord, yCoord, zCoord+1) == blockID && aWorld.getBlockMetadata(xCoord, yCoord, zCoord+1) == tMeta,
    		aWorld.getBlockId(xCoord-1, yCoord, zCoord) == blockID && aWorld.getBlockMetadata(xCoord-1, yCoord, zCoord) == tMeta,
    		aWorld.getBlockId(xCoord, yCoord, zCoord-1) == blockID && aWorld.getBlockMetadata(xCoord, yCoord, zCoord-1) == tMeta
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
}