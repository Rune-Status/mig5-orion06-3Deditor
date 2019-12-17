package client;


final class WorldController {

   private boolean aBoolean434;
   public static boolean lowMem = true;
   private final int anInt437;
   private final int anInt438;
   private final int anInt439;
   private final int[][][] anIntArrayArrayArray440;
   private final Ground[][][] groundArray;
   private int anInt442;
   private int obj5CacheCurrPos;
   private final Object5[] obj5Cache;
   private final int[][][] anIntArrayArrayArray445;
   private static int anInt446;
   private static int anInt447;
   private static int anInt448;
   private static int anInt449;
   private static int anInt450;
   private static int anInt451;
   private static int anInt452;
   private static int anInt453;
   private static int anInt454;
   private static int anInt455;
   private static int anInt456;
   private static int anInt457;
   private static int anInt458;
   private static int anInt459;
   private static int anInt460;
   private static int anInt461;
   private static Object5[] aClass28Array462 = new Object5[100];
   private static final int[] anIntArray463 = new int[]{53, -53, -53, 53};
   private static final int[] anIntArray464 = new int[]{-53, -53, 53, 53};
   private static final int[] anIntArray465 = new int[]{-45, 45, 45, -45};
   private static final int[] anIntArray466 = new int[]{45, 45, -45, -45};
   private static boolean aBoolean467;
   private static int anInt468;
   private static int anInt469;
   public static int anInt470 = -1;
   public static int anInt471 = -1;
   private static final int anInt472 = 4;
   private static int[] anIntArray473 = new int[anInt472];
   private static Class47[][] aClass47ArrayArray474 = new Class47[anInt472][500];
   private static int anInt475;
   private static final Class47[] aClass47Array476 = new Class47[500];
   private static NodeList aClass19_477 = new NodeList();
   private static final int[] anIntArray478 = new int[]{19, 55, 38, 155, 255, 110, 137, 205, 76};
   private static final int[] anIntArray479 = new int[]{160, 192, 80, 96, 0, 144, 80, 48, 160};
   private static final int[] anIntArray480 = new int[]{76, 8, 137, 4, 0, 1, 38, 2, 19};
   private static final int[] anIntArray481 = new int[]{0, 0, 2, 0, 0, 2, 1, 1, 0};
   private static final int[] anIntArray482 = new int[]{2, 0, 0, 2, 0, 0, 0, 4, 4};
   private static final int[] anIntArray483 = new int[]{0, 4, 4, 8, 0, 0, 8, 0, 0};
   private static final int[] anIntArray484 = new int[]{1, 1, 0, 0, 0, 8, 0, 0, 8};
   private static final int[] anIntArray485 = new int[]{41, '\u9950', 41, 4643, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, '\ua84e', 41, 41, 41, 41, 41, 41, 41, 8602, 41, 28992, 41, 41, 41, 41, 41, 5056, 41, 41, 41, 7079, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 3131, 41, 41, 41};
   private final int[] anIntArray486;
   private final int[] anIntArray487;
   private int anInt488;
   private final int[][] anIntArrayArray489 = new int[][]{new int[16], {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1}};
   private final int[][] anIntArrayArray490 = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, {12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3}, {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, {3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12}};
   private static boolean[][][][] aBooleanArrayArrayArrayArray491 = new boolean[8][32][51][51];
   private static boolean[][] aBooleanArrayArray492;
   private static int anInt493;
   private static int anInt494;
   private static int anInt495;
   private static int anInt496;
   private static int anInt497;
   private static int anInt498;


   public WorldController(int[][][] ai) {
      byte i = 104;
      byte j = 104;
      byte k = 4;
      this.aBoolean434 = true;
      this.obj5Cache = new Object5[5000];
      this.anIntArray486 = new int[10000];
      this.anIntArray487 = new int[10000];
      this.anInt437 = k;
      this.anInt438 = j;
      this.anInt439 = i;
      this.groundArray = new Ground[k][j][i];
      this.anIntArrayArrayArray445 = new int[k][j + 1][i + 1];
      this.anIntArrayArrayArray440 = ai;
      this.initToNull();
   }

   public static void nullLoader() {
      aClass28Array462 = null;
      anIntArray473 = null;
      aClass47ArrayArray474 = (Class47[][])null;
      aClass19_477 = null;
      aBooleanArrayArrayArrayArray491 = (boolean[][][][])null;
      aBooleanArrayArray492 = (boolean[][])null;
   }

   public void initToNull() {
      int l1;
      int j1;
      for(l1 = 0; l1 < this.anInt437; ++l1) {
         for(j1 = 0; j1 < this.anInt438; ++j1) {
            for(int i1 = 0; i1 < this.anInt439; ++i1) {
               this.groundArray[l1][j1][i1] = null;
            }
         }
      }

      for(l1 = 0; l1 < anInt472; ++l1) {
         for(j1 = 0; j1 < anIntArray473[l1]; ++j1) {
            aClass47ArrayArray474[l1][j1] = null;
         }

         anIntArray473[l1] = 0;
      }

      for(l1 = 0; l1 < this.obj5CacheCurrPos; ++l1) {
         this.obj5Cache[l1] = null;
      }

      this.obj5CacheCurrPos = 0;

      for(l1 = 0; l1 < aClass28Array462.length; ++l1) {
         aClass28Array462[l1] = null;
      }

   }

   public void method275(int i) {
      this.anInt442 = i;

      for(int k = 0; k < this.anInt438; ++k) {
         for(int l = 0; l < this.anInt439; ++l) {
            if(this.groundArray[i][k][l] == null) {
               this.groundArray[i][k][l] = new Ground(i, k, l);
            }
         }
      }

   }

   public void method276(int i, int j) {
      Ground class30_sub3 = this.groundArray[0][j][i];

      for(int l = 0; l < 3; ++l) {
         Ground class30_sub3_1 = this.groundArray[l][j][i] = this.groundArray[l + 1][j][i];
         if(class30_sub3_1 != null) {
            --class30_sub3_1.anInt1307;

            for(int j1 = 0; j1 < class30_sub3_1.anInt1317; ++j1) {
               Object5 class28 = class30_sub3_1.obj5Array[j1];
               if((class28.uid >> 29 & 3) == 2 && class28.anInt523 == j && class28.anInt525 == i) {
                  --class28.anInt517;
               }
            }
         }
      }

      if(this.groundArray[0][j][i] == null) {
         this.groundArray[0][j][i] = new Ground(0, j, i);
      }

      this.groundArray[0][j][i].aClass30_Sub3_1329 = class30_sub3;
      this.groundArray[3][j][i] = null;
   }

   public static void method277(int i, int j, int k, int l, int i1, int j1, int l1, int i2) {
      Class47 class47 = new Class47();
      class47.anInt787 = j / 128;
      class47.anInt788 = l / 128;
      class47.anInt789 = l1 / 128;
      class47.anInt790 = i1 / 128;
      class47.anInt791 = i2;
      class47.anInt792 = j;
      class47.anInt793 = l;
      class47.anInt794 = l1;
      class47.anInt795 = i1;
      class47.anInt796 = j1;
      class47.anInt797 = k;
      aClass47ArrayArray474[i][anIntArray473[i]++] = class47;
   }

   public void method278(int i, int j, int k, int l) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      if(class30_sub3 != null) {
         this.groundArray[i][j][k].anInt1321 = l;
      }

   }

   public void method279(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2, int i3, int j3, int k3, int l3, int i4, int j4, int k4, int l4) {
      Class43 class40;
      int k5;
      if(l == 0) {
         class40 = new Class43(k2, l2, i3, j3, -1, k4, false);

         for(k5 = i; k5 >= 0; --k5) {
            if(this.groundArray[k5][j][k] == null) {
               this.groundArray[k5][j][k] = new Ground(k5, j, k);
            }
         }

         this.groundArray[i][j][k].aClass43_1311 = class40;
      } else if(l != 1) {
         Class40 var23 = new Class40(k, k3, j3, i2, j1, i4, i1, k2, k4, i3, j2, l1, k1, l, j4, l3, l2, j, l4);

         for(k5 = i; k5 >= 0; --k5) {
            if(this.groundArray[k5][j][k] == null) {
               this.groundArray[k5][j][k] = new Ground(k5, j, k);
            }
         }

         this.groundArray[i][j][k].aClass40_1312 = var23;
      } else {
         class40 = new Class43(k3, l3, i4, j4, j1, l4, k1 == l1 && k1 == i2 && k1 == j2);

         for(k5 = i; k5 >= 0; --k5) {
            if(this.groundArray[k5][j][k] == null) {
               this.groundArray[k5][j][k] = new Ground(k5, j, k);
            }
         }

         this.groundArray[i][j][k].aClass43_1311 = class40;
      }
   }

   public void method280(int i, int j, int k, Animable class30_sub2_sub4, byte byte0, int i1, int j1) {
      if(class30_sub2_sub4 != null) {
         Object3 class49 = new Object3();
         class49.aClass30_Sub2_Sub4_814 = class30_sub2_sub4;
         class49.anInt812 = j1 * 128 + 64;
         class49.anInt813 = k * 128 + 64;
         class49.anInt811 = j;
         class49.uid = i1;
         class49.aByte816 = byte0;
         if(this.groundArray[i][j1][k] == null) {
            this.groundArray[i][j1][k] = new Ground(i, j1, k);
         }

         this.groundArray[i][j1][k].obj3 = class49;
      }
   }

   public void method281(int i, int j, Animable class30_sub2_sub4, int k, Animable class30_sub2_sub4_1, Animable class30_sub2_sub4_2, int l, int i1) {
      Object4 object4 = new Object4();
      object4.aClass30_Sub2_Sub4_48 = class30_sub2_sub4_2;
      object4.anInt46 = i * 128 + 64;
      object4.anInt47 = i1 * 128 + 64;
      object4.anInt45 = k;
      object4.uid = j;
      object4.aClass30_Sub2_Sub4_49 = class30_sub2_sub4;
      object4.aClass30_Sub2_Sub4_50 = class30_sub2_sub4_1;
      int j1 = 0;
      Ground class30_sub3 = this.groundArray[l][i][i1];
      if(class30_sub3 != null) {
         for(int k1 = 0; k1 < class30_sub3.anInt1317; ++k1) {
            if(class30_sub3.obj5Array[k1].aClass30_Sub2_Sub4_521 instanceof Model) {
               int l1 = ((Model)class30_sub3.obj5Array[k1].aClass30_Sub2_Sub4_521).anInt1654;
               if(l1 > j1) {
                  j1 = l1;
               }
            }
         }
      }

      object4.anInt52 = j1;
      if(this.groundArray[l][i][i1] == null) {
         this.groundArray[l][i][i1] = new Ground(l, i, i1);
      }

      this.groundArray[l][i][i1].obj4 = object4;
   }

   public void method282(int i, Animable class30_sub2_sub4, int j, int k, byte byte0, int l, Animable class30_sub2_sub4_1, int i1, int j1, int k1) {
      if(class30_sub2_sub4 != null || class30_sub2_sub4_1 != null) {
         Object1 object1 = new Object1();
         object1.uid = j;
         object1.aByte281 = byte0;
         object1.anInt274 = l * 128 + 64;
         object1.anInt275 = k * 128 + 64;
         object1.anInt273 = i1;
         object1.aClass30_Sub2_Sub4_278 = class30_sub2_sub4;
         object1.aClass30_Sub2_Sub4_279 = class30_sub2_sub4_1;
         object1.orientation = i;
         object1.orientation1 = j1;

         for(int l1 = k1; l1 >= 0; --l1) {
            if(this.groundArray[l1][l][k] == null) {
               this.groundArray[l1][l][k] = new Ground(l1, l, k);
            }
         }

         this.groundArray[k1][l][k].obj1 = object1;
      }
   }

   public void method283(int i, int j, int k, int i1, int j1, int k1, Animable class30_sub2_sub4, int l1, byte byte0, int i2, int j2) {
      if(class30_sub2_sub4 != null) {
         Object2 class26 = new Object2();
         class26.uid = i;
         class26.aByte506 = byte0;
         class26.anInt500 = l1 * 128 + 64 + j1;
         class26.anInt501 = j * 128 + 64 + i2;
         class26.anInt499 = k1;
         class26.aClass30_Sub2_Sub4_504 = class30_sub2_sub4;
         class26.anInt502 = j2;
         class26.anInt503 = k;

         for(int k2 = i1; k2 >= 0; --k2) {
            if(this.groundArray[k2][l1][j] == null) {
               this.groundArray[k2][l1][j] = new Ground(k2, l1, j);
            }
         }

         this.groundArray[i1][l1][j].obj2 = class26;
      }
   }

   public boolean method284(int i, byte byte0, int j, int k, Animable class30_sub2_sub4, int l, int i1, int j1, int k1, int l1) {
      if(class30_sub2_sub4 == null) {
         return true;
      } else {
         int i2 = l1 * 128 + 64 * l;
         int j2 = k1 * 128 + 64 * k;
         return this.method287(i1, l1, k1, l, k, i2, j2, j, class30_sub2_sub4, j1, false, i, byte0);
      }
   }

   public boolean method285(int i, int j, int k, int l, int i1, int j1, int k1, Animable class30_sub2_sub4, boolean flag) {
      if(class30_sub2_sub4 == null) {
         return true;
      } else {
         int l1 = k1 - j1;
         int i2 = i1 - j1;
         int j2 = k1 + j1;
         int k2 = i1 + j1;
         if(flag) {
            if(j > 640 && j < 1408) {
               k2 += 128;
            }

            if(j > 1152 && j < 1920) {
               j2 += 128;
            }

            if(j > 1664 || j < 384) {
               i2 -= 128;
            }

            if(j > 128 && j < 896) {
               l1 -= 128;
            }
         }

         l1 /= 128;
         i2 /= 128;
         j2 /= 128;
         k2 /= 128;
         return this.method287(i, l1, i2, j2 - l1 + 1, k2 - i2 + 1, k1, i1, k, class30_sub2_sub4, j, true, l, (byte)0);
      }
   }

   public boolean method286(int j, int k, Animable class30_sub2_sub4, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2) {
      return class30_sub2_sub4 == null || this.method287(j, l1, k2, i2 - l1 + 1, i1 - k2 + 1, j1, k, k1, class30_sub2_sub4, l, true, j2, (byte)0);
   }

   private boolean method287(int i, int j, int k, int l, int i1, int j1, int k1, int l1, Animable class30_sub2_sub4, int i2, boolean flag, int j2, byte byte0) {
      int i3;
      for(int class28 = j; class28 < j + l; ++class28) {
         for(i3 = k; i3 < k + i1; ++i3) {
            if(class28 < 0 || i3 < 0 || class28 >= this.anInt438 || i3 >= this.anInt439) {
               return false;
            }

            Ground j3 = this.groundArray[i][class28][i3];
            if(j3 != null && j3.anInt1317 >= 5) {
               return false;
            }
         }
      }

      Object5 var19 = new Object5();
      var19.uid = j2;
      var19.aByte530 = byte0;
      var19.anInt517 = i;
      var19.anInt519 = j1;
      var19.anInt520 = k1;
      var19.anInt518 = l1;
      var19.aClass30_Sub2_Sub4_521 = class30_sub2_sub4;
      var19.anInt522 = i2;
      var19.anInt523 = j;
      var19.anInt525 = k;
      var19.anInt524 = j + l - 1;
      var19.anInt526 = k + i1 - 1;

      for(i3 = j; i3 < j + l; ++i3) {
         for(int var20 = k; var20 < k + i1; ++var20) {
            int k3 = 0;
            if(i3 > j) {
               ++k3;
            }

            if(i3 < j + l - 1) {
               k3 += 4;
            }

            if(var20 > k) {
               k3 += 8;
            }

            if(var20 < k + i1 - 1) {
               k3 += 2;
            }

            for(int class30_sub3_1 = i; class30_sub3_1 >= 0; --class30_sub3_1) {
               if(this.groundArray[class30_sub3_1][i3][var20] == null) {
                  this.groundArray[class30_sub3_1][i3][var20] = new Ground(class30_sub3_1, i3, var20);
               }
            }

            Ground var21 = this.groundArray[i][i3][var20];
            var21.obj5Array[var21.anInt1317] = var19;
            var21.anIntArray1319[var21.anInt1317] = k3;
            var21.anInt1320 |= k3;
            ++var21.anInt1317;
         }
      }

      if(flag) {
         this.obj5Cache[this.obj5CacheCurrPos++] = var19;
      }

      return true;
   }

   public void clearObj5Cache() {
      for(int i = 0; i < this.obj5CacheCurrPos; ++i) {
         Object5 object5 = this.obj5Cache[i];
         this.method289(object5);
         this.obj5Cache[i] = null;
      }

      this.obj5CacheCurrPos = 0;
   }

   private void method289(Object5 class28) {
      for(int j = class28.anInt523; j <= class28.anInt524; ++j) {
         for(int k = class28.anInt525; k <= class28.anInt526; ++k) {
            Ground class30_sub3 = this.groundArray[class28.anInt517][j][k];
            if(class30_sub3 != null) {
               int j1;
               for(j1 = 0; j1 < class30_sub3.anInt1317; ++j1) {
                  if(class30_sub3.obj5Array[j1] == class28) {
                     --class30_sub3.anInt1317;

                     for(int i1 = j1; i1 < class30_sub3.anInt1317; ++i1) {
                        class30_sub3.obj5Array[i1] = class30_sub3.obj5Array[i1 + 1];
                        class30_sub3.anIntArray1319[i1] = class30_sub3.anIntArray1319[i1 + 1];
                     }

                     class30_sub3.obj5Array[class30_sub3.anInt1317] = null;
                     break;
                  }
               }

               class30_sub3.anInt1320 = 0;

               for(j1 = 0; j1 < class30_sub3.anInt1317; ++j1) {
                  class30_sub3.anInt1320 |= class30_sub3.anIntArray1319[j1];
               }
            }
         }
      }

   }

   public void method290(int i, int k, int l, int i1) {
      Ground class30_sub3 = this.groundArray[i1][l][i];
      if(class30_sub3 != null) {
         Object2 class26 = class30_sub3.obj2;
         if(class26 != null) {
            int j1 = l * 128 + 64;
            int k1 = i * 128 + 64;
            class26.anInt500 = j1 + (class26.anInt500 - j1) * k / 16;
            class26.anInt501 = k1 + (class26.anInt501 - k1) * k / 16;
         }

      }
   }

   public void method291(int i, int j, int k, byte byte0) {
      Ground class30_sub3 = this.groundArray[j][i][k];
      if(byte0 != -119) {
         this.aBoolean434 = !this.aBoolean434;
      }

      if(class30_sub3 != null) {
         class30_sub3.obj1 = null;
      }

   }

   public void method292(int j, int k, int l) {
      Ground class30_sub3 = this.groundArray[k][l][j];
      if(class30_sub3 != null) {
         class30_sub3.obj2 = null;
      }

   }

   public void method293(int i, int k, int l) {
      Ground class30_sub3 = this.groundArray[i][k][l];
      if(class30_sub3 != null) {
         for(int j1 = 0; j1 < class30_sub3.anInt1317; ++j1) {
            Object5 class28 = class30_sub3.obj5Array[j1];
            if((class28.uid >> 29 & 3) == 2 && class28.anInt523 == k && class28.anInt525 == l) {
               this.method289(class28);
               return;
            }
         }

      }
   }

   public void method294(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][k][j];
      if(class30_sub3 != null) {
         class30_sub3.obj3 = null;
      }
   }

   public void method295(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      if(class30_sub3 != null) {
         class30_sub3.obj4 = null;
      }

   }

   public Object1 method296(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      return class30_sub3 == null?null:class30_sub3.obj1;
   }

   public Object2 method297(int i, int k, int l) {
      Ground class30_sub3 = this.groundArray[l][i][k];
      return class30_sub3 == null?null:class30_sub3.obj2;
   }

   public Object5 method298(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[k][i][j];
      if(class30_sub3 == null) {
         return null;
      } else {
         for(int l = 0; l < class30_sub3.anInt1317; ++l) {
            Object5 class28 = class30_sub3.obj5Array[l];
            if((class28.uid >> 29 & 3) == 2 && class28.anInt523 == i && class28.anInt525 == j) {
               return class28;
            }
         }

         return null;
      }
   }

   public Object3 method299(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[k][j][i];
      return class30_sub3 != null && class30_sub3.obj3 != null?class30_sub3.obj3:null;
   }

   public int method300(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      return class30_sub3 != null && class30_sub3.obj1 != null?class30_sub3.obj1.uid:0;
   }

   public int method301(int i, int j, int l) {
      Ground class30_sub3 = this.groundArray[i][j][l];
      return class30_sub3 != null && class30_sub3.obj2 != null?class30_sub3.obj2.uid:0;
   }

   public int method302(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      if(class30_sub3 == null) {
         return 0;
      } else {
         for(int l = 0; l < class30_sub3.anInt1317; ++l) {
            Object5 class28 = class30_sub3.obj5Array[l];
            if((class28.uid >> 29 & 3) == 2 && class28.anInt523 == j && class28.anInt525 == k) {
               return class28.uid;
            }
         }

         return 0;
      }
   }

   public int method303(int i, int j, int k) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      return class30_sub3 != null && class30_sub3.obj3 != null?class30_sub3.obj3.uid:0;
   }

   public int method304(int i, int j, int k, int l) {
      Ground class30_sub3 = this.groundArray[i][j][k];
      if(class30_sub3 == null) {
         return -1;
      } else if(class30_sub3.obj1 != null && class30_sub3.obj1.uid == l) {
         return class30_sub3.obj1.aByte281 & 255;
      } else if(class30_sub3.obj2 != null && class30_sub3.obj2.uid == l) {
         return class30_sub3.obj2.aByte506 & 255;
      } else if(class30_sub3.obj3 != null && class30_sub3.obj3.uid == l) {
         return class30_sub3.obj3.aByte816 & 255;
      } else {
         for(int i1 = 0; i1 < class30_sub3.anInt1317; ++i1) {
            if(class30_sub3.obj5Array[i1].uid == l) {
               return class30_sub3.obj5Array[i1].aByte530 & 255;
            }
         }

         return -1;
      }
   }

   public void method305(int i, int k, int i1) {
      /*byte j = 64;
      short l = 768;
      int j1 = (int)Math.sqrt((double)(k * k + i * i + i1 * i1));
      int k1 = l * j1 >> 8;*/
	  //custom improved object shadowing.. makes wizard tower wall look a lot better, instead of just black on some spots
	  int j = 100;
	  int l = 5500;
	  int j1 = (int)Math.sqrt(k * k + i * i + i1 * i1);
	  int b = (int) Math.ceil(j * 12.69);
	  //int k1 = l >> 4;//use either the one below or this
	  int k1 = b + (l / 2) >> 4;//this might have a bit better shadows than the other one

      for(int l1 = 0; l1 < this.anInt437; ++l1) {
         for(int i2 = 0; i2 < this.anInt438; ++i2) {
            for(int j2 = 0; j2 < this.anInt439; ++j2) {
               Ground class30_sub3 = this.groundArray[l1][i2][j2];
               if(class30_sub3 != null) {
                  Object1 class10 = class30_sub3.obj1;
                  if(class10 != null && class10.aClass30_Sub2_Sub4_278 != null && class10.aClass30_Sub2_Sub4_278.aClass33Array1425 != null) {
                     this.method307(l1, 1, 1, i2, j2, (Model)class10.aClass30_Sub2_Sub4_278);
                     if(class10.aClass30_Sub2_Sub4_279 != null && class10.aClass30_Sub2_Sub4_279.aClass33Array1425 != null) {
                        this.method307(l1, 1, 1, i2, j2, (Model)class10.aClass30_Sub2_Sub4_279);
                        this.method308((Model)class10.aClass30_Sub2_Sub4_278, (Model)class10.aClass30_Sub2_Sub4_279, 0, 0, 0, false);
                        ((Model)class10.aClass30_Sub2_Sub4_279).method480(j, k1, k, i, i1);
                     }

                     ((Model)class10.aClass30_Sub2_Sub4_278).method480(j, k1, k, i, i1);
                  }

                  for(int class49 = 0; class49 < class30_sub3.anInt1317; ++class49) {
                     Object5 class28 = class30_sub3.obj5Array[class49];
                     if(class28 != null && class28.aClass30_Sub2_Sub4_521 != null && class28.aClass30_Sub2_Sub4_521.aClass33Array1425 != null) {
                        this.method307(l1, class28.anInt524 - class28.anInt523 + 1, class28.anInt526 - class28.anInt525 + 1, i2, j2, (Model)class28.aClass30_Sub2_Sub4_521);
                        ((Model)class28.aClass30_Sub2_Sub4_521).method480(j, k1, k, i, i1);
                     }
                  }

                  Object3 var15 = class30_sub3.obj3;
                  if(var15 != null && var15.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
                     this.method306(i2, l1, (Model)var15.aClass30_Sub2_Sub4_814, j2);
                     ((Model)var15.aClass30_Sub2_Sub4_814).method480(j, k1, k, i, i1);
                  }
               }
            }
         }
      }

   }

   private void method306(int i, int j, Model model, int k) {
      Ground class30_sub3_3;
      if(i < this.anInt438) {
         class30_sub3_3 = this.groundArray[j][i + 1][k];
         if(class30_sub3_3 != null && class30_sub3_3.obj3 != null && class30_sub3_3.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
            this.method308(model, (Model)class30_sub3_3.obj3.aClass30_Sub2_Sub4_814, 128, 0, 0, true);
         }
      }

      if(k < this.anInt438) {
         class30_sub3_3 = this.groundArray[j][i][k + 1];
         if(class30_sub3_3 != null && class30_sub3_3.obj3 != null && class30_sub3_3.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
            this.method308(model, (Model)class30_sub3_3.obj3.aClass30_Sub2_Sub4_814, 0, 0, 128, true);
         }
      }

      if(i < this.anInt438 && k < this.anInt439) {
         class30_sub3_3 = this.groundArray[j][i + 1][k + 1];
         if(class30_sub3_3 != null && class30_sub3_3.obj3 != null && class30_sub3_3.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
            this.method308(model, (Model)class30_sub3_3.obj3.aClass30_Sub2_Sub4_814, 128, 0, 128, true);
         }
      }

      if(i < this.anInt438 && k > 0) {
         class30_sub3_3 = this.groundArray[j][i + 1][k - 1];
         if(class30_sub3_3 != null && class30_sub3_3.obj3 != null && class30_sub3_3.obj3.aClass30_Sub2_Sub4_814.aClass33Array1425 != null) {
            this.method308(model, (Model)class30_sub3_3.obj3.aClass30_Sub2_Sub4_814, 128, 0, -128, true);
         }
      }

   }

   private void method307(int i, int j, int k, int l, int i1, Model model) {
      boolean flag = true;
      int j1 = l;
      int k1 = l + j;
      int l1 = i1 - 1;
      int i2 = i1 + k;

      for(int j2 = i; j2 <= i + 1; ++j2) {
         if(j2 != this.anInt437) {
            for(int k2 = j1; k2 <= k1; ++k2) {
               if(k2 >= 0 && k2 < this.anInt438) {
                  for(int l2 = l1; l2 <= i2; ++l2) {
                     if(l2 >= 0 && l2 < this.anInt439 && (!flag || k2 >= k1 || l2 >= i2 || l2 < i1 && k2 != l)) {
                        Ground class30_sub3 = this.groundArray[j2][k2][l2];
                        if(class30_sub3 != null) {
                           int i3 = (this.anIntArrayArrayArray440[j2][k2][l2] + this.anIntArrayArrayArray440[j2][k2 + 1][l2] + this.anIntArrayArrayArray440[j2][k2][l2 + 1] + this.anIntArrayArrayArray440[j2][k2 + 1][l2 + 1]) / 4 - (this.anIntArrayArrayArray440[i][l][i1] + this.anIntArrayArrayArray440[i][l + 1][i1] + this.anIntArrayArrayArray440[i][l][i1 + 1] + this.anIntArrayArrayArray440[i][l + 1][i1 + 1]) / 4;
                           Object1 class10 = class30_sub3.obj1;
                           if(class10 != null && class10.aClass30_Sub2_Sub4_278 != null && class10.aClass30_Sub2_Sub4_278.aClass33Array1425 != null) {
                              this.method308(model, (Model)class10.aClass30_Sub2_Sub4_278, (k2 - l) * 128 + (1 - j) * 64, i3, (l2 - i1) * 128 + (1 - k) * 64, flag);
                           }

                           if(class10 != null && class10.aClass30_Sub2_Sub4_279 != null && class10.aClass30_Sub2_Sub4_279.aClass33Array1425 != null) {
                              this.method308(model, (Model)class10.aClass30_Sub2_Sub4_279, (k2 - l) * 128 + (1 - j) * 64, i3, (l2 - i1) * 128 + (1 - k) * 64, flag);
                           }

                           for(int j3 = 0; j3 < class30_sub3.anInt1317; ++j3) {
                              Object5 class28 = class30_sub3.obj5Array[j3];
                              if(class28 != null && class28.aClass30_Sub2_Sub4_521 != null && class28.aClass30_Sub2_Sub4_521.aClass33Array1425 != null) {
                                 int k3 = class28.anInt524 - class28.anInt523 + 1;
                                 int l3 = class28.anInt526 - class28.anInt525 + 1;
                                 this.method308(model, (Model)class28.aClass30_Sub2_Sub4_521, (class28.anInt523 - l) * 128 + (k3 - j) * 64, i3, (class28.anInt525 - i1) * 128 + (l3 - k) * 64, flag);
                              }
                           }
                        }
                     }
                  }
               }
            }

            --j1;
            flag = false;
         }
      }

   }

   private void method308(Model model, Model model_1, int i, int j, int k, boolean flag) {
      ++this.anInt488;
      int l = 0;
      int[] ai = model_1.anIntArray1627;
      int i1 = model_1.anInt1626;

      int l1;
      for(l1 = 0; l1 < model.anInt1626; ++l1) {
         Class33 class33 = model.aClass33Array1425[l1];
         Class33 class33_1 = model.aClass33Array1660[l1];
         if(class33_1.anInt605 != 0) {
            int i2 = model.anIntArray1628[l1] - j;
            if(i2 <= model_1.anInt1651) {
               int j2 = model.anIntArray1627[l1] - i;
               if(j2 >= model_1.anInt1646 && j2 <= model_1.anInt1647) {
                  int k2 = model.anIntArray1629[l1] - k;
                  if(k2 >= model_1.anInt1649 && k2 <= model_1.anInt1648) {
                     for(int l2 = 0; l2 < i1; ++l2) {
                        Class33 class33_2 = model_1.aClass33Array1425[l2];
                        Class33 class33_3 = model_1.aClass33Array1660[l2];
                        if(j2 == ai[l2] && k2 == model_1.anIntArray1629[l2] && i2 == model_1.anIntArray1628[l2] && class33_3.anInt605 != 0) {
                           class33.anInt602 += class33_3.anInt602;
                           class33.anInt603 += class33_3.anInt603;
                           class33.anInt604 += class33_3.anInt604;
                           class33.anInt605 += class33_3.anInt605;
                           class33_2.anInt602 += class33_1.anInt602;
                           class33_2.anInt603 += class33_1.anInt603;
                           class33_2.anInt604 += class33_1.anInt604;
                           class33_2.anInt605 += class33_1.anInt605;
                           ++l;
                           this.anIntArray486[l1] = this.anInt488;
                           this.anIntArray487[l2] = this.anInt488;
                        }
                     }
                  }
               }
            }
         }
      }

      if(l >= 3 && flag) {
         for(l1 = 0; l1 < model.anInt1630; ++l1) {
            if(this.anIntArray486[model.anIntArray1631[l1]] == this.anInt488 && this.anIntArray486[model.anIntArray1632[l1]] == this.anInt488 && this.anIntArray486[model.anIntArray1633[l1]] == this.anInt488) {
               model.anIntArray1637[l1] = -1;
            }
         }

         for(l1 = 0; l1 < model_1.anInt1630; ++l1) {
            if(this.anIntArray487[model_1.anIntArray1631[l1]] == this.anInt488 && this.anIntArray487[model_1.anIntArray1632[l1]] == this.anInt488 && this.anIntArray487[model_1.anIntArray1633[l1]] == this.anInt488) {
               model_1.anIntArray1637[l1] = -1;
            }
         }

      }
   }

   public void method309(int[] ai, int i, int k, int l, int i1) {
      short j = 512;
      Ground class30_sub3 = this.groundArray[k][l][i1];
      if(class30_sub3 != null) {
         Class43 class43 = class30_sub3.aClass43_1311;
         int l1;
         if(class43 == null) {
            Class40 var18 = class30_sub3.aClass40_1312;
            if(var18 != null) {
               l1 = var18.anInt684;
               int i2 = var18.anInt685;
               int j2 = var18.anInt686;
               int k2 = var18.anInt687;
               int[] ai1 = this.anIntArrayArray489[l1];
               int[] ai2 = this.anIntArrayArray490[i2];
               int l2 = 0;
               int j3;
               if(j2 != 0) {
                  for(j3 = 0; j3 < 4; ++j3) {
                     ai[i] = ai1[ai2[l2++]] != 0?k2:j2;
                     ai[i + 1] = ai1[ai2[l2++]] != 0?k2:j2;
                     ai[i + 2] = ai1[ai2[l2++]] != 0?k2:j2;
                     ai[i + 3] = ai1[ai2[l2++]] != 0?k2:j2;
                     i += j;
                  }

               } else {
                  for(j3 = 0; j3 < 4; ++j3) {
                     if(ai1[ai2[l2++]] != 0) {
                        ai[i] = k2;
                     }

                     if(ai1[ai2[l2++]] != 0) {
                        ai[i + 1] = k2;
                     }

                     if(ai1[ai2[l2++]] != 0) {
                        ai[i + 2] = k2;
                     }

                     if(ai1[ai2[l2++]] != 0) {
                        ai[i + 3] = k2;
                     }

                     i += j;
                  }

               }
            }
         } else {
            int class40 = class43.anInt722;
            if(class40 != 0) {
               for(l1 = 0; l1 < 4; ++l1) {
                  ai[i] = class40;
                  ai[i + 1] = class40;
                  ai[i + 2] = class40;
                  ai[i + 3] = class40;
                  i += j;
               }

            }
         }
      }
   }

   public static void method310(int i, int j, int k, int l, int[] ai) {
      anInt495 = 0;
      anInt496 = 0;
      anInt497 = k;
      anInt498 = l;
      anInt493 = k / 2;
      anInt494 = l / 2;
      boolean[][][][] aflag = new boolean[9][32][53][53];

      int k1;
      int i2;
      int k2;
      int i3;
      int l3;
      int j4;
      for(k1 = 128; k1 <= 384; k1 += 32) {
         for(i2 = 0; i2 < 2048; i2 += 64) {
            anInt458 = Model.modelIntArray1[k1];
            anInt459 = Model.modelIntArray2[k1];
            anInt460 = Model.modelIntArray1[i2];
            anInt461 = Model.modelIntArray2[i2];
            k2 = (k1 - 128) / 32;
            i3 = i2 / 64;

            for(int flag1 = -26; flag1 <= 26; ++flag1) {
               l3 = -26;

               while(l3 <= 26) {
                  j4 = flag1 * 128;
                  int i4 = l3 * 128;
                  boolean flag2 = false;
                  int k4 = -i;

                  while(true) {
                     if(k4 <= j) {
                        if(!method311(ai[k2] + k4, i4, j4)) {
                           k4 += 128;
                           continue;
                        }

                        flag2 = true;
                     }

                     aflag[k2][i3][flag1 + 25 + 1][l3 + 25 + 1] = flag2;
                     ++l3;
                     break;
                  }
               }
            }
         }
      }

      for(k1 = 0; k1 < 8; ++k1) {
         for(i2 = 0; i2 < 32; ++i2) {
            for(k2 = -25; k2 < 25; ++k2) {
               i3 = -25;

               while(i3 < 25) {
                  boolean var16 = false;
                  l3 = -1;

                  label68:
                  while(true) {
                     if(l3 <= 1) {
                        j4 = -1;

                        while(true) {
                           if(j4 > 1) {
                              ++l3;
                              continue label68;
                           }

                           if(aflag[k1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                              var16 = true;
                              break;
                           }

                           if(aflag[k1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                              var16 = true;
                              break;
                           }

                           if(aflag[k1 + 1][i2][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                              var16 = true;
                              break;
                           }

                           if(aflag[k1 + 1][(i2 + 1) % 31][k2 + l3 + 25 + 1][i3 + j4 + 25 + 1]) {
                              var16 = true;
                              break;
                           }

                           ++j4;
                        }
                     }

                     aBooleanArrayArrayArrayArray491[k1][i2][k2 + 25][i3 + 25] = var16;
                     ++i3;
                     break;
                  }
               }
            }
         }
      }

   }

   private static boolean method311(int i, int j, int k) {
      int l = j * anInt460 + k * anInt461 >> 16;
      int i1 = j * anInt461 - k * anInt460 >> 16;
      int j1 = i * anInt458 + i1 * anInt459 >> 16;
      int k1 = i * anInt459 - i1 * anInt458 >> 16;
      if(j1 >= 50 && j1 <= 3500) {
         int l1 = anInt493 + (l << 9) / j1;
         int i2 = anInt494 + (k1 << 9) / j1;
         return l1 >= anInt495 && l1 <= anInt497 && i2 >= anInt496 && i2 <= anInt498;
      } else {
         return false;
      }
   }

   public void method312(int i, int j) {
      aBoolean467 = true;
      anInt468 = j;
      anInt469 = i;
      anInt470 = -1;
      anInt471 = -1;
   }

   public void method313(int i, int j, int k, int l, int i1, int j1) {
      if(i < 0) {
         i = 0;
      } else if(i >= this.anInt438 * 128) {
         i = this.anInt438 * 128 - 1;
      }

      if(j < 0) {
         j = 0;
      } else if(j >= this.anInt439 * 128) {
         j = this.anInt439 * 128 - 1;
      }

      ++anInt448;
      anInt458 = Model.modelIntArray1[j1];
      anInt459 = Model.modelIntArray2[j1];
      anInt460 = Model.modelIntArray1[k];
      anInt461 = Model.modelIntArray2[k];
      aBooleanArrayArray492 = aBooleanArrayArrayArrayArray491[(j1 - 128) / 32][k / 64];
      anInt455 = i;
      anInt456 = l;
      anInt457 = j;
      anInt453 = i / 128;
      anInt454 = j / 128;
      anInt447 = i1;
      anInt449 = anInt453 - 25;
      if(anInt449 < 0) {
         anInt449 = 0;
      }

      anInt451 = anInt454 - 25;
      if(anInt451 < 0) {
         anInt451 = 0;
      }

      anInt450 = anInt453 + 25;
      if(anInt450 > this.anInt438) {
         anInt450 = this.anInt438;
      }

      anInt452 = anInt454 + 25;
      if(anInt452 > this.anInt439) {
         anInt452 = this.anInt439;
      }

      this.method319();
      anInt446 = 0;

      int j2;
      Ground[][] aclass30_sub3_2;
      int j3;
      int l3;
      for(j2 = this.anInt442; j2 < this.anInt437; ++j2) {
         aclass30_sub3_2 = this.groundArray[j2];

         for(j3 = anInt449; j3 < anInt450; ++j3) {
            for(l3 = anInt451; l3 < anInt452; ++l3) {
               Ground j4 = aclass30_sub3_2[j3][l3];
               if(j4 != null) {
                  if(j4.anInt1321 <= i1 && (aBooleanArrayArray492[j3 - anInt453 + 25][l3 - anInt454 + 25] || this.anIntArrayArrayArray440[j2][j3][l3] - l >= 2000)) {
                     j4.aBoolean1322 = true;
                     j4.aBoolean1323 = true;
                     j4.aBoolean1324 = j4.anInt1317 > 0;
                     ++anInt446;
                  } else {
                     j4.aBoolean1322 = false;
                     j4.aBoolean1323 = false;
                     j4.anInt1325 = 0;
                  }
               }
            }
         }
      }

      int l4;
      int j5;
      int k5;
      Ground class30_sub3_8;
      int var16;
      for(j2 = this.anInt442; j2 < this.anInt437; ++j2) {
         aclass30_sub3_2 = this.groundArray[j2];

         for(j3 = -25; j3 <= 0; ++j3) {
            l3 = anInt453 + j3;
            var16 = anInt453 - j3;
            if(l3 >= anInt449 || var16 < anInt450) {
               for(l4 = -25; l4 <= 0; ++l4) {
                  j5 = anInt454 + l4;
                  k5 = anInt454 - l4;
                  if(l3 >= anInt449) {
                     if(j5 >= anInt451) {
                        class30_sub3_8 = aclass30_sub3_2[l3][j5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, true);
                        }
                     }

                     if(k5 < anInt452) {
                        class30_sub3_8 = aclass30_sub3_2[l3][k5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, true);
                        }
                     }
                  }

                  if(var16 < anInt450) {
                     if(j5 >= anInt451) {
                        class30_sub3_8 = aclass30_sub3_2[var16][j5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, true);
                        }
                     }

                     if(k5 < anInt452) {
                        class30_sub3_8 = aclass30_sub3_2[var16][k5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, true);
                        }
                     }
                  }

                  if(anInt446 == 0) {
                     aBoolean467 = false;
                     return;
                  }
               }
            }
         }
      }

      for(j2 = this.anInt442; j2 < this.anInt437; ++j2) {
         aclass30_sub3_2 = this.groundArray[j2];

         for(j3 = -25; j3 <= 0; ++j3) {
            l3 = anInt453 + j3;
            var16 = anInt453 - j3;
            if(l3 >= anInt449 || var16 < anInt450) {
               for(l4 = -25; l4 <= 0; ++l4) {
                  j5 = anInt454 + l4;
                  k5 = anInt454 - l4;
                  if(l3 >= anInt449) {
                     if(j5 >= anInt451) {
                        class30_sub3_8 = aclass30_sub3_2[l3][j5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, false);
                        }
                     }

                     if(k5 < anInt452) {
                        class30_sub3_8 = aclass30_sub3_2[l3][k5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, false);
                        }
                     }
                  }

                  if(var16 < anInt450) {
                     if(j5 >= anInt451) {
                        class30_sub3_8 = aclass30_sub3_2[var16][j5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, false);
                        }
                     }

                     if(k5 < anInt452) {
                        class30_sub3_8 = aclass30_sub3_2[var16][k5];
                        if(class30_sub3_8 != null && class30_sub3_8.aBoolean1322) {
                           this.method314(class30_sub3_8, false);
                        }
                     }
                  }

                  if(anInt446 == 0) {
                     aBoolean467 = false;
                     return;
                  }
               }
            }
         }
      }

      aBoolean467 = false;
   }

   private void method314(Ground class30_sub3, boolean flag) {
      aClass19_477.insertHead(class30_sub3);

      while(true) {
         Ground class30_sub3_1 = (Ground)aClass19_477.popHead();
         if(class30_sub3_1 == null) {
            return;
         }

         if(class30_sub3_1.aBoolean1323) {
            int i = class30_sub3_1.anInt1308;
            int j = class30_sub3_1.anInt1309;
            int k = class30_sub3_1.anInt1307;
            int l = class30_sub3_1.anInt1310;
            Ground[][] aclass30_sub3 = this.groundArray[k];
            Ground object4;
            Object1 class30_sub3_16;
            int class10_2;
            Object5 j3;
            int k5;
            int j6;
            int j9;
            int l7;
            int j10;
            boolean var23;
            int var24;
            Ground var37;
            if(class30_sub3_1.aBoolean1322) {
               if(flag) {
                  if(k > 0) {
                     object4 = this.groundArray[k - 1][i][j];
                     if(object4 != null && object4.aBoolean1323) {
                        continue;
                     }
                  }

                  if(i <= anInt453 && i > anInt449) {
                     object4 = aclass30_sub3[i - 1][j];
                     if(object4 != null && object4.aBoolean1323 && (object4.aBoolean1322 || (class30_sub3_1.anInt1320 & 1) == 0)) {
                        continue;
                     }
                  }

                  if(i >= anInt453 && i < anInt450 - 1) {
                     object4 = aclass30_sub3[i + 1][j];
                     if(object4 != null && object4.aBoolean1323 && (object4.aBoolean1322 || (class30_sub3_1.anInt1320 & 4) == 0)) {
                        continue;
                     }
                  }

                  if(j <= anInt454 && j > anInt451) {
                     object4 = aclass30_sub3[i][j - 1];
                     if(object4 != null && object4.aBoolean1323 && (object4.aBoolean1322 || (class30_sub3_1.anInt1320 & 8) == 0)) {
                        continue;
                     }
                  }

                  if(j >= anInt454 && j < anInt452 - 1) {
                     object4 = aclass30_sub3[i][j + 1];
                     if(object4 != null && object4.aBoolean1323 && (object4.aBoolean1322 || (class30_sub3_1.anInt1320 & 2) == 0)) {
                        continue;
                     }
                  }
               } else {
                  flag = true;
               }

               class30_sub3_1.aBoolean1322 = false;
               if(class30_sub3_1.aClass30_Sub3_1329 != null) {
                  object4 = class30_sub3_1.aClass30_Sub3_1329;
                  if(object4.aClass43_1311 != null) {
                     if(!this.method320(0, i, j)) {
                        this.method315(object4.aClass43_1311, 0, anInt458, anInt459, anInt460, anInt461, i, j);
                     }
                  } else if(object4.aClass40_1312 != null && !this.method320(0, i, j)) {
                     this.method316(i, anInt458, anInt460, object4.aClass40_1312, anInt459, j, anInt461);
                  }

                  class30_sub3_16 = object4.obj1;
                  if(class30_sub3_16 != null) {
                     class30_sub3_16.aClass30_Sub2_Sub4_278.method443(0, anInt458, anInt459, anInt460, anInt461, class30_sub3_16.anInt274 - anInt455, class30_sub3_16.anInt273 - anInt456, class30_sub3_16.anInt275 - anInt457, class30_sub3_16.uid);
                  }

                  for(class10_2 = 0; class10_2 < object4.anInt1317; ++class10_2) {
                     j3 = object4.obj5Array[class10_2];
                     if(j3 != null) {
                        j3.aClass30_Sub2_Sub4_521.method443(j3.anInt522, anInt458, anInt459, anInt460, anInt461, j3.anInt519 - anInt455, j3.anInt518 - anInt456, j3.anInt520 - anInt457, j3.uid);
                     }
                  }
               }

               var23 = false;
               if(class30_sub3_1.aClass43_1311 != null) {
                  if(!this.method320(l, i, j)) {
                     var23 = true;
                     this.method315(class30_sub3_1.aClass43_1311, l, anInt458, anInt459, anInt460, anInt461, i, j);
                  }
               } else if(class30_sub3_1.aClass40_1312 != null && !this.method320(l, i, j)) {
                  var23 = true;
                  this.method316(i, anInt458, anInt460, class30_sub3_1.aClass40_1312, anInt459, j, anInt461);
               }

               var24 = 0;
               class10_2 = 0;
               Object1 var25 = class30_sub3_1.obj1;
               Object2 i4 = class30_sub3_1.obj2;
               if(var25 != null || i4 != null) {
                  if(anInt453 == i) {
                     ++var24;
                  } else if(anInt453 < i) {
                     var24 += 2;
                  }

                  if(anInt454 == j) {
                     var24 += 3;
                  } else if(anInt454 > j) {
                     var24 += 6;
                  }

                  class10_2 = anIntArray478[var24];
                  class30_sub3_1.anInt1328 = anIntArray480[var24];
               }

               if(var25 != null) {
                  if((var25.orientation & anIntArray479[var24]) != 0) {
                     if(var25.orientation == 16) {
                        class30_sub3_1.anInt1325 = 3;
                        class30_sub3_1.anInt1326 = anIntArray481[var24];
                        class30_sub3_1.anInt1327 = 3 - class30_sub3_1.anInt1326;
                     } else if(var25.orientation == 32) {
                        class30_sub3_1.anInt1325 = 6;
                        class30_sub3_1.anInt1326 = anIntArray482[var24];
                        class30_sub3_1.anInt1327 = 6 - class30_sub3_1.anInt1326;
                     } else if(var25.orientation == 64) {
                        class30_sub3_1.anInt1325 = 12;
                        class30_sub3_1.anInt1326 = anIntArray483[var24];
                        class30_sub3_1.anInt1327 = 12 - class30_sub3_1.anInt1326;
                     } else {
                        class30_sub3_1.anInt1325 = 9;
                        class30_sub3_1.anInt1326 = anIntArray484[var24];
                        class30_sub3_1.anInt1327 = 9 - class30_sub3_1.anInt1326;
                     }
                  } else {
                     class30_sub3_1.anInt1325 = 0;
                  }

                  if((var25.orientation & class10_2) != 0 && !this.method321(l, i, j, var25.orientation)) {
                     var25.aClass30_Sub2_Sub4_278.method443(0, anInt458, anInt459, anInt460, anInt461, var25.anInt274 - anInt455, var25.anInt273 - anInt456, var25.anInt275 - anInt457, var25.uid);
                  }

                  if((var25.orientation1 & class10_2) != 0 && !this.method321(l, i, j, var25.orientation1)) {
                     var25.aClass30_Sub2_Sub4_279.method443(0, anInt458, anInt459, anInt460, anInt461, var25.anInt274 - anInt455, var25.anInt273 - anInt456, var25.anInt275 - anInt457, var25.uid);
                  }
               }

               if(i4 != null && !this.method322(l, i, j, i4.aClass30_Sub2_Sub4_504.modelHeight)) {
                  if((i4.anInt502 & class10_2) != 0) {
                     i4.aClass30_Sub2_Sub4_504.method443(i4.anInt503, anInt458, anInt459, anInt460, anInt461, i4.anInt500 - anInt455, i4.anInt499 - anInt456, i4.anInt501 - anInt457, i4.uid);
                  } else if((i4.anInt502 & 768) != 0) {
                     k5 = i4.anInt500 - anInt455;
                     j6 = i4.anInt499 - anInt456;
                     l7 = i4.anInt501 - anInt457;
                     j9 = i4.anInt503;
                     if(j9 != 1 && j9 != 2) {
                        j10 = k5;
                     } else {
                        j10 = -k5;
                     }

                     int k10;
                     if(j9 != 2 && j9 != 3) {
                        k10 = l7;
                     } else {
                        k10 = -l7;
                     }

                     int l11;
                     int j11;
                     if((i4.anInt502 & 256) != 0 && k10 < j10) {
                        j11 = k5 + anIntArray463[j9];
                        l11 = l7 + anIntArray464[j9];
                        i4.aClass30_Sub2_Sub4_504.method443(j9 * 512 + 256, anInt458, anInt459, anInt460, anInt461, j11, j6, l11, i4.uid);
                     }

                     if((i4.anInt502 & 512) != 0 && k10 > j10) {
                        j11 = k5 + anIntArray465[j9];
                        l11 = l7 + anIntArray466[j9];
                        i4.aClass30_Sub2_Sub4_504.method443(j9 * 512 + 1280 & 2047, anInt458, anInt459, anInt460, anInt461, j11, j6, l11, i4.uid);
                     }
                  }
               }

               if(var23) {
                  Object3 var33 = class30_sub3_1.obj3;
                  if(var33 != null) {
                     var33.aClass30_Sub2_Sub4_814.method443(0, anInt458, anInt459, anInt460, anInt461, var33.anInt812 - anInt455, var33.anInt811 - anInt456, var33.anInt813 - anInt457, var33.uid);
                  }

                  Object4 var32 = class30_sub3_1.obj4;
                  if(var32 != null && var32.anInt52 == 0) {
                     if(var32.aClass30_Sub2_Sub4_49 != null) {
                        var32.aClass30_Sub2_Sub4_49.method443(0, anInt458, anInt459, anInt460, anInt461, var32.anInt46 - anInt455, var32.anInt45 - anInt456, var32.anInt47 - anInt457, var32.uid);
                     }

                     if(var32.aClass30_Sub2_Sub4_50 != null) {
                        var32.aClass30_Sub2_Sub4_50.method443(0, anInt458, anInt459, anInt460, anInt461, var32.anInt46 - anInt455, var32.anInt45 - anInt456, var32.anInt47 - anInt457, var32.uid);
                     }

                     if(var32.aClass30_Sub2_Sub4_48 != null) {
                        var32.aClass30_Sub2_Sub4_48.method443(0, anInt458, anInt459, anInt460, anInt461, var32.anInt46 - anInt455, var32.anInt45 - anInt456, var32.anInt47 - anInt457, var32.uid);
                     }
                  }
               }

               k5 = class30_sub3_1.anInt1320;
               if(k5 != 0) {
                  if(i < anInt453 && (k5 & 4) != 0) {
                     var37 = aclass30_sub3[i + 1][j];
                     if(var37 != null && var37.aBoolean1323) {
                        aClass19_477.insertHead(var37);
                     }
                  }

                  if(j < anInt454 && (k5 & 2) != 0) {
                     var37 = aclass30_sub3[i][j + 1];
                     if(var37 != null && var37.aBoolean1323) {
                        aClass19_477.insertHead(var37);
                     }
                  }

                  if(i > anInt453 && (k5 & 1) != 0) {
                     var37 = aclass30_sub3[i - 1][j];
                     if(var37 != null && var37.aBoolean1323) {
                        aClass19_477.insertHead(var37);
                     }
                  }

                  if(j > anInt454 && (k5 & 8) != 0) {
                     var37 = aclass30_sub3[i][j - 1];
                     if(var37 != null && var37.aBoolean1323) {
                        aClass19_477.insertHead(var37);
                     }
                  }
               }
            }

            if(class30_sub3_1.anInt1325 != 0) {
               var23 = true;

               for(var24 = 0; var24 < class30_sub3_1.anInt1317; ++var24) {
                  if(class30_sub3_1.obj5Array[var24].anInt528 != anInt448 && (class30_sub3_1.anIntArray1319[var24] & class30_sub3_1.anInt1325) == class30_sub3_1.anInt1326) {
                     var23 = false;
                     break;
                  }
               }

               if(var23) {
                  class30_sub3_16 = class30_sub3_1.obj1;
                  if(!this.method321(l, i, j, class30_sub3_16.orientation)) {
                     class30_sub3_16.aClass30_Sub2_Sub4_278.method443(0, anInt458, anInt459, anInt460, anInt461, class30_sub3_16.anInt274 - anInt455, class30_sub3_16.anInt273 - anInt456, class30_sub3_16.anInt275 - anInt457, class30_sub3_16.uid);
                  }

                  class30_sub3_1.anInt1325 = 0;
               }
            }

            int var26;
            int var28;
            if(class30_sub3_1.aBoolean1324) {
               try {
                  int var27 = class30_sub3_1.anInt1317;
                  class30_sub3_1.aBoolean1324 = false;
                  var24 = 0;

                  label556:
                  for(class10_2 = 0; class10_2 < var27; ++class10_2) {
                     j3 = class30_sub3_1.obj5Array[class10_2];
                     if(j3.anInt528 != anInt448) {
                        for(var28 = j3.anInt523; var28 <= j3.anInt524; ++var28) {
                           for(k5 = j3.anInt525; k5 <= j3.anInt526; ++k5) {
                              var37 = aclass30_sub3[var28][k5];
                              if(var37.aBoolean1322) {
                                 class30_sub3_1.aBoolean1324 = true;
                                 continue label556;
                              }

                              if(var37.anInt1325 != 0) {
                                 l7 = 0;
                                 if(var28 > j3.anInt523) {
                                    ++l7;
                                 }

                                 if(var28 < j3.anInt524) {
                                    l7 += 4;
                                 }

                                 if(k5 > j3.anInt525) {
                                    l7 += 8;
                                 }

                                 if(k5 < j3.anInt526) {
                                    l7 += 2;
                                 }

                                 if((l7 & var37.anInt1325) == class30_sub3_1.anInt1327) {
                                    class30_sub3_1.aBoolean1324 = true;
                                    continue label556;
                                 }
                              }
                           }
                        }

                        aClass28Array462[var24++] = j3;
                        var28 = anInt453 - j3.anInt523;
                        k5 = j3.anInt524 - anInt453;
                        if(k5 > var28) {
                           var28 = k5;
                        }

                        j6 = anInt454 - j3.anInt525;
                        l7 = j3.anInt526 - anInt454;
                        if(l7 > j6) {
                           j3.anInt527 = var28 + l7;
                        } else {
                           j3.anInt527 = var28 + j6;
                        }
                     }
                  }

                  while(var24 > 0) {
                     class10_2 = -50;
                     var26 = -1;

                     for(var28 = 0; var28 < var24; ++var28) {
                        Object5 var36 = aClass28Array462[var28];
                        if(var36.anInt528 != anInt448) {
                           if(var36.anInt527 > class10_2) {
                              class10_2 = var36.anInt527;
                              var26 = var28;
                           } else if(var36.anInt527 == class10_2) {
                              j6 = var36.anInt519 - anInt455;
                              l7 = var36.anInt520 - anInt457;
                              j9 = aClass28Array462[var26].anInt519 - anInt455;
                              j10 = aClass28Array462[var26].anInt520 - anInt457;
                              if(j6 * j6 + l7 * l7 > j9 * j9 + j10 * j10) {
                                 var26 = var28;
                              }
                           }
                        }
                     }

                     if(var26 == -1) {
                        break;
                     }

                     Object5 var35 = aClass28Array462[var26];
                     var35.anInt528 = anInt448;
                     if(!this.method323(l, var35.anInt523, var35.anInt524, var35.anInt525, var35.anInt526, var35.aClass30_Sub2_Sub4_521.modelHeight)) {
                        var35.aClass30_Sub2_Sub4_521.method443(var35.anInt522, anInt458, anInt459, anInt460, anInt461, var35.anInt519 - anInt455, var35.anInt518 - anInt456, var35.anInt520 - anInt457, var35.uid);
                     }

                     for(k5 = var35.anInt523; k5 <= var35.anInt524; ++k5) {
                        for(j6 = var35.anInt525; j6 <= var35.anInt526; ++j6) {
                           Ground var38 = aclass30_sub3[k5][j6];
                           if(var38.anInt1325 != 0) {
                              aClass19_477.insertHead(var38);
                           } else if((k5 != i || j6 != j) && var38.aBoolean1323) {
                              aClass19_477.insertHead(var38);
                           }
                        }
                     }
                  }

                  if(class30_sub3_1.aBoolean1324) {
                     continue;
                  }
               } catch (Exception var22) {
                  class30_sub3_1.aBoolean1324 = false;
               }
            }

            if(class30_sub3_1.aBoolean1323 && class30_sub3_1.anInt1325 == 0) {
               if(i <= anInt453 && i > anInt449) {
                  object4 = aclass30_sub3[i - 1][j];
                  if(object4 != null && object4.aBoolean1323) {
                     continue;
                  }
               }

               if(i >= anInt453 && i < anInt450 - 1) {
                  object4 = aclass30_sub3[i + 1][j];
                  if(object4 != null && object4.aBoolean1323) {
                     continue;
                  }
               }

               if(j <= anInt454 && j > anInt451) {
                  object4 = aclass30_sub3[i][j - 1];
                  if(object4 != null && object4.aBoolean1323) {
                     continue;
                  }
               }

               if(j >= anInt454 && j < anInt452 - 1) {
                  object4 = aclass30_sub3[i][j + 1];
                  if(object4 != null && object4.aBoolean1323) {
                     continue;
                  }
               }

               class30_sub3_1.aBoolean1323 = false;
               --anInt446;
               Object4 var30 = class30_sub3_1.obj4;
               if(var30 != null && var30.anInt52 != 0) {
                  if(var30.aClass30_Sub2_Sub4_49 != null) {
                     var30.aClass30_Sub2_Sub4_49.method443(0, anInt458, anInt459, anInt460, anInt461, var30.anInt46 - anInt455, var30.anInt45 - anInt456 - var30.anInt52, var30.anInt47 - anInt457, var30.uid);
                  }

                  if(var30.aClass30_Sub2_Sub4_50 != null) {
                     var30.aClass30_Sub2_Sub4_50.method443(0, anInt458, anInt459, anInt460, anInt461, var30.anInt46 - anInt455, var30.anInt45 - anInt456 - var30.anInt52, var30.anInt47 - anInt457, var30.uid);
                  }

                  if(var30.aClass30_Sub2_Sub4_48 != null) {
                     var30.aClass30_Sub2_Sub4_48.method443(0, anInt458, anInt459, anInt460, anInt461, var30.anInt46 - anInt455, var30.anInt45 - anInt456 - var30.anInt52, var30.anInt47 - anInt457, var30.uid);
                  }
               }

               if(class30_sub3_1.anInt1328 != 0) {
                  Object2 var29 = class30_sub3_1.obj2;
                  if(var29 != null && !this.method322(l, i, j, var29.aClass30_Sub2_Sub4_504.modelHeight)) {
                     if((var29.anInt502 & class30_sub3_1.anInt1328) != 0) {
                        var29.aClass30_Sub2_Sub4_504.method443(var29.anInt503, anInt458, anInt459, anInt460, anInt461, var29.anInt500 - anInt455, var29.anInt499 - anInt456, var29.anInt501 - anInt457, var29.uid);
                     } else if((var29.anInt502 & 768) != 0) {
                        class10_2 = var29.anInt500 - anInt455;
                        var26 = var29.anInt499 - anInt456;
                        var28 = var29.anInt501 - anInt457;
                        k5 = var29.anInt503;
                        if(k5 != 1 && k5 != 2) {
                           j6 = class10_2;
                        } else {
                           j6 = -class10_2;
                        }

                        if(k5 != 2 && k5 != 3) {
                           l7 = var28;
                        } else {
                           l7 = -var28;
                        }

                        if((var29.anInt502 & 256) != 0 && l7 >= j6) {
                           j9 = class10_2 + anIntArray463[k5];
                           j10 = var28 + anIntArray464[k5];
                           var29.aClass30_Sub2_Sub4_504.method443(k5 * 512 + 256, anInt458, anInt459, anInt460, anInt461, j9, var26, j10, var29.uid);
                        }

                        if((var29.anInt502 & 512) != 0 && l7 <= j6) {
                           j9 = class10_2 + anIntArray465[k5];
                           j10 = var28 + anIntArray466[k5];
                           var29.aClass30_Sub2_Sub4_504.method443(k5 * 512 + 1280 & 2047, anInt458, anInt459, anInt460, anInt461, j9, var26, j10, var29.uid);
                        }
                     }
                  }

                  Object1 var34 = class30_sub3_1.obj1;
                  if(var34 != null) {
                     if((var34.orientation1 & class30_sub3_1.anInt1328) != 0 && !this.method321(l, i, j, var34.orientation1)) {
                        var34.aClass30_Sub2_Sub4_279.method443(0, anInt458, anInt459, anInt460, anInt461, var34.anInt274 - anInt455, var34.anInt273 - anInt456, var34.anInt275 - anInt457, var34.uid);
                     }

                     if((var34.orientation & class30_sub3_1.anInt1328) != 0 && !this.method321(l, i, j, var34.orientation)) {
                        var34.aClass30_Sub2_Sub4_278.method443(0, anInt458, anInt459, anInt460, anInt461, var34.anInt274 - anInt455, var34.anInt273 - anInt456, var34.anInt275 - anInt457, var34.uid);
                     }
                  }
               }

               Ground var31;
               if(k < this.anInt437 - 1) {
                  var31 = this.groundArray[k + 1][i][j];
                  if(var31 != null && var31.aBoolean1323) {
                     aClass19_477.insertHead(var31);
                  }
               }

               if(i < anInt453) {
                  var31 = aclass30_sub3[i + 1][j];
                  if(var31 != null && var31.aBoolean1323) {
                     aClass19_477.insertHead(var31);
                  }
               }

               if(j < anInt454) {
                  var31 = aclass30_sub3[i][j + 1];
                  if(var31 != null && var31.aBoolean1323) {
                     aClass19_477.insertHead(var31);
                  }
               }

               if(i > anInt453) {
                  var31 = aclass30_sub3[i - 1][j];
                  if(var31 != null && var31.aBoolean1323) {
                     aClass19_477.insertHead(var31);
                  }
               }

               if(j > anInt454) {
                  var31 = aclass30_sub3[i][j - 1];
                  if(var31 != null && var31.aBoolean1323) {
                     aClass19_477.insertHead(var31);
                  }
               }
            }
         }
      }
   }

   private void method315(Class43 class43, int i, int j, int k, int l, int i1, int j1, int k1) {
      int l1;
      int i2 = l1 = (j1 << 7) - anInt455;
      int j2;
      int k2 = j2 = (k1 << 7) - anInt457;
      int l2;
      int i3 = l2 = i2 + 128;
      int j3;
      int k3 = j3 = k2 + 128;
      int l3 = this.anIntArrayArrayArray440[i][j1][k1] - anInt456;
      int i4 = this.anIntArrayArrayArray440[i][j1 + 1][k1] - anInt456;
      int j4 = this.anIntArrayArrayArray440[i][j1 + 1][k1 + 1] - anInt456;
      int k4 = this.anIntArrayArrayArray440[i][j1][k1 + 1] - anInt456;
      int l4 = k2 * l + i2 * i1 >> 16;
      k2 = k2 * i1 - i2 * l >> 16;
      i2 = l4;
      l4 = l3 * k - k2 * j >> 16;
      k2 = l3 * j + k2 * k >> 16;
      l3 = l4;
      if(k2 >= 50) {
         l4 = j2 * l + i3 * i1 >> 16;
         j2 = j2 * i1 - i3 * l >> 16;
         i3 = l4;
         l4 = i4 * k - j2 * j >> 16;
         j2 = i4 * j + j2 * k >> 16;
         i4 = l4;
         if(j2 >= 50) {
            l4 = k3 * l + l2 * i1 >> 16;
            k3 = k3 * i1 - l2 * l >> 16;
            l2 = l4;
            l4 = j4 * k - k3 * j >> 16;
            k3 = j4 * j + k3 * k >> 16;
            j4 = l4;
            if(k3 >= 50) {
               l4 = j3 * l + l1 * i1 >> 16;
               j3 = j3 * i1 - l1 * l >> 16;
               l1 = l4;
               l4 = k4 * k - j3 * j >> 16;
               j3 = k4 * j + j3 * k >> 16;
               if(j3 >= 50) {
                  int i5 = Texture.textureInt1 + (i2 << 9) / k2;
                  int j5 = Texture.textureInt2 + (l3 << 9) / k2;
                  int k5 = Texture.textureInt1 + (i3 << 9) / j2;
                  int l5 = Texture.textureInt2 + (i4 << 9) / j2;
                  int i6 = Texture.textureInt1 + (l2 << 9) / k3;
                  int j6 = Texture.textureInt2 + (j4 << 9) / k3;
                  int k6 = Texture.textureInt1 + (l1 << 9) / j3;
                  int l6 = Texture.textureInt2 + (l4 << 9) / j3;
                  Texture.anInt1465 = 0;
                  int j7;
                  if((i6 - k6) * (l5 - l6) - (j6 - l6) * (k5 - k6) > 0) {
                     Texture.aBoolean1462 = i6 < 0 || k6 < 0 || k5 < 0 || i6 > DrawingArea.centerX || k6 > DrawingArea.centerX || k5 > DrawingArea.centerX;
                     if(aBoolean467 && this.method318(anInt468, anInt469, j6, l6, l5, i6, k6, k5)) {
                        anInt470 = j1;
                        anInt471 = k1;
                     }

                     if(class43.anInt720 == -1) {
                        if(class43.anInt718 != 12345678) {
                           Texture.method374(j6, l6, l5, i6, k6, k5, class43.anInt718, class43.anInt719, class43.anInt717);
                        }
                     } else if(!lowMem) {
                        if(class43.aBoolean721) {
                           Texture.method378(j6, l6, l5, i6, k6, k5, class43.anInt718, class43.anInt719, class43.anInt717, i2, i3, l1, l3, i4, l4, k2, j2, j3, class43.anInt720);
                        } else {
                           Texture.method378(j6, l6, l5, i6, k6, k5, class43.anInt718, class43.anInt719, class43.anInt717, l2, l1, i3, j4, l4, i4, k3, j3, j2, class43.anInt720);
                        }
                     } else {
                        j7 = anIntArray485[class43.anInt720];
                        Texture.method374(j6, l6, l5, i6, k6, k5, this.method317(j7, class43.anInt718), this.method317(j7, class43.anInt719), this.method317(j7, class43.anInt717));
                     }
                  }

                  if((i5 - k5) * (l6 - l5) - (j5 - l5) * (k6 - k5) > 0) {
                     Texture.aBoolean1462 = i5 < 0 || k5 < 0 || k6 < 0 || i5 > DrawingArea.centerX || k5 > DrawingArea.centerX || k6 > DrawingArea.centerX;
                     if(aBoolean467 && this.method318(anInt468, anInt469, j5, l5, l6, i5, k5, k6)) {
                        anInt470 = j1;
                        anInt471 = k1;
                     }

                     if(class43.anInt720 == -1) {
                        if(class43.anInt716 != 12345678) {
                           Texture.method374(j5, l5, l6, i5, k5, k6, class43.anInt716, class43.anInt717, class43.anInt719);
                        }
                     } else {
                        if(!lowMem) {
                           Texture.method378(j5, l5, l6, i5, k5, k6, class43.anInt716, class43.anInt717, class43.anInt719, i2, i3, l1, l3, i4, l4, k2, j2, j3, class43.anInt720);
                           return;
                        }

                        j7 = anIntArray485[class43.anInt720];
                        Texture.method374(j5, l5, l6, i5, k5, k6, this.method317(j7, class43.anInt716), this.method317(j7, class43.anInt717), this.method317(j7, class43.anInt719));
                     }
                  }

               }
            }
         }
      }
   }

   private void method316(int i, int j, int k, Class40 class40, int l, int i1, int j1) {
      int k1 = class40.anIntArray673.length;

      int j2;
      int l2;
      int j3;
      int l3;
      int i4;
      for(j2 = 0; j2 < k1; ++j2) {
         l2 = class40.anIntArray673[j2] - anInt455;
         j3 = class40.anIntArray674[j2] - anInt456;
         l3 = class40.anIntArray675[j2] - anInt457;
         i4 = l3 * k + l2 * j1 >> 16;
         l3 = l3 * j1 - l2 * k >> 16;
         l2 = i4;
         i4 = j3 * l - l3 * j >> 16;
         l3 = j3 * j + l3 * l >> 16;
         if(l3 < 50) {
            return;
         }

         if(class40.anIntArray682 != null) {
            Class40.anIntArray690[j2] = l2;
            Class40.anIntArray691[j2] = i4;
            Class40.anIntArray692[j2] = l3;
         }

         Class40.anIntArray688[j2] = Texture.textureInt1 + (l2 << 9) / l3;
         Class40.anIntArray689[j2] = Texture.textureInt2 + (i4 << 9) / l3;
      }

      Texture.anInt1465 = 0;
      k1 = class40.anIntArray679.length;

      for(j2 = 0; j2 < k1; ++j2) {
         l2 = class40.anIntArray679[j2];
         j3 = class40.anIntArray680[j2];
         l3 = class40.anIntArray681[j2];
         i4 = Class40.anIntArray688[l2];
         int j4 = Class40.anIntArray688[j3];
         int k4 = Class40.anIntArray688[l3];
         int l4 = Class40.anIntArray689[l2];
         int i5 = Class40.anIntArray689[j3];
         int j5 = Class40.anIntArray689[l3];
         if((i4 - j4) * (j5 - i5) - (l4 - i5) * (k4 - j4) > 0) {
            Texture.aBoolean1462 = i4 < 0 || j4 < 0 || k4 < 0 || i4 > DrawingArea.centerX || j4 > DrawingArea.centerX || k4 > DrawingArea.centerX;
            if(aBoolean467 && this.method318(anInt468, anInt469, l4, i5, j5, i4, j4, k4)) {
               anInt470 = i;
               anInt471 = i1;
            }

            if(class40.anIntArray682 != null && class40.anIntArray682[j2] != -1) {
               if(!lowMem) {
                  if(class40.aBoolean683) {
                     Texture.method378(l4, i5, j5, i4, j4, k4, class40.anIntArray676[j2], class40.anIntArray677[j2], class40.anIntArray678[j2], Class40.anIntArray690[0], Class40.anIntArray690[1], Class40.anIntArray690[3], Class40.anIntArray691[0], Class40.anIntArray691[1], Class40.anIntArray691[3], Class40.anIntArray692[0], Class40.anIntArray692[1], Class40.anIntArray692[3], class40.anIntArray682[j2]);
                  } else {
                     Texture.method378(l4, i5, j5, i4, j4, k4, class40.anIntArray676[j2], class40.anIntArray677[j2], class40.anIntArray678[j2], Class40.anIntArray690[l2], Class40.anIntArray690[j3], Class40.anIntArray690[l3], Class40.anIntArray691[l2], Class40.anIntArray691[j3], Class40.anIntArray691[l3], Class40.anIntArray692[l2], Class40.anIntArray692[j3], Class40.anIntArray692[l3], class40.anIntArray682[j2]);
                  }
               } else {
                  int k5 = anIntArray485[class40.anIntArray682[j2]];
                  Texture.method374(l4, i5, j5, i4, j4, k4, this.method317(k5, class40.anIntArray676[j2]), this.method317(k5, class40.anIntArray677[j2]), this.method317(k5, class40.anIntArray678[j2]));
               }
            } else if(class40.anIntArray676[j2] != 12345678) {
               Texture.method374(l4, i5, j5, i4, j4, k4, class40.anIntArray676[j2], class40.anIntArray677[j2], class40.anIntArray678[j2]);
            }
         }
      }

   }

   private int method317(int j, int k) {
      k = 127 - k;
      k = k * (j & 127) / 160;
      if(k < 2) {
         k = 2;
      } else if(k > 126) {
         k = 126;
      }

      return (j & '\uff80') + k;
   }

   private boolean method318(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
      if(j < k && j < l && j < i1) {
         return false;
      } else if(j > k && j > l && j > i1) {
         return false;
      } else if(i < j1 && i < k1 && i < l1) {
         return false;
      } else if(i > j1 && i > k1 && i > l1) {
         return false;
      } else {
         int i2 = (j - k) * (k1 - j1) - (i - j1) * (l - k);
         int j2 = (j - i1) * (j1 - l1) - (i - l1) * (k - i1);
         int k2 = (j - l) * (l1 - k1) - (i - k1) * (i1 - l);
         return i2 * k2 > 0 && k2 * j2 > 0;
      }
   }

   private void method319() {
      int j = anIntArray473[anInt447];
      Class47[] aclass47 = aClass47ArrayArray474[anInt447];
      anInt475 = 0;

      for(int k = 0; k < j; ++k) {
         Class47 class47 = aclass47[k];
         int j1;
         int i2;
         int l2;
         boolean i3;
         int l3;
         if(class47.anInt791 == 1) {
            j1 = class47.anInt787 - anInt453 + 25;
            if(j1 >= 0 && j1 <= 50) {
               i2 = class47.anInt789 - anInt454 + 25;
               if(i2 < 0) {
                  i2 = 0;
               }

               l2 = class47.anInt790 - anInt454 + 25;
               if(l2 > 50) {
                  l2 = 50;
               }

               i3 = false;

               while(i2 <= l2) {
                  if(aBooleanArrayArray492[j1][i2++]) {
                     i3 = true;
                     break;
                  }
               }

               if(i3) {
                  l3 = anInt455 - class47.anInt792;
                  if(l3 > 32) {
                     class47.anInt798 = 1;
                  } else {
                     if(l3 >= -32) {
                        continue;
                     }

                     class47.anInt798 = 2;
                     l3 = -l3;
                  }

                  class47.anInt801 = (class47.anInt794 - anInt457 << 8) / l3;
                  class47.anInt802 = (class47.anInt795 - anInt457 << 8) / l3;
                  class47.anInt803 = (class47.anInt796 - anInt456 << 8) / l3;
                  class47.anInt804 = (class47.anInt797 - anInt456 << 8) / l3;
                  aClass47Array476[anInt475++] = class47;
               }
            }
         } else if(class47.anInt791 == 2) {
            j1 = class47.anInt789 - anInt454 + 25;
            if(j1 >= 0 && j1 <= 50) {
               i2 = class47.anInt787 - anInt453 + 25;
               if(i2 < 0) {
                  i2 = 0;
               }

               l2 = class47.anInt788 - anInt453 + 25;
               if(l2 > 50) {
                  l2 = 50;
               }

               i3 = false;

               while(i2 <= l2) {
                  if(aBooleanArrayArray492[i2++][j1]) {
                     i3 = true;
                     break;
                  }
               }

               if(i3) {
                  l3 = anInt457 - class47.anInt794;
                  if(l3 > 32) {
                     class47.anInt798 = 3;
                  } else {
                     if(l3 >= -32) {
                        continue;
                     }

                     class47.anInt798 = 4;
                     l3 = -l3;
                  }

                  class47.anInt799 = (class47.anInt792 - anInt455 << 8) / l3;
                  class47.anInt800 = (class47.anInt793 - anInt455 << 8) / l3;
                  class47.anInt803 = (class47.anInt796 - anInt456 << 8) / l3;
                  class47.anInt804 = (class47.anInt797 - anInt456 << 8) / l3;
                  aClass47Array476[anInt475++] = class47;
               }
            }
         } else if(class47.anInt791 == 4) {
            j1 = class47.anInt796 - anInt456;
            if(j1 > 128) {
               i2 = class47.anInt789 - anInt454 + 25;
               if(i2 < 0) {
                  i2 = 0;
               }

               l2 = class47.anInt790 - anInt454 + 25;
               if(l2 > 50) {
                  l2 = 50;
               }

               if(i2 <= l2) {
                  int var13 = class47.anInt787 - anInt453 + 25;
                  if(var13 < 0) {
                     var13 = 0;
                  }

                  l3 = class47.anInt788 - anInt453 + 25;
                  if(l3 > 50) {
                     l3 = 50;
                  }

                  boolean flag2 = false;

                  label111:
                  for(int i4 = var13; i4 <= l3; ++i4) {
                     for(int j4 = i2; j4 <= l2; ++j4) {
                        if(aBooleanArrayArray492[i4][j4]) {
                           flag2 = true;
                           break label111;
                        }
                     }
                  }

                  if(flag2) {
                     class47.anInt798 = 5;
                     class47.anInt799 = (class47.anInt792 - anInt455 << 8) / j1;
                     class47.anInt800 = (class47.anInt793 - anInt455 << 8) / j1;
                     class47.anInt801 = (class47.anInt794 - anInt457 << 8) / j1;
                     class47.anInt802 = (class47.anInt795 - anInt457 << 8) / j1;
                     aClass47Array476[anInt475++] = class47;
                  }
               }
            }
         }
      }

   }

   private boolean method320(int i, int j, int k) {
      int l = this.anIntArrayArrayArray445[i][j][k];
      if(l == -anInt448) {
         return false;
      } else if(l == anInt448) {
         return true;
      } else {
         int i1 = j << 7;
         int j1 = k << 7;
         if(this.method324(i1 + 1, this.anIntArrayArrayArray440[i][j][k], j1 + 1) && this.method324(i1 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][k], j1 + 1) && this.method324(i1 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][k + 1], j1 + 128 - 1) && this.method324(i1 + 1, this.anIntArrayArrayArray440[i][j][k + 1], j1 + 128 - 1)) {
            this.anIntArrayArrayArray445[i][j][k] = anInt448;
            return true;
         } else {
            this.anIntArrayArrayArray445[i][j][k] = -anInt448;
            return false;
         }
      }
   }

   private boolean method321(int i, int j, int k, int l) {
      if(!this.method320(i, j, k)) {
         return false;
      } else {
         int i1 = j << 7;
         int j1 = k << 7;
         int k1 = this.anIntArrayArrayArray440[i][j][k] - 1;
         int l1 = k1 - 120;
         int i2 = k1 - 230;
         int j2 = k1 - 238;
         if(l < 16) {
            if(l == 1) {
               if(i1 > anInt455) {
                  if(!this.method324(i1, k1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1, k1, j1 + 128)) {
                     return false;
                  }
               }

               if(i > 0) {
                  if(!this.method324(i1, l1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1, l1, j1 + 128)) {
                     return false;
                  }
               }

               return this.method324(i1, i2, j1) && this.method324(i1, i2, j1 + 128);
            }

            if(l == 2) {
               if(j1 < anInt457) {
                  if(!this.method324(i1, k1, j1 + 128)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, k1, j1 + 128)) {
                     return false;
                  }
               }

               if(i > 0) {
                  if(!this.method324(i1, l1, j1 + 128)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, l1, j1 + 128)) {
                     return false;
                  }
               }

               return this.method324(i1, i2, j1 + 128) && this.method324(i1 + 128, i2, j1 + 128);
            }

            if(l == 4) {
               if(i1 < anInt455) {
                  if(!this.method324(i1 + 128, k1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, k1, j1 + 128)) {
                     return false;
                  }
               }

               if(i > 0) {
                  if(!this.method324(i1 + 128, l1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, l1, j1 + 128)) {
                     return false;
                  }
               }

               return this.method324(i1 + 128, i2, j1) && this.method324(i1 + 128, i2, j1 + 128);
            }

            if(l == 8) {
               if(j1 > anInt457) {
                  if(!this.method324(i1, k1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, k1, j1)) {
                     return false;
                  }
               }

               if(i > 0) {
                  if(!this.method324(i1, l1, j1)) {
                     return false;
                  }

                  if(!this.method324(i1 + 128, l1, j1)) {
                     return false;
                  }
               }

               return this.method324(i1, i2, j1) && this.method324(i1 + 128, i2, j1);
            }
         }

         if(!this.method324(i1 + 64, j2, j1 + 64)) {
            return false;
         } else if(l == 16) {
            return this.method324(i1, i2, j1 + 128);
         } else if(l == 32) {
            return this.method324(i1 + 128, i2, j1 + 128);
         } else if(l == 64) {
            return this.method324(i1 + 128, i2, j1);
         } else if(l == 128) {
            return this.method324(i1, i2, j1);
         } else {
            System.out.println("Warning unsupported wall type");
            return true;
         }
      }
   }

   private boolean method322(int i, int j, int k, int l) {
      if(!this.method320(i, j, k)) {
         return false;
      } else {
         int i1 = j << 7;
         int j1 = k << 7;
         return this.method324(i1 + 1, this.anIntArrayArrayArray440[i][j][k] - l, j1 + 1) && this.method324(i1 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][k] - l, j1 + 1) && this.method324(i1 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][k + 1] - l, j1 + 128 - 1) && this.method324(i1 + 1, this.anIntArrayArrayArray440[i][j][k + 1] - l, j1 + 128 - 1);
      }
   }

   private boolean method323(int i, int j, int k, int l, int i1, int j1) {
      int k2;
      int l2;
      if(j == k && l == i1) {
         if(!this.method320(i, j, l)) {
            return false;
         } else {
            k2 = j << 7;
            l2 = l << 7;
            return this.method324(k2 + 1, this.anIntArrayArrayArray440[i][j][l] - j1, l2 + 1) && this.method324(k2 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][l] - j1, l2 + 1) && this.method324(k2 + 128 - 1, this.anIntArrayArrayArray440[i][j + 1][l + 1] - j1, l2 + 128 - 1) && this.method324(k2 + 1, this.anIntArrayArrayArray440[i][j][l + 1] - j1, l2 + 128 - 1);
         }
      } else {
         for(k2 = j; k2 <= k; ++k2) {
            for(l2 = l; l2 <= i1; ++l2) {
               if(this.anIntArrayArrayArray445[i][k2][l2] == -anInt448) {
                  return false;
               }
            }
         }

         k2 = (j << 7) + 1;
         l2 = (l << 7) + 2;
         int i3 = this.anIntArrayArrayArray440[i][j][l] - j1;
         if(!this.method324(k2, i3, l2)) {
            return false;
         } else {
            int j3 = (k << 7) - 1;
            if(!this.method324(j3, i3, l2)) {
               return false;
            } else {
               int k3 = (i1 << 7) - 1;
               return this.method324(k2, i3, k3) && this.method324(j3, i3, k3);
            }
         }
      }
   }

   private boolean method324(int i, int j, int k) {
      for(int l = 0; l < anInt475; ++l) {
         Class47 class47 = aClass47Array476[l];
         int i2;
         int j3;
         int k4;
         int l5;
         int i7;
         if(class47.anInt798 == 1) {
            i2 = class47.anInt792 - i;
            if(i2 > 0) {
               j3 = class47.anInt794 + (class47.anInt801 * i2 >> 8);
               k4 = class47.anInt795 + (class47.anInt802 * i2 >> 8);
               l5 = class47.anInt796 + (class47.anInt803 * i2 >> 8);
               i7 = class47.anInt797 + (class47.anInt804 * i2 >> 8);
               if(k >= j3 && k <= k4 && j >= l5 && j <= i7) {
                  return true;
               }
            }
         } else if(class47.anInt798 == 2) {
            i2 = i - class47.anInt792;
            if(i2 > 0) {
               j3 = class47.anInt794 + (class47.anInt801 * i2 >> 8);
               k4 = class47.anInt795 + (class47.anInt802 * i2 >> 8);
               l5 = class47.anInt796 + (class47.anInt803 * i2 >> 8);
               i7 = class47.anInt797 + (class47.anInt804 * i2 >> 8);
               if(k >= j3 && k <= k4 && j >= l5 && j <= i7) {
                  return true;
               }
            }
         } else if(class47.anInt798 == 3) {
            i2 = class47.anInt794 - k;
            if(i2 > 0) {
               j3 = class47.anInt792 + (class47.anInt799 * i2 >> 8);
               k4 = class47.anInt793 + (class47.anInt800 * i2 >> 8);
               l5 = class47.anInt796 + (class47.anInt803 * i2 >> 8);
               i7 = class47.anInt797 + (class47.anInt804 * i2 >> 8);
               if(i >= j3 && i <= k4 && j >= l5 && j <= i7) {
                  return true;
               }
            }
         } else if(class47.anInt798 == 4) {
            i2 = k - class47.anInt794;
            if(i2 > 0) {
               j3 = class47.anInt792 + (class47.anInt799 * i2 >> 8);
               k4 = class47.anInt793 + (class47.anInt800 * i2 >> 8);
               l5 = class47.anInt796 + (class47.anInt803 * i2 >> 8);
               i7 = class47.anInt797 + (class47.anInt804 * i2 >> 8);
               if(i >= j3 && i <= k4 && j >= l5 && j <= i7) {
                  return true;
               }
            }
         } else if(class47.anInt798 == 5) {
            i2 = j - class47.anInt796;
            if(i2 > 0) {
               j3 = class47.anInt792 + (class47.anInt799 * i2 >> 8);
               k4 = class47.anInt793 + (class47.anInt800 * i2 >> 8);
               l5 = class47.anInt794 + (class47.anInt801 * i2 >> 8);
               i7 = class47.anInt795 + (class47.anInt802 * i2 >> 8);
               if(i >= j3 && i <= k4 && k >= l5 && k <= i7) {
                  return true;
               }
            }
         }
      }

      return false;
   }

}
