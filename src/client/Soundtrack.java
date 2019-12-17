package client;

import java.util.Random;

final class Soundtrack {

   int anInt400 = 0;
   private Class51 aClass51_401;
   private Class51 aClass51_402;
   private Class51 aClass51_403;
   private int[] anIntArray404 = new int[5];
   private Class51 aClass51_405;
   private int anInt406 = 0;
   private int[] anIntArray407 = new int[5];
   int anInt408 = 500;
   private Class51 aClass51_409;
   private Class37 aClass37_410;
   private Class51 aClass51_411;
   private static int[] sineTable;
   private Class51 aClass51_413;
   private int[] anIntArray414 = new int[5];
   private Class51 aClass51_415;
   private static int[] noise = new int['\u8000'];
   private Class51 aClass51_417;
   private int anInt418 = 100;
   private static int[] sampleBuffer;
   private static int[] anIntArray420;
   private static int[] anIntArray421;
   private static int[] anIntArray422;
   private static int[] anIntArray423;
   private static int[] anIntArray424;


   final void method626(Stream class3_sub12) {
      this.aClass51_415 = new Class51();
      this.aClass51_415.method801(class3_sub12);
      this.aClass51_409 = new Class51();
      this.aClass51_409.method801(class3_sub12);
      int i = class3_sub12.readUnsignedByte();
      if(i != 0) {
         --class3_sub12.currentOffset;
         this.aClass51_401 = new Class51();
         this.aClass51_401.method801(class3_sub12);
         this.aClass51_402 = new Class51();
         this.aClass51_402.method801(class3_sub12);
      }

      i = class3_sub12.readUnsignedByte();
      if(i != 0) {
         --class3_sub12.currentOffset;
         this.aClass51_411 = new Class51();
         this.aClass51_411.method801(class3_sub12);
         this.aClass51_405 = new Class51();
         this.aClass51_405.method801(class3_sub12);
      }

      i = class3_sub12.readUnsignedByte();
      if(i != 0) {
         --class3_sub12.currentOffset;
         this.aClass51_413 = new Class51();
         this.aClass51_413.method801(class3_sub12);
         this.aClass51_403 = new Class51();
         this.aClass51_403.method801(class3_sub12);
      }

      for(int i_0_ = 0; i_0_ < 10; ++i_0_) {
         int i_1_ = class3_sub12.method422();
         if(i_1_ == 0) {
            break;
         }

         this.anIntArray404[i_0_] = i_1_;
         this.anIntArray407[i_0_] = class3_sub12.method421();
         this.anIntArray414[i_0_] = class3_sub12.method422();
      }

      this.anInt406 = class3_sub12.method422();
      this.anInt418 = class3_sub12.method422();
      this.anInt408 = class3_sub12.readUnsignedWord();
      this.anInt400 = class3_sub12.readUnsignedWord();
      this.aClass37_410 = new Class37();
      this.aClass51_417 = new Class51();
      this.aClass37_410.method717(class3_sub12, this.aClass51_417);
   }

   private final int method627(int i, int i_2_, int i_3_) {
      return i_3_ == 1?((i & 32767) < 16384?i_2_:-i_2_):(i_3_ == 2?sineTable[i & 32767] * i_2_ >> 14:(i_3_ == 3?((i & 32767) * i_2_ >> 14) - i_2_:(i_3_ == 4?noise[i / 2607 & 32767] * i_2_:0)));
   }

   final int[] method628(int i, int i_4_) {
      for(int d = 0; d < i; ++d) {
         sampleBuffer[d] = 0;
      }

      if(i_4_ < 10) {
         return sampleBuffer;
      } else {
         double var18 = (double)i / ((double)i_4_ + 0.0D);
         this.aClass51_415.resetValues();
         this.aClass51_409.resetValues();
         int i_6_ = 0;
         int i_7_ = 0;
         int i_8_ = 0;
         if(this.aClass51_401 != null) {
            this.aClass51_401.resetValues();
            this.aClass51_402.resetValues();
            i_6_ = (int)((double)(this.aClass51_401.anInt856 - this.aClass51_401.anInt858) * 32.768D / var18);
            i_7_ = (int)((double)this.aClass51_401.anInt858 * 32.768D / var18);
         }

         int i_9_ = 0;
         int i_10_ = 0;
         int i_11_ = 0;
         if(this.aClass51_411 != null) {
            this.aClass51_411.resetValues();
            this.aClass51_405.resetValues();
            i_9_ = (int)((double)(this.aClass51_411.anInt856 - this.aClass51_411.anInt858) * 32.768D / var18);
            i_10_ = (int)((double)this.aClass51_411.anInt858 * 32.768D / var18);
         }

         int i_44_;
         for(i_44_ = 0; i_44_ < 5; ++i_44_) {
            if(this.anIntArray404[i_44_] != 0) {
               anIntArray422[i_44_] = 0;
               anIntArray423[i_44_] = (int)((double)this.anIntArray414[i_44_] * var18);
               anIntArray421[i_44_] = (this.anIntArray404[i_44_] << 14) / 100;
               anIntArray420[i_44_] = (int)((double)(this.aClass51_415.anInt856 - this.aClass51_415.anInt858) * 32.768D * Math.pow(1.0057929410678534D, (double)this.anIntArray407[i_44_]) / var18);
               anIntArray424[i_44_] = (int)((double)this.aClass51_415.anInt858 * 32.768D / var18);
            }
         }

         int i_31_;
         int i_32_;
         int i_33_;
         int i_34_;
         for(i_44_ = 0; i_44_ < i; ++i_44_) {
            i_31_ = this.aClass51_415.method798(i);
            i_32_ = this.aClass51_409.method798(i);
            if(this.aClass51_401 != null) {
               i_33_ = this.aClass51_401.method798(i);
               i_34_ = this.aClass51_402.method798(i);
               i_31_ += this.method627(i_8_, i_34_, this.aClass51_401.anInt855) >> 1;
               i_8_ += (i_33_ * i_6_ >> 16) + i_7_;
            }

            if(this.aClass51_411 != null) {
               i_33_ = this.aClass51_411.method798(i);
               i_34_ = this.aClass51_405.method798(i);
               i_32_ = i_32_ * ((this.method627(i_11_, i_34_, this.aClass51_411.anInt855) >> 1) + '\u8000') >> 15;
               i_11_ += (i_33_ * i_9_ >> 16) + i_10_;
            }

            for(i_33_ = 0; i_33_ < 5; ++i_33_) {
               if(this.anIntArray404[i_33_] != 0) {
                  i_34_ = i_44_ + anIntArray423[i_33_];
                  if(i_34_ < i) {
                     sampleBuffer[i_34_] += this.method627(anIntArray422[i_33_], i_32_ * anIntArray421[i_33_] >> 15, this.aClass51_415.anInt855);
                     anIntArray422[i_33_] += (i_31_ * anIntArray420[i_33_] >> 16) + anIntArray424[i_33_];
                  }
               }
            }
         }

         int i_41_;
         if(this.aClass51_413 != null) {
            this.aClass51_413.resetValues();
            this.aClass51_403.resetValues();
            i_44_ = 0;
            boolean var19 = true;

            for(i_32_ = 0; i_32_ < i; ++i_32_) {
               i_33_ = this.aClass51_413.method798(i);
               i_34_ = this.aClass51_403.method798(i);
               if(var19) {
                  i_41_ = this.aClass51_413.anInt858 + ((this.aClass51_413.anInt856 - this.aClass51_413.anInt858) * i_33_ >> 8);
               } else {
                  i_41_ = this.aClass51_413.anInt858 + ((this.aClass51_413.anInt856 - this.aClass51_413.anInt858) * i_34_ >> 8);
               }

               i_44_ += 256;
               if(i_44_ >= i_41_) {
                  i_44_ = 0;
                  var19 = !var19;
               }

               if(var19) {
                  sampleBuffer[i_32_] = 0;
               }
            }
         }

         if(this.anInt406 > 0 && this.anInt418 > 0) {
            i_44_ = (int)((double)this.anInt406 * var18);

            for(i_31_ = i_44_; i_31_ < i; ++i_31_) {
               sampleBuffer[i_31_] += sampleBuffer[i_31_ - i_44_] * this.anInt418 / 100;
            }
         }

         if(this.aClass37_410.anIntArray646[0] > 0 || this.aClass37_410.anIntArray646[1] > 0) {
            this.aClass51_417.resetValues();
            i_44_ = this.aClass51_417.method798(i + 1);
            i_31_ = this.aClass37_410.method720(0, (float)i_44_ / 65536.0F);
            i_32_ = this.aClass37_410.method720(1, (float)i_44_ / 65536.0F);
            if(i >= i_31_ + i_32_) {
               i_33_ = 0;
               i_34_ = i_32_;
               if(i_32_ > i - i_31_) {
                  i_34_ = i - i_31_;
               }

               int i_43_;
               while(i_33_ < i_34_) {
                  i_41_ = (int)((long)sampleBuffer[i_33_ + i_31_] * (long)Class37.anInt650 >> 16);

                  for(i_43_ = 0; i_43_ < i_31_; ++i_43_) {
                     i_41_ += (int)((long)sampleBuffer[i_33_ + i_31_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[0][i_43_] >> 16);
                  }

                  for(i_43_ = 0; i_43_ < i_33_; ++i_43_) {
                     i_41_ -= (int)((long)sampleBuffer[i_33_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[1][i_43_] >> 16);
                  }

                  sampleBuffer[i_33_] = i_41_;
                  i_44_ = this.aClass51_417.method798(i + 1);
                  ++i_33_;
               }

               i_34_ = 128;

               while(true) {
                  if(i_34_ > i - i_31_) {
                     i_34_ = i - i_31_;
                  }

                  while(i_33_ < i_34_) {
                     i_41_ = (int)((long)sampleBuffer[i_33_ + i_31_] * (long)Class37.anInt650 >> 16);

                     for(i_43_ = 0; i_43_ < i_31_; ++i_43_) {
                        i_41_ += (int)((long)sampleBuffer[i_33_ + i_31_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[0][i_43_] >> 16);
                     }

                     for(i_43_ = 0; i_43_ < i_32_; ++i_43_) {
                        i_41_ -= (int)((long)sampleBuffer[i_33_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[1][i_43_] >> 16);
                     }

                     sampleBuffer[i_33_] = i_41_;
                     i_44_ = this.aClass51_417.method798(i + 1);
                     ++i_33_;
                  }

                  if(i_33_ >= i - i_31_) {
                     while(i_33_ < i) {
                        i_41_ = 0;

                        for(i_43_ = i_33_ + i_31_ - i; i_43_ < i_31_; ++i_43_) {
                           i_41_ += (int)((long)sampleBuffer[i_33_ + i_31_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[0][i_43_] >> 16);
                        }

                        for(i_43_ = 0; i_43_ < i_32_; ++i_43_) {
                           i_41_ -= (int)((long)sampleBuffer[i_33_ - 1 - i_43_] * (long)Class37.anIntArrayArray648[1][i_43_] >> 16);
                        }

                        sampleBuffer[i_33_] = i_41_;
                        this.aClass51_417.method798(i + 1);
                        ++i_33_;
                     }
                     break;
                  }

                  i_31_ = this.aClass37_410.method720(0, (float)i_44_ / 65536.0F);
                  i_32_ = this.aClass37_410.method720(1, (float)i_44_ / 65536.0F);
                  i_34_ += 128;
               }
            }
         }

         for(i_44_ = 0; i_44_ < i; ++i_44_) {
            if(sampleBuffer[i_44_] < -32768) {
               sampleBuffer[i_44_] = -32768;
            }

            if(sampleBuffer[i_44_] > 32767) {
               sampleBuffer[i_44_] = 32767;
            }
         }

         return sampleBuffer;
      }
   }

   public static void reset() {
      sampleBuffer = null;
      noise = null;
      sineTable = null;
      anIntArray422 = null;
      anIntArray423 = null;
      anIntArray421 = null;
      anIntArray420 = null;
      anIntArray424 = null;
   }

   static {
      Random random = new Random(0L);

      int i;
      for(i = 0; i < '\u8000'; ++i) {
         noise[i] = (random.nextInt() & 2) - 1;
      }

      sineTable = new int['\u8000'];

      for(i = 0; i < '\u8000'; ++i) {
         sineTable[i] = (int)(Math.sin((double)i / 5215.1903D) * 16384.0D);
      }

      sampleBuffer = new int[220500];
      anIntArray421 = new int[5];
      anIntArray420 = new int[5];
      anIntArray422 = new int[5];
      anIntArray424 = new int[5];
      anIntArray423 = new int[5];
   }
}
