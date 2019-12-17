package client;


final class Animable_Sub5 extends Animable {

   private int anInt1599;
   private final int[] anIntArray1600;
   private final int anInt1601;
   private final int anInt1602;
   private final int anInt1603;
   private final int anInt1604;
   private final int anInt1605;
   private final int anInt1606;
   private Animation aAnimation_1607;
   private int anInt1608;
   public static Client clientInstance;
   private final int anInt1610;
   private final int anInt1611;
   private final int anInt1612;


   public Model getRotatedModel() {
      int j = -1;
      if(this.aAnimation_1607 != null) {
         int class46 = Client.loopCycle - this.anInt1608;
         if(class46 > 100 && this.aAnimation_1607.anInt356 > 0) {
            class46 = 100;
         }

         while(class46 > this.aAnimation_1607.method258(this.anInt1599)) {
            class46 -= this.aAnimation_1607.method258(this.anInt1599);
            ++this.anInt1599;
            if(this.anInt1599 >= this.aAnimation_1607.anInt352) {
               this.anInt1599 -= this.aAnimation_1607.anInt356;
               if(this.anInt1599 < 0 || this.anInt1599 >= this.aAnimation_1607.anInt352) {
                  this.aAnimation_1607 = null;
                  break;
               }
            }
         }

         this.anInt1608 = Client.loopCycle - class46;
         if(this.aAnimation_1607 != null) {
            j = this.aAnimation_1607.anIntArray353[this.anInt1599];
         }
      }

      ObjectDef var3;
      if(this.anIntArray1600 != null) {
         var3 = this.method457();
      } else {
         var3 = ObjectDef.forID(this.anInt1610);
      }

      return var3 == null?null:var3.method578(this.anInt1611, this.anInt1612, this.anInt1603, this.anInt1604, this.anInt1605, this.anInt1606, j);
   }

   private ObjectDef method457() {
      int i = -1;
      if(this.anInt1601 != -1) {
         VarBit varBit = VarBit.cache[this.anInt1601];
         int k = varBit.anInt648;
         int l = varBit.anInt649;
         int i1 = varBit.anInt650;
         int j1 = Client.anIntArray1232[i1 - l];
         i = clientInstance.variousSettings[k] >> l & j1;
      } else if(this.anInt1602 != -1) {
         i = clientInstance.variousSettings[this.anInt1602];
      }

      return i >= 0 && i < this.anIntArray1600.length && this.anIntArray1600[i] != -1?ObjectDef.forID(this.anIntArray1600[i]):null;
   }

   public Animable_Sub5(int i, int j, int k, int l, int i1, int j1, int k1, int l1, boolean flag) {
      this.anInt1610 = i;
      this.anInt1611 = k;
      this.anInt1612 = j;
      this.anInt1603 = j1;
      this.anInt1604 = l;
      this.anInt1605 = i1;
      this.anInt1606 = k1;
      if(l1 != -1) {
         this.aAnimation_1607 = Animation.anims[l1];
         this.anInt1599 = 0;
         this.anInt1608 = Client.loopCycle;
         if(flag && this.aAnimation_1607.anInt356 != -1) {
            this.anInt1599 = (int)(Math.random() * (double)this.aAnimation_1607.anInt352);
            this.anInt1608 -= (int)(Math.random() * (double)this.aAnimation_1607.method258(this.anInt1599));
         }
      }

      ObjectDef class46 = ObjectDef.forID(this.anInt1610);
      this.anInt1601 = class46.anInt774;
      this.anInt1602 = class46.anInt749;
      this.anIntArray1600 = class46.childrenIDs;
   }
}
