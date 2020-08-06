package gregtechmod.loaders.postload;

import java.lang.reflect.Method;

import cpw.mods.fml.relauncher.ReflectionHelper;
import gregtechmod.GT_Mod;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;

public class GT_BlockResistanceLoader implements Runnable {
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
    	Blocks.brick_block.setResistance(20.0F);
    	Blocks.hardened_clay.setResistance(15.0F);
    	Blocks.stained_hardened_clay.setResistance(15.0F);
    	Blocks.iron_block.setResistance(30.0F);
    	Blocks.diamond_block.setResistance(60.0F);
    	
    	if (GT_Mod.sHardRock) {
    		Blocks.stone.setHardness(16.0F);
    		Blocks.brick_block.setHardness(32.0F);
    		Blocks.hardened_clay.setHardness(32.0F);
    		Blocks.stained_hardened_clay.setHardness(32.0F);
    		Blocks.cobblestone.setHardness(12.0F);
    		Blocks.stonebrick.setHardness(24.0F);
    	}
    	
    	Blocks.bed.setHarvestLevel("axe", 0);
    	Blocks.hay_block.setHarvestLevel("axe", 0);
    	Blocks.tnt.setHarvestLevel("pickaxe", 0);
    	Blocks.sponge.setHarvestLevel("axe", 0);
    	Blocks.monster_egg.setHarvestLevel("pickaxe", 0);
    	
    	Method m = ReflectionHelper.findMethod(Material.class, Material.tnt, new String[] {"setAdventureModeExempt", "func_85158_p"});
    	try {
    		m.invoke(Material.tnt);    	
    	} catch (Throwable e) {}
    	
    	try {
    		ItemAxe.field_150917_c.add(Blocks.bed);
    		ItemAxe.field_150917_c.add(Blocks.hay_block);
    		ItemAxe.field_150917_c.add(Blocks.sponge);
    		
    		ItemPickaxe.field_150915_c.add(Blocks.monster_egg);
    		ItemPickaxe.field_150915_c.add(Blocks.tnt);
    	} catch(Throwable e) {}
	}
}
