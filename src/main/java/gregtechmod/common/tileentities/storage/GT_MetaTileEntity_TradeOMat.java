package gregtechmod.common.tileentities.storage;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_TradeOMat extends MetaTileEntity {

   public int mPerformedTrades = 0;
   public boolean mIsWorking = true;
   private static final short SLOT_ENERGY = 66;
   private static final short HOLO_SLOT_MONEY = 64;
   private static final short HOLO_SLOT_OBJECT = 65;
   private static final short START_OBJECTS_TO_SEND = 0;
   private static final short RANGE_OBJECTS_TO_SEND = 27;
   private static final short START_MONEY_TO_RECEIVE = 27;
   private static final short RANGE_MONEY_TO_RECEIVE = 27;
   private static final short START_MONEY_TO_SEND = 54;
   private static final short RANGE_MONEY_TO_SEND = 5;
   private static final short START_OBJECTS_TO_RECEIVE = 59;
   private static final short RANGE_OBJECTS_TO_RECEIVE = 5;


   public GT_MetaTileEntity_TradeOMat(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_TradeOMat() {}

   public boolean isTransformerUpgradable() {
      return true;
   }

   public boolean isOverclockerUpgradable() {
      return false;
   }

   public boolean isBatteryUpgradable() {
      return true;
   }

   public boolean isSimpleMachine() {
      return true;
   }

   public int getInvSize() {
      return 67;
   }

   public boolean isValidSlot(int aIndex) {
      return aIndex != 64 && aIndex != 65;
   }

   public boolean isFacingValid(byte aFacing) {
      return aFacing > 1;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean unbreakable() {
      return true;
   }

   public boolean isEnetOutput() {
      return false;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isOutputFacing(byte aSide) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return true;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

   public int getMinimumStoredEU() {
      return 1500;
   }

   public int maxEUInput() {
      return 32;
   }

   public int maxEUStore() {
      return 2000;
   }

   public int maxMJStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public int dechargerSlotStartIndex() {
      return 66;
   }

   public int dechargerSlotCount() {
      return 1;
   }

   public int getProgresstime() {
      return super.mInventory[64] == null?0:this.getAmountMoney() * 100 / super.mInventory[64].getMaxStackSize();
   }

   public int maxProgresstime() {
      return this.mIsWorking?1600:0;
   }

   public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
      if(aSide == this.getBaseMetaTileEntity().getFrontFacing()) {
         if(this.getBaseMetaTileEntity().getOwnerName().equalsIgnoreCase(aPlayer.username)) {
            float[] tCoords = GT_Utility.getClickedFacingCoords(aSide, aX, aY, aZ);
            switch((byte)((byte)((int)(tCoords[0] * 2.0F)) + 2 * (byte)((int)(tCoords[1] * 2.0F)))) {
            case 0:
               return this.getBaseMetaTileEntity().openGUI(aPlayer, 176);
            case 1:
            default:
               break;
            case 2:
               return this.getBaseMetaTileEntity().openGUI(aPlayer, 178);
            case 3:
               return this.getBaseMetaTileEntity().openGUI(aPlayer, 179);
            }
         }

         return this.getBaseMetaTileEntity().openGUI(aPlayer, 177);
      } else if(aSide == this.getBaseMetaTileEntity().getBackFacing()) {
         if(this.getBaseMetaTileEntity().getOwnerName().equalsIgnoreCase(aPlayer.username)) {
            this.getBaseMetaTileEntity().openGUI(aPlayer, 178);
         }

         return true;
      } else {
         return false;
      }
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_TradeOMat();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.setInteger("mPerformedTrades", this.mPerformedTrades);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mPerformedTrades = aNBT.getInteger("mPerformedTrades");
   }

   public void onPreTick() {
      if(this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().getTimer() % 5L == 0L) {
         if(this.getBaseMetaTileEntity().hasInventoryBeenModified()) {
            this.fillStacksIntoFirstSlots();
         }

         if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isUniversalEnergyStored(1024)) {
            if(GT_Utility.isDebugItem(super.mInventory[66])) {
               if(super.mInventory[27] != null) {
                  this.fillStacksIntoFirstSlots();
                  super.mInventory[27] = null;
               }

               super.mInventory[0] = GT_Utility.copy(new Object[]{super.mInventory[65]});
            }

            this.mIsWorking = true;
            if(super.mInventory[27] == null && GT_Utility.isStackValid(super.mInventory[64]) && GT_Utility.isStackValid(super.mInventory[65]) && GT_Utility.areStacksEqual(super.mInventory[65], super.mInventory[0]) && super.mInventory[0].stackSize >= super.mInventory[65].stackSize) {
               if(super.mInventory[59] == null && GT_Utility.areStacksEqual(super.mInventory[64], super.mInventory[54]) && super.mInventory[54].stackSize >= super.mInventory[64].stackSize) {
                  byte tCost = GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 54, 27, (byte)super.mInventory[64].stackSize, (byte)super.mInventory[64].stackSize, (byte)super.mInventory[64].stackSize, (byte)super.mInventory[64].stackSize);
                  if(tCost > 0) {
                     int tCost1 = tCost + GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 0, 59, (byte)super.mInventory[65].stackSize, (byte)super.mInventory[65].stackSize, (byte)super.mInventory[65].stackSize, (byte)super.mInventory[65].stackSize);
                     this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost1 * 8, true);
                     ++this.mPerformedTrades;
                     this.sendSound((byte)10);
                     this.fillStacksIntoFirstSlots();
                  }
               }
            } else {
               this.mIsWorking = false;
            }
         } else {
            this.mIsWorking = false;
         }
      }

   }

   public void doSound(byte aIndex, double aX, double aY, double aZ) {
      super.doSound(aIndex, aX, aY, aZ);
      if(aIndex == 10) {
         GT_Utility.doSoundAtClient((String)GregTech_API.sSoundList.get(Integer.valueOf(211)), 20, 1.0F, 0.01F, aX, aY, aZ);
      }

   }

   private void fillStacksIntoFirstSlots() {
      int i;
      if(super.mInventory[27] != null) {
         for(i = 1; i < 27; ++i) {
            GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 27, 27 + i, (byte)64, (byte)1, (byte)64, (byte)1);
         }
      }

      if(super.mInventory[59] != null) {
         for(i = 1; i < 5; ++i) {
            GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 59, 59 + i, (byte)64, (byte)1, (byte)64, (byte)1);
         }
      }

      if(GT_Utility.isStackValid(super.mInventory[65])) {
         if(!GT_Utility.areStacksEqual(super.mInventory[65], super.mInventory[0])) {
            for(i = 1; i < 27; ++i) {
               GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 0, 0 + i, (byte)64, (byte)1, (byte)64, (byte)1);
            }
         }

         for(i = 1; i < 27; ++i) {
            if(GT_Utility.areStacksEqual(super.mInventory[65], super.mInventory[0 + i])) {
               GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 0 + i, 0, (byte)64, (byte)1, (byte)64, (byte)1);
            }
         }
      }

      if(GT_Utility.isStackValid(super.mInventory[64])) {
         if(!GT_Utility.areStacksEqual(super.mInventory[64], super.mInventory[54])) {
            for(i = 1; i < 5; ++i) {
               GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 54, 54 + i, (byte)64, (byte)1, (byte)64, (byte)1);
            }
         }

         for(i = 1; i < 5; ++i) {
            if(GT_Utility.areStacksEqual(super.mInventory[64], super.mInventory[54 + i])) {
               GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 54 + i, 54, (byte)64, (byte)1, (byte)64, (byte)1);
            }
         }
      }

   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex >= 59 && aIndex < 64;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return aIndex >= 54 && aIndex < 59 && GT_Utility.areStacksEqual(super.mInventory[64], aStack);
   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getFrontFacing() || GregTech_API.getCoverBehavior(aCoverID).isGUIClickable(aSide, aCoverID, 0, this.getBaseMetaTileEntity());
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      return aSide == 0?32:(aSide == 1?29:(aSide == aFacing?309:(aSide == GT_Utility.getOppositeSide(aFacing)?214:40)));
   }

   private int getAmountOffered() {
      int rAmount = 0;

      for(int i = 0; i < 27; ++i) {
         if(GT_Utility.areStacksEqual(super.mInventory[i + 0], super.mInventory[65])) {
            rAmount += super.mInventory[i + 0].stackSize;
         }
      }

      return rAmount;
   }

   private int getAmountMoney() {
      int rAmount = 0;

      for(int i = 0; i < 27; ++i) {
         if(GT_Utility.areStacksEqual(super.mInventory[i + 27], super.mInventory[64])) {
            rAmount += super.mInventory[i + 27].stackSize;
         }
      }

      return rAmount;
   }

   public float getExplosionResistance(byte aSide) {
      return GT_Utility.isDebugItem(super.mInventory[66])?Float.MAX_VALUE:10.0F;
   }

   public String[] getInfoData() {
      return !GT_Utility.isStackInvalid(super.mInventory[65]) && !GT_Utility.isStackInvalid(super.mInventory[64])?new String[]{"Performed Trades: " + this.mPerformedTrades, "Stock: " + (GT_Utility.isDebugItem(super.mInventory[66])?"Infinite":Integer.valueOf(this.getAmountOffered())), "Received: " + this.getAmountMoney(), "Selling: " + super.mInventory[65].stackSize + " \\\\" + GT_LanguageManager.getTranslateableItemStackName(super.mInventory[65]), "Buying: " + super.mInventory[64].stackSize + " \\\\" + GT_LanguageManager.getTranslateableItemStackName(super.mInventory[64]), "For: " + super.mInventory[65].stackSize + " \\\\" + GT_LanguageManager.getTranslateableItemStackName(super.mInventory[65]), "For: " + super.mInventory[64].stackSize + " \\\\" + GT_LanguageManager.getTranslateableItemStackName(super.mInventory[64])}:new String[0];
   }

   public boolean isGivingInformation() {
      return true;
   }

   public String getDescription() {
      return "Electric Trading Machine";
   }
}
