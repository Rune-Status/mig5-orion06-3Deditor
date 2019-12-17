package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

final class RSSocket implements Runnable {

   private InputStream inputStream;
   private OutputStream outputStream;
   private final Socket socket;
   private boolean closed = false;
   private final RSApplet rsApplet;
   private byte[] buffer;
   private int writeIndex;
   private int buffIndex;
   private boolean isWriter = false;
   private boolean hasIOError = false;


   public RSSocket(RSApplet RSApplet_, Socket socket1) throws IOException {
      this.rsApplet = RSApplet_;
      this.socket = socket1;
      this.socket.setSoTimeout(30000);
      this.socket.setTcpNoDelay(true);
      this.inputStream = this.socket.getInputStream();
      this.outputStream = this.socket.getOutputStream();
   }

   public void close() {
      this.closed = true;

      try {
         if(this.inputStream != null) {
            this.inputStream.close();
         }

         if(this.outputStream != null) {
            this.outputStream.close();
         }

         if(this.socket != null) {
            this.socket.close();
         }
      } catch (IOException var4) {
         System.out.println("Error closing stream");
      }

      this.isWriter = false;
      synchronized(this) {
         this.notify();
      }

      this.buffer = null;
   }

   public int read() throws IOException {
      return this.closed?0:this.inputStream.read();
   }

   public int available() throws IOException {
      return this.closed?0:this.inputStream.available();
   }

   public void flushInputStream(byte[] abyte0, int j) throws IOException {
      int i = 0;
      if(!this.closed) {
         while(j > 0) {
            int k = this.inputStream.read(abyte0, i, j);
            if(k <= 0) {
               throw new IOException("EOF");
            }

            i += k;
            j -= k;
         }

      }
   }

   public void queueBytes(int i, byte[] abyte0) throws IOException {
      if(!this.closed) {
         if(this.hasIOError) {
            this.hasIOError = false;
            throw new IOException("Error in writer thread");
         } else {
            if(this.buffer == null) {
               this.buffer = new byte[5000];
            }

            synchronized(this) {
               for(int l = 0; l < i; ++l) {
                  this.buffer[this.buffIndex] = abyte0[l];
                  this.buffIndex = (this.buffIndex + 1) % 5000;
                  if(this.buffIndex == (this.writeIndex + 4900) % 5000) {
                     throw new IOException("buffer overflow");
                  }
               }

               if(!this.isWriter) {
                  this.isWriter = true;
                  this.rsApplet.startRunnable(this, 3);
               }

               this.notify();
            }
         }
      }
   }

   public void run() {
      while(this.isWriter) {
         int i;
         int j;
         synchronized(this) {
            if(this.buffIndex == this.writeIndex) {
               try {
                  this.wait();
               } catch (InterruptedException var8) {
                  ;
               }
            }

            if(!this.isWriter) {
               return;
            }

            j = this.writeIndex;
            if(this.buffIndex >= this.writeIndex) {
               i = this.buffIndex - this.writeIndex;
            } else {
               i = 5000 - this.writeIndex;
            }
         }

         if(i > 0) {
            try {
               this.outputStream.write(this.buffer, j, i);
            } catch (IOException var7) {
               this.hasIOError = true;
            }

            this.writeIndex = (this.writeIndex + i) % 5000;

            try {
               if(this.buffIndex == this.writeIndex) {
                  this.outputStream.flush();
               }
            } catch (IOException var6) {
               this.hasIOError = true;
            }
         }
      }

   }

   public void printDebug() {
      System.out.println("dummy:" + this.closed);
      System.out.println("tcycl:" + this.writeIndex);
      System.out.println("tnum:" + this.buffIndex);
      System.out.println("writer:" + this.isWriter);
      System.out.println("ioerror:" + this.hasIOError);

      try {
         System.out.println("available:" + this.available());
      } catch (IOException var2) {
         ;
      }

   }
}
