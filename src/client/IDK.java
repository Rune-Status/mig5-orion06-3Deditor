package client;


public final class IDK {

   public static int length;
   public static IDK[] cache;
   public int anInt657 = -1;
   private int[] anIntArray658;
   private final int[] anIntArray659 = new int[6];
   private final int[] anIntArray660 = new int[6];
   private final int[] anIntArray661 = new int[]{-1, -1, -1, -1, -1};
   public boolean aBoolean662 = false;


   public static void unpackConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("idk.dat"));
      length = stream.readUnsignedWord();
      if(cache == null) {
         cache = new IDK[length];
      }

      for(int j = 0; j < length; ++j) {
         if(cache[j] == null) {
            cache[j] = new IDK();
         }

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
            this.anInt657 = stream.readUnsignedByte();
         } else if(i == 2) {
            int j = stream.readUnsignedByte();
            this.anIntArray658 = new int[j];

            for(int k = 0; k < j; ++k) {
               this.anIntArray658[k] = stream.readUnsignedWord();
            }
         } else if(i == 3) {
            this.aBoolean662 = true;
         } else if(i >= 40 && i < 50) {
            this.anIntArray659[i - 40] = stream.readUnsignedWord();
         } else if(i >= 50 && i < 60) {
            this.anIntArray660[i - 50] = stream.readUnsignedWord();
         } else if(i >= 60 && i < 70) {
            this.anIntArray661[i - 60] = stream.readUnsignedWord();
         } else {
            System.out.println("Error unrecognised config code: " + i);
         }
      }
   }

   public boolean method537() {
      if(this.anIntArray658 == null) {
         return true;
      } else {
         boolean flag = true;

         for(int j = 0; j < this.anIntArray658.length; ++j) {
            if(!Model.method463(this.anIntArray658[j])) {
               flag = false;
            }
         }

         return flag;
      }
   }

   public Model method538() {
      if(this.anIntArray658 == null) {
         return null;
      } else {
         Model[] aclass30_sub2_sub4_sub6s = new Model[this.anIntArray658.length];

         for(int model = 0; model < this.anIntArray658.length; ++model) {
            aclass30_sub2_sub4_sub6s[model] = Model.method462(this.anIntArray658[model]);
         }

         Model var4;
         if(aclass30_sub2_sub4_sub6s.length == 1) {
            var4 = aclass30_sub2_sub4_sub6s[0];
         } else {
            var4 = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
         }

         for(int j = 0; j < 6 && this.anIntArray659[j] != 0; ++j) {
            var4.method476(this.anIntArray659[j], this.anIntArray660[j]);
         }

         return var4;
      }
   }

   public boolean method539() {
      boolean flag1 = true;

      for(int i = 0; i < 5; ++i) {
         if(this.anIntArray661[i] != -1 && !Model.method463(this.anIntArray661[i])) {
            flag1 = false;
         }
      }

      return flag1;
   }

   public Model method540() {
      Model[] aclass30_sub2_sub4_sub6s = new Model[5];
      int j = 0;

      for(int model = 0; model < 5; ++model) {
         if(this.anIntArray661[model] != -1) {
            aclass30_sub2_sub4_sub6s[j++] = Model.method462(this.anIntArray661[model]);
         }
      }

      Model var5 = new Model(j, aclass30_sub2_sub4_sub6s);

      for(int l = 0; l < 6 && this.anIntArray659[l] != 0; ++l) {
         var5.method476(this.anIntArray659[l], this.anIntArray660[l]);
      }

      return var5;
   }

}
