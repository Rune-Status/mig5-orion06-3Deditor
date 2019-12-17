package client;


final class Animable_Sub4 extends Animable {

   public final int anInt1571;
   public final int anInt1572;
   private double aDouble1574;
   private double aDouble1575;
   private double aDouble1576;
   private double aDouble1577;
   private double aDouble1578;
   private boolean aBoolean1579 = false;
   private final int anInt1580;
   private final int anInt1581;
   private final int anInt1582;
   public final int anInt1583;
   public double aDouble1585;
   public double aDouble1586;
   public double aDouble1587;
   private final int anInt1588;
   private final int anInt1589;
   public final int anInt1590;
   private final SpotAnim aSpotAnim_1592;
   private int anInt1593;
   private int anInt1594;
   public int anInt1595;
   private int anInt1596;
   public final int anInt1597;


   public void method455(int i, int j, int k, int l) {
      double d1;
      if(!this.aBoolean1579) {
         d1 = (double)(l - this.anInt1580);
         double d2 = (double)(j - this.anInt1581);
         double d3 = Math.sqrt(d1 * d1 + d2 * d2);
         this.aDouble1585 = (double)this.anInt1580 + d1 * (double)this.anInt1589 / d3;
         this.aDouble1586 = (double)this.anInt1581 + d2 * (double)this.anInt1589 / d3;
         this.aDouble1587 = (double)this.anInt1582;
      }

      d1 = (double)(this.anInt1572 + 1 - i);
      this.aDouble1574 = ((double)l - this.aDouble1585) / d1;
      this.aDouble1575 = ((double)j - this.aDouble1586) / d1;
      this.aDouble1576 = Math.sqrt(this.aDouble1574 * this.aDouble1574 + this.aDouble1575 * this.aDouble1575);
      if(!this.aBoolean1579) {
         this.aDouble1577 = -this.aDouble1576 * Math.tan((double)this.anInt1588 * 0.02454369D);
      }

      this.aDouble1578 = 2.0D * ((double)k - this.aDouble1587 - this.aDouble1577 * d1) / (d1 * d1);
   }

   public Model getRotatedModel() {
      Model model = this.aSpotAnim_1592.getModel();
      if(model == null) {
         return null;
      } else {
         int j = -1;
         if(this.aSpotAnim_1592.aAnimation_407 != null) {
            j = this.aSpotAnim_1592.aAnimation_407.anIntArray353[this.anInt1593];
         }

         Model model_1 = new Model(true, Class36.method532(j), false, model);
         if(j != -1) {
            model_1.method469();
            model_1.method470(j);
            model_1.anIntArrayArray1658 = (int[][])null;
            model_1.anIntArrayArray1657 = (int[][])null;
         }

         if(this.aSpotAnim_1592.anInt410 != 128 || this.aSpotAnim_1592.anInt411 != 128) {
            model_1.method478(this.aSpotAnim_1592.anInt410, this.aSpotAnim_1592.anInt410, this.aSpotAnim_1592.anInt411);
         }

         model_1.method474(this.anInt1596);
         model_1.method479(64 + this.aSpotAnim_1592.anInt413, 850 + this.aSpotAnim_1592.anInt414, -30, -50, -30, true);
         return model_1;
      }
   }

   public Animable_Sub4(int i, int j, int l, int i1, int j1, int k1, int l1, int i2, int j2, int k2, int l2) {
      this.aSpotAnim_1592 = SpotAnim.cache[l2];
      this.anInt1597 = k1;
      this.anInt1580 = j2;
      this.anInt1581 = i2;
      this.anInt1582 = l1;
      this.anInt1571 = l;
      this.anInt1572 = i1;
      this.anInt1588 = i;
      this.anInt1589 = j1;
      this.anInt1590 = k2;
      this.anInt1583 = j;
      this.aBoolean1579 = false;
   }

   public void method456(int i) {
      this.aBoolean1579 = true;
      this.aDouble1585 += this.aDouble1574 * (double)i;
      this.aDouble1586 += this.aDouble1575 * (double)i;
      this.aDouble1587 += this.aDouble1577 * (double)i + 0.5D * this.aDouble1578 * (double)i * (double)i;
      this.aDouble1577 += this.aDouble1578 * (double)i;
      this.anInt1595 = (int)(Math.atan2(this.aDouble1574, this.aDouble1575) * 325.949D) + 1024 & 2047;
      this.anInt1596 = (int)(Math.atan2(this.aDouble1577, this.aDouble1576) * 325.949D) & 2047;
      if(this.aSpotAnim_1592.aAnimation_407 != null) {
         this.anInt1594 += i;

         while(this.anInt1594 > this.aSpotAnim_1592.aAnimation_407.method258(this.anInt1593)) {
            this.anInt1594 -= this.aSpotAnim_1592.aAnimation_407.method258(this.anInt1593) + 1;
            ++this.anInt1593;
            if(this.anInt1593 >= this.aSpotAnim_1592.aAnimation_407.anInt352) {
               this.anInt1593 = 0;
            }
         }
      }

   }
}
