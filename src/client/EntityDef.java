package client;


public final class EntityDef {

   public int anInt55 = -1;
   private static int anInt56;
   private int anInt57 = -1;
   public int anInt58 = -1;
   private int anInt59 = -1;
   private static Stream stream;
   public int combatLevel = -1;
   public String name;
   public String[] actions;
   public int anInt67 = -1;
   public byte aByte68 = 1;
   private int[] anIntArray70;
   private static int[] streamIndices;
   private int[] anIntArray73;
   public int anInt75 = -1;
   private int[] anIntArray76;
   public int anInt77 = -1;
   public long type = -1L;
   public int anInt79 = 32;
   private static EntityDef[] cache;
   public static Client clientInstance;
   public int anInt83 = -1;
   public boolean aBoolean84 = true;
   private int anInt85;
   private int anInt86 = 128;
   public boolean aBoolean87 = true;
   public int[] childrenIDs;
   public byte[] description;
   private int anInt91 = 128;
   private int anInt92;
   public boolean aBoolean93 = false;
   private int[] anIntArray94;
   public static MRUNodes mruNodes = new MRUNodes(30);
   public boolean getModelValues = false;

   
   public static Sprite getSprite2(int i, int j, int k, int maxSize) {
		// System.out.println("Sprite for: " + i);
		/*if (k == 0) {
			Sprite sprite = (Sprite) mruNodes.insertFromCache(i);
			if (sprite != null && sprite.anInt1444 != j
					&& sprite.anInt1444 != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}*/
	   int rotateY = 200;
	   int rotateX = 200;
	   int offset1 = 0;
	   int offset2 = 0;
	   int unknown1 = 0;
		
		EntityDef npc = forID(i);
		if(npc.anIntArray94 == null)
			return null;
		/*
		 int l;
      if(super.anim >= 0 && super.anInt1529 == 0) {
         l = Animation.anims[super.anim].anIntArray353[super.anInt1527];
         int i1 = -1;
         if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
            i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(i1, l, Animation.anims[super.anim].anIntArray357);
      } else {
         l = -1;
         if(super.anInt1517 >= 0) {
            l = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(-1, l, (int[])null);
      }
		 */
		int l = -1;
		if(npc.anInt77 != -1)
			l = Animation.anims[npc.anInt77].anIntArray353[0];
		Model model = npc.method164(-1, l, (int[])null);
		if (model == null){
			return null;
		}
		/*if(npc.anInt77 != -1)
			model.method470(Animation.anims[npc.anInt77].anIntArray353[0]);*/
		Sprite sprite = null;
		Sprite enabledSprite = new Sprite(maxSize, maxSize);
		//System.out.println("Sprite Loaded for: " + i);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(maxSize, maxSize, enabledSprite.myPixels);
		DrawingArea.method336(maxSize, 0, 0, 0, maxSize);
		Texture.method364();
		double zoom = (520*Math.log(npc.aByte68))+520;
		int k3 = (int) zoom;//zoom
		//System.out.println("k3 "+k3);
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Texture.anIntArray1470[rotateY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[rotateY] * k3 >> 16;
		model.method482(rotateX, unknown1,
				rotateY, offset1, l3
						+ model.modelHeight / 2 + offset2, i4
						+ offset2);
		/*
		 int l3 = Texture.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204,
				itemDef.modelRotationY, itemDef.modelOffset1, l3
						+ model.modelHeight / 2 + itemDef.modelOffset2, i4
						+ itemDef.modelOffset2);
		 */
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (enabledSprite.myPixels[i5 + j4 * maxSize] == 0)
					if (i5 > 0
							&& enabledSprite.myPixels[(i5 - 1) + j4 * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (j4 > 0
							&& enabledSprite.myPixels[i5 + (j4 - 1) * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (i5 < 31
							&& enabledSprite.myPixels[i5 + 1 + j4 * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (j4 < 31
							&& enabledSprite.myPixels[i5 + (j4 + 1) * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (enabledSprite.myPixels[j5 + k4 * maxSize] == 0)
						if (j5 > 0
								&& enabledSprite.myPixels[(j5 - 1) + k4 * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (k4 > 0
								&& enabledSprite.myPixels[j5 + (k4 - 1) * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (j5 < 31
								&& enabledSprite.myPixels[j5 + 1 + k4 * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (k4 < 31
								&& enabledSprite.myPixels[j5 + (k4 + 1) * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (enabledSprite.myPixels[k5 + l4 * maxSize] == 0
							&& k5 > 0
							&& l4 > 0
							&& enabledSprite.myPixels[(k5 - 1) + (l4 - 1) * maxSize] > 0)
						enabledSprite.myPixels[k5 + l4 * maxSize] = 0x302020;

			}

		}
		if (k == 0)
			mruNodes.removeFromCache(enabledSprite, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		enabledSprite.anInt1445 = maxSize;
		enabledSprite.anInt1444 = j;
		return enabledSprite;
	}

   public static EntityDef forID(int i) {
      for(int entityDef = 0; entityDef < 20; ++entityDef) {
         if(cache[entityDef].type == (long)i) {
            return cache[entityDef];
         }
      }

      anInt56 = (anInt56 + 1) % 20;
      EntityDef var2 = cache[anInt56] = new EntityDef();
      stream.currentOffset = streamIndices[i];
      var2.type = (long)i;
      var2.readValues(stream);
      switch(i) {
	  //case 125:
	  /*case 178:
		var2.getModelValues = true;
	  break;*/
	 /* case 410:
		/*
		Model 0: 208//head
Model 1: 250//beard
Model 2: 292//shirt
Model 3: 324//??stripes on shirt
Model 4: 317//cape
Model 5: 170//hands
Model 6: 176//hands
Model 7: 267//legs
Model 8: 185//boots
		*/
		/*var2.anIntArray94 = new int[8];
		var2.anIntArray94[0] = 250;
		var2.anIntArray94[1] = 292;
		var2.anIntArray94[2] = 324;
		var2.anIntArray94[3] = 317;
		var2.anIntArray94[4] = 170;
		var2.anIntArray94[5] = 176;
		var2.anIntArray94[6] = 267;
		var2.anIntArray94[7] = 185;
	  break;*/
	  /*case 172:
		var2.anIntArray94 = new int[8];
		var2.anIntArray94[0] = 208;
		var2.anIntArray94[1] = 181;
		var2.anIntArray94[2] = 250;
		var2.anIntArray94[3] = 292;
		var2.anIntArray94[4] = 170;
		var2.anIntArray94[5] = 176;
		var2.anIntArray94[6] = 265;
		var2.anIntArray94[7] = 14943;
		var2.anIntArray76 = new int[11];
        var2.anIntArray70 = new int[11];
		var2.anIntArray76[0] = 36300;
		var2.anIntArray76[1] = 36292;
		var2.anIntArray76[2] = 57280;
		var2.anIntArray76[3] = 54183;
		var2.anIntArray76[4] = 54503;
		var2.anIntArray76[5] = 960;
		var2.anIntArray76[6] = 22464;
		var2.anIntArray76[7] = 43968;
		//qp cape
		var2.anIntArray76[8] = 8741;
		var2.anIntArray76[9] = 25238;
		var2.anIntArray76[10] = 6798;
		
		var2.anIntArray70[0] = 941;
		var2.anIntArray70[1] = 941;
		var2.anIntArray70[2] = 941;
		var2.anIntArray70[3] = 792;
		var2.anIntArray70[4] = 792;
		var2.anIntArray70[5] = 43113;
		var2.anIntArray70[6] = 43096;
		var2.anIntArray70[7] = 43096;
		//qp cape
		var2.anIntArray70[8] = 43113;
		var2.anIntArray70[9] = 43113;
		var2.anIntArray70[10] = 43096;
		var2.getModelValues = true;
	  break;*/
      case 945:
         var2.name = Client.NAME+" Guide";
         break;
      case 2258:
         var2.actions = new String[5];
         var2.actions[0] = "Talk-to";
         var2.actions[2] = "Trade";
         var2.actions[3] = "Teleport";
      }

      return var2;
   }

   public Model method160() {
      if(this.childrenIDs != null) {
         EntityDef var5 = this.method161();
         return var5 == null?null:var5.method160();
      } else if(this.anIntArray73 == null) {
         return null;
      } else {
         boolean flag1 = false;

         for(int aclass30_sub2_sub4_sub6s = 0; aclass30_sub2_sub4_sub6s < this.anIntArray73.length; ++aclass30_sub2_sub4_sub6s) {
            if(!Model.method463(this.anIntArray73[aclass30_sub2_sub4_sub6s])) {
               flag1 = true;
            }
         }

         if(flag1) {
            return null;
         } else {
            Model[] var6 = new Model[this.anIntArray73.length];

            for(int model = 0; model < this.anIntArray73.length; ++model) {
               var6[model] = Model.method462(this.anIntArray73[model]);
            }

            Model var7;
            if(var6.length == 1) {
               var7 = var6[0];
            } else {
               var7 = new Model(var6.length, var6);
            }

            if(this.anIntArray76 != null) {
               for(int k = 0; k < this.anIntArray76.length; ++k) {
                  var7.method476(this.anIntArray76[k], this.anIntArray70[k]);
               }
            }

            return var7;
         }
      }
   }

   public EntityDef method161() {
      int j = -1;
      if(this.anInt57 != -1) {
         VarBit varBit = VarBit.cache[this.anInt57];
         int k = varBit.anInt648;
         int l = varBit.anInt649;
         int i1 = varBit.anInt650;
         int j1 = Client.anIntArray1232[i1 - l];
         j = clientInstance.variousSettings[k] >> l & j1;
      } else if(this.anInt59 != -1) {
         j = clientInstance.variousSettings[this.anInt59];
      }

      return j >= 0 && j < this.childrenIDs.length && this.childrenIDs[j] != -1?forID(this.childrenIDs[j]):null;
   }

   public static int totalNPCs;
   
   public static void unpackConfig(StreamLoader streamLoader) {
      stream = new Stream(streamLoader.getDataForName("npc.dat"));
      Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
      totalNPCs = stream2.readUnsignedWord();
      streamIndices = new int[totalNPCs];
      int i = 2;

      int k;
      for(k = 0; k < totalNPCs; ++k) {
         streamIndices[k] = i;
         i += stream2.readUnsignedWord();
      }

      cache = new EntityDef[20];

      for(k = 0; k < 20; ++k) {
         cache[k] = new EntityDef();
      }

   }

   public static void nullLoader() {
      mruNodes = null;
      streamIndices = null;
      cache = null;
      stream = null;
   }
   
   /*public Model test(){
	   Model model = (Model)mruNodes.insertFromCache(this.type);
	   Model var9 = Model.aModel_1621;
       var9.method464(model, Class36.method532(-1) & Class36.method532(-1));
	   var9.setModelAlpha(128);
	   return var9;
   }*/
   

   public Model method164(int j, int k, int[] ai) {
      if(this.childrenIDs != null) {
         EntityDef var8 = this.method161();
         return var8 == null?null:var8.method164(j, k, ai);
      } else {
         Model model = (Model)mruNodes.insertFromCache(this.type);
         if(model == null) {
            boolean model_1 = false;

            for(int aclass30_sub2_sub4_sub6s = 0; aclass30_sub2_sub4_sub6s < this.anIntArray94.length; ++aclass30_sub2_sub4_sub6s) {
               if(!Model.method463(this.anIntArray94[aclass30_sub2_sub4_sub6s])) {
                  model_1 = true;
               }
            }

            if(model_1) {
               return null;
            }

            Model[] var10 = new Model[this.anIntArray94.length];

            int k1;
            for(k1 = 0; k1 < this.anIntArray94.length; ++k1) {
               var10[k1] = Model.method462(this.anIntArray94[k1]);
            }

            if(var10.length == 1) {
               model = var10[0];
            } else {
               model = new Model(var10.length, var10);
            }

            if(this.anIntArray76 != null) {
               for(k1 = 0; k1 < this.anIntArray76.length; ++k1) {
                  model.method476(this.anIntArray76[k1], this.anIntArray70[k1]);
               }
            }
			
			if(this.getModelValues){
				this.getModelValues = false;
				System.out.println("Values for npc: "+this.type);
				model.getModelValues();
			}

            model.method469();
            model.method479(64 + this.anInt85, 850 + this.anInt92, -30, -50, -30, true);
            mruNodes.removeFromCache(model, this.type);
         }

         Model var9 = Model.aModel_1621;
         var9.method464(model, Class36.method532(k) & Class36.method532(j));
         if(k != -1 && j != -1) {
            var9.method471(ai, j, k);
         } else if(k != -1) {
            var9.method470(k);
         }

         if(this.anInt91 != 128 || this.anInt86 != 128) {
            var9.method478(this.anInt91, this.anInt91, this.anInt86);
         }

         var9.method466();
         var9.anIntArrayArray1658 = (int[][])null;
         var9.anIntArrayArray1657 = (int[][])null;
         if(this.aByte68 == 1) {
            var9.aBoolean1659 = true;
         }
		 
         return var9;
      }
   }

   private void readValues(Stream stream) {
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            return;
         }

         int i1;
         int i2;
         if(i == 1) {
            i1 = stream.readUnsignedByte();
            this.anIntArray94 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray94[i2] = stream.readUnsignedWord();
            }
         } else if(i == 2) {
            this.name = stream.readString();
         } else if(i == 3) {
            this.description = stream.readBytes();
         } else if(i == 12) {
            this.aByte68 = stream.readSignedByte();
         } else if(i == 13) {
            this.anInt77 = stream.readUnsignedWord();
         } else if(i == 14) {
            this.anInt67 = stream.readUnsignedWord();
         } else if(i == 17) {
            this.anInt67 = stream.readUnsignedWord();
            this.anInt58 = stream.readUnsignedWord();
            this.anInt83 = stream.readUnsignedWord();
            this.anInt55 = stream.readUnsignedWord();
         } else if(i >= 30 && i < 40) {
            if(this.actions == null) {
               this.actions = new String[5];
            }

            this.actions[i - 30] = stream.readString();
            if(this.actions[i - 30].equalsIgnoreCase("hidden")) {
               this.actions[i - 30] = null;
            }
         } else if(i == 40) {
            i1 = stream.readUnsignedByte();
            this.anIntArray76 = new int[i1];
            this.anIntArray70 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray76[i2] = stream.readUnsignedWord();
               this.anIntArray70[i2] = stream.readUnsignedWord();
            }
         } else if(i == 60) {
            i1 = stream.readUnsignedByte();
            this.anIntArray73 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray73[i2] = stream.readUnsignedWord();
            }
         } else if(i == 90) {
            stream.readUnsignedWord();
         } else if(i == 91) {
            stream.readUnsignedWord();
         } else if(i == 92) {
            stream.readUnsignedWord();
         } else if(i == 93) {
            this.aBoolean87 = false;
         } else if(i == 95) {
            this.combatLevel = stream.readUnsignedWord();
         } else if(i == 97) {
            this.anInt91 = stream.readUnsignedWord();
         } else if(i == 98) {
            this.anInt86 = stream.readUnsignedWord();
         } else if(i == 99) {
            this.aBoolean93 = true;
         } else if(i == 100) {
            this.anInt85 = stream.readSignedByte();
         } else if(i == 101) {
            this.anInt92 = stream.readSignedByte() * 5;
         } else if(i == 102) {
            this.anInt75 = stream.readUnsignedWord();
         } else if(i == 103) {
            this.anInt79 = stream.readUnsignedWord();
         } else if(i == 106) {
            this.anInt57 = stream.readUnsignedWord();
            if(this.anInt57 == '\uffff') {
               this.anInt57 = -1;
            }

            this.anInt59 = stream.readUnsignedWord();
            if(this.anInt59 == '\uffff') {
               this.anInt59 = -1;
            }

            i1 = stream.readUnsignedByte();
            this.childrenIDs = new int[i1 + 1];

            for(i2 = 0; i2 <= i1; ++i2) {
               this.childrenIDs[i2] = stream.readUnsignedWord();
               if(this.childrenIDs[i2] == '\uffff') {
                  this.childrenIDs[i2] = -1;
               }
            }
         } else if(i == 107) {
            this.aBoolean84 = false;
         }
      }
   }

}
