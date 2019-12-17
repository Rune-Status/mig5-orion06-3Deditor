package client;


final class Class39 {

   final int[] anIntArray665 = new int[2];
   private final int[][][] anIntArrayArrayArray666 = new int[2][2][4];
   private final int[][][] anIntArrayArrayArray667 = new int[2][2][4];
   private final int[] anIntArray668 = new int[2];
   private static final float[][] aFloatArrayArray669 = new float[2][8];
   static final int[][] anIntArrayArray670 = new int[2][8];
   private static float aFloat671;
   static int anInt672;


   private float method541(int i, int j, float f) {
      float f1 = (float)this.anIntArrayArrayArray667[i][0][j] + f * (float)(this.anIntArrayArrayArray667[i][1][j] - this.anIntArrayArrayArray667[i][0][j]);
      f1 *= 0.001525879F;
      return 1.0F - (float)Math.pow(10.0D, (double)(-f1 / 20.0F));
   }

   private float method542(float f) {
      float f1 = 32.7032F * (float)Math.pow(2.0D, (double)f);
      return f1 * 3.141593F / 11025.0F;
   }

   private float method543(float f, int i, int j) {
      float f1 = (float)this.anIntArrayArrayArray666[j][0][i] + f * (float)(this.anIntArrayArrayArray666[j][1][i] - this.anIntArrayArrayArray666[j][0][i]);
      f1 *= 1.220703E-4F;
      return this.method542(f1);
   }

   public int method544(int i, float f) {
      float f2;
      if(i == 0) {
         f2 = (float)this.anIntArray668[0] + (float)(this.anIntArray668[1] - this.anIntArray668[0]) * f;
         f2 *= 0.003051758F;
         aFloat671 = (float)Math.pow(0.1D, (double)(f2 / 20.0F));
         anInt672 = (int)(aFloat671 * 65536.0F);
      }

      if(this.anIntArray665[i] == 0) {
         return 0;
      } else {
         f2 = this.method541(i, 0, f);
         aFloatArrayArray669[i][0] = -2.0F * f2 * (float)Math.cos((double)this.method543(f, 0, i));
         aFloatArrayArray669[i][1] = f2 * f2;

         int i1;
         for(i1 = 1; i1 < this.anIntArray665[i]; ++i1) {
            float f3 = this.method541(i, i1, f);
            float f4 = -2.0F * f3 * (float)Math.cos((double)this.method543(f, i1, i));
            float f5 = f3 * f3;
            aFloatArrayArray669[i][i1 * 2 + 1] = aFloatArrayArray669[i][i1 * 2 - 1] * f5;
            aFloatArrayArray669[i][i1 * 2] = aFloatArrayArray669[i][i1 * 2 - 1] * f4 + aFloatArrayArray669[i][i1 * 2 - 2] * f5;

            for(int j1 = i1 * 2 - 1; j1 >= 2; --j1) {
               aFloatArrayArray669[i][j1] += aFloatArrayArray669[i][j1 - 1] * f4 + aFloatArrayArray669[i][j1 - 2] * f5;
            }

            aFloatArrayArray669[i][1] += aFloatArrayArray669[i][0] * f4 + f5;
            aFloatArrayArray669[i][0] += f4;
         }

         if(i == 0) {
            for(i1 = 0; i1 < this.anIntArray665[0] * 2; ++i1) {
               aFloatArrayArray669[0][i1] *= aFloat671;
            }
         }

         for(i1 = 0; i1 < this.anIntArray665[i] * 2; ++i1) {
            anIntArrayArray670[i][i1] = (int)(aFloatArrayArray669[i][i1] * 65536.0F);
         }

         return this.anIntArray665[i] * 2;
      }
   }

   public void method545(Stream stream, Class29 class29) {
      int i = stream.readUnsignedByte();
      this.anIntArray665[0] = i >> 4;
      this.anIntArray665[1] = i & 15;
      if(i != 0) {
         this.anIntArray668[0] = stream.readUnsignedWord();
         this.anIntArray668[1] = stream.readUnsignedWord();
         int j = stream.readUnsignedByte();

         int i1;
         int j1;
         for(i1 = 0; i1 < 2; ++i1) {
            for(j1 = 0; j1 < this.anIntArray665[i1]; ++j1) {
               this.anIntArrayArrayArray666[i1][0][j1] = stream.readUnsignedWord();
               this.anIntArrayArrayArray667[i1][0][j1] = stream.readUnsignedWord();
            }
         }

         for(i1 = 0; i1 < 2; ++i1) {
            for(j1 = 0; j1 < this.anIntArray665[i1]; ++j1) {
               if((j & 1 << i1 * 4 << j1) != 0) {
                  this.anIntArrayArrayArray666[i1][1][j1] = stream.readUnsignedWord();
                  this.anIntArrayArrayArray667[i1][1][j1] = stream.readUnsignedWord();
               } else {
                  this.anIntArrayArrayArray666[i1][1][j1] = this.anIntArrayArrayArray666[i1][0][j1];
                  this.anIntArrayArrayArray667[i1][1][j1] = this.anIntArrayArrayArray667[i1][0][j1];
               }
            }
         }

         if(j != 0 || this.anIntArray668[1] != this.anIntArray668[0]) {
            class29.method326(stream);
         }
      } else {
         this.anIntArray668[0] = this.anIntArray668[1] = 0;
      }

   }

}
