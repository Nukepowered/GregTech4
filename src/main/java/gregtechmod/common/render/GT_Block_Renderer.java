package gregtechmod.common.render;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class GT_Block_Renderer implements ISimpleBlockRenderingHandler {
	
	public final int mRenderID;
	public static GT_Block_Renderer instance;
	
	public GT_Block_Renderer() {
		mRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(instance = this);
	}
	
    public void renderInventoryBlock(Block aBlock, int aMeta, int aModelID, RenderBlocks aRenderer) {
    	if (aMeta >= 0 && aMeta < 16) {
			renderNormalInventoryBlock(aBlock, aMeta, aModelID, aRenderer);
    		return;
    	}
    	if (aMeta > 15 && aMeta < GregTech_API.MAXIMUM_METATILE_IDS && GregTech_API.mMetaTileList[aMeta] != null) {
    		switch (GregTech_API.mMetaTileList[aMeta].getTileEntityBaseType()) {
    		case  1: // don't forget the 'break;'
    		case  2: // don't forget the 'break;'
    		case  3: // don't forget the 'break;'
    		default: renderNormalInventoryBlock(aBlock, aMeta, aModelID, aRenderer);
    		}
    		return;
    	}
    }
    
    private void renderNormalInventoryBlock(Block aBlock, int aMeta, int aModelID, RenderBlocks aRenderer) {
    	aBlock.setBlockBoundsForItemRender();
		aRenderer.setRenderBoundsFromBlock(aBlock);
		
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0, -1, 0);
		renderNegativeYFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 0, aMeta));
		Tessellator.instance.draw();
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0, 1, 0);
		renderPositiveYFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 1, aMeta));
		Tessellator.instance.draw();
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0, 0, -1);
		renderNegativeZFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 2, aMeta));
		Tessellator.instance.draw();
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0, 0, 1);
		renderPositiveZFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 3, aMeta));
		Tessellator.instance.draw();
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(-1, 0, 0);
		renderNegativeXFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 4, aMeta));
		Tessellator.instance.draw();
		
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(1, 0, 0);
		renderPositiveXFacing(aRenderer, aBlock, 0, 0, 0, aRenderer.getBlockIconFromSideAndMetadata(aBlock, 5, aMeta));
		Tessellator.instance.draw();
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    public boolean renderWorldBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, int aModelID, RenderBlocks aRenderer) {
		int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
		switch(aMeta) {
		case  1: return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aModelID, aRenderer);
		case  2: return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aModelID, aRenderer);
		case  3: return renderPipeBlock(aWorld, aX, aY, aZ, aBlock, aModelID, aRenderer);
		default: 
			aBlock.colorMultiplier(aWorld, aX, aY, aZ);
			
			return aRenderer.renderStandardBlock(aBlock, aX, aY, aZ);
		}
    }
    
    /**
     * @author Player
     * 
     * Most of this has been "stolen" from IC2, all credit to Player for doing the original Render Code.
     * I have changed quite a bit of this Code, just to make it compatible with my Stuff.
     */
    public boolean renderPipeBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, int aModelID, RenderBlocks aRenderer) {
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (aTileEntity instanceof BaseMetaPipeEntity) {
			aRenderer.flipTexture = false;
			
			BaseMetaPipeEntity tTileEntity = (BaseMetaPipeEntity)aTileEntity;
			
			if ((tTileEntity.mConnections & 192) != 0) return aRenderer.renderStandardBlock(aBlock, aX, aY, aZ);
			
			float th = tTileEntity.getThickNess();
			
			if (th >= 1.0F) return aRenderer.renderStandardBlock(aBlock, aX, aY, aZ);
			
			float sp = (1F-th) / 2F;
			
			byte tConnections = 0;
			for (byte i = 0; i < 6; i++) if ((tTileEntity.mConnections & (1 << i)) != 0) tConnections |= (1 << ((i + 2) % 6));
			
			IIcon tIcons[] = new IIcon[6], tCovers[] = new IIcon[6];
			boolean tIsCovered[] = new boolean[6];
			for (byte i = 0; i < 6; i++) tIsCovered[i] = (tTileEntity.getCoverIDAtSide(i) != 0);
			
			if (tIsCovered[0] && tIsCovered[1] && tIsCovered[2] && tIsCovered[3] && tIsCovered[4] && tIsCovered[5]) return aRenderer.renderStandardBlock(aBlock, aX, aY, aZ);
			
			for (byte i = 0; i < 6; i++) {
				tCovers[i] = aBlock.getIcon(aWorld, aX, aY, aZ, i);//.getBlockTextureFromSide(i);
				tIcons[i] = tTileEntity.getUncoveredIcon(i, (byte)1);
				if (tIcons[i] == null) {
					int tIndex = tTileEntity.getUncoveredIndex(i, (byte)1);
					if (tIndex >= 0 && tIndex < GT_BlockMetaID_Machine.mIcons.length) tIcons[i] = GT_BlockMetaID_Machine.mIcons[tIndex];
					if (tIcons[i] == null) {
						tIcons[i] = GregTech_API.FAIL_ICON;
					}
				}
			}
			
			int tC = aBlock.colorMultiplier(aWorld, aX, aY, aZ);
	        float tR = (float)(tC >> 16 & 255) / 300.0F, tG = (float)(tC >>  8 & 255) / 300.0F, tB = (float)(tC       & 255) / 300.0F;
	        
	        Tessellator.instance.setColorOpaque_F(tR, tG, tB);
	        
			if (tConnections == 0) {
				aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
				renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
				renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
				renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
				renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
				renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
			} else if (tConnections == 0x3) {
				aBlock.setBlockBounds(0, sp, sp, 1F, sp+th, sp+th);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
				renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
				aRenderer.flipTexture = true;
				renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
				aRenderer.flipTexture = false;
				renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
				if (!tIsCovered[4]) {
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
				}
				if (!tIsCovered[5]) {
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
				}
			} else if (tConnections == 0xc) {
				aBlock.setBlockBounds(sp, 0, sp, sp+th, 1F, sp+th);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
				renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
				renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
				renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
				
				if (!tIsCovered[0]) {
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
				}
				if (!tIsCovered[1]) {
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
				}
			} else if (tConnections == 0x30) {
				aBlock.setBlockBounds(sp, sp, 0, sp+th, sp+th, 1F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
				renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
				renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
				renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
				if (!tIsCovered[2]) {
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
				}
				if (!tIsCovered[3]) {
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
				}
			} else {
				// xN
				if ((tConnections & 0x1) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
				} else {
					aBlock.setBlockBounds(0, sp, sp, sp, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
					if (!tIsCovered[4]) {
						renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
					}
				}
				
				// xP
				if ((tConnections & 0x2) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
				} else {
					aBlock.setBlockBounds(sp+th, sp, sp, 1F, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
					if (!tIsCovered[5]) {
						renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
					}
				}
				
				// yN
				if ((tConnections & 0x4) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
				} else {
					aBlock.setBlockBounds(sp, 0, sp, sp+th, sp, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
					if (!tIsCovered[0]) {
						renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
					}
				}
				
				// yP
				if ((tConnections & 0x8) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
				} else {
					aBlock.setBlockBounds(sp, sp+th, sp, sp+th, 1F, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
					if (!tIsCovered[1]) {
						renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
					}
				}
				
				// zN
				if ((tConnections & 0x10) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
				} else {
					aBlock.setBlockBounds(sp, sp, 0, sp+th, sp+th, sp);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
					if (!tIsCovered[2]) {
						renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[2]);
					}
				}
				
				// zP
				if ((tConnections & 0x20) == 0) {
					aBlock.setBlockBounds(sp, sp, sp, sp+th, sp+th, sp+th);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
				} else {
					aBlock.setBlockBounds(sp, sp, sp+th, sp+th, sp+th, 1F);
					aRenderer.setRenderBoundsFromBlock(aBlock);
					renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[0]);
					renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[1]);
					renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[4]);
					renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[5]);
					if (!tIsCovered[3]) {
						renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tIcons[3]);
					}
				}
			}
			
			if (tIsCovered[0]) {
				aBlock.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
				renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
				if (!tIsCovered[2]) renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
				if (!tIsCovered[3]) renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
				if (!tIsCovered[4]) renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
				if (!tIsCovered[5]) renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[0]);
			}
			if (tIsCovered[1]) {
				aBlock.setBlockBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
				renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
				if (!tIsCovered[2]) renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
				if (!tIsCovered[3]) renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
				if (!tIsCovered[4]) renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
				if (!tIsCovered[5]) renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[1]);
			}
			if (tIsCovered[2]) {
				aBlock.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				if (!tIsCovered[0]) renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
				if (!tIsCovered[1]) renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
				renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
				renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
				if (!tIsCovered[4]) renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
				if (!tIsCovered[5]) renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[2]);
			}
			if (tIsCovered[3]) {
				aBlock.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				if (!tIsCovered[0]) renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
				if (!tIsCovered[1]) renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
				renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
				renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
				if (!tIsCovered[4]) renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
				if (!tIsCovered[5]) renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[3]);
			}
			if (tIsCovered[4]) {
				aBlock.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				if (!tIsCovered[0]) renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
				if (!tIsCovered[1]) renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
				if (!tIsCovered[2]) renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
				if (!tIsCovered[3]) renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
				renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
				renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[4]);
			}
			if (tIsCovered[5]) {
				aBlock.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				aRenderer.setRenderBoundsFromBlock(aBlock);
				if (!tIsCovered[0]) renderNegativeYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
				if (!tIsCovered[1]) renderPositiveYFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
				if (!tIsCovered[2]) renderNegativeZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
				if (!tIsCovered[3]) renderPositiveZFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
				renderNegativeXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
				renderPositiveXFacing(aRenderer, aBlock, aX, aY, aZ, tCovers[5]);
			}
			
			aBlock.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			aRenderer.setRenderBoundsFromBlock(aBlock);
		}
		
		return true;
    }
    
    public static void renderPositiveXFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.flipTexture = true;
        aRenderer.renderFaceXPos(aBlock, aX, aY, aZ, aIcon);
		aRenderer.flipTexture = false;
    }
    
    public static void renderPositiveYFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.renderFaceYPos(aBlock, aX, aY, aZ, aIcon);
    }
    
    public static void renderPositiveZFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.renderFaceZPos(aBlock, aX, aY, aZ, aIcon);
    }
    
    public static void renderNegativeXFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.renderFaceXNeg(aBlock, aX, aY, aZ, aIcon);
    }
    
    public static void renderNegativeYFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.renderFaceYNeg(aBlock, aX, aY, aZ, aIcon);
    }
    
    public static void renderNegativeZFacing(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, IIcon aIcon) {
    	aRenderer.flipTexture = true;
        aRenderer.renderFaceZNeg(aBlock, aX, aY, aZ, aIcon);
		aRenderer.flipTexture = false;
    }
    
    public int getRenderId() {
        return mRenderID;
    }

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}