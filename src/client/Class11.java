package client;


final class Class11 {

   private static byte[] aByteArray210 = new byte[]{(byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)1, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)2, (byte)0, (byte)1, (byte)2, (byte)1, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
   private Stream aClass3_Sub12_211 = new Stream((byte[])null);
   private int[] anIntArray212;
   int anInt213;
   private int[] anIntArray214;
   private long aLong215;
   int[] anIntArray216;
   private int[] anIntArray217;
   private int anInt218;


   final void method520(int i) {
      int i_0_ = this.aClass3_Sub12_211.method428();
      this.anIntArray216[i] += i_0_;
   }

   final boolean method521() {
      return this.aClass3_Sub12_211.currentOffset < 0;
   }

   final void method522(int i) {
      this.anIntArray214[i] = this.aClass3_Sub12_211.currentOffset;
   }

   final void method523() {
      this.aClass3_Sub12_211.buffer = null;
      this.anIntArray217 = null;
      this.anIntArray214 = null;
      this.anIntArray216 = null;
      this.anIntArray212 = null;
   }

   private final int method524(int i) {
      byte i_1_ = this.aClass3_Sub12_211.buffer[this.aClass3_Sub12_211.currentOffset];
      int var5;
      if(i_1_ < 0) {
         var5 = i_1_ & 255;
         this.anIntArray212[i] = var5;
         ++this.aClass3_Sub12_211.currentOffset;
      } else {
         var5 = this.anIntArray212[i];
      }

      if(var5 != 240 && var5 != 247) {
         return this.method535(i, var5);
      } else {
         int i_2_ = this.aClass3_Sub12_211.method428();
         if(var5 == 247 && i_2_ > 0) {
            int i_3_ = this.aClass3_Sub12_211.buffer[this.aClass3_Sub12_211.currentOffset] & 255;
            if(i_3_ >= 241 && i_3_ <= 243 || i_3_ == 246 || i_3_ == 248 || i_3_ >= 250 && i_3_ <= 252 || i_3_ == 254) {
               ++this.aClass3_Sub12_211.currentOffset;
               this.anIntArray212[i] = i_3_;
               return this.method535(i, i_3_);
            }
         }

         this.aClass3_Sub12_211.currentOffset += i_2_;
         return 0;
      }
   }

   final void method525(byte[] is) {
      this.aClass3_Sub12_211.buffer = is;
      this.aClass3_Sub12_211.currentOffset = 10;
      int i = this.aClass3_Sub12_211.readUnsignedWord();
      this.anInt213 = this.aClass3_Sub12_211.readUnsignedWord();
      this.anInt218 = 500000;
      this.anIntArray217 = new int[i];

      int i_6_;
      for(int i_4_ = 0; i_4_ < i; this.aClass3_Sub12_211.currentOffset += i_6_) {
         int i_5_ = this.aClass3_Sub12_211.readDWord();
         i_6_ = this.aClass3_Sub12_211.readDWord();
         if(i_5_ == 1297379947) {
            this.anIntArray217[i_4_] = this.aClass3_Sub12_211.currentOffset;
            ++i_4_;
         }
      }

      this.anIntArray214 = (int[])this.anIntArray217.clone();
      this.anIntArray216 = new int[i];
      this.anIntArray212 = new int[i];
   }

   final void method526(int i) {
      this.aClass3_Sub12_211.currentOffset = this.anIntArray214[i];
   }

   final boolean method527() {
      return this.aClass3_Sub12_211.buffer != null;
   }

   final void method528() {
      this.aClass3_Sub12_211.currentOffset = -1;
   }

   final int method529(int i) {
      int i_7_ = this.method524(i);
      return i_7_;
   }

   public static void reset() {
      aByteArray210 = null;
   }

   final boolean method531() {
      int i = this.anIntArray214.length;

      for(int i_8_ = 0; i_8_ < i; ++i_8_) {
         if(this.anIntArray214[i_8_] >= 0) {
            return false;
         }
      }

      return true;
   }

   final long method532(int i) {
      return this.aLong215 + (long)i * (long)this.anInt218;
   }

   final int method533() {
      return this.anIntArray214.length;
   }

   final void method534(long l) {
      this.aLong215 = l;
      int i = this.anIntArray214.length;

      for(int i_9_ = 0; i_9_ < i; ++i_9_) {
         this.anIntArray216[i_9_] = 0;
         this.anIntArray212[i_9_] = 0;
         this.aClass3_Sub12_211.currentOffset = this.anIntArray217[i_9_];
         this.method520(i_9_);
         this.anIntArray214[i_9_] = this.aClass3_Sub12_211.currentOffset;
      }

   }

   private final int method535(int i, int i_10_) {
      int i_16_;
      if(i_10_ == 255) {
         int i_15_1 = this.aClass3_Sub12_211.readUnsignedByte();
         i_16_ = this.aClass3_Sub12_211.method428();
         if(i_15_1 == 47) {
            this.aClass3_Sub12_211.currentOffset += i_16_;
            return 1;
         } else if(i_15_1 == 81) {
            int i_13_ = this.aClass3_Sub12_211.read3Bytes();
            i_16_ -= 3;
            int i_14_ = this.anIntArray216[i];
            this.aLong215 += (long)i_14_ * (long)(this.anInt218 - i_13_);
            this.anInt218 = i_13_;
            this.aClass3_Sub12_211.currentOffset += i_16_;
            return 2;
         } else {
            this.aClass3_Sub12_211.currentOffset += i_16_;
            return 3;
         }
      } else {
         byte i_15_ = aByteArray210[i_10_ - 128];
         i_16_ = i_10_;
         if(i_15_ >= 1) {
            i_16_ = i_10_ | this.aClass3_Sub12_211.readUnsignedByte() << 8;
         }

         if(i_15_ >= 2) {
            i_16_ |= this.aClass3_Sub12_211.readUnsignedByte() << 16;
         }

         return i_16_;
      }
   }

   final int method536() {
      int i = this.anIntArray214.length;
      int i_17_ = -1;
      int i_18_ = Integer.MAX_VALUE;

      for(int i_19_ = 0; i_19_ < i; ++i_19_) {
         if(this.anIntArray214[i_19_] >= 0 && this.anIntArray216[i_19_] < i_18_) {
            i_17_ = i_19_;
            i_18_ = this.anIntArray216[i_19_];
         }
      }

      return i_17_;
   }

}
