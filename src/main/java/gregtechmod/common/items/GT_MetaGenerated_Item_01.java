package gregtechmod.common.items;

import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;

public class GT_MetaGenerated_Item_01 extends GT_MetaGenerated_Item {

   public GT_MetaGenerated_Item_01() {
      super("GregTech_MetaGenerated_Item_01", new OrePrefixes[]{OrePrefixes.dustTiny, OrePrefixes.dustSmall, OrePrefixes.dust, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.gem, OrePrefixes.nugget, null, OrePrefixes.ingot, OrePrefixes.ingotHot, OrePrefixes.ingotDouble, OrePrefixes.ingotTriple, OrePrefixes.ingotQuadruple, OrePrefixes.ingotQuintuple, OrePrefixes.plate, OrePrefixes.plateDouble, OrePrefixes.plateTriple, OrePrefixes.plateQuadruple, OrePrefixes.plateQuintuple, OrePrefixes.plateDense, OrePrefixes.stick, OrePrefixes.lense, OrePrefixes.round, OrePrefixes.bolt, OrePrefixes.screw, OrePrefixes.ring, null, OrePrefixes.cell, OrePrefixes.cellPlasma});
   }

   public String getDefaultLocalization(OrePrefixes aPrefix, Materials aMaterial, int aMetaData) {
      switch(aMaterial) {
      case Wheat:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "Flour";
         }
         break;
      case Ice:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "crushed Ice";
         }
         break;
      case Wood:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
         }

         if(aPrefix.name().startsWith("nugget")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
         }
         break;
      case Plastic:
      case Rubber:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Pulp";
         }

         if(aPrefix.name().startsWith("plate")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Sheet";
         }

         if(aPrefix.name().startsWith("ingot")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Bar";
         }

         if(aPrefix.name().startsWith("nugget")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Chip";
         }
         break;
      case SteelLeaf:
         if(aPrefix.name().startsWith("ingot")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }
      case Blaze:
      case Milk:
      case Chocolate:
      case Coffee:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + " Powder";
         }
         break;
      case Paper:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + "Chad";
         }

         if(aPrefix == OrePrefixes.plate) {
            return "Sheet of Paper";
         }

         if(aPrefix == OrePrefixes.plateDouble) {
            return "Paperboard";
         }

         if(aPrefix == OrePrefixes.plateTriple) {
            return "Carton";
         }

         if(aPrefix == OrePrefixes.plateQuadruple) {
            return "Cardboard";
         }

         if(aPrefix == OrePrefixes.plateQuintuple) {
            return "Thick Cardboard";
         }

         if(aPrefix == OrePrefixes.plateDense) {
            return "Strong Cardboard";
         }
         break;
      case Ash:
      case DarkAsh:
      case Gunpowder:
      case Sugar:
      case Salt:
      case RockSalt:
      case VolcanicAsh:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }
         break;
      case Vermiculite:
      case Bentonite:
      case Kaolinite:
      case Talc:
      case BasalticMineralSand:
      case GraniticMineralSand:
      case CassiteriteSand:
      case GarnetSand:
      case QuartzSand:
      case Pitchblende:
      case FullersEarth:
         if(aPrefix.name().startsWith("dust")) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushedCentrifuged) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushedPurified) {
            return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName;
         }

         if(aPrefix == OrePrefixes.crushed) {
            return "Ground " + aMaterial.mDefaultLocalName;
         }
      default: break;
      }

      return super.getDefaultLocalization(aPrefix, aMaterial, aMetaData);
   }

   public String getOreDictString(OrePrefixes aPrefix, Materials aMaterial) {
      return aMaterial == Materials.Wood && aPrefix == OrePrefixes.plate?"plankWood":super.getOreDictString(aPrefix, aMaterial);
   }

   public IIconContainer getIconContainer(int aMetaData, Materials aMaterial) {
      return aMaterial.mIconSet[aMetaData / 1000];
   }

   public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
      return aDoShowAllItems || aPrefix != OrePrefixes.nugget && aPrefix != OrePrefixes.dustTiny && aPrefix != OrePrefixes.dustSmall && aPrefix != OrePrefixes.dustImpure && aPrefix != OrePrefixes.dustPure && aPrefix != OrePrefixes.crushed && aPrefix != OrePrefixes.crushedPurified && aPrefix != OrePrefixes.crushedCentrifuged && aPrefix != OrePrefixes.ingotHot && aPrefix != OrePrefixes.cellPlasma;
   }

   public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
      if(!super.doesMaterialAllowGeneration(aPrefix, aMaterial)) {
         return false;
      } else {
         switch(aPrefix) {
         case dustTiny:
            return (aMaterial.mTypes & 15) != 0;
         case dustSmall:
            return (aMaterial.mTypes & 15) != 0;
         case dust:
            return (aMaterial.mTypes & 15) != 0;
         case dustImpure:
            return (aMaterial.mTypes & 8) != 0 || aMaterial == Materials.GraniteRed || aMaterial == Materials.GraniteBlack || aMaterial == Materials.Quartzite || aMaterial == Materials.Flint || aMaterial == Materials.Redrock || aMaterial == Materials.Basalt || aMaterial == Materials.Marble || aMaterial == Materials.Netherrack || aMaterial == Materials.Endstone;
         case dustPure:
            return (aMaterial.mTypes & 8) != 0;
         case crushed:
            return (aMaterial.mTypes & 8) != 0;
         case crushedPurified:
            return (aMaterial.mTypes & 8) != 0;
         case crushedCentrifuged:
            return (aMaterial.mTypes & 8) != 0;
         case gem:
            return (aMaterial.mTypes & 4) != 0;
         case nugget:
            return (aMaterial.mTypes & 2) != 0;
         case ingot:
            return (aMaterial.mTypes & 2) != 0;
         case ingotHot:
            return (aMaterial.mTypes & 2) != 0 && aMaterial.mBlastFurnaceTemp > 1750;
         case plate:
            return (aMaterial.mTypes & 6) != 0 || aMaterial == Materials.Paper || aMaterial == Materials.Redstone || aMaterial == Materials.GraniteRed || aMaterial == Materials.GraniteBlack || aMaterial == Materials.Glowstone || aMaterial == Materials.Nikolite || aMaterial == Materials.Obsidian || aMaterial == Materials.Wood;
         case plateDouble:
            return aMaterial == Materials.Paper;
         case plateTriple:
            return aMaterial == Materials.Paper;
         case plateQuadruple:
            return aMaterial == Materials.Paper;
         case plateQuintuple:
            return aMaterial == Materials.Paper;
         case stick:
            return (aMaterial.mTypes & 2) != 0;
         case lense:
            return (aMaterial.mTypes & 4) != 0 && aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL || aMaterial == Materials.EnderPearl || aMaterial == Materials.EnderEye;
         case round:
            return (aMaterial.mTypes & 2) != 0;
         case bolt:
            return (aMaterial.mTypes & 2) != 0;
         case screw:
            return (aMaterial.mTypes & 2) != 0;
         case ring:
            return (aMaterial.mTypes & 2) != 0;
         case cell:
            return (aMaterial.mTypes & 16) != 0;
         case cellPlasma:
            return (aMaterial.mTypes & 32) != 0;
         default:
            return false;
         }
      }
   }
}
