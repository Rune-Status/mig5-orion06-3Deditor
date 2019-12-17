package client;


final class Sounds {

   private static final Sounds[] aSoundsArray325s = new Sounds[5000];
   public static final int[] anIntArray326 = new int[5000];
   private static byte[] aByteArray327;
   private static Stream aStream_328;
   private final Class6[] aClass6Array329 = new Class6[10];
   private int anInt330;
   private int anInt331;


   public static void unpack(Stream stream) {
      aByteArray327 = new byte[441000];
      aStream_328 = new Stream(aByteArray327);
      Class6.method166();

      while(true) {
         int j = stream.readUnsignedWord();
         if(j == '\uffff') {
            return;
         }

         aSoundsArray325s[j] = new Sounds();
         aSoundsArray325s[j].method242(stream);
         anIntArray326[j] = aSoundsArray325s[j].method243();
      }
   }

   public static Stream method241(int i, int j) {
      if(aSoundsArray325s[j] != null) {
         Sounds sounds = aSoundsArray325s[j];
         return sounds.method244(i);
      } else {
         return null;
      }
   }

   private void method242(Stream stream) {
      for(int i = 0; i < 10; ++i) {
         int j = stream.readUnsignedByte();
         if(j != 0) {
            --stream.currentOffset;
            this.aClass6Array329[i] = new Class6();
            this.aClass6Array329[i].method169(stream);
         }
      }

      this.anInt330 = stream.readUnsignedWord();
      this.anInt331 = stream.readUnsignedWord();
   }

   private int method243() {
      int j = 9999999;

      int l;
      for(l = 0; l < 10; ++l) {
         if(this.aClass6Array329[l] != null && this.aClass6Array329[l].anInt114 / 20 < j) {
            j = this.aClass6Array329[l].anInt114 / 20;
         }
      }

      if(this.anInt330 < this.anInt331 && this.anInt330 / 20 < j) {
         j = this.anInt330 / 20;
      }

      if(j != 9999999 && j != 0) {
         for(l = 0; l < 10; ++l) {
            if(this.aClass6Array329[l] != null) {
               this.aClass6Array329[l].anInt114 -= j * 20;
            }
         }

         if(this.anInt330 < this.anInt331) {
            this.anInt330 -= j * 20;
            this.anInt331 -= j * 20;
         }

         return j;
      } else {
         return 0;
      }
   }

   private Stream method244(int i) {
      int k = this.method245(i);
      aStream_328.currentOffset = 0;
      aStream_328.writeDWord(1380533830);
      aStream_328.method403(36 + k);
      aStream_328.writeDWord(1463899717);
      aStream_328.writeDWord(1718449184);
      aStream_328.method403(16);
      aStream_328.method400(1);
      aStream_328.method400(1);
      aStream_328.method403(22050);
      aStream_328.method403(22050);
      aStream_328.method400(1);
      aStream_328.method400(8);
      aStream_328.writeDWord(1684108385);
      aStream_328.method403(k);
      aStream_328.currentOffset += k;
      return aStream_328;
   }

   private int method245(int i) {
      int j = 0;

      int l;
      for(l = 0; l < 10; ++l) {
         if(this.aClass6Array329[l] != null && this.aClass6Array329[l].anInt113 + this.aClass6Array329[l].anInt114 > j) {
            j = this.aClass6Array329[l].anInt113 + this.aClass6Array329[l].anInt114;
         }
      }

      if(j == 0) {
         return 0;
      } else {
         l = 22050 * j / 1000;
         int i1 = 22050 * this.anInt330 / 1000;
         int j1 = 22050 * this.anInt331 / 1000;
         if(i1 < 0 || i1 > l || j1 < 0 || j1 > l || i1 >= j1) {
            i = 0;
         }

         int k1 = l + (j1 - i1) * (i - 1);

         int k2;
         for(k2 = 44; k2 < k1 + 44; ++k2) {
            aByteArray327[k2] = -128;
         }

         int k3;
         int l2;
         for(k2 = 0; k2 < 10; ++k2) {
            if(this.aClass6Array329[k2] != null) {
               k3 = this.aClass6Array329[k2].anInt113 * 22050 / 1000;
               l2 = this.aClass6Array329[k2].anInt114 * 22050 / 1000;
               int[] ai = this.aClass6Array329[k2].method167(k3, this.aClass6Array329[k2].anInt113);

               for(int l3 = 0; l3 < k3; ++l3) {
                  aByteArray327[l3 + l2 + 44] += (byte)(ai[l3] >> 8);
               }
            }
         }

         if(i > 1) {
            i1 += 44;
            j1 += 44;
            l += 44;
            k1 += 44;
            k2 = k1 - l;

            for(k3 = l - 1; k3 >= j1; --k3) {
               aByteArray327[k3 + k2] = aByteArray327[k3];
            }

            for(k3 = 1; k3 < i; ++k3) {
               l2 = (j1 - i1) * k3;
               System.arraycopy(aByteArray327, i1, aByteArray327, i1 + l2, j1 - i1);
            }

            k1 -= 44;
         }

         return k1;
      }
   }

}
