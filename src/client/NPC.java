package client;


public final class NPC extends Entity {

   public EntityDef desc;


   private Model method450() {
      int l;
      if(super.anim >= 0 && super.anInt1529 == 0) {
         l = Animation.anims[super.anim].anIntArray353[super.anInt1527];
         int i1 = -1;
         if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
            i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(i1, l, Animation.anims[super.anim].anIntArray357);
      } else {
         l = -1;
         if(super.anInt1517 >= 0) {
            l = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(-1, l, (int[])null);
      }
   }

   public Model getRotatedModel() {
      if(this.desc == null) {
         return null;
      } else {
         Model model = this.method450();
         if(model == null) {
            return null;
         } else {
            super.height = model.modelHeight;
            if(super.anInt1520 != -1 && super.anInt1521 != -1) {
               SpotAnim spotAnim = SpotAnim.cache[super.anInt1520];
               Model model_1 = spotAnim.getModel();
               if(model_1 != null) {
                  int j = spotAnim.aAnimation_407.anIntArray353[super.anInt1521];
                  Model model_2 = new Model(true, Class36.method532(j), false, model_1);
                  model_2.method475(0, -super.anInt1524, 0);
                  model_2.method469();
                  model_2.method470(j);
                  model_2.anIntArrayArray1658 = (int[][])null;
                  model_2.anIntArrayArray1657 = (int[][])null;
                  if(spotAnim.anInt410 != 128 || spotAnim.anInt411 != 128) {
                     model_2.method478(spotAnim.anInt410, spotAnim.anInt410, spotAnim.anInt411);
                  }

                  model_2.method479(64 + spotAnim.anInt413, 850 + spotAnim.anInt414, -30, -50, -30, true);
                  Model[] aModel = new Model[]{model, model_2};
                  model = new Model(aModel);
               }
            }

            if(this.desc.aByte68 == 1) {
               model.aBoolean1659 = true;
            }

            return model;
         }
      }
   }

   public boolean isVisible() {
      return this.desc != null;
   }

}
