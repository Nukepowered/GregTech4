package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.render.GT_Renderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class GT_Client extends GT_Proxy {
	public static GT_Renderer mRenderer = new GT_Renderer();
	
	public boolean isServerSide() {
		return true;
	}
	
	public boolean isClientSide() {
		return true;
	}
	
	public boolean isBukkitSide() {
		return false;
	}
	
	@Override
	public int addArmor(String aPrefix) {
		try {
			return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);
		} catch(Throwable e) {}
		return 0;
	}
	
	@Override
    public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
    	float tFloat = 1.0F;
    	String tString = "note.harp";
    	
    	if (aStack == null) return;
    		
	    for (int i = 0; i < GT_Mod.mSoundItems.size(); i++) {
	    	if (GT_Utility.areStacksEqual(GT_Mod.mSoundItems.get(i), aStack)) {
	    		tString = GT_Mod.mSoundNames.get(i);
	    		break;
	    	}
	    }
    	
    	if (tString.startsWith("random.explode")) {
    		if (aStack.stackSize==3) {
    			tString = "random.fuse";
    		} else if (aStack.stackSize==2) {
    			tString = "random.old_explode";
    		}
    	}

    	if (tString.startsWith("streaming.")) {
    		switch (aStack.stackSize) {
    		case  1:
    			tString += "13";
    			break;
    		case  2:
    			tString += "cat";
    			break;
    		case  3:
    			tString += "blocks";
    			break;
    		case  4:
    			tString += "chirp";
    			break;
    		case  5:
    			tString += "far";
    			break;
    		case  6:
    			tString += "mall";
    			break;
    		case  7:
    			tString += "mellohi";
    			break;
    		case  8:
    			tString += "stal";
    			break;
    		case  9:
    			tString += "strad";
    			break;
    		case 10:
    			tString += "ward";
    			break;
    		case 11:
    			tString += "11";
    			break;
    		case 12:
    			tString += "wait";
    			break;
    		default:
    			tString += "wherearewenow";
    			break;
    		}
    	}
    	
    	if (tString.startsWith("note.")) {
    		tFloat = (float)Math.pow(2.0D, (double)(aStack.stackSize - 13) / 12.0D);
    	}
    	
    	if (tString.startsWith("streaming.")) {
    		aWorld.playRecord(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ);
    	} else {
    		aWorld.playSound(aX, aY, aZ, tString, 3.0F, tFloat, false);
    	}
    }
}
