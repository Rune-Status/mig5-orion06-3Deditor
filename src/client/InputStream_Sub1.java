package client;

import java.io.InputStream;

public final class InputStream_Sub1 extends InputStream {

   boolean aBoolean31;
   private int[] anIntArray32 = new int[]{0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
   private byte[] aByteArray33 = new byte[65536];
   private int[] anIntArray34 = new int[256];


   private final byte method41(int i) {
      int i_0_ = i >> 8 & 128;
      if(i_0_ != 0) {
         i = -i;
      }

      if(i > 32635) {
         i = 32635;
      }

      i += 132;
      int i_1_ = this.anIntArray32[i >> 7 & 255];
      int i_2_ = i >> i_1_ + 3 & 15;
      byte i_3_ = (byte)(~(i_0_ | i_1_ << 4 | i_2_));
      return i_3_;
   }

   public final synchronized int read(byte[] is, int i, int i_4_) {
      try {
         if(this.aBoolean31) {
            return -1;
         } else if(i_4_ > 256) {
            this.read(is, i, 256);
            this.read(is, i + 256, i_4_ - 256);
            return i_4_;
         } else {
            Client.method486(this.anIntArray34, i_4_);

            for(int exception = 0; exception < i_4_; ++exception) {
               int i_7_ = this.anIntArray34[exception];
               if((i_7_ + 8388608 & -16777216) != 0) {
                  this.anIntArray34[exception] = 8388607 ^ i_7_ >> 31;
               }
            }

            method42(this.aByteArray33, this.anIntArray34, is, 0, i, i_4_);
            return i_4_;
         }
      } catch (Exception var7) {
         this.aBoolean31 = true;
         return -1;
      }
   }

   public final int read() {
      byte[] is = new byte[1];
      this.read(is, 0, 1);
      return is[0];
   }

   private static final void method42(byte[] is, int[] is_8_, byte[] is_9_, int i, int i_10_, int i_11_) {
      for(i = 0; i < i_11_; ++i) {
         is_9_[i_10_++] = is[(is_8_[i] >> 8) + '\u8000'];
      }

   }

   InputStream_Sub1() {
      for(int i = -32768; i < '\u8000'; ++i) {
         this.aByteArray33[i + '\u8000'] = this.method41(i);
      }

   }
}
