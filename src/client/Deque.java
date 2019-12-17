package client;


final class Deque {

   Node head = new Node();
   private Node current;


   final Node getBack() {
      Node node = this.head.prev;
      if(node == this.head) {
         this.current = null;
         return null;
      } else {
         this.current = node.prev;
         return node;
      }
   }

   final Node getFront() {
      Node node = this.head.next;
      if(node == this.head) {
         this.current = null;
         return null;
      } else {
         this.current = node.next;
         return node;
      }
   }

   final Node method889() {
      Node node = this.head.prev;
      if(node == this.head) {
         return null;
      } else {
         node.unlink();
         return node;
      }
   }

   final void insertFront(Node node) {
      if(node.prev != null) {
         node.unlink();
      }

      node.next = this.head.next;
      node.prev = this.head;
      node.prev.next = node;
      node.next.prev = node;
   }

   final void insertBack(Node node) {
      if(node.prev != null) {
         node.unlink();
      }

      node.next = this.head;
      node.prev = this.head.prev;
      node.prev.next = node;
      node.next.prev = node;
   }

   final void method894(Node class3, Node class3_27_) {
      if(class3.prev != null) {
         class3.unlink();
      }

      class3.next = class3_27_;
      class3.prev = class3_27_.prev;
      class3.prev.next = class3;
      class3.next.prev = class3;
   }

   final Node getNext() {
      Node node = this.current;
      if(this.head == node) {
         this.current = null;
         return null;
      } else {
         this.current = node.next;
         return node;
      }
   }

   final void clear() {
      while(true) {
         Node node = this.head.next;
         if(node == this.head) {
            return;
         }

         node.unlink();
      }
   }

   final Node popFront() {
      Node node = this.head.next;
      if(node == this.head) {
         return null;
      } else {
         node.unlink();
         return node;
      }
   }

   final Node getPrevious() {
      Node node = this.current;
      if(this.head == node) {
         this.current = null;
         return null;
      } else {
         this.current = node.prev;
         return node;
      }
   }

   public Deque() {
      this.head.next = this.head;
      this.head.prev = this.head;
   }
}
