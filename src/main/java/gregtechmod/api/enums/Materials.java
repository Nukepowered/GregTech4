package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
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
	_NULL			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			, Element._NULL		),
	
	/**
	 * Direct Elements
	 */
	Aluminium		(    0,       0,          0,          0,       1700, 1700,  true, false,   3,   1,   1, Dyes.dyeLightBlue	, Element.Al		),
	Antimony		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeLightGray	, Element.Sb		),
	Arsenic			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, Element.As		),
	Beryllium		(    0,       0,          0,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeGreen		, Element.Be		),
	Calcium			(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyePink		, Element.Ca		),
	Carbon			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeBlack		, Element.C			),
	Cadmium			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGray		, Element.Cd		),
	Chlorine		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeCyan		, Element.Cl		),
	Chrome			(    0,       0,          0,          0,       1700, 1700,  true, false,   5,   1,   1, Dyes.dyePink		, Element.Cr		),
	Cobalt			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, Element.Co		),
	Copper			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, Element.Cu		),
	Deuterium		(    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeYellow		, Element.D			),
	Empty			(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			, Element._NULL		),
	Fluorine		(    0,       0,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeGreen		, Element.F			),
	Gold			(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeYellow		, Element.Au		),
	Hydrogen		(    1,      15,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeBlue		, Element.H			),
	Helium			(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeYellow		, Element.He		),
	Helium_3		(    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeYellow		, Element.He_3		),
	Indium			(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.In		),
	Iridium			(    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeWhite		, Element.Ir		),
	Iron			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Fe		),
	Lead			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		, Element.Pb		),
	Lithium			(    3,      60,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, Element.Li		),
	Magic			(    5,      32,          0,          0,          0,    0, false, false,   7,   1,   1, Dyes.dyePurple		, Element.Ma		),
	Magnesium		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, Element.Mg		),
	Manganese		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, Element.Mn		),
	Mercury			(    5,       8,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Hg		),
	Neutronium		(    0,       0,          0,          0,          0,    0, false, false,  20,   1,   1, Dyes.dyeWhite		, Element.Nt		),
	Nickel			(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, Element.Ni		),
	Nitrogen		(    0,       0,          0,          0,          0,    0, false,  true,   2,   1,   1, Dyes.dyeCyan		, Element.N			),
	Osmium			(    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlue		, Element.Os		),
	Oxygen			(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		, Element.O			),
	Palladium		(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.Pd		),
	Phosphor		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, Element.P			),
	Platinum		(    0,       0,          0,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeOrange		, Element.Pt		),
	Plutonium		(    0,       0,    2000000,          0,          0,    0, false, false,   6,   1,   1, Dyes.dyeLime		, Element.Pu		),
	Potassium		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, Element.K			),
	Silicon			(    0,       0,          0,          0,       1500, 1500,  true, false,   1,   1,   1, Dyes.dyeBlack		, Element.Si		),
	Silver			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, Element.Ag		),
	Sodium			(    3,      30,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, Element.Na		),
	Sulfur			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, Element.S			),
	Tellurium		(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGray		, Element.Te		),
	Thorium			(    0,       0,     500000,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeBlack		, Element.Th		),
	Tin				(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, Element.Sn		),
	Titanium		(    0,       0,          0,          0,       1500, 1500,  true, false,   5,   1,   1, Dyes.dyePurple		, Element.Ti		),
	Tritium			(    0,       0,          0,          0,          0,    0, false,  true,  10,   1,   1, Dyes.dyeRed			, Element.T			),
	Tungsten		(    0,       0,          0,          0,       2500, 2500,  true, false,   4,   1,   1, Dyes.dyeBlack		, Element.W			),
	Uranium			(    0,       0,    1000000,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeGreen		, Element.U			),
	Zinc			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, Element.Zn		),
	
	/**
	 * Unknown Material Components. Dead End Section.
	 */
	Adamant			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Adamite			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Adluorite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Alduorite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Amber			(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeOrange		),
	Amordrine		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Andesite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Angmallen		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Ardite			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Aredrite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Atlarus			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Bitumen			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Blueschist		(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeLightBlue	),
	Bluestone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		),
	Carmot			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Celenegil		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	CertusQuartz	(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	),
	Ceruclase		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	CobaltHexahydrate(   0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		),
	ConstructionFoam(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Chalcopyrite	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Chalk			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeWhite		),
	Chert			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	CrudeOil		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Dacite			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeLightGray	),
	DarkStone		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlack		),
	Demonite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	Desh			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Desichalkos		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Dilithium		(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	Draconic		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Duranium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	),
	Eclogite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Emery			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Energized		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Epidote			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Eximite			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	FieryBlood		(    5,     100,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Firestone		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		),
	Fluorite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	FoolsRuby		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	Force			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	Gabbro			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	Glowstone		(    0,       0,      25000,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Gneiss			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes._NULL			),
	GraniteBlack	(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeBlack		),
	GraniteRed		(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeMagenta		),
	Graphite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGray		),
	Greenschist		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Greenstone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Greywacke		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Haderoth		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Hepatizon		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Ignatius		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Infernal		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Infuscolium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	InfusedAir		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		),
	InfusedFire		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			),
	InfusedEarth	(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	InfusedWater	(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		),
	InfusedVis		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		),
	InfusedDull		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	),
	Inolashite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Invisium		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Jade			(    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeGreen		),
	Kalendrite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Komatiite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Lava			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Lemurite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Limestone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Lodestone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Luminite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Magnetite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Magma			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Mercassium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	MeteoricIron	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	MeteoricSteel	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	Meteorite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Meutoite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Migmatite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Monazite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Naquadah		(    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlack		),
	NaquadahAlloy	(    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyeBlack		),
	NetherBrick		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	NetherQuartz	(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		),
	NetherStar		(    5,   50000,          0,          0,          0,    0, false, false,  15,   1,   1, Dyes.dyeWhite		),
	Nikolite		(    0,       0,       5000,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeCyan		),
	Orichalcum		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Osmonium		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		),
	Oureclase		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Pitchblende		(    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeYellow		),
	Potash			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Plastic			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Prometheum		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Quartz			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Quartzite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		),
	Quicklime		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Randomite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	RefinedGlowstone(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	RefinedObsidian	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Rhyolite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Rubracium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Sand			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Sanguinite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Siltstone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Soapstone		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Starconium		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Tar				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Tartarite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Tapazite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		),
	Thyrium			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes._NULL			),
	Tritanium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	UUMatter		(    0,       0,          0,          0,          0,    0, false, false,  10,   1,   1, Dyes.dyePink		),
	Void			(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			),
	Voidstone		(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes._NULL			),
	Vulcanite		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Vyroxeres		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes._NULL			),
	Zectium			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlack		),
	
	/**
	 * Not possible to determine exact Components
	 */
	AluminumBrass	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Antimatter		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	BioFuel			(    0,       6,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		),
	Biomass			(    3,       8,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGreen		),
	Cluster			(    0,       0,          0,          0,          0,    0, false,  true,   1,   1,   1, Dyes.dyeWhite		),
	CoalFuel		(    0,      16,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Creosote		(    3,       3,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	Crystal			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		),
	Ethanol			(    0,     128,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		),
	Fuel			(    0,     384,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Gunpowder		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	LimePure		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		),
	Meat			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	MeatRaw			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	MeatCooked		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		),
	Mud				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	NaturalAluminum	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	),
	Osmiridium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	),
	Oil				(    3,      64,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		),
	Red				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Reinforced		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		),
	SeedOil			(    3,       2,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		),
	Stone			(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeGray		),
	TNT				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			),
	Wheat			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		),
	Wood			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		),
	
	/**
	 * TODO: This
	 */
	Endstone		(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeYellow		),
	Netherrack		(    0,       0,          0,          0,          0,    0, false, false,   0,   1,   1, Dyes.dyeRed			),
	
	/**
	 * First Degree Compounds
	 */
	Adamantine		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 2, Arrays.asList(new MaterialStack(Adamant, 1))),
	Almandine		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Iron, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Amethyst		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(Silicon, 4), new MaterialStack(Oxygen, 8), new MaterialStack(Iron, 1))),
	Andradite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Iron, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Ash				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightGray	, 2, Arrays.asList(new MaterialStack(Carbon, 1))),
	BatteryAlloy	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Lead, 4), new MaterialStack(Antimony, 1))),
	Bauxite			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBrown		, 1, Arrays.asList(new MaterialStack(Titanium, 1), new MaterialStack(Aluminium, 16), new MaterialStack(Hydrogen, 10), new MaterialStack(Oxygen, 12))),
	Bone			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 0, Arrays.asList(new MaterialStack(Calcium, 1))),
	Brass			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Copper, 3))),
	Bronze			(    0,       0,          0,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Copper, 3))),
	Calcite			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Calcium, 1), new MaterialStack(Carbon, 1), new MaterialStack(Oxygen, 3))),
	Cassiterite		(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeWhite		, 0, Arrays.asList(new MaterialStack(Tin, 1), new MaterialStack(Oxygen, 2))),
	Charcoal		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Chromite		(    0,       0,          0,          0,       1700, 1700,  true, false,   6,   1,   1, Dyes.dyePink		, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Chrome, 2), new MaterialStack(Oxygen, 4))),
	Cinnabar		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Mercury, 1), new MaterialStack(Sulfur, 1))),
	Clay			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightBlue	, 1, Arrays.asList(new MaterialStack(Sodium, 2), new MaterialStack(Lithium, 1), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 2))),
	Coal			(    0,       0,          0,          0,          0,    0, false, false,   2,   2,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Cobaltite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Arsenic, 1), new MaterialStack(Sulfur, 1))),
	Cooperite		(    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Platinum, 3), new MaterialStack(Nickel, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Palladium, 1))),
	Cupronickel		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Copper, 1), new MaterialStack(Nickel, 1))),
	DarkAsh			(    0,       0,          0,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	DeepIron		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	Diamond			(    0,       0,          0,          0,          0,    0, false,  true,   5, 128,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Carbon, 1))),
	Electrum		(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Silver, 1), new MaterialStack(Gold, 1))),
	Emerald			(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Beryllium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 6), new MaterialStack(Oxygen, 18))),
	Flint			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Oxygen, 2))),
	Galena			(    0,       0,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Lead, 3), new MaterialStack(Silver, 3), new MaterialStack(Sulfur, 2))),
	Glyceryl		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Carbon, 3), new MaterialStack(Hydrogen, 5), new MaterialStack(Nitrogen, 3), new MaterialStack(Oxygen, 9))),
	GreenSapphire	(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3))),
	Grossular		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Invar			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Iron, 2), new MaterialStack(Nickel, 1))),
	Kanthal			(    0,       0,          0,          0,       2500, 2500,  true, false,   1,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Aluminium, 1), new MaterialStack(Chrome, 1))),
	Lazurite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 6), new MaterialStack(Silicon, 6), new MaterialStack(Calcium, 8), new MaterialStack(Sodium, 8))),
	Liveroot		(    0,       0,          0,          0,          0,    0, false, false,   2,   4,   3, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Wood, 3), new MaterialStack(Magic, 1))),
	Magnalium		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Aluminium, 2))),
	Methane			(    1,      45,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeMagenta		, 1, Arrays.asList(new MaterialStack(Carbon, 1), new MaterialStack(Hydrogen, 4))),
	Nichrome		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Nickel, 4), new MaterialStack(Chrome, 1))),
	NitroCarbon		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Nitrogen, 1), new MaterialStack(Carbon, 1))),
	NitrogenDioxide	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 2))),
	Obsidian		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 8))),
	Olivine			(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeLime		, 1, Arrays.asList(new MaterialStack(Magnesium, 2), new MaterialStack(Iron, 1), new MaterialStack(Silicon, 2), new MaterialStack(Oxygen, 4))),
	Phosphate		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Phosphor, 1), new MaterialStack(Oxygen, 4))),
	PigIron			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	Pyrite			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Iron, 1), new MaterialStack(Sulfur, 2))),
	Pyrope			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Magnesium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Ruby			(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3), new MaterialStack(Chrome, 1))),
	Salt			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Chlorine, 1))),
	Saltpeter		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Potassium, 1), new MaterialStack(Nitrogen, 1), new MaterialStack(Oxygen, 3))),
	Sapphire		(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Oxygen, 3))),
	Sodalite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 1, Arrays.asList(new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Sodium, 4), new MaterialStack(Chlorine, 1))),
	SodiumPersulfate(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	SodiumSulfide	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Sodium, 1), new MaterialStack(Sulfur, 1))),
	SolderingAlloy	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Tin, 9), new MaterialStack(Antimony, 1))),
	Spessartine		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Manganese, 3), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Sphalerite		(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeYellow		, 1, Arrays.asList(new MaterialStack(Zinc, 1), new MaterialStack(Sulfur, 1))),
	StainlessSteel	(    0,       0,          0,          0,       2000, 2000,  true, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Iron, 6), new MaterialStack(Chrome, 1), new MaterialStack(Manganese, 1), new MaterialStack(Nickel, 1))),
	Steel			(    0,       0,          0,          0,       1000, 1000,  true, false,   4,  51,  50, Dyes.dyeGray		, 1, Arrays.asList(new MaterialStack(Iron, 50), new MaterialStack(Carbon, 1))),
	SulfuricAcid	(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Sulfur, 1), new MaterialStack(Oxygen, 4))),
	Tanzanite		(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyePurple		, 1, Arrays.asList(new MaterialStack(Calcium, 2), new MaterialStack(Aluminium, 3), new MaterialStack(Silicon, 3), new MaterialStack(Hydrogen, 1), new MaterialStack(Oxygen, 13))),
	Tetrahedrite	(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Copper, 3), new MaterialStack(Antimony, 1), new MaterialStack(Sulfur, 3), new MaterialStack(Iron, 1))), //Cu3SbS3 + x(Fe,Zn)6Sb2S9
	Topaz			(    0,       0,          0,          0,          0,    0, false,  true,   5,   1,   1, Dyes.dyeOrange		, 1, Arrays.asList(new MaterialStack(Aluminium, 2), new MaterialStack(Silicon, 1), new MaterialStack(Fluorine, 2), new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 6))),
	Tungstate		(    0,       0,          0,          0,       2500, 2500,  true, false,   4,   1,   1, Dyes.dyeBlack		, 1, Arrays.asList(new MaterialStack(Tungsten, 1), new MaterialStack(Lithium, 2), new MaterialStack(Oxygen, 4))),
	Uvarovite		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Chrome, 2), new MaterialStack(Silicon, 3), new MaterialStack(Oxygen, 12))),
	Water			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1))),
	Ice				(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlue		, 0, Arrays.asList(new MaterialStack(Hydrogen, 2), new MaterialStack(Oxygen, 1))),
	WroughtIron		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeLightGray	, 2, Arrays.asList(new MaterialStack(Iron, 1))),
	
	/**
	 * Second Degree Compounds
	 */
	HydratedCoal	(    0,       0,          0,          0,          0,    0, false, false,   1,   9,   8, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Coal, 8), new MaterialStack(Water, 1))),
	Apatite			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeCyan		, 1, Arrays.asList(new MaterialStack(Calcium, 5), new MaterialStack(Phosphate, 3), new MaterialStack(Chlorine, 1))),
	Alumite			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePink		, 2, Arrays.asList(new MaterialStack(Aluminium, 5), new MaterialStack(Iron, 2), new MaterialStack(Obsidian, 2))),
	Manyullyn		(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Cobalt, 1), new MaterialStack(Aredrite, 1))),
	IronWood		(    0,       0,          0,          0,          0,    0, false, false,   2,  19,  18, Dyes.dyeBrown		, 2, Arrays.asList(new MaterialStack(Iron, 9), new MaterialStack(Liveroot, 9), new MaterialStack(Gold, 1))),
	ShadowIron		(    0,       0,          0,          0,          0,    0, false, false,   3,   4,   3, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Iron, 3), new MaterialStack(Magic, 1))),
	ShadowSteel		(    0,       0,          0,          0,       1700, 1700,  true, false,   4,   4,   3, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Steel, 3), new MaterialStack(Magic, 1))),
	SteelLeaf		(    0,       0,          0,          0,          0,    0, false, false,   4,   2,   1, Dyes.dyeGreen		, 2, Arrays.asList(new MaterialStack(Steel, 1), new MaterialStack(Magic, 1))),
	BlackSteel		(    0,       0,          0,          0,       1200, 1200,  true, false,   4,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Steel, 1))),
	DamascusSteel	(    0,       0,          0,          0,       1500, 1500,  true, false,   4,   1,   1, Dyes.dyeGray		, 2, Arrays.asList(new MaterialStack(Steel, 1))),
	TungstenSteel	(    0,       0,          0,          0,       3000, 3000,  true, false,   4,   1,   1, Dyes.dyeBlue		, 2, Arrays.asList(new MaterialStack(Steel, 1), new MaterialStack(Tungsten, 1))),
	NitroCoalFuel	(    0,      48,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeBlack		, 0, Arrays.asList(new MaterialStack(Glyceryl, 1), new MaterialStack(CoalFuel, 4))),
	NitroFuel		(    0,     400,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeLime		, 0, Arrays.asList(new MaterialStack(Glyceryl, 1), new MaterialStack(Fuel, 4))),
	EnderPearl		(    0,       0,      25000,          0,          0,    0, false, false,   1,  16,  10, Dyes.dyeGreen		, 1, Arrays.asList(new MaterialStack(Beryllium, 1), new MaterialStack(Potassium, 4), new MaterialStack(Nitrogen, 5), new MaterialStack(Magic, 6))),
	AstralSilver	(    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Silver, 2), new MaterialStack(Magic, 1))),
	Midasium		(    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeOrange		, 2, Arrays.asList(new MaterialStack(Gold, 2), new MaterialStack(Magic, 1))),
	Mithril			(    0,       0,          0,          0,          0,    0, false, false,   4,   3,   2, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Platinum, 2), new MaterialStack(Magic, 1))),
	Redstone		(    0,       0,       5000,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Silicon, 1), new MaterialStack(Pyrite, 5), new MaterialStack(Ruby, 1), new MaterialStack(Mercury, 3))),
	Lapis			(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeBlue		, 2, Arrays.asList(new MaterialStack(Lazurite, 12), new MaterialStack(Sodalite, 2), new MaterialStack(Pyrite, 1), new MaterialStack(Calcite, 1))),
	Phosphorus		(    0,       0,          0,          0,          0,    0, false, false,   3,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Calcium, 3), new MaterialStack(Phosphate, 2))),
	Basalt			(    0,       0,          0,          0,          0,    0, false, false,   2,   1,   1, Dyes.dyeBlack		, 2, Arrays.asList(new MaterialStack(Olivine, 1), new MaterialStack(Calcite, 3), new MaterialStack(Flint, 8), new MaterialStack(DarkAsh, 4))),
	Blaze			(    0,       0,          0,          0,          0,    0, false, false,   2,   3,   2, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(DarkAsh, 1), new MaterialStack(Sulfur, 1), new MaterialStack(Magic, 1))),
	EnderEye		(    5,      10,      50000,          0,          0,    0, false, false,   1,   2,   1, Dyes.dyeGreen		, 2, Arrays.asList(new MaterialStack(EnderPearl, 1), new MaterialStack(Blaze, 1))),
	GarnetRed		(    0,       0,          0,          0,          0,    0, false,  true,   4,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Pyrope, 3), new MaterialStack(Almandine, 5), new MaterialStack(Spessartine, 8))),
	GarnetYellow	(    0,       0,          0,          0,          0,    0, false,  true,   4,   1,   1, Dyes.dyeYellow		, 2, Arrays.asList(new MaterialStack(Andradite, 5), new MaterialStack(Grossular, 8), new MaterialStack(Uvarovite, 3))),
	Marble			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 2, Arrays.asList(new MaterialStack(Magnesium, 1), new MaterialStack(Calcite, 7))),
	Sugar			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeWhite		, 1, Arrays.asList(new MaterialStack(Carbon, 2), new MaterialStack(Water, 5), new MaterialStack(Oxygen, 25))),
	Thaumium		(    0,       0,          0,          0,          0,    0, false, false,   5,   1,   1, Dyes.dyePurple		, 0, Arrays.asList(new MaterialStack(Magic, 1))),
	Vinteum			(    5,      32,          0,          0,          0,    0, false, false,   4,   1,   1, Dyes.dyeLightBlue	, 2, Arrays.asList(new MaterialStack(Magic, 1))),
	Vis				(    5,      32,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyePurple		, 2, Arrays.asList(new MaterialStack(Magic, 1))),
	Redrock			(    0,       0,          0,          0,          0,    0, false, false,   1,   1,   1, Dyes.dyeRed			, 2, Arrays.asList(new MaterialStack(Calcite, 2), new MaterialStack(Flint, 1), new MaterialStack(Clay, 1))),
	
	/**
	 * Materials which are renamed automatically
	 */
	@Deprecated Abyssal			(Basalt),
	@Deprecated Adamantium		(Adamant),
	@Deprecated Aluminum		(Aluminium),
	@Deprecated Beryl			(Emerald),
	@Deprecated BlackGranite	(GraniteBlack),
	@Deprecated CalciumCarbonate(Calcite),
	@Deprecated CreosoteOil		(Creosote),
	@Deprecated Chromium		(Chrome),
	@Deprecated Diesel			(Fuel),
	@Deprecated Enderpearl		(EnderPearl),
	@Deprecated Endereye		(EnderEye),
	@Deprecated EyeOfEnder		(EnderEye),
	@Deprecated Eyeofender		(EnderEye),
	@Deprecated Flour			(Wheat),
	@Deprecated Garnet			(GarnetRed),
	@Deprecated Granite			(GraniteBlack),
	@Deprecated Kalium			(Potassium),
	@Deprecated Lapislazuli		(Lapis),
	@Deprecated LapisLazuli		(Lapis),
	@Deprecated Monazit			(Monazite),
	@Deprecated Natrium			(Sodium),
	@Deprecated NitroDiesel		(NitroFuel),
	@Deprecated Obby			(Obsidian),
	@Deprecated Peridot			(Olivine),
	@Deprecated Phosphorite		(Phosphorus),
	@Deprecated Quarried		(Marble),
	@Deprecated Quicksilver		(Mercury),
	@Deprecated QuickSilver		(Mercury),
	@Deprecated RedRock			(Redrock),
	@Deprecated RefinedIron		(Iron),
	@Deprecated RedGranite		(GraniteRed),
	@Deprecated Sheldonite		(Cooperite),
	@Deprecated SilverLead		(Galena),
	@Deprecated Titan			(Titanium),
	@Deprecated Uran			(Uranium),
	@Deprecated Wolframite		(Tungstate),
	@Deprecated Wolframium		(Tungsten),
	@Deprecated Wolfram			(Tungsten),
	@Deprecated WrougtIron		(WroughtIron);
	
	static {
		Pyrite			.add(SubTag.BLASTFURNACE_CALCITE_DOUBLE).add(SubTag.INDUCTIONSMELTING_LOW_OUTPUT);
		
		Iron			.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		PigIron			.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		DeepIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		ShadowIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		WroughtIron		.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		MeteoricIron	.add(SubTag.BLASTFURNACE_CALCITE_TRIPLE);
		
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
		Pyrite			.setOreMultiplier( 2).setSmeltingMultiplier( 1);
		Cinnabar		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Sphalerite		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Cassiterite		.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		NetherQuartz	.setOreMultiplier( 2).setSmeltingMultiplier( 2);
		Phosphorus		.setOreMultiplier( 3).setSmeltingMultiplier( 3);
		Sulfur			.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Bauxite			.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Apatite			.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Saltpeter		.setOreMultiplier( 4).setSmeltingMultiplier( 4);
		Nikolite		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Redstone		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Glowstone		.setOreMultiplier( 5).setSmeltingMultiplier( 5);
		Lapis			.setOreMultiplier( 6).setSmeltingMultiplier( 6);
		Sodalite		.setOreMultiplier( 6).setSmeltingMultiplier( 6);
		Lazurite		.setOreMultiplier( 6).setSmeltingMultiplier( 6);
		Monazite		.setOreMultiplier( 8).setSmeltingMultiplier( 8);
		
		Pyrite			.setDirectSmelting(Iron			);
		Tetrahedrite	.setDirectSmelting(Copper		);
		Chromite		.setOreReplacement(Chrome		).setDirectSmelting(Chrome			);
		Cobaltite		.setOreReplacement(Cobalt		).setDirectSmelting(Cobalt			);
		Cooperite		.setOreReplacement(Platinum		).setDirectSmelting(Platinum		);
		Tungstate		.setOreReplacement(Tungsten		).setDirectSmelting(Tungsten		);
		Adamantine		.setOreReplacement(Adamant		).setDirectSmelting(Adamant			);
		Cassiterite		.setOreReplacement(Tin			).setDirectSmelting(Tin				);
		
		Galena			.addOreByProduct(Sulfur			).addOreByProduct(Silver			).addOreByProduct(Lead			);
		Lapis			.addOreByProduct(Lazurite		).addOreByProduct(Sodalite			).addOreByProduct(Pyrite		);
		Pyrite			.addOreByProduct(Sulfur			).addOreByProduct(Phosphorus		).addOreByProduct(Iron			);
		Tungstate		.addOreByProduct(Manganese		).addOreByProduct(Silver			).addOreByProduct(Lithium			);
		Sphalerite		.addOreByProduct(Zinc			).addOreByProduct(GarnetYellow		);
		Iron			.addOreByProduct(Nickel			).addOreByProduct(Tin				);
		Gold			.addOreByProduct(Copper			).addOreByProduct(Nickel			);
		Copper			.addOreByProduct(Gold			).addOreByProduct(Nickel			);
		Tin				.addOreByProduct(Iron			).addOreByProduct(Zinc				);
		Antimony		.addOreByProduct(Zinc			).addOreByProduct(Iron				);
		Nickel			.addOreByProduct(Iron			).addOreByProduct(Platinum			);
		Silver			.addOreByProduct(Lead			).addOreByProduct(Sulfur			);
		Lead			.addOreByProduct(Silver			).addOreByProduct(Sulfur			);
		Cinnabar		.addOreByProduct(Redstone		).addOreByProduct(Glowstone			);
		Uranium			.addOreByProduct(Plutonium		).addOreByProduct(Lead				);
		Thorium			.addOreByProduct(Uranium		).addOreByProduct(Lead				);
		Plutonium		.addOreByProduct(Uranium		).addOreByProduct(Lead				);
		Electrum		.addOreByProduct(Gold			).addOreByProduct(Silver			);
		Bronze			.addOreByProduct(Copper			).addOreByProduct(Tin				);
		Brass			.addOreByProduct(Copper			).addOreByProduct(Zinc				);
		Coal			.addOreByProduct(Coal			).addOreByProduct(Thorium			);
		Redstone		.addOreByProduct(Cinnabar		).addOreByProduct(Glowstone			);
		Glowstone		.addOreByProduct(Redstone		).addOreByProduct(Gold				);
		Bauxite			.addOreByProduct(Grossular		).addOreByProduct(Titanium			);
		Manganese		.addOreByProduct(Chrome			).addOreByProduct(Iron				);
		Sapphire		.addOreByProduct(Aluminium		).addOreByProduct(GreenSapphire		);
		GreenSapphire	.addOreByProduct(Aluminium		).addOreByProduct(Sapphire			);
		GarnetRed		.addOreByProduct(Spessartine	).addOreByProduct(Ruby				);
		GarnetYellow	.addOreByProduct(Uvarovite		).addOreByProduct(Sphalerite		);
		Platinum		.addOreByProduct(Iridium		).addOreByProduct(Nickel			);
		Emerald			.addOreByProduct(Beryllium		).addOreByProduct(Aluminium			);
		Olivine			.addOreByProduct(Pyrope			).addOreByProduct(Magnesium			);
		Cooperite		.addOreByProduct(Palladium		).addOreByProduct(Iridium			);
		Chrome			.addOreByProduct(Iron			).addOreByProduct(Magnesium			);
		Tetrahedrite	.addOreByProduct(Antimony		).addOreByProduct(Zinc				);
		Basalt			.addOreByProduct(Olivine		).addOreByProduct(DarkAsh			);
		Lazurite		.addOreByProduct(Sodalite		).addOreByProduct(Lapis				);
		Sodalite		.addOreByProduct(Lazurite		).addOreByProduct(Lapis				);
		Ruby			.addOreByProduct(Chrome			).addOreByProduct(GarnetRed			);
		PigIron			.addOreByProduct(Iron			);
		Calcite			.addOreByProduct(Andradite		);
		Apatite			.addOreByProduct(Phosphorus		);
		Zinc			.addOreByProduct(Tin			);
		Nikolite		.addOreByProduct(Diamond		);
		Monazite		.addOreByProduct(Thorium		);
		NetherQuartz	.addOreByProduct(Netherrack		);
		DeepIron		.addOreByProduct(Iron			);
		ShadowIron		.addOreByProduct(Iron			);
		Midasium		.addOreByProduct(Gold			);
		AstralSilver	.addOreByProduct(Silver			);
		Steel			.addOreByProduct(Iron			);
		Phosphorus		.addOreByProduct(Apatite		);
		Netherrack		.addOreByProduct(Sulfur			);
		Flint			.addOreByProduct(Obsidian		);
		Sulfur			.addOreByProduct(Sulfur			);
		Saltpeter		.addOreByProduct(Saltpeter		);
		Endstone		.addOreByProduct(Helium_3		);
		Pyrope			.addOreByProduct(GarnetRed		);
		Almandine		.addOreByProduct(GarnetRed		);
		Spessartine		.addOreByProduct(GarnetRed		);
		Andradite		.addOreByProduct(GarnetYellow	);
		Grossular		.addOreByProduct(GarnetYellow	);
		Uvarovite		.addOreByProduct(GarnetYellow	);
		Diamond			.addOreByProduct(Coal			);
		Osmium			.addOreByProduct(Iridium		);
		Iridium			.addOreByProduct(Platinum		).addOreByProduct(Osmium			);
		Magnesium		.addOreByProduct(Olivine		);
		Aluminium		.addOreByProduct(Bauxite		);
		Titanium		.addOreByProduct(Almandine		);
		Chromite		.addOreByProduct(Iron			);
		Tungsten		.addOreByProduct(Manganese		);
		Obsidian		.addOreByProduct(Olivine		);
		Ash				.addOreByProduct(Carbon			);
		DarkAsh			.addOreByProduct(Carbon			);
		Redrock			.addOreByProduct(Clay			);
		Marble			.addOreByProduct(Calcite		);
		Clay			.addOreByProduct(Clay			);
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
			if (tMaterial.mBlastFurnaceRequired) tMaterial.mBlastFurnaceRequired = aConfiguration.addAdvConfig(GT_ConfigCategories.blastfurnacerequirements, tString, true);
			if (tMaterial.mAmplificationValue > 0) tMaterial.mAmplificationValue = aConfiguration.addAdvConfig("UUM.MaterialCost", tString, tMaterial.mAmplificationValue);
			if (tMaterial.mUUMEnergy > 0) tMaterial.mUUMEnergy = aConfiguration.addAdvConfig("UUM.EnergyCost", tString, tMaterial.mUUMEnergy);
		}
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
		return getToolTip(1);
	}
	
	public String getToolTip(long aMultiplier) {
		if (getDensity() * aMultiplier >= GregTech_API.MATERIAL_UNIT * 2) {
			if (mElement != null || mMaterialList.size() < 2) {
				return mChemicalFormula + ((getDensity() * aMultiplier) / GregTech_API.MATERIAL_UNIT);
			} else {
				return "(" + mChemicalFormula + ")" + ((getDensity() * aMultiplier) / GregTech_API.MATERIAL_UNIT);
			}
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
	 * This is used to determine if an ItemStack belongs to this Material.
	 */
	public boolean contains(ItemStack aStack) {
		if (aStack == null) return false;
		for (ItemStack tStack : mMaterialItems) if (GT_Utility.areStacksEqual(aStack, tStack)) return true;
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
		if (!mOreByProducts.contains(aMaterial)) mOreByProducts.add(aMaterial);
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
		if (aMaterial != null) mDirectSmelting = aMaterial;
		return this;
	}
	
	/**
	 * This Material should be the Main Material this Ore gets ground into.
	 * Example, Chromite giving Chrome or Tungstate giving Tungsten.
	 */
	public Materials setOreReplacement(Materials aMaterial) {
		if (aMaterial != null) mOreReplacement = aMaterial;
		return this;
	}
	
	public boolean mBlastFurnaceRequired = false, mTransparent = false;
	public String mChemicalFormula = "";
	public Dyes mColor = Dyes._NULL;
	public short mMeltingPoint = 0, mBlastFurnaceTemp = 0;
	public int mAmplificationValue = 0, mUUMEnergy = 0, mFuelPower = 0, mFuelType = 0, mExtraData = 0, mOreValue = 0, mOreMultiplier = 1, mSmeltingMultiplier = 1;
	public long mDensity = GregTech_API.MATERIAL_UNIT;
	public Element mElement = null;
	public Materials mDirectSmelting = this, mOreReplacement = this;
	public final Materials mMaterialInto;
	public final List<MaterialStack> mMaterialList = new ArrayList<MaterialStack>();
	public final List<Materials> mOreByProducts = new ArrayList<Materials>();
	public FluidStack mSolid = null, mFluid = null, mGas = null, mPlasma = null;
	
	private Materials() {
		mMaterialInto = this;
	}
	
	private Materials(Materials aMaterialInto) {
		mMaterialInto = aMaterialInto.mMaterialInto;
	}
	
	/**
	 * @param aFuelType Type of Generator to get Energy from this Material.
	 * @param aFuelPower EU generated. Will be multiplied by 1000, also additionally multiplied by 2 for Gems.
	 * @param aAmplificationValue Amount of UUM amplifier gotten from this.
	 * @param aUUMEnergy Amount of EU needed to shape the UUM into this Material.
	 * @param aMeltingPoint Used to determine the smelting Costs in Furnii.
	 * @param aBlastFurnaceTemp Used to determine the needed Heat capactiy Costs in Blast Furnii.
	 * @param aBlastFurnaceRequired If this requires a Blast Furnace.
	 */
	private Materials(int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor) {
		this();
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
	}
	
	/**
	 * @param aElement The Element Enum represented by this Material
	 */
	private Materials(int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, Element aElement) {
		this(aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
		mElement = aElement;
		mElement.mLinkedMaterials.add(this);
		mChemicalFormula = aElement.toString();
		mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
	}
	
	private Materials(int aFuelType, int aFuelPower, int aAmplificationValue, int aUUMEnergy, int aMeltingPoint, int aBlastFurnaceTemp, boolean aBlastFurnaceRequired, boolean aTransparent, int aOreValue, int aDensityMultiplier, int aDensityDivider, Dyes aColor, int aExtraData, List<MaterialStack> aMaterialList) {
		this(aFuelType, aFuelPower, aAmplificationValue, aUUMEnergy, aMeltingPoint, aBlastFurnaceTemp, aBlastFurnaceRequired, aTransparent, aOreValue, aDensityMultiplier, aDensityDivider, aColor);
		mExtraData = aExtraData;
		mMaterialList.addAll(aMaterialList);
		mChemicalFormula = "";
		for (MaterialStack tMaterial : mMaterialList) mChemicalFormula += tMaterial.toString();
		mChemicalFormula = mChemicalFormula.replaceAll("_", "-");
	}
	
	public static volatile int VERSION = 404;
}