package client;


public final class ObjectDef {

   public boolean aBoolean736;
   private byte aByte737;
   private int anInt738;
   public String name;
   private int anInt740;
   private static final Model[] aModelArray741s = new Model[4];
   private byte aByte742;
   public int anInt744;
   private int anInt745;
   public int anInt746;
   private int[] originalModelColors;
   private int anInt748;
   public int anInt749;
   private boolean aBoolean751;
   public static boolean lowMem;
   private static Stream stream;
   public int type = -1;
   private static int[] streamIndices;
   public boolean aBoolean757;
   public int anInt758;
   public int[] childrenIDs;
   private int anInt760;
   public int anInt761;
   public boolean aBoolean762;
   public boolean aBoolean764;
   public static Client clientInstance;
   private boolean aBoolean766;
   public boolean aBoolean767;
   public int anInt768;
   private boolean aBoolean769;
   private static int cacheIndex;
   private int anInt772;
   private int[] anIntArray773;
   public int anInt774;
   public int anInt775;
   private int[] anIntArray776;
   public byte[] description;
   public boolean hasActions;
   public boolean aBoolean779;
   public static MRUNodes mruNodes2 = new MRUNodes(30);
   public int anInt781;
   private static ObjectDef[] cache;
   private int anInt783;
   private int[] modifiedModelColors;
   public static MRUNodes mruNodes1 = new MRUNodes(500);
   public String[] actions;


   public static ObjectDef forID(int i) {
      for(int class46 = 0; class46 < 20; ++class46) {
         if(cache[class46].type == i) {
            return cache[class46];
         }
      }

      cacheIndex = (cacheIndex + 1) % 20;
      ObjectDef var2 = cache[cacheIndex];
      stream.currentOffset = streamIndices[i];
      var2.type = i;
      var2.setDefaults();
      var2.readValues(stream);
      return var2;
   }

   private void setDefaults() {
      this.anIntArray773 = null;
      this.anIntArray776 = null;
      this.name = null;
      this.description = null;
      this.modifiedModelColors = null;
      this.originalModelColors = null;
      this.anInt744 = 1;
      this.anInt761 = 1;
      this.aBoolean767 = true;
      this.aBoolean757 = true;
      this.hasActions = false;
      this.aBoolean762 = false;
      this.aBoolean769 = false;
      this.aBoolean764 = false;
      this.anInt781 = -1;
      this.anInt775 = 16;
      this.aByte737 = 0;
      this.aByte742 = 0;
      this.actions = null;
      this.anInt746 = -1;
      this.anInt758 = -1;
      this.aBoolean751 = false;
      this.aBoolean779 = true;
      this.anInt748 = 128;
      this.anInt772 = 128;
      this.anInt740 = 128;
      this.anInt768 = 0;
      this.anInt738 = 0;
      this.anInt745 = 0;
      this.anInt783 = 0;
      this.aBoolean736 = false;
      this.aBoolean766 = false;
      this.anInt760 = -1;
      this.anInt774 = -1;
      this.anInt749 = -1;
      this.childrenIDs = null;
   }

   public void method574(OnDemandFetcher class42_sub1) {
      if(this.anIntArray773 != null) {
         int[] arr$ = this.anIntArray773;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            int element = arr$[i$];
            class42_sub1.method560(element & '\uffff', 0);
         }

      }
   }

   public static void nullLoader() {
      mruNodes1 = null;
      mruNodes2 = null;
      streamIndices = null;
      cache = null;
      stream = null;
   }

   public static void unpackConfig(StreamLoader streamLoader) {
      stream = new Stream(streamLoader.getDataForName("loc.dat"));
      Stream stream = new Stream(streamLoader.getDataForName("loc.idx"));
	  //FileOperations.WriteFile("C:\\Users\\Cody Pate\\Desktop\\RSPS\\2006Scape\\data\\world\\loc.dat", streamLoader.getDataForName("loc.dat"));
	 // FileOperations.WriteFile("C:\\Users\\Cody Pate\\Desktop\\RSPS\\2006Scape\\data\\world\\loc.idx", streamLoader.getDataForName("loc.idx"));
      int totalObjects = stream.readUnsignedWord();
      streamIndices = new int[totalObjects];
      int i = 2;

      int k;
      for(k = 0; k < totalObjects; ++k) {
         streamIndices[k] = i;
         i += stream.readUnsignedWord();
      }

      cache = new ObjectDef[20];

      for(k = 0; k < 20; ++k) {
         cache[k] = new ObjectDef();
      }

   }

   public boolean method577(int i) {
      if(this.anIntArray776 != null) {
         for(int var7 = 0; var7 < this.anIntArray776.length; ++var7) {
            if(this.anIntArray776[var7] == i) {
               return Model.method463(this.anIntArray773[var7] & '\uffff');
            }
         }

         return true;
      } else if(this.anIntArray773 == null) {
         return true;
      } else if(i != 10) {
         return true;
      } else {
         boolean j = true;
         int[] arr$ = this.anIntArray773;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            int element = arr$[i$];
            j &= Model.method463(element & '\uffff');
         }

         return j;
      }
   }

   public Model method578(int i, int j, int k, int l, int i1, int j1, int k1) {
      Model model = this.method581(i, k1, j);
      if(model == null) {
         return null;
      } else {
         if(this.aBoolean762 || this.aBoolean769) {
            model = new Model(this.aBoolean762, this.aBoolean769, model);
         }

         if(this.aBoolean762) {
            int l1 = (k + l + i1 + j1) / 4;

            for(int i2 = 0; i2 < model.anInt1626; ++i2) {
               int j2 = model.anIntArray1627[i2];
               int k2 = model.anIntArray1629[i2];
               int l2 = k + (l - k) * (j2 + 64) / 128;
               int i3 = j1 + (i1 - j1) * (j2 + 64) / 128;
               int j3 = l2 + (i3 - l2) * (k2 + 64) / 128;
               model.anIntArray1628[i2] += j3 - l1;
            }

            model.method467();
         }

         return model;
      }
   }

   public boolean method579() {
      if(this.anIntArray773 == null) {
         return true;
      } else {
         boolean flag1 = true;
         int[] arr$ = this.anIntArray773;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            int element = arr$[i$];
            flag1 &= Model.method463(element & '\uffff');
         }

         return flag1;
      }
   }

   public ObjectDef method580() {
      int i = -1;
      if(this.anInt774 != -1) {
         VarBit varBit = VarBit.cache[this.anInt774];
         int j = varBit.anInt648;
         int k = varBit.anInt649;
         int l = varBit.anInt650;
         int i1 = Client.anIntArray1232[l - k];
         i = clientInstance.variousSettings[j] >> k & i1;
      } else if(this.anInt749 != -1) {
         i = clientInstance.variousSettings[this.anInt749];
      }

      return i >= 0 && i < this.childrenIDs.length && this.childrenIDs[i] != -1?forID(this.childrenIDs[i]):null;
   }

   private Model method581(int j, int k, int l) {
      Model model = null;
      long l1;
      boolean flag2;
      int model_3;
      int k2;
      if(this.anIntArray776 == null) {
         if(j != 10) {
            return null;
         }

         l1 = (long)((this.type << 6) + l) + ((long)(k + 1) << 32);
         Model flag = (Model)mruNodes2.insertFromCache(l1);
         if(flag != null) {
            return flag;
         }

         if(this.anIntArray773 == null) {
            return null;
         }

         flag2 = this.aBoolean751 ^ l > 3;
         model_3 = this.anIntArray773.length;

         for(k2 = 0; k2 < model_3; ++k2) {
            int l2 = this.anIntArray773[k2];
            if(flag2) {
               l2 += 65536;
            }

            model = (Model)mruNodes1.insertFromCache((long)l2);
            if(model == null) {
               model = Model.method462(l2 & '\uffff');
               if(model == null) {
                  return null;
               }

               if(flag2) {
                  model.method477();
               }

               mruNodes1.removeFromCache(model, (long)l2);
            }

            if(model_3 > 1) {
               aModelArray741s[k2] = model;
            }
         }

         if(model_3 > 1) {
            model = new Model(model_3, aModelArray741s);
         }
      } else {
         int var12 = -1;

         for(int var14 = 0; var14 < this.anIntArray776.length; ++var14) {
            if(this.anIntArray776[var14] == j) {
               var12 = var14;
               break;
            }
         }

         if(var12 == -1) {
            return null;
         }

         l1 = (long)((this.type << 6) + (var12 << 3) + l) + ((long)(k + 1) << 32);
         Model var15 = (Model)mruNodes2.insertFromCache(l1);
         if(var15 != null) {
            return var15;
         }

         model_3 = this.anIntArray773[var12];
         boolean var17 = this.aBoolean751 ^ l > 3;
         if(var17) {
            model_3 += 65536;
         }

         model = (Model)mruNodes1.insertFromCache((long)model_3);
         if(model == null) {
            model = Model.method462(model_3 & '\uffff');
            if(model == null) {
               return null;
            }

            if(var17) {
               model.method477();
            }

            mruNodes1.removeFromCache(model, (long)model_3);
         }
      }

      boolean var13 = this.anInt748 != 128 || this.anInt772 != 128 || this.anInt740 != 128;
      flag2 = this.anInt738 != 0 || this.anInt745 != 0 || this.anInt783 != 0;
      Model var16 = new Model(this.modifiedModelColors == null, Class36.method532(k), l == 0 && k == -1 && !var13 && !flag2, model);
      if(k != -1) {
         var16.method469();
         var16.method470(k);
         var16.anIntArrayArray1658 = (int[][])null;
         var16.anIntArrayArray1657 = (int[][])null;
      }

      while(l-- > 0) {
         var16.method473();
      }

      if(this.modifiedModelColors != null) {
         for(k2 = 0; k2 < this.modifiedModelColors.length; ++k2) {
            var16.method476(this.modifiedModelColors[k2], this.originalModelColors[k2]);
         }
      }

      if(var13) {
         var16.method478(this.anInt748, this.anInt740, this.anInt772);
      }

      if(flag2) {
         var16.method475(this.anInt738, this.anInt745, this.anInt783);
      }

      var16.method479(64 + this.aByte737, 768 + this.aByte742 * 5, -50, -10, -50, !this.aBoolean769);
      if(this.anInt760 == 1) {
         var16.anInt1654 = var16.modelHeight;
      }

      mruNodes2.removeFromCache(var16, l1);
      return var16;
   }

   private void readValues(Stream stream) {
      int i = -1;

      while(true) {
         int j = stream.readUnsignedByte();
         if(j == 0) {
            if(i == -1) {
               this.hasActions = this.anIntArray773 != null && (this.anIntArray776 == null || this.anIntArray776[0] == 10);
               if(this.actions != null) {
                  this.hasActions = true;
               }
            }

            if(this.aBoolean766) {
               this.aBoolean767 = false;
               this.aBoolean757 = false;
            }

            if(this.anInt760 == -1) {
               this.anInt760 = this.aBoolean767?1:0;
            }

            return;
         }

         int j1;
         int j2;
         if(j == 1) {
            j1 = stream.readUnsignedByte();
            if(j1 > 0) {
               if(this.anIntArray773 != null && !lowMem) {
                  stream.currentOffset += j1 * 3;
               } else {
                  this.anIntArray776 = new int[j1];
                  this.anIntArray773 = new int[j1];

                  for(j2 = 0; j2 < j1; ++j2) {
                     this.anIntArray773[j2] = stream.readUnsignedWord();
                     this.anIntArray776[j2] = stream.readUnsignedByte();
                  }
               }
            }
         } else if(j == 2) {
            this.name = stream.readString();
         } else if(j == 3) {
            this.description = stream.readBytes();
         } else if(j == 5) {
            j1 = stream.readUnsignedByte();
            if(j1 > 0) {
               if(this.anIntArray773 != null && !lowMem) {
                  stream.currentOffset += j1 * 2;
               } else {
                  this.anIntArray776 = null;
                  this.anIntArray773 = new int[j1];

                  for(j2 = 0; j2 < j1; ++j2) {
                     this.anIntArray773[j2] = stream.readUnsignedWord();
                  }
               }
            }
         } else if(j == 14) {
            this.anInt744 = stream.readUnsignedByte();
         } else if(j == 15) {
            this.anInt761 = stream.readUnsignedByte();
         } else if(j == 17) {
            this.aBoolean767 = false;
         } else if(j == 18) {
            this.aBoolean757 = false;
         } else if(j == 19) {
            i = stream.readUnsignedByte();
            if(i == 1) {
               this.hasActions = true;
            }
         } else if(j == 21) {
            this.aBoolean762 = true;
         } else if(j == 22) {
            this.aBoolean769 = true;
         } else if(j == 23) {
            this.aBoolean764 = true;
         } else if(j == 24) {
            this.anInt781 = stream.readUnsignedWord();
            if(this.anInt781 == '\uffff') {
               this.anInt781 = -1;
            }
         } else if(j == 28) {
            this.anInt775 = stream.readUnsignedByte();
         } else if(j == 29) {
            this.aByte737 = stream.readSignedByte();
         } else if(j == 39) {
            this.aByte742 = stream.readSignedByte();
         } else if(j >= 30 && j < 39) {
            if(this.actions == null) {
               this.actions = new String[5];
            }

            this.actions[j - 30] = stream.readString();
            if(this.actions[j - 30].equalsIgnoreCase("hidden")) {
               this.actions[j - 30] = null;
            }
         } else if(j == 40) {
            j1 = stream.readUnsignedByte();
            this.modifiedModelColors = new int[j1];
            this.originalModelColors = new int[j1];

            for(j2 = 0; j2 < j1; ++j2) {
               this.modifiedModelColors[j2] = stream.readUnsignedWord();
               this.originalModelColors[j2] = stream.readUnsignedWord();
            }
         } else if(j == 60) {
            this.anInt746 = stream.readUnsignedWord();
         } else if(j == 62) {
            this.aBoolean751 = true;
         } else if(j == 64) {
            this.aBoolean779 = false;
         } else if(j == 65) {
            this.anInt748 = stream.readUnsignedWord();
         } else if(j == 66) {
            this.anInt772 = stream.readUnsignedWord();
         } else if(j == 67) {
            this.anInt740 = stream.readUnsignedWord();
         } else if(j == 68) {
            this.anInt758 = stream.readUnsignedWord();
         } else if(j == 69) {
            this.anInt768 = stream.readUnsignedByte();
         } else if(j == 70) {
            this.anInt738 = stream.readSignedWord();
         } else if(j == 71) {
            this.anInt745 = stream.readSignedWord();
         } else if(j == 72) {
            this.anInt783 = stream.readSignedWord();
         } else if(j == 73) {
            this.aBoolean736 = true;
         } else if(j == 74) {
            this.aBoolean766 = true;
         } else if(j != 75) {
            if(j == 77) {
               this.anInt774 = stream.readUnsignedWord();
               if(this.anInt774 == '\uffff') {
                  this.anInt774 = -1;
               }

               this.anInt749 = stream.readUnsignedWord();
               if(this.anInt749 == '\uffff') {
                  this.anInt749 = -1;
               }

               j1 = stream.readUnsignedByte();
               this.childrenIDs = new int[j1 + 1];

               for(j2 = 0; j2 <= j1; ++j2) {
                  this.childrenIDs[j2] = stream.readUnsignedWord();
                  if(this.childrenIDs[j2] == '\uffff') {
                     this.childrenIDs[j2] = -1;
                  }
               }
            }
         } else {
            this.anInt760 = stream.readUnsignedByte();
         }
      }
   }

}
