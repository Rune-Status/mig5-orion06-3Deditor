package client;


final class Texture extends DrawingArea {
	
	public final static int TEXTURE_COUNT = 51;

   public static final int anInt1459 = -477;
   public static boolean lowMem = true;
   static boolean aBoolean1462;
   private static boolean aBoolean1463;
   public static boolean aBoolean1464 = true;
   public static int anInt1465;
   public static int textureInt1;
   public static int textureInt2;
   private static int[] anIntArray1468 = new int[512];
   public static final int[] anIntArray1469 = new int[2048];
   public static int[] anIntArray1470 = new int[2048];
   public static int[] anIntArray1471 = new int[2048];
   public static int[] anIntArray1472;
   private static int anInt1473;
   public static Background[] aBackgroundArray1474s = new Background[TEXTURE_COUNT];
   private static boolean[] aBooleanArray1475 = new boolean[TEXTURE_COUNT];
   private static int[] anIntArray1476 = new int[TEXTURE_COUNT];
   private static int anInt1477;
   private static int[][] anIntArrayArray1478;
   private static int[][] anIntArrayArray1479 = new int[TEXTURE_COUNT][];
   public static int[] anIntArray1480 = new int[TEXTURE_COUNT];
   public static int anInt1481;
   public static int[] anIntArray1482 = new int[65536];
   private static int[][] anIntArrayArray1483 = new int[TEXTURE_COUNT][];

   public static void nullLoader() {
      anIntArray1468 = null;
      anIntArray1468 = null;
      anIntArray1470 = null;
      anIntArray1471 = null;
      anIntArray1472 = null;
      aBackgroundArray1474s = null;
      aBooleanArray1475 = null;
      anIntArray1476 = null;
      anIntArrayArray1478 = (int[][])null;
      anIntArrayArray1479 = (int[][])null;
      anIntArray1480 = null;
      anIntArray1482 = null;
      anIntArrayArray1483 = (int[][])null;
   }

   public static void method364() {
      anIntArray1472 = new int[DrawingArea.height];

      for(int j = 0; j < DrawingArea.height; ++j) {
         anIntArray1472[j] = DrawingArea.width * j;
      }

      textureInt1 = DrawingArea.width / 2;
      textureInt2 = DrawingArea.height / 2;
   }

   public static void method365(int j, int k) {
      anIntArray1472 = new int[k];

      for(int l = 0; l < k; ++l) {
         anIntArray1472[l] = j * l;
      }

      textureInt1 = j / 2;
      textureInt2 = k / 2;
   }

   public static void method366() {
      anIntArrayArray1478 = (int[][])null;

      for(int j = 0; j < TEXTURE_COUNT; ++j) {
         anIntArrayArray1479[j] = null;
      }

   }

   public static void method367() {
      if(anIntArrayArray1478 == null) {
         anInt1477 = 20;
         if(lowMem) {
            anIntArrayArray1478 = new int[anInt1477][16384];
         } else {
            anIntArrayArray1478 = new int[anInt1477][65536];
         }

         for(int k = 0; k < TEXTURE_COUNT; ++k) {
            anIntArrayArray1479[k] = null;
         }
      }

   }

   public static void method368(StreamLoader streamLoader) {
      anInt1473 = 0;

      for(int j = 0; j < TEXTURE_COUNT; ++j) {
         try {
            aBackgroundArray1474s[j] = new Background(streamLoader, String.valueOf(j), 0);
            if(lowMem && aBackgroundArray1474s[j].anInt1456 == 128) {
               aBackgroundArray1474s[j].method356();
            } else {
               aBackgroundArray1474s[j].method357();
            }

            ++anInt1473;
         } catch (Exception var3) {
            ;
         }
      }

   }

   public static int method369(int i) {
      if(anIntArray1476[i] != 0) {
         return anIntArray1476[i];
      } else {
         int k = 0;
         int l = 0;
         int i1 = 0;
         int j1 = anIntArrayArray1483[i].length;

         int l1;
         for(l1 = 0; l1 < j1; ++l1) {
            k += anIntArrayArray1483[i][l1] >> 16 & 255;
            l += anIntArrayArray1483[i][l1] >> 8 & 255;
            i1 += anIntArrayArray1483[i][l1] & 255;
         }

         l1 = (k / j1 << 16) + (l / j1 << 8) + i1 / j1;
         l1 = method373(l1, 1.4D);
         if(l1 == 0) {
            l1 = 1;
         }

         anIntArray1476[i] = l1;
         return l1;
      }
   }

   public static void method370(int i) {
      if(anIntArrayArray1479[i] != null) {
         anIntArrayArray1478[anInt1477++] = anIntArrayArray1479[i];
         anIntArrayArray1479[i] = null;
      }
   }

   private static int[] method371(int i) {
      anIntArray1480[i] = anInt1481++;
      if(anIntArrayArray1479[i] != null) {
         return anIntArrayArray1479[i];
      } else {
         int[] ai;
         int l1;
         if(anInt1477 > 0) {
            ai = anIntArrayArray1478[--anInt1477];
            anIntArrayArray1478[anInt1477] = null;
         } else {
            int background = 0;
            int ai1 = -1;

            for(l1 = 0; l1 < anInt1473; ++l1) {
               if(anIntArrayArray1479[l1] != null && (anIntArray1480[l1] < background || ai1 == -1)) {
                  background = anIntArray1480[l1];
                  ai1 = l1;
               }
            }

            ai = anIntArrayArray1479[ai1];
            anIntArrayArray1479[ai1] = null;
         }

         anIntArrayArray1479[i] = ai;
         Background var6 = aBackgroundArray1474s[i];
         int[] var7 = anIntArrayArray1483[i];
         int k2;
         if(lowMem) {
            aBooleanArray1475[i] = false;

            for(l1 = 0; l1 < 4096; ++l1) {
               k2 = ai[l1] = var7[var6.aByteArray1450[l1]] & 16316671;
               if(k2 == 0) {
                  aBooleanArray1475[i] = true;
               }

               ai[4096 + l1] = k2 - (k2 >>> 3) & 16316671;
               ai[8192 + l1] = k2 - (k2 >>> 2) & 16316671;
               ai[12288 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 16316671;
            }
         } else {
            if(var6.anInt1452 == 64) {
               for(l1 = 0; l1 < 128; ++l1) {
                  for(k2 = 0; k2 < 128; ++k2) {
                     ai[k2 + (l1 << 7)] = var7[var6.aByteArray1450[(k2 >> 1) + (l1 >> 1 << 6)]];
                  }
               }
            } else {
               for(l1 = 0; l1 < 16384; ++l1) {
                  ai[l1] = var7[var6.aByteArray1450[l1]];
               }
            }

            aBooleanArray1475[i] = false;

            for(l1 = 0; l1 < 16384; ++l1) {
               ai[l1] &= 16316671;
               k2 = ai[l1];
               if(k2 == 0) {
                  aBooleanArray1475[i] = true;
               }

               ai[16384 + l1] = k2 - (k2 >>> 3) & 16316671;
               ai['\u8000' + l1] = k2 - (k2 >>> 2) & 16316671;
               ai['\uc000' + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 16316671;
            }
         }

         return ai;
      }
   }

   public static void method372(double d) {
      d += Math.random() * 0.03D - 0.015D;
      int j = 0;

      int i1;
      for(i1 = 0; i1 < 512; ++i1) {
         double ai = (double)(i1 / 8) / 64.0D + 0.0078125D;
         double d2 = (double)(i1 & 7) / 8.0D + 0.0625D;

         for(int k1 = 0; k1 < 128; ++k1) {
            double d3 = (double)k1 / 128.0D;
            double d4 = d3;
            double d5 = d3;
            double d6 = d3;
            if(d2 != 0.0D) {
               double l1;
               if(d3 < 0.5D) {
                  l1 = d3 * (1.0D + d2);
               } else {
                  l1 = d3 + d2 - d3 * d2;
               }

               double j2 = 2.0D * d3 - l1;
               double d9 = ai + 0.3333333333333333D;
               if(d9 > 1.0D) {
                  --d9;
               }

               double d11 = ai - 0.3333333333333333D;
               if(d11 < 0.0D) {
                  ++d11;
               }

               if(6.0D * d9 < 1.0D) {
                  d4 = j2 + (l1 - j2) * 6.0D * d9;
               } else if(2.0D * d9 < 1.0D) {
                  d4 = l1;
               } else if(3.0D * d9 < 2.0D) {
                  d4 = j2 + (l1 - j2) * (0.6666666666666666D - d9) * 6.0D;
               } else {
                  d4 = j2;
               }

               if(6.0D * ai < 1.0D) {
                  d5 = j2 + (l1 - j2) * 6.0D * ai;
               } else if(2.0D * ai < 1.0D) {
                  d5 = l1;
               } else if(3.0D * ai < 2.0D) {
                  d5 = j2 + (l1 - j2) * (0.6666666666666666D - ai) * 6.0D;
               } else {
                  d5 = j2;
               }

               if(6.0D * d11 < 1.0D) {
                  d6 = j2 + (l1 - j2) * 6.0D * d11;
               } else if(2.0D * d11 < 1.0D) {
                  d6 = l1;
               } else if(3.0D * d11 < 2.0D) {
                  d6 = j2 + (l1 - j2) * (0.6666666666666666D - d11) * 6.0D;
               } else {
                  d6 = j2;
               }
            }

            int var28 = (int)(d4 * 256.0D);
            int i2 = (int)(d5 * 256.0D);
            int var29 = (int)(d6 * 256.0D);
            int k2 = (var28 << 16) + (i2 << 8) + var29;
            k2 = method373(k2, d);
            if(k2 == 0) {
               k2 = 1;
            }

            anIntArray1482[j++] = k2;
         }
      }

      for(i1 = 0; i1 < TEXTURE_COUNT; ++i1) {
         if(aBackgroundArray1474s[i1] != null) {
            int[] var27 = aBackgroundArray1474s[i1].anIntArray1451;
            anIntArrayArray1483[i1] = new int[var27.length];

            for(int j1 = 0; j1 < var27.length; ++j1) {
               anIntArrayArray1483[i1][j1] = method373(var27[j1], d);
               if((anIntArrayArray1483[i1][j1] & 16316671) == 0 && j1 != 0) {
                  anIntArrayArray1483[i1][j1] = 1;
               }
            }
         }
      }

      for(i1 = 0; i1 < TEXTURE_COUNT; ++i1) {
         method370(i1);
      }

   }

   private static int method373(int i, double d) {
      double d1 = (double)(i >> 16) / 256.0D;
      double d2 = (double)(i >> 8 & 255) / 256.0D;
      double d3 = (double)(i & 255) / 256.0D;
      d1 = Math.pow(d1, d);
      d2 = Math.pow(d2, d);
      d3 = Math.pow(d3, d);
      int j = (int)(d1 * 256.0D);
      int k = (int)(d2 * 256.0D);
      int l = (int)(d3 * 256.0D);
      return (j << 16) + (k << 8) + l;
   }

   public static void method374(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
      int j2 = 0;
      int k2 = 0;
      if(j != i) {
         j2 = (i1 - l << 16) / (j - i);
         k2 = (l1 - k1 << 15) / (j - i);
      }

      int l2 = 0;
      int i3 = 0;
      if(k != j) {
         l2 = (j1 - i1 << 16) / (k - j);
         i3 = (i2 - l1 << 15) / (k - j);
      }

      int j3 = 0;
      int k3 = 0;
      if(k != i) {
         j3 = (l - j1 << 16) / (i - k);
         k3 = (k1 - i2 << 15) / (i - k);
      }

      if(i <= j && i <= k) {
         if(i < DrawingArea.bottomY) {
            if(j > DrawingArea.bottomY) {
               j = DrawingArea.bottomY;
            }

            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(j < k) {
               j1 = l <<= 16;
               i2 = k1 <<= 15;
               if(i < 0) {
                  j1 -= j3 * i;
                  l -= j2 * i;
                  i2 -= k3 * i;
                  k1 -= k2 * i;
                  i = 0;
               }

               i1 <<= 16;
               l1 <<= 15;
               if(j < 0) {
                  i1 -= l2 * j;
                  l1 -= i3 * j;
                  j = 0;
               }

               if((i == j || j3 >= j2) && (i != j || j3 <= l2)) {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, i, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                           j1 += j3;
                           i1 += l2;
                           i2 += k3;
                           l1 += i3;
                           i += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                     j1 += j3;
                     l += j2;
                     i2 += k3;
                     k1 += k2;
                     i += DrawingArea.width;
                  }
               } else {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, i, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                           j1 += j3;
                           i1 += l2;
                           i2 += k3;
                           l1 += i3;
                           i += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                     j1 += j3;
                     l += j2;
                     i2 += k3;
                     k1 += k2;
                     i += DrawingArea.width;
                  }
               }
            } else {
               i1 = l <<= 16;
               l1 = k1 <<= 15;
               if(i < 0) {
                  i1 -= j3 * i;
                  l -= j2 * i;
                  l1 -= k3 * i;
                  k1 -= k2 * i;
                  i = 0;
               }

               j1 <<= 16;
               i2 <<= 15;
               if(k < 0) {
                  j1 -= l2 * k;
                  i2 -= i3 * k;
                  k = 0;
               }

               if((i == k || j3 >= j2) && (i != k || l2 <= j2)) {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                           j1 += l2;
                           l += j2;
                           i2 += i3;
                           k1 += k2;
                           i += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, i, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                     i1 += j3;
                     l += j2;
                     l1 += k3;
                     k1 += k2;
                     i += DrawingArea.width;
                  }
               } else {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                           j1 += l2;
                           l += j2;
                           i2 += i3;
                           k1 += k2;
                           i += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, i, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                     i1 += j3;
                     l += j2;
                     l1 += k3;
                     k1 += k2;
                     i += DrawingArea.width;
                  }
               }
            }
         }
      } else if(j <= k) {
         if(j < DrawingArea.bottomY) {
            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(i > DrawingArea.bottomY) {
               i = DrawingArea.bottomY;
            }

            if(k < i) {
               l = i1 <<= 16;
               k1 = l1 <<= 15;
               if(j < 0) {
                  l -= j2 * j;
                  i1 -= l2 * j;
                  k1 -= k2 * j;
                  l1 -= i3 * j;
                  j = 0;
               }

               j1 <<= 16;
               i2 <<= 15;
               if(k < 0) {
                  j1 -= j3 * k;
                  i2 -= k3 * k;
                  k = 0;
               }

               if((j == k || j2 >= l2) && (j != k || j2 <= j3)) {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, j, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                           l += j2;
                           j1 += j3;
                           k1 += k2;
                           i2 += k3;
                           j += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                     l += j2;
                     i1 += l2;
                     k1 += k2;
                     l1 += i3;
                     j += DrawingArea.width;
                  }
               } else {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, j, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                           l += j2;
                           j1 += j3;
                           k1 += k2;
                           i2 += k3;
                           j += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                     l += j2;
                     i1 += l2;
                     k1 += k2;
                     l1 += i3;
                     j += DrawingArea.width;
                  }
               }
            } else {
               j1 = i1 <<= 16;
               i2 = l1 <<= 15;
               if(j < 0) {
                  j1 -= j2 * j;
                  i1 -= l2 * j;
                  i2 -= k2 * j;
                  l1 -= i3 * j;
                  j = 0;
               }

               l <<= 16;
               k1 <<= 15;
               if(i < 0) {
                  l -= j3 * i;
                  k1 -= k3 * i;
                  i = 0;
               }

               if(j2 < l2) {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                           l += j3;
                           i1 += l2;
                           k1 += k3;
                           l1 += i3;
                           j += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, j, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                     j1 += j2;
                     i1 += l2;
                     i2 += k2;
                     l1 += i3;
                     j += DrawingArea.width;
                  }
               } else {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                           l += j3;
                           i1 += l2;
                           k1 += k3;
                           l1 += i3;
                           j += DrawingArea.width;
                        }
                     }

                     method375(DrawingArea.pixels, j, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                     j1 += j2;
                     i1 += l2;
                     i2 += k2;
                     l1 += i3;
                     j += DrawingArea.width;
                  }
               }
            }
         }
      } else if(k < DrawingArea.bottomY) {
         if(i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
         }

         if(j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
         }

         if(i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 15;
            if(k < 0) {
               i1 -= l2 * k;
               j1 -= j3 * k;
               l1 -= i3 * k;
               i2 -= k3 * k;
               k = 0;
            }

            l <<= 16;
            k1 <<= 15;
            if(i < 0) {
               l -= j2 * i;
               k1 -= k2 * i;
               i = 0;
            }

            if(l2 < j3) {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method375(DrawingArea.pixels, k, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                        i1 += l2;
                        l += j2;
                        l1 += i3;
                        k1 += k2;
                        k += DrawingArea.width;
                     }
                  }

                  method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                  i1 += l2;
                  j1 += j3;
                  l1 += i3;
                  i2 += k3;
                  k += DrawingArea.width;
               }
            } else {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method375(DrawingArea.pixels, k, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                        i1 += l2;
                        l += j2;
                        l1 += i3;
                        k1 += k2;
                        k += DrawingArea.width;
                     }
                  }

                  method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                  i1 += l2;
                  j1 += j3;
                  l1 += i3;
                  i2 += k3;
                  k += DrawingArea.width;
               }
            }
         } else {
            l = j1 <<= 16;
            k1 = i2 <<= 15;
            if(k < 0) {
               l -= l2 * k;
               j1 -= j3 * k;
               k1 -= i3 * k;
               i2 -= k3 * k;
               k = 0;
            }

            i1 <<= 16;
            l1 <<= 15;
            if(j < 0) {
               i1 -= j2 * j;
               l1 -= k2 * j;
               j = 0;
            }

            if(l2 < j3) {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                        i1 += j2;
                        j1 += j3;
                        l1 += k2;
                        i2 += k3;
                        k += DrawingArea.width;
                     }
                  }

                  method375(DrawingArea.pixels, k, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                  l += l2;
                  j1 += j3;
                  k1 += i3;
                  i2 += k3;
                  k += DrawingArea.width;
               }
            } else {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                        i1 += j2;
                        j1 += j3;
                        l1 += k2;
                        i2 += k3;
                        k += DrawingArea.width;
                     }
                  }

                  method375(DrawingArea.pixels, k, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                  l += l2;
                  j1 += j3;
                  k1 += i3;
                  i2 += k3;
                  k += DrawingArea.width;
               }
            }
         }
      }
   }
   
   public static void method375(int[] ai, int i, int l, int i1, int j1, int k1) {
		int j;
		int k;
		int l1 = 0;
		if (aBoolean1462) {
			if (i1 > DrawingArea.centerX)
				i1 = DrawingArea.centerX;
			if (l < 0) {
				j1 -= l * l1;
				l = 0;
			}
		}
		if (l < i1) {
			i += l;
			j1 += l1 * l;
			if (aBoolean1464) {
				k = i1 - l >> 2;
				if (k > 0)
					l1 = (k1 - j1) * anIntArray1468[k] >> 15;
				else
					l1 = 0;
				if (anInt1465 == 0) {
					if (k > 0) {
						do {
							j = anIntArray1482[j1 >> 8];
							j1 += l1;
							ai[i++] = j;
							ai[i++] = j;
							ai[i++] = j;
							ai[i++] = j;
						} while (--k > 0);
					}
					k = i1 - l & 0x3;
					if (k > 0) {
						j = anIntArray1482[j1 >> 8];
						do
							ai[i++] = j;
						while (--k > 0);
					}
				} else {
					int j2 = anInt1465;
					int l2 = 256 - anInt1465;
					if (k > 0) {
						do {
							j = anIntArray1482[j1 >> 8];
							j1 += l1;
							j = (((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00)
									* l2 >> 8 & 0xff00));
							int h = ai[i];
							ai[i++] = (j
									+ ((h & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((h & 0xff00)
									* j2 >> 8 & 0xff00));
							h = ai[i];
							ai[i++] = (j
									+ ((h & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((h & 0xff00)
									* j2 >> 8 & 0xff00));
							h = ai[i];
							ai[i++] = (j
									+ ((h & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((h & 0xff00)
									* j2 >> 8 & 0xff00));
							h = ai[i];
							ai[i++] = (j
									+ ((h & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((h & 0xff00)
									* j2 >> 8 & 0xff00));
						} while (--k > 0);
					}
					k = i1 - l & 0x3;
					if (k > 0) {
						j = anIntArray1482[j1 >> 8];
						j = (((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00)
								* l2 >> 8 & 0xff00));
						do {
							int i_61_ = ai[i];
							ai[i++] = (j
									+ ((i_61_ & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((i_61_ & 0xff00)
									* j2 >> 8 & 0xff00));
						} while (--k > 0);
					}
				}
			} else {
				int i2 = (k1 - j1) / (i1 - l);
				k = i1 - l;
				if (anInt1465 == 0) {
					do {
						ai[i++] = anIntArray1482[j1 >> 8];
						j1 += i2;
					} while (--k > 0);
				} else {
					int i_62_ = anInt1465;
					int i_63_ = 256 - anInt1465;
					do {
						j = anIntArray1482[j1 >> 8];
						j1 += i2;
						j = (((j & 0xff00ff) * i_63_ >> 8 & 0xff00ff) + ((j & 0xff00)
								* i_63_ >> 8 & 0xff00));
						int i_64_ = ai[i];
						ai[i++] = (j
								+ ((i_64_ & 0xff00ff) * i_62_ >> 8 & 0xff00ff) + ((i_64_ & 0xff00)
								* i_62_ >> 8 & 0xff00));
					} while (--k > 0);
				}
			}
		}
	}

   /*private static void method375(int[] ai, int i, int l, int i1, int j1, int k1) {
      int j;
      int k;
      int i2;
      int k2;
      int i3;
      if(aBoolean1464) {
         if(aBoolean1462) {
            if(i1 - l > 3) {
               i2 = (k1 - j1) / (i1 - l);
            } else {
               i2 = 0;
            }

            if(i1 > DrawingArea.centerX) {
               i1 = DrawingArea.centerX;
            }

            if(l < 0) {
               j1 -= l * i2;
               l = 0;
            }

            if(l >= i1) {
               return;
            }

            i += l;
            k = i1 - l >> 2;
            i2 <<= 2;
         } else {
            if(l >= i1) {
               return;
            }

            i += l;
            k = i1 - l >> 2;
            if(k > 0) {
               i2 = (k1 - j1) * anIntArray1468[k] >> 15;
            } else {
               i2 = 0;
            }
         }

         if(anInt1465 == 0) {
            while(true) {
               --k;
               if(k < 0) {
                  k = i1 - l & 3;
                  if(k > 0) {
                     j = anIntArray1482[j1 >> 8];

                     do {
                        ai[i++] = j;
                        --k;
                     } while(k > 0);

                     return;
                  }
                  break;
               }

               j = anIntArray1482[j1 >> 8];
               j1 += i2;
               ai[i++] = j;
               ai[i++] = j;
               ai[i++] = j;
               ai[i++] = j;
            }
         } else {
            k2 = anInt1465;
            i3 = 256 - anInt1465;

            while(true) {
               --k;
               if(k < 0) {
                  k = i1 - l & 3;
                  if(k > 0) {
                     j = anIntArray1482[j1 >> 8];
                     j = ((j & 16711935) * i3 >> 8 & 16711935) + ((j & '\uff00') * i3 >> 8 & '\uff00');

                     do {
                        ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
                        --k;
                     } while(k > 0);
                  }
                  break;
               }

               j = anIntArray1482[j1 >> 8];
               j1 += i2;
               j = ((j & 16711935) * i3 >> 8 & 16711935) + ((j & '\uff00') * i3 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
            }
         }

      } else if(l < i1) {
         i2 = (k1 - j1) / (i1 - l);
         if(aBoolean1462) {
            if(i1 > DrawingArea.centerX) {
               i1 = DrawingArea.centerX;
            }

            if(l < 0) {
               j1 -= l * i2;
               l = 0;
            }

            if(l >= i1) {
               return;
            }
         }

         i += l;
         k = i1 - l;
         if(anInt1465 == 0) {
            do {
               ai[i++] = anIntArray1482[j1 >> 8];
               j1 += i2;
               --k;
            } while(k > 0);

         } else {
            k2 = anInt1465;
            i3 = 256 - anInt1465;

            do {
               j = anIntArray1482[j1 >> 8];
               j1 += i2;
               j = ((j & 16711935) * i3 >> 8 & 16711935) + ((j & '\uff00') * i3 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * k2 >> 8 & 16711935) + ((ai[i] & '\uff00') * k2 >> 8 & '\uff00');
               --k;
            } while(k > 0);

         }
      }
   }*/

   public static void method376(int i, int j, int k, int l, int i1, int j1, int k1) {
      int l1 = 0;
      if(j != i) {
         l1 = (i1 - l << 16) / (j - i);
      }

      int i2 = 0;
      if(k != j) {
         i2 = (j1 - i1 << 16) / (k - j);
      }

      int j2 = 0;
      if(k != i) {
         j2 = (l - j1 << 16) / (i - k);
      }

      if(i <= j && i <= k) {
         if(i < DrawingArea.bottomY) {
            if(j > DrawingArea.bottomY) {
               j = DrawingArea.bottomY;
            }

            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(j < k) {
               j1 = l <<= 16;
               if(i < 0) {
                  j1 -= j2 * i;
                  l -= l1 * i;
                  i = 0;
               }

               i1 <<= 16;
               if(j < 0) {
                  i1 -= i2 * j;
                  j = 0;
               }

               if((i == j || j2 >= l1) && (i != j || j2 <= i2)) {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, i, k1, i1 >> 16, j1 >> 16);
                           j1 += j2;
                           i1 += i2;
                           i += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
                     j1 += j2;
                     l += l1;
                     i += DrawingArea.width;
                  }
               } else {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, i, k1, j1 >> 16, i1 >> 16);
                           j1 += j2;
                           i1 += i2;
                           i += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
                     j1 += j2;
                     l += l1;
                     i += DrawingArea.width;
                  }
               }
            } else {
               i1 = l <<= 16;
               if(i < 0) {
                  i1 -= j2 * i;
                  l -= l1 * i;
                  i = 0;
               }

               j1 <<= 16;
               if(k < 0) {
                  j1 -= i2 * k;
                  k = 0;
               }

               if((i == k || j2 >= l1) && (i != k || i2 <= l1)) {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
                           j1 += i2;
                           l += l1;
                           i += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, i, k1, l >> 16, i1 >> 16);
                     i1 += j2;
                     l += l1;
                     i += DrawingArea.width;
                  }
               } else {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
                           j1 += i2;
                           l += l1;
                           i += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, i, k1, i1 >> 16, l >> 16);
                     i1 += j2;
                     l += l1;
                     i += DrawingArea.width;
                  }
               }
            }
         }
      } else if(j <= k) {
         if(j < DrawingArea.bottomY) {
            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(i > DrawingArea.bottomY) {
               i = DrawingArea.bottomY;
            }

            if(k < i) {
               l = i1 <<= 16;
               if(j < 0) {
                  l -= l1 * j;
                  i1 -= i2 * j;
                  j = 0;
               }

               j1 <<= 16;
               if(k < 0) {
                  j1 -= j2 * k;
                  k = 0;
               }

               if((j == k || l1 >= i2) && (j != k || l1 <= j2)) {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, j, k1, j1 >> 16, l >> 16);
                           l += l1;
                           j1 += j2;
                           j += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
                     l += l1;
                     i1 += i2;
                     j += DrawingArea.width;
                  }
               } else {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, j, k1, l >> 16, j1 >> 16);
                           l += l1;
                           j1 += j2;
                           j += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
                     l += l1;
                     i1 += i2;
                     j += DrawingArea.width;
                  }
               }
            } else {
               j1 = i1 <<= 16;
               if(j < 0) {
                  j1 -= l1 * j;
                  i1 -= i2 * j;
                  j = 0;
               }

               l <<= 16;
               if(i < 0) {
                  l -= j2 * i;
                  i = 0;
               }

               if(l1 < i2) {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
                           l += j2;
                           i1 += i2;
                           j += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, j, k1, j1 >> 16, i1 >> 16);
                     j1 += l1;
                     i1 += i2;
                     j += DrawingArea.width;
                  }
               } else {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
                           l += j2;
                           i1 += i2;
                           j += DrawingArea.width;
                        }
                     }

                     method377(DrawingArea.pixels, j, k1, i1 >> 16, j1 >> 16);
                     j1 += l1;
                     i1 += i2;
                     j += DrawingArea.width;
                  }
               }
            }
         }
      } else if(k < DrawingArea.bottomY) {
         if(i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
         }

         if(j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
         }

         if(i < j) {
            i1 = j1 <<= 16;
            if(k < 0) {
               i1 -= i2 * k;
               j1 -= j2 * k;
               k = 0;
            }

            l <<= 16;
            if(i < 0) {
               l -= l1 * i;
               i = 0;
            }

            if(i2 < j2) {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method377(DrawingArea.pixels, k, k1, i1 >> 16, l >> 16);
                        i1 += i2;
                        l += l1;
                        k += DrawingArea.width;
                     }
                  }

                  method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
                  i1 += i2;
                  j1 += j2;
                  k += DrawingArea.width;
               }
            } else {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method377(DrawingArea.pixels, k, k1, l >> 16, i1 >> 16);
                        i1 += i2;
                        l += l1;
                        k += DrawingArea.width;
                     }
                  }

                  method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
                  i1 += i2;
                  j1 += j2;
                  k += DrawingArea.width;
               }
            }
         } else {
            l = j1 <<= 16;
            if(k < 0) {
               l -= i2 * k;
               j1 -= j2 * k;
               k = 0;
            }

            i1 <<= 16;
            if(j < 0) {
               i1 -= l1 * j;
               j = 0;
            }

            if(i2 < j2) {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
                        i1 += l1;
                        j1 += j2;
                        k += DrawingArea.width;
                     }
                  }

                  method377(DrawingArea.pixels, k, k1, l >> 16, j1 >> 16);
                  l += i2;
                  j1 += j2;
                  k += DrawingArea.width;
               }
            } else {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
                        i1 += l1;
                        j1 += j2;
                        k += DrawingArea.width;
                     }
                  }

                  method377(DrawingArea.pixels, k, k1, j1 >> 16, l >> 16);
                  l += i2;
                  j1 += j2;
                  k += DrawingArea.width;
               }
            }
         }
      }
   }

   private static void method377(int[] ai, int i, int j, int l, int i1) {
      if(aBoolean1462) {
         if(i1 > DrawingArea.centerX) {
            i1 = DrawingArea.centerX;
         }

         if(l < 0) {
            l = 0;
         }
      }

      if(l < i1) {
         i += l;
         int k = i1 - l >> 2;
         if(anInt1465 == 0) {
            while(true) {
               --k;
               if(k < 0) {
                  k = i1 - l & 3;

                  while(true) {
                     --k;
                     if(k < 0) {
                        return;
                     }

                     ai[i++] = j;
                  }
               }

               ai[i++] = j;
               ai[i++] = j;
               ai[i++] = j;
               ai[i++] = j;
            }
         } else {
            int j1 = anInt1465;
            int k1 = 256 - anInt1465;
            j = ((j & 16711935) * k1 >> 8 & 16711935) + ((j & '\uff00') * k1 >> 8 & '\uff00');

            while(true) {
               --k;
               if(k < 0) {
                  k = i1 - l & 3;

                  while(true) {
                     --k;
                     if(k < 0) {
                        return;
                     }

                     ai[i++] = j + ((ai[i] & 16711935) * j1 >> 8 & 16711935) + ((ai[i] & '\uff00') * j1 >> 8 & '\uff00');
                  }
               }

               ai[i++] = j + ((ai[i] & 16711935) * j1 >> 8 & 16711935) + ((ai[i] & '\uff00') * j1 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * j1 >> 8 & 16711935) + ((ai[i] & '\uff00') * j1 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * j1 >> 8 & 16711935) + ((ai[i] & '\uff00') * j1 >> 8 & '\uff00');
               ai[i++] = j + ((ai[i] & 16711935) * j1 >> 8 & 16711935) + ((ai[i] & '\uff00') * j1 >> 8 & '\uff00');
            }
         }
      }
   }

   public static void method378(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2, int i3, int j3, int k3, int l3, int i4, int j4, int k4) {
      int[] ai = method371(k4);
      aBoolean1463 = !aBooleanArray1475[k4];
      k2 = j2 - k2;
      j3 = i3 - j3;
      i4 = l3 - i4;
      l2 -= j2;
      k3 -= i3;
      j4 -= l3;
      int l4 = l2 * i3 - k3 * j2 << 14;
      int i5 = k3 * l3 - j4 * i3 << 8;
      int j5 = j4 * j2 - l2 * l3 << 5;
      int k5 = k2 * i3 - j3 * j2 << 14;
      int l5 = j3 * l3 - i4 * i3 << 8;
      int i6 = i4 * j2 - k2 * l3 << 5;
      int j6 = j3 * l2 - k2 * k3 << 14;
      int k6 = i4 * k3 - j3 * j4 << 8;
      int l6 = k2 * j4 - i4 * l2 << 5;
      int i7 = 0;
      int j7 = 0;
      if(j != i) {
         i7 = (i1 - l << 16) / (j - i);
         j7 = (l1 - k1 << 16) / (j - i);
      }

      int k7 = 0;
      int l7 = 0;
      if(k != j) {
         k7 = (j1 - i1 << 16) / (k - j);
         l7 = (i2 - l1 << 16) / (k - j);
      }

      int i8 = 0;
      int j8 = 0;
      if(k != i) {
         i8 = (l - j1 << 16) / (i - k);
         j8 = (k1 - i2 << 16) / (i - k);
      }

      int l9;
      if(i <= j && i <= k) {
         if(i < DrawingArea.bottomY) {
            if(j > DrawingArea.bottomY) {
               j = DrawingArea.bottomY;
            }

            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(j < k) {
               j1 = l <<= 16;
               i2 = k1 <<= 16;
               if(i < 0) {
                  j1 -= i8 * i;
                  l -= i7 * i;
                  i2 -= j8 * i;
                  k1 -= j7 * i;
                  i = 0;
               }

               i1 <<= 16;
               l1 <<= 16;
               if(j < 0) {
                  i1 -= k7 * j;
                  l1 -= l7 * j;
                  j = 0;
               }

               l9 = i - textureInt2;
               l4 += j5 * l9;
               k5 += i6 * l9;
               j6 += l6 * l9;
               if((i == j || i8 >= i7) && (i != j || i8 <= k7)) {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, i, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                           j1 += i8;
                           i1 += k7;
                           i2 += j8;
                           l1 += l7;
                           i += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                     j1 += i8;
                     l += i7;
                     i2 += j8;
                     k1 += j7;
                     i += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               } else {
                  k -= j;
                  j -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --j;
                     if(j < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, i, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                           j1 += i8;
                           i1 += k7;
                           i2 += j8;
                           l1 += l7;
                           i += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                     j1 += i8;
                     l += i7;
                     i2 += j8;
                     k1 += j7;
                     i += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               }
            } else {
               i1 = l <<= 16;
               l1 = k1 <<= 16;
               if(i < 0) {
                  i1 -= i8 * i;
                  l -= i7 * i;
                  l1 -= j8 * i;
                  k1 -= j7 * i;
                  i = 0;
               }

               j1 <<= 16;
               i2 <<= 16;
               if(k < 0) {
                  j1 -= k7 * k;
                  i2 -= l7 * k;
                  k = 0;
               }

               l9 = i - textureInt2;
               l4 += j5 * l9;
               k5 += i6 * l9;
               j6 += l6 * l9;
               if((i == k || i8 >= i7) && (i != k || k7 <= i7)) {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                           j1 += k7;
                           l += i7;
                           i2 += l7;
                           k1 += j7;
                           i += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, i, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                     i1 += i8;
                     l += i7;
                     l1 += j8;
                     k1 += j7;
                     i += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               } else {
                  j -= k;
                  k -= i;
                  i = anIntArray1472[i];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --j;
                           if(j < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                           j1 += k7;
                           l += i7;
                           i2 += l7;
                           k1 += j7;
                           i += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, i, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                     i1 += i8;
                     l += i7;
                     l1 += j8;
                     k1 += j7;
                     i += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               }
            }
         }
      } else if(j <= k) {
         if(j < DrawingArea.bottomY) {
            if(k > DrawingArea.bottomY) {
               k = DrawingArea.bottomY;
            }

            if(i > DrawingArea.bottomY) {
               i = DrawingArea.bottomY;
            }

            if(k < i) {
               l = i1 <<= 16;
               k1 = l1 <<= 16;
               if(j < 0) {
                  l -= i7 * j;
                  i1 -= k7 * j;
                  k1 -= j7 * j;
                  l1 -= l7 * j;
                  j = 0;
               }

               j1 <<= 16;
               i2 <<= 16;
               if(k < 0) {
                  j1 -= i8 * k;
                  i2 -= j8 * k;
                  k = 0;
               }

               l9 = j - textureInt2;
               l4 += j5 * l9;
               k5 += i6 * l9;
               j6 += l6 * l9;
               if((j == k || i7 >= k7) && (j != k || i7 <= i8)) {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, j, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                           l += i7;
                           j1 += i8;
                           k1 += j7;
                           i2 += j8;
                           j += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                     l += i7;
                     i1 += k7;
                     k1 += j7;
                     l1 += l7;
                     j += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               } else {
                  i -= k;
                  k -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --k;
                     if(k < 0) {
                        while(true) {
                           --i;
                           if(i < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, j, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                           l += i7;
                           j1 += i8;
                           k1 += j7;
                           i2 += j8;
                           j += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                     l += i7;
                     i1 += k7;
                     k1 += j7;
                     l1 += l7;
                     j += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               }
            } else {
               j1 = i1 <<= 16;
               i2 = l1 <<= 16;
               if(j < 0) {
                  j1 -= i7 * j;
                  i1 -= k7 * j;
                  i2 -= j7 * j;
                  l1 -= l7 * j;
                  j = 0;
               }

               l <<= 16;
               k1 <<= 16;
               if(i < 0) {
                  l -= i8 * i;
                  k1 -= j8 * i;
                  i = 0;
               }

               l9 = j - textureInt2;
               l4 += j5 * l9;
               k5 += i6 * l9;
               j6 += l6 * l9;
               if(i7 < k7) {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                           l += i8;
                           i1 += k7;
                           k1 += j8;
                           l1 += l7;
                           j += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, j, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                     j1 += i7;
                     i1 += k7;
                     i2 += j7;
                     l1 += l7;
                     j += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               } else {
                  k -= i;
                  i -= j;
                  j = anIntArray1472[j];

                  while(true) {
                     --i;
                     if(i < 0) {
                        while(true) {
                           --k;
                           if(k < 0) {
                              return;
                           }

                           method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                           l += i8;
                           i1 += k7;
                           k1 += j8;
                           l1 += l7;
                           j += DrawingArea.width;
                           l4 += j5;
                           k5 += i6;
                           j6 += l6;
                        }
                     }

                     method379(DrawingArea.pixels, ai, j, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                     j1 += i7;
                     i1 += k7;
                     i2 += j7;
                     l1 += l7;
                     j += DrawingArea.width;
                     l4 += j5;
                     k5 += i6;
                     j6 += l6;
                  }
               }
            }
         }
      } else if(k < DrawingArea.bottomY) {
         if(i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
         }

         if(j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
         }

         if(i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 16;
            if(k < 0) {
               i1 -= k7 * k;
               j1 -= i8 * k;
               l1 -= l7 * k;
               i2 -= j8 * k;
               k = 0;
            }

            l <<= 16;
            k1 <<= 16;
            if(i < 0) {
               l -= i7 * i;
               k1 -= j7 * i;
               i = 0;
            }

            l9 = k - textureInt2;
            l4 += j5 * l9;
            k5 += i6 * l9;
            j6 += l6 * l9;
            if(k7 < i8) {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method379(DrawingArea.pixels, ai, k, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                        i1 += k7;
                        l += i7;
                        l1 += l7;
                        k1 += j7;
                        k += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                     }
                  }

                  method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                  i1 += k7;
                  j1 += i8;
                  l1 += l7;
                  i2 += j8;
                  k += DrawingArea.width;
                  l4 += j5;
                  k5 += i6;
                  j6 += l6;
               }
            } else {
               j -= i;
               i -= k;
               k = anIntArray1472[k];

               while(true) {
                  --i;
                  if(i < 0) {
                     while(true) {
                        --j;
                        if(j < 0) {
                           return;
                        }

                        method379(DrawingArea.pixels, ai, k, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                        i1 += k7;
                        l += i7;
                        l1 += l7;
                        k1 += j7;
                        k += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                     }
                  }

                  method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                  i1 += k7;
                  j1 += i8;
                  l1 += l7;
                  i2 += j8;
                  k += DrawingArea.width;
                  l4 += j5;
                  k5 += i6;
                  j6 += l6;
               }
            }
         } else {
            l = j1 <<= 16;
            k1 = i2 <<= 16;
            if(k < 0) {
               l -= k7 * k;
               j1 -= i8 * k;
               k1 -= l7 * k;
               i2 -= j8 * k;
               k = 0;
            }

            i1 <<= 16;
            l1 <<= 16;
            if(j < 0) {
               i1 -= i7 * j;
               l1 -= j7 * j;
               j = 0;
            }

            l9 = k - textureInt2;
            l4 += j5 * l9;
            k5 += i6 * l9;
            j6 += l6 * l9;
            if(k7 < i8) {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                        i1 += i7;
                        j1 += i8;
                        l1 += j7;
                        i2 += j8;
                        k += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                     }
                  }

                  method379(DrawingArea.pixels, ai, k, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                  l += k7;
                  j1 += i8;
                  k1 += l7;
                  i2 += j8;
                  k += DrawingArea.width;
                  l4 += j5;
                  k5 += i6;
                  j6 += l6;
               }
            } else {
               i -= j;
               j -= k;
               k = anIntArray1472[k];

               while(true) {
                  --j;
                  if(j < 0) {
                     while(true) {
                        --i;
                        if(i < 0) {
                           return;
                        }

                        method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                        i1 += i7;
                        j1 += i8;
                        l1 += j7;
                        i2 += j8;
                        k += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                     }
                  }

                  method379(DrawingArea.pixels, ai, k, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                  l += k7;
                  j1 += i8;
                  k1 += l7;
                  i2 += j8;
                  k += DrawingArea.width;
                  l4 += j5;
                  k5 += i6;
                  j6 += l6;
               }
            }
         }
      }
   }

   private static void method379(int[] ai, int[] ai1, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2, int i3) {
      int i = 0;
      int j = 0;
      if(l < i1) {
         int j3;
         int k3;
         if(aBoolean1462) {
            j3 = (k1 - j1) / (i1 - l);
            if(i1 > DrawingArea.centerX) {
               i1 = DrawingArea.centerX;
            }

            if(l < 0) {
               j1 -= l * j3;
               l = 0;
            }

            if(l >= i1) {
               return;
            }

            k3 = i1 - l >> 3;
            j3 <<= 12;
            j1 <<= 9;
         } else {
            if(i1 - l > 7) {
               k3 = i1 - l >> 3;
               j3 = (k1 - j1) * anIntArray1468[k3] >> 6;
            } else {
               k3 = 0;
               j3 = 0;
            }

            j1 <<= 9;
         }

         k += l;
         int j4;
         int l6;
         int l4;
         int j7;
         int l5;
         int j8;
         int l7;
         int j9;
         int l3;
         if(lowMem) {
            j4 = 0;
            l4 = 0;
            l6 = l - textureInt1;
            l1 += (k2 >> 3) * l6;
            i2 += (l2 >> 3) * l6;
            j2 += (i3 >> 3) * l6;
            l5 = j2 >> 12;
            if(l5 != 0) {
               i = l1 / l5;
               j = i2 / l5;
               if(i < 0) {
                  i = 0;
               } else if(i > 4032) {
                  i = 4032;
               }
            }

            l1 += k2;
            i2 += l2;
            j2 += i3;
            l5 = j2 >> 12;
            if(l5 != 0) {
               j4 = l1 / l5;
               l4 = i2 / l5;
               if(j4 < 7) {
                  j4 = 7;
               } else if(j4 > 4032) {
                  j4 = 4032;
               }
            }

            j7 = j4 - i >> 3;
            l7 = l4 - j >> 3;
            i += (j1 & 6291456) >> 3;
            j8 = j1 >> 23;
            if(!aBoolean1463) {
               while(k3-- > 0) {
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i = j4;
                  j = l4;
                  l1 += k2;
                  i2 += l2;
                  j2 += i3;
                  j9 = j2 >> 12;
                  if(j9 != 0) {
                     j4 = l1 / j9;
                     l4 = i2 / j9;
                     if(j4 < 7) {
                        j4 = 7;
                     } else if(j4 > 4032) {
                        j4 = 4032;
                     }
                  }

                  j7 = j4 - i >> 3;
                  l7 = l4 - j >> 3;
                  j1 += j3;
                  i += (j1 & 6291456) >> 3;
                  j8 = j1 >> 23;
               }

               for(k3 = i1 - l & 7; k3-- > 0; j += l7) {
                  if((l3 = ai1[(j & 4032) + (i >> 6)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
               }

            } else {
               while(k3-- > 0) {
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i = j4;
                  j = l4;
                  l1 += k2;
                  i2 += l2;
                  j2 += i3;
                  l3 = j2 >> 12;
                  if(l3 != 0) {
                     j4 = l1 / l3;
                     l4 = i2 / l3;
                     if(j4 < 7) {
                        j4 = 7;
                     } else if(j4 > 4032) {
                        j4 = 4032;
                     }
                  }

                  j7 = j4 - i >> 3;
                  l7 = l4 - j >> 3;
                  j1 += j3;
                  i += (j1 & 6291456) >> 3;
                  j8 = j1 >> 23;
               }

               for(k3 = i1 - l & 7; k3-- > 0; j += l7) {
                  ai[k++] = ai1[(j & 4032) + (i >> 6)] >>> j8;
                  i += j7;
               }

            }
         } else {
            j4 = 0;
            l4 = 0;
            l6 = l - textureInt1;
            l1 += (k2 >> 3) * l6;
            i2 += (l2 >> 3) * l6;
            j2 += (i3 >> 3) * l6;
            l5 = j2 >> 14;
            if(l5 != 0) {
               i = l1 / l5;
               j = i2 / l5;
               if(i < 0) {
                  i = 0;
               } else if(i > 16256) {
                  i = 16256;
               }
            }

            l1 += k2;
            i2 += l2;
            j2 += i3;
            l5 = j2 >> 14;
            if(l5 != 0) {
               j4 = l1 / l5;
               l4 = i2 / l5;
               if(j4 < 7) {
                  j4 = 7;
               } else if(j4 > 16256) {
                  j4 = 16256;
               }
            }

            j7 = j4 - i >> 3;
            l7 = l4 - j >> 3;
            i += j1 & 6291456;
            j8 = j1 >> 23;
            if(!aBoolean1463) {
               while(k3-- > 0) {
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i += j7;
                  j += l7;
                  if((l3 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = l3;
                  }

                  ++k;
                  i = j4;
                  j = l4;
                  l1 += k2;
                  i2 += l2;
                  j2 += i3;
                  j9 = j2 >> 14;
                  if(j9 != 0) {
                     j4 = l1 / j9;
                     l4 = i2 / j9;
                     if(j4 < 7) {
                        j4 = 7;
                     } else if(j4 > 16256) {
                        j4 = 16256;
                     }
                  }

                  j7 = j4 - i >> 3;
                  l7 = l4 - j >> 3;
                  j1 += j3;
                  i += j1 & 6291456;
                  j8 = j1 >> 23;
               }

               for(l3 = i1 - l & 7; l3-- > 0; j += l7) {
                  if((j9 = ai1[(j & 16256) + (i >> 7)] >>> j8) != 0) {
                     ai[k] = j9;
                  }

                  ++k;
                  i += j7;
               }

            } else {
               while(k3-- > 0) {
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
                  j += l7;
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i = j4;
                  j = l4;
                  l1 += k2;
                  i2 += l2;
                  j2 += i3;
                  l3 = j2 >> 14;
                  if(l3 != 0) {
                     j4 = l1 / l3;
                     l4 = i2 / l3;
                     if(j4 < 7) {
                        j4 = 7;
                     } else if(j4 > 16256) {
                        j4 = 16256;
                     }
                  }

                  j7 = j4 - i >> 3;
                  l7 = l4 - j >> 3;
                  j1 += j3;
                  i += j1 & 6291456;
                  j8 = j1 >> 23;
               }

               for(k3 = i1 - l & 7; k3-- > 0; j += l7) {
                  ai[k++] = ai1[(j & 16256) + (i >> 7)] >>> j8;
                  i += j7;
               }

            }
         }
      }
   }

   static {
      int k;
      for(k = 1; k < 512; ++k) {
         anIntArray1468[k] = '\u8000' / k;
      }

      for(k = 1; k < 2048; ++k) {
         anIntArray1469[k] = 65536 / k;
      }

      for(k = 0; k < 2048; ++k) {
         anIntArray1470[k] = (int)(65536.0D * Math.sin((double)k * 0.0030679615D));
         anIntArray1471[k] = (int)(65536.0D * Math.cos((double)k * 0.0030679615D));
      }

   }
}
