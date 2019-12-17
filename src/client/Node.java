package client;


public class Node {

   public long id;
   public Node prev;
   public Node next;


   public final void unlink() {
      if(this.next != null) {
         this.next.prev = this.prev;
         this.prev.next = this.next;
         this.prev = null;
         this.next = null;
      }

   }

}
