package client;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Hashtable;

final class RSImageProducer implements ImageProducer, ImageObserver {

   public final int[] anIntArray315;
   private final int anInt316;
   private final int anInt317;
   private final ColorModel aColorModel318;
   private ImageConsumer anImageConsumer319;
   private final Image anImage320;


   public RSImageProducer(int i, int j, Component component) {
      this.anInt316 = i;
      this.anInt317 = j;
      this.anIntArray315 = new int[i * j];
      this.aColorModel318 = new DirectColorModel(32, 16711680, '\uff00', 255);
      this.anImage320 = component.createImage(this);
      this.method239();
      component.prepareImage(this.anImage320, this);
      this.method239();
      component.prepareImage(this.anImage320, this);
      this.method239();
      component.prepareImage(this.anImage320, this);
      this.initDrawingArea();
   }

   public void initDrawingArea() {
      DrawingArea.initDrawingArea(this.anInt317, this.anInt316, this.anIntArray315);
   }

   public void drawGraphics(int i, Graphics g, int k) {
      this.method239();
      g.drawImage(this.anImage320, k, i, this);
   }

   public synchronized void addConsumer(ImageConsumer imageconsumer) {
      this.anImageConsumer319 = imageconsumer;
      imageconsumer.setDimensions(this.anInt316, this.anInt317);
      imageconsumer.setProperties((Hashtable)null);
      imageconsumer.setColorModel(this.aColorModel318);
      imageconsumer.setHints(14);
   }

   public synchronized boolean isConsumer(ImageConsumer imageconsumer) {
      return this.anImageConsumer319 == imageconsumer;
   }

   public synchronized void removeConsumer(ImageConsumer imageconsumer) {
      if(this.anImageConsumer319 == imageconsumer) {
         this.anImageConsumer319 = null;
      }

   }

   public void startProduction(ImageConsumer imageconsumer) {
      this.addConsumer(imageconsumer);
   }

   public void requestTopDownLeftRightResend(ImageConsumer imageconsumer) {
      System.out.println("TDLR");
   }

   private synchronized void method239() {
      if(this.anImageConsumer319 != null) {
         this.anImageConsumer319.setPixels(0, 0, this.anInt316, this.anInt317, this.aColorModel318, this.anIntArray315, 0, this.anInt316);
         this.anImageConsumer319.imageComplete(2);
      }

   }

   public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
      return true;
   }
}
