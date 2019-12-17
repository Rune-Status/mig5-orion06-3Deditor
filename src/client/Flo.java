package client;


public final class Flo {

   public static Flo[] cache;
   public int anInt390;
   public int anInt391 = -1;
   public boolean aBoolean393 = true;
   public int anInt394;
   public int anInt395;
   public int anInt396;
   public int anInt397;
   public int anInt398;
   public int anInt399;
	public int mapColor;

   public static void unpackConfig(StreamLoader streamLoader) {
      Stream stream = new Stream(streamLoader.getDataForName("flo.dat"));
      int cacheSize = stream.readUnsignedWord();
      if(cache == null) {
         cache = new Flo[cacheSize];
      }

      for(int j = 0; j < cacheSize; ++j) {
         if(cache[j] == null) {
            cache[j] = new Flo();
         }

         cache[j].readValues(j, stream);
      }

   }
   
   //28,47,48,49,50,51
   //52,53,92?
   
	//32,58 - ice overlay
   
   //28 - 149
   //51 - 144
   
   private void readValues(int id, Stream stream) { 
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            return;
         }

         if(i == 1) {
            this.anInt390 = stream.read3Bytes();
			if(Client.isWinter){
				if(id == 28 || id == 47 || id == 48 || id == 49 || id == 50 || id == 51)
					anInt390 = 0xffffff;
			} else if(Client.isHween){
				if(id == 28)
					anInt390 = 1579032;
				if(id == 47)
					anInt390 = 2105376;
				if(id == 48)
					anInt390 = 2631720;
				if(id == 49)
					anInt390 = 3158064;
				if(id == 50)
					anInt390 = 3684408;
				if(id == 51)
					anInt390 = 4210752;
			}
            this.method262(this.anInt390);
         } else if(i == 2) {
            this.anInt391 = stream.readUnsignedByte();
            if(Client.isHween){
            	if(id == 5)
            		anInt391 = 25;//31 = lava, 25 = swamp water
            }
         } else if(i != 3) {
            if(i == 5) {
               this.aBoolean393 = false;
            } else if(i == 6) {
               stream.readString();
            } else if(i == 7) {
               int j = this.anInt394;
               int k = this.anInt395;
               int l = this.anInt396;
               int i1 = this.anInt397;
               int j1 = stream.read3Bytes();
			   mapColor = j1;
               this.method262(j1);
               this.anInt394 = j;
               this.anInt395 = k;
               this.anInt396 = l;
               this.anInt397 = i1;
               this.anInt398 = i1;
            } else {
               System.out.println("Error unrecognised config code: " + i);
            }
         }
      }
   }

   private void method262(int i) {
      double d = (double)(i >> 16 & 255) / 256.0D;
      double d1 = (double)(i >> 8 & 255) / 256.0D;
      double d2 = (double)(i & 255) / 256.0D;
      double d3 = d;
      if(d1 < d) {
         d3 = d1;
      }

      if(d2 < d3) {
         d3 = d2;
      }

      double d4 = d;
      if(d1 > d) {
         d4 = d1;
      }

      if(d2 > d4) {
         d4 = d2;
      }

      double d5 = 0.0D;
      double d6 = 0.0D;
      double d7 = (d3 + d4) / 2.0D;
      if(d3 != d4) {
         if(d7 < 0.5D) {
            d6 = (d4 - d3) / (d4 + d3);
         }

         if(d7 >= 0.5D) {
            d6 = (d4 - d3) / (2.0D - d4 - d3);
         }

         if(d == d4) {
            d5 = (d1 - d2) / (d4 - d3);
         } else if(d1 == d4) {
            d5 = 2.0D + (d2 - d) / (d4 - d3);
         } else if(d2 == d4) {
            d5 = 4.0D + (d - d1) / (d4 - d3);
         }
      }

      d5 /= 6.0D;
      this.anInt394 = (int)(d5 * 256.0D);
      this.anInt395 = (int)(d6 * 256.0D);
      this.anInt396 = (int)(d7 * 256.0D);
      if(this.anInt395 < 0) {
         this.anInt395 = 0;
      } else if(this.anInt395 > 255) {
         this.anInt395 = 255;
      }

      if(this.anInt396 < 0) {
         this.anInt396 = 0;
      } else if(this.anInt396 > 255) {
         this.anInt396 = 255;
      }

      if(d7 > 0.5D) {
         this.anInt398 = (int)((1.0D - d7) * d6 * 512.0D);
      } else {
         this.anInt398 = (int)(d7 * d6 * 512.0D);
      }

      if(this.anInt398 < 1) {
         this.anInt398 = 1;
      }

      this.anInt397 = (int)(d5 * (double)this.anInt398);
      int k = this.anInt394 + (int)(Math.random() * 16.0D) - 8;
      if(k < 0) {
         k = 0;
      } else if(k > 255) {
         k = 255;
      }

      int l = this.anInt395 + (int)(Math.random() * 48.0D) - 24;
      if(l < 0) {
         l = 0;
      } else if(l > 255) {
         l = 255;
      }

      int i1 = this.anInt396 + (int)(Math.random() * 48.0D) - 24;
      if(i1 < 0) {
         i1 = 0;
      } else if(i1 > 255) {
         i1 = 255;
      }

      this.anInt399 = this.method263(k, l, i1);
   }

   private int method263(int i, int j, int k) {
      if(k > 179) {
         j /= 2;
      }

      if(k > 192) {
         j /= 2;
      }

      if(k > 217) {
         j /= 2;
      }

      if(k > 243) {
         j /= 2;
      }

      return (i / 4 << 10) + (j / 32 << 7) + k / 2;
   }

}
