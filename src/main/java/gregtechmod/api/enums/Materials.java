package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * This List contains every Material I know about, and is used to determine Recipes for the 
 */
public enum Materials {
	/**
	 * This is the Default Material returned in case no Material has been found or a NullPointer has been inserted at a location where it shouldn't happen.
	 * 
	 * Mainly for preventing NullPointer Exceptions and providing Default Values.
	 */
	_NULL				(  -1, GT_ItemTextures.SET_EMPTY			, 0                                     , 255, 255, 255,   0,	"NULL"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, Element._NULL		),
	
	/**
	 * Direct Elements
	 */
	Aluminium			(  19, GT_ItemTextures.SET_DULL				, 1|2  |8      |64|128                  , 128, 200, 240,   0,	"Aluminium"						,    0,       0,          0,          0,       1700, 1700,  true, false,   3,   1,   1, Dyes.dyeLightBlue	, Element.Al		),
	Americium			( 103, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 200, 200, 200,   0,	"Americium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Am		),
	Antimony			(  58, GT_ItemTextures.SET_SHINY			, 1|2  |8                               , 220, 220, 240,   0,	"Antimony"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeLightGray	, Element.Sb		),
	Arsenic				(  39, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255, 255,   0,	"Arsenic"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, Element.As		),
	Barium				(  63, GT_ItemTextures.SET_METALLIC			, 1    |8|16|32                         , 255, 255, 255,   0,	"Barium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, Element.Ba		),
	Beryllium			(   8, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32|64                      , 100, 180, 100,   0,	"Beryllium"						,    0,       0,          0,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeGreen		, Element.Be		),
	Boron				(   9, GT_ItemTextures.SET_DULL				, 1    |8|16|32                         , 250, 250, 250,   0,	"Boron"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, Element.B			),
	Caesium				(  62, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Caesium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes._NULL			, Element.Cs		),
	Calcium				(  26, GT_ItemTextures.SET_METALLIC			, 1      |16|32                         , 255, 245, 245,   0,	"Calcium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyePink		, Element.Ca		),
	Carbon				(  10, GT_ItemTextures.SET_DULL				, 1      |16|32|64|128                  ,  20,  20,  20,   0,	"Carbon"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeBlack		, Element.C			),
	Cadmium				(  55, GT_ItemTextures.SET_SHINY			, 1    |8|16|32                         ,  50,  50,  60,   0,	"Cadmium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGray		, Element.Cd		),
	Cerium				(  65, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Cerium"						,    0,       0,          0,          0,       1068, 1068, true , false,   4,   1,   1, Dyes._NULL			, Element.Ce		),
	Chlorine			(  23, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255, 255,   0,	"Chlorine"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeCyan		, Element.Cl		),
	Chrome				(  30, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 255, 230, 230,   0,	"Chrome"						,    0,       0,          0,          0,       1700, 1700,  true, false,   5,   1,   1, Dyes.dyePink		, Element.Cr		),
	Cobalt				(  33, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      ,  80,  80, 250,   0,	"Cobalt"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, Element.Co		),
	Copper				(  35, GT_ItemTextures.SET_SHINY			, 1|2  |8         |128                  , 255, 100,   0,   0,	"Copper"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, Element.Cu		),
	Deuterium			(   2, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255,   0, 240,	"Deuterium"						,    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeYellow		, Element.D			),
	Dysprosium			(  73, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Dysprosium"					,    0,       0,          0,          0,       1680, 1680, true , false,   4,   1,   1, Dyes._NULL			, Element.Dy		),
	Empty				(   0, GT_ItemTextures.SET_EMPTY			,         16|32                         , 255, 255, 255, 255,	"Empty"							,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			, Element._NULL		),
	Erbium				(  75, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Erbium"						,    0,       0,          0,          0,       1802, 1802, true , false,   4,   1,   1, Dyes._NULL			, Element.Er		),
	Europium			(  70, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Europium"						,    0,       0,          0,          0,       1099, 1099, true , false,   4,   1,   1, Dyes._NULL			, Element.Eu		),
	Fluorine			(  14, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255, 255, 127,	"Fluorine"						,    0,       0,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeGreen		, Element.F			),
	Gadolinium			(  71, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Gadolinium"					,    0,       0,          0,          0,       1585, 1585, true , false,   4,   1,   1, Dyes._NULL			, Element.Gd		),
	Gold				(  86, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 255, 255,  30,   0,	"Gold"							,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeYellow		, Element.Au		),
	Holmium				(  74, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Holmium"						,    0,       0,          0,          0,       1734, 1734, true , false,   4,   1,   1, Dyes._NULL			, Element.Ho		),
	Hydrogen			(   1, GT_ItemTextures.SET_FLUID			,         16|32                         ,   0,   0, 255, 240,	"Hydrogen"						,    1,      15,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeBlue		, Element.H			),
	Helium				(   4, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255,   0, 240,	"Helium"						,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeYellow		, Element.He		),
	Helium_3			(   5, GT_ItemTextures.SET_FLUID			,         16|32                         , 255, 255,   0, 240,	"Helium-3"						,    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeYellow		, Element.He_3		),
	Indium				(  56, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 255, 255, 255,   0,	"Indium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.In		),
	Iridium				(  84, GT_ItemTextures.SET_DULL				, 1|2  |8      |64|128                  , 240, 240, 245,   0,	"Iridium"						,    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeWhite		, Element.Ir		),
	Iron				(  32, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64|128                  , 200, 200, 200,   0,	"Iron"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Fe		),
	Lanthanum			(  64, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Lanthanum"						,    0,       0,          0,          0,       1193, 1193, true , false,   4,   1,   1, Dyes._NULL			, Element.La		),
	Lead				(  89, GT_ItemTextures.SET_DULL				, 1|2  |8      |64|128                  , 140, 100, 140,   0,	"Lead"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		, Element.Pb		),
	Lithium				(   6, GT_ItemTextures.SET_DULL				, 1    |8|16|32                         , 225, 220, 255,   0,	"Lithium"						,    3,      60,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, Element.Li		),
	Lutetium			(  78, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Lutetium"						,    0,       0,          0,          0,       1925, 1925, true , false,   4,   1,   1, Dyes._NULL			, Element.Lu		),
	Magic				(-128, GT_ItemTextures.SET_SHINY			, 1|2|4|8|16|32|64|128                  , 100,   0, 200,   0,	"Magic"							,    5,      32,          0,          0,          0,    0, false, false,   7,   1,   1, Dyes.dyePurple		, Element.Ma		),
	Magnesium			(  18, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 200, 200,   0,	"Magnesium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, Element.Mg		),
	Manganese			(  31, GT_ItemTextures.SET_DULL				, 1|2  |8      |64                      , 250, 250, 250,   0,	"Manganese"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, Element.Mn		),
	Mercury				(  87, GT_ItemTextures.SET_SHINY			,         16|32                         , 255, 220, 220,   0,	"Mercury"						,    5,      32,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Hg		),
	Niobium				(  47, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 255, 255, 255,   0,	"Niobium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes._NULL			, Element.Nb		),
	Molybdenum			(  48, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      , 180, 180, 220,   0,	"Molybdenum"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, Element.Mo		),
	Neodymium			(  67, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Neodymium"						,    0,       0,          0,          0,       1297, 1297, true , false,   4,   1,   1, Dyes._NULL			, Element.Nd		),
	Neutronium			( 129, GT_ItemTextures.SET_DULL				, 1|2  |8      |64|128                  , 250, 250, 250,   0,	"Neutronium"					,    0,       0,          0,          0,          0,    0, false, false,  20,   1,   1, Dyes.dyeWhite		, Element.Nt		),
	Nickel				(  34, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64|128                  , 200, 200, 250,   0,	"Nickel"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, Element.Ni		),
	Nitrogen			(  12, GT_ItemTextures.SET_FLUID			,         16|32                         ,   0, 150, 200, 240,	"Nitrogen"						,    0,       0,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeCyan		, Element.N			),
	Osmium				(  83, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64|128                  ,  50,  50, 255,   0,	"Osmium"						,    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlue		, Element.Os		),
	Oxygen				(  13, GT_ItemTextures.SET_FLUID			,         16|32                         ,   0, 100, 200, 240,	"Oxygen"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		, Element.O			),
	Palladium			(  52, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 128, 128, 128,   0,	"Palladium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.Pd		),
	Phosphor			(  21, GT_ItemTextures.SET_DULL				, 1    |8|16|32                         , 255, 255,   0,   0,	"Phosphor"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, Element.P			),
	Platinum			(  85, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 255, 255, 200,   0,	"Platinum"						,    0,       0,          0,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeOrange		, Element.Pt		),
	Plutonium			( 100, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      , 240,  50,  50,   0,	"Plutonium 244"					,    0,       0,    2000000,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeLime		, Element.Pu		),
	Plutonium241		( 101, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      , 250,  70,  70,   0,	"Plutonium 241"					,    0,       0,    2000000,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeLime		, Element.Pu_241	),
	Potassium			(  25, GT_ItemTextures.SET_METALLIC			, 1      |16|32                         , 250, 250, 250,   0,	"Potassium"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, Element.K			),
	Praseodymium		(  66, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Praseodymium"					,    0,       0,          0,          0,       1208, 1208, true , false,   4,   1,   1, Dyes._NULL			, Element.Pr		),
	Promethium			(  68, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Promethium"					,    0,       0,          0,          0,       1315, 1315, true , false,   4,   1,   1, Dyes._NULL			, Element.Pm		),
	Rubidium			(  43, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 240,  30,  30,   0,	"Rubidium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeRed			, Element.Rb		),
	Samarium			(  69, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Samarium"						,    0,       0,          0,          0,       1345, 1345, true , false,   4,   1,   1, Dyes._NULL			, Element.Sm		),
	Scandium			(  27, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Scandium"						,    0,       0,          0,          0,       1814, 1814, true , false,   2,   1,   1, Dyes.dyeYellow		, Element.Sc		),
	Silicon				(  20, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         ,  60,  60,  80,   0,	"Silicon"						,    0,       0,          0,          0,       1500, 1500, true , false,   1,   1,   1, Dyes.dyeBlack		, Element.Si		),
	Silver				(  54, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 220, 220, 255,   0,	"Silver"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Ag		),
	Sodium				(  17, GT_ItemTextures.SET_METALLIC			, 1      |16|32                         ,   0,   0, 150,   0,	"Sodium"						,    3,      30,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, Element.Na		),
	Strontium			(  44, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 200, 200, 200,   0,	"Strontium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, Element.Sr		),
	Sulfur				(  22, GT_ItemTextures.SET_DULL				, 1    |8|16|32                         , 200, 200,   0,   0,	"Sulfur"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, Element.S			),
	Tantalum			(  80, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 255, 255, 255,   0,	"Tantalum"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes._NULL			, Element.Ta		),
	Tellurium			(  59, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 255, 255, 255,   0,	"Tellurium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.Te		),
	Terbium				(  72, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Terbium"						,    0,       0,          0,          0,       1629, 1629, true , false,   4,   1,   1, Dyes._NULL			, Element.Tb		),
	Thorium				(  96, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      ,   0,  30,   0,   0,	"Thorium"						,    0,       0,     500000,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeBlack		, Element.Th		),
	Thulium				(  76, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Thulium"						,    0,       0,          0,          0,       1818, 1818, true , false,   4,   1,   1, Dyes._NULL			, Element.Tm		),
	Tin					(  57, GT_ItemTextures.SET_DULL				, 1|2  |8         |128                  , 220, 220, 220,   0,	"Tin"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, Element.Sn		),
	Titanium			(  28, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64|128                  , 220, 160, 240,   0,	"Titanium"						,    0,       0,          0,          0,       1500, 1500, true , false,   5,   1,   1, Dyes.dyePurple		, Element.Ti		),
	Tritium				(   3, GT_ItemTextures.SET_METALLIC			,         16|32                         , 255,   0,   0, 240,	"Tritium"						,    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeRed			, Element.T			),
	Tungsten			(  81, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64|128                  ,  50,  50,  50,   0,	"Tungsten"						,    0,       0,          0,          0,       2500, 2500, true , false,   4,   1,   1, Dyes.dyeBlack		, Element.W			),
	Uranium				(  98, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      ,  50, 240,  50,   0,	"Uranium 238"					,    0,       0,    1000000,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGreen		, Element.U			),
	Uranium235			(  97, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      ,  70, 250,  70,   0,	"Uranium 235"					,    0,       0,    1000000,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGreen		, Element.U_235		),
	Vanadium			(  29, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               ,  50,  50,  50,   0,	"Vanadium"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeBlack		, Element.V			),
	Ytterbium			(  77, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Ytterbium"						,    0,       0,          0,          0,       1097, 1097, true , false,   4,   1,   1, Dyes._NULL			, Element.Yb		),
	Yttrium				(  45, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16|32                         , 255, 255, 255,   0,	"Yttrium"						,    0,       0,          0,          0,       1799, 1799, true , false,   4,   1,   1, Dyes._NULL			, Element.Y			),
	Zinc				(  36, GT_ItemTextures.SET_METALLIC			, 1|2  |8                               , 250, 250, 250,   0,	"Zinc"							,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, Element.Zn		),
	
	/**
	 * The "Random Material" ones.
	 */
	Organic				(  -1, GT_ItemTextures.SET_LEAF				, false),
	Crystal				(  -1, GT_ItemTextures.SET_SHINY			, false),
	Quartz				(  -1, GT_ItemTextures.SET_QUARTZ			, false),
	Metal				(  -1, GT_ItemTextures.SET_METALLIC			, false),
	Cobblestone			(  -1, GT_ItemTextures.SET_DULL				, false),
	
	/**
	 * Unknown Material Components. Dead End Section.
	 */
	Adamantium			( 319, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 255, 255, 255,   0,	"Adamantium"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Adamite				(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Adamite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Adluorite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Adluorite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Agate				(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Agate"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Alduorite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Alduorite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Amber				( 514, GT_ItemTextures.SET_RUBY				, 1  |4|8                               , 255, 128,   0, 127,	"Amber"							,    5,       3,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeOrange		),
	Ammonium			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Ammonium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Amordrine			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Amordrine"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Andesite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Andesite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Angmallen			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Angmallen"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Ardite				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255,   0,   0,   0,	"Ardite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Aredrite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255,   0,   0,   0,	"Aredrite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Atlarus				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Atlarus"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Bitumen				(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Bitumen"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Black				(  -1, GT_ItemTextures.SET_NONE				, 0                                     ,   0,   0,   0,   0,	"Black"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlack		),
	Blizz				( 851, GT_ItemTextures.SET_SHINY			, 1                                     , 220, 233, 255,   0,	"Blizz"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Blueschist			( 852, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Blueschist"					,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeLightBlue	),
	Bluestone			( 813, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Bluestone"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		),
	Bloodstone			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Bloodstone"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	Blutonium			(  -1, GT_ItemTextures.SET_SHINY			, 1|2  |8                               ,   0,   0, 255,   0,	"Blutonium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		),
	Carmot				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Carmot"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Celenegil			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Celenegil"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	CertusQuartz		( 516, GT_ItemTextures.SET_QUARTZ			, 1  |4|8      |64                      , 210, 210, 230,   0,	"Certus Quartz"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	),
	Ceruclase			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Ceruclase"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Citrine				(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Citrine"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	CobaltHexahydrate	( 853, GT_ItemTextures.SET_METALLIC			, 1      |16                            ,  80,  80, 250,   0,	"Cobalt Hexahydrate"			,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		),
	ConstructionFoam	( 854, GT_ItemTextures.SET_DULL				, 1      |16                            , 128, 128, 128,   0,	"Construction Foam"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Chalk				( 856, GT_ItemTextures.SET_FINE				, 1                                     , 250, 250, 250,   0,	"Chalk"							,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeWhite		),
	Chert				( 857, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Chert"							,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Chimerite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Chimerite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Coral				(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 128, 255,   0,	"Coral"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	CrudeOil			( 858, GT_ItemTextures.SET_DULL				, 1                                     ,  10,  10,  10,   0,	"Crude Oil"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Chrysocolla			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Chrysocolla"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	CrystalFlux			( 517, GT_ItemTextures.SET_QUARTZ			, 1  |4                                 , 100,  50, 100,   0,	"Flux Crystal"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Cyanite				(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Cyanite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeCyan		),
	Dacite				( 859, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Dacite"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeLightGray	),
	DarkIron			( 342, GT_ItemTextures.SET_DULL				, 1|2  |8      |64                      ,  55,  40,  60,   0,	"Dark Iron"						,    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyePurple		),
	DarkStone			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Dark Stone"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlack		),
	Demonite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Demonite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	Desh				( 884, GT_ItemTextures.SET_DULL				, 1|2  |8                               ,  40,  40,  40,   0,	"Desh"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Desichalkos			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Desichalkos"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Dilithium			( 515, GT_ItemTextures.SET_DIAMOND			, 1  |4|8|16                            , 255, 250, 250, 127,	"Dilithium"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Draconic			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Draconic"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Duranium			( 328, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 255, 255, 255,   0,	"Duranium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Eclogite			( 860, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Eclogite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	ElectrumFlux		( 320, GT_ItemTextures.SET_SHINY			, 1|2          |64                      , 255, 255, 120,   0,	"Fluxed Electrum"				,    0,       0,          0,          0,       3000, 3000, true , false,   1,   1,   1, Dyes.dyeYellow		),
	Emery				( 861, GT_ItemTextures.SET_DULL				, 1    |8                               , 255, 255, 255,   0,	"Emery"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Enderium			( 321, GT_ItemTextures.SET_DULL				, 1|2          |64                      ,  89, 145, 135,   0,	"Enderium"						,    0,       0,          0,          0,       3000, 3000, true , false,   1,   1,   1, Dyes.dyeGreen		),
	Energized			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Energized"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Epidote				( 862, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Epidote"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Eximite				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Eximite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	FieryBlood			(  -1, GT_ItemTextures.SET_NONE				, 1|2          |64                      , 255, 255, 255,   0,	"Fiery Blood"					,    5,    2048,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Firestone			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Firestone"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		),
	Fluorite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Fluorite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	FoolsRuby			( 512, GT_ItemTextures.SET_RUBY				, 1  |4|8                               , 255, 100, 100, 127,	"Ruby"							,    0,       0,          0,          0,          0,    0, false,  true,   3,   1,   1, Dyes.dyeRed			),
	Force				( 521, GT_ItemTextures.SET_DIAMOND			, 1|2|4|8      |64|128                  , 255, 255,   0,   0,	"Force"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Forcicium			( 518, GT_ItemTextures.SET_DIAMOND			, 1  |4|8|16                            ,  50,  50,  70,   0,	"Forcicium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Forcillium			( 519, GT_ItemTextures.SET_DIAMOND			, 1  |4|8|16                            ,  50,  50,  70,   0,	"Forcillium"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Gabbro				( 863, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Gabbro"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Glowstone			( 811, GT_ItemTextures.SET_SHINY			, 1      |16                            , 255, 255,   0,   0,	"Glowstone"						,    0,       0,      25000,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Gneiss				( 864, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Gneiss"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Graphite			( 865, GT_ItemTextures.SET_DULL				, 1    |8      |64|128                  , 128, 128, 128,   0,	"Graphite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGray		),
	Greenschist			( 866, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Green Schist"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Greenstone			( 867, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Greenstone"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Greywacke			( 868, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Greywacke"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Haderoth			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Haderoth"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Hematite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Hematite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Hepatizon			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Hepatizon"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	HSLA				( 322, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 128, 128, 128,   0,	"HSLA Steel"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Ignatius			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Ignatius"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Infernal			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infernal"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Infuscolium			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Infuscolium"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	InfusedGold			( 323, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64|128                  , 255, 200,  60,   0,	"Infused Gold"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	InfusedAir			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Air"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	InfusedFire			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Fire"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	InfusedEarth		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Earth"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	InfusedWater		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Water"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		),
	InfusedVis			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Vis"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		),
	InfusedDull			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Dull"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	),
	InfusedEntropy		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Entropy"				,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	InfusedOrder		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infused Order"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Inolashite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Inolashite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Invisium			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Invisium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Jade				( 537, GT_ItemTextures.SET_SHINY			, 1    |8                               ,   0, 100,   0,   0,	"Jade"							,    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeGreen		),
	Jasper				( 511, GT_ItemTextures.SET_EMERALD			, 1  |4|8                               , 200,  80,  80, 100,	"Jasper"						,    0,       0,          0,          0,          0,    0, false,  true,   3,   1,   1, Dyes.dyeRed			),
	Kalendrite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Kalendrite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Komatiite			( 869, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Komatiite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Lava				( 700, GT_ItemTextures.SET_FLUID			,         16                            , 255,  64,   0,   0,	"Lava"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Lemurite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Lemurite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Limestone			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Limestone"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Lodestone			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Lodestone"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Luminite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 250, 250, 250,   0,	"Luminite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Magma				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255,  64,   0,   0,	"Magma"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Mawsitsit			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Mawsitsit"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Mercassium			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Mercassium"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	MeteoricIron		( 340, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      , 100,  50,  80,   0,	"Meteoric Iron"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	MeteoricSteel		( 341, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      ,  50,  25,  40,   0,	"Meteoric Steel"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Meteorite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               ,  80,  35,  60,   0,	"Meteorite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Meutoite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Meutoite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Migmatite			( 872, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Migmatite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Mimichite			(  -1, GT_ItemTextures.SET_GEM_VERTICAL		, 1  |4|8                               , 255, 255, 255,   0,	"Mimichite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Monazite			( 520, GT_ItemTextures.SET_DIAMOND			, 1  |4|8                               ,  50,  70,  50,   0,	"Monazite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Moonstone			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Moonstone"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Naquadah			( 324, GT_ItemTextures.SET_METALLIC			, 1|2  |8|16   |64                      ,  50,  50,  50,   0,	"Naquadah"						,    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlack		),
	NaquadahAlloy		( 325, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  ,  40,  40,  40,   0,	"Naquadah Alloy"				,    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlack		),
	Nether				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Nether"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	NetherBrick			( 814, GT_ItemTextures.SET_DULL				, 1                                     , 100,   0,   0,   0,	"Nether Brick"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	NetherQuartz		( 522, GT_ItemTextures.SET_QUARTZ			, 1  |4|8                               , 230, 210, 210,   0,	"Nether Quartz"					,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		),
	NetherStar			( 506, GT_ItemTextures.SET_NETHERSTAR		, 1  |4                                 , 255, 255, 255,   0,	"Nether Star"					,    5,   50000,          0,          0,          0,    0, false, false,  15,   1,   1, Dyes.dyeWhite		),
	Nikolite			( 812, GT_ItemTextures.SET_SHINY			, 1    |8                               ,  60, 180, 200,   0,	"Nikolite"						,    0,       0,       5000,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeCyan		),
	ObsidianFlux		(  -1, GT_ItemTextures.SET_DULL				, 1|2                                   ,  80,  50, 100,   0,	"Fluxed Obsidian"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Oilsands			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Oilsands"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Onyx				(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Onyx"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Orichalcum			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Orichalcum"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Osmonium			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Osmonium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		),
	Oureclase			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Oureclase"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Painite				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Painite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Peanutwood			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Peanut Wood"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Petroleum			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Petroleum"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Pewter				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Pewter"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Pitchblende			( 873, GT_ItemTextures.SET_DULL				, 1    |8                               , 200, 210,   0,   0,	"Pitchblende"					,    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeYellow		),
	Phoenixite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Phoenixite"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Potash				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Potash"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Prometheum			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Prometheum"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Quartzite			( 523, GT_ItemTextures.SET_QUARTZ			, 1  |4                                 , 210, 230, 210,   0,	"Quartzite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Quicklime			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Quicklime"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Randomite			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Randomite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	RefinedGlowstone	( 326, GT_ItemTextures.SET_METALLIC			, 1|2                                   , 255, 255,   0,   0,	"Refined Glowstone"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	RefinedObsidian		( 327, GT_ItemTextures.SET_METALLIC			, 1|2                                   ,  80,  50, 100,   0,	"Refined Obsidian"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Rhyolite			( 875, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Rhyolite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Rubracium			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Rubracium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	RyuDragonRyder		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Ryu Dragon Ryder"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Sand				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Sand"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Sanguinite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Sanguinite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Siltstone			( 876, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Siltstone"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Soapstone			( 877, GT_ItemTextures.SET_DULL				, 1                                     ,  95, 145,  95,   0,	"Soapstone"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Spinel				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Spinel"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Starconium			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Starconium"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Sugilite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Sugilite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Sunstone			(  -1, GT_ItemTextures.SET_NONE				, 1    |8                               , 255, 255, 255,   0,	"Sunstone"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Tar					(  -1, GT_ItemTextures.SET_NONE				, 0                                     ,  10,  10,  10,   0,	"Tar"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Tartarite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Tartarite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Tapazite			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Tapazite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Thyrium				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Thyrium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Tourmaline			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Tourmaline"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Tritanium			( 329, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 255, 255, 255,   0,	"Tritanium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Turquoise			(  -1, GT_ItemTextures.SET_NONE				, 1                                     , 255, 255, 255,   0,	"Turquoise"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	UUMatter			( 703, GT_ItemTextures.SET_FLUID			,         16                            , 128,   0, 196,   0,	"UUMatter"						,    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyePink		),
	Void				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255, 200,	"Void"							,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			),
	Voidstone			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255, 200,	"Voidstone"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			),
	Vulcanite			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Vulcanite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Vyroxeres			(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8      |64                      , 255, 255, 255,   0,	"Vyroxeres"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Wimalite			(  -1, GT_ItemTextures.SET_NONE				,       8                               , 255, 255, 255,   0,	"Wimalite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Yellorite			(  -1, GT_ItemTextures.SET_NONE				,       8                               , 255, 255, 255,   0,	"Yellorite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Yellorium			(  -1, GT_ItemTextures.SET_NONE				, 1|2                                   , 255, 255, 255,   0,	"Yellorium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Zectium				(  -1, GT_ItemTextures.SET_NONE				, 1|2  |8                               , 255, 255, 255,   0,	"Zectium"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlack		),
	
	/**
	 * Not possible to determine exact Components
	 */
	Advanced			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Advanced"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Basic				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Basic"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Antimatter			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Antimatter"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	BioFuel				( 705, GT_ItemTextures.SET_FLUID			,         16                            , 255, 128,   0,   0,	"Biofuel"						,    0,       6,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Biomass				( 704, GT_ItemTextures.SET_FLUID			,         16                            ,   0, 255,   0,   0,	"Biomass"						,    3,       8,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Chocolate			( 886, GT_ItemTextures.SET_FINE				, 1                                     , 190,  95,   0,   0,	"Chocolate"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Cluster				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255, 127,	"Cluster"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	CoalFuel			( 710, GT_ItemTextures.SET_FLUID			,         16                            ,  50,  50,  70,   0,	"Coalfuel"						,    0,      16,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Coffee				( 887, GT_ItemTextures.SET_FINE				, 1                                     , 150,  75,   0,   0,	"Coffee"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Creosote			( 712, GT_ItemTextures.SET_FLUID			,         16                            , 128,  64,   0,   0,	"Creosote"						,    3,       3,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	Data				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Data"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Elite				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Elite"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Ethanol				( 706, GT_ItemTextures.SET_FLUID			,         16                            , 255, 128,   0,   0,	"Ethanol"						,    0,     128,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Fuel				( 708, GT_ItemTextures.SET_FLUID			,         16                            , 255, 255,   0,   0,	"Diesel"						,    0,     128,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Good				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Good"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Gunpowder			( 800, GT_ItemTextures.SET_DULL				, 1                                     , 128, 128, 128,   0,	"Gunpowder"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Infinite			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Infinite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	LimePure			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Pure Lime"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		),
	Master				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Master"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Meat				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Meat"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	MeatRaw				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Raw Meat"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	MeatCooked			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Cooked Meat"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	Milk				( 885, GT_ItemTextures.SET_FINE				, 1      |16                            , 254, 254, 254,   0,	"Milk"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Mud					(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Mud"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	Oil					( 707, GT_ItemTextures.SET_FLUID			,         16                            ,   0,   0,   0,   0,	"Oil"							,    3,      16,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Paper				( 879, GT_ItemTextures.SET_PAPER			, 1                                     , 250, 250, 250,   0,	"Paper"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Peat				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Peat"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	Primitive			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Primitive"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Quantum				(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Quantum"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Red					(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255,   0,   0,   0,	"Red"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Reinforced			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Reinforced"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	SeedOil				( 713, GT_ItemTextures.SET_FLUID			,         16                            , 196, 255,   0,   0,	"Seed Oil"						,    3,       2,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		),
	Stone				( 299, GT_ItemTextures.SET_ROUGH			, 1            |64|128                  , 205, 205, 205,   0,	"Stone"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	TNT					(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"TNT"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Ultimate			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255,   0,	"Ultimate"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Unstable			(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255, 127,	"Unstable"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Unstableingot		(  -1, GT_ItemTextures.SET_NONE				, 0                                     , 255, 255, 255, 127,	"Unstable"						,    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Wheat				( 881, GT_ItemTextures.SET_POWDER			, 1                                     , 255, 255, 196,   0,	"Wheat"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Wood				( 809, GT_ItemTextures.SET_WOOD				, 1            |64|128                  , 200, 100,   0,   0,	"Wood"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	
	/**
	 * TODO: This
	 */
	AluminiumBrass		(  -1, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 255, 255, 255,   0,	"Aluminium Brass"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Osmiridium			( 317, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 100, 100, 255,   0,	"Osmiridium"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	),
	Sunnarium			( 318, GT_ItemTextures.SET_SHINY			, 1|2                                   , 255, 255,   0,   0,	"Sunnarium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Endstone			( 808, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Endstone"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeYellow		),
	Netherrack			( 807, GT_ItemTextures.SET_DULL				, 1                                     , 200,   0,   0,   0,	"Netherrack"					,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeRed			),
	SoulSand			(  -1, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Soulsand"						,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeBrown		),
	
	/**
	 * First Degree Compounds
	 */
	Almandine			( 820, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 255,   0,   0,   0,	"Almandine"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Iron, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Andradite			( 821, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 150, 120,   0,   0,	"Andradite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Iron, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Asbestos			( 946, GT_ItemTextures.SET_DULL				, 1    |8                               , 230, 230, 230,   0,	"Asbestos"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 9))), // Mg3Si2O5(OH)4
	Ash					( 815, GT_ItemTextures.SET_DULL				, 1                                     , 150, 150, 150,   0,	"Ashes"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 2, Arrays.asList(new MaterialStack(Carbon, 1))),
	BandedIron			( 917, GT_ItemTextures.SET_DULL				, 1    |8                               , 145,  90,  90,   0,	"Banded Iron"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		, 1, Arrays.asList(new MaterialStack(Iron, 2), new MaterialStack(Oxygen, 3))),
	BatteryAlloy		( 315, GT_ItemTextures.SET_DULL				, 1|2                                   , 156, 124, 160,   0,	"Battery Alloy"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Lead, 4), new MaterialStack(Antimony, 1))),
	Bauxite				( 822, GT_ItemTextures.SET_DULL				, 1    |8                               , 200, 100,   0,   0,	"Bauxite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBrown		, 1, Arrays.asList(new MaterialStack(Titanium, 1), new MaterialStack(Aluminium, 16), new MaterialStack(Hydrogen, 10), new MaterialStack(Oxygen, 12))),
	BlueTopaz			( 513, GT_ItemTextures.SET_GEM_HORIZONTAL	, 1  |4|8      |64                      ,   0,   0, 255, 127,	"Blue Topaz"					,    0,       0,          0,          0,          0,    0, false,  true,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6))),
	Bone				( 806, GT_ItemTextures.SET_DULL				, 0                                     , 250, 250, 250,   0,	"Bone"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 0, Arrays.asList(new MaterialStack(Calcium, 1))),
	Brass				( 301, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 255, 180,   0,   0,	"Brass"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3))),
	Bronze				( 300, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 255, 128,   0,   0,	"Bronze"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Copper, 3))),
	BrownLimonite		( 930, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 200, 100,   0,   0,	"Brown Limonite"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2))), // FeO(OH)
	Calcite				( 823, GT_ItemTextures.SET_DULL				, 1                                     , 250, 230, 220,   0,	"Calcite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3))),
	Cassiterite			( 824, GT_ItemTextures.SET_METALLIC			,       8                               , 220, 220, 220,   0,	"Cassiterite"					,    0,       0,          0,          0,          0,    0, false, false,   4,   3,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2))),
	CassiteriteSand		( 937, GT_ItemTextures.SET_SAND				,       8                               , 220, 220, 220,   0,	"Cassiterite Sand"				,    0,       0,          0,          0,          0,    0, false, false,   4,   3,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2))),
	Celestine			( 913, GT_ItemTextures.SET_DULL				, 1    |8                               , 200, 205, 240,   0,	"Celestine"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 1, Arrays.asList(new MaterialStack(Strontium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	Chalcopyrite		( 855, GT_ItemTextures.SET_DULL				, 1    |8                               , 160, 120,  40,   0,	"Chalcopyrite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2))),
	Charcoal			( 536, GT_ItemTextures.SET_FINE				, 1  |4                                 , 100,  70,  70,   0,	"Charcoal"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Chromite			( 825, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  35,  20,  15,   0,	"Chromite"						,    0,       0,          0,          0,       1700, 1700,  true, false,   6,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Chrome, 2), new MaterialStack(Oxygen, 4))),
	Cinnabar			( 826, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 150,   0,   0,   0,	"Cinnabar"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Mercury, 1), new MaterialStack(Sulfur, 1))),
	Clay				( 805, GT_ItemTextures.SET_DULL				, 1                                     , 255, 255, 255,   0,	"Clay"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightBlue	, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2))),
	Coal				( 535, GT_ItemTextures.SET_ROUGH			, 1  |4|8	                            ,  70,  70,  70,   0,	"Coal"							,    0,       0,          0,          0,          0,    0, false, false,   2,   2,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Cobaltite			( 827, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  80,  80, 250,   0,	"Cobaltite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 1))),
	Cooperite			( 828, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 255, 255, 200,   0,	"Cooperite"						,    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Platinum, 3), new MaterialStack(Nickel, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Palladium, 1))),
	Cupronickel			( 310, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 227, 150, 128,   0,	"Cupronickel"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Nickel, 1))),
	DarkAsh				( 816, GT_ItemTextures.SET_DULL				, 1                                     ,  50,  50,  50,   0,	"Dark Ashes"					,    0,       0,          0,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	DeepIron			( 829, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      , 150, 140, 140,   0,	"Deep Iron"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	Diamond				( 500, GT_ItemTextures.SET_DIAMOND			, 1  |4|8      |64|128                  , 200, 255, 255, 127,	"Diamond"						,    0,       0,          0,          0,          0,    0, false,  true,   5, 128,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Electrum			( 303, GT_ItemTextures.SET_SHINY			, 1|2          |64|128                  , 255, 255, 100,   0,	"Electrum"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1))),
	Emerald				( 501, GT_ItemTextures.SET_EMERALD			, 1  |4|8      |64                      ,  80, 255,  80, 127,	"Emerald"						,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Beryllium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 6), new MaterialStack(Oxygen, 18))),
	Galena				( 830, GT_ItemTextures.SET_DULL				, 1    |8                               , 100,  60, 100,   0,	"Galena"						,    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Lead, 3), new MaterialStack(Silver, 3), new MaterialStack(Sulfur, 2))),
	Garnierite			( 906, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  50, 200,  70,   0,	"Garnierite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	, 1, Arrays.asList(new MaterialStack(Nickel, 1), new MaterialStack(Oxygen, 1))), 
	Glyceryl			( 714, GT_ItemTextures.SET_FLUID			,         16                            ,   0, 150, 150,   0,	"Glyceryl"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Nitrogen, 3), new MaterialStack(Oxygen, 9))),
	GreenSapphire		( 504, GT_ItemTextures.SET_GEM_HORIZONTAL	, 1  |4|8      |64                      , 100, 255, 130, 127,	"Green Sapphire"				,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3))),
	Grossular			( 831, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 200, 100,   0,   0,	"Grossular"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Ice					( 702, GT_ItemTextures.SET_SHINY			, 1|      16                            , 200, 200, 255,   0,	"Ice"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1))),
	Ilmenite			( 918, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  70,  55,  50,   0,	"Ilmenite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Titanium, 1), new MaterialStack(Oxygen, 3))),
	Invar				( 302, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 180, 180, 120,   0,	"Invar"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Iron, 2), new MaterialStack(Nickel, 1))),
	Kanthal				( 312, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 194, 210, 223,   0,	"Kanthal"						,    0,       0,          0,          0,       2500, 2500,  true, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Chrome, 1))),
	Lazurite			( 524, GT_ItemTextures.SET_LAPIS			, 1  |4|8                               , 100, 120, 255,   0,	"Lazurite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Aluminium, 6), new MaterialStack(Silicon, 6), new MaterialStack(Calcium, 8), new MaterialStack(Sodium, 8))),
	LiveRoot			( 832, GT_ItemTextures.SET_WOOD				, 1                                     , 220, 200,   0,   0,	"Liveroot"						,    5,      16,          0,          0,          0,    0, false, false,   2,   4,   3, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Wood, 3), new MaterialStack(Magic, 1))),
	Magnalium			( 313, GT_ItemTextures.SET_DULL				, 1|2          |64|128                  , 200, 190, 255,   0,	"Magnalium"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2))),
	Magnesite			( 908, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 250, 250, 180,   0,	"Magnesite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3))),
	Magnetite			( 870, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  30,  30,  30,   0,	"Magnetite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Iron, 3), new MaterialStack(Oxygen, 4))),
	Methane				( 715, GT_ItemTextures.SET_FLUID			,         16                            , 255, 255, 255,   0,	"Methane"						,    1,      45,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeMagenta		, 1, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4))),
	Molybdenite			( 942, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  25,  25,  25,   0,	"Molybdenite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Molybdenum, 1), new MaterialStack(Sulfur, 2))), // MoS2 (also source of Re)
	Nichrome			( 311, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 205, 206, 246,   0,	"Nichrome"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1))),
	NitroCarbon			( 716, GT_ItemTextures.SET_FLUID			,         16                            ,   0,  75, 100,   0,	"Nitro-Carbon"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Nitrogen, 1), new MaterialStack(Carbon, 1))),
	NitrogenDioxide		( 717, GT_ItemTextures.SET_FLUID			,         16                            , 100, 175, 255,   0,	"Nitrogen Dioxide"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 2))),
	Obsidian			( 804, GT_ItemTextures.SET_DULL				, 1                                     ,  80,  50, 100,   0,	"Obsidian"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 8))),
	Phosphate			( 833, GT_ItemTextures.SET_DULL				, 1    |8|16                            , 255, 255,   0,   0,	"Phosphate"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4))),
	PigIron				( 307, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      , 200, 180, 180,   0,	"Pig Iron"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	Plastic				( 874, GT_ItemTextures.SET_DULL				, 1|2          |64|128                  , 200, 200, 200,   0,	"Plastic"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 0, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2))),
	Powellite			( 883, GT_ItemTextures.SET_DULL				, 1    |8                               , 255, 255,   0,   0,	"Powellite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4))),
	Pumice				( 926, GT_ItemTextures.SET_DULL				, 1    |8                               , 230, 185, 185,   0,	"Pumice"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 2, Arrays.asList(new MaterialStack(Stone, 1))),
	Pyrite				( 834, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 150, 120,  40,   0,	"Pyrite"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2))),
	Pyrolusite			( 943, GT_ItemTextures.SET_DULL				, 1    |8                               , 150, 150, 170,   0,	"Pyrolusite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 1, Arrays.asList(new MaterialStack(Manganese, 1), new MaterialStack(Oxygen, 2))),
	Pyrope				( 835, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 120,  50, 100,   0,	"Pyrope"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	RockSalt			( 944, GT_ItemTextures.SET_FINE				, 1    |8                               , 240, 200, 200,   0,	"Rock Salt"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Chlorine, 1))),
	Rubber				( 880, GT_ItemTextures.SET_SHINY			, 1|2          |64|128                  ,   0,   0,   0,   0,	"Rubber"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 0, Arrays.asList(new MaterialStack(Carbon, 5), new MaterialStack(Hydrogen, 8))),
	Ruby				( 502, GT_ItemTextures.SET_RUBY				, 1  |4|8      |64                      , 255, 100, 100, 127,	"Ruby"							,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Chrome, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3))),
	Salt				( 817, GT_ItemTextures.SET_FINE				, 1    |8                               , 250, 250, 250,   0,	"Salt"							,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Chlorine, 1))),
	Saltpeter			( 836, GT_ItemTextures.SET_FINE				, 1    |8                               , 230, 230, 230,   0,	"Saltpeter"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3))),
	Sapphire			( 503, GT_ItemTextures.SET_GEM_VERTICAL		, 1  |4|8      |64                      , 100, 100, 200, 127,	"Sapphire"						,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3))),
	Scheelite			( 910, GT_ItemTextures.SET_DULL				, 1    |8                               , 200, 140,  20,   0,	"Scheelite"						,    0,       0,          0,          0,       2500, 2500, false, false,   4,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Calcium, 2), new MaterialStack(Oxygen, 4))),
	SiliconDioxide		( 837, GT_ItemTextures.SET_QUARTZ			, 1      |16                            , 255, 255, 255,   0,	"Silicon Dioxide"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 1, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2))),
	Sodalite			( 525, GT_ItemTextures.SET_LAPIS			, 1  |4|8                               ,  20,  20, 255,   0,	"Sodalite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Sodium, 4), new MaterialStack(Chlorine, 1))),
	SodiumPersulfate	( 718, GT_ItemTextures.SET_FLUID			,         16                            , 255, 255, 255,   0,	"Sodium Persulfate"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	SodiumSulfide		( 719, GT_ItemTextures.SET_FLUID			,         16                            , 255, 255, 255,   0,	"Sodium Sulfide"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Sulfur, 1))),
	SolderingAlloy		( 314, GT_ItemTextures.SET_DULL				, 1|2                                   , 220, 220, 230,   0,	"Soldering Alloy"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Tin, 9), new MaterialStack(Antimony, 1))),
	Spessartine			( 838, GT_ItemTextures.SET_DULL				, 1    |8                               , 255, 100, 100,   0,	"Spessartine"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Manganese, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Sphalerite			( 839, GT_ItemTextures.SET_DULL				, 1    |8                               , 255, 255, 255,   0,	"Sphalerite"					,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 1))),
	StainlessSteel		( 306, GT_ItemTextures.SET_SHINY			, 1|2          |64|128                  , 200, 200, 220,   0,	"Stainless Steel"				,    0,       0,          0,          0,       1700, 1700,  true, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Iron, 6), new MaterialStack(Chrome, 1), new MaterialStack(Manganese, 1), new MaterialStack(Nickel, 1))),
	Steel				( 305, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 128, 128, 128,   0,	"Steel"							,    0,       0,          0,          0,       1000, 1000,  true, false,   4,  51,  50, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Iron, 50), new MaterialStack(Carbon, 1))),
	Stibnite			( 945, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  70,  70,  70,   0,	"Stibnite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Antimony, 2), new MaterialStack(Sulfur, 3))),
	SulfuricAcid		( 720, GT_ItemTextures.SET_FLUID			,         16                            , 255, 128,   0,   0,	"Sulfuric Acid"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	Tanzanite			( 508, GT_ItemTextures.SET_GEM_VERTICAL		, 1  |4|8      |64                      ,  64,   0, 200, 127,	"Tanzanite"						,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Calcium, 2), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 13))),
	Tetrahedrite		( 840, GT_ItemTextures.SET_DULL				, 1    |8                               , 200,  32,   0,   0,	"Tetrahedrite"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Copper, 3), new MaterialStack(Antimony, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Iron, 1))), //Cu3SbS3 + x(Fe,Zn)6Sb2S9
	Topaz				( 507, GT_ItemTextures.SET_GEM_HORIZONTAL	, 1  |4|8      |64                      , 255, 128,   0, 127,	"Topaz"							,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6))),
	Tungstate			( 841, GT_ItemTextures.SET_DULL				, 1    |8                               ,  55,  50,  35,   0,	"Tungstate"						,    0,       0,          0,          0,       2500, 2500,  true, false,   4,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Lithium, 2), new MaterialStack(Oxygen, 4))),
	Ultimet				( 344, GT_ItemTextures.SET_SHINY			, 1|2          |64|128                  , 180, 180, 230,   0,	"Ultimet"						,    0,       0,          0,          0,       2700, 2700,  true, false,   1,   1,   1, Dyes.dyeLightBlue	, 1, Arrays.asList(new MaterialStack(Cobalt, 5), new MaterialStack(Chrome, 2), new MaterialStack(Nickel, 1), new MaterialStack(Molybdenum, 1))), // 54% Cobalt, 26% Chromium, 9% Nickel, 5% Molybdenum, 3% Iron, 2% Tungsten, 0.8% Manganese, 0.3% Silicon, 0.08% Nitrogen and 0.06% Carbon
	Uraninite			( 922, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  35,  35,  35,   0,	"Uraninite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		, 2, Arrays.asList(new MaterialStack(Uranium, 1), new MaterialStack(Oxygen, 2))),
	Uvarovite			( 842, GT_ItemTextures.SET_DIAMOND			, 1    |8                               , 180, 255, 180,   0,	"Uvarovite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Chrome, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Water				( 701, GT_ItemTextures.SET_FLUID			,         16                            ,   0,   0, 255,   0,	"Water"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1))),
	Wulfenite			( 882, GT_ItemTextures.SET_DULL				, 1    |8                               , 255, 128,   0,   0,	"Wulfenite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Lead, 1), new MaterialStack(Molybdenum, 1), new MaterialStack(Oxygen, 4))),
	WroughtIron			( 304, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 200, 180, 180,   0,	"Wrought Iron"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	YellowLimonite		( 931, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 200, 200,   0,   0,	"Yellow Limonite"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 2))), // FeO(OH) + a bit Ni and Co
	
	/**
	 * Second Degree Compounds
	 */
	Perlite				( 925, GT_ItemTextures.SET_DULL				, 1    |8                               ,  30,  20,  30,   0,	"Perlite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Obsidian, 2), new MaterialStack(Water, 1))),
	Borax				( 941, GT_ItemTextures.SET_FINE				, 1    |8                               , 250, 250, 250,   0,	"Borax"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Boron, 4), new MaterialStack(Water, 10), new MaterialStack(Oxygen, 7))),
	Lignite				( 538, GT_ItemTextures.SET_LIGNITE			, 1  |4|8                               , 100,  70,  70,   0,	"Lignite Coal"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Water, 4), new MaterialStack(DarkAsh, 1))),
	Olivine				( 505, GT_ItemTextures.SET_RUBY				, 1  |4|8      |64                      , 150, 255, 150, 127,	"Olivine"						,    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeLime		, 1, Arrays.asList(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 1), new MaterialStack(SiliconDioxide, 2))),
	Opal				( 510, GT_ItemTextures.SET_OPAL				, 1  |4|8      |64                      ,   0,   0, 255,   0,	"Opal"							,    0,       0,          0,          0,          0,    0, false,  true,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(SiliconDioxide, 1))),
	Amethyst			( 509, GT_ItemTextures.SET_FLINT			, 1  |4|8      |64                      , 210,  50, 210, 127,	"Amethyst"						,    0,       0,          0,          0,          0,    0, false,  true,   3,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Iron, 1))),
	Redstone			( 810, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 200,   0,   0,   0,	"Redstone"						,    0,       0,       5000,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Pyrite, 5), new MaterialStack(Ruby, 1), new MaterialStack(Mercury, 3))),
	Lapis				( 526, GT_ItemTextures.SET_LAPIS			, 1  |4|8                               ,  70,  70, 220,   0,	"Lapis"							,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 2, Arrays.asList(new MaterialStack(Lazurite, 12), new MaterialStack(Sodalite, 2), new MaterialStack(Pyrite, 1), new MaterialStack(Calcite, 1))),
	Blaze				( 801, GT_ItemTextures.SET_SHINY			, 1            |64                      , 255, 255, 255,   0,	"Blaze"							,    0,       0,          0,          0,          0,    0, false, false,   2,   3,   2, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(DarkAsh, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Magic, 1))),
	EnderPearl			( 532, GT_ItemTextures.SET_SHINY			, 1                                     , 108, 220, 200,   0,	"Enderpearl"					,    0,       0,      25000,          0,          0,    0, false, false,   1,  16,  10, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Beryllium, 1), new MaterialStack(Potassium, 4), new MaterialStack(Nitrogen, 5), new MaterialStack(Magic, 6))),
	EnderEye			( 533, GT_ItemTextures.SET_SHINY			, 1                                     , 160, 250, 230,   0,	"Endereye"						,    5,      10,      50000,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyeGreen		, 2, Arrays.asList(new MaterialStack(EnderPearl, 1), new MaterialStack(Blaze, 1))),
	Flint				( 802, GT_ItemTextures.SET_FLINT			, 1            |64                      ,   0,  32,  64,   0,	"Flint"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 2, Arrays.asList(new MaterialStack(SiliconDioxide, 1))),
	Diatomite			( 948, GT_ItemTextures.SET_DULL				, 1    |8                               , 225, 225, 225,   0,	"Diatomite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 2, Arrays.asList(new MaterialStack(Flint, 8), new MaterialStack(BandedIron, 1), new MaterialStack(Sapphire, 1))),
	VolcanicAsh			( 940, GT_ItemTextures.SET_FLINT			, 1                                     ,  60,  50,  50,   0,	"Volcanic Ashes"				,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Flint, 6), new MaterialStack(Iron, 1), new MaterialStack(Magnesium, 1))),
	Niter				( 531, GT_ItemTextures.SET_FLINT			, 1  |4|8                               , 255, 200, 200,   0,	"Niter"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Saltpeter, 1))),
	Pyrotheum			( 843, GT_ItemTextures.SET_SHINY			, 1                                     , 255, 128,   0,   0,	"Pyrotheum"						,    2,      62,          0,          0,          0,    0, false, false,   2,   3,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Coal, 1), new MaterialStack(Redstone, 1), new MaterialStack(Blaze, 1))),
	HydratedCoal		( 818, GT_ItemTextures.SET_ROUGH			, 1                                     ,  70,  70, 100,   0,	"Hydrated Coal"					,    0,       0,          0,          0,          0,    0, false, false,   1,   9,   8, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Coal, 8), new MaterialStack(Water, 1))),
	Apatite				( 530, GT_ItemTextures.SET_DIAMOND			, 1  |4|8                               , 200, 200, 255,   0,	"Apatite"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Calcium, 5), new MaterialStack(Phosphate, 3), new MaterialStack(Chlorine, 1))),
	Alumite				(  -1, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 255, 255, 255,   0,	"Alumite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Aluminium, 5), new MaterialStack(Iron, 2), new MaterialStack(Obsidian, 2))),
	Manyullyn			(  -1, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 255, 255, 255,   0,	"Manyullyn"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Aredrite, 1))),
	IronWood			( 338, GT_ItemTextures.SET_WOOD				, 1|2          |64|128                  , 220, 175,   0,   0,	"Ironwood"						,    5,       8,          0,          0,          0,    0, false, false,   2,  19,   9, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Iron, 9), new MaterialStack(LiveRoot, 9), new MaterialStack(Gold, 1))),
	ShadowIron			( 336, GT_ItemTextures.SET_METALLIC			, 1|2  |8      |64                      , 120, 120, 120,   0,	"Shadowiron"					,    0,       0,          0,          0,          0,    0, false, false,   3,   4,   3, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Iron, 3), new MaterialStack(Magic, 1))),
	ShadowSteel			( 337, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      ,  90,  90,  90,   0,	"Shadowsteel"					,    0,       0,          0,          0,       1700, 1700,  true, false,   4,   4,   3, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Steel, 3), new MaterialStack(Magic, 1))),
	SteelLeaf			( 339, GT_ItemTextures.SET_LEAF				, 1|2          |64                      ,   0, 127,   0,   0,	"Steelleaf"						,    5,      24,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGreen		, 2, Arrays.asList(new MaterialStack(Steel, 1), new MaterialStack(Magic, 1))),
	BlackSteel			( 334, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 100, 100, 100,   0,	"Black Steel"					,    0,       0,          0,          0,       1200, 1200,  true, false,   4,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Steel, 1))),
	DamascusSteel		( 335, GT_ItemTextures.SET_METALLIC			, 1|2          |64                      , 110, 110, 110,   0,	"Damascus Steel"				,    0,       0,          0,          0,       1500, 1500,  true, false,   4,   1,   1, Dyes.dyeGray		, 2, Arrays.asList(new MaterialStack(Steel, 1))),
	TungstenSteel		( 316, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 100, 100, 160,   0,	"Tungstensteel"					,    0,       0,          0,          0,       3000, 3000,  true, false,   4,   1,   1, Dyes.dyeBlue		, 2, Arrays.asList(new MaterialStack(Steel, 1), new MaterialStack(Tungsten, 1))),
	NitroCoalFuel		( 711, GT_ItemTextures.SET_FLUID			,         16                            ,  50,  70,  50,   0,	"Nitro-Coalfuel"				,    0,      48,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 0, Arrays.asList(new MaterialStack(Glyceryl, 1), new MaterialStack(CoalFuel, 4))),
	NitroFuel			( 709, GT_ItemTextures.SET_FLUID			,         16                            , 200, 255,   0,   0,	"Nitro-Diesel"					,    0,     384,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		, 0, Arrays.asList(new MaterialStack(Glyceryl, 1), new MaterialStack(Fuel, 4))),
	AstralSilver		( 333, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      , 230, 230, 255,   0,	"Astral Silver"					,    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Silver, 2), new MaterialStack(Magic, 1))),
	Midasium			( 332, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      , 255, 200,  40,   0,	"Midasium"						,    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Gold, 2), new MaterialStack(Magic, 1))),
	Mithril				( 331, GT_ItemTextures.SET_SHINY			, 1|2  |8      |64                      , 255, 255, 210,   0,	"Mithril"						,    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Platinum, 2), new MaterialStack(Magic, 1))),
	BlueAlloy			( 309, GT_ItemTextures.SET_DULL				, 1|2                                   , 100, 180, 255,   0,	"Blue Alloy"					,    0,       0,          0,          0,          0,    0, false, false,   3,   5,   1, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Silver, 1), new MaterialStack(Nikolite, 4))),
	RedAlloy			( 308, GT_ItemTextures.SET_DULL				, 1|2                                   , 200,   0,   0,   0,	"Red Alloy"						,    0,       0,          0,          0,          0,    0, false, false,   3,   5,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Metal, 1), new MaterialStack(Redstone, 4))),
	CobaltBrass			( 343, GT_ItemTextures.SET_METALLIC			, 1|2          |64|128                  , 180, 180, 160,   0,	"Cobalt Brass"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Brass, 7), new MaterialStack(Aluminium, 1), new MaterialStack(Cobalt, 1))),
	Phosphorus			( 534, GT_ItemTextures.SET_FLINT			, 1  |4|8|16                            , 255, 255,   0,   0,	"Phosphorus"					,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Phosphate, 2))),
	Basalt				( 844, GT_ItemTextures.SET_ROUGH			, 1                                     ,  30,  20,  20,   0,	"Basalt"						,    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Olivine, 1), new MaterialStack(Calcite, 3), new MaterialStack(Flint, 8), new MaterialStack(DarkAsh, 4))),
	GarnetRed			( 527, GT_ItemTextures.SET_RUBY				, 1  |4|8      |64                      , 200,  80,  80, 127,	"Red Garnet"					,    0,       0,          0,          0,          0,    0, false,  true,   4,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Pyrope, 3), new MaterialStack(Almandine, 5), new MaterialStack(Spessartine, 8))),
	GarnetYellow		( 528, GT_ItemTextures.SET_RUBY				, 1  |4|8      |64                      , 200, 200,  80, 127,	"Yellow Garnet"					,    0,       0,          0,          0,          0,    0, false,  true,   4,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Andradite, 5), new MaterialStack(Grossular, 8), new MaterialStack(Uvarovite, 3))),
	Marble				( 845, GT_ItemTextures.SET_FINE				, 1                                     , 200, 200, 200,   0,	"Marble"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Calcite, 7))),
	Sugar				( 803, GT_ItemTextures.SET_FINE				, 1                                     , 250, 250, 250,   0,	"Sugar"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 25))),
	Thaumium			( 330, GT_ItemTextures.SET_SHINY			, 1|2          |64|128                  , 100,   0, 200,   0,	"Thaumium"						,    0,       0,          0,          0,          0,    0, false, false,   5,   2,   1, Dyes.dyePurple		, 0, Arrays.asList(new MaterialStack(Metal, 1), new MaterialStack(Magic, 1))),
	Vinteum				( 529, GT_ItemTextures.SET_EMERALD			, 1  |4|8      |64                      , 100, 200, 255,   0,	"Vinteum"						,    5,      32,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Magic, 1))),
	Vis					(  -1, GT_ItemTextures.SET_SHINY			, 0                                     , 128,   0, 255,   0,	"Vis"							,    5,      32,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Magic, 1))),
	Redrock				( 846, GT_ItemTextures.SET_ROUGH			, 1                                     , 255,  80,  50,   0,	"Redrock"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Calcite, 2), new MaterialStack(Flint, 1), new MaterialStack(Clay, 1))),
	PotassiumFeldspar	( 847, GT_ItemTextures.SET_FINE				, 1                                     , 120,  40,  40,   0,	"Potassium Feldspar"			,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 8))),
	Biotite				( 848, GT_ItemTextures.SET_METALLIC			, 1                                     ,  20,  30,  20,   0,	"Biotite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 3), new MaterialStack(Aluminium, 3), new MaterialStack(Fluorine, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 10))),
	GraniteBlack		( 849, GT_ItemTextures.SET_ROUGH			, 1            |64|128                  ,  10,  10,  10,   0,	"Black Granite"					,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(SiliconDioxide, 4), new MaterialStack(Biotite, 1))),
	GraniteRed			( 850, GT_ItemTextures.SET_ROUGH			, 1            |64|128                  , 255,   0, 128,   0,	"Red Granite"					,    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeMagenta		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(PotassiumFeldspar, 1), new MaterialStack(Oxygen, 3))),
	Chrysotile			( 912, GT_ItemTextures.SET_DULL				, 1    |8                               , 110, 140, 110,   0,	"Chrysotile"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Asbestos, 1))),
	VanadiumMagnetite	( 923, GT_ItemTextures.SET_METALLIC			, 1    |8                               ,  35,  35,  60,   0,	"Vanadium Magnetite"			,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(Vanadium, 1))), // Mixture of Fe3O4 and V2O5
	BasalticMineralSand	( 935, GT_ItemTextures.SET_SAND				, 1    |8                               ,  40,  50,  40,   0,	"Basaltic Mineral Sand"			,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(Basalt, 1))),
	GraniticMineralSand	( 936, GT_ItemTextures.SET_SAND				, 1    |8                               ,  40,  60,  60,   0,	"Granitic Mineral Sand"			,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Magnetite, 1), new MaterialStack(GraniteBlack, 1))),
	GarnetSand			( 938, GT_ItemTextures.SET_SAND				, 1    |8                               , 200, 100,   0,   0,	"Garnet Sand"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(GarnetRed, 1), new MaterialStack(GarnetYellow, 1))),
	QuartzSand			( 939, GT_ItemTextures.SET_SAND				, 1    |8                               , 200, 200, 200,   0,	"Quartz Sand"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(CertusQuartz, 1), new MaterialStack(Quartzite, 1))),
	Bastnasite			( 905, GT_ItemTextures.SET_FINE				, 1    |8                               , 200, 110,  45,   0,	"Bastnasite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Cerium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Fluorine, 1), new MaterialStack(Oxygen, 3))), // (Ce, La, Y)CO3F
	Pentlandite			( 909, GT_ItemTextures.SET_DULL				, 1    |8                               , 165, 150,   5,   0,	"Pentlandite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Nickel, 9), new MaterialStack(Sulfur, 8))), // (Fe,Ni)9S8
	Spodumene			( 920, GT_ItemTextures.SET_DULL				, 1    |8                               , 190, 170, 170,   0,	"Spodumene"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 6))), // LiAl(SiO3)2
	Pollucite			( 919, GT_ItemTextures.SET_DULL				, 1    |8                               , 240, 210, 210,   0,	"Pollucite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Caesium, 2), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 4), new MaterialStack(Water, 2), new MaterialStack(Oxygen, 12))), // (Cs,Na)2Al2Si4O12 2H2O (also a source of Rb)
	Tantalite			( 921, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 145,  80,  40,   0,	"Tantalite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Manganese, 1), new MaterialStack(Tantalum, 2), new MaterialStack(Oxygen, 6))), // (Fe, Mn)Ta2O6 (also source of Nb)
	Lepidolite			( 907, GT_ItemTextures.SET_FINE				, 1    |8                               , 240,  50, 140,   0,	"Lepidolite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Lithium, 3), new MaterialStack(Aluminium, 4), new MaterialStack(Fluorine, 2), new MaterialStack(Oxygen, 10))), // K(Li,Al,Rb)3(Al,Si)4O10(F,OH)2
	Glauconite			( 933, GT_ItemTextures.SET_DULL				, 1    |8                               , 130, 180,  60,   0,	"Glauconite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Magnesium, 2), new MaterialStack(Aluminium, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12))), // (K,Na)(Fe3+,Al,Mg)2(Si,Al)4O10(OH)2
	Vermiculite			( 932, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 200, 180,  15,   0,	"Vermiculite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Iron, 3), new MaterialStack(Aluminium, 4), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Water, 4), new MaterialStack(Oxygen, 12))), // (Mg+2, Fe+2, Fe+3)3 [(AlSi)4O10] (OH)2 4H2O)
	Bentonite			( 927, GT_ItemTextures.SET_ROUGH			, 1    |8                               , 245, 215, 210,   0,	"Bentonite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Magnesium, 6), new MaterialStack(Silicon, 12), new MaterialStack(Hydrogen, 6), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 36))), // (Na,Ca)0.33(Al,Mg)2(Si4O10)(OH)2 nH2O
	FullersEarth		( 928, GT_ItemTextures.SET_FINE				, 1    |8                               , 160, 160, 120,   0,	"Fullers Earth"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 1), new MaterialStack(Water, 4), new MaterialStack(Oxygen, 11))), // (Mg,Al)2Si4O10(OH) 4(H2O)
	
	Malachite			( 871, GT_ItemTextures.SET_DULL				, 1    |8                               ,   5,  95,   5,   0,	"Malachite"						,    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Copper, 2), new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 5))), // Cu2CO3(OH)2
	Mirabilite			( 900, GT_ItemTextures.SET_DULL				, 1    |8                               , 240, 250, 210,   0,	"Mirabilite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Water, 10), new MaterialStack(Oxygen, 4))), // Na2SO4 10H2O
	Mica				( 901, GT_ItemTextures.SET_FINE				, 1    |8                               , 195, 195, 205,   0,	"Mica"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Fluorine, 2), new MaterialStack(Oxygen, 10))), // KAl2(AlSi3O10)(F,OH)2
	Trona				( 903, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 135, 135,  95,   0,	"Trona"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Sodium, 3), new MaterialStack(Carbon, 2), new MaterialStack(Hydrogen, 1), new MaterialStack(Water, 2), new MaterialStack(Oxygen, 6))), // Na3(CO3)(HCO3) 2H2O
	Barite				( 904, GT_ItemTextures.SET_DULL				, 1    |8                               , 230, 235, 255,   0,	"Barite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Barium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	Gypsum				( 934, GT_ItemTextures.SET_DULL				, 1    |8                               , 230, 230, 250,   0,	"Gypsum"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Water, 2), new MaterialStack(Oxygen, 4))), // CaSO4 2H2O
	Alunite				( 911, GT_ItemTextures.SET_METALLIC			, 1    |8                               , 225, 180,  65,   0,	"Alunite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 2), new MaterialStack(Hydrogen, 6), new MaterialStack(Oxygen, 14))), // KAl3(SO4)2(OH)6
	Dolomite			( 914, GT_ItemTextures.SET_FLINT			, 1    |8                               , 225, 205, 205,   0,	"Dolomite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Magnesium, 1), new MaterialStack(Carbon, 2), new MaterialStack(Oxygen, 6))), // CaMg(CO3)2
	Wollastonite		( 915, GT_ItemTextures.SET_DULL				, 1    |8                               , 240, 240, 240,   0,	"Wollastonite"					,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 3))), // CaSiO3
	Zeolite				( 916, GT_ItemTextures.SET_DULL				, 1    |8                               , 240, 230, 230,   0,	"Zeolite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Calcium, 4), new MaterialStack(Silicon, 27), new MaterialStack(Aluminium, 9), new MaterialStack(Water, 28), new MaterialStack(Oxygen, 72))), // NaCa4(Si27Al9)O72 28(H2O)
	Kyanite				( 924, GT_ItemTextures.SET_FLINT			, 1    |8                               , 110, 110, 250,   0,	"Kyanite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 5))), // Al2SiO5
	Kaolinite			( 929, GT_ItemTextures.SET_DULL				, 1    |8                               , 245, 235, 235,   0,	"Kaolinite"						,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2), new MaterialStack(Hydrogen, 4), new MaterialStack(Oxygen, 9))), // Al2Si2O5(OH)4
	Talc				( 902, GT_ItemTextures.SET_DULL				, 1    |8                               ,  90, 180,  90,   0,	"Talc"							,    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, 1, Arrays.asList(new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 4), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 12))), // H2Mg3(SiO3)4 
	
	/**
	 * Materials which are renamed automatically
	 */
	@Deprecated Palygorskite		(FullersEarth, false),
	@Deprecated Adamantine			(Adamantium, true),
	@Deprecated FzDarkIron			(DarkIron, false),
	@Deprecated FZDarkIron			(DarkIron, false),
	@Deprecated Ashes				(Ash, false),
	@Deprecated DarkAshes			(DarkAsh, false),
	@Deprecated Abyssal				(Basalt, false),
	@Deprecated Adamant				(Adamantium, true),
	@Deprecated AluminumBrass		(AluminiumBrass, false),
	@Deprecated Aluminum			(Aluminium, false),
	@Deprecated NaturalAluminum		(Aluminium, false),
	@Deprecated NaturalAluminium	(Aluminium, false),
	@Deprecated Americum			(Americium, false),
	@Deprecated Beryl				(Emerald, false), // 30,200,200
	@Deprecated BlackGranite		(GraniteBlack, false),
	@Deprecated CalciumCarbonate	(Calcite, false),
	@Deprecated CreosoteOil			(Creosote, false),
	@Deprecated Chromium			(Chrome, false),
	@Deprecated Diesel				(Fuel, false),
	@Deprecated Enderpearl			(EnderPearl, false),
	@Deprecated Endereye			(EnderEye, false),
	@Deprecated EyeOfEnder			(EnderEye, false),
	@Deprecated Eyeofender			(EnderEye, false),
	@Deprecated Flour				(Wheat, false),
	@Deprecated Garnet				(GarnetRed, true),
	@Deprecated Granite				(GraniteBlack, false),
	@Deprecated Kalium				(Potassium, false),
	@Deprecated Lapislazuli			(Lapis, false),
	@Deprecated LapisLazuli			(Lapis, false),
	@Deprecated Monazit				(Monazite, false),
	@Deprecated Natrium				(Sodium, false),
	@Deprecated NitroDiesel			(NitroFuel, false),
	@Deprecated Obby				(Obsidian, false),
	@Deprecated Peridot				(Olivine, true),
	@Deprecated Phosphorite			(Phosphorus, true),
	@Deprecated Quarried			(Marble, false),
	@Deprecated Quicksilver			(Mercury, true),
	@Deprecated QuickSilver			(Mercury, false),
	@Deprecated RedRock				(Redrock, false),
	@Deprecated RefinedIron			(Iron, false),
	@Deprecated RedGranite			(GraniteRed, false),
	@Deprecated Sheldonite			(Cooperite, false),
	@Deprecated Soulsand			(SoulSand, false),
	@Deprecated SilverLead			(Galena, false),
	@Deprecated Titan				(Titanium, false),
	@Deprecated Uran				(Uranium, false),
	@Deprecated Wolframite			(Tungstate, false),
	@Deprecated Wolframium			(Tungsten, false),
	@Deprecated Wolfram				(Tungsten, false),
	@Deprecated WrougtIron			(WroughtIron, false);
	
	static {
		Thorium			.add(SubTag.ENCHANTMENT_GLOW);
		Uranium			.add(SubTag.ENCHANTMENT_GLOW);
		Plutonium		.add(SubTag.ENCHANTMENT_GLOW);
		NetherStar		.add(SubTag.ENCHANTMENT_GLOW);
		Pyrotheum		.add(SubTag.ENCHANTMENT_GLOW);
		Thaumium		.add(SubTag.ENCHANTMENT_GLOW);
		Vinteum			.add(SubTag.ENCHANTMENT_GLOW);
		Magic			.add(SubTag.ENCHANTMENT_GLOW);
		
		Rubber			.add(SubTag.NO_SMASHING);
		Plastic			.add(SubTag.NO_SMASHING);
		Stone			.add(SubTag.NO_SMASHING);
		Paper			.add(SubTag.NO_SMASHING);
		Wood			.add(SubTag.NO_SMASHING);
		
		Gunpowder		.add(SubTag.NO_SMELTING);
		Stone			.add(SubTag.NO_SMELTING);
		Paper			.add(SubTag.NO_SMELTING);
		Wood			.add(SubTag.NO_SMELTING);
		
		Redstone		.add(SubTag.PULVERIZING_CINNABAR);
		
		Pyrite			.add(SubTag.BLASTFURNACE_CALCITE_DOUBLE);
		YellowLimonite	.add(SubTag.BLASTFURNACE_CALCITE_DOUBLE);
		
		Iron			.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		PigIron			.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		DeepIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		ShadowIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		WroughtIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		MeteoricIron	.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		BrownLimonite	.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		
		Gold			.add(SubTag.WASHING_MERCURY);
		Silver			.add(SubTag.WASHING_MERCURY);
		Osmium			.add(SubTag.WASHING_MERCURY);
		Mithril			.add(SubTag.WASHING_MERCURY);
		Platinum		.add(SubTag.WASHING_MERCURY);
		Midasium		.add(SubTag.WASHING_MERCURY);
		Cooperite		.add(SubTag.WASHING_MERCURY);
		AstralSilver	.add(SubTag.WASHING_MERCURY);
		
		Zinc			.add(SubTag.WASHING_SODIUMPERSULFATE);
		Nickel			.add(SubTag.WASHING_SODIUMPERSULFATE);
		Copper			.add(SubTag.WASHING_SODIUMPERSULFATE);
		Cobalt			.add(SubTag.WASHING_SODIUMPERSULFATE);
		Cobaltite		.add(SubTag.WASHING_SODIUMPERSULFATE);
		Tetrahedrite	.add(SubTag.WASHING_SODIUMPERSULFATE);
		
		Salt			.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		RockSalt		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Scheelite		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Tungstate		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Cassiterite		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		CassiteriteSand	.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		NetherQuartz	.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		CertusQuartz	.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Phosphorus		.setOreMultiplier( 3).setSmeltingMultiplier( 3);
		Sulfur			.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Saltpeter		.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Apatite			.setOreMultiplier( 4).setSmeltingMultiplier( 4).setByProductMultiplier(2);
		Nikolite		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Redstone		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Glowstone		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Lapis			.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
		Sodalite		.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
		Lazurite		.setOreMultiplier( 6).setSmeltingMultiplier( 6).setByProductMultiplier(4);
		Monazite		.setOreMultiplier( 8).setSmeltingMultiplier( 8).setByProductMultiplier(2);
		
		Plastic			.setSpecialEffect(GT_SpecialToolEffect.Bouncy, 1);
		Rubber			.setSpecialEffect(GT_SpecialToolEffect.Bouncy, 2);
		
		Thorium			.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 1);
		Uranium			.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 2);
		Plutonium		.setSpecialEffect(GT_SpecialToolEffect.Radioactive, 3);
		
		Stone			.setSpecialEffect(GT_SpecialToolEffect.Crushing, 1);
		GraniteRed		.setSpecialEffect(GT_SpecialToolEffect.Crushing, 2);
		GraniteBlack	.setSpecialEffect(GT_SpecialToolEffect.Crushing, 2);
		Adamantium		.setSpecialEffect(GT_SpecialToolEffect.Crushing, 3);
		
		Electrum		.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 1);
		Silver			.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 2);
		AstralSilver	.setSpecialEffect(GT_SpecialToolEffect.BaneOfEnder, 3);
		
		IronWood		.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
		SteelLeaf		.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
		Midasium		.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
		Mithril			.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
		Vinteum			.setSpecialEffect(GT_SpecialToolEffect.Fortune, 1);
		Thaumium		.setSpecialEffect(GT_SpecialToolEffect.Fortune, 2);
		Magic			.setSpecialEffect(GT_SpecialToolEffect.Fortune, 3);
		
		Flint			.setSpecialEffect(GT_SpecialToolEffect.Fire, 1);
		DarkIron		.setSpecialEffect(GT_SpecialToolEffect.Fire, 2);
		FieryBlood		.setSpecialEffect(GT_SpecialToolEffect.Fire, 3);
		Blaze			.setSpecialEffect(GT_SpecialToolEffect.Fire, 3);
		
		DeepIron		.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 1);
		MeteoricIron	.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 2);
		MeteoricSteel	.setSpecialEffect(GT_SpecialToolEffect.Magnetic, 3);
		
		Cinnabar		.setDirectSmelting(Mercury		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT).add(SubTag.SMELTING_TO_GEM);
		Celestine		.setDirectSmelting(Strontium	).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Tetrahedrite	.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Chalcopyrite	.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Malachite		.setDirectSmelting(Copper		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Pentlandite		.setDirectSmelting(Nickel		).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Sphalerite		.setDirectSmelting(Zinc			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		Pyrite			.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		YellowLimonite	.setDirectSmelting(Iron			).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		BrownLimonite	.setDirectSmelting(Iron			);
		BandedIron		.setDirectSmelting(Iron			);
		Cassiterite		.setDirectSmelting(Tin			);
		CassiteriteSand	.setDirectSmelting(Tin			);
		Chromite		.setDirectSmelting(Chrome		);
		Garnierite		.setDirectSmelting(Nickel		);
		Cobaltite		.setDirectSmelting(Cobalt		);
		Stibnite		.setDirectSmelting(Antimony		);
		Cooperite		.setDirectSmelting(Platinum		);
		Pyrolusite		.setDirectSmelting(Manganese	);
		Magnesite		.setDirectSmelting(Magnesium	);
		Molybdenite		.setDirectSmelting(Molybdenum	);
		
		Glauconite		.addOreByProduct(Sodium			).addOreByProduct(Aluminium			).addOreByProduct(Iron			);
		Vermiculite		.addOreByProduct(Iron			).addOreByProduct(Aluminium			).addOreByProduct(Magnesium		);
		FullersEarth	.addOreByProduct(Aluminium		).addOreByProduct(Silicon			).addOreByProduct(Magnesium		);
		Bentonite		.addOreByProduct(Aluminium		).addOreByProduct(Calcium			).addOreByProduct(Magnesium		);
		Bastnasite		.addOreByProduct(Yttrium		).addOreByProduct(Lanthanum			).addOreByProduct(Cerium		);
		Uraninite		.addOreByProduct(Uranium		).addOreByProduct(Thorium			).addOreByProduct(Plutonium		);
		Galena			.addOreByProduct(Sulfur			).addOreByProduct(Silver			).addOreByProduct(Lead			);
		Lapis			.addOreByProduct(Lazurite		).addOreByProduct(Sodalite			).addOreByProduct(Pyrite		);
		Pyrite			.addOreByProduct(Sulfur			).addOreByProduct(Phosphorus		).addOreByProduct(Iron			);
		Copper			.addOreByProduct(Cobalt			).addOreByProduct(Gold				).addOreByProduct(Nickel		);
		Nickel			.addOreByProduct(Cobalt			).addOreByProduct(Platinum			).addOreByProduct(Iron			);
		GarnetRed		.addOreByProduct(Spessartine	).addOreByProduct(Pyrope			).addOreByProduct(Almandine		);
		GarnetYellow	.addOreByProduct(Andradite		).addOreByProduct(Grossular			).addOreByProduct(Uvarovite		);
		Cooperite		.addOreByProduct(Palladium		).addOreByProduct(Nickel			).addOreByProduct(Iridium		);
		Cinnabar		.addOreByProduct(Redstone		).addOreByProduct(Sulfur			).addOreByProduct(Glowstone		);
		Pitchblende		.addOreByProduct(Thorium		).addOreByProduct(Uranium			).addOreByProduct(Plutonium		);
		Tantalite		.addOreByProduct(Manganese		).addOreByProduct(Niobium			).addOreByProduct(Tantalum		);
		Pollucite		.addOreByProduct(Caesium		).addOreByProduct(Aluminium			).addOreByProduct(Rubidium		);
		Chrysotile		.addOreByProduct(Asbestos		).addOreByProduct(Silicon			).addOreByProduct(Magnesium		);
		Asbestos		.addOreByProduct(Asbestos		).addOreByProduct(Silicon			).addOreByProduct(Magnesium		);
		Sphalerite		.addOreByProduct(Zinc			).addOreByProduct(GarnetYellow		).addOreByProduct(Cadmium		);
		Chalcopyrite	.addOreByProduct(Pyrite			).addOreByProduct(Cobalt			).addOreByProduct(Cadmium		); // Gold?
		Pentlandite		.addOreByProduct(Iron			).addOreByProduct(Sulfur			).addOreByProduct(Cobalt		);
		Uranium			.addOreByProduct(Lead			).addOreByProduct(Plutonium			).addOreByProduct(Thorium		);
		Scheelite		.addOreByProduct(Manganese		).addOreByProduct(Molybdenum		).addOreByProduct(Calcium		);
		Tungstate		.addOreByProduct(Manganese		).addOreByProduct(Silver			).addOreByProduct(Lithium		);
		Tungsten		.addOreByProduct(Manganese		).addOreByProduct(Molybdenum		);
		Diatomite		.addOreByProduct(BandedIron		).addOreByProduct(Sapphire			);
		Iron			.addOreByProduct(Nickel			).addOreByProduct(Tin				);
		Lepidolite		.addOreByProduct(Lithium		).addOreByProduct(Caesium			);
		Gold			.addOreByProduct(Copper			).addOreByProduct(Nickel			);
		Tin				.addOreByProduct(Iron			).addOreByProduct(Zinc				);
		Antimony		.addOreByProduct(Zinc			).addOreByProduct(Iron				);
		Silver			.addOreByProduct(Lead			).addOreByProduct(Sulfur			);
		Lead			.addOreByProduct(Silver			).addOreByProduct(Sulfur			);
		Thorium			.addOreByProduct(Uranium		).addOreByProduct(Lead				);
		Plutonium		.addOreByProduct(Uranium		).addOreByProduct(Lead				);
		Electrum		.addOreByProduct(Gold			).addOreByProduct(Silver			);
		Bronze			.addOreByProduct(Copper			).addOreByProduct(Tin				);
		Brass			.addOreByProduct(Copper			).addOreByProduct(Zinc				);
		Coal			.addOreByProduct(Lignite		).addOreByProduct(Thorium			);
		Redstone		.addOreByProduct(Cinnabar		).addOreByProduct(Glowstone			);
		Glowstone		.addOreByProduct(Redstone		).addOreByProduct(Gold				);
		Ilmenite		.addOreByProduct(Iron			).addOreByProduct(Titanium			);
		Bauxite			.addOreByProduct(Grossular		).addOreByProduct(Titanium			);
		Manganese		.addOreByProduct(Chrome			).addOreByProduct(Iron				);
		Sapphire		.addOreByProduct(Aluminium		).addOreByProduct(GreenSapphire		);
		GreenSapphire	.addOreByProduct(Aluminium		).addOreByProduct(Sapphire			);
		Platinum		.addOreByProduct(Nickel			).addOreByProduct(Iridium			);
		Emerald			.addOreByProduct(Beryllium		).addOreByProduct(Aluminium			);
		Olivine			.addOreByProduct(Pyrope			).addOreByProduct(Magnesium			);
		Chrome			.addOreByProduct(Iron			).addOreByProduct(Magnesium			);
		Chromite		.addOreByProduct(Iron			).addOreByProduct(Magnesium			);
		Tetrahedrite	.addOreByProduct(Antimony		).addOreByProduct(Zinc				);
		QuartzSand		.addOreByProduct(CertusQuartz	).addOreByProduct(Quartzite			);
		GarnetSand		.addOreByProduct(GarnetRed		).addOreByProduct(GarnetYellow		);
		Magnetite		.addOreByProduct(Iron			).addOreByProduct(Gold				);
		GraniticMineralSand.addOreByProduct(GraniteBlack).addOreByProduct(Magnetite			);
		BasalticMineralSand.addOreByProduct(Basalt		).addOreByProduct(Magnetite			);
		Basalt			.addOreByProduct(Olivine		).addOreByProduct(DarkAsh			);
		Celestine		.addOreByProduct(Strontium		).addOreByProduct(Sulfur			);
		VanadiumMagnetite.addOreByProduct(Magnetite		).addOreByProduct(Vanadium			);
		Lazurite		.addOreByProduct(Sodalite		).addOreByProduct(Lapis				);
		Sodalite		.addOreByProduct(Lazurite		).addOreByProduct(Lapis				);
		Spodumene		.addOreByProduct(Aluminium		).addOreByProduct(Lithium			);
		Ruby			.addOreByProduct(Chrome			).addOreByProduct(GarnetRed			);
		Phosphorus		.addOreByProduct(Apatite		).addOreByProduct(Phosphate			);
		Iridium			.addOreByProduct(Platinum		).addOreByProduct(Osmium			);
		Pyrope			.addOreByProduct(GarnetRed		).addOreByProduct(Magnesium			);
		Almandine		.addOreByProduct(GarnetRed		).addOreByProduct(Aluminium			);
		Spessartine		.addOreByProduct(GarnetRed		).addOreByProduct(Manganese			);
		Andradite		.addOreByProduct(GarnetYellow	).addOreByProduct(Iron				);
		Grossular		.addOreByProduct(GarnetYellow	).addOreByProduct(Calcium			);
		Uvarovite		.addOreByProduct(GarnetYellow	).addOreByProduct(Chrome			);
		YellowLimonite	.addOreByProduct(Nickel			).addOreByProduct(Cobalt			);
		BrownLimonite	.addOreByProduct(YellowLimonite	);
		Pyrolusite		.addOreByProduct(Manganese		);
		Molybdenite		.addOreByProduct(Molybdenum		);
		Stibnite		.addOreByProduct(Antimony		);
		Garnierite		.addOreByProduct(Nickel			);
		Lignite			.addOreByProduct(Coal			);
		Diamond			.addOreByProduct(Graphite		);
		Beryllium		.addOreByProduct(Emerald		);
		Forcicium		.addOreByProduct(Thorium		);
		Forcillium		.addOreByProduct(Thorium		);
		Monazite		.addOreByProduct(Thorium		);
		Quartzite		.addOreByProduct(CertusQuartz	);
		CertusQuartz	.addOreByProduct(Quartzite		);
		Calcite			.addOreByProduct(Andradite		);
		Apatite			.addOreByProduct(Phosphorus		);
		Zinc			.addOreByProduct(Tin			);
		Nikolite		.addOreByProduct(Diamond		);
		Magnesite		.addOreByProduct(Magnesium		);
		NetherQuartz	.addOreByProduct(Netherrack		);
		PigIron			.addOreByProduct(Iron			);
		DeepIron		.addOreByProduct(Iron			);
		ShadowIron		.addOreByProduct(Iron			);
		DarkIron		.addOreByProduct(Iron			);
		MeteoricIron	.addOreByProduct(Iron			);
		Steel			.addOreByProduct(Iron			);
		Mithril			.addOreByProduct(Platinum		);
		Midasium		.addOreByProduct(Gold			);
		AstralSilver	.addOreByProduct(Silver			);
		Graphite		.addOreByProduct(Carbon			);
		Netherrack		.addOreByProduct(Sulfur			);
		Flint			.addOreByProduct(Obsidian		);
		Cobaltite		.addOreByProduct(Cobalt			);
		Cobalt			.addOreByProduct(Cobaltite		);
		Sulfur			.addOreByProduct(Sulfur			);
		Saltpeter		.addOreByProduct(Saltpeter		);
		Endstone		.addOreByProduct(Helium_3		);
		Osmium			.addOreByProduct(Iridium		);
		Magnesium		.addOreByProduct(Olivine		);
		Aluminium		.addOreByProduct(Bauxite		);
		Titanium		.addOreByProduct(Almandine		);
		Obsidian		.addOreByProduct(Olivine		);
		Ash				.addOreByProduct(Carbon			);
		DarkAsh			.addOreByProduct(Carbon			);
		Redrock			.addOreByProduct(Clay			);
		Marble			.addOreByProduct(Calcite		);
		Clay			.addOreByProduct(Clay			);
		Cassiterite		.addOreByProduct(Tin			);
		CassiteriteSand	.addOreByProduct(Tin			);
		GraniteBlack	.addOreByProduct(Biotite		);
		GraniteRed		.addOreByProduct(PotassiumFeldspar);
		Phosphate		.addOreByProduct(Phosphor		);
		Phosphor		.addOreByProduct(Phosphate		);
		Jade			.addOreByProduct(Jade			);
		Tanzanite		.addOreByProduct(Opal			);
		Opal			.addOreByProduct(Tanzanite		);
		Amethyst		.addOreByProduct(Amethyst		);
		Jasper			.addOreByProduct(FoolsRuby		);
		FoolsRuby		.addOreByProduct(Jasper			);
		Amber			.addOreByProduct(Amber			);
		Topaz			.addOreByProduct(BlueTopaz		);
		BlueTopaz		.addOreByProduct(Topaz			);
		Niter			.addOreByProduct(Saltpeter		);
		Vinteum			.addOreByProduct(Vinteum		);
		Force			.addOreByProduct(Force			);
		Dilithium		.addOreByProduct(Dilithium		);
		Naquadah		.addOreByProduct(Naquadah		);
		Neutronium		.addOreByProduct(Neutronium		);
		Lithium			.addOreByProduct(Lithium		);
		Silicon			.addOreByProduct(SiliconDioxide	);
		Salt			.addOreByProduct(RockSalt		);
		RockSalt		.addOreByProduct(Salt			);
		
		FoolsRuby.mChemicalFormula = Ruby.mChemicalFormula;
		Naquadah.mChemicalFormula = "Nq";
	}
	
	public static Materials get(String aMaterialName) {
		Object tObject = GT_Utility.getFieldContent(Materials.class, aMaterialName, false, false);
		if (tObject != null && tObject instanceof Materials) return (Materials)tObject;
		return _NULL;
	}
	
	public static Materials getRealMaterial(String aMaterialName) {
		return get(aMaterialName).mMaterialInto;
	}
	
	public static void init(GT_Config aConfiguration) {
		for (Materials tMaterial : values()) {
			String tString = tMaterial.toString().toLowerCase();
			if (tMaterial.mBlastFurnaceRequired) tMaterial.mBlastFurnaceRequired = aConfiguration.get(GT_ConfigCategories.Materials.blastfurnacerequirements, tString, true);
			if (tMaterial.mAmplificationValue > 0) tMaterial.mAmplificationValue = aConfiguration.get(GT_ConfigCategories.Materials.UUM_MaterialCost, tString, tMaterial.mAmplificationValue);
			if (tMaterial.mUUMEnergy > 0) tMaterial.mUUMEnergy = aConfiguration.get(GT_ConfigCategories.Materials.UUM_EnergyCost, tString, tMaterial.mUUMEnergy);
			if (tMaterial.mBlastFurnaceRequired && aConfiguration.get(GT_ConfigCategories.Materials.blastinductionsmelter, tString, tMaterial.mBlastFurnaceTemp < 1500)) GT_ModHandler.ThermalExpansion.addSmelterBlastOre(tMaterial);
		}
	}
	
	public boolean isRadioactive() {
		if (mElement != null) return mElement.mHalfLifeSeconds >= 0;
		for (MaterialStack tMaterial : mMaterialList) if (tMaterial.mMaterial.isRadioactive()) return true;
		return false;
	}
	
	public int getProtons() {
		if (mElement != null) return mElement.getProtons();
		if (mMaterialList.size() <= 0) return Element.Tc.getProtons();
		int rAmount = 0, tAmount = 0;
		for (MaterialStack tMaterial : mMaterialList) {
			tAmount += tMaterial.mAmount;
			rAmount += tMaterial.mAmount * tMaterial.mMaterial.getProtons();
		}
		return (int)((getDensity() * rAmount) / (tAmount * GregTech_API.MATERIAL_UNIT));
	}
	
	public int getNeutrons() {
		if (mElement != null) return mElement.getNeutrons();
		if (mMaterialList.size() <= 0) return Element.Tc.getNeutrons();
		int rAmount = 0, tAmount = 0;
		for (MaterialStack tMaterial : mMaterialList) {
			tAmount += tMaterial.mAmount;
			rAmount += tMaterial.mAmount * tMaterial.mMaterial.getNeutrons();
		}
		return (int)((getDensity() * rAmount) / (tAmount * GregTech_API.MATERIAL_UNIT));
	}
	
	public int getMass() {
		if (mElement != null) return mElement.getMass();
		if (mMaterialList.size() <= 0) return Element.Tc.getMass();
		int rAmount = 0, tAmount = 0;
		for (MaterialStack tMaterial : mMaterialList) {
			tAmount += tMaterial.mAmount;
			rAmount += tMaterial.mAmount * tMaterial.mMaterial.getMass();
		}
		return (int)((getDensity() * rAmount) / (tAmount * GregTech_API.MATERIAL_UNIT));
	}
	
	public long getDensity() {
		return mDensity;
	}
	
	public String getToolTip() {
		return getToolTip(1, false);
	}
	
	public String getToolTip(boolean aShowQuestionMarks) {
		return getToolTip(1, aShowQuestionMarks);
	}
	
	public String getToolTip(long aMultiplier) {
		return getToolTip(aMultiplier, false);
	}
	
	public String getToolTip(long aMultiplier, boolean aShowQuestionMarks) {
		if (!aShowQuestionMarks && mChemicalFormula.equals("?")) return "";
		if (getDensity() * aMultiplier >= GregTech_API.MATERIAL_UNIT * 2 && !mMaterialList.isEmpty()) {
			return ((mElement != null || (mMaterialList.size() < 2 && mMaterialList.get(0).mAmount == 1))?mChemicalFormula:"(" + mChemicalFormula + ")") + ((getDensity() * aMultiplier) / GregTech_API.MATERIAL_UNIT);
		}
		return mChemicalFormula;
	}
	
	private final ArrayList<ItemStack> mMaterialItems = new ArrayList<ItemStack>();
	
	/**
	 * Adds an ItemStack to this Material.
	 */
	public Materials add(ItemStack aStack) {
		if (aStack != null && !contains(aStack)) mMaterialItems.add(aStack);
		return this;
	}
	
	/**
	 * This is used to determine if any of the ItemStacks belongs to this Material.
	 */
	public boolean contains(ItemStack... aStacks) {
		if (aStacks == null || aStacks.length <= 0) return false;
		for (ItemStack tStack : mMaterialItems) for (ItemStack aStack : aStacks) if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
		return false;
	}
	
	/**
	 * This is used to determine if an ItemStack belongs to this Material.
	 */
	public boolean remove(ItemStack aStack) {
		if (aStack == null) return false;
		boolean temp = false;
		for (int i = 0; i < mMaterialItems.size(); i++) if (GT_Utility.areStacksEqual(aStack, mMaterialItems.get(i))) {
			mMaterialItems.remove(i--);
			temp = true;
		}
		return temp;
	}
	
	private final List<SubTag> mSubTags = new ArrayList<SubTag>();
	
	/**
	 * Adds a SubTag to this Material
	 */
	public Materials add(SubTag aTag) {
		if (aTag != null && !contains(aTag)) mSubTags.add(aTag);
		return this;
	}
	
	/**
	 * If this Material has this exact SubTag
	 */
	public boolean contains(SubTag aTag) {
		return mSubTags.contains(aTag);
	}
	
	/**
	 * Removes a SubTag from this Material
	 */
	public boolean remove(SubTag aTag) {
		return mSubTags.remove(aTag);
	}
	
	/**
	 * Adds a Material to the List of Byproducts when grinding this Ore.
	 * Is used for more precise Ore grinding, so that it is possible to choose between certain kinds of Materials.
	 */
	public Materials addOreByProduct(Materials aMaterial) {
		if (!mOreByProducts.contains(aMaterial.mMaterialInto)) mOreByProducts.add(aMaterial.mMaterialInto);
		return this;
	}
	
	/**
	 * If this Ore gives multiple drops of its Main Material.
	 * Lapis Ore for example gives about 6 drops.
	 */
	public Materials setOreMultiplier(int aOreMultiplier) {
		if (aOreMultiplier > 0) mOreMultiplier = aOreMultiplier;
		return this;
	}
	
	/**
	 * If this Ore gives multiple drops of its Byproduct Material.
	 */
	public Materials setByProductMultiplier(int aByProductMultiplier) {
		if (aByProductMultiplier > 0) mByProductMultiplier = aByProductMultiplier;
		return this;
	}
	
	/**
	 * If this Ore gives multiple drops of its Main Material.
	 * Lapis Ore for example gives about 6 drops.
	 */
	public Materials setSmeltingMultiplier(int aSmeltingMultiplier) {
		if (aSmeltingMultiplier > 0) mSmeltingMultiplier = aSmeltingMultiplier;
		return this;
	}
	
	/**
	 * This Ore should be smolten directly into an Ingot of this Material instead of an Ingot of itself.
	 */
	public Materials setDirectSmelting(Materials aMaterial) {
		if (aMaterial != null) mDirectSmelting = aMaterial.mMaterialInto;
		return this;
	}

	/**
	 * This Material should be the Main Material this Ore gets ground into.
	 * Example, Chromite giving Chrome or Tungstate giving Tungsten.
	 */
	public Materials setOreReplacement(Materials aMaterial) {
		if (aMaterial != null) mOreReplacement = aMaterial.mMaterialInto;
		return this;
	}
	
	public Materials setSpecialEffect(GT_SpecialToolEffect aSpecialEffect, int aLevel) {
		if (aSpecialEffect != null && aLevel > 0 && aLevel <= 10) {
			mSpecialEffect = aSpecialEffect;
			mEffectLevel = (byte)aLevel;
		}
		return this;
	}
	
	/**
	 * This Array can be changed dynamically by a Tick Handler in order to get a glowing Effect on all GT Meta Items out of this Material.
	 */
	public final short[] mRGBa = new short[] {255, 255, 255, 0};
	
	public final IIconContainer[] mIconSet;
	public boolean mBlastFurnaceRequired = false, mTransparent = false;
	public GT_SpecialToolEffect mSpecialEffect = null;
	public byte mEffectLevel = 0;
	public String mChemicalFormula = "?", mDefaultLocalName = "null";
	public Dyes mColor = Dyes._NULL;
	public short mMeltingPoint = 0, mBlastFurnaceTemp = 0;
	public int mTypes = 0, mAmplificationValue = 0, mUUMEnergy = 0, mFuelPower = 0, mFuelType = 0, mExtraData = 0, mOreValue = 0, mOreMultiplier = 1, mByProductMultiplier = 1, mSmeltingMultiplier = 1;
	public long mDensity = GregTech_API.MATERIAL_UNIT;
	public Element mElement = null;
	public Materials mDirectSmelting = this, mOreReplacement = this;
	public final int mMetaItemSubID;
	public final boolean mUnificatable;
	public final Materials mMaterialInto;
	public final List<MaterialStack> mMaterialList = new ArrayList<MaterialStack>();
	public final List<Materials> mOreByProducts = new ArrayList<Materials>(), mOreReRegistrations = new ArrayList<Materials>();
	public FluidStack mSolid = null, mFluid = null, mGas = null, mPlasma = null;
	
	private Materials(int aMetaItemSubID, IIconContainer[] aIconSet, boolean aUnificatable) {
		mUnificatable = aUnificatable;
		mMaterialInto = this;
		mMetaItemSubID = aMetaItemSubID;
		mIconSet = Arrays.copyOf(aIconSet, 64);
		if (aMetaItemSubID >= 0) {
			if (GregTech_API.sGeneratedMaterials[aMetaItemSubID] == null) {
				GregTech_API.sGeneratedMaterials[aMetaItemSubID] = this;
			} else {
				throw new IllegalArgumentException("The Index " + aMetaItemSubID + " is already used!");
			}
		}
	}
	
	private Materials(Materials aMaterialInto, boolean aReRegisterIntoThis) {
		mUnificatable = false;
		mDefaultLocalName = aMaterialInto.mDefaultLocalName;
		mMaterialInto = aMaterialInto.mMaterialInto;
		if (aReRegisterIntoThis) mMaterialInto.mOreReRegistrations.add(this);
		mChemicalFormula = aMaterialInto.mChemicalFormula;
		mMetaItemSubID = -1;
		mIconSet = GT_ItemTextures.SET_NONE;
	}
	
	/**
	 * @param aMetaItemSubID the Sub-ID used in my own MetaItems. Range 0-1000. -1 for no Material
	 * @param aTypes which kind of Items should be generated. Bitmask as follows:
	 *      1 = Dusts of all kinds.
	 *      2 = Dusts, Ingots, Plates, Rods/Sticks, Machine Components and other Metal specific things.
	 *      4 = Dusts, Gems, Plates, Lenses (if transparent).
	 *      8 = Dusts, Impure Dusts, crushed Ores, purified Ores, centrifuged Ores etc.
	 *     16 = Cells
	 *     32 = Plasma Cells
	 *     64 = Tool Heads
	 *    128 = Gears
	 * @param aR, aG, aB Color of the Material 0-255 each.
	 * @param aA transparency of the Material Texture. 0 = fully visible, 255 = Invisible.
	 * @param aLocalName The Name used as Default for localization.
	 * @param aFuelType Type of Generator to get Energy from this Material.
	 * @param aFuelPower EU generated. Will be multiplied by 1000, also additionally multiplied by 2 for Gems.
	 * @param aAmplificationValue Amount of UUM amplifier gotten from this.
	 * @param aUUMEnergy Amount of EU needed to shape the UUM into this Material.
	 * @param aMeltingPoint Used to determine the smelting Costs in Furnii.
	 * @param aBlastFurnaceTemp Used to determine the needed Heat capactiy Costs in Blast Furnii.
	 * @param aBlastFurnaceRequired If this requires a Blast Furnace.
	 * @param aColor Vanilla MC Wool Color which comes the closest to this.
	 */
	private Materials(int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor) {
		this(aMetaItemSubID, aIconSet, true);
		mDefaultLocalName = aLocalName;
		mMeltingPoint = (short)aMeltingPoint;
		mBlastFurnaceTemp = (short)aBlastFurnaceTemp;
		mBlastFurnaceRequired = aBlastFurnaceRequired;
		mTransparent = aTransparent;
		mAmplificationValue = aAmplificationValue;
		mUUMEnergy = aUUMEnergy;
		mFuelPower = aFuelPower;
		mFuelType = aFuelType;
		mOreValue = aOreValue;
		mDensity = (GregTech_API.MATERIAL_UNIT * aDensityMultiplier) / aDensityDivider;
		mColor = aColor==null?Dyes._NULL:aColor;
		mRGBa[0] = (short)aR;
		mRGBa[1] = (short)aG;
		mRGBa[2] = (short)aB;
		mRGBa[3] = (short)aA;
		mTypes = aTypes;
	}
	
	/**
	 * @param aElement The Element Enum represented by this Material
	 */
	private Materials(int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, Element aElement) {
		this(aMetaItemSubID, aIconSet, aTypes, aR, aG, aB, aA, aLocalName, aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
		mElement = aElement;
		mElement.mLinkedMaterials.add(this);
		if (aElement == Element._NULL) {
			mChemicalFormula = "Empty";
		} else {
			mChemicalFormula = aElement.toString();
			mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
		}
	}
	
	private Materials(int aMetaItemSubID, IIconContainer[] aIconSet, int aTypes, int aR, int aG, int aB, int aA, String aLocalName, int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, int aExtraData, List<MaterialStack> aMaterialList) {
		this(aMetaItemSubID, aIconSet, aTypes, aR, aG, aB, aA, aLocalName, aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
		mExtraData = aExtraData;
		mMaterialList.addAll(aMaterialList);
		mChemicalFormula = "";
		for (MaterialStack tMaterial : mMaterialList) mChemicalFormula += tMaterial.toString();
		mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
	}
	
	public static volatile int VERSION = 408;
}