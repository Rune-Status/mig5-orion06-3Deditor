package client;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

   private int anInt4;
   private int delayTime = 20;
   int minDelay = 1;
   private final long[] aLongArray7 = new long[10];
   int fps;
   boolean shouldDebug = false;
   int myWidth;
   int myHeight;
   Graphics graphics;
   RSImageProducer fullGameScreen;
   RSFrame gameFrame;
   private boolean shouldClearScreen = true;
   boolean awtFocus = true;
   int idleTime;
   int clickMode2;
   public int mouseX;
   public int mouseY;
   private int clickMode1;
   private int clickX;
   private int clickY;
   private long clickTime;
   int clickMode3;
   int saveClickX;
   int saveClickY;
   long aLong29;
   final int[] keyArray = new int[128];
   private final int[] charQueue = new int[128];
   private int readIndex;
   private int writeIndex;
   public static int anInt34;


   final void createClientFrame(int i, int j) {
      this.myWidth = j;
      this.myHeight = i;
      this.gameFrame = new RSFrame(this, this.myWidth, this.myHeight);
      this.graphics = this.getGameComponent().getGraphics();
      this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
      this.startRunnable(this, 1);
   }

   final void initClientFrame(int i, int j) {
      this.myWidth = j;
      this.myHeight = i;
      this.graphics = this.getGameComponent().getGraphics();
      this.fullGameScreen = new RSImageProducer(this.myWidth, this.myHeight, this.getGameComponent());
      this.startRunnable(this, 1);
   }

   public void run() {
      this.getGameComponent().addMouseListener(this);
      this.getGameComponent().addMouseMotionListener(this);
      this.getGameComponent().addKeyListener(this);
      this.getGameComponent().addFocusListener(this);
      if(this.gameFrame != null) {
         this.gameFrame.addWindowListener(this);
      }

      this.drawLoadingText(0, "Loading...");
      this.startUp();
      int i = 0;
      int j = 256;
      int k = 1;
      int i1 = 0;
      int j1 = 0;

      int i2;
      for(i2 = 0; i2 < 10; ++i2) {
         this.aLongArray7[i2] = System.currentTimeMillis();
      }

      System.currentTimeMillis();

      while(this.anInt4 >= 0) {
         if(this.anInt4 > 0) {
            --this.anInt4;
            if(this.anInt4 == 0) {
               this.exit();
               return;
            }
         }

         i2 = j;
         int j2 = k;
         j = 300;
         k = 1;
         long l1 = System.currentTimeMillis();
         if(this.aLongArray7[i] == 0L) {
            j = i2;
            k = j2;
         } else if(l1 > this.aLongArray7[i]) {
            j = (int)((long)(2560 * this.delayTime) / (l1 - this.aLongArray7[i]));
         }

         if(j < 25) {
            j = 25;
         }

         if(j > 256) {
            j = 256;
            k = (int)((long)this.delayTime - (l1 - this.aLongArray7[i]) / 10L);
         }

         if(k > this.delayTime) {
            k = this.delayTime;
         }

         this.aLongArray7[i] = l1;
         i = (i + 1) % 10;
         int l2;
         if(k > 1) {
            for(l2 = 0; l2 < 10; ++l2) {
               if(this.aLongArray7[l2] != 0L) {
                  this.aLongArray7[l2] += (long)k;
               }
            }
         }

         if(k < this.minDelay) {
            k = this.minDelay;
         }

         try {
            Thread.sleep((long)k);
         } catch (InterruptedException var12) {
            ++j1;
         }

         while(i1 < 256) {
            this.clickMode3 = this.clickMode1;
            this.saveClickX = this.clickX;
            this.saveClickY = this.clickY;
            this.aLong29 = this.clickTime;
            this.clickMode1 = 0;
            this.processGameLoop();
            this.readIndex = this.writeIndex;
            i1 += j;
         }

         i1 &= 255;
         if(this.delayTime > 0) {
            this.fps = 1000 * j / (this.delayTime * 256);
         }

         this.processDrawing();
         if(this.shouldDebug) {
            System.out.println("ntime:" + l1);

            for(l2 = 0; l2 < 10; ++l2) {
               int i3 = (i - l2 - 1 + 20) % 10;
               System.out.println("otim" + i3 + ":" + this.aLongArray7[i3]);
            }

            System.out.println("fps:" + this.fps + " ratio:" + j + " count:" + i1);
            System.out.println("del:" + k + " deltime:" + this.delayTime + " mindel:" + this.minDelay);
            System.out.println("intex:" + j1 + " opos:" + i);
            this.shouldDebug = false;
            j1 = 0;
         }
      }

      if(this.anInt4 == -1) {
         this.exit();
      }

   }

   private void exit() {
      this.anInt4 = -2;
      this.cleanUpForQuit();
      if(this.gameFrame != null) {
         try {
            Thread.sleep(1000L);
         } catch (Exception var3) {
            ;
         }

         try {
            System.exit(0);
         } catch (Throwable var2) {
            ;
         }
      }

   }

   final void method4(int i) {
      this.delayTime = 1000 / i;
   }

   public final void start() {
      if(this.anInt4 >= 0) {
         this.anInt4 = 0;
      }

   }

   public final void stop() {
      if(this.anInt4 >= 0) {
         this.anInt4 = 4000 / this.delayTime;
      }

   }

   public final void destroy() {
      this.anInt4 = -1;

      try {
         Thread.sleep(5000L);
      } catch (Exception var2) {
         ;
      }

      if(this.anInt4 == -1) {
         this.exit();
      }

   }

   public final void update(Graphics g) {
      if(this.graphics == null) {
         this.graphics = g;
      }

      this.shouldClearScreen = true;
      this.raiseWelcomeScreen();
   }

   public final void paint(Graphics g) {
      if(this.graphics == null) {
         this.graphics = g;
      }

      this.shouldClearScreen = true;
      this.raiseWelcomeScreen();
   }
   
   private ArrayList<ClickRecorder> clicks = new ArrayList<ClickRecorder>();

   public final void mousePressed(MouseEvent mouseevent) {
      int i = mouseevent.getX();
      int j = mouseevent.getY();
      if(this.gameFrame != null) {
         i -= 4;
         j -= 22;
      }

      this.idleTime = 0;
      int lastClickX = this.clickX;
      int lastClickY = this.clickY;
      this.clickX = i;
      this.clickY = j;
      long lastTime = this.clickTime;
      this.clickTime = System.currentTimeMillis();
      if(lastTime != 0){
			if (lastClickX == this.clickX && lastClickY == this.clickY) {
				int timeDiff = (int) (this.clickTime - lastTime);
				ClickRecorder click = new ClickRecorder(this.clickX, this.clickY, timeDiff);
				clicks.add(click);
				//System.out.println(clicks.size()+": "+this.clickX + "," + this.clickY + " " + (this.clickTime - lastTime));
				if(clicks.size() >= 50){
					int small = -1;
					int high = -1;
					for(ClickRecorder c1 : clicks){
						int time = c1.getTime();
						if(small < 0)
							small = time;
						if(high < 0)
							high = time;
						if(time < small)
							small = time;
						if(time > high)
							high = time;
					}
					int diff = high - small;
					if(diff <= 16 && diff >= 0){
						//System.out.println("Autoclicking: "+diff);
						this.sendCheatDetectionToServer(1);
					}
					clicks.clear();
				}
			} else {
				clicks.clear();
				//System.out.println("clear clicks");
			}
      }
      if(mouseevent.isMetaDown()) {
         this.clickMode1 = 2;
         this.clickMode2 = 2;
      } else {
         this.clickMode1 = 1;
         this.clickMode2 = 1;
      }

   }

   public final void mouseReleased(MouseEvent mouseevent) {
      this.idleTime = 0;
      this.clickMode2 = 0;
   }

   public final void mouseClicked(MouseEvent mouseevent) {}

   public final void mouseEntered(MouseEvent mouseevent) {}

   public final void mouseExited(MouseEvent mouseevent) {
      this.idleTime = 0;
      this.mouseX = -1;
      this.mouseY = -1;
   }

   public final void mouseDragged(MouseEvent mouseevent) {
      int i = mouseevent.getX();
      int j = mouseevent.getY();
      if(this.gameFrame != null) {
         i -= 4;
         j -= 22;
      }

      if (System.currentTimeMillis() - clickTime >= 250L
            || Math.abs(saveClickX - i) > 5 || Math.abs(saveClickY - j) > 5) {
            this.idleTime = 0;
            this.mouseX = i;
            this.mouseY = j;
        }
   }

   public final void mouseMoved(MouseEvent mouseevent) {
      int i = mouseevent.getX();
      int j = mouseevent.getY();
      if(this.gameFrame != null) {
         i -= 4;
         j -= 22;
      }

      if (System.currentTimeMillis() - clickTime >= 250L
            || Math.abs(saveClickX - i) > 5 || Math.abs(saveClickY - j) > 5) {
            this.idleTime = 0;
            this.mouseX = i;
            this.mouseY = j;
      }
   }

   public final void keyPressed(KeyEvent keyevent) {
      this.idleTime = 0;
      int i = keyevent.getKeyCode();
      int j = keyevent.getKeyChar();
      if(j < 30) {
         j = 0;
      }

      if(i == 37) {
         j = 1;
      }

      if(i == 39) {
         j = 2;
      }

      if(i == 38) {
         j = 3;
      }

      if(i == 40) {
         j = 4;
      }

      if(i == 17) {
         j = 5;
      }

      if(i == 8) {
         j = 8;
      }

      if(i == 127) {
         j = 8;
      }

      if(i == 9) {
         j = 9;
      }

      if(i == 10) {
         j = 10;
      }

      if(i >= 112 && i <= 123) {
         j = 1008 + i - 112;
      }

      if(i == 36) {
         j = 1000;
      }

      if(i == 35) {
         j = 1001;
      }

      if(i == 33) {
         j = 1002;
      }

      if(i == 34) {
         j = 1003;
      }

      if(j > 0 && j < 128) {
         this.keyArray[j] = 1;
      }

      if(j > 4) {
         this.charQueue[this.writeIndex] = j;
         this.writeIndex = this.writeIndex + 1 & 127;
      }

   }

   public final void keyReleased(KeyEvent keyevent) {
      this.idleTime = 0;
      int i = keyevent.getKeyCode();
      char c = keyevent.getKeyChar();
      if(c < 30) {
         c = 0;
      }

      if(i == 37) {
         c = 1;
      }

      if(i == 39) {
         c = 2;
      }

      if(i == 38) {
         c = 3;
      }

      if(i == 40) {
         c = 4;
      }

      if(i == 17) {
         c = 5;
      }

      if(i == 8) {
         c = 8;
      }

      if(i == 127) {
         c = 8;
      }

      if(i == 9) {
         c = 9;
      }

      if(i == 10) {
         c = 10;
      }

      if(c > 0 && c < 128) {
         this.keyArray[c] = 0;
      }

   }

   public final void keyTyped(KeyEvent keyevent) {}

   final int readChar(int dummy) {
      label20:
      while(true) {
         int k;
         if(dummy >= 0) {
            k = 1;

            while(true) {
               if(k <= 0) {
                  continue label20;
               }

               ++k;
            }
         }

         k = -1;
         if(this.writeIndex != this.readIndex) {
            k = this.charQueue[this.readIndex];
            this.readIndex = this.readIndex + 1 & 127;
         }

         return k;
      }
   }

   public final void focusGained(FocusEvent focusevent) {
      this.awtFocus = true;
      this.shouldClearScreen = true;
      this.raiseWelcomeScreen();
   }

   public final void focusLost(FocusEvent focusevent) {
      this.awtFocus = false;

      for(int i = 0; i < 128; ++i) {
         this.keyArray[i] = 0;
      }

   }

   public final void windowActivated(WindowEvent windowevent) {}

   public final void windowClosed(WindowEvent windowevent) {}

   public final void windowClosing(WindowEvent windowevent) {
      this.destroy();
   }

   public final void windowDeactivated(WindowEvent windowevent) {}

   public final void windowDeiconified(WindowEvent windowevent) {}

   public final void windowIconified(WindowEvent windowevent) {}

   public final void windowOpened(WindowEvent windowevent) {}

   void startUp() {}

   void processGameLoop() {}

   void cleanUpForQuit() {}

   void processDrawing() {}

   void raiseWelcomeScreen() {}
   
   void sendCheatDetectionToServer(int type) {}

   Component getGameComponent() {
      return (Component)(this.gameFrame != null?this.gameFrame:this);
   }

   public void startRunnable(Runnable runnable, int priority) {
      Thread thread = new Thread(runnable);
      thread.start();
      thread.setPriority(priority);
   }

   void drawLoadingText(int i, String s) {
      while(this.graphics == null) {
         this.graphics = this.getGameComponent().getGraphics();

         try {
            this.getGameComponent().repaint();
         } catch (Exception var9) {
            ;
         }

         try {
            Thread.sleep(1000L);
         } catch (Exception var8) {
            ;
         }
      }

      Font font = new Font("Helvetica", 1, 13);
      FontMetrics fontmetrics = this.getGameComponent().getFontMetrics(font);
      Font font1 = new Font("Helvetica", 0, 13);
      this.getGameComponent().getFontMetrics(font1);
      if(this.shouldClearScreen) {
         this.graphics.setColor(Color.black);
         this.graphics.fillRect(0, 0, this.myWidth, this.myHeight);
         this.shouldClearScreen = false;
      }

      Color color = new Color(140, 17, 17);
      int j = this.myHeight / 2 - 18;
      this.graphics.setColor(color);
      this.graphics.drawRect(this.myWidth / 2 - 152, j, 304, 34);
      this.graphics.fillRect(this.myWidth / 2 - 150, j + 2, i * 3, 30);
      this.graphics.setColor(Color.black);
      this.graphics.fillRect(this.myWidth / 2 - 150 + i * 3, j + 2, 300 - i * 3, 30);
      this.graphics.setFont(font);
      this.graphics.setColor(Color.white);
      this.graphics.drawString(s, (this.myWidth - fontmetrics.stringWidth(s)) / 2, j + 22);
   }

}
