package client;


final class MouseDetection implements Runnable {

   private Client clientInstance;
   public final Object syncObject = new Object();
   public final int[] coordsY = new int[500];
   public boolean running = true;
   public final int[] coordsX = new int[500];
   public int coordsIndex;


   public void run() {
      while(this.running) {
         Object _ex = this.syncObject;
         synchronized(this.syncObject) {
            if(this.coordsIndex < 500) {
               this.coordsX[this.coordsIndex] = this.clientInstance.mouseX;
               this.coordsY[this.coordsIndex] = this.clientInstance.mouseY;
               ++this.coordsIndex;
            }
         }

         try {
            Thread.sleep(50L);
         } catch (Exception var3) {
            ;
         }
      }

   }

   public MouseDetection(Client client1) {
      this.clientInstance = client1;
   }
}
