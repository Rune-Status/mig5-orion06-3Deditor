package client;

import java.awt.Component;

final class Class5_Sub2_Sub1 extends Class5_Sub2 {

   private static SoundInterface anInterface2_1845;


   final int avail() {
      return anInterface2_1845.method4(32403);
   }

   final void close() {
      anInterface2_1845.method5(102);
   }

   final void method494(int i) throws Exception {
      anInterface2_1845.method2(26853, i);
   }

   final void write() {
      anInterface2_1845.method3(Class5_Sub2.anIntArray1317);
   }

   public static void reset() {
      anInterface2_1845 = null;
   }

   Class5_Sub2_Sub1(Component component) throws Exception {
      super(22050);
      anInterface2_1845 = null;
      anInterface2_1845.method1(component);
      this.method502(16384);
   }
}
