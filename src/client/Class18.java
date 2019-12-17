package client;


public final class Class18 {

   public final int[] anIntArray342;
   public final int[][] anIntArrayArray343;


   public Class18(Stream stream) {
      int anInt341 = stream.readUnsignedByte();
      this.anIntArray342 = new int[anInt341];
      this.anIntArrayArray343 = new int[anInt341][];

      int k;
      for(k = 0; k < anInt341; ++k) {
         this.anIntArray342[k] = stream.readUnsignedByte();
      }

      for(k = 0; k < anInt341; ++k) {
         int l = stream.readUnsignedByte();
         this.anIntArrayArray343[k] = new int[l];

         for(int i1 = 0; i1 < l; ++i1) {
            this.anIntArrayArray343[k][i1] = stream.readUnsignedByte();
         }
      }

   }
}
