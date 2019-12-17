package client;


final class Class13 {

   private static final Class32 aClass32_305 = new Class32();


   public static int method225(byte[] abyte0, int i, byte[] abyte1, int j, int k) {
      Class32 var5 = aClass32_305;
      synchronized(aClass32_305) {
         aClass32_305.aByteArray563 = abyte1;
         aClass32_305.anInt564 = k;
         aClass32_305.aByteArray568 = abyte0;
         aClass32_305.anInt569 = 0;
         aClass32_305.anInt565 = j;
         aClass32_305.anInt570 = i;
         aClass32_305.anInt577 = 0;
         aClass32_305.anInt576 = 0;
         aClass32_305.anInt566 = 0;
         aClass32_305.anInt567 = 0;
         aClass32_305.anInt571 = 0;
         aClass32_305.anInt572 = 0;
         aClass32_305.anInt579 = 0;
         method227(aClass32_305);
         i -= aClass32_305.anInt570;
         return i;
      }
   }

   private static void method226(Class32 class32) {
      byte byte4 = class32.aByte573;
      int i = class32.anInt574;
      int j = class32.anInt584;
      int k = class32.anInt582;
      int[] ai = Class32.anIntArray587;
      int l = class32.anInt581;
      byte[] abyte0 = class32.aByteArray568;
      int i1 = class32.anInt569;
      int j1 = class32.anInt570;
      int l1 = class32.anInt601 + 1;

      label68:
      while(true) {
         if(i > 0) {
            while(true) {
               if(j1 == 0) {
                  break label68;
               }

               if(i == 1) {
                  if(j1 == 0) {
                     i = 1;
                     break label68;
                  }

                  abyte0[i1] = byte4;
                  ++i1;
                  --j1;
                  break;
               }

               abyte0[i1] = byte4;
               --i;
               ++i1;
               --j1;
            }
         }

         boolean i2 = true;

         byte byte1;
         while(i2) {
            i2 = false;
            if(j == l1) {
               i = 0;
               break label68;
            }

            byte4 = (byte)k;
            l = ai[l];
            byte1 = (byte)(l & 255);
            l >>= 8;
            ++j;
            if(byte1 != k) {
               k = byte1;
               if(j1 == 0) {
                  i = 1;
                  break label68;
               }

               abyte0[i1] = byte4;
               ++i1;
               --j1;
               i2 = true;
            } else if(j == l1) {
               if(j1 == 0) {
                  i = 1;
                  break label68;
               }

               abyte0[i1] = byte4;
               ++i1;
               --j1;
               i2 = true;
            }
         }

         i = 2;
         l = ai[l];
         byte1 = (byte)(l & 255);
         l >>= 8;
         ++j;
         if(j != l1) {
            if(byte1 != k) {
               k = byte1;
            } else {
               i = 3;
               l = ai[l];
               byte byte2 = (byte)(l & 255);
               l >>= 8;
               ++j;
               if(j != l1) {
                  if(byte2 != k) {
                     k = byte2;
                  } else {
                     l = ai[l];
                     byte byte3 = (byte)(l & 255);
                     l >>= 8;
                     ++j;
                     i = (byte3 & 255) + 4;
                     l = ai[l];
                     k = (byte)(l & 255);
                     l >>= 8;
                     ++j;
                  }
               }
            }
         }
      }

      int var16 = class32.anInt571;
      class32.anInt571 += j1 - j1;
      if(class32.anInt571 < var16) {
         ++class32.anInt572;
      }

      class32.aByte573 = byte4;
      class32.anInt574 = i;
      class32.anInt584 = j;
      class32.anInt582 = k;
      Class32.anIntArray587 = ai;
      class32.anInt581 = l;
      class32.aByteArray568 = abyte0;
      class32.anInt569 = i1;
      class32.anInt570 = j1;
   }

   private static void method227(Class32 class32) {
      int k8 = 0;
      int[] ai = null;
      int[] ai1 = null;
      int[] ai2 = null;
      class32.anInt578 = 1;
      if(Class32.anIntArray587 == null) {
         Class32.anIntArray587 = new int[class32.anInt578 * 100000];
      }

      for(boolean flag19 = true; flag19; flag19 = class32.anInt584 == class32.anInt601 + 1 && class32.anInt574 == 0) {
         byte byte0 = method228(class32);
         if(byte0 == 23) {
            return;
         }

         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method228(class32);
         ++class32.anInt579;
         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method228(class32);
         byte0 = method229(class32);
         class32.aBoolean575 = byte0 != 0;
         if(class32.aBoolean575) {
            System.out.println("PANIC! RANDOMISED BLOCK!");
         }

         class32.anInt580 = 0;
         byte0 = method228(class32);
         class32.anInt580 = class32.anInt580 << 8 | byte0 & 255;
         byte0 = method228(class32);
         class32.anInt580 = class32.anInt580 << 8 | byte0 & 255;
         byte0 = method228(class32);
         class32.anInt580 = class32.anInt580 << 8 | byte0 & 255;

         int i4;
         for(i4 = 0; i4 < 16; ++i4) {
            byte j4 = method229(class32);
            class32.aBooleanArray590[i4] = j4 == 1;
         }

         for(i4 = 0; i4 < 256; ++i4) {
            class32.aBooleanArray589[i4] = false;
         }

         int var28;
         for(i4 = 0; i4 < 16; ++i4) {
            if(class32.aBooleanArray590[i4]) {
               for(var28 = 0; var28 < 16; ++var28) {
                  byte k4 = method229(class32);
                  if(k4 == 1) {
                     class32.aBooleanArray589[i4 * 16 + var28] = true;
                  }
               }
            }
         }

         method231(class32);
         i4 = class32.anInt588 + 2;
         var28 = method230(3, class32);
         int var29 = method230(15, class32);
         int abyte0 = 0;

         int l4;
         byte i5;
         while(abyte0 < var29) {
            l4 = 0;

            while(true) {
               i5 = method229(class32);
               if(i5 == 0) {
                  class32.aByteArray595[abyte0] = (byte)l4;
                  ++abyte0;
                  break;
               }

               ++l4;
            }
         }

         byte[] var30 = new byte[6];

         for(byte var31 = 0; var31 < var28; var30[var31] = var31++) {
            ;
         }

         byte j5;
         for(l4 = 0; l4 < var29; ++l4) {
            i5 = class32.aByteArray595[l4];

            for(j5 = var30[i5]; i5 > 0; --i5) {
               var30[i5] = var30[i5 - 1];
            }

            var30[0] = j5;
            class32.aByteArray594[l4] = j5;
         }

         int var32;
         int var33;
         for(l4 = 0; l4 < var28; ++l4) {
            var32 = method230(5, class32);
            var33 = 0;

            while(var33 < i4) {
               while(true) {
                  byte j9 = method229(class32);
                  if(j9 == 0) {
                     class32.aByteArrayArray596[l4][var33] = (byte)var32;
                     ++var33;
                     break;
                  }

                  j9 = method229(class32);
                  if(j9 == 0) {
                     ++var32;
                  } else {
                     --var32;
                  }
               }
            }
         }

         int var34;
         for(l4 = 0; l4 < var28; ++l4) {
            i5 = 32;
            j5 = 0;

            for(var34 = 0; var34 < i4; ++var34) {
               if(class32.aByteArrayArray596[l4][var34] > j5) {
                  j5 = class32.aByteArrayArray596[l4][var34];
               }

               if(class32.aByteArrayArray596[l4][var34] < i5) {
                  i5 = class32.aByteArrayArray596[l4][var34];
               }
            }

            method232(class32.anIntArrayArray597[l4], class32.anIntArrayArray598[l4], class32.anIntArrayArray599[l4], class32.aByteArrayArray596[l4], i5, j5, i4);
            class32.anIntArray600[l4] = i5;
         }

         l4 = class32.anInt588 + 1;
         var32 = -1;
         byte var35 = 0;

         for(var34 = 0; var34 <= 255; ++var34) {
            class32.anIntArray583[var34] = 0;
         }

         var34 = 4095;

         int i6;
         int i7;
         for(i6 = 15; i6 >= 0; --i6) {
            for(i7 = 15; i7 >= 0; --i7) {
               class32.aByteArray592[var34] = (byte)(i6 * 16 + i7);
               --var34;
            }

            class32.anIntArray593[i6] = var34 + 1;
         }

         i6 = 0;
         if(var35 == 0) {
            ++var32;
            var35 = 50;
            byte var36 = class32.aByteArray594[var32];
            k8 = class32.anIntArray600[var36];
            ai = class32.anIntArrayArray597[var36];
            ai2 = class32.anIntArrayArray599[var36];
            ai1 = class32.anIntArrayArray598[var36];
         }

         var33 = var35 - 1;
         i7 = k8;

         int l7;
         byte byte9;
         for(l7 = method230(k8, class32); l7 > ai[i7]; l7 = l7 << 1 | byte9) {
            ++i7;
            byte9 = method229(class32);
         }

         int l2 = ai2[l7 - ai1[i7]];

         while(l2 != l4) {
            int byte7;
            int j8;
            byte k7;
            byte byte11;
            int var37;
            if(l2 != 0 && l2 != 1) {
               byte7 = l2 - 1;
               byte var39;
               if(byte7 < 16) {
                  var37 = class32.anIntArray593[0];

                  for(var39 = class32.aByteArray592[var37 + byte7]; byte7 > 3; byte7 -= 4) {
                     j8 = var37 + byte7;
                     class32.aByteArray592[j8] = class32.aByteArray592[j8 - 1];
                     class32.aByteArray592[j8 - 1] = class32.aByteArray592[j8 - 2];
                     class32.aByteArray592[j8 - 2] = class32.aByteArray592[j8 - 3];
                     class32.aByteArray592[j8 - 3] = class32.aByteArray592[j8 - 4];
                  }

                  while(byte7 > 0) {
                     class32.aByteArray592[var37 + byte7] = class32.aByteArray592[var37 + byte7 - 1];
                     --byte7;
                  }

                  class32.aByteArray592[var37] = var39;
               } else {
                  var37 = byte7 / 16;
                  j8 = byte7 % 16;
                  int var40 = class32.anIntArray593[var37] + j8;

                  for(var39 = class32.aByteArray592[var40]; var40 > class32.anIntArray593[var37]; --var40) {
                     class32.aByteArray592[var40] = class32.aByteArray592[var40 - 1];
                  }

                  ++class32.anIntArray593[var37];

                  while(var37 > 0) {
                     --class32.anIntArray593[var37];
                     class32.aByteArray592[class32.anIntArray593[var37]] = class32.aByteArray592[class32.anIntArray593[var37 - 1] + 16 - 1];
                     --var37;
                  }

                  --class32.anIntArray593[0];
                  class32.aByteArray592[class32.anIntArray593[0]] = var39;
                  if(class32.anIntArray593[0] == 0) {
                     int i10 = 4095;

                     for(int k9 = 15; k9 >= 0; --k9) {
                        for(int l9 = 15; l9 >= 0; --l9) {
                           class32.aByteArray592[i10] = class32.aByteArray592[class32.anIntArray593[k9] + l9];
                           --i10;
                        }

                        class32.anIntArray593[k9] = i10 + 1;
                     }
                  }
               }

               ++class32.anIntArray583[class32.aByteArray591[var39 & 255] & 255];
               Class32.anIntArray587[i6] = class32.aByteArray591[var39 & 255] & 255;
               ++i6;
               if(var33 == 0) {
                  ++var32;
                  var33 = 50;
                  k7 = class32.aByteArray594[var32];
                  k8 = class32.anIntArray600[k7];
                  ai = class32.anIntArrayArray597[k7];
                  ai2 = class32.anIntArrayArray599[k7];
                  ai1 = class32.anIntArrayArray598[k7];
               }

               --var33;
               var37 = k8;

               for(j8 = method230(k8, class32); j8 > ai[var37]; j8 = j8 << 1 | byte11) {
                  ++var37;
                  byte11 = method229(class32);
               }

               l2 = ai2[j8 - ai1[var37]];
            } else {
               byte7 = -1;
               int byte6 = 1;

               do {
                  if(l2 == 0) {
                     byte7 += byte6;
                  } else if(l2 == 1) {
                     byte7 += 2 * byte6;
                  }

                  byte6 *= 2;
                  if(var33 == 0) {
                     ++var32;
                     var33 = 50;
                     k7 = class32.aByteArray594[var32];
                     k8 = class32.anIntArray600[k7];
                     ai = class32.anIntArrayArray597[k7];
                     ai2 = class32.anIntArrayArray599[k7];
                     ai1 = class32.anIntArrayArray598[k7];
                  }

                  --var33;
                  var37 = k8;

                  for(j8 = method230(k8, class32); j8 > ai[var37]; j8 = j8 << 1 | byte11) {
                     ++var37;
                     byte11 = method229(class32);
                  }

                  l2 = ai2[j8 - ai1[var37]];
               } while(l2 == 0 || l2 == 1);

               ++byte7;
               k7 = class32.aByteArray591[class32.aByteArray592[class32.anIntArray593[0]] & 255];

               for(class32.anIntArray583[k7 & 255] += byte7; byte7 > 0; --byte7) {
                  Class32.anIntArray587[i6] = k7 & 255;
                  ++i6;
               }
            }
         }

         class32.anInt574 = 0;
         class32.aByte573 = 0;
         class32.anIntArray585[0] = 0;

         for(l2 = 1; l2 <= 256; ++l2) {
            class32.anIntArray585[l2] = class32.anIntArray583[l2 - 1];
         }

         for(l2 = 1; l2 <= 256; ++l2) {
            class32.anIntArray585[l2] += class32.anIntArray585[l2 - 1];
         }

         for(l2 = 0; l2 < i6; ++l2) {
            byte var38 = (byte)(Class32.anIntArray587[l2] & 255);
            Class32.anIntArray587[class32.anIntArray585[var38 & 255]] |= l2 << 8;
            ++class32.anIntArray585[var38 & 255];
         }

         class32.anInt581 = Class32.anIntArray587[class32.anInt580] >> 8;
         class32.anInt584 = 0;
         class32.anInt581 = Class32.anIntArray587[class32.anInt581];
         class32.anInt582 = (byte)(class32.anInt581 & 255);
         class32.anInt581 >>= 8;
         ++class32.anInt584;
         class32.anInt601 = i6;
         method226(class32);
      }

   }

   private static byte method228(Class32 class32) {
      return (byte)method230(8, class32);
   }

   private static byte method229(Class32 class32) {
      return (byte)method230(1, class32);
   }

   private static int method230(int i, Class32 class32) {
      while(class32.anInt577 < i) {
         class32.anInt576 = class32.anInt576 << 8 | class32.aByteArray563[class32.anInt564] & 255;
         class32.anInt577 += 8;
         ++class32.anInt564;
         --class32.anInt565;
         ++class32.anInt566;
         if(class32.anInt566 == 0) {
            ++class32.anInt567;
         }
      }

      int k = class32.anInt576 >> class32.anInt577 - i & (1 << i) - 1;
      class32.anInt577 -= i;
      return k;
   }

   private static void method231(Class32 class32) {
      class32.anInt588 = 0;

      for(int i = 0; i < 256; ++i) {
         if(class32.aBooleanArray589[i]) {
            class32.aByteArray591[class32.anInt588] = (byte)i;
            ++class32.anInt588;
         }
      }

   }

   private static void method232(int[] ai, int[] ai1, int[] ai2, byte[] abyte0, int i, int j, int k) {
      int l = 0;

      int i3;
      int k2;
      for(i3 = i; i3 <= j; ++i3) {
         for(k2 = 0; k2 < k; ++k2) {
            if(abyte0[k2] == i3) {
               ai2[l] = k2;
               ++l;
            }
         }
      }

      for(i3 = 0; i3 < 23; ++i3) {
         ai1[i3] = 0;
      }

      for(i3 = 0; i3 < k; ++i3) {
         ++ai1[abyte0[i3] + 1];
      }

      for(i3 = 1; i3 < 23; ++i3) {
         ai1[i3] += ai1[i3 - 1];
      }

      for(i3 = 0; i3 < 23; ++i3) {
         ai[i3] = 0;
      }

      i3 = 0;

      for(k2 = i; k2 <= j; ++k2) {
         i3 += ai1[k2 + 1] - ai1[k2];
         ai[k2] = i3 - 1;
         i3 <<= 1;
      }

      for(k2 = i + 1; k2 <= j; ++k2) {
         ai1[k2] = (ai[k2 - 1] + 1 << 1) - ai1[k2];
      }

   }

}
