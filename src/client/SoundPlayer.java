package client;

import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl.Type;

public class SoundPlayer implements Runnable {

   private AudioInputStream stream;
   private Info info;
   private Clip sound;
   private InputStream file;
   private Thread player;
   private int delay;
   private int level;
   public static int volumeLevel;


   public SoundPlayer(InputStream file, int level, int delay) {
      if(level != 0 && volumeLevel != 4 && level - volumeLevel > 0) {
         this.file = file;
         this.level = level;
         this.delay = delay;
         this.player = new Thread(this);
         this.player.start();
      }
   }

   public void run() {
      try {
         this.stream = AudioSystem.getAudioInputStream(this.file);
         this.info = new Info(Clip.class, this.stream.getFormat());
         this.sound = (Clip)AudioSystem.getLine(this.info);
         this.sound.open(this.stream);
         FloatControl e = (FloatControl)this.sound.getControl(Type.MASTER_GAIN);
         e.setValue(this.getValue(this.level - volumeLevel));
         System.out.println(this.level + ", " + volumeLevel + ", " + (this.level - volumeLevel));
         if(this.delay > 0) {
            Thread.sleep((long)this.delay);
         }

         this.sound.start();

         while(this.sound.isActive()) {
            Thread.sleep(250L);
         }

         Thread.sleep(10000L);
         this.sound.close();
         this.stream.close();
         this.player.interrupt();
      } catch (Exception var2) {
         this.player.interrupt();
         var2.printStackTrace();
      }

   }

   public float getValue(int level) {
      switch(level) {
      case 1:
         return -80.0F;
      case 2:
         return -70.0F;
      case 3:
         return -60.0F;
      case 4:
         return -50.0F;
      case 5:
         return -40.0F;
      case 6:
         return -30.0F;
      case 7:
         return -20.0F;
      case 8:
         return -10.0F;
      case 9:
         return -0.0F;
      case 10:
         return 6.0F;
      default:
         return 0.0F;
      }
   }
}
