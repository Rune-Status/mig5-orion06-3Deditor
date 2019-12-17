package client;


final class Class37 {

   private int[] anIntArray643 = new int[2];
   private int[][][] anIntArrayArrayArray644 = new int[2][2][4];
   private static float aFloat645;
   int[] anIntArray646 = new int[2];
   private int[][][] anIntArrayArrayArray647 = new int[2][2][4];
   static int[][] anIntArrayArray648 = new int[2][8];
   private static float[][] aFloatArrayArray649 = new float[2][8];
   static int anInt650;


   final void method717(Stream class3_sub12, Class51 class51) {
      int i = class3_sub12.readUnsignedByte();
      this.anIntArray646[0] = i >> 4;
      this.anIntArray646[1] = i & 15;
      if(i != 0) {
         this.anIntArray643[0] = class3_sub12.readUnsignedWord();
         this.anIntArray643[1] = class3_sub12.readUnsignedWord();
         int i_0_ = class3_sub12.readUnsignedByte();

         int i_3_;
         int i_4_;
         for(i_3_ = 0; i_3_ < 2; ++i_3_) {
            for(i_4_ = 0; i_4_ < this.anIntArray646[i_3_]; ++i_4_) {
               this.anIntArrayArrayArray644[i_3_][0][i_4_] = class3_sub12.readUnsignedWord();
               this.anIntArrayArrayArray647[i_3_][0][i_4_] = class3_sub12.readUnsignedWord();
            }
         }

         for(i_3_ = 0; i_3_ < 2; ++i_3_) {
            for(i_4_ = 0; i_4_ < this.anIntArray646[i_3_]; ++i_4_) {
               if((i_0_ & 1 << i_3_ * 4 << i_4_) != 0) {
                  this.anIntArrayArrayArray644[i_3_][1][i_4_] = class3_sub12.readUnsignedWord();
                  this.anIntArrayArrayArray647[i_3_][1][i_4_] = class3_sub12.readUnsignedWord();
               } else {
                  this.anIntArrayArrayArray644[i_3_][1][i_4_] = this.anIntArrayArrayArray644[i_3_][0][i_4_];
                  this.anIntArrayArrayArray647[i_3_][1][i_4_] = this.anIntArrayArrayArray647[i_3_][0][i_4_];
               }
            }
         }

         if(i_0_ != 0 || this.anIntArray643[1] != this.anIntArray643[0]) {
            class51.method800(class3_sub12);
         }
      } else {
         this.anIntArray643[0] = this.anIntArray643[1] = 0;
      }

   }

   private static final float method718(float f) {
      float f_5_ = (float)Math.pow(2.0D, (double)f) * 32.703197F;
      return f_5_ * 3.1415927F / 11025.0F;
   }

   public static void reset() {
      aFloatArrayArray649 = (float[][])null;
      anIntArrayArray648 = (int[][])null;
   }

   final int method720(int i, float f) {
      float f_7_;
      if(i == 0) {
         f_7_ = (float)this.anIntArray643[0] + (float)(this.anIntArray643[1] - this.anIntArray643[0]) * f;
         f_7_ *= 0.0030517578F;
         aFloat645 = (float)Math.pow(0.1D, (double)(f_7_ / 20.0F));
         anInt650 = (int)(aFloat645 * 65536.0F);
      }

      if(this.anIntArray646[i] == 0) {
         return 0;
      } else {
         f_7_ = this.method721(i, 0, f);
         aFloatArrayArray649[i][0] = f_7_ * -2.0F * (float)Math.cos((double)this.method722(i, 0, f));
         aFloatArrayArray649[i][1] = f_7_ * f_7_;

         int i_13_;
         for(i_13_ = 1; i_13_ < this.anIntArray646[i]; ++i_13_) {
            f_7_ = this.method721(i, i_13_, f);
            float f_9_ = f_7_ * -2.0F * (float)Math.cos((double)this.method722(i, i_13_, f));
            float f_10_ = f_7_ * f_7_;
            aFloatArrayArray649[i][i_13_ * 2 + 1] = aFloatArrayArray649[i][i_13_ * 2 - 1] * f_10_;
            aFloatArrayArray649[i][i_13_ * 2] = aFloatArrayArray649[i][i_13_ * 2 - 1] * f_9_ + aFloatArrayArray649[i][i_13_ * 2 - 2] * f_10_;

            for(int i_11_ = i_13_ * 2 - 1; i_11_ >= 2; --i_11_) {
               aFloatArrayArray649[i][i_11_] += aFloatArrayArray649[i][i_11_ - 1] * f_9_ + aFloatArrayArray649[i][i_11_ - 2] * f_10_;
            }

            aFloatArrayArray649[i][1] += aFloatArrayArray649[i][0] * f_9_ + f_10_;
            aFloatArrayArray649[i][0] += f_9_;
         }

         if(i == 0) {
            for(i_13_ = 0; i_13_ < this.anIntArray646[0] * 2; ++i_13_) {
               aFloatArrayArray649[0][i_13_] *= aFloat645;
            }
         }

         for(i_13_ = 0; i_13_ < this.anIntArray646[i] * 2; ++i_13_) {
            anIntArrayArray648[i][i_13_] = (int)(aFloatArrayArray649[i][i_13_] * 65536.0F);
         }

         return this.anIntArray646[i] * 2;
      }
   }

   private final float method721(int i, int i_14_, float f) {
      float f_15_ = (float)this.anIntArrayArrayArray647[i][0][i_14_] + f * (float)(this.anIntArrayArrayArray647[i][1][i_14_] - this.anIntArrayArrayArray647[i][0][i_14_]);
      f_15_ *= 0.0015258789F;
      return 1.0F - (float)Math.pow(10.0D, (double)(-f_15_ / 20.0F));
   }

   private final float method722(int i, int i_16_, float f) {
      float f_17_ = (float)this.anIntArrayArrayArray644[i][0][i_16_] + f * (float)(this.anIntArrayArrayArray644[i][1][i_16_] - this.anIntArrayArrayArray644[i][0][i_16_]);
      f_17_ *= 1.2207031E-4F;
      return method718(f_17_);
   }

}
