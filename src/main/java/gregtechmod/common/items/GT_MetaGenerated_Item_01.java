package gregtechmod.common.items;

import java.util.HashMap;

import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IFoodStat;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import net.minecraft.item.ItemStack;

public class GT_MetaGenerated_Item_01 extends GT_MetaGenerated_Item {
   
   private final HashMap<Integer, String> sUnlocalizedNames = new HashMap<>();
	
   public GT_MetaGenerated_Item_01() {
      super("GregTech_MetaGenerated_Item_01", new OrePrefixes[]{OrePrefixes.dustTiny, OrePrefixes.dustSmall, OrePrefixes.dust, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.gem, OrePrefixes.nugget, null, OrePrefixes.ingot, OrePrefixes.ingotHot, OrePrefixes.ingotDouble, OrePrefixes.ingotTriple, OrePrefixes.ingotQuadruple, OrePrefixes.ingotQuintuple, OrePrefixes.plate, OrePrefixes.plateDouble, OrePrefixes.plateTriple, OrePrefixes.plateQuadruple, OrePrefixes.plateQuintuple, OrePrefixes.plateDense, OrePrefixes.stick, OrePrefixes.lense, OrePrefixes.round, OrePrefixes.bolt, OrePrefixes.screw, OrePrefixes.ring, null, OrePrefixes.cell, OrePrefixes.cellPlasma});
   }
   
   public final ItemStack addItem(int aID, String aUnlocalizedName, String aToolTip, IFoodStat aFoodBehavior, Object... aOreDictNames) {
	   ItemStack aStack = addItem(aID, aToolTip, aFoodBehavior, aOreDictNames);
	   if (aStack != null) {
		   sUnlocalizedNames.put(32000+aID, aUnlocalizedName);
	   }
	   
	   return aStack;
   }
   
   @Override
   public String getUnlocalizedName(ItemStack aStack) {
	   String subName = null;
	   
	   try {
		   int aMeta = aStack.getItemDamage();
		   subName = sUnlocalizedNames.get(aMeta);
	   } catch (Throwable e) {}
	   
	   if (subName == null || subName.isEmpty()) {
		   return getUnlocalizedName() + "." + getDamage(aStack);
	   } else {
		   return getUnlocalizedName() + "." + subName;
	   }
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
