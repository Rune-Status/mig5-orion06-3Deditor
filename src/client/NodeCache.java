package client;


final class NodeCache {

   private final int size;
   private final Node[] cache;


   public NodeCache() {
      short i = 1024;
      this.size = i;
      this.cache = new Node[i];

      for(int k = 0; k < i; ++k) {
         Node node = this.cache[k] = new Node();
         node.prev = node;
         node.next = node;
      }

   }

   public Node findNodeByID(long l) {
      Node node = this.cache[(int)(l & (long)(this.size - 1))];

      for(Node node_1 = node.prev; node_1 != node; node_1 = node_1.prev) {
         if(node_1.id == l) {
            return node_1;
         }
      }

      return null;
   }

   public void removeFromCache(Node node, long l) {
      try {
         if(node.next != null) {
            node.unlink();
         }

         Node runtimeexception = this.cache[(int)(l & (long)(this.size - 1))];
         node.next = runtimeexception.next;
         node.prev = runtimeexception;
         node.next.prev = node;
         node.prev.next = node;
         node.id = l;
      } catch (RuntimeException var5) {
         SignLink.reporterror("91499, " + node + ", " + l + ", " + 7 + ", " + var5.toString());
         throw new RuntimeException();
      }
   }
}
