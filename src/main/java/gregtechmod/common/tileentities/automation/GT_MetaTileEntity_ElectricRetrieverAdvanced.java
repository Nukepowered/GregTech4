package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricRetrieverAdvanced extends MetaTileEntity {

   public int[] mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   public boolean mPartialRequests = false;
   public boolean bOutput = false;


   public GT_MetaTileEntity_ElectricRetrieverAdvanced(int aID, String aName) {
      super(aID, aName);
   }

   public GT_MetaTileEntity_ElectricRetrieverAdvanced() {}

   public boolean isFacingValid(byte aFacing) {
      return true;
   }

   public boolean isEnetInput() {
      return true;
   }

   public boolean isEnetOutput() {
      return true;
   }

   public boolean isTeleporterCompatible() {
      return false;
   }

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
      return false;
   }

   public int maxEUOutput() {
      return this.bOutput?32:0;
   }

   public int maxEUInput() {
      return 128;
   }

   public int maxEUPulses() {
      return 4;
   }

   public int maxEUStore() {
      return 10000;
   }

   public int maxRFStore() {
      return this.maxEUStore();
   }

   public int maxSteamStore() {
      return this.maxEUStore();
   }

   public void onRightclick(EntityPlayer aPlayer) {
      this.getBaseMetaTileEntity().openGUI(aPlayer, 182);
   }

   public boolean isValidSlot(int aIndex) {
      return false;
   }

   public boolean isInputFacing(byte aSide) {
      return !this.isOutputFacing(aSide);
   }

   public boolean isOutputFacing(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getBackFacing();
   }

   public int getMinimumStoredEU() {
      return 2500;
   }

   public int getInvSize() {
      return 10;
   }

   public boolean isAccessAllowed(EntityPlayer aPlayer) {
      return true;
   }

   public boolean connectsToItemPipe(byte aSide) {
      return aSide == this.getBaseMetaTileEntity().getFrontFacing();
   }

   public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
      return new GT_MetaTileEntity_ElectricRetrieverAdvanced();
   }

   public void saveNBTData(NBTTagCompound aNBT) {
      aNBT.setInteger("mTargetSlot1", this.mTargetSlots[0]);
      aNBT.setInteger("mTargetSlot2", this.mTargetSlots[1]);
      aNBT.setInteger("mTargetSlot3", this.mTargetSlots[2]);
      aNBT.setInteger("mTargetSlot4", this.mTargetSlots[3]);
      aNBT.setInteger("mTargetSlot5", this.mTargetSlots[4]);
      aNBT.setInteger("mTargetSlot6", this.mTargetSlots[5]);
      aNBT.setInteger("mTargetSlot7", this.mTargetSlots[6]);
      aNBT.setInteger("mTargetSlot8", this.mTargetSlots[7]);
      aNBT.setInteger("mTargetSlot9", this.mTargetSlots[8]);
      aNBT.setBoolean("mPartialRequests", this.mPartialRequests);
      aNBT.setBoolean("bOutput", this.bOutput);
   }

   public void loadNBTData(NBTTagCompound aNBT) {
      this.mTargetSlots[0] = aNBT.getInteger("mTargetSlot1");
      this.mTargetSlots[1] = aNBT.getInteger("mTargetSlot2");
      this.mTargetSlots[2] = aNBT.getInteger("mTargetSlot3");
      this.mTargetSlots[3] = aNBT.getInteger("mTargetSlot4");
      this.mTargetSlots[4] = aNBT.getInteger("mTargetSlot5");
      this.mTargetSlots[5] = aNBT.getInteger("mTargetSlot6");
      this.mTargetSlots[6] = aNBT.getInteger("mTargetSlot7");
      this.mTargetSlots[7] = aNBT.getInteger("mTargetSlot8");
      this.mTargetSlots[8] = aNBT.getInteger("mTargetSlot9");
      this.mPartialRequests = aNBT.getBoolean("mPartialRequests");
      this.bOutput = aNBT.getBoolean("bOutput");
   }

   public void onPostTick() {
      if(this.getBaseMetaTileEntity().isAllowedToWork() && this.getBaseMetaTileEntity().isServerSide() && this.getBaseMetaTileEntity().isUniversalEnergyStored(2000) && (this.getBaseMetaTileEntity().hasWorkJustBeenEnabled() || this.getBaseMetaTileEntity().getTimer() % 50L == 0L)) {
         IInventory tInventory = this.getBaseMetaTileEntity().getIInventoryAtSide(this.getBaseMetaTileEntity().getBackFacing());
         IGregTechTileEntity tConnectedPipe = this.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(this.getBaseMetaTileEntity().getFrontFacing());
         if(tInventory != null && tConnectedPipe != null && tConnectedPipe.getMetaTileEntity() != null && tConnectedPipe.getMetaTileEntity() instanceof GT_MetaPipeEntity_Item) {
            boolean temp = false;
            boolean[] tNeedsItems = new boolean[9];

            for(int tMap = 0; tMap < 9; ++tMap) {
               tNeedsItems[tMap] = false;
               if(super.mInventory[tMap] != null) {
                  ItemStack tList = tInventory.getStackInSlot(this.mTargetSlots[tMap]);
                  if(tList == null || GT_Utility.areStacksEqual(tList, super.mInventory[tMap]) && tList.stackSize < super.mInventory[tMap].stackSize) {
                     temp = true;
                     tNeedsItems[tMap] = true;
                  }
               }
            }

            if(temp) {
               super.doTickProfilingInThisTick = false;
               LinkedHashMap<GT_MetaPipeEntity_Item, Long> pipes = GT_Utility.sortMapByValuesAcending(GT_Utility.scanPipes((GT_MetaPipeEntity_Item) tConnectedPipe.getMetaTileEntity(), new HashMap<>(), 0, true));
               List<GT_MetaPipeEntity_Item> pipes1 = new ArrayList<>();

               for (GT_MetaPipeEntity_Item tPipe : pipes.keySet()) {
            	   if (!temp) {
            		   GT_MetaPipeEntity_Item tmpPipe;
                       for(Iterator<GT_MetaPipeEntity_Item> iter = pipes1.iterator(); iter.hasNext(); ++tmpPipe.mTransferredItems) {
                    	   tmpPipe = iter.next();
                       }
                       
                       return;
            	   }
            	   
            	   pipes1.add(tPipe);
            	   
            	   for (byte tSide = 0; temp && tSide < 6; ++tSide) {
            		   if (tPipe.getBaseMetaTileEntity().getCoverBehaviorAtSide(tSide).letsItemsIn(tSide, tPipe.getBaseMetaTileEntity().getCoverIDAtSide(tSide), tPipe.getBaseMetaTileEntity().getCoverDataAtSide(tSide), tPipe.getBaseMetaTileEntity())) {
            			   IInventory tTileEntity = tPipe.getBaseMetaTileEntity().getIInventoryAtSide(tSide);
            			   if (tTileEntity != tInventory) {
            				   for (int i = 0; temp && i < 9; ++i) {
            					   if (tNeedsItems[i]) {
                                     int itemsMoved = GT_Utility.moveOneItemStackIntoSlot(tTileEntity, tInventory, GT_Utility.getOppositeSide(tSide), this.mTargetSlots[i], Arrays.asList(new ItemStack[]{super.mInventory[i]}), false, (byte)super.mInventory[i].stackSize, this.mPartialRequests?1:(byte)super.mInventory[i].stackSize, (byte)64, (byte)1) * 20;
                                     if(itemsMoved > 0) {
                                        this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(itemsMoved, true);
                                        temp = false;
                                     }
            					   }
            				   }
            			   }
            		   }
            	   }
               }
            }
         }
      }

   }

   public boolean allowCoverOnSide(byte aSide, int aCoverID) {
      return aSide != this.getBaseMetaTileEntity().getBackFacing() && aSide != this.getBaseMetaTileEntity().getFrontFacing();
   }

   public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
      return false;
   }

   public String getDescription() {
      return "metatileentity.GT_RetrieverAdvanced.tooltip";
   }

   public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
      if(aSide == aFacing) {
         return 112 + (aRedstone?8:0);
      } else if(GT_Utility.getOppositeSide(aSide) == aFacing) {
         return 113 + (aRedstone?8:0);
      } else {
         int tIndex = 135 + (aRedstone?8:0);
         switch(aFacing) {
         case 0:
            return tIndex + 64;
         case 1:
            return tIndex + 32;
         case 2:
            switch(aSide) {
            case 0:
               return tIndex + 32;
            case 1:
               return tIndex + 32;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 16;
            case 5:
               return tIndex + 48;
            }
         case 3:
            switch(aSide) {
            case 0:
               return tIndex + 64;
            case 1:
               return tIndex + 64;
            case 2:
            case 3:
            default:
               break;
            case 4:
               return tIndex + 48;
            case 5:
               return tIndex + 16;
            }
         case 4:
            switch(aSide) {
            case 0:
               return tIndex + 48;
            case 1:
               return tIndex + 16;
            case 2:
               return tIndex + 48;
            case 3:
               return tIndex + 16;
            }
         case 5:
            switch(aSide) {
            case 0:
               return tIndex + 16;
            case 1:
               return tIndex + 48;
            case 2:
               return tIndex + 16;
            case 3:
               return tIndex + 48;
            }
         default:
            return tIndex;
         }
      }
   }
}
