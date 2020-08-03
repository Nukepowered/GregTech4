package gregtechmod.loaders.misc;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Material;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class GT_CoverLoader implements Runnable {
	@Override
	public void run() {
		GregTech_API.sCovers.put(-1, GT_BlockMetaID_Machine.mIcons[51]);
		GregTech_API.sCovers.put(-2, GT_BlockMetaID_Machine.mIcons[95]);
		
		GregTech_API.registerCover(GT_MetaItem_Material .instance.getStack(15, 1), GT_BlockMetaID_Machine.mIcons[ 10]);
		
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(30, 1), GT_BlockMetaID_Machine.mIcons[260]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(31, 1), GT_BlockMetaID_Machine.mIcons[261]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(15, 1), GT_BlockMetaID_Machine.mIcons[262]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 0, 1), GT_BlockMetaID_Machine.mIcons[289]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 1, 1), GT_BlockMetaID_Machine.mIcons[288]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 4, 1), GT_BlockMetaID_Machine.mIcons[ 48]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 5, 1), GT_BlockMetaID_Machine.mIcons[263]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 6, 1), GT_BlockMetaID_Machine.mIcons[280]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 7, 1), GT_BlockMetaID_Machine.mIcons[285]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 8, 1), GT_BlockMetaID_Machine.mIcons[283]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack( 9, 1), GT_BlockMetaID_Machine.mIcons[284]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(10, 1), GT_BlockMetaID_Machine.mIcons[287]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(11, 1), GT_BlockMetaID_Machine.mIcons[286]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(64, 1), GT_BlockMetaID_Machine.mIcons[290]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(65, 1), GT_BlockMetaID_Machine.mIcons[285]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(66, 1), GT_BlockMetaID_Machine.mIcons[285]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(67, 1), GT_BlockMetaID_Machine.mIcons[285]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(82, 1), GT_BlockMetaID_Machine.mIcons[264]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(83, 1), GT_BlockMetaID_Machine.mIcons[265]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(84, 1), GT_BlockMetaID_Machine.mIcons[266]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(85, 1), GT_BlockMetaID_Machine.mIcons[267]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(86, 1), GT_BlockMetaID_Machine.mIcons[268]);
		GregTech_API.registerCover(GT_MetaItem_Component.instance.getStack(87, 1), GT_BlockMetaID_Machine.mIcons[269]);
		
		try {
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(60, 1), Block.planks.getIcon(0, 0));
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(61, 1), Block.planks.getIcon(0, 1));
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(62, 1), Block.planks.getIcon(0, 2));
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(63, 1), Block.planks.getIcon(0, 3));
		} catch (Throwable e) {
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(60, 1), "");
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(61, 1), "");
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(62, 1), "");
			GregTech_API.registerCover(GT_MetaItem_Material.instance.getStack(63, 1), "");
		}
		
		for (byte i = 0; i < 16; i++) {
			if (GregTech_API.gregtechmod.isClientSide()) {
				GregTech_API.registerCover(new ItemStack(Block.carpet /** Carpet */, 1, i), Block.cloth.getIcon(0, i));
			} else {
				GregTech_API.registerCover(new ItemStack(Block.carpet /** Carpet */, 1, i), (Icon)null);
			}
		}
		
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateCoal")			, "coal_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateIron")			, "iron_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGold")			, "gold_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateLapis")			, "lapis_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateDiamond")			, "diamond_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateEmerald")			, "emerald_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateRedstone")		, "redstone_block");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateRefinedIron")		, GregTech_API.IC2_TEXTURE_PATH + "machine/blockMachine");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTin")				, GregTech_API.IC2_TEXTURE_PATH + "blockMetalTin");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateCopper")			, GregTech_API.IC2_TEXTURE_PATH + "blockMetalCopper");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateBronze")			, GregTech_API.IC2_TEXTURE_PATH + "blockMetalBronze");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateUranium")			, GregTech_API.IC2_TEXTURE_PATH + "blockMetalUranium");
		GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAlloyAdvanced")	, GregTech_API.IC2_TEXTURE_PATH + "blockAlloy");
		
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlating", 1)		, GregTech_API.IC2_TEXTURE_PATH + "generator/blockNuclearReactor");
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlatingHeat", 1)	, GregTech_API.IC2_TEXTURE_PATH + "generator/blockNuclearReactor");
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorPlatingExplosive",1), GregTech_API.IC2_TEXTURE_PATH + "generator/blockNuclearReactor");
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVent", 1)			, GT_BlockMetaID_Machine.mIcons[259]);
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentCore", 1)		, GT_BlockMetaID_Machine.mIcons[259]);
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentGold", 1)		, GT_BlockMetaID_Machine.mIcons[111]);
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentSpread",1)		, GT_BlockMetaID_Machine.mIcons[258]);
		GregTech_API.registerCover(GT_ModHandler.getIC2Item("reactorVentDiamond",1)		, GT_BlockMetaID_Machine.mIcons[111]);
	}
}