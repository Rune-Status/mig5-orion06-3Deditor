package client;


final class Class25 {

   private int[][] anIntArrayArray467;
   private int anInt468;
   private int anInt472;


   final int method641(int i) {
      if(this.anIntArrayArray467 != null) {
         i = i * this.anInt472 / this.anInt468;
      }

      return i;
   }

   final byte[] method644(byte[] is) {
      if(this.anIntArrayArray467 != null) {
         int i_1_ = is.length * this.anInt472 / this.anInt468 + 14;
         int i_2_ = 0;
         int[] is_3_ = new int[i_1_];
         int i_4_ = 0;

         int i_10_;
         for(i_10_ = 0; is.length > i_10_; ++i_10_) {
            byte i_11_ = is[i_10_];
            int[] is_7_ = this.anIntArrayArray467[i_4_];

            int i_9_;
            for(i_9_ = 0; i_9_ < 14; ++i_9_) {
               is_3_[i_9_ + i_2_] += i_11_ * is_7_[i_9_];
            }

            i_4_ += this.anInt472;
            i_9_ = i_4_ / this.anInt468;
            i_4_ -= i_9_ * this.anInt468;
            i_2_ += i_9_;
         }

         is = new byte[i_1_];

         for(i_10_ = 0; i_1_ > i_10_; ++i_10_) {
            int var10 = is_3_[i_10_] + '\u8000' >> 16;
            if(var10 >= -128) {
               if(var10 <= 127) {
                  is[i_10_] = (byte)var10;
               } else {
                  is[i_10_] = 127;
               }
            } else {
               is[i_10_] = -128;
            }
         }
      }

      return is;
   }

   final int method648(int i) {
      if(this.anIntArrayArray467 != null) {
         i = 7 + this.anInt472 * i / this.anInt468;
      }

      return i;
   }

   Class25(int i, int i_31_) {
      int i_32_ = Client.method670(i_31_, i);
      i_31_ /= i_32_;
      i /= i_32_;
      this.anInt468 = i;
      this.anInt472 = i_31_;
      if(i_31_ != i) {
         this.anIntArrayArray467 = new int[i][14];

         for(int i_33_ = 0; i > i_33_; ++i_33_) {
            int[] is = this.anIntArrayArray467[i_33_];
            double d = (double)i_33_ / (double)i + 6.0D;
            int i_34_ = (int)Math.floor(d + -7.0D + 1.0D);
            if(i_34_ < 0) {
               i_34_ = 0;
            }

            double d_35_ = (double)i_31_ / (double)i;
            int i_36_ = (int)Math.ceil(d + 7.0D);
            if(i_36_ > 14) {
               i_36_ = 14;
            }

            while(i_36_ > i_34_) {
               double d_37_ = (-d + (double)i_34_) * 3.141592653589793D;
               double d_38_ = d_35_;
               if(d_37_ < -1.0E-4D || d_37_ > 1.0E-4D) {
                  d_38_ = d_35_ * (Math.sin(d_37_) / d_37_);
               }

               d_38_ *= Math.cos((-d + (double)i_34_) * 0.2243994752564138D) * 0.46D + 0.54D;
               is[i_34_] = (int)Math.floor(d_38_ * 65536.0D + 0.5D);
               ++i_34_;
            }
         }
      }

   }
}
