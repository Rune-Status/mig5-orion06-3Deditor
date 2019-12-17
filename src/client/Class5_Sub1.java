package client;

import sun.audio.AudioPlayer;

final class Class5_Sub1 extends Class5 {

   private InputStream_Sub1 anInputStream_Sub1_1310 = new InputStream_Sub1();


   Class5_Sub1() {
      super(8000);
      AudioPlayer.player.start(this.anInputStream_Sub1_1310);
   }

   final void stop() {
      AudioPlayer.player.stop(this.anInputStream_Sub1_1310);
      InputStream_Sub1 var1 = this.anInputStream_Sub1_1310;
      synchronized(this.anInputStream_Sub1_1310) {
         this.anInputStream_Sub1_1310.aBoolean31 = true;
      }
   }
}
