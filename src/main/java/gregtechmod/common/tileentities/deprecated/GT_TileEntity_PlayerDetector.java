package gregtechmod.common.tileentities.deprecated;

import gregtechmod.api.GregTech_API;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_TileEntity_PlayerDetector extends GT_TileEntityMetaID_Machine {

   public float getWrenchDropRate() {
      return 1.0F;
   }

   public ItemStack getWrenchDrop(EntityPlayer aPlayer) {
      return new ItemStack(GregTech_API.sBlockList[1], 1, 77);
   }

   public String getDescription() {
      return "Place and Wrench to get the Functioning Version.";
   }

   public int getTexture(int aSide, int aMeta) {
      return 23;
   }
}
