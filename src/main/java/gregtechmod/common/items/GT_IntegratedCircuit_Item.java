package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.items.GT_Generic_Item;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_IntegratedCircuit_Item extends GT_Generic_Item {

   public GT_IntegratedCircuit_Item(String aUnlocalized) {
      super(aUnlocalized, "");
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
   }

   public ItemStack func_77659_a(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
      return aStack;
   }

   public void addAdditionalToolTips(List<String> aList, ItemStack aStack) {
      super.addAdditionalToolTips(aList, aStack);
      aList.add(I18n.format("util.GT_IntegratedCircuit.configuration", getConfigurationString(this.getDamage(aStack))));
   }
   
   @SideOnly(Side.CLIENT)
   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public final void getSubItems(Item var1, CreativeTabs aCreativeTab, List aList) {
      aList.add(new ItemStack(this, 1, 0));
   }

   private static String getModeString(int aMetaData) {
      switch((byte)(aMetaData >>> 8)) {
      case 0:
         return "==";
      case 1:
         return "<=";
      case 2:
         return ">=";
      case 3:
         return "<";
      case 4:
         return ">";
      default:
         return "";
      }
   }

   @SuppressWarnings("unused")
   private static String getConfigurationString(int aMetaData) {
      return getModeString(aMetaData) + " " + (byte)(aMetaData & 255);
   }
}
