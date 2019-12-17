package client;


final class CollisionMap {

   private final int anInt290 = 0;
   private final int anInt291 = 0;
   private final int anInt292 = 104;
   private final int anInt293 = 104;
   public final int[][] anIntArrayArray294;


   public CollisionMap() {
      this.anIntArrayArray294 = new int[this.anInt292][this.anInt293];
      this.method210();
   }

   public void method210() {
      for(int i = 0; i < this.anInt292; ++i) {
         for(int j = 0; j < this.anInt293; ++j) {
            if(i != 0 && j != 0 && i != this.anInt292 - 1 && j != this.anInt293 - 1) {
               this.anIntArrayArray294[i][j] = 16777216;
            } else {
               this.anIntArrayArray294[i][j] = 16777215;
            }
         }
      }

   }

   public void method211(int i, int j, int k, int l, boolean flag) {
      k -= this.anInt290;
      i -= this.anInt291;
      if(l == 0) {
         if(j == 0) {
            this.method214(k, i, 128);
            this.method214(k - 1, i, 8);
         }

         if(j == 1) {
            this.method214(k, i, 2);
            this.method214(k, i + 1, 32);
         }

         if(j == 2) {
            this.method214(k, i, 8);
            this.method214(k + 1, i, 128);
         }

         if(j == 3) {
            this.method214(k, i, 32);
            this.method214(k, i - 1, 2);
         }
      }

      if(l == 1 || l == 3) {
         if(j == 0) {
            this.method214(k, i, 1);
            this.method214(k - 1, i + 1, 16);
         }

         if(j == 1) {
            this.method214(k, i, 4);
            this.method214(k + 1, i + 1, 64);
         }

         if(j == 2) {
            this.method214(k, i, 16);
            this.method214(k + 1, i - 1, 1);
         }

         if(j == 3) {
            this.method214(k, i, 64);
            this.method214(k - 1, i - 1, 4);
         }
      }

      if(l == 2) {
         if(j == 0) {
            this.method214(k, i, 130);
            this.method214(k - 1, i, 8);
            this.method214(k, i + 1, 32);
         }

         if(j == 1) {
            this.method214(k, i, 10);
            this.method214(k, i + 1, 32);
            this.method214(k + 1, i, 128);
         }

         if(j == 2) {
            this.method214(k, i, 40);
            this.method214(k + 1, i, 128);
            this.method214(k, i - 1, 2);
         }

         if(j == 3) {
            this.method214(k, i, 160);
            this.method214(k, i - 1, 2);
            this.method214(k - 1, i, 8);
         }
      }

      if(flag) {
         if(l == 0) {
            if(j == 0) {
               this.method214(k, i, 65536);
               this.method214(k - 1, i, 4096);
            }

            if(j == 1) {
               this.method214(k, i, 1024);
               this.method214(k, i + 1, 16384);
            }

            if(j == 2) {
               this.method214(k, i, 4096);
               this.method214(k + 1, i, 65536);
            }

            if(j == 3) {
               this.method214(k, i, 16384);
               this.method214(k, i - 1, 1024);
            }
         }

         if(l == 1 || l == 3) {
            if(j == 0) {
               this.method214(k, i, 512);
               this.method214(k - 1, i + 1, 8192);
            }

            if(j == 1) {
               this.method214(k, i, 2048);
               this.method214(k + 1, i + 1, '\u8000');
            }

            if(j == 2) {
               this.method214(k, i, 8192);
               this.method214(k + 1, i - 1, 512);
            }

            if(j == 3) {
               this.method214(k, i, '\u8000');
               this.method214(k - 1, i - 1, 2048);
            }
         }

         if(l == 2) {
            if(j == 0) {
               this.method214(k, i, 66560);
               this.method214(k - 1, i, 4096);
               this.method214(k, i + 1, 16384);
            }

            if(j == 1) {
               this.method214(k, i, 5120);
               this.method214(k, i + 1, 16384);
               this.method214(k + 1, i, 65536);
            }

            if(j == 2) {
               this.method214(k, i, 20480);
               this.method214(k + 1, i, 65536);
               this.method214(k, i - 1, 1024);
            }

            if(j == 3) {
               this.method214(k, i, 81920);
               this.method214(k, i - 1, 1024);
               this.method214(k - 1, i, 4096);
            }
         }
      }

   }

   public void method212(boolean flag, int j, int k, int l, int i1, int j1) {
      int k1 = 256;
      if(flag) {
         k1 += 131072;
      }

      l -= this.anInt290;
      i1 -= this.anInt291;
      int i2;
      if(j1 == 1 || j1 == 3) {
         i2 = j;
         j = k;
         k = i2;
      }

      for(i2 = l; i2 < l + j; ++i2) {
         if(i2 >= 0 && i2 < this.anInt292) {
            for(int j2 = i1; j2 < i1 + k; ++j2) {
               if(j2 >= 0 && j2 < this.anInt293) {
                  this.method214(i2, j2, k1);
               }
            }
         }
      }

   }

   public void method213(int i, int k) {
      k -= this.anInt290;
      i -= this.anInt291;
      this.anIntArrayArray294[k][i] |= 2097152;
   }

   private void method214(int i, int j, int k) {
      this.anIntArrayArray294[i][j] |= k;
   }

   public void method215(int i, int j, boolean flag, int k, int l) {
      k -= this.anInt290;
      l -= this.anInt291;
      if(j == 0) {
         if(i == 0) {
            this.method217(128, k, l);
            this.method217(8, k - 1, l);
         }

         if(i == 1) {
            this.method217(2, k, l);
            this.method217(32, k, l + 1);
         }

         if(i == 2) {
            this.method217(8, k, l);
            this.method217(128, k + 1, l);
         }

         if(i == 3) {
            this.method217(32, k, l);
            this.method217(2, k, l - 1);
         }
      }

      if(j == 1 || j == 3) {
         if(i == 0) {
            this.method217(1, k, l);
            this.method217(16, k - 1, l + 1);
         }

         if(i == 1) {
            this.method217(4, k, l);
            this.method217(64, k + 1, l + 1);
         }

         if(i == 2) {
            this.method217(16, k, l);
            this.method217(1, k + 1, l - 1);
         }

         if(i == 3) {
            this.method217(64, k, l);
            this.method217(4, k - 1, l - 1);
         }
      }

      if(j == 2) {
         if(i == 0) {
            this.method217(130, k, l);
            this.method217(8, k - 1, l);
            this.method217(32, k, l + 1);
         }

         if(i == 1) {
            this.method217(10, k, l);
            this.method217(32, k, l + 1);
            this.method217(128, k + 1, l);
         }

         if(i == 2) {
            this.method217(40, k, l);
            this.method217(128, k + 1, l);
            this.method217(2, k, l - 1);
         }

         if(i == 3) {
            this.method217(160, k, l);
            this.method217(2, k, l - 1);
            this.method217(8, k - 1, l);
         }
      }

      if(flag) {
         if(j == 0) {
            if(i == 0) {
               this.method217(65536, k, l);
               this.method217(4096, k - 1, l);
            }

            if(i == 1) {
               this.method217(1024, k, l);
               this.method217(16384, k, l + 1);
            }

            if(i == 2) {
               this.method217(4096, k, l);
               this.method217(65536, k + 1, l);
            }

            if(i == 3) {
               this.method217(16384, k, l);
               this.method217(1024, k, l - 1);
            }
         }

         if(j == 1 || j == 3) {
            if(i == 0) {
               this.method217(512, k, l);
               this.method217(8192, k - 1, l + 1);
            }

            if(i == 1) {
               this.method217(2048, k, l);
               this.method217('\u8000', k + 1, l + 1);
            }

            if(i == 2) {
               this.method217(8192, k, l);
               this.method217(512, k + 1, l - 1);
            }

            if(i == 3) {
               this.method217('\u8000', k, l);
               this.method217(2048, k - 1, l - 1);
            }
         }

         if(j == 2) {
            if(i == 0) {
               this.method217(66560, k, l);
               this.method217(4096, k - 1, l);
               this.method217(16384, k, l + 1);
            }

            if(i == 1) {
               this.method217(5120, k, l);
               this.method217(16384, k, l + 1);
               this.method217(65536, k + 1, l);
            }

            if(i == 2) {
               this.method217(20480, k, l);
               this.method217(65536, k + 1, l);
               this.method217(1024, k, l - 1);
            }

            if(i == 3) {
               this.method217(81920, k, l);
               this.method217(1024, k, l - 1);
               this.method217(4096, k - 1, l);
            }
         }
      }

   }

   public void method216(int i, int j, int k, int l, int i1, boolean flag) {
      int j1 = 256;
      if(flag) {
         j1 += 131072;
      }

      k -= this.anInt290;
      l -= this.anInt291;
      int l1;
      if(i == 1 || i == 3) {
         l1 = j;
         j = i1;
         i1 = l1;
      }

      for(l1 = k; l1 < k + j; ++l1) {
         if(l1 >= 0 && l1 < this.anInt292) {
            for(int i2 = l; i2 < l + i1; ++i2) {
               if(i2 >= 0 && i2 < this.anInt293) {
                  this.method217(j1, l1, i2);
               }
            }
         }
      }

   }

   private void method217(int i, int j, int k) {
      this.anIntArrayArray294[j][k] &= 16777215 - i;
   }

   public void method218(int j, int k) {
      k -= this.anInt290;
      j -= this.anInt291;
      this.anIntArrayArray294[k][j] &= 14680063;
   }

   public boolean method219(int i, int j, int k, int i1, int j1, int k1) {
      if(j == i && k == k1) {
         return true;
      } else {
         j -= this.anInt290;
         k -= this.anInt291;
         i -= this.anInt290;
         k1 -= this.anInt291;
         if(j1 == 0) {
            if(i1 == 0) {
               if(j == i - 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 + 1 && (this.anIntArrayArray294[j][k] & 19398944) == 0) {
                  return true;
               }

               if(j == i && k == k1 - 1 && (this.anIntArrayArray294[j][k] & 19398914) == 0) {
                  return true;
               }
            } else if(i1 == 1) {
               if(j == i && k == k1 + 1) {
                  return true;
               }

               if(j == i - 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19398920) == 0) {
                  return true;
               }

               if(j == i + 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19399040) == 0) {
                  return true;
               }
            } else if(i1 == 2) {
               if(j == i + 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 + 1 && (this.anIntArrayArray294[j][k] & 19398944) == 0) {
                  return true;
               }

               if(j == i && k == k1 - 1 && (this.anIntArrayArray294[j][k] & 19398914) == 0) {
                  return true;
               }
            } else if(i1 == 3) {
               if(j == i && k == k1 - 1) {
                  return true;
               }

               if(j == i - 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19398920) == 0) {
                  return true;
               }

               if(j == i + 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19399040) == 0) {
                  return true;
               }
            }
         }

         if(j1 == 2) {
            if(i1 == 0) {
               if(j == i - 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 + 1) {
                  return true;
               }

               if(j == i + 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19399040) == 0) {
                  return true;
               }

               if(j == i && k == k1 - 1 && (this.anIntArrayArray294[j][k] & 19398914) == 0) {
                  return true;
               }
            } else if(i1 == 1) {
               if(j == i - 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19398920) == 0) {
                  return true;
               }

               if(j == i && k == k1 + 1) {
                  return true;
               }

               if(j == i + 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 - 1 && (this.anIntArrayArray294[j][k] & 19398914) == 0) {
                  return true;
               }
            } else if(i1 == 2) {
               if(j == i - 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19398920) == 0) {
                  return true;
               }

               if(j == i && k == k1 + 1 && (this.anIntArrayArray294[j][k] & 19398944) == 0) {
                  return true;
               }

               if(j == i + 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 - 1) {
                  return true;
               }
            } else if(i1 == 3) {
               if(j == i - 1 && k == k1) {
                  return true;
               }

               if(j == i && k == k1 + 1 && (this.anIntArrayArray294[j][k] & 19398944) == 0) {
                  return true;
               }

               if(j == i + 1 && k == k1 && (this.anIntArrayArray294[j][k] & 19399040) == 0) {
                  return true;
               }

               if(j == i && k == k1 - 1) {
                  return true;
               }
            }
         }

         if(j1 == 9) {
            if(j == i && k == k1 + 1 && (this.anIntArrayArray294[j][k] & 32) == 0) {
               return true;
            }

            if(j == i && k == k1 - 1 && (this.anIntArrayArray294[j][k] & 2) == 0) {
               return true;
            }

            if(j == i - 1 && k == k1 && (this.anIntArrayArray294[j][k] & 8) == 0) {
               return true;
            }

            if(j == i + 1 && k == k1 && (this.anIntArrayArray294[j][k] & 128) == 0) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean method220(int i, int j, int k, int l, int i1, int j1) {
      if(j1 == i && k == j) {
         return true;
      } else {
         j1 -= this.anInt290;
         k -= this.anInt291;
         i -= this.anInt290;
         j -= this.anInt291;
         if(l == 6 || l == 7) {
            if(l == 7) {
               i1 = i1 + 2 & 3;
            }

            if(i1 == 0) {
               if(j1 == i + 1 && k == j && (this.anIntArrayArray294[j1][k] & 128) == 0) {
                  return true;
               }

               if(j1 == i && k == j - 1 && (this.anIntArrayArray294[j1][k] & 2) == 0) {
                  return true;
               }
            } else if(i1 == 1) {
               if(j1 == i - 1 && k == j && (this.anIntArrayArray294[j1][k] & 8) == 0) {
                  return true;
               }

               if(j1 == i && k == j - 1 && (this.anIntArrayArray294[j1][k] & 2) == 0) {
                  return true;
               }
            } else if(i1 == 2) {
               if(j1 == i - 1 && k == j && (this.anIntArrayArray294[j1][k] & 8) == 0) {
                  return true;
               }

               if(j1 == i && k == j + 1 && (this.anIntArrayArray294[j1][k] & 32) == 0) {
                  return true;
               }
            } else if(i1 == 3) {
               if(j1 == i + 1 && k == j && (this.anIntArrayArray294[j1][k] & 128) == 0) {
                  return true;
               }

               if(j1 == i && k == j + 1 && (this.anIntArrayArray294[j1][k] & 32) == 0) {
                  return true;
               }
            }
         }

         if(l == 8) {
            if(j1 == i && k == j + 1 && (this.anIntArrayArray294[j1][k] & 32) == 0) {
               return true;
            }

            if(j1 == i && k == j - 1 && (this.anIntArrayArray294[j1][k] & 2) == 0) {
               return true;
            }

            if(j1 == i - 1 && k == j && (this.anIntArrayArray294[j1][k] & 8) == 0) {
               return true;
            }

            if(j1 == i + 1 && k == j && (this.anIntArrayArray294[j1][k] & 128) == 0) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean method221(int i, int j, int k, int l, int i1, int j1, int k1) {
      int l1 = j + j1 - 1;
      int i2 = i + l - 1;
      return k >= j && k <= l1 && k1 >= i && k1 <= i2?true:(k == j - 1 && k1 >= i && k1 <= i2 && (this.anIntArrayArray294[k - this.anInt290][k1 - this.anInt291] & 8) == 0 && (i1 & 8) == 0?true:(k == l1 + 1 && k1 >= i && k1 <= i2 && (this.anIntArrayArray294[k - this.anInt290][k1 - this.anInt291] & 128) == 0 && (i1 & 2) == 0?true:k1 == i - 1 && k >= j && k <= l1 && (this.anIntArrayArray294[k - this.anInt290][k1 - this.anInt291] & 2) == 0 && (i1 & 4) == 0 || k1 == i2 + 1 && k >= j && k <= l1 && (this.anIntArrayArray294[k - this.anInt290][k1 - this.anInt291] & 32) == 0 && (i1 & 1) == 0));
   }
}
