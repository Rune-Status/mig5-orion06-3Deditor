package client;


final class ObjectManager {

   private static int anInt123 = (int)(Math.random() * 17.0D) - 8;
   private final int[] anIntArray124;
   private final int[] anIntArray125;
   private final int[] anIntArray126;
   private final int[] anIntArray127;
   private final int[] anIntArray128;
   private final int[][][] anIntArrayArrayArray129;
   private final byte[][][] aByteArrayArrayArray130;
   static int anInt131;
   private static int anInt133 = (int)(Math.random() * 33.0D) - 16;
   private final byte[][][] aByteArrayArrayArray134;
   private final int[][][] anIntArrayArrayArray135;
   private final byte[][][] aByteArrayArrayArray136;
   private static final int[] anIntArray137 = new int[]{1, 0, -1, 0};
   private final int[][] anIntArrayArray139;
   private static final int[] anIntArray140 = new int[]{16, 32, 64, 128};
   private final byte[][][] aByteArrayArrayArray142;
   private static final int[] anIntArray144 = new int[]{0, -1, 0, 1};
   static int anInt145 = 99;
   private final int anInt146;
   private final int anInt147;
   private final byte[][][] aByteArrayArrayArray148;
   private final byte[][][] aByteArrayArrayArray149;
   static boolean lowMem = true;
   private static final int[] anIntArray152 = new int[]{1, 2, 4, 8};


   public ObjectManager(byte[][][] abyte0, int[][][] ai) {
      anInt145 = 99;
      this.anInt146 = 104;
      this.anInt147 = 104;
      this.anIntArrayArrayArray129 = ai;
      this.aByteArrayArrayArray149 = abyte0;
      this.aByteArrayArrayArray142 = new byte[4][this.anInt146][this.anInt147];
      this.aByteArrayArrayArray130 = new byte[4][this.anInt146][this.anInt147];
      this.aByteArrayArrayArray136 = new byte[4][this.anInt146][this.anInt147];
      this.aByteArrayArrayArray148 = new byte[4][this.anInt146][this.anInt147];
      this.anIntArrayArrayArray135 = new int[4][this.anInt146 + 1][this.anInt147 + 1];
      this.aByteArrayArrayArray134 = new byte[4][this.anInt146 + 1][this.anInt147 + 1];
      this.anIntArrayArray139 = new int[this.anInt146 + 1][this.anInt147 + 1];
      this.anIntArray124 = new int[this.anInt147];
      this.anIntArray125 = new int[this.anInt147];
      this.anIntArray126 = new int[this.anInt147];
      this.anIntArray127 = new int[this.anInt147];
      this.anIntArray128 = new int[this.anInt147];
   }

   private static int method170(int i, int j) {
      int k = i + j * 57;
      k ^= k << 13;
      int l = k * (k * k * 15731 + 789221) + 1376312589 & Integer.MAX_VALUE;
      return l >> 19 & 255;
   }

   public final void method171(CollisionMap[] aclass11, WorldController worldController) {
      int i2;
      int j2;
      int k2;
      int l2;
      for(i2 = 0; i2 < 4; ++i2) {
         for(j2 = 0; j2 < 104; ++j2) {
            for(k2 = 0; k2 < 104; ++k2) {
               if((this.aByteArrayArrayArray149[i2][j2][k2] & 1) == 1) {
                  l2 = i2;
                  if((this.aByteArrayArrayArray149[1][j2][k2] & 2) == 2) {
                     l2 = i2 - 1;
                  }

                  if(l2 >= 0) {
                     aclass11[l2].method213(k2, j2);
                  }
               }
            }
         }
      }

      anInt123 += (int)(Math.random() * 5.0D) - 2;
      if(anInt123 < -8) {
         anInt123 = -8;
      }

      if(anInt123 > 8) {
         anInt123 = 8;
      }

      anInt133 += (int)(Math.random() * 5.0D) - 2;
      if(anInt133 < -16) {
         anInt133 = -16;
      }

      if(anInt133 > 16) {
         anInt133 = 16;
      }

      int i5;
      int j6;
      int k7;
      int i9;
      int j12;
      int k13;
      int i16;
      int i15;
      int i18;
      int i17;
      for(i2 = 0; i2 < 4; ++i2) {
         byte[][] var40 = this.aByteArrayArrayArray134[i2];
         byte var41 = 96;
         short var43 = 768;
         byte i3 = -50;
         byte k3 = -10;
         byte i4 = -50;
         i5 = (int)Math.sqrt((double)(i3 * i3 + k3 * k3 + i4 * i4));
         j6 = var43 * i5 >> 8;

         int l18;
         int k18;
         for(k7 = 1; k7 < this.anInt147 - 1; ++k7) {
            for(i9 = 1; i9 < this.anInt146 - 1; ++i9) {
               j12 = this.anIntArrayArrayArray129[i2][i9 + 1][k7] - this.anIntArrayArrayArray129[i2][i9 - 1][k7];
               k13 = this.anIntArrayArrayArray129[i2][i9][k7 + 1] - this.anIntArrayArrayArray129[i2][i9][k7 - 1];
               i15 = (int)Math.sqrt((double)(j12 * j12 + 65536 + k13 * k13));
               i16 = (j12 << 8) / i15;
               i17 = 65536 / i15;
               i18 = (k13 << 8) / i15;
               k18 = var41 + (i3 * i16 + k3 * i17 + i4 * i18) / j6;
               l18 = (var40[i9 - 1][k7] >> 2) + (var40[i9 + 1][k7] >> 3) + (var40[i9][k7 - 1] >> 2) + (var40[i9][k7 + 1] >> 3) + (var40[i9][k7] >> 1);
               this.anIntArrayArray139[i9][k7] = k18 - l18;
            }
         }

         for(k7 = 0; k7 < this.anInt147; ++k7) {
            this.anIntArray124[k7] = 0;
            this.anIntArray125[k7] = 0;
            this.anIntArray126[k7] = 0;
            this.anIntArray127[k7] = 0;
            this.anIntArray128[k7] = 0;
         }

         for(k7 = -5; k7 < this.anInt146 + 5; ++k7) {
            for(i9 = 0; i9 < this.anInt147; ++i9) {
               j12 = k7 + 5;
               if(j12 >= 0 && j12 < this.anInt146) {
                  k13 = this.aByteArrayArrayArray142[i2][j12][i9] & 255;
                  if(k13 > 0) {
                     Flo var46 = Flo.cache[k13 - 1];
                     this.anIntArray124[i9] += var46.anInt397;
                     this.anIntArray125[i9] += var46.anInt395;
                     this.anIntArray126[i9] += var46.anInt396;
                     this.anIntArray127[i9] += var46.anInt398;
                     ++this.anIntArray128[i9];
                  }
               }

               k13 = k7 - 5;
               if(k13 >= 0 && k13 < this.anInt146) {
                  i15 = this.aByteArrayArrayArray142[i2][k13][i9] & 255;
                  if(i15 > 0) {
                     Flo var48 = Flo.cache[i15 - 1];
                     this.anIntArray124[i9] -= var48.anInt397;
                     this.anIntArray125[i9] -= var48.anInt395;
                     this.anIntArray126[i9] -= var48.anInt396;
                     this.anIntArray127[i9] -= var48.anInt398;
                     --this.anIntArray128[i9];
                  }
               }
            }

            if(k7 >= 1 && k7 < this.anInt146 - 1) {
               i9 = 0;
               j12 = 0;
               k13 = 0;
               i15 = 0;
               i16 = 0;

               for(i17 = -5; i17 < this.anInt147 + 5; ++i17) {
                  i18 = i17 + 5;
                  if(i18 >= 0 && i18 < this.anInt147) {
                     i9 += this.anIntArray124[i18];
                     j12 += this.anIntArray125[i18];
                     k13 += this.anIntArray126[i18];
                     i15 += this.anIntArray127[i18];
                     i16 += this.anIntArray128[i18];
                  }

                  k18 = i17 - 5;
                  if(k18 >= 0 && k18 < this.anInt147) {
                     i9 -= this.anIntArray124[k18];
                     j12 -= this.anIntArray125[k18];
                     k13 -= this.anIntArray126[k18];
                     i15 -= this.anIntArray127[k18];
                     i16 -= this.anIntArray128[k18];
                  }

                  if(i17 >= 1 && i17 < this.anInt147 - 1 && (!lowMem || (this.aByteArrayArrayArray149[0][k7][i17] & 2) != 0 || (this.aByteArrayArrayArray149[i2][k7][i17] & 16) == 0 && this.method182(i17, i2, k7) == anInt131)) {
                     if(i2 < anInt145) {
                        anInt145 = i2;
                     }

                     l18 = this.aByteArrayArrayArray142[i2][k7][i17] & 255;
                     int i19 = this.aByteArrayArrayArray130[i2][k7][i17] & 255;
                     if(l18 > 0 || i19 > 0) {
                        int j19 = this.anIntArrayArrayArray129[i2][k7][i17];
                        int k19 = this.anIntArrayArrayArray129[i2][k7 + 1][i17];
                        int l19 = this.anIntArrayArrayArray129[i2][k7 + 1][i17 + 1];
                        int i20 = this.anIntArrayArrayArray129[i2][k7][i17 + 1];
                        int j20 = this.anIntArrayArray139[k7][i17];
                        int k20 = this.anIntArrayArray139[k7 + 1][i17];
                        int l20 = this.anIntArrayArray139[k7 + 1][i17 + 1];
                        int i21 = this.anIntArrayArray139[k7][i17 + 1];
                        int j21 = -1;
                        int k21 = -1;
                        int k22;
                        int i22;
                        if(l18 > 0) {
                           i22 = i9 * 256 / i15;
                           k22 = j12 / i16;
                           int byte4 = k13 / i16;
                           j21 = this.method177(i22, k22, byte4);
                           i22 = i22 + anInt123 & 255;
                           byte4 += anInt133;
                           if(byte4 < 0) {
                              byte4 = 0;
                           } else if(byte4 > 255) {
                              byte4 = 255;
                           }

                           k21 = this.method177(i22, k22, byte4);
                        }

                        if(i2 > 0) {
                           boolean var49 = true;
                           if(l18 == 0 && this.aByteArrayArrayArray136[i2][k7][i17] != 0) {
                              var49 = false;
                           }

                           if(i19 > 0 && !Flo.cache[i19 - 1].aBoolean393) {
                              var49 = false;
                           }

                           if(var49 && j19 == k19 && j19 == l19 && j19 == i20) {
                              this.anIntArrayArrayArray135[i2][k7][i17] |= 2340;
                           }
                        }

                        i22 = 0;
                        if(j21 != -1) {
                           i22 = Texture.anIntArray1482[method187(k21, 96)];
                        }

                        if(i19 == 0) {
                           worldController.method279(i2, k7, i17, 0, 0, -1, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), 0, 0, 0, 0, i22, 0);
                        } else {
                           k22 = this.aByteArrayArrayArray136[i2][k7][i17] + 1;
                           byte var50 = this.aByteArrayArrayArray148[i2][k7][i17];
                           Flo flo_2 = Flo.cache[i19 - 1];
                           int i23 = flo_2.anInt391;
                           int j23;
                           int k23;
                           if(i23 >= 0) {
                              k23 = Texture.method369(i23);
                              j23 = -1;
                           } else if(flo_2.anInt390 == 16711935) {
							 k23 = 0;
							if(flo_2.mapColor != 0)
								k23 = Texture.anIntArray1482[method185(flo_2.anInt399, 96)];
                              j23 = -2;
                              i23 = -1;
                           } else {
                              j23 = this.method177(flo_2.anInt394, flo_2.anInt395, flo_2.anInt396);
                              //k23 = Texture.anIntArray1482[this.method185(flo_2.anInt399, 96)];
							  k23 = Texture.anIntArray1482[this.method185(j23, 96)];
                           }

                           worldController.method279(i2, k7, i17, k22, var50, i23, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), this.method185(j23, j20), this.method185(j23, k20), this.method185(j23, l20), this.method185(j23, i21), i22, k23);
                        }
                     }
                  }
               }
            }
         }

         for(k7 = 1; k7 < this.anInt147 - 1; ++k7) {
            for(i9 = 1; i9 < this.anInt146 - 1; ++i9) {
               worldController.method278(i2, i9, k7, this.method182(k7, i2, i9));
            }
         }
      }

      worldController.method305(-10, -50, -50);

      for(i2 = 0; i2 < this.anInt146; ++i2) {
         for(j2 = 0; j2 < this.anInt147; ++j2) {
            if((this.aByteArrayArrayArray149[1][i2][j2] & 2) == 2) {
               worldController.method276(j2, i2);
            }
         }
      }

      i2 = 1;
      j2 = 2;
      k2 = 4;

      for(l2 = 0; l2 < 4; ++l2) {
         if(l2 > 0) {
            i2 <<= 3;
            j2 <<= 3;
            k2 <<= 3;
         }

         for(int var42 = 0; var42 <= l2; ++var42) {
            for(int var44 = 0; var44 <= this.anInt147; ++var44) {
               for(int var45 = 0; var45 <= this.anInt146; ++var45) {
                  short var47;
                  if((this.anIntArrayArrayArray135[var42][var45][var44] & i2) != 0) {
                     i5 = var44;
                     j6 = var44;
                     k7 = var42;

                     for(i9 = var42; i5 > 0 && (this.anIntArrayArrayArray135[var42][var45][i5 - 1] & i2) != 0; --i5) {
                        ;
                     }

                     while(j6 < this.anInt147 && (this.anIntArrayArrayArray135[var42][var45][j6 + 1] & i2) != 0) {
                        ++j6;
                     }

                     label294:
                     while(k7 > 0) {
                        for(j12 = i5; j12 <= j6; ++j12) {
                           if((this.anIntArrayArrayArray135[k7 - 1][var45][j12] & i2) == 0) {
                              break label294;
                           }
                        }

                        --k7;
                     }

                     label305:
                     while(i9 < l2) {
                        for(j12 = i5; j12 <= j6; ++j12) {
                           if((this.anIntArrayArrayArray135[i9 + 1][var45][j12] & i2) == 0) {
                              break label305;
                           }
                        }

                        ++i9;
                     }

                     j12 = (i9 + 1 - k7) * (j6 - i5 + 1);
                     if(j12 >= 8) {
                        var47 = 240;
                        i15 = this.anIntArrayArrayArray129[i9][var45][i5] - var47;
                        i16 = this.anIntArrayArrayArray129[k7][var45][i5];
                        WorldController.method277(l2, var45 * 128, i16, var45 * 128, j6 * 128 + 128, i15, i5 * 128, 1);

                        for(i17 = k7; i17 <= i9; ++i17) {
                           for(i18 = i5; i18 <= j6; ++i18) {
                              this.anIntArrayArrayArray135[i17][var45][i18] &= ~i2;
                           }
                        }
                     }
                  }

                  if((this.anIntArrayArrayArray135[var42][var45][var44] & j2) != 0) {
                     i5 = var45;
                     j6 = var45;
                     k7 = var42;

                     for(i9 = var42; i5 > 0 && (this.anIntArrayArrayArray135[var42][i5 - 1][var44] & j2) != 0; --i5) {
                        ;
                     }

                     while(j6 < this.anInt146 && (this.anIntArrayArrayArray135[var42][j6 + 1][var44] & j2) != 0) {
                        ++j6;
                     }

                     label343:
                     while(k7 > 0) {
                        for(j12 = i5; j12 <= j6; ++j12) {
                           if((this.anIntArrayArrayArray135[k7 - 1][j12][var44] & j2) == 0) {
                              break label343;
                           }
                        }

                        --k7;
                     }

                     label354:
                     while(i9 < l2) {
                        for(j12 = i5; j12 <= j6; ++j12) {
                           if((this.anIntArrayArrayArray135[i9 + 1][j12][var44] & j2) == 0) {
                              break label354;
                           }
                        }

                        ++i9;
                     }

                     j12 = (i9 + 1 - k7) * (j6 - i5 + 1);
                     if(j12 >= 8) {
                        var47 = 240;
                        i15 = this.anIntArrayArrayArray129[i9][i5][var44] - var47;
                        i16 = this.anIntArrayArrayArray129[k7][i5][var44];
                        WorldController.method277(l2, i5 * 128, i16, j6 * 128 + 128, var44 * 128, i15, var44 * 128, 2);

                        for(i17 = k7; i17 <= i9; ++i17) {
                           for(i18 = i5; i18 <= j6; ++i18) {
                              this.anIntArrayArrayArray135[i17][i18][var44] &= ~j2;
                           }
                        }
                     }
                  }

                  if((this.anIntArrayArrayArray135[var42][var45][var44] & k2) != 0) {
                     i5 = var45;
                     j6 = var45;
                     k7 = var44;

                     for(i9 = var44; k7 > 0 && (this.anIntArrayArrayArray135[var42][var45][k7 - 1] & k2) != 0; --k7) {
                        ;
                     }

                     while(i9 < this.anInt147 && (this.anIntArrayArrayArray135[var42][var45][i9 + 1] & k2) != 0) {
                        ++i9;
                     }

                     label393:
                     while(i5 > 0) {
                        for(j12 = k7; j12 <= i9; ++j12) {
                           if((this.anIntArrayArrayArray135[var42][i5 - 1][j12] & k2) == 0) {
                              break label393;
                           }
                        }

                        --i5;
                     }

                     label404:
                     while(j6 < this.anInt146) {
                        for(j12 = k7; j12 <= i9; ++j12) {
                           if((this.anIntArrayArrayArray135[var42][j6 + 1][j12] & k2) == 0) {
                              break label404;
                           }
                        }

                        ++j6;
                     }

                     if((j6 - i5 + 1) * (i9 - k7 + 1) >= 4) {
                        j12 = this.anIntArrayArrayArray129[var42][i5][k7];
                        WorldController.method277(l2, i5 * 128, j12, j6 * 128 + 128, i9 * 128 + 128, j12, k7 * 128, 4);

                        for(k13 = i5; k13 <= j6; ++k13) {
                           for(i15 = k7; i15 <= i9; ++i15) {
                              this.anIntArrayArrayArray135[var42][k13][i15] &= ~k2;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static int method172(int i, int j) {
      int k = method176(i + '\ub135', j + 91923, 4) - 128 + (method176(i + 10294, j + '\u93bd', 2) - 128 >> 1) + (method176(i, j, 1) - 128 >> 2);
      k = (int)((double)k * 0.3D) + 35;
      if(k < 10) {
         k = 10;
      } else if(k > 60) {
         k = 60;
      }

      return k;
   }

   public static void method173(Stream stream, OnDemandFetcher class42_sub1) {
      int i = -1;

      while(true) {
         int j = stream.method422();
         if(j == 0) {
            return;
         }

         i += j;
         ObjectDef class46 = ObjectDef.forID(i);
         class46.method574(class42_sub1);

         while(true) {
            int k = stream.method422();
            if(k == 0) {
               break;
            }

            stream.readUnsignedByte();
         }
      }
   }

   public final void method174(int i, int j, int l, int i1) {
      for(int j1 = i; j1 <= i + j; ++j1) {
         for(int k1 = i1; k1 <= i1 + l; ++k1) {
            if(k1 >= 0 && k1 < this.anInt146 && j1 >= 0 && j1 < this.anInt147) {
               this.aByteArrayArrayArray134[0][k1][j1] = 127;
               if(k1 == i1 && k1 > 0) {
                  this.anIntArrayArrayArray129[0][k1][j1] = this.anIntArrayArrayArray129[0][k1 - 1][j1];
               }

               if(k1 == i1 + l && k1 < this.anInt146 - 1) {
                  this.anIntArrayArrayArray129[0][k1][j1] = this.anIntArrayArrayArray129[0][k1 + 1][j1];
               }

               if(j1 == i && j1 > 0) {
                  this.anIntArrayArrayArray129[0][k1][j1] = this.anIntArrayArrayArray129[0][k1][j1 - 1];
               }

               if(j1 == i + j && j1 < this.anInt147 - 1) {
                  this.anIntArrayArrayArray129[0][k1][j1] = this.anIntArrayArrayArray129[0][k1][j1 + 1];
               }
            }
         }
      }

   }

   private void method175(int i, WorldController worldController, CollisionMap class11, int j, int k, int l, int i1, int j1) {
      if(lowMem && (this.aByteArrayArrayArray149[0][l][i] & 2) == 0) {
         if((this.aByteArrayArrayArray149[k][l][i] & 16) != 0) {
            return;
         }

         if(this.method182(i, k, l) != anInt131) {
            return;
         }
      }

      if(k < anInt145) {
         anInt145 = k;
      }

      int k1 = this.anIntArrayArrayArray129[k][l][i];
      int l1 = this.anIntArrayArrayArray129[k][l + 1][i];
      int i2 = this.anIntArrayArrayArray129[k][l + 1][i + 1];
      int j2 = this.anIntArrayArrayArray129[k][l][i + 1];
      int k2 = k1 + l1 + i2 + j2 >> 2;
      ObjectDef class46 = ObjectDef.forID(i1);
      int l2 = l + (i << 7) + (i1 << 14) + 1073741824;
      if(!class46.hasActions) {
         l2 -= Integer.MIN_VALUE;
      }

      byte byte0 = (byte)((j1 << 6) + j);
      Object obj10;
      if(j == 22) {
         if(!lowMem || class46.hasActions || class46.aBoolean736) {
            if(class46.anInt781 == -1 && class46.childrenIDs == null) {
               obj10 = class46.method578(22, j1, k1, l1, i2, j2, -1);
            } else {
               obj10 = new Animable_Sub5(i1, j1, 22, l1, i2, k1, j2, class46.anInt781, true);
            }

            worldController.method280(k, k2, i, (Animable)obj10, byte0, l2, l);
            if(class46.aBoolean767 && class46.hasActions && class11 != null) {
               class11.method213(i, l);
            }

         }
      } else {
         int k4;
         if(j != 10 && j != 11) {
            if(j >= 12) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(j, j1, k1, l1, i2, j2, -1);
               } else {
                  obj10 = new Animable_Sub5(i1, j1, j, l1, i2, k1, j2, class46.anInt781, true);
               }

               worldController.method284(l2, byte0, k2, 1, (Animable)obj10, 1, k, 0, i, l);
               if(j >= 12 && j <= 17 && j != 13 && k > 0) {
                  this.anIntArrayArrayArray135[k][l][i] |= 2340;
               }

               if(class46.aBoolean767 && class11 != null) {
                  class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, l, i, j1);
               }

            } else if(j == 0) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(0, j1, k1, l1, i2, j2, -1);
               } else {
                  obj10 = new Animable_Sub5(i1, j1, 0, l1, i2, k1, j2, class46.anInt781, true);
               }

               worldController.method282(anIntArray152[j1], (Animable)obj10, l2, i, byte0, l, (Animable)null, k2, 0, k);
               if(j1 == 0) {
                  if(class46.aBoolean779) {
                     this.aByteArrayArrayArray134[k][l][i] = 50;
                     this.aByteArrayArrayArray134[k][l][i + 1] = 50;
                  }

                  if(class46.aBoolean764) {
                     this.anIntArrayArrayArray135[k][l][i] |= 585;
                  }
               } else if(j1 == 1) {
                  if(class46.aBoolean779) {
                     this.aByteArrayArrayArray134[k][l][i + 1] = 50;
                     this.aByteArrayArrayArray134[k][l + 1][i + 1] = 50;
                  }

                  if(class46.aBoolean764) {
                     this.anIntArrayArrayArray135[k][l][i + 1] |= 1170;
                  }
               } else if(j1 == 2) {
                  if(class46.aBoolean779) {
                     this.aByteArrayArrayArray134[k][l + 1][i] = 50;
                     this.aByteArrayArrayArray134[k][l + 1][i + 1] = 50;
                  }

                  if(class46.aBoolean764) {
                     this.anIntArrayArrayArray135[k][l + 1][i] |= 585;
                  }
               } else if(j1 == 3) {
                  if(class46.aBoolean779) {
                     this.aByteArrayArrayArray134[k][l][i] = 50;
                     this.aByteArrayArrayArray134[k][l + 1][i] = 50;
                  }

                  if(class46.aBoolean764) {
                     this.anIntArrayArrayArray135[k][l][i] |= 1170;
                  }
               }

               if(class46.aBoolean767 && class11 != null) {
                  class11.method211(i, j1, l, j, class46.aBoolean757);
               }

               if(class46.anInt775 != 16) {
                  worldController.method290(i, class46.anInt775, l, k);
               }

            } else if(j == 1) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(1, j1, k1, l1, i2, j2, -1);
               } else {
                  obj10 = new Animable_Sub5(i1, j1, 1, l1, i2, k1, j2, class46.anInt781, true);
               }

               worldController.method282(anIntArray140[j1], (Animable)obj10, l2, i, byte0, l, (Animable)null, k2, 0, k);
               if(class46.aBoolean779) {
                  if(j1 == 0) {
                     this.aByteArrayArrayArray134[k][l][i + 1] = 50;
                  } else if(j1 == 1) {
                     this.aByteArrayArrayArray134[k][l + 1][i + 1] = 50;
                  } else if(j1 == 2) {
                     this.aByteArrayArrayArray134[k][l + 1][i] = 50;
                  } else if(j1 == 3) {
                     this.aByteArrayArrayArray134[k][l][i] = 50;
                  }
               }

               if(class46.aBoolean767 && class11 != null) {
                  class11.method211(i, j1, l, j, class46.aBoolean757);
               }

            } else {
               int var25;
               Object var27;
               if(j == 2) {
                  var25 = j1 + 1 & 3;
                  Object var26;
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     var26 = class46.method578(2, 4 + j1, k1, l1, i2, j2, -1);
                     var27 = class46.method578(2, var25, k1, l1, i2, j2, -1);
                  } else {
                     var26 = new Animable_Sub5(i1, 4 + j1, 2, l1, i2, k1, j2, class46.anInt781, true);
                     var27 = new Animable_Sub5(i1, var25, 2, l1, i2, k1, j2, class46.anInt781, true);
                  }

                  worldController.method282(anIntArray152[j1], (Animable)var26, l2, i, byte0, l, (Animable)var27, k2, anIntArray152[var25], k);
                  if(class46.aBoolean764) {
                     if(j1 == 0) {
                        this.anIntArrayArrayArray135[k][l][i] |= 585;
                        this.anIntArrayArrayArray135[k][l][i + 1] |= 1170;
                     } else if(j1 == 1) {
                        this.anIntArrayArrayArray135[k][l][i + 1] |= 1170;
                        this.anIntArrayArrayArray135[k][l + 1][i] |= 585;
                     } else if(j1 == 2) {
                        this.anIntArrayArrayArray135[k][l + 1][i] |= 585;
                        this.anIntArrayArrayArray135[k][l][i] |= 1170;
                     } else if(j1 == 3) {
                        this.anIntArrayArrayArray135[k][l][i] |= 1170;
                        this.anIntArrayArrayArray135[k][l][i] |= 585;
                     }
                  }

                  if(class46.aBoolean767 && class11 != null) {
                     class11.method211(i, j1, l, j, class46.aBoolean757);
                  }

                  if(class46.anInt775 != 16) {
                     worldController.method290(i, class46.anInt775, l, k);
                  }

               } else if(j == 3) {
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     obj10 = class46.method578(3, j1, k1, l1, i2, j2, -1);
                  } else {
                     obj10 = new Animable_Sub5(i1, j1, 3, l1, i2, k1, j2, class46.anInt781, true);
                  }

                  worldController.method282(anIntArray140[j1], (Animable)obj10, l2, i, byte0, l, (Animable)null, k2, 0, k);
                  if(class46.aBoolean779) {
                     if(j1 == 0) {
                        this.aByteArrayArrayArray134[k][l][i + 1] = 50;
                     } else if(j1 == 1) {
                        this.aByteArrayArrayArray134[k][l + 1][i + 1] = 50;
                     } else if(j1 == 2) {
                        this.aByteArrayArrayArray134[k][l + 1][i] = 50;
                     } else if(j1 == 3) {
                        this.aByteArrayArrayArray134[k][l][i] = 50;
                     }
                  }

                  if(class46.aBoolean767 && class11 != null) {
                     class11.method211(i, j1, l, j, class46.aBoolean757);
                  }

               } else if(j == 9) {
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     obj10 = class46.method578(j, j1, k1, l1, i2, j2, -1);
                  } else {
                     obj10 = new Animable_Sub5(i1, j1, j, l1, i2, k1, j2, class46.anInt781, true);
                  }

                  worldController.method284(l2, byte0, k2, 1, (Animable)obj10, 1, k, 0, i, l);
                  if(class46.aBoolean767 && class11 != null) {
                     class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, l, i, j1);
                  }

               } else {
                  if(class46.aBoolean762) {
                     if(j1 == 1) {
                        var25 = j2;
                        j2 = i2;
                        i2 = l1;
                        l1 = k1;
                        k1 = var25;
                     } else if(j1 == 2) {
                        var25 = j2;
                        j2 = l1;
                        l1 = var25;
                        var25 = i2;
                        i2 = k1;
                        k1 = var25;
                     } else if(j1 == 3) {
                        var25 = j2;
                        j2 = k1;
                        k1 = l1;
                        l1 = i2;
                        i2 = var25;
                     }
                  }

                  if(j == 4) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, k1, l1, i2, j2, -1);
                     } else {
                        obj10 = new Animable_Sub5(i1, 0, 4, l1, i2, k1, j2, class46.anInt781, true);
                     }

                     worldController.method283(l2, i, j1 * 512, k, 0, k2, (Animable)obj10, l, byte0, 0, anIntArray152[j1]);
                  } else if(j == 5) {
                     var25 = 16;
                     k4 = worldController.method300(k, l, i);
                     if(k4 > 0) {
                        var25 = ObjectDef.forID(k4 >> 14 & 32767).anInt775;
                     }

                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        var27 = class46.method578(4, 0, k1, l1, i2, j2, -1);
                     } else {
                        var27 = new Animable_Sub5(i1, 0, 4, l1, i2, k1, j2, class46.anInt781, true);
                     }

                     worldController.method283(l2, i, j1 * 512, k, anIntArray137[j1] * var25, k2, (Animable)var27, l, byte0, anIntArray144[j1] * var25, anIntArray152[j1]);
                  } else if(j == 6) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, k1, l1, i2, j2, -1);
                     } else {
                        obj10 = new Animable_Sub5(i1, 0, 4, l1, i2, k1, j2, class46.anInt781, true);
                     }

                     worldController.method283(l2, i, j1, k, 0, k2, (Animable)obj10, l, byte0, 0, 256);
                  } else if(j == 7) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, k1, l1, i2, j2, -1);
                     } else {
                        obj10 = new Animable_Sub5(i1, 0, 4, l1, i2, k1, j2, class46.anInt781, true);
                     }

                     worldController.method283(l2, i, j1, k, 0, k2, (Animable)obj10, l, byte0, 0, 512);
                  } else {
                     if(j == 8) {
                        if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                           obj10 = class46.method578(4, 0, k1, l1, i2, j2, -1);
                        } else {
                           obj10 = new Animable_Sub5(i1, 0, 4, l1, i2, k1, j2, class46.anInt781, true);
                        }

                        worldController.method283(l2, i, j1, k, 0, k2, (Animable)obj10, l, byte0, 0, 768);
                     }

                  }
               }
            }
         } else {
            if(class46.anInt781 == -1 && class46.childrenIDs == null) {
               obj10 = class46.method578(10, j1, k1, l1, i2, j2, -1);
            } else {
               obj10 = new Animable_Sub5(i1, j1, 10, l1, i2, k1, j2, class46.anInt781, true);
            }

            if(obj10 != null) {
               k4 = 0;
               if(j == 11) {
                  k4 += 256;
               }

               int obj13;
               int l4;
               if(j1 != 1 && j1 != 3) {
                  obj13 = class46.anInt744;
                  l4 = class46.anInt761;
               } else {
                  obj13 = class46.anInt761;
                  l4 = class46.anInt744;
               }

               if(worldController.method284(l2, byte0, k2, l4, (Animable)obj10, obj13, k, k4, i, l) && class46.aBoolean779) {
                  Model model;
                  if(obj10 instanceof Model) {
                     model = (Model)obj10;
                  } else {
                     model = class46.method578(10, j1, k1, l1, i2, j2, -1);
                  }

                  if(model != null) {
                     for(int j5 = 0; j5 <= obj13; ++j5) {
                        for(int k5 = 0; k5 <= l4; ++k5) {
                           int l5 = model.anInt1650 / 4;
                           if(l5 > 30) {
                              l5 = 30;
                           }

                           if(l5 > this.aByteArrayArrayArray134[k][l + j5][i + k5]) {
                              this.aByteArrayArrayArray134[k][l + j5][i + k5] = (byte)l5;
                           }
                        }
                     }
                  }
               }
            }

            if(class46.aBoolean767 && class11 != null) {
               class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, l, i, j1);
            }

         }
      }
   }

   private static int method176(int i, int j, int k) {
      int l = i / k;
      int i1 = i & k - 1;
      int j1 = j / k;
      int k1 = j & k - 1;
      int l1 = method186(l, j1);
      int i2 = method186(l + 1, j1);
      int j2 = method186(l, j1 + 1);
      int k2 = method186(l + 1, j1 + 1);
      int l2 = method184(l1, i2, i1, k);
      int i3 = method184(j2, k2, i1, k);
      return method184(l2, i3, k1, k);
   }

   private int method177(int i, int j, int k) {
      if(k > 179) {
         j /= 2;
      }

      if(k > 192) {
         j /= 2;
      }

      if(k > 217) {
         j /= 2;
      }

      if(k > 243) {
         j /= 2;
      }

      return (i / 4 << 10) + (j / 32 << 7) + k / 2;
   }

   public static boolean method178(int i, int j) {
      ObjectDef class46 = ObjectDef.forID(i);
      if(j == 11) {
         j = 10;
      }

      if(j >= 5 && j <= 8) {
         j = 4;
      }

      return class46.method577(j);
   }

   public final void method179(int i, int j, CollisionMap[] aclass11, int l, int i1, byte[] abyte0, int j1, int k1, int l1) {
      int l2;
      for(int stream = 0; stream < 8; ++stream) {
         for(l2 = 0; l2 < 8; ++l2) {
            if(l + stream > 0 && l + stream < 103 && l1 + l2 > 0 && l1 + l2 < 103) {
               aclass11[k1].anIntArrayArray294[l + stream][l1 + l2] &= -16777217;
            }
         }
      }

      Stream var14 = new Stream(abyte0);

      for(l2 = 0; l2 < 4; ++l2) {
         for(int i3 = 0; i3 < 64; ++i3) {
            for(int j3 = 0; j3 < 64; ++j3) {
               if(l2 == i && i3 >= i1 && i3 < i1 + 8 && j3 >= j1 && j3 < j1 + 8) {
                  this.method181(l1 + Class4.method156(j3 & 7, j, i3 & 7), 0, var14, l + Class4.method155(j, j3 & 7, i3 & 7), k1, j, 0);
               } else {
                  this.method181(-1, 0, var14, -1, 0, 0, 0);
               }
            }
         }
      }

   }

   public final void method180(byte[] abyte0, int i, int j, int k, int l, CollisionMap[] aclass11) {
      int l1;
      int i2;
      for(int stream = 0; stream < 4; ++stream) {
         for(l1 = 0; l1 < 64; ++l1) {
            for(i2 = 0; i2 < 64; ++i2) {
               if(j + l1 > 0 && j + l1 < 103 && i + i2 > 0 && i + i2 < 103) {
                  aclass11[stream].anIntArrayArray294[j + l1][i + i2] &= -16777217;
               }
            }
         }
      }

      Stream var11 = new Stream(abyte0);

      for(l1 = 0; l1 < 4; ++l1) {
         for(i2 = 0; i2 < 64; ++i2) {
            for(int j2 = 0; j2 < 64; ++j2) {
               this.method181(j2 + i, l, var11, i2 + j, l1, 0, k);
            }
         }
      }

   }

   private void method181(int i, int j, Stream stream, int k, int l, int i1, int k1) {
      int i2;
      if(k >= 0 && k < 104 && i >= 0 && i < 104) {
         this.aByteArrayArrayArray149[l][k][i] = 0;

         while(true) {
            i2 = stream.readUnsignedByte();
            if(i2 == 0) {
               if(l == 0) {
                  this.anIntArrayArrayArray129[0][k][i] = -method172(932731 + k + k1, 556238 + i + j) * 8;
                  return;
               } else {
                  this.anIntArrayArrayArray129[l][k][i] = this.anIntArrayArrayArray129[l - 1][k][i] - 240;
                  return;
               }
            }

            if(i2 == 1) {
               int j2 = stream.readUnsignedByte();
               if(j2 == 1) {
                  j2 = 0;
               }

               if(l == 0) {
                  this.anIntArrayArrayArray129[0][k][i] = -j2 * 8;
                  return;
               }

               this.anIntArrayArrayArray129[l][k][i] = this.anIntArrayArrayArray129[l - 1][k][i] - j2 * 8;
               return;
            }

            if(i2 <= 49) {
               this.aByteArrayArrayArray130[l][k][i] = stream.readSignedByte();
               this.aByteArrayArrayArray136[l][k][i] = (byte)((i2 - 2) / 4);
               this.aByteArrayArrayArray148[l][k][i] = (byte)(i2 - 2 + i1 & 3);
            } else if(i2 <= 81) {
               this.aByteArrayArrayArray149[l][k][i] = (byte)(i2 - 49);
            } else {
               this.aByteArrayArrayArray142[l][k][i] = (byte)(i2 - 81);
            }
         }
      } else {
         while(true) {
            i2 = stream.readUnsignedByte();
            if(i2 == 0) {
               return;
            }

            if(i2 == 1) {
               stream.readUnsignedByte();
               return;
            }

            if(i2 <= 49) {
               stream.readUnsignedByte();
            }
         }
      }
   }

   private int method182(int i, int j, int k) {
      return (this.aByteArrayArrayArray149[j][k][i] & 8) != 0?0:(j > 0 && (this.aByteArrayArrayArray149[1][k][i] & 2) != 0?j - 1:j);
   }

   public final void method183(CollisionMap[] aclass11, WorldController worldController, int i, int j, int k, int l, byte[] abyte0, int i1, int j1, int k1) {
      Stream stream = new Stream(abyte0);
      int l1 = -1;

      while(true) {
         int i2 = stream.method422();
         if(i2 == 0) {
            return;
         }

         l1 += i2;
         int j2 = 0;

         while(true) {
            int k2 = stream.method422();
            if(k2 == 0) {
               break;
            }

            j2 += k2 - 1;
            int l2 = j2 & 63;
            int i3 = j2 >> 6 & 63;
            int j3 = j2 >> 12;
            int k3 = stream.readUnsignedByte();
            int l3 = k3 >> 2;
            int i4 = k3 & 3;
            if(j3 == i && i3 >= i1 && i3 < i1 + 8 && l2 >= k && l2 < k + 8) {
               ObjectDef class46 = ObjectDef.forID(l1);
               int j4 = j + Class4.method157(j1, class46.anInt761, i3 & 7, l2 & 7, class46.anInt744);
               int k4 = k1 + Class4.method158(l2 & 7, class46.anInt761, j1, class46.anInt744, i3 & 7);
               if(j4 > 0 && k4 > 0 && j4 < 103 && k4 < 103) {
                  int l4 = j3;
                  if((this.aByteArrayArrayArray149[1][j4][k4] & 2) == 2) {
                     l4 = j3 - 1;
                  }

                  CollisionMap class11 = null;
                  if(l4 >= 0) {
                     class11 = aclass11[l4];
                  }

                  this.method175(k4, worldController, class11, l3, l, j4, l1, i4 + j1 & 3);
               }
            }
         }
      }
   }

   private static int method184(int i, int j, int k, int l) {
      int i1 = 65536 - Texture.anIntArray1471[k * 1024 / l] >> 1;
      return (i * (65536 - i1) >> 16) + (j * i1 >> 16);
   }

   private int method185(int i, int j) {
      if(i == -2) {
         return 12345678;
      } else if(i == -1) {
         if(j < 0) {
            j = 0;
         } else if(j > 127) {
            j = 127;
         }

         j = 127 - j;
         return j;
      } else {
         j = j * (i & 127) / 128;
         if(j < 2) {
            j = 2;
         } else if(j > 126) {
            j = 126;
         }

         return (i & '\uff80') + j;
      }
   }

   private static int method186(int i, int j) {
      int k = method170(i - 1, j - 1) + method170(i + 1, j - 1) + method170(i - 1, j + 1) + method170(i + 1, j + 1);
      int l = method170(i - 1, j) + method170(i + 1, j) + method170(i, j - 1) + method170(i, j + 1);
      int i1 = method170(i, j);
      return k / 16 + l / 8 + i1 / 4;
   }

   private static int method187(int i, int j) {
      if(i == -1) {
         return 12345678;
      } else {
         j = j * (i & 127) / 128;
         if(j < 2) {
            j = 2;
         } else if(j > 126) {
            j = 126;
         }

         return (i & '\uff80') + j;
      }
   }

   public static void method188(WorldController worldController, int i, int j, int k, int l, CollisionMap class11, int[][][] ai, int i1, int j1, int k1) {
      int l1 = ai[l][i1][j];
      int i2 = ai[l][i1 + 1][j];
      int j2 = ai[l][i1 + 1][j + 1];
      int k2 = ai[l][i1][j + 1];
      int l2 = l1 + i2 + j2 + k2 >> 2;
      ObjectDef class46 = ObjectDef.forID(j1);
      int i3 = i1 + (j << 7) + (j1 << 14) + 1073741824;
      if(!class46.hasActions) {
         i3 -= Integer.MIN_VALUE;
      }

      byte byte1 = (byte)((i << 6) + k);
      Object obj10;
      if(k == 22) {
         if(class46.anInt781 == -1 && class46.childrenIDs == null) {
            obj10 = class46.method578(22, i, l1, i2, j2, k2, -1);
         } else {
            obj10 = new Animable_Sub5(j1, i, 22, i2, j2, l1, k2, class46.anInt781, true);
         }

         worldController.method280(k1, l2, j, (Animable)obj10, byte1, i3, i1);
         if(class46.aBoolean767 && class46.hasActions) {
            class11.method213(j, i1);
         }

      } else {
         int l4;
         if(k != 10 && k != 11) {
            if(k >= 12) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(k, i, l1, i2, j2, k2, -1);
               } else {
                  obj10 = new Animable_Sub5(j1, i, k, i2, j2, l1, k2, class46.anInt781, true);
               }

               worldController.method284(i3, byte1, l2, 1, (Animable)obj10, 1, k1, 0, j, i1);
               if(class46.aBoolean767) {
                  class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, i1, j, i);
               }

            } else if(k == 0) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(0, i, l1, i2, j2, k2, -1);
               } else {
                  obj10 = new Animable_Sub5(j1, i, 0, i2, j2, l1, k2, class46.anInt781, true);
               }

               worldController.method282(anIntArray152[i], (Animable)obj10, i3, j, byte1, i1, (Animable)null, l2, 0, k1);
               if(class46.aBoolean767) {
                  class11.method211(j, i, i1, k, class46.aBoolean757);
               }

            } else if(k == 1) {
               if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                  obj10 = class46.method578(1, i, l1, i2, j2, k2, -1);
               } else {
                  obj10 = new Animable_Sub5(j1, i, 1, i2, j2, l1, k2, class46.anInt781, true);
               }

               worldController.method282(anIntArray140[i], (Animable)obj10, i3, j, byte1, i1, (Animable)null, l2, 0, k1);
               if(class46.aBoolean767) {
                  class11.method211(j, i, i1, k, class46.aBoolean757);
               }

            } else {
               int obj101;
               Object obj131;
               if(k == 2) {
                  obj101 = i + 1 & 3;
                  Object l41;
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     l41 = class46.method578(2, 4 + i, l1, i2, j2, k2, -1);
                     obj131 = class46.method578(2, obj101, l1, i2, j2, k2, -1);
                  } else {
                     l41 = new Animable_Sub5(j1, 4 + i, 2, i2, j2, l1, k2, class46.anInt781, true);
                     obj131 = new Animable_Sub5(j1, obj101, 2, i2, j2, l1, k2, class46.anInt781, true);
                  }

                  worldController.method282(anIntArray152[i], (Animable)l41, i3, j, byte1, i1, (Animable)obj131, l2, anIntArray152[obj101], k1);
                  if(class46.aBoolean767) {
                     class11.method211(j, i, i1, k, class46.aBoolean757);
                  }

               } else if(k == 3) {
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     obj10 = class46.method578(3, i, l1, i2, j2, k2, -1);
                  } else {
                     obj10 = new Animable_Sub5(j1, i, 3, i2, j2, l1, k2, class46.anInt781, true);
                  }

                  worldController.method282(anIntArray140[i], (Animable)obj10, i3, j, byte1, i1, (Animable)null, l2, 0, k1);
                  if(class46.aBoolean767) {
                     class11.method211(j, i, i1, k, class46.aBoolean757);
                  }

               } else if(k == 9) {
                  if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                     obj10 = class46.method578(k, i, l1, i2, j2, k2, -1);
                  } else {
                     obj10 = new Animable_Sub5(j1, i, k, i2, j2, l1, k2, class46.anInt781, true);
                  }

                  worldController.method284(i3, byte1, l2, 1, (Animable)obj10, 1, k1, 0, j, i1);
                  if(class46.aBoolean767) {
                     class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, i1, j, i);
                  }

               } else {
                  if(class46.aBoolean762) {
                     if(i == 1) {
                        obj101 = k2;
                        k2 = j2;
                        j2 = i2;
                        i2 = l1;
                        l1 = obj101;
                     } else if(i == 2) {
                        obj101 = k2;
                        k2 = i2;
                        i2 = obj101;
                        obj101 = j2;
                        j2 = l1;
                        l1 = obj101;
                     } else if(i == 3) {
                        obj101 = k2;
                        k2 = l1;
                        l1 = i2;
                        i2 = j2;
                        j2 = obj101;
                     }
                  }

                  if(k == 4) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, l1, i2, j2, k2, -1);
                     } else {
                        obj10 = new Animable_Sub5(j1, 0, 4, i2, j2, l1, k2, class46.anInt781, true);
                     }

                     worldController.method283(i3, j, i * 512, k1, 0, l2, (Animable)obj10, i1, byte1, 0, anIntArray152[i]);
                  } else if(k == 5) {
                     obj101 = 16;
                     l4 = worldController.method300(k1, i1, j);
                     if(l4 > 0) {
                        obj101 = ObjectDef.forID(l4 >> 14 & 32767).anInt775;
                     }

                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj131 = class46.method578(4, 0, l1, i2, j2, k2, -1);
                     } else {
                        obj131 = new Animable_Sub5(j1, 0, 4, i2, j2, l1, k2, class46.anInt781, true);
                     }

                     worldController.method283(i3, j, i * 512, k1, anIntArray137[i] * obj101, l2, (Animable)obj131, i1, byte1, anIntArray144[i] * obj101, anIntArray152[i]);
                  } else if(k == 6) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, l1, i2, j2, k2, -1);
                     } else {
                        obj10 = new Animable_Sub5(j1, 0, 4, i2, j2, l1, k2, class46.anInt781, true);
                     }

                     worldController.method283(i3, j, i, k1, 0, l2, (Animable)obj10, i1, byte1, 0, 256);
                  } else if(k == 7) {
                     if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                        obj10 = class46.method578(4, 0, l1, i2, j2, k2, -1);
                     } else {
                        obj10 = new Animable_Sub5(j1, 0, 4, i2, j2, l1, k2, class46.anInt781, true);
                     }

                     worldController.method283(i3, j, i, k1, 0, l2, (Animable)obj10, i1, byte1, 0, 512);
                  } else {
                     if(k == 8) {
                        if(class46.anInt781 == -1 && class46.childrenIDs == null) {
                           obj10 = class46.method578(4, 0, l1, i2, j2, k2, -1);
                        } else {
                           obj10 = new Animable_Sub5(j1, 0, 4, i2, j2, l1, k2, class46.anInt781, true);
                        }

                        worldController.method283(i3, j, i, k1, 0, l2, (Animable)obj10, i1, byte1, 0, 768);
                     }

                  }
               }
            }
         } else {
            if(class46.anInt781 == -1 && class46.childrenIDs == null) {
               obj10 = class46.method578(10, i, l1, i2, j2, k2, -1);
            } else {
               obj10 = new Animable_Sub5(j1, i, 10, i2, j2, l1, k2, class46.anInt781, true);
            }

            if(obj10 != null) {
               l4 = 0;
               if(k == 11) {
                  l4 += 256;
               }

               int i5;
               int obj13;
               if(i != 1 && i != 3) {
                  obj13 = class46.anInt744;
                  i5 = class46.anInt761;
               } else {
                  obj13 = class46.anInt761;
                  i5 = class46.anInt744;
               }

               worldController.method284(i3, byte1, l2, i5, (Animable)obj10, obj13, k1, l4, j, i1);
            }

            if(class46.aBoolean767) {
               class11.method212(class46.aBoolean757, class46.anInt744, class46.anInt761, i1, j, i);
            }

         }
      }
   }

   public static boolean method189(int i, byte[] is, int i_250_) {
      boolean bool = true;
      Stream stream = new Stream(is);
      int i_252_ = -1;

      while(true) {
         int i_253_ = stream.method422();
         if(i_253_ == 0) {
            return bool;
         }

         i_252_ += i_253_;
         int i_254_ = 0;
         boolean bool_255_ = false;

         while(true) {
            int i_257_;
            if(bool_255_) {
               i_257_ = stream.method422();
               if(i_257_ == 0) {
                  break;
               }

               stream.readUnsignedByte();
            } else {
               i_257_ = stream.method422();
               if(i_257_ == 0) {
                  break;
               }

               i_254_ += i_257_ - 1;
               int i_258_ = i_254_ & 63;
               int i_259_ = i_254_ >> 6 & 63;
               int i_260_ = stream.readUnsignedByte() >> 2;
               int i_261_ = i_259_ + i;
               int i_262_ = i_258_ + i_250_;
               if(i_261_ > 0 && i_262_ > 0 && i_261_ < 103 && i_262_ < 103) {
                  ObjectDef class46 = ObjectDef.forID(i_252_);
                  if(i_260_ != 22 || !lowMem || class46.hasActions || class46.aBoolean736) {
                     bool &= class46.method579();
                     bool_255_ = true;
                  }
               }
            }
         }
      }
   }

   public final void method190(int i, CollisionMap[] aclass11, int j, WorldController worldController, byte[] abyte0) {
      Stream stream = new Stream(abyte0);
      int l = -1;

      while(true) {
         int i1 = stream.method422();
         if(i1 == 0) {
            return;
         }

         l += i1;
         int j1 = 0;

         while(true) {
            int k1 = stream.method422();
            if(k1 == 0) {
               break;
            }

            j1 += k1 - 1;
            int l1 = j1 & 63;
            int i2 = j1 >> 6 & 63;
            int j2 = j1 >> 12;
            int k2 = stream.readUnsignedByte();
            int l2 = k2 >> 2;
            int i3 = k2 & 3;
            int j3 = i2 + i;
            int k3 = l1 + j;
            if(j3 > 0 && k3 > 0 && j3 < 103 && k3 < 103) {
               int l3 = j2;
               if((this.aByteArrayArrayArray149[1][j3][k3] & 2) == 2) {
                  l3 = j2 - 1;
               }

               CollisionMap class11 = null;
               if(l3 >= 0) {
                  class11 = aclass11[l3];
               }

               this.method175(k3, worldController, class11, l2, j2, j3, l, i3);
            }
         }
      }
   }

}
