package client;


public final class MRUNodes {

   private final NodeSub emptyNodeSub = new NodeSub();
   private final int initialCount;
   private int spaceLeft;
   private final NodeCache nodeCache;
   private final NodeSubList nodeSubList = new NodeSubList();


   public MRUNodes(int i) {
      this.initialCount = i;
      this.spaceLeft = i;
      this.nodeCache = new NodeCache();
   }

   public NodeSub insertFromCache(long l) {
      NodeSub nodeSub = (NodeSub)this.nodeCache.findNodeByID(l);
      if(nodeSub != null) {
         this.nodeSubList.insertHead(nodeSub);
      }

      return nodeSub;
   }

   public void removeFromCache(NodeSub nodeSub, long l) {
      try {
         if(this.spaceLeft == 0) {
            NodeSub runtimeexception = this.nodeSubList.popTail();
            runtimeexception.unlink();
            runtimeexception.unlinkSub();
            if(runtimeexception == this.emptyNodeSub) {
               NodeSub nodeSub_2 = this.nodeSubList.popTail();
               nodeSub_2.unlink();
               nodeSub_2.unlinkSub();
            }
         } else {
            --this.spaceLeft;
         }

         this.nodeCache.removeFromCache(nodeSub, l);
         this.nodeSubList.insertHead(nodeSub);
      } catch (RuntimeException var6) {
         SignLink.reporterror("47547, " + nodeSub + ", " + l + ", " + 2 + ", " + var6.toString());
         throw new RuntimeException();
      }
   }

   public void unlinkAll() {
      while(true) {
         NodeSub nodeSub = this.nodeSubList.popTail();
         if(nodeSub == null) {
            this.spaceLeft = this.initialCount;
            return;
         }

         nodeSub.unlink();
         nodeSub.unlinkSub();
      }
   }
}
