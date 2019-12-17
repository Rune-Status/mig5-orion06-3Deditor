package client;


final class Animable_Sub3 extends Animable {

   public final int anInt1560;
   public final int anInt1561;
   public final int anInt1562;
   public final int anInt1563;
   public final int anInt1564;
   public boolean aBoolean1567 = false;
   private final SpotAnim aSpotAnim_1568;
   private int anInt1569;
   private int anInt1570;


   public Animable_Sub3(int i, int j, int l, int i1, int j1, int k1, int l1) {
      this.aSpotAnim_1568 = SpotAnim.cache[i1];
      this.anInt1560 = i;
      this.anInt1561 = l1;
      this.anInt1562 = k1;
      this.anInt1563 = j1;
      this.anInt1564 = j + l;
      this.aBoolean1567 = false;
   }

   public Model getRotatedModel() {
      Model model = this.aSpotAnim_1568.getModel();
      if(model == null) {
         return null;
      } else {
         int j = this.aSpotAnim_1568.aAnimation_407.anIntArray353[this.anInt1569];
         Model model_1 = new Model(true, Class36.method532(j), false, model);
         if(!this.aBoolean1567) {
            model_1.method469();
            model_1.method470(j);
            model_1.anIntArrayArray1658 = (int[][])null;
            model_1.anIntArrayArray1657 = (int[][])null;
         }

         if(this.aSpotAnim_1568.anInt410 != 128 || this.aSpotAnim_1568.anInt411 != 128) {
            model_1.method478(this.aSpotAnim_1568.anInt410, this.aSpotAnim_1568.anInt410, this.aSpotAnim_1568.anInt411);
         }

         if(this.aSpotAnim_1568.anInt412 != 0) {
            if(this.aSpotAnim_1568.anInt412 == 90) {
               model_1.method473();
            }

            if(this.aSpotAnim_1568.anInt412 == 180) {
               model_1.method473();
               model_1.method473();
            }

            if(this.aSpotAnim_1568.anInt412 == 270) {
               model_1.method473();
               model_1.method473();
               model_1.method473();
            }
         }

         model_1.method479(64 + this.aSpotAnim_1568.anInt413, 850 + this.aSpotAnim_1568.anInt414, -30, -50, -30, true);
         return model_1;
      }
   }

   public void method454(int i) {
      this.anInt1570 += i;

      while(this.anInt1570 > this.aSpotAnim_1568.aAnimation_407.method258(this.anInt1569)) {
         this.anInt1570 -= this.aSpotAnim_1568.aAnimation_407.method258(this.anInt1569) + 1;
         ++this.anInt1569;
         if(this.anInt1569 >= this.aSpotAnim_1568.aAnimation_407.anInt352 && (this.anInt1569 < 0 || this.anInt1569 >= this.aSpotAnim_1568.aAnimation_407.anInt352)) {
            this.anInt1569 = 0;
            this.aBoolean1567 = true;
         }
      }

   }
}
