package client;

import Editor.NpcSpawn;


public class Entity extends Animable {

   public int entScreenX;
   public int entScreenY;
   public final int index = -1;
   public final int[] smallX = new int[10];
   public final int[] smallY = new int[10];
   public int interactingEntity = -1;
   int anInt1503;
   int anInt1504 = 32;
   int anInt1505 = -1;
   public String textSpoken;
   public int height = 200;
   public int turnDirection;
   int anInt1511 = -1;
   int anInt1512 = -1;
   int anInt1513;
   final int[] hitArray = new int[4];
   final int[] hitMarkTypes = new int[4];
   final int[] hitsLoopCycle = new int[4];
   int anInt1517 = -1;
   int anInt1518;
   int anInt1519;
   int anInt1520 = -1;
   int anInt1521;
   int anInt1522;
   int anInt1523;
   int anInt1524;
   int smallXYIndex;
   public int anim = -1;
   int anInt1527;
   int anInt1528;
   int anInt1529;
   int anInt1530;
   int anInt1531;
   public int loopCycleStatus = -1000;
   public int currentHealth;
   public int maxHealth;
   int textCycle = 100;
   public NpcSpawn npcSpawn;
   int anInt1537;
   int anInt1538;
   int anInt1539;
   int anInt1540 = 1;
   boolean aBoolean1541 = false;
   int anInt1542;
   int anInt1543;
   int anInt1544;
   int anInt1545;
   int anInt1546;
   int anInt1547;
   int anInt1548;
   int anInt1549;
   public int x;
   public int y;
   int anInt1552;
   final boolean[] aBooleanArray1553 = new boolean[10];
   int anInt1554 = -1;
   int anInt1555 = -1;
   int anInt1556 = -1;
   int anInt1557 = -1;


   public final void setPos(int i, int j, boolean flag) {
      if(this.anim != -1 && Animation.anims[this.anim].anInt364 == 1) {
         this.anim = -1;
      }

      if(!flag) {
         int k = i - this.smallX[0];
         int l = j - this.smallY[0];
         if(k >= -8 && k <= 8 && l >= -8 && l <= 8) {
            if(this.smallXYIndex < 9) {
               ++this.smallXYIndex;
            }

            for(int i1 = this.smallXYIndex; i1 > 0; --i1) {
               this.smallX[i1] = this.smallX[i1 - 1];
               this.smallY[i1] = this.smallY[i1 - 1];
               this.aBooleanArray1553[i1] = this.aBooleanArray1553[i1 - 1];
            }

            this.smallX[0] = i;
            this.smallY[0] = j;
            this.aBooleanArray1553[0] = false;
            return;
         }
      }

      this.smallXYIndex = 0;
      this.anInt1542 = 0;
      this.anInt1503 = 0;
      this.smallX[0] = i;
      this.smallY[0] = j;
      this.x = this.smallX[0] * 128 + this.anInt1540 * 64;
      this.y = this.smallY[0] * 128 + this.anInt1540 * 64;
   }

   public final void method446() {
      this.smallXYIndex = 0;
      this.anInt1542 = 0;
   }

   public final void updateHitData(int j, int k, int l) {
      for(int i1 = 0; i1 < 4; ++i1) {
         if(this.hitsLoopCycle[i1] <= l) {
            this.hitArray[i1] = k;
            this.hitMarkTypes[i1] = j;
            this.hitsLoopCycle[i1] = l + 70;
            return;
         }
      }

   }

   public final void moveInDir(boolean flag, int i) {
      int j = this.smallX[0];
      int k = this.smallY[0];
      if(i == 0) {
         --j;
         ++k;
      }

      if(i == 1) {
         ++k;
      }

      if(i == 2) {
         ++j;
         ++k;
      }

      if(i == 3) {
         --j;
      }

      if(i == 4) {
         ++j;
      }

      if(i == 5) {
         --j;
         --k;
      }

      if(i == 6) {
         --k;
      }

      if(i == 7) {
         ++j;
         --k;
      }

      if(this.anim != -1 && Animation.anims[this.anim].anInt364 == 1) {
         this.anim = -1;
      }

      if(this.smallXYIndex < 9) {
         ++this.smallXYIndex;
      }

      for(int l = this.smallXYIndex; l > 0; --l) {
         this.smallX[l] = this.smallX[l - 1];
         this.smallY[l] = this.smallY[l - 1];
         this.aBooleanArray1553[l] = this.aBooleanArray1553[l - 1];
      }

      this.smallX[0] = j;
      this.smallY[0] = k;
      this.aBooleanArray1553[0] = flag;
   }

   public boolean isVisible() {
      return false;
   }

}
