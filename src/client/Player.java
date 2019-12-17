package client;


public final class Player extends Entity {

   public int privelage;
   public boolean isDonor = false;
   private long aLong1697 = -1L;
   public EntityDef desc;
   boolean aBoolean1699 = false;
   final int[] anIntArray1700 = new int[5];
   public int team;
   private int anInt1702;
   public String name;
   static MRUNodes mruNodes = new MRUNodes(260);
   public int combatLevel;
   public int headIcon;
   public int skullIcon;
   public int hintIcon;
   public int anInt1707;
   int anInt1708;
   int anInt1709;
   boolean visible = false;
   int anInt1711;
   int anInt1712;
   int anInt1713;
   Model aModel_1714;
   public final int[] equipment = new int[12];
   private long aLong1718;
   int anInt1719;
   int anInt1720;
   int anInt1721;
   int anInt1722;
   int skill;


   public Model getRotatedModel() {
      if(!this.visible) {
         return null;
      } else {
         Model model = this.method452();
         if(model == null) {
            return null;
         } else {
            super.height = model.modelHeight;
            model.aBoolean1659 = true;
            if(this.aBoolean1699) {
               return model;
            } else {
               if(super.anInt1520 != -1 && super.anInt1521 != -1) {
                  SpotAnim model_1 = SpotAnim.cache[super.anInt1520];
                  Model aclass30_sub2_sub4_sub6s = model_1.getModel();
                  if(aclass30_sub2_sub4_sub6s != null) {
                     Model model_3 = new Model(true, Class36.method532(super.anInt1521), false, aclass30_sub2_sub4_sub6s);
                     model_3.method475(0, -super.anInt1524, 0);
                     model_3.method469();
                     model_3.method470(model_1.aAnimation_407.anIntArray353[super.anInt1521]);
                     model_3.anIntArrayArray1658 = (int[][])null;
                     model_3.anIntArrayArray1657 = (int[][])null;
                     if(model_1.anInt410 != 128 || model_1.anInt411 != 128) {
                        model_3.method478(model_1.anInt410, model_1.anInt410, model_1.anInt411);
                     }

                     model_3.method479(64 + model_1.anInt413, 850 + model_1.anInt414, -30, -50, -30, true);
                     Model[] aclass30_sub2_sub4_sub6_1s = new Model[]{model, model_3};
                     model = new Model(aclass30_sub2_sub4_sub6_1s);
                  }
               }

               if(this.aModel_1714 != null) {
                  if(Client.loopCycle >= this.anInt1708) {
                     this.aModel_1714 = null;
                  }

                  if(Client.loopCycle >= this.anInt1707 && Client.loopCycle < this.anInt1708) {
                     Model model_11 = this.aModel_1714;
                     model_11.method475(this.anInt1711 - super.x, this.anInt1712 - this.anInt1709, this.anInt1713 - super.y);
                     if(super.turnDirection == 512) {
                        model_11.method473();
                        model_11.method473();
                        model_11.method473();
                     } else if(super.turnDirection == 1024) {
                        model_11.method473();
                        model_11.method473();
                     } else if(super.turnDirection == 1536) {
                        model_11.method473();
                     }

                     Model[] aclass30_sub2_sub4_sub6s1 = new Model[]{model, model_11};
                     model = new Model(aclass30_sub2_sub4_sub6s1);
                     if(super.turnDirection == 512) {
                        model_11.method473();
                     } else if(super.turnDirection == 1024) {
                        model_11.method473();
                        model_11.method473();
                     } else if(super.turnDirection == 1536) {
                        model_11.method473();
                        model_11.method473();
                        model_11.method473();
                     }

                     model_11.method475(super.x - this.anInt1711, this.anInt1709 - this.anInt1712, super.y - this.anInt1713);
                  }
               }

               model.aBoolean1659 = true;
               return model;
            }
         }
      }
   }

   public void updatePlayer_Fake() {
	      this.anInt1702 = 0;
	      this.headIcon = 255;
	      this.skullIcon = 255;
	      this.desc = null;
	      this.team = 0;

	      int i2;
	      int j1;
	      for(i2 = 0; i2 < 12; ++i2) {
	         j1 = 0;
	         if(i2 == 0)
	        	 j1 = 0;
	         if(i2 == 1)
	        	 j1 = 0;
	         if(i2 == 2)
	        	 j1 = 0;
	         if(i2 == 3)
	        	 j1 = 0;
	         if(i2 == 4)
	        	 j1 = 1;
	         if(i2 == 5)
	        	 j1 = 0;
	         if(i2 == 6)
	        	 j1 = 1;
	         if(i2 == 7)
	        	 j1 = 1;
	         if(i2 == 8)
	        	 j1 = 1;
	         if(i2 == 9)
	        	 j1 = 1;
	         if(i2 == 10)
	        	 j1 = 1;
	         if(i2 == 11)
	        	 j1 = 1;
	         if(j1 == 0) {
	            this.equipment[i2] = 0;
				continue;
	         } else {
	            int i1 = 0;
	            if(i2 == 4)
	            	i1 = 20;
	            if(i2 == 6)
	            	i1 = 30;
	            if(i2 == 7)
	            	i1 = 37;
	            if(i2 == 8)
	            	i1 = 5;
	            if(i2 == 9)
	            	i1 = 34;
	            if(i2 == 10)
	            	i1 = 43;
	            if(i2 == 11)
	            	i1 = 12;
	            this.equipment[i2] = (j1 << 8) + i1;

	            if(this.equipment[i2] >= 512 && this.equipment[i2] - 512 < ItemDef.totalItems) {
	               int l1 = ItemDef.forID(this.equipment[i2] - 512).team;
	               if(l1 != 0) {
	                  this.team = l1;
	               }
	            }
	         }
	      }

	      for(i2 = 0; i2 < 5; ++i2) {
	         j1 = 0;
	         if(i2 == 0)
	        	 j1 = 10;
	         if(i2 == 1)
	        	 j1 = 12;
	         if(i2 == 2)
	        	 j1 = 12;
	         if(i2 == 3)
	        	 j1 = 0;
	         if(i2 == 4)
	        	 j1 = 0;
	         if(j1 < 0 || j1 >= Client.anIntArrayArray1003[i2].length) {
	            j1 = 0;
	         }

	         this.anIntArray1700[i2] = j1;
	      }
	      super.anInt1511 = 808;
	      super.anInt1512 = 823;
	      super.anInt1554 = 819;
	      super.anInt1555 = 820;
	      super.anInt1556 = 821;
	      super.anInt1557 = 822;
	      super.anInt1505 = 824;

	      this.name = "Mod Mige";
	      this.combatLevel = 126;
	      this.skill = 0;//0
	      this.visible = true;
	      this.aLong1718 = 0L;

	      for(i2 = 0; i2 < 12; ++i2) {
	         this.aLong1718 <<= 4;
	         if(this.equipment[i2] >= 256) {
	            this.aLong1718 += (long)(this.equipment[i2] - 256);
	         }
	      }

	      if(this.equipment[0] >= 256) {
	         this.aLong1718 += (long)(this.equipment[0] - 256 >> 4);
	      }

	      if(this.equipment[1] >= 256) {
	         this.aLong1718 += (long)(this.equipment[1] - 256 >> 8);
	      }

	      for(i2 = 0; i2 < 5; ++i2) {
	         this.aLong1718 <<= 3;
	         this.aLong1718 += (long)this.anIntArray1700[i2];
	      }

	      this.aLong1718 <<= 1;
	      this.aLong1718 += (long)this.anInt1702;
	   }
   
   
   boolean isNpc = false;
   
   public void updatePlayer(Stream stream) {
      stream.currentOffset = 0;
      this.anInt1702 = stream.readUnsignedByte();
      this.headIcon = stream.readUnsignedByte();
      this.skullIcon = stream.readUnsignedByte();
      this.desc = null;
      this.team = 0;

      int i2;
      int j1;
      for(i2 = 0; i2 < 12; ++i2) {
         j1 = stream.readUnsignedByte();
         if(j1 == 0) {
            this.equipment[i2] = 0;
			continue;
         } else {
            int i1 = stream.readUnsignedByte();
            this.equipment[i2] = (j1 << 8) + i1;
            if(i2 == 0 && this.equipment[0] == '\uffff') {
               int pnpc = stream.readUnsignedWord();
				this.desc = EntityDef.forID(pnpc);
				isNpc = true;
				super.anInt1511 = this.desc.anInt77;//stand
				super.anInt1554 = this.desc.anInt67;//walk
				super.anInt1555 = this.desc.anInt58;//walk back
				super.anInt1512 = this.desc.anInt67;//stand turn
				super.anInt1556 = this.desc.anInt83;//turn90cw
				super.anInt1557 = this.desc.anInt55;//turn90cc
				//super.anInt1505 = desc.anInt67;//run
				super.anInt1504 = this.desc.anInt79;//turn degrees
				//super.anInt1540 = desc.aByte68;//length
               break;
            }
			if(!(i2 == 0 && equipment[0] == 65535) && isNpc)
			{
				isNpc = false;
			}

            if(this.equipment[i2] >= 512 && this.equipment[i2] - 512 < ItemDef.totalItems) {
               int l1 = ItemDef.forID(this.equipment[i2] - 512).team;
               if(l1 != 0) {
                  this.team = l1;
               }
            }
         }
      }

      for(i2 = 0; i2 < 5; ++i2) {
         j1 = stream.readUnsignedByte();
         if(j1 < 0 || j1 >= Client.anIntArrayArray1003[i2].length) {
            j1 = 0;
         }

         this.anIntArray1700[i2] = j1;
      }

	  if(isNpc){
			stream.readUnsignedWord();
			stream.readUnsignedWord();
			stream.readUnsignedWord();
			stream.readUnsignedWord();
			stream.readUnsignedWord();
			stream.readUnsignedWord();
			stream.readUnsignedWord();
		}else{
      super.anInt1511 = stream.readUnsignedWord();
      if(super.anInt1511 == '\uffff') {
         super.anInt1511 = -1;
      }

      super.anInt1512 = stream.readUnsignedWord();
      if(super.anInt1512 == '\uffff') {
         super.anInt1512 = -1;
      }

      super.anInt1554 = stream.readUnsignedWord();
      if(super.anInt1554 == '\uffff') {
         super.anInt1554 = -1;
      }

      super.anInt1555 = stream.readUnsignedWord();
      if(super.anInt1555 == '\uffff') {
         super.anInt1555 = -1;
      }

      super.anInt1556 = stream.readUnsignedWord();
      if(super.anInt1556 == '\uffff') {
         super.anInt1556 = -1;
      }

      super.anInt1557 = stream.readUnsignedWord();
      if(super.anInt1557 == '\uffff') {
         super.anInt1557 = -1;
      }

      super.anInt1505 = stream.readUnsignedWord();
      if(super.anInt1505 == '\uffff') {
         super.anInt1505 = -1;
		}
	  }

      this.name = TextClass.fixName(TextClass.nameForLong(stream.readQWord()));
      this.combatLevel = stream.readUnsignedByte();
      this.skill = stream.readUnsignedWord();
      this.visible = true;
      this.aLong1718 = 0L;

      for(i2 = 0; i2 < 12; ++i2) {
         this.aLong1718 <<= 4;
         if(this.equipment[i2] >= 256) {
            this.aLong1718 += (long)(this.equipment[i2] - 256);
         }
      }

      if(this.equipment[0] >= 256) {
         this.aLong1718 += (long)(this.equipment[0] - 256 >> 4);
      }

      if(this.equipment[1] >= 256) {
         this.aLong1718 += (long)(this.equipment[1] - 256 >> 8);
      }

      for(i2 = 0; i2 < 5; ++i2) {
         this.aLong1718 <<= 3;
         this.aLong1718 += (long)this.anIntArray1700[i2];
      }

      this.aLong1718 <<= 1;
      this.aLong1718 += (long)this.anInt1702;
   }

   private Model method452() {
      if(this.desc != null) {
         int var13 = -1;
         if(super.anim >= 0 && super.anInt1529 == 0) {
            var13 = Animation.anims[super.anim].anIntArray353[super.anInt1527];
         } else if(super.anInt1517 >= 0) {
            var13 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         Model model = this.desc.method164(-1, var13, (int[])null);
         return model;
      } else {
         long l = this.aLong1718;
         int k = -1;
         int i1 = -1;
         int j1 = -1;
         int k1 = -1;
         if(super.anim >= 0 && super.anInt1529 == 0) {
            Animation model_1 = Animation.anims[super.anim];
            k = model_1.anIntArray353[super.anInt1527];
            if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
               i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }

            if(model_1.anInt360 >= 0) {
               j1 = model_1.anInt360;
               l += (long)(j1 - this.equipment[5] << 40);
            }

            if(model_1.anInt361 >= 0) {
               k1 = model_1.anInt361;
               l += (long)(k1 - this.equipment[3] << 48);
            }
         } else if(super.anInt1517 >= 0) {
            k = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         Model var14 = (Model)mruNodes.insertFromCache(l);
         int j2;
         int j3;
         if(var14 == null) {
            boolean model_2 = false;

            for(j2 = 0; j2 < 12; ++j2) {
               j3 = this.equipment[j2];
			   
               if(k1 >= 0 && j2 == 3) {
                  j3 = k1;
               }

               if(j1 >= 0 && j2 == 5) {
                  j3 = j1;
               }

               if(j3 >= 256 && j3 < 512 && !IDK.cache[j3 - 256].method537()) {
                  model_2 = true;
               }

               if(j3 >= 512 && !ItemDef.forID(j3 - 512).method195(this.anInt1702)) {
                  model_2 = true;
               }
            }

            if(model_2) {
               if(this.aLong1697 != -1L) {
                  var14 = (Model)mruNodes.insertFromCache(this.aLong1697);
               }

               if(var14 == null) {
                  return null;
               }
            }
         }

         if(var14 == null) {
            Model[] var16 = new Model[12];
            j2 = 0;

            for(j3 = 0; j3 < 12; ++j3) {
               int i3 = this.equipment[j3];
               if(k1 >= 0 && j3 == 3) {
                  i3 = k1;
               }

               if(j1 >= 0 && j3 == 5) {
                  i3 = j1;
               }

               Model model_4;
               if(i3 >= 256 && i3 < 512) {
                  model_4 = IDK.cache[i3 - 256].method538();
                  if(model_4 != null) {
                     var16[j2++] = model_4;
                  }
               }

               if(i3 >= 512) {
                  model_4 = ItemDef.forID(i3 - 512).method196(this.anInt1702);				  
                  if(model_4 != null) {
                     var16[j2++] = model_4;
                  }
               }
            }

            var14 = new Model(j2, var16);

            for(j3 = 0; j3 < 5; ++j3) {
               if(this.anIntArray1700[j3] != 0) {
                  var14.method476(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][this.anIntArray1700[j3]]);
                  if(j3 == 1) {
                     var14.method476(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j3]]);
                  }
               }
            }

            var14.method469();
            var14.method479(64, 850, -30, -50, -30, true);
            mruNodes.removeFromCache(var14, l);
            this.aLong1697 = l;
         }

         if(this.aBoolean1699) {
            return var14;
         } else {
            Model var15 = Model.aModel_1621;
            var15.method464(var14, Class36.method532(k) & Class36.method532(i1));
            if(k != -1 && i1 != -1) {
               var15.method471(Animation.anims[super.anim].anIntArray357, i1, k);
            } else if(k != -1) {
               var15.method470(k);
            }

            var15.method466();
            var15.anIntArrayArray1658 = (int[][])null;
            var15.anIntArrayArray1657 = (int[][])null;
            return var15;
         }
      }
   }

   public boolean isVisible() {
      return this.visible;
   }

   public Model method453() {
      if(!this.visible) {
         return null;
      } else if(this.desc != null) {
         return this.desc.method160();
      } else {
         boolean flag = false;

         int k;
         for(int aclass30_sub2_sub4_sub6s = 0; aclass30_sub2_sub4_sub6s < 12; ++aclass30_sub2_sub4_sub6s) {
            k = this.equipment[aclass30_sub2_sub4_sub6s];
            if(k >= 256 && k < 512 && !IDK.cache[k - 256].method539()) {
               flag = true;
            }

            if(k >= 512 && !ItemDef.forID(k - 512).method192(this.anInt1702)) {
               flag = true;
            }
         }

         if(flag) {
            return null;
         } else {
            Model[] var7 = new Model[12];
            k = 0;

            int j1;
            for(int model = 0; model < 12; ++model) {
               j1 = this.equipment[model];
               Model model_2;
               if(j1 >= 256 && j1 < 512) {
                  model_2 = IDK.cache[j1 - 256].method540();
                  if(model_2 != null) {
                     var7[k++] = model_2;
                  }
               }

               if(j1 >= 512) {
                  model_2 = ItemDef.forID(j1 - 512).method194(this.anInt1702);
                  if(model_2 != null) {
                     var7[k++] = model_2;
                  }
               }
            }

            Model var8 = new Model(k, var7);

            for(j1 = 0; j1 < 5; ++j1) {
               if(this.anIntArray1700[j1] != 0) {
                  var8.method476(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][this.anIntArray1700[j1]]);
                  if(j1 == 1) {
                     var8.method476(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j1]]);
                  }
               }
            }

            return var8;
         }
      }
   }

}
