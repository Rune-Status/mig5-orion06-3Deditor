package client;


final class Class40 {

   final int[] anIntArray673;
   final int[] anIntArray674;
   final int[] anIntArray675;
   final int[] anIntArray676;
   final int[] anIntArray677;
   final int[] anIntArray678;
   final int[] anIntArray679;
   final int[] anIntArray680;
   final int[] anIntArray681;
   int[] anIntArray682;
   final boolean aBoolean683;
   final int anInt684;
   final int anInt685;
   final int anInt686;
   final int anInt687;
   static final int[] anIntArray688 = new int[6];
   static final int[] anIntArray689 = new int[6];
   static final int[] anIntArray690 = new int[6];
   static final int[] anIntArray691 = new int[6];
   static final int[] anIntArray692 = new int[6];
   static final int[] anIntArray693 = new int[]{1, 0};
   static final int[] anIntArray694 = new int[]{2, 1};
   static final int[] anIntArray695 = new int[]{3, 3};
   private static final int[][] anIntArrayArray696 = new int[][]{{1, 3, 5, 7}, {1, 3, 5, 7}, {1, 3, 5, 7}, {1, 3, 5, 7, 6}, {1, 3, 5, 7, 6}, {1, 3, 5, 7, 6}, {1, 3, 5, 7, 6}, {1, 3, 5, 7, 2, 6}, {1, 3, 5, 7, 2, 8}, {1, 3, 5, 7, 2, 8}, {1, 3, 5, 7, 11, 12}, {1, 3, 5, 7, 11, 12}, {1, 3, 5, 7, 13, 14}};
   private static final int[][] anIntArrayArray697 = new int[][]{{0, 1, 2, 3, 0, 0, 1, 3}, {1, 1, 2, 3, 1, 0, 1, 3}, {0, 1, 2, 3, 1, 0, 1, 3}, {0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3}, {0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4}, {0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4}, {0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3}, {0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3}, {0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5}, {0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5}, {0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3}, {1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3}, {1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5}};


   public Class40(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2, int i3, int j3, int k3, int l3, int i4, int k4, int l4) {
      this.aBoolean683 = i3 == l2 && i3 == l && i3 == k2;
      this.anInt684 = j3;
      this.anInt685 = k1;
      this.anInt686 = i2;
      this.anInt687 = l4;
      short c = 128;
      int i5 = c / 2;
      int j5 = c / 4;
      int k5 = c * 3 / 4;
      int[] ai = anIntArrayArray696[j3];
      int l5 = ai.length;
      this.anIntArray673 = new int[l5];
      this.anIntArray674 = new int[l5];
      this.anIntArray675 = new int[l5];
      int[] ai1 = new int[l5];
      int[] ai2 = new int[l5];
      int i6 = k4 * c;
      int j6 = i * c;

      int j7;
      int l9;
      int k9;
      int l7;
      int i9;
      int i10;
      for(int ai3 = 0; ai3 < l5; ++ai3) {
         j7 = ai[ai3];
         if((j7 & 1) == 0 && j7 <= 8) {
            j7 = (j7 - k1 - k1 - 1 & 7) + 1;
         }

         if(j7 > 8 && j7 <= 12) {
            j7 = (j7 - 9 - k1 & 3) + 9;
         }

         if(j7 > 12 && j7 <= 16) {
            j7 = (j7 - 13 - k1 & 3) + 13;
         }

         if(j7 == 1) {
            l7 = i6;
            i9 = j6;
            l9 = i3;
            k9 = l1;
            i10 = j;
         } else if(j7 == 2) {
            l7 = i6 + i5;
            i9 = j6;
            l9 = i3 + l2 >> 1;
            k9 = l1 + i4 >> 1;
            i10 = j + l3 >> 1;
         } else if(j7 == 3) {
            l7 = i6 + c;
            i9 = j6;
            l9 = l2;
            k9 = i4;
            i10 = l3;
         } else if(j7 == 4) {
            l7 = i6 + c;
            i9 = j6 + i5;
            l9 = l2 + l >> 1;
            k9 = i4 + j2 >> 1;
            i10 = l3 + j1 >> 1;
         } else if(j7 == 5) {
            l7 = i6 + c;
            i9 = j6 + c;
            l9 = l;
            k9 = j2;
            i10 = j1;
         } else if(j7 == 6) {
            l7 = i6 + i5;
            i9 = j6 + c;
            l9 = l + k2 >> 1;
            k9 = j2 + k >> 1;
            i10 = j1 + k3 >> 1;
         } else if(j7 == 7) {
            l7 = i6;
            i9 = j6 + c;
            l9 = k2;
            k9 = k;
            i10 = k3;
         } else if(j7 == 8) {
            l7 = i6;
            i9 = j6 + i5;
            l9 = k2 + i3 >> 1;
            k9 = k + l1 >> 1;
            i10 = k3 + j >> 1;
         } else if(j7 == 9) {
            l7 = i6 + i5;
            i9 = j6 + j5;
            l9 = i3 + l2 >> 1;
            k9 = l1 + i4 >> 1;
            i10 = j + l3 >> 1;
         } else if(j7 == 10) {
            l7 = i6 + k5;
            i9 = j6 + i5;
            l9 = l2 + l >> 1;
            k9 = i4 + j2 >> 1;
            i10 = l3 + j1 >> 1;
         } else if(j7 == 11) {
            l7 = i6 + i5;
            i9 = j6 + k5;
            l9 = l + k2 >> 1;
            k9 = j2 + k >> 1;
            i10 = j1 + k3 >> 1;
         } else if(j7 == 12) {
            l7 = i6 + j5;
            i9 = j6 + i5;
            l9 = k2 + i3 >> 1;
            k9 = k + l1 >> 1;
            i10 = k3 + j >> 1;
         } else if(j7 == 13) {
            l7 = i6 + j5;
            i9 = j6 + j5;
            l9 = i3;
            k9 = l1;
            i10 = j;
         } else if(j7 == 14) {
            l7 = i6 + k5;
            i9 = j6 + j5;
            l9 = l2;
            k9 = i4;
            i10 = l3;
         } else if(j7 == 15) {
            l7 = i6 + k5;
            i9 = j6 + k5;
            l9 = l;
            k9 = j2;
            i10 = j1;
         } else {
            l7 = i6 + j5;
            i9 = j6 + k5;
            l9 = k2;
            k9 = k;
            i10 = k3;
         }

         this.anIntArray673[ai3] = l7;
         this.anIntArray674[ai3] = l9;
         this.anIntArray675[ai3] = i9;
         ai1[ai3] = k9;
         ai2[ai3] = i10;
      }

      int[] var38 = anIntArrayArray697[j3];
      j7 = var38.length / 4;
      this.anIntArray679 = new int[j7];
      this.anIntArray680 = new int[j7];
      this.anIntArray681 = new int[j7];
      this.anIntArray676 = new int[j7];
      this.anIntArray677 = new int[j7];
      this.anIntArray678 = new int[j7];
      if(i1 != -1) {
         this.anIntArray682 = new int[j7];
      }

      l7 = 0;

      for(i9 = 0; i9 < j7; ++i9) {
         l9 = var38[l7];
         k9 = var38[l7 + 1];
         i10 = var38[l7 + 2];
         int k10 = var38[l7 + 3];
         l7 += 4;
         if(k9 < 4) {
            k9 = k9 - k1 & 3;
         }

         if(i10 < 4) {
            i10 = i10 - k1 & 3;
         }

         if(k10 < 4) {
            k10 = k10 - k1 & 3;
         }

         this.anIntArray679[i9] = k9;
         this.anIntArray680[i9] = i10;
         this.anIntArray681[i9] = k10;
         if(l9 == 0) {
            this.anIntArray676[i9] = ai1[k9];
            this.anIntArray677[i9] = ai1[i10];
            this.anIntArray678[i9] = ai1[k10];
            if(this.anIntArray682 != null) {
               this.anIntArray682[i9] = -1;
            }
         } else {
            this.anIntArray676[i9] = ai2[k9];
            this.anIntArray677[i9] = ai2[i10];
            this.anIntArray678[i9] = ai2[k10];
            if(this.anIntArray682 != null) {
               this.anIntArray682[i9] = i1;
            }
         }
      }

      i9 = i3;
      l9 = l2;
      if(l2 < i3) {
         i9 = l2;
      }

      if(l2 > l2) {
         l9 = l2;
      }

      if(l < i9) {
         i9 = l;
      }

      if(l > l9) {
         l9 = l;
      }

      if(k2 < i9) {
         i9 = k2;
      }

      if(k2 > l9) {
         l9 = k2;
      }

      i9 /= 14;
      l9 /= 14;
   }

}
