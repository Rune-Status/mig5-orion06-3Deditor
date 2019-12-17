package client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

final class Class5_Sub2_Sub2 extends Class5_Sub2 {

   private AudioFormat anAudioFormat1846 = new AudioFormat(22050.0F, 16, 1, true, false);
   private SourceDataLine aSourceDataLine1847;
   private byte[] aByteArray1848 = new byte[512];
   static Class aClass1849;


   final void method494(int i) throws LineUnavailableException {
      try {
         Info lineunavailableexception = new Info(aClass1849 == null?(aClass1849 = method504("javax.sound.sampled.SourceDataLine")):aClass1849, this.anAudioFormat1846, i * 2);
         this.aSourceDataLine1847 = (SourceDataLine)AudioSystem.getLine(lineunavailableexception);
         this.aSourceDataLine1847.open();
         this.aSourceDataLine1847.start();
      } catch (LineUnavailableException var3) {
         this.aSourceDataLine1847 = null;
         throw var3;
      }
   }

   final int avail() {
      return this.aSourceDataLine1847.available() >> 1;
   }

   Class5_Sub2_Sub2() throws Exception {
      super(22050);
   }

   final void close() {
      if(this.aSourceDataLine1847 != null) {
         this.aSourceDataLine1847.close();
         this.aSourceDataLine1847 = null;
      }

   }

   final void write() {
      for(int i = 0; i < 256; ++i) {
         int i_0_ = anIntArray1317[i];
         if((i_0_ + 8388608 & -16777216) != 0) {
            i_0_ = 8388607 ^ i_0_ >> 31;
         }

         this.aByteArray1848[i * 2] = (byte)(i_0_ >> 8);
         this.aByteArray1848[i * 2 + 1] = (byte)(i_0_ >> 16);
      }

      this.aSourceDataLine1847.write(this.aByteArray1848, 0, 512);
   }

   static Class method504(String string) {
      Class var_class = null;

      try {
         var_class = Class.forName(string);
      } catch (ClassNotFoundException var5) {
         ClassNotFoundException classnotfoundexception = var5;

         try {
            throw (new NoClassDefFoundError()).initCause(classnotfoundexception);
         } catch (Throwable var4) {
            var4.printStackTrace();
         }
      }

      return var_class;
   }
}
