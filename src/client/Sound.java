package client;


final class Sound {

   private int anInt482;
   private int anInt483;
   private Soundtrack[] aClass22Array484 = new Soundtrack[10];
   public static Sound[] cache = new Sound[5000];


   public static void unpack(Stream stream) {
      while(true) {
         int j = stream.readUnsignedWord();
         if(j == '\uffff') {
            return;
         }

         cache[j] = new Sound(stream);
      }
   }

   final Class3_Sub9_Sub1 method651() {
      byte[] is = this.method654();
      return new Class3_Sub9_Sub1(22050, is, this.anInt483 * 22050 / 1000, this.anInt482 * 22050 / 1000);
   }

   final int method652() {
      int i = 9999999;

      int i_1_;
      for(i_1_ = 0; i_1_ < 10; ++i_1_) {
         if(this.aClass22Array484[i_1_] != null && this.aClass22Array484[i_1_].anInt400 / 20 < i) {
            i = this.aClass22Array484[i_1_].anInt400 / 20;
         }
      }

      if(this.anInt483 < this.anInt482 && this.anInt483 / 20 < i) {
         i = this.anInt483 / 20;
      }

      if(i != 9999999 && i != 0) {
         for(i_1_ = 0; i_1_ < 10; ++i_1_) {
            if(this.aClass22Array484[i_1_] != null) {
               this.aClass22Array484[i_1_].anInt400 -= i * 20;
            }
         }

         if(this.anInt483 < this.anInt482) {
            this.anInt483 -= i * 20;
            this.anInt482 -= i * 20;
         }

         return i;
      } else {
         return 0;
      }
   }

   private final byte[] method654() {
      int i = 0;

      int i_4_;
      for(i_4_ = 0; i_4_ < 10; ++i_4_) {
         if(this.aClass22Array484[i_4_] != null && this.aClass22Array484[i_4_].anInt408 + this.aClass22Array484[i_4_].anInt400 > i) {
            i = this.aClass22Array484[i_4_].anInt408 + this.aClass22Array484[i_4_].anInt400;
         }
      }

      if(i == 0) {
         return new byte[0];
      } else {
         i_4_ = i * 22050 / 1000;
         byte[] is = new byte[i_4_];

         for(int i_5_ = 0; i_5_ < 10; ++i_5_) {
            if(this.aClass22Array484[i_5_] != null) {
               int i_6_ = this.aClass22Array484[i_5_].anInt408 * 22050 / 1000;
               int i_7_ = this.aClass22Array484[i_5_].anInt400 * 22050 / 1000;
               int[] is_8_ = this.aClass22Array484[i_5_].method628(i_6_, this.aClass22Array484[i_5_].anInt408);

               for(int i_9_ = 0; i_9_ < i_6_; ++i_9_) {
                  int i_10_ = is[i_9_ + i_7_] + (is_8_[i_9_] >> 8);
                  if((i_10_ + 128 & -256) != 0) {
                     i_10_ = i_10_ >> 31 ^ 127;
                  }

                  is[i_9_ + i_7_] = (byte)i_10_;
               }
            }
         }

         return is;
      }
   }

   private Sound(Stream class3_sub12) {
      for(int i = 0; i < 10; ++i) {
         int i_11_ = class3_sub12.readUnsignedByte();
         if(i_11_ != 0) {
            --class3_sub12.currentOffset;
            this.aClass22Array484[i] = new Soundtrack();
            this.aClass22Array484[i].method626(class3_sub12);
         }
      }

      this.anInt483 = class3_sub12.readUnsignedWord();
      this.anInt482 = class3_sub12.readUnsignedWord();
   }

   private Sound() {}

}
