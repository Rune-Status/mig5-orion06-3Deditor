package client;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;

final class RSFrame extends Frame {

   private final RSApplet rsApplet;


   public RSFrame(RSApplet RSApplet_, int i, int j) {
      this.rsApplet = RSApplet_;
      this.setTitle("Jagex");
      this.setResizable(false);
      this.setVisible(true);
      this.toFront();
      this.setSize(i + 8, j + 28);
      this.setLocationRelativeTo((Component)null);
   }

   public Graphics getGraphics() {
      Graphics g = super.getGraphics();
      g.translate(4, 24);
      return g;
   }

   public void update(Graphics g) {
      this.rsApplet.update(g);
   }

   public void paint(Graphics g) {
      this.rsApplet.paint(g);
   }
}
