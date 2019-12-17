package client;


final class Class3_Sub7_Sub1 extends Class3_Sub7 {

   private Deque[] aClass60Array1810 = new Deque[8];
   private Deque aClass60_1811 = new Deque();
   private int anInt1812 = 16;
   private int anInt1813 = -1;
   private int anInt1814 = 0;
   private int anInt1815 = 0;


   final synchronized void method380(int i) {
      while(true) {
         if(this.anInt1813 < 0) {
            this.method381(i);
         } else if(this.anInt1814 + i < this.anInt1813) {
            this.anInt1814 += i;
            this.method381(i);
         } else {
            int i_0_ = this.anInt1813 - this.anInt1814;
            this.method381(i_0_);
            i -= i_0_;
            this.anInt1814 += i_0_;
            this.method382();
            Class3_Sub5 class3_sub5 = (Class3_Sub5)this.aClass60_1811.getFront();
            synchronized(class3_sub5) {
               int i_1_ = class3_sub5.method372(this);
               if(i_1_ < 0) {
                  class3_sub5.anInt1206 = 0;
                  this.method385(class3_sub5);
               } else {
                  class3_sub5.anInt1206 = i_1_;
                  this.method383(class3_sub5.next, class3_sub5);
               }
            }

            if(i != 0) {
               continue;
            }
         }

         return;
      }
   }

   private final void method381(int i) {
      this.anInt1815 -= i;
      if(this.anInt1815 < 0) {
         this.anInt1815 = 0;
      }

      for(int i_2_ = 0; i_2_ < 8; ++i_2_) {
         Deque class60 = this.aClass60Array1810[i_2_];

         for(Class3_Sub7 class3_sub7 = (Class3_Sub7)class60.getFront(); class3_sub7 != null; class3_sub7 = (Class3_Sub7)class60.getNext()) {
            class3_sub7.method380(i);
         }
      }

   }

   private final void method382() {
      if(this.anInt1814 > 0) {
         for(Class3_Sub5 class3_sub5 = (Class3_Sub5)this.aClass60_1811.getFront(); class3_sub5 != null; class3_sub5 = (Class3_Sub5)this.aClass60_1811.getNext()) {
            class3_sub5.anInt1206 -= this.anInt1814;
         }

         this.anInt1813 -= this.anInt1814;
         this.anInt1814 = 0;
      }

   }

   final synchronized int method378(int[] is, int i, int i_3_) {
      int i_4_;
      do {
         if(this.anInt1813 < 0) {
            return this.method387(is, i, i_3_);
         }

         if(this.anInt1814 + i_3_ < this.anInt1813) {
            this.anInt1814 += i_3_;
            return this.method387(is, i, i_3_);
         }

         int i_5_ = this.anInt1813 - this.anInt1814;
         i_4_ = this.method387(is, i, i_5_);
         i += i_5_;
         i_3_ -= i_5_;
         this.anInt1814 += i_5_;
         this.method382();
         Class3_Sub5 class3_sub5 = (Class3_Sub5)this.aClass60_1811.getFront();
         synchronized(class3_sub5) {
            int i_6_ = class3_sub5.method372(this);
            if(i_6_ < 0) {
               class3_sub5.anInt1206 = 0;
               this.method385(class3_sub5);
            } else {
               class3_sub5.anInt1206 = i_6_;
               this.method383(class3_sub5.next, class3_sub5);
            }
         }
      } while(i_3_ != 0);

      return i_4_;
   }

   private final void method383(Node class3, Class3_Sub5 class3_sub5) {
      while(class3 != this.aClass60_1811.head && ((Class3_Sub5)class3).anInt1206 <= class3_sub5.anInt1206) {
         class3 = class3.next;
      }

      this.aClass60_1811.method894(class3_sub5, class3);
      this.anInt1813 = ((Class3_Sub5)this.aClass60_1811.head.next).anInt1206;
   }

   final synchronized void method384(Class3_Sub7 class3_sub7) {
      Deque class60 = this.aClass60Array1810[method386(class3_sub7)];
      class60.insertFront(class3_sub7);
   }

   private final void method385(Class3_Sub5 class3_sub5) {
      class3_sub5.unlink();
      class3_sub5.method371();
      Node class3 = this.aClass60_1811.head.next;
      if(class3 == this.aClass60_1811.head) {
         this.anInt1813 = -1;
      } else {
         this.anInt1813 = ((Class3_Sub5)class3).anInt1206;
      }

   }

   private static final int method386(Class3_Sub7 class3_sub7) {
      return class3_sub7.method379() >> 5;
   }

   private final int method387(int[] is, int i, int i_7_) {
      this.anInt1815 -= i_7_;
      int i_11_;
      Deque i_12_;
      Class3_Sub7 i_13_;
      int i_14_;
      if(this.anInt1815 <= 0) {
         this.anInt1815 += Client.anInt197 >> 4;

         for(i_11_ = 0; i_11_ < 8; ++i_11_) {
            i_12_ = this.aClass60Array1810[i_11_];

            for(i_13_ = (Class3_Sub7)i_12_.getFront(); i_13_ != null; i_13_ = (Class3_Sub7)i_12_.getNext()) {
               i_14_ = method386(i_13_);
               if(i_14_ != i_11_) {
                  this.aClass60Array1810[i_14_].insertFront(i_13_);
               }
            }
         }
      }

      for(i_11_ = 0; i_11_ < 8; ++i_11_) {
         i_12_ = this.aClass60Array1810[i_11_];

         for(i_13_ = (Class3_Sub7)i_12_.getFront(); i_13_ != null; i_13_ = (Class3_Sub7)i_12_.getNext()) {
            i_13_.aBoolean1221 = false;
            if(i_13_.aClass3_Sub9_1220 != null) {
               i_13_.aClass3_Sub9_1220.anInt1240 = 0;
            }
         }
      }

      i_11_ = 0;
      int var14 = 255;

      for(int var15 = 7; var14 != 0; --var15) {
         int i_15_;
         if(var15 < 0) {
            i_14_ = var15 & 3;
            i_15_ = -(var15 >> 2);
         } else {
            i_14_ = var15;
            i_15_ = 0;
         }

         for(int i_16_ = var14 >>> i_14_ & 286331153; i_16_ != 0; i_16_ >>>= 4) {
            if((i_16_ & 1) != 0) {
               var14 &= ~(1 << i_14_);
               Deque class60 = this.aClass60Array1810[i_14_];

               for(Class3_Sub7 class3_sub7 = (Class3_Sub7)class60.getFront(); class3_sub7 != null; class3_sub7 = (Class3_Sub7)class60.getNext()) {
                  if(!class3_sub7.aBoolean1221) {
                     Class3_Sub9 class3_sub9 = class3_sub7.aClass3_Sub9_1220;
                     if(class3_sub9 != null && class3_sub9.anInt1240 > i_15_) {
                        var14 |= 1 << i_14_;
                     } else {
                        if(i_11_ < this.anInt1812) {
                           int i_17_ = class3_sub7.method378(is, i, i_7_);
                           i_11_ += i_17_;
                           if(class3_sub9 != null) {
                              class3_sub9.anInt1240 += i_17_;
                           }
                        } else {
                           class3_sub7.method380(i_7_);
                        }

                        class3_sub7.aBoolean1221 = true;
                     }
                  }
               }
            }

            i_14_ += 4;
            ++i_15_;
         }
      }

      return i_11_;
   }

   public Class3_Sub7_Sub1() {
      for(int i = 0; i < 8; ++i) {
         this.aClass60Array1810[i] = new Deque();
      }

   }
}
