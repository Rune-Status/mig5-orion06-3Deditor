package client;


public final class ISAACRandomGen {

   private int count;
   private final int[] results = new int[256];
   private final int[] memory = new int[256];
   private int accumulator;
   private int lastResult;
   private int counter;


   public ISAACRandomGen(int[] seed) {
      System.arraycopy(seed, 0, this.results, 0, seed.length);
      this.initializeKeySet();
   }

   public int getNextKey() {
      if(this.count-- == 0) {
         this.isaac();
         this.count = 255;
      }

      return this.results[this.count];
   }

   private void isaac() {
      this.lastResult += ++this.counter;

      for(int i = 0; i < 256; ++i) {
         int j = this.memory[i];
         if((i & 3) == 0) {
            this.accumulator ^= this.accumulator << 13;
         } else if((i & 3) == 1) {
            this.accumulator ^= this.accumulator >>> 6;
         } else if((i & 3) == 2) {
            this.accumulator ^= this.accumulator << 2;
         } else if((i & 3) == 3) {
            this.accumulator ^= this.accumulator >>> 16;
         }

         this.accumulator += this.memory[i + 128 & 255];
         int k;
         this.memory[i] = k = this.memory[(j & 1020) >> 2] + this.accumulator + this.lastResult;
         this.results[i] = this.lastResult = this.memory[(k >> 8 & 1020) >> 2] + j;
      }

   }

   private void initializeKeySet() {
      int k2 = -1640531527;
      int j2 = -1640531527;
      int i2 = -1640531527;
      int l1 = -1640531527;
      int k1 = -1640531527;
      int j1 = -1640531527;
      int i1 = -1640531527;
      int l = -1640531527;

      int k;
      for(k = 0; k < 4; ++k) {
         l ^= i1 << 11;
         k1 += l;
         i1 += j1;
         i1 ^= j1 >>> 2;
         l1 += i1;
         j1 += k1;
         j1 ^= k1 << 8;
         i2 += j1;
         k1 += l1;
         k1 ^= l1 >>> 16;
         j2 += k1;
         l1 += i2;
         l1 ^= i2 << 10;
         k2 += l1;
         i2 += j2;
         i2 ^= j2 >>> 4;
         l += i2;
         j2 += k2;
         j2 ^= k2 << 8;
         i1 += j2;
         k2 += l;
         k2 ^= l >>> 9;
         j1 += k2;
         l += i1;
      }

      for(k = 0; k < 256; k += 8) {
         l += this.results[k];
         i1 += this.results[k + 1];
         j1 += this.results[k + 2];
         k1 += this.results[k + 3];
         l1 += this.results[k + 4];
         i2 += this.results[k + 5];
         j2 += this.results[k + 6];
         k2 += this.results[k + 7];
         l ^= i1 << 11;
         k1 += l;
         i1 += j1;
         i1 ^= j1 >>> 2;
         l1 += i1;
         j1 += k1;
         j1 ^= k1 << 8;
         i2 += j1;
         k1 += l1;
         k1 ^= l1 >>> 16;
         j2 += k1;
         l1 += i2;
         l1 ^= i2 << 10;
         k2 += l1;
         i2 += j2;
         i2 ^= j2 >>> 4;
         l += i2;
         j2 += k2;
         j2 ^= k2 << 8;
         i1 += j2;
         k2 += l;
         k2 ^= l >>> 9;
         j1 += k2;
         l += i1;
         this.memory[k] = l;
         this.memory[k + 1] = i1;
         this.memory[k + 2] = j1;
         this.memory[k + 3] = k1;
         this.memory[k + 4] = l1;
         this.memory[k + 5] = i2;
         this.memory[k + 6] = j2;
         this.memory[k + 7] = k2;
      }

      for(k = 0; k < 256; k += 8) {
         l += this.memory[k];
         i1 += this.memory[k + 1];
         j1 += this.memory[k + 2];
         k1 += this.memory[k + 3];
         l1 += this.memory[k + 4];
         i2 += this.memory[k + 5];
         j2 += this.memory[k + 6];
         k2 += this.memory[k + 7];
         l ^= i1 << 11;
         k1 += l;
         i1 += j1;
         i1 ^= j1 >>> 2;
         l1 += i1;
         j1 += k1;
         j1 ^= k1 << 8;
         i2 += j1;
         k1 += l1;
         k1 ^= l1 >>> 16;
         j2 += k1;
         l1 += i2;
         l1 ^= i2 << 10;
         k2 += l1;
         i2 += j2;
         i2 ^= j2 >>> 4;
         l += i2;
         j2 += k2;
         j2 ^= k2 << 8;
         i1 += j2;
         k2 += l;
         k2 ^= l >>> 9;
         j1 += k2;
         l += i1;
         this.memory[k] = l;
         this.memory[k + 1] = i1;
         this.memory[k + 2] = j1;
         this.memory[k + 3] = k1;
         this.memory[k + 4] = l1;
         this.memory[k + 5] = i2;
         this.memory[k + 6] = j2;
         this.memory[k + 7] = k2;
      }

      this.isaac();
      this.count = 256;
   }
}
