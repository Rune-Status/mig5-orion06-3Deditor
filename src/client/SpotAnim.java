package client;


public final class SpotAnim {

   public static SpotAnim[] cache;
   private int anInt404;
   private int anInt405;
   private int anInt406 = -1;
   public Animation aAnimation_407;
   private final int[] anIntArray408 = new int[6];
   private final int[] anIntArray409 = new int[6];
   public int anInt410 = 128;
   public int anInt411 = 128;
   public int anInt412;
   public int anInt413;
   public int anInt414;
   public static MRUNodes aMRUNodes_415 = new MRUNodes(30);


   public static void unpackConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("spotanim.dat"));
      int length = stream.readUnsignedWord();
      if(cache == null) {
         cache = new SpotAnim[length];
      }

      for(int j = 0; j < length; ++j) {
         if(cache[j] == null) {
            cache[j] = new SpotAnim();
         }

         cache[j].anInt404 = j;
         cache[j].readValues(stream);
      }

   }

   private void readValues(Stream stream) {
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            return;
         }

         if(i == 1) {
            this.anInt405 = stream.readUnsignedWord();
         } else if(i == 2) {
            this.anInt406 = stream.readUnsignedWord();
            if(Animation.anims != null) {
               this.aAnimation_407 = Animation.anims[this.anInt406];
            }
         } else if(i == 4) {
            this.anInt410 = stream.readUnsignedWord();
         } else if(i == 5) {
            this.anInt411 = stream.readUnsignedWord();
         } else if(i == 6) {
            this.anInt412 = stream.readUnsignedWord();
         } else if(i == 7) {
            this.anInt413 = stream.readUnsignedByte();
         } else if(i == 8) {
            this.anInt414 = stream.readUnsignedByte();
         } else if(i >= 40 && i < 50) {
            this.anIntArray408[i - 40] = stream.readUnsignedWord();
         } else if(i >= 50 && i < 60) {
            this.anIntArray409[i - 50] = stream.readUnsignedWord();
         } else {
            System.out.println("Error unrecognised spotanim config code: " + i);
         }
      }
   }

   public Model getModel() {
      Model model = (Model)aMRUNodes_415.insertFromCache((long)this.anInt404);
      if(model != null) {
         return model;
      } else {
         model = Model.method462(this.anInt405);
         if(model == null) {
            return null;
         } else {
            for(int i = 0; i < 6; ++i) {
               if(this.anIntArray408[0] != 0) {
                  model.method476(this.anIntArray408[i], this.anIntArray409[i]);
               }
            }

            aMRUNodes_415.removeFromCache(model, (long)this.anInt404);
            return model;
         }
      }
   }

}
