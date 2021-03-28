package gregtechmod.loaders.postload;

public class GT_UUMRecipeLoader implements Runnable {
	@Override
	public void run() {
//        FIXME: UU M RECIPES
//        GT_Log.log.info("Adding/Removing/Overloading UUM Recipes.");
//        
//        String tMat = "craftingUUMatter";
//        
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemDiamond"		, 1)	, new Object[] {"UUU", "UUU", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemEmerald"		, 2)	, new Object[] {"UUU", "UUU", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemRuby"			, 2)	, new Object[] {" UU", "UUU", "UU ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemSapphire"		, 2)	, new Object[] {"UU ", "UUU", " UU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemGreenSapphire"	, 2)	, new Object[] {" UU", "UUU", " UU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemOlivine"		, 2)	, new Object[] {"UU ", "UUU", "UU ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustIron", 4)				, new Object[] {"U U", " U ", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGold", 2)				, new Object[] {" U ", "UUU", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTin", 10)				, new Object[] {"   ", "U U", "  U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustCopper", 10)			, new Object[] {"  U", "U U", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustZinc", 10)				, new Object[] {"   ", "U U", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustNickel", 10)			, new Object[] {"U  ", "U U", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustLead", 14)				, new Object[] {"UUU", "UUU", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSilver", 14)			, new Object[] {" U ", "UUU", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustPlatinum", 1)			, new Object[] {"  U", "UUU", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTungsten", 6)			, new Object[] {"U  ", "UUU", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGlowstone", 32)		, new Object[] {" U ", "U U", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSmallOsmium", 1)		, new Object[] {"U U", "UUU", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTitanium", 2)			, new Object[] {"UUU", " U ", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustAluminium", 16)		, new Object[] {" U ", " U ", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustRedstone", 24)			, new Object[] {"   ", " U ", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.getFirstOre("dustNikolite", 12)	, new Object[] {"UUU", " U ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.coal, 8)							, new Object[] {"  U", "U  ", "  U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.ender_pearl, 1)					, new Object[] {"UUU", "U U", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.blaze_rod, 4)					, new Object[] {"U U", "UU ", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.waterlily, 32)					, new Object[] {"U U", "UUU", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.leather, 32)						, new Object[] {"U U", " U ", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.string, 32)						, new Object[] {"U U", "   ", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.obsidian, 12)					, new Object[] {"U U", "U U", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.netherrack, 16)					, new Object[] {"  U", " U ", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.water, 1)						, new Object[] {"   ", " U ", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.lava, 1)						, new Object[] {" U ", " U ", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.stone, 16)						, new Object[] {"   ", " U ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.log, 8, 0)						, new Object[] {" U ", "   ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.log, 8, 1)						, new Object[] {"U  ", "   ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.log, 8, 2)						, new Object[] {"  U", "   ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.log, 8, 3)						, new Object[] {"   ", "U  ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.glass, 32)						, new Object[] {" U ", "U U", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.grass, 16)						, new Object[] {"   ", "U  ", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.sandstone, 16)					, new Object[] {"   ", "  U", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.mossy_cobblestone, 16)			, new Object[] {"   ", " U ", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.snow, 16)						, new Object[] {"U U", "   ", "   ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.cactus, 48)						, new Object[] {" U ", "UUU", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.vine, 24)						, new Object[] {"U  ", "U  ", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.dye, 9, 4)						, new Object[] {" U ", " U ", " UU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.feather, 32)						, new Object[] {" U ", " U ", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.gunpowder, 15)					, new Object[] {"UUU", "U  ", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.clay_ball, 48)					, new Object[] {"UU ", "U  ", "UU ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.dye, 32, 3)						, new Object[] {"UU ", "  U", "UU ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.dye, 48, 0)						, new Object[] {" UU", " UU", " U ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.reeds, 48)						, new Object[] {"U U", "U U", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.flint, 32)						, new Object[] {" U ", "UU ", "UU ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Items.bone, 32)						, new Object[] {"U  ", "UU ", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.stonebrick, 48, 3)				, new Object[] {"UU ", "UU ", "U  ", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(new ItemStack(Blocks.mycelium, 24)					, new Object[] {"   ", "U U", "UUU", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("resin", 21)				, new Object[] {"U U", "   ", "U U", true, 'U', tMat});
//        GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("iridiumOre", 1)			, new Object[] {"UUU", " U ", "UUU", true, 'U', tMat});
//        
//        GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, null, null, GT_ModHandler.getIC2Item("matter", 1), GT_ModHandler.getIC2Item("matter", 1), GT_ModHandler.getIC2Item("matter", 1)});
		
	}
}