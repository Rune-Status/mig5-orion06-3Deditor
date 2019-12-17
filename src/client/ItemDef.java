package client;


public final class ItemDef {

   private byte aByte154;
   public int value;
   private int[] originalModelColors;
   private int[] alphaColor;
   private int[] alphaLevel;
   public int id = -1;
   static MRUNodes mruNodes1 = new MRUNodes(100);
   public static MRUNodes mruNodes2 = new MRUNodes(50);
   private int[] modifiedModelColors;
   public boolean membersObject;
   private int anInt162;
   int certTemplateID;
   private int anInt164;
   private int anInt165;
   private int anInt166;
   private int anInt167;
   public String[] groundActions;
   private int modelOffset1;
   public String name;
   private static ItemDef[] cache;
   private int anInt173;
   private int modelID;
   private int anInt175;
   public boolean stackable;
   public byte[] description;
   int certID;
   private static int cacheIndex;
   public int modelZoom;
   public static boolean isMembers = true;
   private static Stream stream;
   private int anInt184;
   private int anInt185;
   private int anInt188;
   public String[] actions;
   public int modelRotation1;
   private int anInt191;
   private int anInt192;
   private int[] stackIDs;
   private int modelOffset2;
   private static int[] streamIndices;
   private int anInt196;
   private int anInt197;
   public int modelRotation2;
   private int anInt200;
   private int[] stackAmounts;
   public int team;
   public static int totalItems;
   private int anInt204;
   private byte aByte205;
   public boolean getModelValues;
   public boolean isOnGe;


   public static void nullLoader() {
      mruNodes2 = null;
      mruNodes1 = null;
      streamIndices = null;
      cache = null;
      stream = null;
   }

   public boolean method192(int j) {
      int k = this.anInt175;
      int l = this.anInt166;
      if(j == 1) {
         k = this.anInt197;
         l = this.anInt173;
      }

      if(k == -1) {
         return true;
      } else {
         boolean flag = true;
         if(!Model.method463(k)) {
            flag = false;
         }

         if(l != -1 && !Model.method463(l)) {
            flag = false;
         }

         return flag;
      }
   }

   public static void unpackConfig(StreamLoader streamLoader) {
      stream = new Stream(streamLoader.getDataForName("obj.dat"));
      Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
      totalItems = stream.readUnsignedWord();
      streamIndices = new int[totalItems];
      int i = 2;

      int k;
      for(k = 0; k < totalItems; ++k) {
         streamIndices[k] = i;
         i += stream.readUnsignedWord();
      }

      cache = new ItemDef[10];

      for(k = 0; k < 10; ++k) {
         cache[k] = new ItemDef();
      }

   }

   public Model method194(int j) {
      int k = this.anInt175;
      int l = this.anInt166;
      if(j == 1) {
         k = this.anInt197;
         l = this.anInt173;
      }

      if(k == -1) {
         return null;
      } else {
         Model model = Model.method462(k);
         if(l != -1) {
            Model i1 = Model.method462(l);
            Model[] aclass30_sub2_sub4_sub6s = new Model[]{model, i1};
            model = new Model(2, aclass30_sub2_sub4_sub6s);
         }

         if(this.originalModelColors != null) {
            for(int var7 = 0; var7 < this.originalModelColors.length; ++var7) {
               model.method476(this.originalModelColors[var7], this.modifiedModelColors[var7]);
            }
         }
		 if(this.alphaColor != null) {
            for(int var7 = 0; var7 < this.alphaColor.length; ++var7) {
               model.setAlphaToColor(this.alphaColor[var7], this.alphaLevel[var7]);
            }
         }

         return model;
      }
   }

   public boolean method195(int j) {
      int k = this.anInt165;
      int l = this.anInt188;
      int i1 = this.anInt185;
      if(j == 1) {
         k = this.anInt200;
         l = this.anInt164;
         i1 = this.anInt162;
      }

      if(k == -1) {
         return true;
      } else {
         boolean flag = true;
         if(!Model.method463(k)) {
            flag = false;
         }

         if(l != -1 && !Model.method463(l)) {
            flag = false;
         }

         if(i1 != -1 && !Model.method463(i1)) {
            flag = false;
         }

         return flag;
      }
   }

   public Model method196(int i) {
      int j = this.anInt165;
      int k = this.anInt188;
      int l = this.anInt185;
      if(i == 1) {
         j = this.anInt200;
         k = this.anInt164;
         l = this.anInt162;
      }

      if(j == -1) {
         return null;
      } else {
         Model model = Model.method462(j);
         if(k != -1) {
            Model i1;
            if(l != -1) {
               i1 = Model.method462(k);
               Model aclass30_sub2_sub4_sub6s = Model.method462(l);
               Model[] aclass30_sub2_sub4_sub6_1s = new Model[]{model, i1, aclass30_sub2_sub4_sub6s};
               model = new Model(3, aclass30_sub2_sub4_sub6_1s);
            } else {
               i1 = Model.method462(k);
               Model[] var10 = new Model[]{model, i1};
               model = new Model(2, var10);
            }
         }

         if(i == 0 && this.aByte205 != 0) {
            model.method475(0, this.aByte205, 0);
         }

         if(i == 1 && this.aByte154 != 0) {
            model.method475(0, this.aByte154, 0);
         }

         if(this.originalModelColors != null) {
            for(int var9 = 0; var9 < this.originalModelColors.length; ++var9) {
               model.method476(this.originalModelColors[var9], this.modifiedModelColors[var9]);
            }
         }
		 if(this.alphaColor != null) {
            for(int var9 = 0; var9 < this.alphaColor.length; ++var9) {
               model.setAlphaToColor(this.alphaColor[var9], this.alphaLevel[var9]);
            }
         }
		  if(this.getModelValues){
			this.getModelValues = false;
			System.out.println("Values for item: "+this.id);
			model.getModelValues();
		}

         return model;
      }
   }

   private void setDefaults() {
      this.modelID = 0;
      this.name = null;
      this.description = null;
      this.originalModelColors = null;
      this.modifiedModelColors = null;
      this.modelZoom = 2000;
      this.modelRotation1 = 0;
      this.modelRotation2 = 0;
      this.anInt204 = 0;
      this.modelOffset1 = 0;
      this.modelOffset2 = 0;
      this.stackable = false;
      this.isOnGe = false;
      this.value = 1;
      this.membersObject = false;
      this.groundActions = null;
      this.actions = null;
      this.anInt165 = -1;
      this.anInt188 = -1;
      this.aByte205 = 0;
      this.anInt200 = -1;
      this.anInt164 = -1;
      this.aByte154 = 0;
      this.anInt185 = -1;
      this.anInt162 = -1;
      this.anInt175 = -1;
      this.anInt166 = -1;
      this.anInt197 = -1;
      this.anInt173 = -1;
      this.stackIDs = null;
      this.stackAmounts = null;
      this.certID = -1;
      this.certTemplateID = -1;
      this.anInt167 = 128;
      this.anInt192 = 128;
      this.anInt191 = 128;
      this.anInt196 = 0;
      this.anInt184 = 0;
      this.team = 0;
	  this.alphaLevel = null;
	  this.alphaColor = null;
	  this.getModelValues = false;
   }

   public static ItemDef forID(int i) {
      for(int itemDef = 0; itemDef < 10; ++itemDef) {
         if(cache[itemDef].id == i) {
            return cache[itemDef];
         }
      }

      cacheIndex = (cacheIndex + 1) % 10;
      ItemDef var2 = cache[cacheIndex];
      stream.currentOffset = streamIndices[i];
      var2.id = i;
      var2.setDefaults();
      var2.readValues(stream);
      if(var2.certTemplateID != -1) {
         var2.toNote();
      }

      if(!isMembers && var2.membersObject) {
         var2.name = "Members Object";
         var2.description = "Login to a members\' server to use this object.".getBytes();
         var2.groundActions = null;
         var2.actions = null;
         var2.team = 0;
      }
	  /*if(i == 2890){
			var2.originalModelColors = new int[2];
			var2.modifiedModelColors = new int[2];
			var2.originalModelColors[0] = 46554;
			var2.originalModelColors[1] = 45285;
			var2.modifiedModelColors[0] = 22416;
			var2.modifiedModelColors[1] = 21147;
			/*
			46554
			45285
			
			red:
			var2.modifiedModelColors[0] = 937;
			var2.modifiedModelColors[1] = -309;
			
			green:
			var2.modifiedModelColors[0] = 22416;
			var2.modifiedModelColors[1] = 21147;
			
		}*/
	  /*if(i == 1053){
			var2.originalModelColors = new int[2];
			var2.modifiedModelColors = new int[2];
			var2.originalModelColors[0] = 926;
			var2.originalModelColors[1] = 0;
			var2.modifiedModelColors[0] = 8;
			var2.modifiedModelColors[1] = 9152;
		}*/
	 /* if(i == 4133 || i == 4521 || i == 4134 || i == 4135 || i == 4520 || i == 4136 || i == 4137 || i == 4138 || i == 4139 || i == 4140 || i == 4141 || i == 4142 || i == 4143 || i == 4144 ||
			i == 4145 || i == 4146 || i == 6811 || i == 4147 || i == 4148 || i == 4149 || i == 6637){
			var2.modelID = 9319;
			var2.modelZoom = 10000;
			//var2.modelZoom = 5800;chaos
			//var2.modelZoom = 4500;kbd
			//var2.modelZoom = 10000;//kq,jad
	 }*/
	  /*if(i == 6570){
		var2.anInt165 = 14943;//3189,18946
	  }*/
	 /* int c = 10258;
	  if(i == 1040){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 35792;
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 120;
		}
		if(i == 1053){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 35792;
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 120;
			var2.getModelValues = true;
		}
		if(i == 2643){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 35792;
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 100;
		}
	  //shadow set
	  /*if(i == 2595){//helm
			//var2.alpha = 200;
			//var2.getModelValues = true;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = c;
			var2.modifiedModelColors[1] = c;
			var2.alphaColor[0] = c;
			var2.alphaLevel[0] = 115;
		}
		if(i == 1121){//pl8
			var2.originalModelColors = new int[5];
			var2.modifiedModelColors = new int[5];
			var2.originalModelColors[0] = 24;
			var2.originalModelColors[1] = 61;
			var2.originalModelColors[2] = 41;
			var2.originalModelColors[3] = 12;
			var2.originalModelColors[4] = 8741;
			var2.modifiedModelColors[0] = c;
			var2.modifiedModelColors[1] = c;
			var2.modifiedModelColors[2] = c;
			var2.modifiedModelColors[3] = c;
			var2.modifiedModelColors[4] = c;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = c;
			var2.alphaLevel[0] = 175;
			var2.getModelValues = true;
		}
		if(i == 1067){//legs
			var2.modifiedModelColors[0] = c;
			var2.modifiedModelColors[1] = c;
			var2.modifiedModelColors[2] = c;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = c;
			var2.alphaLevel[0] = 125;
		}
		if(i == 2904){//boots
			var2.modifiedModelColors[0] = c;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = c;
			var2.alphaLevel[0] = 125;
		}
		if(i == 2912){//gloves
			var2.modifiedModelColors[0] = c;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = c;
			var2.alphaLevel[0] = 125;
		}*/
	  //ice set
	  /*if(i == 2595){//helm
			//var2.alpha = 200;
			//var2.getModelValues = true;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 35792;
			var2.modifiedModelColors[1] = 35792;
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 115;
		}
		if(i == 1121){//pl8
			var2.modifiedModelColors[0] = 35792;
			var2.modifiedModelColors[1] = 35792;
			var2.modifiedModelColors[2] = 35792;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 175;
		}
		if(i == 1067){//legs
			var2.modifiedModelColors[0] = 35792;
			var2.modifiedModelColors[1] = 35792;
			var2.modifiedModelColors[2] = 35792;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 125;
		}
		if(i == 2904){//boots
			var2.modifiedModelColors[0] = 35792;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 125;
		}
		if(i == 2912){//gloves
			var2.modifiedModelColors[0] = 35792;
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.alphaColor[0] = 35792;
			var2.alphaLevel[0] = 125;
		}*/
		/*
		ghostly color: 10266, ghostly alpha: 140
		*/
		/*if(i == 2595){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 10266;
			var2.alphaColor[0] = 10266;
			var2.alphaLevel[0] = 140;
		}
		if(i == 2643){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 10266;
			var2.alphaColor[0] = 10266;
			var2.alphaLevel[0] = 140;
		}
		if(i == 1040){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 10266;
			var2.alphaColor[0] = 10266;
			var2.alphaLevel[0] = 140;
		}
		if(i == 1053){
			var2.alphaColor = new int[1];
			var2.alphaLevel = new int[1];
			var2.modifiedModelColors[0] = 10266;
			var2.alphaColor[0] = 10266;
			var2.alphaLevel[0] = 140;
		}*/
		/*
		Model 0: 213
Model 1: 306
Model 2: 164
Model 3: 176
Model 4: 268
Model 5: 184
Model 6: 511
		
		//ice warrior:
		Colors:
35792
8741
43072

Alphas:
0
		*/

      return var2;
   }

   private void toNote() {
      ItemDef itemDef = forID(this.certTemplateID);
      this.modelID = itemDef.modelID;
      this.modelZoom = itemDef.modelZoom;
      this.modelRotation1 = itemDef.modelRotation1;
      this.modelRotation2 = itemDef.modelRotation2;
      this.anInt204 = itemDef.anInt204;
      this.modelOffset1 = itemDef.modelOffset1;
      this.modelOffset2 = itemDef.modelOffset2;
      this.originalModelColors = itemDef.originalModelColors;
      this.modifiedModelColors = itemDef.modifiedModelColors;
	  this.alphaLevel = itemDef.alphaLevel;
      this.alphaColor = itemDef.alphaColor;
      ItemDef itemDef_1 = forID(this.certID);
      this.name = itemDef_1.name;
      this.membersObject = itemDef_1.membersObject;
      this.value = itemDef_1.value;
      String s = "a";
      char c = itemDef_1.name.charAt(0);
      if(c == 65 || c == 69 || c == 73 || c == 79 || c == 85) {
         s = "an";
      }

      this.description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".").getBytes();
      this.stackable = true;
   }

   public static Sprite getSprite(int i, int j, int k) {
      if(k == 0) {
         Sprite itemDef = (Sprite)mruNodes1.insertFromCache((long)i);
         if(itemDef != null && itemDef.anInt1445 != j && itemDef.anInt1445 != -1) {
            itemDef.unlink();
            itemDef = null;
         }

         if(itemDef != null) {
            return itemDef;
         }
      }

      ItemDef var22 = forID(i);
      if(var22.stackIDs == null) {
         j = -1;
      }

      if(j > 1) {
         int model = -1;

         for(int sprite = 0; sprite < 10; ++sprite) {
            if(j >= var22.stackAmounts[sprite] && var22.stackAmounts[sprite] != 0) {
               model = var22.stackIDs[sprite];
            }
         }

         if(model != -1) {
            var22 = forID(model);
         }
      }

      Model var23 = var22.method201(1);
      if(var23 == null) {
         return null;
      } else {
         Sprite var24 = null;
         if(var22.certTemplateID != -1) {
            var24 = getSprite(var22.certID, 10, -1);
            if(var24 == null) {
               return null;
            }
         }

         Sprite sprite2 = new Sprite(32, 32);
         int k1 = Texture.textureInt1;
         int l1 = Texture.textureInt2;
         int[] ai = Texture.anIntArray1472;
         int[] ai1 = DrawingArea.pixels;
         int i2 = DrawingArea.width;
         int j2 = DrawingArea.height;
         int k2 = DrawingArea.topX;
         int l2 = DrawingArea.bottomX;
         int i3 = DrawingArea.topY;
         int j3 = DrawingArea.bottomY;
         Texture.aBoolean1464 = false;
         DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
         DrawingArea.method336(32, 0, 0, 0, 32);
         Texture.method364();
         int k3 = var22.modelZoom;
         if(k == -1) {
            k3 = (int)((double)k3 * 1.5D);
         }

         if(k > 0) {
            k3 = (int)((double)k3 * 1.04D);
         }

         int l3 = Texture.anIntArray1470[var22.modelRotation1] * k3 >> 16;
         int i4 = Texture.anIntArray1471[var22.modelRotation1] * k3 >> 16;
         var23.method482(var22.modelRotation2, var22.anInt204, var22.modelRotation1, var22.modelOffset1, l3 + var23.modelHeight / 2 + var22.modelOffset2, i4 + var22.modelOffset2);

         int j6;
         int l5;
         for(l5 = 31; l5 >= 0; --l5) {
            for(j6 = 31; j6 >= 0; --j6) {
               if(sprite2.myPixels[l5 + j6 * 32] == 0) {
                  if(l5 > 0 && sprite2.myPixels[l5 - 1 + j6 * 32] > 1) {
                     sprite2.myPixels[l5 + j6 * 32] = 1;
                  } else if(j6 > 0 && sprite2.myPixels[l5 + (j6 - 1) * 32] > 1) {
                     sprite2.myPixels[l5 + j6 * 32] = 1;
                  } else if(l5 < 31 && sprite2.myPixels[l5 + 1 + j6 * 32] > 1) {
                     sprite2.myPixels[l5 + j6 * 32] = 1;
                  } else if(j6 < 31 && sprite2.myPixels[l5 + (j6 + 1) * 32] > 1) {
                     sprite2.myPixels[l5 + j6 * 32] = 1;
                  }
               }
            }
         }

         if(k > 0) {
            for(l5 = 31; l5 >= 0; --l5) {
               for(j6 = 31; j6 >= 0; --j6) {
                  if(sprite2.myPixels[l5 + j6 * 32] == 0) {
                     if(l5 > 0 && sprite2.myPixels[l5 - 1 + j6 * 32] == 1) {
                        sprite2.myPixels[l5 + j6 * 32] = k;
                     } else if(j6 > 0 && sprite2.myPixels[l5 + (j6 - 1) * 32] == 1) {
                        sprite2.myPixels[l5 + j6 * 32] = k;
                     } else if(l5 < 31 && sprite2.myPixels[l5 + 1 + j6 * 32] == 1) {
                        sprite2.myPixels[l5 + j6 * 32] = k;
                     } else if(j6 < 31 && sprite2.myPixels[l5 + (j6 + 1) * 32] == 1) {
                        sprite2.myPixels[l5 + j6 * 32] = k;
                     }
                  }
               }
            }
         } else if(k == 0) {
            for(l5 = 31; l5 >= 0; --l5) {
               for(j6 = 31; j6 >= 0; --j6) {
                  if(sprite2.myPixels[l5 + j6 * 32] == 0 && l5 > 0 && j6 > 0 && sprite2.myPixels[l5 - 1 + (j6 - 1) * 32] > 0) {
                     sprite2.myPixels[l5 + j6 * 32] = 3153952;
                  }
               }
            }
         }

         if(var22.certTemplateID != -1) {
            l5 = var24.anInt1444;
            j6 = var24.anInt1445;
            var24.anInt1444 = 32;
            var24.anInt1445 = 32;
            var24.drawSprite(0, 0);
            var24.anInt1444 = l5;
            var24.anInt1445 = j6;
         }

         if(k == 0) {
            mruNodes1.removeFromCache(sprite2, (long)i);
         }

         DrawingArea.initDrawingArea(j2, i2, ai1);
         DrawingArea.setDrawingArea(j3, k2, l2, i3);
         Texture.textureInt1 = k1;
         Texture.textureInt2 = l1;
         Texture.anIntArray1472 = ai;
         Texture.aBoolean1464 = true;
         if(var22.stackable) {
            sprite2.anInt1444 = 33;
         } else {
            sprite2.anInt1444 = 32;
         }

         sprite2.anInt1445 = j;
         return sprite2;
      }
   }

   public Model method201(int i) {
      int l;
      if(this.stackIDs != null && i > 1) {
         int model = -1;

         for(l = 0; l < 10; ++l) {
            if(i >= this.stackAmounts[l] && this.stackAmounts[l] != 0) {
               model = this.stackIDs[l];
            }
         }

         if(model != -1) {
            return forID(model).method201(1);
         }
      }

      Model var4 = (Model)mruNodes2.insertFromCache((long)this.id);
      if(var4 != null) {
         return var4;
      } else {
         var4 = Model.method462(this.modelID);
         if(var4 == null) {
            return null;
         } else {
            if(this.anInt167 != 128 || this.anInt192 != 128 || this.anInt191 != 128) {
               var4.method478(this.anInt167, this.anInt191, this.anInt192);
            }

            if(this.originalModelColors != null) {
               for(l = 0; l < this.originalModelColors.length; ++l) {
                  var4.method476(this.originalModelColors[l], this.modifiedModelColors[l]);
               }
            }
			if(this.alphaColor != null) {
               for(l = 0; l < this.alphaColor.length; ++l) {
                  var4.setAlphaToColor(this.alphaColor[l], this.alphaLevel[l]);
               }
            }
            var4.method479(64 + this.anInt196, 768 + this.anInt184, -50, -10, -50, true);
            var4.aBoolean1659 = true;
            mruNodes2.removeFromCache(var4, (long)this.id);
            return var4;
         }
      }
   }

   public Model method202(int i) {
      int l;
      if(this.stackIDs != null && i > 1) {
         int model = -1;

         for(l = 0; l < 10; ++l) {
            if(i >= this.stackAmounts[l] && this.stackAmounts[l] != 0) {
               model = this.stackIDs[l];
            }
         }

         if(model != -1) {
            return forID(model).method202(1);
         }
      }

      Model var4 = Model.method462(this.modelID);
      if(var4 == null) {
         return null;
      } else {
         if(this.originalModelColors != null) {
            for(l = 0; l < this.originalModelColors.length; ++l) {
               var4.method476(this.originalModelColors[l], this.modifiedModelColors[l]);
            }
         }
		 if(this.alphaColor != null) {
            for(l = 0; l < this.alphaColor.length; ++l) {
               var4.setAlphaToColor(this.alphaColor[l], this.alphaLevel[l]);
            }
         }

         return var4;
      }
   }

   private void readValues(Stream stream) {
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            return;
         }

         if(i == 1) {
            this.modelID = stream.readUnsignedWord();
         } else if(i == 2) {
            this.name = stream.readString();
         } else if(i == 3) {
            this.description = stream.readBytes();
         } else if(i == 4) {
            this.modelZoom = stream.readUnsignedWord();
         } else if(i == 5) {
            this.modelRotation1 = stream.readUnsignedWord();
         } else if(i == 6) {
            this.modelRotation2 = stream.readUnsignedWord();
         } else if(i == 7) {
            this.modelOffset1 = stream.readUnsignedWord();
            if(this.modelOffset1 > 32767) {
               this.modelOffset1 -= 65536;
            }
         } else if(i == 8) {
            this.modelOffset2 = stream.readUnsignedWord();
            if(this.modelOffset2 > 32767) {
               this.modelOffset2 -= 65536;
            }
         } else if(i == 10) {
            stream.readUnsignedWord();
         } else if(i == 11) {
            this.stackable = true;
         } else if(i == 12) {
            this.value = stream.readDWord();
         } else if(i == 16) {
            this.membersObject = true;
         } else if(i == 23) {
            this.anInt165 = stream.readUnsignedWord();
            this.aByte205 = stream.readSignedByte();
         } else if(i == 24) {
            this.anInt188 = stream.readUnsignedWord();
         } else if(i == 25) {
            this.anInt200 = stream.readUnsignedWord();
            this.aByte154 = stream.readSignedByte();
         } else if(i == 26) {
            this.anInt164 = stream.readUnsignedWord();
         } else if(i >= 30 && i < 35) {
            if(this.groundActions == null) {
               this.groundActions = new String[5];
            }

            this.groundActions[i - 30] = stream.readString();
            if(this.groundActions[i - 30].equalsIgnoreCase("hidden")) {
               this.groundActions[i - 30] = null;
            }
         } else if(i >= 35 && i < 40) {
            if(this.actions == null) {
               this.actions = new String[5];
            }

            this.actions[i - 35] = stream.readString();
         } else if(i == 40) {
            int j = stream.readUnsignedByte();
            this.originalModelColors = new int[j];
            this.modifiedModelColors = new int[j];

            for(int k = 0; k < j; ++k) {
               this.originalModelColors[k] = stream.readUnsignedWord();
               this.modifiedModelColors[k] = stream.readUnsignedWord();
            }
         } else if(i == 78) {
            this.anInt185 = stream.readUnsignedWord();
         } else if(i == 79) {
            this.anInt162 = stream.readUnsignedWord();
         } else if(i == 90) {
            this.anInt175 = stream.readUnsignedWord();
         } else if(i == 91) {
            this.anInt197 = stream.readUnsignedWord();
         } else if(i == 92) {
            this.anInt166 = stream.readUnsignedWord();
         } else if(i == 93) {
            this.anInt173 = stream.readUnsignedWord();
         } else if(i == 95) {
            this.anInt204 = stream.readUnsignedWord();
         } else if(i == 97) {
            this.certID = stream.readUnsignedWord();
         } else if(i == 98) {
            this.certTemplateID = stream.readUnsignedWord();
         } else if(i >= 100 && i < 110) {
            if(this.stackIDs == null) {
               this.stackIDs = new int[10];
               this.stackAmounts = new int[10];
            }

            this.stackIDs[i - 100] = stream.readUnsignedWord();
            this.stackAmounts[i - 100] = stream.readUnsignedWord();
         } else if(i == 110) {
            this.anInt167 = stream.readUnsignedWord();
         } else if(i == 111) {
            this.anInt192 = stream.readUnsignedWord();
         } else if(i == 112) {
            this.anInt191 = stream.readUnsignedWord();
         } else if(i == 113) {
            this.anInt196 = stream.readSignedByte();
         } else if(i == 114) {
            this.anInt184 = stream.readSignedByte() * 5;
         } else if(i == 115) {
            this.team = stream.readUnsignedByte();
         } else if(i == 140) {
            int j = stream.readUnsignedByte();
            this.alphaColor = new int[j];
            this.alphaLevel = new int[j];

            for(int k = 0; k < j; ++k) {
               this.alphaColor[k] = stream.readUnsignedWord();
               this.alphaLevel[k] = stream.readUnsignedWord();
            }
         } else if(i == 177) {
             this.isOnGe = true;
         }
      }
   }

}
