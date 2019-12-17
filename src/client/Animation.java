package client;


public final class Animation {

   public static Animation[] anims;
   public int anInt352;
   public int[] anIntArray353;
   public int[] anIntArray354;
   private int[] anIntArray355;
   public int anInt356 = -1;
   public int[] anIntArray357;
   public boolean aBoolean358 = false;
   public int anInt359 = 5;
   public int anInt360 = -1;
   public int anInt361 = -1;
   public int anInt362 = 99;
   public int anInt363 = -1;
   public int anInt364 = -1;
   public int anInt365 = 2;
   public static int anInt367;


   public static void unpackConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
      int length = stream.readUnsignedWord();
      if(anims == null) {
         anims = new Animation[length];
      }

      for(int j = 0; j < length; ++j) {
         if(anims[j] == null) {
            anims[j] = new Animation();
         }

         anims[j].readValues(stream);
      }

   }

   public int method258(int i) {
      int j = this.anIntArray355[i];
      if(j == 0) {
         Class36 class36 = Class36.method531(this.anIntArray353[i]);
         if(class36 != null) {
            j = this.anIntArray355[i] = class36.anInt636;
         }
      }

      if(j == 0) {
         j = 1;
      }

      return j;
   }

   private void readValues(Stream stream) {
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            if(this.anInt352 == 0) {
               this.anInt352 = 1;
               this.anIntArray353 = new int[1];
               this.anIntArray353[0] = -1;
               this.anIntArray354 = new int[1];
               this.anIntArray354[0] = -1;
               this.anIntArray355 = new int[1];
               this.anIntArray355[0] = -1;
            }

            if(this.anInt363 == -1) {
               if(this.anIntArray357 != null) {
                  this.anInt363 = 2;
               } else {
                  this.anInt363 = 0;
               }
            }

            if(this.anInt364 == -1) {
               if(this.anIntArray357 != null) {
                  this.anInt364 = 2;
                  return;
               }

               this.anInt364 = 0;
            }

            return;
         }

         int k;
         if(i == 1) {
            this.anInt352 = stream.readUnsignedByte();
            this.anIntArray353 = new int[this.anInt352];
            this.anIntArray354 = new int[this.anInt352];
            this.anIntArray355 = new int[this.anInt352];

            for(k = 0; k < this.anInt352; ++k) {
               this.anIntArray353[k] = stream.readUnsignedWord();
               this.anIntArray354[k] = stream.readUnsignedWord();
               if(this.anIntArray354[k] == '\uffff') {
                  this.anIntArray354[k] = -1;
               }

               this.anIntArray355[k] = stream.readUnsignedWord();
            }
         } else if(i == 2) {
            this.anInt356 = stream.readUnsignedWord();
         } else if(i != 3) {
            if(i == 4) {
               this.aBoolean358 = true;
            } else if(i == 5) {
               this.anInt359 = stream.readUnsignedByte();
            } else if(i == 6) {
               this.anInt360 = stream.readUnsignedWord();
            } else if(i == 7) {
               this.anInt361 = stream.readUnsignedWord();
            } else if(i == 8) {
               this.anInt362 = stream.readUnsignedByte();
            } else if(i == 9) {
               this.anInt363 = stream.readUnsignedByte();
            } else if(i == 10) {
               this.anInt364 = stream.readUnsignedByte();
            } else if(i == 11) {
               this.anInt365 = stream.readUnsignedByte();
            } else if(i == 12) {
               stream.readDWord();
            } else {
               System.out.println("Error unrecognised seq config code: " + i);
            }
         } else {
            k = stream.readUnsignedByte();
            this.anIntArray357 = new int[k + 1];

            for(int l = 0; l < k; ++l) {
               this.anIntArray357[l] = stream.readUnsignedByte();
            }

            this.anIntArray357[k] = 9999999;
         }
      }
   }

}
