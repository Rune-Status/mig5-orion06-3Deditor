package client;


final class Class3_Sub9_Sub1 extends Class3_Sub9 {

   byte[] aByteArray1827;
   int anInt1828;
   int anInt1829;
   int anInt1830;


   final Class3_Sub9_Sub1 method405(Class25 class25) {
      this.aByteArray1827 = class25.method644(this.aByteArray1827);
      this.anInt1828 = class25.method641(this.anInt1828);
      if(this.anInt1830 == this.anInt1829) {
         this.anInt1830 = this.anInt1829 = class25.method648(this.anInt1830);
      } else {
         this.anInt1830 = class25.method648(this.anInt1830);
         this.anInt1829 = class25.method648(this.anInt1829);
         if(this.anInt1830 == this.anInt1829) {
            --this.anInt1830;
         }
      }

      return this;
   }

   Class3_Sub9_Sub1(int i, byte[] is, int i_0_, int i_1_) {
      this.anInt1828 = i;
      this.aByteArray1827 = is;
      this.anInt1830 = i_0_;
      this.anInt1829 = i_1_;
   }
}
