package client;


final class Class6 {

   private Class29 aClass29_98;
   private Class29 aClass29_99;
   private Class29 aClass29_100;
   private Class29 aClass29_101;
   private Class29 aClass29_102;
   private Class29 aClass29_103;
   private Class29 aClass29_104;
   private Class29 aClass29_105;
   private final int[] anIntArray106 = new int[5];
   private final int[] anIntArray107 = new int[5];
   private final int[] anIntArray108 = new int[5];
   private int anInt109;
   private int anInt110 = 100;
   private Class39 aClass39_111;
   private Class29 aClass29_112;
   int anInt113 = 500;
   int anInt114;
   private static int[] anIntArray115;
   private static int[] anIntArray116;
   private static int[] anIntArray117;
   private static final int[] anIntArray118 = new int[5];
   private static final int[] anIntArray119 = new int[5];
   private static final int[] anIntArray120 = new int[5];
   private static final int[] anIntArray121 = new int[5];
   private static final int[] anIntArray122 = new int[5];


   public static void method166() {
      anIntArray116 = new int['\u8000'];

      int j;
      for(j = 0; j < '\u8000'; ++j) {
         if(Math.random() > 0.5D) {
            anIntArray116[j] = 1;
         } else {
            anIntArray116[j] = -1;
         }
      }

      anIntArray117 = new int['\u8000'];

      for(j = 0; j < '\u8000'; ++j) {
         anIntArray117[j] = (int)(Math.sin((double)j / 5215.1903D) * 16384.0D);
      }

      anIntArray115 = new int[220500];
   }

   public int[] method167(int i, int j) {
      for(int d = 0; d < i; ++d) {
         anIntArray115[d] = 0;
      }

      if(j < 10) {
         return anIntArray115;
      } else {
         double var19 = (double)i / ((double)j + 0.0D);
         this.aClass29_98.resetValues();
         this.aClass29_99.resetValues();
         int l = 0;
         int i1 = 0;
         int j1 = 0;
         if(this.aClass29_100 != null) {
            this.aClass29_100.resetValues();
            this.aClass29_101.resetValues();
            l = (int)((double)(this.aClass29_100.anInt539 - this.aClass29_100.anInt538) * 32.768D / var19);
            i1 = (int)((double)this.aClass29_100.anInt538 * 32.768D / var19);
         }

         int k1 = 0;
         int l1 = 0;
         int i2 = 0;
         if(this.aClass29_102 != null) {
            this.aClass29_102.resetValues();
            this.aClass29_103.resetValues();
            k1 = (int)((double)(this.aClass29_102.anInt539 - this.aClass29_102.anInt538) * 32.768D / var19);
            l1 = (int)((double)this.aClass29_102.anInt538 * 32.768D / var19);
         }

         int i4;
         for(i4 = 0; i4 < 5; ++i4) {
            if(this.anIntArray106[i4] != 0) {
               anIntArray118[i4] = 0;
               anIntArray119[i4] = (int)((double)this.anIntArray108[i4] * var19);
               anIntArray120[i4] = (this.anIntArray106[i4] << 14) / 100;
               anIntArray121[i4] = (int)((double)(this.aClass29_98.anInt539 - this.aClass29_98.anInt538) * 32.768D * Math.pow(1.0057929410678534D, (double)this.anIntArray107[i4]) / var19);
               anIntArray122[i4] = (int)((double)this.aClass29_98.anInt538 * 32.768D / var19);
            }
         }

         int i5;
         int i6;
         int j7;
         int l7;
         for(i4 = 0; i4 < i; ++i4) {
            i5 = this.aClass29_98.method328(i);
            i6 = this.aClass29_99.method328(i);
            if(this.aClass29_100 != null) {
               j7 = this.aClass29_100.method328(i);
               l7 = this.aClass29_101.method328(i);
               i5 += this.method168(l7, j1, this.aClass29_100.anInt540) >> 1;
               j1 += (j7 * l >> 16) + i1;
            }

            if(this.aClass29_102 != null) {
               j7 = this.aClass29_102.method328(i);
               l7 = this.aClass29_103.method328(i);
               i6 = i6 * ((this.method168(l7, i2, this.aClass29_102.anInt540) >> 1) + '\u8000') >> 15;
               i2 += (j7 * k1 >> 16) + l1;
            }

            for(j7 = 0; j7 < 5; ++j7) {
               if(this.anIntArray106[j7] != 0) {
                  l7 = i4 + anIntArray119[j7];
                  if(l7 < i) {
                     anIntArray115[l7] += this.method168(i6 * anIntArray120[j7] >> 15, anIntArray118[j7], this.aClass29_98.anInt540);
                     anIntArray118[j7] += (i5 * anIntArray121[j7] >> 16) + anIntArray122[j7];
                  }
               }
            }
         }

         int c;
         if(this.aClass29_104 != null) {
            this.aClass29_104.resetValues();
            this.aClass29_105.resetValues();
            i4 = 0;
            boolean var20 = true;

            for(i6 = 0; i6 < i; ++i6) {
               j7 = this.aClass29_104.method328(i);
               l7 = this.aClass29_105.method328(i);
               if(var20) {
                  c = this.aClass29_104.anInt538 + ((this.aClass29_104.anInt539 - this.aClass29_104.anInt538) * j7 >> 8);
               } else {
                  c = this.aClass29_104.anInt538 + ((this.aClass29_104.anInt539 - this.aClass29_104.anInt538) * l7 >> 8);
               }

               i4 += 256;
               if(i4 >= c) {
                  i4 = 0;
                  var20 = !var20;
               }

               if(var20) {
                  anIntArray115[i6] = 0;
               }
            }
         }

         if(this.anInt109 > 0 && this.anInt110 > 0) {
            i4 = (int)((double)this.anInt109 * var19);

            for(i5 = i4; i5 < i; ++i5) {
               anIntArray115[i5] += anIntArray115[i5 - i4] * this.anInt110 / 100;
            }
         }

         if(this.aClass39_111.anIntArray665[0] > 0 || this.aClass39_111.anIntArray665[1] > 0) {
            this.aClass29_112.resetValues();
            i4 = this.aClass29_112.method328(i + 1);
            i5 = this.aClass39_111.method544(0, (float)i4 / 65536.0F);
            i6 = this.aClass39_111.method544(1, (float)i4 / 65536.0F);
            if(i >= i5 + i6) {
               j7 = 0;
               l7 = i6;
               if(i6 > i - i5) {
                  l7 = i - i5;
               }

               int i9;
               while(j7 < l7) {
                  c = (int)((long)anIntArray115[j7 + i5] * (long)Class39.anInt672 >> 16);

                  for(i9 = 0; i9 < i5; ++i9) {
                     c += (int)((long)anIntArray115[j7 + i5 - 1 - i9] * (long)Class39.anIntArrayArray670[0][i9] >> 16);
                  }

                  for(i9 = 0; i9 < j7; ++i9) {
                     c -= (int)((long)anIntArray115[j7 - 1 - i9] * (long)Class39.anIntArrayArray670[1][i9] >> 16);
                  }

                  anIntArray115[j7] = c;
                  i4 = this.aClass29_112.method328(i + 1);
                  ++j7;
               }

               short var21 = 128;
               l7 = var21;

               while(true) {
                  if(l7 > i - i5) {
                     l7 = i - i5;
                  }

                  int j10;
                  while(j7 < l7) {
                     i9 = (int)((long)anIntArray115[j7 + i5] * (long)Class39.anInt672 >> 16);

                     for(j10 = 0; j10 < i5; ++j10) {
                        i9 += (int)((long)anIntArray115[j7 + i5 - 1 - j10] * (long)Class39.anIntArrayArray670[0][j10] >> 16);
                     }

                     for(j10 = 0; j10 < i6; ++j10) {
                        i9 -= (int)((long)anIntArray115[j7 - 1 - j10] * (long)Class39.anIntArrayArray670[1][j10] >> 16);
                     }

                     anIntArray115[j7] = i9;
                     i4 = this.aClass29_112.method328(i + 1);
                     ++j7;
                  }

                  if(j7 >= i - i5) {
                     while(j7 < i) {
                        i9 = 0;

                        for(j10 = j7 + i5 - i; j10 < i5; ++j10) {
                           i9 += (int)((long)anIntArray115[j7 + i5 - 1 - j10] * (long)Class39.anIntArrayArray670[0][j10] >> 16);
                        }

                        for(j10 = 0; j10 < i6; ++j10) {
                           i9 -= (int)((long)anIntArray115[j7 - 1 - j10] * (long)Class39.anIntArrayArray670[1][j10] >> 16);
                        }

                        anIntArray115[j7] = i9;
                        this.aClass29_112.method328(i + 1);
                        ++j7;
                     }
                     break;
                  }

                  i5 = this.aClass39_111.method544(0, (float)i4 / 65536.0F);
                  i6 = this.aClass39_111.method544(1, (float)i4 / 65536.0F);
                  l7 += var21;
               }
            }
         }

         for(i4 = 0; i4 < i; ++i4) {
            if(anIntArray115[i4] < -32768) {
               anIntArray115[i4] = -32768;
            }

            if(anIntArray115[i4] > 32767) {
               anIntArray115[i4] = 32767;
            }
         }

         return anIntArray115;
      }
   }

   private int method168(int i, int k, int l) {
      return l == 1?((k & 32767) < 16384?i:-i):(l == 2?anIntArray117[k & 32767] * i >> 14:(l == 3?((k & 32767) * i >> 14) - i:(l == 4?anIntArray116[k / 2607 & 32767] * i:0)));
   }

   public void method169(Stream stream) {
      this.aClass29_98 = new Class29();
      this.aClass29_98.method325(stream);
      this.aClass29_99 = new Class29();
      this.aClass29_99.method325(stream);
      int i = stream.readUnsignedByte();
      if(i != 0) {
         --stream.currentOffset;
         this.aClass29_100 = new Class29();
         this.aClass29_100.method325(stream);
         this.aClass29_101 = new Class29();
         this.aClass29_101.method325(stream);
      }

      i = stream.readUnsignedByte();
      if(i != 0) {
         --stream.currentOffset;
         this.aClass29_102 = new Class29();
         this.aClass29_102.method325(stream);
         this.aClass29_103 = new Class29();
         this.aClass29_103.method325(stream);
      }

      i = stream.readUnsignedByte();
      if(i != 0) {
         --stream.currentOffset;
         this.aClass29_104 = new Class29();
         this.aClass29_104.method325(stream);
         this.aClass29_105 = new Class29();
         this.aClass29_105.method325(stream);
      }

      for(int j = 0; j < 10; ++j) {
         int k = stream.method422();
         if(k == 0) {
            break;
         }

         this.anIntArray106[j] = k;
         this.anIntArray107[j] = stream.method421();
         this.anIntArray108[j] = stream.method422();
      }

      this.anInt109 = stream.method422();
      this.anInt110 = stream.method422();
      this.anInt113 = stream.readUnsignedWord();
      this.anInt114 = stream.readUnsignedWord();
      this.aClass39_111 = new Class39();
      this.aClass29_112 = new Class29();
      this.aClass39_111.method545(stream, this.aClass29_112);
   }

}
