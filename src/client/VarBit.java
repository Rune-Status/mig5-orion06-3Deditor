package client;


public final class VarBit {

   public static VarBit[] cache;
   public int anInt648;
   public int anInt649;
   public int anInt650;
   private boolean aBoolean651 = false;


   public static void unpackConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("varbit.dat"));
      int cacheSize = stream.readUnsignedWord();
      if(cache == null) {
         cache = new VarBit[cacheSize];
      }

      for(int j = 0; j < cacheSize; ++j) {
         if(cache[j] == null) {
            cache[j] = new VarBit();
         }

         cache[j].readValues(stream, j);
         if(cache[j].aBoolean651) {
            Varp.cache[cache[j].anInt648].aBoolean713 = true;
         }
      }

      if(stream.currentOffset != stream.buffer.length) {
         System.out.println("varbit load mismatch");
      }

   }

   private void readValues(Stream stream, int id) {
      while(true) {
         int j = stream.readUnsignedByte();
         if(j == 0) {
            return;
         }

         if(j == 1) {
			//id = config object id
            this.anInt648 = stream.readUnsignedWord();//config id
            this.anInt649 = stream.readUnsignedByte();//bits start
            this.anInt650 = stream.readUnsignedByte();//bits end
			/*if(id > 739 && id < 744)
			System.out.println(id+" "+this.anInt648+" "+this.anInt649+" "+this.anInt650);*/
         } else if(j == 10) {
            stream.readString();
         } else if(j == 2) {
            this.aBoolean651 = true;
         } else if(j == 3) {
            stream.readDWord();
         } else if(j == 4) {
            stream.readDWord();
         } else {
            System.out.println("Error unrecognised config code: " + j);
         }
      }
   }

}
