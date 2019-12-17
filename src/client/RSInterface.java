package client;


public final class RSInterface {

   public Sprite sprite1;
   public Sprite hoverSprite1;
   public Sprite hoverSprite2;
   public int anInt208;
   public int originalWidth;
   public Sprite[] sprites;
   public static RSInterface[] interfaceCache;
   public int[] anIntArray212;
   public int anInt214;
   public int[] spritesX;
   public int anInt216;
   public int atActionType;
   public String spellName;
   public int anInt219;
   public int width;
   public String tooltip;
   public String selectedActionName;
   public boolean aBoolean223;
   public int scrollPosition;
   public String[] actions;
   public int[][] valueIndexArray;
   public boolean aBoolean227;
   public String aString228;
   public int anInt230;
   public int invSpritePadX;
   public int textColor;
   public int anInt233;
   public int mediaID;
   public boolean aBoolean235;
   public int parentID;
   public int spellUsableOn;
   private static MRUNodes aMRUNodes_238;
   public int anInt239;
   public int[] children;
   public int[] childX;
   public boolean usableItemInterface;
   public TextDrawingArea textDrawingAreas;
   public int invSpritePadY;
   public int[] anIntArray245;
   public int anInt246;
   public int[] spritesY;
   public String message;
   public boolean isInventoryInterface;
   public int id;
   public int[] invStackSizes;
   public int[] inv;
   public byte aByte254;
   private int anInt255;
   private int anInt256;
   public int anInt257;
   public int anInt258;
   public boolean aBoolean259;
   public Sprite sprite2;
   public int scrollMax;
   public int type;
   public int anInt263;
   private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
   public int anInt265;
   public boolean aBoolean266;
   public int height;
   public boolean aBoolean268;
   public int anInt269;
   public int anInt270;
   public int anInt271;
   public int[] childY;


   public void swapInventoryItems(int i, int j) {
      int k = this.inv[i];
      this.inv[i] = this.inv[j];
      this.inv[j] = k;
      k = this.invStackSizes[i];
      this.invStackSizes[i] = this.invStackSizes[j];
      this.invStackSizes[j] = k;
   }
   
   public static void unpack(StreamLoader streamLoader, TextDrawingArea[] textDrawingAreas, StreamLoader streamLoader_1) {
      aMRUNodes_238 = new MRUNodes('\uc350');
      Stream stream = new Stream(streamLoader.getDataForName("data"));
      //Stream stream = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "interfaces2.dat"));
      int i = -1;
      int j = stream.readUnsignedWord();
      interfaceCache = new RSInterface[j];

      while(stream.currentOffset < stream.buffer.length) {
         int k = stream.readUnsignedWord();
         if(k == '\uffff') {
            i = stream.readUnsignedWord();
            k = stream.readUnsignedWord();
         }

         RSInterface rsInterface = interfaceCache[k] = new RSInterface();
         rsInterface.id = k;
         rsInterface.parentID = i;
         rsInterface.type = stream.readUnsignedByte();
         rsInterface.atActionType = stream.readUnsignedByte();
         rsInterface.anInt214 = stream.readUnsignedWord();
         int width = stream.readUnsignedWord();
         rsInterface.width = width;
         if(rsInterface.type == 3)
        	 rsInterface.originalWidth = width;
         rsInterface.height = stream.readUnsignedWord();
         rsInterface.aByte254 = (byte)stream.readUnsignedByte();
         rsInterface.anInt230 = stream.readUnsignedByte();
         if(rsInterface.anInt230 != 0) {
            rsInterface.anInt230 = (rsInterface.anInt230 - 1 << 8) + stream.readUnsignedByte();
         } else {
            rsInterface.anInt230 = -1;
         }

         int i1 = stream.readUnsignedByte();
         int k1;
         if(i1 > 0) {
            rsInterface.anIntArray245 = new int[i1];
            rsInterface.anIntArray212 = new int[i1];

            for(k1 = 0; k1 < i1; ++k1) {
               rsInterface.anIntArray245[k1] = stream.readUnsignedByte();
               rsInterface.anIntArray212[k1] = stream.readUnsignedWord();
            }
         }

         k1 = stream.readUnsignedByte();
         int l2;
         int k4;
         if(k1 > 0) {
            rsInterface.valueIndexArray = new int[k1][];

            for(l2 = 0; l2 < k1; ++l2) {
               k4 = stream.readUnsignedWord();
               rsInterface.valueIndexArray[l2] = new int[k4];

               for(int s1 = 0; s1 < k4; ++s1) {
                  rsInterface.valueIndexArray[l2][s1] = stream.readUnsignedWord();
               }
            }
         }

         if(rsInterface.type == 0) {
            rsInterface.scrollMax = stream.readUnsignedWord();
            rsInterface.aBoolean266 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedWord();
            rsInterface.children = new int[l2];
            rsInterface.childX = new int[l2];
            rsInterface.childY = new int[l2];

            for(k4 = 0; k4 < l2; ++k4) {
               rsInterface.children[k4] = stream.readUnsignedWord();
               rsInterface.childX[k4] = stream.readSignedWord();
               rsInterface.childY[k4] = stream.readSignedWord();
            }
         }

         if(rsInterface.type == 1) {
            stream.readUnsignedWord();
            stream.readUnsignedByte();
         }

         if(rsInterface.type == 2) {
            rsInterface.inv = new int[rsInterface.width * rsInterface.height];
            rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
            rsInterface.aBoolean259 = stream.readUnsignedByte() == 1;
            rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
            rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
            rsInterface.aBoolean235 = stream.readUnsignedByte() == 1;
            rsInterface.invSpritePadX = stream.readUnsignedByte();
            rsInterface.invSpritePadY = stream.readUnsignedByte();
            rsInterface.spritesX = new int[20];
            rsInterface.spritesY = new int[20];
            rsInterface.sprites = new Sprite[20];

            for(l2 = 0; l2 < 20; ++l2) {
               k4 = stream.readUnsignedByte();
               if(k4 == 1) {
                  rsInterface.spritesX[l2] = stream.readSignedWord();
                  rsInterface.spritesY[l2] = stream.readSignedWord();
                  String var15 = stream.readString();
                  if(streamLoader_1 != null && var15.length() > 0) {
                     int i5 = var15.lastIndexOf(",");
                     if(var15.substring(0, i5).toLowerCase().equals("cache")){
                    	 int id = Integer.parseInt(var15.substring(i5 + 1));
                    	 rsInterface.sprites[l2] = Client.cacheSpriteI[id];
                     }else
                    	 rsInterface.sprites[l2] = method207(Integer.parseInt(var15.substring(i5 + 1)), streamLoader_1, var15.substring(0, i5));
                  }
               }
            }

            rsInterface.actions = new String[5];

            for(l2 = 0; l2 < 5; ++l2) {
               rsInterface.actions[l2] = stream.readString();
               if(rsInterface.actions[l2].length() == 0) {
                  rsInterface.actions[l2] = null;
               }
				if(rsInterface.parentID == 3824)
					rsInterface.actions[4] = "Buy 100";
				if(rsInterface.parentID == 3822)
					rsInterface.actions[4] = "Sell 100";
            }
         }

         if(rsInterface.type == 3) {
            rsInterface.aBoolean227 = stream.readUnsignedByte() == 1;
         }

         if(rsInterface.type == 4 || rsInterface.type == 1) {
            rsInterface.aBoolean223 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedByte();
            if(textDrawingAreas != null) {
               rsInterface.textDrawingAreas = textDrawingAreas[l2];
            }

            rsInterface.aBoolean268 = stream.readUnsignedByte() == 1;
         }

         if(rsInterface.type == 4) {
            rsInterface.message = stream.readString().replaceAll("RuneScape", Client.NAME).replaceAll("Runescape", Client.NAME).replaceAll("runescape", Client.NAME).replaceAll("Jagex", Client.NAME).replaceAll("jagex", Client.NAME);
            rsInterface.aString228 = stream.readString();
         }

         if(rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4) {
            rsInterface.textColor = stream.readDWord();
         }

         if(rsInterface.type == 3 || rsInterface.type == 4) {
            rsInterface.anInt219 = stream.readDWord();
            rsInterface.anInt216 = stream.readDWord();
            rsInterface.anInt239 = stream.readDWord();
         }

         if(rsInterface.type == 5 || rsInterface.type == 17) {
            String var14 = stream.readString();
            if(streamLoader_1 != null && var14.length() > 0) {
               k4 = var14.lastIndexOf(",");
               if(var14.substring(0, k4).toLowerCase().equals("cache")){
            	   int id = Integer.parseInt(var14.substring(k4 + 1));
            	   /*if(id == 7)
            		   rsInterface.hoverSprite1 = Client.cacheSpriteI[8];*/
            	   rsInterface.sprite1 = Client.cacheSpriteI[id];
               }else
            	   rsInterface.sprite1 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
            }

            var14 = stream.readString();
            if(streamLoader_1 != null && var14.length() > 0) {
               k4 = var14.lastIndexOf(",");
               if(var14.substring(0, k4).toLowerCase().equals("cache")){
            	   int id = Integer.parseInt(var14.substring(k4 + 1));
            	   rsInterface.sprite2 = Client.cacheSpriteI[id];
               }else
            	   rsInterface.sprite2 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
            }
            
            if(rsInterface.type == 17) {//hovers
            	var14 = stream.readString();
                if(streamLoader_1 != null && var14.length() > 0) {
                   k4 = var14.lastIndexOf(",");
                   if(var14.substring(0, k4).toLowerCase().equals("cache")){
                	   int id = Integer.parseInt(var14.substring(k4 + 1));
                	   rsInterface.hoverSprite1 = Client.cacheSpriteI[id];
                   }else
                	   rsInterface.hoverSprite1 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
                }
                
                var14 = stream.readString();
                if(streamLoader_1 != null && var14.length() > 0) {
                   k4 = var14.lastIndexOf(",");
                   if(var14.substring(0, k4).toLowerCase().equals("cache")){
                	   int id = Integer.parseInt(var14.substring(k4 + 1));
                	   rsInterface.hoverSprite2 = Client.cacheSpriteI[id];
                   }else
                	   rsInterface.hoverSprite2 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
                }
            }
            
         }

         if(rsInterface.type == 6) {
            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               rsInterface.anInt233 = 1;
               rsInterface.mediaID = (l2 - 1 << 8) + stream.readUnsignedByte();
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               rsInterface.anInt255 = 1;
               rsInterface.anInt256 = (l2 - 1 << 8) + stream.readUnsignedByte();
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               rsInterface.anInt257 = (l2 - 1 << 8) + stream.readUnsignedByte();
            } else {
               rsInterface.anInt257 = -1;
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               rsInterface.anInt258 = (l2 - 1 << 8) + stream.readUnsignedByte();
            } else {
               rsInterface.anInt258 = -1;
            }

            rsInterface.anInt269 = stream.readUnsignedWord();
            rsInterface.anInt270 = stream.readUnsignedWord();
            rsInterface.anInt271 = stream.readUnsignedWord();
         }

         if(rsInterface.type == 7) {
            rsInterface.inv = new int[rsInterface.width * rsInterface.height];
            rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
            rsInterface.aBoolean223 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedByte();
            if(textDrawingAreas != null) {
               rsInterface.textDrawingAreas = textDrawingAreas[l2];
            }

            rsInterface.aBoolean268 = stream.readUnsignedByte() == 1;
            rsInterface.textColor = stream.readDWord();
            rsInterface.invSpritePadX = stream.readSignedWord();
            rsInterface.invSpritePadY = stream.readSignedWord();
            rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
            rsInterface.actions = new String[5];

            for(k4 = 0; k4 < 5; ++k4) {
               rsInterface.actions[k4] = stream.readString();
               if(rsInterface.actions[k4].length() == 0) {
                  rsInterface.actions[k4] = null;
               }
            }
         }

         if(rsInterface.atActionType == 2 || rsInterface.type == 2) {
            rsInterface.selectedActionName = stream.readString();
            rsInterface.spellName = stream.readString();
            rsInterface.spellUsableOn = stream.readUnsignedWord();
         }

         if(rsInterface.type == 8) {
            rsInterface.message = stream.readString();
         }

         if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
            rsInterface.tooltip = stream.readString();
            if(rsInterface.tooltip.length() == 0) {
               if(rsInterface.atActionType == 1) {
                  rsInterface.tooltip = "Ok";
               }

               if(rsInterface.atActionType == 4) {
                  rsInterface.tooltip = "Select";
               }

               if(rsInterface.atActionType == 5) {
                  rsInterface.tooltip = "Select";
               }

               if(rsInterface.atActionType == 6) {
                  rsInterface.tooltip = "Continue";
               }
            }
         }
      }

      aMRUNodes_238 = null;
   }

   private Model method206(int i, int j) {
      Model model = (Model)aMRUNodes_264.insertFromCache((long)((i << 16) + j));
      if(model != null) {
         return model;
      } else {
         if(i == 1) {
            model = Model.method462(j);
         }

         if(i == 2) {
            model = EntityDef.forID(j).method160();
         }

         if(i == 3) {
            model = Client.myPlayer.method453();
         }

         if(i == 4) {
            model = ItemDef.forID(j).method202(50);
         }

         if(i == 5) {
            model = null;
         }

         if(model != null) {
            aMRUNodes_264.removeFromCache(model, (long)((i << 16) + j));
         }

         return model;
      }
   }

   private static Sprite method207(int i, StreamLoader streamLoader, String s) {
      long l = (TextClass.method585(s) << 8) + (long)i;
      Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
      if(sprite != null) {
         return sprite;
      } else {
         try {
            sprite = new Sprite(streamLoader, s, i);
            aMRUNodes_238.removeFromCache(sprite, l);
            return sprite;
         } catch (Exception var7) {
            return null;
         }
      }
   }

   public static void method208(boolean flag, Model model) {
      byte i = 0;
      byte j = 5;
      if(!flag) {
         aMRUNodes_264.unlinkAll();
         if(model != null && j != 4) {
            aMRUNodes_264.removeFromCache(model, (long)((j << 16) + i));
         }

      }
   }

   public Model method209(int j, int k, boolean flag) {
      Model model;
      if(flag) {
         model = this.method206(this.anInt255, this.anInt256);
      } else {
         model = this.method206(this.anInt233, this.mediaID);
      }

      if(model == null) {
         return null;
      } else if(k == -1 && j == -1 && model.anIntArray1640 == null) {
         return model;
      } else {
         Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
         if(k != -1 || j != -1) {
            model_1.method469();
         }

         if(k != -1) {
            model_1.method470(k);
         }

         if(j != -1) {
            model_1.method470(j);
         }

         model_1.method479(64, 768, -50, -10, -50, true);
         return model_1;
      }
   }

}
