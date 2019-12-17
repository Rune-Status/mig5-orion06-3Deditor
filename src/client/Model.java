package client;


public final class Model extends Animable {

   public static final Model aModel_1621 = new Model();
   private static int[] anIntArray1622 = new int[2000];
   private static int[] anIntArray1623 = new int[2000];
   private static int[] anIntArray1624 = new int[2000];
   private static int[] anIntArray1625 = new int[2000];
   public int anInt1626;
   public int[] anIntArray1627;
   public int[] anIntArray1628;
   public int[] anIntArray1629;
   public int anInt1630;
   public int[] anIntArray1631;
   public int[] anIntArray1632;
   public int[] anIntArray1633;
   private int[] anIntArray1634;
   private int[] anIntArray1635;
   private int[] anIntArray1636;
   public int[] anIntArray1637;
   private int[] anIntArray1638;
   private int[] anIntArray1639;
   public int[] anIntArray1640;
   private int anInt1641;
   private int anInt1642;
   private int[] anIntArray1643;
   private int[] anIntArray1644;
   private int[] anIntArray1645;
   public int anInt1646;
   public int anInt1647;
   public int anInt1648;
   public int anInt1649;
   public int anInt1650;
   public int anInt1651;
   private int anInt1652;
   private int anInt1653;
   public int anInt1654;
   private int[] anIntArray1655;
   private int[] anIntArray1656;
   public int[][] anIntArrayArray1657;
   public int[][] anIntArrayArray1658;
   public boolean aBoolean1659;
   Class33[] aClass33Array1660;
   private static Class21[] aClass21Array1661;
   private static OnDemandFetcherParent aOnDemandFetcherParent_1662;
   private static boolean[] aBooleanArray1663 = new boolean[4096];
   private static boolean[] aBooleanArray1664 = new boolean[4096];
   private static int[] anIntArray1665 = new int[4096];
   private static int[] anIntArray1666 = new int[4096];
   private static int[] anIntArray1667 = new int[4096];
   private static int[] anIntArray1668 = new int[4096];
   private static int[] anIntArray1669 = new int[4096];
   private static int[] anIntArray1670 = new int[4096];
   private static int[] anIntArray1671 = new int[1500];
   private static int[][] anIntArrayArray1672 = new int[1500][512];
   private static int[] anIntArray1673 = new int[12];
   private static int[][] anIntArrayArray1674 = new int[12][2000];
   private static int[] anIntArray1675 = new int[2000];
   private static int[] anIntArray1676 = new int[2000];
   private static int[] anIntArray1677 = new int[12];
   private static final int[] anIntArray1678 = new int[10];
   private static final int[] anIntArray1679 = new int[10];
   private static final int[] anIntArray1680 = new int[10];
   private static int anInt1681;
   private static int anInt1682;
   private static int anInt1683;
   public static boolean aBoolean1684;
   public static int anInt1685;
   public static int anInt1686;
   public static int anInt1687;
   public static final int[] anIntArray1688 = new int[1000];
   public static int[] modelIntArray1 = Texture.anIntArray1470;
   public static int[] modelIntArray2 = Texture.anIntArray1471;
   private static int[] modelIntArray3 = Texture.anIntArray1482;
   private static int[] modelIntArray4 = Texture.anIntArray1469;


   public static void nullLoader() {
      aClass21Array1661 = null;
      aBooleanArray1663 = null;
      aBooleanArray1664 = null;
      anIntArray1665 = null;
      anIntArray1666 = null;
      anIntArray1667 = null;
      anIntArray1668 = null;
      anIntArray1669 = null;
      anIntArray1670 = null;
      anIntArray1671 = null;
      anIntArrayArray1672 = (int[][])null;
      anIntArray1673 = null;
      anIntArrayArray1674 = (int[][])null;
      anIntArray1675 = null;
      anIntArray1676 = null;
      anIntArray1677 = null;
      modelIntArray1 = null;
      modelIntArray2 = null;
      modelIntArray3 = null;
      modelIntArray4 = null;
   }

   public static void method459(int i, OnDemandFetcherParent onDemandFetcherParent) {
      aClass21Array1661 = new Class21[i];
      aOnDemandFetcherParent_1662 = onDemandFetcherParent;
   }

   public static void method460(byte[] abyte0, int j) {
      if(abyte0 == null) {
         Class21 stream1 = aClass21Array1661[j] = new Class21();
         stream1.anInt369 = 0;
         stream1.anInt370 = 0;
         stream1.anInt371 = 0;
      } else {
         Stream stream = new Stream(abyte0);
         stream.currentOffset = abyte0.length - 18;
         Class21 class21_1 = aClass21Array1661[j] = new Class21();
         class21_1.aByteArray368 = abyte0;
         class21_1.anInt369 = stream.readUnsignedWord();
         class21_1.anInt370 = stream.readUnsignedWord();
         class21_1.anInt371 = stream.readUnsignedByte();
         int k = stream.readUnsignedByte();
         int l = stream.readUnsignedByte();
         int i1 = stream.readUnsignedByte();
         int j1 = stream.readUnsignedByte();
         int k1 = stream.readUnsignedByte();
         int l1 = stream.readUnsignedWord();
         int i2 = stream.readUnsignedWord();
         int j2 = stream.readUnsignedWord();
         int k2 = stream.readUnsignedWord();
         byte l2 = 0;
         class21_1.anInt372 = l2;
         int l21 = l2 + class21_1.anInt369;
         class21_1.anInt378 = l21;
         l21 += class21_1.anInt370;
         class21_1.anInt381 = l21;
         if(l == 255) {
            l21 += class21_1.anInt370;
         } else {
            class21_1.anInt381 = -l - 1;
         }

         class21_1.anInt383 = l21;
         if(j1 == 1) {
            l21 += class21_1.anInt370;
         } else {
            class21_1.anInt383 = -1;
         }

         class21_1.anInt380 = l21;
         if(k == 1) {
            l21 += class21_1.anInt370;
         } else {
            class21_1.anInt380 = -1;
         }

         class21_1.anInt376 = l21;
         if(k1 == 1) {
            l21 += class21_1.anInt369;
         } else {
            class21_1.anInt376 = -1;
         }

         class21_1.anInt382 = l21;
         if(i1 == 1) {
            l21 += class21_1.anInt370;
         } else {
            class21_1.anInt382 = -1;
         }

         class21_1.anInt377 = l21;
         l21 += k2;
         class21_1.anInt379 = l21;
         l21 += class21_1.anInt370 * 2;
         class21_1.anInt384 = l21;
         l21 += class21_1.anInt371 * 6;
         class21_1.anInt373 = l21;
         l21 += l1;
         class21_1.anInt374 = l21;
         l21 += i2;
         class21_1.anInt375 = l21;
         int var10000 = l21 + j2;
      }
   }

   public static void method461(int j) {
      aClass21Array1661[j] = null;
   }

   public static Model method462(int j) {
      if(aClass21Array1661 == null) {
         return null;
      } else {
         Class21 class21 = aClass21Array1661[j];
         if(class21 == null) {
            aOnDemandFetcherParent_1662.method548(j);
            return null;
         } else {
            return new Model(j);
         }
      }
   }

   public static boolean method463(int i) {
      if(aClass21Array1661 == null) {
         return false;
      } else {
         Class21 class21 = aClass21Array1661[i];
         if(class21 == null) {
            aOnDemandFetcherParent_1662.method548(i);
            return false;
         } else {
            return true;
         }
      }
   }

   private Model() {
      this.aBoolean1659 = false;
   }

   private Model(int i) {
      this.aBoolean1659 = false;
      Class21 class21 = aClass21Array1661[i];
      this.anInt1626 = class21.anInt369;
      this.anInt1630 = class21.anInt370;
      this.anInt1642 = class21.anInt371;
      this.anIntArray1627 = new int[this.anInt1626];
      this.anIntArray1628 = new int[this.anInt1626];
      this.anIntArray1629 = new int[this.anInt1626];
      this.anIntArray1631 = new int[this.anInt1630];
      this.anIntArray1632 = new int[this.anInt1630];
      this.anIntArray1633 = new int[this.anInt1630];
      this.anIntArray1643 = new int[this.anInt1642];
      this.anIntArray1644 = new int[this.anInt1642];
      this.anIntArray1645 = new int[this.anInt1642];
      if(class21.anInt376 >= 0) {
         this.anIntArray1655 = new int[this.anInt1626];
      }

      if(class21.anInt380 >= 0) {
         this.anIntArray1637 = new int[this.anInt1630];
      }

      if(class21.anInt381 >= 0) {
         this.anIntArray1638 = new int[this.anInt1630];
      } else {
         this.anInt1641 = -class21.anInt381 - 1;
      }

      if(class21.anInt382 >= 0) {
         this.anIntArray1639 = new int[this.anInt1630];
      }

      if(class21.anInt383 >= 0) {
         this.anIntArray1656 = new int[this.anInt1630];
      }

      this.anIntArray1640 = new int[this.anInt1630];
      Stream stream = new Stream(class21.aByteArray368);
      stream.currentOffset = class21.anInt372;
      Stream stream_1 = new Stream(class21.aByteArray368);
      stream_1.currentOffset = class21.anInt373;
      Stream stream_2 = new Stream(class21.aByteArray368);
      stream_2.currentOffset = class21.anInt374;
      Stream stream_3 = new Stream(class21.aByteArray368);
      stream_3.currentOffset = class21.anInt375;
      Stream stream_4 = new Stream(class21.aByteArray368);
      stream_4.currentOffset = class21.anInt376;
      int k = 0;
      int l = 0;
      int i1 = 0;

      int j2;
      int l2;
      int j3;
      int k3;
      int j4;
      for(j2 = 0; j2 < this.anInt1626; ++j2) {
         l2 = stream.readUnsignedByte();
         j3 = 0;
         if((l2 & 1) != 0) {
            j3 = stream_1.method421();
         }

         k3 = 0;
         if((l2 & 2) != 0) {
            k3 = stream_2.method421();
         }

         j4 = 0;
         if((l2 & 4) != 0) {
            j4 = stream_3.method421();
         }

         this.anIntArray1627[j2] = k + j3;
         this.anIntArray1628[j2] = l + k3;
         this.anIntArray1629[j2] = i1 + j4;
         k = this.anIntArray1627[j2];
         l = this.anIntArray1628[j2];
         i1 = this.anIntArray1629[j2];
         if(this.anIntArray1655 != null) {
            this.anIntArray1655[j2] = stream_4.readUnsignedByte();
         }
      }

      stream.currentOffset = class21.anInt379;
      stream_1.currentOffset = class21.anInt380;
      stream_2.currentOffset = class21.anInt381;
      stream_3.currentOffset = class21.anInt382;
      stream_4.currentOffset = class21.anInt383;
	  
      for(j2 = 0; j2 < this.anInt1630; ++j2) {
         this.anIntArray1640[j2] = stream.readUnsignedWord();
		 /*if(this.anIntArray1640[j2] == 8){
			this.anIntArray1640[j2] = 40;
		 }*/
         if(this.anIntArray1637 != null) {
            this.anIntArray1637[j2] = stream_1.readUnsignedByte();
         }

         if(this.anIntArray1638 != null) {
            this.anIntArray1638[j2] = stream_2.readUnsignedByte();
         }

         if(this.anIntArray1639 != null) {
            this.anIntArray1639[j2] = stream_3.readUnsignedByte();//alpha
         }

         if(this.anIntArray1656 != null) {
            this.anIntArray1656[j2] = stream_4.readUnsignedByte();
         }
      }
	  if(this.anIntArray1639 == null)
		this.anIntArray1639 = new int[this.anInt1630];

      stream.currentOffset = class21.anInt377;
      stream_1.currentOffset = class21.anInt378;
      j2 = 0;
      l2 = 0;
      j3 = 0;
      k3 = 0;

      for(j4 = 0; j4 < this.anInt1630; ++j4) {
         int i4 = stream_1.readUnsignedByte();
         if(i4 == 1) {
            j2 = stream.method421() + k3;
            l2 = stream.method421() + j2;
            j3 = stream.method421() + l2;
            k3 = j3;
            this.anIntArray1631[j4] = j2;
            this.anIntArray1632[j4] = l2;
            this.anIntArray1633[j4] = j3;
         }

         if(i4 == 2) {
            l2 = j3;
            j3 = stream.method421() + k3;
            k3 = j3;
            this.anIntArray1631[j4] = j2;
            this.anIntArray1632[j4] = l2;
            this.anIntArray1633[j4] = j3;
         }

         if(i4 == 3) {
            j2 = j3;
            j3 = stream.method421() + k3;
            k3 = j3;
            this.anIntArray1631[j4] = j2;
            this.anIntArray1632[j4] = l2;
            this.anIntArray1633[j4] = j3;
         }

         if(i4 == 4) {
            int k4 = j2;
            j2 = l2;
            l2 = k4;
            j3 = stream.method421() + k3;
            k3 = j3;
            this.anIntArray1631[j4] = j2;
            this.anIntArray1632[j4] = k4;
            this.anIntArray1633[j4] = j3;
         }
      }

      stream.currentOffset = class21.anInt384;

      for(j4 = 0; j4 < this.anInt1642; ++j4) {
         this.anIntArray1643[j4] = stream.readUnsignedWord();
         this.anIntArray1644[j4] = stream.readUnsignedWord();
         this.anIntArray1645[j4] = stream.readUnsignedWord();
      }

   }

   public Model(int i, Model[] aclass30_sub2_sub4_sub6s) {
      this.aBoolean1659 = false;
      boolean flag = false;
      boolean flag1 = false;
      boolean flag2 = false;
      boolean flag3 = false;
      this.anInt1626 = 0;
      this.anInt1630 = 0;
      this.anInt1642 = 0;
      this.anInt1641 = -1;

      int l;
      for(l = 0; l < i; ++l) {
         Model i1 = aclass30_sub2_sub4_sub6s[l];
         if(i1 != null) {
            this.anInt1626 += i1.anInt1626;
            this.anInt1630 += i1.anInt1630;
            this.anInt1642 += i1.anInt1642;
            flag |= i1.anIntArray1637 != null;
            if(i1.anIntArray1638 != null) {
               flag1 = true;
            } else {
               if(this.anInt1641 == -1) {
                  this.anInt1641 = i1.anInt1641;
               }

               if(this.anInt1641 != i1.anInt1641) {
                  flag1 = true;
               }
            }

            flag2 |= i1.anIntArray1639 != null;
            flag3 |= i1.anIntArray1656 != null;
         }
      }

      this.anIntArray1627 = new int[this.anInt1626];
      this.anIntArray1628 = new int[this.anInt1626];
      this.anIntArray1629 = new int[this.anInt1626];
      this.anIntArray1655 = new int[this.anInt1626];
      this.anIntArray1631 = new int[this.anInt1630];
      this.anIntArray1632 = new int[this.anInt1630];
      this.anIntArray1633 = new int[this.anInt1630];
      this.anIntArray1643 = new int[this.anInt1642];
      this.anIntArray1644 = new int[this.anInt1642];
      this.anIntArray1645 = new int[this.anInt1642];
      if(flag) {
         this.anIntArray1637 = new int[this.anInt1630];
      }

      if(flag1) {
         this.anIntArray1638 = new int[this.anInt1630];
      }

      if(flag2) {
         this.anIntArray1639 = new int[this.anInt1630];
      }

      if(flag3) {
         this.anIntArray1656 = new int[this.anInt1630];
      }

      this.anIntArray1640 = new int[this.anInt1630];
      this.anInt1626 = 0;
      this.anInt1630 = 0;
      this.anInt1642 = 0;
      l = 0;

      for(int var12 = 0; var12 < i; ++var12) {
         Model model_1 = aclass30_sub2_sub4_sub6s[var12];
         if(model_1 != null) {
            int l1;
            for(l1 = 0; l1 < model_1.anInt1630; ++l1) {
               if(flag) {
                  if(model_1.anIntArray1637 == null) {
                     this.anIntArray1637[this.anInt1630] = 0;
                  } else {
                     int k1 = model_1.anIntArray1637[l1];
                     if((k1 & 2) == 2) {
                        k1 += l << 2;
                     }

                     this.anIntArray1637[this.anInt1630] = k1;
                  }
               }

               if(flag1) {
                  if(model_1.anIntArray1638 == null) {
                     this.anIntArray1638[this.anInt1630] = model_1.anInt1641;
                  } else {
                     this.anIntArray1638[this.anInt1630] = model_1.anIntArray1638[l1];
                  }
               }

               if(flag2) {
                  if(model_1.anIntArray1639 == null) {
                     this.anIntArray1639[this.anInt1630] = 0;
                  } else {
                     this.anIntArray1639[this.anInt1630] = model_1.anIntArray1639[l1];
                  }
               }

               if(flag3 && model_1.anIntArray1656 != null) {
                  this.anIntArray1656[this.anInt1630] = model_1.anIntArray1656[l1];
               }

               this.anIntArray1640[this.anInt1630] = model_1.anIntArray1640[l1];
               this.anIntArray1631[this.anInt1630] = this.method465(model_1, model_1.anIntArray1631[l1]);
               this.anIntArray1632[this.anInt1630] = this.method465(model_1, model_1.anIntArray1632[l1]);
               this.anIntArray1633[this.anInt1630] = this.method465(model_1, model_1.anIntArray1633[l1]);
               ++this.anInt1630;
            }

            for(l1 = 0; l1 < model_1.anInt1642; ++l1) {
               this.anIntArray1643[this.anInt1642] = this.method465(model_1, model_1.anIntArray1643[l1]);
               this.anIntArray1644[this.anInt1642] = this.method465(model_1, model_1.anIntArray1644[l1]);
               this.anIntArray1645[this.anInt1642] = this.method465(model_1, model_1.anIntArray1645[l1]);
               ++this.anInt1642;
            }

            l += model_1.anInt1642;
         }
      }

   }

   public Model(Model[] aclass30_sub2_sub4_sub6s) {
      byte i = 2;
      this.aBoolean1659 = false;
      boolean flag1 = false;
      boolean flag2 = false;
      boolean flag3 = false;
      boolean flag4 = false;
      this.anInt1626 = 0;
      this.anInt1630 = 0;
      this.anInt1642 = 0;
      this.anInt1641 = -1;

      int i1;
      for(i1 = 0; i1 < i; ++i1) {
         Model j1 = aclass30_sub2_sub4_sub6s[i1];
         if(j1 != null) {
            this.anInt1626 += j1.anInt1626;
            this.anInt1630 += j1.anInt1630;
            this.anInt1642 += j1.anInt1642;
            flag1 |= j1.anIntArray1637 != null;
            if(j1.anIntArray1638 != null) {
               flag2 = true;
            } else {
               if(this.anInt1641 == -1) {
                  this.anInt1641 = j1.anInt1641;
               }

               if(this.anInt1641 != j1.anInt1641) {
                  flag2 = true;
               }
            }

            flag3 |= j1.anIntArray1639 != null;
            flag4 |= j1.anIntArray1640 != null;
         }
      }

      this.anIntArray1627 = new int[this.anInt1626];
      this.anIntArray1628 = new int[this.anInt1626];
      this.anIntArray1629 = new int[this.anInt1626];
      this.anIntArray1631 = new int[this.anInt1630];
      this.anIntArray1632 = new int[this.anInt1630];
      this.anIntArray1633 = new int[this.anInt1630];
      this.anIntArray1634 = new int[this.anInt1630];
      this.anIntArray1635 = new int[this.anInt1630];
      this.anIntArray1636 = new int[this.anInt1630];
      this.anIntArray1643 = new int[this.anInt1642];
      this.anIntArray1644 = new int[this.anInt1642];
      this.anIntArray1645 = new int[this.anInt1642];
      if(flag1) {
         this.anIntArray1637 = new int[this.anInt1630];
      }

      if(flag2) {
         this.anIntArray1638 = new int[this.anInt1630];
      }

      if(flag3) {
         this.anIntArray1639 = new int[this.anInt1630];
      }

      if(flag4) {
         this.anIntArray1640 = new int[this.anInt1630];
      }

      this.anInt1626 = 0;
      this.anInt1630 = 0;
      this.anInt1642 = 0;
      i1 = 0;

      for(int var13 = 0; var13 < i; ++var13) {
         Model model_1 = aclass30_sub2_sub4_sub6s[var13];
         if(model_1 != null) {
            int k1 = this.anInt1626;

            int k2;
            for(k2 = 0; k2 < model_1.anInt1626; ++k2) {
               this.anIntArray1627[this.anInt1626] = model_1.anIntArray1627[k2];
               this.anIntArray1628[this.anInt1626] = model_1.anIntArray1628[k2];
               this.anIntArray1629[this.anInt1626] = model_1.anIntArray1629[k2];
               ++this.anInt1626;
            }

            for(k2 = 0; k2 < model_1.anInt1630; ++k2) {
               this.anIntArray1631[this.anInt1630] = model_1.anIntArray1631[k2] + k1;
               this.anIntArray1632[this.anInt1630] = model_1.anIntArray1632[k2] + k1;
               this.anIntArray1633[this.anInt1630] = model_1.anIntArray1633[k2] + k1;
               this.anIntArray1634[this.anInt1630] = model_1.anIntArray1634[k2];
               this.anIntArray1635[this.anInt1630] = model_1.anIntArray1635[k2];
               this.anIntArray1636[this.anInt1630] = model_1.anIntArray1636[k2];
               if(flag1) {
                  if(model_1.anIntArray1637 == null) {
                     this.anIntArray1637[this.anInt1630] = 0;
                  } else {
                     int j2 = model_1.anIntArray1637[k2];
                     if((j2 & 2) == 2) {
                        j2 += i1 << 2;
                     }

                     this.anIntArray1637[this.anInt1630] = j2;
                  }
               }

               if(flag2) {
                  if(model_1.anIntArray1638 == null) {
                     this.anIntArray1638[this.anInt1630] = model_1.anInt1641;
                  } else {
                     this.anIntArray1638[this.anInt1630] = model_1.anIntArray1638[k2];
                  }
               }

               if(flag3) {
                  if(model_1.anIntArray1639 == null) {
                     this.anIntArray1639[this.anInt1630] = 0;
                  } else {
                     this.anIntArray1639[this.anInt1630] = model_1.anIntArray1639[k2];
                  }
               }

               if(flag4 && model_1.anIntArray1640 != null) {
                  this.anIntArray1640[this.anInt1630] = model_1.anIntArray1640[k2];
               }

               ++this.anInt1630;
            }

            for(k2 = 0; k2 < model_1.anInt1642; ++k2) {
               this.anIntArray1643[this.anInt1642] = model_1.anIntArray1643[k2] + k1;
               this.anIntArray1644[this.anInt1642] = model_1.anIntArray1644[k2] + k1;
               this.anIntArray1645[this.anInt1642] = model_1.anIntArray1645[k2] + k1;
               ++this.anInt1642;
            }

            i1 += model_1.anInt1642;
         }
      }

      this.method466();
   }

   public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
      this.aBoolean1659 = false;
      this.anInt1626 = model.anInt1626;
      this.anInt1630 = model.anInt1630;
      this.anInt1642 = model.anInt1642;
      int l;
      if(flag2) {
         this.anIntArray1627 = model.anIntArray1627;
         this.anIntArray1628 = model.anIntArray1628;
         this.anIntArray1629 = model.anIntArray1629;
      } else {
         this.anIntArray1627 = new int[this.anInt1626];
         this.anIntArray1628 = new int[this.anInt1626];
         this.anIntArray1629 = new int[this.anInt1626];

         for(l = 0; l < this.anInt1626; ++l) {
            this.anIntArray1627[l] = model.anIntArray1627[l];
            this.anIntArray1628[l] = model.anIntArray1628[l];
            this.anIntArray1629[l] = model.anIntArray1629[l];
         }
      }

      if(flag) {
         this.anIntArray1640 = model.anIntArray1640;
      } else {
         this.anIntArray1640 = new int[this.anInt1630];
         System.arraycopy(model.anIntArray1640, 0, this.anIntArray1640, 0, this.anInt1630);
      }

      if(flag1) {
         this.anIntArray1639 = model.anIntArray1639;
      } else {
         this.anIntArray1639 = new int[this.anInt1630];
         if(model.anIntArray1639 == null) {
            for(l = 0; l < this.anInt1630; ++l) {
               this.anIntArray1639[l] = 0;
            }
         } else {
            System.arraycopy(model.anIntArray1639, 0, this.anIntArray1639, 0, this.anInt1630);
         }
      }

      this.anIntArray1655 = model.anIntArray1655;
      this.anIntArray1656 = model.anIntArray1656;
      this.anIntArray1637 = model.anIntArray1637;
      this.anIntArray1631 = model.anIntArray1631;
      this.anIntArray1632 = model.anIntArray1632;
      this.anIntArray1633 = model.anIntArray1633;
      this.anIntArray1638 = model.anIntArray1638;
      this.anInt1641 = model.anInt1641;
      this.anIntArray1643 = model.anIntArray1643;
      this.anIntArray1644 = model.anIntArray1644;
      this.anIntArray1645 = model.anIntArray1645;
   }

   public Model(boolean flag, boolean flag1, Model model) {
      this.aBoolean1659 = false;
      this.anInt1626 = model.anInt1626;
      this.anInt1630 = model.anInt1630;
      this.anInt1642 = model.anInt1642;
      if(flag) {
         this.anIntArray1628 = new int[this.anInt1626];
         System.arraycopy(model.anIntArray1628, 0, this.anIntArray1628, 0, this.anInt1626);
      } else {
         this.anIntArray1628 = model.anIntArray1628;
      }

      if(flag1) {
         this.anIntArray1634 = new int[this.anInt1630];
         this.anIntArray1635 = new int[this.anInt1630];
         this.anIntArray1636 = new int[this.anInt1630];

         int j1;
         for(j1 = 0; j1 < this.anInt1630; ++j1) {
            this.anIntArray1634[j1] = model.anIntArray1634[j1];
            this.anIntArray1635[j1] = model.anIntArray1635[j1];
            this.anIntArray1636[j1] = model.anIntArray1636[j1];
         }

         this.anIntArray1637 = new int[this.anInt1630];
         if(model.anIntArray1637 == null) {
            for(j1 = 0; j1 < this.anInt1630; ++j1) {
               this.anIntArray1637[j1] = 0;
            }
         } else {
            System.arraycopy(model.anIntArray1637, 0, this.anIntArray1637, 0, this.anInt1630);
         }

         super.aClass33Array1425 = new Class33[this.anInt1626];

         for(j1 = 0; j1 < this.anInt1626; ++j1) {
            Class33 class33 = super.aClass33Array1425[j1] = new Class33();
            Class33 class33_1 = model.aClass33Array1425[j1];
            class33.anInt602 = class33_1.anInt602;
            class33.anInt603 = class33_1.anInt603;
            class33.anInt604 = class33_1.anInt604;
            class33.anInt605 = class33_1.anInt605;
         }

         this.aClass33Array1660 = model.aClass33Array1660;
      } else {
         this.anIntArray1634 = model.anIntArray1634;
         this.anIntArray1635 = model.anIntArray1635;
         this.anIntArray1636 = model.anIntArray1636;
         this.anIntArray1637 = model.anIntArray1637;
      }

      this.anIntArray1627 = model.anIntArray1627;
      this.anIntArray1629 = model.anIntArray1629;
      this.anIntArray1640 = model.anIntArray1640;
      this.anIntArray1639 = model.anIntArray1639;
      this.anIntArray1638 = model.anIntArray1638;
      this.anInt1641 = model.anInt1641;
      this.anIntArray1631 = model.anIntArray1631;
      this.anIntArray1632 = model.anIntArray1632;
      this.anIntArray1633 = model.anIntArray1633;
      this.anIntArray1643 = model.anIntArray1643;
      this.anIntArray1644 = model.anIntArray1644;
      this.anIntArray1645 = model.anIntArray1645;
      super.modelHeight = model.modelHeight;
      this.anInt1651 = model.anInt1651;
      this.anInt1650 = model.anInt1650;
      this.anInt1653 = model.anInt1653;
      this.anInt1652 = model.anInt1652;
      this.anInt1646 = model.anInt1646;
      this.anInt1648 = model.anInt1648;
      this.anInt1649 = model.anInt1649;
      this.anInt1647 = model.anInt1647;
   }

   public void method464(Model model, boolean flag) {
      this.anInt1626 = model.anInt1626;
      this.anInt1630 = model.anInt1630;
      this.anInt1642 = model.anInt1642;
      if(anIntArray1622.length < this.anInt1626) {
         anIntArray1622 = new int[this.anInt1626 + 100];
         anIntArray1623 = new int[this.anInt1626 + 100];
         anIntArray1624 = new int[this.anInt1626 + 100];
      }

      this.anIntArray1627 = anIntArray1622;
      this.anIntArray1628 = anIntArray1623;
      this.anIntArray1629 = anIntArray1624;

      int l;
      for(l = 0; l < this.anInt1626; ++l) {
         this.anIntArray1627[l] = model.anIntArray1627[l];
         this.anIntArray1628[l] = model.anIntArray1628[l];
         this.anIntArray1629[l] = model.anIntArray1629[l];
      }

      if(flag) {
         this.anIntArray1639 = model.anIntArray1639;
      } else {
         if(anIntArray1625.length < this.anInt1630) {
            anIntArray1625 = new int[this.anInt1630 + 100];
         }

         this.anIntArray1639 = anIntArray1625;
         if(model.anIntArray1639 == null) {
            for(l = 0; l < this.anInt1630; ++l) {
               this.anIntArray1639[l] = 0;
            }
         } else {
            System.arraycopy(model.anIntArray1639, 0, this.anIntArray1639, 0, this.anInt1630);
         }
      }

      this.anIntArray1637 = model.anIntArray1637;
      this.anIntArray1640 = model.anIntArray1640;
      this.anIntArray1638 = model.anIntArray1638;
      this.anInt1641 = model.anInt1641;
      this.anIntArrayArray1658 = model.anIntArrayArray1658;
      this.anIntArrayArray1657 = model.anIntArrayArray1657;
      this.anIntArray1631 = model.anIntArray1631;
      this.anIntArray1632 = model.anIntArray1632;
      this.anIntArray1633 = model.anIntArray1633;
      this.anIntArray1634 = model.anIntArray1634;
      this.anIntArray1635 = model.anIntArray1635;
      this.anIntArray1636 = model.anIntArray1636;
      this.anIntArray1643 = model.anIntArray1643;
      this.anIntArray1644 = model.anIntArray1644;
      this.anIntArray1645 = model.anIntArray1645;
   }

   private int method465(Model model, int i) {
      int j = -1;
      int k = model.anIntArray1627[i];
      int l = model.anIntArray1628[i];
      int i1 = model.anIntArray1629[i];

      for(int j1 = 0; j1 < this.anInt1626; ++j1) {
         if(k == this.anIntArray1627[j1] && l == this.anIntArray1628[j1] && i1 == this.anIntArray1629[j1]) {
            j = j1;
            break;
         }
      }

      if(j == -1) {
         this.anIntArray1627[this.anInt1626] = k;
         this.anIntArray1628[this.anInt1626] = l;
         this.anIntArray1629[this.anInt1626] = i1;
         if(model.anIntArray1655 != null) {
            this.anIntArray1655[this.anInt1626] = model.anIntArray1655[i];
         }

         j = this.anInt1626++;
      }

      return j;
   }

   public void method466() {
      super.modelHeight = 0;
      this.anInt1650 = 0;
      this.anInt1651 = 0;

      for(int i = 0; i < this.anInt1626; ++i) {
         int j = this.anIntArray1627[i];
         int k = this.anIntArray1628[i];
         int l = this.anIntArray1629[i];
         if(-k > super.modelHeight) {
            super.modelHeight = -k;
         }

         if(k > this.anInt1651) {
            this.anInt1651 = k;
         }

         int i1 = j * j + l * l;
         if(i1 > this.anInt1650) {
            this.anInt1650 = i1;
         }
      }

      this.anInt1650 = (int)(Math.sqrt((double)this.anInt1650) + 0.99D);
      this.anInt1653 = (int)(Math.sqrt((double)(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight)) + 0.99D);
      this.anInt1652 = this.anInt1653 + (int)(Math.sqrt((double)(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651)) + 0.99D);
   }

   public void method467() {
      super.modelHeight = 0;
      this.anInt1651 = 0;

      for(int i = 0; i < this.anInt1626; ++i) {
         int j = this.anIntArray1628[i];
         if(-j > super.modelHeight) {
            super.modelHeight = -j;
         }

         if(j > this.anInt1651) {
            this.anInt1651 = j;
         }
      }

      this.anInt1653 = (int)(Math.sqrt((double)(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight)) + 0.99D);
      this.anInt1652 = this.anInt1653 + (int)(Math.sqrt((double)(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651)) + 0.99D);
   }

   private void method468() {
      super.modelHeight = 0;
      this.anInt1650 = 0;
      this.anInt1651 = 0;
      this.anInt1646 = 999999;
      this.anInt1647 = -999999;
      this.anInt1648 = -99999;
      this.anInt1649 = 99999;

      for(int j = 0; j < this.anInt1626; ++j) {
         int k = this.anIntArray1627[j];
         int l = this.anIntArray1628[j];
         int i1 = this.anIntArray1629[j];
         if(k < this.anInt1646) {
            this.anInt1646 = k;
         }

         if(k > this.anInt1647) {
            this.anInt1647 = k;
         }

         if(i1 < this.anInt1649) {
            this.anInt1649 = i1;
         }

         if(i1 > this.anInt1648) {
            this.anInt1648 = i1;
         }

         if(-l > super.modelHeight) {
            super.modelHeight = -l;
         }

         if(l > this.anInt1651) {
            this.anInt1651 = l;
         }

         int j1 = k * k + i1 * i1;
         if(j1 > this.anInt1650) {
            this.anInt1650 = j1;
         }
      }

      this.anInt1650 = (int)Math.sqrt((double)this.anInt1650);
      this.anInt1653 = (int)Math.sqrt((double)(this.anInt1650 * this.anInt1650 + super.modelHeight * super.modelHeight));
      this.anInt1652 = this.anInt1653 + (int)Math.sqrt((double)(this.anInt1650 * this.anInt1650 + this.anInt1651 * this.anInt1651));
   }

   public void method469() {
      int[] ai1;
      int k;
      int k2;
      int i3;
      if(this.anIntArray1655 != null) {
         ai1 = new int[256];
         k = 0;

         for(k2 = 0; k2 < this.anInt1626; ++k2) {
            i3 = this.anIntArray1655[k2];
            ++ai1[i3];
            if(i3 > k) {
               k = i3;
            }
         }

         this.anIntArrayArray1657 = new int[k + 1][];

         for(k2 = 0; k2 <= k; ++k2) {
            this.anIntArrayArray1657[k2] = new int[ai1[k2]];
            ai1[k2] = 0;
         }

         for(k2 = 0; k2 < this.anInt1626; this.anIntArrayArray1657[i3][ai1[i3]++] = k2++) {
            i3 = this.anIntArray1655[k2];
         }

         this.anIntArray1655 = null;
      }

      if(this.anIntArray1656 != null) {
         ai1 = new int[256];
         k = 0;

         for(k2 = 0; k2 < this.anInt1630; ++k2) {
            i3 = this.anIntArray1656[k2];
            ++ai1[i3];
            if(i3 > k) {
               k = i3;
            }
         }

         this.anIntArrayArray1658 = new int[k + 1][];

         for(k2 = 0; k2 <= k; ++k2) {
            this.anIntArrayArray1658[k2] = new int[ai1[k2]];
            ai1[k2] = 0;
         }

         for(k2 = 0; k2 < this.anInt1630; this.anIntArrayArray1658[i3][ai1[i3]++] = k2++) {
            i3 = this.anIntArray1656[k2];
         }

         this.anIntArray1656 = null;
      }

   }

   public void method470(int i) {
      if(this.anIntArrayArray1657 != null) {
         if(i != -1) {
            Class36 class36 = Class36.method531(i);
            if(class36 != null) {
               Class18 class18 = class36.aClass18_637;
               anInt1681 = 0;
               anInt1682 = 0;
               anInt1683 = 0;

               for(int k = 0; k < class36.anInt638; ++k) {
                  int l = class36.anIntArray639[k];
                  this.method472(class18.anIntArray342[l], class18.anIntArrayArray343[l], class36.anIntArray640[k], class36.anIntArray641[k], class36.anIntArray642[k]);
               }

            }
         }
      }
   }

   public void method471(int[] ai, int j, int k) {
      if(k != -1) {
         if(ai != null && j != -1) {
            Class36 class36 = Class36.method531(k);
            if(class36 != null) {
               Class36 class36_1 = Class36.method531(j);
               if(class36_1 == null) {
                  this.method470(k);
               } else {
                  Class18 class18 = class36.aClass18_637;
                  anInt1681 = 0;
                  anInt1682 = 0;
                  anInt1683 = 0;
                  byte l = 0;
                  int var12 = l + 1;
                  int i1 = ai[l];

                  int l1;
                  int i2;
                  for(l1 = 0; l1 < class36.anInt638; ++l1) {
                     for(i2 = class36.anIntArray639[l1]; i2 > i1; i1 = ai[var12++]) {
                        ;
                     }

                     if(i2 != i1 || class18.anIntArray342[i2] == 0) {
                        this.method472(class18.anIntArray342[i2], class18.anIntArrayArray343[i2], class36.anIntArray640[l1], class36.anIntArray641[l1], class36.anIntArray642[l1]);
                     }
                  }

                  anInt1681 = 0;
                  anInt1682 = 0;
                  anInt1683 = 0;
                  l = 0;
                  var12 = l + 1;
                  i1 = ai[l];

                  for(l1 = 0; l1 < class36_1.anInt638; ++l1) {
                     for(i2 = class36_1.anIntArray639[l1]; i2 > i1; i1 = ai[var12++]) {
                        ;
                     }

                     if(i2 == i1 || class18.anIntArray342[i2] == 0) {
                        this.method472(class18.anIntArray342[i2], class18.anIntArrayArray343[i2], class36_1.anIntArray640[l1], class36_1.anIntArray641[l1], class36_1.anIntArray642[l1]);
                     }
                  }

               }
            }
         } else {
            this.method470(k);
         }
      }
   }

   private void method472(int i, int[] ai, int j, int k, int l) {
      int i1 = ai.length;
      int j2;
      int k3;
      int[] arr$;
      int i$;
      int element;
      if(i == 0) {
         j2 = 0;
         anInt1681 = 0;
         anInt1682 = 0;
         anInt1683 = 0;

         for(k3 = 0; k3 < i1; ++k3) {
            int var21 = ai[k3];
            if(var21 < this.anIntArrayArray1657.length) {
               arr$ = this.anIntArrayArray1657[var21];
               int[] var22 = arr$;
               i$ = arr$.length;

               for(element = 0; element < i$; ++element) {
                  int i6 = var22[element];
                  anInt1681 += this.anIntArray1627[i6];
                  anInt1682 += this.anIntArray1628[i6];
                  anInt1683 += this.anIntArray1629[i6];
                  ++j2;
               }
            }
         }

         if(j2 > 0) {
            anInt1681 = anInt1681 / j2 + j;
            anInt1682 = anInt1682 / j2 + k;
            anInt1683 = anInt1683 / j2 + l;
         } else {
            anInt1681 = j;
            anInt1682 = k;
            anInt1683 = l;
         }
      } else {
         int[] ai4;
         int len$;
         if(i == 1) {
            for(j2 = 0; j2 < i1; ++j2) {
               k3 = ai[j2];
               if(k3 < this.anIntArrayArray1657.length) {
                  ai4 = this.anIntArrayArray1657[k3];
                  arr$ = ai4;
                  len$ = ai4.length;

                  for(i$ = 0; i$ < len$; ++i$) {
                     element = arr$[i$];
                     this.anIntArray1627[element] += j;
                     this.anIntArray1628[element] += k;
                     this.anIntArray1629[element] += l;
                  }
               }
            }

         } else if(i == 2) {
            for(j2 = 0; j2 < i1; ++j2) {
               k3 = ai[j2];
               if(k3 < this.anIntArrayArray1657.length) {
                  ai4 = this.anIntArrayArray1657[k3];
                  arr$ = ai4;
                  len$ = ai4.length;

                  for(i$ = 0; i$ < len$; ++i$) {
                     element = arr$[i$];
                     this.anIntArray1627[element] -= anInt1681;
                     this.anIntArray1628[element] -= anInt1682;
                     this.anIntArray1629[element] -= anInt1683;
                     int k6 = (j & 255) * 8;
                     int l6 = (k & 255) * 8;
                     int i7 = (l & 255) * 8;
                     int k8;
                     int l7;
                     int j9;
                     if(i7 != 0) {
                        l7 = modelIntArray1[i7];
                        k8 = modelIntArray2[i7];
                        j9 = this.anIntArray1628[element] * l7 + this.anIntArray1627[element] * k8 >> 16;
                        this.anIntArray1628[element] = this.anIntArray1628[element] * k8 - this.anIntArray1627[element] * l7 >> 16;
                        this.anIntArray1627[element] = j9;
                     }

                     if(k6 != 0) {
                        l7 = modelIntArray1[k6];
                        k8 = modelIntArray2[k6];
                        j9 = this.anIntArray1628[element] * k8 - this.anIntArray1629[element] * l7 >> 16;
                        this.anIntArray1629[element] = this.anIntArray1628[element] * l7 + this.anIntArray1629[element] * k8 >> 16;
                        this.anIntArray1628[element] = j9;
                     }

                     if(l6 != 0) {
                        l7 = modelIntArray1[l6];
                        k8 = modelIntArray2[l6];
                        j9 = this.anIntArray1629[element] * l7 + this.anIntArray1627[element] * k8 >> 16;
                        this.anIntArray1629[element] = this.anIntArray1629[element] * k8 - this.anIntArray1627[element] * l7 >> 16;
                        this.anIntArray1627[element] = j9;
                     }

                     this.anIntArray1627[element] += anInt1681;
                     this.anIntArray1628[element] += anInt1682;
                     this.anIntArray1629[element] += anInt1683;
                  }
               }
            }

         } else if(i == 3) {
            for(j2 = 0; j2 < i1; ++j2) {
               k3 = ai[j2];
               if(k3 < this.anIntArrayArray1657.length) {
                  ai4 = this.anIntArrayArray1657[k3];
                  arr$ = ai4;
                  len$ = ai4.length;

                  for(i$ = 0; i$ < len$; ++i$) {
                     element = arr$[i$];
                     this.anIntArray1627[element] -= anInt1681;
                     this.anIntArray1628[element] -= anInt1682;
                     this.anIntArray1629[element] -= anInt1683;
                     this.anIntArray1627[element] = this.anIntArray1627[element] * j / 128;
                     this.anIntArray1628[element] = this.anIntArray1628[element] * k / 128;
                     this.anIntArray1629[element] = this.anIntArray1629[element] * l / 128;
                     this.anIntArray1627[element] += anInt1681;
                     this.anIntArray1628[element] += anInt1682;
                     this.anIntArray1629[element] += anInt1683;
                  }
               }
            }

         } else {
            if(i == 5 && this.anIntArrayArray1658 != null && this.anIntArray1639 != null) {
               for(j2 = 0; j2 < i1; ++j2) {
                  k3 = ai[j2];
                  if(k3 < this.anIntArrayArray1658.length) {
                     ai4 = this.anIntArrayArray1658[k3];
                     arr$ = ai4;
                     len$ = ai4.length;

                     for(i$ = 0; i$ < len$; ++i$) {
                        element = arr$[i$];
                        this.anIntArray1639[element] += j * 8;
                        if(this.anIntArray1639[element] < 0) {
                           this.anIntArray1639[element] = 0;
                        }

                        if(this.anIntArray1639[element] > 255) {
                           this.anIntArray1639[element] = 255;
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public void method473() {
      for(int j = 0; j < this.anInt1626; ++j) {
         int k = this.anIntArray1627[j];
         this.anIntArray1627[j] = this.anIntArray1629[j];
         this.anIntArray1629[j] = -k;
      }

   }

   public void method474(int i) {
      int k = modelIntArray1[i];
      int l = modelIntArray2[i];

      for(int i1 = 0; i1 < this.anInt1626; ++i1) {
         int j1 = this.anIntArray1628[i1] * l - this.anIntArray1629[i1] * k >> 16;
         this.anIntArray1629[i1] = this.anIntArray1628[i1] * k + this.anIntArray1629[i1] * l >> 16;
         this.anIntArray1628[i1] = j1;
      }

   }

   public void method475(int i, int j, int l) {
      for(int i1 = 0; i1 < this.anInt1626; ++i1) {
         this.anIntArray1627[i1] += i;
         this.anIntArray1628[i1] += j;
         this.anIntArray1629[i1] += l;
      }

   }
   
   private boolean isNewValue(int values[], int value, int length){
	for(int i = 0; i < length; i++){
		if(values[i] == value)
		return false;
	}
	return true;
   }
   
   public void getModelValues(){
	int colors[] = new int[this.anInt1630];
	int alphas[] = new int[this.anInt1630];
	int crun = 0;
	int crun2 = 0;
	for(int k = 0; k < this.anInt1630; ++k) {
		 if(k == 0){
			crun = 0;
			crun2 = 0;
		 }
		int color = this.anIntArray1640[k];
		if(isNewValue(colors, color, crun)) {
			colors[crun] = color;
			crun++;
		}
		int alpha = this.anIntArray1639[k];
		if(isNewValue(alphas, alpha, crun2)) {
			alphas[crun2] = alpha;
			crun2++;
		}
    }
	System.out.println("");
	System.out.println("Colors:");
	for(int k = 0; k < crun; ++k) {
		System.out.println(colors[k]);
	}
	System.out.println("");
	System.out.println("Alphas:");
	for(int k = 0; k < crun2; ++k) {
		System.out.println(alphas[k]);
	}
	System.out.println("");
   }

   public void setModelAlpha(int alpha) {
      for(int k = 0; k < this.anInt1630; ++k) {
		 this.anIntArray1639[k] = alpha;
      }

   }
   
   public void setAlphaToColor(int i, int j) {
      for(int k = 0; k < this.anInt1630; ++k) {
         if(this.anIntArray1640[k] == i) {
            this.anIntArray1639[k] = j;
         }
      }

   }
   
   public void method476(int i, int j) {
      for(int k = 0; k < this.anInt1630; ++k) {
         if(this.anIntArray1640[k] == i) {
            this.anIntArray1640[k] = j;
         }
      }

   }

   public void method477() {
      int k;
      for(k = 0; k < this.anInt1626; ++k) {
         this.anIntArray1629[k] = -this.anIntArray1629[k];
      }

      for(k = 0; k < this.anInt1630; ++k) {
         int l = this.anIntArray1631[k];
         this.anIntArray1631[k] = this.anIntArray1633[k];
         this.anIntArray1633[k] = l;
      }

   }

   public void method478(int i, int j, int l) {
      for(int i1 = 0; i1 < this.anInt1626; ++i1) {
         this.anIntArray1627[i1] = this.anIntArray1627[i1] * i / 128;
         this.anIntArray1628[i1] = this.anIntArray1628[i1] * l / 128;
         this.anIntArray1629[i1] = this.anIntArray1629[i1] * j / 128;
      }

   }

   public void method479(int i, int j, int k, int l, int i1, boolean flag) {
      int j1 = (int)Math.sqrt((double)(k * k + l * l + i1 * i1));
      int k1 = j * j1 >> 8;
      if(this.anIntArray1634 == null) {
         this.anIntArray1634 = new int[this.anInt1630];
         this.anIntArray1635 = new int[this.anInt1630];
         this.anIntArray1636 = new int[this.anInt1630];
      }

      int k2;
      if(super.aClass33Array1425 == null) {
         super.aClass33Array1425 = new Class33[this.anInt1626];

         for(k2 = 0; k2 < this.anInt1626; ++k2) {
            super.aClass33Array1425[k2] = new Class33();
         }
      }

      for(k2 = 0; k2 < this.anInt1630; ++k2) {
         int class33 = this.anIntArray1631[k2];
         int class33_1 = this.anIntArray1632[k2];
         int i3 = this.anIntArray1633[k2];
         int j3 = this.anIntArray1627[class33_1] - this.anIntArray1627[class33];
         int k3 = this.anIntArray1628[class33_1] - this.anIntArray1628[class33];
         int l3 = this.anIntArray1629[class33_1] - this.anIntArray1629[class33];
         int i4 = this.anIntArray1627[i3] - this.anIntArray1627[class33];
         int j4 = this.anIntArray1628[i3] - this.anIntArray1628[class33];
         int k4 = this.anIntArray1629[i3] - this.anIntArray1629[class33];
         int l4 = k3 * k4 - j4 * l3;
         int i5 = l3 * i4 - k4 * j3;

         int j5;
         for(j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192 || j5 < -8192; j5 >>= 1) {
            l4 >>= 1;
            i5 >>= 1;
         }

         int k5 = (int)Math.sqrt((double)(l4 * l4 + i5 * i5 + j5 * j5));
         if(k5 <= 0) {
            k5 = 1;
         }

         l4 = l4 * 256 / k5;
         i5 = i5 * 256 / k5;
         j5 = j5 * 256 / k5;
         if(this.anIntArray1637 != null && (this.anIntArray1637[k2] & 1) != 0) {
            int var26 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
            this.anIntArray1634[k2] = method481(this.anIntArray1640[k2], var26, this.anIntArray1637[k2]);
         } else {
            Class33 l5 = super.aClass33Array1425[class33];
            l5.anInt602 += l4;
            l5.anInt603 += i5;
            l5.anInt604 += j5;
            ++l5.anInt605;
            l5 = super.aClass33Array1425[class33_1];
            l5.anInt602 += l4;
            l5.anInt603 += i5;
            l5.anInt604 += j5;
            ++l5.anInt605;
            l5 = super.aClass33Array1425[i3];
            l5.anInt602 += l4;
            l5.anInt603 += i5;
            l5.anInt604 += j5;
            ++l5.anInt605;
         }
      }

      if(flag) {
         this.method480(i, k1, k, l, i1);
      } else {
         this.aClass33Array1660 = new Class33[this.anInt1626];

         for(k2 = 0; k2 < this.anInt1626; ++k2) {
            Class33 var25 = super.aClass33Array1425[k2];
            Class33 var24 = this.aClass33Array1660[k2] = new Class33();
            var24.anInt602 = var25.anInt602;
            var24.anInt603 = var25.anInt603;
            var24.anInt604 = var25.anInt604;
            var24.anInt605 = var25.anInt605;
         }
      }

      if(flag) {
         this.method466();
      } else {
         this.method468();
      }

   }

   public void method480(int i, int j, int k, int l, int i1) {
      int l1;
      for(l1 = 0; l1 < this.anInt1630; ++l1) {
         int k1 = this.anIntArray1631[l1];
         int i2 = this.anIntArray1632[l1];
         int j2 = this.anIntArray1633[l1];
         int j3;
         if(this.anIntArray1637 == null) {
            j3 = this.anIntArray1640[l1];
            Class33 k3 = super.aClass33Array1425[k1];
            int class33_1 = i + (k * k3.anInt602 + l * k3.anInt603 + i1 * k3.anInt604) / (j * k3.anInt605);
            this.anIntArray1634[l1] = method481(j3, class33_1, 0);
            k3 = super.aClass33Array1425[i2];
            class33_1 = i + (k * k3.anInt602 + l * k3.anInt603 + i1 * k3.anInt604) / (j * k3.anInt605);
            this.anIntArray1635[l1] = method481(j3, class33_1, 0);
            k3 = super.aClass33Array1425[j2];
            class33_1 = i + (k * k3.anInt602 + l * k3.anInt603 + i1 * k3.anInt604) / (j * k3.anInt605);
            this.anIntArray1636[l1] = method481(j3, class33_1, 0);
         } else if((this.anIntArray1637[l1] & 1) == 0) {
            j3 = this.anIntArray1640[l1];
            int var14 = this.anIntArray1637[l1];
            Class33 var15 = super.aClass33Array1425[k1];
            int l2 = i + (k * var15.anInt602 + l * var15.anInt603 + i1 * var15.anInt604) / (j * var15.anInt605);
            this.anIntArray1634[l1] = method481(j3, l2, var14);
            var15 = super.aClass33Array1425[i2];
            l2 = i + (k * var15.anInt602 + l * var15.anInt603 + i1 * var15.anInt604) / (j * var15.anInt605);
            this.anIntArray1635[l1] = method481(j3, l2, var14);
            var15 = super.aClass33Array1425[j2];
            l2 = i + (k * var15.anInt602 + l * var15.anInt603 + i1 * var15.anInt604) / (j * var15.anInt605);
            this.anIntArray1636[l1] = method481(j3, l2, var14);
         }
      }

      super.aClass33Array1425 = null;
      this.aClass33Array1660 = null;
      this.anIntArray1655 = null;
      this.anIntArray1656 = null;
      if(this.anIntArray1637 != null) {
         for(l1 = 0; l1 < this.anInt1630; ++l1) {
            if((this.anIntArray1637[l1] & 2) == 2) {
               return;
            }
         }
      }

      this.anIntArray1640 = null;
   }

   private static int method481(int i, int j, int k) {
      if((k & 2) == 2) {
         if(j < 0) {
            j = 0;
         } else if(j > 127) {
            j = 127;
         }

         j = 127 - j;
         return j;
      } else {
         j = j * (i & 127) >> 7;
         if(j < 2) {
            j = 2;
         } else if(j > 126) {
            j = 126;
         }

         return (i & '\uff80') + j;
      }
   }

   public void method482(int j, int k, int l, int i1, int j1, int k1) {
      byte i = 0;
      int l1 = Texture.textureInt1;
      int i2 = Texture.textureInt2;
      int j2 = modelIntArray1[i];
      int k2 = modelIntArray2[i];
      int l2 = modelIntArray1[j];
      int i3 = modelIntArray2[j];
      int j3 = modelIntArray1[k];
      int k3 = modelIntArray2[k];
      int l3 = modelIntArray1[l];
      int i4 = modelIntArray2[l];
      int j4 = j1 * l3 + k1 * i4 >> 16;

      for(int _ex = 0; _ex < this.anInt1626; ++_ex) {
         int l4 = this.anIntArray1627[_ex];
         int i5 = this.anIntArray1628[_ex];
         int j5 = this.anIntArray1629[_ex];
         int j6;
         if(k != 0) {
            j6 = i5 * j3 + l4 * k3 >> 16;
            i5 = i5 * k3 - l4 * j3 >> 16;
            l4 = j6;
         }

         if(i != 0) {
            j6 = i5 * k2 - j5 * j2 >> 16;
            j5 = i5 * j2 + j5 * k2 >> 16;
            i5 = j6;
         }

         if(j != 0) {
            j6 = j5 * l2 + l4 * i3 >> 16;
            j5 = j5 * i3 - l4 * l2 >> 16;
            l4 = j6;
         }

         l4 += i1;
         i5 += j1;
         j5 += k1;
         j6 = i5 * i4 - j5 * l3 >> 16;
         j5 = i5 * l3 + j5 * i4 >> 16;
         anIntArray1667[_ex] = j5 - j4;
         anIntArray1665[_ex] = l1 + (l4 << 9) / j5;
         anIntArray1666[_ex] = i2 + (j6 << 9) / j5;
         if(this.anInt1642 > 0) {
            anIntArray1668[_ex] = l4;
            anIntArray1669[_ex] = j6;
            anIntArray1670[_ex] = j5;
         }
      }

      try {
         this.method483(false, false, 0);
      } catch (Exception var24) {
         ;
      }

   }

   public void method443(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
      int j2 = l1 * i1 - j1 * l >> 16;
      int k2 = k1 * j + j2 * k >> 16;
      int l2 = this.anInt1650 * k >> 16;
      int i3 = k2 + l2;
      if(i3 > 50 && k2 < 3500) {
         int j3 = l1 * l + j1 * i1 >> 16;
         int k3 = j3 - this.anInt1650 << 9;
         if(k3 / i3 < DrawingArea.centerY) {
            int l3 = j3 + this.anInt1650 << 9;
            if(l3 / i3 > -DrawingArea.centerY) {
               int i4 = k1 * k - j2 * j >> 16;
               int j4 = this.anInt1650 * j >> 16;
               int k4 = i4 + j4 << 9;
               if(k4 / i3 > -DrawingArea.anInt1387) {
                  int l4 = j4 + (super.modelHeight * k >> 16);
                  int i5 = i4 - l4 << 9;
                  if(i5 / i3 < DrawingArea.anInt1387) {
                     int j5 = l2 + (super.modelHeight * j >> 16);
                     boolean flag = false;
                     if(k2 - j5 <= 50) {
                        flag = true;
                     }

                     boolean flag1 = false;
                     int l5;
                     int l6;
                     int j6;
                     if(i2 > 0 && aBoolean1684) {
                        l5 = k2 - l2;
                        if(l5 <= 50) {
                           l5 = 50;
                        }

                        if(j3 > 0) {
                           k3 /= i3;
                           l3 /= l5;
                        } else {
                           l3 /= i3;
                           k3 /= l5;
                        }

                        if(i4 > 0) {
                           i5 /= i3;
                           k4 /= l5;
                        } else {
                           k4 /= i3;
                           i5 /= l5;
                        }

                        j6 = anInt1685 - Texture.textureInt1;
                        l6 = anInt1686 - Texture.textureInt2;
                        if(j6 > k3 && j6 < l3 && l6 > i5 && l6 < k4) {
                           if(this.aBoolean1659) {
                              anIntArray1688[anInt1687++] = i2;
                           } else {
                              flag1 = true;
                           }
                        }
                     }

                     l5 = Texture.textureInt1;
                     j6 = Texture.textureInt2;
                     l6 = 0;
                     int i7 = 0;
                     if(i != 0) {
                        l6 = modelIntArray1[i];
                        i7 = modelIntArray2[i];
                     }

                     for(int _ex = 0; _ex < this.anInt1626; ++_ex) {
                        int k7 = this.anIntArray1627[_ex];
                        int l7 = this.anIntArray1628[_ex];
                        int i8 = this.anIntArray1629[_ex];
                        int k8;
                        if(i != 0) {
                           k8 = i8 * l6 + k7 * i7 >> 16;
                           i8 = i8 * i7 - k7 * l6 >> 16;
                           k7 = k8;
                        }

                        k7 += j1;
                        l7 += k1;
                        i8 += l1;
                        k8 = i8 * l + k7 * i1 >> 16;
                        i8 = i8 * i1 - k7 * l >> 16;
                        k7 = k8;
                        k8 = l7 * k - i8 * j >> 16;
                        i8 = l7 * j + i8 * k >> 16;
                        anIntArray1667[_ex] = i8 - k2;
                        if(i8 >= 50) {
                           anIntArray1665[_ex] = l5 + (k7 << 9) / i8;
                           anIntArray1666[_ex] = j6 + (k8 << 9) / i8;
                        } else {
                           anIntArray1665[_ex] = -5000;
                           flag = true;
                        }

                        if(flag || this.anInt1642 > 0) {
                           anIntArray1668[_ex] = k7;
                           anIntArray1669[_ex] = k8;
                           anIntArray1670[_ex] = i8;
                        }
                     }

                     try {
                        this.method483(flag, flag1, i2);
                     } catch (Exception var34) {
                        ;
                     }

                  }
               }
            }
         }
      }
   }

   private void method483(boolean flag, boolean flag1, int i) {
      int l2;
      for(l2 = 0; l2 < this.anInt1652; ++l2) {
         anIntArray1671[l2] = 0;
      }

      int k3;
      int j4;
      int i6;
      int k6;
      int ai2;
      int ai3;
      int i5;
      for(l2 = 0; l2 < this.anInt1630; ++l2) {
         if(this.anIntArray1637 == null || this.anIntArray1637[l2] != -1) {
            k3 = this.anIntArray1631[l2];
            j4 = this.anIntArray1632[l2];
            i6 = this.anIntArray1633[l2];
            k6 = anIntArray1665[k3];
            ai2 = anIntArray1665[j4];
            ai3 = anIntArray1665[i6];
            if(flag && (k6 == -5000 || ai2 == -5000 || ai3 == -5000)) {
               aBooleanArray1664[l2] = true;
               i5 = (anIntArray1667[k3] + anIntArray1667[j4] + anIntArray1667[i6]) / 3 + this.anInt1653;
               anIntArrayArray1672[i5][anIntArray1671[i5]++] = l2;
            } else {
               if(flag1 && this.method486(anInt1685, anInt1686, anIntArray1666[k3], anIntArray1666[j4], anIntArray1666[i6], k6, ai2, ai3)) {
                  anIntArray1688[anInt1687++] = i;
                  flag1 = false;
               }

               if((k6 - ai2) * (anIntArray1666[i6] - anIntArray1666[j4]) - (anIntArray1666[k3] - anIntArray1666[j4]) * (ai3 - ai2) > 0) {
                  aBooleanArray1664[l2] = false;
                  aBooleanArray1663[l2] = k6 < 0 || ai2 < 0 || ai3 < 0 || k6 > DrawingArea.centerX || ai2 > DrawingArea.centerX || ai3 > DrawingArea.centerX;
                  i5 = (anIntArray1667[k3] + anIntArray1667[j4] + anIntArray1667[i6]) / 3 + this.anInt1653;
                  anIntArrayArray1672[i5][anIntArray1671[i5]++] = l2;
               }
            }
         }
      }

      int[] var16;
      if(this.anIntArray1638 == null) {
         for(l2 = this.anInt1652 - 1; l2 >= 0; --l2) {
            k3 = anIntArray1671[l2];
            if(k3 > 0) {
               var16 = anIntArrayArray1672[l2];

               for(i6 = 0; i6 < k3; ++i6) {
                  this.method484(var16[i6]);
               }
            }
         }

      } else {
         for(l2 = 0; l2 < 12; ++l2) {
            anIntArray1673[l2] = 0;
            anIntArray1677[l2] = 0;
         }

         for(l2 = this.anInt1652 - 1; l2 >= 0; --l2) {
            k3 = anIntArray1671[l2];
            if(k3 > 0) {
               var16 = anIntArrayArray1672[l2];

               for(i6 = 0; i6 < k3; ++i6) {
                  k6 = var16[i6];
                  ai2 = this.anIntArray1638[k6];
                  ai3 = anIntArray1673[ai2]++;
                  anIntArrayArray1674[ai2][ai3] = k6;
                  if(ai2 < 10) {
                     anIntArray1677[ai2] += l2;
                  } else if(ai2 == 10) {
                     anIntArray1675[ai3] = l2;
                  } else {
                     anIntArray1676[ai3] = l2;
                  }
               }
            }
         }

         l2 = 0;
         if(anIntArray1673[1] > 0 || anIntArray1673[2] > 0) {
            l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
         }

         k3 = 0;
         if(anIntArray1673[3] > 0 || anIntArray1673[4] > 0) {
            k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
         }

         j4 = 0;
         if(anIntArray1673[6] > 0 || anIntArray1673[8] > 0) {
            j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
         }

         i6 = 0;
         k6 = anIntArray1673[10];
         int[] var17 = anIntArrayArray1674[10];
         int[] var18 = anIntArray1675;
         if(i6 == k6) {
            i6 = 0;
            k6 = anIntArray1673[11];
            var17 = anIntArrayArray1674[11];
            var18 = anIntArray1676;
         }

         if(i6 < k6) {
            i5 = var18[i6];
         } else {
            i5 = -1000;
         }

         for(int l6 = 0; l6 < 10; ++l6) {
            while(l6 == 0 && i5 > l2) {
               this.method484(var17[i6++]);
               if(i6 == k6 && var17 != anIntArrayArray1674[11]) {
                  i6 = 0;
                  k6 = anIntArray1673[11];
                  var17 = anIntArrayArray1674[11];
                  var18 = anIntArray1676;
               }

               if(i6 < k6) {
                  i5 = var18[i6];
               } else {
                  i5 = -1000;
               }
            }

            while(l6 == 3 && i5 > k3) {
               this.method484(var17[i6++]);
               if(i6 == k6 && var17 != anIntArrayArray1674[11]) {
                  i6 = 0;
                  k6 = anIntArray1673[11];
                  var17 = anIntArrayArray1674[11];
                  var18 = anIntArray1676;
               }

               if(i6 < k6) {
                  i5 = var18[i6];
               } else {
                  i5 = -1000;
               }
            }

            while(l6 == 5 && i5 > j4) {
               this.method484(var17[i6++]);
               if(i6 == k6 && var17 != anIntArrayArray1674[11]) {
                  i6 = 0;
                  k6 = anIntArray1673[11];
                  var17 = anIntArrayArray1674[11];
                  var18 = anIntArray1676;
               }

               if(i6 < k6) {
                  i5 = var18[i6];
               } else {
                  i5 = -1000;
               }
            }

            int i7 = anIntArray1673[l6];
            int[] ai4 = anIntArrayArray1674[l6];

            for(int j7 = 0; j7 < i7; ++j7) {
               this.method484(ai4[j7]);
            }
         }

         while(i5 != -1000) {
            this.method484(var17[i6++]);
            if(i6 == k6 && var17 != anIntArrayArray1674[11]) {
               i6 = 0;
               var17 = anIntArrayArray1674[11];
               k6 = anIntArray1673[11];
               var18 = anIntArray1676;
            }

            if(i6 < k6) {
               i5 = var18[i6];
            } else {
               i5 = -1000;
            }
         }

      }
   }

   private void method484(int i) {
      if(aBooleanArray1664[i]) {
         this.method485(i);
      } else {
         int j = this.anIntArray1631[i];
         int k = this.anIntArray1632[i];
         int l = this.anIntArray1633[i];
         Texture.aBoolean1462 = aBooleanArray1663[i];
         if(this.anIntArray1639 == null) {
            Texture.anInt1465 = 0;
         } else {
            Texture.anInt1465 = this.anIntArray1639[i];
         }

         int i1;
         if(this.anIntArray1637 == null) {
            i1 = 0;
         } else {
            i1 = this.anIntArray1637[i] & 3;
         }

         if(i1 == 0) {
            Texture.method374(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], this.anIntArray1634[i], this.anIntArray1635[i], this.anIntArray1636[i]);
         } else if(i1 == 1) {
            Texture.method376(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], modelIntArray3[this.anIntArray1634[i]]);
         } else {
            int k1;
            int i2;
            int k2;
            int i3;
            if(i1 == 2) {
               k1 = this.anIntArray1637[i] >> 2;
               i2 = this.anIntArray1643[k1];
               k2 = this.anIntArray1644[k1];
               i3 = this.anIntArray1645[k1];
               Texture.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], this.anIntArray1634[i], this.anIntArray1635[i], this.anIntArray1636[i], anIntArray1668[i2], anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3], anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], this.anIntArray1640[i]);
            } else {
               if(i1 == 3) {
                  k1 = this.anIntArray1637[i] >> 2;
                  i2 = this.anIntArray1643[k1];
                  k2 = this.anIntArray1644[k1];
                  i3 = this.anIntArray1645[k1];
                  Texture.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], this.anIntArray1634[i], this.anIntArray1634[i], this.anIntArray1634[i], anIntArray1668[i2], anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3], anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], this.anIntArray1640[i]);
               }

            }
         }
      }
   }

   private void method485(int i) {
      int j = Texture.textureInt1;
      int k = Texture.textureInt2;
      int l = 0;
      int i1 = this.anIntArray1631[i];
      int j1 = this.anIntArray1632[i];
      int k1 = this.anIntArray1633[i];
      int l1 = anIntArray1670[i1];
      int i2 = anIntArray1670[j1];
      int j2 = anIntArray1670[k1];
      int j3;
      int j4;
      int j5;
      int i7;
      if(l1 >= 50) {
         anIntArray1678[l] = anIntArray1665[i1];
         anIntArray1679[l] = anIntArray1666[i1];
         anIntArray1680[l++] = this.anIntArray1634[i];
      } else {
         j3 = anIntArray1668[i1];
         j4 = anIntArray1669[i1];
         j5 = this.anIntArray1634[i];
         if(j2 >= 50) {
            i7 = (50 - l1) * modelIntArray4[j2 - l1];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[k1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[k1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1636[i] - j5) * i7 >> 16);
         }

         if(i2 >= 50) {
            i7 = (50 - l1) * modelIntArray4[i2 - l1];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[j1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[j1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1635[i] - j5) * i7 >> 16);
         }
      }

      if(i2 >= 50) {
         anIntArray1678[l] = anIntArray1665[j1];
         anIntArray1679[l] = anIntArray1666[j1];
         anIntArray1680[l++] = this.anIntArray1635[i];
      } else {
         j3 = anIntArray1668[j1];
         j4 = anIntArray1669[j1];
         j5 = this.anIntArray1635[i];
         if(l1 >= 50) {
            i7 = (50 - i2) * modelIntArray4[l1 - i2];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[i1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[i1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1634[i] - j5) * i7 >> 16);
         }

         if(j2 >= 50) {
            i7 = (50 - i2) * modelIntArray4[j2 - i2];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[k1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[k1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1636[i] - j5) * i7 >> 16);
         }
      }

      if(j2 >= 50) {
         anIntArray1678[l] = anIntArray1665[k1];
         anIntArray1679[l] = anIntArray1666[k1];
         anIntArray1680[l++] = this.anIntArray1636[i];
      } else {
         j3 = anIntArray1668[k1];
         j4 = anIntArray1669[k1];
         j5 = this.anIntArray1636[i];
         if(i2 >= 50) {
            i7 = (50 - j2) * modelIntArray4[i2 - j2];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[j1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[j1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1635[i] - j5) * i7 >> 16);
         }

         if(l1 >= 50) {
            i7 = (50 - j2) * modelIntArray4[l1 - j2];
            anIntArray1678[l] = j + (j3 + ((anIntArray1668[i1] - j3) * i7 >> 16) << 9) / 50;
            anIntArray1679[l] = k + (j4 + ((anIntArray1669[i1] - j4) * i7 >> 16) << 9) / 50;
            anIntArray1680[l++] = j5 + ((this.anIntArray1634[i] - j5) * i7 >> 16);
         }
      }

      j3 = anIntArray1678[0];
      j4 = anIntArray1678[1];
      j5 = anIntArray1678[2];
      i7 = anIntArray1679[0];
      int j7 = anIntArray1679[1];
      int k7 = anIntArray1679[2];
      if((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
         Texture.aBoolean1462 = false;
         int i8;
         int j10;
         int j9;
         int j12;
         int j11;
         if(l == 3) {
            if(j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX) {
               Texture.aBoolean1462 = true;
            }

            if(this.anIntArray1637 == null) {
               i8 = 0;
            } else {
               i8 = this.anIntArray1637[i] & 3;
            }

            if(i8 == 0) {
               Texture.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
            } else if(i8 == 1) {
               Texture.method376(i7, j7, k7, j3, j4, j5, modelIntArray3[this.anIntArray1634[i]]);
            } else if(i8 == 2) {
               j9 = this.anIntArray1637[i] >> 2;
               j10 = this.anIntArray1643[j9];
               j11 = this.anIntArray1644[j9];
               j12 = this.anIntArray1645[j9];
               Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
            } else if(i8 == 3) {
               j9 = this.anIntArray1637[i] >> 2;
               j10 = this.anIntArray1643[j9];
               j11 = this.anIntArray1644[j9];
               j12 = this.anIntArray1645[j9];
               Texture.method378(i7, j7, k7, j3, j4, j5, this.anIntArray1634[i], this.anIntArray1634[i], this.anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
            }
         }

         if(l == 4) {
            if(j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX || anIntArray1678[3] < 0 || anIntArray1678[3] > DrawingArea.centerX) {
               Texture.aBoolean1462 = true;
            }

            if(this.anIntArray1637 == null) {
               i8 = 0;
            } else {
               i8 = this.anIntArray1637[i] & 3;
            }

            if(i8 == 0) {
               Texture.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
               Texture.method374(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3]);
               return;
            }

            if(i8 == 1) {
               j9 = modelIntArray3[this.anIntArray1634[i]];
               Texture.method376(i7, j7, k7, j3, j4, j5, j9);
               Texture.method376(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], j9);
               return;
            }

            if(i8 == 2) {
               j9 = this.anIntArray1637[i] >> 2;
               j10 = this.anIntArray1643[j9];
               j11 = this.anIntArray1644[j9];
               j12 = this.anIntArray1645[j9];
               Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
               Texture.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
               return;
            }

            if(i8 == 3) {
               j9 = this.anIntArray1637[i] >> 2;
               j10 = this.anIntArray1643[j9];
               j11 = this.anIntArray1644[j9];
               j12 = this.anIntArray1645[j9];
               Texture.method378(i7, j7, k7, j3, j4, j5, this.anIntArray1634[i], this.anIntArray1634[i], this.anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
               Texture.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], this.anIntArray1634[i], this.anIntArray1634[i], this.anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], this.anIntArray1640[i]);
            }
         }
      }

   }

   private boolean method486(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
      return j < k && j < l && j < i1?false:(j > k && j > l && j > i1?false:(i >= j1 || i >= k1 || i >= l1) && (i <= j1 || i <= k1 || i <= l1));
   }

}
