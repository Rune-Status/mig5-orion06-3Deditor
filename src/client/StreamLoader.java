package client;


final class StreamLoader {

   private final byte[] aByteArray726;
   private final int dataSize;
   private final int[] anIntArray728;
   private final int[] anIntArray729;
   private final int[] anIntArray730;
   private final int[] anIntArray731;
   private final boolean aBoolean732;


   public StreamLoader(byte[] abyte0) {
      Stream stream = new Stream(abyte0);
      int i = stream.read3Bytes();
      int j = stream.read3Bytes();
      if(j != i) {
         byte[] k = new byte[i];
         Class13.method225(k, i, abyte0, j, 6);
         this.aByteArray726 = k;
         stream = new Stream(this.aByteArray726);
         this.aBoolean732 = true;
      } else {
         this.aByteArray726 = abyte0;
         this.aBoolean732 = false;
      }

      this.dataSize = stream.readUnsignedWord();
      this.anIntArray728 = new int[this.dataSize];
      this.anIntArray729 = new int[this.dataSize];
      this.anIntArray730 = new int[this.dataSize];
      this.anIntArray731 = new int[this.dataSize];
      int var7 = stream.currentOffset + this.dataSize * 10;

      for(int l = 0; l < this.dataSize; ++l) {
         this.anIntArray728[l] = stream.readDWord();
         this.anIntArray729[l] = stream.read3Bytes();
         this.anIntArray730[l] = stream.read3Bytes();
         this.anIntArray731[l] = var7;
         var7 += this.anIntArray730[l];
      }

   }

   public byte[] getDataForName(String s) {
      byte[] abyte0 = null;
      int i = 0;
      s = s.toUpperCase();

      int k;
      for(k = 0; k < s.length(); ++k) {
         i = i * 61 + s.charAt(k) - 32;
      }

      for(k = 0; k < this.dataSize; ++k) {
         if(this.anIntArray728[k] == i) {
            if(abyte0 == null) {
               abyte0 = new byte[this.anIntArray729[k]];
            }

            if(!this.aBoolean732) {
               Class13.method225(abyte0, this.anIntArray729[k], this.aByteArray726, this.anIntArray730[k], this.anIntArray731[k]);
            } else {
               System.arraycopy(this.aByteArray726, this.anIntArray731[k], abyte0, 0, this.anIntArray729[k]);
            }

            return abyte0;
         }
      }

      return null;
   }
}
