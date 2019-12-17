package client;


abstract class Class5_Sub2 extends Class5 implements Runnable {

   private long aLong1311 = 0L;
   private int anInt1312 = 0;
   private int anInt1313 = 0;
   private int anInt1314 = 0;
   private boolean aBoolean1315 = false;
   private long aLong1316;
   static int[] anIntArray1317 = new int[256];
   private int anInt1318;
   private int[] anIntArray1319 = new int[512];
   private long aLong1320;
   private int anInt1321 = 0;
   private int anInt1322;
   private int anInt1323 = 2560;
   private int anInt1324;


   abstract void method494(int var1) throws Exception;

   abstract void close();

   private final void method496(long l) throws Exception {
      this.method494(this.anInt1324);

      while(true) {
         int i = this.avail();
         if(i < this.anInt1323) {
            this.anInt1322 = 0;
            this.anInt1318 = 0;
            this.aLong1320 = l;
            this.aLong1316 = l;
            return;
         }

         this.write();
      }
   }

   public final void run() {
      while(true) {
         try {
            synchronized(this) {
               if(this.aBoolean1315) {
                  if(this.aLong1311 == 0L) {
                     this.close();
                  }

                  this.aBoolean1315 = false;
                  return;
               }

               this.method489(System.currentTimeMillis());
            }

            Client.sleep(5L);
         } catch (Exception var4) {
            return;
         }
      }
   }

   abstract void write() throws Exception;

   public static void reset() {
      anIntArray1317 = null;
   }

   private final void method499(long l) {
      if(this.aLong1311 != 0L) {
         while(this.aLong1320 < l) {
            Client.method493(256);
            this.aLong1320 += (long)(256000 / Client.anInt197);
         }

         if(l < this.aLong1311) {
            return;
         }

         try {
            this.method496(l);
         } catch (Exception var8) {
            this.close();
            this.aLong1311 += 5000L;
            return;
         }

         this.aLong1311 = 0L;
      }

      int i;
      while(this.aLong1320 < l) {
         this.aLong1320 += (long)(250880 / Client.anInt197);

         try {
            i = this.avail();
         } catch (Exception var7) {
            this.close();
            this.aLong1311 = l;
            return;
         }

         this.method500(i);
         int i_0_ = this.anInt1314 * 3 / 512 - this.anInt1312 * 2;
         if(i_0_ < 0) {
            i_0_ = 0;
         } else if(i_0_ > this.anInt1313) {
            i_0_ = this.anInt1313;
         }

         this.anInt1323 = this.anInt1324 - 256 - i_0_;
         if(this.anInt1323 < 256) {
            this.anInt1323 = 256;
         }

         if(this.anInt1324 < 16384) {
            if(i >= this.anInt1324) {
               this.anInt1322 += 5;
               if(this.anInt1322 >= 100) {
                  this.close();
                  this.anInt1324 += 2048;
                  this.aLong1311 = l;
                  return;
               }
            } else if(i != this.anInt1318 && this.anInt1322 > 0) {
               --this.anInt1322;
            }
         }

         this.anInt1318 = i;
         if(i < this.anInt1323) {
            break;
         }

         Client.method486(anIntArray1317, 256);

         try {
            this.write();
         } catch (Exception var6) {
            this.close();
            this.aLong1311 = l;
            return;
         }

         this.aLong1316 = l;
         this.anInt1318 -= 256;
      }

      if(l >= this.aLong1316 + 5000L) {
         this.close();
         this.aLong1311 = l;

         for(i = 0; i < 512; ++i) {
            this.anIntArray1319[i] = 0;
         }

         this.anInt1312 = this.anInt1313 = this.anInt1314 = 0;
      }

   }

   Class5_Sub2(int i) throws Exception {
      super(i);
   }

   private final void method500(int i) {
      int i_1_ = i - this.anInt1323;
      int i_2_ = this.anIntArray1319[this.anInt1321];
      this.anIntArray1319[this.anInt1321] = i_1_;
      this.anInt1314 += i_1_ - i_2_;
      int i_3_ = this.anInt1321 + 1 & 511;
      if(i_1_ > this.anInt1313) {
         this.anInt1313 = i_1_;
      }

      if(i_1_ < this.anInt1312) {
         this.anInt1312 = i_1_;
      }

      int i_7_;
      int i_8_;
      int i_9_;
      if(i_2_ == this.anInt1313) {
         i_7_ = i_1_;

         for(i_8_ = i_3_; i_8_ != this.anInt1321 && i_7_ < this.anInt1313; i_8_ = i_8_ + 1 & 511) {
            i_9_ = this.anIntArray1319[i_8_];
            if(i_9_ > i_7_) {
               i_7_ = i_9_;
            }
         }

         this.anInt1313 = i_7_;
      }

      if(i_2_ == this.anInt1312) {
         i_7_ = i_1_;

         for(i_8_ = i_3_; i_8_ != this.anInt1321 && i_7_ > this.anInt1312; i_8_ = i_8_ + 1 & 511) {
            i_9_ = this.anIntArray1319[i_8_];
            if(i_9_ < i_7_) {
               i_7_ = i_9_;
            }
         }

         this.anInt1312 = i_7_;
      }

      this.anInt1321 = i_3_;
   }

   final void stop() {
      synchronized(this) {
         this.aBoolean1315 = true;
      }

      while(true) {
         synchronized(this) {
            if(!this.aBoolean1315) {
               return;
            }
         }

         Client.sleep(50L);
      }
   }

   abstract int avail() throws Exception;

   final synchronized void method489(long l) {
      this.method499(l);
      if(this.aLong1320 < l) {
         this.aLong1320 = l;
      }

   }

   final void method502(int i) throws Exception {
      this.anInt1324 = i;
      this.method496(System.currentTimeMillis());
      Thread thread = new Thread(this);
      thread.setDaemon(true);
      thread.start();
      thread.setPriority(10);
   }

}
