package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.world.GT_Worldgen_Ore_Normal;
import gregtechmod.api.world.GT_Worldgen_Ore_SingleBlock;
import gregtechmod.api.world.GT_Worldgen_Ore_SingleBlock_UnderLava;
import gregtechmod.api.world.GT_Worldgen_Stone_Ore_SingleBlock;
import gregtechmod.common.GT_Worldgenerator;

public class GT_Worldgenloader implements Runnable {
	@Override
	public void run() {
		int tID = GregTech_API.sBlockList[2].blockID;
		
		//new GT_Worldgen_Boulder						("Overworld.Boulder.BlackGranite_Small"		, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1, 30, 1,   0, 120, null, true);
		//new GT_Worldgen_Boulder						("Overworld.Boulder.BlackGranite_Medium"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1, 40, 2,   0, 120, null, true);
		//new GT_Worldgen_Boulder						("Overworld.Boulder.BlackGranite_Large"		, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1, 50, 4,   0, 120, null, true);
		
		//new GT_Worldgen_Boulder						("Overworld.Boulder.RedGranite_Small"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1, 30, 1,   0, 120, null, true);
		//new GT_Worldgen_Boulder						("Overworld.Boulder.RedGranite_Medium"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1, 50, 2,   0, 120, null, true);
		//new GT_Worldgen_Boulder						("Overworld.Boulder.RedGranite_Large"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1, 60, 4,   0, 120, null, true);
		
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.BlackGranite_Tiny"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1, 50, 48,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {2, 9, 10}, new int[] {20000, 2500, 15000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.BlackGranite_Small"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1,100, 96,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {2, 9, 10}, new int[] {20000, 2500, 15000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.BlackGranite_Medium"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1,200,144,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {2, 9, 10}, new int[] {20000, 2500, 15000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.BlackGranite_Large"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1,300,192,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {2, 9, 10}, new int[] {20000, 2500, 15000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.BlackGranite_Huge"	, true , GregTech_API.sBlockList[5].blockID,  0,  0,  1,400,240,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {2, 9, 10}, new int[] {20000, 2500, 15000});
		
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.RedGranite_Tiny"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1, 50, 48,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {7, 8, 13}, new int[] {20000, 2000, 5000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.RedGranite_Small"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1,100, 96,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {7, 8, 13}, new int[] {20000, 2000, 5000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.RedGranite_Medium"	, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1,200,144,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {7, 8, 13}, new int[] {20000, 2000, 5000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.RedGranite_Large"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1,300,192,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {7, 8, 13}, new int[] {20000, 2000, 5000});
		new GT_Worldgen_Stone_Ore_SingleBlock     	("Overworld.Layer.RedGranite_Huge"		, true , GregTech_API.sBlockList[5].blockID,  8,  0,  1,400,240,   0, 120, null, false, new int[] {tID, tID, tID}, new int[] {7, 8, 13}, new int[] {20000, 2000, 5000});
		
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Galena"					, true , tID,  1,  0,  1, 16,  3,   0,  32, null, false);
		
		new GT_Worldgen_Ore_SingleBlock				("Overworld.Ore.Iridium"				, true , tID,  2,  0,  1,  0,  5,   0, 100, null, false);
		
		new GT_Worldgen_Ore_SingleBlock				("Overworld.Ore.Ruby"					, true , tID,  3,  0,  8,  0,  1,   0,  32, GregTech_API.sRubyList, false);
		
		new GT_Worldgen_Ore_SingleBlock				("Overworld.Ore.Sapphire"				, true , tID,  4,  0,  8,  0,  1,   0,  32, GregTech_API.sSapphireList, false);
		
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Bauxite"				, true , tID,  5,  0,  2, 16,  1,  32,  80, GregTech_API.sBauxiteList, false);
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Bauxite_High"			, true , tID,  5,  0,  3, 16,  1,  70, 120, GregTech_API.sBauxiteList, false);
		
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Tetrahedrite"			, true , tID, 13,  0,  1, 32,  6,  32,  70, GregTech_API.sTetrahedriteList, false);
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Tetrahedrite_High"		, true , tID, 13,  0,  1, 32,  1,  70, 120, GregTech_API.sTetrahedriteList, false);
		
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Cassiterite"			, true , tID, 14,  0,  1, 32, 12,  32,  70, GregTech_API.sCassiteriteList, false);
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Cassiterite_High"		, true , tID, 14,  0,  1, 32,  3,  70, 120, GregTech_API.sCassiteriteList, false);
		
		new GT_Worldgen_Ore_Normal     				("Overworld.Ore.Nickel"					, true , tID, 15,  0,  1, 32, 18,  10,  32, GregTech_API.sNickelList, false);
		
		new GT_Worldgen_Ore_SingleBlock_UnderLava	("Overworld.Ore.Sphalerite"				, true , tID,  8,  0,  8,  0,  1,   2,  10, null, false);
		
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Pyrite_Tiny"				, true , tID,  6, -1, 16,  4,  1,   0,  64, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Pyrite_Small"				, true , tID,  6, -1,  8,  8,  2,   0,  64, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Pyrite_Medium"				, true , tID,  6, -1,  4, 12,  4,   0,  64, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Pyrite_Large"				, true , tID,  6, -1,  2, 24,  8,   0,  64, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Pyrite_Huge"				, true , tID,  6, -1,  1, 32, 16,   0,  64, null, false);
		
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Cinnabar_Tiny"				, true , tID,  7, -1, 16,  4,  1,  64, 128, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Cinnabar_Small"			, true , tID,  7, -1,  8,  8,  2,  64, 128, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Cinnabar_Medium"			, true , tID,  7, -1,  4, 12,  4,  64, 128, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Cinnabar_Large"			, false, tID,  7, -1,  2, 24,  8,  64, 128, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Cinnabar_Huge"				, false, tID,  7, -1,  1, 32, 16,  64, 128, null, false);
		
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Sphalerite_Tiny"			, true , tID,  8, -1, 16,  4,  1,  32,  96, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Sphalerite_Small"			, true , tID,  8, -1,  8,  8,  2,  32,  96, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Sphalerite_Medium"			, true , tID,  8, -1,  4, 12,  4,  32,  96, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Sphalerite_Large"			, true , tID,  8, -1,  2, 24,  8,  32,  96, null, false);
		new GT_Worldgen_Ore_Normal     				("Nether.Ore.Sphalerite_Huge"			, true , tID,  8, -1,  1, 32, 16,  32,  96, null, false);
		
		new GT_Worldgenerator();
	}
}