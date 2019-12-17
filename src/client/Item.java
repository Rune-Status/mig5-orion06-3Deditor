package client;

import Editor.ItemSpawn;


final class Item extends Animable {

   public int ID;
   public int x;
   public int y;
   public int anInt1559;
   public ItemSpawn itemSpawn;

   public final Model getRotatedModel() {
      ItemDef itemDef = ItemDef.forID(this.ID);
      return itemDef.method201(this.anInt1559);
   }

}
