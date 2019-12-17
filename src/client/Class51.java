package client;


final class Class51 {

   private int[] anIntArray853 = new int[2];
   private int[] anIntArray854 = new int[2];
   int anInt855;
   int anInt856;
   private int anInt857 = 2;
   int anInt858;
   private int anInt859;
   private int anInt860;
   private int anInt861;
   private int anInt862;
   private int anInt863;


   final int method798(int i) {
      if(this.anInt863 >= this.anInt859) {
         this.anInt862 = this.anIntArray853[this.anInt860++] << 15;
         if(this.anInt860 >= this.anInt857) {
            this.anInt860 = this.anInt857 - 1;
         }

         this.anInt859 = (int)((double)this.anIntArray854[this.anInt860] / 65536.0D * (double)i);
         if(this.anInt859 > this.anInt863) {
            this.anInt861 = ((this.anIntArray853[this.anInt860] << 15) - this.anInt862) / (this.anInt859 - this.anInt863);
         }
      }

      this.anInt862 += this.anInt861;
      ++this.anInt863;
      return this.anInt862 - this.anInt861 >> 15;
   }

   final void resetValues() {
      this.anInt859 = 0;
      this.anInt860 = 0;
      this.anInt861 = 0;
      this.anInt862 = 0;
      this.anInt863 = 0;
   }

   final void method800(Stream class3_sub12) {
      this.anInt857 = class3_sub12.readUnsignedByte();
      this.anIntArray854 = new int[this.anInt857];
      this.anIntArray853 = new int[this.anInt857];

      for(int i = 0; i < this.anInt857; ++i) {
         this.anIntArray854[i] = class3_sub12.readUnsignedWord();
         this.anIntArray853[i] = class3_sub12.readUnsignedWord();
      }

   }

   final void method801(Stream class3_sub12) {
      this.anInt855 = class3_sub12.readUnsignedByte();
      this.anInt858 = class3_sub12.readDWord();
      this.anInt856 = class3_sub12.readDWord();
      this.method800(class3_sub12);
   }

   public Class51() {
      this.anIntArray854[0] = 0;
      this.anIntArray854[1] = '\uffff';
      this.anIntArray853[0] = 0;
      this.anIntArray853[1] = '\uffff';
   }
}
