package client;


abstract class Class56_Sub1 extends Class56 {

   final void method835(int i, int i_1_, long l) {
      i_1_ = (int)((double)i_1_ * Math.pow(0.1D, (double)i * 5.0E-4D) + 0.5D);
      if(i_1_ != Client.anInt1401) {
         Client.anInt1401 = i_1_;

         for(int i_2_ = 0; i_2_ < 16; ++i_2_) {
            int i_3_ = method844(i_2_);
            this.method836(i_2_ + 176, 7, i_3_ >> 7, l);
            this.method836(i_2_ + 176, 39, i_3_ & 127, l);
         }
      }

   }

   abstract void method836(int var1, int var2, int var3, long var4);

   final boolean method837(int i, int i_6_, int i_7_, long l) {
      if((i & 240) == 176) {
         int i_10_;
         int i_11_;
         if(i_6_ == 121) {
            this.method836(i, i_6_, i_7_, l);
            i_10_ = i & 15;
            Client.anIntArray385[i_10_] = 12800;
            i_11_ = method844(i_10_);
            this.method836(i, 7, i_11_ >> 7, l);
            this.method836(i, 39, i_11_ & 127, l);
            return true;
         }

         if(i_6_ == 7 || i_6_ == 39) {
            i_10_ = i & 15;
            if(i_6_ == 7) {
               Client.anIntArray385[i_10_] = (Client.anIntArray385[i_10_] & 127) + (i_7_ << 7);
            } else {
               Client.anIntArray385[i_10_] = (Client.anIntArray385[i_10_] & 16256) + i_7_;
            }

            i_11_ = method844(i_10_);
            this.method836(i, 7, i_11_ >> 7, l);
            this.method836(i, 39, i_11_ & 127, l);
            return true;
         }
      }

      return false;
   }

   final void method838(long l) {
      int i_17_;
      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 176, 123, 0, l);
      }

      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 176, 120, 0, l);
      }

      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 176, 121, 0, l);
      }

      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 176, 0, 0, l);
      }

      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 176, 32, 0, l);
      }

      for(i_17_ = 0; i_17_ < 16; ++i_17_) {
         this.method836(i_17_ + 192, 0, 0, l);
      }

   }

   final void method840(int i, long l) {
      Client.anInt1401 = i;

      int i_22_;
      for(i_22_ = 0; i_22_ < 16; ++i_22_) {
         Client.anIntArray385[i_22_] = 12800;
      }

      for(i_22_ = 0; i_22_ < 16; ++i_22_) {
         int i_23_ = method844(i_22_);
         this.method836(i_22_ + 176, 7, i_23_ >> 7, l);
         this.method836(i_22_ + 176, 39, i_23_ & 127, l);
      }

   }

   private static final int method844(int i) {
      int i_32_ = Client.anIntArray385[i];
      i_32_ = (i_32_ * Client.anInt1401 >> 8) * i_32_;
      return (int)(Math.sqrt((double)i_32_) + 0.5D);
   }
}
